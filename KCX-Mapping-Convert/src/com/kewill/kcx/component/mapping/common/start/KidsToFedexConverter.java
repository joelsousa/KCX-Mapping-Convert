package com.kewill.kcx.component.mapping.common.start;

/*
 * Function    : KidsToUids.java
 * Titel       :
 * Date        : 02.09.2008
 * Author      : Kewill CSF / Christine Kron
 * Description : transformer called by Mule 
 * 				 to convert KIDS-Format to UIDS messages
 * 			   : 
 * Parameters  : 

 * Changes 
 * -----------
 * Author      : Sven Heise
 * Date        : 08.09.2008
 * Label       : 
 * Description : InternalStatusInformation, Cancellation, Completion (25.09.08)
 *
 */

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.EDirections;
import com.kewill.kcx.component.mapping.common.CommonFieldsDTO;
import com.kewill.kcx.component.mapping.companies.fedex.ics.FedexEnvelope;
import com.kewill.kcx.component.mapping.companies.fedex.ics.KidsToFedexICS;
import com.kewill.kcx.component.mapping.db.CustomerDataDTO;
import com.kewill.kcx.component.mapping.db.CustomerProcedureDTO;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.util.Utils;


/**
 * Module       : KidsToFedexConverter<br>
 * Created 		: 05.11.2008<br>
 * Description  : transformer called by Mule 
 *                to convert KIDS-Format to Fedex messages.
 * 
 * @author kron
 * @version 1.0.00
 */
public abstract class KidsToFedexConverter {
    /**
     * Christine Kron: Muss hier definiert sein wg. filename-Aenderung bei tgz-Dateien
     */
    protected CommonFieldsDTO commonFieldsDTO		= null;

    
	public Object readKids(String payload, String encoding, String filename, EDirections direction) 
	                                                                                throws Exception {
        // MS20110930 Begin
//        String content = removeKcxEnvelope(payload, encoding);
        String content = new RemoveKcxEnvelope().removeEnvelope(payload, encoding);
        // MS20110930 End

        InputStream ins = new ByteArrayInputStream(content.getBytes());
        InputStreamReader is = new InputStreamReader(ins, encoding);
        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLEventReader parser = factory.createXMLEventReader(is);
        
        return readKids(parser, encoding, filename, direction);
	}
	
    public Object readKids(XMLEventReader parser, String encoding, String filename, EDirections direction) 
                                                                                      throws Exception {
        KidsHeader kidsHeader = getKidsHeader(parser);
        Utils.log("(KidsToFedexConverter readKids) kidsHeader.getMessageName() = " + kidsHeader.getMessageName());
		
        FedexEnvelope fedexEnvelope = new FedexEnvelope();
        
        String localIdFedex = null;
        //String localIdKids = null;
        String kcxId = kidsHeader.getReceiver();
        //localIdKids = Utils.getCustomerIdFromKewill(kcxId, "KIDS", kidsHeader.getCountryCode()).trim();
        //kidsHeader.setTransmitter(localIdKids); 
        localIdFedex = Utils.getCustomerIdFromKewill(kcxId, "FEDEX", kidsHeader.getCountryCode()).trim();
        kidsHeader.setTransmitter(localIdFedex);  //AK20120419
        kidsHeader.setReceiver(localIdFedex);
        //Utils.log("(KidsToFedexConverter readKids) localIdKids = " + localIdKids + " localIdFedex = " + localIdFedex);
        
        commonFieldsDTO = new CommonFieldsDTO();
        commonFieldsDTO.setKcxId(kcxId);
        commonFieldsDTO.setUidsId(localIdFedex);
        CustomerProcedureDTO customerProcedureDTO = Utils.getCustomerProceduresFromKcxId(
        		kcxId, 
                                                        kidsHeader.getProcedure().toUpperCase());
        commonFieldsDTO.setCustomerProcedureDTO(customerProcedureDTO);
        CustomerDataDTO customerDataDTO = Utils.getCustomerDataFromKcxId(kcxId);
        commonFieldsDTO.setCustomerDataDTO(customerDataDTO);
        commonFieldsDTO.setCountryCode(kidsHeader.getCountryCode());
        commonFieldsDTO.setFilename(filename);
        // MS20101018
        if (Utils.isStringEmpty(kidsHeader.getInReplyTo())) {
            commonFieldsDTO.setMessageReferenceNumber(kidsHeader.getMessageID());
        } else {
            commonFieldsDTO.setMessageReferenceNumber(kidsHeader.getInReplyTo());
        }
        commonFieldsDTO.setDirection(direction);
        
        String result = null;
        String procedure = kidsHeader.getProcedure().toUpperCase();
        String version = kidsHeader.getRelease();
        String msgCode = "";
        if (version == null) {
            version = "10";
        } else  {
            version = Utils.removeDots(version.substring(0, 3));
        }
        switch (EKidsProcedureVersions.valueOf("K" + version + procedure)) {
              case K10ICS:
              case K20ICS:
                commonFieldsDTO.setProcedure("ICS");
                KidsToFedexICS kidsToFedexICS = new KidsToFedexICS();
                result = kidsToFedexICS.readKids(parser, encoding, kidsHeader, commonFieldsDTO);
                msgCode = kidsToFedexICS.getMsgCode();
                break;
            default:
                throw new FssException("Unknown KIDS procedure and version " + version);
        }


        fedexEnvelope.setContent(result);
        fedexEnvelope.setEncoding(encoding);

        String fedex = wrapMessageInFedexEnvelope(fedexEnvelope, msgCode);
        logAudit(kidsHeader, commonFieldsDTO);        
        //Utils.log("ICS WrapedFedexMessage = " + fedex);
        
        return fedex;
    }

    // Muss bei Bedarf von den abgeleiteten Klassen überschrieben werden.
    public abstract void logAudit(KidsHeader kidsHeader, CommonFieldsDTO commonFieldsDTO) throws Exception;

// MS20110930 Begin    
//    public String removeKcxEnvelope(String message, String encoding) throws Exception {
//        if (Config.getLogXML()) {
//            Utils.log("(KidsToFedexConverter removeKcxEnvelope) message = \n" + message);
//        }
//        String content = null;
//
//        InputStream ins = new ByteArrayInputStream(message.getBytes());
//        InputStreamReader is = new InputStreamReader(ins);
//        XMLInputFactory factory = XMLInputFactory.newInstance();
//        XMLEventReader parser = factory.createXMLEventReader(is);
//        
//        XmlMsgScanner scanner = new XmlMsgScanner(parser);
//        scanner.skipTo(Token.START_TAG, "Content", 0);
//        scanner.next();
//        content = scanner.getLexem();
//        if (Config.getLogXML()) {
//            Utils.log("(KidsToFedexConverter removeKcxEnvelope) content = \n" + content);
//        }
//        
//        return content.trim();
//    }
// MS20110930 End
    
    private KidsHeader getKidsHeader(XMLEventReader parser) throws Exception {
        KidsHeader kidsHeader = new KidsHeader();
        kidsHeader.setParser(parser);
        kidsHeader.setHeaderFields();
        return kidsHeader;
    }
    
    private String wrapMessageInFedexEnvelope(FedexEnvelope fedexEnvelope, String msgCode) {
        StringWriter fedexEnvelopeString = new StringWriter();
      
        XMLOutputFactory factory = XMLOutputFactory.newInstance();
        try {
            XMLStreamWriter fedexWriter = factory.createXMLStreamWriter(fedexEnvelopeString);
            fedexEnvelope.writeHeader(fedexWriter, msgCode);
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
        return fedexEnvelopeString.toString();
    }
    
}

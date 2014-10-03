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

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;

import com.kewill.kcx.component.mapping.EDirections;
import com.kewill.kcx.component.mapping.common.CommonFieldsDTO;
import com.kewill.kcx.component.mapping.companies.unisys.ics.KidsToUnisysICS;
import com.kewill.kcx.component.mapping.db.CustomerDataDTO;
import com.kewill.kcx.component.mapping.db.CustomerProcedureDTO;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.util.Utils;


/**
 * Module       : KidsToUids<br>
 * Created 		: 05.11.2008<br>
 * Description  : transformer called by Mule 
 *                to convert KIDS-Format to UIDS messages.
 * 
 * @author kron
 * @version 1.0.00
 */
public abstract class KidsToUnisysConverter {
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
        Utils.log("(KidsToUnisysConverter readKids) kidsHeader.getMessageName() = " + kidsHeader.getMessageName());
		
        String kcxId = kidsHeader.getReceiver();
        String localIdKids = null;
        String localIdUnisys = null;
        String corporateIdentity = null;
        
        //localIdKids = Utils.getCustomerIdFromKewill(kcxId, "KIDS", kidsHeader.getCountryCode()).trim();
        corporateIdentity = Utils.getCorporateIdentityFromCustomer(kcxId, "KIDS", kidsHeader.getCountryCode().trim()); // AK20120424_2
        //EI20110118: Transmitter doch nicht 'KEWILL.DE sondern bleibt kcx_id, so wie es ZABIS f�llt: 
        ////kidsHeader.setTransmitter(localIdKids);
        localIdUnisys = Utils.getCustomerIdFromKewill(kcxId, "UNISYS", kidsHeader.getCountryCode()).trim();
        ////kidsHeader.setReceiver(localIdUnisys);
        
      //AK20120424: kidsHeader.setTransmitter(localIdUnisys); 
      //AK20120424: kidsHeader.setReceiver(localIdKids); 
        //kidsHeader.setTransmitter(localIdKids); //AK20120424
        kidsHeader.setTransmitter(corporateIdentity); //AK20120424_2
        kidsHeader.setReceiver(localIdUnisys); //AK20120424

        commonFieldsDTO = new CommonFieldsDTO();
        commonFieldsDTO.setKcxId(kidsHeader.getReceiver());
        commonFieldsDTO.setUidsId(localIdUnisys);
        CustomerProcedureDTO customerProcedureDTO = Utils.getCustomerProceduresFromKcxId(kcxId,
                                                        			kidsHeader.getProcedure().toUpperCase());
        commonFieldsDTO.setCustomerProcedureDTO(customerProcedureDTO);
        CustomerDataDTO customerDataDTO = Utils.getCustomerDataFromKcxId(kcxId);
        commonFieldsDTO.setCustomerDataDTO(customerDataDTO);
        commonFieldsDTO.setCountryCode(kidsHeader.getCountryCode());
        commonFieldsDTO.setFilename(filename);
        
        if (Utils.isStringEmpty(kidsHeader.getInReplyTo())) {
            commonFieldsDTO.setMessageReferenceNumber(kidsHeader.getMessageID());
        } else {
            commonFieldsDTO.setMessageReferenceNumber(kidsHeader.getInReplyTo());
        }
        commonFieldsDTO.setDirection(direction);
        
        Object result = null;
        String procedure = kidsHeader.getProcedure().toUpperCase();
        String version = kidsHeader.getRelease();
        if (version == null) {
            version = "1";
        } else  {
            version = version.substring(0, 1);
        }
        commonFieldsDTO.setProcedure(procedure);
        KidsToUnisysICS kidsToUnisysICS = new KidsToUnisysICS();
        result = kidsToUnisysICS.readKids(parser, encoding, kidsHeader, commonFieldsDTO);

        logAudit(kidsHeader, commonFieldsDTO);
        
        return result;
    }
    
    // Muss bei Bedarf von den abgeleiteten Klassen �berschrieben werden.
    public abstract void logAudit(KidsHeader kidsHeader, CommonFieldsDTO commonFieldsDTO) throws Exception;

// MS20110930 Begin    
//    public String removeKcxEnvelope(String message, String encoding) throws Exception {
//        if (Config.getLogXML()) {
//            Utils.log("(KidsToUidsConverter removeKcxEnvelope) message = \n" + message);
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
//            Utils.log("(KidsToUidsConverter removeKcxEnvelope) content = \n" + content);
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
    
}

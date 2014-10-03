package com.kewill.kcx.component.mapping.common.start;

/*
 * Function    : KidsToCyprusConverter.java
 * Titel       :
 * Date        : 02.09.2008
 * Author      : Kewill CSF / Christine Kron
 * Description : transformer called by Mule 
 * 				 to convert KIDS-Format to Cyprus messages
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
import com.kewill.kcx.component.mapping.countries.cy.ics.KidsToCyprusICS;
import com.kewill.kcx.component.mapping.db.CustomerDataDTO;
import com.kewill.kcx.component.mapping.db.CustomerProcedureDTO;
import com.kewill.kcx.component.mapping.db.Db;
import com.kewill.kcx.component.mapping.formats.cyprus.common.CyprusHeader;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.util.Utils;


/**
 * Module       : KidsToCyprusConverter<br>
 * Created 		: 08.06.2011<br>
 * Description  : transformer called by Mule 
 *                to convert KIDS-Format to Cyprus messages.
 * 
 * @author schmidt
 * @version 1.0.00
 */
public abstract class KidsToCyprusConverter {
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
        CyprusHeader cyprusHeader = new CyprusHeader(); 
        mapKidsToCyprusHeader(kidsHeader, cyprusHeader);
        
        Utils.log("(KidsToCyprusConverter readKids) kidsHeader.getMessageName() = " + kidsHeader.getMessageName());
		
        // MS20110629 CyprusEnvelope cyprusEnvelope = new CyprusEnvelope();
        
        String localId = null;
        localId = Utils.getCustomerIdFromKewill(kidsHeader.getReceiver(), "CYPRUS", kidsHeader.getCountryCode()).trim();
        Utils.log("(KidsToCyprusConverter readKids) localId = " + localId);
        
        commonFieldsDTO = new CommonFieldsDTO();
        commonFieldsDTO.setKcxId(kidsHeader.getReceiver());
        commonFieldsDTO.setLocalId(localId);
        CustomerProcedureDTO customerProcedureDTO = Utils.getCustomerProceduresFromKcxId(
                                                        kidsHeader.getReceiver(), 
                                                        kidsHeader.getProcedure().toUpperCase());
        commonFieldsDTO.setCustomerProcedureDTO(customerProcedureDTO);
        CustomerDataDTO customerDataDTO = Utils.getCustomerDataFromKcxId(kidsHeader.getReceiver());
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
//        String msgCode = "";      MS20110930
        if (version == null) {
            version = "10";
        } else  {
            version = Utils.removeDots(version.substring(0, 3));
        }
        switch (EKidsProcedureVersions.valueOf("K" + version + procedure)) {
              case K10ICS:
                commonFieldsDTO.setProcedure("ICS");
                KidsToCyprusICS kidsToCyprusICS = new KidsToCyprusICS();
                result = kidsToCyprusICS.readKids(parser, encoding, kidsHeader, cyprusHeader, commonFieldsDTO);
//                msgCode = kidsToCyprusICS.getMsgCode();   MS20110930
                break;
            default:
                throw new FssException("Unknown KIDS procedure and version " + version);
        }


//        cyprusEnvelope.setContent(result);
//        cyprusEnvelope.setEncoding(encoding);

        //EI20110628: 
        // MS20110629 String cyprus = wrapMessageInCyprusEnvelope(cyprusEnvelope, msgCode);
        logAudit(kidsHeader, commonFieldsDTO); 
        // MS20110629 Begin
//        Utils.log("(KidsToCyprusConverter) converted message = \n" + cyprus);
//        return cyprus;
        Utils.log("(KidsToCyprusConverter) converted message = \n" + result);
        return result;
        // MS20110629 End
        
        //logAudit(kidsHeader, commonFieldsDTO);   
        //return result;     //EI20110628
    }

    // Muss bei Bedarf von den abgeleiteten Klassen überschrieben werden.
    public abstract void logAudit(KidsHeader kidsHeader, CommonFieldsDTO commonFieldsDTO) throws Exception;

// MS20110930 Begin    
//    public String removeKcxEnvelope(String message, String encoding) throws Exception {
//        if (Config.getLogXML()) {
//            Utils.log("(KidsToCyprusConverter removeKcxEnvelope) message = \n" + message);
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
//            Utils.log("(KidsToCyprusConverter removeKcxEnvelope) content = \n" + content);
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
    
    private void mapKidsToCyprusHeader(KidsHeader kidsHeader, CyprusHeader cyprusHeader) {
        String transmitter = kidsHeader.getTransmitter();
        String mesSenMES3 = Utils.getCustomerIdFromKewill(transmitter, "CYPRUS", kidsHeader.getCountryCode()).trim();
        cyprusHeader.setMesSenMES3(mesSenMES3);

        String receiver = kidsHeader.getReceiver();
        String mesRecMES6 = Utils.getCustomerIdFromKewill(receiver, "CYPRUS", kidsHeader.getCountryCode()).trim();
        cyprusHeader.setMesRecMES6(mesRecMES6);
        
        cyprusHeader.setDatOfPreMES9(getDate(kidsHeader.getYear(), kidsHeader.getMonth(), kidsHeader.getDay()));
        cyprusHeader.setTimOfPreMES10(getTime(kidsHeader.getTime()));
        cyprusHeader.setMesIdeMES19(kidsHeader.getMessageID());        
        if (!Utils.isStringEmpty(cyprusHeader.getMesIdeMES19())) {  //EI20110714
        	String messageIdOrg = cyprusHeader.getMesIdeMES19(); 
//        	if (messageIdOrg.length() > 14) {
// MS20110815 Begin        	    
//        		cyprusHeader.setMesIdeMES19(messageIdOrg.substring(0, 14));
        		String messageIdGen = Utils.generateMessageId(14);
        		cyprusHeader.setMesIdeMES19(messageIdGen);
        		Db.addMessageIdHistory(messageIdOrg, messageIdGen, receiver, kidsHeader.getCountryCode());
// MS20110815 End
//        	}
        }
    }
    
    protected String getDate(String year, String month, String day) {
        String   retYear  = "";
        String   retMonth = "";
        String   retDay   = "";
        if (year != null) {
            if (year.length() > 2) {
                retYear = year.substring(year.length() - 2);
            } else {
                retYear = Utils.nFormat(year, 2);
            }
        }
        
        if (month != null) {
            retMonth = Utils.nFormat(month, 2);
        }
        
        if (day != null) {
            retDay = Utils.nFormat(day, 2);
        }
        
        return retYear + retMonth + retDay;
    }
    
    
    protected String getTime(String time) {
        String   retTime = "";
        if (time != null) {
            String   hour = null;
            String   minute = null;
            String[] timeParts = time.split(":");
            if (timeParts.length > 1) {
                hour   = Utils.nFormat(timeParts[0], 2);
                minute = Utils.nFormat(timeParts[1], 2);
                retTime = hour + minute;
            } else {
                if (time.length() > 3) {
                    retTime = time.substring(0, 4);
                }
            }
        }
        return retTime;
    }
    
    
//    private String wrapMessageInCyprusEnvelope(CyprusEnvelope cyprusEnvelope, String msgCode) {
//        StringWriter cyprusEnvelopeString = new StringWriter();
//      
//        XMLOutputFactory factory = XMLOutputFactory.newInstance();
//        try {
//            XMLStreamWriter cyprusWriter = factory.createXMLStreamWriter(cyprusEnvelopeString);
//            cyprusEnvelope.writeHeader(cyprusWriter, msgCode);
//        } catch (XMLStreamException e) {
//            e.printStackTrace();
//        }
//        return cyprusEnvelopeString.toString();
//    }
    
}

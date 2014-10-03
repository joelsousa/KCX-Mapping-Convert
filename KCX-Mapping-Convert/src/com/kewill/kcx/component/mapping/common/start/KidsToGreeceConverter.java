package com.kewill.kcx.component.mapping.common.start;

/*
 * Function    : KidsToGreeceConverter.java
 * Titel       :
 * Date        : 18.07.2011
 * Author      : Kewill CSF / schmidt
 * Description : transformer called by Mule 
 * 				 to convert KIDS-Format to Greece messages
 * 			   : 
 * Parameters  : 

 * Changes 
 * -----------
 * Author      : 
 * Date        : 
 * Label       : 
 * Description : 
 *
 */

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;

import com.kewill.kcx.component.mapping.EDirections;
import com.kewill.kcx.component.mapping.common.CommonFieldsDTO;
import com.kewill.kcx.component.mapping.countries.de.ics.EKidsICSMessages;
import com.kewill.kcx.component.mapping.countries.gr.ics.KidsToGreeceICS;
import com.kewill.kcx.component.mapping.db.CustomerDataDTO;
import com.kewill.kcx.component.mapping.db.CustomerProcedureDTO;
import com.kewill.kcx.component.mapping.db.Db;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.greece.common.GreeceHeader;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.util.Utils;


/**
 * Module       : KidsToGreeceConverter<br>
 * Created 		: 18.07.2011<br>
 * Description  : transformer called by Mule 
 *                to convert KIDS-Format to Greece messages.
 * 
 * @author schmidt
 * @version 1.0.00
 */
public abstract class KidsToGreeceConverter {
    /**
     * Structure to pass common values.
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
        GreeceHeader greeceHeader = new GreeceHeader(); 
        mapKidsToGreeceHeader(kidsHeader, greeceHeader);
        
        Utils.log("(KidsToGreeceConverter readKids) kidsHeader.getMessageName() = " + kidsHeader.getMessageName());
		
        String localId = null;
        localId = Utils.getCustomerIdFromKewill(kidsHeader.getReceiver(), "GREECE", kidsHeader.getCountryCode()).trim();
        Utils.log("(KidsToGreeceConverter readKids) localId = " + localId);
        
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
        if (version == null) {
            version = "10";
        } else  {
            version = Utils.removeDots(version.substring(0, 3));
        }
        switch (EKidsProcedureVersions.valueOf("K" + version + procedure)) {
              case K10ICS:
                commonFieldsDTO.setProcedure("ICS");
                KidsToGreeceICS kidsToGreeceICS = new KidsToGreeceICS();
                result = kidsToGreeceICS.readKids(parser, encoding, kidsHeader, greeceHeader, commonFieldsDTO);
                break;
            default:
                throw new FssException("Unknown KIDS procedure and version " + version);
        }


        logAudit(kidsHeader, commonFieldsDTO); 
        Utils.log("(KidsToGreeceConverter) converted message = \n" + result);
        return result;
    }

    // Muss bei Bedarf von den abgeleiteten Klassen überschrieben werden.
    public abstract void logAudit(KidsHeader kidsHeader, CommonFieldsDTO commonFieldsDTO) throws Exception;

// MS20110930 Begin    
//    public String removeKcxEnvelope(String message, String encoding) throws Exception {
//        if (Config.getLogXML()) {
//            Utils.log("(KidsToGreeceConverter removeKcxEnvelope) message = \n" + message);
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
//            Utils.log("(KidsToGreeceConverter removeKcxEnvelope) content = \n" + content);
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
    
    private void mapKidsToGreeceHeader(KidsHeader kidsHeader, GreeceHeader greeceHeader) {
        String transmitter = kidsHeader.getTransmitter();
        String mesSenMES3 = Utils.getCustomerIdFromKewill(transmitter, "GREECE", kidsHeader.getCountryCode()).trim();
        greeceHeader.setMesSenMES3(mesSenMES3);

        String receiver = kidsHeader.getReceiver();
        String mesRecMES6 = Utils.getCustomerIdFromKewill(receiver, "GREECE", kidsHeader.getCountryCode()).trim();
        greeceHeader.setMesRecMES6(mesRecMES6);
        
        greeceHeader.setDatOfPreMES9(getDate(kidsHeader.getYear(), kidsHeader.getMonth(), kidsHeader.getDay()));
        greeceHeader.setTimOfPreMES10(getTime(kidsHeader.getTime()));
        greeceHeader.setMesIdeMES19(kidsHeader.getMessageID());        
        if (!Utils.isStringEmpty(greeceHeader.getMesIdeMES19())) {  //EI20110714
        	String messageIdOrg = greeceHeader.getMesIdeMES19(); 
//        	if (messageIdOrg.length() > 14) {        	
// MS20110815 Begin             
//              greeceHeader.setMesIdeMES19(temp.substring(0, 14));
                String messageIdGen = Utils.generateMessageId(14);
                greeceHeader.setMesIdeMES19(messageIdGen);
//                Db.addMessageIdHistory(messageIdOrg, messageIdGen);
                Db.addMessageIdHistory(messageIdOrg, messageIdGen, receiver, kidsHeader.getCountryCode());
// MS20110815 End
//        	}
        }
        // MS20110805 Begin
        String kidsMessageType = kidsHeader.getMessageName();
        greeceHeader.setMesTypMES20(EKidsICSMessages.valueOf(kidsMessageType).getGreece());
        // MS20110805 End
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
    
    public  CommonFieldsDTO getCommonFieldsDTO() {  //EI29120124
   	 	return commonFieldsDTO;
    }
}

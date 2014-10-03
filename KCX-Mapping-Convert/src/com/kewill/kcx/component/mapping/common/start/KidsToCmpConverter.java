package com.kewill.kcx.component.mapping.common.start;

/*
 * Function    : KidsToCMP.java
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
import com.kewill.kcx.component.mapping.countries.de.Import.KidsToKids64Import;
import com.kewill.kcx.component.mapping.countries.de.Import20.KidsToKidsImport20;
import com.kewill.kcx.component.mapping.countries.de.Port20.KidsToKidsPort20;
import com.kewill.kcx.component.mapping.countries.de.aes21.KidsToKidsExportV21;
import com.kewill.kcx.component.mapping.countries.de.cmp.KidsToCmp;
import com.kewill.kcx.component.mapping.countries.de.emcs20.KidsToKidsEmcs20;
import com.kewill.kcx.component.mapping.countries.de.ics.KidsToKidsICS;
import com.kewill.kcx.component.mapping.countries.de.ics20.KidsToKidsICS20;
import com.kewill.kcx.component.mapping.db.CustomerDataDTO;
import com.kewill.kcx.component.mapping.db.CustomerProcedureDTO;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.util.Utils;


/**
 * Module       : KidsToPort<br>
 * Created 		: 19.06.2013<br>
 * Description  : transformer called by Mule 
 *                to convert KIDS-Format to CMP messages.
 * 
 * @author iwaniuk   
 * @version 1.0.00
 */
public abstract class KidsToCmpConverter {
   
    protected CommonFieldsDTO commonFieldsDTO = null; //noetig wg.filename-Aenderung bei tgz-Dateien
    
	public String readKids(String payload, String encoding, String filename, EDirections direction) 
	                                                                                throws Exception {
		
        String 				content = new RemoveKcxEnvelope().removeEnvelope(payload, encoding);       
        InputStream 		ins = new ByteArrayInputStream(content.getBytes());
        InputStreamReader 	is = new InputStreamReader(ins, encoding);
        XMLInputFactory 	factory = XMLInputFactory.newInstance();
        XMLEventReader 		parser = factory.createXMLEventReader(is);
        
        return readKids(parser, payload, encoding, filename, direction);
	}
	
    public String readKids(XMLEventReader parser, String payload, String encoding, String filename, EDirections direction) 
                                                                                      throws Exception {
        KidsHeader kidsHeader = getKidsHeader(parser);
        Utils.log("(KidsToCmpConverter readKids) kidsHeader.getMessageName() = " + kidsHeader.getMessageName());
		
        String result = payload;
        String kcxId = kidsHeader.getReceiver();
        String localIdKids = null;        
        String msg = kidsHeader.getMessageName();
        
        localIdKids = Utils.getCustomerIdFromKewill(kcxId, "KIDS", kidsHeader.getCountryCode()).trim();        
        kidsHeader.setTransmitter("DE");
        kidsHeader.setReceiver(localIdKids);
        
        commonFieldsDTO = new CommonFieldsDTO();
        commonFieldsDTO.setKcxId(kidsHeader.getReceiver());
        
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
        
        if (Utils.isStringEmpty(kidsHeader.getMessageID())) {
        	kidsHeader.setMessageID(filename);
        }
        String procedure = kidsHeader.getProcedure().toUpperCase();
        String version = kidsHeader.getRelease();
        if (version == null) {
            version = "20";
        } else  {
        	version = Utils.removeDots(version.substring(0, 3));
        }
        commonFieldsDTO.setProcedure(procedure);       
        
        boolean isCMP = false;
        String versionProcedure = "K" + version + procedure;
        kidsHeader.setCommonFieldsDTO(commonFieldsDTO);     // needed in writeHeader   
        if (versionProcedure.equalsIgnoreCase("K20MANIFEST") || 
        	versionProcedure.equalsIgnoreCase("K40NCTS") || versionProcedure.equalsIgnoreCase("K41NCTS") || 
        	versionProcedure.equalsIgnoreCase("K20MANIFEST")) {
        	isCMP = true;
        }
     if (isCMP) {
        switch (EKidsProcedureVersions.valueOf("K" + version + procedure)) {
        
        case K40NCTS:	                     
        case K41NCTS:	        
        case K20MANIFEST:	
        	KidsToCmp kidsToCmp = new KidsToCmp();
        	result = kidsToCmp.readKids(parser, encoding, kidsHeader, commonFieldsDTO);
        	break;
        	
        default:
            throw new FssException("Unknown KIDS procedure and version " + procedure + version);
        } 
     } else {
    	 Utils.log("(KidsToCmpConverter.readKids) kidsHeader.getMessageName() = " + msg + "(Release " + version + ")" +
         " wird NICHT in CMP-Format konvertiert!");
    	 Utils.log("(KidsToCmpConverter.readKids) payload = " + result);
    	 result = new RemoveKcxEnvelope().removeEnvelope(payload, encoding);
    	 Utils.log("(KidsToCmpConverter.readKids) result = " + result);
     }
        logAudit(kidsHeader, commonFieldsDTO);
        
        return result;
    }
    
    // Muss bei Bedarf von den abgeleiteten Klassen überschrieben werden.
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

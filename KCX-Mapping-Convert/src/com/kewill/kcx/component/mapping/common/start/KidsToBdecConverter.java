package com.kewill.kcx.component.mapping.common.start;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;

import com.kewill.kcx.component.mapping.EDirections;
import com.kewill.kcx.component.mapping.common.CommonFieldsDTO;
import com.kewill.kcx.component.mapping.countries.de.aes.KidsToBdecExport;
import com.kewill.kcx.component.mapping.db.CustomerDataDTO;
import com.kewill.kcx.component.mapping.db.CustomerProcedureDTO;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Modul		: KidsToBdecConverter<br>
 * Erstellt		: 03.11.2009<br>
 * Description  : transformer called by Mule 
 *                to convert KIDS-Format to E-Customs format (via Bell Davies).
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public abstract class KidsToBdecConverter {
//    private CommonFieldsDTO commonFieldsDTO     = null;
    /**
     * Structure to pass common values.
     */
    protected CommonFieldsDTO commonFieldsDTO       = null;

    public String readKids(String message, String encoding, String filename, EDirections direction) 
                                                                                    throws Exception {
        // MS20110930 Begin
//        String content = removeKcxEnvelope(message, encoding);
        String content = new RemoveKcxEnvelope().removeEnvelope(message, encoding);
        // MS20110930 End

        InputStream ins = new ByteArrayInputStream(content.getBytes());
        InputStreamReader is = new InputStreamReader(ins, encoding);
        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLEventReader parser = factory.createXMLEventReader(is);
        
        return readKids(parser, encoding, filename, direction);
    }
    	
	public String readKids(XMLEventReader parser, String encoding, String filename, EDirections direction) 
	                                                                                                throws Exception {
		KidsHeader kidsHeader = getKidsHeader(parser);
        Utils.log("(KidsToBDecConverter readKids) kidsHeader.getMessageName() = " + kidsHeader.getMessageName());
        
        String localId = null;
        localId = Utils.getCustomerIdFromKewill(kidsHeader.getReceiver(), "BDEC", kidsHeader.getCountryCode()).trim();
        Utils.log("(KidsToBdecConverter readKids) localId = " + localId); 

        
        commonFieldsDTO = new CommonFieldsDTO();
        commonFieldsDTO.setKcxId(kidsHeader.getReceiver());
        commonFieldsDTO.setUidsId(localId);
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
        
        String procedure = kidsHeader.getProcedure().toUpperCase();
        String kcxId     = kidsHeader.getReceiver().toUpperCase();
        String version = kidsHeader.getRelease();   
        String msg = kidsHeader.getMessageName();  
        Utils.log("(KidsToBdecConverter readKids) kidsHeader.getReceiver  = " + kcxId);
        Utils.log("(KidsToBdecConverter readKids) kidsHeader.getProcedure = " + procedure);        
        Utils.log("(KidsToBdecConverter readKids) : kidsHeader.getRelease() liefert: " + version);
        Utils.log("(KidsToBdecConverter readKids) kidsHeader.getMessageName  = " + msg);

        if (version == null) {
            version = "10";
        } else  {
            version = Utils.removeDots(version.substring(0, 3));
        }
        String result = null;
        switch (EKidsProcedureVersions.valueOf("K" + version + procedure)) {
            case K10EXPORT:
            case K11EXPORT:
            case K12EXPORT:
            case K21EXPORT:    //EI20120802, fuer Bdec hat sich nichts geaendert, die neuen KIDS-Tags sind unrelevant
                KidsToBdecExport kidsToBdecExport = new KidsToBdecExport();
                result = kidsToBdecExport.readKids(parser, encoding, kidsHeader, commonFieldsDTO);
                Utils.log("(KidsToBdecConverter ) converted message  = \n" + result);
                break;
            default:
                throw new FssException("Unknown KIDS procedure and version " + version);
        }
        
        logAudit(kidsHeader, commonFieldsDTO);

        return result;        
	}
	
    // Muss bei Bedarf von den abgeleiteten Klassen überschrieben werden.
    public abstract void logAudit(KidsHeader kidsHeader, CommonFieldsDTO commonFieldsDTO) throws Exception;
	
// MS20110930 Begin
//    public String removeKcxEnvelope(String payload, String encoding) throws Exception {
//        String content = null;
//
//        InputStream ins = new ByteArrayInputStream(payload.getBytes());
// 
//        InputStreamReader is = new InputStreamReader(ins);
//        XMLInputFactory factory = XMLInputFactory.newInstance();
//        XMLEventReader parser = factory.createXMLEventReader(is);
//        
//        XmlMsgScanner scanner = new XmlMsgScanner(parser);
//        scanner.skipTo(Token.START_TAG, "Content", 0);
//        scanner.next();
//        content = scanner.getLexem();
//        if (Config.getLogXML()) {
//            Utils.log("(KidsToBdecConverter removeKcxEnvelope) content = \n" + content);
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

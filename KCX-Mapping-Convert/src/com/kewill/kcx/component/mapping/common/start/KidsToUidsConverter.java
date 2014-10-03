package com.kewill.kcx.component.mapping.common.start;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;

import com.kewill.kcx.component.mapping.EDirections;
import com.kewill.kcx.component.mapping.common.CommonFieldsDTO;
import com.kewill.kcx.component.mapping.countries.de.aes.KidsToUidsExport;
import com.kewill.kcx.component.mapping.countries.de.aes21.KidsToUidsExportV21;
import com.kewill.kcx.component.mapping.countries.de.emcs.KidsToUidsEmcs;
import com.kewill.kcx.component.mapping.countries.de.emcs21.KidsToUidsEmcs21;
import com.kewill.kcx.component.mapping.countries.de.ics.KidsToUidsICS;
import com.kewill.kcx.component.mapping.countries.de.ics20.KidsToUidsICS20;
import com.kewill.kcx.component.mapping.countries.de.ncts.KidsToUidsNCTS;
import com.kewill.kcx.component.mapping.countries.de.ncts20.KidsToUidsNCTS41;
import com.kewill.kcx.component.mapping.countries.de.suma62.KidsToUidsManifest;
import com.kewill.kcx.component.mapping.countries.de.suma70.KidsToUidsManifest20;
import com.kewill.kcx.component.mapping.db.CustomerDataDTO;
import com.kewill.kcx.component.mapping.db.CustomerProcedureDTO;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
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
public abstract class KidsToUidsConverter {
    /**
     * Christine Kron: Muss hier definiert sein wg. filename-Aenderung bei tgz-Dateien
     */
    protected CommonFieldsDTO commonFieldsDTO		= null;

    
	public Object readKids(String payload, String encoding, String filename, EDirections direction) 
	                                                                                throws Exception {
        // MS20110930 Begin
//      String content = removeKcxEnvelope(payload, encoding);
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
        Utils.log("(KidsToUidsConverter readKids) kidsHeader.getMessageName() = " + kidsHeader.getMessageName());
		
        String localId = null;
        localId = Utils.getCustomerIdFromKewill(kidsHeader.getReceiver(), "UIDS", kidsHeader.getCountryCode()).trim();
        Utils.log("(KidsToUidsConverter readKids) localId = " + localId);
        
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
        
        Object result = null;
        String procedure = kidsHeader.getProcedure().toUpperCase();
        Utils.isProcedureLicensed(kidsHeader.getReceiver(), procedure, kidsHeader.getCountryCode());
        
        // CK 2010-12-22 procedure set once here - not in switch-case any more
        commonFieldsDTO.setProcedure(procedure);
        String version = kidsHeader.getRelease();
        if (version == null) {
            version = "10";
        } else  {
            version = Utils.removeDots(version.substring(0, 3));
        }
        switch (EKidsProcedureVersions.valueOf("K" + version + procedure)) {
            case K10EXPORT:
            case K11EXPORT:
            case K12EXPORT:
            case K20EXPORT:
                KidsToUidsExport kidsToUidsExport = new KidsToUidsExport();
                result = kidsToUidsExport.readKids(parser, encoding, kidsHeader, commonFieldsDTO);
                break;
            case K21EXPORT:
                KidsToUidsExportV21 kidsToUidsExportV21 = new KidsToUidsExportV21();
                result = kidsToUidsExportV21.readKids(parser, encoding, kidsHeader, commonFieldsDTO);
                break;
                
            case K10EMCS:            	
            //EI20121012: case K20EMCS:
                KidsToUidsEmcs kidsToUidsEmcs = new KidsToUidsEmcs();
                result = kidsToUidsEmcs.readKids(parser, encoding, kidsHeader, commonFieldsDTO);
                break;
            case K20EMCS:    //EI20121012:
            	//Utils.log("(KidsToUidsConverter) : KIDS-EMCS 2.0.00 nicht aktiviert");
                KidsToUidsEmcs kidsToUidsEmcs20 = new KidsToUidsEmcs();
                result = kidsToUidsEmcs20.readKids(parser, encoding, kidsHeader, commonFieldsDTO);
                break;
            case K21EMCS:    //EI20121012:
            	//Utils.log("(KidsToUidsConverter) : KIDS-EMCS 2.0.00 nicht aktiviert");
                KidsToUidsEmcs21 kidsToUidsEmcs21 = new KidsToUidsEmcs21();
                result = kidsToUidsEmcs21.readKids(parser, encoding, kidsHeader, commonFieldsDTO);
                break;
                
            case K10ICS:
                KidsToUidsICS kidsToUidsICS = new KidsToUidsICS();
                result = kidsToUidsICS.readKids(parser, encoding, kidsHeader, commonFieldsDTO);
                break;
            case K20ICS:
                KidsToUidsICS20 kidsToUidsICS20 = new KidsToUidsICS20();
                result = kidsToUidsICS20.readKids(parser, encoding, kidsHeader, commonFieldsDTO);
                break;
            case K40NCTS:	//FT23082010            
                KidsToUidsNCTS kidsToUidsNCTS = new KidsToUidsNCTS();
                result = kidsToUidsNCTS.readKids(parser, encoding, kidsHeader, commonFieldsDTO);
                break;
            case K41NCTS:	//EI20130204
                //Utils.log("(KidsToUidsConverter VE-V70) nicht definiert ");
                KidsToUidsNCTS41 kidsToUidsNCTS41 = new KidsToUidsNCTS41();
                result = kidsToUidsNCTS41.readKids(parser, encoding, kidsHeader, commonFieldsDTO);
                break;
            
            case K10MANIFEST:	// CK20121115
            	KidsToUidsManifest kidsToUidsManifest = new KidsToUidsManifest();
                result = kidsToUidsManifest.readKids(parser, encoding, kidsHeader, commonFieldsDTO);
                break;
            case K20MANIFEST:	// EI20130906
            	KidsToUidsManifest20 kidsToUidsManifest20 = new KidsToUidsManifest20();
                result = kidsToUidsManifest20.readKids(parser, encoding, kidsHeader, commonFieldsDTO);
                break;
                
            default:
                throw new FssException("Unknown KIDS procedure and version: " + procedure + " " + version);
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
//        // MS20110929 Begin
//        // Falls eine Nachricht requeued wird, fehlt unter Umständen bereits der KCX-Envelope.
//        // Das ist daran erkennbar, das das Tag "Content" fehlt.
//        // In diesem Fall wird einfach die komplette Nachricht zurückgegeben.
//        // scanner.skipTo(Token.START_TAG, "Content", 0);
//        if (!scanner.skipTo(Token.START_TAG, "Content", 0)) {
//            Utils.log("(KidsToUidsConverter removeKcxEnvelope) KCX-Envelope was already removed.");
//            return message;
//        }
//        // MS20110929 End
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

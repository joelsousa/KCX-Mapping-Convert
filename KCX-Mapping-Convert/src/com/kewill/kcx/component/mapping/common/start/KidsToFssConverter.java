package com.kewill.kcx.component.mapping.common.start;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;

import com.kewill.kcx.component.mapping.EDirections;
import com.kewill.kcx.component.mapping.common.CommonFieldsDTO;
import com.kewill.kcx.component.mapping.countries.de.Import.KidsToFss64Import;
import com.kewill.kcx.component.mapping.countries.de.Import20.KidsToFss70Import;
import com.kewill.kcx.component.mapping.countries.de.Port.KidsToFssPort;
import com.kewill.kcx.component.mapping.countries.de.Port20.KidsToFss70Port;
import com.kewill.kcx.component.mapping.countries.de.aes.KidsToFss53Export;
import com.kewill.kcx.component.mapping.countries.de.aes.KidsToFss60Export;
import com.kewill.kcx.component.mapping.countries.de.aes21.KidsToFss70Export;
import com.kewill.kcx.component.mapping.countries.de.ncts.KidsToFss62NCTS;
import com.kewill.kcx.component.mapping.countries.de.ncts20.KidsToFss70NCTS;
import com.kewill.kcx.component.mapping.countries.de.suma62.KidsToFss62Manifest;
import com.kewill.kcx.component.mapping.countries.de.suma70.KidsToFss70Manifest;
import com.kewill.kcx.component.mapping.db.CustomerProcedureDTO;
import com.kewill.kcx.component.mapping.db.Db;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.fss.common.TsHead;
import com.kewill.kcx.component.mapping.formats.fss.common.TsVOR;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: KidsToFssConverter<br>
 * Created		: 05.09.2008<br>
 * Description  : transformer called by Mule 
 *                to convert KIDS-Format to ZABIS FSS.
 * 
 * @author kron
 * @version 1.0.00
 */
public abstract class KidsToFssConverter {
//    private CommonFieldsDTO commonFieldsDTO     = null;
    /**
     * Structure to pass common values.
     */
    protected CommonFieldsDTO commonFieldsDTO       = null;

    public String readKids(String message, String encoding, EDirections direction) throws Exception {
        // MS20110930 Begin
//        String content = removeKcxEnvelope(message, encoding);
        String content = new RemoveKcxEnvelope().removeEnvelope(message, encoding);
        // MS20110930 End

        // remove newlines and/or carriage return
        // because this leads to an error in ZABIS-FSS
        content = content.replaceAll("[\r\n]", "");

        InputStream ins = new ByteArrayInputStream(content.getBytes());
        InputStreamReader is = new InputStreamReader(ins, encoding);
        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLEventReader parser = factory.createXMLEventReader(is);
        
        return readKids(parser, direction);
    }
    

	// SH080917 for test changed from private to public
	public String readKids(XMLEventReader parser, EDirections direction) throws Exception {
		KidsHeader kidsHeader = getKidsHeader(parser);
		String version = null;
	
        String localId = null;
        // 150509MS Begin
        // DB-Zugriff nur noch über eigene Routinen, da Pflege der GB-Tabellen zu aufwändig.
//        if (muleMessage != null) {
//            muleMessage.setStringProperty("KewillId", kidsHeader.getReceiver());
//            muleMessage.setStringProperty("Country", kidsHeader.getCountryCode());
//            muleMessage.setStringProperty("System", "FSS");
////            localId = Utils.getCustomsIdFromKewillId(muleEventContext);
//            localId = Utils.getCustomerIdFromKewillId(muleEventContext);
//        } else {
//            localId = Utils.getCustomerIdFromKewill(kidsHeader.getReceiver(), "FSS").trim();
//        }
       
        localId = Utils.getCustomerIdFromKewill(kidsHeader.getReceiver(), "FSS", kidsHeader.getCountryCode()).trim();     
        Utils.log("(KidsToFssConverter readKids) localId = " + localId);
       
        commonFieldsDTO = new CommonFieldsDTO();
        // MS20101018
        if (Utils.isStringEmpty(kidsHeader.getInReplyTo())) {
            commonFieldsDTO.setMessageReferenceNumber(kidsHeader.getMessageID());
        } else {
            commonFieldsDTO.setMessageReferenceNumber(kidsHeader.getInReplyTo());
        }
        commonFieldsDTO.setDirection(direction);
        commonFieldsDTO.setCountryCode(kidsHeader.getCountryCode());
        commonFieldsDTO.setKcxId(kidsHeader.getReceiver());             // 20110120MS


        TsVOR vorSubset = new TsVOR("E");
        TsHead headSubset = new TsHead();                               //EI20121107
        if (kidsHeader.getCountryCode().equalsIgnoreCase("DE")) {
        	// Das Submodul Export Deutschland lautet EX, 
        	// Die FSS-Dateien werden in den Ordner FSSINEX gestellt
        	vorSubset.setModul("EX");	
        } else {
        	// Das Submodul Export Schweiz lautet VCH statt EX, 
        	// Die FSS-Dateien Export Schweiz werden in den ORdner FSSINVCH gestellt
        	vorSubset.setModul("VCH");
        }
		
		vorSubset.setMsgid(kidsHeader.getMessageID()); 
		
		String[] fssCustomer = localId.split("-");
        vorSubset.setMan(fssCustomer[0]);
        if (fssCustomer.length > 1) {
            vorSubset.setNl(fssCustomer[1]);
        } else {
            vorSubset.setNl("");
        }
        if (Utils.isStringEmpty(kidsHeader.getProcedure())) { //EI20121012
        	Utils.log("(KidsToFssConverter readKids) kidsHeader.Procedure is not provide");
        	return "";
        }
        if (Utils.isStringEmpty(kidsHeader.getReceiver())) { //EI20121012
        	Utils.log("(KidsToFssConverter readKids) kidsHeader.Receiver is not provide");
        	return "";
        }
        String procedure = kidsHeader.getProcedure().toUpperCase();
        String kcxId     = kidsHeader.getReceiver().toUpperCase();
        Utils.log("(KidsToFssConverter readKids) kidsHeader.getReceiver  = " + kcxId);
        Utils.log("(KidsToFssConverter readKids) kidsHeader.getProcedure = " + procedure);
        
        //CustomerProcedureDTO customerProcedureDTO = Db.getCustomerProceduresFromKidsId(kcxId, procedure);
        //version = Utils.getStringWithoutDot(customerProcedureDTO.getFssVersion().trim());
        //EI20130213 wieder aktiviert: wg Umschaltung auf HEAD:
        CustomerProcedureDTO customerProcedureDTO = Db.getCustomerProceduresFromKidsId(kcxId, procedure);  
        commonFieldsDTO.setCustomerProcedureDTO(customerProcedureDTO);
        
        String bob =  Utils.getBobNameFromKcxId(kcxId);   //EI20130215 wg. KFF - Sonderlocken
        commonFieldsDTO.setBOB(bob);
        
        version = kidsHeader.getRelease();
        Utils.log("(KidsToFssConverter readKids) : kidsHeader.getRelease() liefert: " + version);
        if (version == null) {
        	version = "10";
        } else  {
        	version = Utils.removeDots(version.substring(0, 3));
        }
        
        headSubset.mapVor2Head(vorSubset);                                         //EI20121107 
        
        Utils.log("(KidsToFssConverter readKids) : version = " + version);
		String xml = "";  
				
        switch (EKidsProcedureVersions.valueOf("K" + version + procedure)) {
        case K10EXPORT:
        	Utils.log("(KidsToFssConverter readKids) : KIDS wird nach Zabis Version 5 konvertiert");
        	KidsToFss53Export kidsToFss53Export = new KidsToFss53Export();
			xml = kidsToFss53Export.readKids(parser, vorSubset, kidsHeader, commonFieldsDTO);
            break;

        case K11EXPORT:
        case K12EXPORT:
        	Utils.log("(KidsToFssConverter readKids) : KIDS wird nach Zabis Version 6 konvertiert");
        	KidsToFss60Export kidsToFss60Export = new KidsToFss60Export();
			xml = kidsToFss60Export.readKids(parser, vorSubset, kidsHeader, commonFieldsDTO);
            break;
        case K21EXPORT:      //EI20120802
        	Utils.log("(KidsToFssConverter readKids) : KIDS wird nach Zabis Version 70 konvertiert");
        	KidsToFss70Export kidsToFss70Export = new KidsToFss70Export();
			xml = kidsToFss70Export.readKids(parser, vorSubset, headSubset, kidsHeader, commonFieldsDTO);
            break;
            
        case K40NCTS:
        	Utils.log("(KidsToFssConverter readKids) : KIDS wird nach Zabis Version 6.2 NCTS konvertiert");
        	KidsToFss62NCTS kidsToFss62NCTS = new KidsToFss62NCTS();
			xml = kidsToFss62NCTS.readKids(parser, vorSubset, kidsHeader, commonFieldsDTO);
            break;       
        case K41NCTS:     //EI20121220
        	//Utils.log("(KidsToFssConverter readKids) : KIDS-NCTS 4.1.00 nicht definiert"); //EI20130104
        	Utils.log("(KidsToFssConverter readKids) : KIDS wird nach Zabis Version 7.0 NCTS konvertiert");
        	KidsToFss70NCTS kidsToFss70NCTS = new KidsToFss70NCTS();
			xml = kidsToFss70NCTS.readKids(parser, vorSubset, headSubset, kidsHeader, commonFieldsDTO);
            break;
            
        case K10IMPORT:
        	Utils.log("(KidsToFssConverter readKids) : KIDS wird nach Zabis Version 6.4 Import konvertiert");
        	KidsToFss64Import kidsToFss64Import = new KidsToFss64Import();
			xml = kidsToFss64Import.readKids(parser, vorSubset, kidsHeader, commonFieldsDTO);
            break;
        case K20IMPORT:
        	//Utils.log("(KidsToFssConverter readKids) : KIDS-Import 2.0.00 nicht definiert");  //EI20130104
        	Utils.log("(KidsToFssConverter readKids) : KIDS wird nach Zabis Version 7.0 Import konvertiert");        	
        	KidsToFss70Import kidsToFss70Import = new KidsToFss70Import();
			xml = kidsToFss70Import.readKids(parser, vorSubset, headSubset, kidsHeader, commonFieldsDTO);
            break;
            
        case K10PORT:              //EI20111021
        case K10POR: 
        	Utils.log("(KidsToFssConverter readKids) : KIDS wird nach Zabis Version 6.4 Port konvertiert");
        	KidsToFssPort kidsToFssPort = new KidsToFssPort();
			xml = kidsToFssPort.readKids(parser, vorSubset, kidsHeader, commonFieldsDTO);
            break;
        case K20PORT:        	
        	Utils.log("(KidsToFssConverter readKids) : KIDS wird nach Zabis Version 7.0 Port konvertiert");
        	KidsToFss70Port kidsToFss70Port = new KidsToFss70Port();
			xml = kidsToFss70Port.readKids(parser, vorSubset, headSubset, kidsHeader, commonFieldsDTO);
            break;
            
        case K10MANIFEST:   //EI20130104
        	Utils.log("(KidsToFssConverter readKids) : KIDS wird nach Zabis Version 6.2 konvertiert");
        	KidsToFss62Manifest kidsToFss62Manifest = new KidsToFss62Manifest();
			xml = kidsToFss62Manifest.readKids(parser, vorSubset, kidsHeader, commonFieldsDTO);
            break;

        case K20MANIFEST:   //AK20130604
        	Utils.log("(KidsToFssConverter readKids) : KIDS wird nach Zabis Version 7.0 Manifest konvertiert");
        	KidsToFss70Manifest kidsToFss70Manifest = new KidsToFss70Manifest();
			xml = kidsToFss70Manifest.readKids(parser, vorSubset, headSubset, kidsHeader, commonFieldsDTO);
            break;
            
        default:
            throw new FssException("Unknown KIDS procedure and version " + "K" + version + procedure);
        }
        
        logAudit(kidsHeader, commonFieldsDTO);

        return xml;
	}
	
    // Muss bei Bedarf von den abgeleiteten Klassen überschrieben werden.
    public abstract void logAudit(KidsHeader kidsHeader, CommonFieldsDTO commonFieldsDTO) throws Exception;

// MS20110930 Begin	
//    public String removeKcxEnvelope(String payload, String encoding) throws Exception {
//        String content = null;
//        
////        InputStream ins = new StringInputStream(payload);
//        InputStream ins = new ByteArrayInputStream(payload.getBytes());
//        
////        InputStreamReader is = new InputStreamReader(ins, encoding);
//        InputStreamReader is = new InputStreamReader(ins);
//        XMLInputFactory factory = XMLInputFactory.newInstance();
//        XMLEventReader parser = factory.createXMLEventReader(is);
//        
//        XmlMsgScanner scanner = new XmlMsgScanner(parser);
//        scanner.skipTo(Token.START_TAG, "Content", 0);
//        scanner.next();
//        content = scanner.getLexem();
//        if (Config.getLogXML()) {
//            Utils.log("(KidsToFssConverter removeKcxEnvelope) content = \n" + content);
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

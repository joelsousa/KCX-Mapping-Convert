package com.kewill.kcx.component.mapping.common.start;

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
import com.kewill.kcx.component.mapping.conf.Config;
import com.kewill.kcx.component.mapping.countries.de.cmp.CmpToKidsManifest;
import com.kewill.kcx.component.mapping.countries.de.cmp.CmpToKidsNCTS;
import com.kewill.kcx.component.mapping.countries.de.cmp.CmpToKidsReExport;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.kcx.KcxEnvelope;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.util.KcxMappingException;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module      : CMPToKidsConverter<br>
 * Created     : 07.06.2013<br>
 * Description : Transformer convert CMP-Format to KIDS
 * messages.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public abstract class CMPToKidsConverter {
    /**
     * Structure to pass common values.
     */
    protected CommonFieldsDTO commonFieldsDTO = null;
    
    public String readCmp(String message, String encoding, EDirections direction) throws Exception {
        InputStream ins = new ByteArrayInputStream(message.getBytes());
        InputStreamReader is = new InputStreamReader(ins, encoding);
        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLEventReader parser = factory.createXMLEventReader(is);
        
        return readCmp(parser, message, "No Mule Call", direction);
    }
    
	public String readCmp(XMLEventReader parser, String payload, String auditId, EDirections direction)
			                                                                        throws Exception {		
		String mappingResult = payload;
        KcxEnvelope kcxEnvelope = new KcxEnvelope();
        KidsHeader kidsHeader = getKidsHeader(parser);	
        
        String kcxId = "";
        //kcxId = kidsHeader.getReceiver();   //hier steht erst mal DE.KCX.KWL, wird später eine richtige kcx_id aus der DB.customer
        									//anhand von airportLocation(suma) /departureLocation(ncts, reexport) gelesen
        
        //damit kann ich es steuern, falls von CMP was anderes kommt:
        //aber auch ermitteln, ob es sich um Test(DE.CMP.TST) oder Live(DE.CMP.PRD) handelt
        kcxId = Utils.getKewillIdFromCustomer(kidsHeader.getReceiver(), "CMP");  
        		//EI20140211: ist z.Z.immer kidsHeader.getReceiver() = DE.KCX.KWL und kcx_id = DE.CMP.TST (bzw. DE.CMP.PRD)
        kidsHeader.setReceiver(kcxId);  //damit kann ich es steuern, falls von CMP was anderes kommt
        //Utils.log("(CMPToKidsConverter readKids) kcxId = " + kcxId);          
        
        String countryCode = kidsHeader.getCountryCode();
		String msg = kidsHeader.getMessageName(); 
		String procedure = kidsHeader.getProcedure();
		String version = kidsHeader.getRelease();
		String method = kidsHeader.getMethod();    //EI20130607: ev. bei Kids2KidsConverter:
		kidsHeader.setMap("0");   //TODO was schickt LCAG??? DE-Codes?, Decimalzahlen=Ja
		kidsHeader.setMapFrom(countryCode);
		kidsHeader.setMapTo("DE");
        
		if (version == null) {
            version = "20";
        } else  {
            version = Utils.removeDots(version.substring(0, 3));
        }  
		
		if (procedure == null) {
            throw new KcxMappingException("No procedure found in KIDS data! Cannot determine mapping class!");
        } else {
            procedure = procedure.toUpperCase();
        }
		     
       if (procedure.equalsIgnoreCase("TEMPSTORAGE")) {
    	   version = "20";
    	   procedure = "MANIFEST";    	  
       }
       if (procedure.equalsIgnoreCase("SUMA")) {
    	   version = "20";
    	   procedure = "MANIFEST";    	  
       }
       if (procedure.equalsIgnoreCase("REEXPORT")) {
    	   version = "20";
    	   //procedure = "MANIFEST";  //TODO-IWA: ist es auch SUMA? JAAA!
    	   procedure = "REEXPORT";	
       }
       if (procedure.equalsIgnoreCase("NCTSOUT") || procedure.equalsIgnoreCase("NCTS-OUT")) {    	  
    	   version = "41";
    	   procedure = "NCTS";    	  
       }       
		
        commonFieldsDTO = new CommonFieldsDTO();
        commonFieldsDTO.setKcxId(kcxId);
        commonFieldsDTO.setCountryCode(countryCode);
        commonFieldsDTO.setDirection(direction);        
        if (Utils.isStringEmpty(kidsHeader.getInReplyTo())) {
            commonFieldsDTO.setMessageReferenceNumber(kidsHeader.getMessageID());
        } else {
            commonFieldsDTO.setMessageReferenceNumber(kidsHeader.getInReplyTo());
        }
        kidsHeader.setCommonFieldsDTO(commonFieldsDTO);  
        
        Utils.log("(CMPToKidsConverter readKids) direction = " + direction);       
        if (direction == EDirections.CustomerToCountry) {
            kcxEnvelope.setPrimary(countryCode);
        } else {
        	String kcxIdWithoutDots = Utils.removeDots(kcxId);
            kcxEnvelope.setPrimary(kcxIdWithoutDots);
        }
      //EI20140124: kcxEnvelope.setSecondary(procedure.toUpperCase());
        
		if (direction == EDirections.CustomerToCountry) {
            kidsHeader.setDirection("FROM_CUSTOMER");
            Utils.isProcedureLicensed(kcxId, procedure, countryCode);            
        } else {
            kidsHeader.setDirection("TO_CUSTOMER");           
        }
		Utils.log("(CMPToKidsConverter readKids) direction = " + direction);
	
		//kidsHeader.setMapping(direction);  //in CMP-LCAG haben wir keine kcx-codes 
					
        Utils.log("(CmpToKidsConverter.readKids) kidsHeader: Message = " + msg + ", Procedure = " +  procedure);
       
        switch (EKidsProcedureVersions.valueOf("K" + version + procedure)) {
               
                case K20MANIFEST:                 
                	CmpToKidsManifest cmpToManifest20 = new CmpToKidsManifest();
                	kidsHeader.setRelease("2.0.00");
                	kidsHeader.setProcedure("MANIFEST");
                    mappingResult = cmpToManifest20.readCmp(parser, kidsHeader, commonFieldsDTO);
                    break;                                  
                
                case K41NCTS:	                	                	
                	CmpToKidsNCTS cmpToNcts = new CmpToKidsNCTS();
                	kidsHeader.setRelease("4.1.00");
                	kidsHeader.setProcedure("NCTS");
                	Utils.log("(IWA-CmpToKidsConverter readCmp) NCTS CommonFieldsDTO" + commonFieldsDTO);                    
                    mappingResult = cmpToNcts.readCmp(parser, kidsHeader, commonFieldsDTO);
                    Utils.log("(IWA-CmpToKidsConverter nach der Konvertierung) NCTS mappingResult" + mappingResult);                    
                    Utils.log("(IWA-CmpToKidsConverter nach der Konvertierung) NCTS CommonFieldsDTO" + commonFieldsDTO);                    
                    break;
                    
                case K20REEXPORT:	    //EI20130822            	                	
                	CmpToKidsReExport cmpToReExport = new CmpToKidsReExport();
                	kidsHeader.setRelease("2.0.00");
                	kidsHeader.setProcedure("MANIFEST");
                	procedure = "MANIFEST";  //EI20140124
                    mappingResult = cmpToReExport.readCmp(parser, kidsHeader, commonFieldsDTO);
                    break;
                    
                default:
                    throw new FssException("Unknown KIDS version and procedure " + "K" + version + procedure); 
		}
       
        if (mappingResult == null) {
            return null;
        }
        kcxEnvelope.setSecondary(procedure.toUpperCase()); //EI20140124 
		kcxEnvelope.setTertiary(null);
		kcxEnvelope.setAuditId(auditId);
		kcxEnvelope.setContent(mappingResult);
		
        String kcx = wrapMessageInKcxEnvelope(kcxEnvelope);
        Utils.log("(IWA-CmpToKidsConverter nach der Konvertierung) kcx" + kcx);                    
        logAudit(kcxEnvelope, kidsHeader, commonFieldsDTO);
        
        return kcx;

	}

    // Muss bei Bedarf von den abgeleiteten Klassen überschrieben werden.
    public abstract void logAudit(KcxEnvelope kcxEnvelope, KidsHeader kidsHeader, 
                                                        CommonFieldsDTO commonFieldsDTO) throws Exception;

	private String wrapMessageInKcxEnvelope(KcxEnvelope kcxHeader) {
		StringWriter kcxEnvelopeString = new StringWriter();

		XMLOutputFactory factory = XMLOutputFactory.newInstance();
		try {
			XMLStreamWriter kcxWriter = factory.createXMLStreamWriter(kcxEnvelopeString);
			kcxHeader.writeHeader(kcxWriter);
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
		return kcxEnvelopeString.toString();
	}
	
    private KidsHeader getKidsHeader(XMLEventReader parser) throws Exception {
        KidsHeader kidsHeader = new KidsHeader();
        kidsHeader.setParser(parser);
        kidsHeader.setHeaderFields();
        return kidsHeader;
    }
    
}

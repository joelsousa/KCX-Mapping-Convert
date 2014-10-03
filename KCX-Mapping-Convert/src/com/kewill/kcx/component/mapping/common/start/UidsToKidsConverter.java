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
import com.kewill.kcx.component.mapping.countries.de.aes.UidsToKidsExport;
import com.kewill.kcx.component.mapping.countries.de.aes21.UidsToKidsExportV21;
import com.kewill.kcx.component.mapping.countries.de.emcs.UidsToKidsEmcs;
import com.kewill.kcx.component.mapping.countries.de.emcs21.UidsToKidsEmcs21;
import com.kewill.kcx.component.mapping.countries.de.ics.UidsToKidsICS;
import com.kewill.kcx.component.mapping.countries.de.ics20.UidsToKidsICS20;
import com.kewill.kcx.component.mapping.countries.de.ncts.UidsToKidsNCTS;
import com.kewill.kcx.component.mapping.countries.de.ncts20.UidsToKidsNCTS41;
import com.kewill.kcx.component.mapping.countries.de.suma62.UidsToKidsManifest;
import com.kewill.kcx.component.mapping.countries.de.suma70.UidsToKidsManifest20;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.kcx.KcxEnvelope;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsHeader;
import com.kewill.kcx.component.mapping.util.KcxMappingException;
import com.kewill.kcx.component.mapping.util.Utils;


/**
 * Module:      Start class 
 * Created:     05.09.2008
 * Description: Transformer called to convert UIDS-Format to KIDS messages.
 * 
 * @author kron
 * @version 1.0.00
 */
public abstract class UidsToKidsConverter {
    /**
     * Structure to pass common values.
     */
    protected CommonFieldsDTO commonFieldsDTO       = null;


    public String readUids(String message, String encoding, EDirections direction) throws Exception {
      InputStream ins = new ByteArrayInputStream(message.getBytes());
      InputStreamReader is = new InputStreamReader(ins, encoding);
      XMLInputFactory factory = XMLInputFactory.newInstance();
      XMLEventReader parser = factory.createXMLEventReader(is);
      
      return readUids(parser,  "No Mule Call", encoding, direction);
  }
  
	public String readUids(XMLEventReader parser, String auditId, String encoding, 
	                                                              EDirections direction) throws Exception {
        UidsHeader uidsHeader = getUidsHeader(parser);
        
        KcxEnvelope kcxEnvelope = new KcxEnvelope();
        String kcxId = Utils.getKewillIdFromCustomer(uidsHeader.getTo());
        Utils.log("(UidsToKidsConverter readUids) kcxId = " + kcxId);
	    String countryCode = uidsHeader.getCountryCode();

	    // MS20101018
//        CommonFieldsDTO commonFieldsDTO = new CommonFieldsDTO();
        commonFieldsDTO = new CommonFieldsDTO();
        commonFieldsDTO.setKcxId(kcxId);
        commonFieldsDTO.setUidsId(uidsHeader.getTo());
        commonFieldsDTO.setCountryCode(countryCode);
        commonFieldsDTO.setDirection(direction);
        // MS20101018
        if (Utils.isStringEmpty(uidsHeader.getInreplyto())) {
            commonFieldsDTO.setMessageReferenceNumber(uidsHeader.getMsgid());
        } else {
            commonFieldsDTO.setMessageReferenceNumber(uidsHeader.getInreplyto());
        }
        
        // MS20101129
        String messageType = uidsHeader.getMessageType();
        if (messageType != null) {   //EI20120104
        	if (messageType.equalsIgnoreCase("Confirm") || messageType.equalsIgnoreCase("Failure") || 
        			messageType.equalsIgnoreCase("InternalStatusInformation")) {
        		commonFieldsDTO.setFunctionalAcknowledgement(true);
        	}
        }
        
        String kcxIdWithoutDots = Utils.removeDots(kcxId);

        if (direction == EDirections.CustomerToCountry) {
            kcxEnvelope.setPrimary(countryCode);
        } else {
            kcxEnvelope.setPrimary(kcxIdWithoutDots);
        }
        
        String mappingResult = null;
        String procedure = uidsHeader.getProcedure();             
        
        if (procedure == null) {
            throw new KcxMappingException("No procedure found in UIDS data! Cannot determine mapping class!");
        } else {
            procedure = procedure.toUpperCase();
        }
        Utils.isProcedureLicensed(kcxId, procedure, countryCode);
        
        kcxEnvelope.setSecondary(procedure.toUpperCase());
        
        String version = uidsHeader.getMessageVersion();
        if (version == null) {
            version = "10";
        } else  {
            version = Utils.removeDots(version.substring(0, 3));
        }
        switch (EUidsProcedureVersions.valueOf("U" + version + procedure)) {
            case U10EXPORT:
            case U11EXPORT:
            case U12EXPORT:
            case U20EXPORT:
                UidsToKidsExport uidsToKidsExport = new UidsToKidsExport();
                mappingResult = uidsToKidsExport.readUids(parser, encoding, uidsHeader, commonFieldsDTO);   
                break;                
            case U21EXPORT:
            case U22EXPORT:    //EI20131114
                UidsToKidsExportV21 uidsToKidsExportV21 = new UidsToKidsExportV21();
                mappingResult = uidsToKidsExportV21.readUids(parser, encoding, uidsHeader, commonFieldsDTO);
                break;
                
            case U10EMCS:
            //EI20121012: case U20EMCS:
                UidsToKidsEmcs uidsToKidsEmcs = new UidsToKidsEmcs();
                mappingResult = uidsToKidsEmcs.readUids(parser, encoding, uidsHeader, commonFieldsDTO);
                break;                
            case U20EMCS:                 //EI20121012:
            	//Utils.log("(UidsToKidsConverter) : KIDS-EMCS 2.0.00 nicht aktiviert");
                UidsToKidsEmcs uidsToKidsEmcs20 = new UidsToKidsEmcs();
                mappingResult = uidsToKidsEmcs20.readUids(parser, encoding, uidsHeader, commonFieldsDTO);
                break;
            case U21EMCS:                 //EI20121012:
            	//Utils.log("(UidsToKidsConverter) : KIDS-EMCS 2.0.00 nicht aktiviert");
                UidsToKidsEmcs21 uidsToKidsEmcs21 = new UidsToKidsEmcs21();
                mappingResult = uidsToKidsEmcs21.readUids(parser, encoding, uidsHeader, commonFieldsDTO);
                break;
                
            case U10ICS:
                UidsToKidsICS uidsToKidsICS = new UidsToKidsICS();
                mappingResult = uidsToKidsICS.readUids(parser, encoding, uidsHeader, commonFieldsDTO);
                break;                
            case U20ICS:
                UidsToKidsICS20 uidsToKidsICS20 = new UidsToKidsICS20();
                mappingResult = uidsToKidsICS20.readUids(parser, encoding, uidsHeader, commonFieldsDTO);
                break;
                
            case U40NCTS:	//EB31082010
            	 UidsToKidsNCTS uidsToKidsNCTS = new UidsToKidsNCTS();
                 mappingResult = uidsToKidsNCTS.readUids(parser, encoding, uidsHeader, commonFieldsDTO);
                 break;
            case U41NCTS:	//EI20130204
            	//Utils.log("(UidsToKidsConverter VE-V70) nicht definiert ");
                UidsToKidsNCTS41 uidsToKidsNCTS41 = new UidsToKidsNCTS41();
                mappingResult = uidsToKidsNCTS41.readUids(parser, encoding, uidsHeader, commonFieldsDTO);
                break;           
                
            case U10MANIFEST:	// EI20121221
            	UidsToKidsManifest uidsToKidsManifest = new UidsToKidsManifest();
            	mappingResult = uidsToKidsManifest.readUids(parser, encoding, uidsHeader, commonFieldsDTO);
                break;

            case U20MANIFEST:	// AK20130604
            	UidsToKidsManifest20 uidsToKidsManifest20 = new UidsToKidsManifest20();
            	mappingResult = uidsToKidsManifest20.readUids(parser, encoding, uidsHeader, commonFieldsDTO);
                break;

            default:
                throw new FssException("Unknown UIDS procedure and version " + version);
        }
        
        if (mappingResult == null) {
        	return null;
        }
        
        kcxEnvelope.setTertiary(null);
        kcxEnvelope.setAuditId(auditId);
        kcxEnvelope.setContent(mappingResult);
        kcxEnvelope.setEncoding(encoding);

        String kcx = wrapMessageInKcxEnvelope(kcxEnvelope);

        logAudit(kcxEnvelope, uidsHeader, commonFieldsDTO);
        
        return kcx;
    }

	
	// Muss bei Bedarf von den abgeleiteten Klassen überschrieben werden.
    public abstract void logAudit(KcxEnvelope kcxEnvelope, 
            UidsHeader uidsHeader, CommonFieldsDTO commonFieldsDTO) throws Exception;

    
    private String wrapMessageInKcxEnvelope(KcxEnvelope kcxEnvelope) {
        StringWriter kcxEnvelopeString = new StringWriter();
      
        XMLOutputFactory factory = XMLOutputFactory.newInstance();
        try {
            XMLStreamWriter kcxWriter = factory.createXMLStreamWriter(kcxEnvelopeString);
            kcxEnvelope.writeHeader(kcxWriter);
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
        return kcxEnvelopeString.toString();
    }
	
    private UidsHeader getUidsHeader(XMLEventReader parser) throws Exception {
        UidsHeader uidsHeader = new UidsHeader();
        uidsHeader.setParser(parser);
        uidsHeader.setHeaderFields();
        return uidsHeader;
    }
    
}

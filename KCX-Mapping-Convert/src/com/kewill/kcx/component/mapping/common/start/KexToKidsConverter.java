/*
 * Function    : KexToKidsConverter.java
 * Titel       :
 * Date        : 08.11.2011
 * Author      : krzoska
 * Description : transformer to convert KEX-Format to KIDS messages
 *             : 
 * Parameters  : 

 * Changes 
 * ------------
 * Author      :
 * Date        :
 * Label       :
 * Description:
 *
 */
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
import com.kewill.kcx.component.mapping.countries.de.kex.KexToKids;
import com.kewill.kcx.component.mapping.formats.kcx.KcxEnvelope;
import com.kewill.kcx.component.mapping.formats.kex.msg.KexHeader;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.util.Utils;


/**
 * Transformer called to convert messages KEX-Format to KIDS .
 * @author krzoska
 * @version 1.0.00
 */
public abstract class KexToKidsConverter {
    /**
     * Structure to pass common values.
     */
    protected CommonFieldsDTO commonFieldsDTO       = null;


    public String readKex(String message, String encoding, EDirections direction) throws Exception {
      InputStream ins = new ByteArrayInputStream(message.getBytes());
      InputStreamReader is = new InputStreamReader(ins, encoding);
      XMLInputFactory factory = XMLInputFactory.newInstance();
      XMLEventReader parser = factory.createXMLEventReader(is);
      
      return readKex(parser,  "No Mule Call", encoding, direction);
    }
   
	public String readKex(XMLEventReader parser, String auditId, String encoding, 
	                                                            EDirections direction) throws Exception {        
		KidsHeader kexHeader = getKidsHeader(parser);
        
        KcxEnvelope kcxEnvelope = new KcxEnvelope();
                
        String kcxId = kexHeader.getReceiver();
        Utils.log("(KexToKidsConverter readKex) kcxId = " + kcxId);
        String countryCode = kexHeader.getCountryCode();
        
        commonFieldsDTO = new CommonFieldsDTO();
        commonFieldsDTO.setKcxId(kcxId);          
        commonFieldsDTO.setCountryCode(countryCode);
        commonFieldsDTO.setDirection(direction);
        commonFieldsDTO.setMessageReferenceNumber(kexHeader.getMessageID());         
       
        String kcxIdWithoutDots = Utils.removeDots(kcxId);
        Utils.log("(KexToKidsConverter readKex) kcxIdWithoutDots = " + kcxIdWithoutDots);
        Utils.log("(KexToKidsConverter readKex) direction = " + direction);
        if (direction == EDirections.CustomerToCountry) { 
            kcxEnvelope.setPrimary(countryCode);
        } else {
            Utils.log("(KexToKidsConverter readKex) kcxIdWithoutDots = " + kcxIdWithoutDots);
            kcxEnvelope.setPrimary(kcxIdWithoutDots);
        }
        Utils.log("(KexToKidsConverter readKex) kcxEnvelope.primary = " + kcxEnvelope.getPrimary());

        
        // MS20111117 Begin
//        String procedure = "KEX2KIDS";     
        String procedure = kexHeader.getProcedure();     
        commonFieldsDTO.setProcedure(procedure);
        // MS20111117 End
                
        String version = kexHeader.getRelease();    
        
        if (version == null || Utils.isStringEmpty(version) || version.length() < 2) {
            version = "10";
        } else  {
        	if (version.length() < 4) {        	
        		version = version.substring(1, 2);  
        	} else {        		
        		version = version.substring(1, 2) + version.substring(3, 4);
        	}        	
        }
        
        String mappingResult = null;        
        KexToKids kexToKids = new KexToKids();
        mappingResult = kexToKids.readKex(parser, encoding, kexHeader, commonFieldsDTO);               
        
        kcxEnvelope.setSecondary(procedure.toUpperCase());
        kcxEnvelope.setTertiary(null);
        kcxEnvelope.setAuditId(auditId);
        kcxEnvelope.setContent(mappingResult);
        kcxEnvelope.setEncoding(encoding);

        String kcx = wrapMessageInKcxEnvelope(kcxEnvelope);

        logAudit(kcxEnvelope, kexHeader, commonFieldsDTO);
        
        return kcx;
    }

	
	// Muss bei Bedarf von den abgeleiteten Klassen überschrieben werden.
    public abstract void logAudit(KcxEnvelope kcxEnvelope, 
    		KidsHeader kexHeader, CommonFieldsDTO commonFieldsDTO) throws Exception;

    
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
	
    private KidsHeader getKidsHeader(XMLEventReader parser) throws Exception {
    	KidsHeader kexHeader = new KidsHeader();
    	kexHeader.setParser(parser);
    	kexHeader.setHeaderFields();
        return kexHeader;
    }
}


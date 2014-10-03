/*
 * Function    : UnisysToKidsConverter.java
 * Titel       :
 * Date        : 14.12.2008
 * Author      : krzoska
 * Description : transformer to convert UNISYS-Format to KIDS messages
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
import com.kewill.kcx.component.mapping.countries.de.Port.KffToKidsPort;
import com.kewill.kcx.component.mapping.formats.kcx.KcxEnvelope;
import com.kewill.kcx.component.mapping.formats.kff.msg.JobHeader;
import com.kewill.kcx.component.mapping.util.Utils;


/**
 * Transformer called to convert KFF-Format to KIDS messages.
 * @author iwaniuk
 * @version 1.0.00
 */
public abstract class KffJobToKidsConverter {
    /**
     * Structure to pass common values.
     */
    protected CommonFieldsDTO commonFieldsDTO       = null;


    public String readKff(String message, String encoding, EDirections direction) throws Exception {
      InputStream ins = new ByteArrayInputStream(message.getBytes());
      InputStreamReader is = new InputStreamReader(ins, encoding);
      XMLInputFactory factory = XMLInputFactory.newInstance();
      XMLEventReader parser = factory.createXMLEventReader(is);
      
      return readKff(parser,  "No Mule Call", encoding, direction);
  }
   
	public String readKff(XMLEventReader parser, String auditId, String encoding, 
	                                                            EDirections direction) throws Exception {        
		JobHeader kffHeader = getKffHeader(parser);
        
        KcxEnvelope kcxEnvelope = new KcxEnvelope();
                
        String kcxId = kffHeader.getSenderId();   
        String countryCode = "";
        
	    if (kffHeader != null && !Utils.isStringEmpty(kffHeader.getSenderId())) {
	    	//Ei20111117:kcxId = Utils.getKewillIdFromCustomer(kffHeader.getSenderId(), "KIDS");  
	    	kcxId = Utils.getKewillIdFromCustomer(kffHeader.getSenderId(), "PORT");   
	    	if (kcxId.length() > 2 && kcxId.substring(2, 3).equals(".")) {      		
	    		countryCode = kcxId.substring(0, 2);	    	
	    	}
		} else {
			return "";
		}
        
        commonFieldsDTO = new CommonFieldsDTO();
        commonFieldsDTO.setKcxId(kcxId);          
        commonFieldsDTO.setCountryCode(countryCode);
        commonFieldsDTO.setDirection(direction);
        commonFieldsDTO.setMessageReferenceNumber(kffHeader.getBatchNo());         
       
        String kcxIdWithoutDots = Utils.removeDots(kcxId);
        if (direction == EDirections.CustomerToCountry) { 
            kcxEnvelope.setPrimary(countryCode);
        } else {
            kcxEnvelope.setPrimary(kcxIdWithoutDots);
        }
        
        String procedure = "PORT";  //TODO-EI-Port: danach musste es aus der nachricht ermittelt werden   
        commonFieldsDTO.setProcedure(procedure);
                
        String version = kffHeader.getFormatVersion();    
        String vers = "";
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
        //switch (EKffProcedureVersions.valueOf (procedure)) {    
        //   case PORT:
                KffToKidsPort kffToKidsPort = new KffToKidsPort();
                mappingResult = kffToKidsPort.readKff(parser, encoding, kffHeader, commonFieldsDTO);               
       //         break;
       //   default:
       //         throw new FssException("Unknown procedure " + procedure);
       //}                     
        
        kcxEnvelope.setSecondary(procedure.toUpperCase());
        kcxEnvelope.setTertiary(null);
        kcxEnvelope.setAuditId(auditId);
        kcxEnvelope.setContent(mappingResult);
        kcxEnvelope.setEncoding(encoding);

        String kcx = wrapMessageInKcxEnvelope(kcxEnvelope);

        logAudit(kcxEnvelope, kffHeader, commonFieldsDTO);
        
        return kcx;
    }

	
	// Muss bei Bedarf von den abgeleiteten Klassen überschrieben werden.
    public abstract void logAudit(KcxEnvelope kcxEnvelope, 
    		JobHeader kffHeader, CommonFieldsDTO commonFieldsDTO) throws Exception;

    
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
	
    private JobHeader getKffHeader(XMLEventReader parser) throws Exception {
    	JobHeader kffHeader = new JobHeader();
    	kffHeader.setParser(parser);
    	kffHeader.setHeaderFields();
        return kffHeader;
    }
    
}

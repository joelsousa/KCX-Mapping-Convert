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
import com.kewill.kcx.component.mapping.companies.unisys.ics.UnisysToKidsICS;
import com.kewill.kcx.component.mapping.companies.unisys.ics.msg.UnisysHeader;
import com.kewill.kcx.component.mapping.formats.kcx.KcxEnvelope;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.util.Utils;


/**
 * Transformer called to convert UNISYS-Format to KIDS messages.
 * @author krzoska
 * @version 1.0.00
 */
public abstract class UnisysToKidsConverter {
    /**
     * Structure to pass common values.
     */
    protected CommonFieldsDTO commonFieldsDTO       = null;


    public String readUnisys(String message, String encoding, EDirections direction) throws Exception {
      InputStream ins = new ByteArrayInputStream(message.getBytes());
      InputStreamReader is = new InputStreamReader(ins, encoding);
      XMLInputFactory factory = XMLInputFactory.newInstance();
      XMLEventReader parser = factory.createXMLEventReader(is);
      
      return readUnisys(parser,  "No Mule Call", encoding, direction);
  }
  
	public String readUnisys(XMLEventReader parser, String auditId, String encoding, 
	                                                            EDirections direction) throws Exception {        
		UnisysHeader unisysHeader = getUnisysHeader(parser);
        
        KcxEnvelope kcxEnvelope = new KcxEnvelope();
        //String kcxId = Utils.getKewillIdFromCustomer(unisysHeader.getMsgRecipient());         
        String kcxId = "";   
	    String countryCode = "";        
	    if (unisysHeader.getMsgRecipient() != null && !Utils.isStringEmpty(unisysHeader.getMsgRecipient())) {
	    	//kcxId = Utils.getKewillIdFromCustomer(unisysHeader.getMsgRecipient(), "KIDS");      
	    	//AK20120424
	    	kcxId = Utils.getKewillIdFromCustomer(unisysHeader.getMsgRecipient(), "UNISYS");
	    	if (kcxId.length() > 2 && kcxId.substring(2, 3).equals(".")) {  //Test mit DE.UNSY.TST	    		
	    		countryCode = kcxId.substring(0, 2);	    	
	    	}
		} else {
			return "";
		}

        commonFieldsDTO = new CommonFieldsDTO();
        commonFieldsDTO.setKcxId(kcxId);  
        //commonFieldsDTO.setUidsId(uidsHeader.getTo());  // konnte man mit UNISYS local_id belegen, brauche hier niht
        commonFieldsDTO.setCountryCode(countryCode);
        commonFieldsDTO.setDirection(direction);
        commonFieldsDTO.setMessageReferenceNumber(unisysHeader.getMsgId());         
       
        String kcxIdWithoutDots = Utils.removeDots(kcxId);
        if (direction == EDirections.CustomerToCountry) { 
            kcxEnvelope.setPrimary(countryCode);
        } else {
            kcxEnvelope.setPrimary(kcxIdWithoutDots);
        }
        
        String procedure = "ICS";  //wird in UnisysHeader nicht gesendet, Unisys sendet nur "ICS"   
        commonFieldsDTO.setProcedure(procedure);
                
        String version = unisysHeader.getMsgVersion();
        if (version == null) {
            version = "1";
        } else  {
            version = version.substring(0, 1);
        }
        
        String mappingResult = null;        
//        switch (EUnisysProcedureVersions.valueOf (procedure)) {    
//           case ICS:
                UnisysToKidsICS unisysToKidsICS = new UnisysToKidsICS();
                mappingResult = unisysToKidsICS.readUnisys(parser, encoding, unisysHeader, commonFieldsDTO);
//                break;
//            default:
//                throw new FssException("Unknown procedure " + procedure);
//       }                     
        
        kcxEnvelope.setSecondary(procedure.toUpperCase());
        kcxEnvelope.setTertiary(null);
        kcxEnvelope.setAuditId(auditId);
        kcxEnvelope.setContent(mappingResult);
        kcxEnvelope.setEncoding(encoding);

        String kcx = wrapMessageInKcxEnvelope(kcxEnvelope);

        logAudit(kcxEnvelope, unisysHeader, commonFieldsDTO);
        
        return kcx;
    }

	
	// Muss bei Bedarf von den abgeleiteten Klassen überschrieben werden.
    public abstract void logAudit(KcxEnvelope kcxEnvelope, 
    		UnisysHeader unisysHeader, CommonFieldsDTO commonFieldsDTO) throws Exception;

    
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
	
    private UnisysHeader getUnisysHeader(XMLEventReader parser) throws Exception {
    	UnisysHeader unisysHeader = new UnisysHeader();
    	unisysHeader.setParser(parser);
    	unisysHeader.setHeaderFields();
        return unisysHeader;
    }
    
}

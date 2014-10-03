/*
 * Function    : MaltaToKidsonverter.java
 * Titel       :
 * Date        : 19.08.2013
 * Author      : krzoska
 * Description : Transformer to convert Cyprus messages to KIDS format
 *             : 
 * Parameters  : 

 * Changes 
 * ------------
 * Author      :
 * Date        :
 * Label       :
 * Description :
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
import com.kewill.kcx.component.mapping.countries.mt.ics.MaltaToKidsICS;
import com.kewill.kcx.component.mapping.formats.cyprus.ics.ECyprusICSMessageTypes;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.kcx.KcxEnvelope;
import com.kewill.kcx.component.mapping.formats.malta.common.MaltaHeader;
import com.kewill.kcx.component.mapping.formats.malta.common.ics.EMaltaICSMessageTypes;
import com.kewill.kcx.component.mapping.util.Utils;


/**
 * Transformer to convert Cyprus messages to KIDS format.
 * 
 * @author krzoska 
 * @version 1.0.00
 */
public abstract class MaltaToKidsConverter {
    /**
     * Structure to pass common values.
     */
    protected CommonFieldsDTO commonFieldsDTO       = null;


    public String readMalta(String message, String encoding, EDirections direction) throws Exception {
      InputStream ins = new ByteArrayInputStream(message.getBytes());
      InputStreamReader is = new InputStreamReader(ins, encoding);
      XMLInputFactory factory = XMLInputFactory.newInstance();
      XMLEventReader parser = factory.createXMLEventReader(is);
      
      return readMalta(parser,  "No Mule Call", encoding, direction);
  }
  
	public String readMalta(XMLEventReader parser, String auditId, String encoding, 
	                                                              EDirections direction) throws Exception {
        String  countryCode = "";        
		//malta...header informationen auslesen
		MaltaHeader maltaHeader = getMaltaHeader(parser);
	    
        KcxEnvelope kcxEnvelope = new KcxEnvelope();
        // MS20110906 Begin
        // CY schickt manchmal nur "CY" in MesRecMES6
        // In dem Fall wird der Receiver leer gelsaaen und später aus der Tabelle message_id_history ergänzt.
        // String kcxId = Utils.getKewillIdFromCustomer(maltaHeader.getMesRecMES6(), "CYPRUS");
        String kcxId = null;
        try {
            kcxId = Utils.getKewillIdFromCustomer(maltaHeader.getMesRecMES6(), "MALTA");
        } catch (Exception e) {
            kcxId = "";
        }
        
        //EI20110706:countryCode = "CY";  // Kein Land im Header. Malta-Format kommt aber nur aus Malta. Daher fix MT.
// AK20131111        
//        if (maltaHeader.getMesRecMES6() != null && maltaHeader.getMesRecMES6().length() > 1) { //EI20110706
//			countryCode = maltaHeader.getMesRecMES6().substring(0, 2);
//		} else {
//			countryCode = "MT"; // Kein Land im Header. Malta-Format kommt aber nur aus Malta. Daher fix MT.
//		}
        countryCode = "MT"; // Kein Land im Header. Malta-Format kommt aber nur aus Malta. Daher fix MT.
        
        commonFieldsDTO = new CommonFieldsDTO();
        commonFieldsDTO.setKcxId(kcxId);   
        commonFieldsDTO.setCountryCode(countryCode);
        commonFieldsDTO.setDirection(direction);
        commonFieldsDTO.setMessageReferenceNumber(maltaHeader.getMesIdeMES19());         

            
        String messageType = maltaHeader.getMesTypMES20();
        String procedure = "";
        if (EMaltaICSMessageTypes.valueOf(messageType) != null) {
            procedure = "ICS";
        }
        
        String version = messageType.substring(messageType.length() - 1);
        if (version == null) {
            version = "A";
        }
        String mappingResult = null;
        String procedureVersion = "C_" + version + "_" + procedure;
        switch (ECyprusProcedureVersions.valueOf(procedureVersion)) {    
            case C_A_ICS: 
            case C_K_ICS:	
            case C_B_ICS:
                MaltaToKidsICS maltaToKidsICS = new MaltaToKidsICS();
                mappingResult = maltaToKidsICS.readMalta(parser, encoding, maltaHeader, commonFieldsDTO);
                break;
            default:
                throw new FssException("Unknown Malta procedure and version" + procedureVersion);
        }
        
//        kcxEnvelope.setPrimary(Utils.removeDots(kcxId));
        kcxEnvelope.setPrimary(Utils.removeDots(commonFieldsDTO.getKcxId()));
        kcxEnvelope.setSecondary(procedure.toUpperCase());
        kcxEnvelope.setTertiary(null);
        kcxEnvelope.setAuditId(auditId);
        kcxEnvelope.setContent(mappingResult);
        kcxEnvelope.setEncoding(encoding);

        String kcx = wrapMessageInKcxEnvelope(kcxEnvelope);

        logAudit(kcxEnvelope, maltaHeader, commonFieldsDTO);
        
        return kcx;
    }

	
	// Muss bei Bedarf von den abgeleiteten Klassen überschrieben werden.
    public abstract void logAudit(KcxEnvelope kcxEnvelope, 
    		MaltaHeader maltaHeader, CommonFieldsDTO commonFieldsDTO) throws Exception;

    
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
	
    private MaltaHeader getMaltaHeader(XMLEventReader parser) throws Exception {
    	MaltaHeader maltaHeader = new MaltaHeader();
    	maltaHeader.setParser(parser);
    	maltaHeader.setHeaderFields();
        return maltaHeader;
    }
}
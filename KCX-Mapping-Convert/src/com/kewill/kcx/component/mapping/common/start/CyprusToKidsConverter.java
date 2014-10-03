/*
 * Function    : CyprusToKidsonverter.java
 * Titel       :
 * Date        : 08.06.2011
 * Author      : Kewill CSF / schmidt
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
import com.kewill.kcx.component.mapping.countries.cy.ics.CyprusToKidsICS;
import com.kewill.kcx.component.mapping.formats.cyprus.common.CyprusHeader;
import com.kewill.kcx.component.mapping.formats.cyprus.ics.ECyprusICSMessageTypes;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.kcx.KcxEnvelope;
import com.kewill.kcx.component.mapping.util.Utils;


/**
 * Transformer to convert Cyprus messages to KIDS format.
 * 
 * @author schmidt
 * @version 1.0.00
 */
public abstract class CyprusToKidsConverter {
    /**
     * Structure to pass common values.
     */
    protected CommonFieldsDTO commonFieldsDTO       = null;


    public String readCyprus(String message, String encoding, EDirections direction) throws Exception {
      InputStream ins = new ByteArrayInputStream(message.getBytes());
      InputStreamReader is = new InputStreamReader(ins, encoding);
      XMLInputFactory factory = XMLInputFactory.newInstance();
      XMLEventReader parser = factory.createXMLEventReader(is);
      
      return readCyprus(parser,  "No Mule Call", encoding, direction);
  }
  
	public String readCyprus(XMLEventReader parser, String auditId, String encoding, 
	                                                              EDirections direction) throws Exception {
        String  countryCode = "";        
		//cyprus...header informationen auslesen
		CyprusHeader cyprusHeader = getCyprusHeader(parser);
	    
        KcxEnvelope kcxEnvelope = new KcxEnvelope();
        // MS20110906 Begin
        // CY schickt manchmal nur "CY" in MesRecMES6
        // In dem Fall wird der Receiver leer gelsaaen und später aus der Tabelle message_id_history ergänzt.
        // String kcxId = Utils.getKewillIdFromCustomer(cyprusHeader.getMesRecMES6(), "CYPRUS");
        String kcxId = null;
        try {
            kcxId = Utils.getKewillIdFromCustomer(cyprusHeader.getMesRecMES6(), "CYPRUS");
        } catch (Exception e) {
            kcxId = "";
        }
        
        //EI20110706:countryCode = "CY";  // Kein Land im Header. Cyprus-Format kommt aber nur aus Zypern. Daher fix CY.
        if (cyprusHeader.getMesRecMES6() != null && cyprusHeader.getMesRecMES6().length() > 1) { //EI20110706
			countryCode = cyprusHeader.getMesRecMES6().substring(0, 2);
		} else {
			countryCode = "CY"; // Kein Land im Header. Cyprus-Format kommt aber nur aus Zypern. Daher fix CY.
		}
        
        commonFieldsDTO = new CommonFieldsDTO();
        commonFieldsDTO.setKcxId(kcxId);   
        commonFieldsDTO.setCountryCode(countryCode);
        commonFieldsDTO.setDirection(direction);
        commonFieldsDTO.setMessageReferenceNumber(cyprusHeader.getMesIdeMES19());         

            
        String messageType = cyprusHeader.getMesTypMES20();
        String procedure = "";
        if (ECyprusICSMessageTypes.valueOf(messageType) != null) {
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
            case C_B_ICS:
                CyprusToKidsICS cyprusToKidsICS = new CyprusToKidsICS();
                mappingResult = cyprusToKidsICS.readCyprus(parser, encoding, cyprusHeader, commonFieldsDTO);
                break;
            default:
                throw new FssException("Unknown Cyprus procedure and version" + procedureVersion);
        }
        
//        kcxEnvelope.setPrimary(Utils.removeDots(kcxId));
        kcxEnvelope.setPrimary(Utils.removeDots(commonFieldsDTO.getKcxId()));
        kcxEnvelope.setSecondary(procedure.toUpperCase());
        kcxEnvelope.setTertiary(null);
        kcxEnvelope.setAuditId(auditId);
        kcxEnvelope.setContent(mappingResult);
        kcxEnvelope.setEncoding(encoding);

        String kcx = wrapMessageInKcxEnvelope(kcxEnvelope);

        logAudit(kcxEnvelope, cyprusHeader, commonFieldsDTO);
        
        return kcx;
    }

	
	// Muss bei Bedarf von den abgeleiteten Klassen überschrieben werden.
    public abstract void logAudit(KcxEnvelope kcxEnvelope, 
    		CyprusHeader cyprusHeader, CommonFieldsDTO commonFieldsDTO) throws Exception;

    
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
	
    private CyprusHeader getCyprusHeader(XMLEventReader parser) throws Exception {
    	CyprusHeader cyprusHeader = new CyprusHeader();
    	cyprusHeader.setParser(parser);
    	cyprusHeader.setHeaderFields();
        return cyprusHeader;
    }
    
}

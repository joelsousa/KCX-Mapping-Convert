/*
 * Function    : GreeceToKidsonverter.java
 * Titel       :
 * Date        : 18.07.2011
 * Author      : Kewill CSF / schmidt
 * Description : Transformer to convert Greece messages to KIDS format
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
import com.kewill.kcx.component.mapping.countries.gr.ics.EGreeceICSMessages;
import com.kewill.kcx.component.mapping.countries.gr.ics.GreeceToKidsICS;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.greece.common.GreeceHeader;
import com.kewill.kcx.component.mapping.formats.kcx.KcxEnvelope;
import com.kewill.kcx.component.mapping.util.Utils;


/**
 * Transformer to convert Greece messages to KIDS format.
 * 
 * @author schmidt
 * @version 1.0.00
 */
public abstract class GreeceToKidsConverter {
    /**
     * Structure to pass common values.
     */
    protected CommonFieldsDTO commonFieldsDTO       = null;


    public String readGreece(String message, String encoding, EDirections direction) throws Exception {
      InputStream ins = new ByteArrayInputStream(message.getBytes());
      InputStreamReader is = new InputStreamReader(ins, encoding);
      XMLInputFactory factory = XMLInputFactory.newInstance();
      XMLEventReader parser = factory.createXMLEventReader(is);

      return readGreece(parser,  "No Mule Call", encoding, direction);
  }
  
	public String readGreece(XMLEventReader parser, String auditId, String encoding, EDirections direction) 
	                                                                                                throws Exception {
        String  countryCode = "";        
		//greece...header informationen auslesen
		GreeceHeader greeceHeader = getGreeceHeader(parser);
	    
        KcxEnvelope kcxEnvelope = new KcxEnvelope();
        // MS20110906 Begin
        // CY schickt manchmal nur "CY" in MesRecMES6
        // In dem Fall wird der Receiver leer gelsaaen und später aus der Tabelle message_id_history ergänzt.
        // String kcxId = Utils.getKewillIdFromCustomer(greeceHeader.getMesRecMES6(), "GREECE");
        String kcxId = null;
        try {
            kcxId = Utils.getKewillIdFromCustomer(greeceHeader.getMesRecMES6(), "GREECE");
        } catch (Exception e) {
            kcxId = "";
        }

        // MS20110906 Begin
        // Von GR kommt in MesRecMES6 immer "TRADER.GR"
        // Daher funktioniert das nicht mehr, so wie in CY.
//        if (greeceHeader.getMesRecMES6() != null && greeceHeader.getMesRecMES6().length() > 1) { //EI20110706
//			countryCode = greeceHeader.getMesRecMES6().substring(0, 2);
//		} else {
//			countryCode = "GR"; // Kein Land im Header. Greece-Format kommt aber nur aus Griechenland. Daher fix GR.
//		}
        countryCode = "GR"; // Kein Land im Header. Greece-Format kommt aber nur aus Griechenland. Daher fix GR.
        // MS20110906 End
        
        commonFieldsDTO = new CommonFieldsDTO();
        commonFieldsDTO.setKcxId(kcxId);   
        commonFieldsDTO.setCountryCode(countryCode);
        commonFieldsDTO.setDirection(direction);
        commonFieldsDTO.setMessageReferenceNumber(greeceHeader.getMesIdeMES19());      

            
        String messageType = greeceHeader.getMesTypMES20();
        String procedure = "";
        if (EGreeceICSMessages.valueOf(messageType) != null) {
            procedure = "ICS";
        }
        
        String version = messageType.substring(messageType.length() - 1);
        if (version == null || version.equals("p")) {
            version = "A";
        }
        String mappingResult = null;
        //String procedureVersion = "C_" + version + "_" + procedure;
        String procedureVersion = "G_" + version + "_" + procedure; //EI20110810
        switch (EGreeceProcedureVersions.valueOf(procedureVersion)) {    
            case G_A_ICS: 
            case G_B_ICS:            
                GreeceToKidsICS greeceToKidsICS = new GreeceToKidsICS();
                mappingResult = greeceToKidsICS.readGreece(parser, encoding, greeceHeader, commonFieldsDTO);
                break;
            default:
                throw new FssException("Unknown Greece procedure and version" + procedureVersion);
        }
        
        // MS20111020 Begin
        if (mappingResult == null) {
            return null;
        }
        // MS20111020 End
        
//        kcxEnvelope.setPrimary(Utils.removeDots(kcxId));
        kcxEnvelope.setPrimary(Utils.removeDots(commonFieldsDTO.getKcxId()));
        kcxEnvelope.setSecondary(procedure.toUpperCase());
        kcxEnvelope.setTertiary(null);
        kcxEnvelope.setAuditId(auditId);
        kcxEnvelope.setContent(mappingResult);
        kcxEnvelope.setEncoding(encoding);

        String kcx = wrapMessageInKcxEnvelope(kcxEnvelope);

        logAudit(kcxEnvelope, greeceHeader, commonFieldsDTO);
        
        return kcx;
    }

	
	// Muss bei Bedarf von den abgeleiteten Klassen überschrieben werden.
    public abstract void logAudit(KcxEnvelope kcxEnvelope, 
    		GreeceHeader header, CommonFieldsDTO commonFieldsDTO) throws Exception;

    
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
	
    private GreeceHeader getGreeceHeader(XMLEventReader parser) throws Exception {
    	GreeceHeader header = new GreeceHeader();
    	header.setParser(parser);
    	header.setHeaderFields();
        return header;
    }
    
}

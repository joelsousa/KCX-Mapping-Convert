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
import com.kewill.kcx.component.mapping.countries.ie.IrelandHeader;
import com.kewill.kcx.component.mapping.countries.ie.IrelandToKids;
import com.kewill.kcx.component.mapping.formats.cyprus.ics.ECyprusICSMessageTypes;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.kcx.KcxEnvelope;
import com.kewill.kcx.component.mapping.util.Utils;


/** 
 * Module		: Ireland<br>
 * Created		: 05.06.2014<br>
 * Description	: Transformer to convert Ireland messages to KIDS format.
 *                           
 * @author Iwaniuk
 * @version 1.0.00
 */

public abstract class IrelandToKidsConverter {
    /**
     * Structure to pass common values.
     */
    protected CommonFieldsDTO commonFieldsDTO       = null;


    public String readIreland(String message, String encoding, EDirections direction) throws Exception {
      InputStream ins = new ByteArrayInputStream(message.getBytes());
      InputStreamReader is = new InputStreamReader(ins, encoding);
      XMLInputFactory factory = XMLInputFactory.newInstance();
      XMLEventReader parser = factory.createXMLEventReader(is);
      
      return readIreland(parser,  "No Mule Call", encoding, direction);
  }
  
	public String readIreland(XMLEventReader parser, String auditId, String encoding, 
	                                                              EDirections direction) throws Exception {
        String  countryCode = "";        
		//...header informationen auslesen
		IrelandHeader irelandHeader = getIrelandHeader(parser);
	    
        KcxEnvelope kcxEnvelope = new KcxEnvelope();       
        String kcxId = null;
        
        try {
            kcxId = Utils.getKewillIdFromCustomer(irelandHeader.getMesageReceiver(), "IRELAND");
        } catch (Exception e) {
            kcxId = "";
        }
        
        // todo
        /*if (irelandHeader.getMesRecMES6() != null && irelandHeader.getMesRecMES6().length() > 1) { //EI20110706
			countryCode = irelandHeader.getMesRecMES6().substring(0, 2);
		} else {
			countryCode = "IE"; 
		}*/
        
        commonFieldsDTO = new CommonFieldsDTO();
        commonFieldsDTO.setKcxId(kcxId);   
        commonFieldsDTO.setCountryCode(countryCode);
        commonFieldsDTO.setDirection(direction);
        commonFieldsDTO.setMessageReferenceNumber(irelandHeader.getMesageReferenceNumber());         
       
        String procedure = irelandHeader.getProcedure();
        if (Utils.isStringEmpty(procedure)) {
            procedure = "Import";
        }
        
        String version = irelandHeader.getRelease();
        if (Utils.isStringEmpty(version)) {
            version = "10";
        }
        String mappingResult = null;
        String procedureVersion = "IE" + version + procedure;
        //switch (EieProcedureVersions.valueOf(procedureVersion)) {    
        //    case K10IMPORT: 
            
                IrelandToKids irelandToKids = new IrelandToKids();
                mappingResult = irelandToKids.readIreland(parser, encoding, irelandHeader, commonFieldsDTO);
       //         break;
       //     default:
       //         throw new FssException("Unknown Cyprus procedure and version" + procedureVersion);
       // }
        
//        kcxEnvelope.setPrimary(Utils.removeDots(kcxId));
        kcxEnvelope.setPrimary(Utils.removeDots(commonFieldsDTO.getKcxId()));
        kcxEnvelope.setSecondary(procedure.toUpperCase());
        kcxEnvelope.setTertiary(null);
        kcxEnvelope.setAuditId(auditId);
        kcxEnvelope.setContent(mappingResult);
        kcxEnvelope.setEncoding(encoding);

        String kcx = wrapMessageInKcxEnvelope(kcxEnvelope);

        logAudit(kcxEnvelope, irelandHeader, commonFieldsDTO);
       
        return kcx;
    }

	
	// Muss bei Bedarf von den abgeleiteten Klassen überschrieben werden.
    public abstract void logAudit(KcxEnvelope kcxEnvelope, 
    		IrelandHeader header, CommonFieldsDTO commonFieldsDTO) throws Exception;

    
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
	
    private IrelandHeader getIrelandHeader(XMLEventReader parser) throws Exception {    	
    	IrelandHeader header = new IrelandHeader();
    	header.setParser(parser);
    	header.setHeaderFields();
        return header;
       
    }
    
}

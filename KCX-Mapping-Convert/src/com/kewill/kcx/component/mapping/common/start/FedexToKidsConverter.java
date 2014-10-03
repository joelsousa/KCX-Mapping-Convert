/*
 * Function    : UidsToKidsonverter.java
 * Titel       :
 * Date        : 05.09.2008
 * Author      : Kewill CSF / kron
 * Description : transformer to convert FEDEX-Format to KIDS messages
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
import com.kewill.kcx.component.mapping.companies.fedex.ics.EFedexICSMessages;
import com.kewill.kcx.component.mapping.companies.fedex.ics.FedexToKidsICS;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.kcx.KcxEnvelope;
import com.kewill.kcx.component.mapping.companies.fedex.ics.msg.FedexHeader;
import com.kewill.kcx.component.mapping.util.KcxMappingException;
import com.kewill.kcx.component.mapping.util.Utils;


/**
 * Transformer called to convert FEDEX-Format to KIDS messages.
 * @author kron
 * @version 1.0.00
 */
public abstract class FedexToKidsConverter {
    /**
     * Structure to pass common values.
     */
    protected CommonFieldsDTO commonFieldsDTO       = null;


    public String readFedex(String message, String encoding, EDirections direction) throws Exception {
      InputStream ins = new ByteArrayInputStream(message.getBytes());
      InputStreamReader is = new InputStreamReader(ins, encoding);
      XMLInputFactory factory = XMLInputFactory.newInstance();
      XMLEventReader parser = factory.createXMLEventReader(is);
      
      return readFedex(parser,  "No Mule Call", encoding, direction);
  }
  
	public String readFedex(XMLEventReader parser, String auditId, String encoding, 
	                                                              EDirections direction) throws Exception {
        String  countryCode = "";        
		//fedex...header informationen auslesen
		FedexHeader fedexHeader = getFedexHeader(parser);
        
        KcxEnvelope kcxEnvelope = new KcxEnvelope();
        //String kcxId = Utils.getKewillIdFromCustomer(fedexHeader.getReceiver());  <== TODO-ak kein receiver im Header
        String kcxId = "";
        
        if (fedexHeader.getSchemaID().equalsIgnoreCase(EFedexICSMessages.Message347FR.name())) {
        	countryCode = fedexHeader.getPartyId();
        } else  {
        	countryCode = fedexHeader.getCountryCode();
        }

        commonFieldsDTO = new CommonFieldsDTO();
        //commonFieldsDTO.setKcxId(kcxId);     <== TODO-ak   
        commonFieldsDTO.setCountryCode(countryCode);
        commonFieldsDTO.setDirection(direction);
        commonFieldsDTO.setMessageReferenceNumber(fedexHeader.getTransactionId());         

            
        //String procedure = fedexHeader.getProcedure();        <== TODO-ak keine procedure im Header   
        String procedure = "ICS";
        //kcxEnvelope.setSecondary(procedure.toUpperCase());
        
        String version = fedexHeader.getSchemaVersion();
        if (version == null) {
            version = "1";
        } else  {
            version = version.substring(0, 1);
        }
        String mappingResult = null;
        //switch (EFedexProcedureVersions.valueOf("F" + version + procedure)) {  
        switch (EFedexProcedureVersions.valueOf(procedure)) {    
            case ICS:
                FedexToKidsICS fedexToKidsICS = new FedexToKidsICS();
                mappingResult = fedexToKidsICS.readFedex(parser, encoding, fedexHeader, commonFieldsDTO);
                break;
            default:
                throw new FssException("Unknown procedure " + procedure);
        }
        
        //AK20101213
        kcxEnvelope.setPrimary(fedexHeader.getCountryCode());
        kcxEnvelope.setSecondary("ICS");
        kcxEnvelope.setTertiary(null);
        kcxEnvelope.setAuditId(auditId);
        kcxEnvelope.setContent(mappingResult);
        kcxEnvelope.setEncoding(encoding);

        String kcx = wrapMessageInKcxEnvelope(kcxEnvelope);

        logAudit(kcxEnvelope, fedexHeader, commonFieldsDTO);
        
        return kcx;
    }

	
	// Muss bei Bedarf von den abgeleiteten Klassen überschrieben werden.
    public abstract void logAudit(KcxEnvelope kcxEnvelope, 
    		FedexHeader fedexHeader, CommonFieldsDTO commonFieldsDTO) throws Exception;

    
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
	
    private FedexHeader getFedexHeader(XMLEventReader parser) throws Exception {
    	FedexHeader fedexHeader = new FedexHeader();
    	fedexHeader.setParser(parser);
    	fedexHeader.setHeaderFields();
        return fedexHeader;
    }
    
}

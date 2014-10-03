package com.kewill.kcx.component.mapping.common.start;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;

import com.kewill.kcx.component.mapping.EDirections;
import com.kewill.kcx.component.mapping.common.CommonFieldsDTO;
import com.kewill.kcx.component.mapping.countries.de.kex.KidsToKex;
import com.kewill.kcx.component.mapping.db.CustomerDataDTO;
import com.kewill.kcx.component.mapping.db.CustomerProcedureDTO;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.util.Utils;


/**
 * Module       : KidsToKexConverter<br>
 * Created 		: 15.11.2011<br>
 * Description  : transformer called by Mule to convert KIDS-Format to Kewill Export (KEX) format. 
 * 
 * @author schmidt
 * @version 1.0.00
 */

public abstract class KidsToKexConverter {
    /**
     * Value Objekt für diverse oft benötigte Variablen.
     */
    protected CommonFieldsDTO commonFieldsDTO		= null;

    
	public Object readKids(String payload, String encoding, String filename, EDirections direction) 
	                                                                                throws Exception {
	    String content = new RemoveKcxEnvelope().removeEnvelope(payload, encoding);

        InputStream ins = new ByteArrayInputStream(content.getBytes());
        InputStreamReader is = new InputStreamReader(ins, encoding);
        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLEventReader parser = factory.createXMLEventReader(is);
        
        return readKids(parser, encoding, filename, direction);
	}
	
    public Object readKids(XMLEventReader parser, String encoding, String filename, EDirections direction) 
                                                                                      throws Exception {
        KidsHeader kidsHeader = getKidsHeader(parser);
        Utils.log("(KidsToKexConverter readKids) kidsHeader.getMessageName() = " + kidsHeader.getMessageName());
		
        String localId = null;
        localId = Utils.getCustomerIdFromKewill(kidsHeader.getReceiver(), "KEX", kidsHeader.getCountryCode()).trim();
        Utils.log("(KidsToKexConverter readKids) localId = " + localId);
        
        commonFieldsDTO = new CommonFieldsDTO();
        commonFieldsDTO.setKcxId(kidsHeader.getReceiver());
        commonFieldsDTO.setUidsId(localId);
        CustomerProcedureDTO customerProcedureDTO = Utils.getCustomerProceduresFromKcxId(
                                                        kidsHeader.getReceiver(), 
                                                        kidsHeader.getProcedure().toUpperCase());
        commonFieldsDTO.setCustomerProcedureDTO(customerProcedureDTO);
        CustomerDataDTO customerDataDTO = Utils.getCustomerDataFromKcxId(kidsHeader.getReceiver());
        commonFieldsDTO.setCustomerDataDTO(customerDataDTO);
        commonFieldsDTO.setCountryCode(kidsHeader.getCountryCode());
        commonFieldsDTO.setFilename(filename);

        if (Utils.isStringEmpty(kidsHeader.getInReplyTo())) {
            commonFieldsDTO.setMessageReferenceNumber(kidsHeader.getMessageID());
        } else {
            commonFieldsDTO.setMessageReferenceNumber(kidsHeader.getInReplyTo());
        }
        commonFieldsDTO.setDirection(direction);
        
        Object result = null;
        String procedure = kidsHeader.getProcedure().toUpperCase();
        Utils.isProcedureLicensed(kidsHeader.getReceiver(), procedure, kidsHeader.getCountryCode());
        
        commonFieldsDTO.setProcedure(procedure);
        String version = kidsHeader.getRelease();
        if (version == null) {
            version = "10";
        } else  {
            version = Utils.removeDots(version.substring(0, 3));
        }
        switch (EKidsProcedureVersions.valueOf("K" + version + procedure)) {
            case K10EXPORT:
            case K11EXPORT:
            case K12EXPORT:
            case K20EXPORT:
            case K21EXPORT:   //EI20120802, eigentlich KEX bekommt nur KIDS
                KidsToKex kidsToKex = new KidsToKex();
                result = kidsToKex.readKids(parser, encoding, kidsHeader, commonFieldsDTO);
                break;
            default:
                throw new FssException("Unknown KIDS procedure and version " + version);
        }
        
        logAudit(kidsHeader, commonFieldsDTO);
        
        return result;
    }
    
    // Muss bei Bedarf von den abgeleiteten Klassen überschrieben werden.
    public abstract void logAudit(KidsHeader kidsHeader, CommonFieldsDTO commonFieldsDTO) throws Exception;

    
    private KidsHeader getKidsHeader(XMLEventReader parser) throws Exception {
        KidsHeader kidsHeader = new KidsHeader();
        kidsHeader.setParser(parser);
        kidsHeader.setHeaderFields();
        return kidsHeader;
    }
    
}

package com.kewill.kcx.component.mapping.common.start;

/*
 * Function    : KidsToUids.java
 * Titel       :
 * Date        : 02.09.2008
 * Author      : Kewill CSF / Christine Kron
 * Description : transformer called by Mule 
 * 				 to convert KIDS-Format to UIDS messages
 * 			   : 
 * Parameters  : 

 * Changes 
 * -----------
 * Author      : Sven Heise
 * Date        : 08.09.2008
 * Label       : 
 * Description : InternalStatusInformation, Cancellation, Completion (25.09.08)
 *
 */

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
import com.kewill.kcx.component.mapping.companies.fedex.ics.FedexEnvelope;
import com.kewill.kcx.component.mapping.countries.de.emcs.KidsToUidsEmcs;
import com.kewill.kcx.component.mapping.countries.de.ics.KidsToUidsICS;
import com.kewill.kcx.component.mapping.countries.de.ics20.KidsToUidsICS20;
import com.kewill.kcx.component.mapping.countries.fr.ncts.KidsToFrNCTS;
import com.kewill.kcx.component.mapping.db.CustomerDataDTO;
import com.kewill.kcx.component.mapping.db.CustomerProcedureDTO;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.util.Utils;


/**
 * Module       : KidsToFrExportImportConverter<br>
 * Created 		: 19.11.2013<br>
 * Description  : transformer called by Mule 
 *                to convert KIDS-Format to FR: IE20, IE07 messages.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public abstract class KidsToFranceConverter {
    /**
     * Muss hier definiert sein wg. filename-Aenderung bei tgz-Dateien
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
        Utils.log("(KidsToFrance readKids) kidsHeader.getMessageName() = " + kidsHeader.getMessageName());
        
        commonFieldsDTO = new CommonFieldsDTO();
        Object result = null;
        
        String localIdUids = ""; 
        String localIdNCTS = "";
        String kcxId = kidsHeader.getReceiver();   
        String procedure = kidsHeader.getProcedure();
        String version = kidsHeader.getRelease();
        
        if (kidsHeader != null && !Utils.isStringEmpty(procedure)) {
        	procedure = kidsHeader.getProcedure().toUpperCase();

        	if (procedure.equals("NCTS")) {        
        		localIdNCTS = Utils.getCustomerIdFromKewill(kcxId, "NCTS", kidsHeader.getCountryCode()).trim();
        	} else {
        		localIdUids = Utils.getCustomerIdFromKewill(kcxId, "UIDS", kidsHeader.getCountryCode()).trim();        	
        	} 
        } else { 
        	return null;        
        }
        Utils.log("(KidsToFrConverter readKids) localIdFr = " + localIdUids + ", localIdNCTS = " + localIdNCTS);
        Utils.isProcedureLicensed(kcxId, procedure, kidsHeader.getCountryCode());
        
        commonFieldsDTO.setKcxId(kcxId);
        commonFieldsDTO.setUidsId(localIdUids);
        commonFieldsDTO.setLocalId(localIdNCTS);
        commonFieldsDTO.setProcedure(procedure);
        commonFieldsDTO.setDirection(direction);
        CustomerProcedureDTO customerProcedureDTO = Utils.getCustomerProceduresFromKcxId(kcxId, procedure);
        commonFieldsDTO.setCustomerProcedureDTO(customerProcedureDTO);
        CustomerDataDTO customerDataDTO = Utils.getCustomerDataFromKcxId(kcxId);
        commonFieldsDTO.setCustomerDataDTO(customerDataDTO);
        commonFieldsDTO.setCountryCode(kidsHeader.getCountryCode());
        commonFieldsDTO.setFilename(filename);
   
        if (Utils.isStringEmpty(kidsHeader.getInReplyTo())) {
            commonFieldsDTO.setMessageReferenceNumber(kidsHeader.getMessageID());
        } else {
            commonFieldsDTO.setMessageReferenceNumber(kidsHeader.getInReplyTo());
        }
               
        if (version == null) {
            version = "10";
        } else  {
            version = Utils.removeDots(version.substring(0, 3));
        }
        
        switch (EKidsProcedureVersions.valueOf("K" + version + procedure)) {
        
        	case K10ICS:
        		KidsToUidsICS kidsToUidsICS = new KidsToUidsICS();
        		result = kidsToUidsICS.readKids(parser, encoding, kidsHeader, commonFieldsDTO);
        		break;
        	case K20ICS:
        		KidsToUidsICS20 kidsToUidsICS20 = new KidsToUidsICS20();
        		result = kidsToUidsICS20.readKids(parser, encoding, kidsHeader, commonFieldsDTO);
        		break;
        		
        	case K10EMCS:            	                 
                KidsToUidsEmcs kidsToUidsEmcs = new KidsToUidsEmcs();
                result = kidsToUidsEmcs.readKids(parser, encoding, kidsHeader, commonFieldsDTO);
                break;
            case K20EMCS:                     	
                KidsToUidsEmcs kidsToUidsEmcs20 = new KidsToUidsEmcs();
                result = kidsToUidsEmcs20.readKids(parser, encoding, kidsHeader, commonFieldsDTO);
                break;
        		
            case K40NCTS:  
            case K41NCTS: 
                KidsToFrNCTS kidsToFrNCTS = new KidsToFrNCTS();
                result = kidsToFrNCTS.readKids(parser, kidsHeader, encoding);                
                break;
                
            default:
                throw new FssException("Unknown KIDS procedure and version " + version);
        }

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

package com.kewill.kcx.component.mapping.countries.de.suma62;

import java.io.ByteArrayOutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.EDirections;
import com.kewill.kcx.component.mapping.common.CommonFieldsDTO;
import com.kewill.kcx.component.mapping.countries.de.suma62.uids2kids.MapNotificationOfPresentationUK;
import com.kewill.kcx.component.mapping.db.CustomerProcedureDTO;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsHeader;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module    	: Manifest/SumA
 * Created     	: 21.12.2012
 * Description 	: Transformer to convert UIDS-Format to KIDS ICS messages.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class UidsToKidsManifest {

	public String readUids(XMLEventReader parser, String encoding, 
			UidsHeader uidsHeader, CommonFieldsDTO commonFieldsDTO) throws Exception {
		
		String      kcxId       = commonFieldsDTO.getKcxId();
        EDirections direction   = commonFieldsDTO.getDirection();
        String      countryCode = commonFieldsDTO.getCountryCode();  //EI20110808
        
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        XMLStreamWriter writer    = getWriter(bos, encoding);
        KidsHeader kidsHeader     = new KidsHeader(writer);
        kidsHeader.setReceiver(kcxId);
        kidsHeader.setCountryCode(countryCode);
        kidsHeader.setProcedure(uidsHeader.getProcedure().toUpperCase());       
        kidsHeader.setRelease("1.0.00");

        if (direction == EDirections.CustomerToCountry) {
            kidsHeader.setDirection("FROM_CUSTOMER");
            Utils.isProcedureLicensed(kcxId, uidsHeader.getProcedure(), countryCode);   
        } else {
            kidsHeader.setDirection("TO_CUSTOMER");
        }
        
        kidsHeader.setCommonFieldsDTO(commonFieldsDTO);
        
        String msg = uidsHeader.getMessageType();
        if (Utils.isStringEmpty(msg)) {      
        	msg = uidsHeader.getMessageName();
        }
        
        Utils.log("(UidsToKidsManifest readUids) uidsHeader.messageName = " + msg);
        
        switch(EUidsManifestMessages.valueOf(msg)) {
	        case NotificationOfPresentation:
	        	MapNotificationOfPresentationUK mapNotificationOfPresentationUK = 
	        		new MapNotificationOfPresentationUK(parser, kidsHeader, encoding);
	    		prepareMessage(uidsHeader, kidsHeader, mapNotificationOfPresentationUK, 
	    			commonFieldsDTO, "NotificationOfPresentation", direction);
	    		mapNotificationOfPresentationUK.getMessage(writer);
	    		break;	    			    
	    		
	    	default: throw new FssException("Unknown message type " + msg);
        }
        
        String xml = bos.toString();
        Utils.log("(UidsToKidsManifest) converted message = \n" + xml);
        return xml;
	}
	
	private void prepareMessage(UidsHeader uidsHeader, KidsHeader kidsHeader, 
			KidsMessage message, CommonFieldsDTO commonFieldsDTO, String messageType, 
			EDirections direction) throws XMLStreamException {
		
		mapUidsToKidsHeader(uidsHeader, kidsHeader, messageType);
		setKidsHeaderMappingFields(uidsHeader, kidsHeader, direction);
		message.setUidsHeader(uidsHeader);
		message.setCommonFieldsDTO(commonFieldsDTO);
	}
	
	private XMLStreamWriter getWriter(ByteArrayOutputStream bos, String encoding) throws Exception {
        OutputStreamWriter osw = null;
        try {
            osw = new OutputStreamWriter(bos, encoding);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        XMLOutputFactory factory = XMLOutputFactory.newInstance();
        XMLStreamWriter writer   = factory.createXMLStreamWriter(osw);
        
	    return writer;
	}
	
	private KidsHeader mapUidsToKidsHeader(UidsHeader uidsHeader, KidsHeader kidsHeader, String messageName) {
    	kidsHeader.setDay(uidsHeader.getDay());
    	kidsHeader.setMonth(uidsHeader.getMonth());
    	kidsHeader.setYear(uidsHeader.getYear());
    	kidsHeader.setTime(uidsHeader.getTime());
    	kidsHeader.setTimeZone(uidsHeader.getTimezone());
    	kidsHeader.setTransmitter(uidsHeader.getFrom());    
    	kidsHeader.setMethod(uidsHeader.getMethod());
    	kidsHeader.setMessageName(messageName);        
  		kidsHeader.setMessageID(uidsHeader.getMsgid());
    	kidsHeader.setInReplyTo(uidsHeader.getInreplyto());
    
    	return kidsHeader;
    }
	
	private void setKidsHeaderMappingFields(UidsHeader uidsHeader, KidsHeader kidsHeader, EDirections direction) {
        CustomerProcedureDTO customerProcedureDTO = 
            Utils.getCustomerProceduresFromKcxId(kidsHeader.getReceiver(), kidsHeader.getProcedure().toUpperCase());
        String mappingCode = customerProcedureDTO.getMappingCode();

        Utils.log("(UidsToKidsManifest setKidsHeaderMappingFields) mappingCode                 = " + mappingCode);
        Utils.log("(UidsToKidsManifest setKidsHeaderMappingFields) uidsHeader.getCountryCode() = " + 
                                                                               uidsHeader.getCountryCode());
        Utils.log("(UidsToKidsManifest setKidsHeaderMappingFields) kidsHeader.getCountryCode() = " + 
                                                                               kidsHeader.getCountryCode());
        
        
        // Wenn Nachricht von Kunde Richtung Zoll,  dann ist mapFrom = mappingCode
        // und mapTo = countryCode.
        // Wenn Nachricht von Zoll  Richtung Kunde, dann ist mapFrom = countryCode
        // und mapto = mappingCode.
        
		// CK 2011-01-20 mapping code "EU" with the actual procedure means "no code mapping"
        
        if (mappingCode.equalsIgnoreCase("EU")) {
        	kidsHeader.setMap("0");
        } else {        
        	if (direction == EDirections.CustomerToCountry) {
	            kidsHeader.setMapFrom(mappingCode);
            	kidsHeader.setMapTo(uidsHeader.getCountryCode());
        	} else {
	            kidsHeader.setMapFrom(uidsHeader.getCountryCode());
            	kidsHeader.setMapTo(mappingCode);
        	}
        
        	Utils.log("(UidsToKidsManifest setKidsHeaderMappingFields) " +
        		                	"kidsHeader.getMapFrom() = " + kidsHeader.getMapFrom());
        	Utils.log("(UidsToKidsManifest setKidsHeaderMappingFields) " +
        		                	"kidsHeader.getMapTo()   = " + kidsHeader.getMapTo());
        	if (kidsHeader.getMapFrom().equalsIgnoreCase(kidsHeader.getMapTo())) {
	            kidsHeader.setMap("0");
        	} else {
            	kidsHeader.setMap("1");
        	}
        }
    }
}

package com.kewill.kcx.component.mapping.countries.de.ncts;

import java.io.ByteArrayOutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.EDirections;
import com.kewill.kcx.component.mapping.common.CommonFieldsDTO;
import com.kewill.kcx.component.mapping.countries.de.ncts.uids2kids.MapNCTSArrivalRejectionUK;
import com.kewill.kcx.component.mapping.countries.de.ncts.uids2kids.MapNCTSArrivalNotificationUK;
import com.kewill.kcx.component.mapping.countries.de.ncts.uids2kids.MapNCTSDeclarationRejectedUK;
import com.kewill.kcx.component.mapping.countries.de.ncts.uids2kids.MapNCTSDeclarationUK;
import com.kewill.kcx.component.mapping.countries.de.ncts.uids2kids.MapNCTSMRNAllocatedUK;
import com.kewill.kcx.component.mapping.countries.de.ncts.uids2kids.MapNCTSUnloadingPermissionUK;
import com.kewill.kcx.component.mapping.countries.de.ncts.uids2kids.MapNCTSUnloadingRemarksRejectionUK;
import com.kewill.kcx.component.mapping.countries.de.ncts.uids2kids.MapNCTSUnloadingRemarksUK;
import com.kewill.kcx.component.mapping.db.CustomerProcedureDTO;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsHeader;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module    	: UidsToKidsNCTS
 * Created     	: 23.08.2010
 * Description 	: Transformer to convert UIDS-Format to KIDS ICS messages.
 * 
 * @author Frederick Topico
 * @version 1.0.00
 */
public class UidsToKidsNCTS {

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
        kidsHeader.setProcedure("NCTS");
        String version = uidsHeader.getMessageVersion();
        version = Utils.removeDots(version.substring(0, 3));
        if (version.equals("41")) {
        	kidsHeader.setRelease("4.1.00");
        } else {
        	kidsHeader.setRelease("4.0.00");
        }

        if (direction == EDirections.CustomerToCountry) {
            kidsHeader.setDirection("FROM_CUSTOMER");
            Utils.isProcedureLicensed(kcxId, "NCTS", countryCode);   //EI20110808
        } else {
            kidsHeader.setDirection("TO_CUSTOMER");
        }
        
        kidsHeader.setCommonFieldsDTO(commonFieldsDTO);
        
        String msg = uidsHeader.getMessageType();
        Utils.log("(UidsToKidsNCTS readUids) uidsHeader.messageName = " + msg);
        
        switch(EUidsNCTSMessages.valueOf(msg)) {
	        case NCTSArrivalNotification:
	        	MapNCTSArrivalNotificationUK mapNCTSArrivalNotificationUK = 
	        		new MapNCTSArrivalNotificationUK(parser, kidsHeader, encoding);
	    		prepareMessage(uidsHeader, kidsHeader, mapNCTSArrivalNotificationUK, 
	    			commonFieldsDTO, "ArrivalNotification", direction);
	    		mapNCTSArrivalNotificationUK.getMessage(writer);
	    		break;
	    		
	    	case NCTSArrivalRejection:
	    		MapNCTSArrivalRejectionUK mapNCTSArrivalRejectionUK	= 
	    			new MapNCTSArrivalRejectionUK(parser, kidsHeader, encoding);
	    		prepareMessage(uidsHeader, kidsHeader, mapNCTSArrivalRejectionUK, 
	    			commonFieldsDTO, "ArrivalRejection", direction);
	    			mapNCTSArrivalRejectionUK.getMessage(writer);
	    		break;
	    		
	    	case NCTSDeclarationRejected:
	    		MapNCTSDeclarationRejectedUK mapNCTSDeclarationRejectedUK = 
	    			new MapNCTSDeclarationRejectedUK(parser, kidsHeader, encoding);
	    		prepareMessage(uidsHeader, kidsHeader, mapNCTSDeclarationRejectedUK, 
	    			commonFieldsDTO, "NCTSDeclarationRejected", direction);
	    		mapNCTSDeclarationRejectedUK.getMessage(writer);
	    		break;
	    		
	    	case NCTSMRNAllocated:
	    		MapNCTSMRNAllocatedUK mapNCTSMRNAllocatedUK = 
	    			new MapNCTSMRNAllocatedUK(parser, kidsHeader, encoding);
	    		prepareMessage(uidsHeader, kidsHeader, mapNCTSMRNAllocatedUK, 
	    			commonFieldsDTO, "MRNAllocated", direction);
	    		mapNCTSMRNAllocatedUK.getMessage(writer);
	    		break;
	    		
	    	case NCTSUnloadingPermission:
	    		MapNCTSUnloadingPermissionUK mapNCTSUnloadingPermissionUK = 
	    			new MapNCTSUnloadingPermissionUK(parser, kidsHeader, encoding);
	    		prepareMessage(uidsHeader, kidsHeader, mapNCTSUnloadingPermissionUK, 
	    			commonFieldsDTO, "UnloadingPermission", direction);
	    		mapNCTSUnloadingPermissionUK.getMessage(writer);
	    		break;	
	    		
	    	case NCTSUnloadingRemarksRejection:
	    		MapNCTSUnloadingRemarksRejectionUK mapNCTSUnloadingRemarksRejectionUK	= 
	    			new MapNCTSUnloadingRemarksRejectionUK(parser, kidsHeader, encoding);
	    		prepareMessage(uidsHeader, kidsHeader, mapNCTSUnloadingRemarksRejectionUK,
	    			commonFieldsDTO, "UnloadingRemarksRejection", direction);
	    		mapNCTSUnloadingRemarksRejectionUK.getMessage(writer);
	    		break;
	    	
	    	case NCTSUnloadingRemarks:
	    		MapNCTSUnloadingRemarksUK mapNCTSUnloadingRemarksUK	= 
	    			new MapNCTSUnloadingRemarksUK(parser, kidsHeader, encoding);
	    		prepareMessage(uidsHeader, kidsHeader, mapNCTSUnloadingRemarksUK, 
	    			commonFieldsDTO, "UnloadingRemarks", direction);
	    			mapNCTSUnloadingRemarksUK.getMessage(writer);
	    		break;
	    		
	    	case NCTSDeclaration:
	    		MapNCTSDeclarationUK mapNCTSDeclarationUK =  
	    			new MapNCTSDeclarationUK(parser, kidsHeader, encoding);
	    		prepareMessage(uidsHeader, kidsHeader, mapNCTSDeclarationUK, 
	    				commonFieldsDTO, "NCTSDeclaration", direction);
	    		mapNCTSDeclarationUK.getMessage(writer);
	    		break;
	    		
	    		
	    	default: throw new FssException("Unknown message type " + msg);
        }
        
        String xml = bos.toString();
        Utils.log("(UidsToKidsNCTS) converted message = \n" + xml);
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
    	// Wird bereits vor dem Aufruf:  
        // kidsHeader.setReceiver(uidsHeader.getTo());   //mit der kcx_Id besetzt. 	
    	// kidsHeader.setCountryCode(uidsHeader.getCountryCode());    	
    	// kidsHeader.setProcedure("NCTS");
    	// kidsHeader.setRelease("4.0.00");
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

        Utils.log("(UidsToKidsNCTS setKidsHeaderMappingFields) mappingCode                 = " + mappingCode);
        Utils.log("(UidsToKidsNCTS setKidsHeaderMappingFields) uidsHeader.getCountryCode() = " + 
                                                                               uidsHeader.getCountryCode());
        Utils.log("(UidsToKidsNCTS setKidsHeaderMappingFields) kidsHeader.getCountryCode() = " + 
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
        
        	Utils.log("(UidsToKidsNCTS setKidsHeaderMappingFields) " +
        		                	"kidsHeader.getMapFrom() = " + kidsHeader.getMapFrom());
        	Utils.log("(UidsToKidsNCTS setKidsHeaderMappingFields) " +
        		                	"kidsHeader.getMapTo()   = " + kidsHeader.getMapTo());
        	if (kidsHeader.getMapFrom().equalsIgnoreCase(kidsHeader.getMapTo())) {
	            kidsHeader.setMap("0");
        	} else {
            	kidsHeader.setMap("1");
        	}
        }
    }
}

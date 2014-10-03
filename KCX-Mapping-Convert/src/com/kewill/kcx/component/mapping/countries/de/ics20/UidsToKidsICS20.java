package com.kewill.kcx.component.mapping.countries.de.ics20;

import java.io.ByteArrayOutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.EDirections;
import com.kewill.kcx.component.mapping.common.CommonFieldsDTO;
import com.kewill.kcx.component.mapping.conf.Config;
import com.kewill.kcx.component.mapping.countries.de.aes.uids2kids.MapConfirmUK;
import com.kewill.kcx.component.mapping.countries.de.aes.uids2kids.MapFailureUK;
import com.kewill.kcx.component.mapping.countries.de.ics.EUidsICSMessages;
import com.kewill.kcx.component.mapping.countries.de.ics20.uids2kids.MapArrivalItemRejectionUK;
import com.kewill.kcx.component.mapping.countries.de.ics20.uids2kids.MapArrivalNotificationUK;
import com.kewill.kcx.component.mapping.countries.de.ics20.uids2kids.MapArrivalNotificationValidationUK;
import com.kewill.kcx.component.mapping.countries.de.ics20.uids2kids.MapDeclarationAcceptedUK;
import com.kewill.kcx.component.mapping.countries.de.ics20.uids2kids.MapDeclarationAmendmentAcceptedUK;
import com.kewill.kcx.component.mapping.countries.de.ics20.uids2kids.MapDeclarationAmendmentRejectedUK;
import com.kewill.kcx.component.mapping.countries.de.ics20.uids2kids.MapDeclarationAmendmentUK;
import com.kewill.kcx.component.mapping.countries.de.ics20.uids2kids.MapDeclarationProhibitedUK;
import com.kewill.kcx.component.mapping.countries.de.ics20.uids2kids.MapDeclarationRejectedUK;
import com.kewill.kcx.component.mapping.countries.de.ics20.uids2kids.MapDeclarationUK;
import com.kewill.kcx.component.mapping.countries.de.ics20.uids2kids.MapDiversionRequestAcceptedUK;
import com.kewill.kcx.component.mapping.countries.de.ics20.uids2kids.MapDiversionRequestRejectedUK;
import com.kewill.kcx.component.mapping.countries.de.ics20.uids2kids.MapDiversionRequestUK;
import com.kewill.kcx.component.mapping.countries.de.ics20.uids2kids.MapEntryDetailsDataUK;
import com.kewill.kcx.component.mapping.countries.de.ics20.uids2kids.MapEntryReleaseRejectionUK;
import com.kewill.kcx.component.mapping.countries.de.ics20.uids2kids.MapEntryReleaseUK;
import com.kewill.kcx.component.mapping.countries.de.ics20.uids2kids.MapImportControlDecisionUK;
import com.kewill.kcx.component.mapping.db.CustomerProcedureDTO;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsHeader;
import com.kewill.kcx.component.mapping.util.Utils;


/**
 * Module       : ICS20<br>
 * Created 		: 2012.10.22<br>
 * Description  : transformer to convert ICS UIDS-Format to KIDS messages.
 *				: EI20131108: new ICSEntryDetailsData
 *
 * @author iwaniuk
 * @version 1.0.00
 */

public class UidsToKidsICS20 {

	public String readUids(XMLEventReader parser, String encoding, UidsHeader uidsHeader,
	                                                    CommonFieldsDTO commonFieldsDTO) throws Exception {

        String      countryCode = commonFieldsDTO.getCountryCode();
        String      kcxId       = commonFieldsDTO.getKcxId();
        EDirections direction   = commonFieldsDTO.getDirection();

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        XMLStreamWriter writer    = getWriter(bos, encoding);
        KidsHeader kidsHeader     = new KidsHeader(writer);
        kidsHeader.setReceiver(kcxId);
        kidsHeader.setCommonFieldsDTO(commonFieldsDTO);

        if (direction == EDirections.CustomerToCountry) {
            kidsHeader.setDirection("FROM_CUSTOMER");
            Utils.isProcedureLicensed(kcxId, "ICS", countryCode);
        } else {
            kidsHeader.setDirection("TO_CUSTOMER");
        }

        String msg = uidsHeader.getMessageType();
        Utils.log("(UidsToKidsICS readUids) uidsHeader.messageName = " + msg);        
        
        switch (EUidsICSMessages.valueOf(msg)) {               			    	         
    	case ICSDeclaration:        									
        	MapDeclarationUK mapDeclarationUK = new MapDeclarationUK(parser, kidsHeader, encoding);          			      	
        	prepareMessage(uidsHeader, kidsHeader, mapDeclarationUK, commonFieldsDTO,
                    "ICSEntrySummaryDeclaration", direction);        	
        	mapDeclarationUK.getMessage(writer);
        	break;
    	case ICSDeclarationAccepted:  									
    		MapDeclarationAcceptedUK mapDeclAcceptedUK = new MapDeclarationAcceptedUK(parser, kidsHeader, encoding);                    
    		prepareMessage(uidsHeader, kidsHeader, mapDeclAcceptedUK, commonFieldsDTO,
                    "ICSEntrySummaryDeclarationAcknowledgment", direction);
    		mapDeclAcceptedUK.getMessage(writer);
        	break;        	        
    	case ICSDeclarationRejected:									
    		MapDeclarationRejectedUK mapDeclRejectedUK = new MapDeclarationRejectedUK(parser, kidsHeader, encoding);    				
    		prepareMessage(uidsHeader, kidsHeader, mapDeclRejectedUK, commonFieldsDTO,
			        "ICSEntrySummaryDeclarationRejected", direction);
    		mapDeclRejectedUK.getMessage(writer);
			break;			    
    	case ICSDeclarationAmendment:	
    		MapDeclarationAmendmentUK mapDeclAmendmentUK = new MapDeclarationAmendmentUK(parser, kidsHeader, encoding);					
			prepareMessage(uidsHeader, kidsHeader, mapDeclAmendmentUK, commonFieldsDTO,
			        "ICSDeclarationAmendment", direction);
			mapDeclAmendmentUK.getMessage(writer);
			break;

    	case ICSDeclarationAmendmentAccepted:    						
			MapDeclarationAmendmentAcceptedUK mapDAAUK = new MapDeclarationAmendmentAcceptedUK(parser, kidsHeader, encoding);			         
			prepareMessage(uidsHeader, kidsHeader, mapDAAUK, commonFieldsDTO, 
			         "ICSEntrySummaryDeclarationAmendmentAcceptance", direction);
			mapDAAUK.getMessage(writer);
			break;		
    	case ICSDeclarationAmendmentRejected:    	
    		MapDeclarationAmendmentRejectedUK mapDARUK = new MapDeclarationAmendmentRejectedUK(parser, kidsHeader, encoding);
    		prepareMessage(uidsHeader, kidsHeader, mapDARUK, commonFieldsDTO,
    				"ICSEntrySummaryDeclarationAmendmentRejection", direction);
    		mapDARUK.getMessage(writer);
    		break;    		
    	case ICSDeclarationProhibited:									
			MapDeclarationProhibitedUK mapDeclProhibitedUK = new MapDeclarationProhibitedUK(parser, kidsHeader, encoding);
			prepareMessage(uidsHeader, kidsHeader, mapDeclProhibitedUK, commonFieldsDTO, 
					"ICSAdvancedInterventionNot", direction);  			
			mapDeclProhibitedUK.getMessage(writer);
			break;    	    
    	case ICSDiversionRequest:
    		MapDiversionRequestUK mapDivRequestUK = new MapDiversionRequestUK(parser, kidsHeader, encoding);
    		prepareMessage(uidsHeader, kidsHeader, mapDivRequestUK, commonFieldsDTO,
                     "ICSDiversionRequest", direction);
    		mapDivRequestUK.getMessage(writer);
        	break;         	
    	case ICSDiversionRequestAccepted:  								
    		MapDiversionRequestAcceptedUK mapDivRequestAcceptedUK = new MapDiversionRequestAcceptedUK(parser, kidsHeader, encoding);
    		prepareMessage(uidsHeader, kidsHeader, mapDivRequestAcceptedUK, commonFieldsDTO,
                     "ICSDiversionRequestAcknowledgment", direction);
    		mapDivRequestAcceptedUK.getMessage(writer);
        	break;        	
    	case ICSDiversionRequestRejected:
    		MapDiversionRequestRejectedUK mapDivRequestRejectedUK = new MapDiversionRequestRejectedUK(parser, kidsHeader, encoding);
    		prepareMessage(uidsHeader, kidsHeader, mapDivRequestRejectedUK, commonFieldsDTO, 
    				 "ICSDiversionRequestRejected", direction);
    		mapDivRequestRejectedUK.getMessage(writer);
    		break;      		
    	case ICSArrivalNotification:
    		MapArrivalNotificationUK mapArrivalNotificationUK = new MapArrivalNotificationUK(parser, kidsHeader, encoding);
    		prepareMessage(uidsHeader, kidsHeader, mapArrivalNotificationUK, commonFieldsDTO,
                      "ICSArrivalNotification", direction);
    		mapArrivalNotificationUK.getMessage(writer);
        	break;        	
        case ICSArrivalNotificationValidation:	
    		MapArrivalNotificationValidationUK mapArrNotValidationUK = new MapArrivalNotificationValidationUK(parser, kidsHeader, encoding);
    		prepareMessage(uidsHeader, kidsHeader, mapArrNotValidationUK, commonFieldsDTO,
			         "ICSArrivalNotificationValidation", direction);
    		mapArrNotValidationUK.getMessage(writer);
			break;        	
        case ICSArrivalItemRejection:										
			MapArrivalItemRejectionUK mapArrivalItemRejectionUK	= new MapArrivalItemRejectionUK(parser, kidsHeader, encoding);
			prepareMessage(uidsHeader, kidsHeader, mapArrivalItemRejectionUK, commonFieldsDTO, 
			          "ICSArrivalItemRejection", direction);
			mapArrivalItemRejectionUK.getMessage(writer);
			break;						
    	case ICSEntryRelease:									
    		MapEntryReleaseUK mapEntryReleaseUK	= new MapEntryReleaseUK(parser, kidsHeader, encoding);
    		prepareMessage(uidsHeader, kidsHeader, mapEntryReleaseUK, commonFieldsDTO, 
		          "ICSEntryRelease", direction);
    		mapEntryReleaseUK.getMessage(writer);    
    		break;    		
    	case ICSEntryReleaseRejection:							
    		MapEntryReleaseRejectionUK mapEntryReleaseRejectionUK = new MapEntryReleaseRejectionUK(parser, kidsHeader, encoding);
    		prepareMessage(uidsHeader, kidsHeader, mapEntryReleaseRejectionUK, commonFieldsDTO, 
		          "ICSEntryReleaseRejection", direction);
    		mapEntryReleaseRejectionUK.getMessage(writer);
    		break;    		
    	case ICSImportControlDecision:							
    		MapImportControlDecisionUK mapImportControlDecisionUK = new MapImportControlDecisionUK(parser, kidsHeader, encoding);
    		prepareMessage(uidsHeader, kidsHeader, mapImportControlDecisionUK, commonFieldsDTO, 
		          "ICSImportControlDecisionNotification", direction);
    		mapImportControlDecisionUK.getMessage(writer);
    		break; 
    	case ICSEntryDetailsData:		 //EI20131108					
    		MapEntryDetailsDataUK mapEntryDetailsDataUK = new MapEntryDetailsDataUK(parser, kidsHeader, encoding);
    		prepareMessage(uidsHeader, kidsHeader, mapEntryDetailsDataUK, commonFieldsDTO, 
		          "ICSEntryDetailsData", direction);
    		mapEntryDetailsDataUK.getMessage(writer);
    		break; 
    	case Failure:  
   			MapFailureUK mapFailureUK = new MapFailureUK(parser, kidsHeader, encoding);
            prepareMessage(uidsHeader, kidsHeader, mapFailureUK, commonFieldsDTO, 
            		"localAppResult", direction);
   			mapFailureUK.getMessage(writer);
   			break;   			
    	case Confirm:  
    		if (Config.isReturnConfirm()) {
    			Utils.log("Confirm wird von UIDS nach KIDS gemapt! CK08122011");
       			MapConfirmUK mapConfirmUK = new MapConfirmUK(parser, kidsHeader, encoding);
                prepareMessage(uidsHeader, kidsHeader, mapConfirmUK, commonFieldsDTO, 
                		"localAppResult",  direction);
       			mapConfirmUK.getMessage(writer);
    		} else {
    			Utils.log("Confirm wird NICHT von UIDS nach KIDS gemapt! CK08122011");
    			return null;
    		}
   			break;      			   		

   		default: throw new FssException("Unknown message type " + msg);
        }

        String xml = bos.toString();
        return xml;
    }

	private void prepareMessage(UidsHeader uidsHeader, KidsHeader kidsHeader, KidsMessage message,
	                            CommonFieldsDTO commonFieldsDTO, String messageType, EDirections direction)
	                                                                            throws XMLStreamException {
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
    	kidsHeader.setCountryCode(uidsHeader.getCountryCode());
    	kidsHeader.setProcedure("ICS");
  		kidsHeader.setMessageName(messageName);     
  		if (uidsHeader.getMessageVersion() == null) {
  			Utils.log("(UidsToKidsICS mapUidsToKidsHeader) getMessageVersion liefert null!");
  			kidsHeader.setRelease("1.0.00");
  		} else if (uidsHeader.getMessageVersion().equals("2.0")) {
  			kidsHeader.setRelease("2.0.00");
  			Utils.log("(UidsToKidsICS mapUidsToKidsHeader) getMessageVersion ist 2.0");
  		} else  { kidsHeader.setRelease("1.0.00");
  		Utils.log("(UidsToKidsICS mapUidsToKidsHeader) getMessageVersion NICHT 2.0, daher" +
  				"wird KIDS Release auf 1 gesetzt");
  		}    	
    	kidsHeader.setMessageID(uidsHeader.getMsgid());
    	kidsHeader.setInReplyTo(uidsHeader.getInreplyto());

    	return kidsHeader;
    }

    private void setKidsHeaderMappingFields(UidsHeader uidsHeader, KidsHeader kidsHeader, EDirections direction) {
        CustomerProcedureDTO customerProcedureDTO =
            Utils.getCustomerProceduresFromKcxId(kidsHeader.getReceiver(), kidsHeader.getProcedure().toUpperCase());
        String mappingCode = customerProcedureDTO.getMappingCode();

        Utils.log("(UidsToKidsICS setKidsHeaderMappingFields) mappingCode                 = " + mappingCode);
        Utils.log("(UidsToKidsICS setKidsHeaderMappingFields) uidsHeader.getCountryCode() = " +
                                                                               uidsHeader.getCountryCode());
        Utils.log("(UidsToKidsICS setKidsHeaderMappingFields) kidsHeader.getCountryCode() = " +
                                                                               kidsHeader.getCountryCode());

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
	
        	Utils.log("(UidsToKidsICS setKidsHeaderMappingFields) " +
        		                	"kidsHeader.getMapFrom() = " + kidsHeader.getMapFrom());
        	Utils.log("(UidsToKidsICS setKidsHeaderMappingFields) " +
        		                	"kidsHeader.getMapTo()   = " + kidsHeader.getMapTo());
        	if (kidsHeader.getMapFrom().equalsIgnoreCase(kidsHeader.getMapTo())) {
	            kidsHeader.setMap("0");
        	} else {
	            kidsHeader.setMap("1");
    	    }
        }

    }

}

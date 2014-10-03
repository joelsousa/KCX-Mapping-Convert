package com.kewill.kcx.component.mapping.countries.de.emcs21;

import java.io.ByteArrayOutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.EDirections;
import com.kewill.kcx.component.mapping.common.CommonFieldsDTO;
import com.kewill.kcx.component.mapping.countries.de.aes.uids2kids.MapConfirmUK;
import com.kewill.kcx.component.mapping.countries.de.aes.uids2kids.MapFailureUK;
import com.kewill.kcx.component.mapping.countries.de.emcs.EUidsEmcsMessages;
import com.kewill.kcx.component.mapping.countries.de.emcs20.uids2kids.MapAcceptedExportUK;
import com.kewill.kcx.component.mapping.countries.de.emcs21.uids2kids.MapAlertOrRejectionUK;
import com.kewill.kcx.component.mapping.countries.de.emcs20.uids2kids.MapCancellationUK;
import com.kewill.kcx.component.mapping.countries.de.emcs21.uids2kids.MapChangeOfDestinationUK;
import com.kewill.kcx.component.mapping.countries.de.emcs20.uids2kids.MapDeclarationUK;
import com.kewill.kcx.component.mapping.countries.de.emcs20.uids2kids.MapDiversionNotificationUK;
import com.kewill.kcx.component.mapping.countries.de.emcs20.uids2kids.MapEventReportUK;
import com.kewill.kcx.component.mapping.countries.de.emcs20.uids2kids.MapExplanationOfReasonForShortageUK;
import com.kewill.kcx.component.mapping.countries.de.emcs20.uids2kids.MapExplanationOnDelayUK;
import com.kewill.kcx.component.mapping.countries.de.emcs20.uids2kids.MapExportRejectionUK;
import com.kewill.kcx.component.mapping.countries.de.emcs20.uids2kids.MapGenericRefusalMessageUK;
import com.kewill.kcx.component.mapping.countries.de.emcs20.uids2kids.MapInternalStatusInformationUK;
import com.kewill.kcx.component.mapping.countries.de.emcs20.uids2kids.MapInterruptionOfMovementUK;
import com.kewill.kcx.component.mapping.countries.de.emcs20.uids2kids.MapReminderMessageUK;
import com.kewill.kcx.component.mapping.countries.de.emcs20.uids2kids.MapReportOfReceiptUK;
import com.kewill.kcx.component.mapping.countries.de.emcs20.uids2kids.MapStatusResponseUK;
import com.kewill.kcx.component.mapping.countries.de.emcs20.uids2kids.MapSubmittedDraftOfSplittingOperationUK;
import com.kewill.kcx.component.mapping.countries.de.emcs20.uids2kids.MapValidDeclarationUK;
import com.kewill.kcx.component.mapping.db.CustomerProcedureDTO;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsHeader;
import com.kewill.kcx.component.mapping.util.Utils;


/**
 * Module        : EMCS
 * Created       : 10.02.2014<br>
 * Description   : Transformer called to convert UIDS-Format to KIDS messages.
 * 				 : Changes in V21: EMCSAlertOrRejection and EMCSChangeOfDestination
 * 
 * @author iwaniuk
 * @version 2.0.00
 */

public class UidsToKidsEmcs21 {

	public String readUids(XMLEventReader parser, String encoding, UidsHeader uidsHeader,
	                                                   CommonFieldsDTO commonFieldsDTO) throws Exception {
        String      countryCode = commonFieldsDTO.getCountryCode();
        String      kcxId       = commonFieldsDTO.getKcxId();
        EDirections direction   = commonFieldsDTO.getDirection();
        
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        XMLStreamWriter writer    = getWriter(bos, encoding);
        KidsHeader kidsHeader     = new KidsHeader(writer);
        kidsHeader.setReceiver(kcxId);
        if (direction == EDirections.CustomerToCountry) {
            kidsHeader.setDirection("FROM_CUSTOMER");
        } else {
            kidsHeader.setDirection("TO_CUSTOMER");
        }            
        kidsHeader.setCommonFieldsDTO(commonFieldsDTO);
        
        String msg = uidsHeader.getMessageType().trim();
        Utils.log("(UidsToKidsEmcs readUids) uidsHeader.messageName = " + msg);
        switch (EUidsEmcsMessages.valueOf(msg)) {
        	case EMCSValidDeclaration:  
        		MapValidDeclarationUK mapValidDeclarationUK = 
                                             new MapValidDeclarationUK(parser, kidsHeader, encoding);
        		prepareMessage(uidsHeader, kidsHeader, mapValidDeclarationUK, commonFieldsDTO, 
                                          "EMCSValidDeclaration", direction);
        		mapValidDeclarationUK.getMessage(writer);                 
            	break; 
        	case EMCSCancellationEAAD:                     
       			MapCancellationUK mapCancellationUK = new MapCancellationUK(parser, kidsHeader, encoding);
                prepareMessage(uidsHeader, kidsHeader, mapCancellationUK, commonFieldsDTO, 
                						 "EMCSCancellation", direction);
                mapCancellationUK.getMessage(writer);       			
       			break;            	
       		case EMCSChgOfDestinationInfo:         		
       	        Utils.isProcedureLicensed(kcxId, "EMCS", countryCode);
                MapChangeOfDestinationUK mapChangeOfDestinationUK 
                                                     = new MapChangeOfDestinationUK(parser, kidsHeader, encoding);
       	        prepareMessage(uidsHeader, kidsHeader, mapChangeOfDestinationUK, commonFieldsDTO, 
       	                                 "EMCSChangeOfDestination", direction);
       	        mapChangeOfDestinationUK.getMessage(writer);                 
       			break;            
       		case EMCSDeclaration:
                MapDeclarationUK mapDeclarationUK = new MapDeclarationUK(parser, kidsHeader, encoding);
                prepareMessage(uidsHeader, kidsHeader, mapDeclarationUK, commonFieldsDTO, 
                             			"EMCSDeclaration", direction);
                mapDeclarationUK.getMessage(writer);                 
       			break;
       		case EMCSReportOfReceipt:                   
       			MapReportOfReceiptUK mapReportOfReceiptUK = new MapReportOfReceiptUK(parser, kidsHeader, encoding);
       			prepareMessage(uidsHeader, kidsHeader, mapReportOfReceiptUK, commonFieldsDTO, 
       									"EMCSReportOfReceipt", direction);
       			mapReportOfReceiptUK.getMessage(writer);  
       			break;       			
       		case EMCSExplanationOnDelayForDelivery:		
       			MapExplanationOnDelayUK mapExplanationOnDelayUK 
       			                                          = new MapExplanationOnDelayUK(parser, kidsHeader, encoding);
       			prepareMessage(uidsHeader, kidsHeader, mapExplanationOnDelayUK, commonFieldsDTO, 
       									"EMCSExplanationOnDelay",  direction);                       
       			mapExplanationOnDelayUK.getMessage(writer);  
       			break;
            case Confirm:  
       			MapConfirmUK mapConfirmUK = new MapConfirmUK(parser, kidsHeader, encoding);
                prepareMessage(uidsHeader, kidsHeader, mapConfirmUK, commonFieldsDTO, 
                						"Confirmation",  direction);
       			mapConfirmUK.getMessage(writer);
       			break;
       		case Failure:  
       			MapFailureUK mapFailureUK = new MapFailureUK(parser, kidsHeader, encoding);
                prepareMessage(uidsHeader, kidsHeader, mapFailureUK, commonFieldsDTO, 
                						"localAppResult", direction);
       			mapFailureUK.getMessage(writer);
       			break;
       		case EMCSNotificationDivertedEAAD:    
       			MapDiversionNotificationUK mapDiversionNotificationUK = 
       													new MapDiversionNotificationUK(parser, kidsHeader, encoding);
                prepareMessage(uidsHeader, kidsHeader, mapDiversionNotificationUK, commonFieldsDTO, 
                						"EMCSDiversionNotification", direction);  //EI20110927 wrong Header.KidsName
                mapDiversionNotificationUK.getMessage(writer);
       			break; 
       		case EMCSReminderMessageForExciseMovement:
       			MapReminderMessageUK mapReminderMessageUK = new MapReminderMessageUK(parser, kidsHeader, encoding);
       			prepareMessage(uidsHeader, kidsHeader, mapReminderMessageUK, commonFieldsDTO, 
            							"EMCSReminderMessage", direction);
       			mapReminderMessageUK.getMessage(writer);
       			break;        		
       		case EMCSNotificationOfAcceptedExport: 
       			MapAcceptedExportUK mapAcceptedExportUK = new MapAcceptedExportUK(parser, kidsHeader, encoding);
       			prepareMessage(uidsHeader, kidsHeader, mapAcceptedExportUK, commonFieldsDTO,
       									"EMCSAcceptedExport", direction); //EI20110927 wrong Header.KidsName
       			mapAcceptedExportUK.getMessage(writer);
       			break;
       		case EMCSRejectionOfEAADForExport:
       			MapExportRejectionUK mapExportRejectionUK = new MapExportRejectionUK(parser, kidsHeader, encoding);
       			prepareMessage(uidsHeader, kidsHeader, mapExportRejectionUK, commonFieldsDTO, 
            							"EMCSExportRejection", direction);  //EI20110927 wrong Header.KidsName
       			mapExportRejectionUK.getMessage(writer);
       			break;
       		case EMCSDeclarationRejected:
       			MapGenericRefusalMessageUK mapGenericRefusalMessageUK = 
       												 new MapGenericRefusalMessageUK(parser, kidsHeader, encoding);
       			prepareMessage(uidsHeader, kidsHeader, mapGenericRefusalMessageUK, commonFieldsDTO, 
            							"EMCSGenericRefusalMessage", direction);   //EI20110927 wrong Header.KidsName
       			mapGenericRefusalMessageUK.getMessage(writer);
       			break;
       		case EMCSAlertOrRejection:                 //EI20110705  new for V20
       			MapAlertOrRejectionUK mapAlertOrRejectionUK = new MapAlertOrRejectionUK(parser, kidsHeader, encoding);
       			prepareMessage(uidsHeader, kidsHeader, mapAlertOrRejectionUK, commonFieldsDTO, 
       									"EMCSAlertOrRejection", direction);
       			mapAlertOrRejectionUK.getMessage(writer);
       			break;
       		case EMCSInterruptionOfMovement:           		 //EI20110705  new for V20
       			MapInterruptionOfMovementUK mapInterruptionOfMovementUK = 
       													new MapInterruptionOfMovementUK(parser, kidsHeader, encoding);
       			prepareMessage(uidsHeader, kidsHeader, mapInterruptionOfMovementUK, commonFieldsDTO, 
       									"EMCSInterruptionOfMovement", direction);
       			mapInterruptionOfMovementUK.getMessage(writer);
       			break;
       		//case EMCSExplanationOfReasonForShortage:        //EI20110705  new for V20
       		case EMCSExplanationOnReasonForShortage:    	  //EI20110927
				MapExplanationOfReasonForShortageUK mapExplanationOfReasonForShortageUK = 
												new MapExplanationOfReasonForShortageUK(parser, kidsHeader, encoding);
				prepareMessage(uidsHeader, kidsHeader, mapExplanationOfReasonForShortageUK, commonFieldsDTO, 
										"EMCSExplanationOnReasonForShortage", direction);
				mapExplanationOfReasonForShortageUK.getMessage(writer);
				break;
			case EMCSStatusResponse:                        //EI20110721  new for V20
				MapStatusResponseUK mapStatusResponseUK = new MapStatusResponseUK(parser, kidsHeader, encoding);
				prepareMessage(uidsHeader, kidsHeader, mapStatusResponseUK, commonFieldsDTO, 
										"EMCSStatusResponse", direction);
				mapStatusResponseUK.getMessage(writer);
               break;
			case EMCSEventReport:  							//EI20110721  new for V20
               MapEventReportUK mapEventReportUK = new MapEventReportUK(parser, kidsHeader, encoding);
               prepareMessage(uidsHeader, kidsHeader, mapEventReportUK, commonFieldsDTO, 
										"EMCSEventReport", direction);
               mapEventReportUK.getMessage(writer);
               break;	       			
			case EMCSSubmittedDraftOfSplittingOperation:  	//EI20110727  new for V20
                MapSubmittedDraftOfSplittingOperationUK mapSplittingUK = 
                							new MapSubmittedDraftOfSplittingOperationUK(parser, kidsHeader, encoding);
                prepareMessage(uidsHeader, kidsHeader, mapSplittingUK, commonFieldsDTO, 
										"EMCSSubmittedDraftOfSplittingOperation", direction);
                mapSplittingUK.getMessage(writer);
                break;               
       		case InternalStatusInformation:
       			MapInternalStatusInformationUK mapInternalStatusInformationUK = 
       												new MapInternalStatusInformationUK(parser, kidsHeader, encoding);
       			prepareMessage(uidsHeader, kidsHeader, mapInternalStatusInformationUK, commonFieldsDTO, 
            							"InternalStatusInformation", direction);
       			mapInternalStatusInformationUK.getMessage(writer);
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
    	// Wird bereits vor dem Aufruf  mit der KewillId besetzt.
//    	kidsHeader.setReceiver(uidsHeader.getTo());
    	kidsHeader.setMethod(uidsHeader.getMethod());
    	kidsHeader.setCountryCode(uidsHeader.getCountryCode());
    	kidsHeader.setProcedure("EMCS");
  		kidsHeader.setMessageName(messageName);
        // CK090428 Kids Release in Abh. von Uids-Version setzen
  		// Uids 1.0 entspricht Kids 1.0.00 entspricht Zabis 5.3
  	    // Uids 2.0 entspricht Kids 2.0.00 entspricht Zabis 6.0
  		if (uidsHeader.getMessageVersion() == null) {
  			Utils.log("(UidsToKidsEmcs mapUidsToKidsHeader) getMessageVersion liefert null!");
  			kidsHeader.setRelease("1.0.00");
  		} else if (uidsHeader.getMessageVersion().equals("2.0")) {
  			kidsHeader.setRelease("2.0.00");
  			Utils.log("(UidsToKidsEmcs mapUidsToKidsHeader) getMessageVersion ist 2.0");
  			
  		} else  { kidsHeader.setRelease("1.0.00");
  		Utils.log("(UidsToKidsEmcs mapUidsToKidsHeader) getMessageVersion NICHT 2.0, daher" +
  				"wird KIDS Release auf 1 gesetzt");
  		}
    	// kidsHeader.setRelease("1.0.00");
  		// Ende CK090428
    	kidsHeader.setMessageID(uidsHeader.getMsgid());
    	kidsHeader.setInReplyTo(uidsHeader.getInreplyto());
    
    	return kidsHeader;
    }
    
    private void setKidsHeaderMappingFields(UidsHeader uidsHeader, KidsHeader kidsHeader, EDirections direction) {
        CustomerProcedureDTO customerProcedureDTO = 
            Utils.getCustomerProceduresFromKcxId(kidsHeader.getReceiver(), kidsHeader.getProcedure().toUpperCase());
        String mappingCode = customerProcedureDTO.getMappingCode();

        Utils.log("(UidsToKidsEmcs setKidsHeaderMappingFields) mappingCode                 = " + mappingCode);
        Utils.log("(UidsToKidsEmcs setKidsHeaderMappingFields) uidsHeader.getCountryCode() = " + 
                                                                               uidsHeader.getCountryCode());
        Utils.log("(UidsToKidsEmcs setKidsHeaderMappingFields) kidsHeader.getCountryCode() = " + 
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
	        
        	Utils.log("(UidsToKidsEmcs) " + "kidsHeader.getMapFrom() = " + kidsHeader.getMapFrom());
        	Utils.log("(UidsToKidsEmcs) " + "kidsHeader.getMapTo()   = " + kidsHeader.getMapTo());
        	if (kidsHeader.getMapFrom().equalsIgnoreCase(kidsHeader.getMapTo())) {
	            kidsHeader.setMap("0");
    	    } else {
            	kidsHeader.setMap("1");
        	}
        }
    }

}

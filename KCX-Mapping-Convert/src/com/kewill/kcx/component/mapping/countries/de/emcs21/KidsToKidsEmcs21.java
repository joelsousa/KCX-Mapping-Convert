package com.kewill.kcx.component.mapping.countries.de.emcs21;

import javax.xml.stream.XMLEventReader;

import com.kewill.kcx.component.mapping.common.CommonFieldsDTO;
import com.kewill.kcx.component.mapping.countries.de.aes.kids2kids.MapExpNckKK;
import com.kewill.kcx.component.mapping.countries.de.emcs.EKidsEmcsMessages;
import com.kewill.kcx.component.mapping.countries.de.emcs20.kids2kids.MapAcceptedExportKK;
import com.kewill.kcx.component.mapping.countries.de.emcs21.kids2kids.MapAlertOrRejectionKK;
import com.kewill.kcx.component.mapping.countries.de.emcs20.kids2kids.MapCancellationKK;
import com.kewill.kcx.component.mapping.countries.de.emcs21.kids2kids.MapChangeOfDestinationKK;
import com.kewill.kcx.component.mapping.countries.de.emcs20.kids2kids.MapEMCSDeclarationKK;
import com.kewill.kcx.component.mapping.countries.de.emcs20.kids2kids.MapDiversionNotificationKK;
import com.kewill.kcx.component.mapping.countries.de.emcs20.kids2kids.MapEventReportKK;
import com.kewill.kcx.component.mapping.countries.de.emcs20.kids2kids.MapExplanationOfReasonForShortageKK;
import com.kewill.kcx.component.mapping.countries.de.emcs20.kids2kids.MapExplanationOnDelayKK;
import com.kewill.kcx.component.mapping.countries.de.emcs20.kids2kids.MapExportRejectionKK;
import com.kewill.kcx.component.mapping.countries.de.emcs20.kids2kids.MapGenericRefusalMessageKK;
import com.kewill.kcx.component.mapping.countries.de.emcs20.kids2kids.MapInterruptionOfMovementKK;
import com.kewill.kcx.component.mapping.countries.de.emcs20.kids2kids.MapReminderMessageKK;
import com.kewill.kcx.component.mapping.countries.de.emcs20.kids2kids.MapReportOfReceiptKK;
import com.kewill.kcx.component.mapping.countries.de.emcs20.kids2kids.MapStatusResponseKK;
import com.kewill.kcx.component.mapping.countries.de.emcs20.kids2kids.MapSubmittedDraftOfSplittingOperationKK;
import com.kewill.kcx.component.mapping.countries.de.emcs20.kids2kids.MapValidDeclarationKK;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;

/**
 * Module       : EMCS<br>
 * Created	    : 10.02.2014<br>
 * Description  : Transformer called by Mule to convert KIDS-Format to KIDS messages.
 * 				 : Changes in V21: EMCSAlertOrRejection and EMCSChangeOfDestination 
 * 
 * @author iwaniuk
 * @version 2.0.00
 */
public class KidsToKidsEmcs21 {
    
	public String readKids(XMLEventReader parser, KidsHeader kidsHeader, CommonFieldsDTO commonFieldsDTO)
			throws Exception {

	    String encoding = "UTF-8";
        String xml = null;
	    String msg = kidsHeader.getMessageName();
		switch (EKidsEmcsMessages.valueOf(msg)) {

			case EMCSAcceptedExport:  
                MapAcceptedExportKK mapAcceptedExportKK = new MapAcceptedExportKK(parser, encoding);
                mapAcceptedExportKK.setKidsHeader(kidsHeader);
                mapAcceptedExportKK.setCommonFieldsDTO(commonFieldsDTO);
                xml = mapAcceptedExportKK.getMessage();
                break;	 
        	case EMCSCancellation:  
                MapCancellationKK mapCancellationKK = new MapCancellationKK(parser, encoding);
                mapCancellationKK.setKidsHeader(kidsHeader);
                mapCancellationKK.setCommonFieldsDTO(commonFieldsDTO);
                xml = mapCancellationKK.getMessage();
                break;     
           case EMCSChangeOfDestination:  
                MapChangeOfDestinationKK mapChangeOfDestinationKK = new MapChangeOfDestinationKK(parser, encoding);
                mapChangeOfDestinationKK.setKidsHeader(kidsHeader);
                mapChangeOfDestinationKK.setCommonFieldsDTO(commonFieldsDTO);
                xml = mapChangeOfDestinationKK.getMessage();
                break; 
   		    case EMCSDeclaration:
    			MapEMCSDeclarationKK mapDeclarationKK = new MapEMCSDeclarationKK(parser, encoding);
    			mapDeclarationKK.setKidsHeader(kidsHeader);
    			mapDeclarationKK.setCommonFieldsDTO(commonFieldsDTO);
    			xml = mapDeclarationKK.getMessage();
    			break;          
        	case EMCSDiversionNotification:  
                MapDiversionNotificationKK mapDiversionNotificationKK = 
                                                    new MapDiversionNotificationKK(parser, encoding);
                mapDiversionNotificationKK.setKidsHeader(kidsHeader);
                mapDiversionNotificationKK.setCommonFieldsDTO(commonFieldsDTO);
                xml = mapDiversionNotificationKK.getMessage();
                break; 
            case EMCSExplanationOnDelay:
                MapExplanationOnDelayKK mapExplanationOnDelayKK = new MapExplanationOnDelayKK(parser, encoding);
                mapExplanationOnDelayKK.setKidsHeader(kidsHeader);
                mapExplanationOnDelayKK.setCommonFieldsDTO(commonFieldsDTO);
                xml = mapExplanationOnDelayKK.getMessage();
                break;
            case EMCSExportRejection:  
                MapExportRejectionKK mapExportRejectionKK = new MapExportRejectionKK(parser, encoding);
                mapExportRejectionKK.setKidsHeader(kidsHeader);
                mapExportRejectionKK.setCommonFieldsDTO(commonFieldsDTO);
                xml = mapExportRejectionKK.getMessage();
                break;  
            case EMCSGenericRefusalMessage:  
                MapGenericRefusalMessageKK mapGenericRefusalMessageKK = 
                                                    new MapGenericRefusalMessageKK(parser, encoding);
                mapGenericRefusalMessageKK.setKidsHeader(kidsHeader);
                mapGenericRefusalMessageKK.setCommonFieldsDTO(commonFieldsDTO);
                xml = mapGenericRefusalMessageKK.getMessage();
                break;             
            case EMCSReminderMessage:  
                MapReminderMessageKK mapReminderMessageKK = new MapReminderMessageKK(parser, encoding);
                mapReminderMessageKK.setKidsHeader(kidsHeader);
                mapReminderMessageKK.setCommonFieldsDTO(commonFieldsDTO);
                xml = mapReminderMessageKK.getMessage();
                break; 
            case EMCSReportOfReceipt:  
                MapReportOfReceiptKK mapReportOfReceiptKK = new MapReportOfReceiptKK(parser, encoding);
                mapReportOfReceiptKK.setKidsHeader(kidsHeader);
                mapReportOfReceiptKK.setCommonFieldsDTO(commonFieldsDTO);
                xml = mapReportOfReceiptKK.getMessage();
                break;                
 			case EMCSValidDeclaration:  
                MapValidDeclarationKK mapValidDeclarationKK = new MapValidDeclarationKK(parser, encoding);
                mapValidDeclarationKK.setKidsHeader(kidsHeader);
                mapValidDeclarationKK.setCommonFieldsDTO(commonFieldsDTO);
                xml = mapValidDeclarationKK.getMessage();
                break;   
 			case EMCSAlertOrRejection:  				 //EI20110705 new for V20
                MapAlertOrRejectionKK mapAlertOrRejectionKK = new MapAlertOrRejectionKK(parser, encoding);
                mapAlertOrRejectionKK.setKidsHeader(kidsHeader);
                mapAlertOrRejectionKK.setCommonFieldsDTO(commonFieldsDTO);
                xml = mapAlertOrRejectionKK.getMessage();
                break;  
 			case EMCSInterruptionOfMovement:                 //EI20110705  new for V20
                MapInterruptionOfMovementKK mapInterruptionOfMovementKK = 
                											  new MapInterruptionOfMovementKK(parser, encoding);
                mapInterruptionOfMovementKK.setKidsHeader(kidsHeader);
                mapInterruptionOfMovementKK.setCommonFieldsDTO(commonFieldsDTO);
                xml = mapInterruptionOfMovementKK.getMessage();
                break;
 			//case EMCSExplanationOfReasonForShortage:        //EI20110721  new for V20
 			case EMCSExplanationOnReasonForShortage:    //EI20110917
 				MapExplanationOfReasonForShortageKK mapExplanationOfReasonForShortageKK = 
 													new MapExplanationOfReasonForShortageKK(parser, encoding);
 				mapExplanationOfReasonForShortageKK.setKidsHeader(kidsHeader);
 				mapExplanationOfReasonForShortageKK.setCommonFieldsDTO(commonFieldsDTO);
                xml = mapExplanationOfReasonForShortageKK.getMessage();
                break;
 			case EMCSStatusResponse:                        //EI20110721  new for V20
 				MapStatusResponseKK mapStatusResponseKK = new MapStatusResponseKK(parser, encoding);
 				mapStatusResponseKK.setKidsHeader(kidsHeader);
 				mapStatusResponseKK.setCommonFieldsDTO(commonFieldsDTO);
                xml = mapStatusResponseKK.getMessage();
                break;
 			case EMCSEventReport:  							//EI20110721  new for V20
                MapEventReportKK mapEventReportKK = new MapEventReportKK(parser, encoding);
                mapEventReportKK.setKidsHeader(kidsHeader);
                mapEventReportKK.setCommonFieldsDTO(commonFieldsDTO);
                xml = mapEventReportKK.getMessage();
                break;
 			case EMCSSubmittedDraftOfSplittingOperation:  							//EI20110727  new for V20
                MapSubmittedDraftOfSplittingOperationKK mapSplittingKK = 
                									new MapSubmittedDraftOfSplittingOperationKK(parser, encoding);
                mapSplittingKK.setKidsHeader(kidsHeader);
                mapSplittingKK.setCommonFieldsDTO(commonFieldsDTO);
                xml = mapSplittingKK.getMessage();
                break;
        	case InternalStatusInformation:
    			MapExpNckKK mapExpNckKK = new MapExpNckKK(parser, "UTF-8");
    			mapExpNckKK.setKidsHeader(kidsHeader);
    			xml = mapExpNckKK.getMessage();
    			break;            
                

           default: throw new FssException("Unknown message type " + msg);
        }
        
	
		return xml;
	}
	
	

}

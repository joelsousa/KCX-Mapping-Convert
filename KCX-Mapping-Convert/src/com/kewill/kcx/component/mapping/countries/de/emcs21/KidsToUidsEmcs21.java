package com.kewill.kcx.component.mapping.countries.de.emcs21;

import javax.xml.stream.XMLEventReader;

import com.kewill.kcx.component.mapping.common.CommonFieldsDTO;
import com.kewill.kcx.component.mapping.conf.Config;
import com.kewill.kcx.component.mapping.countries.de.aes.kids2uids.MapFailureKU;
import com.kewill.kcx.component.mapping.countries.de.emcs.EKidsEmcsMessages;
import com.kewill.kcx.component.mapping.countries.de.emcs20.kids2uids.MapAcceptedExportKU;
import com.kewill.kcx.component.mapping.countries.de.emcs21.kids2uids.MapAlertOrRejectionKU;
import com.kewill.kcx.component.mapping.countries.de.emcs20.kids2uids.MapCancellationKU;
import com.kewill.kcx.component.mapping.countries.de.emcs21.kids2uids.MapChangeOfDestinationKU;
import com.kewill.kcx.component.mapping.countries.de.emcs20.kids2uids.MapDeclarationKU;
import com.kewill.kcx.component.mapping.countries.de.emcs20.kids2uids.MapDiversionNotificationKU;
import com.kewill.kcx.component.mapping.countries.de.emcs20.kids2uids.MapEventReportKU;
import com.kewill.kcx.component.mapping.countries.de.emcs20.kids2uids.MapExplanationOfReasonForShortageKU;
import com.kewill.kcx.component.mapping.countries.de.emcs20.kids2uids.MapExplanationOnDelayKU;
import com.kewill.kcx.component.mapping.countries.de.emcs20.kids2uids.MapExportRejectionKU;
import com.kewill.kcx.component.mapping.countries.de.emcs20.kids2uids.MapGenericRefusalMessageKU;
import com.kewill.kcx.component.mapping.countries.de.emcs.kids2uids.MapInternalStatusInformationKU;
import com.kewill.kcx.component.mapping.countries.de.emcs20.kids2uids.MapInterruptionOfMovementKU;
import com.kewill.kcx.component.mapping.countries.de.emcs20.kids2uids.MapReminderMessageKU;
import com.kewill.kcx.component.mapping.countries.de.emcs20.kids2uids.MapReportOfReceiptKU;
import com.kewill.kcx.component.mapping.countries.de.emcs20.kids2uids.MapStatusResponseKU;
import com.kewill.kcx.component.mapping.countries.de.emcs20.kids2uids.MapSubmittedDraftOfSplittingOperationKU;
import com.kewill.kcx.component.mapping.countries.de.emcs20.kids2uids.MapValidDeclarationKU;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlPdfTgzIntern2;


/**
 * Module       : EMCS<br>
 * Created 		: 10.02.2014<br>
 * Description  : transformer to convert EMCS KIDS-Format to UIDS messages.
 * 				: Changes in V21: EMCSAlertOrRejection and EMCSChangeOfDestination
 * 
 * @author iwaniuk
 * @version 2.0.00
 */
public class KidsToUidsEmcs21 {
    
    public Object readKids(XMLEventReader parser, String encoding, KidsHeader kidsHeader, 
                                                        CommonFieldsDTO commonFieldsDTO) throws Exception {
        String xml = "";
        Object tgz = null;
        boolean returnTgz = false;
	    String msg = kidsHeader.getMessageName();	
	    
        switch (EKidsEmcsMessages.valueOf(msg)) {
        	case EMCSValidDeclaration:  
        		MapValidDeclarationKU mapValidDeclarationKU = new MapValidDeclarationKU(parser, encoding);
        		mapValidDeclarationKU.setKidsHeader(kidsHeader);
        		mapValidDeclarationKU.setCommonFieldsDTO(commonFieldsDTO);
        		xml = mapValidDeclarationKU.getMessage();        		
                if (commonFieldsDTO.isPdfTgz() && commonFieldsDTO.pdfExists()) { // 20110107MS
                    Utils.log("es wird ein tgz generiert!!!");
                    returnTgz = true;
                    tgz = XmlPdfTgzIntern2.createTgz(xml, Config.getPdfpath(), commonFieldsDTO.getFilename());
                    commonFieldsDTO.updateFilename();
                }              
        		break;       
        	case EMCSReminderMessage:  
                MapReminderMessageKU mapReminderMessageKU = new MapReminderMessageKU(parser, encoding);
                mapReminderMessageKU.setKidsHeader(kidsHeader);
                mapReminderMessageKU.setCommonFieldsDTO(commonFieldsDTO);
                xml = mapReminderMessageKU.getMessage();
                break; 
        	case EMCSDiversionNotification:  
                MapDiversionNotificationKU mapDiversionNotificationKU = 
                											new MapDiversionNotificationKU(parser, encoding);
                mapDiversionNotificationKU.setKidsHeader(kidsHeader);
                mapDiversionNotificationKU.setCommonFieldsDTO(commonFieldsDTO);
                xml = mapDiversionNotificationKU.getMessage();
                break;                  
        	case EMCSCancellation:  
                MapCancellationKU mapCancellationKU = new MapCancellationKU(parser, encoding);
                mapCancellationKU.setKidsHeader(kidsHeader);
                mapCancellationKU.setCommonFieldsDTO(commonFieldsDTO);
                xml = mapCancellationKU.getMessage();
                break;
        	case EMCSChangeOfDestination:  
                MapChangeOfDestinationKU mapChangeOfDestinationKU = new MapChangeOfDestinationKU(parser, encoding);
                mapChangeOfDestinationKU.setKidsHeader(kidsHeader);
                mapChangeOfDestinationKU.setCommonFieldsDTO(commonFieldsDTO);
                xml = mapChangeOfDestinationKU.getMessage();
                break;                
           case EMCSDeclaration:  
                MapDeclarationKU mapDeclarationKU = new MapDeclarationKU(parser, encoding);
                mapDeclarationKU.setKidsHeader(kidsHeader);
                mapDeclarationKU.setCommonFieldsDTO(commonFieldsDTO);
                xml = mapDeclarationKU.getMessage();
                break;                                         
           case EMCSReportOfReceipt:  
               MapReportOfReceiptKU mapReportOfReceiptKU = new MapReportOfReceiptKU(parser, encoding);
               mapReportOfReceiptKU.setKidsHeader(kidsHeader);
               mapReportOfReceiptKU.setCommonFieldsDTO(commonFieldsDTO);
               xml = mapReportOfReceiptKU.getMessage();
               break;               
           case EMCSAcceptedExport:  
               MapAcceptedExportKU mapAcceptedExportKU = new MapAcceptedExportKU(parser, encoding);
               mapAcceptedExportKU.setKidsHeader(kidsHeader);
               mapAcceptedExportKU.setCommonFieldsDTO(commonFieldsDTO);
               xml = mapAcceptedExportKU.getMessage();
               break;
           case EMCSExportRejection:  
               MapExportRejectionKU mapExportRejectionKU = new MapExportRejectionKU(parser, encoding);
               mapExportRejectionKU.setKidsHeader(kidsHeader);
               mapExportRejectionKU.setCommonFieldsDTO(commonFieldsDTO);
               xml = mapExportRejectionKU.getMessage();
               break;
           case InternalStatusInformation:  
               MapInternalStatusInformationKU mapInternalStatusInformationKU = 
            	   									              new MapInternalStatusInformationKU(parser, encoding);
               mapInternalStatusInformationKU.setKidsHeader(kidsHeader);
               mapInternalStatusInformationKU.setCommonFieldsDTO(commonFieldsDTO);
               xml = mapInternalStatusInformationKU.getMessage();
               break;
           case EMCSGenericRefusalMessage:  
               MapGenericRefusalMessageKU mapGenericRefusalMessageKU = new MapGenericRefusalMessageKU(parser, encoding);
               mapGenericRefusalMessageKU.setKidsHeader(kidsHeader);
               mapGenericRefusalMessageKU.setCommonFieldsDTO(commonFieldsDTO);
               xml = mapGenericRefusalMessageKU.getMessage();
               break;  
           case EMCSExplanationOnDelay:
               MapExplanationOnDelayKU mapExplanationOnDelayKU = new MapExplanationOnDelayKU(parser, encoding);
               mapExplanationOnDelayKU.setKidsHeader(kidsHeader);
               mapExplanationOnDelayKU.setCommonFieldsDTO(commonFieldsDTO);
               xml = mapExplanationOnDelayKU.getMessage();
               break; 
           case EMCSAlertOrRejection:                      //EI20110705  new for V20
        	   MapAlertOrRejectionKU mapAlertOrRejectionKU = new MapAlertOrRejectionKU(parser, encoding);
               mapAlertOrRejectionKU.setKidsHeader(kidsHeader);
               mapAlertOrRejectionKU.setCommonFieldsDTO(commonFieldsDTO);
               xml = mapAlertOrRejectionKU.getMessage();
               break;
           case EMCSInterruptionOfMovement:                  //EI20110721  new for V20
        	   MapInterruptionOfMovementKU mapInterruptionOfMovementKU = 
        	   												new MapInterruptionOfMovementKU(parser, encoding);
               mapInterruptionOfMovementKU.setKidsHeader(kidsHeader);
               mapInterruptionOfMovementKU.setCommonFieldsDTO(commonFieldsDTO);
               xml = mapInterruptionOfMovementKU.getMessage();
               break;           		
		   //case EMCSExplanationOfReasonForShortage:        //EI20110705  new for V20
           case EMCSExplanationOnReasonForShortage:    //EI20110917
				MapExplanationOfReasonForShortageKU mapExplanationOfReasonForShortageKU = 
													new MapExplanationOfReasonForShortageKU(parser, encoding);
				mapExplanationOfReasonForShortageKU.setKidsHeader(kidsHeader);
				mapExplanationOfReasonForShortageKU.setCommonFieldsDTO(commonFieldsDTO);
               xml = mapExplanationOfReasonForShortageKU.getMessage();
               break;
			case EMCSStatusResponse:                        //EI20110721  new for V20
				MapStatusResponseKU mapStatusResponseKU = new MapStatusResponseKU(parser, encoding);
				mapStatusResponseKU.setKidsHeader(kidsHeader);
				mapStatusResponseKU.setCommonFieldsDTO(commonFieldsDTO);
               xml = mapStatusResponseKU.getMessage();
               break;
			case EMCSEventReport:  							//EI20110721  new for V20
               MapEventReportKU mapEventReportKU = new MapEventReportKU(parser, encoding);
               mapEventReportKU.setKidsHeader(kidsHeader);
               mapEventReportKU.setCommonFieldsDTO(commonFieldsDTO);
               xml = mapEventReportKU.getMessage();
               if (commonFieldsDTO.isPdfTgz() && commonFieldsDTO.pdfExists()) {
                   Utils.log("es wird ein tgz generiert!!!");
                   returnTgz = true;
                   tgz = XmlPdfTgzIntern2.createTgz(xml, Config.getPdfpath(), commonFieldsDTO.getFilename());
                   commonFieldsDTO.updateFilename();
               }
               break;
			case EMCSSubmittedDraftOfSplittingOperation:  	//EI20110727  new for V20
				MapSubmittedDraftOfSplittingOperationKU mapSplittingKU = 
												new MapSubmittedDraftOfSplittingOperationKU(parser, encoding);
				mapSplittingKU.setKidsHeader(kidsHeader);
				mapSplittingKU.setCommonFieldsDTO(commonFieldsDTO);
	            xml = mapSplittingKU.getMessage();
	            break;
            case localAppResult:   //EI20100601
        	   MapFailureKU mapFailureKU  = new MapFailureKU(parser, encoding);
        	   mapFailureKU.setKidsHeader(kidsHeader);
        	   mapFailureKU.setCommonFieldsDTO(commonFieldsDTO);
               xml = mapFailureKU.getMessage();
               break;                                       
           default: 
        	   throw new FssException("Unknown message type " + msg);
        }
        
        if (returnTgz) {
            return tgz;
        } else {
            return xml;
        }
    }   
}

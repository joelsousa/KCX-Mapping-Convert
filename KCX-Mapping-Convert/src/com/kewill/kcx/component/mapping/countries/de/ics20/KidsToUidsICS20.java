package com.kewill.kcx.component.mapping.countries.de.ics20;

import javax.xml.stream.XMLEventReader;

import com.kewill.kcx.component.mapping.common.CommonFieldsDTO;
import com.kewill.kcx.component.mapping.countries.de.aes.kids2uids.MapFailureKU;
import com.kewill.kcx.component.mapping.countries.de.emcs.kids2uids.MapInternalStatusInformationKU;
import com.kewill.kcx.component.mapping.countries.de.ics.EKidsICSMessages;
import com.kewill.kcx.component.mapping.countries.de.ics20.kids2uids.MapAdvanceInterventionNotKU;
import com.kewill.kcx.component.mapping.countries.de.ics20.kids2uids.MapArrivalItemRejectionKU;
import com.kewill.kcx.component.mapping.countries.de.ics20.kids2uids.MapArrivalNotificationKU;
import com.kewill.kcx.component.mapping.countries.de.ics20.kids2uids.MapArrivalNotificationValidationKU;
import com.kewill.kcx.component.mapping.countries.de.ics20.kids2uids.MapDeclarationAmendmentKU;
import com.kewill.kcx.component.mapping.countries.de.ics20.kids2uids.MapDiversionRequestAcknowledgmentKU;
import com.kewill.kcx.component.mapping.countries.de.ics20.kids2uids.MapDiversionRequestKU;
import com.kewill.kcx.component.mapping.countries.de.ics20.kids2uids.MapDiversionRequestRejectedKU;
import com.kewill.kcx.component.mapping.countries.de.ics20.kids2uids.MapEntryReleaseKU;
import com.kewill.kcx.component.mapping.countries.de.ics20.kids2uids.MapEntryReleaseRejectionKU;
import com.kewill.kcx.component.mapping.countries.de.ics20.kids2uids.MapEntrySummaryDeclarationAcknowledgmentKU;
import com.kewill.kcx.component.mapping.countries.de.ics20.kids2uids.MapEntrySummaryDeclarationAmendmentAcceptanceKU;
import com.kewill.kcx.component.mapping.countries.de.ics20.kids2uids.MapEntrySummaryDeclarationAmendmentRejectionKU;
import com.kewill.kcx.component.mapping.countries.de.ics20.kids2uids.MapEntrySummaryDeclarationKU;
import com.kewill.kcx.component.mapping.countries.de.ics20.kids2uids.MapEntrySummaryDeclarationRejectedKU;
import com.kewill.kcx.component.mapping.countries.de.ics20.kids2uids.MapImportControlDecisionKU;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;

/**
 * Module       : ICS<br>
 * Created 		: 2012.10.22<br>
 * Description  : transformer to convert ICS KIDS-Format to UIDS messages.
 *
 * @author iwaniuk
 * @version 1.0.00
 */

public class KidsToUidsICS20 {

    public Object readKids(XMLEventReader parser, String encoding, KidsHeader kidsHeader,
                                                        CommonFieldsDTO commonFieldsDTO) throws Exception {
        String xml = "";
        Object tgz = null;
        boolean returnTgz = false;
	    String msg = kidsHeader.getMessageName();

        switch (EKidsICSMessages.valueOf(msg)) {
        
        	case ICSEntrySummaryDeclaration:
        		MapEntrySummaryDeclarationKU mapEntrySummaryDeclarationKU = 
        			new MapEntrySummaryDeclarationKU(parser, encoding);
        		mapEntrySummaryDeclarationKU.setKidsHeader(kidsHeader);
        		mapEntrySummaryDeclarationKU.setCommonFieldsDTO(commonFieldsDTO);
    			xml = mapEntrySummaryDeclarationKU.getMessage();
    			break;        	
        	case ICSEntrySummaryDeclarationAcknowledgment:
        		MapEntrySummaryDeclarationAcknowledgmentKU entrySummaryDeclarationAcknowledgmentKU =
        			new MapEntrySummaryDeclarationAcknowledgmentKU(parser, encoding);
        		entrySummaryDeclarationAcknowledgmentKU.setKidsHeader(kidsHeader);
        		entrySummaryDeclarationAcknowledgmentKU.setCommonFieldsDTO(commonFieldsDTO);
        		xml = entrySummaryDeclarationAcknowledgmentKU.getMessage();
                break;                
        	case ICSEntrySummaryDeclarationRejected:	
        		MapEntrySummaryDeclarationRejectedKU mapEntrySummaryDeclarationRejectedKU =
        			new MapEntrySummaryDeclarationRejectedKU(parser, encoding);
        		mapEntrySummaryDeclarationRejectedKU.setKidsHeader(kidsHeader);
        		mapEntrySummaryDeclarationRejectedKU.setCommonFieldsDTO(commonFieldsDTO);
        		xml = mapEntrySummaryDeclarationRejectedKU.getMessage();
        		break;        		
        	case ICSDeclarationAmendment:	
        		MapDeclarationAmendmentKU mapDeclarationAmendmentKU = 
        			new MapDeclarationAmendmentKU(parser, encoding);
        		mapDeclarationAmendmentKU.setKidsHeader(kidsHeader);
        		mapDeclarationAmendmentKU.setCommonFieldsDTO(commonFieldsDTO);
        		xml = mapDeclarationAmendmentKU.getMessage();
        		break;        		
        	case ICSEntrySummaryDeclarationAmendmentAcceptance:
        		MapEntrySummaryDeclarationAmendmentAcceptanceKU mapESDecAcc	= 
        			new MapEntrySummaryDeclarationAmendmentAcceptanceKU(parser, encoding);
        		mapESDecAcc.setKidsHeader(kidsHeader);
        		mapESDecAcc.setCommonFieldsDTO(commonFieldsDTO);
        		xml	= mapESDecAcc.getMessage();
        		break;        		
        	case ICSEntrySummaryDeclarationAmendmentRejection:
        		MapEntrySummaryDeclarationAmendmentRejectionKU mapEntrySummaryDecAmendRejectKU =
        			new MapEntrySummaryDeclarationAmendmentRejectionKU(parser, encoding);
    			mapEntrySummaryDecAmendRejectKU.setKidsHeader(kidsHeader);
    			mapEntrySummaryDecAmendRejectKU.setCommonFieldsDTO(commonFieldsDTO);
    			xml = mapEntrySummaryDecAmendRejectKU.getMessage();
    			break;    			
        	case ICSAdvancedInterventionNot:	
        		MapAdvanceInterventionNotKU mapAdvanceInterventionNotKU =
        			new MapAdvanceInterventionNotKU(parser, encoding);
        		mapAdvanceInterventionNotKU.setKidsHeader(kidsHeader);
        		mapAdvanceInterventionNotKU.setCommonFieldsDTO(commonFieldsDTO);
        		xml = mapAdvanceInterventionNotKU.getMessage();
        		break;        		
        	case ICSArrivalNotification:	
        		MapArrivalNotificationKU mapArrivalNotificationKU =
        			new MapArrivalNotificationKU(parser, encoding);
        		mapArrivalNotificationKU.setKidsHeader(kidsHeader);
        		mapArrivalNotificationKU.setCommonFieldsDTO(commonFieldsDTO);
        		xml = mapArrivalNotificationKU.getMessage();
        		break;        	        
        	case ICSArrivalNotificationValidation:	
        		MapArrivalNotificationValidationKU mapArrivalNotificationValidationKU =
        			new MapArrivalNotificationValidationKU(parser, encoding);
        		mapArrivalNotificationValidationKU.setKidsHeader(kidsHeader);
        		mapArrivalNotificationValidationKU.setCommonFieldsDTO(commonFieldsDTO);
        		xml = mapArrivalNotificationValidationKU.getMessage();
        		break;        		
        	case ICSArrivalItemRejection:
        		MapArrivalItemRejectionKU mapArrivalItemRejectionKU =
        			new MapArrivalItemRejectionKU(parser, encoding);
        		mapArrivalItemRejectionKU.setKidsHeader(kidsHeader);
        		mapArrivalItemRejectionKU.setCommonFieldsDTO(commonFieldsDTO);
        		xml = mapArrivalItemRejectionKU.getMessage();
        		break;        		                	
        	case ICSDiversionRequest:
        		MapDiversionRequestKU mapDiversionRequestKU = new MapDiversionRequestKU(parser, encoding);
        		mapDiversionRequestKU.setKidsHeader(kidsHeader);
        		mapDiversionRequestKU.setCommonFieldsDTO(commonFieldsDTO);
        		xml = mapDiversionRequestKU.getMessage();
        		break;
        	case ICSDiversionRequestAcknowledgment:
        		MapDiversionRequestAcknowledgmentKU mapDiversionRequestAcknowledgmentKU =
        			new MapDiversionRequestAcknowledgmentKU(parser, encoding);
        		mapDiversionRequestAcknowledgmentKU.setKidsHeader(kidsHeader);
        		mapDiversionRequestAcknowledgmentKU.setCommonFieldsDTO(commonFieldsDTO);
        		xml = mapDiversionRequestAcknowledgmentKU.getMessage();
                break;                
        	case ICSDiversionRequestRejected:
        		MapDiversionRequestRejectedKU mapDiveReqRejKU	= 
        				new MapDiversionRequestRejectedKU(parser, encoding);
        		mapDiveReqRejKU.setKidsHeader(kidsHeader);
        		mapDiveReqRejKU.setCommonFieldsDTO(commonFieldsDTO);
        		xml	= mapDiveReqRejKU.getMessage();
        		break;        	    			
        	case ICSEntryRelease:                          
        		MapEntryReleaseKU mapEntryReleaseKU =
        			new MapEntryReleaseKU(parser, encoding);
        		mapEntryReleaseKU.setKidsHeader(kidsHeader);
        		mapEntryReleaseKU.setCommonFieldsDTO(commonFieldsDTO);
        		xml = mapEntryReleaseKU.getMessage();
        		break;        		
        	case ICSEntryReleaseRejection:					
        		MapEntryReleaseRejectionKU mapEntryReleaseRejectionKU =
        			new MapEntryReleaseRejectionKU(parser, encoding);
        		mapEntryReleaseRejectionKU.setKidsHeader(kidsHeader);
        		mapEntryReleaseRejectionKU.setCommonFieldsDTO(commonFieldsDTO);
        		xml = mapEntryReleaseRejectionKU.getMessage();
        		break;        		
        	case ICSImportControlDecisionNotification:		
        		MapImportControlDecisionKU mapImportControlDecisionKU =
        			new MapImportControlDecisionKU(parser, encoding);
        		mapImportControlDecisionKU.setKidsHeader(kidsHeader);
        		mapImportControlDecisionKU.setCommonFieldsDTO(commonFieldsDTO);
        		xml = mapImportControlDecisionKU.getMessage();
        		break;                    
        	case localAppResult:   
           	   MapFailureKU mapFailureKU  = new MapFailureKU(parser, encoding);
           	   mapFailureKU.setKidsHeader(kidsHeader);
           	   mapFailureKU.setCommonFieldsDTO(commonFieldsDTO);
                  xml = mapFailureKU.getMessage();
                  break;                     
         	case InternalStatusInformation:  
                 MapInternalStatusInformationKU mapInternalStatusInformationKU = 
                 	new MapInternalStatusInformationKU(parser, encoding);
                 mapInternalStatusInformationKU.setKidsHeader(kidsHeader);
                 mapInternalStatusInformationKU.setCommonFieldsDTO(commonFieldsDTO);
                 xml = mapInternalStatusInformationKU.getMessage();
                 break;
                 
           default: throw new FssException("Unknown message type " + msg);
        }

        if (returnTgz) {
            return tgz;
        } else {
            return xml;
        }
    }

}

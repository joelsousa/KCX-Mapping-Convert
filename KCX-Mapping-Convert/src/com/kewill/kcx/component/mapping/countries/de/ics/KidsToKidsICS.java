package com.kewill.kcx.component.mapping.countries.de.ics;

/*
 * Function    : KidsToKids.java
 * Titel       :
 * Date        : 14.06.2010
 * Author      : Pete T
 * Description : transformer called to convert KIDS-Format to KIDS messages
 * 			   :
 * Parameters  :
 */

import javax.xml.stream.XMLEventReader;

import com.kewill.kcx.component.mapping.common.CommonFieldsDTO;
import com.kewill.kcx.component.mapping.countries.de.aes.kids2kids.MapExpNckKK;
import com.kewill.kcx.component.mapping.countries.de.aes.kids2kids.MapLocalAppKK;
import com.kewill.kcx.component.mapping.countries.de.ics.kids2kids.MapAdvanceInterventionNotKK;
import com.kewill.kcx.component.mapping.countries.de.ics.kids2kids.MapArrivalItemRejectionKK;
import com.kewill.kcx.component.mapping.countries.de.ics.kids2kids.MapArrivalNotificationKK;
import com.kewill.kcx.component.mapping.countries.de.ics.kids2kids.MapArrivalNotificationValidationKK;
import com.kewill.kcx.component.mapping.countries.de.ics.kids2kids.MapDeclarationAmendmentKK;
import com.kewill.kcx.component.mapping.countries.de.ics.kids2kids.MapDiversionRequestAcknowledgmentKK;
import com.kewill.kcx.component.mapping.countries.de.ics.kids2kids.MapDiversionRequestKK;
import com.kewill.kcx.component.mapping.countries.de.ics.kids2kids.MapDiversionRequestRejectedKK;
import com.kewill.kcx.component.mapping.countries.de.ics.kids2kids.MapEntryDetailsDataKK;
import com.kewill.kcx.component.mapping.countries.de.ics.kids2kids.MapEntryReleaseKK;
import com.kewill.kcx.component.mapping.countries.de.ics.kids2kids.MapEntryReleaseRejectionKK;
import com.kewill.kcx.component.mapping.countries.de.ics.kids2kids.MapEntrySummaryDeclarationAcknowledgmentKK;
import com.kewill.kcx.component.mapping.countries.de.ics.kids2kids.MapEntrySummaryDeclarationAmendmentAcceptanceKK;
import com.kewill.kcx.component.mapping.countries.de.ics.kids2kids.MapEntrySummaryDeclarationAmendmentRejectionKK;
import com.kewill.kcx.component.mapping.countries.de.ics.kids2kids.MapEntrySummaryDeclarationKK;
import com.kewill.kcx.component.mapping.countries.de.ics.kids2kids.MapEntrySummaryDeclarationRejectedKK;
import com.kewill.kcx.component.mapping.countries.de.ics.kids2kids.MapImportControlDecisionKK;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;


/**
 * Module       : KidsToKidsICS<br>
 * Created 		: 14.06.2010<br>
 * Description  : transformer to convert ICS KIDS-Format to KIDS messages.
 *
 * @author Pete T
 * @version 1.0.00
 */
public class KidsToKidsICS {

    public String readKids(XMLEventReader parser, KidsHeader kidsHeader,
    		CommonFieldsDTO commonFieldsDTO) throws Exception {
	    String encoding = "UTF-8";
        String xml = "";
	    String msg = kidsHeader.getMessageName();

        switch (EKidsICSMessages.valueOf(msg)) {
        
        	case ICSEntrySummaryDeclaration:
        		MapEntrySummaryDeclarationKK mapEntrySummaryDeclarationKK =
        			new MapEntrySummaryDeclarationKK(parser, encoding);
    			mapEntrySummaryDeclarationKK.setKidsHeader(kidsHeader);
    			mapEntrySummaryDeclarationKK.setCommonFieldsDTO(commonFieldsDTO);
    			xml = mapEntrySummaryDeclarationKK.getMessage();
    			break;
    	
        	case ICSEntrySummaryDeclarationAcknowledgment:
        		MapEntrySummaryDeclarationAcknowledgmentKK mapEntrySummaryDeclarationAcknowledgmentKK =
        			new MapEntrySummaryDeclarationAcknowledgmentKK(parser, encoding);
        		mapEntrySummaryDeclarationAcknowledgmentKK.setKidsHeader(kidsHeader);
        		mapEntrySummaryDeclarationAcknowledgmentKK.setCommonFieldsDTO(commonFieldsDTO);
        		xml = mapEntrySummaryDeclarationAcknowledgmentKK.getMessage();
                break;
                
        	case ICSEntrySummaryDeclarationRejected:
        		MapEntrySummaryDeclarationRejectedKK mapEntrySummaryDeclarationRejectedKK =
        			new MapEntrySummaryDeclarationRejectedKK(parser, encoding);
    			mapEntrySummaryDeclarationRejectedKK.setKidsHeader(kidsHeader);
    			mapEntrySummaryDeclarationRejectedKK.setCommonFieldsDTO(commonFieldsDTO);
    			xml = mapEntrySummaryDeclarationRejectedKK.getMessage();
            break;
                
        	case ICSDeclarationAmendment:
        		MapDeclarationAmendmentKK mapDeclarationAmendmentKK =
        			new MapDeclarationAmendmentKK(parser, encoding);
        		mapDeclarationAmendmentKK.setKidsHeader(kidsHeader);
        		mapDeclarationAmendmentKK.setCommonFieldsDTO(commonFieldsDTO);
        		xml = mapDeclarationAmendmentKK.getMessage();
                break;                    
                
        	case ICSEntrySummaryDeclarationAmendmentAcceptance:
        		MapEntrySummaryDeclarationAmendmentAcceptanceKK mapEntrySummaryDeclarationAcceptanceKK =
        			new MapEntrySummaryDeclarationAmendmentAcceptanceKK(parser, encoding);
        		mapEntrySummaryDeclarationAcceptanceKK.setKidsHeader(kidsHeader);
        		mapEntrySummaryDeclarationAcceptanceKK.setCommonFieldsDTO(commonFieldsDTO);
        		xml = mapEntrySummaryDeclarationAcceptanceKK.getMessage();
                break;
                            
        	case ICSEntrySummaryDeclarationAmendmentRejection:
        		MapEntrySummaryDeclarationAmendmentRejectionKK mapEntrySummaryDecAmendRejectKK =
        			new MapEntrySummaryDeclarationAmendmentRejectionKK(parser, encoding);
        		mapEntrySummaryDecAmendRejectKK.setKidsHeader(kidsHeader);
        		mapEntrySummaryDecAmendRejectKK.setCommonFieldsDTO(commonFieldsDTO);
    			xml = mapEntrySummaryDecAmendRejectKK.getMessage();
            	break;
        
        	case ICSDiversionRequest:
        		MapDiversionRequestKK mapDiversionRequestKK = new MapDiversionRequestKK(parser, encoding);
        		mapDiversionRequestKK.setKidsHeader(kidsHeader);
        		mapDiversionRequestKK.setCommonFieldsDTO(commonFieldsDTO);
        		xml = mapDiversionRequestKK.getMessage();
        		break;
        	
        	case ICSDiversionRequestAcknowledgment:
        		MapDiversionRequestAcknowledgmentKK mapDiversionRequestAcknowledgmentKK =
        			new MapDiversionRequestAcknowledgmentKK(parser, encoding);
        		mapDiversionRequestAcknowledgmentKK.setKidsHeader(kidsHeader);
        		mapDiversionRequestAcknowledgmentKK.setCommonFieldsDTO(commonFieldsDTO);
        		xml = mapDiversionRequestAcknowledgmentKK.getMessage();
                break;
                
        	case ICSDiversionRequestRejected:	// MB20101109
        		MapDiversionRequestRejectedKK mapDiveReqRejKK	= 
        				new MapDiversionRequestRejectedKK(parser, encoding);
        			mapDiveReqRejKK.setKidsHeader(kidsHeader);
        			mapDiveReqRejKK.setCommonFieldsDTO(commonFieldsDTO);
        			xml	= mapDiveReqRejKK.getMessage();
        		break;                                        
            
        	case ICSArrivalNotification:
        		MapArrivalNotificationKK mapArrivalNotificationKK =
        			new MapArrivalNotificationKK(parser, encoding);
        		mapArrivalNotificationKK.setKidsHeader(kidsHeader);
        		mapArrivalNotificationKK.setCommonFieldsDTO(commonFieldsDTO);
        		xml = mapArrivalNotificationKK.getMessage();
                break;
                
        	case ICSArrivalNotificationValidation:
        		MapArrivalNotificationValidationKK mapArrivalNotificationValidationKK =
        			new MapArrivalNotificationValidationKK(parser, encoding);
        		mapArrivalNotificationValidationKK.setKidsHeader(kidsHeader);
        		mapArrivalNotificationValidationKK.setCommonFieldsDTO(commonFieldsDTO);
        		xml = mapArrivalNotificationValidationKK.getMessage();
                break;
               
        	case ICSArrivalItemRejection:
        		MapArrivalItemRejectionKK mapArrivalItemRejectionKK =
        			new MapArrivalItemRejectionKK(parser, encoding);
        		mapArrivalItemRejectionKK.setKidsHeader(kidsHeader);
        		mapArrivalItemRejectionKK.setCommonFieldsDTO(commonFieldsDTO);
        		xml = mapArrivalItemRejectionKK.getMessage();
                break;
                
        	case ICSAdvancedInterventionNot:
        		MapAdvanceInterventionNotKK mapAdvanceInterventionNotKK =
        			new MapAdvanceInterventionNotKK(parser, encoding);
        		mapAdvanceInterventionNotKK.setKidsHeader(kidsHeader);
        		mapAdvanceInterventionNotKK.setCommonFieldsDTO(commonFieldsDTO);
        		xml = mapAdvanceInterventionNotKK.getMessage();
                break;                                	        
        		
        	case ICSEntryRelease:									//EI20110203 new
        		MapEntryReleaseKK mapEntryReleaseKK =
        			new MapEntryReleaseKK(parser, encoding);
        		mapEntryReleaseKK.setKidsHeader(kidsHeader);
        		mapEntryReleaseKK.setCommonFieldsDTO(commonFieldsDTO);
        		xml = mapEntryReleaseKK.getMessage();
        		break;
        		
        	case ICSEntryReleaseRejection:							//EI20110203 new
        		MapEntryReleaseRejectionKK mapEntryReleaseRejectionKK =
        			new MapEntryReleaseRejectionKK(parser, encoding);
        		mapEntryReleaseRejectionKK.setKidsHeader(kidsHeader);
        		mapEntryReleaseRejectionKK.setCommonFieldsDTO(commonFieldsDTO);
        		xml = mapEntryReleaseRejectionKK.getMessage();
        		break;
        		
        	case ICSImportControlDecisionNotification:				//EI20110203 new
        		MapImportControlDecisionKK mapImportControlDecisionKK =
        			new MapImportControlDecisionKK(parser, encoding);
        		mapImportControlDecisionKK.setKidsHeader(kidsHeader);
        		mapImportControlDecisionKK.setCommonFieldsDTO(commonFieldsDTO);
        		xml = mapImportControlDecisionKK.getMessage();
        		break;     
        		
        	case ICSEntryDetailsData:		 //EI20131108					
        		MapEntryDetailsDataKK mapEntryDetailsDataKK = 
        			new MapEntryDetailsDataKK(parser, encoding);
        		mapEntryDetailsDataKK.setKidsHeader(kidsHeader);
        		mapEntryDetailsDataKK.setCommonFieldsDTO(commonFieldsDTO);
        		xml = mapEntryDetailsDataKK.getMessage();
        		break; 
        		
        	case InternalStatusInformation:
    			MapExpNckKK mapExpNckKK = new MapExpNckKK(parser, "UTF-8");
    			mapExpNckKK.setKidsHeader(kidsHeader);
    			xml = mapExpNckKK.getMessage();
    			break;    
    			
        	case localAppResult:   //CK20101105
        		//MapFailureKK mapFailureKK  = new MapFailureKK(parser, encoding);
        		MapLocalAppKK mapFailureKK  = new MapLocalAppKK(parser, encoding);        	
        		mapFailureKK.setKidsHeader(kidsHeader);
        		mapFailureKK.setCommonFieldsDTO(commonFieldsDTO);
                xml = mapFailureKK.getMessage();
                break;    			
    			
           default: throw new FssException("Unknown message type " + msg);
        }

        return xml;
    }

}

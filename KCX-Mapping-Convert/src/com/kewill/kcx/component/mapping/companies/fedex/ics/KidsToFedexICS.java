package com.kewill.kcx.component.mapping.companies.fedex.ics;

import javax.xml.stream.XMLEventReader;

import com.kewill.kcx.component.mapping.common.CommonFieldsDTO;
import com.kewill.kcx.component.mapping.companies.fedex.ics.kids2fedex.MapICSAdvancedInterventionNotKF;
import com.kewill.kcx.component.mapping.companies.fedex.ics.kids2fedex.MapICSArrivalItemRejectionKF;
import com.kewill.kcx.component.mapping.companies.fedex.ics.kids2fedex.MapICSArrivalNotificationValidationKF;
import com.kewill.kcx.component.mapping.companies.fedex.ics.kids2fedex.MapICSDiversionRequestAcknowledgmentKF;
import com.kewill.kcx.component.mapping.companies.fedex.ics.kids2fedex.MapICSDiversionRequestRejectedKF;
import com.kewill.kcx.component.mapping.companies.fedex.ics.kids2fedex.MapICSEntrySummaryDeclarationAcknowledgmentKF;
import com.kewill.kcx.component.mapping.companies.fedex.ics.kids2fedex.MapICSEntrySummaryDeclarationAmendmentAcceptanceKF;
import com.kewill.kcx.component.mapping.companies.fedex.ics.kids2fedex.MapICSEntrySummaryDeclarationAmendmentRejectionKF;
import com.kewill.kcx.component.mapping.companies.fedex.ics.kids2fedex.MapICSEntrySummaryDeclarationRejectedKF;
import com.kewill.kcx.component.mapping.countries.de.ics.EKidsICSMessages;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;


/**
 * Module       : KidsToFedexICS<br>
 * Created 		: 12.06.2010<br>
 * Description  : transformer to convert ICS KIDS-Format to Fedex messages.
 *
 * @author Edwin B.
 * @version 1.0.00
 */
public class KidsToFedexICS {
    private String msgCode = "";
    public String readKids(XMLEventReader parser, String encoding, KidsHeader kidsHeader,
                                                        CommonFieldsDTO commonFieldsDTO) throws Exception {
        String xml = "";
	    String msg = kidsHeader.getMessageName();

 
        switch (EKidsICSMessages.valueOf(msg)) {
        	
        	case ICSEntrySummaryDeclarationAcknowledgment:
        		msgCode = "IE328";
        		MapICSEntrySummaryDeclarationAcknowledgmentKF mapICSEntrySummaryDeclarationAcknowledgmentKF	= 
        			new MapICSEntrySummaryDeclarationAcknowledgmentKF(parser, encoding);

        		mapICSEntrySummaryDeclarationAcknowledgmentKF.setKidsHeader(kidsHeader);
        		mapICSEntrySummaryDeclarationAcknowledgmentKF.setCommonFieldsDTO(commonFieldsDTO);
        		//String content = mapICSEntrySummaryDeclarationAcknowledgmentKF.getMessage();
        		xml	= mapICSEntrySummaryDeclarationAcknowledgmentKF.getMessage();
    			break;        	
    		
        	case ICSEntrySummaryDeclarationRejected:
        		MapICSEntrySummaryDeclarationRejectedKF mapICSEntrySumDecRejKF	= 
        			new MapICSEntrySummaryDeclarationRejectedKF(parser, encoding);
    				msgCode = "IE316";
        			mapICSEntrySumDecRejKF.setKidsHeader(kidsHeader);
        			mapICSEntrySumDecRejKF.setCommonFieldsDTO(commonFieldsDTO);
        			xml	= mapICSEntrySumDecRejKF.getMessage();
    			break;
    		
        	case ICSArrivalItemRejection:
        		MapICSArrivalItemRejectionKF mapICSArrivalItemRejection	= 
        			new MapICSArrivalItemRejectionKF(parser, encoding);
        		msgCode = "IE349";
        		mapICSArrivalItemRejection.setKidsHeader(kidsHeader);
        		mapICSArrivalItemRejection.setCommonFieldsDTO(commonFieldsDTO);
        		xml	= mapICSArrivalItemRejection.getMessage();
        		
    			break; 
    			
        	case ICSDiversionRequestRejected:
        		MapICSDiversionRequestRejectedKF mapICSDiversionRequestRejected	= 
        			new MapICSDiversionRequestRejectedKF(parser, encoding);
        		msgCode = "IE324";
        		mapICSDiversionRequestRejected.setKidsHeader(kidsHeader);
        		mapICSDiversionRequestRejected.setCommonFieldsDTO(commonFieldsDTO);
        		xml	= mapICSDiversionRequestRejected.getMessage();

    			break;
    		
        	case ICSEntrySummaryDeclarationAmendmentAcceptance:
        		MapICSEntrySummaryDeclarationAmendmentAcceptanceKF mapICSEntrySummaryDeclarationAmendmentAcceptance	= 
        			new MapICSEntrySummaryDeclarationAmendmentAcceptanceKF(parser, encoding);
        		msgCode = "IE304";
        		mapICSEntrySummaryDeclarationAmendmentAcceptance.setKidsHeader(kidsHeader);
        		mapICSEntrySummaryDeclarationAmendmentAcceptance.setCommonFieldsDTO(commonFieldsDTO);
        		xml	= mapICSEntrySummaryDeclarationAmendmentAcceptance.getMessage();
        		break;
        	
        	case ICSEntrySummaryDeclarationAmendmentRejection:
        		MapICSEntrySummaryDeclarationAmendmentRejectionKF mapICSEntrySumDecAmendRej	= 
        			new MapICSEntrySummaryDeclarationAmendmentRejectionKF(parser, encoding);
    				msgCode = "IE305";
        			mapICSEntrySumDecAmendRej.setKidsHeader(kidsHeader);
        			mapICSEntrySumDecAmendRej.setCommonFieldsDTO(commonFieldsDTO);
        			xml	= mapICSEntrySumDecAmendRej.getMessage();

        		break;
        	
        	case ICSDiversionRequestAcknowledgment:
        		MapICSDiversionRequestAcknowledgmentKF mapICSDiversionRequestAcknowledgmentKF = 
        			new MapICSDiversionRequestAcknowledgmentKF(parser, encoding);
        			msgCode = "IE325";
        			mapICSDiversionRequestAcknowledgmentKF.setKidsHeader(kidsHeader);
        			mapICSDiversionRequestAcknowledgmentKF.setCommonFieldsDTO(commonFieldsDTO);
        			xml	= mapICSDiversionRequestAcknowledgmentKF.getMessage();
        		break;
        		
        	case ICSArrivalNotificationValidation:
        		MapICSArrivalNotificationValidationKF mapICSArrivalNotificationValidationKF	= 
        			new MapICSArrivalNotificationValidationKF(parser, encoding);
        			msgCode = "IE348";
        			mapICSArrivalNotificationValidationKF.setKidsHeader(kidsHeader);
        			mapICSArrivalNotificationValidationKF.setCommonFieldsDTO(commonFieldsDTO);
        			xml	= mapICSArrivalNotificationValidationKF.getMessage();
        		break;
        	case ICSAdvancedInterventionNot:
        		MapICSAdvancedInterventionNotKF mapICSAdvancedInterventionNotKF	= 
        			new MapICSAdvancedInterventionNotKF(parser, encoding);
        			msgCode = "IE351";
        			mapICSAdvancedInterventionNotKF.setKidsHeader(kidsHeader);
	        		mapICSAdvancedInterventionNotKF.setCommonFieldsDTO(commonFieldsDTO);
        			xml	= mapICSAdvancedInterventionNotKF.getMessage();
        		break;
        	default: throw new FssException("Unknown message type " + msg);
        }

       return xml;

    }
    
    public String getMsgCode() {
    	return msgCode;
    }

}

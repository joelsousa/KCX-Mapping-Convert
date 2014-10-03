package com.kewill.kcx.component.mapping.companies.unisys.ics;

/*
 * Function    : KidsToUids.java
 * Titel       :
 * Date        : 12.06.2010
 * Author      : Pete T
 * Description : transformer called to convert KIDS-Format to UIDS messages
 * 			   :
 * Parameters  :
 */

import javax.xml.stream.XMLEventReader;

import com.kewill.kcx.component.mapping.common.CommonFieldsDTO;
import com.kewill.kcx.component.mapping.companies.unisys.ics.kids2unisys.MapArrivalItemRejectionKUni;
import com.kewill.kcx.component.mapping.companies.unisys.ics.kids2unisys.MapArrivalNotificationValidationKUni;
import com.kewill.kcx.component.mapping.companies.unisys.ics.kids2unisys.MapDiversionRequestAcknowledgmentKUni;
import com.kewill.kcx.component.mapping.companies.unisys.ics.kids2unisys.MapDiversionRequestRejectedKUni;
import com.kewill.kcx.component.mapping.companies.unisys.ics.kids2unisys.MapEntrySummaryDeclarationAcknowledgmentKUni;
import com.kewill.kcx.component.mapping.companies.unisys.ics.kids2unisys.MapEntrySummaryDeclarationAmendmentAcceptanceKUni;
import com.kewill.kcx.component.mapping.companies.unisys.ics.kids2unisys.MapEntrySummaryDeclarationAmendmentRejectionKUni;
import com.kewill.kcx.component.mapping.companies.unisys.ics.kids2unisys.MapEntrySummaryDeclarationRejectedKUni;
import com.kewill.kcx.component.mapping.companies.unisys.ics.kids2unisys.MapLocalAppResultKUni;
import com.kewill.kcx.component.mapping.countries.de.ics.EKidsICSMessages;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;


/**
 * Module       : KidsToUidsICS<br>
 * Created 		: 12.06.2010<br>
 * Description  : transformer to convert ICS KIDS-Format to UIDS messages.
 *
 * @author Pete T
 * @version 1.0.00
 */
public class KidsToUnisysICS {

    public Object readKids(XMLEventReader parser, String encoding, KidsHeader kidsHeader,
                                                        CommonFieldsDTO commonFieldsDTO) throws Exception {
        String xml = "";
        Object tgz = null;
        boolean returnTgz = false;
	    String msg = kidsHeader.getMessageName();
 
        switch (EKidsICSMessages.valueOf(msg)) {
        	
        	case ICSEntrySummaryDeclarationAmendmentAcceptance:    	// IE304
        		MapEntrySummaryDeclarationAmendmentAcceptanceKUni mapESDeclarationAcc	= 
        			new MapEntrySummaryDeclarationAmendmentAcceptanceKUni(parser, encoding);
        		mapESDeclarationAcc.setKidsHeader(kidsHeader);
        		mapESDeclarationAcc.setCommonFieldsDTO(commonFieldsDTO);
        		xml	= mapESDeclarationAcc.getMessage();
    			break;        	
        	case ICSEntrySummaryDeclarationRejected:	  			 // IE316
        		MapEntrySummaryDeclarationRejectedKUni mapESDeclarationRejected =
        			new MapEntrySummaryDeclarationRejectedKUni(parser, encoding);
        		mapESDeclarationRejected.setKidsHeader(kidsHeader);
        		mapESDeclarationRejected.setCommonFieldsDTO(commonFieldsDTO);
        		xml = mapESDeclarationRejected.getMessage();
        		break;         	
        	case ICSEntrySummaryDeclarationAcknowledgment:			// IE328
        		MapEntrySummaryDeclarationAcknowledgmentKUni mapESDeclarationAck =
        			new MapEntrySummaryDeclarationAcknowledgmentKUni(parser, encoding);
        		mapESDeclarationAck.setKidsHeader(kidsHeader);
        		mapESDeclarationAck.setCommonFieldsDTO(commonFieldsDTO);
        		xml = mapESDeclarationAck.getMessage();
                break;   
        	case ICSEntrySummaryDeclarationAmendmentRejection:		// IE305
        		MapEntrySummaryDeclarationAmendmentRejectionKUni mapESDeclarationAmendReject =
        			new MapEntrySummaryDeclarationAmendmentRejectionKUni(parser, encoding);
        		mapESDeclarationAmendReject.setKidsHeader(kidsHeader);
        		mapESDeclarationAmendReject.setCommonFieldsDTO(commonFieldsDTO);
    			xml = mapESDeclarationAmendReject.getMessage();
    			break;
        	case ICSDiversionRequestAcknowledgment:					// IE325
        		MapDiversionRequestAcknowledgmentKUni mapDiversionAck =
        			new MapDiversionRequestAcknowledgmentKUni(parser, encoding);
        		mapDiversionAck.setKidsHeader(kidsHeader);
        		mapDiversionAck.setCommonFieldsDTO(commonFieldsDTO);
        		xml = mapDiversionAck.getMessage();
                break;    		        
        	case ICSDiversionRequestRejected:						// IE324
        		MapDiversionRequestRejectedKUni mapDiversionRejected	= 
        			new MapDiversionRequestRejectedKUni(parser, encoding);
        		mapDiversionRejected.setKidsHeader(kidsHeader);
        		mapDiversionRejected.setCommonFieldsDTO(commonFieldsDTO);
        		xml	= mapDiversionRejected.getMessage();
        		break;        		        
        	case ICSArrivalNotificationValidation:					// IE348
        		MapArrivalNotificationValidationKUni mapArrivalValidation =
        			new MapArrivalNotificationValidationKUni(parser, encoding);
        		mapArrivalValidation.setKidsHeader(kidsHeader);
        		mapArrivalValidation.setCommonFieldsDTO(commonFieldsDTO);
        		xml = mapArrivalValidation.getMessage();
        		break;        	
        	case ICSArrivalItemRejection:							// IE349
        		MapArrivalItemRejectionKUni mapArrivalItemRejection =
        			new MapArrivalItemRejectionKUni(parser, encoding);
        		mapArrivalItemRejection.setKidsHeader(kidsHeader);
        		mapArrivalItemRejection.setCommonFieldsDTO(commonFieldsDTO);
        		xml = mapArrivalItemRejection.getMessage();
        		break;  	
        	//20110209                                             //IE316 
        	case localAppResult:   
        		MapLocalAppResultKUni mapLocalAppResultKUni =
        			new MapLocalAppResultKUni(parser, encoding);
        		mapLocalAppResultKUni.setKidsHeader(kidsHeader);
        		mapLocalAppResultKUni.setCommonFieldsDTO(commonFieldsDTO);
        		xml = mapLocalAppResultKUni.getMessage();
        		break;         	
        		/*
        		MapLocalAppResultKUni mapLocalAppResultKUni  = new MapLocalAppResultKUni(parser, encoding);
        		mapLocalAppResultKUni.setKidsHeader(kidsHeader);
        		mapLocalAppResultKUni.setCommonFieldsDTO(commonFieldsDTO);
             	xml = mapLocalAppResultKUni.getMessage();
             	break;   
    		
            	case ICSDiversionRequest:
            		MapDiversionRequestKU mapDiversionRequestKU = new MapDiversionRequestKU(parser, encoding);
            		mapDiversionRequestKU.setKidsHeader(kidsHeader);
            		mapDiversionRequestKU.setCommonFieldsDTO(commonFieldsDTO);
            		xml = mapDiversionRequestKU.getMessage();
            		break;
            	case ICSArrivalNotification:	
        			MapArrivalNotificationKU mapArrivalNotificationKU =
        			new MapArrivalNotificationKU(parser, encoding);
        			mapArrivalNotificationKU.setKidsHeader(kidsHeader);
        			mapArrivalNotificationKU.setCommonFieldsDTO(commonFieldsDTO);
        			xml = mapArrivalNotificationKU.getMessage();
        			break;
				case ICSDeclarationAmendment:	
        			MapDeclarationAmendmentKU mapDeclarationAmendmentKU = 
        			new MapDeclarationAmendmentKU(parser, encoding);
        			mapDeclarationAmendmentKU.setKidsHeader(kidsHeader);
        			mapDeclarationAmendmentKU.setCommonFieldsDTO(commonFieldsDTO);
        			xml = mapDeclarationAmendmentKU.getMessage();
        			break; 
        		case ICSAdvancedInterventionNot:	
        			MapAdvanceInterventionNotKU mapAdvanceInterventionNotKU =
        			new MapAdvanceInterventionNotKU(parser, encoding);
        			mapAdvanceInterventionNotKU.setKidsHeader(kidsHeader);
        			mapAdvanceInterventionNotKU.setCommonFieldsDTO(commonFieldsDTO);
        			xml = mapAdvanceInterventionNotKU.getMessage();
        			break;  
        	
        		case InternalStatusInformation:  
                	MapInternalStatusInformationKU mapInternalStatusInformationKU = 
                	new MapInternalStatusInformationKU(parser, encoding);
                	mapInternalStatusInformationKU.setKidsHeader(kidsHeader);
                	mapInternalStatusInformationKU.setCommonFieldsDTO(commonFieldsDTO);
                	xml = mapInternalStatusInformationKU.getMessage();
                	break;
            */
    			
           default: throw new FssException("Unknown message type " + msg);
        }

        if (returnTgz) {
            return tgz;
        } else {
            return xml;
        }
    }

}

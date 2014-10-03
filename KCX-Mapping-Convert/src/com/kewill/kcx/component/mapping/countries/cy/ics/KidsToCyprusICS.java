package com.kewill.kcx.component.mapping.countries.cy.ics;

import javax.xml.stream.XMLEventReader;

import com.kewill.kcx.component.mapping.common.CommonFieldsDTO;
import com.kewill.kcx.component.mapping.countries.cy.ics.kids2cy.MapICSAdvancedInterventionNotKC;
import com.kewill.kcx.component.mapping.countries.cy.ics.kids2cy.MapICSDeclarationAmendmentKC;
import com.kewill.kcx.component.mapping.countries.cy.ics.kids2cy.MapICSDiversionRequestAcknowledgementKC;
import com.kewill.kcx.component.mapping.countries.cy.ics.kids2cy.MapICSDiversionRequestKC;
import com.kewill.kcx.component.mapping.countries.cy.ics.kids2cy.MapICSDiversionRequestRejectedKC;
import com.kewill.kcx.component.mapping.countries.cy.ics.kids2cy.MapICSEntrySummaryDeclarationAcknowledgementKC;
import com.kewill.kcx.component.mapping.countries.cy.ics.kids2cy.MapICSEntrySummaryDeclarationAmendmentAcceptedKC;
import com.kewill.kcx.component.mapping.countries.cy.ics.kids2cy.MapICSEntrySummaryDeclarationAmendmentRejectionKC;
import com.kewill.kcx.component.mapping.countries.cy.ics.kids2cy.MapICSEntrySummaryDeclarationKC;
import com.kewill.kcx.component.mapping.countries.cy.ics.kids2cy.MapLocalAppResultKC;
import com.kewill.kcx.component.mapping.formats.cyprus.common.CyprusHeader;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;

/**
 * Module : KidsToCyprusICS<br>
 * Date Created : 01.06.2011<br>
 * Description : Converts ICS KIDS-Format to Cyprus messages format.
 * 
 * @author Frederick Topico
 * @version 1.0.00
 */
public class KidsToCyprusICS {
	private String msgCode = "";

	public String readKids(XMLEventReader parser, String encoding,
			KidsHeader kidsHeader, CyprusHeader cyprusHeader, CommonFieldsDTO commonFieldsDTO)
			throws Exception {

		String xml = null;
		String msg = kidsHeader.getMessageName();

		switch (ECyprusICSMessages.valueOf(msg)) {
		case ICSEntrySummaryDeclaration:
			msgCode = "IE315";
			MapICSEntrySummaryDeclarationKC mapICSESDKC	= new MapICSEntrySummaryDeclarationKC(parser, encoding);
			mapICSESDKC.setKidsHeader(kidsHeader);
			mapICSESDKC.setCyprusHeader(cyprusHeader);     // MS20110629
			mapICSESDKC.setCommonFieldsDTO(commonFieldsDTO);
			xml	= mapICSESDKC.getMessage();
			break;		
		case ICSDeclarationAmendment:  
			msgCode = "IE313";
			MapICSDeclarationAmendmentKC mapICSDeclarationAmendmentKC = 
				new MapICSDeclarationAmendmentKC(parser, encoding);
			mapICSDeclarationAmendmentKC.setKidsHeader(kidsHeader);
			mapICSDeclarationAmendmentKC.setCyprusHeader(cyprusHeader);     // MS20110629
			mapICSDeclarationAmendmentKC.setCommonFieldsDTO(commonFieldsDTO);
			xml = mapICSDeclarationAmendmentKC.getMessage();
			break;	
		case ICSDiversionRequest:
			msgCode = "IE323";
			MapICSDiversionRequestKC mapICSDiversionRequestKC = new MapICSDiversionRequestKC(parser, encoding);
			mapICSDiversionRequestKC.setKidsHeader(kidsHeader);
			mapICSDiversionRequestKC.setCyprusHeader(cyprusHeader);     // MS20110629
			mapICSDiversionRequestKC.setCommonFieldsDTO(commonFieldsDTO);
			xml = mapICSDiversionRequestKC.getMessage();
			break;
		case localAppResult:   //EI20110708 is actually only CyToKids
			msgCode = "CD917B";			
    		MapLocalAppResultKC mapLocalAppResultKC  = new MapLocalAppResultKC(parser, encoding); 
    		mapLocalAppResultKC.setKidsHeader(kidsHeader);
    		mapLocalAppResultKC.setCyprusHeader(cyprusHeader);    
    		mapLocalAppResultKC.setCommonFieldsDTO(commonFieldsDTO);
            xml = mapLocalAppResultKC.getMessage();            
            break;
            
            
		//EI20110708: case ICSEntrySummaryDeclarationAcknowledgement: //was wrong written, right is without "e"
		case ICSEntrySummaryDeclarationAcknowledgment:  //EI20110708: is actually only CyToKids
			msgCode = "IE328";
			MapICSEntrySummaryDeclarationAcknowledgementKC mapICSEntrySummaryDeclarationAcknowledgementKC = 
				new MapICSEntrySummaryDeclarationAcknowledgementKC(parser, encoding);
			mapICSEntrySummaryDeclarationAcknowledgementKC.setKidsHeader(kidsHeader);
			mapICSEntrySummaryDeclarationAcknowledgementKC.setCyprusHeader(cyprusHeader);     // MS20110629
			mapICSEntrySummaryDeclarationAcknowledgementKC.setCommonFieldsDTO(commonFieldsDTO);
			xml = mapICSEntrySummaryDeclarationAcknowledgementKC.getMessage();
			break;
		//case ICSEntrySummaryDeclarationRejected:    //EI20110708: is actually only CyToKids					
			
		
			
		//EI20110708: ICSEntrySummaryDeclarationAmendmentAccepted: //was wrong written
		case ICSEntrySummaryDeclarationAmendmentAcceptance:   //EI20110708: is actually only CyToKids	  
			msgCode = "IE304";
			MapICSEntrySummaryDeclarationAmendmentAcceptedKC mapICSEntrySummaryDeclarationAmendmentAcceptedKC = 
			    new MapICSEntrySummaryDeclarationAmendmentAcceptedKC(parser, encoding);
			mapICSEntrySummaryDeclarationAmendmentAcceptedKC.setKidsHeader(kidsHeader);
			mapICSEntrySummaryDeclarationAmendmentAcceptedKC.setCyprusHeader(cyprusHeader);     // MS20110629
			mapICSEntrySummaryDeclarationAmendmentAcceptedKC.setCommonFieldsDTO(commonFieldsDTO);

			xml = mapICSEntrySummaryDeclarationAmendmentAcceptedKC.getMessage();
			break;
		
		case ICSEntrySummaryDeclarationAmendmentRejection: //EI20110708: ist eigentlich nur CyToKids
			msgCode = "IE305";
			MapICSEntrySummaryDeclarationAmendmentRejectionKC mapICSEntrySummaryDeclarationAmendmentRejectionKC = 
					new MapICSEntrySummaryDeclarationAmendmentRejectionKC(parser, encoding);
			mapICSEntrySummaryDeclarationAmendmentRejectionKC.setKidsHeader(kidsHeader);
			mapICSEntrySummaryDeclarationAmendmentRejectionKC.setCyprusHeader(cyprusHeader);     // MS20110629
			mapICSEntrySummaryDeclarationAmendmentRejectionKC.setCommonFieldsDTO(commonFieldsDTO);
			xml = mapICSEntrySummaryDeclarationAmendmentRejectionKC.getMessage();
			break;				
			
		//EI20110708: case ICSDiversionRequestAcknowledgement: //was wrong written, right is without "e"
		case ICSDiversionRequestAcknowledgment:  //EI20110708 is actually only CyToKids		
			msgCode = "IE325";
			MapICSDiversionRequestAcknowledgementKC mapICSDiversionRequestAcknowledgementKC = 
				new MapICSDiversionRequestAcknowledgementKC(parser, encoding);
			mapICSDiversionRequestAcknowledgementKC.setKidsHeader(kidsHeader);
			mapICSDiversionRequestAcknowledgementKC.setCyprusHeader(cyprusHeader);     // MS20110629
			mapICSDiversionRequestAcknowledgementKC.setCommonFieldsDTO(commonFieldsDTO);
			xml = mapICSDiversionRequestAcknowledgementKC.getMessage();
			break;
			
		case ICSDiversionRequestRejected:  //EI20110708 is actually only CyToKids
			msgCode = "IE324";
			MapICSDiversionRequestRejectedKC mapICSDiversionRequestRejectedKC = 
				new MapICSDiversionRequestRejectedKC(parser, encoding);
			mapICSDiversionRequestRejectedKC.setKidsHeader(kidsHeader);
			mapICSDiversionRequestRejectedKC.setCyprusHeader(cyprusHeader);     // MS20110629
			mapICSDiversionRequestRejectedKC.setCommonFieldsDTO(commonFieldsDTO);
			xml = mapICSDiversionRequestRejectedKC.getMessage();
			break;
			
		case ICSArrivalNotification:   //EI20110708 ??? no CypernMessage???
			break;
		case ICSAdvancedInterventionNot:
			msgCode = "IE351";
			MapICSAdvancedInterventionNotKC mapICSAdvancedInterventionNotKC = 
				new MapICSAdvancedInterventionNotKC(
					parser, encoding);
			mapICSAdvancedInterventionNotKC.setKidsHeader(kidsHeader);
			mapICSAdvancedInterventionNotKC.setCyprusHeader(cyprusHeader);     // MS20110629
			mapICSAdvancedInterventionNotKC.setCommonFieldsDTO(commonFieldsDTO);
			xml = mapICSAdvancedInterventionNotKC.getMessage();
			break;			
            
		default:
			throw new FssException("Unknown message type " + msg);
		}

		return xml;
	}

	public String getMsgCode() {
		return msgCode;
	}
}

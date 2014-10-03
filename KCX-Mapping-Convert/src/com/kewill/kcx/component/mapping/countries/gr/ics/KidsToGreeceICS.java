package com.kewill.kcx.component.mapping.countries.gr.ics;

import javax.xml.stream.XMLEventReader;

import com.kewill.kcx.component.mapping.common.CommonFieldsDTO;
import com.kewill.kcx.component.mapping.countries.de.ics.EKidsICSMessages;
import com.kewill.kcx.component.mapping.countries.gr.ics.kids2gr.MapICSEntrySummaryDeclarationKG;
import com.kewill.kcx.component.mapping.countries.gr.ics.kids2gr.MapICSDeclarationAmendmentKG;
import com.kewill.kcx.component.mapping.countries.gr.ics.kids2gr.MapICSDiversionRequestKG;
import com.kewill.kcx.component.mapping.countries.gr.ics.kids2gr.MapLocalAppResultKC;
import com.kewill.kcx.component.mapping.countries.gr.ics.kids2gr.MapICSAdvancedInterventionNotKG;
import com.kewill.kcx.component.mapping.countries.gr.ics.kids2gr.MapICSDiversionRequestAcknowledgementKG;
import com.kewill.kcx.component.mapping.countries.gr.ics.kids2gr.MapICSDiversionRequestRejectedKG;
import com.kewill.kcx.component.mapping.countries.gr.ics.kids2gr.MapICSEntrySummaryDeclarationAcknowledgementKG;
import com.kewill.kcx.component.mapping.countries.gr.ics.kids2gr.MapICSEntrySummaryDeclarationAmendmentAcceptedKG;
import com.kewill.kcx.component.mapping.countries.gr.ics.kids2gr.MapICSEntrySummaryDeclarationAmendmentRejectionKG;

import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.greece.common.GreeceHeader;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;

/**
 * Module : KidsToGreeceICS<br>
 * Date Created : 08.06.2011<br>
 * Description : Converts ICS KIDS-Format to Greece messages format.
 * 
 * @author Frederick Topico
 * @version 1.0.00
 */
public class KidsToGreeceICS {
	private String msgCode = "";

	public String readKids(XMLEventReader parser, String encoding, KidsHeader kidsHeader, GreeceHeader greeceHeader,
	                                                          CommonFieldsDTO commonFieldsDTO) throws Exception {
		String xml = "";
		String msg = kidsHeader.getMessageName();

		switch (EKidsICSMessages.valueOf(msg)) {
			case ICSEntrySummaryDeclaration:
				msgCode	= "IE315";
				MapICSEntrySummaryDeclarationKG mapICSESDKG	= new MapICSEntrySummaryDeclarationKG(parser, encoding);
				mapICSESDKG.setKidsHeader(kidsHeader);
				mapICSESDKG.setGreeceHeader(greeceHeader);     
				mapICSESDKG.setCommonFieldsDTO(commonFieldsDTO);
				xml	= mapICSESDKG.getMessage();
			break;
		//EI20110803: case ICSEntrySummaryDeclarationAmendment:
			case ICSDeclarationAmendment:
				msgCode = "IE313";
				MapICSDeclarationAmendmentKG mapICSDAmendmentKG = new MapICSDeclarationAmendmentKG(parser, encoding);
				mapICSDAmendmentKG.setKidsHeader(kidsHeader);
				mapICSDAmendmentKG.setGreeceHeader(greeceHeader);  
				mapICSDAmendmentKG.setCommonFieldsDTO(commonFieldsDTO);
				xml	= mapICSDAmendmentKG.getMessage();
				break;
			case ICSDiversionRequest:
				msgCode	= "IE323";
				MapICSDiversionRequestKG mapICSDRKG	= new MapICSDiversionRequestKG(parser, encoding);
				mapICSDRKG.setKidsHeader(kidsHeader);
				mapICSDRKG.setGreeceHeader(greeceHeader);     
				mapICSDRKG.setCommonFieldsDTO(commonFieldsDTO);
				xml	= mapICSDRKG.getMessage();
				break;
				
			case localAppResult:   //EI20110708 is actually only CyToKids
				msgCode = "CD917B";			
	    		MapLocalAppResultKC mapLocalAppResultKC  = new MapLocalAppResultKC(parser, encoding); 
	    		mapLocalAppResultKC.setKidsHeader(kidsHeader);
	    		mapLocalAppResultKC.setGreeceHeader(greeceHeader);    
	    		mapLocalAppResultKC.setCommonFieldsDTO(commonFieldsDTO);
	            xml = mapLocalAppResultKC.getMessage();            
	            break;
			
	            //the other cases are not used; only in greeceToKids
			case ICSAdvancedInterventionNot:
				msgCode = "IE351";
				MapICSAdvancedInterventionNotKG mapICSAdvancedInterventionNotKG = 
					                                            new MapICSAdvancedInterventionNotKG(parser, encoding);
				mapICSAdvancedInterventionNotKG.setKidsHeader(kidsHeader);
				mapICSAdvancedInterventionNotKG.setGreeceHeader(greeceHeader);     // MS20110720
				mapICSAdvancedInterventionNotKG.setCommonFieldsDTO(commonFieldsDTO);
				xml = mapICSAdvancedInterventionNotKG.getMessage();
				break;
						
			case ICSDiversionRequestRejected:
				msgCode = "IE324";
				MapICSDiversionRequestRejectedKG mapICSDiversionRequestRejectedKG = 
				                                                new MapICSDiversionRequestRejectedKG(parser, encoding);
				mapICSDiversionRequestRejectedKG.setKidsHeader(kidsHeader);
				mapICSDiversionRequestRejectedKG.setGreeceHeader(greeceHeader);     // MS20110720
				mapICSDiversionRequestRejectedKG.setCommonFieldsDTO(commonFieldsDTO);
				xml = mapICSDiversionRequestRejectedKG.getMessage();
				break;
			case ICSDiversionRequestAcknowledgment:
				msgCode = "IE325";
				MapICSDiversionRequestAcknowledgementKG mapICSDiversionRequestAcknowledgementKG = 
					                                    new MapICSDiversionRequestAcknowledgementKG(parser, encoding);
				mapICSDiversionRequestAcknowledgementKG.setKidsHeader(kidsHeader);
				mapICSDiversionRequestAcknowledgementKG.setGreeceHeader(greeceHeader);     // MS20110720
				mapICSDiversionRequestAcknowledgementKG.setCommonFieldsDTO(commonFieldsDTO);
				xml = mapICSDiversionRequestAcknowledgementKG.getMessage();
				break;
			
			case ICSEntrySummaryDeclarationAcknowledgment:
					msgCode	= "IE328";
					MapICSEntrySummaryDeclarationAcknowledgementKG mapICSESDAKG	= 
						new MapICSEntrySummaryDeclarationAcknowledgementKG(parser, encoding);
					mapICSESDAKG.setKidsHeader(kidsHeader);
					mapICSESDAKG.setGreeceHeader(greeceHeader);     // MS20110720
					mapICSESDAKG.setCommonFieldsDTO(commonFieldsDTO);
					xml	= mapICSESDAKG.getMessage();
				break;
			
			case ICSEntrySummaryDeclarationAmendmentAcceptance:
					msgCode	= "IE304";
					MapICSEntrySummaryDeclarationAmendmentAcceptedKG mapICSESDAAKG	= 
						new MapICSEntrySummaryDeclarationAmendmentAcceptedKG(parser, encoding);
					mapICSESDAAKG.setKidsHeader(kidsHeader);
					mapICSESDAAKG.setGreeceHeader(greeceHeader);     // MS20110720
					mapICSESDAAKG.setCommonFieldsDTO(commonFieldsDTO);
					xml	= mapICSESDAAKG.getMessage();
				break;						
				
			case ICSArrivalNotification:   //AK20110909 missing specification of ArrivalMessage 
				break;
			
			case ICSEntrySummaryDeclarationAmendmentRejection:
					msgCode	= "IE305";
					MapICSEntrySummaryDeclarationAmendmentRejectionKG mapICSESDARKG	= 
						new MapICSEntrySummaryDeclarationAmendmentRejectionKG(parser, encoding);
					mapICSESDARKG.setKidsHeader(kidsHeader);
					mapICSESDARKG.setGreeceHeader(greeceHeader);     // MS20110720
					mapICSESDARKG.setCommonFieldsDTO(commonFieldsDTO);
					xml	= mapICSESDARKG.getMessage();
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

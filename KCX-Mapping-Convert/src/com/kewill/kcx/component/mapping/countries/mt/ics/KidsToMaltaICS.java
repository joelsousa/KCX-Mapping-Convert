package com.kewill.kcx.component.mapping.countries.mt.ics;

import java.io.ByteArrayOutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.common.CommonFieldsDTO;
import com.kewill.kcx.component.mapping.countries.mt.ics.kids2mt.MapICSAdvancedInterventionNotKM;
import com.kewill.kcx.component.mapping.countries.mt.ics.kids2mt.MapICSArrivalNotificationKM;
import com.kewill.kcx.component.mapping.countries.mt.ics.kids2mt.MapICSDeclarationAmendmentKM;
import com.kewill.kcx.component.mapping.countries.mt.ics.kids2mt.MapICSDiversionRequestKM;
import com.kewill.kcx.component.mapping.countries.mt.ics.kids2mt.MapICSEntrySummaryDeclarationKM;
import com.kewill.kcx.component.mapping.countries.mt.ics.kids2mt.MapLocalAppResultKM;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.formats.malta.common.MaltaHeader;

/**
 * Module : KidsToCyprusICS<br>
 * Date Created : 14.08.2013<br>
 * Description : Converts ICS KIDS-Format to Malta messages format.
 * 
 * @author Alfred Krzoska
 * @version 1.0.00
 */
public class KidsToMaltaICS {
	private String msgCode = "";

	public String readKids(XMLEventReader parser, String encoding, KidsHeader kidsHeader, 
					       MaltaHeader maltaHeader, CommonFieldsDTO commonFieldsDTO)
			throws Exception {

		String xml = null;
		String msg = kidsHeader.getMessageName();
		

		
		switch (EMaltaICSMessages.valueOf(msg)) {
		case ICSEntrySummaryDeclaration:
			msgCode = "IE315";
			MapICSEntrySummaryDeclarationKM mapICSESDKM	= new MapICSEntrySummaryDeclarationKM(parser, encoding, "");
			mapICSESDKM.setKidsHeader(kidsHeader);
			mapICSESDKM.setMaltaHeader(maltaHeader);
			mapICSESDKM.setCommonFieldsDTO(commonFieldsDTO);
			xml	= mapICSESDKM.getMessage();
			break;		
		
		case ICSDeclarationAmendment:  
			msgCode = "IE313";
			MapICSDeclarationAmendmentKM mapICSDeclarationAmendmentKM = 
				new MapICSDeclarationAmendmentKM(parser, encoding, "");
			mapICSDeclarationAmendmentKM.setKidsHeader(kidsHeader);
			mapICSDeclarationAmendmentKM.setMaltaHeader(maltaHeader);
			mapICSDeclarationAmendmentKM.setCommonFieldsDTO(commonFieldsDTO);
			xml = mapICSDeclarationAmendmentKM.getMessage();
			break;	
		case ICSDiversionRequest:
			msgCode = "IE323";
			MapICSDiversionRequestKM mapICSDiversionRequestKM = new MapICSDiversionRequestKM(parser, encoding, "");
			mapICSDiversionRequestKM.setKidsHeader(kidsHeader);
			mapICSDiversionRequestKM.setMaltaHeader(maltaHeader);
			mapICSDiversionRequestKM.setCommonFieldsDTO(commonFieldsDTO);
			xml = mapICSDiversionRequestKM.getMessage();
			break;
			
		case localAppResult:
			msgCode = "CD917B";			
    		MapLocalAppResultKM mapLocalAppResultKM  = new MapLocalAppResultKM(parser, encoding); 
    		mapLocalAppResultKM.setKidsHeader(kidsHeader);
    		mapLocalAppResultKM.setMaltaHeader(maltaHeader);    
    		mapLocalAppResultKM.setCommonFieldsDTO(commonFieldsDTO);
            xml = mapLocalAppResultKM.getMessage();            
            break;
            
		case ICSArrivalNotification:
			msgCode = "IE347";			
			MapICSArrivalNotificationKM mapICSArrivalNotificationKM  = new MapICSArrivalNotificationKM(parser, encoding, ""); 
			mapICSArrivalNotificationKM.setKidsHeader(kidsHeader);
			mapICSArrivalNotificationKM.setMaltaHeader(maltaHeader);    
			mapICSArrivalNotificationKM.setCommonFieldsDTO(commonFieldsDTO);
            xml = mapICSArrivalNotificationKM.getMessage();            

			break;
/*****                        
		case ICSEntrySummaryDeclarationAcknowledgment:  //EI20110708: is actually only CyToKids
			msgCode = "IE328";
			MapICSEntrySummaryDeclarationAcknowledgementKC mapICSEntrySummaryDeclarationAcknowledgementKC = 
				new MapICSEntrySummaryDeclarationAcknowledgementKC(parser, encoding);
			mapICSEntrySummaryDeclarationAcknowledgementKC.setKidsHeader(kidsHeader);
			mapICSEntrySummaryDeclarationAcknowledgementKC.setCyprusHeader(cyprusHeader);     // MS20110629
			mapICSEntrySummaryDeclarationAcknowledgementKC.setCommonFieldsDTO(commonFieldsDTO);
			xml = mapICSEntrySummaryDeclarationAcknowledgementKC.getMessage();
			break;
			/*********
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
****/
		case ICSAdvancedInterventionNot:
			msgCode = "IE351";
			MapICSAdvancedInterventionNotKM mapICSAdvancedInterventionNotKM = 
				new MapICSAdvancedInterventionNotKM(
					parser, encoding, "");
			mapICSAdvancedInterventionNotKM.setKidsHeader(kidsHeader);
			mapICSAdvancedInterventionNotKM.setMaltaHeader(maltaHeader);
			mapICSAdvancedInterventionNotKM.setCommonFieldsDTO(commonFieldsDTO);
			xml = mapICSAdvancedInterventionNotKM.getMessage();
			break;			
            
		default:
			throw new FssException("Unknown message type " + msg);
		}

		return xml;
	}

	public String getMsgCode() {
		return msgCode;
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

}

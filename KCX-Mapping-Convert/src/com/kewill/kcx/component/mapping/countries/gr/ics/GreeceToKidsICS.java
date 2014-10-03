package com.kewill.kcx.component.mapping.countries.gr.ics;

import java.io.ByteArrayOutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.EDirections;
import com.kewill.kcx.component.mapping.common.CommonFieldsDTO;
import com.kewill.kcx.component.mapping.countries.gr.ics.gr2kids.MapICSAdvancedInterventionNotGK;
import com.kewill.kcx.component.mapping.countries.gr.ics.gr2kids.MapICSDiversionRequestAcknowledgmentGK;
import com.kewill.kcx.component.mapping.countries.gr.ics.gr2kids.MapICSDiversionRequestRejectedGK;
import com.kewill.kcx.component.mapping.countries.gr.ics.gr2kids.MapICSEntrySummaryDeclarationAcknowledgementGK;
import com.kewill.kcx.component.mapping.countries.gr.ics.gr2kids.MapICSEntrySummaryDeclarationAmendmentAcceptedGK;
import com.kewill.kcx.component.mapping.countries.gr.ics.gr2kids.MapICSEntrySummaryDeclarationAmendmentRejectedGK;
import com.kewill.kcx.component.mapping.countries.gr.ics.gr2kids.MapICSEntrySummaryDeclarationRejectedGK;
import com.kewill.kcx.component.mapping.countries.gr.ics.gr2kids.MapLocalAppResultGK;
import com.kewill.kcx.component.mapping.db.Db;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.greece.common.GreeceHeader;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Transformer called to convert Greece-Format to KIDS ICS messages.
 * @author Frederick T.
 * @version 1.0.00
 */
public class GreeceToKidsICS {

	public String readGreece(XMLEventReader parser, String encoding, GreeceHeader greeceHeader,
							 CommonFieldsDTO commonFieldsDTO) throws Exception {                

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        XMLStreamWriter writer    = getWriter(bos, encoding);
        
        EDirections direction = commonFieldsDTO.getDirection();
        
        KidsHeader kidsHeader = new KidsHeader(writer);
        kidsHeader.setCommonFieldsDTO(commonFieldsDTO);
        kidsHeader.setMethod("ESU");     
        kidsHeader.setProcedure("ICS");  
        
        if (direction == EDirections.CustomerToCountry) {
            kidsHeader.setDirection("FROM_CUSTOMER");
        } else {
            kidsHeader.setDirection("TO_CUSTOMER");
        }
        
        //EI20110729: String msg = greeceHeader.getMesIdeMES19();
        String msg = greeceHeader.getMesTypMES20();   //EI20110729
        Utils.log("(GreeceToKidsICS readGreece) greeceHeader " + msg);
        switch (EGreeceICSMessages.valueOf(msg)) {
            case CC351A:
            	MapICSAdvancedInterventionNotGK mapICSAdvancedInterventionNotGK	= 
        			new MapICSAdvancedInterventionNotGK(parser, kidsHeader, encoding, greeceHeader);
    			prepareMessage(greeceHeader, kidsHeader, mapICSAdvancedInterventionNotGK, commonFieldsDTO, 
    					"ICSAdvancedInterventionNot", direction);
    			mapICSAdvancedInterventionNotGK.getMessage(writer);
    				break;
    				
            case CC325A:
            	MapICSDiversionRequestAcknowledgmentGK mapICSDiversionRequestAcknowledgmentGK = 
            		new MapICSDiversionRequestAcknowledgmentGK(parser, kidsHeader, 
        					encoding, greeceHeader);
            	
            	prepareMessage(greeceHeader, kidsHeader, mapICSDiversionRequestAcknowledgmentGK, 
    					commonFieldsDTO, "ICSDiversionRequestAcknowledgment", 
    					direction);
            	mapICSDiversionRequestAcknowledgmentGK.getMessage(writer);
            	break;
            	
            case CC305A:
            	MapICSEntrySummaryDeclarationAmendmentRejectedGK mapICSEntrySummaryDeclarationAmendmentRejectedGK = 
            		new MapICSEntrySummaryDeclarationAmendmentRejectedGK(parser, kidsHeader, 
        					encoding, greeceHeader);
            	
            	prepareMessage(greeceHeader, kidsHeader, mapICSEntrySummaryDeclarationAmendmentRejectedGK, 
    					commonFieldsDTO, "ICSEntrySummaryDeclarationAmendmentRejected", 
    					direction);
            	mapICSEntrySummaryDeclarationAmendmentRejectedGK.getMessage(writer);
            	break;
            	
            case CC324A:
            	MapICSDiversionRequestRejectedGK mapICSDiversionRequestRejectedGK = 
            		new MapICSDiversionRequestRejectedGK(parser, kidsHeader, 
        					encoding, greeceHeader);
            	
            	prepareMessage(greeceHeader, kidsHeader, mapICSDiversionRequestRejectedGK, 
    					commonFieldsDTO, "ICSDiversionRequestRejected", 
    					direction);
            	mapICSDiversionRequestRejectedGK.getMessage(writer);
            	break;
            	
            case CC316A:
            	MapICSEntrySummaryDeclarationRejectedGK mapICSEntrySummaryDeclarationRejectedGK = 
            		new MapICSEntrySummaryDeclarationRejectedGK(parser, kidsHeader, 
        					encoding, greeceHeader);
            	
            	prepareMessage(greeceHeader, kidsHeader, mapICSEntrySummaryDeclarationRejectedGK, 
    					commonFieldsDTO, "ICSEntrySummaryDeclarationRejected", 
    					direction);
            	mapICSEntrySummaryDeclarationRejectedGK.getMessage(writer);
            	break;	
            
            case CC304A:
            	MapICSEntrySummaryDeclarationAmendmentAcceptedGK mapICSESDAAGK	= 
            		new MapICSEntrySummaryDeclarationAmendmentAcceptedGK(parser, kidsHeader, encoding, greeceHeader);
            		prepareMessage(greeceHeader, kidsHeader, mapICSESDAAGK, 
            				commonFieldsDTO, "ICSEntrySummaryDeclarationAmendmentAccepted", direction);
            		mapICSESDAAGK.getMessage(writer);
            	break;
            
            case CC328A:
            	MapICSEntrySummaryDeclarationAcknowledgementGK mapICSESDAGK	= 
            		new MapICSEntrySummaryDeclarationAcknowledgementGK(parser, kidsHeader, encoding, greeceHeader);
//            		prepareMessage(greeceHeader, kidsHeader, mapICSESDAGK, 
//            				commonFieldsDTO, "ICSEntrySummaryDeclarationAcknowledgement", direction);
// C.K. 15.02.2012 Acknowledgment without "e"            	
            		prepareMessage(greeceHeader, kidsHeader, mapICSESDAGK, 
            				commonFieldsDTO, "ICSEntrySummaryDeclarationAcknowledgment", direction);
            		mapICSESDAGK.getMessage(writer);
            	break;
            	
            case localApp:                                             //EI20110910
            	MapLocalAppResultGK mapLocalAppResultGK	= 
            		new MapLocalAppResultGK(parser, kidsHeader, encoding, greeceHeader);
            		prepareMessage(greeceHeader, kidsHeader, mapLocalAppResultGK, 
            				commonFieldsDTO, "localAppResult", direction);
            		mapLocalAppResultGK.getMessage(writer);
            	break;	
            
            default: throw new FssException("Unknown message type " + msg);
        }
        
        String xml = bos.toString();
        
        // MS20111020 Begin
        // return xml;
        if (!alreadyReceived(kidsHeader, greeceHeader)) {
            Db.addMessageResponseHistory(kidsHeader, greeceHeader);
            commonFieldsDTO.setAlreadyReceived(false);
            return xml;
        } else {
            commonFieldsDTO.setAlreadyReceived(true);
            return null;
        }
        // MS20111020 End
	}
	
	private void prepareMessage(GreeceHeader greeceHeader, KidsHeader kidsHeader, KidsMessage message,
					CommonFieldsDTO commonFieldsDTO, String messageName, EDirections direction)
					throws XMLStreamException {
		mapGreeceToKidsHeader(greeceHeader, kidsHeader, messageName);
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

	private KidsHeader mapGreeceToKidsHeader(GreeceHeader greeceHeader, KidsHeader kidsHeader, String messageName) {
		kidsHeader.setMessageName(messageName);
		kidsHeader.setCountryCode(kidsHeader.getCommonFieldsDTO().getCountryCode());
		kidsHeader.setReceiver(kidsHeader.getCommonFieldsDTO().getKcxId());          
		kidsHeader.setTransmitter(greeceHeader.getMesSenMES3());     
			
		
		kidsHeader.setDay(greeceHeader.getDay());
		kidsHeader.setMonth(greeceHeader.getMonth());
		kidsHeader.setYear(greeceHeader.getYear());
		kidsHeader.setTime(greeceHeader.getTime());
		kidsHeader.setTimeZone(greeceHeader.getTimezone());
					
		// MS20110815 Begin
		// ist falsch und wurde sowieso schon weiter oben besetzt
		// kidsHeader.setDirection("FROM_CUSTOMER");
		// MS20110815 End
		
		//if (greeceHeader.getSchemaVersion() == null) {
		kidsHeader.setRelease("1.0.00");
		//} else if (greeceHeader.getSchemaVersion().equals("2.0")) {
		//kidsHeader.setRelease("2.0.00");  					
		//} else  { 
		//kidsHeader.setRelease("1.0.00");  		
		//}    	
		kidsHeader.setMessageID(greeceHeader.getMesIdeMES19());  
		//kidsHeader.setInReplyTo(greeceHeader.getCorIdeMES25()); //EI20110707 wird im Body nachgefuellt
		
		return kidsHeader;
	}
	
	private boolean alreadyReceived(KidsHeader kidsHeader, GreeceHeader greeceHeader) {
	    boolean result = Db.readMessageResponse(kidsHeader, greeceHeader);
	    if (result) {
	        Utils.log("(GreeceToKidsICS alreadyReceived) Nachricht wurde schon einmal empfangen.");
	    } else {
            Utils.log("(GreeceToKidsICS alreadyReceived) Nachricht wurde noch nicht empfangen.");
	    }
	    return result;
	}
	
}

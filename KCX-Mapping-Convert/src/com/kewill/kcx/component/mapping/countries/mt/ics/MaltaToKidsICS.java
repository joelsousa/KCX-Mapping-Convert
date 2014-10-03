package com.kewill.kcx.component.mapping.countries.mt.ics;

import java.io.ByteArrayOutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.EDirections;
import com.kewill.kcx.component.mapping.common.CommonFieldsDTO;
import com.kewill.kcx.component.mapping.countries.mt.ics.mt2kids.MapICSAdvancedInterventionNotMK;
import com.kewill.kcx.component.mapping.countries.mt.ics.mt2kids.MapICSArrivalNotificationValidationMK;
import com.kewill.kcx.component.mapping.countries.mt.ics.mt2kids.MapICSDiversionRequestAcknowledgementMK;
import com.kewill.kcx.component.mapping.countries.mt.ics.mt2kids.MapICSDiversionRequestRejectedMK;
import com.kewill.kcx.component.mapping.countries.mt.ics.mt2kids.MapICSEntrySummaryDeclarationAcknowledgmentMK;
import com.kewill.kcx.component.mapping.countries.mt.ics.mt2kids.MapICSEntrySummaryDeclarationAmendmentAcceptedMK;
import com.kewill.kcx.component.mapping.countries.mt.ics.mt2kids.MapICSEntrySummaryDeclarationAmendmentRejectionMK;
import com.kewill.kcx.component.mapping.countries.mt.ics.mt2kids.MapICSEntrySummaryDeclarationRejectedMK;
import com.kewill.kcx.component.mapping.countries.mt.ics.mt2kids.MapLocalAppResultMK;
import com.kewill.kcx.component.mapping.countries.mt.ics.mt2kids.MapOprnakMK;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;
import com.kewill.kcx.component.mapping.formats.malta.common.MaltaHeader;
import com.kewill.kcx.component.mapping.formats.malta.common.ics.EMaltaICSMessageTypes;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Transformer called to convert Malta-Format to KIDS ICS messages.
 * @author Alfred Krzoska
 * @version 1.0.00
 */
public class MaltaToKidsICS {

	public String readMalta(XMLEventReader parser, String encoding, MaltaHeader maltaHeader,
			CommonFieldsDTO commonFieldsDTO) throws Exception {
        String      countryCode = commonFieldsDTO.getCountryCode();
        String      kcxId       = commonFieldsDTO.getKcxId();
        EDirections direction   = commonFieldsDTO.getDirection();

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        XMLStreamWriter writer    = getWriter(bos, encoding);
        
        KidsHeader kidsHeader     = new KidsHeader(writer);
        kidsHeader.setCommonFieldsDTO(commonFieldsDTO);
        kidsHeader.setMethod("ESU");     
        kidsHeader.setProcedure("ICS");   
        
        if (direction == EDirections.CustomerToCountry) { 
            kidsHeader.setDirection("FROM_CUSTOMER");
        } else {
            kidsHeader.setDirection("TO_CUSTOMER");
        }
        
        String msg = maltaHeader.getMesTypMES20();
        Utils.log("(MaltaToKidsICS readMalta) maltaHeader " + msg);
        switch (EMaltaICSMessageTypes.valueOf(msg)) {
        case CC304A:
	    	MapICSEntrySummaryDeclarationAmendmentAcceptedMK mapICSESDAAcceptedMK = 
	    			new MapICSEntrySummaryDeclarationAmendmentAcceptedMK(parser, kidsHeader, encoding);
	    	prepareMessage(maltaHeader, kidsHeader, mapICSESDAAcceptedMK, commonFieldsDTO,
	    			"ICSEntrySummaryDeclarationAmendmentAcceptance", direction);
	    	mapICSESDAAcceptedMK.getMessage(writer);
	    	break;
	    	
        case CC305A:
        	MapICSEntrySummaryDeclarationAmendmentRejectionMK mapICSESDARejectionMK = 
        			new MapICSEntrySummaryDeclarationAmendmentRejectionMK(parser, kidsHeader, encoding);
        	prepareMessage(maltaHeader, kidsHeader, mapICSESDARejectionMK, commonFieldsDTO,
        			"ICSEntrySummaryDeclarationAmendmentRejection", direction);
        	mapICSESDARejectionMK.getMessage(writer);
        	break;   

        case CC316A:
        	MapICSEntrySummaryDeclarationRejectedMK mapICSESDRejectedMK =
        			new MapICSEntrySummaryDeclarationRejectedMK(parser, kidsHeader, encoding);
    		prepareMessage(maltaHeader, kidsHeader, mapICSESDRejectedMK, commonFieldsDTO,
    				"ICSEntrySummaryDeclarationRejected", direction);
    		mapICSESDRejectedMK.getMessage(writer);
    		break;
    		
        case CC324A:
        	MapICSDiversionRequestRejectedMK mapICSDRRejectedMK = 
        			new MapICSDiversionRequestRejectedMK(parser, kidsHeader, encoding);        			
        	prepareMessage(maltaHeader, kidsHeader, mapICSDRRejectedMK, commonFieldsDTO,
        			"ICSDiversionRequestRejected", direction);
        	mapICSDRRejectedMK.getMessage(writer);
        	break;           	       

        case CC325A:
        	MapICSDiversionRequestAcknowledgementMK mapICSDRAcknowledgementMK = 
        			new MapICSDiversionRequestAcknowledgementMK(parser, kidsHeader, encoding);
        	prepareMessage(maltaHeader, kidsHeader, mapICSDRAcknowledgementMK, commonFieldsDTO, 
        			"ICSDiversionRequestAcknowledgment", direction);
        	mapICSDRAcknowledgementMK.getMessage(writer);
        	break;
        	
        case CC328A:
        	MapICSEntrySummaryDeclarationAcknowledgmentMK mapICSESDAcknowledgmentMK =
        			new MapICSEntrySummaryDeclarationAcknowledgmentMK(parser, kidsHeader, encoding);
        	prepareMessage(maltaHeader, kidsHeader, mapICSESDAcknowledgmentMK, commonFieldsDTO,
        			"ICSEntrySummaryDeclarationAcknowledgment", direction);
        	mapICSESDAcknowledgmentMK.getMessage(writer);
        	break;
        	
        case CC348A:
        	MapICSArrivalNotificationValidationMK mapICSArrivalNotificationValidationMK =
        			new MapICSArrivalNotificationValidationMK(parser, kidsHeader, encoding);
        	prepareMessage(maltaHeader, kidsHeader, mapICSArrivalNotificationValidationMK, commonFieldsDTO,
        			"ICSArrivalNotificationValidation", direction);
        	mapICSArrivalNotificationValidationMK.getMessage(writer);
        	break;
        	
	    		
        case CC351A:
	        MapICSAdvancedInterventionNotMK mapICSAINotMK = 
	    			new MapICSAdvancedInterventionNotMK(parser, kidsHeader, encoding);	        	
    		prepareMessage(maltaHeader, kidsHeader, mapICSAINotMK, commonFieldsDTO,
    				 "ICSAdvancedInterventionNot", direction);
    		mapICSAINotMK.getMessage(writer);
        	break;
    		
        case CD917B:
   			MapLocalAppResultMK mapLocalAppResultMK = 
   					new MapLocalAppResultMK(parser, kidsHeader, encoding, maltaHeader);
            prepareMessage(maltaHeader, kidsHeader, mapLocalAppResultMK, commonFieldsDTO, 
            		"localAppResult",  direction);
            mapLocalAppResultMK.getMessage(writer);
   			break;

   	    case OPRNAK:
        	MapOprnakMK mapOprnakMK = 
   					new MapOprnakMK(parser, kidsHeader, encoding, maltaHeader);
            prepareMessage(maltaHeader, kidsHeader, mapOprnakMK, commonFieldsDTO, 
            		"localAppResult",  direction);
            mapOprnakMK.getMessage(writer);
   			break;

        /*case OPRNAK:
        	MapOprnakMK mapOprnakMK = 
   					new MapOprnakMK(parser, kidsHeader, encoding, maltaHeader);
            prepareMessage(maltaHeader, kidsHeader, mapOprnakMK, commonFieldsDTO, 
            		"ErrorMessage",  direction);
            mapOprnakMK.getMessage(writer);
   			break;
*/        case OPRACK:
   			break;


        default: throw new FssException("Unknown message type " + msg);
        }
        
        String xml = bos.toString();
        return xml;
	}
	
	private void prepareMessage(MaltaHeader maltaHeader, KidsHeader kidsHeader, KidsMessage message,
	                            CommonFieldsDTO commonFieldsDTO, String messageName, EDirections direction)
								throws XMLStreamException {
		mapMaltaToKidsHeader(maltaHeader, kidsHeader, messageName);		      
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
	
	private KidsHeader mapMaltaToKidsHeader(MaltaHeader maltaHeader, KidsHeader kidsHeader, String messageName) {
		
		kidsHeader.setMessageName(messageName);		
		kidsHeader.setCountryCode(kidsHeader.getCommonFieldsDTO().getCountryCode());  //EI20110706       
     	kidsHeader.setReceiver(kidsHeader.getCommonFieldsDTO().getKcxId());           //EI20110706
		kidsHeader.setTransmitter(maltaHeader.getMesSenMES3());      //EI20110706
    			
		kidsHeader.setDay(maltaHeader.getDay());
    	kidsHeader.setMonth(maltaHeader.getMonth());
    	kidsHeader.setYear(maltaHeader.getYear());
    	kidsHeader.setTime(maltaHeader.getTime());
    	kidsHeader.setTimeZone(maltaHeader.getTimezone());
    	    	    	 	    
		kidsHeader.setRelease("1.0.00");

    	kidsHeader.setMessageID(maltaHeader.getMesIdeMES19());   //EI20110707     	

    	return kidsHeader;
	}
}

package com.kewill.kcx.component.mapping.countries.cy.ics;

import java.io.ByteArrayOutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.EDirections;
import com.kewill.kcx.component.mapping.common.CommonFieldsDTO;
import com.kewill.kcx.component.mapping.countries.cy.ics.cy2kids.MapICSAdvancedInterventionNotCK;
import com.kewill.kcx.component.mapping.countries.cy.ics.cy2kids.MapICSDiversionRequestAcknowledgementCK;
import com.kewill.kcx.component.mapping.countries.cy.ics.cy2kids.MapICSDiversionRequestRejectedCK;
import com.kewill.kcx.component.mapping.countries.cy.ics.cy2kids.MapICSEntrySummaryDeclarationAcknowledgmentCK;
import com.kewill.kcx.component.mapping.countries.cy.ics.cy2kids.MapICSEntrySummaryDeclarationAmendmentAcceptedCK;
import com.kewill.kcx.component.mapping.countries.cy.ics.cy2kids.MapICSEntrySummaryDeclarationAmendmentRejectionCK;
import com.kewill.kcx.component.mapping.countries.cy.ics.cy2kids.MapICSEntrySummaryDeclarationRejectedCK;
import com.kewill.kcx.component.mapping.countries.cy.ics.cy2kids.MapLocalAppResultCK;
import com.kewill.kcx.component.mapping.formats.cyprus.common.CyprusHeader;
import com.kewill.kcx.component.mapping.formats.cyprus.ics.ECyprusICSMessageTypes;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Transformer called to convert Cyprus-Format to KIDS ICS messages.
 * @author Frederick T.
 * @version 1.0.00
 */
public class CyprusToKidsICS {

	public String readCyprus(XMLEventReader parser, String encoding, CyprusHeader cyprusHeader,
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
        
        String msg = cyprusHeader.getMesTypMES20();
        Utils.log("(CyprusToKidsICS readCyprus) cyprusHeader " + msg);
        switch (ECyprusICSMessageTypes.valueOf(msg)) {
        case CC328A:
        	MapICSEntrySummaryDeclarationAcknowledgmentCK mapICSESDAcknowledgmentCK =
        			new MapICSEntrySummaryDeclarationAcknowledgmentCK(parser, kidsHeader, encoding, cyprusHeader);
        	prepareMessage(cyprusHeader, kidsHeader, mapICSESDAcknowledgmentCK, commonFieldsDTO,
        			"ICSEntrySummaryDeclarationAcknowledgment", direction);
        	mapICSESDAcknowledgmentCK.getMessage(writer);
        	break;        // MS20110704
        	
        case CC316A:
        	MapICSEntrySummaryDeclarationRejectedCK mapICSESDRejectedCK =
        			new MapICSEntrySummaryDeclarationRejectedCK(parser, kidsHeader, encoding, cyprusHeader);
    		prepareMessage(cyprusHeader, kidsHeader, mapICSESDRejectedCK, commonFieldsDTO,
    				"ICSEntrySummaryDeclarationRejected", direction);
    		mapICSESDRejectedCK.getMessage(writer);
    		break;       // MS20110704
    		
        case CC304A:
	    	MapICSEntrySummaryDeclarationAmendmentAcceptedCK mapICSESDAAcceptedCK = 
	    			new MapICSEntrySummaryDeclarationAmendmentAcceptedCK(parser, kidsHeader, encoding, cyprusHeader);
	    	prepareMessage(cyprusHeader, kidsHeader, mapICSESDAAcceptedCK, commonFieldsDTO,
	    			"ICSEntrySummaryDeclarationAmendmentAcceptance", direction);
	    	mapICSESDAAcceptedCK.getMessage(writer);
	    	break;
	    	
        case CC305A:
        	MapICSEntrySummaryDeclarationAmendmentRejectionCK mapICSESDARejectionCK = 
        			new MapICSEntrySummaryDeclarationAmendmentRejectionCK(parser, kidsHeader, encoding, cyprusHeader);
        	prepareMessage(cyprusHeader, kidsHeader, mapICSESDARejectionCK, commonFieldsDTO,
        			"ICSEntrySummaryDeclarationAmendmentRejection", direction);
        	mapICSESDARejectionCK.getMessage(writer);
        	break;   
        	
        case CC325A:
        	MapICSDiversionRequestAcknowledgementCK mapICSDRAcknowledgementCK = 
        			new MapICSDiversionRequestAcknowledgementCK(parser, kidsHeader, encoding, cyprusHeader);
        	prepareMessage(cyprusHeader, kidsHeader, mapICSDRAcknowledgementCK, commonFieldsDTO, 
        			"ICSDiversionRequestAcknowledgment", direction);
        	mapICSDRAcknowledgementCK.getMessage(writer);
        	break;
        	
        case CC324A:
        	MapICSDiversionRequestRejectedCK mapICSDRRejectedCK = 
        			new MapICSDiversionRequestRejectedCK(parser, kidsHeader, encoding, cyprusHeader);        			
        	prepareMessage(cyprusHeader, kidsHeader, mapICSDRRejectedCK, commonFieldsDTO,
        			"ICSDiversionRequestRejected", direction);
        	mapICSDRRejectedCK.getMessage(writer);
        	break;           	       
	    		
        case CC351A:
	        MapICSAdvancedInterventionNotCK mapICSAINotCK = 
	    			new MapICSAdvancedInterventionNotCK(parser, kidsHeader, encoding, cyprusHeader);	        	
    		prepareMessage(cyprusHeader, kidsHeader, mapICSAINotCK, commonFieldsDTO,
    				 "ICSAdvancedInterventionNot", direction);
    		mapICSAINotCK.getMessage(writer);
        	break;
    		
        case CD917B:    //EI20110708
   			MapLocalAppResultCK mapLocalAppResultCK = 
   					new MapLocalAppResultCK(parser, kidsHeader, encoding, cyprusHeader);
            prepareMessage(cyprusHeader, kidsHeader, mapLocalAppResultCK, commonFieldsDTO, 
            		"localAppResult",  direction);
            mapLocalAppResultCK.getMessage(writer);
   			break;
   			
        default: throw new FssException("Unknown message type " + msg);
        }
        
        String xml = bos.toString();
        return xml;
	}
	
	private void prepareMessage(CyprusHeader cyprusHeader, KidsHeader kidsHeader, KidsMessage message,
	                            CommonFieldsDTO commonFieldsDTO, String messageName, EDirections direction)
								throws XMLStreamException {
		mapCyprusToKidsHeader(cyprusHeader, kidsHeader, messageName);		      
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
	
	private KidsHeader mapCyprusToKidsHeader(CyprusHeader cyprusHeader, KidsHeader kidsHeader, String messageName) {
		
		kidsHeader.setMessageName(messageName);		
		kidsHeader.setCountryCode(kidsHeader.getCommonFieldsDTO().getCountryCode());  //EI20110706       
     	kidsHeader.setReceiver(kidsHeader.getCommonFieldsDTO().getKcxId());           //EI20110706
		kidsHeader.setTransmitter(cyprusHeader.getMesSenMES3());      //EI20110706
    			
		kidsHeader.setDay(cyprusHeader.getDay());
    	kidsHeader.setMonth(cyprusHeader.getMonth());
    	kidsHeader.setYear(cyprusHeader.getYear());
    	kidsHeader.setTime(cyprusHeader.getTime());
    	kidsHeader.setTimeZone(cyprusHeader.getTimezone());
    	    	    	 	    
        // MS20110816 Begin
        // ist falsch und wurde sowieso schon weiter oben besetzt
        // kidsHeader.setDirection("FROM_CUSTOMER");
        // MS20110816 End
    	      
  		//if (cyprusHeader.getSchemaVersion() == null) {
  		//	Utils.log("(CyprusToKidsICS mapCyprusToKidsHeader) getMessageVersion liefert null!");
  			kidsHeader.setRelease("1.0.00");
  		//} else if (cyprusHeader.getSchemaVersion().equals("2.0")) {
  		//	kidsHeader.setRelease("2.0.00");  			

    	kidsHeader.setMessageID(cyprusHeader.getMesIdeMES19());   //EI20110707     	
    	//kidsHeader.setInReplyTo(cyprusHeader.getCorIdeMES25());   //EI20110707 wird im Body nachgefüllt

    	return kidsHeader;
	}
}

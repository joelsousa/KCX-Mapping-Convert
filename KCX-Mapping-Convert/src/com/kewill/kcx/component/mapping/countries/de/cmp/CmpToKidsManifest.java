package com.kewill.kcx.component.mapping.countries.de.cmp;

import javax.xml.stream.XMLEventReader;

import com.kewill.kcx.component.mapping.common.CommonFieldsDTO;
import com.kewill.kcx.component.mapping.countries.de.cmp.cmp2kids.MapCSNtoCustomsStatusNotificationCK;
import com.kewill.kcx.component.mapping.countries.de.cmp.cmp2kids.MapFFMtoNotificationOfPresentationCK;
import com.kewill.kcx.component.mapping.countries.de.cmp.cmp2kids.MapFWBtoNotificationOfPresentationConfirmedCK;
import com.kewill.kcx.component.mapping.countries.de.cmp.msg.MsgCmpCompleteData;
import com.kewill.kcx.component.mapping.countries.de.ncts20.kids2kids.MapNCTSDeclarationKK;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Module    	: Manifest/SumA
 * Created     	: 21.12.2012
 * Description 	: Transformer to convert CMP-Format to KIDS-Manifest (SUMA) messages.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class CmpToKidsManifest {

	public String readCmp(XMLEventReader parser,  
			KidsHeader kidsHeader, CommonFieldsDTO commonFieldsDTO) throws Exception {
		
		String encoding = "UTF-8";
        String xml = "";
	    //String msg = kidsHeader.getMessageName();
        String msg = "";        

        MsgCmpCompleteData cmp = new MsgCmpCompleteData(parser);        
        cmp.parse(HeaderType.KIDS);        
        if (cmp.getFlightManifestMessage() != null && !cmp.getFlightManifestMessage().isEmpty()) {        		
        	msg = "FlightManifestMessage";        
        } else if (cmp.getFreightWayBill() != null && !cmp.getFreightWayBill().isEmpty() &&
        		cmp.getFlightStatusUpdate() != null && !cmp.getFlightStatusUpdate().isEmpty()) {        	    
        	msg = "FreightWayBill"; 
        } else if (cmp.getCustomsStatusNotification() != null && !cmp.getCustomsStatusNotification().isEmpty()) {
        	msg = "CustomsStatusNotification"; 
        } else {
        	msg = "";
        }       
        Utils.log("(CmpToKidsManifest20 readCmp) MessageName = " + msg);        
        
        switch(ECmpMessages.valueOf(msg)) {
	       
	    	case FlightManifestMessage:    		    	 
	    		MapFFMtoNotificationOfPresentationCK mapNotpreCK = new MapFFMtoNotificationOfPresentationCK(cmp, encoding);
	    		kidsHeader.setMessageName("NotificationOfPresentation");
	    		mapNotpreCK.setKidsHeader(kidsHeader);
	    		mapNotpreCK.setCommonFieldsDTO(commonFieldsDTO);
    			xml = mapNotpreCK.getMessage();    		
                break;    
                
	    	case FreightWayBill:  	    	
	    		MapFWBtoNotificationOfPresentationConfirmedCK mapNotpreConfCK = new MapFWBtoNotificationOfPresentationConfirmedCK(cmp, encoding);
	    		kidsHeader.setMessageName("FreightWayBill");
	    		mapNotpreConfCK.setKidsHeader(kidsHeader);
	    		mapNotpreConfCK.setCommonFieldsDTO(commonFieldsDTO);
    			xml = mapNotpreConfCK.getMessage();    			
                break;    
	    		
              /*ist immer ein zweiter Teil von Waybill
	    	case FlightStatusUpdate:
	    	case StatusMessage:	    		
	    		break;
	    		*/
                
	    	case CustomsStatusNotification:     //EI20131216
	    		MapCSNtoCustomsStatusNotificationCK mapCsnCK = new MapCSNtoCustomsStatusNotificationCK(cmp, encoding);
	    		kidsHeader.setMessageName("CustomsStatusNotification");	    		
	    		mapCsnCK.setKidsHeader(kidsHeader);
	    		mapCsnCK.setCommonFieldsDTO(commonFieldsDTO);
    			xml = mapCsnCK.getMessage();    			
	    		break;
	    		/*
	    	case NCTSDeclaration:
	    		MapNCTSDeclarationKK mapNCTSDeclarationKK = new MapNCTSDeclarationKK(parser, "UTF-8");
	    		mapNCTSDeclarationKK.setKidsHeader(kidsHeader);
	    		xml = mapNCTSDeclarationKK.getMessage();	    		
	    		break;			
	    		*/
	    	default: throw new FssException("Unknown CMP-Message " + msg);
        }

        Utils.log("(CmpToKidsManifest) converted message = \n" + xml);
        return xml;
	}
		
}

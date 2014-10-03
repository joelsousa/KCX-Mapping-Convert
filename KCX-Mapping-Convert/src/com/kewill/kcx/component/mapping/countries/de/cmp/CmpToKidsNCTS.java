package com.kewill.kcx.component.mapping.countries.de.cmp;

import javax.xml.stream.XMLEventReader;

import com.kewill.kcx.component.mapping.common.CommonFieldsDTO;
import com.kewill.kcx.component.mapping.countries.de.cmp.cmp2kids.MapCMPtoNCTSDeclarationCK;
import com.kewill.kcx.component.mapping.countries.de.cmp.cmp2kids.MapCSNtoCustomsStatusNotificationCK;
import com.kewill.kcx.component.mapping.countries.de.cmp.msg.MsgCmpCompleteData;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Module    	: Manifest/SumA
 * Created     	: 21.12.2012
 * Description 	: Transformer to convert CMP-Format to KIDS-NCTS messages.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class CmpToKidsNCTS {

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
        } else if (cmp.getCustomsStatusNotification() != null && !cmp.getCustomsStatusNotification().isEmpty()) {
        	msg = "CustomsStatusNotification"; 
        } else {
        	msg = "";
        }       
        
        Utils.log("(CmpToKidsNCTS readCmp) MessageName = " + msg);
        Utils.log("(IWA-CmpToKidsNCTS readCmp) CommonFieldsDTO = " + commonFieldsDTO);
        
        switch(ECmpMessages.valueOf(msg)) {
	       
	    	case FlightManifestMessage:    
	    		//MapNotificationOfPresentationCK mapNotpreCK = new MapNotificationOfPresentationCK(parser, encoding);
	    		MapCMPtoNCTSDeclarationCK mapNctsCK = new MapCMPtoNCTSDeclarationCK(cmp, encoding);
	    		kidsHeader.setMessageName("NCTSDeclaration");
	    		mapNctsCK.setKidsHeader(kidsHeader);
	    		mapNctsCK.setCommonFieldsDTO(commonFieldsDTO);
    			xml = mapNctsCK.getMessage();    			
                break;    
	    	case CustomsStatusNotification:     //EI20140206
	    		MapCSNtoCustomsStatusNotificationCK mapCsnCK = new MapCSNtoCustomsStatusNotificationCK(cmp, encoding);
	    		kidsHeader.setMessageName("CustomsStatusNotification");	    		
	    		mapCsnCK.setKidsHeader(kidsHeader);
	    		mapCsnCK.setCommonFieldsDTO(commonFieldsDTO);
    			xml = mapCsnCK.getMessage();    			
	    		break;
	    		
	    	default: throw new FssException("Unknown CMP-Message " + msg);
        }

        Utils.log("(CmpToKidsNCTS readCmp) converted message = \n" + xml);
        Utils.log("(CmpToKidsNCTS readCmp) CommonFieldsDTO = " + commonFieldsDTO);
        return xml;
	}	
}

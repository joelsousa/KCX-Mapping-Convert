package com.kewill.kcx.component.mapping.countries.de.ncts20;

import javax.xml.stream.XMLEventReader;

import com.kewill.kcx.component.mapping.common.CommonFieldsDTO;
import com.kewill.kcx.component.mapping.countries.de.ncts.EKidsNCTSMessages;
import com.kewill.kcx.component.mapping.countries.de.ncts20.kids2kids.MapNCTSDeclarationKK;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;

/** 
 * Module 		: NCTS OUT<br>
 * Created 		: 13.02.2013<br>
 * Description 	: transformer to convert NCTS KIDS-Format to KIDS messages.
 * 
 * @author iwaniuk
 * @version 4.1.00
 */

public class KidsToKidsNCTS41 {

	public String readKids(XMLEventReader parser, KidsHeader kidsHeader,
    		CommonFieldsDTO commonFieldsDTO) throws Exception {
	    String encoding = "UTF-8";
        String xml = "";
	    String msg = kidsHeader.getMessageName();
	    
	    switch (EKidsNCTSMessages.valueOf(msg)) {
	    	case NCTSDeclaration:
	    		MapNCTSDeclarationKK mapNCTSDeclarationKK = new MapNCTSDeclarationKK(parser, "UTF-8");
	    		mapNCTSDeclarationKK.setKidsHeader(kidsHeader);
	    		xml = mapNCTSDeclarationKK.getMessage();
	    		break;
			
		    case ArrivalNotification:	    		
	    		break;
	    		
	    	case MRNAllocated:	    		
	    		break;
	    		
	    	case ArrivalRejection:	    		
	    		break;
	    		
	    	case NCTSDeclarationRejected:	    		
	    		break;
	    		
	    	case UnloadingPermission:	    		
	    		break;
	    		
	    	default: throw new FssException("Unknown message type " + msg);
	    }
	    
	    return xml;
    }
}

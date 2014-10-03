package com.kewill.kcx.component.mapping.countries.de.ncts;

import javax.xml.stream.XMLEventReader;

import com.kewill.kcx.component.mapping.common.CommonFieldsDTO;
import com.kewill.kcx.component.mapping.countries.de.ncts.kids2kids.MapNCTSDeclarationKK;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.util.Utils;

/*
 * Function    : KidsToKids.java
 * Title       :
 * Date        : 23.08.2010
 * Author      : Frederick T
 * Description : transformer called to convert KIDS-Format to KIDS messages
 * 			   : 
 * Parameters  : 
 */

/**
 * Module       : KidsToKidsICS<br>
 * Created 		: 23.08.2010<br>
 * Description  : transformer to convert NCTS KIDS-Format to KIDS messages.
 * 
 * @author Frederick T
 * @version 1.0.00
 */
public class KidsToKidsNCTS {

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

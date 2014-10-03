package com.kewill.kcx.component.mapping.countries.de.Import;

/*
 * Function    : KidsToKids.java
 * Titel       :
 * Date        : 14.06.2010
 * Author      : Pete T
 * Description : transformer called to convert KIDS-Format to KIDS messages
 * 			   :
 * Parameters  :
 */

import javax.xml.stream.XMLEventReader;

import com.kewill.kcx.component.mapping.common.CommonFieldsDTO;
import com.kewill.kcx.component.mapping.countries.de.Import.kids2kids.MapImportDeclarationKK;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;


/**
 * Module       : KidsToKidsICS<br>
 * Created 		: 14.06.2010<br>
 * Description  : transformer to convert ICS KIDS-Format to KIDS messages.
 *
 * @author Pete T
 * @version 1.0.00
 */
public class KidsToKids64Import {

    public String readKids(XMLEventReader parser, KidsHeader kidsHeader,
    		CommonFieldsDTO commonFieldsDTO) throws Exception {
	    String encoding = "UTF-8";
        String xml = "";
	    String msg = kidsHeader.getMessageName();

        switch (EKidsImportMessages.valueOf(msg)) {
        
        	case ImportDeclaration:
        		MapImportDeclarationKK mapImportDeclarationKK = new MapImportDeclarationKK(parser, encoding);
        		mapImportDeclarationKK.setKidsHeader(kidsHeader);
        		mapImportDeclarationKK.setCommonFieldsDTO(commonFieldsDTO);
    			xml = mapImportDeclarationKK.getMessage();
    			break;
    	
        	//case ImportDeclarationConfirmation:        		
            //    break;                        
    			
           default: throw new FssException("Unknown Import Kids2Kids message type: " + msg);
        }

        return xml;
    }

}

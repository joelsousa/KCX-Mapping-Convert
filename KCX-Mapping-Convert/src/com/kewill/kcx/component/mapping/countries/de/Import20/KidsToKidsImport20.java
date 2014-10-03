package com.kewill.kcx.component.mapping.countries.de.Import20;

import javax.xml.stream.XMLEventReader;

import com.kewill.kcx.component.mapping.common.CommonFieldsDTO;
import com.kewill.kcx.component.mapping.countries.de.Import.EKidsImportMessages;
import com.kewill.kcx.component.mapping.countries.de.Import20.kids2kids.MapImportDeclarationConfirmationKK;
import com.kewill.kcx.component.mapping.countries.de.Import20.kids2kids.MapImportDeclarationKK;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;


/**
 * Module       : Import 20<br>
 * Created 		: 12.11.2012<br>
 * Description  : transformer to convert ICS KIDS-Format to KIDS messages.
 *
 * @author iwaniuk
 * @version 2.0.00
 */
public class KidsToKidsImport20 {

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
    	
        	case ImportDeclarationConfirmation:    
        		MapImportDeclarationConfirmationKK mapImportDeclConKK = new MapImportDeclarationConfirmationKK(parser, encoding);
        		mapImportDeclConKK.setKidsHeader(kidsHeader);
        		mapImportDeclConKK.setCommonFieldsDTO(commonFieldsDTO);
    			xml = mapImportDeclConKK.getMessage();
                break;                        
    			
           default: throw new FssException("Unknown Import Kids2Kids message type: " + msg);
        }

        return xml;
    }

}

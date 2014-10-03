package com.kewill.kcx.component.mapping.countries.de.Port;

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
import com.kewill.kcx.component.mapping.countries.de.Port.kids2kids.MapPortDeclarationKK;
import com.kewill.kcx.component.mapping.countries.de.Port.kids2kids.MapPortDeclarationRegistrationKK;
import com.kewill.kcx.component.mapping.countries.de.Port.kids2kids.MapPortDeclarationStatusKK;
import com.kewill.kcx.component.mapping.countries.de.aes.kids2kids.MapErrInfKK;
import com.kewill.kcx.component.mapping.countries.de.aes.kids2kids.MapLocalAppKK;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;


/**
 * Module       : KidsToKidsICS<br>
 * Created 		: 09.11.2011<br>
 * Description  : transformer to convert Port KIDS-Format to KIDS messages.
 *
 * @author iwaniuk
 * @version 1.0.00
 */
public class KidsToKidsPort {

    public String readKids(XMLEventReader parser, KidsHeader kidsHeader,
    		CommonFieldsDTO commonFieldsDTO) throws Exception {
	    String encoding = "UTF-8";
        String xml = "";
	    String msg = kidsHeader.getMessageName();
	   
        switch (EKidsPortMessages.valueOf(msg)) {
        
        	case PortDeclaration:
        		MapPortDeclarationKK mapPortDeclarationKK = new MapPortDeclarationKK(parser, encoding);
        		mapPortDeclarationKK.setKidsHeader(kidsHeader);
        		mapPortDeclarationKK.setCommonFieldsDTO(commonFieldsDTO);
    			xml = mapPortDeclarationKK.getMessage();
    			break;
    	
        	case PortDeclarationStatus:
        		MapPortDeclarationStatusKK mapPortStatusKK  = new MapPortDeclarationStatusKK(parser, encoding); //EI20120207
        		mapPortStatusKK.setKidsHeader(kidsHeader);
        		mapPortStatusKK.setCommonFieldsDTO(commonFieldsDTO);
                xml = mapPortStatusKK.getMessage();
                break;
                
        	case PortDeclarationRegistration:
        		MapPortDeclarationRegistrationKK mapPortRegistrationKK  = new MapPortDeclarationRegistrationKK(parser, encoding); //EI20120207
        		mapPortRegistrationKK.setKidsHeader(kidsHeader);
        		mapPortRegistrationKK.setCommonFieldsDTO(commonFieldsDTO);
                xml = mapPortRegistrationKK.getMessage();
                break;
                
        	case localAppResult:   
        		//EI20120207: MapFailureKK mapFailureKK  = new MapFailureKK(parser, encoding);
        		MapLocalAppKK mapFailureKK  = new MapLocalAppKK(parser, encoding); //EI20120207
        		mapFailureKK.setKidsHeader(kidsHeader);
        		mapFailureKK.setCommonFieldsDTO(commonFieldsDTO);
                xml = mapFailureKK.getMessage();
                break;  
                
        	case ErrorMessage:   
        		//EI20120207: MapFailureKK mapFailureKK  = new MapFailureKK(parser, encoding);
        		MapErrInfKK mapErrorKK  = new MapErrInfKK(parser, encoding); //EI20120207        		
        		mapErrorKK.setKidsHeader(kidsHeader);
        		mapErrorKK.setCommonFieldsDTO(commonFieldsDTO);
                xml = mapErrorKK.getMessage();
                break;
    			
           default: throw new FssException("Unknown message type " + msg);
        }

        return xml;
    }

}

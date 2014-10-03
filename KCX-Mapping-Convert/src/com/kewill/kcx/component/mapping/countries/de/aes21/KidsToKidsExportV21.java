package com.kewill.kcx.component.mapping.countries.de.aes21;

import javax.xml.stream.XMLEventReader;

import com.kewill.kcx.component.mapping.EDirections;
import com.kewill.kcx.component.mapping.common.CommonFieldsDTO;
import com.kewill.kcx.component.mapping.countries.de.aes.EKidsMessages;
import com.kewill.kcx.component.mapping.countries.de.aes.kids2kids.MapCHReverseDeclarationKK;
import com.kewill.kcx.component.mapping.countries.de.aes.kids2kids.MapConfirmKK;
import com.kewill.kcx.component.mapping.countries.de.aes.kids2kids.MapErrInfKK;
import com.kewill.kcx.component.mapping.countries.de.aes.kids2kids.MapExpConKK;
import com.kewill.kcx.component.mapping.countries.de.aes.kids2kids.MapExpErlKK;
import com.kewill.kcx.component.mapping.countries.de.aes.kids2kids.MapExpNckKK;
import com.kewill.kcx.component.mapping.countries.de.aes.kids2kids.MapFailureKK;
import com.kewill.kcx.component.mapping.countries.de.aes.kids2kids.MapLocalAppKK;
import com.kewill.kcx.component.mapping.countries.de.aes21.kids2kids.MapControlNotificationKK;
import com.kewill.kcx.component.mapping.countries.de.aes21.kids2kids.MapExitAuthorisationKK;
import com.kewill.kcx.component.mapping.countries.de.aes21.kids2kids.MapExitPresentationKK;
import com.kewill.kcx.component.mapping.countries.de.aes21.kids2kids.MapExpAmdKK;
import com.kewill.kcx.component.mapping.countries.de.aes21.kids2kids.MapExpCanKK;
import com.kewill.kcx.component.mapping.countries.de.aes21.kids2kids.MapExpDatKK;
import com.kewill.kcx.component.mapping.countries.de.aes21.kids2kids.MapExpEntKK;
import com.kewill.kcx.component.mapping.countries.de.aes21.kids2kids.MapExpExtKK;
import com.kewill.kcx.component.mapping.countries.de.aes21.kids2kids.MapExpFupKK;
import com.kewill.kcx.component.mapping.countries.de.aes21.kids2kids.MapExpIndKK;
import com.kewill.kcx.component.mapping.countries.de.aes21.kids2kids.MapExpRelKK;
import com.kewill.kcx.component.mapping.countries.de.aes21.kids2kids.MapExpStaKK;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;

/**
 * Module      : KidsToKids<br>
 * Created     : 26.07.2012<br>
 * Description : Transformer called by Mule to convert KIDS-Format to KIDS
 * messages.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class KidsToKidsExportV21  {
    
	public String readKids(XMLEventReader parser, KidsHeader kidsHeader, CommonFieldsDTO commonFieldsDTO)
			throws Exception {

        String xml = null;
	    String msg = kidsHeader.getMessageName();
	    kidsHeader.setCommonFieldsDTO(commonFieldsDTO);
		switch (EKidsMessages.valueOf(msg)) {
    		case Confirmation:
    			MapExpConKK mapExpConKK = new MapExpConKK(parser, "UTF-8");
    			mapExpConKK.setKidsHeader(kidsHeader);
    			xml = mapExpConKK.getMessage();
    			break;
    		case InternalStatusInformation:
    			MapExpNckKK mapExpNckKK = new MapExpNckKK(parser, "UTF-8");
    			mapExpNckKK.setKidsHeader(kidsHeader);
    			xml = mapExpNckKK.getMessage();
    			break;
    		case Cancellation:
    			MapExpCanKK mapExpCanKK = new MapExpCanKK(parser, "UTF-8");
    			mapExpCanKK.setKidsHeader(kidsHeader);
    			xml = mapExpCanKK.getMessage();
    			break;
    		case Confirm:
    			MapConfirmKK mapConfirmKK = new MapConfirmKK(parser, "UTF-8");
    			mapConfirmKK.setKidsHeader(kidsHeader);
    			xml = mapConfirmKK.getMessage();
    			break;
    		case Failure:
    			MapFailureKK mapfailureKK = new MapFailureKK(parser, "UTF-8");
    			mapfailureKK.setKidsHeader(kidsHeader);
    			xml = mapfailureKK.getMessage();
    			break;
    		case ExportDeclaration:
    			MapExpDatKK mapExpDatKK = new MapExpDatKK(parser, "UTF-8");
    			mapExpDatKK.setKidsHeader(kidsHeader);
    			xml = mapExpDatKK.getMessage();
    			break;
    		case Amendment:
    			MapExpAmdKK mapExpAmdKK = new MapExpAmdKK(parser, "UTF-8");
    			mapExpAmdKK.setKidsHeader(kidsHeader);
    			xml = mapExpAmdKK.getMessage();
    			break;
    		case ReverseDeclaration:
    			kidsHeader.setMapping(EDirections.CountryToCustomer);
    			if (kidsHeader.getCountryCode().equalsIgnoreCase("CH")) {
    				MapCHReverseDeclarationKK mapCHReverseDeclarationKK = 
    				                    new MapCHReverseDeclarationKK(parser, "UTF-8");
    				mapCHReverseDeclarationKK.setKidsHeader(kidsHeader);
    				xml = mapCHReverseDeclarationKK.getMessage();
    			} else {
    				MapExpRelKK mapReverseDeclarationKK = new MapExpRelKK(parser, "UTF-8");
    				mapReverseDeclarationKK.setKidsHeader(kidsHeader);
    				mapReverseDeclarationKK.setCommonFieldsDTO(commonFieldsDTO);
    				xml = mapReverseDeclarationKK.getMessage();
    		        xml = xml.replaceAll("><", ">\n  <");
    			}
    			break;
    		case ErrorMessage:
    			MapErrInfKK mapErrMessKK = new MapErrInfKK(parser, "UTF-8");
    			mapErrMessKK.setKidsHeader(kidsHeader);
    			xml = mapErrMessKK.getMessage();
    			break;
    		case DeclarationResponse:
    			MapExpStaKK mapExpStaKK = new MapExpStaKK(parser, "UTF-8");
    			mapExpStaKK.setKidsHeader(kidsHeader);
    			xml = mapExpStaKK.getMessage();
    			break;
    		case Completion:
    			MapExpEntKK mapExpEntKK = new MapExpEntKK(parser, "UTF-8");
    			mapExpEntKK.setKidsHeader(kidsHeader);
    			xml = mapExpEntKK.getMessage();
    			break;
    		case ManualTermination:
    			MapExpErlKK mapExpErlKK = new MapExpErlKK(parser, "UTF-8");
    			mapExpErlKK.setKidsHeader(kidsHeader);
    			xml = mapExpErlKK.getMessage();
    			break;
    		case localAppResult:
    			//EI20120207: MapFailureKK mapFailureKK = new MapFailureKK(parser, "UTF-8");
    			MapLocalAppKK mapFailureKK = new MapLocalAppKK(parser, "UTF-8"); //EI20120207
    			mapFailureKK.setKidsHeader(kidsHeader);
    			xml = mapFailureKK.getMessage();    			
    			break;
    		case Investigation:
    			MapExpFupKK mapExpFupKK = new MapExpFupKK(parser, "UTF-8");
    			mapExpFupKK.setKidsHeader(kidsHeader);
    			xml = mapExpFupKK.getMessage();
    			break;
    		case ConfirmInvestigation:
    			MapExpExtKK mapExpExtKK = new MapExpExtKK(parser, "UTF-8");
    			mapExpExtKK.setKidsHeader(kidsHeader);
    			xml = mapExpExtKK.getMessage();
    			break;
    		case PreNotification:
    			MapExpIndKK mapExpIndKK = new MapExpIndKK(parser, "UTF-8");
    			mapExpIndKK.setKidsHeader(kidsHeader);
    			xml = mapExpIndKK.getMessage();
    			break;
    		case ExitPresentation:                       //EI20120706
    			MapExitPresentationKK mapExitPresentationKK = new MapExitPresentationKK(parser, "UTF-8");
    			mapExitPresentationKK.setKidsHeader(kidsHeader);
    			xml = mapExitPresentationKK.getMessage();
    			break;
    		case ControlNotification:    				//EI20130722
    		case ExportControlNotification:  			//EI20131202
    			MapControlNotificationKK mapCtlNotKK = new MapControlNotificationKK(parser, "UTF-8");
    			mapCtlNotKK.setKidsHeader(kidsHeader);
    			xml = mapCtlNotKK.getMessage();
    			break; 
    		case ExitAuthorisation:  					//EI20130810
    		case ExitAuthorization:
    			MapExitAuthorisationKK mapExtAuthoKK = new MapExitAuthorisationKK(parser, "UTF-8");
    			mapExtAuthoKK.setKidsHeader(kidsHeader);
    			xml = mapExtAuthoKK.getMessage();
    			break; 
    		default:
    			throw new FssException("Unknown message type " + msg);
		}
		
		return xml;
	}

}

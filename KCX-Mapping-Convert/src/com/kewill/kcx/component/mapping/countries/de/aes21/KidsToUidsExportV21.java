package com.kewill.kcx.component.mapping.countries.de.aes21;

import javax.xml.stream.XMLEventReader;

import com.kewill.kcx.component.mapping.common.CommonFieldsDTO;
import com.kewill.kcx.component.mapping.conf.Config;
import com.kewill.kcx.component.mapping.countries.ch.aus.kids2uids.MapCHReverseDeclarationKU;
import com.kewill.kcx.component.mapping.countries.de.aes.EKidsMessages;
import com.kewill.kcx.component.mapping.countries.de.aes.kids2uids.MapExpNckKU;
import com.kewill.kcx.component.mapping.countries.de.aes.kids2uids.MapFailureKU;
import com.kewill.kcx.component.mapping.countries.de.aes21.kids2uids.MapControlNotificationKU;
import com.kewill.kcx.component.mapping.countries.de.aes21.kids2uids.MapErrInfKU;
import com.kewill.kcx.component.mapping.countries.de.aes21.kids2uids.MapExitAuthorisationKU;
import com.kewill.kcx.component.mapping.countries.de.aes21.kids2uids.MapExpAmdKU;
import com.kewill.kcx.component.mapping.countries.de.aes21.kids2uids.MapExpCanKU;
import com.kewill.kcx.component.mapping.countries.de.aes21.kids2uids.MapExpConKU;
import com.kewill.kcx.component.mapping.countries.de.aes21.kids2uids.MapExpDatKU;
import com.kewill.kcx.component.mapping.countries.de.aes21.kids2uids.MapExpEntKU;
import com.kewill.kcx.component.mapping.countries.de.aes21.kids2uids.MapExpErlKU;
import com.kewill.kcx.component.mapping.countries.de.aes21.kids2uids.MapExpExtKU;
import com.kewill.kcx.component.mapping.countries.de.aes21.kids2uids.MapExpFupKU;
import com.kewill.kcx.component.mapping.countries.de.aes21.kids2uids.MapExpIndKU;
import com.kewill.kcx.component.mapping.countries.de.aes21.kids2uids.MapExpRelKU;
import com.kewill.kcx.component.mapping.countries.de.aes21.kids2uids.MapExpStaKU;
import com.kewill.kcx.component.mapping.countries.de.aes21.kids2uids.MapExpUrgKU;
import com.kewill.kcx.component.mapping.countries.de.aes21.kids2uids.MapExtNotKU;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlPdfTgzIntern2;


/**
 * Module       : KidsToUidsExport<br>
 * Created 		: 24.07.2012<br>
 * Description  : transformer to convert EXPORT KIDS-Format to UIDS messages.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class KidsToUidsExportV21 {
    
    public Object readKids(XMLEventReader parser, String encoding, KidsHeader kidsHeader, 
                                                        CommonFieldsDTO commonFieldsDTO) throws Exception {
        String xml = "";
        Object tgz = null;
        boolean returnTgz = false;
        //EI20120917: hier kann man NameSpace fuer alle Bodies festlegen (ab aes21)
        commonFieldsDTO.setNameSpaceText("http://www.eurtradenet.com/schemas/uids/export/body/200809");
        
	    String msg = kidsHeader.getMessageName();
        switch (EKidsMessages.valueOf(msg)) {
        	case PreNotification: 
        		MapExpIndKU mapExpIndKU = new MapExpIndKU(parser, encoding);
        		mapExpIndKU.setKidsHeader(kidsHeader);
        		mapExpIndKU.setCommonFieldsDTO(commonFieldsDTO);
        		xml = mapExpIndKU.getMessage();
        		break;
        	case ExportDeclaration:
                MapExpDatKU mapExpDatKU = new MapExpDatKU(parser, encoding);
                mapExpDatKU.setKidsHeader(kidsHeader);
                mapExpDatKU.setCommonFieldsDTO(commonFieldsDTO);
                xml = mapExpDatKU.getMessage();
                break; 
        	case Amendment:
        		MapExpAmdKU mapExpAmdKU  = new MapExpAmdKU(parser, encoding);
        		mapExpAmdKU.setKidsHeader(kidsHeader);
        		mapExpAmdKU.setCommonFieldsDTO(commonFieldsDTO);
        		xml = mapExpAmdKU.getMessage();
        		break;
        	case Completion:
                MapExpEntKU mapExpEntKU = new MapExpEntKU(parser, encoding);
                mapExpEntKU.setKidsHeader(kidsHeader);
                mapExpEntKU.setCommonFieldsDTO(commonFieldsDTO);
                xml = mapExpEntKU.getMessage();
                break;
        	case Cancellation:
                MapExpCanKU mapExpCanKU = new MapExpCanKU(parser, encoding);
                mapExpCanKU.setKidsHeader(kidsHeader);
                mapExpCanKU.setCommonFieldsDTO(commonFieldsDTO);
                xml = mapExpCanKU.getMessage();
                break;	
        	case ManualTermination:
                MapExpErlKU mapExpErlKU = new MapExpErlKU(parser, encoding);
                mapExpErlKU.setKidsHeader(kidsHeader);
                mapExpErlKU.setCommonFieldsDTO(commonFieldsDTO);
                xml = mapExpErlKU.getMessage();
                break;         
           case ReverseDeclaration:    
               if (kidsHeader.getCountryCode().equalsIgnoreCase("CH")) {
                   MapCHReverseDeclarationKU mapCHReverseDeclarationKU = 
                                                   new MapCHReverseDeclarationKU(parser, encoding);
                   mapCHReverseDeclarationKU.setKidsHeader(kidsHeader);
                   mapCHReverseDeclarationKU.setCommonFieldsDTO(commonFieldsDTO);
                   xml = mapCHReverseDeclarationKU.getMessage();
        	   } else {
        		   MapExpRelKU mapReverseDeclarationKU = new MapExpRelKU(parser, encoding);
        		   mapReverseDeclarationKU.setKidsHeader(kidsHeader);
        		   mapReverseDeclarationKU.setCommonFieldsDTO(commonFieldsDTO);
        		   xml = mapReverseDeclarationKU.getMessage();
        		   if (commonFieldsDTO.isPdfTgz() && commonFieldsDTO.pdfExists()) {
        			   Utils.log("es wird ein tgz generiert!!!");
        			   returnTgz = true;
                       tgz = XmlPdfTgzIntern2.createTgz(xml, Config.getPdfpath(), commonFieldsDTO.getFilename());
        			   commonFieldsDTO.updateFilename();
        		   }
        	   }
               break;
           case DeclarationResponse:
       	    	MapExpStaKU mapExpStaKU = new MapExpStaKU(parser, encoding);
       	    	mapExpStaKU.setKidsHeader(kidsHeader);
       	    	mapExpStaKU.setCommonFieldsDTO(commonFieldsDTO);
       	    	xml = mapExpStaKU.getMessage();
       	    	break;
          case Confirmation:  
              	MapExpConKU mapExpConKU = new MapExpConKU(parser, encoding);
              	mapExpConKU.setKidsHeader(kidsHeader);
              	mapExpConKU.setCommonFieldsDTO(commonFieldsDTO);
              	xml = mapExpConKU.getMessage();
              	break;
              	
           case ErrorMessage:
         	    MapErrInfKU mapErrMessKU = new MapErrInfKU(parser, encoding);
        	    mapErrMessKU.setKidsHeader(kidsHeader);
        	    mapErrMessKU.setCommonFieldsDTO(commonFieldsDTO);
        	    xml = mapErrMessKU.getMessage();
        	    break;           
          case InternalStatusInformation:
             	MapExpNckKU mapExpNckKU = new MapExpNckKU(parser, encoding);
               mapExpNckKU.setKidsHeader(kidsHeader);
               mapExpNckKU.setCommonFieldsDTO(commonFieldsDTO);
               xml = mapExpNckKU.getMessage();
               break;            
           case localAppResult:
        	   MapFailureKU mapFailureKU  = new MapFailureKU(parser, encoding);
        	   mapFailureKU.setKidsHeader(kidsHeader);
        	   mapFailureKU.setCommonFieldsDTO(commonFieldsDTO);
               xml = mapFailureKU.getMessage();
               break;
           
           case Investigation:
        	   MapExpFupKU mapExpFupKU  = new MapExpFupKU(parser, encoding);
        	   mapExpFupKU.setKidsHeader(kidsHeader);
        	   mapExpFupKU.setCommonFieldsDTO(commonFieldsDTO);
               xml = mapExpFupKU.getMessage();
               break;
           case ConfirmInvestigation:
        	   MapExpExtKU mapExpExtKU  = new MapExpExtKU(parser, encoding);
        	   mapExpExtKU.setKidsHeader(kidsHeader);
        	   mapExpExtKU.setCommonFieldsDTO(commonFieldsDTO);
               xml = mapExpExtKU.getMessage();
               break;            	            
           case ExitNotification:     //new for V21
        	   MapExtNotKU mapExpNotKU  = new MapExtNotKU(parser, encoding);
        	   mapExpNotKU.setKidsHeader(kidsHeader);
        	   mapExpNotKU.setCommonFieldsDTO(commonFieldsDTO);
               xml = mapExpNotKU.getMessage();
      			break;
           case ExportReminder:           //EI20120914 new for V21
               MapExpUrgKU mapExpUrgKU = new MapExpUrgKU(parser, encoding);
               mapExpUrgKU.setKidsHeader(kidsHeader);
               mapExpUrgKU.setCommonFieldsDTO(commonFieldsDTO);
               xml = mapExpUrgKU.getMessage();
               break; 
           case ControlNotification:     //EI20130722
           case ExportControlNotification:  //EI20131202
        	   MapControlNotificationKU mapCtlNotKU = new MapControlNotificationKU(parser, encoding);
        	   mapCtlNotKU.setKidsHeader(kidsHeader);
        	   mapCtlNotKU.setCommonFieldsDTO(commonFieldsDTO);
               xml = mapCtlNotKU.getMessage();
               break;
           case ExitAuthorisation:    //EI20130810
   		   case ExitAuthorization:
        	   MapExitAuthorisationKU mapExtAuthoKU = new MapExitAuthorisationKU(parser, encoding);
        	   mapExtAuthoKU.setKidsHeader(kidsHeader);
        	   mapExtAuthoKU.setCommonFieldsDTO(commonFieldsDTO);
               xml = mapExtAuthoKU.getMessage();
   			   break;
           default: 
        	   throw new FssException("Unknown message type " + msg);
        }
        
        if (returnTgz) {
            return tgz;
        } else {
            return xml;
        }
    }
    
}

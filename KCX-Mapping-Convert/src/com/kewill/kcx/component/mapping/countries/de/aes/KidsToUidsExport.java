package com.kewill.kcx.component.mapping.countries.de.aes;

/*
 * Function    : KidsToUids.java
 * Titel       :
 * Date        : 02.09.2008
 * Author      : Kewill CSF / Christine Kron
 * Description : transformer called by Mule 
 * 				 to convert KIDS-Format to UIDS messages
 * 			   : 
 * Parameters  : 

 * Changes 
 * -----------
 * Author      : Sven Heise
 * Date        : 08.09.2008
 * Label       : 
 * Description : InternalStatusInformation, Cancellation, Completion (25.09.08)
 *
 */

import javax.xml.stream.XMLEventReader;

import com.kewill.kcx.component.mapping.common.CommonFieldsDTO;
import com.kewill.kcx.component.mapping.conf.Config;
import com.kewill.kcx.component.mapping.countries.ch.aus.kids2uids.MapCHReverseDeclarationKU;
import com.kewill.kcx.component.mapping.countries.de.aes.kids2uids.MapConfirmKU;
import com.kewill.kcx.component.mapping.countries.de.aes.kids2uids.MapErrInfKU;
import com.kewill.kcx.component.mapping.countries.de.aes.kids2uids.MapExpAmdKU;
import com.kewill.kcx.component.mapping.countries.de.aes.kids2uids.MapExpCanKU;
import com.kewill.kcx.component.mapping.countries.de.aes.kids2uids.MapExpConKU;
import com.kewill.kcx.component.mapping.countries.de.aes.kids2uids.MapExpDatKU;
import com.kewill.kcx.component.mapping.countries.de.aes.kids2uids.MapExpEntKU;
import com.kewill.kcx.component.mapping.countries.de.aes.kids2uids.MapExpErlKU;
import com.kewill.kcx.component.mapping.countries.de.aes.kids2uids.MapExpExtKU;
import com.kewill.kcx.component.mapping.countries.de.aes.kids2uids.MapExpFupKU;
import com.kewill.kcx.component.mapping.countries.de.aes.kids2uids.MapExpIndKU;
import com.kewill.kcx.component.mapping.countries.de.aes.kids2uids.MapExpNckKU;
import com.kewill.kcx.component.mapping.countries.de.aes.kids2uids.MapExpRelKU;
import com.kewill.kcx.component.mapping.countries.de.aes.kids2uids.MapExpStaKU;
import com.kewill.kcx.component.mapping.countries.de.aes.kids2uids.MapFailureKU;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlPdfTgzIntern2;


/**
 * Module       : KidsToUidsExport<br>
 * Created 		: 05.05.2010<br>
 * Description  : transformer to convert EXPORT KIDS-Format to UIDS messages.
 * 
 * @author schmidt
 * @version 1.0.00
 */
public class KidsToUidsExport {
    
    public Object readKids(XMLEventReader parser, String encoding, KidsHeader kidsHeader, 
                                                        CommonFieldsDTO commonFieldsDTO) throws Exception {
        String xml = "";
        Object tgz = null;
        boolean returnTgz = false;
	    String msg = kidsHeader.getMessageName();
        switch (EKidsMessages.valueOf(msg)) {
           case Confirmation:  
                MapExpConKU mapExpConKU = new MapExpConKU(parser, encoding);
                mapExpConKU.setKidsHeader(kidsHeader);
                mapExpConKU.setCommonFieldsDTO(commonFieldsDTO);
                xml = mapExpConKU.getMessage();
                break;
           case InternalStatusInformation:
              	MapExpNckKU mapExpNckKU = new MapExpNckKU(parser, encoding);
                mapExpNckKU.setKidsHeader(kidsHeader);
                mapExpNckKU.setCommonFieldsDTO(commonFieldsDTO);
                xml = mapExpNckKU.getMessage();
                break;
           case Cancellation:
                MapExpCanKU mapExpCanKU = new MapExpCanKU(parser, encoding);
                mapExpCanKU.setKidsHeader(kidsHeader);
                mapExpCanKU.setCommonFieldsDTO(commonFieldsDTO);
                xml = mapExpCanKU.getMessage();
                break;	
//           case Confirm:
//               MapConfirmKU mapConfirmKU = new MapConfirmKU(parser, encoding);
//               mapConfirmKU.setKidsHeader(kidsHeader);
//               mapConfirmKU.setCommonFieldsDTO(commonFieldsDTO);
//               xml = mapConfirmKU.getMessage();
//               break;	
//           case Failure:
//               MapFailureKU mapfailureKU = new MapFailureKU(parser, encoding);
//               mapfailureKU.setKidsHeader(kidsHeader);
//               mapfailureKU.setCommonFieldsDTO(commonFieldsDTO);
//               xml = mapfailureKU.getMessage();
//               break;	
           case ExportDeclaration:
                MapExpDatKU mapExpDatKU = new MapExpDatKU(parser, encoding);
                mapExpDatKU.setKidsHeader(kidsHeader);
                mapExpDatKU.setCommonFieldsDTO(commonFieldsDTO);
                xml = mapExpDatKU.getMessage();
                break;
              //AK20090120 ReverseDeclaration instead of ExportRelease (which is UIDS-tag)
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
//                       tgz = XmlPdfTgzIntern.createTgz(xml, Config.getPdfpath(), commonFieldsDTO.getFilename());
                       tgz = XmlPdfTgzIntern2.createTgz(xml, Config.getPdfpath(), commonFieldsDTO.getFilename());
        			   commonFieldsDTO.updateFilename();
        		   }
        	   }
               break;
           case ErrorMessage:
         	    MapErrInfKU mapErrMessKU = new MapErrInfKU(parser, encoding);
        	    mapErrMessKU.setKidsHeader(kidsHeader);
        	    mapErrMessKU.setCommonFieldsDTO(commonFieldsDTO);
        	    xml = mapErrMessKU.getMessage();
        	    break;
           case DeclarationResponse:
        	    MapExpStaKU mapExpStaKU = new MapExpStaKU(parser, encoding);
        	    mapExpStaKU.setKidsHeader(kidsHeader);
        	    mapExpStaKU.setCommonFieldsDTO(commonFieldsDTO);
        	    xml = mapExpStaKU.getMessage();
        	    break;
           case Completion:
               MapExpEntKU mapExpEntKU = new MapExpEntKU(parser, encoding);
               mapExpEntKU.setKidsHeader(kidsHeader);
               mapExpEntKU.setCommonFieldsDTO(commonFieldsDTO);
               xml = mapExpEntKU.getMessage();
               break;
           case ManualTermination:
               MapExpErlKU mapExpErlKU = new MapExpErlKU(parser, encoding);
               mapExpErlKU.setKidsHeader(kidsHeader);
               mapExpErlKU.setCommonFieldsDTO(commonFieldsDTO);
               xml = mapExpErlKU.getMessage();
               break;
           case localAppResult:
        	   MapFailureKU mapFailureKU  = new MapFailureKU(parser, encoding);
        	   mapFailureKU.setKidsHeader(kidsHeader);
        	   mapFailureKU.setCommonFieldsDTO(commonFieldsDTO);
               xml = mapFailureKU.getMessage();
               break;
           case PreNotification:  //EI20090317
               MapExpIndKU mapExpIndKU = new MapExpIndKU(parser, encoding);
               mapExpIndKU.setKidsHeader(kidsHeader);
               mapExpIndKU.setCommonFieldsDTO(commonFieldsDTO);
               xml = mapExpIndKU.getMessage();
               break;
           case Amendment:
        	   MapExpAmdKU mapExpAmdKU  = new MapExpAmdKU(parser, encoding);
        	   mapExpAmdKU.setKidsHeader(kidsHeader);
        	   mapExpAmdKU.setCommonFieldsDTO(commonFieldsDTO);
               xml = mapExpAmdKU.getMessage();
               break;
           case ConfirmInvestigation:
        	   MapExpExtKU mapExpExtKU  = new MapExpExtKU(parser, encoding);
        	   mapExpExtKU.setKidsHeader(kidsHeader);
        	   mapExpExtKU.setCommonFieldsDTO(commonFieldsDTO);
               xml = mapExpExtKU.getMessage();
               break;            	  
           case Investigation:
        	   MapExpFupKU mapExpFupKU  = new MapExpFupKU(parser, encoding);
        	   mapExpFupKU.setKidsHeader(kidsHeader);
        	   mapExpFupKU.setCommonFieldsDTO(commonFieldsDTO);
               xml = mapExpFupKU.getMessage();
               break;            	  
           default: throw new FssException("Unknown message type " + msg);
        }
        
        if (returnTgz) {
            return tgz;
        } else {
            return xml;
        }
    }
    
}

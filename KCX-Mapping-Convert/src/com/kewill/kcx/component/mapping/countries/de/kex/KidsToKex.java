/*
 * Function    : KidsToKex.java
 * Titel       :
 * Date        : 15.11.2011
 * Author      : Kewill CSF / Schmidt
 * Description : transformer to convert KIDS-Format to Kewill Export format
 *             :
 * Parameters  :

 * Changes
 * ------------
 * Author      :
 * Date        :
 * Label       :
 * Description:
 *
 */
package com.kewill.kcx.component.mapping.countries.de.kex;

import javax.xml.stream.XMLEventReader;

import com.kewill.kcx.component.mapping.common.CommonFieldsDTO;
import com.kewill.kcx.component.mapping.countries.de.aes.EKidsMessages;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;


/**
 * Module       : KidsToKex<br>
 * Created 		: 15.11.2011<br>
 * Description  : transformer to convert KIDS-Format to Kewill Export formaz.
 * 
 * @author schmidt
 * @version 1.0.00
 */
public class KidsToKex {
    
    public Object readKids(XMLEventReader parser, String encoding, KidsHeader kidsHeader, 
                                                        CommonFieldsDTO commonFieldsDTO) throws Exception {
        String xml = "";
        Object tgz = null;
        boolean returnTgz = false;
	    String msg = kidsHeader.getMessageName();
        switch (EKidsMessages.valueOf(msg)) {
//           case Confirmation:  
//                MapExpConKX mapExpConKX = new MapExpConKX(parser, encoding);
//                mapExpConKX.setKidsHeader(kidsHeader);
//                mapExpConKX.setCommonFieldsDTO(commonFieldsDTO);
//                xml = mapExpConKX.getMessage();
//                break;
//           case InternalStatusInformation:
//              	MapExpNckKX mapExpNckKX = new MapExpNckKX(parser, encoding);
//                mapExpNckKX.setKidsHeader(kidsHeader);
//                mapExpNckKX.setCommonFieldsDTO(commonFieldsDTO);
//                xml = mapExpNckKX.getMessage();
//                break;
//           case Cancellation:
//                MapExpCanKX mapExpCanKX = new MapExpCanKX(parser, encoding);
//                mapExpCanKX.setKidsHeader(kidsHeader);
//                mapExpCanKX.setCommonFieldsDTO(commonFieldsDTO);
//                xml = mapExpCanKX.getMessage();
//                break;	
//           case ExportDeclaration:
//                MapExpDatKX mapExpDatKX = new MapExpDatKX(parser, encoding);
//                mapExpDatKX.setKidsHeader(kidsHeader);
//                mapExpDatKX.setCommonFieldsDTO(commonFieldsDTO);
//                xml = mapExpDatKX.getMessage();
//                break;
//           case ReverseDeclaration:    
//               if (kidsHeader.getCountryCode().equalsIgnoreCase("CH")) {
//                   MapCHReverseDeclarationKX mapCHReverseDeclarationKX = 
//                                                   new MapCHReverseDeclarationKX(parser, encoding);
//                   mapCHReverseDeclarationKX.setKidsHeader(kidsHeader);
//                   mapCHReverseDeclarationKX.setCommonFieldsDTO(commonFieldsDTO);
//                   xml = mapCHReverseDeclarationKX.getMessage();
//        	   } else {
//        		   MapExpRelKX mapReverseDeclarationKX = new MapExpRelKX(parser, encoding);
//        		   mapReverseDeclarationKX.setKidsHeader(kidsHeader);
//        		   mapReverseDeclarationKX.setCommonFieldsDTO(commonFieldsDTO);
//        		   xml = mapReverseDeclarationKX.getMessage();
//        		   if (commonFieldsDTO.isPdfTgz() && commonFieldsDTO.pdfExists()) {
//        			   Utils.log("es wird ein tgz generiert!!!");
//        			   returnTgz = true;
//                       tgz = XmlPdfTgzIntern2.createTgz(xml, Config.getPdfpath(), commonFieldsDTO.getFilename());
//        			   commonFieldsDTO.updateFilename();
//        		   }
//        	   }
//               break;
//           case ErrorMessage:
//         	    MapErrInfKX mapErrMessKX = new MapErrInfKX(parser, encoding);
//        	    mapErrMessKX.setKidsHeader(kidsHeader);
//        	    mapErrMessKX.setCommonFieldsDTO(commonFieldsDTO);
//        	    xml = mapErrMessKX.getMessage();
//        	    break;
//           case DeclarationResponse:
//        	    MapExpStaKX mapExpStaKX = new MapExpStaKX(parser, encoding);
//        	    mapExpStaKX.setKidsHeader(kidsHeader);
//        	    mapExpStaKX.setCommonFieldsDTO(commonFieldsDTO);
//        	    xml = mapExpStaKX.getMessage();
//        	    break;
//           case Completion:
//               MapExpEntKX mapExpEntKX = new MapExpEntKX(parser, encoding);
//               mapExpEntKX.setKidsHeader(kidsHeader);
//               mapExpEntKX.setCommonFieldsDTO(commonFieldsDTO);
//               xml = mapExpEntKX.getMessage();
//               break;
//           case ManualTermination:
//               MapExpErlKX mapExpErlKX = new MapExpErlKX(parser, encoding);
//               mapExpErlKX.setKidsHeader(kidsHeader);
//               mapExpErlKX.setCommonFieldsDTO(commonFieldsDTO);
//               xml = mapExpErlKX.getMessage();
//               break;
//           case localAppResult:
//        	   MapFailureKX mapFailureKX  = new MapFailureKX(parser, encoding);
//        	   mapFailureKX.setKidsHeader(kidsHeader);
//        	   mapFailureKX.setCommonFieldsDTO(commonFieldsDTO);
//               xml = mapFailureKX.getMessage();
//               break;
//           case PreNotification: 
//               MapExpIndKX mapExpIndKX = new MapExpIndKX(parser, encoding);
//               mapExpIndKX.setKidsHeader(kidsHeader);
//               mapExpIndKX.setCommonFieldsDTO(commonFieldsDTO);
//               xml = mapExpIndKX.getMessage();
//               break;
//           case Amendment:
//        	   MapExpAmdKX mapExpAmdKX  = new MapExpAmdKX(parser, encoding);
//        	   mapExpAmdKX.setKidsHeader(kidsHeader);
//        	   mapExpAmdKX.setCommonFieldsDTO(commonFieldsDTO);
//               xml = mapExpAmdKX.getMessage();
//               break;
//           case ConfirmInvestigation:
//        	   MapExpExtKX mapExpExtKX  = new MapExpExtKX(parser, encoding);
//        	   mapExpExtKX.setKidsHeader(kidsHeader);
//        	   mapExpExtKX.setCommonFieldsDTO(commonFieldsDTO);
//               xml = mapExpExtKX.getMessage();
//               break;            	  
//           case Investigation:
//        	   MapExpFupKX mapExpFupKX  = new MapExpFupKX(parser, encoding);
//        	   mapExpFupKX.setKidsHeader(kidsHeader);
//        	   mapExpFupKX.setCommonFieldsDTO(commonFieldsDTO);
//               xml = mapExpFupKX.getMessage();
//               break;            	  
           default:
               xml = "<Header><Receiver>" + kidsHeader.getReceiver() + "</Receiver></Header>" +
               		 "<Body>Body " + kidsHeader.getMessageName() + "</Body>";
//               throw new FssException("Unknown message type " + msg);
        }
        
        if (returnTgz) {
            return tgz;
        } else {
            return xml;
        }
    }
    
}

package com.kewill.kcx.component.mapping.countries.de.suma70;

import javax.xml.stream.XMLEventReader;

import com.kewill.kcx.component.mapping.common.CommonFieldsDTO;
import com.kewill.kcx.component.mapping.countries.de.aes.kids2uids.MapFailureKU;
import com.kewill.kcx.component.mapping.countries.de.suma70.kids2uids.MapGoodsReleasedExternalKU;
import com.kewill.kcx.component.mapping.countries.de.suma70.kids2uids.MapGoodsReleasedInternalKU;
import com.kewill.kcx.component.mapping.countries.de.suma70.kids2uids.MapProcessingResultsKU;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;


/**
 * Module       : KidsToUidsManifest<br>
 * Created 		: 06.09.2013<br>
 * Description  : transformer to convert Manifest / Suma KIDS-Format to UIDS messages.
 * 
 * @author iwaniuk
 * @version 2.0.00
 */
public class KidsToUidsManifest20 {
    
    public Object readKids(XMLEventReader parser, String encoding, KidsHeader kidsHeader, 
                                                        CommonFieldsDTO commonFieldsDTO) throws Exception {
        String xml = "";
	    String msg = kidsHeader.getMessageName();
        switch (EKidsManifestMessages.valueOf(msg)) {
           case GoodsReleasedExternal:  
        	   	MapGoodsReleasedExternalKU mapGoodsReleasedExternalKU = new MapGoodsReleasedExternalKU(parser, encoding);
        	   	mapGoodsReleasedExternalKU.setKidsHeader(kidsHeader);
        	   	mapGoodsReleasedExternalKU.setCommonFieldsDTO(commonFieldsDTO);
                xml = mapGoodsReleasedExternalKU.getMessage();
                break;
           case GoodsReleasedInternal:  
       	   		MapGoodsReleasedInternalKU mapGoodsReleasedInternalKU = new MapGoodsReleasedInternalKU(parser, encoding);
       	   		mapGoodsReleasedInternalKU.setKidsHeader(kidsHeader);
       	   		mapGoodsReleasedInternalKU.setCommonFieldsDTO(commonFieldsDTO);
               xml = mapGoodsReleasedInternalKU.getMessage();
               break;
        
           case ProcessingResults:                                   //EI20130211  
      	   		MapProcessingResultsKU mapProcessingResults = new MapProcessingResultsKU(parser, encoding);
      	   	mapProcessingResults.setKidsHeader(kidsHeader);
      	  mapProcessingResults.setCommonFieldsDTO(commonFieldsDTO);
              xml = mapProcessingResults.getMessage();
              break;
           
//           case ErrorMessage:
//         	    MapErrInfKU mapErrMessKU = new MapErrInfKU(parser, encoding);
//        	    mapErrMessKU.setKidsHeader(kidsHeader);
//        	    mapErrMessKU.setCommonFieldsDTO(commonFieldsDTO);
//        	    xml = mapErrMessKU.getMessage();
//        	    break;
//          
           case localAppResult:                          //EI20130209 aktiviert
        	   MapFailureKU mapFailureKU  = new MapFailureKU(parser, encoding);
        	   mapFailureKU.setKidsHeader(kidsHeader);
        	   mapFailureKU.setCommonFieldsDTO(commonFieldsDTO);
               xml = mapFailureKU.getMessage();               break;
          
             	  
           default: throw new FssException("Unknown message type " + msg);
        }
        return xml;
    }
    
}

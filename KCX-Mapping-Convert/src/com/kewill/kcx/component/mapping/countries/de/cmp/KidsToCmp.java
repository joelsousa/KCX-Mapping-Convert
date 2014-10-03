package com.kewill.kcx.component.mapping.countries.de.cmp;

/*
 * Function    : KidsToUids.java
 * Titel       :
 * Date        : 12.06.2010
 * Author      : Pete T
 * Description : transformer called to convert KIDS-Format to UIDS messages
 * 			   :
 * Parameters  :
 */

import javax.xml.stream.XMLEventReader;

import com.kewill.kcx.component.mapping.common.CommonFieldsDTO;
import com.kewill.kcx.component.mapping.countries.de.cmp.kids2cmp.MapControlDecisionKC;
import com.kewill.kcx.component.mapping.countries.de.cmp.kids2cmp.MapGoodsReleasedExternalKC;
import com.kewill.kcx.component.mapping.countries.de.cmp.kids2cmp.MapGoodsReleasedInternalKC;
import com.kewill.kcx.component.mapping.countries.de.cmp.kids2cmp.MapNCTSWriteOffNotificationKC;
import com.kewill.kcx.component.mapping.countries.de.cmp.kids2cmp.MapNotificationOfCompletionKC;
import com.kewill.kcx.component.mapping.countries.de.cmp.kids2cmp.MapProcessingResultsKC;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;


/**
 * Module       : KidsToKffPort<br>
 * Created 		: 09.11.2011<br>
 * Description  : transformer to convert KIDS-Format to KFF-PORT messages.
 *
 * @author iwaniuk
 * @version 1.0.00
 */
public class KidsToCmp {

    public String readKids(XMLEventReader parser, String encoding, KidsHeader kidsHeader,
                                                        CommonFieldsDTO commonFieldsDTO) throws Exception {
        String xml = "";
       
	    String msg = kidsHeader.getMessageName();
 
        switch (ECmpMessages.valueOf(msg)) {        	      
        	case ProcessingResults:	  //CUSREC, FSS-SCK, 	
        		MapProcessingResultsKC mapProcessingResults = new MapProcessingResultsKC(parser, encoding);
          	   	mapProcessingResults.setKidsHeader(kidsHeader);
          	   	mapProcessingResults.setCommonFieldsDTO(commonFieldsDTO);          	   	
                xml = mapProcessingResults.getMessage();
        		break;     
        		
        	case ControlDecision:     //CUSSTP, FSS-SST Bekanntgabe einer Massnahme
        		MapControlDecisionKC mapControlDecisionKC = new MapControlDecisionKC(parser, encoding);
        		mapControlDecisionKC.setKidsHeader(kidsHeader);
        		mapControlDecisionKC.setCommonFieldsDTO(commonFieldsDTO);
                xml = mapControlDecisionKC.getMessage();                                
        		break;
        		
        	case GoodsReleasedInternal:		//CUSTST, FSS-SWV
        		MapGoodsReleasedInternalKC mapGoodsReleasedInternal = new MapGoodsReleasedInternalKC(parser, encoding);
       	   		mapGoodsReleasedInternal.setKidsHeader(kidsHeader);
       	   		mapGoodsReleasedInternal.setCommonFieldsDTO(commonFieldsDTO);
                xml = mapGoodsReleasedInternal.getMessage();
                break;       	
               
        	case NotificationOfCompletion: //CUSFIN, FSS-SEK
        		MapNotificationOfCompletionKC mapNotificationOfCompletion = new MapNotificationOfCompletionKC(parser, encoding);
        		mapNotificationOfCompletion.setKidsHeader(kidsHeader);
        		mapNotificationOfCompletion.setCommonFieldsDTO(commonFieldsDTO);
                xml = mapNotificationOfCompletion.getMessage();
        		break;
        	
        	case NCTSWriteOffNotification: //TUFSTA NCTS-OUT, FSS-VSO
        		MapNCTSWriteOffNotificationKC mapNCTSWriteOffNotification = new MapNCTSWriteOffNotificationKC(parser, encoding);
        		mapNCTSWriteOffNotification.setKidsHeader(kidsHeader);
        		mapNCTSWriteOffNotification.setCommonFieldsDTO(commonFieldsDTO);
                xml = mapNCTSWriteOffNotification.getMessage();
        		break;        	
        		
        	case GoodsReleasedExternal:  //CUSCAN, FSS-EVK
        		MapGoodsReleasedExternalKC mapGoodsReleasedExternal = new MapGoodsReleasedExternalKC(parser, encoding);
        	   	mapGoodsReleasedExternal.setKidsHeader(kidsHeader);
        	   	mapGoodsReleasedExternal.setCommonFieldsDTO(commonFieldsDTO);
                xml = mapGoodsReleasedExternal.getMessage();
                break;
        	/*
        	case localAppResult:          
        		MapLocalAppKC mapLocalApp = new MapLocalAppKC(parser, encoding);
        		mapLocalApp.setKidsHeader(kidsHeader);
        		mapLocalApp.setCommonFieldsDTO(commonFieldsDTO);
        		xml = mapLocalApp.getMessage();
        		break;         	        		    			
                
        	case ErrorMessage:              
        		MapErrInfKC mapErrInf = new MapErrInfKC(parser, encoding);
        		mapErrInf.setKidsHeader(kidsHeader);
        		mapErrInf.setCommonFieldsDTO(commonFieldsDTO);
        		xml = mapErrInf.getMessage();
        		break;  
        	*/
           default: throw new FssException("KidsToCMP - Message not defined: " + msg);
        }
        
        return xml;        
    }

}

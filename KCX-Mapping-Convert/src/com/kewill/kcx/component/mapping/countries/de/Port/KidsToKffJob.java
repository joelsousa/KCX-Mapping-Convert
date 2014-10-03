package com.kewill.kcx.component.mapping.countries.de.Port;

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
import com.kewill.kcx.component.mapping.countries.de.Import.kids2kffJob.MapImportTaxAssessmentToKffJob;
import com.kewill.kcx.component.mapping.countries.de.Port.kids2kff.MapPortDeclarationRegistrationToJob;
import com.kewill.kcx.component.mapping.countries.de.Port.kids2kids.MapPortDeclarationStatusKK;
import com.kewill.kcx.component.mapping.countries.de.aes.kids2kids.MapErrInfKK;
import com.kewill.kcx.component.mapping.countries.de.aes.kids2kids.MapLocalAppKK;
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
public class KidsToKffJob {

    public String readKids(XMLEventReader parser, String encoding, KidsHeader kidsHeader,
                                                        CommonFieldsDTO commonFieldsDTO) throws Exception {
        String xml = "";
       
	    String msg = kidsHeader.getMessageName();
 
        switch (EKidsPortMessages.valueOf(msg)) {        	      
        	case PortDeclarationRejected:	  	
        		/*
        		MapEntrySummaryDeclarationRejectedKUni mapESDeclarationRejected =
        			new MapEntrySummaryDeclarationRejectedKUni(parser, encoding);
        		mapESDeclarationRejected.setKidsHeader(kidsHeader);
        		mapESDeclarationRejected.setCommonFieldsDTO(commonFieldsDTO);
        		xml = mapESDeclarationRejected.getMessage();
        		*/
        		break;         	
        	case PortDeclarationRegistration:			    //EI20120427 umbenannt "...Status.." to  "...Registration.."   	
        		MapPortDeclarationRegistrationToJob mapDeclarationRegistrationToJob =
        			new MapPortDeclarationRegistrationToJob(parser, encoding);
        		mapDeclarationRegistrationToJob.setKidsHeader(kidsHeader);
        		mapDeclarationRegistrationToJob.setCommonFieldsDTO(commonFieldsDTO);
        		xml = mapDeclarationRegistrationToJob.getMessage();        	
                break;   
               
        	case PortDeclarationStatus:			    		//EI20120510   	
        		MapPortDeclarationStatusKK mapDeclarationStatusKK =
        			new MapPortDeclarationStatusKK(parser, encoding);
        		mapDeclarationStatusKK.setKidsHeader(kidsHeader);
        		mapDeclarationStatusKK.setCommonFieldsDTO(commonFieldsDTO);
        		xml = mapDeclarationStatusKK.getMessage();        	
                break; 
                
        	case localAppResult:               //EI20120419 KFF soll KIDS bekommen, nicht JOB
        		MapLocalAppKK mapLocalAppKK = new MapLocalAppKK(parser, encoding);
        		mapLocalAppKK.setKidsHeader(kidsHeader);
        		mapLocalAppKK.setCommonFieldsDTO(commonFieldsDTO);
        		xml = mapLocalAppKK.getMessage();
        		break;         	        		    			
                
        	case ErrorMessage:               //EI20120426 KFF soll KIDS bekommen, nicht JOB
        		MapErrInfKK mapErrInfKK = new MapErrInfKK(parser, encoding);
        		mapErrInfKK.setKidsHeader(kidsHeader);
        		mapErrInfKK.setCommonFieldsDTO(commonFieldsDTO);
        		xml = mapErrInfKK.getMessage();
        		break;  
        		
        	case ImportTaxAssessment:    //EI20120228
        		MapImportTaxAssessmentToKffJob mapImportTaxToJob =
        			new MapImportTaxAssessmentToKffJob(parser, encoding);
        		mapImportTaxToJob.setKidsHeader(kidsHeader);
        		mapImportTaxToJob.setCommonFieldsDTO(commonFieldsDTO);
        		xml = mapImportTaxToJob.getMessage();        	
                break;
                
           default: throw new FssException("Unknown message type " + msg);
        }
        
        return xml;        
    }

}

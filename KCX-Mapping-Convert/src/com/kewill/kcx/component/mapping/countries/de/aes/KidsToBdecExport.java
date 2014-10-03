package com.kewill.kcx.component.mapping.countries.de.aes;
/*
 * Function    : KidsToBdecExport.java
 * Titel       :
 * Date        : 03.11.2009
 * Author      : Kewill CSF / iwaniuk
 * Description : transformer called by Mule 
 * 				 to convert KIDS-Format to Bell Davies ECustoms 
 * 			   : 
 * Parameters  : 

 * Changes 
 * -----------
 * Author      :
 * Date        :
 * Label       :
 * Description:
 *
 */

import javax.xml.stream.XMLEventReader;

import com.kewill.kcx.component.mapping.common.CommonFieldsDTO;
import com.kewill.kcx.component.mapping.countries.de.aes.kids2kids.MapExpCanKK;
import com.kewill.kcx.component.mapping.countries.uk.exp.kids2Bdec.MapExpDatToECustoms;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: Export/aes<br>
 * Created		: 03.11.2009<br>
 * Description  : Select of Kids-Messages to right Mapping to BellDavies E-Customs format.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class KidsToBdecExport {
    
	public String readKids(XMLEventReader parser, String encoding, KidsHeader kidsHeader, 
                                                        CommonFieldsDTO commonFieldsDTO) throws Exception {
		
        String xml = "";
        String msg = kidsHeader.getMessageName(); 
        String method = kidsHeader.getMethod();
        
        if (!Utils.isStringEmpty(method)) {
        	method = method.trim();
        }        	
        // tag Method in KIDS header is used for Bdec to define the soap action
        // bDec is always an ExportDeclaration, only Method/Action and edecType are differ !!        
        commonFieldsDTO.setActionType(method); 
        
      if (!Utils.isStringEmpty(method) && method.substring(0, 2).equals("at")) {
    	//EI20120125: neue Lösung	        
        MapExpDatToECustoms mapExpDatToECustoms = new MapExpDatToECustoms(parser);			
		mapExpDatToECustoms.setKidsHeader(kidsHeader);
		mapExpDatToECustoms.setCommonFieldsDTO(commonFieldsDTO);
       
		switch (EKidsECustomsMessages.valueOf(msg)) {        
        case PreNotification:        	                	
			xml = mapExpDatToECustoms.getMessage("PSA");			
            break;
        case ExportDeclaration:        	               	
			xml = mapExpDatToECustoms.getMessage("FDE");			
            break;
        case Completion:        	             	
			xml = mapExpDatToECustoms.getMessage("SDE");			
            break;
        default:
            throw new FssException("Unknown message type " + msg);
        }
        
        return xml;  
        
      } else {
    	  //EI20120125: alte Lösung
    	  switch (EKidsECustomsMessages.valueOf(msg)) {        
          case PreNotification:
              commonFieldsDTO.setActionType("atNew");         
              Utils.log("(KidsToBdecExport readKids) commonFieldsDTO.setActionType(\"atNew\")");
          	MapExpDatToECustoms mapPreNotification = new MapExpDatToECustoms(parser);			
          	mapPreNotification.setKidsHeader(kidsHeader);
          	mapPreNotification.setCommonFieldsDTO(commonFieldsDTO);
  			xml = mapPreNotification.getMessage("PSA");			
              break;
          
          case ExportDeclaration:
              commonFieldsDTO.setActionType("atNew");          
          	MapExpDatToECustoms mapExpDatToECustoms = new MapExpDatToECustoms(parser);			
  			mapExpDatToECustoms.setKidsHeader(kidsHeader);
  			mapExpDatToECustoms.setCommonFieldsDTO(commonFieldsDTO);
  			xml = mapExpDatToECustoms.getMessage("FDE");			
              break;
          case Update:                                     
              commonFieldsDTO.setActionType("atUpdate");           
              Utils.log("(KidsToBdecExport readKids) commonFieldsDTO.setActionType(\"atUpdate\")");
          	MapExpDatToECustoms mapUpdate = new MapExpDatToECustoms(parser);			
          	mapUpdate.setKidsHeader(kidsHeader);
          	mapUpdate.setCommonFieldsDTO(commonFieldsDTO);
  			xml = mapUpdate.getMessage("FDE");			
              break;
          case Delete:                                     
              commonFieldsDTO.setActionType("atDelete");           
              Utils.log("(KidsToBdecExport readKids) commonFieldsDTO.setActionType(\"atDelete\")");
          	MapExpDatToECustoms mapDelete = new MapExpDatToECustoms(parser);			
          	mapDelete.setKidsHeader(kidsHeader);
          	mapDelete.setCommonFieldsDTO(commonFieldsDTO);
  			xml = mapDelete.getMessage("FDE");			
              break;
          case Amendment:                                     
              commonFieldsDTO.setActionType("atAmend");           
              Utils.log("(KidsToBdecExport readKids) commonFieldsDTO.setActionType(\"atAmend\")");
          	MapExpDatToECustoms mapAmendment = new MapExpDatToECustoms(parser);			
          	mapAmendment.setKidsHeader(kidsHeader);
          	mapAmendment.setCommonFieldsDTO(commonFieldsDTO);
  			xml = mapAmendment.getMessage("FDE");			
              break;            
          case Cancellation:										
          	commonFieldsDTO.setActionType("atCancel");           
          	Utils.log("(KidsToBdecExport readKids) commonFieldsDTO.setActionType(\"atCancel\")");
          	MapExpDatToECustoms mapCancellation = new MapExpDatToECustoms(parser);			
          	mapCancellation.setKidsHeader(kidsHeader);
          	mapCancellation.setCommonFieldsDTO(commonFieldsDTO);
  			xml = mapCancellation.getMessage("FDE");		
  			break;
  			
          case Completion:                                      //EI20111216 SDE is Completion not Amendment
              commonFieldsDTO.setActionType("atNew");        
              Utils.log("(KidsToBdecExport readKids) commonFieldsDTO.setActionType(\"atAmend\")");
          	MapExpDatToECustoms mapCompletion = new MapExpDatToECustoms(parser);			
          	mapCompletion.setKidsHeader(kidsHeader);
          	mapCompletion.setCommonFieldsDTO(commonFieldsDTO);
  			xml = mapCompletion.getMessage("SDE");			
              break;
         
          default:
              throw new FssException("Unknown message type " + msg);
          }
          
          return xml;  
      }
	}
	
}

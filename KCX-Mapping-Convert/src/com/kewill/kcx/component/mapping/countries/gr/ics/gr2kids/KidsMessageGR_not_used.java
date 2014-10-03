package com.kewill.kcx.component.mapping.countries.gr.ics.gr2kids;

import com.kewill.kcx.component.mapping.countries.common.Address;
import com.kewill.kcx.component.mapping.countries.common.Party;
import com.kewill.kcx.component.mapping.countries.common.TIN;
import com.kewill.kcx.component.mapping.countries.cy.ics.msg.common.CyprusAddress;
import com.kewill.kcx.component.mapping.db.CustomerProcedureDTO;
import com.kewill.kcx.component.mapping.db.Db;
import com.kewill.kcx.component.mapping.db.MessageIdHistoryDTO;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: ICS Greece.
 * Created      :
 * Description  :  common mehtods for converting Gr2Kids
 * 
 * @author 		:kewill
 * @version 	:1.0.00
 */
public class KidsMessageGR_not_used extends KidsMessage {
	
	public void setMapping() {
		String mappingCode = "";
		
    	if (kidsHeader.getMap() == null) {
            CustomerProcedureDTO customerProcedureDTO = Utils.getCustomerProceduresFromKcxId(
            				kidsHeader.getReceiver(), 
            				kidsHeader.getProcedure().toUpperCase());
            	
            if (customerProcedureDTO != null) {			//EI20110802
            	customerProcedureDTO.setMsgFormat("GREECE");
            	mappingCode = customerProcedureDTO.getMappingCode();	
            	kidsHeader.getCommonFieldsDTO().setCustomerProcedureDTO(customerProcedureDTO);
             
            	//EI: kidsHeader.setMapFrom(mappingCode); 	            	
            	//EI: kidsHeader.setMapTo(kidsHeader.getCountryCode());
                //
                //EI: wenn Nachricht von Zoll Richtung Kunde, dann ist mapFrom = countryCode
                // und mapTo = mappingCode.
                	kidsHeader.setMapFrom(kidsHeader.getCountryCode()); //never null: "" or Code	                 
                	kidsHeader.setMapTo(mappingCode);   
                }
    		} 
        	
        	//kidsHeader.getMap() is != null or is null but MapFrom/MapTo is != null
        	if (mappingCode.equalsIgnoreCase("EU")) {  
             	kidsHeader.setMap("0");
            } else {	
             	if (kidsHeader.getMapFrom() != null && kidsHeader.getMapTo() != null) {
             		if (kidsHeader.getMapFrom().equalsIgnoreCase(kidsHeader.getMapTo())) {         	
             			kidsHeader.setMap("0");
             		} else {
             			kidsHeader.setMap("1");
             		}
             	} 
            }
	}
	
	
}

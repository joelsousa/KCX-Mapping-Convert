package com.kewill.kcx.component.mapping.countries.cy.ics.cy2kids;

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
 * Module		: ICS Cyprus.
 * 
 * @author 		:kewill
 * @version 	:1.0.00
 */

public class KidsMessageCY extends KidsMessage {
	
    /**
     * Replace generated message ID of InReplyTo in KIDS header with the original messageID stored in the database.
     * MS20110816
     * 
     * @param corIdeMES25 InReplyTo as reecived from customs.
     * @param countryCode InReplyTo as sender CountryCode.
     */
    //public void setInReplyTo(String corIdeMES25, String CountryCode) {
	 public void setInReplyTo(String corIdeMES25, String countryCode) {
        MessageIdHistoryDTO dto = Db.readMessageIdHistory(corIdeMES25, countryCode);
        if (dto != null) {
            String messageIdOrg = dto.getMessageIdOrg();
            if (messageIdOrg != null) {
                kidsHeader.setInReplyTo(messageIdOrg);
            } else {
                kidsHeader.setInReplyTo(corIdeMES25);
            }
            String receiver = dto.getMessageReceiver();
            if (receiver != null) {
                kidsHeader.setReceiver(receiver);
            }
        } else {
            kidsHeader.setInReplyTo(corIdeMES25);
        }
        getCommonFieldsDTO().setKcxId(kidsHeader.getReceiver());
    }
    
	public void setMapping() {
		String mappingCode = "";
		
    	if (kidsHeader.getMap() == null) {
            CustomerProcedureDTO customerProcedureDTO = Utils.getCustomerProceduresFromKcxId(
            				kidsHeader.getReceiver(), 
            				kidsHeader.getProcedure().toUpperCase());
            if (customerProcedureDTO != null) {			//EI20110713				
            	customerProcedureDTO.setMsgFormat("CYPRUS");
            	mappingCode = customerProcedureDTO.getMappingCode();	
            	kidsHeader.getCommonFieldsDTO().setCustomerProcedureDTO(customerProcedureDTO);
            
        	//EI: kidsHeader.setMapFrom(mappingCode); 	            	
        	//EI: kidsHeader.setMapTo(kidsHeader.getCountryCode());
            //
            //EI: wenn Nachricht von Zoll Richtung Kunde, dann ist mapFrom = countryCode
            // und mapto = mappingCode.
            	kidsHeader.setMapFrom(kidsHeader.getCountryCode()); //never null: "" or Code	                 
            	kidsHeader.setMapTo(mappingCode);   
            }
		} 
    	
    	//kidsHeader.getMap() is != null or is null but MapFrom is != null
    	if (mappingCode.equalsIgnoreCase("EU")) {   //EI20110713
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
	
	public Party setParty(CyprusAddress cyprusAddress, String person) {			
		
		if (cyprusAddress == null) {
			return null;
		}
		
		Party party = null;
		Address kidsAddress = new Address();
		TIN tin =  new TIN();
		
		if (cyprusAddress != null) {
			kidsAddress.setName(cyprusAddress.getName());
			kidsAddress.setStreet(cyprusAddress.getStreet());
			kidsAddress.setPostalCode(cyprusAddress.getPostalCode());
			kidsAddress.setCity(cyprusAddress.getCity());
			kidsAddress.setCountry(cyprusAddress.getCountry());
			//kidsAddress.setLanguage(cyprusAddress.getLng());
			tin.setTin(cyprusAddress.getTin());
		}
		
		if (!kidsAddress.isEmpty() || !tin.isEmpty()) {		
			party = new Party(person);
			if (!kidsAddress.isEmpty()) {
				party.setAddress(kidsAddress);
			}
			if (!tin.isEmpty()) {
				party.setPartyTIN(tin);
			}
		}
		return party;
		
	}
	
}

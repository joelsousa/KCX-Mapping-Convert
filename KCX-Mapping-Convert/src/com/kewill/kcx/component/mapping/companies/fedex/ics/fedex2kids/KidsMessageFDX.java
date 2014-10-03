package com.kewill.kcx.component.mapping.companies.fedex.ics.fedex2kids;


import com.kewill.kcx.component.mapping.companies.fedex.ics.msg.common.FedexAddress;
import com.kewill.kcx.component.mapping.countries.common.Address;
import com.kewill.kcx.component.mapping.countries.common.Party;
import com.kewill.kcx.component.mapping.countries.common.TIN;
import com.kewill.kcx.component.mapping.db.CustomerProcedureDTO;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;
import com.kewill.kcx.component.mapping.util.Utils;


/**
 * Modul		: KidsMessageICS<br>
 * Erstellt		: 30.03.2011<br>
 * Beschreibung	: Methods used in all FedexsToKids Mappings .
 * 
 * @author iwaniuk
 * @version 1.0.00
 */

public class KidsMessageFDX extends KidsMessage {		
	
	public void setMapping() {
    	// C. Kron
    	// Fehlen die Angaben zum Encoding wird folgendermaßen kodiert (analog UidsToKids)
    	// Wenn Nachricht von Kunde Richtung Zoll,  dann ist mapFrom = mappingCode aus der DB
        // und mapTo = countryCode.
        // Wenn Nachricht von Zoll  Richtung Kunde, dann ist mapFrom = countryCode
        // und mapto = mappingCode aus der DB

    	if (kidsHeader.getMap() == null) {
            CustomerProcedureDTO customerProcedureDTO = Utils.getCustomerProceduresFromKcxId(
            				kidsHeader.getReceiver(), 
            				kidsHeader.getProcedure().toUpperCase());
            							
            customerProcedureDTO.setMsgFormat("FEDEX");
            String mappingCode = customerProcedureDTO.getMappingCode();	            
            //if (kidsHeader.getDirection().equals("FROM_CUSTOMER")) {
                if (mappingCode != null) {
                	kidsHeader.setMapFrom(mappingCode.trim()); 	            	//AK20120301  trim
                }
                if (kidsHeader.getCountryCode() != null) {
                	kidsHeader.setMapTo(kidsHeader.getCountryCode().trim());    //AK20120301  trim
                }
            	//statt aus der DB könnte MappingCode direkt von transmitter nehmen
            	/* TODO-iwa
            	String mappingCountry = "";	            	
            	if (this.kidsHeader.getTransmitter() != null && !this.kidsHeader.getTransmitter().isEmpty()) {
            		mappingCountry = this.msgFedex.getTransmitter().substring(0, 2);
	    		}
            	kidsHeader.setMapTo(mappingCountry);
            	*/
            //} else {
            //	kidsHeader.setMapFrom(kidsHeader.getCountryCode()());
            // 	kidsHeader.setMapTo(mappingCode);
            // }
            	        
            if (kidsHeader.getMapFrom().equalsIgnoreCase(kidsHeader.getMapTo())) {
            	kidsHeader.setMap("0");
            } else {
            	kidsHeader.setMap("1");
            }
		} else {
        	// Sind in den Encoding-Angaben "mapFrom" und "mapTo" gleich
        	// d.h. das Sender- und Empfängerland identisch, 
        	// wird kein Mapping durchgeführt und die Angabe im Tag "Map" entsprechend
        	// auf "0" gesetzt!
        	if (kidsHeader.getMapFrom().equalsIgnoreCase(kidsHeader.getMapTo())) {
        		kidsHeader.setMap("0");
        	}
		}
	}

	public Party setParty(FedexAddress fedexaddress, String person) {
		Party  	party = null;
		if (fedexaddress == null) {
			return party;
		}
		
		Address kidsAddress = new Address();
		TIN 	tin = new TIN();
		
		if (fedexaddress != null) {
			//party = new Party(person);
			kidsAddress.setName(fedexaddress.getName());
			kidsAddress.setStreet(fedexaddress.getStreet());
			kidsAddress.setPostalCode(fedexaddress.getPostalCode());
			kidsAddress.setCity(fedexaddress.getCity());
			kidsAddress.setCountry(fedexaddress.getCountry());
			kidsAddress.setLanguage(fedexaddress.getLng());			
			//party.setAddress(kidsAddress);
			
			tin.setTin(fedexaddress.getTin());				
			//party.setPartyTIN(tin);
			
		}
		if (kidsAddress.isEmpty() && tin.isEmpty()) {   //EI20110330
			party = null;
		} else {
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
	/*
	public void checkPartyTin(String kcxId, String name, Party party) {  //EI20110329
		String tin = "";
		String tinFromCompletion = "";
		
		if (party == null) {
			return;		
		}
		if (party.getPartyTIN() != null) {  
			tin = party.getPartyTIN().getTin().trim();	
			
			if (!Utils.isStringEmpty(tin) && tin.length() > 1 && !tin.substring(0, 2).equals("00")) {
				return;
			} 
		} 
				
		tinFromCompletion = Utils.getFedexCompletion(kcxId, name);	
		if (Utils.isStringEmpty(tinFromCompletion)) {	
			return;
		}			
		
		if (party.getPartyTIN() == null) { 
			tin = "";
			TIN partyTin = new TIN();
			partyTin.setTin(tin);
			party.setPartyTIN(partyTin);
		}
		
		if (Utils.isStringEmpty(tin)) {
			party.getPartyTIN().setTin(tinFromCompletion);
			Utils.log("(FEDEX: checkPartyTin) TIN (" + tin + ") was overwritten with default TIN from DB");       
		} else {
			if (tin.length() < 2 ||
			(tin.length() > 1 && tin.substring(0, 2).equals("00"))) {
				party.getPartyTIN().setTin(tinFromCompletion);
				Utils.log("(FEDEX: checkPartyTin) TIN (" + tin + ") was overwritten with default TIN from DB");       
			}
		} 
		
	}	
	*/
	public void checkPartyTin(String kcxId, String msgTagName, Party party) {  //EI20110329		
		if (party == null || msgTagName == null || kcxId == null) {
			return;		
		}
		
		if (party.getPartyTIN() == null) { 			
			TIN partyTin = new TIN();			
			party.setPartyTIN(partyTin);
		}
		
		String tinFromDB = "";								
		tinFromDB = Utils.getFedexCompletion(kcxId, msgTagName);	
		
		if (Utils.isStringEmpty(tinFromDB)) {
			party.getPartyTIN().setTin("");
		} else {
			party.getPartyTIN().setTin(tinFromDB);
		}				
	}
	
}

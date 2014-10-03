package com.kewill.kcx.component.mapping.countries.de.Port.kff2kids;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.common.ContactPerson;
import com.kewill.kcx.component.mapping.countries.de.Port.msg.MsgPortDeclaration;
import com.kewill.kcx.component.mapping.countries.de.Port.msg.common.AdditionalData;
import com.kewill.kcx.component.mapping.countries.de.Port.msg.common.GoodsItem;
import com.kewill.kcx.component.mapping.countries.de.Port.msg.common.Party;
import com.kewill.kcx.component.mapping.countries.de.Port.msg.common.PreCarriage;
import com.kewill.kcx.component.mapping.countries.de.Port.msg.common.Voyage;
import com.kewill.kcx.component.mapping.formats.kff.common.CargoItem;
import com.kewill.kcx.component.mapping.formats.kff.msg.MsgJOB;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;
import com.kewill.kcx.component.mapping.formats.kids.port.BodyPortDeclarationKids;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Module		: Port<br>
 * Erstellt		: 27.10.2011<br>
 * Beschreibung	: Mapping KFF to PortDeclaration.
 * 				
 * @author iwaniuk  
 * @version 1.0.00
 */
public class MapPortMMKff2K extends KidsMessage {

	
	///    TODO
	

	private MsgJOB msgKff 	= null;   	
	private MsgPortDeclaration message;
		
	private String countryTo = "";
		
	public MapPortMMKff2K(XMLEventReader parser, KidsHeader kidsHeader, String encoding) 
																				throws XMLStreamException {
			msgKff  = new MsgJOB(parser);
			message = new MsgPortDeclaration();
	        
			this.kidsHeader	= kidsHeader;			
			this.encoding = encoding;
	}
	
	public void getMessage(XMLStreamWriter writer) {
		 this.writer = writer;
	     try {
	    	 BodyPortDeclarationKids body = new BodyPortDeclarationKids(writer);

	         writeStartDocument(encoding, "1.0");
	         openElement("soap:Envelope");
	         setAttribute("xmlns:soap", "http://www.w3.org/2003/05/soap-envelope");
	            
	         kidsHeader.writeHeader();	         	          	            
	         msgKff.parse(HeaderType.KFF);	 
			 countryTo = kidsHeader.getCountryCode();	         
	         getKidsFromKFF();	         
	         getCommonFieldsDTO().setReferenceNumber(message.getReferenceNumber());
	            
	            body.setKidsHeader(kidsHeader);
	            body.setMessage(message);
	            body.writeBody();

	            closeElement();  // soap:Envelope
	            writer.writeEndDocument();

	            writer.flush();
	            writer.close();

	        } catch (XMLStreamException e) {
	            e.printStackTrace();
	        }
	}
	
	private void getKidsFromKFF() {	
		
		message.setReferenceNumber(msgKff.getJobNo()); 
		message.setPortRegistrationNumber("");		            //akr_bhtref	Z/B-Nummer, BHT-Reference
		message.setPortSystem("ZAPP");                  //aki_hasys: ZAPP, BHT
		message.setMessageFunction(msgKff.getFuncCode()); //akr_artna; 9-original, 1-storno, ...
		message.setDeclarationType("HDS");	            //aki_artauf; HDS, A06...,; LAS,AUS
		message.setDeclarationMode("");	                //aki_kzsaco: 0=leer,1=SACO-Anmeldung, 2=Kaiantrag
		message.setDeclarationTypeSpecification("001");	        //aki_qartau: 001 in Verbindung mit HDS:
		message.setActivityType("");                     //aki_leiart: nur ZAPP.A15
		
		message.setShipperReferenceNumber("");          //akr_sptref
		message.setShipper(getShipper()); 
		//message.setFOBShipper(msgKff.get()); 
		//message.setAgent(msgKff.get()); 
		//message.setAlternativeConsignee(msgKff.get()); 
		//message.setTally(msgKff.get()); 		      
		message.setContactPersonForDangerousGoods(getContactPerson("GG")); 
		
		//message.setTerminalCode(msgKff.get()); 
		//message.setOfferNumber(msgKff.get()); 
		message.setCustomsOfficeExport(msgKff.getDeclPlace());   //akd_extdst	
		//message.setDestinationFederalState(msgKff.()); 
		
		message.setVoyage(getVoyageFromKff()); 
		message.setAdditionalData(getBookingDataFromKff());
		//message.setNachlauf(msgKff.getJobNo());
		//message.setVorlauf(getVorlaufFromKff());
		
		int idx = 0;
		/*
		if (msgKff.getdddddddddList() != null) {  
			idx = 0;
			for (Detail saco : msgKff.getdddddddddList()) {						
				message.addConsolidatedContainerList(getCocoFromKff(saco));	
			}
		}				
		if (msgKff.getdddddddddList() != null) {  
			idx = 0;
			for (Detail text : msgKff.getdddddddddList()) {						
				message.addDescriptionist(getGoodsItemFromKff(text, idx));	
			}
		}
		*/
				
		if (msgKff.getContainerList() != null) { 							
			message.setContainerList(msgKff.getContainerList());				
		}
		
	}
	
	private Party getShipper() {
		if (msgKff == null) {
			return null;
		}
		Party shipper = new Party();
		shipper.setCustomerIdentifier(msgKff.getCarrierCode());
		shipper.setTin(msgKff.getShprEORINo());
		
		ContactPerson contact = new ContactPerson();
		contact.setName(msgKff.getShprName());
		contact.setClerk(msgKff.getShprName());
		contact.setPosition(msgKff.getShprPIC());
		contact.setPhoneNumber(msgKff.getShprTel());
		contact.setFaxNumber(msgKff.getShprTel());
		
		if (!contact.isEmpty()) {
			shipper.setContactPerson(contact);		
		}
		return shipper;
	}
	
	private ContactPerson getContactPerson(String person) {
		if (msgKff == null) {
			return null;
		}		
		ContactPerson contact = new ContactPerson();
		if (person.equals("GG")) {
			contact.setName(msgKff.getItemHazardContact());		
			contact.setPhoneNumber(msgKff.getItemHazardContactTel());		
		} 
		
		if (contact.isEmpty()) {
			return null;
		} else {
			return contact;
		}
	}	
	
	private Voyage getVoyageFromKff() {
		if (msgKff == null) {
			return null;
		}		
		Voyage voyage = new Voyage();
		
		voyage.setShipCallSign(msgKff.getMotherVesselCallSign());		
		voyage.setShipName(msgKff.getMotherVesselName());	
		voyage.setArrivalDate(msgKff.getMotherVesselEta());		
		voyage.setDepartureDate(msgKff.getMotherVesselEtd());
		
		if (!Utils.isStringEmpty(msgKff.getPolCtry()) && 
				!Utils.isStringEmpty(msgKff.getPolCity())) {
			//voyage.getLoadingPort(msgKff.getPolName());	
			voyage.setLoadingPort(msgKff.getPolCtry() + msgKff.getPolCity());
		}
		if (!Utils.isStringEmpty(msgKff.getPodCtry()) && 
				!Utils.isStringEmpty(msgKff.getPodCity())) {
			//getDischargePort(msgKff.getPolName());	
			voyage.setDischargePort(msgKff.getPodCtry() + msgKff.getPodCity());
		}		
		//voyage.setFinalPort(msgKff.getDestCity());	
		if (!Utils.isStringEmpty(msgKff.getDestCtry()) && 
				!Utils.isStringEmpty(msgKff.getDestCity())) {
			voyage.setDestinationPlaceCode(msgKff.getDestCtry() + msgKff.getDestCity());				
		}
		voyage.setDestinationPlaceName(msgKff.getDestName());
		
		if (voyage.isEmpty()) {
			return null;
		} else {
			return voyage;
		}
	}	
	
	private AdditionalData getBookingDataFromKff() {
		if (msgKff == null) {
			return null;
		}		
		AdditionalData additionalData = new AdditionalData();
		
		additionalData.setBookingNumber(msgKff.getBookingNo());
		additionalData.setBillOfLadingNumber(msgKff.getShpNo());
		
		if (additionalData.isEmpty()) {
			return null;
		} else {
			return additionalData;
		}
	}	
	
	
}

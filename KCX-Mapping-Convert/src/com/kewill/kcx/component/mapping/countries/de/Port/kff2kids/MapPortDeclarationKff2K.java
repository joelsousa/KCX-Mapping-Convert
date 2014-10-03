package com.kewill.kcx.component.mapping.countries.de.Port.kff2kids;

import java.util.List;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.common.Address;
import com.kewill.kcx.component.mapping.countries.common.ContactPerson;
import com.kewill.kcx.component.mapping.countries.de.Port.msg.MsgPortDeclaration;
import com.kewill.kcx.component.mapping.countries.de.Port.msg.common.AdditionalData;
import com.kewill.kcx.component.mapping.countries.de.Port.msg.common.DangerousGoods;
import com.kewill.kcx.component.mapping.countries.de.Port.msg.common.DangerousGoodsLand;
import com.kewill.kcx.component.mapping.countries.de.Port.msg.common.DangerousGoodsSea;
import com.kewill.kcx.component.mapping.countries.de.Port.msg.common.GoodsItem;
import com.kewill.kcx.component.mapping.countries.de.Port.msg.common.GoodsLevel;
import com.kewill.kcx.component.mapping.countries.de.Port.msg.common.MrnStatement;
import com.kewill.kcx.component.mapping.countries.de.Port.msg.common.Party;
import com.kewill.kcx.component.mapping.countries.de.Port.msg.common.PreCarriage;
import com.kewill.kcx.component.mapping.countries.de.Port.msg.common.Voyage;
import com.kewill.kcx.component.mapping.formats.kff.common.CargoItem;
import com.kewill.kcx.component.mapping.formats.kff.common.RefNo;
import com.kewill.kcx.component.mapping.formats.kff.msg.MsgJOB;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;
import com.kewill.kcx.component.mapping.formats.kids.port.BodyPortDeclarationKids;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: Port<br>
 * Erstellt		: 27.10.2011<br>
 * Beschreibung	: Mapping KFF to PortDeclaration.
 * 				
 * @author iwaniuk  
 * @version 1.0.00
 */
public class MapPortDeclarationKff2K extends KidsMessage {


	private MsgJOB msgKff 	= null;   	
	private MsgPortDeclaration message;
		
	private String jobCustomsDeclType = "";
		
	//public MapPortDeclarationKff2K(XMLEventReader parser, KidsHeader kidsHeader, String encoding) throws XMLStreamException {
	public MapPortDeclarationKff2K(MsgJOB msgJOB, KidsHeader kidsHeader, String encoding) throws XMLStreamException {
		
		//EI20120424: msgKff  = new MsgJOB(parser);
		msgKff = msgJOB;
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
	         //msgKff.parse(HeaderType.KFF);	 
			 			
	         this.getKidsFromKFF();	         
	         getCommonFieldsDTO().setReferenceNumber(message.getReferenceNumber());	         	
	         body.setKidsHeader(kidsHeader);
	         body.setMessage(message);
	         if (this.kidsHeader != null) {      //EI20130513 wg. PackageCodes-jetzt in der DB.kcx_codes
	         	this.kidsHeader.setMap("1");
	         	this.kidsHeader.setMapFrom("KFF");
	         	this.kidsHeader.setMapTo("DE");
	         }
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
		message.setTestMode(kidsHeader.getTestIndicator());
		message.setPortRegistrationNumber("");		            //akr_bhtref	Z/B-Nummer, BHT-Reference
		//message.setPortSystem("ZAPP");                  //aki_hasys: ZAPP, BHT
		if (msgKff.getPolCtry() != null && msgKff.getPolCtry().equals("DE") &&
			msgKff.getPolCity() != null && msgKff.getPolCity().equals("BRV")) {
			message.setPortSystem("BHT");
		} else {
			message.setPortSystem("ZAPP");
		}
		if (msgKff.getJobKCX() != null) {   //EI20120705			
			if (!Utils.isStringEmpty(msgKff.getJobKCX().getDakosyMsgType())) { 
				message.setPortSystem("ZAPP");		
			} else if (!Utils.isStringEmpty(msgKff.getJobKCX().getDBHMsgType())) {
				message.setPortSystem("BHT");
			} else {			
				//EI20120808: message.setPortSystem("");
				message.setPortSystem("ZAPP");   //EI20120808
			}			
		}
		message.setMessageFunction(msgKff.getFuncCode());   //akr_artna; 9-original, 1-storno, ...
		message.setDeclarationType("HDS");	                //aki_artauf; HDS, A06...,; LAS,AUS
		message.setDeclarationMode("0");	                //aki_kzsaco: 0=leer,1=SACO-Anmeldung, 2=Kaiantrag
		//message.setDeclarationTypeSpecification("001");	//aki_qartau: 001 in Verbindung mit HDS:
		message.setDeclarationTypeSpecification("");        //EI20120509
		message.setActivityType("");                        //aki_leiart: nur ZAPP.A15
		
		message.setShipperReferenceNumber("");              //akr_sptref
		message.setShipper(this.getShipperFromJOB()); 
		//message.setFOBShipper(msgKff.get()); 
		
		//message.setAgent(getAgent(msgKff.getRdrCde()));    
		message.setAgent(this.getAgentFromJOB(msgKff.getCarrierCode()));    //EI20120423
		
		//message.setAlternativeConsignee(msgKff.get()); 
		//message.setTally(msgKff.get()); 		      
		message.setContactPersonForDangerousGoods(this.getContactPersonFromJOB("GG")); 
		
		message.setTerminalCode(msgKff.getKaiCde());   //TODO-IWA
		//message.setTerminalCode(msgKff???));   //EI20120423
		
		//message.setOfferNumber(msgKff.get()); 
		message.setCustomsOfficeExport(msgKff.getDeclPlace());   //akd_extdst	
		//message.setDestinationFederalState(msgKff.()); 
		
		message.setVoyage(this.getVoyageFromJOB()); 
		message.setAdditionalData(this.getAdditionalDataFromJOB());
		//message.setNachlauf(msgKff.getJobNo());
		message.setPreCarriage(this.getPreCarriageFromJOB());
		
		int idx = 0;
		/*
		if (msgKff.getdddddddddList() != null) {  
			idx = 0;
			for (Detail saco : msgKff.getdddddddddList()) {						
				message.addConsolidatedContainerList(getCocoFromKff(saco));	
			}
		}
		*/	
		/*
		if (msgKff.get != null) {  Texte zu Declaration ???
			idx = 0;
			for (Detail text : msgKff.getdddddddddList()) {						
				message.addDescriptionist(getGoodsItemFromKff(text, idx));	
			}
		}
		*/
		if (msgKff.getCustomsDeclType() != null && msgKff.getCustomsDeclType().length() == 3) {   //EI20120412
			jobCustomsDeclType = msgKff.getCustomsDeclType();
		}
			
		if (msgKff.getCargoItemList() != null) {  			
			for (CargoItem cargoItem : msgKff.getCargoItemList()) {
				GoodsItem item = this.mapGoodsItemJobToKids(cargoItem, msgKff.getRefNoList());		
                if (item != null) {				
                	message.addGoodsItemList(item);
                }
			}			
		}		
		if (msgKff.getContainerList() != null) { 							
			message.setContainerList(msgKff.getContainerList());				
		}
		
	}
	
	private Party getShipperFromJOB() {
		if (msgKff == null) {
			return null;
		}
		Party shipper = new Party();		
		//shipper.setCustomerIdentifier(msgKff.getCarrierCode()); //TODO-IWA ??? CarrierCode oder ShprPartyId
		shipper.setCustomerIdentifier(msgKff.getShprPartyId());    //EI20120424
		shipper.setTin(msgKff.getShprEORINo());
				
		shipper.setAddress(this.getShipperAddressFromKff());
		
		ContactPerson contact = new ContactPerson();
		contact.setName(msgKff.getShprName());
		contact.setClerk(msgKff.getShprPIC());   //EI20120427
		contact.setPosition(msgKff.getShprBEFunction());
		contact.setPhoneNumber(msgKff.getShprTel());
		contact.setFaxNumber(msgKff.getShprFax());      //EI20120419
		contact.setEmail(msgKff.getShprEmail());        //EI20120423
		
		if (!contact.isEmpty()) {
			shipper.setContactPerson(contact);		
		}
		return shipper;
	}

	private Address getShipperAddressFromKff() {
		
		Address address = new Address();
		String str = "";
		String plzCity = "";
		String plz = msgKff.getShprPostalCode();
		String city = "";		
		//EI20130422: if (msgKff.getShprAddrList() != null && msgKff.getShprAddrList().length == 4) {	
		if (msgKff.getShprAddrList() != null && msgKff.getShprAddrList().size() > 1) {	 //EI20130422
			List<String> list = msgKff.getShprAddrList();
			str = list.get(0);
			plzCity = list.get(1);
			city = this.getCityName(plzCity, plz);
		}		
		
		address.setName(msgKff.getShprName());		
		address.setCity(city);
		address.setCountry(msgKff.getShprCtryCode());
		address.setPostalCode(plz);
		address.setStreet(str);
				
		return address;
	}
	
	
	private String getCityName(String plzCity, String plz) {
		String city = plzCity;
		int plzLength = 0;
		if (plzCity == null) {
			return "";
		}		
		if (plz != null && plz.length() > 1) {
			plzLength = plz.length();
			if (plzCity.length() >= plzLength) {
				if (plzCity.substring(0, plzLength - 1).equals(plz)) {
					city = plzCity.substring(plzLength - 1);
				}
			}
		}
		return city;
	}
		
	private Party getAgentFromJOB(String portCode) {     //EI20120412
		if (msgKff == null) {
			return null;
		}
		Party agent = new Party();	
		agent.setPortCode(portCode);
		return agent;
	}
	
	private ContactPerson getContactPersonFromJOB(String person) {
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
	
	private Voyage getVoyageFromJOB() {
		if (msgKff == null) {
			return null;
		}		
		Voyage voyage = new Voyage();
		
		voyage.setShipReferenceNumber(msgKff.getMotherVoyage());   //AK20120403
		
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
			voyage.setDischargePort(msgKff.getPodCtry() + msgKff.getPodCity());
		}		
		
		if (!Utils.isStringEmpty(msgKff.getDestCtry()) && 
				!Utils.isStringEmpty(msgKff.getDestCity())) {
			//voyage.setDestinationPlaceCode(msgKff.getDestCtry() + msgKff.getDestCity());	//fss Endhafen
			voyage.setFinalPort(msgKff.getDestCtry() + msgKff.getDestCity());
		}
		
		if (!Utils.isStringEmpty(msgKff.getDevryCtry()) && 
				!Utils.isStringEmpty(msgKff.getDevryCtry())) {
			voyage.setDestinationPlaceCode(msgKff.getDevryCtry() + msgKff.getDevryCity());	//fss Bestimmungsort			
		}		
		voyage.setDestinationPlaceName(msgKff.getDestName());  //??? nicht msgKff.getDevryName()?
		voyage.setImoNumber(msgKff.getMotherVesselLioydsregno()); //EI20120509
		
		if (voyage.isEmpty()) {
			return null;
		} else {
			return voyage;
		}
	}	
	
	private AdditionalData getAdditionalDataFromJOB() {
		if (msgKff == null) {
			return null;
		}		
		AdditionalData additionData = new AdditionalData();
		
		additionData.setBookingNumber(msgKff.getBookingNo());
		additionData.setBillOfLadingNumber(msgKff.getShpNo());		
		
		if (additionData.isEmpty()) {
			return null;
		} else {
			return additionData;
		}
	}	
	
	private PreCarriage getPreCarriageFromJOB() {
		if (msgKff == null) {
			return null;
		}		
		PreCarriage preCarrige = new PreCarriage();
		
		preCarrige.setForwarderCode(msgKff.getSrvReqTruckNo());	
		preCarrige.setForwarder(msgKff.getSrvReqSubcName());
		if (!Utils.isStringEmpty(msgKff.getSrvReqTruckNo()) || !Utils.isStringEmpty(msgKff.getSrvReqSubcName())) { //EI20130603
			preCarrige.setTransportType("3");   //EI20130529 war vorher in kids2fss, wg BDP muss es hier vorbelegt werden
		}
		
		if (preCarrige.isEmpty()) {
			return null;
		} else {
			return preCarrige;
		}
	}	
	
	//private GoodsItem getGoodsItem(CargoItem cargoItem) {  
	private GoodsItem mapGoodsItemJobToKids(CargoItem cargoItem, List <RefNo>  mrnList) {   //EI20120116: 
		if (cargoItem == null || cargoItem.isEmpty()) {
			return null;
		}
		GoodsItem item = new GoodsItem();		
		String mrn = "";   															//EI20120116
		
		if (mrnList != null && !mrnList.isEmpty()) {  								//EI20120116
			for (RefNo ref : mrnList) {
			 if (ref != null) {
				 if (ref.getRefCode() != null && ref.getRefNoRemark() != null) {					
					
					if (ref.getRefCode().equals("MRN")) {							//EI20120208						
						String mrnItems = "";										//EI20120117						
						String mrnRemarks = ref.getRefNoRemark();           //EI20120425
						int ln = mrnRemarks.length();
						if (mrnRemarks.substring(ln - 1, ln).equals(";")) {
							mrnRemarks = mrnRemarks.substring(0, ln - 1);
						}
						
						if (mrnRemarks.length() > 5 && 
						   (mrnRemarks.substring(0, 5).equalsIgnoreCase("ITEM:") || mrnRemarks.substring(0, 5).equalsIgnoreCase("ITEM;"))) {
							mrnItems = mrnRemarks.substring(5).trim();
						} else {
							mrnItems = mrnRemarks;
						}
						/*EI20120425:
						if (mrnItem.equals(cargoItem.getItemSNO())) {
							mrn = ref.getRefNumber();
						}
						*/
						char trenner = '|';
						String tr = "" + trenner;
						if (mrnItems.contains("|")) {  		 //EI20120425
						//if (mrnItems.contains(trenner)) {  		 //EI20120425
							//String[] tokens = mrnItems.split("|");							
							String[] tokens = mrnItems.split(tr);
							int len = tokens.length;
							for (int i = 0; i < len; i++) {
								String nr = tokens[i];
								if (nr.equals(cargoItem.getItemSNO())) {									
									mrn = ref.getRefNumber();
									break;
								}
							}
						} else {							
							if (mrnItems.equals(cargoItem.getItemSNO())) {
								mrn = ref.getRefNumber();
							}
						}
												
						
						jobCustomsDeclType = "AES";                          //EI20120412
					}
					
				 }
			 }	
			}
		}
		
		item.setItemNumber(cargoItem.getItemSNO());
		//EI20120412: item.setCustomsDeclarationType(cargoItem.getCustomsDeclarationType());
		item.setCustomsDeclarationType(jobCustomsDeclType);   //EI20120412
		if (cargoItem.getCtnItem() != null) {
			item.setContainerNumber(cargoItem.getCtnItem().getContainerNumber());
		}
		item.setTarifCode(cargoItem.getHsCoded());
		item.setTruckNumber(cargoItem.getItemCargoItemRemarks());
		item.setTransportationNumber(cargoItem.getItemCargoItemRemarks());
		
		if (cargoItem.getItemPcs() != null && !Utils.isStringEmpty(cargoItem.getItemPcs())) {			
			GoodsLevel level1 = new GoodsLevel();
			level1.setItemRemark(cargoItem.getItemCargoItemRemarks());
			level1.setQuantity(cargoItem.getItemPcs());
			level1.setPackingType(cargoItem.getItemPcsUt());
			level1.setGrossMass(cargoItem.getItemWgt());
			level1.setVolume(cargoItem.getItemVol());
			level1.setDescription(cargoItem.getDescription());
			level1.setMarks(cargoItem.getMarks());
			if (!Utils.isStringEmpty(mrn)) {                          //EI20120116
				MrnStatement mrnStatement = new MrnStatement();
				mrnStatement.setMrn(mrn);
				mrnStatement.setMrnComplete("1");
				level1.addMrnStatementList(mrnStatement);
			}
			//TODO: level1.setEADocument(cargoItem.???);
			DangerousGoods dg = new DangerousGoods();			
			DangerousGoodsSea sea = new DangerousGoodsSea();
			DangerousGoodsLand land = new DangerousGoodsLand();
			//sea.setImdgClass(cargoItem.getItemHazardClass());
			//sea.setUnNumber(cargoItem.getItemHazardCode());
			dg.setImdgClass(cargoItem.getItemHazardClass());   //EI20120423
			dg.setUnNumber(cargoItem.getItemHazardCode());     //EI20120423
			dg.setPackagingSafetyGroup(cargoItem.getItemDGPackingGroup()); //EI20120423
			
			dg.setTechnicalSpecification(cargoItem.getItemHazardDesc());
			dg.setSeaTransportDetails(sea);
			dg.setLandTransportDetails(land);
			//dg.set???(cargoItem.getItemHazardCodeQualifier());
			//sea.setPackagingGroup(cargoItem.getItemDGPackingGroup());
			if (!dg.isEmpty()) {
				level1.setDangerousGoods(dg);
			}	
			item.setFirstGoodsLevel(level1);
			if (message.getContactPersonForDangerousGoods() == null &&            //EI20120510
					!Utils.isStringEmpty(cargoItem.getItemHazardContact())) {
				ContactPerson dgContactPerson = new ContactPerson();
				dgContactPerson.setName(cargoItem.getItemHazardContact());
				dgContactPerson.setPhoneNumber(cargoItem.getItemHazardContactTel());
				message.setContactPersonForDangerousGoods(dgContactPerson);
			}
		}
		if (cargoItem.getItemInnerPcs() != null && !Utils.isStringEmpty(cargoItem.getItemInnerPcs())) {			
			GoodsLevel level2 = new GoodsLevel();
			level2.setSequenceNumber("1"); 
			//level2.setItemRemark(cargoItem.getItemCargoItemRemarks());
			level2.setQuantity(cargoItem.getItemInnerPcs());
			level2.setPackingType(cargoItem.getItemPcsUt());
				
			item.addSecondGoodsLevelList(level2);	
		}
			
				
		return item;
	}
	
}

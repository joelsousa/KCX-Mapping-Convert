package com.kewill.kcx.component.mapping.countries.de.Port.kff2kids;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.util.ArrayList;
import java.util.List;

import com.kewill.kcx.component.mapping.countries.de.Port.msg.MsgBillOfLading;
import com.kewill.kcx.component.mapping.countries.de.Port.msg.common.Container;
import com.kewill.kcx.component.mapping.countries.de.Port.msg.common.CtnItem;
import com.kewill.kcx.component.mapping.countries.de.Port.msg.commonBL.AdditionalReferenceGroup;
import com.kewill.kcx.component.mapping.countries.de.Port.msg.commonBL.Address;
import com.kewill.kcx.component.mapping.countries.de.Port.msg.commonBL.AddressUnqualified;
import com.kewill.kcx.component.mapping.countries.de.Port.msg.commonBL.AllocatedEquipment;
import com.kewill.kcx.component.mapping.countries.de.Port.msg.commonBL.Carriage;
import com.kewill.kcx.component.mapping.countries.de.Port.msg.commonBL.CarriageDetails;
import com.kewill.kcx.component.mapping.countries.de.Port.msg.commonBL.CommunicationContact;
import com.kewill.kcx.component.mapping.countries.de.Port.msg.commonBL.Contact;
import com.kewill.kcx.component.mapping.countries.de.Port.msg.commonBL.DangerousGoods;
import com.kewill.kcx.component.mapping.countries.de.Port.msg.commonBL.DangerousGoodsDetails;
import com.kewill.kcx.component.mapping.countries.de.Port.msg.commonBL.DistributedOverSeveralContainer;
import com.kewill.kcx.component.mapping.countries.de.Port.msg.commonBL.DocumentDetails;
import com.kewill.kcx.component.mapping.countries.de.Port.msg.commonBL.Equipment;
import com.kewill.kcx.component.mapping.countries.de.Port.msg.commonBL.EquipmentDetails;
import com.kewill.kcx.component.mapping.countries.de.Port.msg.commonBL.EquipmentQualifier;
import com.kewill.kcx.component.mapping.countries.de.Port.msg.commonBL.Goods;
import com.kewill.kcx.component.mapping.countries.de.Port.msg.commonBL.GoodsItem;
import com.kewill.kcx.component.mapping.countries.de.Port.msg.commonBL.ItemDetails;
import com.kewill.kcx.component.mapping.countries.de.Port.msg.commonBL.ItemText;
import com.kewill.kcx.component.mapping.countries.de.Port.msg.commonBL.MeansOfTransport;
import com.kewill.kcx.component.mapping.countries.de.Port.msg.commonBL.MonetaryAmount;
import com.kewill.kcx.component.mapping.countries.de.Port.msg.commonBL.PackingLevel1;
import com.kewill.kcx.component.mapping.countries.de.Port.msg.commonBL.PackingLevel2;
import com.kewill.kcx.component.mapping.countries.de.Port.msg.commonBL.Parties;
import com.kewill.kcx.component.mapping.countries.de.Port.msg.commonBL.PartyDetails;
import com.kewill.kcx.component.mapping.countries.de.Port.msg.commonBL.PartyRows;
import com.kewill.kcx.component.mapping.countries.de.Port.msg.commonBL.PaymentInstructions;
import com.kewill.kcx.component.mapping.countries.de.Port.msg.commonBL.PaymentInstructionsGroup;
import com.kewill.kcx.component.mapping.countries.de.Port.msg.commonBL.Place;
import com.kewill.kcx.component.mapping.countries.de.Port.msg.commonBL.PortsAndPlaces;
import com.kewill.kcx.component.mapping.countries.de.Port.msg.commonBL.Reference;
import com.kewill.kcx.component.mapping.countries.de.Port.msg.commonBL.ReferenceDetails;
import com.kewill.kcx.component.mapping.countries.de.Port.msg.commonBL.Seals;
import com.kewill.kcx.component.mapping.countries.de.Port.msg.commonBL.TextBL;
import com.kewill.kcx.component.mapping.countries.de.Port.msg.commonBL.TextOnEntireBL;
import com.kewill.kcx.component.mapping.countries.de.Port.msg.commonBL.Totals;
import com.kewill.kcx.component.mapping.db.CustomerDTO;
import com.kewill.kcx.component.mapping.formats.kff.common.CargoItem;
import com.kewill.kcx.component.mapping.formats.kff.msg.MsgJOB;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;
import com.kewill.kcx.component.mapping.formats.kids.port.BodyBillOfLadingKids;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: Port<br>
 * Erstellt		: 19.04.2012<br>
 * Beschreibung	: Mapping KFF to PortDeclaration.
 * 				
 * @author iwaniuk  
 * @version 1.0.00
 */
public class MapBillOfLadingKff2K extends KidsMessage {

	private MsgJOB msgKff 	= null;   	
	private MsgBillOfLading message;	
	
	//public MapBillOfLadingKff2K(XMLEventReader parser, KidsHeader kidsHeader, String encoding) throws XMLStreamException
	public MapBillOfLadingKff2K(MsgJOB msgJOB, KidsHeader kidsHeader, String encoding) throws XMLStreamException {
		
			//EI20120424:msgKff  = new MsgJOB(parser);
			msgKff = msgJOB;
			message = new MsgBillOfLading();
	       
			this.kidsHeader	= kidsHeader;			
			this.encoding = encoding;
	}
	
	public void getMessage(XMLStreamWriter writer) {
		 String kdnr = null;
		 this.writer = writer;
	     try {
	    	 BodyBillOfLadingKids body = new BodyBillOfLadingKids(writer);

	         writeStartDocument(encoding, "1.0");
	         openElement("soap:Envelope");
	         setAttribute("xmlns:soap", "http://www.w3.org/2003/05/soap-envelope");
	         
	         this.getKidsFromKFF();

 
	         String localId = Utils.getCustomerIdFromKewill(this.kidsHeader.getReceiver(),
                     "BL", this.kidsHeader.getCountryCode()); 

	         if (localId != null) {
	        	 String[] localIdArr = localId.split("-");
	        	 if (localIdArr.length > 0) {
		        	 kdnr = localIdArr[0];	        		 
	        	 } else {
	        		 Utils.log("Falscher Eintrag customer LOCAL_ID_TYPE=BL: KDNR-Teilnehmercode");
	        	 }
	         } else {
	        	 Utils.log("Fehlender Eintrag customer LOCAL_ID_TYPE=BL");
	         }
	         
	         //AK20120807
	         //this.kidsHeader.setMessageID(this.getDateiName(kdnr, this.kidsHeader.getMessageID()));
	         //AK20130418
	         String messageCounterFromCustomerProtocol  = Utils.getProtocolForBL(this.kidsHeader.getReceiver(), this.kidsHeader.getCountryCode());
	         this.kidsHeader.setMessageID(this.getDateiName(kdnr, messageCounterFromCustomerProtocol));
	         
	         kidsHeader.writeHeader();	         	          	            
	         //EI20120424: msgKff.parse(HeaderType.KFF);	 
	            getCommonFieldsDTO().setReferenceNumber(message.getReferenceNumber());

	            body.setKidsHeader(kidsHeader);
	            body.setMessage(message);
	            if (this.kidsHeader != null) {     //EI20130513 wg. PackageCodes-jetzt in der DB.kcx_codes
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
		
		message.setApplicationSenderId(this.kidsHeader.getTransmitter());
		message.setApplicationRecipientId(this.kidsHeader.getReceiver());
		message.setReferenceNumber(msgKff.getJobNo());
		
		message.setTestFlag(kidsHeader.getTestIndicator());                          //AK20120726
		
		//message.setPortReference("");		      //TODO-IWA: was kommt hier fuer BL??? Z/B-Nummer, BHT-Reference
		String jobType = msgKff.getJobType();
						
		if (msgKff.getJobKCX() != null) {			//EI20120705
			if (!Utils.isStringEmpty(msgKff.getJobKCX().getDakosyMsgType())) { 
				message.setPortSystem("DAK");
         		//this.kidsHeader.setMsgID(getDateiName("4085", msgKff.getUnid()));
         		//EI20120807: this.kidsHeader.setMessageID(getDateiName("4085", msgKff.getUnid()));
			} else if (!Utils.isStringEmpty(msgKff.getJobKCX().getDBHMsgType())) {
				message.setPortSystem("BHT");
			} else {			
				//message.setPortSystem("");
				message.setPortSystem("DAK");   //EI20120808
			}			
		}
		
		if (msgKff.getFuncCode() != null) {             //JOB: 9-original, 1-storno, 4-replace 
			if (msgKff.getFuncCode().equals("9")) {   //BL: 9-Original, 1-Cancellation, 5-Replace 
				message.setMessageFunction("Original");
			} else if (msgKff.getFuncCode().equals("1")) {
				message.setMessageFunction("Cancellation");			
			} else if (msgKff.getFuncCode().equals("5")) {
				message.setMessageFunction("Replace");
			} else {
				message.setMessageFunction("");
			}
		}
		message.setSenderContact(this.getShipperContactFromJOB());   
		message.setTextOnEntireBL(this.getTextOnEntireBLFromJOB());
		message.setTotals(this.getTotalsFromJOB());
		message.setDocumentDetails(this.getDocumentDetailsFromJOB());                 
	    //message.setForwardersReference(this.getReferenceDetailsFromJOB(msgKff.getShpNo(), msgKff.getJobDate()));
		//AK20121220
/*		if (msgKff != null && msgKff.getJobKCX() != null && 
				!Utils.isStringEmpty(msgKff.getJobKCX().getForwarderReference())) {
			ReferenceDetails rd = new ReferenceDetails();
			rd.setReference(msgKff.getJobKCX().getForwarderReference());
			message.setForwardersReference(rd);
		}
*/	    //AK20130522
		if (msgKff != null && msgKff.getJobKCX() != null && 
				!Utils.isStringEmpty(msgKff.getJobKCX().getForwarderReference())) {
			ReferenceDetails rd = new ReferenceDetails();
			rd.setReference(msgKff.getJobKCX().getForwarderReference());
			message.setForwardersReference(rd);
		} else if (msgKff != null && !Utils.isStringEmpty(msgKff.getJobNo())) {
			ReferenceDetails rd = new ReferenceDetails();
			rd.setReference(msgKff.getJobNo());
			message.setForwardersReference(rd);
		}
		
		message.setCarriage(this.getCarriageFromJOB());
	    
	    message.setAdditionalReferenceGroup(this.getAdditionalReferenceGroupFromJOB()); 
        message.setPaymentInstructionsGroup(this.getAPaymentInstructionsGroupFromJOB()); 
        //TODO-IWA:  oder soll ich msgKff.getCargoValue und msgKff.getCargoValueCurr besser in FreightAndCharge mappen ???
       
		PartyDetails shpr = this.getShipperFromJOB();
		if (shpr != null) {
			if (message.getParties() == null) {
				Parties parties = new Parties();
				message.setParties(parties);
			}
			message.getParties().setShipper(shpr); 
		}		
		PartyDetails cosnignee = this.getConsigneeFromJOB();
		if (cosnignee != null) {
			if (message.getParties() == null) {
				Parties parties = new Parties();
				message.setParties(parties);
			}
			message.getParties().setConsignee(cosnignee); 
		}        
 
		if (msgKff.getCargoItemList() != null) {  	
			Goods goods = new Goods();
			for (CargoItem cargoItem : msgKff.getCargoItemList()) {
				GoodsItem item = this.getGoodsItemFromJOB(cargoItem);		
                if (item != null) {		                	
                	goods.addGoodsItemList(item);
                }
			}
			message.setGoods(goods);
		}		
		if (msgKff.getContainerList() != null) { 	
			Equipment equipment = new Equipment();
			EquipmentQualifier eq = null;
			
			for (Container container : msgKff.getContainerList()) {
				eq = new EquipmentQualifier();  //AK20121221
				EquipmentDetails ed = this.getEquipmentDetailsFromJOB(container);		            		
                eq.setContainer(ed);  
                equipment.addEquipmentQualifierList(eq);
			}
			message.setEquipment(equipment);				
		}				
	}
	
	private	TextOnEntireBL getTextOnEntireBLFromJOB() {
		//return null;
			//TODO-IWA 20120713 das sind dakosy-mussfelder, die von KFF nicht kommen/gemapped werden
		if (msgKff == null || (msgKff != null && msgKff.getJobKCX() != null &&
				Utils.isStringEmpty(msgKff.getJobKCX().getCargoRemark()))) {
			return null;
		}	
		
		//AK20121220
		// Cargoremarks werden nicht mehr in Remarks/Remark/RemarkType=ESI/RemarkText sondern im 
		// Jobkcxcustomdecl/CargoRemark
		//AK20121220 List <Remark> remarkList = msgKff.getRemarkList();
		TextOnEntireBL texte = new TextOnEntireBL();
		TextBL cargoRemarks = new TextBL();
		int typeOfCargo = Utils.stringToInt(msgKff.getJobKCX().getCargoRemark());
		String remark;
		
		switch (typeOfCargo) {
			case 1: remark = "containerized";
			break;
			case 2: remark = "non-containerized";
			break;
			case 3: remark = "combined";
			break;
			
			default: remark = "";
		}
		
		cargoRemarks.setTextReference(remark);
		
		texte.setCargoRemarks(cargoRemarks);
		
//AK20121220 begin
//		if (remarkList != null) {
//			for (Remark remark : remarkList) {
//				if (remark.getRemarkType() != null && remark.getRemarkType().trim().equalsIgnoreCase("ESI")) {
//					texte = new TextOnEntireBL();
//					TextBL cargoRemarks = new TextBL();
//					
//					cargoRemarks.setTextReference(remark.getRemarkText());
//					//RemarkType = ESI braucht keine Liste cargoRemarks.addTextList(remark.getRemarkText());
//					
//					texte.setCargoRemarks(cargoRemarks);
//				}
//			}
//		}
//AK20121220 begin		
		return texte;
	}
	
	private PartyDetails getShipperFromJOB() {
		if (msgKff == null) {
			return null;
		}
		PartyDetails shipper = new PartyDetails();
		shipper.setPartyId(msgKff.getShprPartyId());
		//shipper.sett(msgKff.getShprEORINo());   TODO-IWA: wohin mit der EORI?
		
		Address address = new Address();
		AddressUnqualified addressUnqualified = null;
		String str = "";
		String plzCity = "";
		String plz = msgKff.getShprPostalCode();
		String city = "";	
		
		//if (msgKff.getShprAddrList() != null && msgKff.getShprAddrList().length == 4) {	
		if (msgKff.getShprAddrList() != null && msgKff.getShprAddrList().size() > 1) {	
			addressUnqualified = new AddressUnqualified();  //EI201300422
			PartyRows rows = new PartyRows();
			rows.setFirstRow(msgKff.getShprName());
			rows.setNextRowList(msgKff.getShprAddrList());
			addressUnqualified.setPartyNameAndAddress(rows);
			shipper.setAddressUnqualified(addressUnqualified);
			
			List<String> list = msgKff.getShprAddrList();
			str = list.get(0);
			plzCity = list.get(1);	
			city = this.getCityName(plzCity, plz);
						
		}		
		//shipper.setAddressUnqualified(msgKff.getShprName(), s1, s2, s3, s4);		
		address.setPartyName(msgKff.getShprName());		
		address.setCity(city);
		address.setCountryCode(msgKff.getShprCtryCode());
		address.setPostalCode(plz);
		address.setStreetOrBox(str);
		
		shipper.setAddressQualified(address);		
		shipper.setContact(this.getShipperContactFromJOB());		
		//taxRegistrationNumber;
		//referenceList;
		
		return shipper;
	}
	
	private PartyDetails getConsigneeFromJOB() {
		if (msgKff == null) {
			return null;
		}
		PartyDetails consignee = new PartyDetails();
		consignee.setPartyId(msgKff.getCsgnPartyId());
		//consignee???(msgKff.getCsgnEORINo());   TODO-IWA: wohin mit der EORI?
		
		Address address = new Address();
		AddressUnqualified addressUnqualified = null;
		String str = "";
		String plzCity = "";		
		String plz = msgKff.getCsgnPostalCode();
		String city = "";
		
		//if (msgKff.getCsgnAddrList() != null && msgKff.getCsgnAddrList().length == 4) {
		if (msgKff.getCsgnAddrList() != null && msgKff.getCsgnAddrList().size() > 1) {
			addressUnqualified = new AddressUnqualified();
			PartyRows rows = new PartyRows();
			rows.setFirstRow(msgKff.getCsgnName());
			rows.setNextRowList(msgKff.getCsgnAddrList());
			addressUnqualified.setPartyNameAndAddress(rows);
			consignee.setAddressUnqualified(addressUnqualified);
			
			List<String> list = msgKff.getCsgnAddrList();
			str = list.get(0);
			plzCity = list.get(1);	
			city = this.getCityName(plzCity, plz);			
		}						
		address.setPartyName(msgKff.getCsgnName());		
		address.setCity(city);
		address.setCountryCode(msgKff.getCsgnCTRYCode());
		address.setPostalCode(plz);
		address.setStreetOrBox(str);
		
		consignee.setAddressQualified(address);		
		consignee.setContact(this.getConsigneeContactFromJOB());	
		
		return consignee;
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
	private Contact getShipperContactFromJOB() {
		if (msgKff == null) {
			return null;
		}		
		Contact contact = new Contact();
		
			contact.setContactName(msgKff.getShprPIC()); //contact.setContactName(msgKff.getShprName());
			if (msgKff.getShprTel() != null && !msgKff.getShprTel().equals("")) {
				CommunicationContact te = new CommunicationContact();	
				te.setCommunicationQualifier("TE"); 
				te.setCommunicationNumber(msgKff.getShprTel());
				contact.addCommunicationContactList(te);				
			}
			if (msgKff.getShprFax() != null && !msgKff.getShprFax().equals("")) {
				CommunicationContact ft = new CommunicationContact();	
				ft.setCommunicationQualifier("FX");   //AK20121210
				ft.setCommunicationNumber(msgKff.getShprFax());
				contact.addCommunicationContactList(ft);
			}
			if (msgKff.getShprEmail() != null && !msgKff.getShprEmail().equals("")) {
				CommunicationContact em = new CommunicationContact();	
				em.setCommunicationQualifier("EM");
				em.setCommunicationNumber(msgKff.getShprEmail());
				contact.addCommunicationContactList(em);
			}
		 		
		if (contact.isEmpty()) {
			return null;
		} else {
			return contact;
		}
	}
	private Contact getConsigneeContactFromJOB() {
		if (msgKff == null) {
			return null;
		}		
		Contact contact = new Contact();
		
			contact.setContactName(msgKff.getCsgnName());  // oder ...PIC
			
			if (msgKff.getCsgnTel() != null && !msgKff.getCsgnTel().equals("")) {
				CommunicationContact te = new CommunicationContact();	
				te.setCommunicationQualifier("TE"); 
				te.setCommunicationNumber(msgKff.getCsgnTel());
				contact.addCommunicationContactList(te);				
			}
			if (msgKff.getCsgnFax() != null && !msgKff.getCsgnFax().equals("")) {
				CommunicationContact ft = new CommunicationContact();	
				ft.setCommunicationQualifier("FX"); 
				ft.setCommunicationNumber(msgKff.getCsgnFax());
				contact.addCommunicationContactList(ft);
			}
			if (msgKff.getCsgnEmail() != null && !msgKff.getCsgnEmail().equals("")) {
				CommunicationContact em = new CommunicationContact();	
				em.setCommunicationQualifier("EM");
				em.setCommunicationNumber(msgKff.getCsgnEmail());
				contact.addCommunicationContactList(em);
			}			
		 		
		if (contact.isEmpty()) {
			return null;
		} else {
			return contact;
		}
	}
	private Totals getTotalsFromJOB() {
		if (msgKff == null) {
			return null;
		}
		Totals totals = new Totals();
	    totals.setGrossWeightKilogram(msgKff.getTotGWGT());
	    totals.setNumberOfPackages(msgKff.getTotPCS());
	    totals.setGrossVolumeCubicMetre(msgKff.getTotVOL());
	    
	    return totals;
	}

	private DocumentDetails getDocumentDetailsFromJOB() {
		if (msgKff == null) {
			return null;
		}	
		//List <RefNo> refNoList 	= msgKff.getRefNoList();
		DocumentDetails documentDetails = null;
		//String layoutKeyDakosy		= null;
		String layoutKey		= null;
		
		
		//AK20121220 layoutKey from <Jobkcxcustomdecl><BillofLading>  
		if (msgKff.getJobKCX() != null && !Utils.isStringEmpty(msgKff.getJobKCX().getBillofLading())) {
			layoutKey = msgKff.getJobKCX().getBillofLading();
		}
		/*if (refNoList != null) {
			for (RefNo refNo : refNoList) {
				if (refNo.getRefCode() != null && refNo.getRefCode().trim().equalsIgnoreCase("DKBL")) {
					layoutKey = refNo.getRefNumber();
					break;
				}
			}
		}*/
			
		if (!Utils.isStringEmpty(layoutKey) || 
			 (msgKff.getSea() != null  && 
			 !Utils.isStringEmpty(msgKff.getSea().getNoBL()) && 
			 !Utils.isStringEmpty(msgKff.getSea().getNoOfCopy2()))) {
			documentDetails = new DocumentDetails();
			documentDetails.setDocumentName("BillOfLadingOriginal");
			documentDetails.setFormularLayoutKeyDakosy(layoutKey);
			if (msgKff.getSea() != null) {
				documentDetails.setNumberOfOriginalsRequired(msgKff.getSea().getNoBL());
				documentDetails.setNumberOfCopiesRequired(msgKff.getSea().getNoOfCopy2());
			}
		}
		
		return documentDetails;
	}

/*	private DocumentDetails getDocumentDetailsFromJOB() {
		if (msgKff == null) {
			return null;
		}	
		return null;
		//TODO-IWA 20120713 das sind dakosy-mussfelder, die von KFF nicht kommen/gemapped werden
		
		DocumentDetails documentDetails = new DocumentDetails();
		documentDetails.setDocumentName("BillOfLading");
		documentDetails.setFormularLayoutKeyDakosy("NOT-FILLED");
		documentDetails.setNumberOfOriginalsRequired("NOT-FILLED");
		documentDetails.setNumberOfCopiesRequired("NOT-FILLED");
		
		return documentDetails;
		
	}*/
	private ReferenceDetails getReferenceDetailsFromJOB(String refNr, String refDate) {
		if (msgKff == null) {
			return null;
		}		 
		ReferenceDetails ref = new ReferenceDetails();
		ref.setReference(refNr);
		ref.setDate(refDate);
		
		return ref;
	}	
	private AdditionalReferenceGroup getAdditionalReferenceGroupFromJOB() {
		if (msgKff == null) {
			return null;
		}
		AdditionalReferenceGroup addRefGroup = new AdditionalReferenceGroup();
		Reference ref;
		
		if (msgKff.getShpNo() != null) {
			ref = new Reference();
			ref.setReferenceName("BM");  //BillOfLading
			ref.setReferenceDetail(this.getReferenceDetailsFromJOB(msgKff.getShpNo(), msgKff.getJobDate()));
			
			addRefGroup.addAdditionalReferenceList(ref);
		}
		/*
		if (msgKff.getShpNo() != null) {
			ref = new Reference();
			ref.setReferenceName("BN");  //BookingNumber
			ref.setReferenceDetail(this.getReferenceDetailsFromJOB(msgKff.getBookingNo(), msgKff.getBookingDate()));
			addRefGroup.addAdditionalReferenceList(ref);
		}
		*/
		return addRefGroup;
	}
	private PaymentInstructionsGroup getAPaymentInstructionsGroupFromJOB() {
		if (msgKff == null) {
			return null;
		}
		if (Utils.isStringEmpty(msgKff.getCargoValue())) {
			return null;
		}
		
		PaymentInstructionsGroup pig = new PaymentInstructionsGroup();
		PaymentInstructions pi = new PaymentInstructions();
		MonetaryAmount ma = new MonetaryAmount();
				
		ma.setCurrencyCode(msgKff.getCargoValueCurr());
		ma.setValue(msgKff.getCargoValue());
		
		pi.addMonetaryAmountList(ma);		
		pig.addPaymentInstructionsList(pi);
		
		return pig;
	}
	private Carriage getCarriageFromJOB() {
		if (msgKff == null) {
			return null;
		}		 
		Carriage carriage = new Carriage();
		CarriageDetails mainCarriage = new CarriageDetails();
		MeansOfTransport mot = new MeansOfTransport();
		PortsAndPlaces pap = new PortsAndPlaces();		
     
		mot.setVesselName(msgKff.getMotherVesselName());
		mot.setShipownerVoyageNumber(msgKff.getMotherVoyage());  //EI20130417
     
		Place placeLa = new Place();
		placeLa.setUnLocode(msgKff.getPlaceOfLoadingCode());
		pap.setPortOfLoading(placeLa);
		Place placeLo = new Place();
		placeLo.setUnLocode(msgKff.getPlaceOfDischargeCode());
		pap.setPortOfDischarge(placeLo);
     
		//EI20130417-KFF schickt keine Schiffsabfahrtsnummer, sondern Reisenummer:
		//EI20130417: mainCarriage.setShipReferenceNumber(msgKff.getMotherVoyage());
		mainCarriage.setEstimatedArrivalDate(msgKff.getMotherVesselEta());
		mainCarriage.setEstimatedDepartureDate(msgKff.getMotherVesselEtd()); 
		mainCarriage.setPortsAndPlaces(pap);               
		mainCarriage.setMeansOfTransport(mot);
     
		carriage.setMainCarriage(mainCarriage);
		//carriage.addPreCarriageList(list);
		//carriage.setOnCarriageList(list);
		
		return carriage;	
	}

	 private GoodsItem getGoodsItemFromJOB(CargoItem cargoItem) {    
		if (cargoItem == null || cargoItem.isEmpty()) {
			return null;
		}
		GoodsItem item = new GoodsItem();	
		PackingLevel1 firstPackLevel = new PackingLevel1();		
		ItemDetails itemDetails =  new ItemDetails();
		ItemText texte = new ItemText();				
					
		itemDetails.setNumberOfPackages(cargoItem.getItemPcs());
		itemDetails.setTypeOfPackagesIdentification(cargoItem.getItemPcsUt());			
		itemDetails.setNetWeightKilogram(cargoItem.getItemWgt());
		itemDetails.setGrossVolumeCubicMetre(cargoItem.getItemVol());
		itemDetails.addMarksAndNumbersList(cargoItem.getMarks());
		itemDetails.setHarmonizedSystemCommodityCode(cargoItem.getHsCoded());
		itemDetails.setBookingReferenceNumber(msgKff.getBookingNo());
		
		if (cargoItem.getDescription() != null) { //AK20121212
			TextBL text = new TextBL();
			text.addTextList(cargoItem.getDescription());
			if (text != null && text.getTextList() != null) {
				texte.addGoodsDescriptionList(text);
			}
	 	}
		/*
		Text text2 = new Text();
		text2.setText(cargoItem.getItemCargoItemRemarks());
		if (text2 != null && !Utils.isStringEmpty(text2.getText())) {
			texte.addRemarksBeforeGoodsDescriptionList(text2);
		}
		*/
		if (cargoItem.getItemCargoItemRemarks() != null) { //AK20121212
			TextBL text3 = new TextBL();
			text3.addTextList(cargoItem.getItemCargoItemRemarks());
			if (text3 != null && text3.getTextList() != null) {
				texte.addRemarksAfterGoodsDescriptionList(text3);
			}
		}
		/*
		AllocatedEquipment container = null;
		if (cargoItem.getCtnItem() != null) {	
			container = new AllocatedEquipment();
			container.setEquipmentIdentificationNumber(cargoItem.getCtnItem().getContainerNumber());
		}
		*/
		DangerousGoods dg = null;
		if (!Utils.isStringEmpty(cargoItem.getItemHazardClass()) || !Utils.isStringEmpty(cargoItem.getItemHazardCode())) {	
			dg = new DangerousGoods();
			DangerousGoodsDetails dgd = new DangerousGoodsDetails();
			dgd.setHazardCodeIdentification(cargoItem.getItemHazardClass());
			dgd.setUNDGNumber(cargoItem.getItemHazardCode());
			dgd.setLevelOfDanger(cargoItem.getItemDGPackingGroup());
			//dgd.setTechnicalName.???.(cargoItem.getItemHazardDesc());
			//TODO-IWA weitere Tags: z.Z. keine Angaben
			dg.setImd(dgd);
		}
		//EI20130412	
		if (cargoItem.getCtnItems() != null && cargoItem.getCtnItems().getCtnItemList() != null) { 				
			for (CtnItem ctn : cargoItem.getCtnItems().getCtnItemList()) {
				AllocatedEquipment container = new AllocatedEquipment();
				DistributedOverSeveralContainer distr = new DistributedOverSeveralContainer();
				distr.setNumberOfPackages(ctn.getCtnItemTOTPCS());
				distr.setGrossWeightKilogram(ctn.getCtnItemTOTWGT());
				//distr.setNetWeightKilogram(ctn);
				//distr.setTareWeightKilogram(ctn);
				distr.setGrossVolumeCubicMetre(ctn.getCtnItemTOTVOL());
				
				container.setEquipmentIdentificationNumber(ctn.getContainerNumber());
				container.setDistributedOverSeveralContainer(distr);
				
				firstPackLevel.addAllocatedEquipmentList(container);
			}
							
		}		
		firstPackLevel.setGrossWeightKilogram(cargoItem.getItemWgt());
		firstPackLevel.setDetailsOnItem(itemDetails);
		firstPackLevel.setTextOnItem(texte);
		//firstPackLevel.addAllocatedEquipmentList(container);
		firstPackLevel.addDangerousGoodsList(dg);
		
		PackingLevel2 secondPackLevel = new PackingLevel2();  //falls nur ein SecondLevel
		ItemDetails itemSecondDetails =  new ItemDetails();
		if (!Utils.isStringEmpty(cargoItem.getItemInnerPcs())) {				 				
			itemSecondDetails.setNumberOfPackages(cargoItem.getItemInnerPcs());
			itemSecondDetails.setTypeOfPackagesIdentification(cargoItem.getItemInnerPcsUt());
			//itemSecondDetails.setTareWeightKilogram(cargoItem.);
			secondPackLevel.setDetailsOnItem(itemSecondDetails);
			firstPackLevel.addPackingLevel2List(secondPackLevel);
		}
		
		item.setGoodsItemNumber(cargoItem.getItemSNO());
		item.setFirstPackingLevel(firstPackLevel);
		
		return item;
	}		
		
	 private EquipmentDetails getEquipmentDetailsFromJOB(Container container) {
		 if (container == null || container.isEmpty()) {
				return null;
			}
		 EquipmentDetails equipmentDetails = new EquipmentDetails();	
		 equipmentDetails.setEquipmentIdentificationNumber(container.getContainerNumber());
		 equipmentDetails.setTareWeightKilogram(container.getTareMass());
		 equipmentDetails.setTypeAndSizeISOCode(container.getType());
		 equipmentDetails.setGrossWeightKilogram(container.getGrossMass());
		 
		 if (container.getSealNumberList() != null) {
			 for (String si : container.getSealNumberList()) {
				 Seals seal = new Seals();
				 seal.setSealingNumber(si);
				 equipmentDetails.addSealsList(seal);
			 }
		 }
		return equipmentDetails;
	 }
	
		/*
	 	 * liefert einen 9 stelligen String (Dateinamen) anhand 
	 	 * der 1. Ziffer der Kundennummer
	 	 * der 3 folgenden Zeichen der Kundennummer
	 	 * und der fortlaufenden Nummer 
	 	 * 		   	 
	 	 */
		public String getDateiName(String kdnr, String lfdnr) {
		char   kdnr1  		=   ' ';
		String exchange 	= 	"";
		String dateiName 	= 	"";
		
		if (kdnr != null && kdnr.trim().length() == 4) {
			kdnr1 = kdnr.charAt(0);
		}

		switch (kdnr1) {
		case '0':
		   		exchange = "K";
		   		break;
		case '1':
				exchange = "L";
				break;
		case '2':
		   		exchange = "M";
		   		break;
		case '3':
				exchange = "N";
				break;
		case '4':
		   		exchange = "O";
		   		break;
		case '5':
				exchange = "P";
				break;
		case '6':
		   		exchange = "Q";
		   		break;
		case '7':
				exchange = "R";
				break;
		case '8':
		   		exchange = "S";
		   		break;
		case '9':
				exchange = "T";
				break;
		default: Utils.log("(MapBillOfLadingKff2K setDateiName wrong first digit for CustomerIdentifier:" + kdnr);
		}
			
		if (exchange.length() == 1) {
			dateiName = exchange + kdnr.substring(1, 4) + Utils.nFormat(lfdnr, 5);
		} else {
			Utils.log("(setDateiName wrong first digit for CustomerIdentifier:" + kdnr);
		}
			
		return dateiName;
	}

}

package com.kewill.kcx.component.mapping.companies.fedex.exportImportNcts;

import java.util.ArrayList;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.Packages;
import com.kewill.kcx.component.mapping.companies.fedex.exportImportNcts.msg.MsgDeclnInput;
import com.kewill.kcx.component.mapping.companies.fedex.exportImportNcts.msg.common.DeclnCust;
import com.kewill.kcx.component.mapping.companies.fedex.exportImportNcts.msg.common.HdrDoc;
import com.kewill.kcx.component.mapping.companies.fedex.exportImportNcts.msg.common.ItemShpDeclnComdty;
import com.kewill.kcx.component.mapping.countries.common.ContactPerson;
import com.kewill.kcx.component.mapping.countries.common.TIN;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.CommodityCode;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.TransportMeans;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.AddressNCTS;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.Document;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.PartyNCTS;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.PlaceOfLoading;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.Security;
import com.kewill.kcx.component.mapping.countries.de.ncts20.msg.MsgNCTSDeclaration;
import com.kewill.kcx.component.mapping.countries.de.ncts20.msg.MsgNCTSDeclarationPos;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;
import com.kewill.kcx.component.mapping.formats.kids.ncts20.BodyNCTSDeclarationKids;
import com.kewill.kcx.component.mapping.util.Utils;

/**
* Module		: FEDEX-Export.<br>
* Created		: 13.12.2013<br>
* Description	: MapDeclnInput
* 
* @author iwaniuk
* @version 1.0.00
*/

public class MapNctsDeclarationFK extends KidsMessage {
	private BodyNCTSDeclarationKids body = null;
	private MsgNCTSDeclaration message = null;
	private MsgDeclnInput msgDecln = null;
		
	public MapNctsDeclarationFK(MsgDeclnInput msgDecln, KidsHeader kidsHeader, String encoding) 
									throws XMLStreamException {	       
			this.encoding = encoding;
			this.kidsHeader = kidsHeader;		
			this.msgDecln = msgDecln;
			message = new MsgNCTSDeclaration();
	}
		
	public void getMessage(XMLStreamWriter writer) {
        this.mapFedexDeclnToKids();	     
		
		 this.writer = writer;
	        try {	        	
	            body = new BodyNCTSDeclarationKids(writer);

	            writeStartDocument(encoding, "1.0");
	            openElement("soap:Envelope");
	            setAttribute("xmlns:soap", "http://www.w3.org/2003/05/soap-envelope");
	          
	            kidsHeader.writeHeader();
	       
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
		
	private void mapFedexDeclnToKids() {		
		
		if (msgDecln.getShpDeclSecInput() != null) {	
			message.setReferenceNumber(msgDecln.getShpDeclSecInput().getShpmtDeclnOidNbr()); 			
			message.setShipmentNumber(msgDecln.getShpDeclSecInput().getShipmentOidNbr());
						
			if (!Utils.isStringEmpty(msgDecln.getShpDeclSecInput().getPlaLdgCd())) {	
				PlaceOfLoading pol = new PlaceOfLoading();
				pol.setCode(msgDecln.getShpDeclSecInput().getPlaLdgCd());
				message.setPlaceOfLoading(pol);
			}
			message.setDispatchCountry(msgDecln.getShpDeclSecInput().getDispCtryCd());
			if (!Utils.isStringEmpty(msgDecln.getShpDeclSecInput().getTrptModInLndCd()) || 
					!Utils.isStringEmpty(msgDecln.getShpDeclSecInput().getTrptIdInLnd())) {
				TransportMeans inland = new TransportMeans();
				inland.setTransportMode(msgDecln.getShpDeclSecInput().getTrptModInLndCd());
				inland.setTransportationNumber(msgDecln.getShpDeclSecInput().getTrptIdInLnd());
				inland.setTransportationCountry(msgDecln.getShpDeclSecInput().getTrptCtryCd()); 
					
			}
			if (!Utils.isStringEmpty(msgDecln.getShpDeclSecInput().getTrptModCd()) 
					//|| !Utils.isStringEmpty(msgDecln.getShpDeclSecInput().getTrptId())
					) {				
					TransportMeans border = new TransportMeans();				
					border.setTransportMode(msgDecln.getShpDeclSecInput().getTrptModCd());
					//border.setTransportationNumber(msgDecln.getShpDeclSecInput().getTrptId());
					
				}
			message.setTotalNumberPositions(msgDecln.getShpDeclSecInput().getCommodityCount());	
			message.setTotalNumberPackages(msgDecln.getShpDeclSecInput().getTotPkgNbr());  
			message.setTotalGrossMass(msgDecln.getShpDeclSecInput().getGrossWt());					
			message.setConveyanceNumber(msgDecln.getShpDeclSecInput().getLocalShipmentOidNbr());			
			message.setPlaceOfUnloading(msgDecln.getShpDeclSecInput().getPlaUldgCd());
			
			if (!Utils.isStringEmpty(msgDecln.getShpDeclSecInput().getShpmtDeclnOidNbr())) {
				Security security = new Security();
				security.setShipmentNumber(msgDecln.getShpDeclSecInput().getShpmtDeclnOidNbr());
				security.setPlaceOfLoading(msgDecln.getShpDeclSecInput().getPlaLdgCd());				
				security.setPlaceOfUnloading(msgDecln.getShpDeclSecInput().getPlaUldgCd());
				message.setSecurity(security);
			}					
		}
		
		if (msgDecln.getDeclSecInput() != null) { 			
			kidsHeader.setMessageID(msgDecln.getDeclSecInput().getDeclnOidNbr());
			if (!Utils.isStringEmpty(msgDecln.getDeclSecInput().getDeclnTypeCd())) {
				if (msgDecln.getDeclSecInput().getDeclnTypeCd().length() > 1) {
					message.setTypeOfDeclaration(msgDecln.getDeclSecInput().getDeclnTypeCd().substring(0, 2));
				}
				if (msgDecln.getDeclSecInput().getDeclnTypeCd().length() > 2) {
					message.setSimplifiedProcedureIndicator(msgDecln.getDeclSecInput().getDeclnTypeCd().substring(2, 3));
				}
			}
			message.setDestinationCountry(msgDecln.getDeclSecInput().getCountryCd());											
		}
		
		if (msgDecln.getDeclCustomerSecInput() != null) {
			//todo
			ArrayList<DeclnCust> list = msgDecln.getDeclCustomerSecInput().getDeclnCustList();
			if (list != null && !list.isEmpty()) {				
				for (DeclnCust customer : list) {
					if (customer != null && customer.getCustomerType() != null) {
						PartyNCTS party = new PartyNCTS();
						party.setVATNumber(customer.getVatNbr());
						AddressNCTS address = this.mapFedexDeclnToAddress(customer); 
						if (!address.isEmpty()) {
							party.setAddress(address);
						}
						if (!Utils.isStringEmpty(customer.getTraderId())) {
							TIN tin = new TIN();
							tin.setTin(customer.getTraderId());
							party.setPartyTIN(tin);
						}
						if (!Utils.isStringEmpty(customer.getName())) {
							ContactPerson contact = new ContactPerson();
							contact.setName(customer.getName());
							contact.setClerk(customer.getName());
							party.setContactPerson(contact);
						}
						//
						if (customer.getCustomerType().equalsIgnoreCase("CZ")) {  //Consignor						
							message.setConsignor(party);
						} else if (customer.getCustomerType().equalsIgnoreCase("CN")) { //Consignee						
							message.setConsignee(party);
						} else if (customer.getCustomerType().equalsIgnoreCase("??")) { //TODO-Mx: wer ist Principal?						
							message.setPrincipal(party);	
						} else if (customer.getCustomerType().equalsIgnoreCase("DT")) { //Declarant					
							//message.setDeclarant(party);						
						} else if (customer.getCustomerType().equalsIgnoreCase("AG")) { //Agent						
							//message.setAgent(party);
						} else if (customer.getCustomerType().equalsIgnoreCase("??")) { //TODO-Mx: wer ist Clerk? 						
							message.setClerk(party.getContactPerson());
						}				
					}	        	
				} 				
			}			
		}
			
		if (msgDecln.getShpDeclCmdtySecInput() != null) {
				//items					
			ArrayList<ItemShpDeclnComdty> list = msgDecln.getShpDeclCmdtySecInput().getItemShpDeclnComdtyList();
			for (ItemShpDeclnComdty cmdtyItem : list) {
				MsgNCTSDeclarationPos item = this.mapFedexDeclnToGoodsItem(cmdtyItem);        		
	        	message.addGoodsItemList(item);
	        } 			
		}	
	}
	
	
	private MsgNCTSDeclarationPos mapFedexDeclnToGoodsItem(ItemShpDeclnComdty cmdtyItem) {
		if (cmdtyItem == null) {
			return null;
		}
		MsgNCTSDeclarationPos item = new MsgNCTSDeclarationPos();	
		item.setItemNumber(cmdtyItem.getItemNbr());
		if (!Utils.isStringEmpty(cmdtyItem.getTaricCmdtyCd()) //|| !Utils.isStringEmpty(cmdtyItem.getTariffAddnCd1())
				) {
			CommodityCode commodityCode = new CommodityCode();
			commodityCode.setTarifCode(cmdtyItem.getTaricCmdtyCd());
			/*
			commodityCode.setAddition(cmdtyItem.getTariffAddnCd1());
			commodityCode.setAddition(cmdtyItem.getTariffAddnCd2());
			commodityCode.setAddition(cmdtyItem.getTariffAddnCd3());
			*/
			item.setCommodityCode(commodityCode);
		}
		item.setDescription(cmdtyItem.getGoodsDesc());
		item.setNetMass(cmdtyItem.getItemNetWt());
		item.setGrossMass(cmdtyItem.getItemGrossWt());
		
		if (cmdtyItem.getShpHdrDoc() != null) {
			ArrayList<HdrDoc> list = cmdtyItem.getShpHdrDoc().getItemShpDeclnComdtyDocList();
			if (list != null) {
				for (HdrDoc hdr : list) {
					if (hdr != null) {
						Document doc = new Document();
						doc.setType(hdr.getHdrDocCode());
						doc.setReference(hdr.getHdrDocRef());
						doc.setAdditionalInformation(hdr.getHdrDocReasonCd());
						doc.setIssueDate(hdr.getDocDate());
						doc.setValue(hdr.getDocQty());
						item.addDocumentList(doc);
					}
				}
			}
		}
		if (cmdtyItem.getPkgCount() != null || cmdtyItem.getPkgKind() != null || 
				cmdtyItem.getPkgMarks() != null) {
			Packages pack = new Packages();
			pack.setQuantity(cmdtyItem.getPkgCount());
			pack.setType(cmdtyItem.getPkgKind());
			pack.setMarks(cmdtyItem.getPkgMarks());
			item.addPackagesList(pack);
		}
		
		return item;
	}

	private AddressNCTS mapFedexDeclnToAddress(DeclnCust customer) {			
		if (customer == null) {
			return null;
		}
		AddressNCTS address = new AddressNCTS();		
		address.setName(customer.getCompanyName());
		address.setStreet(customer.getStreet());
		address.setCity(customer.getCity());
		address.setPostalCode(customer.getZip());
		address.setCountry(customer.getCountryCode());
		address.setCountrySubEntity(customer.getStateCode());
				
		return address;
	}
	
}

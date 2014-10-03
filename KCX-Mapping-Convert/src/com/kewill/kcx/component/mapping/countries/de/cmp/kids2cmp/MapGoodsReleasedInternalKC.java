package com.kewill.kcx.component.mapping.countries.de.cmp.kids2cmp;

import java.io.StringWriter;
import java.util.ArrayList;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.countries.common.Address;
import com.kewill.kcx.component.mapping.countries.common.Party;
import com.kewill.kcx.component.mapping.countries.de.cmp.msg.MsgCustomsResponse;
import com.kewill.kcx.component.mapping.countries.de.cmp.msg.common.Custst;
import com.kewill.kcx.component.mapping.countries.de.cmp.msg.common.HeaderDetail;
import com.kewill.kcx.component.mapping.countries.de.cmp.msg.common.ItemDetails;
import com.kewill.kcx.component.mapping.countries.de.cmp.msg.common.PartyDetails;
import com.kewill.kcx.component.mapping.countries.de.cmp.msg.common.ResponseBody;
import com.kewill.kcx.component.mapping.countries.de.suma70.msg.MsgGoodsReleasedInternal;
import com.kewill.kcx.component.mapping.countries.de.suma70.msg.common.GoodsItem;
import com.kewill.kcx.component.mapping.countries.de.suma70.msg.common.NewCustodyValues;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessageManifest20;
import com.kewill.kcx.component.mapping.formats.kids.manifest20.BodyCustomsResponseKids;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Module        : Manifest / Suma<br>
 * Created       : 13.11.2012<br>
 * Description   : UidsBody of GoodsReleasedInternal.
 * 				 

 * @author kron
 * @version 1.0.00
 */

public class MapGoodsReleasedInternalKC extends KidsMessageManifest20 {
	
	private BodyCustomsResponseKids body = null;
	private MsgCustomsResponse cmp = null;
	private MsgGoodsReleasedInternal message = null;
	
	public MapGoodsReleasedInternalKC(XMLEventReader parser, String encoding) throws XMLStreamException {
		message = new MsgGoodsReleasedInternal(parser);
		cmp = new MsgCustomsResponse();
		this.encoding = encoding;
	}
	
	public String getMessage() {
		   StringWriter xmlOutputString = new StringWriter();
	        
	        XMLOutputFactory factory = XMLOutputFactory.newInstance();
	        try {
	            writer = factory.createXMLStreamWriter(xmlOutputString);
	            body   = new BodyCustomsResponseKids(writer);
	            //kidsHeader = new KidsHeader();
	            kidsHeader.setWriter((writer));
	            
	            writeStartDocument(encoding, "1.0");
	            
	          //EI20131001: openElement("soap:Envelope");
	          //EI20131001: setAttribute("xmlns:soap", "http://www.w3.org/2003/05/soap-envelope");
	            
	            message.parse(HeaderType.KIDS); 
	            mapKidsToCmpHeader(message.getReferenceNumber());           
	            //kidsHeader.writeHeader();
	               
	            mapKidsToCmpBody();
	            body.setMessage(cmp);

	            this.getCommonFieldsDTO().setReferenceNumber(message.getReferenceNumber());
	            body.setCommonFieldsDTO(this.getCommonFieldsDTO());  
	            body.writeBody("CUSTST");
	            
	          //EI20131001: closeElement();  // soap:Envelope
	            writer.writeEndDocument();
	            
	            writer.flush();
	            writer.close();
	            
            
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
        return xmlOutputString.toString();
    }
	
	private void mapKidsToCmpBody() {
		cmp.setMessageHeader(this.mapResponseHeader(message.getLocalApplication(), "CUSTST", message.getReferenceNumber()));
		cmp.setMessageBody(this.mapMessageBody());
	}
		
	private ResponseBody mapMessageBody() {
		ResponseBody mBody = new ResponseBody();		
		ArrayList<ItemDetails> list = new ArrayList<ItemDetails>();	
		Custst custst = new Custst();
		
		if (message.getLocalApplication() != null) {
			//EI20140324: mBody.setDeclarationStatus(message.getLocalApplication().getDeclarationStatus());	
		    //EI20140324: hier kann nur status 45 sein, aber wg. falschen Reihenfolge beim Zoll
			// kommt CUSTST vor CUSREC (es geht un Bruchstücken vor Sekunden)und deshalb bekommt LCAG status 30 (der in 
			// ZABIS erst nach erhalten von CUSREC auf 40 gesetzt wird). Da aber CUSTST von Zoll nur dann generiert wird, 
			// wenn vorhen CUSREC generiert wurde, ist dies hier OK:
			mBody.setDeclarationStatus("45");
		}
		mBody.setCustst(custst);
		custst.setHeaderDetail(this.mapHeaderDetail());	
		if (message.getGoodsItemList() != null) {
			for (GoodsItem item : message.getGoodsItemList()) {						
				list.add(this.mapItemDetails(item));
			}
		}
		custst.setItemDetailsList(list);
		
		return mBody;
	}
	
	private HeaderDetail mapHeaderDetail() {
		if (message == null) {
			return null;
		}
		
		HeaderDetail detail = new HeaderDetail();
		String flnr = "";
		if (message.getReferenceNumber() == null) {  //EI20140221
			return null;
		} else {
			if ((message.getReferenceNumber().length() > 16 || message.getReferenceNumber().startsWith("ATB")) &&
					message.getTransport() != null) {
				flnr = message.getTransport().getTransportationNumber();
			} else {
				flnr = this.calculateFlightNumber(message.getReferenceNumber());  //EI20120212
			}
		}		
		
		if (message.getLocalApplication() != null) {
			//detail.setFlightNumber(message.getLocalApplication().getFlightNumber());
			detail.setAirportOfDeparture(message.getLocalApplication().getAirportOfDeparture());
			detail.setDepartureDate(message.getLocalApplication().getDepartureDate());
			detail.setAirportOfArrival(message.getLocalApplication().getAirportOfArrival());	  //TODO-Daggi
			if (Utils.isStringEmpty(detail.getAirportOfArrival())) {
				detail.setAirportOfArrival(this.calculateAirportCodeFromKcxid(this.kidsHeader));
			}
			//EI20140401: detail.setRegistrationDate(message.getLocalApplication().getRegistrationDate());
		} else {
			//flnr = this.calculateFlightNumber(message.getReferenceNumber());  //EI20120212
			detail.setFlightNumber(flnr);
			detail.setAirportOfDeparture(this.calculateDepartureCode(message.getReferenceNumber()));
			detail.setDepartureDate(this.calculateDepartureDate(message.getReferenceNumber(), flnr));		
			//detail.setAirportOfArrival(???);	  //fss-besort = DestinationPlace =? AirportOfArrival				
			detail.setAirportOfArrival(this.calculateAirportCodeFromKcxid(this.kidsHeader));			
			//EI20140401: detail.setRegistrationDate(message.getDateTimeOfReceipt());
		}
		detail.setFlightNumber(flnr);  //EI20140221
		detail.setArrivalDate(message.getDateOfArrival()); 	
		detail.setCustomsRegistration(message.getRegistrationNumber());
		detail.setRegistrationDate(message.getDateTimeOfReceipt()); //EI20140401
		
		return detail;
	}
	
	private ItemDetails mapItemDetails(GoodsItem item) {
		if (item == null) {
			return null;
		}
		ItemDetails detail = new ItemDetails();
		
		detail.setLineItemNumber(item.getItemNumber());
		
		//EI20140317: detail.setLineStatus("45"); 
		if (message.getLocalApplication() != null) {  //EI20140317
			//detail.setLineStatus(message.getLocalApplication().getPositionStatus(item.getItemNumber()));  //aus TsKUP - neu
			detail.setLineStatus("45");  //EI20140324: gilt das gleiche, wie fuer den Kopf
		}
		
		if (item.getReferencedSpecification() != null) {
			detail.setWaybillNumber(item.getReferencedSpecification().getSpecificationID());
		}		
		detail.setItemDescription(item.getItemDescription());
		detail.setWeight(item.getGrossMass());
		if (item.getPackagesList() != null && item.getPackagesList().get(0) != null) {
			detail.setNumberOfPieces(item.getPackagesList().get(0).getQuantity());
			detail.setItemUnit(item.getPackagesList().get(0).getType());
		}		
		if (item.getItemExtension() != null) {
			detail.setCustomsStatus(item.getCustomsStatusOfGoods());
		}
		detail.setVub(item.getRangeOfGoodsCode());
		detail.setCustodyDeadline(item.getCustodyDeadline());
		detail.setCustodyDetails(mapCustody(item.getCustodian(), item.getNewCustodyValues()));
		detail.setDisposalDetails(mapDisposal(item.getDisposal()));
		detail.setCustodyWarehouse(mapCustodyWarehouse(item.getPlaceOfCustodyCode(), item.getPlaceOfCustody(), item.getNewCustodyValues()));
		
		return detail;
	}
	
	private PartyDetails mapCustody(Party itemParty, NewCustodyValues newParty) {
		if (itemParty == null && newParty == null) {
			return null;
		}
		PartyDetails party = new PartyDetails();
		
		if (itemParty != null && itemParty.getPartyTIN() != null) {
			party.setEori(itemParty.getPartyTIN().getTin());
			party.setBranch(itemParty.getPartyTIN().getBO());
		} else if (newParty != null && newParty.getCustodian() != null) {
			if (newParty.getCustodian().getTIN() != null) {
				party.setEori(newParty.getCustodian().getTIN().getTin());
				party.setBranch(newParty.getCustodian().getTIN().getBO());
			}
			if (newParty.getCustodian().getAddress() != null) {
				party.setName(newParty.getCustodian().getAddress().getName());
				party.setStreet(newParty.getCustodian().getAddress().getStreet());
				party.setZipCode(newParty.getCustodian().getAddress().getPostalCode());
				party.setCity(newParty.getCustodian().getAddress().getCity());
			}		
		}
		return party;
	}
	private PartyDetails mapDisposal(Party itemParty) {
		if (itemParty == null) {
			return null;
		}
		if (itemParty.getPartyTIN() == null) {
			return null;
		}
		PartyDetails party = new PartyDetails();		
		party.setEori(itemParty.getPartyTIN().getTin());
		party.setBranch(itemParty.getPartyTIN().getBO());
		
		return party;
	}
	private PartyDetails mapCustodyWarehouse(String code, Address itemAddress, NewCustodyValues newParty) {
		//if (itemAddress == null && newParty == null) {
		if (Utils.isStringEmpty(code) && itemAddress == null && newParty == null) {  //EI20140401
			return null;
		}
		PartyDetails party = new PartyDetails();
		if (!Utils.isStringEmpty(code)) {
			party.setName(code);			
		} else {		
			if (itemAddress != null) {
				party.setDescription(itemAddress.getName()); //oder code?				
				party.setStreet(itemAddress.getStreet());
				party.setZipCode(itemAddress.getPostalCode());
				party.setCity(itemAddress.getCity());
			} else if (newParty != null && newParty.getPlaceOfCustody() != null && newParty.getPlaceOfCustody().getAddress() != null) {
			Address address = newParty.getPlaceOfCustody().getAddress();
				party.setDescription(address.getName());	//oder code?		
				party.setStreet(address.getStreet());
				party.setZipCode(address.getPostalCode());
				party.setCity(address.getCity());
			}			
		}
		return party;
	}
}

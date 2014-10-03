package com.kewill.kcx.component.mapping.countries.de.cmp.kids2cmp;

import java.io.StringWriter;
import java.util.ArrayList;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;


import com.kewill.kcx.component.mapping.countries.de.cmp.msg.MsgCustomsResponse;
import com.kewill.kcx.component.mapping.countries.de.cmp.msg.common.Cusstp;
import com.kewill.kcx.component.mapping.countries.de.cmp.msg.common.HeaderDetail;
import com.kewill.kcx.component.mapping.countries.de.cmp.msg.common.ItemDetails;
import com.kewill.kcx.component.mapping.countries.de.cmp.msg.common.PartyDetails;
import com.kewill.kcx.component.mapping.countries.de.cmp.msg.common.ResponseBody;
import com.kewill.kcx.component.mapping.countries.de.suma70.msg.MsgControlDecision;
import com.kewill.kcx.component.mapping.countries.de.suma70.msg.common.GoodsItemControl;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessageManifest20;
import com.kewill.kcx.component.mapping.formats.kids.manifest20.BodyCustomsResponseKids;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;

/**
 * Module        : Manifest / Suma<br>
 * Created       : 03.07.2013<br>
 * Description   : Mapping of Kids ControlDecision (CUSSTP/SST) to CMP-CustomsResponse.
 * 				 

 * @author iwaniuk
 * @version 1.0.00
 */

public class MapControlDecisionKC extends KidsMessageManifest20 {
	
	private BodyCustomsResponseKids body = null;
	private MsgCustomsResponse cmp = null;
	private MsgControlDecision message = null;
	
	
	public MapControlDecisionKC(XMLEventReader parser, String encoding) throws XMLStreamException {
		message = new MsgControlDecision(parser);
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
            body.writeBody("CUSSTP");
            
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
		cmp.setMessageHeader(this.mapResponseHeader(message.getLocalApplication(), "CUSSTP", message.getReferenceNumber()));
		cmp.setMessageBody(this.mapMessageBody());
	}
	
	
	private ResponseBody mapMessageBody() {
		ResponseBody mBody = new ResponseBody();
		ArrayList<ItemDetails> list = new ArrayList<ItemDetails>();	
		Cusstp cusstp = new Cusstp();
			
		if (message.getLocalApplication() != null) {
			mBody.setDeclarationStatus(message.getLocalApplication().getDeclarationStatus());	
		}
		mBody.setCusstp(cusstp);
		cusstp.setHeaderDetail(this.mapHeaderDetail());
		
		if (message.getGoodsItemList() != null) {
			for (GoodsItemControl item : message.getGoodsItemList()) {							
				list.add(this.mapItemDetails(item));
			}
		}
		cusstp.setItemDetailsList(list);
		
		return mBody;
	}
	
	private HeaderDetail mapHeaderDetail() {
		if (message == null) {
			return null;
		}
		HeaderDetail detail = new HeaderDetail();
		String flnr = "";
		if (message.getLocalApplication() != null) {
			detail.setFlightNumber(message.getLocalApplication().getFlightNumber());
			detail.setAirportOfDeparture(message.getLocalApplication().getAirportOfDeparture());
			detail.setDepartureDate(message.getLocalApplication().getDepartureDate());
			detail.setAirportOfArrival(message.getLocalApplication().getAirportOfArrival());	 //TODO-Daggi 			
			detail.setArrivalDate(message.getLocalApplication().getArrivalDate());	      	     //TODO-Daggi
			if (Utils.isStringEmpty(detail.getAirportOfArrival())) {
				detail.setAirportOfArrival(this.calculateAirportCodeFromKcxid(this.kidsHeader));
			}
			//EI20140401: detail.setRegistrationDate(message.getLocalApplication().getRegistrationDate());
		} else {
			flnr = this.calculateFlightNumber(message.getReferenceNumber());
			detail.setFlightNumber(flnr);  //EI20140212);		
			detail.setAirportOfDeparture(this.calculateDepartureCode(message.getReferenceNumber())); 
			detail.setDepartureDate(this.calculateDepartureDate(message.getReferenceNumber(), flnr));  
			//detail.setAirportOfArrival(???);			
			detail.setAirportOfArrival(this.calculateAirportCodeFromKcxid(this.kidsHeader));			
			//detail.setArrivalDate(???);			
			//EI20140401: detail.setRegistrationDate(message.getDateTimeOfReceipt().substring(0, 8)); //max-excel
		}
		detail.setCustomsRegistration(message.getRegistrationNumber());	//sst.regnr			
		detail.setRegistrationDate(message.getDateTimeOfReceipt().substring(0, 8)); //EI20140401
		
		
		return detail;
	}
	
	private ItemDetails mapItemDetails(GoodsItemControl item) {
		if (item == null) {
			return null;
		}
		ItemDetails detail = new ItemDetails();
		
		detail.setLineItemNumber(item.getItemNumber());
		//detail.setLineStatus("45"); //email von Daggi 20130708 14:02: CUSSTP fix 45
		//stimmt nich es kann 45 oder 49 kommen:
		if (message.getLocalApplication() != null) { //EI20140317
			detail.setLineStatus(message.getLocalApplication().getPositionStatus(item.getItemNumber()));  //aus TsKUP - neu	
		}
		detail.setItemDescription(item.getItemDescription());  //EI20131022
		if (item.getReferencedSpecification() != null) {
			detail.setWaybillNumber(item.getReferencedSpecification().getSpecificationID());
		}			
		if (item.getPackagesList() != null && item.getPackagesList().get(0) != null) {
			detail.setNumberOfPieces(item.getPackagesList().get(0).getQuantity());
		}
		if (item.getControlDecision() != null) {
			detail.setActionCode(item.getControlDecision().getControlDecisionCode());
			detail.setActionInformation(item.getControlDecision().getAdditionalInformation());
		}
		
		if (item.getCustodian() != null) {			
			PartyDetails custodyDetails = new PartyDetails();	
			if (item.getCustodian().getPartyTIN() != null) {
				custodyDetails.setEori(item.getCustodian().getPartyTIN().getTin());
				custodyDetails.setBranch(item.getCustodian().getPartyTIN().getBO());
			}
			if (item.getCustodian().getAddress() != null) {
				custodyDetails.setName(item.getCustodian().getAddress().getName());
				custodyDetails.setStreet(item.getCustodian().getAddress().getStreet());
				custodyDetails.setZipCode(item.getCustodian().getAddress().getPostalCode());
				custodyDetails.setCity(item.getCustodian().getAddress().getCity());
			}			
			detail.setCustodyDetails(custodyDetails);
		}			
		return detail;
	}
		
}

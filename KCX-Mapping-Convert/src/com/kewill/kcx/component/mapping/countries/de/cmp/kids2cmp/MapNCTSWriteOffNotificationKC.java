package com.kewill.kcx.component.mapping.countries.de.cmp.kids2cmp;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.countries.de.cmp.msg.MsgCustomsResponse;
import com.kewill.kcx.component.mapping.countries.de.cmp.msg.common.Cusrec;
import com.kewill.kcx.component.mapping.countries.de.cmp.msg.common.ErrorDetails;
import com.kewill.kcx.component.mapping.countries.de.cmp.msg.common.GuarantyDetails;
import com.kewill.kcx.component.mapping.countries.de.cmp.msg.common.HeaderDetail;
import com.kewill.kcx.component.mapping.countries.de.cmp.msg.common.HeaderNotification;
import com.kewill.kcx.component.mapping.countries.de.cmp.msg.common.ItemDetails;
import com.kewill.kcx.component.mapping.countries.de.cmp.msg.common.ItemNotification;
import com.kewill.kcx.component.mapping.countries.de.cmp.msg.common.PartyDetails;
import com.kewill.kcx.component.mapping.countries.de.cmp.msg.common.ResponseBody;
import com.kewill.kcx.component.mapping.countries.de.cmp.msg.common.Tufsta;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.FunctionalErrorNcts;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.PartyNCTS;
import com.kewill.kcx.component.mapping.countries.de.ncts20.msg.MsgNCTSWriteOffNotification;
import com.kewill.kcx.component.mapping.countries.de.suma70.msg.common.LocalAppPosition;
import com.kewill.kcx.component.mapping.countries.de.suma70.msg.common.LocalApplication;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessageManifest20;
import com.kewill.kcx.component.mapping.formats.kids.manifest20.BodyCustomsResponseKids;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;
/**
 * Module        : Manifest / NCTS<br>
 * Created       : 03.07.2013<br>
 * Description   : Mapping of Kids NCTSWriteOffNotification (VSO) to CMP- CustomsResponse.
 * 				 

 * @author iwaniuk
 * @version 1.0.00
 */

public class MapNCTSWriteOffNotificationKC extends KidsMessageManifest20 {
	
	private BodyCustomsResponseKids body = null;
	private MsgCustomsResponse cmp = null;
	private MsgNCTSWriteOffNotification message = null;
	
	
	public MapNCTSWriteOffNotificationKC(XMLEventReader parser, String encoding) throws XMLStreamException {
		message = new MsgNCTSWriteOffNotification(parser);
		cmp = new MsgCustomsResponse();
		this.encoding = encoding;
	}
	
	public String getMessage() {
        StringWriter xmlOutputString = new StringWriter();
        
        XMLOutputFactory factory = XMLOutputFactory.newInstance();
        try {
            writer = factory.createXMLStreamWriter(xmlOutputString);
            body   = new BodyCustomsResponseKids(writer);
            
            kidsHeader.setWriter((writer));
            this.mapKidsToCmpHeader(message.getReferenceNumber());
            
            message.parse(HeaderType.KIDS);
            this.getCommonFieldsDTO().setReferenceNumber(message.getReferenceNumber());
            body.setCommonFieldsDTO(this.getCommonFieldsDTO());      
            this.mapKidsToCmpBody();
            body.setMessage(cmp);
            
            writeStartDocument(encoding, "1.0");            
          //EI20131001: openElement("soap:Envelope");
          //EI20131001: setAttribute("xmlns:soap", "http://www.w3.org/2003/05/soap-envelope");
          //kidsHeader.writeHeader();
         
            body.writeBody("TUFSTA");
            
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
		String subType = "NCTS";
		if (this.kidsHeader != null && this.kidsHeader.getCountryCode() != null) {
			subType = subType + "-" + this.kidsHeader.getCountryCode();
		}
		if (message.getLocalApplication() == null) {
			LocalApplication app = new LocalApplication();			
			message.setLocalApplication(app);			
		}	
		message.getLocalApplication().setMessageSubType(subType);
		cmp.setMessageHeader(this.mapResponseHeader(message.getLocalApplication(), "TUFSTA", message.getReferenceNumber()));
		cmp.setMessageBody(this.mapMessageBody());
	}
		
	private ResponseBody mapMessageBody() {
		ResponseBody mBody = new ResponseBody();
		Tufsta tufsta = new Tufsta();
		Cusrec cusrec = new Cusrec();
		
		if (message.getLocalApplication() != null) {
			mBody.setDeclarationStatus(message.getLocalApplication().getDeclarationStatus());	
		}
		mBody.setTufsta(tufsta);
		mBody.setCusrec(cusrec);
		
		tufsta.setHeaderDetail(this.mapHeaderDetail("TUFSTA"));
		cusrec.setHeaderDetail(this.mapHeaderDetail("CUSREC"));
		cusrec.setHeaderNotification(this.mapCusrecHeaderNotification());        //EI20140127
		cusrec.setItemDetailsList(this.mapCusrecHeaderDetailItemDetailsList());  //EI20140127
		
		return mBody;
	}
	
	private HeaderDetail mapHeaderDetail(String part) {
		if (message == null) {
			return null;
		}
		HeaderDetail detail = new HeaderDetail();
		String flnr = "";
			
		if (message.getLocalApplication() != null) {  //EI20140118 kommt von FSS.TsVER
			flnr = message.getLocalApplication().getFlightNumber();
			detail.setFlightNumber(flnr);
			detail.setAirportOfDeparture(message.getLocalApplication().getAirportOfDeparture()); 
			if (Utils.isStringEmpty(detail.getAirportOfDeparture())) {  //EI20140304
				detail.setAirportOfDeparture(this.calculateAirportCodeFromKcxid(this.kidsHeader));
			}
			detail.setDepartureDate(message.getLocalApplication().getDepartureDate());  
			detail.setAirportOfArrival(message.getLocalApplication().getAirportOfArrival());
			if (Utils.isStringEmpty(detail.getAirportOfArrival())) {  //EI20140304
				detail.setAirportOfArrival(this.calculateDepartureDate(message.getReferenceNumber(), flnr)); 
			}
			detail.setArrivalDate(message.getLocalApplication().getArrivalDate());			
		} else {
			flnr = this.calculateFlightNumber(message.getReferenceNumber());
			detail.setFlightNumber(flnr);  //EI20140212);	
			detail.setDepartureDate(this.calculateDepartureDate(message.getReferenceNumber(), flnr));  
			//detail.setAirportOfDeparture(???); 
			detail.setAirportOfDeparture(this.calculateAirportCodeFromKcxid(this.kidsHeader));
			detail.setAirportOfArrival(this.calculateDepartureDate(message.getReferenceNumber(), flnr)); 
			//detail.setArrivalDate(???);
		}
		
		if (part != null && part.equalsIgnoreCase("CUSREC")) {    //EI20140128
			detail.setCustomsRegistration(message.getLocalApplication().getRegistrationNumber());
			detail.setRegistrationDate(message.getLocalApplication().getRegistrationDate());
		}
		if (part != null && part.equalsIgnoreCase("TUFSTA")) {		 //EI20140128
			detail.setCustomsStatus(message.getStatusOfControl());
			detail.setTemporaryMRN(message.getTemporaryUCR());
			detail.setMrn(message.getUcrNumber());		
			detail.setReleaseDate(message.getReleaseTime());
			detail.setCompletionDate(message.getCompletionTime());
			detail.setReason(message.getStatusInformation());
			//EI20140515: detail.setCustomsOfficeOfDeparture(message.getOfficeOfDeparture());
			detail.setCustomsOfficeOfDeparture(getOfficeOfDeparture(message.getUcrNumber()));     //EI20140515
			detail.setPrincipalDetails(mapPrincipal(message.getPrincipal()));
			detail.setGuarantyDetailsList(mapGuaranty(message.getGrnErrorList()));
			detail.setErrorDetailsList(mapError(message.getFunctionalErrorList()));
		}
		
		return detail;
	}
		
	private HeaderNotification mapCusrecHeaderNotification() {   //EI20140127
		if (message == null) {
			return null;
		}
		return null;
	}
	private ArrayList<ItemDetails> mapCusrecHeaderDetailItemDetailsList() {  //EI20140127
		//201403013: neue TsVEP mit mehreren AWBs
		
		if (message == null) {
			return null;
		}
		if (message.getLocalApplication() == null) {
			return null;
		}
		if (message.getLocalApplication().getPositionList() == null) {
			return null;
		}
		ArrayList<ItemDetails> list = new ArrayList<ItemDetails>();		
		for (LocalAppPosition appPos : message.getLocalApplication().getPositionList()) {
			ItemDetails detail = new ItemDetails();
			detail.setLineItemNumber(appPos.getPositionNumber());  		
			detail.setLineStatus(appPos.getPositionStatus());  			
			detail.setWaybillNumber(appPos.getPositionAWB());  			
			detail.setItemNotification(this.createItemNotificationForTufsta(message.getUcrNumber()));  //EI20140515
			list.add(detail);
		}
		return list;		
	}
	private ItemNotification createItemNotificationForTufsta(String mrn) {   //EI20140515
		ItemNotification itNot = new ItemNotification();
		itNot.setNotificationType("INF");
		itNot.setNotificationDetails("Es wurde ein NCTS-Vorgang mit MRN " + mrn + " angelegt.");
		
		return itNot;	
	}
	private String getOfficeOfDeparture(String mrn) {   //EI20140515
		String office = "";
		if (!Utils.isStringEmpty(mrn) && mrn.length() > 8) {
			office = mrn.substring(2, 4) + "00" +  mrn.substring(4, 8);
		}
		return office;
	}
	
	private PartyDetails mapPrincipal(PartyNCTS itemParty) {
		if (itemParty == null) {
			return null;
		}
		PartyDetails party = new PartyDetails();
		if (itemParty.getPartyTIN() != null) {
			party.setEori(itemParty.getPartyTIN().getTin());
			party.setBranch(itemParty.getPartyTIN().getBO());
		}
		if (itemParty.getAddress() != null) {
			party.setName(itemParty.getAddress().getName());
			party.setStreet(itemParty.getAddress().getStreet());
			party.setZipCode(itemParty.getAddress().getPostalCode());
			party.setCity(itemParty.getAddress().getCity());
			party.setCountry(itemParty.getAddress().getCountry());
		}
		if (itemParty.getContactPerson() != null) {		
			party.setContact(itemParty.getContactPerson());			
		}
		return party;
	}
	private ArrayList<GuarantyDetails>  mapGuaranty(List<FunctionalErrorNcts> itemList) {
		if (itemList == null) {
			return null;
		}
		ArrayList<GuarantyDetails> list = new ArrayList<GuarantyDetails>();
		for (FunctionalErrorNcts err : itemList) {
			GuarantyDetails detail = new GuarantyDetails();
			detail.setGrn(err.getGRN());
			detail.setInvalidCode(err.getCode());
			detail.setInvalidText(err.getText());
			list.add(detail);
		}
		return list;
	}
	
	private ArrayList<ErrorDetails> mapError(List<FunctionalErrorNcts> itemList) {
		if (itemList == null) {
			return null;
		}
		ArrayList<ErrorDetails> list = new ArrayList<ErrorDetails>();
		for (FunctionalErrorNcts err : itemList) {
			ErrorDetails detail = new ErrorDetails();
			detail.setErrorText(err.getText());
		}
		return list;
	}
}

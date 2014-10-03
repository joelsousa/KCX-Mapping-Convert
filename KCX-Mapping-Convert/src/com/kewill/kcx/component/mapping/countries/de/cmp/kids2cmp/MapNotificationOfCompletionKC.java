package com.kewill.kcx.component.mapping.countries.de.cmp.kids2cmp;

import java.io.StringWriter;
import java.util.ArrayList;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.countries.de.cmp.msg.MsgCustomsResponse;
import com.kewill.kcx.component.mapping.countries.de.cmp.msg.common.Cusfin;
import com.kewill.kcx.component.mapping.countries.de.cmp.msg.common.HeaderDetail;
import com.kewill.kcx.component.mapping.countries.de.cmp.msg.common.HeaderNotification;
import com.kewill.kcx.component.mapping.countries.de.cmp.msg.common.ItemDetails;
import com.kewill.kcx.component.mapping.countries.de.cmp.msg.common.ItemNotification;
import com.kewill.kcx.component.mapping.countries.de.cmp.msg.common.PartyDetails;
import com.kewill.kcx.component.mapping.countries.de.cmp.msg.common.ResponseBody;
import com.kewill.kcx.component.mapping.countries.de.suma70.msg.MsgNotificationOfCompletion;
import com.kewill.kcx.component.mapping.countries.de.suma70.msg.common.GoodsItemCompletion;
import com.kewill.kcx.component.mapping.countries.de.suma70.msg.common.ItemProcessingResults;
import com.kewill.kcx.component.mapping.countries.de.suma70.msg.common.Notification;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessageManifest20;
import com.kewill.kcx.component.mapping.formats.kids.manifest20.BodyCustomsResponseKids;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner.HeaderType;
/**
 * Module        : Manifest / Suma<br>
 * Created       : 03.07.2013<br>
 * Description   : Mapping of Kids ProcessingResults (SCK) to CMP- CustomsResponse.
 * 				 

 * @author iwaniuk
 * @version 1.0.00
 */

public class MapNotificationOfCompletionKC extends KidsMessageManifest20 {
	
	private BodyCustomsResponseKids body = null;
	private MsgCustomsResponse cmp = null;
	private MsgNotificationOfCompletion message = null;
	
	
	public MapNotificationOfCompletionKC(XMLEventReader parser, String encoding) throws XMLStreamException {
		message = new MsgNotificationOfCompletion(parser);
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
           
            message.parse(HeaderType.KIDS);
            mapKidsToCmpHeader(message.getReferenceNumber());     
            
            writeStartDocument(encoding, "1.0");
            //EI20131001: openElement("soap:Envelope");
            //EI20131001: setAttribute("xmlns:soap", "http://www.w3.org/2003/05/soap-envelope");
            //EI20131001: kidsHeader.writeHeader();
           
            mapKidsToCmpBody();
            body.setMessage(cmp);

            this.getCommonFieldsDTO().setReferenceNumber(message.getReferenceNumber());
            body.setCommonFieldsDTO(this.getCommonFieldsDTO());  
            body.writeBody("CUSFIN");
            
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
		cmp.setMessageHeader(this.mapResponseHeader(message.getLocalApplication(), "CUSFIN", message.getReferenceNumber()));
		cmp.setMessageBody(this.mapMessageBody());
	}
		
	private ResponseBody mapMessageBody() {
		ResponseBody mBody = new ResponseBody();
		Cusfin cusfin = new Cusfin();
		ArrayList<ItemDetails> list = new ArrayList<ItemDetails>();		
		
		if (message.getLocalApplication() != null) {
			mBody.setDeclarationStatus(message.getLocalApplication().getDeclarationStatus());	
		}
		mBody.setCusfin(cusfin);
		cusfin.setHeaderDetail(this.mapHeaderDetail());
		cusfin.setTypeOfCompletion(message.getTypeOfTransaction());                       //EI20140217
		cusfin.setCompletionAtlasReference(message.getRegistrationNumberOfCompletion());  //EI20140217
		cusfin.setCompletionOtherReference(message.getCompletionOtherReference());
		//CcustodyDetalis aus dem ersten GoodsItem
		int i = 0;		
		if (message.getGoodsItemList() != null) {
			for (GoodsItemCompletion item : message.getGoodsItemList()) {							
				list.add(this.mapItemDetails(item));
				
				if (i == 0) {					
					if (item.getCustodianTIN() != null) {
						i = i + 1;
						PartyDetails custodyDetails = new PartyDetails();
						custodyDetails.setEori(item.getCustodianTIN().getTin());
						custodyDetails.setBranch(item.getCustodianTIN().getBO());
						cusfin.setCustodyDetails(custodyDetails);
					}
				}
			}
		}
		cusfin.setItemDetailsList(list);
	
		return mBody;
	}
	
	private HeaderDetail mapHeaderDetail() {
		if (message == null) {
			return null;
		}
		HeaderDetail detail = new HeaderDetail();
		String flnr = "";
		
		/*
		if (message.getTransport() != null) {
			detail.setFlightNumber(message.getTransport().getTransportationNumber());		
			detail.setAirportOfDeparture(this.calculateDepartureCode(message.getReferenceNumber(), 
										message.getTransport().getTransportationNumber()));  
			detail.setDepartureDate(this.calculateDepartureDate(message.getReferenceNumber(), 
										message.getTransport().getTransportationNumber()));  
		}
		*/
		if (message.getLocalApplication() != null) {
			flnr = message.getLocalApplication().getFlightNumber();
			detail.setFlightNumber(flnr);
			detail.setAirportOfDeparture(message.getLocalApplication().getAirportOfDeparture()); 
			detail.setDepartureDate(message.getLocalApplication().getDepartureDate());  
			detail.setAirportOfArrival(message.getLocalApplication().getAirportOfArrival());	  //TODO-Daggi
			if (Utils.isStringEmpty(detail.getAirportOfArrival())) {
				detail.setAirportOfArrival(this.calculateAirportCodeFromKcxid(this.kidsHeader));
			}
			//EI20140401: detail.setRegistrationDate(message.getLocalApplication().getRegistrationDate());
		} else {			
			flnr = this.calculateFlightNumber(message.getReferenceNumber());  //EI20120212
			detail.setFlightNumber(flnr);
			detail.setAirportOfDeparture(this.calculateDepartureCode(message.getReferenceNumber()));
			detail.setDepartureDate(this.calculateDepartureDate(message.getReferenceNumber(), flnr));		
			//detail.setAirportOfArrival(???);	  //fss-besort = DestinationPlace =? AirportOfArrival				
			detail.setAirportOfArrival(this.calculateAirportCodeFromKcxid(this.kidsHeader));			
			//EI20140401: detail.setRegistrationDate(message.getDateTimeOfReceipt());  
		}
		detail.setArrivalDate(message.getDateOfArrival());	
		detail.setCustomsRegistration(message.getRegistrationNumber()); //EI20140217
	    detail.setRegistrationDate(message.getDateTimeOfReceipt()); //EI20140401
		
		return detail;
	}
	
	private ItemDetails mapItemDetails(GoodsItemCompletion item) {
		if (item == null) {
			return null;
		}
		ItemDetails detail = new ItemDetails();
		
		detail.setLineItemNumber(item.getItemNumber());
		
	    //aus TsKUN - 60 oder 45		
		if (message.getLocalApplication() != null) {  //EI20140317
			detail.setLineStatus(message.getLocalApplication().getPositionStatus(item.getItemNumber()));  //aus TsKUP - neu	
		}
		
		if (item.getReferencedSpecification() != null) {
			detail.setWaybillNumber(item.getReferencedSpecification().getSpecificationID());
		}	
		detail.setCustomsRegistration(item.getRegistrationNumber());
		detail.setRegistrationDate(item.getDateOfCompletion());  //TODO ??? erldat - ist es richtig? oder aud TsKUN

		if (item.getPackagesList() != null && item.getPackagesList().get(0) != null) {
			detail.setNumberOfPieces(item.getPackagesList().get(0).getQuantity());
		}
		detail.setCancellationCode(item.getCancellationCode());		
		
		return detail;
	}	
}

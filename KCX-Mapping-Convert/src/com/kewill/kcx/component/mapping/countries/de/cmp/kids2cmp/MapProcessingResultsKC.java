package com.kewill.kcx.component.mapping.countries.de.cmp.kids2cmp;

import java.io.StringWriter;
import java.util.ArrayList;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.countries.de.cmp.msg.MsgCustomsResponse;
import com.kewill.kcx.component.mapping.countries.de.cmp.msg.common.Cusrec;
import com.kewill.kcx.component.mapping.countries.de.cmp.msg.common.HeaderDetail;
import com.kewill.kcx.component.mapping.countries.de.cmp.msg.common.HeaderNotification;
import com.kewill.kcx.component.mapping.countries.de.cmp.msg.common.ItemDetails;
import com.kewill.kcx.component.mapping.countries.de.cmp.msg.common.ItemNotification;
import com.kewill.kcx.component.mapping.countries.de.cmp.msg.common.ResponseBody;
import com.kewill.kcx.component.mapping.countries.de.suma70.msg.MsgProcessingResults;
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

public class MapProcessingResultsKC extends KidsMessageManifest20 {
	
	private BodyCustomsResponseKids body = null;
	private MsgCustomsResponse cmp = null;
	private MsgProcessingResults message = null;
	private boolean sendToLcag = true;             //EI20140407
	
	
	public MapProcessingResultsKC(XMLEventReader parser, String encoding) throws XMLStreamException {
		message = new MsgProcessingResults(parser);
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
            body.setMessage(cmp);
            this.getCommonFieldsDTO().setReferenceNumber(message.getReferenceNumber());
            body.setCommonFieldsDTO(this.getCommonFieldsDTO());  
          
            if (message.getLocalApplication() != null && message.getLocalApplication().getMessageSubType() != null && message.getLocalApplication().getMessageFunction() != null && 
            	message.getLocalApplication().getMessageSubType().equals("VSA") &&
            	message.getLocalApplication().getMessageFunction().equals("ERR")) {            	
            	Utils.log("KidsToCmp ERR-Message for VSA will be mapped as RESPONSE." + xmlOutputString.toString());
            	message.getLocalApplication().setMessageFunction("RESPONSE");
            }
            this.mapKidsToCmpBody();
            
            
            //EI20140407-beginn:
            if (message.getLocalApplication() != null && message.getLocalApplication().getMessageSubType() != null && 
            		(message.getLocalApplication().getMessageSubType().equalsIgnoreCase("ReEx") ||
            		 message.getLocalApplication().getMessageSubType().equalsIgnoreCase("WAV"))) {
            	sendToLcag = false;            	
            }
          //EI20140407-end                      
               
            if (sendToLcag) {
            	writeStartDocument(encoding, "1.0"); 
            	body.writeBody("CUSREC");   
            	writer.writeEndDocument();
            	writer.flush();
                writer.close();
            } else {
            	Utils.log("ReExport-Response will not be send to LCAG");
            	/* 
            	kidsHeader.setTransmitter("DE.KCX.KWL");
            	kidsHeader.setReceiver("DE.KCX.KWL");
            	kidsHeader.setMethod("ReExport");
            	kidsHeader.setDirection("Response will not be send to LCAG");
            	
            	writeStartDocument(encoding, "1.0"); 
            	openElement("soap:Envelope");
            		setAttribute("xmlns:soap", "http://www.w3.org/2003/05/soap-envelope");                 
            		kidsHeader.writeHeader();
            		//body.writeBody("CUSREC");
            	closeElement();  
                writer.writeEndDocument();
                writer.flush();
            	writer.close();
             */
            }                      
            
            
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
        return xmlOutputString.toString();
    }
	
	private void mapKidsToCmpBody() {
		cmp.setMessageHeader(this.mapResponseHeader(message.getLocalApplication(), "CUSREC", message.getReferenceNumber()));
		cmp.setMessageBody(this.mapMessageBody());
	}
	
	
	private ResponseBody mapMessageBody() {
		ResponseBody mBody = new ResponseBody();
		Cusrec cusrec = new Cusrec();
		ArrayList<ItemDetails> list = new ArrayList<ItemDetails>();		
		if (message.getLocalApplication() != null) {
			mBody.setDeclarationStatus(message.getLocalApplication().getDeclarationStatus());	
		}
		mBody.setCusrec(cusrec);
		cusrec.setHeaderDetail(this.mapHeaderDetail());
		cusrec.setHeaderNotification(this.mapHeaderNotification(message.getNotificationList()));
		if (message.getGoodsItemList() != null) {
			for (ItemProcessingResults item : message.getGoodsItemList()) {						
				list.add(this.mapItemDetails(item));
			}
		}
		cusrec.setItemDetailsList(list);
		
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
			detail.setFlightNumber(message.getTransport().getTransportationNumber());	//sck.befnum			
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
			detail.setAirportOfArrival(message.getLocalApplication().getAirportOfArrival());	 //TODO-Daggi
			if (Utils.isStringEmpty(detail.getAirportOfArrival())) {
				detail.setAirportOfArrival(this.calculateAirportCodeFromKcxid(this.kidsHeader));
			}
			detail.setRegistrationDate(message.getLocalApplication().getRegistrationDate());  //sck.regdat
		} else {
			flnr = this.calculateFlightNumber(message.getReferenceNumber());
			detail.setFlightNumber(flnr);  //EI20140212);	
			detail.setAirportOfDeparture(this.calculateDepartureCode(message.getReferenceNumber())); 
			detail.setDepartureDate(this.calculateDepartureDate(message.getReferenceNumber(), flnr));  
			//detail.setAirportOfArrival(???);  //fss-besort = DestinationPlace == AirportOfArrival		
			detail.setAirportOfArrival(this.calculateAirportCodeFromKcxid(this.kidsHeader));  //EI20140214: zumindest fuer LCAG stimmt es
			detail.setRegistrationDate(message.getDateTimeOfReceipt());  //sck.regdat
		}
		detail.setArrivalDate(message.getDateOfArrival());   //sck.ankdat
		detail.setCustomsRegistration(message.getRegistrationNumber()); //sck.regnr
		//detail.setRegistrationDate(message.getRegistrationDate());  //sck.regdat
		
		return detail;
	}
	
	private ItemDetails mapItemDetails(ItemProcessingResults item) {
		if (item == null) {
			return null;
		}
		ItemDetails detail = new ItemDetails();
		
		detail.setLineItemNumber(item.getItemNumber());   //scp.posnr
		/* email von Daggi 20130708 14:02			
		 * detail.setLineStatus("90"); //email von Daggi 20130708 14:02: CUSREC fix 90
		 /* EI20131008: stimmt nicht! da wir bei VSA auch INF bekommen, und status 90 ist wohl ERR
		 */
		
		if (message.getLocalApplication() != null) {
			//detail.setLineStatus(message.getLocalApplication().getPositionStatus());  
			//EI20140317: detail.setLineStatus(message.getLocalApplication().getDeclarationStatus());
			
			//EI20140317: aus TsKUP - neu:		
			detail.setLineStatus(message.getLocalApplication().getPositionStatus(item.getItemNumber()));  
		}		
		if (item.getReferencedSpecification() != null) {
			detail.setWaybillNumber(item.getReferencedSpecification().getSpecificationID());  //scp.awbzzz
		}		
		detail.setItemNotification(mapItemNotification(item.getNotification()));
		
		return detail;
	}
	
	private HeaderNotification mapHeaderNotification(ArrayList<Notification> list) {		
		ArrayList<String> descList = new ArrayList<String>();
		
		if (list == null) {
			return null;
		}
		HeaderNotification header = new HeaderNotification();		
		for (Notification noti : list) {
			if (noti != null) {
				header.setNotificationWeight(noti.getNotificationType()); //darf hier unterschiedlich typen geben?							
				if (noti.getNotificationDescription() != null) {				
					descList.add(noti.getNotificationDescription());
				}
			}
		}		
		header.setNotificationDetailsList(descList);
		
		return header;
	}
	
	private ItemNotification mapItemNotification(Notification notification) {
		if (notification == null) {
			return null;
		}
		ItemNotification noti = new ItemNotification();
		noti.setNotificationType(notification.getNotificationType());
		noti.setNotificationDetails(notification.getNotificationDescription());
		
		return noti;
	}
}

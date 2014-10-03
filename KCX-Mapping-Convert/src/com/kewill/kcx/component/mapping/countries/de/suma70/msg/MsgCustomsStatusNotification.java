package com.kewill.kcx.component.mapping.countries.de.suma70.msg;


import java.util.ArrayList;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.common.ContactPerson;
import com.kewill.kcx.component.mapping.countries.common.Party;
import com.kewill.kcx.component.mapping.countries.common.TIN;
import com.kewill.kcx.component.mapping.countries.common.Trader;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.AddressType;
import com.kewill.kcx.component.mapping.countries.de.suma62.msg.common.HeaderExtensions;
import com.kewill.kcx.component.mapping.countries.de.suma62.msg.common.ReferencedSpecification;
import com.kewill.kcx.component.mapping.countries.de.suma70.msg.common.FlightDetails;
import com.kewill.kcx.component.mapping.countries.de.suma70.msg.common.GoodsItemReexport;
import com.kewill.kcx.component.mapping.countries.de.suma70.msg.common.CustomsNotification;
import com.kewill.kcx.component.mapping.countries.de.suma70.msg.common.Transport;
import com.kewill.kcx.component.mapping.util.Utils;
/**
 * Module		: Manifest<br>.
 * Created		: 16.12.2013<br>
 * Description	: KIDS-CustomsStatusNotification
 * 
 * @author iwaniuk
 * @version 2.0.00
 */

public class MsgCustomsStatusNotification extends KCXMessage { 
	
	private String msgName = "CustomsStatusNotification";
	private ContactPerson contact;
	private String dateTimeOfReceipt;
	private ReferencedSpecification reference;	
	private Transport transport;     //flightNumber;
	private String dateTimeOfArrival;
	private String airportOfArrival;
	private String airportOfDeparture;  //EI20140123
	private String officeOfEntry;	
	private String customsId;
	private String totalPieces;		
	private CustomsNotification customsNotification;   
	private String countryOfDispatch;
	private String destinationPlace;	
	
	public MsgCustomsStatusNotification() {
		super();
	}
	public MsgCustomsStatusNotification(XMLEventReader parser)throws XMLStreamException {
		super(parser);
	}
	
	private enum EReExport {
		//KIDS 					 	UIDS:		
		Contact,
		DateTimeOfReceipt,
		ReferencedSpecification,		
		MeansOfTransport,           Transport,	
		AirportOfDeparture,
		DateTimeOfArrival,	
		AirportOfArrival,		
		OfficeOfEntry,
		CustomsId,
		TotalPieces,
		CountryOfDispatch,
		DestinationPlace,
		CustomsNotification;	
	}
	
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
			switch ((EReExport) tag) {
			
			case Contact:
				contact = new ContactPerson(getScanner());
				contact.parse(tag.name());
				break;	
								
			case Transport:
			case MeansOfTransport:      
				transport = new Transport(getScanner());
				transport.parse(tag.name());
				break;	
				
			case ReferencedSpecification:
				reference = new ReferencedSpecification(getScanner());
				reference.parse(tag.name());
				break;	
							
			case CustomsNotification:
				customsNotification = new CustomsNotification(getScanner());
				customsNotification.parse(tag.name());
				break;				
			
			default:
				return;
			}
		} else {
			switch ((EReExport) tag) {
			
			case DateTimeOfReceipt:
				setDateTimeOfReceipt(value);
				break;
				
			case DateTimeOfArrival:
				setDateTimeOfArrival(value);
				break;

			case AirportOfArrival:
				setAirportOfArrival(value);
				break;
				
			case OfficeOfEntry:
				setOfficeOfEntry(value);
				break;
				
			case CustomsId:
				setCustomsId(value);
				break;
			case TotalPieces:
				setTotalPieces(value);
				break;
						
			case CountryOfDispatch:
				setCountryOfDispatch(value);
				break;
				
			case DestinationPlace:
				setDestinationPlace(value);
				break;
				
			case AirportOfDeparture:
				setAirportOfDeparture(value);
				break;
				
			default:
				return;	
			}
		}
	}

	public void stoppElement(Enum tag) {
		
	}

	public Enum translate(String token) {
		try {
			return EReExport.valueOf(token);
		} catch (IllegalArgumentException e) {
		return null;
		}
	}
	
	public String getMsgName() {
		return msgName;
	}
	public void setMsgName(String msgName) {
		this.msgName = msgName;
	}
	
	
	public Transport getTransport() {
		return transport;
	}
	public void setTransport(Transport transport) {
		this.transport = transport;
	}
	
	public ContactPerson getContact() {
		return contact;
	}
	public void setContact(ContactPerson contact) {
		this.contact = contact;
	}
	
	public String getDateTimeOfReceipt() {
		return dateTimeOfReceipt;
	}
	public void setDateTimeOfReceipt(String date) {
		this.dateTimeOfReceipt = date;
	}
	
	public ReferencedSpecification getReferencedSpecification() {
		return reference;
	}
	public void setReferencedSpecification(ReferencedSpecification reference) {
		this.reference = reference;
	}
	
	public String getDateTimeOfArrival() {
		return dateTimeOfArrival;
	}
	public void setDateTimeOfArrival(String dateTimeOfArrival) {
		this.dateTimeOfArrival = dateTimeOfArrival;
	}
	
	public String getAirportOfArrival() {
		return airportOfArrival;
	}
	public void setAirportOfArrival(String airportOfArrival) {
		this.airportOfArrival = airportOfArrival;
	}
	
	public String getOfficeOfEntry() {
		return officeOfEntry;
	}
	public void setOfficeOfEntry(String officeOfEntry) {
		this.officeOfEntry = officeOfEntry;
	}
	
	public String getCustomsId() {
		return customsId;
	}
	public void setCustomsId(String customsId) {
		this.customsId = customsId;
	}
	
	public String getTotalPieces() {
		return totalPieces;
	}
	public void setTotalPieces(String totalPieces) {
		this.totalPieces = totalPieces;
	}
	
	public CustomsNotification getCustomsNotification() {
		return customsNotification;
	}
	public void setCustomsNotification(CustomsNotification entrySuma) {
		this.customsNotification = entrySuma;
	}
	public String getCountryOfDispatch() {
		return countryOfDispatch;
	}
	public void setCountryOfDispatch(String countryOfDispatch) {
		this.countryOfDispatch = countryOfDispatch;
	}
	
	public String getDestinationPlace() {
		return destinationPlace;
	}
	public void setDestinationPlace(String destinationPlace) {
		this.destinationPlace = destinationPlace;
	}
	
	public String getAirportOfDeparture() {
		return airportOfDeparture;
	}
	public void setAirportOfDeparture(String value) {
		this.airportOfDeparture = value;
	}
}

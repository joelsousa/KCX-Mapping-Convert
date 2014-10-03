package com.kewill.kcx.component.mapping.countries.de.ncts.msg.common;

import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLEventReader;

import org.xml.sax.Attributes;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: Security
 * Created		: 02.09.2010
 * Description	: contains Security data with all fields used in KIDS/UIDS.
 * 
 * @author Lassiter
 * @version 4.0.00
 */
public class Security extends KCXMessage {
	
	private String					shipmentNumber;
	private String					placeOfLoading;
	private String					placeOfUnloading;
	private List<String>			itineraryList;
	
	private XMLEventReader parser	= null;
	private enum ESecurity {
		//KIDS					UIDS
		ShipmentNumber,			//same
		PlaceOfLoading,			//same
		PlaceOfUnloading,		//same
		Itinerary;				//same	
	}
	
	public Security() {
		super();
	}
	
	public Security(XMLEventReader parser) {
		super(parser);
		this.setParser(parser);
	}
	public Security(XmlMsgScanner scanner) {
		super(scanner);
	}

	public void startElement(Enum tag, String value, Attributes attr) {
		
		if (value == null) {
   			switch ((ESecurity) tag) {
   			default:
   					return;
   			}
   		} else {
   			switch ((ESecurity) tag) {
   			case ShipmentNumber:
   				setShipmentNumber(value);
   				break;
   			case PlaceOfLoading:
   				setPlaceOfLoading(value);
   				break;
   			case PlaceOfUnloading:
   				setPlaceOfUnloading(value);
   				break;
   			case Itinerary:
   				
   				if (itineraryList == null) {
   					itineraryList = new ArrayList<String>();
   				}
				itineraryList.add(value);
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
			return ESecurity.valueOf(token);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}	

	public String getShipmentNumber() {
		return shipmentNumber;
	}

	public void setShipmentNumber(String shipmentNumber) {
		this.shipmentNumber = shipmentNumber;
	}

	public String getPlaceOfLoading() {
		return placeOfLoading;
	}

	public void setPlaceOfLoading(String placeOfLoading) {
		this.placeOfLoading = placeOfLoading;
	}

	public String getPlaceOfUnloading() {
		return placeOfUnloading;
	}

	public void setPlaceOfUnloading(String placeOfUnloading) {
		this.placeOfUnloading = placeOfUnloading;
	}

	public List<String> getItineraryList() {
		return itineraryList;
	}

	public void setItineraryList(List<String> itineraryList) {
		this.itineraryList = itineraryList;
	}
	public XMLEventReader getParser() {
		return parser;
	}
	public void setParser(XMLEventReader parser) {
		this.parser = parser;
	}

}

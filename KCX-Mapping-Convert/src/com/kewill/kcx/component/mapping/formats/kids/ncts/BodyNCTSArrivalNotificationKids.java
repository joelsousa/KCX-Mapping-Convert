package com.kewill.kcx.component.mapping.formats.kids.ncts;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.ncts.msg.MsgNCTSArrivalNotification;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.AddressNCTS;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.NCTSTrader;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.EnRouteEvent;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.Incident;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.TransShipment;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.WriteOffData;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.WriteOffSumA;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessageNCTS;
import com.kewill.kcx.component.mapping.util.EFormat;

/**
 * Module		: BodyNCTSArrivalNotificationKids<br>
 * Created		: 08.26.2010<br>
 * Description	: Contains message structure for NCTSArrivalNotification KIDS. 
 * 
 * @author Jude Eco
 * @version 1.0.00
 */
public class BodyNCTSArrivalNotificationKids extends KidsMessageNCTS {
	private MsgNCTSArrivalNotification message;
	
	public BodyNCTSArrivalNotificationKids(XMLStreamWriter writer) {
        this.writer = writer;
    }
	
	public MsgNCTSArrivalNotification getMessage() {
		return message;
	}
	
	public void setMessage(MsgNCTSArrivalNotification argument) {
		this.message = argument;
	}
	public void writeBody() {
		try {
			openElement("soap:Body");
			openElement("ArrivalNotification");
			openElement("GoodsDeclaration");
			
				writeElement("UCRNumber", message.getmRN());
				writeElement("ReferenceNumber", message.getLocalReferenceNumber());
				writeElement("ShipmentNumber", message.getCommercialReferenceNumber());
				writeElement("CustomsSubPlace", message.getCustomsSubPlace());
				writePartyTIN("AuthorisedConsigneeTIN", message.getAuthorisedConsigneeTIN());
						//EI20110929: writeElement("PermitNumber", message.getPermitNumber());						
				writeFormattedDateOrTime("ArrivalNotificationDate", message.getArrivalNotificationDate(), 
						message.getArrivalNotificationDateFormat(),	EFormat.KIDS_Date);						
				writeElement("PlaceOfUnloadingCode", message.getArrivalNotificationPlace());
				writeElement("PermitNumber", message.getPermitNumber());	//EI20110929
				writeElement("AgreedLocationCode", message.getAgreedLocationCode());
				writeElement("AgreedLocationOfGoods", message.getAgreedLocationOfGoods());
				writeElement("AuthorisedLocationOfGoods", message.getAuthorisedLocationOfGoods());
				writeElement("SimplifiedProcedure", message.getSimplifiedProcedureFlag());
				writeElement("DestLanguage", message.getDestLanguage());
				writeEnRouteEvent(message.getEnRouteEvent());
				writeElement("PresentationOffice", message.getPresentationOffice());
				writeWriteOffSumA("WriteOffSumA", message.getWriteOffSumA());
				writeDestinationTrader("DestinationTrader", message.getDestinationTrader());
				
			closeElement();
			closeElement();
			closeElement();
		} catch (XMLStreamException e) {
	            e.printStackTrace();
	    }     
	}
	
	public void writeDestinationTrader(String tag, NCTSTrader argument) throws XMLStreamException {
    	if (argument == null) {
    	    return;
    	}    
    	
    	openElement(tag);             	                    	   
    		writeElement("VATNumber", argument.getvATID());
	    	writeElement("ETNAddress", argument.geteTNAddress());
	    	writeAddr("Address", argument.getAddress());
	    closeElement();
    }

	public void writeAddr(String tag, AddressNCTS argument) throws XMLStreamException {
		if (argument == null) {
			return;
		}
		if (argument.isEmpty()) {
			return;
		}
		
		openElement(tag);
			writeElement("Name", argument.getName());
			writeElement("Street", argument.getStreet());
			writeElement("HouseNumber", argument.getHouseNumber());
			writeElement("City", argument.getCity());
			writeElement("PostalCode", argument.getPostalCode());
			writeElement("Country", argument.getCountry());
		closeElement();
	}

	public void writeWriteOffSumA(String tag, WriteOffSumA argument) throws XMLStreamException {
    	if (argument == null) {
    	    return;
    	}    
    	
    	openElement(tag);             	                    	   
    		writeElement("Identification", argument.getIdentification());
    		writeElement("Reference", argument.getReference());
    		writeElement("FlightNumber", argument.getFlightNumber());
    		
    		if (argument.getWriteOffDataList() != null) {
	    		for (WriteOffData writeOffData : argument.getWriteOffDataList()) {
					writeWriteOffData("WriteOffData", writeOffData);
				}
    		}
    		
	    closeElement();
    }
	
	public void writeWriteOffData(String tag, WriteOffData argument) throws XMLStreamException {
    	if (argument == null) {
    	    return;
    	}    
    	if (argument.isEmpty())  {  		
    	    return;
    	}
    	
    	openElement(tag);             	                    	   
    		writeElement("NumberOfPieces", argument.getNumberOfPieces());
    		writeElement("RegNumber", argument.getRegNumber());
    		writeElement("ItemNumber", argument.getItemNumber());
    		writeElement("AWBNumber", argument.getaWBNumber());
    		writeElement("Custodian", argument.getCustodian());
    		writeElement("CustomerID", argument.getCustomerID());
    		writeElement("SpoArt", argument.getSpoArt());
    		writeElement("SpO", argument.getSpo());
    		writeElement("ATLASAlignment", argument.getAtlasAlignment());
	    closeElement();
    }
}

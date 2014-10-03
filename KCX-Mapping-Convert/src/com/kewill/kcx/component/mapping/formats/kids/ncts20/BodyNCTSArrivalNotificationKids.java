package com.kewill.kcx.component.mapping.formats.kids.ncts20;

import java.util.ArrayList;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.AddressNCTS;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.NCTSTrader;
import com.kewill.kcx.component.mapping.countries.de.ncts20.msg.MsgNCTSArrivalNotification;
import com.kewill.kcx.component.mapping.countries.de.ncts20.msg.common.ManifestCompletion;
import com.kewill.kcx.component.mapping.countries.de.ncts20.msg.common.ManifestCompletionItem;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessageNCTS20;
import com.kewill.kcx.component.mapping.util.EFormat;

/**
 * Module		: NCTS 41<br>
 * Created		: 08.02.2013<br>
 * Description	: Contains message structure for NCTSArrivalNotification KIDS. 
 * 
 * @author iwaniuk
 * @version 4.1.00
 */
public class BodyNCTSArrivalNotificationKids extends KidsMessageNCTS20 {
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
			
				writeElement("UCRNumber", message.getUCRNumber());
				writeElement("ReferenceNumber", message.getReferenceNumber());
				writeElement("ShipmentNumber", message.getShipmentNumber());
				writeElement("CustomsSubPlace", message.getCustomsSubPlace());
				writePartyTIN("AuthorisedConsigneeTIN", message.getAuthorisedConsigneeTIN());											
				writeFormattedDateOrTime("ArrivalNotificationDate", message.getArrivalNotificationDate(), 
						message.getArrivalNotificationDateFormat(),	EFormat.KIDS_Date);						
				writeElement("PlaceOfUnloadingCode", message.getPlaceOfUnloadingCode());
				writeElement("PermitNumber", message.getPermitNumber());	
				writeElement("AgreedLocationCode", message.getAgreedLocationCode());
				writeElement("AgreedLocationOfGoods", message.getAgreedLocationOfGoods());
				writeElement("AuthorisedLocationOfGoods", message.getAuthorisedLocationOfGoods());
				writeElement("SimplifiedProcedure", message.getSimplifiedProcedure());
				writeElement("DestLanguage", message.getDestLanguage());
				writeEnRouteEvent(message.getEnRouteEvent());
				writeElement("PresentationOffice", message.getPresentationOffice());
				writeWriteOffSumAList(message.getWriteOffSumAList());
				writeDestinationTrader("DestinationTrader", message.getDestinationTrader());
				
				//EI20130208: new V70 writeContact(message.getContact());   soll ich es aktivieren?
				
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
	public void writeWriteOffSumAList(ArrayList<ManifestCompletion> list) throws XMLStreamException {
    	if (list == null) {
    	    return;
    	}   
    	for (ManifestCompletion mc : list) {
    		writeWriteOffSumA(mc);
    	}
	}
	public void writeWriteOffSumA(ManifestCompletion argument) throws XMLStreamException {
    	if (argument == null) {
    	    return;
    	}    
    	
    	//EI20131129: openElement("WriteOffSumA");      
    	openElement("ManifestCompletion");      
    		writeElement("Identification", argument.getIdentification());
    		writeElement("Reference", argument.getReference());
    		writeElement("FlightNumber", argument.getFlightNumber());
    		
    		if (argument.getCompletionItemList() != null) {
	    		for (ManifestCompletionItem writeOffData : argument.getCompletionItemList()) {
					writeWriteOffData(writeOffData);
				}
    		}
    		
	    closeElement();
    }
	
	public void writeWriteOffData(ManifestCompletionItem argument) throws XMLStreamException {
    	if (argument == null) {
    	    return;
    	}    
    	if (argument.isEmpty())  {  		
    	    return;
    	}
    	
    	openElement("CompletionItem");  
    		writeElement("PositionNumber", argument.getPositionNumber());
    		writeElement("UCR", argument.getUCR());
    		writeElement("NumberOfPieces", argument.getNumberOfPieces());    		
    		writeElement("AWB", argument.getAWB());
    		if (argument.getCustodianTIN() != null) {
    			openElement("CustodianTIN");
    				writeElement("TIN", argument.getCustodianTIN().getTIN());
    				writeElement("BO", argument.getCustodianTIN().getBO());
    				writeElement("CustomerIdentifier", argument.getCustodianTIN().getCustomerIdentifier());
    			closeElement();
    		}    		
    		writeElement("SpoArt", argument.getSpoArt());
    		writeElement("Spo", argument.getSpo());
    		writeElement("ATLASAlignment", argument.getAtlasAlignment());
	    closeElement();
    }
}

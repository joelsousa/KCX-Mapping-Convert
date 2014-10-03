package com.kewill.kcx.component.mapping.formats.uids.ncts20;

import java.util.ArrayList;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.ncts20.msg.MsgNCTSArrivalNotification;
import com.kewill.kcx.component.mapping.countries.de.ncts20.msg.common.ManifestCompletion;
import com.kewill.kcx.component.mapping.countries.de.ncts20.msg.common.ManifestCompletionItem;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsMessageNCTS20;
import com.kewill.kcx.component.mapping.util.EFormat;

/**
 * Module		: NCTS<br>
 * Created		: 08.02.2013<br>
 * Description	: Contains message structure for NCTSArrivalNotification UIDS. 
 * 
 * @author iwaniuk
 * @version 4.1.00
 */

public class BodyNCTSArrivalNotificationUids extends UidsMessageNCTS20 {
	private MsgNCTSArrivalNotification message;
	
	public BodyNCTSArrivalNotificationUids(XMLStreamWriter writer) {
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
			openElement("Submit");
			openElement("NCTS");
			openElement("NCTSArrivalNotification");
			
				writeElement("MRN", message.getUCRNumber());
				writeElement("LocalReferenceNumber", message.getReferenceNumber());
				writeElement("CommercialReferenceNumber", message.getShipmentNumber());
				writeElement("CustomsSubPlace", message.getCustomsSubPlace());
				writeElement("ArrivalNotificationPlace", message.getPlaceOfUnloadingCode());
				if (message.getAuthorisedConsigneeTIN() != null) {
					writeElement("AuthorisedConsigneeTIN", message.getAuthorisedConsigneeTIN().getTIN());
				}
				writeElement("PermitNumber", message.getPermitNumber());
				writeElement("AgreedLocationCode", message.getAgreedLocationCode());
				writeElement("AgreedLocationOfGoods", message.getAgreedLocationOfGoods());
				writeElement("AuthorisedLocationOfGoods", message.getAuthorisedLocationOfGoods());
				writeElement("SimplifiedProcedureFlag", message.getSimplifiedProcedure());
				writeFormattedDateOrTime("ArrivalNotificationDate", message.getArrivalNotificationDate(), 
								message.getArrivalNotificationDateFormat(), EFormat.ST_Date);					
				writeElement("DestLanguage", message.getDestLanguage());
				writeNCTSTrader("DestinationTrader", message.getDestinationTrader());
				writeElement("PresentationOffice", message.getPresentationOffice());
				writeEnRouteEvent(message.getEnRouteEvent());
				writeWriteOffSumAList(message.getWriteOffSumAList());
				
				//EI20130208: new V70 writeContact(message.getContact());   soll ich es aktivieren
			
			closeElement();
			closeElement();
			closeElement();
			closeElement();
		} catch (XMLStreamException e) {
            e.printStackTrace();
        }     
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
    	
    	openElement("WriteOffSumA");             	                    	   
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
    	
    	openElement("WriteOffData");             	                    	   
    		writeElement("NumberOfPieces", argument.getNumberOfPieces());
    		writeElement("RegNumber", argument.getUCR());
    		writeElement("ItemNumber", argument.getPositionNumber());
    		writeElement("AWBNumber", argument.getAWB());
    		if (argument.getCustodianTIN() != null) {
    			writeElement("CustodianTIN", argument.getCustodianTIN().getTIN());
    			writeElement("CustodianBO", argument.getCustodianTIN().getBO());
    			writeElement("CustomerID", argument.getCustodianTIN().getCustomerIdentifier());
    		}
    		writeElement("SpoArt", argument.getSpoArt());
    		writeElement("SpO", argument.getSpo());
    		writeElement("ATLASAlignment", argument.getAtlasAlignment());
	    closeElement();
    }
}

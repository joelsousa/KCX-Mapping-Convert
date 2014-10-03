package com.kewill.kcx.component.mapping.formats.kff;

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
import com.kewill.kcx.component.mapping.formats.uids.common.UidsMessageNCTS;
import com.kewill.kcx.component.mapping.util.EFormat;

/**
 * Module		: BodyNCTSArrivalNotificationUids<br>
 * Created		: 08.26.2010<br>
 * Description	: Contains message structure for NCTSArrivalNotification UIDS. 
 * 
 * @author Jude Eco
 * @version 1.0.00
 */
public class BodyPortIwaAbcKff extends UidsMessageNCTS {
	private MsgNCTSArrivalNotification message;
	
	public BodyPortIwaAbcKff(XMLStreamWriter writer) {
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
			
				writeElement("MRN", message.getmRN());
				writeElement("LocalReferenceNumber", message.getLocalReferenceNumber());
				writeElement("CommercialReferenceNumber", message.getCommercialReferenceNumber());
				writeElement("CustomsSubPlace", message.getCustomsSubPlace());
				writeElement("ArrivalNotificationPlace", message.getArrivalNotificationPlace());
				if (message.getAuthorisedConsigneeTIN() != null) {
					writeElement("AuthorisedConsigneeTIN", message.getAuthorisedConsigneeTIN().getTIN());
				}
				writeElement("PermitNumber", message.getPermitNumber());
				writeElement("AgreedLocationCode", message.getAgreedLocationCode());
				writeElement("AgreedLocationOfGoods", message.getAgreedLocationOfGoods());
				writeElement("AuthorisedLocationOfGoods", message.getAuthorisedLocationOfGoods());
				writeElement("SimplifiedProcedureFlag", message.getSimplifiedProcedureFlag());
				writeFormattedDateOrTime("ArrivalNotificationDate", message.getArrivalNotificationDate(), 
								message.getArrivalNotificationDateFormat(), EFormat.ST_Date);					
				writeElement("DestLanguage", message.getDestLanguage());
				writeNCTSTrader("DestinationTrader", message.getDestinationTrader());
				writeElement("PresentationOffice", message.getPresentationOffice());
				writeEnRouteEvent(message.getEnRouteEvent());
				writeWriteOffSumA(message.getWriteOffSumA());
					
			closeElement();
			closeElement();
			closeElement();
			closeElement();
		} catch (XMLStreamException e) {
            e.printStackTrace();
        }     
	}
		
	public void writeWriteOffSumA(WriteOffSumA argument) throws XMLStreamException {
    	if (argument == null) {
    	    return;
    	}    
    	
    	openElement("WriteOffSumA");             	                    	   
    		writeElement("Identification", argument.getIdentification());
    		writeElement("Reference", argument.getReference());
    		writeElement("FlightNumber", argument.getFlightNumber());
    		
    		if (argument.getWriteOffDataList() != null) {
	    		for (WriteOffData writeOffData : argument.getWriteOffDataList()) {
					writeWriteOffData(writeOffData);
				}
    		}
	    closeElement();
    }
	
	public void writeWriteOffData(WriteOffData argument) throws XMLStreamException {
    	if (argument == null) {
    	    return;
    	}    
    	if (argument.isEmpty())  {  		
    	    return;
    	}
    	
    	openElement("WriteOffData");             	                    	   
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

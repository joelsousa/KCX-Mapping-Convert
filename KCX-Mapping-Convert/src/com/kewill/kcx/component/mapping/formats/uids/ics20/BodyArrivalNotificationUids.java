package com.kewill.kcx.component.mapping.formats.uids.ics20;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.ics20.msg.MsgArrivalNotification;
import com.kewill.kcx.component.mapping.countries.de.ics20.msg.common.EsumaDataReference;
import com.kewill.kcx.component.mapping.countries.de.ics20.msg.common.EsumaDetails;
import com.kewill.kcx.component.mapping.countries.de.ics20.msg.common.GoodsItemArn;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsMessageICS20;
import com.kewill.kcx.component.mapping.util.EFormat;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: ICS20<br>
 * Created		: 2012.10.25<br>
 * Description	: UIDS-Body of ICSArrivalNotification (KIDS: ICSArrivalNotification, IE347).
 * 				: new for V20: OfficeOfNotification
 * 
 * @author iwaniuk
 * @version 2.0.00
 */
public class BodyArrivalNotificationUids extends UidsMessageICS20 {

	private MsgArrivalNotification message;
	private String destinationCountry = "";  
	
	public BodyArrivalNotificationUids(XMLStreamWriter writer) {
        this.writer = writer;
    }
	
	public MsgArrivalNotification getMessage() {
		return message;
	}
	
	public void setMessage(MsgArrivalNotification argument) {
		this.message = argument;
	}
	
	public void writeBody() {
		destinationCountry = this.getUidsHeader().getTo().substring(0, 2);  
		try {
			openElement("soap:Body");
			openElement("Submit");
			setAttribute("xmlns", "http://www.eurtradenet.com/schemas/uids/ics/body/200911"); //EI20130904
			openElement("ICS");
			openElement("ICSArrivalNotification");
			
				writeElement("LocalReferenceNumber", message.getReferenceNumber());
				writeElement("CommercialReferenceNumber", message.getShipmentNumber());
				writeElement("TotalNumberOfItems", message.getTotalNumberPositions());
				writeElement("TotalNumberOfPackages", message.getTotalNumberPackages());
				writeElement("TotalGrossMass", message.getTotalGrossMass());
				writeTransport("TransportAtBorder", message.getMeansOfTransportBorder());				
				writeElement("ConveyanceNumber", message.getConveyanceReference());				
				writeFormattedDateTime("ArrivalDateAndTime", message.getDateOfPresentation(), 
							message.getDateOfPresentationFormat(), EFormat.ST_DateTimeTZ);	
				if (destinationCountry.equals("CZ")) {    
					writeCodeElementAE2CZ("ArrivalNotificationPlace", message.getCustomsOfficeFirstEntry(), "KCX0099");		
				}
				writeFormattedDateTime("ExpectedArrivalDateAndTime", message.getDeclaredDateOfArrival(), 
						message.getDeclaredDateOfArrivalFormat(), EFormat.ST_DateTimeTZ);				
				writeTrader("EntryCarrier", message.getCarrier());									
				writeElement("OfficeOfFirstEntry", message.getCustomsOfficeFirstEntry());
				if (message.getCarrier() != null) {
					writeContact("Clerk", message.getCarrier().getContactPerson());
				}
				writeElement("OriginalCountryOfFirstEntry", message.getCountryOfficeFirstEntry());
				writeElement("OfficeOfNotification", message.getOfficeOfNotification());    //new for V20
                
                // CK 20110204 temporär solange es vom Pool nicht geliefert wird für Frankreich!!
				if (destinationCountry.equals("FR") && Utils.isStringEmpty(message.getOfficeOfNotification())) {					
					writeElement("OfficeOfNotification", message.getCustomsOfficeFirstEntry());
				} //CK-end				
				
				if (message.getGoodsItemList() != null) {
					for (GoodsItemArn goodsItem : message.getGoodsItemList()) {
						writeGoodsItem(goodsItem, destinationCountry);
					}
				}
				
			closeElement();
			closeElement();
			closeElement();
			closeElement();		
		} catch (XMLStreamException e) {
            e.printStackTrace();
        }     
	}
	
	public void writeGoodsItem(GoodsItemArn goodsItem, String destinationCountry) throws XMLStreamException {
		openElement("ArrivalItem");
	    	writeElement("ItemNumber", goodsItem.getItemNumber());  
	    	//CommertialReferenceNumber
	    	writeDocument(goodsItem.getDocument());
	    	
	    	if (goodsItem.getEsumaDataReferenceList() != null) {              //new V20: changed structure  	    		
    	    	for (EsumaDataReference data : goodsItem.getEsumaDataReferenceList()) {
    	    		writeEsumaDataReference(data, destinationCountry);    	    	
    	    	}
    	    }   	    	
	    closeElement();		
	}
}

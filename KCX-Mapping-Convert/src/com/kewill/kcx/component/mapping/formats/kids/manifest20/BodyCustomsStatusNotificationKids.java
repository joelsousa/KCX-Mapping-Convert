package com.kewill.kcx.component.mapping.formats.kids.manifest20;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.suma70.msg.MsgCustomsStatusNotification;
import com.kewill.kcx.component.mapping.countries.de.suma70.msg.common.GoodsItemReexport;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessageManifest20;

/**
 * Module	   : Manifest.<br>
 * Created	   : 16.12.2013<br>
 * Description : BodyCustomsStatusNotificationKids
 * 
 * @author krzoska
 * @version 2.0.00
 *
 */
public class BodyCustomsStatusNotificationKids extends KidsMessageManifest20  {

	private MsgCustomsStatusNotification message;	

    public BodyCustomsStatusNotificationKids(XMLStreamWriter writer) {
        this.writer = writer;
    }
   
	public MsgCustomsStatusNotification getMessage() {
		return message;
	}
	
	public void setMessage(MsgCustomsStatusNotification argument) {
		this.message = argument;
	}
		
    public void writeBody() {
        try {
            openElement("soap:Body");
            openElement("CustomsStatusNotification");
            openElement("GoodsDeclaration"); 
            	       	 
                writeContact(message.getContact());               
                writeElement("DateTimeOfReceipt", message.getDateTimeOfReceipt());                
                writeReferencedSpecification("", message.getReferencedSpecification());                
                writeTransport("MeansOfTransport", message.getTransport());  
                writeElement("AirportOfDeparture", message.getAirportOfDeparture());  //EI2040123
                writeElement("DateTimeOfArrival", message.getDateTimeOfArrival());
                writeElement("AirportOfArrival", message.getAirportOfArrival());
                writeElement("OfficeOfEntry", message.getOfficeOfEntry());    
                writeElement("CustomsId", message.getCustomsId());  
                writeElement("TotalPieces", message.getTotalPieces());  
                writeElement("CountryOfDispatch", message.getCountryOfDispatch());  
                writeElement("DestinationPlace", message.getDestinationPlace());
                writeCustomsNotification("CustomsNotification", message.getCustomsNotification());
                                
                
                
            closeElement();
            closeElement();
            closeElement();
            
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }       
	}

   
}


package com.kewill.kcx.component.mapping.formats.kids.emcs;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.emcs.msg.MsgChangeOfDestination;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessageEMCS;
/**
 * Module		: BodyBodyChangeOfDestinationKidsKids.<br>
 * Created		: 04.05.2010<br>
 * Description  : Contains Message Structure with fields used in EMCSBodyChangeOfDestinationKids Kids message,
 *                 
 * @author iwaniuk
 * @version 1.0.00
 */
public class BodyChangeOfDestinationKids extends KidsMessageEMCS {

	private MsgChangeOfDestination message;	

    public BodyChangeOfDestinationKids(XMLStreamWriter writer) {
        this.writer = writer;
    }
   
	public MsgChangeOfDestination getMessage() {
		return message;
	}
	public void setMessage(MsgChangeOfDestination argument) {
		this.message = argument;
	}
		
    public void writeBody() {
        try {
            openElement("soap:Body");
            openElement("EMCS");
                //openElement(this.kidsMessageName);
            	openElement("EMCSChangeOfDestination");
                    
                    writeElement("ReferenceNumber", message.getReferenceNumber());
                    writeElement("CustomerOrderNumber", message.getCustomerOrderNumber());
                    writeElement("Clerk", message.getClerk());             	
                    writeElement("AadReferenceCode", message.getAadReferenceCode());
                    writeElement("SequenceNumber", message.getSequenceNumber()); 
                    writeElement("JourneyTime", message.getJourneyTime());        
                    //writeCodeElement("ChangedTransportArrangement", message.getChangedTransportArrangement(), "A0070");
                    writeElement("ChangedTransportArrangement", message.getChangedTransportArrangement());
                    //writeCodeElement("TransportModeCode", message.getTransportModeCode(), "C0067");
                    writeElement("TransportModeCode", message.getTransportModeCode()); 
                    writeElement("InvoiceNumber", message.getInvoiceNumber());
                    writeDateToString("InvoiceDate", message.getInvoiceDate());                       
                    writeDateTimeToString("DateAndTimeOfValidation", message.getDateAndTimeOfValidation());
                    //writeCodeElement("DestinationTypeCode", message.getDestinationTypeCode(), "A0077");   
                    writeElement("DestinationTypeCode", message.getDestinationTypeCode());   
                    writeElement("DeliveryPlaceCustomsOffice", message.getDeliveryPlaceCustomsOffice());    
                    writeTrader("NewConsignee", message.getNewConsignee());               		
                    writeTrader("DeliveryPlace", message.getDeliveryPlace());    
                    writeTrader("NewTransportArranger", message.getNewTransportArranger());                       
                    writeTrader("NewTransporter", message.getNewTransporter());                        
                    writeTransportDetailsList(message.getTransportDetailsList()); 
               closeElement();
           closeElement();	    
           closeElement();	           
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }       
	} 
 
}

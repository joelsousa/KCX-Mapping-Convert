package com.kewill.kcx.component.mapping.formats.uids.emcs21;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.emcs21.msg.MsgChangeOfDestination;
import com.kewill.kcx.component.mapping.countries.de.emcs.msg.common.ChangedDestination;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsMessageEMCS20;
import com.kewill.kcx.component.mapping.util.EFormat;

/**
 * Module		: EMCS<br>
 * Created		: 12.05.2010<br>
 * Description  : Contains Message Structure with fields used in EMCSChgOfDestinatonInfo Uids message.
 *              : V20 - DateTime format was changed, Trader.Addres.StreetAndNumber now in two Tags
 *                 
 * @author iwaniuk
 * @version 2.0.00
 */

public class BodyChangeOfDestinationUids extends UidsMessageEMCS20 {
	
    private MsgChangeOfDestination  message = new MsgChangeOfDestination();
   
    public BodyChangeOfDestinationUids(XMLStreamWriter writer) {
        this.writer = writer;
    }

	public MsgChangeOfDestination getMessage() {
		return message;
	}

	public void setMessage(MsgChangeOfDestination message) {
		this.message = message;
	}
    
	public void writeBody() {		
		
        try {   
        	
        	openElement("soap:Body");
            openElement("Submit");
                setAttribute("xmlns", "http://www.eurtradenet.com/schemas/uids/emcs/body/200911");               
            openElement("EMCS");
            openElement("EMCSChgOfDestinationInfo"); 
            
                writeElement("LocalReferenceNumber", message.getReferenceNumber());
                writeUpdateEaad21(message);                
                //writeStringToDateTime("DateAndTimeOfValidation", message.getDateAndTimeOfValidation());
                writeFormattedDateTime("DateAndTimeOfValidation", message.getDateAndTimeOfValidation(),
   											   EFormat.KIDS_DateTime, EFormat.ST_DateTimeTZ);	    //EI20111017               
                writeTrader("NewTransportArrangerTrader", message.getNewTransportArranger());
                writeTrader("NewTransporterTrader", message.getNewTransporter());
                writeChangedDestination();                   
                writeTransportDetailsList(message.getTransportDetailsList());
                        	               
            closeElement(); 
            closeElement(); 
            closeElement(); 
            closeElement();
        } catch (XMLStreamException e) {           
            e.printStackTrace();
        }
    }

	private void writeChangedDestination() throws XMLStreamException {			   	
    	ChangedDestination temp = new ChangedDestination();
	   	
	   	temp.setDestinationTypeCode(message.getDestinationTypeCode()); 
		temp.setDeliveryPlaceCustomsOffice(message.getDeliveryPlaceCustomsOffice());
	   	temp.setNewConsignee(message.getNewConsignee());
	   	temp.setDeliveryPlace(message.getDeliveryPlace());
		
	   	writeChangedDestination(temp);
	}
	
}    	

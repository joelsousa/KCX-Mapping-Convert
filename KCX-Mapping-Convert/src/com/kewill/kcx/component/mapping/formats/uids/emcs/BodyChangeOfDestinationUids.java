package com.kewill.kcx.component.mapping.formats.uids.emcs;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.emcs.msg.MsgChangeOfDestination;
import com.kewill.kcx.component.mapping.countries.de.emcs.msg.common.ChangedDestination;
import com.kewill.kcx.component.mapping.util.EFormat;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsMessageEMCS;

/**
 * Module		: EMCS<br>
 * Created		: 12.05.2010<br>
 * Description  : Contains Message Structure with fields used in EMCSChgOfDestinatonInfo Uids message.
 *                 
 * @author krzoska
 * @version 1.0.00
 */

public class BodyChangeOfDestinationUids extends UidsMessageEMCS {
	
    private MsgChangeOfDestination  message = new MsgChangeOfDestination();
    private String version = "";         //EI20110701
 
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
        	version = this.uidsHeader.getMessageVersion();
        	version = Utils.removeDots(version.substring(0, 3));
        	
        	openElement("soap:Body");
            openElement("Submit");
                setAttribute("xmlns", "http://www.eurtradenet.com/schemas/uids/emcs/body/200911");               
            openElement("EMCS");
            openElement("EMCSChgOfDestinationInfo"); 
            
                writeElement("LocalReferenceNumber", message.getReferenceNumber());
                writeUpdateEaad(message); 
                if (version.equals("10")) {    //EI20110701 V20: Tagame and format changed 
                	   writeStringToDateTime("DateAndTimeOfValidationOfEaad", message.getDateAndTimeOfValidation());
                } else {
                	   //writeStringToDateTime("DateAndTimeOfValidation", message.getDateAndTimeOfValidation());
                	   writeFormattedDateTime("DateAndTimeOfValidation", message.getDateAndTimeOfValidation(),
   											   EFormat.KIDS_DateTime, EFormat.ST_DateTimeTZ);	    //EI20111017
                }
                writeTrader("NewTransportArrangerTrader", message.getNewTransportArranger(), version);
                writeTrader("NewTransporterTrader", message.getNewTransporter(), version);
                writeChangedDestination(version);                   
                writeTransportDetailsList(message.getTransportDetailsList());
                        	               
            closeElement(); 
            closeElement(); 
            closeElement(); 
            closeElement();
        } catch (XMLStreamException e) {           
            e.printStackTrace();
        }
    }

	private void writeChangedDestination(String version) throws XMLStreamException {			   	
    	ChangedDestination temp = new ChangedDestination();
	   	
	   	temp.setDestinationTypeCode(message.getDestinationTypeCode()); 
		temp.setDeliveryPlaceCustomsOffice(message.getDeliveryPlaceCustomsOffice());
	   	temp.setNewConsignee(message.getNewConsignee());
	   	temp.setDeliveryPlace(message.getDeliveryPlace());
		
	   	writeChangedDestination(temp, version);
	}
	
}    	

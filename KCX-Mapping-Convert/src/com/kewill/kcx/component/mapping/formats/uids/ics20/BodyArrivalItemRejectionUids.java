package com.kewill.kcx.component.mapping.formats.uids.ics20;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.ics20.msg.MsgArrivalItemRejection;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.FunctionalError;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.ImportDetails;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsMessageICS20;
import com.kewill.kcx.component.mapping.util.EFormat;

/**
 * Module		: ICS20<br>
 * Created		: 2012.10.23<br>
 * Description	: UIDS-Body of ICSArrivalItemRejection  (KIDS: ICSArrivalItemRejection, IE349).
 *  			: : new for V20: TransportAtBorder, EntryCarrier
 * 
 * @author iwaniuk
 * @version 2.0.00
 *
 */
public class BodyArrivalItemRejectionUids extends UidsMessageICS20 {
	private MsgArrivalItemRejection message;
	
	public BodyArrivalItemRejectionUids(XMLStreamWriter writer) {
        this.writer = writer;
    }
	
	public MsgArrivalItemRejection getMessage() {
		return message;
	}
	
	public void setMessage(MsgArrivalItemRejection argument) {
		this.message = argument;
	}
	public void writeBody() {
		try {
			openElement("soap:Body");
			openElement("Submit");
			setAttribute("xmlns", "http://www.eurtradenet.com/schemas/uids/ics/body/200911"); //EI20130904
			openElement("ICS"); 
        	openElement("ICSArrivalItemRejection");
        	
            	writeElement("LocalReferenceNumber", message.getReferenceNumber());            	
            	writeFormattedDateTime("RejectionDateAndTime", message.getRejectionDateTime(),
	                    message.getRejectionDateTimeFormat(), EFormat.ST_DateTimeTZ);            	
            	writeElement("RejectionReason", message.getRejectionReasonHeader());
            	writeFormattedDateTime("ExpectedArrivalDateAndTime", message.getDeclaredDateOfArrival(),
	                    message.getDeclaredDateOfArrivalFormat(), EFormat.ST_DateTimeTZ); 
            	writeElement("ConveyanceNumber", message.getConveyanceReference());
            	writeTransport("TransportAtBorder", message.getMeansOfTransportBorder());  //new for V20
            	if (message.getImportDetailsList() != null) {
	                for (ImportDetails details : message.getImportDetailsList()) {
	                    writeImportDetails("MRNDetails", details);
	                }
            	}
            	writeTrader("EntryCarrier", message.getCarrier());                         //new for V20
            	if (message.getFunctionalErrorList() != null) {
	                for (FunctionalError error : message.getFunctionalErrorList()) {
	                    writeFunctionalError(error);
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

	private void writeImportDetails(String tag, ImportDetails details) throws XMLStreamException {
		if (details == null) {
			return;
		}

    	openElement(tag);             	                    	   
	    	writeElement("MRN", details.getMrn());
	    	writeElement("ItemNumber", details.getItemNumber());
	    	writeElement("LocalReferenceNumber", details.getReferenceNumber());
	    	writeElement("Reason", details.getRejectionReasonPos());
		closeElement();
	}
}

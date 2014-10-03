package com.kewill.kcx.component.mapping.formats.uids.ics;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.ics.msg.MsgArrivalItemRejection;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.FunctionalError;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.ImportDetails;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsMessageICS;
import com.kewill.kcx.component.mapping.util.EFormat;

/**
 * Module		: BodyArrivalItemRejectionUids<br>
 * Created		: 2010.07.20<br>
 * Description	: UIDS-Body of ICSArrivalItemRejection.
 *  			: (KIDS: ICSArrivalItemRejection, IE349)
 * 
 * @author Elbert Jude Eco
 * @version 1.0.00
 *
 */
public class BodyArrivalItemRejectionUids extends UidsMessageICS {
	private MsgArrivalItemRejection msgArrivalItemRejection;
	
	public BodyArrivalItemRejectionUids(XMLStreamWriter writer) {
        this.writer = writer;
    }
	
	public MsgArrivalItemRejection getMessage() {
		return msgArrivalItemRejection;
	}
	
	public void setMessage(MsgArrivalItemRejection argument) {
		this.msgArrivalItemRejection = argument;
	}
	public void writeBody() {
		try {
			openElement("soap:Body");
			openElement("Submit");
			setAttribute("xmlns", "http://www.eurtradenet.com/schemas/uids/ics/body/200911"); //EI20130904
			openElement("ICS"); 
        	openElement("ICSArrivalItemRejection");
        	
            	writeElement("LocalReferenceNumber", msgArrivalItemRejection.getReferenceNumber());            	
            	writeFormattedDateTime("RejectionDateAndTime", msgArrivalItemRejection.getRejectionDateTime(),
	                    msgArrivalItemRejection.getRejectionDateTimeFormat(), EFormat.ST_DateTimeTZ);            	
            	writeElement("RejectionReason", msgArrivalItemRejection.getRejectionReasonHeader());
            	writeFormattedDateTime("ExpectedArrivalDateAndTime", msgArrivalItemRejection.getDeclaredDateOfArrival(),
	                    msgArrivalItemRejection.getDeclaredDateOfArrivalFormat(), EFormat.ST_DateTimeTZ); //EI20110208
            	writeElement("ConveyanceNumber", msgArrivalItemRejection.getConveyanceReference());  //EI20110208
            	if (msgArrivalItemRejection.getImportDetailsList() != null) {
	                for (ImportDetails details : msgArrivalItemRejection.getImportDetailsList()) {
	                    writeImportDetails("MRNDetails", details);
	                }
            	}
            	if (msgArrivalItemRejection.getFunctionalErrorList() != null) {
	                for (FunctionalError error : msgArrivalItemRejection.getFunctionalErrorList()) {
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

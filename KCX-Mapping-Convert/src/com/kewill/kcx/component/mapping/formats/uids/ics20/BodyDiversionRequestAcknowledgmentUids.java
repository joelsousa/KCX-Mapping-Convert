package com.kewill.kcx.component.mapping.formats.uids.ics20;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.ics20.msg.MsgDiversionRequestAcknowledgment;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsMessageICS20;
import com.kewill.kcx.component.mapping.util.EFormat;

/**
 * Module		: ICS20<br>
 * Created		: 2012.10.23<br>
 * Description	: UIDS-Body of ICSDiversionRequestAccepted.
 * 				: (Kids: ICSDiversionRequestAcknowledgment, IE325)
 * 
 * @author iwaniuk
 * @version 2.0.00
 */
public class BodyDiversionRequestAcknowledgmentUids extends UidsMessageICS20 {

	private MsgDiversionRequestAcknowledgment message;

    public BodyDiversionRequestAcknowledgmentUids(XMLStreamWriter writer) {
        this.writer = writer;
    }

	public MsgDiversionRequestAcknowledgment getMessage() {
		return message;
	}

	public void setMessage(MsgDiversionRequestAcknowledgment argument) {
		this.message = argument;
	}

    public void writeBody() {
        try {
            openElement("soap:Body");
            openElement("Submit");
            setAttribute("xmlns", "http://www.eurtradenet.com/schemas/uids/ics/body/200911"); //EI20130904
            openElement("ICS");
            openElement("ICSDiversionRequestAccepted");
            
	        	writeElement("LocalReferenceNumber", message.getReferenceNumber());	           
		        writeFormattedDateTime("RegistrationDateAndTime", message.getRegistrationDateTime(),
		                	message.getRegistrationDateTimeFormat(), EFormat.ST_DateTimeTZ);	            
	            if (message.getSubmitter() != null) {
	            	openElement("Submitter");
	            		if (message.getSubmitter().getPartyTIN() != null) {
	            			writeElement("TIN", message.getSubmitter().getPartyTIN().getTIN());
	            		}
	            		writeAddress("Address", message.getSubmitter().getAddress());
	            		writeContact("Contact", message.getSubmitter().getContactPerson());
		            closeElement();
	            }
	            writeElement("OfficeOfFirstEntry", message.getCustomsOfficeOfFirstEntry());
	                	
	        closeElement();
            closeElement();
            closeElement();
            closeElement();

        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
	}
}

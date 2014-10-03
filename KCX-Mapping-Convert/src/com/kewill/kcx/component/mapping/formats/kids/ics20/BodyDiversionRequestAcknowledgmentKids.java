package com.kewill.kcx.component.mapping.formats.kids.ics20;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.ics20.msg.MsgDiversionRequestAcknowledgment;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessageICS20;
import com.kewill.kcx.component.mapping.util.EFormat;

/**
 * Module		: ICS20<br>
 * Created		: 19.10.2012<br>
 * Description	: Body of ICSDiversionRequestAcknowledgment in KIDS format.
 *              : (IE325)
 *  
 * @author  : krzoska
 * @version 2.0.00
 */
public class BodyDiversionRequestAcknowledgmentKids extends KidsMessageICS20 {

	private MsgDiversionRequestAcknowledgment message;

    public BodyDiversionRequestAcknowledgmentKids(XMLStreamWriter writer) {
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
            openElement("ICSDiversionRequestAcknowledgment");
            openElement("GoodsDeclaration");
            
            	writeElement("ReferenceNumber", message.getReferenceNumber());               
	            writeFormattedDateTime("RegistrationDateTime", message.getRegistrationDateTime(),
	                    	message.getRegistrationDateTimeFormat(), EFormat.KIDS_DateTime);               
                if (message.getSubmitter() != null) {
                    writePartyTIN("Submitter", message.getSubmitter().getPartyTIN());
	                writePartyAddress("Submitter", message.getSubmitter());
                    writeContactPerson("Submitter", message.getSubmitter().getContactPerson());
                }
                writeElement("CustomsOfficeOfFirstEntry", message.getCustomsOfficeOfFirstEntry());
                    	
            closeElement();
            closeElement();
            closeElement();

        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
	}
}
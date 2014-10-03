/*
 * Function    : BodyDiversionRequestAcknowledgmentKids.java
 * Titel       :
 * Date        : 15.06.2010
 * Author      : Pete T
 * Description :
 * Version 	   : 1.0
 * Parameters  :

 */

package com.kewill.kcx.component.mapping.formats.kids.ics;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.ics.msg.MsgDiversionRequestAcknowledgment;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessageICS;
import com.kewill.kcx.component.mapping.util.EFormat;

/**
 * Module		: BodyDiversionRequestAcknowledgmentKids<br>
 * Created		: 14.07.2010<br>
 * Description	: The body of the KIDS format of EntrySummaryDeclarationAmendmentAcceptance message.
 * 
 * @author	: Pete T
 * @version 1.0.00
 */
public class BodyDiversionRequestAcknowledgmentKids extends KidsMessageICS {

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
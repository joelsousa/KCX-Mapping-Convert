/*
 * Function    : BodyExitDeclarationAcknowledgmentKids .java
 * Titel       :
 * Date        : 22.10.2012
 * Author      : krzoska
 * Description :
 * Version 	   : 2.0
 * Parameters  :

 */

package com.kewill.kcx.component.mapping.formats.kids.ics20;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.ics20.msg.MsgExitDeclarationAcknowledgment;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessageICS20;
import com.kewill.kcx.component.mapping.util.EFormat;

/**
 * Module		: BodyExitDeclarationAcknowledgmentKids.<br>
 * Date Started	: 22.10.2012<br>
 * 
 * @author krzoska
 * @version 2.0.00
 */
public class BodyExitDeclarationAcknowledgmentKids extends KidsMessageICS20 {
 
     	private MsgExitDeclarationAcknowledgment message;

    public BodyExitDeclarationAcknowledgmentKids(XMLStreamWriter writer) {
        this.writer = writer;
    }

	public MsgExitDeclarationAcknowledgment getMessage() {
		return message;
	}

	public void setMessage(MsgExitDeclarationAcknowledgment argument) {
		this.message = argument;
	}

    public void writeBody() {
        try {
            openElement("soap:Body");
            openElement("ICSExitDeclarationAcknowledgmentKids");
            openElement("GoodsDeclaration");
            
            	writeElement("ReferenceNumber", message.getReferenceNumber());
                writeElement("MRN", message.getMrn());
                writeFormattedDateTime("RegistrationDateTime", message.getRegistrationDateAndTime(),
                		message.getRegistrationDateTimeFormat(), EFormat.KIDS_DateTime);		
                writeElement("CustomsOfficeOfLodgement", message.getCustomsOfficeOfLodgment());
                if (message.getPersonLodgingSuma() != null) {
                    writePartyTIN("PersonLodgingSuma", message.getPersonLodgingSuma().getPartyTIN());
                    writePartyAddress("PersonLodgingSuma", message.getPersonLodgingSuma());	                		                
                }
            closeElement();
            closeElement();
            closeElement();

        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
	}
}
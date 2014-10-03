package com.kewill.kcx.component.mapping.formats.uids.ics;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.ics.msg.MsgArrivalNotificationValidation;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsMessageICS;
import com.kewill.kcx.component.mapping.util.EFormat;

/**
 * Module: BodyArrivalNotificationValidationUids<br>
 * Date Started: 07.14.2010<br>
 * Description	: UIDS-Body of ICSArrivalNotificationValidation.
 * 				: (KIDS: ICSArrivalNotificationValidation, IE348)
 * 
 * @author Frederick Topico
 * @version 1.0.00
 */

public class BodyArrivalNotificationValidationUids extends UidsMessageICS {

	private MsgArrivalNotificationValidation message;
	
	public BodyArrivalNotificationValidationUids(XMLStreamWriter writer) {
		this.writer = writer;
	}
	
	public MsgArrivalNotificationValidation getMessage() {
		return message;
	}
	
	public void setMessage(MsgArrivalNotificationValidation argument) {
		this.message = argument;
	}
	
	public void writeBody() {
		
		try {
			openElement("soap:Body");
			openElement("Submit");
			setAttribute("xmlns", "http://www.eurtradenet.com/schemas/uids/ics/body/200911"); //EI20130904
			openElement("ICS");
			openElement("ICSArrivalNotificationValidation");
				writeElement("LocalReferenceNumber", message.getReferenceNumber());
				writeElement("MRN", message.getMrn());
				//EI20110105: writeElement("RegistrationDateAndTime", message.getRegistrationDateTime());
				writeFormattedDateTime("RegistrationDateAndTime", message.getRegistrationDateTime(), 
						message.getRegistrationDateTimeFormat(), EFormat.ST_DateTimeTZ);	
			closeElement();
			closeElement();
			closeElement();
			closeElement();
						
		} catch (XMLStreamException e) {
            e.printStackTrace();
        } 
	}
}

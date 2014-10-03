package com.kewill.kcx.component.mapping.formats.uids.ics20;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.ics20.msg.MsgArrivalNotificationValidation;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsMessageICS20;
import com.kewill.kcx.component.mapping.util.EFormat;

/**
 * Module       : ICS20<br>
 * Created		: 2012.10.23<br>
 * Description	: UIDS-Body of ICSArrivalNotificationValidation (KIDS: ICSArrivalNotificationValidation, IE348).
 * 				: new for V20: EntryCarrier 
 * 
 * @author iwaniuk
 * @version 2.0.00
 */

public class BodyArrivalNotificationValidationUids extends UidsMessageICS20 {

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
				writeFormattedDateTime("RegistrationDateAndTime", message.getRegistrationDateTime(), 
						message.getRegistrationDateTimeFormat(), EFormat.ST_DateTimeTZ);	
				writeTrader("EntryCarrier", message.getCarrier());	                   //new for V20
				
			closeElement();
			closeElement();
			closeElement();
			closeElement();
						
		} catch (XMLStreamException e) {
            e.printStackTrace();
        } 
	}
}

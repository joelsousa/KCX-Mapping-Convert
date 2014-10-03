package com.kewill.kcx.component.mapping.formats.uids.ics20;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.ics20.msg.MsgDiversionRequestRejected;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.FunctionalError;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsMessageICS20;
import com.kewill.kcx.component.mapping.util.EFormat;

/**
 * Module		: ICS20<br>
 * Created		: 2012.10.23<br>
 * Description	: UIDS-Body of ICSDiversionRequestRejected message.
 * 				: (KIDS: ICSDiversionRequestRejected, IE324)
 * 
 * @author	: iwaniuk
 * @version 2.0.00
 *
 */
public class BodyDiversionRequestRejectedUids extends UidsMessageICS20 {
	private MsgDiversionRequestRejected message;
	
	public BodyDiversionRequestRejectedUids(XMLStreamWriter writer) {
		this.writer	= writer;
	}
	
	public MsgDiversionRequestRejected getMessage() {
		return this.message;
	}
	public void setMessage(MsgDiversionRequestRejected msg) {
		this.message	= msg;
	}
	
	public void writeBody() {
		try {
			openElement("soap:Body");
			openElement("Submit");
			openElement("ICS");
			setAttribute("xmlns", "http://www.eurtradenet.com/schemas/uids/ics/body/200911"); //EI20130904
			openElement("ICSDiversionRequestRejected");
			
				writeElement("LocalReferenceNumber", message.getReferenceNumber());				
				writeFormattedDateTime("RejectionDateAndTime", message.getDeclarationRejectionDateAndTime(), 
								message.getDeclarationRejectionDateAndTimeFormat(), EFormat.ST_DateTimeTZ);				
				writeElement("RejectionReason", message.getDeclarationRejectionReason());
				
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
		
}

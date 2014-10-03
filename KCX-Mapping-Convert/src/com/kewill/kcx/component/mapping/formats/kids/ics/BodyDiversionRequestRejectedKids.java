package com.kewill.kcx.component.mapping.formats.kids.ics;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.ics.msg.MsgDiversionRequestRejected;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.FunctionalError;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessageICS;
import com.kewill.kcx.component.mapping.util.EFormat;

/**
 * Module		: BodyDiversionRequestRejectedKids<br>
 * Created		: 09.11.2010
 * Description	: The body of the KIDS format of DiversionRequestRejected message.
 * 
 * @author	: Michelle Bauza
 * @version 1.0.00
 *
 */
public class BodyDiversionRequestRejectedKids extends KidsMessageICS {
	private MsgDiversionRequestRejected message;
	
	public BodyDiversionRequestRejectedKids(XMLStreamWriter writer) {
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
			openElement("ICSDiversionRequestRejected");
			openElement("GoodsDeclaration");
			
				writeElement("ReferenceNumber", message.getReferenceNumber());				
				writeFormattedDateTime("DeclarationRejectionDateAndTime", message.getDeclarationRejectionDateAndTime(), 
							message.getDeclarationRejectionDateAndTimeFormat(), EFormat.KIDS_DateTime);				
				writeElement("DeclarationRejectionReason", message.getDeclarationRejectionReason());
				writeElement("DeclarationRejectionReasonLNG", message.getDeclarationRejectionReasonLNG());
				if (message.getFunctionalErrorList() != null) {
					for (FunctionalError fError : message.getFunctionalErrorList()) {
						writeError("FunctionalError", fError);
					}
				}
					
			closeElement();
			closeElement();
			closeElement();
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
	}
	
	private void writeError(String tag, FunctionalError fError) throws XMLStreamException {
		openElement(tag);
			writeElement("ErrorType", fError.getErrorType());
			writeElement("ErrorPointer", fError.getErrorPointer());
			writeElement("ErrorReason", fError.getErrorReason());
			writeElement("OriginalAttributeValue", fError.getOriginalAttributeValue());
		closeElement();
	}
}

package com.kewill.kcx.component.mapping.formats.uids.ncts;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.ncts.msg.MsgNCTSDeclarationRejected;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.FunctionalErrorNcts;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsMessageNCTS;
import com.kewill.kcx.component.mapping.util.EFormat;

/**
 * Module		: BodyNCTSDeclarationRejectedUids<br>
 * Created		: 08.26.2010<br>
 * Description	: Contains message structure for NCTSDeclarationRejected UIDS. 
 * 
 * @author Lassiter Caviles
 * @version 1.0.00
 */
public class BodyNCTSDeclarationRejectedUids extends UidsMessageNCTS {

	private MsgNCTSDeclarationRejected message;

	public BodyNCTSDeclarationRejectedUids(XMLStreamWriter writer) {
		this.writer = writer;
	}

	public MsgNCTSDeclarationRejected getMessage() {
		return message;
	}

	public void setMessage(MsgNCTSDeclarationRejected message) {
		this.message = message;
	}
	public void writeBody() {
		try {
			openElement("soap:Body");
			openElement("Submit");
			openElement("NCTS");
			openElement("NCTSDeclarationRejected");
			
				writeElement("LocalReferenceNumber", message.getReferenceNumber());
				writeElement("MRN", message.getUcrNumber());
				writeElement("TypeOfDeclaration", message.getTypeOfDeclaration());						
				writeFormattedDateOrTime("ArrivalRejectionDate", message.getArrivalRejectionDate(),
								message.getArrivalRejectionDateFormat(), EFormat.ST_Date);						
				writeElement("ArrivalRejectionReason", message.getReason());						
				writeFunctionalErrorList(message.getErrorList());
				
			closeElement();
			closeElement();
			closeElement();
			closeElement();
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
	}
}

package com.kewill.kcx.component.mapping.formats.kids.ncts;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.ncts.msg.MsgNCTSArrivalRejection;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.FunctionalErrorNcts;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessageNCTS;
import com.kewill.kcx.component.mapping.util.EFormat;

/**
 * Module		: BodyNCTSArrivalRejectionKids<br>
 * Created		: 08.26.2010<br>
 * Description	: Contains message structure for NCTSArrivalRejection KIDS. 
 * 
 * @author Michelle Bauza
 * @version 1.0.00
 */
public class BodyNCTSArrivalRejectionKids extends KidsMessageNCTS {
	private MsgNCTSArrivalRejection message;
	
	public BodyNCTSArrivalRejectionKids(XMLStreamWriter writer) {
		this.writer	= writer;
	}

	public void setMessage(MsgNCTSArrivalRejection msg) {
		this.message	= msg;
	}
	
	public MsgNCTSArrivalRejection getMessage() {
		return this.message;
	}

	public void writeBody() {
		try {
			openElement("soap:Body");
				openElement("ArrivalRejection");
					openElement("GoodsDeclaration");
						writeElement("ReferenceNumber", message.getReferenceNumber());
						writeElement("UCRNumber", message.getUCRNumber());											
						writeFormattedDateOrTime("ArrivalRejectionDate", message.getArrivalRejectionDate(),	
									message.getArrivalRejectionDateFormat(), EFormat.KIDS_Date);
						writeElement("Action", message.getAction());
						writeElement("Reason", message.getReason());
						
						if (message.getErrorList() != null) {
							for (FunctionalErrorNcts errList : message.getErrorList()) {
								writeFunctionalErrorNcts("Error", errList);
							}
						}
					closeElement();
				closeElement();
			closeElement();
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
	}
}

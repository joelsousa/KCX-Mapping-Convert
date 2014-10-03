package com.kewill.kcx.component.mapping.formats.kids.ncts;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.ncts.msg.MsgNCTSUnloadingRemarksRejection;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.FunctionalErrorNcts;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessageNCTS;
import com.kewill.kcx.component.mapping.util.EFormat;

/**
 * Module		: BodyNCTSUnloadingRemarksRejectionKids
 * Created		: 31.08.2010
 * Description	: . 
 * 
 * @author	: Michelle Bauza
 * @version	: 1.0.00
 *
 */
public class BodyNCTSUnloadingRemarksRejectionKids extends KidsMessageNCTS {
	
	private MsgNCTSUnloadingRemarksRejection message;
	
	public BodyNCTSUnloadingRemarksRejectionKids(XMLStreamWriter writer) {
		this.writer	= writer;
	}

	public void setMessage(MsgNCTSUnloadingRemarksRejection msg) {
		this.message = msg;
	}
	
	public MsgNCTSUnloadingRemarksRejection getMessage() {
		return this.message;
	}

	public void writeBody() {
		try {
			openElement("soap:Body");
			openElement("UnloadingRemarkRejection");
			openElement("GoodsDeclaration");
				writeElement("ReferenceNumber", message.getReferenceNumber());
				writeElement("UCRNumber", message.getUCRNumber());						
				writeFormattedDateOrTime("ArrivalRejectionDate", message.getArrivalRejectionDate(),									
								message.getArrivalRejectionDateFormat(), EFormat.KIDS_Date);						
				writeElement("Reason", message.getReason());
						
				if (message.getErrorList() != null) {
					for (FunctionalErrorNcts error : message.getErrorList()) {
						writeFunctionalErrorNcts("Error", error);
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

package com.kewill.kcx.component.mapping.formats.uids.ncts;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.ncts.msg.MsgNCTSUnloadingRemarksRejection;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.FunctionalErrorNcts;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsMessageNCTS;
import com.kewill.kcx.component.mapping.util.EFormat;

/**
 * Module		: BodyNCTSUnloadingRemarksRejectionUids
 * Created		: 31.08.2010
 * Description	: . 
 * 
 * @author	: Michelle Bauza
 * @version	: 1.0.00
 *
 */
public class BodyNCTSUnloadingRemarksRejectionUids extends UidsMessageNCTS {
	
	private MsgNCTSUnloadingRemarksRejection message;
	
	public BodyNCTSUnloadingRemarksRejectionUids(XMLStreamWriter writer) {
		this.writer	= writer;
	}

	public void setMessage(MsgNCTSUnloadingRemarksRejection msg) {
		this.message	= msg;
	}
	
	public MsgNCTSUnloadingRemarksRejection getMessage() {
		return this.message;
	}

	public void writeBody() {
		try {
			openElement("soap:Body");
			openElement("Submit");
			openElement("NCTS");
			openElement("NCTSUnloadingRemarksRejection");
			
				writeElement("LocalReferenceNumber", message.getReferenceNumber());
				writeElement("MRN", message.getUCRNumber());						
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

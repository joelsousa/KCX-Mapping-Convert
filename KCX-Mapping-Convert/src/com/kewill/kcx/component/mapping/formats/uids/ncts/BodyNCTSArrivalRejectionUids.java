package com.kewill.kcx.component.mapping.formats.uids.ncts;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.ncts.msg.MsgNCTSArrivalRejection;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.FunctionalErrorNcts;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsMessageNCTS;
import com.kewill.kcx.component.mapping.util.EFormat;

/**
 * Module		: BodyNCTSArrivalRejectionUids
 * Created		: 23.08.2010
 * Description	: Contains message structure for NCTSArrivalRejection UIDS.
 * 
 * @author	: Michelle Bauza
 * @version 1.0.00
 */
public class BodyNCTSArrivalRejectionUids extends UidsMessageNCTS {
	
	private MsgNCTSArrivalRejection message;
	
	public BodyNCTSArrivalRejectionUids(XMLStreamWriter writer) {
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
			openElement("Submit");
			openElement("NCTS");
			openElement("NCTSArrivalRejection");
			
				writeElement("LocalReferenceNumber", message.getReferenceNumber());
				writeElement("MRN", message.getUCRNumber());										
				writeFormattedDateOrTime("ArrivalRejectionDate", message.getArrivalRejectionDate(), 
							message.getArrivalRejectionDateFormat(), EFormat.ST_Date);						
				writeElement("ActionToBeTaken", message.getAction());
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

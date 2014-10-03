package com.kewill.kcx.component.mapping.formats.uids.ncts;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.ncts.msg.MsgNCTSMRNAllocated;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsMessageNCTS;
import com.kewill.kcx.component.mapping.util.EFormat;

/**
 * Module : BodyNCTSMRNAllocatedUids<br>
 * Created : 08.26.2010<br>
 * Description : Contains message structure for NCTSMRNAllocated UIDS.
 * 
 * @author Paul Pagdanganan
 * @version 1.0.00
 */
public class BodyNCTSMRNAllocatedUids extends UidsMessageNCTS {

	private MsgNCTSMRNAllocated message;

	public BodyNCTSMRNAllocatedUids(XMLStreamWriter writer) {
		this.writer = writer;
	}

	public MsgNCTSMRNAllocated getMsgNCTSMRNAllocated() {
		return message;
	}

	public void setMsgNCTSMRNAllocated(MsgNCTSMRNAllocated message) {
		this.message = message;
	}

	public void writeBody() {
		try {
			openElement("soap:Body");
			openElement("Submit");
			openElement("NCTS");
			openElement("NCTSMRNAllocated");
			
				writeElement("LocalReferenceNumber", message.getReferenceNumber());
				writeElement("MRN", message.getUcrNumber());			
				writeFormattedDateOrTime("DateOfAcceptance", message.getAcceptanceDate(),
					message.getAcceptanceDateFormat(), EFormat.ST_Date);  //EI20110815	
				//EI20111026: writePrincipal(message.getPrincipalTIN(), message.getPrincipal());
				writePartyNCTS("Principal", message.getPrincipalTIN(), message.getPrincipal()); //EI20111026
				writeElement("OfficeOfDeparture", message.getOfficeOfDeparture());
				writePdfInformation(message.getPdfInformation());
						
				if (message.getTydenSealNumberList() != null) {
					for (String entry : message.getTydenSealNumberList()) {
						writeElement("Tydensealnumber", entry);
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

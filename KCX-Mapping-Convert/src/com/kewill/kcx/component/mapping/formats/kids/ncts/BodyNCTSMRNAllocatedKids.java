package com.kewill.kcx.component.mapping.formats.kids.ncts;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.common.Party;
import com.kewill.kcx.component.mapping.countries.common.TIN;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.MsgNCTSMRNAllocated;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessageNCTS;
import com.kewill.kcx.component.mapping.util.EFormat;

/**
 * Module : BodyNCTSMRNAllocatedKids<br>
 * Created : 08.26.2010<br>
 * Description : Contains message structure for NCTSMRNAllocated KIDS.
 * 
 * @author Paul Pagdanganan
 * @version 1.0.00
 */
public class BodyNCTSMRNAllocatedKids extends KidsMessageNCTS {

	private MsgNCTSMRNAllocated message;

	public BodyNCTSMRNAllocatedKids(XMLStreamWriter writer) {
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
			openElement("NCTSMRNAllocated");
			openElement("GoodsDeclaration");
					writeElement("ReferenceNumber", message.getReferenceNumber());
					writeElement("UCRNumber", message.getUcrNumber());					
					writeFormattedDateOrTime("AcceptanceDate", message.getAcceptanceDate(), 
							         message.getAcceptanceDateFormat(), EFormat.KIDS_Date);	
					//writePrincipal(message.getPrincipalTIN(), message.getPrincipal());
					writePartyTIN("PrincipalTIN", message.getPrincipalTIN());
					writeParty("Principal", message.getPrincipal());
					writeElement("OfficeOfDeparture", message.getOfficeOfDeparture());
					writePDFInformation(message.getPdfInformation());
						
					if (message.getTydenSealNumberList() != null) {
						for (String entry : message.getTydenSealNumberList()) {
							writeElement("TydenSealNumber", entry);
						}
					}					
			closeElement();
			closeElement();
			closeElement();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
   /*
	private void writePrincipal(TIN argument1, Party argument2)
			throws XMLStreamException {
		if (argument1 == null || argument2 == null) {
			return;
		}
		if (argument1.isEmpty() || argument2.isEmpty()) {
			return;
		}

		openElement("PrincipalTIN");
			writeElement("TIN", argument1.getTIN());
			writeElement("IsTINGermanApprovalNumber", argument1.getIsTINGermanApprovalNumber());
			writeElement("CustomerIdentifier", argument1.getCustomerIdentifier());
		closeElement();
		
		openElement("Principal");
			writeElement("VATNumber", argument2.getVATNumber());
			writeElement("ETNAddress", argument2.getETNAddress());
			writeAddress("Address", argument2.getAddress());
		closeElement();
	}
	*/
}

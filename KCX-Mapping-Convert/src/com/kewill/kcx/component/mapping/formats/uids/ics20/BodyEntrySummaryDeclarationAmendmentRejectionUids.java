package com.kewill.kcx.component.mapping.formats.uids.ics20;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.ics20.msg.MsgEntrySummaryDeclarationAmendmentRejection;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.FunctionalError;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsMessageICS20;
import com.kewill.kcx.component.mapping.util.EFormat;

/**
 * Module		: ICS20<br>
 * Created		: 2012.10.23<br>
 * Description	: UIDS Body format of EntrySummaryDeclarationAmendmentRejection.
 *              : (KIDS: ICSEntrySummaryDeclarationAmendmentRejection, IE305)
 * 
 * @author iwaniuk
 * @version 2.0.00
 */
public class BodyEntrySummaryDeclarationAmendmentRejectionUids extends UidsMessageICS20 {

	private MsgEntrySummaryDeclarationAmendmentRejection message;
	
	public BodyEntrySummaryDeclarationAmendmentRejectionUids(XMLStreamWriter writer) {
        this.writer = writer;
    }
	
	public MsgEntrySummaryDeclarationAmendmentRejection getMessage() {
		return message;
	}
	
	public void setMessage(MsgEntrySummaryDeclarationAmendmentRejection argument) {
		this.message = argument;
	}
	
	public void writeBody() {
		try {
			openElement("soap:Body");
			openElement("Submit");
			setAttribute("xmlns", "http://www.eurtradenet.com/schemas/uids/ics/body/200911"); //EI20130904
			openElement("ICS");
			//EI20110120: openElement("ICSEntrySummaryDeclarationAmendmentRejection");
			openElement("ICSDeclarationAmendmentRejected");
			
				writeElement("LocalReferenceNumber", message.getReferenceNumber());
				writeElement("MRN", message.getMrn());											
				writeFormattedDateTime("RejectionDateAndTime", message.getAmendmentRejectionDateAndTime(), 
							message.getAmendmentRejectionDateAndTimeFormat(), EFormat.ST_DateTimeTZ);									
				writeElement("OfficeOfFirstEntry", message.getCustomsOfficeFirstEntry());
				writeTrader("PersonLodgingSuma", message.getPersonLodgingSuma());
				writeTrader("Representative", message.getRepresentative());											
				writeFormattedDateTime("DateAndTimeOfAmendment", message.getAmendmentDateAndTime(), 
							message.getAmendmentDateAndTimeFormat(), EFormat.ST_DateTimeTZ);
				writeText("Motivation", message.getMotivation());		//new V20				
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

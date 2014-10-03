package com.kewill.kcx.component.mapping.formats.kids.ics;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.ics.msg.MsgEntrySummaryDeclarationAmendmentRejection;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.FunctionalError;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessageICS;
import com.kewill.kcx.component.mapping.util.EFormat;

/**
 * Module 		: BodyEntrySummaryDeclarationAmendmentRejectionKids<br>
 * Date Started	: November 09, 2010<br>
 * Description	: Body class of EntrySummaryDeclarationAmendmentRejectionKids.
 * 
 * @author Frederick Topico
 * @version 1.0.00
 */
public class BodyEntrySummaryDeclarationAmendmentRejectionKids extends KidsMessageICS {

	private MsgEntrySummaryDeclarationAmendmentRejection message;
	
	public BodyEntrySummaryDeclarationAmendmentRejectionKids(XMLStreamWriter writer) {
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
        	openElement("ICSEntrySummaryDeclarationAmendmentRejection");
        	openElement("GoodsDeclaration"); 
        	
				writeElement("ReferenceNumber", message.getReferenceNumber());
				writeElement("MRN", message.getMrn());										
				writeFormattedDateTime("AmendmentRejectionDateAndTime", message.getAmendmentRejectionDateAndTime(),
							message.getAmendmentRejectionDateAndTimeFormat(), EFormat.KIDS_DateTime);				
				writeElement("CustomsOfficeFirstEntry", message.getCustomsOfficeFirstEntry());
				if (message.getPersonLodgingSuma() != null) {
					writePartyTIN("PersonLodgingSuma", message.getPersonLodgingSuma().getPartyTIN());
					writePartyAddress("PersonLodgingSuma", message.getPersonLodgingSuma());
				}
				if (message.getRepresentative() != null) {							
					writePartyTIN("Representative", message.getRepresentative().getPartyTIN());
					writePartyAddress("Representative", message.getRepresentative());
				}				
				writeFormattedDateTime("AmendmentDateAndTime", message.getAmendmentDateAndTime(),
							message.getAmendmentDateAndTimeFormat(), EFormat.KIDS_DateTime);				
				if (message.getFunctionalErrorList() != null) {
					for (FunctionalError functionalError :
						message.getFunctionalErrorList()) {
						writeFunctionalError("FunctionalError", functionalError);
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

package com.kewill.kcx.component.mapping.formats.kids.ics;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.ics.msg.MsgEntrySummaryDeclarationRejected;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.FunctionalError;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessageICS;
import com.kewill.kcx.component.mapping.util.EFormat;
/**
 * Module		: BodyEntrySummaryDeclarationRejectedKids<br>
 * Created		: 2010.11.08<br>
 * Description	: The body of the UIDS format to KIDS format of EntrySummaryDeclarationRejected message.
 * 				: (IE316)
 * @author Elbert Jude Eco
 * @version 1.0.00
 *
 */
public class BodyEntrySummaryDeclarationRejectedKids extends KidsMessageICS {
	private MsgEntrySummaryDeclarationRejected message;	

    public BodyEntrySummaryDeclarationRejectedKids(XMLStreamWriter writer) {
        this.writer = writer;
    }
   
	public MsgEntrySummaryDeclarationRejected getMessage() {
		return message;
	}
	
	public void setMessage(MsgEntrySummaryDeclarationRejected argument) {
		this.message = argument;
	}
	
	public void writeBody() {
		try {
			openElement("soap:Body");
        	openElement("ICSEntrySummaryDeclarationRejected");
        	openElement("GoodsDeclaration");
        	
                writeElement("ReferenceNumber", message.getReferenceNumber());  
              //writeDateTimeToString("DeclarationRejectionDateAndTime", message.getDeclarationRejectionDateAndTime());
	            writeFormattedDateTime("DeclarationRejectionDateAndTime", message.getDeclarationRejectionDateAndTime(),
	                    	message.getDeclarationRejectionDateAndTimeFormat(), EFormat.KIDS_DateTime);
                writeElement("DeclarationRejectionReason", message.getDeclarationRejectionReason());
                writeElement("DeclarationRejectionReasonLNG", message.getDeclarationRejectionReasonLNG());
                if (message.getFunctionalErrorList() != null) {
	                for (FunctionalError error : message.getFunctionalErrorList()) {
	                    writeFunctionalError("FunctionalError", error);
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

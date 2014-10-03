package com.kewill.kcx.component.mapping.formats.kids.ics20;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.ics20.msg.MsgEntrySummaryDeclarationRejected;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.FunctionalError;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessageICS20;
import com.kewill.kcx.component.mapping.util.EFormat;

/**
 * Module		: ICS20<br>
 * Created		: 2012.10.24<br>
 * Description	: Body of EntrySummaryDeclarationRejected in KIDS format.
 * 				: (IE316)
 * 				: new V20: DeclarationRejectionReasonLNG
 * 
 * @author iwaniuk
 * @version 2.0.00
 *
 */
public class BodyEntrySummaryDeclarationRejectedKids extends KidsMessageICS20 {
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
	            writeFormattedDateTime("DeclarationRejectionDateAndTime", message.getDeclarationRejectionDateAndTime(),
	                    	message.getDeclarationRejectionDateAndTimeFormat(), EFormat.KIDS_DateTime);
                writeElement("DeclarationRejectionReason", message.getDeclarationRejectionReason());
                writeElement("DeclarationRejectionReasonLNG", message.getDeclarationRejectionReasonLNG()); //new V20
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

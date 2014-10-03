package com.kewill.kcx.component.mapping.formats.uids.ics;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.ics.msg.MsgEntrySummaryDeclarationRejected;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.FunctionalError;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsMessageICS;
import com.kewill.kcx.component.mapping.util.EFormat;
/**
 * Module		: BodyEntrySummaryDeclarationRejectedUids<br>
 * Created		: 2010.11.09<br>
 * Description	: UIDS Body format of ICSDeclarationRejected.
 *  			: (KIDS: ICSEntrySummaryDeclarationRejected, IE316) 
 * 
 * @author Elbert Jude Eco
 * @version 1.0.00
 *
 */
public class BodyEntrySummaryDeclarationRejectedUids extends UidsMessageICS {
	private MsgEntrySummaryDeclarationRejected message;	

    public BodyEntrySummaryDeclarationRejectedUids(XMLStreamWriter writer) {
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
			openElement("Submit");
			setAttribute("xmlns", "http://www.eurtradenet.com/schemas/uids/ics/body/200911"); //EI20130904
			openElement("ICS");
	        //EI20110120: openElement("ICSEntrySummaryDeclarationRejected");
	        openElement("ICSDeclarationRejected");
	        
	        	writeElement("LocalReferenceNumber", message.getReferenceNumber());	            
		        writeFormattedDateTime("RejectionDateAndTime", message.getDeclarationRejectionDateAndTime(),
		               message.getDeclarationRejectionDateAndTimeFormat(), EFormat.ST_DateTimeTZ);	            
	            writeElement("RejectionReason", message.getDeclarationRejectionReason());
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

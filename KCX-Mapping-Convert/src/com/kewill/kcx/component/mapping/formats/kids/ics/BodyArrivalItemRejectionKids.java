package com.kewill.kcx.component.mapping.formats.kids.ics;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.ics.msg.MsgArrivalItemRejection;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.FunctionalError;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.ImportDetails;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessageICS;
import com.kewill.kcx.component.mapping.util.EFormat;

/**
 * Module		: BodyArrivalItemRejectionKids<br>
 * Created		: 2010.07.20<br>
 * Description	: The body of the UIDS format to KIDS format of ArrivalItemRejection message.
 * 
 * @author Elbert Jude Eco
 * @version 1.0.00
 *
 */
public class BodyArrivalItemRejectionKids extends KidsMessageICS {
	
	private MsgArrivalItemRejection message;	

    public BodyArrivalItemRejectionKids(XMLStreamWriter writer) {
        this.writer = writer;
    }
   
	public MsgArrivalItemRejection getMessage() {
		return message;
	}
	
	public void setMessage(MsgArrivalItemRejection argument) {
		this.message = argument;
	}
	
	public void writeBody() {
		try {
			openElement("soap:Body");
        	openElement("ICSArrivalItemRejection");
        	openElement("GoodsDeclaration");
                writeElement("ReferenceNumber", message.getReferenceNumber());                
	            writeFormattedDateTime("RejectionDateTime", message.getRejectionDateTime(),
	                    	message.getRejectionDateTimeFormat(), EFormat.KIDS_DateTime);                
                writeElement("RejectionReasonHeader", message.getRejectionReasonHeader());
                writeElement("RejectionReasonHeaderLNG", message.getRejectionReasonHeaderLNG());
                //writeTransportMeansBorder(message.getMeansOfTransportBorder());               
	            writeFormattedDateTime("DeclaredDateOfArrival", message.getDeclaredDateOfArrival(),
	                    	message.getDeclaredDateOfArrivalFormat(), EFormat.KIDS_DateTime);               
                writeElement("ConveyanceReference", message.getConveyanceReference());
                writeTransportMeansBorder(message.getMeansOfTransportBorder()); 
                if (message.getImportDetailsList() != null) {
	                for (ImportDetails details : message.getImportDetailsList()) {
	                    writeImportDetails(details);
	                }
                }                		
                if (message.getCarrier() != null) {
                	writePartyTIN("Carrier", message.getCarrier().getPartyTIN());
                	writePartyAddress("Carrier", message.getCarrier());
                }
                		                		
                if (message.getFunctionalErrorList() != null) {
	                for (FunctionalError error : 
	                	message.getFunctionalErrorList()) {
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

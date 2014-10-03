package com.kewill.kcx.component.mapping.formats.kids.ics20;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.ics20.msg.MsgArrivalItemRejection;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.FunctionalError;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.ImportDetails;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessageICS20;
import com.kewill.kcx.component.mapping.util.EFormat;

/**
 * Module		: ICS20<br>
 * Created		: 2012.10.25<br>
 * Description	: Body of ArrivalItemRejection in KIDS format.
 * 				:(IE349)
 * 
 * @author iwaniuk
 * @version 2.0.00
 *
 */
public class BodyArrivalItemRejectionKids extends KidsMessageICS20 {
	
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

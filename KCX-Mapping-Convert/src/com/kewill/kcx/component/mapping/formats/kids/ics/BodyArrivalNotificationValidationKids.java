package com.kewill.kcx.component.mapping.formats.kids.ics;

import java.util.ArrayList;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.ics.msg.MsgArrivalNotificationValidation;
import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.FunctionalError;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessageICS;
import com.kewill.kcx.component.mapping.util.EFormat;

/**
 * Module		: BodyArrivalNotificationValidationKids<br>
 * Date Created	: July 19, 2010<br>
 * Description	: The body of the KIDS format of ArrivalNotificationValidation message.
 * 
 * @author Frederick Topico
 * @version 1.0.00
 *
 */
public class BodyArrivalNotificationValidationKids extends KidsMessageICS  {

	private MsgArrivalNotificationValidation message;
	
	public BodyArrivalNotificationValidationKids(XMLStreamWriter writer) {
        this.writer = writer;
    }
	
	public MsgArrivalNotificationValidation getMessage() {
		return message;
	}
	
	public void setMessage(MsgArrivalNotificationValidation argument) {
		this.message = argument;
	}
	
	public void writeBody() {
		try {
			openElement("soap:Body");
        	openElement("ICSArrivalNotificationValidation");
            openElement("GoodsDeclaration"); 
            
                writeElement("ReferenceNumber", message.getReferenceNumber());
                writeElement("MRN", message.getMrn());               
                writeFormattedDateTime("RegistrationDateTime", message.getRegistrationDateTime(),
	                	message.getRegistrationDateTimeFormat(), EFormat.KIDS_DateTime);	 
                writeFormattedDateTime("ArrivalRegistrationDateTime", message.getArrivalRegistrationDateTime(),
	                	message.getArrivalRegistrationDateTimeFormat(), EFormat.KIDS_DateTime);	//EI20130114  
                writeFormattedDateTime("ArrivalRejectionDateTime", message.getRejectionDateTime(),
	                	message.getRejectionDateTimeFormat(), EFormat.KIDS_DateTime);   //EI20130114 
                writeElement("ArrivalRejectionReason", message.getRejectionReason());   //EI20130114                 	
                if (message.getCarrier() != null) {
                	writePartyTIN("Carrier", message.getCarrier().getPartyTIN());
                	writePartyAddress("Carrier", message.getCarrier());
                }                                 
                //EI20131001: writeFunctionalError("FunctionalError", message.getFunctionalError());   //EI20130114         
                writeFunctionalErrorList(message.getFunctionalErrorList());   //EI20131001
                writeElement("CustomsOfficeFirstEntry", message.getCustomsOfficeFirstEntry()); //EI20130114   
                                
            closeElement();
            closeElement();
            closeElement();
		} catch (XMLStreamException e) {
            e.printStackTrace();
        }  
	}

	private void writeFunctionalErrorList(ArrayList<FunctionalError> functionalErrorList)throws XMLStreamException {
		if (functionalErrorList != null && functionalErrorList.size() > 0) {
			for (FunctionalError fuError : functionalErrorList) {
				if (fuError != null) {
					writeFunctionalError("FunctionalError", fuError); //EI20131001	
				}
			}
		}
		
	}

}

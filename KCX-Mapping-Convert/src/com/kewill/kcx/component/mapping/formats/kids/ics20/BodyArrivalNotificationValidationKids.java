package com.kewill.kcx.component.mapping.formats.kids.ics20;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.ics.msg.common.FunctionalError;
import com.kewill.kcx.component.mapping.countries.de.ics20.msg.MsgArrivalNotificationValidation;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessageICS20;
import com.kewill.kcx.component.mapping.util.EFormat;

/**
 * Module		: ICS20<br>
 * Created		: 19.10.2012<br>
 * Description	: Body of ArrivalNotificationValidation in KIDS format.
 *              : (IE348)
 * 
 * @author Alfred Krzoska
 * @version 2.0.00
 */
public class BodyArrivalNotificationValidationKids extends KidsMessageICS20  {

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
                		message.getArrivalRegistrationDateTimeFormat(), EFormat.KIDS_DateTime);
                writeFormattedDateTime("ArrivalRejectionDateTime", message.getArrivalRejectionDateTime(),
                		message.getArrivalRejectionDateTimeFormat(), EFormat.KIDS_DateTime);
                writeElement("ArrivalRejectionReason", message.getArrivalRejectionReason());
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

                writeElement("CustomsOfficeFirstEntry", message.getCustomsOfficeFirstEntry());
                
            closeElement();   	//GoodsDeclaration
            closeElement();		//ICSArrivalNotificationValidation
            closeElement();		//soap:Body
		} catch (XMLStreamException e) {
            e.printStackTrace();
        }  
	}
}

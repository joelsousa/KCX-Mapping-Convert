package com.kewill.kcx.component.mapping.formats.kids.emcs20;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.emcs.msg.MsgCancellation;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessageEMCS;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Modul		: BodyCancellationKids<br>
 * Erstellt		: 07.05.2010<br>
 * Beschreibung : Contains Message Structure with fields used in EMCSCancellation Kids message.
 *              : V20 Tag DateAndTimeOfValidationOfCancellation renamed in DateAndTimeOfValidation.
 *              : new Tag: ComplementaryInformation
 *                 
 * @author iwaniuk
 * @version 2.0.00
 * 
 */

public class BodyCancellationKids extends KidsMessageEMCS {

	private MsgCancellation message;	
	
    public BodyCancellationKids(XMLStreamWriter writer) {
        this.writer = writer;
    }
   
	public MsgCancellation getMessage() {
		return message;
	}
	public void setMessage(MsgCancellation argument) {
		this.message = argument;
	}
		
    public void writeBody() {
        try {        	
            openElement("soap:Body");
            openElement("EMCS");               
            openElement("EMCSCancellation");
            
            	writeElement("ReferenceNumber", message.getReferenceNumber());
                writeElement("CustomerOrderNumber", message.getCustomerOrderNumber());
                writeElement("Clerk", message.getClerk());       	     
                writeElement("AadReferenceCode", message.getAadReferenceCode());    
                //writeDateTimeToString("DateAndTimeOfValidationOfCancellation", message.getDateAndTimeOfValidationOfCancellation());  
                writeDateTimeToString("DateAndTimeOfValidation", message.getDateAndTimeOfValidationOfCancellation());  //V20 renamed              
                writeElement("CancellationReasonCode", message.getCancellationReasonCode());
                if (message.getComplementaryInformation() != null) {                              //V20 new
                	writeCodeElementWithAttribute("ComplementaryInformation", 
                			message.getComplementaryInformation().getText(), "C0012", "language",   
                			message.getComplementaryInformation().getLanguage());
                }
                       		
           closeElement();
           closeElement();	    
           closeElement();	 
           
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }       
	} 
 
}

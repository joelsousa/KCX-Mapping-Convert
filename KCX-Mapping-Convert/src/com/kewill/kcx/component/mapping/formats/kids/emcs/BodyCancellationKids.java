package com.kewill.kcx.component.mapping.formats.kids.emcs;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.emcs.msg.MsgCancellation;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessageEMCS;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Modul		: BodyCancellationKids<br>
 * Erstellt		: 07.05.2010<br>
 * Beschreibung : Contains Message Structure with fields used in EMCSCancellation Kids message.
 *                 
 * @author iwaniuk
 * @version 1.0.00
 * 
 */

public class BodyCancellationKids extends KidsMessageEMCS {

	private MsgCancellation message;	
	private String version = "";         //EI20110701

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
        	version = this.kidsHeader.getRelease();
        	version = Utils.removeDots(version.substring(0, 3));
        	
            openElement("soap:Body");
            openElement("EMCS");               
            openElement("EMCSCancellation");                     
            	writeElement("ReferenceNumber", message.getReferenceNumber());
                writeElement("CustomerOrderNumber", message.getCustomerOrderNumber());
                writeElement("Clerk", message.getClerk());       	     
                writeElement("AadReferenceCode", message.getAadReferenceCode());  
                if (version.equalsIgnoreCase("10")) {   
                	writeDateTimeToString("DateAndTimeOfValidationOfCancellation", 
                			message.getDateAndTimeOfValidationOfCancellation());
                } else {
                    writeDateTimeToString("DateAndTimeOfValidation", 
                    		message.getDateAndTimeOfValidationOfCancellation());
                }
                //writeCodeElement("CancellationReasonCode", message.getCancellationReasonCode(), "C0043");
                writeElement("CancellationReasonCode", message.getCancellationReasonCode());
                if (!version.equals("10") && message.getComplementaryInformation() != null) {
                	//writeCodeElementWithAttribute("ComplementaryInformation", 
                	//		message.getComplementaryInformation().getText(), "C0012", "language",    //EI20110928
                	//		message.getComplementaryInformation().getLanguage());
                	writeElementWithAttribute("ComplementaryInformation", 
                			message.getComplementaryInformation().getText(), "language",    
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

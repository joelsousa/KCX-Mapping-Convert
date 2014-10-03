package com.kewill.kcx.component.mapping.formats.kids.emcs20;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.emcs.msg.MsgInterruptionOfMovement;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessageEMCS;

/**
 * Module		: BodyGenericRefusalMessageKids<br>
 * Created		: 18.05.2010<br>
 * Description  : Contains Message Structure with fields used in Kids EMCSGenericRefusalMessage.
 *              : V20 new Tags: CustomerOrderNumber, Clerk
 *                 
 * @author iwaniuk
 * @version 2.0.00
 */

public class BodyInterruptionOfMovementKids extends KidsMessageEMCS {

	private MsgInterruptionOfMovement message;	
	
    public BodyInterruptionOfMovementKids(XMLStreamWriter writer) {
        this.writer = writer;
    }
   
	public MsgInterruptionOfMovement getMessage() {
		return message;
	}
	public void setMessage(MsgInterruptionOfMovement argument) {
		this.message = argument;
	}
		
    public void writeBody() {
        try {        	
            openElement("soap:Body");
            openElement("EMCS");           
            openElement("EMCSInterruptionOfMovement");     
            
            	writeElement("ReferenceNumber", message.getReferenceNumber());              	
            	writeElement("CustomerOrderNumber", message.getCustomerOrderNumber());   //V20 new
            	writeElement("Clerk", message.getClerk());                               //V20 new            	
            	writeElement("ReasonForInterruption", message.getReasonForInteruption()); 
            	writeElement("AadReferenceCode", message.getAadReferenceCode()); 
            	writeElement("SequenceNumber", message.getSequenceNumber()); 
            	writeDateTimeToString("DateAndTimeOfIssuance", message.getDateAndTimeOfIssuance());  
            	if (message.getComplementaryInformation() != null) {
            		writeElementWithAttribute("ComplementaryInformation", 
                				message.getComplementaryInformation().getText(), "language",
                				message.getComplementaryInformation().getLanguage()); 
            	}						
            	writeElement("ExciseCustomsOffice", message.getExciseCustomsOffice()); 
            	writeElement("CustomsOfficerID", message.getCustomsOfficerID());
                
                if (message.getReferenceControlReports() != null && 
                		message.getReferenceControlReports().getControlReportReferenceList() != null) {
                	openElement("ReferenceControlReports");  
                	for (String control : message.getReferenceControlReports().getControlReportReferenceList()) {
                		writeElement("ControlReportReference", control);
                	}
                	closeElement();
                }
                if (message.getReferenceEventReports() != null && 
                		message.getReferenceEventReports().getEventReportNumberList() != null) {
                	openElement("ReferenceEventReports");  
                	for (String control : message.getReferenceEventReports().getEventReportNumberList()) {
                		writeElement("EventReportNumber", control);
                	}
                	closeElement();
                }
                
           closeElement();
           closeElement();	    
           closeElement();	           
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }       
	} 
 
}

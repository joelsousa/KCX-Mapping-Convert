package com.kewill.kcx.component.mapping.formats.kids.emcs;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.emcs.msg.MsgGenericRefusalMessage;
import com.kewill.kcx.component.mapping.countries.de.emcs.msg.MsgInterruptionOfMovement;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessageEMCS;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Modul		: BodyGenericRefusalMessageKids<br>
 * Erstellt		: 18.05.2010<br>
 * Beschreibung : Contains Message Structure with fields used in Kids EMCSGenericRefusalMessage.
 *                 
 * @author iwaniuk
 * @version 1.0.00
 */

public class BodyInterruptionOfMovementKids extends KidsMessageEMCS {

	private MsgInterruptionOfMovement message;	
	private String version = "";         //EI20110701

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
        	version = this.kidsHeader.getRelease();
        	version = Utils.removeDots(version.substring(0, 3));
        	
            openElement("soap:Body");
            openElement("EMCS");           
            openElement("EMCSInterruptionOfMovement");                      
            	writeElement("ReferenceNumber", message.getReferenceNumber());  
            	if (!version.equalsIgnoreCase("10")) {        //EI20110819
            		writeElement("CustomerOrderNumber", message.getCustomerOrderNumber());
            		writeElement("Clerk", message.getClerk());
            	}
            	//writeCodeElement("ReasonForInterruption", message.getReasonForInteruption(), "C0026"); //EI20110928
            	writeElement("ReasonForInterruption", message.getReasonForInteruption());
            	writeElement("AadReferenceCode", message.getAadReferenceCode()); 
            	writeElement("SequenceNumber", message.getSequenceNumber()); 
            	writeDateTimeToString("DateAndTimeOfIssuance", message.getDateAndTimeOfIssuance());  
            	if (message.getComplementaryInformation() != null) {
            		//writeCodeElementWithAttribute("ComplementaryInformation", 
                	//			message.getComplementaryInformation().getText(), "C0012", "language",
                	//			message.getComplementaryInformation().getLanguage()); 
            		writeElementWithAttribute("ComplementaryInformation", 
            				message.getComplementaryInformation().getText(),"language",
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

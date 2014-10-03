package com.kewill.kcx.component.mapping.formats.uids.emcs;

/* Changes:  
 * -----------
 * EI20110701 for V20: new member: version
 */

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.emcs.msg.MsgExplanationOnDelay;
import com.kewill.kcx.component.mapping.countries.de.emcs.msg.MsgInterruptionOfMovement;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsMessageEMCS;
import com.kewill.kcx.component.mapping.util.EFormat;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: EMCS<br>
 * Created		: 05.07.2011<br>
 * Description  : Contains Message Structure with fields used in EMCSInterruptionOfMovement Uids message.
 *              : new for EMCS V20
 *                 
 * @author iwaniuk
 * @version 2.0.00
 * 
 */

public class BodyInterruptionOfMovementUids extends UidsMessageEMCS {
	
    private MsgInterruptionOfMovement  message = new MsgInterruptionOfMovement();
    private String version = "";         //EI20110701
 
    public BodyInterruptionOfMovementUids(XMLStreamWriter writer) {
        this.writer = writer;
    }

	public MsgInterruptionOfMovement getMessage() {
		return message;
	}

	public void setMessage(MsgInterruptionOfMovement message) {
		this.message = message;
	}
    
	public void writeBody() {		
		
        try {  
        	version = this.uidsHeader.getMessageVersion();
        	version = Utils.removeDots(version.substring(0, 3));
        	
        	openElement("soap:Body");
            openElement("Submit");
                setAttribute("xmlns", "http://www.eurtradenet.com/schemas/uids/emcs/body/200911");                
            openElement("EMCS");
            openElement("EMCSInterruptionOfMovement"); 
                
                writeElement("LocalReferenceNumber", message.getReferenceNumber());                 	
                writeElement("ReasonForInterruption", message.getReasonForInteruption()); //EI20110928 with ...rr...
                writeElement("AadReferenceCode", message.getAadReferenceCode()); 
                writeElement("SequenceNumber", message.getSequenceNumber()); 
                //DateTime format changed:
                //writeStringToDateTime("DateAndTimeOfIssuance", message.getDateAndTimeOfIssuance());  
                writeFormattedDateTime("DateAndTimeOfIssuance", message.getDateAndTimeOfIssuance(),
                		EFormat.KIDS_DateTime, EFormat.ST_DateTimeTZ);	          //EI20111017	
                if (message.getComplementaryInformation() != null) {
                	writeElementWithAttribute("ComplementaryInformation", 
                			message.getComplementaryInformation().getText(), 
                        	"language", message.getComplementaryInformation().getLanguage());
                }
                writeElement("ExciseCustomsOffice", message.getExciseCustomsOffice()); 
                writeElement("IdentificationOfSenderCustomsOfficer", message.getCustomsOfficerID());
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
            closeElement(); 
        } catch (XMLStreamException e) {           
            e.printStackTrace();
        }
    }

}    	

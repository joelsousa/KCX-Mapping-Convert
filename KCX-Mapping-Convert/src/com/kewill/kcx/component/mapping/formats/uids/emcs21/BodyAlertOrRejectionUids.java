package com.kewill.kcx.component.mapping.formats.uids.emcs21;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.emcs.msg.common.RejectionReason;
import com.kewill.kcx.component.mapping.countries.de.emcs21.msg.MsgAlertOrRejection;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsMessageEMCS20;
import com.kewill.kcx.component.mapping.util.EFormat;

/**
 * Module		: EMCS<br>
 * Created		: 05.07.2011<br>
 * Description  : Contains Message Structure with fields used in EMCSAlertOrRejection Uids message.
 *              : new for EMCS V20
 *                 
 * @author iwaniuk
 * @version 2.0.00
 * 
 */

public class BodyAlertOrRejectionUids extends UidsMessageEMCS20 {
	
    private MsgAlertOrRejection  message = new MsgAlertOrRejection();  
    
    public BodyAlertOrRejectionUids(XMLStreamWriter writer) {
        this.writer = writer;
    }

	public MsgAlertOrRejection getMessage() {
		return message;
	}

	public void setMessage(MsgAlertOrRejection message) {
		this.message = message;
	}
    
	public void writeBody() {		
		
        try {         	
        	openElement("soap:Body");
            openElement("Submit");
            	setAttribute("xmlns", "http://www.eurtradenet.com/schemas/uids/emcs/body/200911");               
            openElement("EMCS");               
            openElement("EMCSAlertOrRejection");  
            
            	writeElement("LocalReferenceNumber", message.getReferenceNumber());
                writeElement("AadReferenceCode", message.getAadReferenceCode());
                writeElement("SequenceNumber", message.getSequenceNumber());
                writeElement("DestinationOffice", message.getDestinationOffice());                               
                //writeStringToDateTime("DateAndTimeOfValidation", message.getDateAndTimeOfValidation());                
                writeFormattedDateTime("DateAndTimeOfValidation", message.getDateAndTimeOfValidation(),
							EFormat.KIDS_DateTime, EFormat.ST_DateTimeTZ);	                        
                writeElement("RejectedFlag", message.getRejectedFlag());                       
                writeStringToDate("DateOfAlertOrRejection", message.getDateOfAlertOrRejection()); 
                writeTrader("ConsigneeTrader", message.getConsignee());
                    
                if (message.getAlertOrRejectionReasons() != null) {
                    if (message.getAlertOrRejectionReasons().getRejectionReasonList() != null && 
                    		   !message.getAlertOrRejectionReasons().getRejectionReasonList().isEmpty()) { 
                    	openElement("AlertOrRejectionReasons");  
                    		for (RejectionReason reason : message.getAlertOrRejectionReasons().getRejectionReasonList()) {
                    			writeRejectionReason(reason);  
                    		}
                    	closeElement();                	   
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

	 private void writeRejectionReason(RejectionReason reason)  throws XMLStreamException {
	    	if (reason == null) {
	    		return;
	    	}
	    	if (reason.isEmpty()) {
	    		return;
	    	}
	    	openElement("RejectionReason"); 
	    		writeElement("Code", reason.getCode());
	    		if (reason.getComplementaryInformation() != null) {   		
	                writeElementWithAttribute("ComplementaryInformation", 
	                		reason.getComplementaryInformation().getText(), "language",
	                		reason.getComplementaryInformation().getLanguage()); 
	            }
	    	closeElement();
	  }
}    	

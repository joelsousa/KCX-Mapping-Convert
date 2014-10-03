package com.kewill.kcx.component.mapping.formats.kids.port;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.Import.msg.common.GoodsItemDeclarationDecision;
import com.kewill.kcx.component.mapping.countries.de.Port.msg.MsgPortDeclarationStatus;
import com.kewill.kcx.component.mapping.countries.de.Port.msg.common.StatusAnnotation;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;

/**
 * Module	   : Port.<br>
 * Created	   : 12.09.2011<br>
 * Description : BodyImportDeclarationDecisionKids
 * 
 * @author iwaniuk
 * @version 1.0.00
 *
 */
public class BodyPortDeclarationStatusKids extends KidsMessage {

	private MsgPortDeclarationStatus message;	

    public BodyPortDeclarationStatusKids(XMLStreamWriter writer) {
        this.writer = writer;
    }
   
	public MsgPortDeclarationStatus getMessage() {
		return message;
	}
	
	public void setMessage(MsgPortDeclarationStatus argument) {
		this.message = argument;
	}
		
    public void writeBody() {
        try {
            openElement("soap:Body");
            openElement("PortDeclarationStatus");
            openElement("GoodsDeclaration"); 
          
            	writeElement("ReferenceNumber", message.getReferenceNumber());  
            	writeElement("PortSystem", message.getPortSystem());
                writeElement("SendingDateTime", message.getSendingDateTime());                
                writeElement("StatusSender", message.getStatusSender());    
                writeElement("SequenceNumber", message.getSequenceNumber());
                writeElement("MRN", message.getMrn());  
            	writeElement("PortRegistrationNumber", message.getPortRegistrationNumber());
            	writeElement("PortRegistrationMode", message.getPortRegistrationMode());
                writeElement("StatusKind", message.getStatusKind());  
             	writeElement("StatusCode", message.getStatusCode());
             	writeElement("StatusType", message.getStatusType());
                writeElement("DateOfStatus", message.getDateOfStatus());  
            	writeElement("StatusDescription", message.getStatusDescription());
            	writeElement("DateOfControl", message.getDateOfControl());
                writeElement("ControlDescription", message.getControlDescription());  
             	writeElement("ControlAnnotation", message.getControlAnnotation());
             	writeElement("DateOfInterdiction", message.getDateOfInterdiction());
                writeElement("DateOfFinal", message.getDateOfFinal());  
            	writeElement("DateOfExitAllowance", message.getDateOfExitAllowance());
            	writeElement("ContainerNumber", message.getContainerNumber());
               
                 if (message.getStatusAnnotationList() != null) {
	                for (StatusAnnotation meldung : message.getStatusAnnotationList()) {
	                   writeMeldung(meldung);
	                }
                 }
                    	
            closeElement();
            closeElement();
            closeElement();
            
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }       
	}
    
    
    private void writeMeldung(StatusAnnotation meldung) throws XMLStreamException {
    	if (meldung == null) {
    	    return;
    	}
    	
	    openElement("StatusAnnotation");
	    	writeElement("AnnotationKey", meldung.getAnnotationKey());
	    	if (meldung.getTextList() != null) {
	    		for (String text : meldung.getTextList()) {	    	  
	    			writeElement("Text", text);  
	    		}
	    	}
	    	
	    closeElement();
    }
    
}

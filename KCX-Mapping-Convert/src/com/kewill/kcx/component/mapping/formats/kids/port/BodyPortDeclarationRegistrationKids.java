package com.kewill.kcx.component.mapping.formats.kids.port;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.Import.msg.common.GoodsItemDeclarationDecision;
import com.kewill.kcx.component.mapping.countries.de.Port.msg.MsgPortDeclarationRegistration;
import com.kewill.kcx.component.mapping.countries.de.Port.msg.MsgPortDeclarationStatus;
import com.kewill.kcx.component.mapping.countries.de.Port.msg.common.StatusAnnotation;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;

/**
 * Module	   : Port.<br>
 * Created	   : 27.04.2012<br>
 * Description : BodyImportDeclarationDecisionKids
 * 
 * @author iwaniuk
 * @version 1.0.00
 *
 */
public class BodyPortDeclarationRegistrationKids extends KidsMessage {

	private MsgPortDeclarationRegistration message;	

    public BodyPortDeclarationRegistrationKids(XMLStreamWriter writer) {
        this.writer = writer;
    }
   
	public MsgPortDeclarationRegistration getMessage() {
		return message;
	}
	
	public void setMessage(MsgPortDeclarationRegistration argument) {
		this.message = argument;
	}
		
    public void writeBody() {
        try {
            openElement("soap:Body");
            openElement("PortDeclarationRegistration");
            openElement("GoodsDeclaration"); 
          
            	writeElement("ReferenceNumber", message.getReferenceNumber());  
            	writeElement("PortSystem", message.getPortSystem());
                writeElement("SendingDateTime", message.getSendingDateTime());                                          	
            	writeElement("PortRegistrationNumber", message.getPortRegistrationNumber());
                writeElement("PortRegistrationMode", message.getPortRegistrationMode());  
             	writeElement("PortContactName", message.getPortContactName());
             	writeElement("PortContactPhone", message.getPortContactPhone());
                writeElement("PortContactEmail", message.getPortContactEmail());  
            		
            closeElement();
            closeElement();
            closeElement();
            
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }       
	}
    
}

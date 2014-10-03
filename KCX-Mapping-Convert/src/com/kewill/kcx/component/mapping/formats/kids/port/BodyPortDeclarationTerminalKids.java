package com.kewill.kcx.component.mapping.formats.kids.port;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.Port.msg.MsgPortDeclarationRegistration;
import com.kewill.kcx.component.mapping.countries.de.Port.msg.MsgPortDeclarationTerminal;
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
public class BodyPortDeclarationTerminalKids extends KidsMessage {

	private MsgPortDeclarationTerminal message;	

    public BodyPortDeclarationTerminalKids(XMLStreamWriter writer) {
        this.writer = writer;
    }
   
	public MsgPortDeclarationTerminal getMessage() {
		return message;
	}
	
	public void setMessage(MsgPortDeclarationTerminal argument) {
		this.message = argument;
	}
		
    public void writeBody() {
        try {
            openElement("soap:Body");
            openElement("PortDeclarationTerminal");
            openElement("GoodsDeclaration"); 
          
            	writeElement("ReferenceNumber", message.getReferenceNumber());  
            	writeElement("PortSystem", message.getPortSystem());
                writeElement("SendingDateTime", message.getSendingDateTime());                                          	
            	writeElement("PortRegistrationNumber", message.getPortRegistrationNumber());
                writeElement("Terminal", message.getTerminal());  
             		
            closeElement();
            closeElement();
            closeElement();
            
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }       
	}
    
}

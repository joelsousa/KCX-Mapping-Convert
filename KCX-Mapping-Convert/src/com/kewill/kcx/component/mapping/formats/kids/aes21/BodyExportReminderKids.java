package com.kewill.kcx.component.mapping.formats.kids.aes21;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.aes21.msg.MsgExpUrg;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;

/**
 * Module		: Export/aes<br>
 * Created		: 24.07.2012<br>
 * Description	: BodyExportInvestigationKids. 
* 				: V21: new Tags  
 * 
 * @author iwaniuk
 * @version 1.0.00
 */

public class BodyExportReminderKids extends KidsMessage {
	
	private MsgExpUrg message;
	
    public BodyExportReminderKids(XMLStreamWriter writer) {
        this.writer = writer;
    }
    
    public void writeBody() {
         try {
            openElement("soap:Body");
            openElement("ExportReminder");
            openElement("GoodsDeclaration");
            writeElement("UCRNumber", message.getUCRNumber()); 
            writeElement("ReferenceNumber", message.getReferenceNumber()); 
            writeElement("CustomsOfficeForCompletion", message.getCustomsOfficeForCompletion());
            	String date;
            	date = message.getDateOfReminder();                                //EI20120730 TODO-V21
                if (date.length() > 8) {                         
                    writeDateToString("DateOfReminder", message.getDateOfReminder());                        
                } else {                    	
                    writeElement("DateOfReminder", message.getDateOfReminder());                        
                }                 
                date = message.getDateOfLatestPossibleReply();
                if (date.length() > 8) {   
                	writeDateToString("DateOfLatestPossibleReply", message.getDateOfLatestPossibleReply()); 
                } else {
                    writeElement("DateOfLatestPossibleReply", message.getDateOfLatestPossibleReply()); 
                }      
               
                writeParty("Consignor", message.getConsignor());     					
                writeParty("Declarant", message.getDeclarant());     					
                writeParty("Agent", message.getAgent());     							
                writeParty("Subcontractor", message.getSubcontractor());     			
                
            closeElement(); 
            closeElement(); 
            closeElement(); 
        } catch (XMLStreamException e) {
            
            e.printStackTrace();
        }
    }
    
	public MsgExpUrg getMessage() {
		return message;
	}

	public void setMessage(MsgExpUrg message) {
		this.message = message;
	}
    
}

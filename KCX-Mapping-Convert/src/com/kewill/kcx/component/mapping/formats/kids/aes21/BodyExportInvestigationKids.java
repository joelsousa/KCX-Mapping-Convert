package com.kewill.kcx.component.mapping.formats.kids.aes21;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.aes21.msg.MsgExpFup;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;

/**
 * Module		: Export/aes<br>
 * Created		: 24.07.2012<br>
 * Description	: BodyExportInvestigationKids. 
* 				: V21: new Tags  
 * 
 * @author iwaniuk
 * @version 1.0.00
 * 
 *  Changes: AES22 new Tags
 */

public class BodyExportInvestigationKids extends KidsMessage {
	
	private MsgExpFup message;
	
    public BodyExportInvestigationKids(XMLStreamWriter writer) {
        this.writer = writer;
    }
    
    public void writeBody() {
         try {
            openElement("soap:Body");
            openElement("Investigation");
            openElement("GoodsDeclaration");
            	String date;
                date = message.getDateOfLatestPossibleReply();
                if (date.length() > 8) {   
                	writeDateToString("DateOfLatestPossibleReply", message.getDateOfLatestPossibleReply()); 
                } else {
                    writeElement("DateOfLatestPossibleReply", message.getDateOfLatestPossibleReply()); 
                }      
                date = message.getDateOfInquiry();
                if (date.length() > 8) {                         
                    writeDateToString("DateOfInquiry", message.getDateOfInquiry());                        
                } else {                    	
                    writeElement("DateOfInquiry", message.getDateOfInquiry());                        
                } 
                
                writeElement("UCRNumber", message.getUCRNumber());                      //new for V21
                writeElement("ReferenceNumber", message.getReferenceNumber());                 
                //??? writeElement("OrderNumber", message.getOrderNumber()); 
               
                writeElement("RequestCode", message.getRequestCode()); 		//EI20130808 AES22
                date = message.getDateOfLatestResponseOfRequest();          //EI20130808 AES22
                if (date.length() > 8) {   
                	writeDateToString("DateOfLatestResponseOfRequest", message.getDateOfLatestResponseOfRequest()); 
                } else {
                    //writeElement("DateOfLatestPossibleReplyOfRequest", message.getDateOfLatestResponseOfRequest()); 
                	writeElement("DateOfLatestResponseOfRequest", message.getDateOfLatestResponseOfRequest()); 
                }  
                writeElement("CustomsOfficeExport", message.getCustomsOfficeExport());  //new for V21
                writeParty("Consignor", message.getConsignor());     					//new for V21
                writeParty("Declarant", message.getDeclarant());     					//new for V21
                writeParty("Agent", message.getAgent());     							//new for V21
                writeParty("Subcontractor", message.getSubcontractor());     			//new for V21
                
            closeElement(); 
            closeElement(); 
            closeElement(); 
        } catch (XMLStreamException e) {
            
            e.printStackTrace();
        }
    }
    
	public MsgExpFup getMessage() {
		return message;
	}

	public void setMessage(MsgExpFup message) {
		this.message = message;
	}
    
}

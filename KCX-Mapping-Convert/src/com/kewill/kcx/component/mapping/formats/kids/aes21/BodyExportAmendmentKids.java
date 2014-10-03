package com.kewill.kcx.component.mapping.formats.kids.aes21;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.MsgExpAmd;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.MsgExpAmdPos;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessageV21;

/**
 * Module		: Export/aes<br>
 * Created		: 23.07.2012<br>
 * Description	: Message Amendment KIDS. 
 * 				: V21: new Tags  
 * 
 * @author iwaniuk
 * @version 2.1.00
 */
 
public class BodyExportAmendmentKids extends KidsMessageV21 {
	
	private MsgExpAmd message	= null;	
   
    public BodyExportAmendmentKids(XMLStreamWriter writer) {
        this.writer = writer;
    }
    
	public MsgExpAmd getMessage() {
		return message;
	}
	public void setMessage(MsgExpAmd argument) {
		this.message = argument;
	}
	
    public void writeBody() {
        try {
            openElement("soap:Body");
            openElement("Amendment");
            openElement("GoodsDeclaration");
            	writeAmendmentInfo(message.getAmendmentInfo());						//new for V21
                writeElement("GrossMass", message.getGrossMass());
                writeElement("ReferenceNumber", message.getReferenceNumber());
                writeParty("Declarant", message.getDeclarant());                    //new for V21
                writeParty("Agent", message.getAgent());                            //new for V21
                    	                  
                if (message.getGoodsItemList() != null) {
	                for (MsgExpAmdPos item : message.getGoodsItemList()) {	                      		
	                    if (item != null) {
	                     openElement("GoodsItem");   //EI20120920 es hat immer gefehlt!!!
	                        writeElement("ItemNumber", item.getItemNumber());
	                        writeElement("NetMass", item.getNetMass());
	                        writeElement("GrossMass", item.getGrossMass());	                       
	                      	if (item.getExportRefundItem() != null)  {
	                      		openElement("ExportRefundItem");
	                      			writeElement("Amount", item.getExportRefundItem().getAmount());
	                      		closeElement(); 
	                      	}
	                      closeElement();
	                    }  
	                }
                }
                
            closeElement(); 
            closeElement(); 
            closeElement(); 
        } catch (XMLStreamException e) {
            
            e.printStackTrace();
        }
    }
    
	public MsgExpAmd getmessage() {
		return message;
	}

	public void setMsgExpCan(MsgExpAmd message) {
		this.message = message;
	}
}

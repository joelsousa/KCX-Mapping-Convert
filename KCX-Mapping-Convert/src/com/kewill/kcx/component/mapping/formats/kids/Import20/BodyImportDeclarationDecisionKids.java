package com.kewill.kcx.component.mapping.formats.kids.Import20;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.Import.msg.MsgImportDeclarationDecision;
import com.kewill.kcx.component.mapping.countries.de.Import.msg.common.GoodsItemDeclarationDecision;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;

/**
 * Module	   : Import 20<br>
 * Created	   : 12.09.2011<br>
 * Description : BodyImportDeclarationDecisionKids.
 * 
 *               TODO KIDS not defined
 * 
 * @author iwaniuk
 * @version 2.0.00
 *
 */
public class BodyImportDeclarationDecisionKids extends KidsMessage {

	private MsgImportDeclarationDecision message;	

    public BodyImportDeclarationDecisionKids(XMLStreamWriter writer) {
        this.writer = writer;
    }
   
	public MsgImportDeclarationDecision getMessage() {
		return message;
	}
	
	public void setMessage(MsgImportDeclarationDecision argument) {
		this.message = argument;
	}
		
    public void writeBody() {
        try {
            openElement("soap:Body");
            openElement("ImportDeclarationDecision");
            openElement("GoodsDeclaration"); 
          
            	writeElement("ReferenceNumber", message.getReferenceNumber());  
            	writeElement("TemporaryMRN", message.getTemporaryMRN());
                writeElement("RegistrationNumber", message.getRegistrationNumber());                
                writeElement("CustomsClerkName", message.getCustomsClerkName());    
                writeElement("OrderNumber", message.getOrderNumber());    
                 if (message.getGoodsItemList() != null) {
	                for (GoodsItemDeclarationDecision goodsItem : message.getGoodsItemList()) {
	                   writeGoodsItem(goodsItem);
	                }
                 }
                    	
            closeElement();
            closeElement();
            closeElement();
            
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }       
	}
    
    
    private void writeGoodsItem(GoodsItemDeclarationDecision item) throws XMLStreamException {
    	if (item == null) {
    	    return;
    	}
    	
	    openElement("GoodsItem");
	    	writeElement("ItemNumber", item.getItemNumber());
	    	//writeElement("", item);  
	    	writeElement("AcceptanceDate", item.getAcceptanceDate());  
	    	writeElement("ReleaseDate", item.getReleaseDate());  
	    	writeElement("ReleaseTime", item.getReleaseTime());  
	    	writeElement("AcceptanceCode", item.getAcceptanceCode());  
	    	writeElement("ReleaseCode", item.getReleaseCode());  
	    	writeElement("ReturnCode", item.getReturnCode());  
	    	writeElement("RulingsCode", item.getRulingsCode());  
	    	writeElement("Text", item.getText());  
	    	
	    	
	    closeElement();
    }
    
}

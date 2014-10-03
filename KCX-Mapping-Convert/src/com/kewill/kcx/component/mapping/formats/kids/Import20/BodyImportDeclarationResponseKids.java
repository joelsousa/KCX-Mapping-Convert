package com.kewill.kcx.component.mapping.formats.kids.Import20;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.Import.msg.MsgImportDeclarationResponse;
import com.kewill.kcx.component.mapping.countries.de.Import.msg.common.GoodsItemDeclarationResponse;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;

/**
 * Module	   : Import 20<br>
 * Created	   : 22.09.2011<br>
 * Description : BodyImportDeclarationResponseKids.
 * 
 *               TODO KIDS not defined
 * 
 * @author iwaniuk
 * @version 2.0.00
 *
 */
public class BodyImportDeclarationResponseKids extends KidsMessage {

	private MsgImportDeclarationResponse message;	

    public BodyImportDeclarationResponseKids(XMLStreamWriter writer) {
        this.writer = writer;
    }
   
	public MsgImportDeclarationResponse getMessage() {
		return message;
	}
	
	public void setMessage(MsgImportDeclarationResponse argument) {
		this.message = argument;
	}
		
    public void writeBody() {
        try {
            openElement("soap:Body");
            openElement("ImportDeclarationResponse");
            openElement("GoodsDeclaration"); 
          
            	writeElement("ReferenceNumber", message.getReferenceNumber());  
            	writeElement("TemporaryMRN", message.getTemporaryMRN());
                writeElement("RegistrationNumber", message.getRegistrationNumber());                
                writeElement("RegistrationDate", message.getRegistrationDate());    
                 
                 if (message.getGoodsItemList() != null) {
	                for (GoodsItemDeclarationResponse goodsItem : message.getGoodsItemList()) {
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
    
    
    private void writeGoodsItem(GoodsItemDeclarationResponse item) throws XMLStreamException {
    	if (item == null) {
    	    return;
    	}
    	
	    openElement("GoodsItem");
	    	writeElement("ItemNumber", item.getItemNumber());	   
	    	writeElement("ErrorCode", item.getErrorCode());  
	    	writeElement("KindOfError", item.getKindOfError());  
	    	writeElement("ReportingPrefix", item.getReportingPrefix());  
	    	writeElement("ErrorText", item.getErrorText());  
	    	writeElement("RegistrationNumberWriteOffAmounts", item.getRegistrationNumberWriteOffAmounts());
	    closeElement();
    }
    
}

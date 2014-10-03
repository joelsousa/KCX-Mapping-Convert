package com.kewill.kcx.component.mapping.formats.kids.base;

import java.util.List;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.common.MsgError;
import com.kewill.kcx.component.mapping.countries.common.MsgErrorPos;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.ErrorType;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.PositionError;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;

/**
 * Module		: KCX<br>
 * Created		: 16.12.2011<br>
 * Description	: KidsBody for MsgError.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class BodyErrorMessageKids extends KidsMessage {

	private MsgError msgError;
	
    public BodyErrorMessageKids(XMLStreamWriter writer) {
        this.writer = writer;
    }
   
    public void writeBody() {    	
        try {
            openElement("soap:Body");                            	
            openElement("ErrorMessage");
            //setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
           //setAttribute("xsi", "noNamespaceSchemaLocation");
            openElement("GoodsDeclaration");
            	writeElement("UCRNumber", msgError.getUCRNumber());
                writeElement("UCROtherSystem", msgError.getUCROtherSystem());
                writeElement("ReferenceNumber", msgError.getReferenceNumber());
                writeElement("ProcedureType", msgError.getProcedureType());
                        
                List<ErrorType> errorList = msgError.getErrorList();  
                if (errorList != null) {
                	for (ErrorType tmpError : errorList) {                        	
                		if (tmpError != null) {
                			openElement("Error");   
                				writeElement("UniqueNumber", tmpError.getUniqueNumber());
                        		writeElement("Code", tmpError.getCode());
                        		writeElement("Text", tmpError.getText());
                        		writeElement("Pointer", tmpError.getPointer());  
                        		writeElement("Number", tmpError.getNumber());
                        	closeElement(); 
                		}
                	} 
                }
                List<MsgErrorPos> goodsItemList = msgError.getGoodsItemList();
                if (goodsItemList != null) {
                	for (MsgErrorPos item : goodsItemList) {
                		if (item != null) {
                			openElement("GoodsItem");                        			
                				writeElement("ItemNumber", item.getItemNumber());  
                				if (item.getPositionErrorList() != null) {
                					for (PositionError error : item.getPositionErrorList()) {
                						openElement("PositionError");   
                						writeElement("KindOfError", error.getKindOfError()); 
                						writeElement("ErrorCode", error.getErrorCode()); 
                						writeElement("ErrorText", error.getErrorText());                 						
                						closeElement(); 
                					}
                				}
                			closeElement(); 
                		}
                	}   	
                }
                        
            closeElement(); // GoodsDeclaration
            closeElement(); // ErrorMessage
            closeElement(); // soap:Body
        } catch (XMLStreamException e) {
            
            e.printStackTrace();
        }
    }

	public MsgError getMsgError() {
		return msgError;
	}

	public void setMsgError(MsgError argument) {
		this.msgError = argument;
	}
}

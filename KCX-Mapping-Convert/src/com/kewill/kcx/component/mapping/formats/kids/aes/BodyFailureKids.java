package com.kewill.kcx.component.mapping.formats.kids.aes;

/*
 * Function    : BodyFailureKids.java
 * Titel       :
 * Date        : 25.09.2008
 * Author      : Kewill CSF Christine Kron
 * Description : valid Names of KIDS-Messagenames in Export
 * 			   : relates to kiff_export.xls and kiff-export-reply.xls
 * Parameters  : 

 * Changes 
 * -----------
 * Author      :
 * Date        :
 * Label       :
 * Description:
 *
 *
 */
 

import java.util.List;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.MsgFailure;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.PositionError;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;

public class BodyFailureKids extends KidsMessage {
	
	private MsgFailure 			msgFailure;
	private int 				len;
	private List 				errorList;
	private PositionError 		tmpError;
	
    public BodyFailureKids(XMLStreamWriter writer) {
        this.writer = writer;
    }
    
    public void writeBody() {
        try {
            openElement("soap:Body");
                openElement("FailureOrConfirm");
                    openElement("GoodsDeclaration");
                        writeElement("UCRNumber", msgFailure.getUCRNumber());
                        writeElement("ReferenceNumber", msgFailure.getReferenceNumber());
                        writeElement("OrderNumber", msgFailure.getOrderNumber());
                        writeElement("CorrectionNumber", msgFailure.getCorrectionNumber());
                        writeElement("TemporaryUCR", msgFailure.getTemporaryUCR());
                        errorList = msgFailure.getErrorList();
            			len = errorList.size();
                        for(int i=0; i < len; i++) {
            				tmpError = (PositionError)errorList.get(i);
                        	if(tmpError != null) {
                            	openElement("GoodsItem");
                            		writeElement("ItemNumber", (i+1)+"");
                            		openElement("PositionError");
                        				writeElement("KindOfError", tmpError.getKindOfError());
                        				writeElement("OriginOfError", tmpError.getOriginOfError());
                        				writeElement("Modul", tmpError.getModul());
                        				writeElement("ErrorCode", tmpError.getErrorCode());
                        				writeElement("ErrorText", tmpError.getErrorText());
                        				writeDateToString("DateOfErrorMessage", tmpError.getDateOfErrorMessage());
                        				writeTimeToString("TimeOfErrorMessage", tmpError.getTimeOfErrorMessage());
                        			closeElement(); // PositionError
                        		closeElement(); // GoodsItem
                        	}
                        }
                    closeElement(); // GoodsDeclaration
                closeElement(); // ExportDeclaration
            closeElement(); // soap:Body
        } catch (XMLStreamException e) {
            
            e.printStackTrace();
        }
    }
    
	public MsgFailure getMsgConfirmFailure() {
		return msgFailure;
	}

	public void setMsgConfirmFailure(MsgFailure msgFailure) {
		this.msgFailure = msgFailure;
	}
    
}

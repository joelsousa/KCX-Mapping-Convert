package com.kewill.kcx.component.mapping.formats.kids.aes;


/*
 * Function    : BodyDeclarationResponseKids.java
 * Titel       :
 * Date        : 24.09.2008
 * Author      : Kewill CSF Christine Kron
 * Description : valid Names of KIDS-Messagenames in Export
 * 			   : relates to kiff_export.xls and kiff-export-reply.xls
 * Parameters  : 

 * Changes 
 * -----------
 * Author      : EI
 * Date        : 24.04.2009
 * Label       :
 * Description : KCX-Code checked
 *
 *
 */
 

import java.util.List;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.MsgExpSta;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.ErrorType;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;

public class BodyDeclarationResponseKids extends KidsMessage {
	
	private MsgExpSta 	msgExpSta;
	private int 		len;
	private List 		errorList;
	private ErrorType 	tmpError;
	
    public BodyDeclarationResponseKids(XMLStreamWriter writer) {
        this.writer = writer;
    }
    
    public void writeBody() {
        try {
            openElement("soap:Body");
                openElement("DeclarationResponse");
                    openElement("GoodsDeclaration");
                    
                     
                        writeDateTimeToString("CancellationTime", msgExpSta.getCancellationTime());
                        writeDateTimeToString("ExitTime", msgExpSta.getExitTime());
                        writeDateTimeToString("ReleaseTime", msgExpSta.getReleaseTime());
                        writeDateTimeToString("AcceptanceTime", msgExpSta.getAcceptanceTime());
                        writeDateTimeToString("ReceiveTime", msgExpSta.getReceiveTime());
                        writeDateTimeToString("TimeOfRejection", msgExpSta.getTimeOfRejection());
                        writeDateTimeToString("BeginTimeOfProcessing", msgExpSta.getBeginTimeOfProcessing());
                        writeDateTimeToString("TimeOfRejectionOfPreannouncement", msgExpSta.getTimeOfRejectionOfPreannouncement());    //EI20120209 war Tippfehler
                        writeDateTimeToString("TimeOfCompletion", msgExpSta.getTimeOfCompletion());
                        writeElement("Reason", msgExpSta.getReason());
                        writeCodeElement("StatusOfCompletion", msgExpSta.getStatusOfCompletion(), "KCX0008");
                        writeCodeElement("StatusOfCompletion2", msgExpSta.getStatusOfCompletion2(), "KCX0008");
                        writeCodeElement("StatusOfCompletion3", msgExpSta.getStatusOfCompletion3(), "KCX0008");
                        writeElement("ReferenceNumber", msgExpSta.getReferenceNumber());
                        // CK 01.02.2012
                        writeElement("EPUNumber", msgExpSta.getEpuNumber());
                        writeElement("EntryNumber", msgExpSta.getEntryNumber());
                        
                        writeLoadingTime(msgExpSta.getLoadingTime());    // AK20081119
                        errorList = msgExpSta.getErrorList();
            			len = errorList.size();
                        for(int i=0; i < len; i++) {
            				tmpError = (ErrorType)errorList.get(i);
                        	if(tmpError != null) {
                            	openElement("Error");                        			
                        			writeElement("Code", tmpError.getCode());
                        			writeElement("Text", tmpError.getText());
                        			writeElement("Pointer", tmpError.getPointer());                        			
                        		closeElement(); // Error
                        	}
                        }
                    closeElement(); // GoodsDeclaration
                closeElement(); // DeclarationResponse
            closeElement(); // soap:Body
        } catch (XMLStreamException e) {
            
            e.printStackTrace();
        }
    }
    
	public MsgExpSta getMsgExpSta() {
		return msgExpSta;
	}

	public void setMsgExpSta(MsgExpSta msgExpSta) {
		this.msgExpSta = msgExpSta;
	}
    
}

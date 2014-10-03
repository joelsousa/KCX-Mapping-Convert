package com.kewill.kcx.component.mapping.formats.kids.aes21;

import java.util.List;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.common.Party;
import com.kewill.kcx.component.mapping.countries.common.TIN;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.MsgExpSta;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.ErrorType;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessageV21;

/**
 * Module      : Export/aes<br>
 * Created     : 24.07.2012<br>
 * Description : KIDS-Body of DeclarationResponse.
 * 			   : V21: new Tags
 * 
 * @author iwaniuk
 * @version 2.1.00
 */

public class BodyDeclarationResponseKids extends KidsMessageV21 {
	
	private MsgExpSta message;
	
    public BodyDeclarationResponseKids(XMLStreamWriter writer) {
        this.writer = writer;
    }
	
    public void writeBody() {
        try {
            openElement("soap:Body");
            openElement("DeclarationResponse");
            openElement("GoodsDeclaration");                                        
            	writeDateTimeToString("CancellationTime", message.getCancellationTime());
            	writeDateTimeToString("DeclarationTime", message.getDeclarationTime());     //new for V21
                writeDateTimeToString("ExitTime", message.getExitTime());
                writeDateTimeToString("ReleaseTime", message.getReleaseTime());
                writeDateTimeToString("AcceptanceTime", message.getAcceptanceTime());
                writeDateTimeToString("ReceiveTime", message.getReceiveTime());
                writeDateTimeToString("TimeOfRejection", message.getTimeOfRejection());
                writeDateTimeToString("BeginTimeOfProcessing", message.getBeginTimeOfProcessing());
                writeDateTimeToString("TimeOfRejectionOfPreannouncement", message.getTimeOfRejectionOfPreannouncement());
                writeDateTimeToString("TimeOfCompletion", message.getTimeOfCompletion());
                writeElement("Reason", message.getReason());
                writeCodeElement("StatusOfCompletion", message.getStatusOfCompletion(), "KCX0008");
                writeCodeElement("StatusOfCompletion2", message.getStatusOfCompletion2(), "KCX0008");
                writeCodeElement("StatusOfCompletion3", message.getStatusOfCompletion3(), "KCX0008");
                writeElement("ReferenceNumber", message.getReferenceNumber());
                writeElement("OrderNumber", message.getOrderNumber()); 						//new for V21
                writeElement("EPUNumber", message.getEpuNumber());
                writeElement("EntryNumber", message.getEntryNumber());
                //writeParty("CustomsOffice", message.getCustomsOffice());					//new for V21                        
                writeLoadingTime(message.getLoadingTime());    
                List<ErrorType> errorList = message.getErrorList();   
                if (errorList != null) {
                	for (ErrorType tmpError : errorList) {            				
                	if (tmpError != null) {
                		openElement("Error");                        			
                        	writeElement("Code", tmpError.getCode());
                        	writeElement("Text", tmpError.getText());
                        	writeElement("Pointer", tmpError.getPointer());                        			
                        closeElement(); 
                    }
                	}
                }
                writeElement("CustomsOfficeExport", message.getCustomsOfficeExport()); 		//new for V21
                writeElement("CustomsOfficeForCompletion", message.getCustomsOfficeForCompletion()); //new for V21						
                writeParty("Declarant", message.getDeclarant());							//new for V21 
                writeParty("Agent", message.getAgent());									//new for V21 
            closeElement(); 
            closeElement(); 
            closeElement(); 
        } catch (XMLStreamException e) {            
            e.printStackTrace();
        }
    }
    
	public MsgExpSta getMessage() {
		return message;
	}

	public void setMessage(MsgExpSta message) {
		this.message = message;
	}	
}

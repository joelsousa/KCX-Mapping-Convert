package com.kewill.kcx.component.mapping.formats.uids.aes21;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.util.List;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.MsgErrInf;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.ErrorType;

/**
 * Module        : Export/aes<br>
 * Created       : 17.09.2012<br>
 * Description   : Uids Body of ErrorInformation.
 * 				 : V21: only NameSpase is changed: written from CommonFieldsDTO

 * @author iwaniuk
 * @version 2.1.00
 */

public class BodyErrorInformationUIDS extends UidsMessage {

	private MsgErrInf message;
	private int listLength;
	private List errorList;
	
    public BodyErrorInformationUIDS(XMLStreamWriter writer) {
        this.writer = writer;
    }
    
	public void writeBody() {		
        try {
            openElement("soap:Body");
            openElement("Submit");
                //EI20120917:
            	if (this.getCommonFieldsDTO() != null && 
                		!Utils.isStringEmpty(this.getCommonFieldsDTO().getNameSpaceText())) {            	             
                	setAttribute("xmlns", this.getCommonFieldsDTO().getNameSpaceText());
                } else {
                	setAttribute("xmlns", "http://www.eurtradenet.com/schemas/uids/export/body/200809");
                }
                
                openElement("Export");
                openElement("ErrorInformation");                    	
                	writeElement("DocumentReferenceNumber", message.getUCRNumber());                        
                    writeElement("ReferenceNumber", message.getReferenceNumber());
                    writeElement("ExternalRegistrationNumber", message.getUCROtherSystem());
                    		                    		                                   
                    if (message.getErrorList() != null) {
                    	for (ErrorType tmpError : message.getErrorList()) {  
                    		if (tmpError != null) {
                            	openElement("Error");
                            		writeElement("Type", tmpError.getCode());
                            		writeElement("Reason", tmpError.getText());
                            		writeElement("Pointer", tmpError.getPointer());
                            	closeElement(); 
                    		}     
                    	}
                    }                    	
                closeElement();
                closeElement(); 
            closeElement(); 
            closeElement(); 
        } catch (XMLStreamException e) {
            
            e.printStackTrace();
        }
    }

	public MsgErrInf getMsgErrInf() {
		return message;
	}

	public void setMsgErrInf(MsgErrInf argument) {
		this.message = argument;
	}
}

/**
 * Function    : BodyErrorInformationUIDS.java
 * Titel       :
 * Date        : 17.09.2008
 * Author      : Kewill CSF / iwaniuk
 * Description : valid Names of UIDS-Messagenames to KIDS-Messagenames in Export
 * 			   : relates to  (Error message) kiff-export-reply.xls 
 * Parameters  : 
 * Changes 
 * -----------
 * Author      :
 * Date        :
 * Label       :
 * Description :
 *
 */

package com.kewill.kcx.component.mapping.formats.uids.aes;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.util.List;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.MsgErrInf;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsMessage;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.ErrorType;

public class BodyErrorInformationUIDS extends UidsMessage {

	private MsgErrInf msgErrInf;
	private int listLength;
	private List errorList;
	
    public BodyErrorInformationUIDS(XMLStreamWriter writer) {
        this.writer = writer;
    }
    
	public void writeBody() {		
        try {
            openElement("soap:Body");
                openElement("Submit");
                // C.K. Namespace Versionsabhängig setzen
                if (getUidsHeader().getUidsNamespaceVersion().trim().equals("200809")) {
                    setAttribute("xmlns", "http://www.eurtradenet.com/schemas/uids/export/body/200809");
                } else {
                    setAttribute("xmlns", "http://www.eurtradenet.com/schemas/uids/export/body/200601");
                }
                // setAttribute("xmlns", "http://www.eurtradenet.com/schemas/uids/export/body/200601");
                    openElement("Export");
                        openElement("ErrorInformation");
                    	//openElement(msgErrInf.msgName);
                        //AK20090129 changed to sequence DocumentReferenceNumber, ReferenceNumber
                        	writeElement("DocumentReferenceNumber", msgErrInf.getUCRNumber());                        
                    		writeElement("ReferenceNumber", msgErrInf.getReferenceNumber());

                    		writeElement("ExternalRegistrationNumber", msgErrInf.getUCROtherSystem());
                    		                    		
                    		errorList = msgErrInf.getErrorList();
                    		if (errorList != null) {	   //EI20130214
                    			listLength = errorList.size();
                    			for (int i = 0; i < listLength; i++) {
                    		  	
                    				ErrorType tmpError = new ErrorType();
                    				tmpError = (ErrorType) errorList.get(i);
                    				//tmpError = msgErrInf.getError();
                    				if (tmpError != null) {
                            			openElement("Error");
                            				writeElement("Type", tmpError.getCode());
                            				writeElement("Reason", tmpError.getText());
                            				writeElement("Pointer", tmpError.getPointer());
                            			closeElement(); 
                    				}                    				
                    			}
                    		}
                        closeElement(); // ErrorInformation
                    closeElement(); // Export
                closeElement(); // Submit
            closeElement(); // soap:Body
        } catch (XMLStreamException e) {
            
            e.printStackTrace();
        }
    }

	public MsgErrInf getMsgErrInf() {
		return msgErrInf;
	}

	public void setMsgErrInf(MsgErrInf argument) {
		this.msgErrInf = argument;
	}
}

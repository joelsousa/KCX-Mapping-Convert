package com.kewill.kcx.component.mapping.formats.uids.aes;


import java.util.List;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.MsgFailure;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.PositionError;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsMessage;

/*
 * Funktion    : BodyFailureUids.java
 * Titel       :
 * Erstellt    : 09.10.2008
 * Author      : Kewill CSF  / Christine Kron
 * Description : valid Names of UIDS-Messagenames to KIDS-Messagenames in Export
 * 			   : relates to (localAppFailure) kids-export-reply.xls 
 * Anmerkungen : 
 * Parameter   : 
 * Rückgabe    : keine
 * Aufruf      : 
 *
 * Änderungen:
 * -----------
 * Author      :
 * Datum       :
 * Kennzeichen :
 * Beschreibung:
 * Anmerkungen :
 * Parameter   :
 *
 */
public class BodyFailureUids extends UidsMessage {
	
    private MsgFailure     		msgFailure;
	private int 				len;
	private List 				errorList;
	private PositionError 		tmpError;

    
    public BodyFailureUids(XMLStreamWriter writer) {
        this.writer = writer;
    }
    
	public void writeBody() {
        try {
            openElement("soap:Body");
            	openElement("soap:Fault");
                	openElement("soap:Code");
               			writeElement("soap:Value", "soap:Server");
                	closeElement(); // soap:Code
                    errorList = msgFailure.getErrorList();
            		len = errorList.size();
            		if (len>0) {
            			openElement("soap:Reason");
            			for(int i=0; i < len; i++) {
                			tmpError = (PositionError)errorList.get(i);
                           	if(tmpError != null) {
                           		openElement("soap:Text");
                           			setAttribute("xml:lang", "de");
                           			writer.writeCharacters(tmpError.getErrorText());
                           		closeElement();
                            }
                        }
            			closeElement(); // soap:Reason
            		}
                closeElement(); // soap:Fault
            closeElement(); // Body
        } catch (XMLStreamException e) {
            
            e.printStackTrace();
        }
    }

	public MsgFailure getMsgFailure() {
		return msgFailure;
	}

	public void setMsgFailure(MsgFailure msgFailure) {
		this.msgFailure = msgFailure;
	}
	
}

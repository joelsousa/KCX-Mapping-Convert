package com.kewill.kcx.component.mapping.formats.uids.emcs;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.emcs.msg.MsgGenericRefusalMessage;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsMessageEMCS;
import com.kewill.kcx.component.mapping.util.EFormat;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: EMCS<br>
 * Created		: 12.05.2010<br>
 * Description  : Contains Message Structure with fields used in UIDS EMCS????Message.
 *                 
 * @author iwaniuk
 * @version 1.0.00
 */

public class BodyGenericRefusalMessageUids extends UidsMessageEMCS {
	
    private MsgGenericRefusalMessage  message = new MsgGenericRefusalMessage();
    private String version = "";         //EI20110701
 
    public BodyGenericRefusalMessageUids(XMLStreamWriter writer) {
        this.writer = writer;
    }

	public MsgGenericRefusalMessage getMessage() {
		return message;
	}

	public void setMessage(MsgGenericRefusalMessage message) {
		this.message = message;
	}
    
	public void writeBody() {		
		
        try { 
        	version = this.uidsHeader.getMessageVersion();
        	version = Utils.removeDots(version.substring(0, 3));
        	
        	openElement("soap:Body");
            openElement("Submit");
                setAttribute("xmlns", "http://www.eurtradenet.com/schemas/uids/emcs/body/200911");                
            openElement("EMCS");
            openElement("EMCSDeclarationRejected"); 
            
            if (version.equals("10")) {               //EI20111017
                writeElement("AadReferenceCode", message.getAadReferenceCode()); 
                writeElement("SequenceNumber", message.getSequenceNumber());
                writeElement("LocalReferenceNumber", message.getReferenceNumber());                   
                writeStringToDateTime("RejectionDateAndTime", message.getRejectionDateAndTime()); 
                writeElement("RejectionReason", message.getRejectionReason());                    
                writeErrorList(message.getFunctionalErrorList());  
            } else {
            	writeElement("AadReferenceCode", message.getAadReferenceCode()); 
                writeElement("SequenceNumber", message.getSequenceNumber());
                writeElement("LocalReferenceNumber", message.getReferenceNumber());  
                //V20 - DateTime format changed:
                writeFormattedDateTime("RejectionDateAndTime", message.getRejectionDateAndTime(),
                		EFormat.KIDS_DateTime, EFormat.ST_DateTimeTZ);	          //EI20111017
                writeElement("RejectionReason", message.getRejectionReason());                    
                writeErrorList(message.getFunctionalErrorList());  
            }
                    
            closeElement(); 
            closeElement();
            closeElement(); 
            closeElement(); 
        } catch (XMLStreamException e) {           
            e.printStackTrace();
        }
    }

}    	

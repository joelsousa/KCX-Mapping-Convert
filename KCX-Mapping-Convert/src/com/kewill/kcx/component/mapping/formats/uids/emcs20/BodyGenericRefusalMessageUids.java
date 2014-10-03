package com.kewill.kcx.component.mapping.formats.uids.emcs20;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.emcs.msg.MsgGenericRefusalMessage;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsMessageEMCS20;
import com.kewill.kcx.component.mapping.util.EFormat;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: EMCS<br>
 * Created		: 12.05.2010<br>
 * Description  : Contains Message Structure with fields used in UIDS EMCS????Message.
 *              : V20 - DateTime format was changed
 *                 
 * @author iwaniuk
 * @version 2.0.00
 */

public class BodyGenericRefusalMessageUids extends UidsMessageEMCS20 {
	
    private MsgGenericRefusalMessage  message = new MsgGenericRefusalMessage();
    
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
        	openElement("soap:Body");
            openElement("Submit");
                setAttribute("xmlns", "http://www.eurtradenet.com/schemas/uids/emcs/body/200911");                
            openElement("EMCS");
            openElement("EMCSDeclarationRejected");  
            
            	writeElement("AadReferenceCode", message.getAadReferenceCode()); 
                writeElement("SequenceNumber", message.getSequenceNumber());
                writeElement("LocalReferenceNumber", message.getReferenceNumber());  
                //V20 - DateTime format changed:
                writeFormattedDateTime("RejectionDateAndTime", message.getRejectionDateAndTime(),
                		EFormat.KIDS_DateTime, EFormat.ST_DateTimeTZ);	          
                writeElement("RejectionReason", message.getRejectionReason());                    
                writeErrorList(message.getFunctionalErrorList()); 
                
            closeElement(); 
            closeElement();
            closeElement(); 
            closeElement(); 
        } catch (XMLStreamException e) {           
            e.printStackTrace();
        }
    }

}    	

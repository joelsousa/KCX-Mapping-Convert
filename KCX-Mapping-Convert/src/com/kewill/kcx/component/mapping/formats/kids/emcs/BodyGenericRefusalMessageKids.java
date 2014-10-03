package com.kewill.kcx.component.mapping.formats.kids.emcs;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.emcs.msg.MsgGenericRefusalMessage;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessageEMCS;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: EMCS<br>
 * Created		: 18.05.2010<br>
 * Description : Contains Message Structure with fields used in Kids EMCSGenericRefusalMessage.
 *                 
 * @author iwaniuk
 * @version 1.0.00
 */

public class BodyGenericRefusalMessageKids extends KidsMessageEMCS {

	private MsgGenericRefusalMessage message;	
	private String version = "";         //EI20110701

    public BodyGenericRefusalMessageKids(XMLStreamWriter writer) {
        this.writer = writer;
    }
   
	public MsgGenericRefusalMessage getMessage() {
		return message;
	}
	public void setMessage(MsgGenericRefusalMessage argument) {
		this.message = argument;
	}
		
    public void writeBody() {
        try {
        	version = this.kidsHeader.getRelease();
        	version = Utils.removeDots(version.substring(0, 3));
        	
            openElement("soap:Body");
            openElement("EMCS");           
            openElement("EMCSGenericRefusalMessage");                      
            	writeElement("ReferenceNumber", message.getReferenceNumber());
                writeElement("CustomerOrderNumber", message.getCustomerOrderNumber());
                writeElement("Clerk", message.getClerk());
                writeElement("AadReferenceCode", message.getAadReferenceCode());  
                writeElement("SequenceNumber", message.getSequenceNumber());                
                writeDateTimeToString("RejectionDateAndTime", message.getRejectionDateAndTime()); //EI20100630
                writeElement("RejectionReason", message.getRejectionReason());            //EI20100716                
                writeFunctionalErrorList(message.getFunctionalErrorList(), version);      //EI20110927            
           closeElement();
           closeElement();	    
           closeElement();	           
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }       
	} 
 
}

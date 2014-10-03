package com.kewill.kcx.component.mapping.formats.kids.emcs;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.emcs.msg.MsgExplanationOnDelay;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessageEMCS;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: BodyExplDelayKids<br>
 * Created		: 11.05.2010<br>
 * Description : Contains Message Structure with fields used in EMCSExplanationOnDelay Kids message.
 *                 
 * @author iwaniuk
 * @version 1.0.00
 * 
 */

public class BodyExplanationOnDelayKids extends KidsMessageEMCS {

	private MsgExplanationOnDelay message;	
	private String version = "";         //EI20110701

    public BodyExplanationOnDelayKids(XMLStreamWriter writer) {
        this.writer = writer;
    }
   
	public MsgExplanationOnDelay getMessage() {
		return message;
	}
	public void setMessage(MsgExplanationOnDelay argument) {
		this.message = argument;
	}
		
    public void writeBody() {
        try {
        	version = this.kidsHeader.getRelease();
        	version = Utils.removeDots(version.substring(0, 3));
        	
            openElement("soap:Body");
            openElement("EMCS");                
            openElement("EMCSExplanationOnDelay");                      
            	writeElement("ReferenceNumber", message.getReferenceNumber());
                writeElement("CustomerOrderNumber", message.getCustomerOrderNumber());
                writeElement("Clerk", message.getClerk()); 
                writeElement("DestinationOffice", message.getDestinationOffice());
                writeElement("AadReferenceCode", message.getAadReferenceCode());
                writeElement("SequenceNumber", message.getSequenceNumber());                   
                if (version.equalsIgnoreCase("10")) {  
                	if (message.getComplementaryInformation() != null) {
                		writeElementWithAttribute("ComplementaryInformation",
                			message.getComplementaryInformation().getText(), 
                            "language", message.getComplementaryInformation().getLanguage());
                	}
                } else {
                	if (message.getComplementaryInformation() != null) {
                		//writeCodeElementWithAttribute("ComplementaryInformation", 
                    	//	message.getComplementaryInformation().getText(),
                    	//	"C0012", "language", message.getComplementaryInformation().getLanguage());
                		writeElementWithAttribute("ComplementaryInformation", 
                        		message.getComplementaryInformation().getText(),
                        		"language", message.getComplementaryInformation().getLanguage());
                	}
                	//writeCodeElement("MessageRole", message.getMessageRole(), "A0073");  
                	writeElement("MessageRole", message.getMessageRole());
                }
               writeElement("ExplanationCode", message.getExplanationCode());   
               writeElement("SubmitterType", message.getSubmitterType());          
               writeElement("ExciseTaxNumber", message.getExciseTaxNumber());                       
           closeElement();
           closeElement();	    
           closeElement();	           
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }       
	} 
 
}

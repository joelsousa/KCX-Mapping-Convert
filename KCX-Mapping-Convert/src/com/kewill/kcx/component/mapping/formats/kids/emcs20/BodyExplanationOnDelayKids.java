package com.kewill.kcx.component.mapping.formats.kids.emcs20;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.emcs.msg.MsgExplanationOnDelay;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessageEMCS;

/**
 * Module		: BodyExplDelayKids<br>
 * Created		: 11.05.2010<br>
 * Description  : Contains Message Structure with fields used in EMCSExplanationOnDelay Kids message.
 *              : V20 new Tag MessageRole, ComplementaryInformation with KCX-Code
 *                 
 * @author iwaniuk
 * @version 2.0.00
 * 
 */

public class BodyExplanationOnDelayKids extends KidsMessageEMCS {

	private MsgExplanationOnDelay message;	

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
            openElement("soap:Body");
            openElement("EMCS");                
            openElement("EMCSExplanationOnDelay");      
            
            	writeElement("ReferenceNumber", message.getReferenceNumber());
                writeElement("CustomerOrderNumber", message.getCustomerOrderNumber());
                writeElement("Clerk", message.getClerk()); 
                writeElement("DestinationOffice", message.getDestinationOffice());
                writeElement("AadReferenceCode", message.getAadReferenceCode());
                writeElement("SequenceNumber", message.getSequenceNumber());                   
                /*
                	if (message.getComplementaryInformation() != null) {
                		writeElementWithAttribute("ComplementaryInformation",
                			message.getComplementaryInformation().getText(), 
                            "language", message.getComplementaryInformation().getLanguage());
                */
                if (message.getComplementaryInformation() != null) {
                		writeElementWithAttribute("ComplementaryInformation",       //V20 new: KCX-Code
                    		message.getComplementaryInformation().getText(),
                    		"language", message.getComplementaryInformation().getLanguage());
                }
                writeElement("MessageRole", message.getMessageRole());      //V20 new             	
                
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

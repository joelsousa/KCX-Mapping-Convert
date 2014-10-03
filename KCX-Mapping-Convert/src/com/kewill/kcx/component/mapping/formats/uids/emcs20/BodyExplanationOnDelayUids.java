package com.kewill.kcx.component.mapping.formats.uids.emcs20;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.emcs.msg.MsgExplanationOnDelay;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsMessageEMCS20;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: EMCS<br>
 * Created		: 07.05.2010<br>
 * Description  : Contains Message Structure with fields used in EMCSExplanationOnDelayForDelivery Uids message.
 *              : V20 - DateTime format was changed, Trader.Addres.StreetAndNumber now in two Tags
 *              .       new Tag: MessageRole
 *                 
 * @author iwaniuk
 * @version 2.0.00 
 */

public class BodyExplanationOnDelayUids extends UidsMessageEMCS20 {
	
    private MsgExplanationOnDelay  message = new MsgExplanationOnDelay();
   
    public BodyExplanationOnDelayUids(XMLStreamWriter writer) {
        this.writer = writer;
    }

	public MsgExplanationOnDelay getMessage() {
		return message;
	}

	public void setMessage(MsgExplanationOnDelay message) {
		this.message = message;
	}
    
	public void writeBody() {		
		
        try {          	
        	openElement("soap:Body");
            openElement("Submit");
                setAttribute("xmlns", "http://www.eurtradenet.com/schemas/uids/emcs/body/200911");                
            openElement("EMCS");
            openElement("EMCSExplanationOnDelayForDelivery");      
            
            	writeElement("ExciseTaxNumber", message.getExciseTaxNumber());  
                writeElement("SubmitterType", message.getSubmitterType()); 
                writeElement("DestinationOffice", message.getDestinationOffice());
                writeElement("ExplanationCode", message.getExplanationCode()); 
                if (message.getComplementaryInformation() != null) {
                	writeElementWithAttribute("ComplementaryInformation", 
                							   message.getComplementaryInformation().getText(),
                             	              "language", message.getComplementaryInformation().getLanguage());
                }               
                writeElement("MessageRole", message.getMessageRole());        //V20 - new Tag MessageRole          
                writeExciseMovementEaad(message.getSequenceNumber(), message.getAadReferenceCode(), "");      
                
            closeElement(); 
            closeElement(); 
            closeElement(); 
            closeElement(); 
        } catch (XMLStreamException e) {           
            e.printStackTrace();
        }
    }

}    	

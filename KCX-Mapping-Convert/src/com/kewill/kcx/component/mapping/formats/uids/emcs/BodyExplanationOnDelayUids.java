package com.kewill.kcx.component.mapping.formats.uids.emcs;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.emcs.msg.MsgExplanationOnDelay;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsMessageEMCS;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: EMCS<br>
 * Created		: 07.05.2010<br>
 * Description  : Contains Message Structure with fields used in EMCSExplanationOnDelayForDelivery Uids message.
 *                 
 * @author iwaniuk
 * @version 1.0.00 
 */

public class BodyExplanationOnDelayUids extends UidsMessageEMCS {
	
    private MsgExplanationOnDelay  message = new MsgExplanationOnDelay();
    private String version = "";         //EI20110701
 
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
        	version = this.uidsHeader.getMessageVersion();
        	version = Utils.removeDots(version.substring(0, 3));
        	
        	openElement("soap:Body");
            openElement("Submit");
                setAttribute("xmlns", "http://www.eurtradenet.com/schemas/uids/emcs/body/200911");                
            openElement("EMCS");
            openElement("EMCSExplanationOnDelayForDelivery"); 
            
            	if (version.equals("10")) { 
                	 writeElement("SubmitterIdentification", message.getExciseTaxNumber());   
                	 writeElement("SubmitterType", message.getSubmitterType()); 
                     writeElement("DestinationOffice", message.getDestinationOffice());
                     writeElement("ExplanationCode", message.getExplanationCode()); 
                     if (message.getComplementaryInformation() != null) {
                         writeElementWithAttribute("ComplementaryInformation", 
                         		message.getComplementaryInformation().getText(), 
                             	"language", message.getComplementaryInformation().getLanguage());
                     }
                   //writeElement("DateAndTimeOfValidationOfExplanationOnDelay"); in exel-mapping not defined
                     writeExciseMovementEaad(message.getSequenceNumber(), message.getAadReferenceCode(), "", version);
                } else {
                	 writeElement("ExciseTaxNumber", message.getExciseTaxNumber());  //EI20110701
                	 writeElement("SubmitterType", message.getSubmitterType()); 
                     writeElement("DestinationOffice", message.getDestinationOffice());
                     writeElement("ExplanationCode", message.getExplanationCode()); 
                     if (message.getComplementaryInformation() != null) {
                         writeElementWithAttribute("ComplementaryInformation", 
                         		message.getComplementaryInformation().getText(), 
                             	"language", message.getComplementaryInformation().getLanguage());
                     }
                     //V20 - new Tag MessageRole:
                     writeElement("MessageRole", message.getMessageRole());        //EI20110701                
                     writeExciseMovementEaad(message.getSequenceNumber(), message.getAadReferenceCode(), "", version);
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

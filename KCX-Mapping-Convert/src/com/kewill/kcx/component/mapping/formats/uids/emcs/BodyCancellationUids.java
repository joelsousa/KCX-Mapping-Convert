package com.kewill.kcx.component.mapping.formats.uids.emcs;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.emcs.msg.MsgCancellation;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsMessageEMCS;
import com.kewill.kcx.component.mapping.util.EFormat;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: EMCS<br>
 * Created		: 07.05.2010<br>
 * Description  : Contains Message Structure with fields used in EMCSCancellation Uids message.
 *                 
 * @author iwaniuk
 * @version 1.0.00
 * 
 */

public class BodyCancellationUids extends UidsMessageEMCS {
	
    private MsgCancellation  message = new MsgCancellation();
    private String version = "";         //EI20110701
 
    public BodyCancellationUids(XMLStreamWriter writer) {
        this.writer = writer;
    }

	public MsgCancellation getMessage() {
		return message;
	}

	public void setMessage(MsgCancellation message) {
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
            openElement("EMCSCancellationEAAD");    
            
            	if (version.equals("10")) { 
                    writeElement("LocalReferenceNumber", message.getReferenceNumber());   
                    writeElement("AadReferenceCode", message.getAadReferenceCode());   
                    writeStringToDateTime("DateAndTimeOfValidationOfCancellation", 
                    					   message.getDateAndTimeOfValidationOfCancellation()); 
                    writeElement("CancellationReasonCode", message.getCancellationReasonCode());
                } else {
                    writeElement("LocalReferenceNumber", message.getReferenceNumber());   
                    writeElement("AadReferenceCode", message.getAadReferenceCode()); 
                  //V20 - DateTimeFormat changed
                  //writeStringToDateTime("DateAndTimeOfValidation", ....
                    writeFormattedDateTime("DateAndTimeOfValidation", 
                    						message.getDateAndTimeOfValidationOfCancellation(),
                    						EFormat.KIDS_DateTime, EFormat.ST_DateTimeTZ);	      //EI20111017
                    writeElement("CancellationReasonCode", message.getCancellationReasonCode());
                    //V20 - new Tag
                    if (message.getComplementaryInformation() != null) {  						  //EI20110819
    	                writeElementWithAttribute("ComplementaryInformation", 
    	                    						message.getComplementaryInformation().getText(),
    	                    						"language", message.getComplementaryInformation().getLanguage()); 
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

}    	

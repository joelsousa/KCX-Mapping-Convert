package com.kewill.kcx.component.mapping.formats.uids.emcs20;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.emcs.msg.MsgCancellation;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsMessageEMCS20;
import com.kewill.kcx.component.mapping.util.EFormat;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: EMCS<br>
 * Created		: 07.05.2010<br>
 * Description  : Contains Message Structure with fields used in EMCSCancellation Uids message.
 *              : V20 - DateTime format was changed                
 *                 
 * @author iwaniuk
 * @version 2.0.00
 * 
 */

public class BodyCancellationUids extends UidsMessageEMCS20 {
	
    private MsgCancellation  message = new MsgCancellation();
  
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
        	openElement("soap:Body");
            openElement("Submit");
                setAttribute("xmlns", "http://www.eurtradenet.com/schemas/uids/emcs/body/200911");
            openElement("EMCS");
            openElement("EMCSCancellationEAAD");    
                        	
            	writeElement("LocalReferenceNumber", message.getReferenceNumber());   
                writeElement("AadReferenceCode", message.getAadReferenceCode());                   
                //writeStringToDateTime("DateAndTimeOfValidationOfCancellation", ....
                writeFormattedDateTime("DateAndTimeOfValidation", 
                    						message.getDateAndTimeOfValidationOfCancellation(),
                    						EFormat.KIDS_DateTime, EFormat.ST_DateTimeTZ);	     
                writeElement("CancellationReasonCode", message.getCancellationReasonCode());
                //V20 - new Tag
                if (message.getComplementaryInformation() != null) {  						  //EI20110819
    	            writeElementWithAttribute("ComplementaryInformation", 
    	                    				message.getComplementaryInformation().getText(),
    	                    				"language", message.getComplementaryInformation().getLanguage()); 
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

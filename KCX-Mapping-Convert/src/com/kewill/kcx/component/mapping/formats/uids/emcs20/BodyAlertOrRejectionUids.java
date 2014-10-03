package com.kewill.kcx.component.mapping.formats.uids.emcs20;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.kewill.kcx.component.mapping.countries.de.emcs.msg.MsgAlertOrRejection;
import com.kewill.kcx.component.mapping.formats.uids.common.UidsMessageEMCS20;
import com.kewill.kcx.component.mapping.util.EFormat;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: EMCS<br>
 * Created		: 05.07.2011<br>
 * Description  : Contains Message Structure with fields used in EMCSAlertOrRejection Uids message.
 *              : new for EMCS V20
 *                 
 * @author iwaniuk
 * @version 2.0.00
 * 
 */

public class BodyAlertOrRejectionUids extends UidsMessageEMCS20 {
	
    private MsgAlertOrRejection  message = new MsgAlertOrRejection();  
    
    public BodyAlertOrRejectionUids(XMLStreamWriter writer) {
        this.writer = writer;
    }

	public MsgAlertOrRejection getMessage() {
		return message;
	}

	public void setMessage(MsgAlertOrRejection message) {
		this.message = message;
	}
    
	public void writeBody() {		
		
        try {         	
        	openElement("soap:Body");
            openElement("Submit");
            	setAttribute("xmlns", "http://www.eurtradenet.com/schemas/uids/emcs/body/200911");               
            openElement("EMCS");               
            openElement("EMCSAlertOrRejection");  
            
            	writeElement("LocalReferenceNumber", message.getReferenceNumber());
                writeElement("AadReferenceCode", message.getAadReferenceCode());
                writeElement("SequenceNumber", message.getSequenceNumber());
                writeElement("DestinationOffice", message.getDestinationOffice());                               
                //writeStringToDateTime("DateAndTimeOfValidation", message.getDateAndTimeOfValidation());                
                writeFormattedDateTime("DateAndTimeOfValidation", message.getDateAndTimeOfValidation(),
							EFormat.KIDS_DateTime, EFormat.ST_DateTimeTZ);	                        
                writeElement("RejectedFlag", message.getRejectedFlag());                       
                writeStringToDate("DateOfAlertOrRejection", message.getDateOfAlertOrRejection()); 
                writeTrader("ConsigneeTrader", message.getConsignee());
                    
                if (message.getAlertOrRejectionReasons() != null) {
                    if (message.getAlertOrRejectionReasons().getReasonCodeList() != null && 
                    		   !message.getAlertOrRejectionReasons().getReasonCodeList().isEmpty()) { 
                    	openElement("AlertOrRejectionReasons");  
                    		for (String code : message.getAlertOrRejectionReasons().getReasonCodeList()) {
                    			writeElement("ReasonCode", code);  
                    		}
                    	closeElement();                	   
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

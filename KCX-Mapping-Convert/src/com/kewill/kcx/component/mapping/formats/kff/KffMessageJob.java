package com.kewill.kcx.component.mapping.formats.kff;

import java.math.BigDecimal;

import javax.xml.stream.XMLStreamException;

import com.kewill.kcx.component.mapping.formats.kids.common.KidsHeader;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module : Imort<br>
 * Created : 24.02.2012<br>
 * Description : common methods for KFF-JOB bodies.
 * 
 * @author Iwaniuk
 * @version 1.0.00
 */
public class KffMessageJob extends KidsMessage {	
	
	public void writeJobHeader(KidsHeader kidsHeader) throws XMLStreamException {
		if (kidsHeader == null) {
			return;
		}
		String kidsDT = kidsHeader.getSentAt();
		String kidsT = kidsHeader.getTime();
		String date = "";
        String time = "";       
        //String senderId = kidsHeader.getTransmitter();
        //String senderUserId = kidsHeader.getReceiver();
        String senderId = "ZABIS";      //EI20120315
        String senderUserId = "ZABIS";  //EI20120315
        
        if (kidsDT != null && kidsDT.length() > 10) {
        	date = kidsDT.substring(0, 4) + kidsDT.substring(5, 7)  + kidsDT.substring(8, 10);
        }
        if (kidsT != null && kidsT.length() == 8) {
        	time = kidsT.substring(0, 2) + kidsT.substring(3, 5)  + kidsT.substring(6, 8);
        }
        	         	              
		openElement("DocumentInformation");		
			openElement("DocumentFormat");
				writeElement("FormatIdentifier", "JOB");
				writeElement("FormatVersion", "V1_0");
			closeElement();		
			openElement("TransmissionDateTime");
				writeElement("TransmissionDate", date);
				writeElement("TransmissionTime", time);
				writeElement("TransmissionTimeZone", "Europe/Amsterdam");
			closeElement();	        			
		closeElement();
	
		openElement("UserInformation");	        	
			writeElement("SenderID", senderId);
			writeElement("SenderUserID", senderUserId);			
		closeElement();  
	               
	}
	public String getDecimal(String value, int zabis, int job) throws XMLStreamException {
		String ret = "";
		if (Utils.isStringEmpty(value)) {
			return ret;
		}
		
		double deci = Double.parseDouble(value);   			
		if (deci > 0) {
			while (zabis > 0) {
				deci = deci / 10;              
			    zabis--;			     
			} 			
		}
		if (job >= 0) { 
			BigDecimal newDec = new BigDecimal(deci);
	        newDec = newDec.setScale(job, BigDecimal.ROUND_HALF_DOWN);
	        ret = newDec.toString();
		} 
		
		return ret;
	}
	
}

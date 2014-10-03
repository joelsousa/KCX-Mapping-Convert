package com.kewill.kcx.component.mapping.countries.de.emcs.msg.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module       : EMCS<br>
 * Created		: 10.02.2014<br>
 * Description  : Contains the Member for save tags from the UIDS message.   
 * 
 * @author iwaniuk
 * @version 1.0.00
 */

public class AlertOrRejectionReasons extends KCXMessage {
	
	private ArrayList <RejectionReason> rejectionReasonList;
	
	private enum EAlertOrRejectionReasons {
    //KIDS : are simple tags	//UIDS:
		RejectionReason,		//same							
	}	
	
	public AlertOrRejectionReasons() {
  		super();  		
  	}
	 
	public AlertOrRejectionReasons(XmlMsgScanner scanner) {
  		super(scanner);  		
  	}
	
	public void startElement(Enum tag, String value, Attributes attr) {
	    if (value == null) {
			switch ((EAlertOrRejectionReasons) tag) {
			case RejectionReason:
				RejectionReason rr = new RejectionReason(getScanner());
				rr.parse(tag.name());
				addRejectionReasonList(rr);
				break;
			default:
				return;
			}
	    } else {
	    	switch ((EAlertOrRejectionReasons) tag) {	    	
			default:
				break;
			}
	    }		
	}

	public void stoppElement(Enum tag) {
	}

	public Enum translate(String token) {
		try {
  			return EAlertOrRejectionReasons.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
	}
	
	public ArrayList<RejectionReason> getRejectionReasonList() {
		return this.rejectionReasonList;
	}
	public void setRejectionReasonList(ArrayList<RejectionReason> list) {
		this.rejectionReasonList = list;
	}
	public void addRejectionReasonList(RejectionReason argument) {
		if (this.rejectionReasonList == null) {
			rejectionReasonList = new ArrayList<RejectionReason>();
		}
		this.rejectionReasonList.add(argument);
	}	

	
	public boolean isEmpty() {		
		return (rejectionReasonList == null || rejectionReasonList.isEmpty());			
	}
}

package com.kewill.kcx.component.mapping.countries.de.cmp.msg.common;

import java.util.ArrayList;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
* Module		: CMP<br>
* Created		: 24.07.2013<br>
* Description	: ReportedStatus.
* 
* @author iwaniuk
* @version 1.0.00
*/

public class ReportedStatus extends KCXMessage {
	
	private String reasonCode;
    private ArrayList<ReportedStatusConsignment> associatedStatusConsignmentList;
       //iwa: ReportedStatusConsignment heisst jetzt in cmp.xsd AssociatedStatusConsignment
	 
    private enum EReportedStatus {
    	ReasonCode,
    	AssociatedStatusConsignment,    	
    }

    public ReportedStatus() {
	      	super();	       
    }
    
    public ReportedStatus(XmlMsgScanner scanner) {
    	super(scanner);    	
  	}
    
    public void startElement(Enum tag, String value, Attributes attr) {
    		if (value == null) {
    			switch ((EReportedStatus) tag) {
    			
    			case AssociatedStatusConsignment:
    				ReportedStatusConsignment status = new ReportedStatusConsignment(getScanner());
    				status.parse(tag.name());  
    				addAssociatedStatusConsignmentList(status);
    				break;
    			
    			default:
    					return;
    			}
    		} else {

    			switch ((EReportedStatus) tag) {
    				case ReasonCode :
    					setReasonCode(value);
    					break;
    					    				
    				default:
    					break;
    			}
    		}
    	}

    public void stoppElement(Enum tag) {
    }

    public Enum translate(String token) {
   		try {
    			return EReportedStatus.valueOf(token);
    		} catch (IllegalArgumentException e) {
    			return null;
    		}
    }
	    
	public String getReasonCode() {
		return reasonCode;
	}
	public void setReasonCode(String value) {
		this.reasonCode = value;
	}

	public ArrayList<ReportedStatusConsignment> getAssociatedStatusConsignmentList() {
		return associatedStatusConsignmentList;
	}
	public void setAssociatedStatusConsignmentList(ArrayList<ReportedStatusConsignment> list) {
		this.associatedStatusConsignmentList = list;
	}
	public void addAssociatedStatusConsignmentList(ReportedStatusConsignment value) {
		if (associatedStatusConsignmentList == null) {
			associatedStatusConsignmentList = new ArrayList<ReportedStatusConsignment>();
		}
		this.associatedStatusConsignmentList.add(value);
	}
			
	public boolean isEmpty() {
		return Utils.isStringEmpty(reasonCode) &&
		associatedStatusConsignmentList == null;
	}
}

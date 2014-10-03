package com.kewill.kcx.component.mapping.countries.de.emcs.msg;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;
import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;

/**
 * Modul		: EMCS<br>
 * Created		: 12.05.2010<br>
 * Description  : Contains Message Structure with fields used in InternalStatusInformation message,
 *              : InternalStatusInformation as aes.MsgExpNck, 
 *              : instaed of InternalStatusInformation will be used MsgExpNck.              
 *                
 * @author iwaniuk
 * @version 1.0.00
 */

public class InternalStatusInformation extends KCXMessage {

	private String msgName = "InternalStatusInformation";
	private String referenceNumber;
	private String orderNumber;
	private String correctionNumber;
	private String temporaryUCR;	
	private String dateNewStatus;
	private String timeNewStatus;
	private String newStatus;
	
	private String newStatusText;
 
	private enum EInternalStatus {
		//KIDS:									UIDS:
		ReferenceNumber,        				
		OrderNumber,     				        
		CorrectionNumber,                  			    
		TemporaryUCR,						
		DateNewStatus, 
		TimeNewStatus,
		NewStatus;				    
	}
	
	public InternalStatusInformation() {
		super();
	}

	public InternalStatusInformation(XMLEventReader parser) throws XMLStreamException {
	super(parser);
	}
	
	public void startElement(Enum tag, String value, Attributes attr) {
	    if (value == null) {
			switch ((EInternalStatus) tag) {
			/*
			case NewConsignee:				
				newConsignee = new Trader(getScanner());
				newConsignee.parse(tag.name());
				break;
				*/		
			default:
				return;
			}
	    } else {
	    	switch ((EInternalStatus) tag) {
			case ReferenceNumber:			
				 setReferenceNumber(value);
				 break;	
			case OrderNumber:
				 setOrderNumber(value);
				 break;		
			case CorrectionNumber:
				 setCorrectionNumber(value);
				 break;	
			case TemporaryUCR:
				 setTemporaryUCR(value);
				 break;
			case DateNewStatus:
				 setDateNewStatus(value);
				 break;					 
			case TimeNewStatus:
				 setDateNewStatus(value);
				 break;		
				 /*
			case NewStatus:
				 setNewStatus(value);
				 break;	
				 */
			case NewStatus:
        		setNewStatusText(value);
                if (this.newStatusText.equals("91")) {
                	setNewStatus("failure");
                	setNewStatusText("91-InhouseCreationError");
                } else if (this.newStatusText.equals("92")) {
                	setNewStatus("failure");
                	setNewStatusText("92-EdifactCreationError");
                } else {
                	setNewStatus("status");
                }
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
  			return EInternalStatus.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
	}

	public String getMsgName() {
		return this.msgName;
	}
	public void setMsgName(String argument) {
		this.msgName = argument;
	}
	
	public String getReferenceNumber() {
		return this.referenceNumber;
	}
	public void setReferenceNumber(String argument) {
		this.referenceNumber = argument;
	}
	
	public String getOrderNumber() {
		return this.orderNumber;
	}
	public void setOrderNumber(String argument) {
		this.orderNumber = argument;
	}		                  			   
	
	public String getCorrectionNumber() {
		return this.correctionNumber;
	}
	public void setCorrectionNumber(String argument) {
		this.correctionNumber = argument;
	}
	
	public String getTemporaryUCR() {
		return this.temporaryUCR;
	}
	public void setTemporaryUCR(String argument) {
		this.temporaryUCR = argument;
	}		
	public String getDateNewStatus() {
		return this.dateNewStatus;
	}
	public void setDateNewStatus(String argument) {
		this.dateNewStatus = argument;
	}
	
	public String getTimeNewStatus() {
		return this.timeNewStatus;
	}
	public void setTimeNewStatus(String argument) {
		this.timeNewStatus = argument;
	}	
	
	public String getNewStatus() {
		return this.newStatus;
	}
	public void setNewStatus(String argument) {
		this.newStatus = argument;
	}
	
	public String getNewStatusText() {
		return newStatusText;
	}

	public void setNewStatusText(String argument) {
		newStatusText = argument;
	}	
}

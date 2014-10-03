package com.kewill.kcx.component.mapping.countries.de.emcs.msg.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.common.Text;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Modul    	: EMCS.<br>
 * Created		: 05.05.2010<br>
 * Description  : AttributesType - UIDS                 
 * 
 * @author krzoska
 * @version 1.0.00
 */

public class AttributesType extends KCXMessage {

	private String submissionMessageType;			
    private String dateAndTimeOfIssuance;
    private Text   reminderInformation;
    private String limitDateAndTime;
    private String reminderMessageType;    
    private String dateAndTimeOfValidationOfCancellation;
    private String deferredSubmissionFlag;        
    
	private enum EAttributesType {	
		//UIDS:  only   KIDS : are simple tags
		SubmissionMessageType,			        
		DateAndTimeOfIssuanceOfReminder,  DateAndTimeOfIssuance,   	        
		ReminderInformation,	
		LimitDateAndTime,	   		      
		ReminderMessageType,				      
		DateAndTimeOfValidationOfCancellation,	
		DeferredSubmissionFlag;								
   }	
	
	public AttributesType() {
  		super();
  	}	
	 
	public AttributesType(XmlMsgScanner scanner) {
  		super(scanner);
  	}

	public void startElement(Enum tag, String value, Attributes attr) {
  		if (value == null) {
  			switch ((EAttributesType) tag) {
  			/*
				case ReminderInformation:
					reminderInformation = new ReminderInformation(getScanner());
					reminderInformation.parse(tag.name());					
					break;
					*/					
				default:
  					return;
  			}
  		} else {
  			switch ((EAttributesType) tag) {   
  				case SubmissionMessageType:
  					setSubmissionMessageType(value);
					break;   			
  				case DateAndTimeOfIssuanceOfReminder:
  				case DateAndTimeOfIssuance:
  					setDateAndTimeOfIssuance(value);
  					break;   					  
  				case ReminderInformation:
  					//reminderInformation = new Text(value, attr.getValue("language"));
  					reminderInformation = new Text(value, attr);  //EI20110926
  					break;   					
  				case LimitDateAndTime:
  					setLimitDateAndTime(value);
  					break;
 				case ReminderMessageType:
 					setReminderMessageType(value);
					break;					
  				case DateAndTimeOfValidationOfCancellation:
  					setDateAndTimeOfValidationOfCancellation(value);
  					break;   					    				  					
  				case DeferredSubmissionFlag:
  					setDeferredSubmissionFlag(value);
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
  			return EAttributesType.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
	}

	public String getSubmissionMessageType() {
		return Utils.checkNull(submissionMessageType);
	}
	public void setSubmissionMessageType(String argument) {
		this.submissionMessageType = argument;
	}

	public String getDateAndTimeOfIssuance() {
		return Utils.checkNull(dateAndTimeOfIssuance);
	}
	public void setDateAndTimeOfIssuance(String argument) {
		this.dateAndTimeOfIssuance = argument;
	}

	public Text getReminderInformation() {
		return reminderInformation;
	}
	public void setReminderInformation(Text reminderInformation) {
		this.reminderInformation = reminderInformation;
	}
	
	public String getLimitDateAndTime() {
		return Utils.checkNull(limitDateAndTime);
	}
	public void setLimitDateAndTime(String argument) {
		this.limitDateAndTime = argument;
	}
	
	public String getReminderMessageType() {
		return Utils.checkNull(reminderMessageType);
	}
	public void setReminderMessageType(String argument) {
		this.reminderMessageType = argument;
	}
	
	public String getDateAndTimeOfValidationOfCancellation() {
		return Utils.checkNull(dateAndTimeOfValidationOfCancellation);
	}
	public void setDateAndTimeOfValidationOfCancellation(String argument) {
		this.dateAndTimeOfValidationOfCancellation = argument;
	}

	public String getDeferredSubmissionFlag() {
		return Utils.checkNull(deferredSubmissionFlag);
	}
	public void setDeferredSubmissionFlag(String argument) {
		this.deferredSubmissionFlag = argument;
	}	
	
	public boolean isEmpty() {
		String text = "";
		if (reminderInformation != null) {
			text = reminderInformation.getText();
		}
	
		return (Utils.isStringEmpty(this.submissionMessageType) && 
			Utils.isStringEmpty(this.dateAndTimeOfIssuance) && 
    		Utils.isStringEmpty(text) && 
    		Utils.isStringEmpty(this.limitDateAndTime) && 
    		Utils.isStringEmpty(this.reminderMessageType) &&
    		Utils.isStringEmpty(this.dateAndTimeOfValidationOfCancellation) && 
    		Utils.isStringEmpty(this.deferredSubmissionFlag));
	}
}
   

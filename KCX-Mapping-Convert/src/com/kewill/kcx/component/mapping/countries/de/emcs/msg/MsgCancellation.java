package com.kewill.kcx.component.mapping.countries.de.emcs.msg;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.common.Text;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;

/**
 * Module		: EMCS<br>
 * Created 		: 07.05.2010<br>
 * Description  : Contains Message Structure with fields used in Kids EMCSCancellation  and
 *              : Uids EMCSCancellationEAAD message. 
 *              : IE810 / C_CAN_DAT
 *                 
 * @author iwaniuk
 * @version 1.0.00
 */

public class MsgCancellation extends KCXMessage {

	private String msgName = "EMCSCancellation";
	private String referenceNumber;
	private String customerOrderNumber;
	private String clerk;
	private String aadReferenceCode;	
	private String dateAndTimeOfValidationOfCancellation;
	private String cancellationReasonCode;
	private Text   complementaryInformation;
	
 
	private enum ECancellation {
		//KIDS:									UIDS:
		ReferenceNumber,        				LocalReferenceNumber,  
		CustomerOrderNumber,     				//not defined
		Clerk,                  			    //not defined  
		AadReferenceCode,						//same
		DateAndTimeOfValidationOfCancellation,  //EI201006730: DateAndTimeOfValidationOfEaad, 
		DateAndTimeOfValidation,				//same
		CancellationReasonCode,				    //same
		ComplementaryInformation;               //same		
	}
	
	public MsgCancellation() {
		super();
	}

	public MsgCancellation(XMLEventReader parser) throws XMLStreamException {
	super(parser);
	}

	
	public void startElement(Enum tag, String value, Attributes attr) {
	    if (value == null) {
			switch ((ECancellation) tag) {
				
			default:
				return;
			}
	    } else {
	    	switch ((ECancellation) tag) {
			case ReferenceNumber:
			case LocalReferenceNumber:
				 setReferenceNumber(value);
				 break;	
			case CustomerOrderNumber:
				 setCustomerOrderNumber(value);
				 break;		
			case Clerk:
				 setClerk(value);
				 break;	
			case AadReferenceCode:
				 setAadReferenceCode(value);
				 break;
			case DateAndTimeOfValidationOfCancellation:
			case DateAndTimeOfValidation:			
				 setDateAndTimeOfValidationOfCancellation(value);
				 break;					 
			case CancellationReasonCode:
				 setCancellationReasonCode(value);
				 break;	
			case ComplementaryInformation:
				 //complementaryInformation = new Text(value, attr.getValue("language"));
				 complementaryInformation = new Text(value, attr);  //EI20110926
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
  			return ECancellation.valueOf(token);
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
	
	public String getCustomerOrderNumber() {
		return this.customerOrderNumber;
	}
	public void setCustomerOrderNumber(String argument) {
		this.customerOrderNumber = argument;
	}	

	public String getClerk() {
		return this.clerk;
	}
	public void setClerk(String argument) {
		this.clerk = argument;
	}
	
	public String getAadReferenceCode() {
		return this.aadReferenceCode;
	}
	public void setAadReferenceCode(String argument) {
		this.aadReferenceCode = argument;
	}		
	public String getDateAndTimeOfValidationOfCancellation() {
		return this.dateAndTimeOfValidationOfCancellation;
	}
	public void setDateAndTimeOfValidationOfCancellation(String argument) {
		this.dateAndTimeOfValidationOfCancellation = argument;
	}
	
	public String getCancellationReasonCode() {
		return this.cancellationReasonCode;
	}
	public void setCancellationReasonCode(String argument) {
		this.cancellationReasonCode = argument;
	}	
	
	public Text getComplementaryInformation() {
		return complementaryInformation;	
	}
	public void setComplementaryInformation(Text complementaryInformation) {
		this.complementaryInformation = complementaryInformation;
	}
}

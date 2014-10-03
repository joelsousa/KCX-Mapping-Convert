package com.kewill.kcx.component.mapping.countries.de.emcs21.msg;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.countries.de.emcs.msg.common.AlertOrRejectionReasons;
import com.kewill.kcx.component.mapping.countries.de.emcs.msg.common.EmcsTrader;

/**
 * Module		: EMCS<br>
 * Created		: 04.07.2011<br>
 * Description  : Contains Message Structure with fields used in Kids/Uids EMCSAlertOrRejection messege.
 *              : IE819 / C_REJ_DAT
 *                 
 * @author iwaniuk
 * @version 1.0.00
 */

public class MsgAlertOrRejection extends KCXMessage {

	private String msgName = "EMCSAlertOrRejection";
	private String referenceNumber;
	private String customerOrderNumber;     //EI20110804
	private String clerk;                   //EI20110804
	private String aadReferenceCode;
	private String sequenceNumber;
	private String destinationOffice;	
	private String dateAndTimeOfValidation;
	private String rejectedFlag;
	private String dateOfAlertOrRejection;
	private EmcsTrader  consignee;
	//private ArrayList<ReasonCode> alertOrRejectionReasons;   
	private AlertOrRejectionReasons alertOrRejectionReasons;
 
	private enum EAlertOrRejection {
		//KIDS:									UIDS:
		ReferenceNumber,        				LocalReferenceNumber, 
		CustomerOrderNumber,                    //--
		Clerk,                                  //--
		AadReferenceCode,						//same
		SequenceNumber,     					//same
		DestinationOffice,                  	//same
		DateAndTimeOfValidation,  				//same
		RejectedFlag,							//same
		DateOfAlertOrRejection,					//same
		Consignee, 								ConsigneeTrader,
		AlertOrRejectionReasons;				    //same			
	}
	
	public MsgAlertOrRejection() {
		super();
	}

	public MsgAlertOrRejection(XMLEventReader parser) throws XMLStreamException {
	super(parser);
	}

	
	public void startElement(Enum tag, String value, Attributes attr) {
	    if (value == null) {
			switch ((EAlertOrRejection) tag) {
			case Consignee:
			case ConsigneeTrader:
				consignee = new EmcsTrader(getScanner());
				consignee.parse(tag.name());
				break;			
			case AlertOrRejectionReasons:
				alertOrRejectionReasons = new AlertOrRejectionReasons(getScanner());
				alertOrRejectionReasons.parse(tag.name());
				break;
			default:
				return;
			}
	    } else {
	    	switch ((EAlertOrRejection) tag) {
			case ReferenceNumber:
			case LocalReferenceNumber:
				 setReferenceNumber(value);
				 break;
			case AadReferenceCode:
				 setAadReferenceCode(value);
				 break;
			case SequenceNumber:
				 setSequenceNumber(value);
				 break;		
			case DestinationOffice:
				 setDestinationOffice(value);
				 break;							
			case DateAndTimeOfValidation:			
				 setDateAndTimeOfValidation(value);
				 break;					 
			case RejectedFlag:
				 setRejectedFlag(value);
				 break;	
			case DateOfAlertOrRejection:
				 setDateOfAlertOrRejection(value);
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
  			return EAlertOrRejection.valueOf(token);
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
	
	public String getSequenceNumber() {
		return this.sequenceNumber;
	}
	public void setSequenceNumber(String argument) {
		this.sequenceNumber = argument;
	}	
	
	public String getAadReferenceCode() {
		return this.aadReferenceCode;
	}
	public void setAadReferenceCode(String argument) {
		this.aadReferenceCode = argument;
	}	
	
	public String getDestinationOffice() {
		return this.destinationOffice;
	}
	public void setDestinationOffice(String argument) {
		this.destinationOffice = argument;
	}
	public String getDateAndTimeOfValidation() {
		return this.dateAndTimeOfValidation;
	}
	public void setDateAndTimeOfValidation(String argument) {
		this.dateAndTimeOfValidation = argument;
	}
	
	public String getRejectedFlag() {
		return this.rejectedFlag;
	}
	public void setRejectedFlag(String argument) {
		this.rejectedFlag = argument;
	}	
	
	public String getDateOfAlertOrRejection() {
		return dateOfAlertOrRejection;	
	}
	public void setDateOfAlertOrRejection(String argument) {
		this.dateOfAlertOrRejection = argument;
	}
	
	public EmcsTrader getConsignee() {
		return consignee;	
	}
	public void setConsignee(EmcsTrader consignee) {
		this.consignee = consignee;
	}	
		
	public void setCustomerOrderNumber(String argument) {
		this.customerOrderNumber = argument;
	}
	public String getCustomerOrderNumber() {
		return this.customerOrderNumber;
	}
	
	public void setClerk(String argument) {
		this.clerk = argument;
	}
	public String getClerk() {
		return this.clerk;
	}

	public AlertOrRejectionReasons getAlertOrRejectionReasons() {
		return alertOrRejectionReasons;
	}
	public void setAlertOrRejectionReasons(AlertOrRejectionReasons alertOrRejectionReasons) {
		this.alertOrRejectionReasons = alertOrRejectionReasons;
	}
	
}

package com.kewill.kcx.component.mapping.countries.de.emcs.msg;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.countries.common.Text;
import com.kewill.kcx.component.mapping.countries.de.emcs.msg.common.ExciseMovementEaad;

/**
 * Module		: EMCS<br>
 * Created		: 11.05.2010<br>
 * Description  : Contains Message Structure with fields used in EMCSExplanationOnDelay (KIDS)
 *              : EMCSExplanationOnDelayForDelivery (UIDS) 
 *              : IE837 / C_DEL_EXP.  
 *                 
 * @author iwaniuk
 * @version 1.0.00
 */

public class MsgExplanationOnDelay extends KCXMessage {

	private String msgName = "EMCSExplanationOnDelay";
	private String referenceNumber;
	private String customerOrderNumber;
	private String clerk;
	private String aadReferenceCode;	
	private String sequenceNumber;
	private Text complementaryInformation;
	private String explanationCode;
	private String submitterType;
	private String exciseTaxNumber;
	private String destinationOffice;
	private String messageRole;
	
	private int count = 0;
	
 
	private enum EExplDelay {
		//KIDS:									UIDS:
		ReferenceNumber,        				//not defined
		CustomerOrderNumber,     				//not defined
		Clerk,                  			    //not defined  
		AadReferenceCode,						ExciseMovementEaad, //ExciseMovementEaad.AadReferenceCode
		SequenceNumber,                                             //ExciseMovementEaad.SequenceNumber
		ComplementaryInformation,               //same
		MessageRole, 							//same  
		ExplanationCode,                        //same
		SubmitterType,							//same
		DestinationOffice,                      //same
		ExciseTaxNumber,				        SubmitterIdentification;

	}
	
	public MsgExplanationOnDelay() {
		super();
	}

	public MsgExplanationOnDelay(XMLEventReader parser) throws XMLStreamException {
	super(parser);
	}
	
	public void startElement(Enum tag, String value, Attributes attr) {
	    if (value == null) {
			switch ((EExplDelay) tag) {
			case ExciseMovementEaad:
				count = count + 1;
				ExciseMovementEaad tempExc = new ExciseMovementEaad(getScanner());
				tempExc.parse(tag.name());
				if (count == 1) {
					setExciseMovementEaad(tempExc);
				}
				break;	
			default:
				return;
			}
	    } else {
	    	switch ((EExplDelay) tag) {
			case ReferenceNumber:			
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
			case SequenceNumber:
				 setSequenceNumber(value);
				 break;					 
			case ComplementaryInformation:
				 //complementaryInformation = new Text(value, attr.getValue("language"));		
				 complementaryInformation = new Text(value, attr);  //EI20110926
				 break;	
			case ExplanationCode:
				 setExplanationCode(value);
				 break;				 
			case SubmitterType:
				 setSubmitterType(value);
				 break;				
			case ExciseTaxNumber:
			case SubmitterIdentification:
				 setExciseTaxNumber(value);
				 break;	
			case DestinationOffice:
				setDestinationOffice(value);
				break;
			case MessageRole:
				setMessageRole(value);
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
  			return EExplDelay.valueOf(token);
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
	
	public String get() {
		return this.aadReferenceCode;
	}
	public void set(String argument) {
		this.aadReferenceCode = argument;
	}	
	
	public String getSequenceNumber() {
		return this.sequenceNumber;
	}
	public void setSequenceNumber(String argument) {
		this.sequenceNumber = argument;
	}
	
	public Text getComplementaryInformation() {
		return this.complementaryInformation;
	}
	public void setComplementaryInformation(Text argument) {
		this.complementaryInformation = argument;
	}
	
	public String getExplanationCode() {
		return this.explanationCode;
	}
	public void setExplanationCode(String argument) {
		this.explanationCode = argument;
	}	
	
	public String getSubmitterType() {
		return this.submitterType;
	}
	public void setSubmitterType(String argument) {
		this.submitterType = argument;
	}
	
	public String getExciseTaxNumber() {
		return this.exciseTaxNumber;
	}
	public void setExciseTaxNumber(String argument) {
		this.exciseTaxNumber = argument;
	}	
	
	public void setExciseMovementEaad(ExciseMovementEaad argument) {
		if (argument == null) {
			return;
		}		
		this.aadReferenceCode = argument.getAadReferenceCode();
		this.sequenceNumber = argument.getSequenceNumber();		
	}
	
	
	public String getDestinationOffice() {
		return this.destinationOffice;
	}
	public void setDestinationOffice(String argument) {
		this.destinationOffice = argument;
	}	
	
	public String getMessageRole() {
		return this.messageRole;
	}
	public void setMessageRole(String argument) {
		this.messageRole = argument;
	}	
}

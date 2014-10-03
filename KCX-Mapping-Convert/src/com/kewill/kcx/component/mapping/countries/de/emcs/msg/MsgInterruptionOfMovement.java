package com.kewill.kcx.component.mapping.countries.de.emcs.msg;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.common.Text;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.countries.de.emcs.msg.common.StringList;

/**
 * Module		: EMCS<br>
 * Created		: 04.07.2011<br>
 * Description	: Contains Message Structure with fields used in Kids/Uids MsgInteruptionOfMovement message. 
 *              : (IE807 / C_STP_NOT)  
 *                 
 * @author iwaniuk
 * @version 1.0.00
 */

public class MsgInterruptionOfMovement extends KCXMessage {

	private String msgName = "EMCSInterruptionOfMovement";
	private String referenceNumber;
	private String customerOrderNumber;     			//EI20110819
	private String clerk;                  			    //EI20110819	
	private String reasonForInteruption;
	private String aadReferenceCode;
	private String dateAndTimeOfIssuance;	
	private String sequenceNumber;
	private Text complementaryInformation;	
	private String exciseCustomsOffice;
	private String customsOfficerID;		
	private StringList controlReport;
	private StringList eventReport;
	
	
	private enum EInteruptionOfMovement {
		//KIDS:									UIDS:  
		ReferenceNumber,        		LocalReferenceNumber,		 
		CustomerOrderNumber,     		//not defined
		Clerk,                  		//not defined  
		ReasonForInterruption,     		//same	                              //EI20110928 with ..rr..
		AadReferenceCode,     			//same
		SequenceNumber,		   			//same
		DateAndTimeOfIssuance,			//same
		ComplementaryInformation,  		//same
		ExciseCustomsOffice,			//same
		CustomsOfficerID,				IdentificationOfSenderCustomsOfficer,
		ReferenceControlReports,        //same
			//ControlReportReference,
		ReferenceEventReports;			//same
			//EventReportNumber;		
	}
	
	public MsgInterruptionOfMovement() {
		super();
	}

	public MsgInterruptionOfMovement(XMLEventReader parser) throws XMLStreamException {
	super(parser);
	}
	
	public void startElement(Enum tag, String value, Attributes attr) {
	    if (value == null) {
			switch ((EInteruptionOfMovement) tag) {
			case ReferenceControlReports:
				controlReport = new StringList(getScanner());
				controlReport.parse(tag.name());				
				break;	
			case ReferenceEventReports:
				eventReport = new StringList(getScanner());
				eventReport.parse(tag.name());				
				break;
			default:
				return;
			}
	    } else {
	    	switch ((EInteruptionOfMovement) tag) {
			case ReferenceNumber:	
			case LocalReferenceNumber:	                 //EI20110928
				 setReferenceNumber(value);
				 break;	
			case CustomerOrderNumber:
				 setCustomerOrderNumber(value);
				 break;		
			case Clerk:
				 setClerk(value);
				 break;	
			//case ReasonForInteruption:
			case ReasonForInterruption:
				 setReasonForInteruption(value);
				 break;		
			case AadReferenceCode:
				 setAadReferenceCode(value);
				 break;	
			case SequenceNumber:
				 setSequenceNumber(value);
				 break;	
			case DateAndTimeOfIssuance:
				 setDateAndTimeOfIssuance(value);
				 break;
			case ComplementaryInformation:
				 //complementaryInformation = new Text(value, attr.getValue("language"));
				 complementaryInformation = new Text(value, attr);  //EI20110926
				 break;					 
			case ExciseCustomsOffice:
				 setExciseCustomsOffice(value);
				 break;							
			case CustomsOfficerID:	
			case IdentificationOfSenderCustomsOfficer:
				 setCustomsOfficerID(value);
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
  			return EInteruptionOfMovement.valueOf(token);
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
	
	public String getReasonForInteruption() {
		return this.reasonForInteruption;
	}
	public void setReasonForInteruption(String argument) {
		this.reasonForInteruption = argument;
	}	

	public String getAadReferenceCode() {
		return this.aadReferenceCode;
	}
	public void setAadReferenceCode(String argument) {
		this.aadReferenceCode = argument;
	}
	
	public String getDateAndTimeOfIssuance() {
		return this.dateAndTimeOfIssuance;
	}
	public void setDateAndTimeOfIssuance(String argument) {
		this.dateAndTimeOfIssuance = argument;
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
	
	public String getExciseCustomsOffice() {
		return this.exciseCustomsOffice;
	}
	public void setExciseCustomsOffice(String argument) {
		this.exciseCustomsOffice = argument;
	}	

	public String getCustomsOfficerID() {
		return this.customsOfficerID;
	}
	public void setCustomsOfficerID(String argument) {
		this.customsOfficerID = argument;
	}	
	
	public StringList getReferenceControlReports() {
		return this.controlReport;
	}
	public void setReferenceControlReports(StringList argument) {
		this.controlReport = argument;
	}		
	
	public StringList getReferenceEventReports() {
		return this.eventReport;
	}
	public void setReferenceEventReports(StringList argument) {
		this.eventReport = argument;
	}
	public String getCustomerOrderNumber() {
		return customerOrderNumber;	
	}

	public void setCustomerOrderNumber(String argument) {
		this.customerOrderNumber = argument;
	}

	public String getClerk() {
		return clerk;	
	}

	public void setClerk(String argument) {
		this.clerk = argument;
	}
}

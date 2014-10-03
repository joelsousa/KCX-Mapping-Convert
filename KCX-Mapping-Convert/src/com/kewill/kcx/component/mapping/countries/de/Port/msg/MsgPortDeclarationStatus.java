package com.kewill.kcx.component.mapping.countries.de.Port.msg;

import java.util.List;
import java.util.Vector;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.Port.msg.common.StatusAnnotation;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;

/**
 * Module		: Port<br>.
 * Created		: 12.09.2011<br>
 * Description	: KIDS MsgPortDeclarationStatus
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class MsgPortDeclarationStatus extends KCXMessage { 
	
	private String			msgName = "PortDeclarationStatus";
	private String			referenceNumber;         //beznr
	private String			portSystem;              //aki_hasys: ZAPP, BHT
	private String			sendingDateTime;       //
	private String			statusSender;         //
	private String			sequenceNumber;
	private String			mrn;         //
	private String			portRegistrationNumber;                //ak	
	private String			portRegistrationMode;           //akr	
	private String			statusKind;             //ak
	private String			statusCode;  //akr
	private String			statusType;                 //akn	
	private String	        dateOfStatus;            //a
	private String	        statusDescription;             //a
	private String          dateOfControl;     //a	        
	private String			controlDescription;                   //	
	private String			controlAnnotation;        //a
	private String			dateOfInterdiction;              //a	
	private String			dateOfFinal;                   //ak.	
	private String   		dateOfExitAllowance;         //a 
	private String			containerNumber;                  //	
	 

	private List<StatusAnnotation> statusAnnotationList;	         //:
		
	public MsgPortDeclarationStatus() {
		super();
	}
	public MsgPortDeclarationStatus(XMLEventReader parser)throws XMLStreamException {
		super(parser);
	}
	
	private enum EPortDeclarationStatus {
		//KIDS:							KFF:
		ReferenceNumber,
		PortSystem,	
		SendingDateTime,
		StatusSender,
		SequenceNumber,
		MRN,		
		PortRegistrationNumber,
		PortRegistrationMode,
		StatusKind,
		StatusCode,
		StatusType,		
		DateOfStatus,					
		StatusDescription,			
		DateOfControl,	
		ControlDescription,
		ControlAnnotation,		
		DateOfInterdiction,
		DateOfFinal,
		DateOfExitAllowance,							
		ContainerNumber,    	
		
		StatusAnnotation;		
	}
	
	
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
			switch ((EPortDeclarationStatus) tag) {
			
			case StatusAnnotation:
				StatusAnnotation item = new StatusAnnotation(getScanner());
				item.parse(tag.name());
				addStatusAnnotationList(item);
				break;			
			
			default:
				return;
			}			 				
		} else {
			switch ((EPortDeclarationStatus) tag) {
			case ReferenceNumber:			
				setReferenceNumber(value);
				break;
			case PortSystem:		
				setPortSystem(value);
				break;			
			case SendingDateTime:		
				setSendingDateTime(value);
				break;
			case StatusSender:			
				setStatusSender(value);
				break;
			case SequenceNumber:
				setSequenceNumber(value);
				break;
			case MRN:	
				setMrn(value);
				break;
			case PortRegistrationNumber:			
				setPortRegistrationNumber(value);
				break;
			case PortRegistrationMode:				
				setPortRegistrationMode(value);
				break;				
			case StatusKind:
				setStatusKind(value);
				break;	
			case StatusCode:
				setStatusCode(value);
				break;	
			case StatusType:
				setStatusType(value);
				break;	
			case DateOfStatus:				
				setDateOfStatus(value);
				break;			
			case StatusDescription:				
				setStatusDescription(value);
				break;			
			case DateOfControl:
				setDateOfControl(value);
				break;	
			case ControlDescription:				
				setControlDescription(value);
				break;	
			case ControlAnnotation:				
				setControlAnnotation(value);
				break;
			case DateOfInterdiction:				
				setDateOfInterdiction(value);
				break;
			case DateOfFinal:				
				setDateOfFinal(value);
				break;
			case DateOfExitAllowance:
				setDateOfExitAllowance(value);
				break;
			case ContainerNumber:
				setContainerNumber(value);
				break;	
			default:
				return;	
			}
		}
	}

	public void stoppElement(Enum tag) {
		
	}

	public Enum translate(String token) {
		try {
			return EPortDeclarationStatus.valueOf(token);
		} catch (IllegalArgumentException e) {
		return null;
		}
	}
	
	public String getMsgName() {
		return msgName;
	}
	public void setMsgName(String msgName) {
		this.msgName = msgName;
	}
	
	public String getPortSystem() {
		return portSystem;
	}
	public void setPortSystem(String value) {
		this.portSystem = value;
	}
	
	public String getReferenceNumber() {
		return referenceNumber;
	}
	public void setReferenceNumber(String referenceNumber) {
		this.referenceNumber = referenceNumber;
	}
	
	public String getSendingDateTime() {
		return sendingDateTime;
	}
	public void setSendingDateTime(String value) {
		this.sendingDateTime = value;
	}	
	
	public String getStatusSender() {
		return statusSender;
	}
	public void setStatusSender(String value) {
		this.statusSender = value;
	}
	
	public String getSequenceNumber() {
		return sequenceNumber;
	}
	public void setSequenceNumber(String value) {
		this.sequenceNumber = value;
	}
	
	public String getMrn() {
		return mrn;
	}
	public void setMrn(String value) {
		this.mrn = value;
	}
	
	public String getPortRegistrationNumber() {
		return portRegistrationNumber;
	}
	public void setPortRegistrationNumber(String value) {
		this.portRegistrationNumber = value;
	}
	
	public String getPortRegistrationMode() {
		return portRegistrationMode;
	}
	public void setPortRegistrationMode(String value) {
		this.portRegistrationMode = value;
	}
	
	public String getStatusKind() {
		return statusKind;
	}
	public void setStatusKind(String value) {
		this.statusKind = value;
	}
		
	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String value) {
		this.statusCode = value;
	}
	
	public String getStatusType() {
		return statusType;
	}
	public void setStatusType(String value) {
		this.statusType = value;
	}
	
	public String getDateOfStatus() {
		return dateOfStatus;
	}
	public void setDateOfStatus(String value) {
		this.dateOfStatus = value;
	}
	
	public String getStatusDescription() {
		return statusDescription;
	}
	public void setStatusDescription(String value) {
		this.statusDescription = value;
	}
	
	public String getDateOfControl() {
		return dateOfControl;
	}
	public void setDateOfControl(String value) {
		this.dateOfControl = value;
	}
	
	public String getControlDescription() {
		return controlDescription;
	}
	public void setControlDescription(String value) {
		this.controlDescription = value;
	}
	
	public String getControlAnnotation() {
		return controlAnnotation;
	}
	public void setControlAnnotation(String value) {
		this.controlAnnotation = value;
	}
	
	
	public String getDateOfInterdiction() {
		return dateOfInterdiction;
	}
	public void setDateOfInterdiction(String value) {
		this.dateOfInterdiction = value;
	}
	
	public String getDateOfFinal() {
		return dateOfFinal;
	}
	public void setDateOfFinal(String value) {
		this.dateOfFinal = value;
	}
					
	public String getDateOfExitAllowance() {
		return dateOfExitAllowance;
	}
	public void setDateOfExitAllowance(String value) {
		this.dateOfExitAllowance = value;
	}
		
	public String getContainerNumber() {
		return containerNumber;
	}
	public void setContainerNumber(String value) {
		this.containerNumber = value;
	}
	
	
	public List<StatusAnnotation> getStatusAnnotationList() {
		return statusAnnotationList;
	}
	public void setStatusAnnotation(List<StatusAnnotation> list) {
		this.statusAnnotationList = list;
	}
	public void addStatusAnnotationList(StatusAnnotation item) {
		if (statusAnnotationList == null) {
			statusAnnotationList = new Vector<StatusAnnotation>();	
		}
		this.statusAnnotationList.add(item);
	}
	
	
}

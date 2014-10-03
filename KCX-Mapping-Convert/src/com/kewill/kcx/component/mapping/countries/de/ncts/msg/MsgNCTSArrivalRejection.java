/**
 * 
 */
package com.kewill.kcx.component.mapping.countries.de.ncts.msg;

import java.util.List;
import java.util.Vector;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.FunctionalErrorNcts;
import com.kewill.kcx.component.mapping.util.EFormat;

/**
 * Module		: MsgNCTSArrivalRejection<br>
 * Created		: 08.25.2010<br>
 * Description	: Contains Tag Mapping for fields used in NCTSArrivalRejection message. 
 * 
 * @author Michelle Bauza
 * @version 1.0.00
 */
public class MsgNCTSArrivalRejection extends KCXMessage {
	private String		messageName	= "NCTSArrivalRejection";
	private String		referenceNumber;
	private String		uCRNumber;
	private String		arrivalRejectionDate;
	private String		action;
	private String		reason;
	private List< FunctionalErrorNcts > errorList;
	
	private EFormat		arrivalRejectionDateFormat;
	
	public MsgNCTSArrivalRejection() {
		super();
	}
	
	public MsgNCTSArrivalRejection(XMLEventReader parser) throws XMLStreamException {
		super(parser);
	}
	
	private enum EArrivalRejection {
		// KIDS					// UIDS
		ReferenceNumber,		LocalReferenceNumber,
		UCRNumber,				MRN,
		ArrivalRejectionDate,	// same
		Action,					ActionToBeTaken,
		Reason,					ArrivalRejectionReason,
		Error,					FunctionalError
	}
	
	@Override
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
			switch((EArrivalRejection) tag) {
				case Error:
				case FunctionalError:
					FunctionalErrorNcts errorList = new FunctionalErrorNcts(getScanner());
					errorList.parse(tag.name());
					addErrorList(errorList);
					break;
				
				default:
					break;
			}
		} else {
			switch((EArrivalRejection) tag) {
				case ReferenceNumber:
				case LocalReferenceNumber:
					setReferenceNumber(value);
					break;
				
				case UCRNumber:
				case MRN:
					setUCRNumber(value);
					break;
				
				case ArrivalRejectionDate:
					setArrivalRejectionDate(value);
					
					//if (tag == EArrivalRejection.ArrivalRejectionDate) {
					if (value.indexOf('-') == -1) {
						setArrivalRejectionDateFormat(EFormat.KIDS_Date);
					} else {
						setArrivalRejectionDateFormat(EFormat.ST_Date);
					}
					break;
				
				case Action:
				case ActionToBeTaken:
					setAction(value);
					break;
				
				case Reason:
				case ArrivalRejectionReason:
					setReason(value);
					break;
				
				default:
					break;
			}
		}
	}

	@Override
	public void stoppElement(Enum tag) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Enum translate(String token) {
		try {
			return EArrivalRejection.valueOf(token);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}
	
	/**
	 * void addErrorList( FunctionalErrorNcts _value )
	 * 		--- method that adds value(s) to the errorList.
	 * 
	 * @param value	: value to be added
	 */
	public void addErrorList(FunctionalErrorNcts value) {
		if (this.errorList == null) {
			this.errorList	= new Vector< FunctionalErrorNcts >();
		}
		
		this.errorList.add(value);
	}

	public String getMessageName() {
		return this.messageName;
	}
	public void setMessageName(String msgName) {
		this.messageName	= msgName;
	}
	
	public String getReferenceNumber() {
		return this.referenceNumber;
	}
	public void setReferenceNumber(String refNum) {
		this.referenceNumber	= refNum;
	}
	
	public String getUCRNumber() {
		return this.uCRNumber;
	}
	public void setUCRNumber(String ucrNum) {
		this.uCRNumber	= ucrNum;
	}
	
	public String getArrivalRejectionDate() {
		return this.arrivalRejectionDate;
	}
	public void setArrivalRejectionDate(String arrRejDate) {
		this.arrivalRejectionDate	= arrRejDate;
	}
	
	public String getAction() {
		return this.action;
	}
	public void setAction(String action) {
		this.action	= action;
	}
	
	public String getReason() {
		return this.reason;
	}
	public void setReason(String reason) {
		this.reason	= reason;
	}
	
	public List< FunctionalErrorNcts > getErrorList() {
		return this.errorList;
	}
	public void setErroList(List<FunctionalErrorNcts> errList) {
		this.errorList	= errList;
	}
	
	public EFormat getArrivalRejectionDateFormat() {
		return this.arrivalRejectionDateFormat;
	}
	public void setArrivalRejectionDateFormat(EFormat arrRefDateFmt) {
		this.arrivalRejectionDateFormat	= arrRefDateFmt;
	}
}

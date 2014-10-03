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
 * Module		: MsgNCTSUnloadingRemarksRejection
 * Created		: 31.08.2010
 * Description	: contains message structure with fields in NCTSUnloadingRemarksRejection.
 * 
 * @author	: Michelle Bauza
 * @version	: 1.0.00
 *
 */
public class MsgNCTSUnloadingRemarksRejection extends KCXMessage {
	private String	messageName	= "NCTSUnloadingRemarksRejection";
	private String	referenceNumber;
	private String	uCRNumber;
	private String	arrivalRejectionDate;
	private String	reason;
	private List< FunctionalErrorNcts >	errorList;
	private EFormat	arrivalRejectionDateFormat;
	
	public MsgNCTSUnloadingRemarksRejection() {
		super();
	}
	
	public MsgNCTSUnloadingRemarksRejection(XMLEventReader parser) throws XMLStreamException {
		super(parser);
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
	public void setReferenceNumber(String refNo) {
		this.referenceNumber	= refNo;
	}
	
	public String getUCRNumber() {
		return this.uCRNumber;
	}
	public void setUCRNumber(String ucrNo) {
		this.uCRNumber	= ucrNo;
	}
	
	public String getArrivalRejectionDate() {
		return this.arrivalRejectionDate;
	}
	public void setArrivalRejectionDate(String arrivalRejDate) {
		this.arrivalRejectionDate	= arrivalRejDate;
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
	public void setErrorList(List< FunctionalErrorNcts > errList) {
		this.errorList	= errList;
	}
	
	public EFormat getArrivalRejectionDateFormat() {
		return this.arrivalRejectionDateFormat;
	}
	public void setArrivalRejectionDateFormat(EFormat arrRefDateFmt) {
		this.arrivalRejectionDateFormat	= arrRefDateFmt;
	}
	
	private enum EUnloadingRemarksRejection {
		// KIDS					// UIDS
		ReferenceNumber,		LocalReferenceNumber,
		UCRNumber,				MRN,
		ArrivalRejectionDate,	// same
		Reason,					ArrivalRejectionReason,
		Error,					FunctionalError
	}
	
	@Override
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
			switch((EUnloadingRemarksRejection) tag) {
				case Error:
				case FunctionalError:
						FunctionalErrorNcts errList	= 
							new FunctionalErrorNcts(getScanner());
							errList.parse(tag.name());
							addErrorList(errList);
					break;
				
				default:
					break;
			}
		} else {
			switch((EUnloadingRemarksRejection) tag) {
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
						if (value.indexOf('-') == -1) {
							setArrivalRejectionDateFormat(EFormat.KIDS_Date);
						} else {
							setArrivalRejectionDateFormat(EFormat.ST_Date);
						}
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
			return EUnloadingRemarksRejection.valueOf(token);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}
	
	/*
	 * void addErrorList(FunctionalErrorNcts _value)
	 * 		--- method that adds value(s) to the errorList
	 * 
	 * @param value	: value to be added
	 */
	public void addErrorList(FunctionalErrorNcts value) {
		if (this.errorList == null) {
			this.errorList	= new Vector< FunctionalErrorNcts >();
		}
		
		this.errorList.add(value);
	}

}

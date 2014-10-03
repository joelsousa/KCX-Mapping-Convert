package com.kewill.kcx.component.mapping.countries.de.ncts.msg;

import java.util.ArrayList;
import java.util.List;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;
import org.xml.sax.Attributes;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.FunctionalErrorNcts;
import com.kewill.kcx.component.mapping.util.EFormat;

/**
 * Module		: MsgNCTSDeclarationRejected<br>
 * Created		: 08.25.2010<br>
 * Description	: Contains Tag Mapping for fields used in NCTSDeclarationRejected message. 
 * 
 * @author Lassiter Caviles
 * @version 1.0.00
 */
public class MsgNCTSDeclarationRejected extends KCXMessage {
	
	private String			msgName = "NCTSDeclarationRejected";
	private String			referenceNumber;
	private String			ucrNumber;
	private String			typeOfDeclaration;
	private String			arrivalRejectionDate;
	private String			reason;
	private List<FunctionalErrorNcts>	errorList = new ArrayList<FunctionalErrorNcts>();
	
	private EFormat			arrivalRejectionDateFormat;
	
	public MsgNCTSDeclarationRejected() {
		super();
	}
	
	public MsgNCTSDeclarationRejected(XMLEventReader parser)throws XMLStreamException {
		super(parser);
	}
	
	public String getMsgName() {
		return msgName;
	}
	public void setMsgName(String msgName) {
		this.msgName = msgName;
	}
	
	public String getReferenceNumber() {
		return referenceNumber;
	}
	public void setReferenceNumber(String referenceNumber) {
		this.referenceNumber = referenceNumber;
	}
	
	public String getUcrNumber() {
		return ucrNumber;
	}
	public void setUcrNumber(String ucrNumber) {
		this.ucrNumber = ucrNumber;
	}
	
	public String getTypeOfDeclaration() {
		return typeOfDeclaration;
	}
	public void setTypeOfDeclaration(String typeOfDeclaration) {
		this.typeOfDeclaration = typeOfDeclaration;
	}
	
	public String getArrivalRejectionDate() {
		return arrivalRejectionDate;
	}
	public void setArrivalRejectionDate(String arrivalRejectionDate) {
		this.arrivalRejectionDate = arrivalRejectionDate;
	}
	
	public List<FunctionalErrorNcts> getErrorList() {
		return errorList;
	}
	public void setErrorList(List<FunctionalErrorNcts> errorList) {
		if (errorList == null) { return; }
		
		this.errorList = errorList;
	}
	
	public EFormat getArrivalRejectionDateFormat() {
		return arrivalRejectionDateFormat;
	}
	public void setArrivalRejectionDateFormat(EFormat arrivalRejectionDateFormat) {
		this.arrivalRejectionDateFormat = arrivalRejectionDateFormat;
	}
	
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	
	private enum ENCTSDeclarationRejected {
		//KIDS:							UIDS:
		ReferenceNumber,        		LocalReferenceNumber,
		UCRNumber,						MRN,
		TypeOfDeclaration,				//same
		ArrivalRejectionDate,			//same
		ArrivalRejectionReason,			Reason,
		Error,							FunctionalError;
		
	}
	
	public void startElement(Enum tag, String value, Attributes attr) {
		
		if (value == null) {
			switch((ENCTSDeclarationRejected) tag) {
			case Error:
			case FunctionalError:
				FunctionalErrorNcts functionalError = new FunctionalErrorNcts(getScanner());
				functionalError.parse(tag.name());
				errorList.add(functionalError);
				return;
			default:
				break;
			}
		} else {
			switch((ENCTSDeclarationRejected) tag) {
			case ReferenceNumber:
			case LocalReferenceNumber:
				setReferenceNumber(value);
				break;
			case UCRNumber:
			case MRN:
				setUcrNumber(value);
				break;
			case TypeOfDeclaration:
				setTypeOfDeclaration(value);
				break;
			case ArrivalRejectionDate:
				setArrivalRejectionDate(value);				
				if (value.indexOf('-') == -1) {
					setArrivalRejectionDateFormat(EFormat.KIDS_Date);
				} else {
					setArrivalRejectionDateFormat(EFormat.ST_Date);
				}
				break;	
			case ArrivalRejectionReason:
			case Reason:
				setReason(value);
				
			default:
				break;
			}
		}
	}

	public void stoppElement(Enum tag) {
		
	}
	
	public Enum translate(String token) {
		try {
			return ENCTSDeclarationRejected.valueOf(token);
		} catch (IllegalArgumentException e) {
		return null;
		}
	}

}

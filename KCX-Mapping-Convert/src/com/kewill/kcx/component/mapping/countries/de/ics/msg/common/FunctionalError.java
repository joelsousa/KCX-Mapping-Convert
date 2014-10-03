package com.kewill.kcx.component.mapping.countries.de.ics.msg.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: FunctionalError<br>
 * Created		: 2010.07.20<br>
 * Description	: FunctionalError tag in ICS.
 * 
 * @author Elbert Jude Eco
 * @version 1.0.00
 *
 */
public class FunctionalError extends KCXMessage {
	private String errorType;
	private String errorPointer;
	private String errorReason;
	private String originalAttributeValue;
	private String errorDescription;
	
	private boolean debug   = false;
	
	private enum EErrorArrivalItemRejection {
		//KIDS					//UIDS
		ErrorType,				Type,					
		ErrorPointer,			Pointer,				
		ErrorReason,			Reason,					
		OriginalAttributeValue,	OriginalValue,			
		ErrorDescription,		Description;			
	}
	
	public FunctionalError() {
		super();
	}
	
	public FunctionalError(XmlMsgScanner scanner) {
  		super(scanner);
  	}
	
  	public boolean isDebug() {
  		return debug;
  	}
  	
	public void setDebug(boolean debug) {
  		this.debug = debug;
  	}
	
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
			switch ((EErrorArrivalItemRejection) tag) {
			default:
				return;
			}
		} else {
			switch ((EErrorArrivalItemRejection) tag) {
			case ErrorType:
			case Type:
				setErrorType(value);
				break;
				
			case ErrorPointer:
			case Pointer:
				setErrorPointer(value);
				break;
				
			case ErrorReason:
			case Reason:
				setErrorReason(value);
				break;
				
			case OriginalAttributeValue:	
			case OriginalValue:
				setOriginalAttributeValue(value);
				break;
				
			case ErrorDescription:	
			case Description:
				setErrorDescription(value);
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
  			return EErrorArrivalItemRejection.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
  	}

	public void setErrorType(String errorType) {
		this.errorType = errorType;
	}

	public String getErrorType() {
		return errorType;
	}

	public void setErrorPointer(String errorPointer) {
		this.errorPointer = errorPointer;
	}

	public String getErrorPointer() {
		return errorPointer;
	}

	public void setErrorReason(String errorReason) {
		this.errorReason = errorReason;
	}

	public String getErrorReason() {
		return errorReason;
	}

	public void setOriginalAttributeValue(String originalAttributeValue) {
		this.originalAttributeValue = originalAttributeValue;
	}

	public String getOriginalAttributeValue() {
		return originalAttributeValue;
	}

	public void setErrorDescription(String errorDescription) {
		this.errorDescription = errorDescription;
	}

	public String getErrorDescription() {
		return errorDescription;
	}

	
}

package com.kewill.kcx.component.mapping.countries.de.emcs.msg.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: EMCS.<br>
 * Created		: 12.05.2010<br>
 * Description	: Fields and methods save tags from KIDS CT_FunctionalError, 
 *              : and UIDS FunctionalErrorType and CT_Error 
 * 
 * @author iwaniuk
 * @version 1.0.00
 */

public class FunctionalError extends KCXMessage {

	private String errorType;
	private String errorReason;
	private String errorLocation;
	private String originalAttributeValue;
	
	private enum EFunctionalError {
    //KIDS : 						//KIDS:					//UIDS:                
	//CT_FunctionalError            //CT_Error:             //FunctionalErrorType: 
    //and used in ST_FunctionalError		           
	ErrorType,	               		Type,					//and ErrorType			
	ErrorReason,                	Reason,		    		//and ErrorReason     
	ErrorLocation,	ErrorPointer, 	Pointer,       			//and ErrorPointer
	OriginalAttributeValue,			Value, OriginalValue; 	//and OriginalAttributeValue  
	
	}
	
	public FunctionalError() {
  		super();  		
  	}
	 
	public FunctionalError(XmlMsgScanner scanner) {
  		super(scanner);  		
  	}
	
	public void startElement(Enum tag, String value, Attributes attr) {
	    if (value == null) {
			switch ((EFunctionalError) tag) {
			default:
				return;
			}
	    } else {
	    	switch ((EFunctionalError) tag) {
	    	case ErrorType:
	    	case Type:
	    		 setErrorType(value);
				 break;
			case ErrorReason:
			case Reason:
				 setErrorReason(value);
				 break;	
			case ErrorPointer:
			case Pointer:
			case ErrorLocation:
				 setErrorLocation(value);
				 break;								
			case OriginalAttributeValue:
			case OriginalValue: 			 //EI20100625
			case Value:
				 setOriginalAttributeValue(value);
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
  			return EFunctionalError.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
	}

	public String getErrorType() {
		return errorType;
	
	}

	public void setErrorType(String errorType) {
		this.errorType = Utils.checkNull(errorType);
	}

	public String getErrorReason() {
		return errorReason;
	
	}

	public void setErrorReason(String errorReason) {
		this.errorReason = Utils.checkNull(errorReason);
	}

	public String getErrorLocation() {
		return errorLocation;
	
	}

	public void setErrorLocation(String errorLocation) {
		this.errorLocation = Utils.checkNull(errorLocation);
	}

	public String getOriginalAttributeValue() {
		return originalAttributeValue;
	
	}

	public void setOriginalAttributeValue(String originalAttributeValue) {
		this.originalAttributeValue = Utils.checkNull(originalAttributeValue);
	}
	
	public boolean isEmpty() {
		
		return (Utils.isStringEmpty(this.errorType) &&			
    		Utils.isStringEmpty(this.errorReason) &&
    		Utils.isStringEmpty(this.errorLocation) &&
    		Utils.isStringEmpty(this.originalAttributeValue));		   
	}
}

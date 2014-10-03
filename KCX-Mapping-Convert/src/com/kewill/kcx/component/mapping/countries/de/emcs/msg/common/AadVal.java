package com.kewill.kcx.component.mapping.countries.de.emcs.msg.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module       : EMCS<br>
 * Created		: 28.05.2010<br>
 * Description  : UIDS: used in EMCSRejectionOfEAADForExport
 *              : KIDS: is equals ST_FunctionalError used in MsgExportRejection.         
 * 
 * @author iwaniuk
 * @version 1.0.00
 */

public class AadVal extends KCXMessage {

	private String aadReferenceCode;			
    private String sequenceNumber;
    private FunctionalError functionalError;   //UIDS: FunctionalErrorType, 
    
	private enum EAadVal {	
		//KIDS:                  UIDS :
		AadReferenceCode,		//same	        
		SequenceNumber,    		//same 	        		
		ErrorType,              FunctionalError, //.ErrorType
		ErrorPointer,			//FunctionalError.ErrorPointer
		OriginalAttributeValue, //FunctionalError.OriginalAttributeValue
		ErrorReason;            //FunctionalError.ErrorReason
   }	
	
	public AadVal() {
  		super();
  	}	
	 
	public AadVal(XmlMsgScanner scanner) {
  		super(scanner);
  	}

	public void startElement(Enum tag, String value, Attributes attr) {
  		if (value == null) {
  			switch ((EAadVal) tag) {  			
				case FunctionalError:
					functionalError = new FunctionalError(getScanner());
					functionalError.parse(tag.name());					
					break;
								
				default:
  					return;
  			}
  		} else {
  			switch ((EAadVal) tag) {   
  				case AadReferenceCode:
  					setAadReferenceCode(value);
					break;   			
  				case SequenceNumber:
  					setSequenceNumber(value);
  					break;   
  				case ErrorType:
  					setFunctionalErrorTag("ErrorType", value);
					break;   			
  				case ErrorPointer:
  					setFunctionalErrorTag("ErrorPointer", value);
  					break;  
  				case OriginalAttributeValue:
  					setFunctionalErrorTag("OriginalAttributeValue", value);
					break;   			
  				case ErrorReason:
  					setFunctionalErrorTag("ErrorReason", value);
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
  			return EAadVal.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
	}

	public String getAadReferenceCode() {
		return this.aadReferenceCode;
	}
	public void setAadReferenceCode(String argument) {
		this.aadReferenceCode = argument;
	}

	public String getSequenceNumber() {
		return sequenceNumber;
	}
	public void setSequenceNumber(String argument) {
		this.sequenceNumber = argument;
	}

	public FunctionalError getFunctionalError() {
		return this.functionalError;
	}
	public void setFunctionalError(FunctionalError argument) {
		this.functionalError = argument;
	}
	
	public void setFunctionalErrorTag(String tagName, String tagValue) {
		if (functionalError == null) {
			functionalError = new FunctionalError();
		}
		if (tagName.equals("ErrorType")) {
			functionalError.setErrorType(tagValue);
		} else if (tagName.equals("ErrorPointer")) {
			functionalError.setErrorLocation(tagValue);
		} else if (tagName.equals("OriginalAttributeValue")) {
			functionalError.setOriginalAttributeValue(tagValue);
		} else if (tagName.equals("ErrorReason")) {
			functionalError.setErrorReason(tagValue);
		} else {
			return;	
		}
	}
	
	public boolean isEmpty() {		
	
	return (Utils.isStringEmpty(this.aadReferenceCode) &&
			Utils.isStringEmpty(this.sequenceNumber) &&
    		(functionalError == null));		
	}
}
   

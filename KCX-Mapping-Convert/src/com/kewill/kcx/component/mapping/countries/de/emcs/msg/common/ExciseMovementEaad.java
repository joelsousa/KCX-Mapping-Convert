package com.kewill.kcx.component.mapping.countries.de.emcs.msg.common;

import org.xml.sax.Attributes;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module       : EMCS<br>
 * Created		: 05.05.2010<br>
 * Description  : Contains the Member for save tags from the KIDS/UIDS message   
 * 				: for ExciseMovementEaad and ExciseMovementEaadType.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */

public class ExciseMovementEaad extends KCXMessage {
	
	private String aadReferenceCode;	
	private String dateAndTimeOfValidationOfEaad;
	private String sequenceNumber;
	
	private enum EExciseMovementEaad {
		//KIDS:								UIDS:
		SequenceNumber,						//same
		AadReferenceCode,       			//same   								
		DateAndTimeOfValidationOfEaad, DateAndTimeOfValidation; 		
	}

	public ExciseMovementEaad() {
  		super();
  	}
	 
	public ExciseMovementEaad(XmlMsgScanner scanner) {
  		super(scanner);
  	}
	
	public void startElement(Enum tag, String value, Attributes attr) {
	    if (value == null) {
			switch ((EExciseMovementEaad) tag) {
			default:
				return;
			}
	    } else {
	    	switch ((EExciseMovementEaad) tag) {
	    	case SequenceNumber:
	    		 setSequenceNumber(value);
				 break;
			case AadReferenceCode:			
				 setAadReferenceCode(value);
				 break;					 				
			case DateAndTimeOfValidationOfEaad:
			case DateAndTimeOfValidation:
				 setDateAndTimeOfValidationOfEaad(value);
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
  			return EExciseMovementEaad.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
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
		
	public String getDateAndTimeOfValidationOfEaad() {
		return this.dateAndTimeOfValidationOfEaad;
	}
	
	public void setDateAndTimeOfValidationOfEaad(String argument) {
		this.dateAndTimeOfValidationOfEaad = argument;
	}	
	
	public boolean isEmpty() {
		
		return (Utils.isStringEmpty(this.sequenceNumber) &&
			Utils.isStringEmpty(this.aadReferenceCode) &&			
    		Utils.isStringEmpty(this.dateAndTimeOfValidationOfEaad));			
	}	
}

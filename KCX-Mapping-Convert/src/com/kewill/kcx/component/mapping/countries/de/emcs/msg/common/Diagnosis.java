package com.kewill.kcx.component.mapping.countries.de.emcs.msg.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module       : EMCS<br>
 * Created		: 12.05.2010.<br>
 * Description  : Diagnosis                 
 * 
 * @author iwaniuk
 * @version 1.0.00
 */

public class Diagnosis extends KCXMessage {

	private String aadReferenceCode;
	private String itemNumber;
	private String diagnosisCode;
	
	private enum EDiagnosis {
		//KIDS:                     //UIDS:
		AadReferenceCode,			//same,
		ItemNumber,					BodyRecordUniqueReference,
		DiagnosisCode;		        //same
	}
			 
	public Diagnosis(XmlMsgScanner scanner) {
		super(scanner);
	}			

	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
  			switch ((EDiagnosis) tag) {	
				default:
  					return;
  			}
  		} else {
			switch ((EDiagnosis) tag) {
			case AadReferenceCode:
				setAadReferenceCode(value);
				break;
			case ItemNumber:
			case BodyRecordUniqueReference:
				setItemNumber(value);
				break;
			case DiagnosisCode:
				setDiagnosisCode(value);
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
  			return EDiagnosis.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
	}
	
	public void setAadReferenceCode(String argument) {
		this.aadReferenceCode = argument;	
	}
	public String getAadReferenceCode() {
		return this.aadReferenceCode;	
	}	
	
	public void setItemNumber(String argument) {
		this.itemNumber = argument;
	}
	public String getItemNumber() {
		return this.itemNumber;	
	}
	
	public void setDiagnosisCode(String argument) {
		this.diagnosisCode = argument;	
	}
	public String getDiagnosisCode() {
		return this.diagnosisCode;	
	}
	
	public boolean isEmpty() {
		
		return (Utils.isStringEmpty(this.aadReferenceCode) &&	
			Utils.isStringEmpty(this.itemNumber) &&
    		Utils.isStringEmpty(this.diagnosisCode));
		
	}	
}

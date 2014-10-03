package com.kewill.kcx.component.mapping.countries.de.Import.msg.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: Import<br>
 * Created		: 14.09.2011<br>
 * Description	: Contains the Document Data with all Fields used in KIDS.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class Document extends KCXMessage {

	private String type;
    private String number;    
    private String issueDate; 
    private String codeExists;
    private String writeOffAmountValue;        
    private String writeOffMeasuringUnit;
    private String writeOffQualifierMeasuringUnit; 
    private String additionalInformation;           //EI20121030 CH V70
    
	private enum EImportDocument {
		Type,				
		Number,					
		IssueDate,		
		CodeExists,
		WriteOffAmountValue, 
		WriteOffMeasuringUnit,
		WriteOffQualifierMeasuringUnit,
		AdditionalInformation;
    }
	
	public Document() {
		super();  
	}

    public Document(XmlMsgScanner scanner) {
  		super(scanner);
  	}  	
 
  	public void startElement(Enum tag, String value, Attributes attr) {
  		if (value == null) {
  			switch ((EImportDocument) tag) {
  			default:
  					return;
  			}
  		} else {

  			switch ((EImportDocument) tag) {
  				case Type:
  					setType(value);
  					break;
  				case Number:
  	  				setNumber(value);
  	  				break;  					  								
  				case IssueDate:  				
  					setIssueDate(value);
  					break;
  				case CodeExists:
  					setCodeExists(value);
  					break;	
  				case WriteOffAmountValue:
  					setWriteOffAmountValue(value);
  					break;	
  				case WriteOffMeasuringUnit:
  					setWriteOffMeasuringUnit(value);
  					break;
  				case WriteOffQualifierMeasuringUnit:
  					setWriteOffQualifierMeasuringUnit(value);
  					break;
  				case AdditionalInformation:
  					setAdditionalInformation(value);
  					break;
  				default: break;
  			}
  		}
  	}

  	public void stoppElement(Enum tag) {
  	}

  	public Enum translate(String token) {
  		try {
  			return EImportDocument.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
  	}

	public String getType() {
		return type;
	}
	public void setType(String argument) {
		type = argument;
	}

	public String getNumber() {
		return number;
	}
	public void setNumber(String value) {
		this.number = value;
	}
	
	public String getIssueDate() {
		return issueDate;
	}
	public void setIssueDate(String argument) {
		issueDate = argument;
	}
	
	public void setCodeExists(String argument) {
		this.codeExists = argument;
	}			
	public String getCodeExists() {
		return this.codeExists;
	}
	
	public void setWriteOffAmountValue(String argument) {
		this.writeOffAmountValue = argument;
	}			
	public String getWriteOffAmountValue() {
		return this.writeOffAmountValue;
	}
	
	public void setWriteOffMeasuringUnit(String argument) {
		this.writeOffMeasuringUnit = argument;
	}			
	public String getWriteOffMeasuringUnit() {
		return this.writeOffMeasuringUnit;
	}
	
	public void setWriteOffQualifierMeasuringUnit(String argument) {
		this.writeOffQualifierMeasuringUnit = argument;
	}			
	public String getWriteOffQualifierMeasuringUnit() {
		return this.writeOffQualifierMeasuringUnit;
	}
	
	public void setAdditionalInformation(String argument) {
		this.additionalInformation = argument;
	}			
	public String getAdditionalInformation() {
		return this.additionalInformation;
	}
	 	
	public boolean isEmpty() {
		return (Utils.isStringEmpty(this.issueDate) && Utils.isStringEmpty(this.number) && 
		        Utils.isStringEmpty(this.type) && Utils.isStringEmpty(this.codeExists) && 		        
		        Utils.isStringEmpty(this.writeOffAmountValue));
	}    	
}

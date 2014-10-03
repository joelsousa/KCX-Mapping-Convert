package com.kewill.kcx.component.mapping.formats.kff.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;


/**
 * Module		: Port<br>
 * Created		: 24.10.2011<br>
 * Description	: MRNs
 * 
 * @author iwaniuk
 * @version 1.0.00
 */

public class RefNo extends KCXMessage {

	 private String refNoSNO;       
	 private String refCode;    
	 private String refNumber;       
	 private String refNoRemark;     
	 private String refNoIssueDate;       
	 private String refNoExpiryDate;     
	
	 public RefNo() {
		 super();  
	 }

	 public RefNo(XmlMsgScanner scanner) {
 		super(scanner);
	 }

	 private enum EDocumentFormat {				
		 RefNoSNO,	
		 RefCode,
		 RefNumber,
		 RefNoRemark,
		 RefNoIssueDate,
		 RefNoExpiryDate;							 			        		
	 }	 
	 
	 public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {		
			switch ((EDocumentFormat) tag) {
				default: return;			
			}
		} else {			
			switch ((EDocumentFormat) tag) {			
				case RefNoSNO:
					setRefNoSNO(value);
					break;
				case RefCode:
					setRefCode(value);
					break;	
				case RefNumber:
					setRefNumber(value);
					break;
				case RefNoRemark:
					setRefNoRemark(value);
					break;
				case RefNoIssueDate:
					setRefNoIssueDate(value);
					break;					
				case RefNoExpiryDate:
					setRefNoExpiryDate(value);
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
			return EDocumentFormat.valueOf(token);
		 } catch (IllegalArgumentException e) {
			return null;
		 }
	 }

    public String getRefNoSNO() {
		return refNoSNO;
	}
	public void setRefNoSNO(String argument) {
		this.refNoSNO = argument;
	}					
		
	public String getRefCode() {
		return refCode;
	}
	public void setRefCode(String argument) {
		this.refCode = argument;
	}		
	
	public String getRefNoRemark() {
		return refNoRemark;
	}
	public void setRefNoRemark(String argument) {
		this.refNoRemark = argument;
	}					
		
	public String getRefNumber() {
		return refNumber;
	}
	public void setRefNumber(String argument) {
		this.refNumber = argument;
	}	
	
	public String getRefNoIssueDate() {
		return refNoIssueDate;
	}
	public void setRefNoIssueDate(String argument) {
		this.refNoIssueDate = argument;
	}					
		
	public String getRefNoExpiryDate() {
		return refNoExpiryDate;
	}
	public void setRefNoExpiryDate(String argument) {
		this.refNoExpiryDate = argument;
	}	
}

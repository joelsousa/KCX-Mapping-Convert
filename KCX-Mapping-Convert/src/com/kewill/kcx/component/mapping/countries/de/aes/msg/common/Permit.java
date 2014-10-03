package com.kewill.kcx.component.mapping.countries.de.aes.msg.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: Export/aes<br>
 * Created		: 10.09.2008<br>
 * Description	: Contains the Permit Data with all Fields used in KIDS.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class Permit extends KCXMessage {

	// KIDS: permit, UIDS-CH: Allowance
	private String permitAuthority;
	private String permitNumber;
	private String issueDate;
	private String additionalInformation;
	private String type;                   //EI20120711 new for V20
	private String permitObligation;       //EI20120711 new for V20
	private String tobaccoPermitType;      //Ei20121030 CH V20
		
	private enum EPermitTags {
			Type,	
		 	PermitAuthority,		Category,		
		 	PermitNumber,			Reference,
		 	PermitObligation,
			IssueDate,				Date,														
			TobaccoPermitType,      //CH
			AdditionalInformation,	Remark,	
		}
	 	 
	
	 public Permit(XmlMsgScanner scanner) {
	  		super(scanner);
	 }
	 
	 public Permit() {  
			super();			
	 }
	 
	 public void startElement(Enum tag, String value, Attributes attr) {
			if (value == null) {			
				switch ((EPermitTags) tag) {
				default:
						return;
				}
			} else {				
				switch ((EPermitTags) tag) {			
					case PermitAuthority:
					case Category:
						setPermitAuthority(value);
						break;					
					case PermitNumber:
					case Reference:
						setPermitNumber(value);
						break;					
					case IssueDate:
					case Date:
						setIssueDate(value);
						break;					
					case AdditionalInformation:
					case Remark:
						setAdditionalInformation(value);
						break;
					case Type:
						setType(value);
						break;	
					case PermitObligation:
						setPermitObligation(value);
						break;
					case TobaccoPermitType:
						setTobaccoPermitType(value);
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
				return EPermitTags.valueOf(token);
			} catch (IllegalArgumentException e) {
				return null;
			}
	}

	public String getPermitAuthority() {
		return permitAuthority;
	}
	public void setPermitAuthority(String permitAuthority) {
		this.permitAuthority = permitAuthority;
	}

	public String getPermitNumber() {
		return permitNumber;
	}
	public void setPermitNumber(String permitNumber) {
		this.permitNumber = permitNumber;
	}

	public String getIssueDate() {
		return issueDate;
	}
	public void setIssueDate(String issueDate) {
		this.issueDate = issueDate;
	}

	public String getAdditionalInformation() {
		return additionalInformation;
	}
	public void setAdditionalInformation(String additionalInformation) {
		this.additionalInformation = additionalInformation;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String value) {
		this.type = value;
	}
		
	public String getPermitObligation() {
		return permitObligation;
	}
	public void setPermitObligation(String value) {
		this.permitObligation = value;
	}
	
	public String getTobaccoPermitType() {
		return tobaccoPermitType;
	}
	public void setTobaccoPermitType(String value) {
		this.tobaccoPermitType = value;
	}
	
	public boolean isEmpty() {
		return (Utils.isStringEmpty(permitNumber) &&  Utils.isStringEmpty(permitAuthority) &&  
		        Utils.isStringEmpty(issueDate) && Utils.isStringEmpty(additionalInformation) &&
		        Utils.isStringEmpty(type) && Utils.isStringEmpty(tobaccoPermitType));       
	}
	
}

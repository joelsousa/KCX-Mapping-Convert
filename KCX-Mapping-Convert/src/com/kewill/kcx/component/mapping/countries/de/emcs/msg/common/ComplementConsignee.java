package com.kewill.kcx.component.mapping.countries.de.emcs.msg.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Function     : EMCS<br>
 * Created		: 30.04.2010<br>
 * Description  : Contains the Member for save the KIDS- and UIDS-tags.                 
 * 
 * @author krzoska
 * @version 1.0.00
 */

public class ComplementConsignee extends KCXMessage {

	private String memberStateCode;
	private String certificateOfExemption;
	
	private enum EComplementConsignee {
		//KIDS:                     //UIDS:
		MemberStateCode,			//same
		CertificateOfExemption;		//same
	}
			 
	public ComplementConsignee(XmlMsgScanner scanner) {
		super(scanner);
	}
			   	
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
  			switch ((EComplementConsignee) tag) {	
				default:
  					return;
  			}
  		} else {
			switch ((EComplementConsignee) tag) {
			case MemberStateCode:
				setMemberStateCode(value);
				break;
			case CertificateOfExemption:
				setCertificateOfExemption(value);
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
  			return EComplementConsignee.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
	}

	public String getMemberStateCode() {
		return memberStateCode;	
	}
	public void setMemberStateCode(String memberStateCode) {
		this.memberStateCode = Utils.checkNull(memberStateCode);
	}

	public String getCertificateOfExemption() {
		return certificateOfExemption;	
	}
	public void setCertificateOfExemption(String certificateOfExemption) {
		this.certificateOfExemption = Utils.checkNull(certificateOfExemption);
	}

	public boolean isEmpty() {
		
		return (Utils.isStringEmpty(this.memberStateCode) &&		
    		Utils.isStringEmpty(this.certificateOfExemption));		
	}	
}

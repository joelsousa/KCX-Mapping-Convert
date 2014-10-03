package com.kewill.kcx.component.mapping.countries.de.emcs.msg.common;

import org.xml.sax.Attributes;
import com.kewill.kcx.component.mapping.countries.common.Text;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Function     : EMCS<br>
 * Created		: 21.07.2011<br>
 * Description  : Contains the Member for save the KIDS- and UIDS-tags.                 
 * 
 * @author iwaniuk
 * @version 1.0.00
 */

public class EvidenceOfEvent extends KCXMessage {

	private String code;
	private Text authority;
	private Text reference;
	private Text complement;
			
	public EvidenceOfEvent() {
  		super();
  	}
	
	public EvidenceOfEvent(XmlMsgScanner scanner) {
		super(scanner);
	}
	
	private enum EEvidenceOfEvent {
		//KIDS == UIDS: 
		EvidenceTypeCode, 
		IssuingAuthority,
		ReferenceOfEvidence,
		EvidenceTypeComplement;																
	}
	
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
  			switch ((EEvidenceOfEvent) tag) {	
				default:
  					return;
  			}
  		} else {
			switch ((EEvidenceOfEvent) tag) {
			case EvidenceTypeCode:
				setEvidenceTypeCode(value);
				break;
			case IssuingAuthority:
				//authority = new Text(value, attr.getValue("language"));
				authority = new Text(value, attr);  //EI20110926
				break;
			case ReferenceOfEvidence:				
				//reference = new Text(value, attr.getValue("language"));
				reference = new Text(value, attr);  //EI20110926
				break;	
			case EvidenceTypeComplement:
				//complement = new Text(value, attr.getValue("language"));
				complement = new Text(value, attr);  //EI20110926
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
  			return EEvidenceOfEvent.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
	}

	
	public String getEvidenceTypeCode() {
		return code;	
	}
	public void setEvidenceTypeCode(String value) {
		code = Utils.checkNull(value);
	}
	
	public Text getIssuingAuthority() {
		return authority;	
	}
	public void setIssuingAuthority(Text text) {
		this.authority = text;
	}

	public Text getReferenceOfEvidence() {
		return reference;	
	}
	public void setReferenceOfEvidence(Text text) {
		reference = text;
	}
	
	public Text getEvidenceTypeComplement() {
		return complement;	
	}
	public void setEvidenceTypeComplement(Text text) {
		this.complement = text;
	}
		
	public boolean isEmpty() {		
		return (Utils.isStringEmpty(this.code) &&
				(authority == null) &&	
				(reference == null) &&
    	        (complement == null));		
	}	
}


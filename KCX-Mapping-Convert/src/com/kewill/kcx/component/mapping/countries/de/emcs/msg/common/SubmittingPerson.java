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

public class SubmittingPerson extends KCXMessage {

	private String code;
	private String name;
	private Text complement;
			
	public SubmittingPerson() {
  		super();
  	}
	
	public SubmittingPerson(XmlMsgScanner scanner) {
		super(scanner);
	}
	
	private enum EPerson {
		//KIDS == UIDS: 
		SubmittingPersonCode, 
		SubmittingPersonName,
		SubmittingPersonComplement;																
	}
	
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
  			switch ((EPerson) tag) {	
				default:
  					return;
  			}
  		} else {
			switch ((EPerson) tag) {
			case SubmittingPersonCode:
				setSubmittingPersonCode(value);
				break;
			case SubmittingPersonName:
				setSubmittingPersonName(value);
				break;
			case SubmittingPersonComplement:				
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
  			return EPerson.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
	}

	public String getSubmittingPersonCode() {
		return code;	
	}
	public void setSubmittingPersonCode(String value) {
		this.code = Utils.checkNull(value);
	}

	public String getSubmittingPersonName() {
		return name;	
	}
	public void setSubmittingPersonName(String value) {
		this.name = Utils.checkNull(value);
	}
	
	public Text getSubmittingPersonComplement() {
		return complement;	
	}
	public void setSubmittingPersonComplement(Text text) {
		this.complement = text;
	}

	public boolean isEmpty() {		
		return (Utils.isStringEmpty(this.code) &&		
    		Utils.isStringEmpty(this.name));		
	}	
}


package com.kewill.kcx.component.mapping.countries.de.emcs.msg.common;

import java.util.List;
import java.util.Vector;

import org.xml.sax.Attributes;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: EMCS.<br>
 * Date			: 05.05.2010<br>
 * Description  : Contains the Member for save tags from the UIDS message   
 * 
 * @author iwaniuk
 * @version 1.0.00
 */

public class MovementGuarantee extends KCXMessage {

	private String guarantorTypeCode;	
	private List <EmcsTrader>guarantorList;
	
	private enum EMovementGuarantee {
		//KIDS:					UIDS:
								GuarantorTypeCode,
								GuarantorTrader;        									
	}
	
	public MovementGuarantee() {
  		super();  		
  	}
	 
	public MovementGuarantee(XmlMsgScanner scanner) {
  		super(scanner); 	
  	}
	
	public void startElement(Enum tag, String value, Attributes attr) {
	    if (value == null) {
			switch ((EMovementGuarantee) tag) {
			case GuarantorTrader:
				EmcsTrader guarantor = new EmcsTrader(getScanner());
				guarantor.parse(tag.name());
				addGuarantorList(guarantor);
				break;
			default:
				return;
			}
	    } else {
	    	switch ((EMovementGuarantee) tag) {
	    	case GuarantorTypeCode:
	    		 setGuarantorTypeCode(value);
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
  			return EMovementGuarantee.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
	}
	
	public String getGuarantorTypeCode() {
		return this.guarantorTypeCode;
	}
	public void setGuarantorTypeCode(String argument) {
		this.guarantorTypeCode = argument;
	}
	
	public List<EmcsTrader> getGuarantorList() {
		return this.guarantorList;
	}
	public void setGuarantorList(List<EmcsTrader> argument) {
		this.guarantorList = argument;
	}
	public void addGuarantorList(EmcsTrader argument) {
		if (guarantorList == null) {
			guarantorList = new Vector<EmcsTrader>();
		}
		this.guarantorList.add(argument);
	}	
	
	public boolean isEmpty() {
	
		int listSize = 0;
		if (this.guarantorList != null) {
			listSize = this.guarantorList.size();
		}
		
		return (Utils.isStringEmpty(this.guarantorTypeCode) && (listSize == 0));    				
	}	
}

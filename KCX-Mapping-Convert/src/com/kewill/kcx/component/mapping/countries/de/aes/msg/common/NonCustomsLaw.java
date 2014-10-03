package com.kewill.kcx.component.mapping.countries.de.aes.msg.common;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: Export/aes<br>
 * Created		: 10.09.2008<br>
 * Description	: Contains the NonCustomsLaw Data with all Fields used in KIDS.
 * 
 * @author Krzoska
 * @version 1.0.00
 */
public class NonCustomsLaw extends KCXMessage {

	 private List<String>  nonCustomsLawTypeList;  //UIDS: SpecialRemarks.RestrictionCode
	 private String  obligation;
	 private String  code;       //EI20130305 CH V20
	 
	 private enum ENonCustomsLawTags {
		 NonCustomsLawType,    RestrictionCode,   Type, //Type for Kids V2.1
		 Obligation,
		 Code;
		}
	 
	 public NonCustomsLaw() {
			super();			
	 }	 
	 public NonCustomsLaw(XmlMsgScanner scanner) {
	  		super(scanner);
	 }
	 
	 public void startElement(Enum tag, String value, Attributes attr) {
			if (value == null) {			
				switch ((ENonCustomsLawTags) tag) {
				default:
						return;
				}
			} else {			
				switch ((ENonCustomsLawTags) tag) {			
					case NonCustomsLawType:
					case RestrictionCode:
					case Type:
						addNonCustomsLawTypeList(value);
						break;
					case Obligation:
						setObligation(value);
						break;
					case Code:
						setCode(value);
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
				return ENonCustomsLawTags.valueOf(token);
			} catch (IllegalArgumentException e) {
				return null;
			}
	}

	public List<String> getNonCustomsLawType() {
		return nonCustomsLawTypeList;
	}
	public List<String> getNonCustomsLawTypeList() {
		return nonCustomsLawTypeList;
	}	
	public void NonCustomsLawTypeList(List<String> list) {
		this.nonCustomsLawTypeList = list;
	}	
	public void addNonCustomsLawTypeList(String argument) {
		if (this.nonCustomsLawTypeList == null) {
			nonCustomsLawTypeList = new ArrayList<String>();
		}
		this.nonCustomsLawTypeList.add(argument);
	}	

	public String getObligation() {
		return obligation;	
	}
	public void setObligation(String obligation) {
		this.obligation = Utils.checkNull(obligation);
	}

	public String getCode() {
		return code;	
	}
	public void setCode(String value) {
		this.code = Utils.checkNull(value);
	}

	
	public boolean isEmpty() {
		return (Utils.isStringEmpty(obligation) && Utils.isStringEmpty(code) &&				
				nonCustomsLawTypeList == null); 
	}
}

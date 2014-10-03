package com.kewill.kcx.component.mapping.countries.de.Import.msg.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: Import<br>
 * Created		: 14.09.2011<br>
 * Description	: Contains the Salary Data with all Fields used in KIDS.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class Salary extends KCXMessage {
      
	 private String   type;       
	 private String   rateOrPercent;
	 
	 private enum ESalary {
		// Kids-TagNames, 			UIDS-TagNames
		 Type,		   			
		 RateOrPercent;
	 }
	 
	
	 public Salary() {
			super();
	 }	     
 
	 public Salary(XmlMsgScanner scanner) {
			super(scanner);
	 }	 
	 
	 public void startElement(Enum tag, String value, Attributes attr) {
			if (value == null) {			
				switch ((ESalary) tag) {
				
				default:
						return;
				}
			} else {				
				switch ((ESalary) tag) {
					case Type:
						setType(value);
						break;
						
					case RateOrPercent:
						setRateOrPercent(value);
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
				return ESalary.valueOf(token);
			} catch (IllegalArgumentException e) {
				return null;
			}
	}

	public String getRateOrPercent() {
		return rateOrPercent;
	}
	public void setRateOrPercent(String value) {
		this.rateOrPercent = value;
	}
	public String getType() {
		return type;
	}
	public void setType(String value) {
		this.type = value;
	}
	
	public boolean isEmpty() {
		return (Utils.isStringEmpty(this.type) && 
		        Utils.isStringEmpty(this.rateOrPercent));
	}	
}

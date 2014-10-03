package com.kewill.kcx.component.mapping.countries.de.Import.msg.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: Import<br>
 * Created		: 14.09.2011<br>
 * Description	: Contains the SpecialStatement Data with all Fields used in KIDS.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class SpecialStatement extends KCXMessage {
      
	 private String   type;       
	 private String   value;
	 private String   group;
	 
	 private enum ESpecialStatement {
		// Kids-TagNames, 			UIDS-TagNames
		 Type,		   			
	     Value,
	     Group;
	 }
	 	 
	 public SpecialStatement() {
			super();
	 }	 
    
	 public SpecialStatement(XmlMsgScanner scanner) {
			super(scanner);
	 }	 
	 
	 public void startElement(Enum tag, String value, Attributes attr) {
			if (value == null) {			
				switch ((ESpecialStatement) tag) {
				
				default:
						return;
				}
			} else {				
				switch ((ESpecialStatement) tag) {
					case Type:
						setType(value);
						break;						
					case Value:
						setValue(value);
						break;
					case Group:
						setGroup(value);
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
				return ESpecialStatement.valueOf(token);
			} catch (IllegalArgumentException e) {
				return null;
			}
	}

	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String value) {
		this.type = value;
	}
	
	public String getGroup() {
		return group;
	}
	public void setGroup(String value) {
		this.group = value;
	}
	public boolean isEmpty() {
		return (Utils.isStringEmpty(this.type) && 
				Utils.isStringEmpty(this.value) &&
		        Utils.isStringEmpty(this.group));
	}	
}

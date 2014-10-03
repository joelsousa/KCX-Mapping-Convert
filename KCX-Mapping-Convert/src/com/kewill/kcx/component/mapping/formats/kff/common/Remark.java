package com.kewill.kcx.component.mapping.formats.kff.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

public class Remark extends KCXMessage {

	private String remarkSNO;         
	private String remarkType;                  
	private String remarkText;      
	
	 public Remark() {
		 super();  
	 }

	 public Remark(XmlMsgScanner scanner) {
		super(scanner);
	 }

	 private enum ERemark {	
			// JobFormat-TagNames
		 RemarkSNO,					
		 RemarkType,					
		 RemarkText;			        				
	 }	 
	 
	 public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {		
			switch ((ERemark) tag) {
			default: 
					return;			
			}
		} else {			
			switch ((ERemark) tag) {			
				case RemarkSNO:
					setRemarkSNO(value);
					break;				
				case RemarkType:
					setRemarkType(value);
					break;				
				case RemarkText:
					setRemarkText(value);
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
			return ERemark.valueOf(token);
		 } catch (IllegalArgumentException e) {
			return null;
		 }
	 }

	public String getRemarkSNO() {
		return remarkSNO;
	}

	public void setRemarkSNO(String remarkSNO) {
		this.remarkSNO = Utils.checkNull(remarkSNO);
	}

	public String getRemarkType() {
		return remarkType;
	}

	public void setRemarkType(String remarkType) {
		this.remarkType = Utils.checkNull(remarkType);
	}

	public String getRemarkText() {
		return remarkText;
	}

	public void setRemarkText(String remarkText) {
		this.remarkText = Utils.checkNull(remarkText);
	}


}

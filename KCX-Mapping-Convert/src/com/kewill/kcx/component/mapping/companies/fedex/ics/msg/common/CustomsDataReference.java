package com.kewill.kcx.component.mapping.companies.fedex.ics.msg.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/*
* Function    : CustomsDataReference
* Titel       :
* Date        : 07.01.2011
* Author      : krzoska
* Description : CustomsDataReference  
* Parameters  :

* Changes
* ------------
* Author      : 
* Date        : 
* Label       : 
* Description : 
*
*/

public class CustomsDataReference extends KCXMessage{

	private boolean debug = false;
	private String  mrn;
	
	public CustomsDataReference() {
		super();
	}
	
	public CustomsDataReference(XmlMsgScanner scanner) {
		super(scanner);
 	}

	private enum ECustomsDataReference {
		DocNumHEA5;
	}
	
	private CustomsDataReference customsDataReference = null;
	
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
		
			switch ((ECustomsDataReference) tag) {
			
			default: 
				return;
			}
		} else {
			switch ((ECustomsDataReference) tag) {
			case DocNumHEA5: setMrn(value);
			break;
			default:
					return;
			}
		}
	}
	
	public void stoppElement(Enum tag) {
		// TODO Auto-generated method stub
		
	}
	
	public Enum translate(String token) {
		try {
			return ECustomsDataReference.valueOf(token);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	public String getMrn() {
		return mrn;
	
	}

	public void setMrn(String mrn) {
		this.mrn = Utils.checkNull(mrn);
	}


}

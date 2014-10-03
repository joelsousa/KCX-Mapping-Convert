package com.kewill.kcx.component.mapping.companies.fedex.ics.msg.common;

import org.xml.sax.Attributes;


import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/*
* Function    : CustomsDataReferences
* Titel       :
* Date        : 07.01.2011
* Author      : krzoska
* Description : CustomsDataReferences  
* Parameters  :

* Changes
* ------------
* Author      : 
* Date        : 
* Label       : 
* Description : 
*
*/

public class CustomsDataReferences extends KCXMessage{

	private CustomsDataReference customsDataReference = null;
	private boolean debug = false;

	public CustomsDataReferences() {
		super();
	}
	
	public CustomsDataReferences(XmlMsgScanner scanner) {
		super(scanner);
 	}

	private enum ECustomsDataReferences {
		CustomsDataReference;
	}	
	
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
		
			switch ((ECustomsDataReferences) tag) {
			case CustomsDataReference:	customsDataReference = new CustomsDataReference(getScanner());
										customsDataReference.parse(tag.name());
			break;
			default: 
				return;
			}
		} else {
			switch ((ECustomsDataReferences) tag) {
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
			return ECustomsDataReferences.valueOf(token);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	public CustomsDataReference getCustomsDataReference() {
		return customsDataReference;
	
	}

	public void setCustomsDataReference(CustomsDataReference customsDataReference) {
		this.customsDataReference = customsDataReference;
	}


}

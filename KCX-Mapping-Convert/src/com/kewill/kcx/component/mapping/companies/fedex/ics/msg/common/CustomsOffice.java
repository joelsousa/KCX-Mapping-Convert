package com.kewill.kcx.component.mapping.companies.fedex.ics.msg.common;

/*
* Function    : CustomsOffice 
* Titel       :
* Date        : 25.11.2010
* Author      : Kewill CSF / krzoska
* Description : CustomsOffice
* Parameters  :

* Changes
* ------------
* Author      : 
* Date        : 
* Label       : 
* Description : 
*
*/

import javax.xml.stream.XMLEventReader;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Modul		: CustomsOffice.<br>
 * Erstellt		: 25.11.2010<br>
 * Beschreibung	: CustomsOffice for both CustomsOfficeOfFirstEntry and CustomsOfficeOfSubsequentEntry 
 * 
 * @author krzoska
 * @version 1.0.00
 */

public class CustomsOffice extends KCXMessage {

	private String customsOfficeOfFirstEntry;
	private String expectedDateOfArrival;
	private String customsOfficeOfSubsequentEntry;
	
	public CustomsOffice() {
      	super();
	}

	public CustomsOffice(XMLEventReader parser) {
		super(parser);
	}      

	public CustomsOffice(XmlMsgScanner scanner) {
		super(scanner);
	}

	private enum ECustomsOffice {
		RefNumCUSOFFFENT731,
		RefNumCUSOFFENTACTOFF701,
		ExpDatOfArrFIRENT733,
		RefNumSUBENR909
	}
	
	@Override
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
		
		switch ((ECustomsOffice) tag) {
		
		default:
				return;
		}
	  } else {
		switch ((ECustomsOffice) tag) {
		case RefNumCUSOFFENTACTOFF701:
		case RefNumCUSOFFFENT731:  
			setCustomsOfficeOfFirstEntry(value);
			break;

		case ExpDatOfArrFIRENT733:  
			setExpectedDateOfArrival(value);
		break;
	
		case RefNumSUBENR909:  
			setCustomsOfficeOfSubsequentEntry(value);
		break;

		
		default:
			return;
		} 
	  }
	}
	
	
	@Override
	public void stoppElement(Enum tag) {
		// TODO Auto-generated method stub
		
	}

	public Enum translate(String token) {
		try {
			return ECustomsOffice.valueOf(token);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	public String getCustomsOfficeOfFirstEntry() {
		return customsOfficeOfFirstEntry;
	
	}

	public void setCustomsOfficeOfFirstEntry(String customsOfficeOfFirstEntry) {
		this.customsOfficeOfFirstEntry = Utils.checkNull(customsOfficeOfFirstEntry);
	}

	public String getExpectedDateOfArrival() {
		return expectedDateOfArrival;
	
	}

	public void setExpectedDateOfArrival(String expectedDateOfArrival) {
		this.expectedDateOfArrival = Utils.checkNull(expectedDateOfArrival);
	}

	public String getCustomsOfficeOfSubsequentEntry() {
		return customsOfficeOfSubsequentEntry;
	
	}

	public void setCustomsOfficeOfSubsequentEntry(
			String customsOfficeOfSubsequentEntry) {
		this.customsOfficeOfSubsequentEntry = Utils
				.checkNull(customsOfficeOfSubsequentEntry);
	}

}

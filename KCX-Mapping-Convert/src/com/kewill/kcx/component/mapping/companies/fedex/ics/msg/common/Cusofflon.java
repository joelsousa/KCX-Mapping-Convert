package com.kewill.kcx.component.mapping.companies.fedex.ics.msg.common;


/*
* Function    : Cusofflon 
* Titel       :
* Date        : 25.11.2010
* Author      : Kewill CSF / krzoska
* Description : Customs Office Of Lodgement  
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
 * Modul		: Cusofflon.<br>
 * Erstellt		: 25.11.2010<br>
 * Beschreibung	: Customs Office Of Lodgement
 * 
 * @author krzoska
 * @version 1.0.00
 */

public class Cusofflon extends KCXMessage {

	private String customsOfficeOfLodgement;
	
	public Cusofflon() {
      	super();
	}

	public Cusofflon(XMLEventReader parser) {
		super(parser);
	}      

	public Cusofflon(XmlMsgScanner scanner) {
		super(scanner);
	}

	private enum ECusofflonTags {
		RefNumCOL1;
	}
	
	@Override
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
		
		switch ((ECusofflonTags) tag) {
		
		default:
				return;
		}
	  } else {
		switch ((ECusofflonTags) tag) {
		case RefNumCOL1:  setCustomsOfficeOfLodgement(value);
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
			return ECusofflonTags.valueOf(token);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	public String getRefNumCOL1() {
		return customsOfficeOfLodgement;
	
	}

	public String getCustomsOfficeOfLodgement() {
		return customsOfficeOfLodgement;
	
	}

	public void setCustomsOfficeOfLodgement(String customsOfficeOfLodgement) {
		this.customsOfficeOfLodgement = Utils.checkNull(customsOfficeOfLodgement);
	}

}

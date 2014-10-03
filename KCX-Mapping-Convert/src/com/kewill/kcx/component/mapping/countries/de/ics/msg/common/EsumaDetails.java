package com.kewill.kcx.component.mapping.countries.de.ics.msg.common;


import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: EsumaDetails<br>
 * Created		: 2010.07.16<br>
 * Description	: EsumaDetails tag in ICS.
 * 
 * @author Frederick T
 * @version 1.0.00
 *
 */
public class EsumaDetails extends KCXMessage {

	private String itemNumberEsuma;
	
	private boolean debug   = false;
	
	private enum EEsumaDetails {
		//KIDS				//UIDS
		ItemNumberEsuma	,	MRNItemNumber;
	}
	
	public EsumaDetails() {
		super();
	}
	
	public EsumaDetails(XmlMsgScanner scanner) {
		super(scanner);
	}
	
	public boolean isDebug() {
		return debug;
	}
	public void setDebug(boolean debug) {
  		this.debug = debug;
  	}
	
	public void startElement(Enum tag, String value, Attributes attr) {
  		if (value == null) {
  			switch ((EEsumaDetails) tag) {
  			default:
  					return;
  			}
  		} else {

  			switch((EEsumaDetails) tag) {
  				case ItemNumberEsuma:
  				case MRNItemNumber:
  						if (Utils.isStringEmpty(getItemNumberEsuma())) {
  							setItemNumberEsuma(value);
  						}
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
  			return EEsumaDetails.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
  	}

	public String getItemNumberEsuma() {
		return itemNumberEsuma;
	}

	public void setItemNumberEsuma(String itemNumberEsuma) {
		this.itemNumberEsuma = itemNumberEsuma;
	}
  	
}

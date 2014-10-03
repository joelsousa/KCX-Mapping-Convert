package com.kewill.kcx.component.mapping.countries.de.Import.msg.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: Import<br>
 * Created		: 30.10.2012<br>
 * Description	: CountrySpecificValues used in KIDS. 
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class CountrySpecificValues extends KCXMessage {

	private SpecificValuesCH  ch;
    
	private enum ECountrySpecificValues {
		//KIDS							
		DE,						
		CH,
		NL,
		BE,
		FR,
		CZ,
		PL,
		SE,    
		FI,	
		GR,
		CY,
		LU,
    }

	public CountrySpecificValues() {
		super();  
	}

    public CountrySpecificValues(XmlMsgScanner scanner) {
  		super(scanner);
  	}
 
  	public void startElement(Enum tag, String value, Attributes attr) {
  		if (value == null) {
  			switch ((ECountrySpecificValues) tag) {
  			case CH:
  				ch = new SpecificValuesCH(getScanner());
  				ch.parse(tag.name());
  				break;  						
  			default:
  					return;
  			}
  		} else {

  			switch ((ECountrySpecificValues) tag) {  				
  				default:  				  					
  					break;  
  			}
  		}
  	}

  	public void stoppElement(Enum tag) {
  	}

  	public Enum translate(String token) {
  		try {
  			return ECountrySpecificValues.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
  	}

	public SpecificValuesCH getCh() {
		return ch;
	}
	public void setCh(SpecificValuesCH argument) {
		ch = argument;
	}

	
	public boolean isEmpty() {
		return (ch == null); 
	}
	
}

package com.kewill.kcx.component.mapping.countries.de.suma62.msg.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.common.Party;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: Manifest<br>
 * Created		: 19.11.2012<br>
 * Description	: Contains the NewCustodyValues Data with all Fields used in KIDS Manifest.
 * 
 * @author Alfred Krzoska
 * @version 1.0.00
 */
public class NewCustody_old extends KCXMessage {
	
	private Custodian custodian;
	
	private enum ENewCustodyValues {
		//KIDS							//UIDS
		Custodian,		
	}
	
	public NewCustody_old() {
		super();  
	}

    public NewCustody_old(XmlMsgScanner scanner) {
  		super(scanner);
  	}
	
	@Override
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
  			switch ((ENewCustodyValues) tag) {
  				case Custodian:
  					custodian = new Custodian(getScanner());
  					custodian.parse(tag.name());	
  				break;
  				
  			default:
  					return;
  			}
  		} else {

  			switch ((ENewCustodyValues) tag) {
  	  			default:
  					return;
  			}
  		}
		
	}

	@Override
	public void stoppElement(Enum tag) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Enum translate(String token) {
		 try {
				return ENewCustodyValues.valueOf(token);
			 } catch (IllegalArgumentException e) {
				return null;
			 }
	}

	public Custodian getCustodian() {
		return custodian;
	}
	public void setCustodian(Custodian custodian) {
		this.custodian = custodian;
	}

	
}

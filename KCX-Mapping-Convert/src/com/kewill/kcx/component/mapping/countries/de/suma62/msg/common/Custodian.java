package com.kewill.kcx.component.mapping.countries.de.suma62.msg.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: Manifest<br>
 * Created		: 19.11.2012<br>
 * Description	: Contains the Custodian Data with all Fields used in KIDS Manifest.
 * 
 * @author Alfred Krzoska
 * @version 1.0.00
 */
public class Custodian extends KCXMessage {
	
	private String 	tin;       		//EI20130527-TODO: in xsd geaendert in CT_TINType
	private Address address;        //EI20130122
	private ReferencedSpecification referencedSpecification;  //EI20130122
	
	private enum ECustodian {
		//KIDS							//UIDS
		TIN,
		Address,
		ReferencedSpecification,
	}
	
	public Custodian() {
		super();  
	}

    public Custodian(XmlMsgScanner scanner) {
  		super(scanner);
  	}
	
	@Override
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
  			switch ((ECustodian) tag) {
  			
  			case Address:
  				address = new Address(getScanner());
  				address.parse(tag.name());
				break;    //EI20130607
				
  			case ReferencedSpecification:
				referencedSpecification = new ReferencedSpecification(getScanner());
				referencedSpecification.parse(tag.name());
				break;    //EI20130607
				
  			default:
  					return;
  			}
  		} else {

  			switch ((ECustodian) tag) {
  				case TIN:  				
  					setTIN(value);
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

	@Override
	public Enum translate(String token) {
		 try {
				return ECustodian.valueOf(token);
			 } catch (IllegalArgumentException e) {
				return null;
			 }
	}

	public String getTIN() {
		return tin;
	}
	public void setTIN(String tIN) {
		this.tin = Utils.checkNull(tIN);
	}

	public Address getAddress() {
		return address;
	}
	public void setAddress(Address adr) {
		this.address = adr;
	}
	
	public ReferencedSpecification getReferencedSpecification() {
		return referencedSpecification;
	}
	public void setReferencedSpecification(ReferencedSpecification ref) {
		this.referencedSpecification = ref;
	}
}

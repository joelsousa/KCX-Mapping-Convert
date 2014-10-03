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
public class NewCustodyValues extends KCXMessage {
	
	private Custodian custodian;
	private Custodian placeOfCustody;   //EI20130121
	private ReferencedSpecification referencedSpecification; //EI20130121
	
	private enum ENewCustodyValues {
		//KIDS							//UIDS
		Custodian,
		PlaceOfCustody,  
		ReferencedSpecification,
	}
	
	public NewCustodyValues() {
		super();  
	}

    public NewCustodyValues(XmlMsgScanner scanner) {
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
  					
  				case PlaceOfCustody: //EI20130121 
  					placeOfCustody = new Custodian(getScanner());
  					placeOfCustody.parse(tag.name());
  					break;
  					
  				case ReferencedSpecification: //EI20130121  					
  					referencedSpecification = new ReferencedSpecification(getScanner());
  					referencedSpecification.parse(tag.name());
  					break;                   //EI20130607
  					
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

	public Custodian getPlaceOfCustody() {
		return placeOfCustody;
	}
	public void setPlaceOfCustody(Custodian custodian) {
		this.placeOfCustody = custodian;
	}

	public ReferencedSpecification getReferencedSpecification() {
		return referencedSpecification;
	}
	public void setReferencedSpecification(ReferencedSpecification referencedSpecification) {
		this.referencedSpecification = referencedSpecification;
	}
}

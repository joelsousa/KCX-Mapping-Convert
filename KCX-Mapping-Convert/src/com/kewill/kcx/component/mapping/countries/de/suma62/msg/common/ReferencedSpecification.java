package com.kewill.kcx.component.mapping.countries.de.suma62.msg.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.common.Trader;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: Manifest<br>
 * Created		: 19.11.2012<br>
 * Description	: Contains the ReferencedSpecification Data with all Fields used in KIDS Manifest.
 * 
 * @author Alfred Krzoska
 * @version 1.0.00
 */
public class ReferencedSpecification extends KCXMessage {
	
	private String typeOfSpecificationID;
	private String specificationID;
	private String custodianTIN;    //EI20121211	
	private Trader custodian;		//EI20121211     Der Verrwahrer
	
	
	
	private enum EReferencedSpecification {
		//KIDS							//UIDS
		TypeOfSpecificationID,
		SpecificationID,
		CustodianTIN,
		Custodian,
	}
	
	public ReferencedSpecification() {
		super();  
	}

    public ReferencedSpecification(XmlMsgScanner scanner) {
  		super(scanner);
  	}
	
	@Override
	public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {
  			switch ((EReferencedSpecification) tag) {
  				case Custodian:
  					custodian = new Trader(getScanner());
  					custodian.parse(tag.name());
  					break;					
  				default:
  					return;
  			}
  		} else {
  			switch ((EReferencedSpecification) tag) {
  				case TypeOfSpecificationID:  				
  					setTypeOfSpecificationID(value);
  					break;
  				case SpecificationID:
  					setSpecificationID(value);
  					break;
  				case CustodianTIN:  				
  					setCustodianTIN(value);
  					break;  				
  				default:
  					return;
  			}
  		}
		
	}

	@Override
	public void stoppElement(Enum tag) {
		
		
	}

	@Override
	public Enum translate(String token) {
		try {
  			return EReferencedSpecification.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}	
  	}

	public String getTypeOfSpecificationID() {
		return typeOfSpecificationID;
	}

	public void setTypeOfSpecificationID(String typeOfSpecificationID) {
		this.typeOfSpecificationID = Utils.checkNull(typeOfSpecificationID);
	}

	public String getSpecificationID() {
		return specificationID;
	}

	public void setSpecificationID(String specificationID) {
		this.specificationID = Utils.checkNull(specificationID);
	}
	
	public String getCustodianTIN() {
		return custodianTIN;
	}
	public void setCustodianTIN(String value) {
		this.custodianTIN = Utils.checkNull(value);
	}

	public Trader getCustodian() {
		return custodian;
	}
	public void setCustodian(Trader trader) {
		this.custodian = trader;
	}
	
	public boolean isEmpty() {
		return (Utils.isStringEmpty(this.specificationID) && Utils.isStringEmpty(this.typeOfSpecificationID));
	}
}

package com.kewill.kcx.component.mapping.countries.de.Import.msg.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: Import<br>
 * Created		: 15.09.2011<br>
 * Description	: Contains the Excise Data with all Fields used in KIDS.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class Excise extends KCXMessage {

    private String taxCode;	
    private String taxQuantity;	
    private String measuringUnit;		    
    private String qualifierMeasuringUnit;
    private String rateOrPercent;
    private String taxValue;	
    private String rateConfirmation;              //EI20121105 new for CH
   
	private enum EExcise {
		TaxCode,
		TaxQuantity,
		MeasuringUnit,		
		QualifierMeasuringUnit,     		
		RateOrPercent,
		TaxValue,
		RateConfirmation,
     }
     
      public Excise() {
	      	super();
      }

      public Excise(XmlMsgScanner scanner) {
  		super(scanner);
  	}
      
  	public void startElement(Enum tag, String value, Attributes attr) {
  		if (value == null) {
  			switch ((EExcise) tag) {
  			default:
  					return;
  			}
  		} else {

  			switch ((EExcise) tag) {

  				case TaxCode:
  					setTaxCode(value);
  					break;
  				case TaxQuantity:
  					setTaxQuantity(value);
  					break;
  				case MeasuringUnit:
  					setMeasuringUnit(value);
  					break;  				  			
  				case QualifierMeasuringUnit:
  					setQualifierMeasuringUnit(value);
  					break;  
  				case RateOrPercent:
  					setRateOrPercent(value);
  					break;
  				case TaxValue:
  					setTaxValue(value);
  					break;
  				case RateConfirmation:
  					setRateConfirmation(value);
  					break;
  					  					
  				default:
  					break;
  			}
  		}
  	}

  	public void stoppElement(Enum tag) {
  	}

  	public Enum translate(String token) {
  		try {
  			return EExcise.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
  	}

  	
  	public String getTaxCode() {
		return taxCode;
	}
	public void setTaxCode(String value) {
		this.taxCode = value;
	}
	
	public String getTaxQuantity() {
		return taxQuantity;
	}
	public void setTaxQuantity(String value) {
		this.taxQuantity = value;
	}
	   		
	public String getMeasuringUnit() {
		return measuringUnit;
	}
	public void setMeasuringUnit(String value) {
		this.measuringUnit = value;
	}
	
	public String getQualifierMeasuringUnit() {
		return qualifierMeasuringUnit;
	}
	public void setQualifierMeasuringUnit(String value) {
		this.qualifierMeasuringUnit = value;
	}	

	public String getRateOrPercent() {
		return rateOrPercent;	
	}
	public void setRateOrPercent(String value) {
		this.rateOrPercent = Utils.checkNull(value);
	}
		
	public String getTaxValue() {
		return taxValue;	
	}
	public void setTaxValue(String value) {
		this.taxValue = Utils.checkNull(value);
	}
	
	public String getRateConfirmation() {
		return rateConfirmation;	
	}
	public void setRateConfirmation(String value) {
		this.rateConfirmation = Utils.checkNull(value);
	}
	
	public boolean isEmpty() {
		return (Utils.isStringEmpty(this.taxCode) && Utils.isStringEmpty(this.taxQuantity) &&
				Utils.isStringEmpty(this.measuringUnit) && Utils.isStringEmpty(this.taxValue) &&
		        Utils.isStringEmpty(this.rateOrPercent));
	}	
}

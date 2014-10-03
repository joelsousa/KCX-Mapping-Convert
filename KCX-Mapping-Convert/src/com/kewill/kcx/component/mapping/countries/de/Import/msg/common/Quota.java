package com.kewill.kcx.component.mapping.countries.de.Import.msg.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: Import<br>
 * Created		: 15.09.2011<br>
 * Description	: Contains the Quota Data with all Fields used in KIDS.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class Quota extends KCXMessage {

    private String number1;		
    private String number2;		  
    private String quantity;
    private String measuringUnit;		    
    private String qualifierMeasuringUnit;
    
    
	private enum EQuota {
		Number1,
		Number2,		 
		Quantity,							
		MeasuringUnit,		
		QualifierMeasuringUnit;
     }
     
      public Quota() {
	      	super();
      }

      public Quota(XmlMsgScanner scanner) {
  		super(scanner);
  	  }
      
    
  	public void startElement(Enum tag, String value, Attributes attr) {
  		if (value == null) {
  			switch ((EQuota) tag) {
  			default:
  					return;
  			}
  		} else {

  			switch ((EQuota) tag) {

  				case Number1:
  					setNumber1(value);
  					break;
  				case Number2:
  					setNumber2(value);
  					break;  					  		
  				case Quantity:
  					setQuantity(value);
  					break;  					  				
  				case MeasuringUnit:
  					setMeasuringUnit(value);
  					break;  				  			
  				case QualifierMeasuringUnit:
  					setQualifierMeasuringUnit(value);
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
  			return EQuota.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
  	}

	public String getNumber1() {
		return number1;
	}
	public void setNumber1(String value) {
		this.number1 = value;
	}

	public String getNumber2() {
		return number2;
	}
	public void setNumber2(String value) {
		this.number2 = value;
	}

	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String value) {
		this.quantity = value;
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
	public boolean isEmpty() {
		return (Utils.isStringEmpty(number1) && Utils.isStringEmpty(number2) && 
				Utils.isStringEmpty(quantity) && Utils.isStringEmpty(measuringUnit) && 
		        Utils.isStringEmpty(qualifierMeasuringUnit)); 
	}
}

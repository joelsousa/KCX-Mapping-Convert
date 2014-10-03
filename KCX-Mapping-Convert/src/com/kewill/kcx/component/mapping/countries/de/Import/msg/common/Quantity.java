package com.kewill.kcx.component.mapping.countries.de.Import.msg.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: Import<br>
 * Created		: 15.09.2011<br>
 * Description	: Contains the Quantity Data with all Fields used in KIDS.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class Quantity extends KCXMessage {
	  
    private String quantity;
    private String unit;		    
    private String qualifier;
    
    
	private enum EQuota {			
		Quantity,							
		MeasuringUnit,	UnitOfMeasurement,	
		QualifierMeasuringUnit, Qualifier;
     }
     
      public Quantity() {
	      	super();
      }

      public Quantity(XmlMsgScanner scanner) {
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
  									  	
  				case Quantity:
  					setQuantity(value);
  					break;  					  				
  				case MeasuringUnit:
  				case UnitOfMeasurement:
  					setUnit(value);
  					break;  				  			
  				case QualifierMeasuringUnit:
  				case Qualifier:
  					setQualifier(value);
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

	
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String value) {
		this.quantity = value;
	}
	
	public String getUnit() {
		return unit;
	}
	public void setUnit(String value) {
		this.unit = value;
	}
	
	public String getQualifier() {
		return qualifier;
	}
	public void setQualifier(String value) {
		this.qualifier = value;
	}	

}

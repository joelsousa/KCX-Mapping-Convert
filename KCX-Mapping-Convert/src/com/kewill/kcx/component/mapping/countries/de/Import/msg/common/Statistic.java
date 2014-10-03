package com.kewill.kcx.component.mapping.countries.de.Import.msg.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: Import<br>
 * Created		: 15.09.2011<br>
 * Description	: Contains the Statistic Data with all Fields used in KIDS.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class Statistic extends KCXMessage {

    private String quantity;		
    private String measuringUnit;		    
    private String qualifierMeasuringUnit;
    private String statisticalValue;
   
	private enum EImportStatistic {
		Quantity,
		MeasuringUnit,		
		QualifierMeasuringUnit,     		
		StatisticalValue;
     }
     
      public Statistic() {
	      	super();
      }

      public Statistic(XmlMsgScanner scanner) {
  		super(scanner);
  	}
      
  	public void startElement(Enum tag, String value, Attributes attr) {
  		if (value == null) {
  			switch ((EImportStatistic) tag) {
  			default:
  					return;
  			}
  		} else {

  			switch ((EImportStatistic) tag) {

  				case Quantity:
  					setQuantity(value);
  					break;
  				case MeasuringUnit:
  					setMeasuringUnit(value);
  					break;  				  			
  				case QualifierMeasuringUnit:
  					setQualifierMeasuringUnit(value);
  					break;  					  			
  				case StatisticalValue:
  					setStatisticalValue(value);
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
  			return EImportStatistic.valueOf(token);
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

	public String getStatisticalValue() {
		return statisticalValue;	
	}
	public void setStatisticalValue(String value) {
		this.statisticalValue = Utils.checkNull(value);
	}

	public boolean isEmpty() {
		return (Utils.isStringEmpty(quantity) && Utils.isStringEmpty(measuringUnit) && 
				Utils.isStringEmpty(qualifierMeasuringUnit) && 
		        Utils.isStringEmpty(statisticalValue)); 
	}
}

package com.kewill.kcx.component.mapping.countries.de.aes.msg.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: Export/aes<br>
 * Created		: 10.09.2008<br>
 * Description	: Contains the Statistic Data with all Fields used in KIDS.
 * 
 * @author Houdek
 * @version 1.0.00
 */
public class Statistic extends KCXMessage {

    private String additionalUnit 	     = "";		//n(12,3)
    private String statisticalValue      = "";		
    private String statisticalValueConfirmation = "";
    private String additionalUnitConfirmation = "";
    private String value				= "";
    private String currency;
    private String additionalUnitCode;  
    private String additionalUnitQualifier;  //EI20130614 fuer BDP neu - wird aber fuer Export im FSS nicht gemapped
    private String statisticalValueSendFlag; //EI20130808: AES22: Values: 1, 0 or empty
    													// 1 = StatisticalValue equal null will be send to Customs,
    													// 0 or no Tag sent = StatisticalValue equal null will not be send to Customs,
    
	private enum EStatistic {
		//KKIDS:						UIDS: in UIDS directly in GoodsItem, not as a class
		AdditionalUnit,					//StatisticalQuantity
		AdditionalUnitConfirmation,     //StatisticalQuantityConfirmation
		AdditionalUnitCode,
		StatisticalValue,               //same
		StatisticalValueConfirmation,	//same   
		StatisticalValueSendFlag,       //same 
		Value,							//StatisticalBaseValue		
		Currency,						//StatisticalBaseCurrency
		Quantity,		//EI20140614 fuer BDP == AdditionalUnit
		MeasuringUnit,  //EI20140614 fuer BDP == AdditionalUnitCode
		QualifierAdditionalUnitCode, QualifierMeasuringUnit, //EI20130614 fuer BDP		
     }

      private boolean debug   = false;

      public Statistic() {
	      	super();
      }

      public Statistic(XmlMsgScanner scanner) {
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
  			switch ((EStatistic) tag) {
  			default:
  					return;
  			}
  		} else {
  			switch ((EStatistic) tag) {

  				case AdditionalUnit:
  				case Quantity:                   //EI20140614
  					setAdditionalUnit(value);
  					break;

  				case StatisticalValue:
  					setStatisticalValue(value);
  					break;
  					
  				case StatisticalValueConfirmation:
  					setStatisticalValueConfirmation(value);
  					break;

  				case AdditionalUnitConfirmation:
  					setAdditionalUnitConfirmation(value);
  					break;
  					
  				case Value:
  					setValue(value);
  					break;
  					  					
  				case Currency:
  					setCurrency(value);
  					break;
  					
  				case AdditionalUnitCode:
  				case MeasuringUnit:              //EI20130614
  					setAdditionalUnitCode(value);
  					break;
  					
  				case QualifierAdditionalUnitCode:       //EI20130614
  				case QualifierMeasuringUnit:
  					setQualifierAdditionalUnitCode(value);
  					break;
  					
  				case StatisticalValueSendFlag:            //EI20130808
  					setStatisticalValueSendFlag(value);
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
  			return EStatistic.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
  	}

	public String getAdditionalUnit() {
		return additionalUnit;
	}

	public void setAdditionalUnit(String additionalUnit) {
		this.additionalUnit = additionalUnit;
	}

	public String getStatisticalValue() {
		return statisticalValue;
	}

	public void setStatisticalValue(String statisticalValue) {
		this.statisticalValue = statisticalValue;
	}

	public String getStatisticalValueConfirmation() {
		return statisticalValueConfirmation;
	}

	public void setStatisticalValueConfirmation(String statisticalValueConfirmation) {
		this.statisticalValueConfirmation = statisticalValueConfirmation;
	}

	public String getAdditionalUnitConfirmation() {
		return additionalUnitConfirmation;
	}

	public void setAdditionalUnitConfirmation(String additionalUnitConfirmation) {
		this.additionalUnitConfirmation = additionalUnitConfirmation;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String argument) {
		this.currency = argument;
	}

	public String getAdditionalUnitCode() {
		return additionalUnitCode;	
	}
	public void setAdditionalUnitCode(String additionalUnitCode) {
		this.additionalUnitCode = Utils.checkNull(additionalUnitCode);
	}
	
	public String getQualifierAdditionalUnitCode() {
		return additionalUnitQualifier;
	}
	public void setQualifierAdditionalUnitCode(String argument) {
		this.additionalUnitQualifier = argument;
	}	

	public String getStatisticalValueSendFlag() {
		return statisticalValueSendFlag;
	}
	public void setStatisticalValueSendFlag(String statisticalValueSendFlag) {
		this.statisticalValueSendFlag = statisticalValueSendFlag;
	}

	public boolean isEmpty() {
		return (Utils.isStringEmpty(additionalUnit) && Utils.isStringEmpty(additionalUnitCode) && 
				Utils.isStringEmpty(additionalUnitConfirmation) &&
				Utils.isStringEmpty(statisticalValue) && Utils.isStringEmpty(statisticalValueConfirmation) &&
		        Utils.isStringEmpty(value) && Utils.isStringEmpty(currency));		       
	}
}

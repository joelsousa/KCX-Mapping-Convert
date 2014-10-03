package com.kewill.kcx.component.mapping.countries.de.Port.msg.commonBL;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: Port - BillOfLading.<br>
 * Created		: 10.04.2012<br>
 * Description	:
 * 
 * @author iwaniuk
 * @version 1.0.00
 */

public class Totals extends KCXMessage {
		
	private String grossWeightKilogram;
	private String numberOfPieces;
	private String numberOfPackages;
	private String grossVolumeCubicMetre;
	private String numberOfEquipment;
	
	private enum ETotals {	
		GrossWeightKilogram,
		NumberOfPieces,
		NumberOfPackages,
		GrossVolumeCubicMetre,
		NumberOfEquipment;
   }	

	public Totals() {
		super();  
	}

	public Totals(XmlMsgScanner scanner) {
  		super(scanner);
  	}
	
  	public void startElement(Enum tag, String value, Attributes attr) {
  		if (value == null) {
  			switch ((ETotals) tag) {
  			/*
				case Address:
  					address = new Address(getScanner());  	
  					address.parse(tag.name());
  					break; 
  				*/
				default:
  					break;
  			}
  		} else {

  			switch((ETotals) tag) {   			
  				case GrossWeightKilogram:
  					setGrossWeightKilogram(value);
  					break; 
  				case NumberOfPieces:
  					setNumberOfPieces(value);
  					break; 	
  				case NumberOfPackages:
  					setNumberOfPackages(value);
  					break; 
  				case GrossVolumeCubicMetre:
  					setGrossVolumeCubicMetre(value);
  					break; 
  				case NumberOfEquipment:
  					setNumberOfEquipment(value);
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
  			return ETotals.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
  	}

	
	public String getGrossWeightKilogram() {
		return grossWeightKilogram;
	}    
	public void setGrossWeightKilogram(String value) {
		this.grossWeightKilogram = value;
	}	
	
	public String getNumberOfPieces() {
		return this.numberOfPieces;
	}    
	public void setNumberOfPieces(String value) {
		this.numberOfPieces = value;
	}	
	
	public String getNumberOfPackages() {
		return numberOfPackages;
	}    
	public void setNumberOfPackages(String value) {
		this.numberOfPackages = value;
	}	
	
	public String getGrossVolumeCubicMetre() {
		return grossVolumeCubicMetre;
	}    
	public void setGrossVolumeCubicMetre(String value) {
		this.grossVolumeCubicMetre = value;
	}
	
	public String getNumberOfEquipment() {
		return numberOfEquipment;
	}    
	public void setNumberOfEquipment(String value) {
		this.numberOfEquipment = value;
	}	
	
	public boolean isEmpty() {
		return (Utils.isStringEmpty(grossWeightKilogram) && Utils.isStringEmpty(numberOfPackages) && 
				Utils.isStringEmpty(grossVolumeCubicMetre));
				
	}
}


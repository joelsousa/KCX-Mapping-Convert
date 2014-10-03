package com.kewill.kcx.component.mapping.countries.de.Port.msg.commonBL;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: Port - BillOfLading.<br>
 * Created		: 10.04.2012<br>
 * Description	:
 * 
 * @author iwaniuk
 * @version 1.0.00
 */

public class DistributedOverSeveralContainer extends KCXMessage {
		
	private String numberOfPackages;	
	private String grossWeight;
	private String netWeight;
	private String tareWeight;
	private String volume;	
	
	private enum ECarriage {	
		NumberOfPackages,	
		GrossWeightKilogram,
		NetWeightKilogram,
		TareWeightKilogram,		
		GrossVolumeCubicMetre;		
   }	

	public DistributedOverSeveralContainer() {
		super();  
	}

	public DistributedOverSeveralContainer(XmlMsgScanner scanner) {
  		super(scanner);
  	}
	
  	public void startElement(Enum tag, String value, Attributes attr) {
  		if (value == null) {
  			switch ((ECarriage) tag) {    				
				default:
  					break;
  			}
  		} else {

  			switch((ECarriage) tag) {   
  			case NumberOfPackages:
  				setNumberOfPackages(value);
  				break;
  			case GrossWeightKilogram:
  				setGrossWeightKilogram(value);
  				break;
  			case NetWeightKilogram:
  				setNetWeightKilogram(value);
  				break;
  			case TareWeightKilogram:
  				setTareWeightKilogram(value);
  				break;  			
  			case GrossVolumeCubicMetre:
  				setGrossVolumeCubicMetre(value);
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
  			return ECarriage.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
  	}
	
	public String getNumberOfPackages() {
		return numberOfPackages;
	}    
	public void setNumberOfPackages(String value) {
		this.numberOfPackages = value;
	}
		
	public String getGrossWeightKilogram() {
		return grossWeight;
	}    
	public void setGrossWeightKilogram(String value) {
		this.grossWeight = value;
	}
	
	public String getNetWeightKilogram() {
		return netWeight;
	}    
	public void setNetWeightKilogram(String value) {
		this.netWeight = value;
	}
	
	public String getTareWeightKilogram() {
		return tareWeight;
	}    
	public void setTareWeightKilogram(String value) {
		this.tareWeight = value;
	}
	
	public String getGrossVolumeCubicMetre() {
		return volume;
	}    
	public void setGrossVolumeCubicMetre(String value) {
		this.volume = value;
	}
}


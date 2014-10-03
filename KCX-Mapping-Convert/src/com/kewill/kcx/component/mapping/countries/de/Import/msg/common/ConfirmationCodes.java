package com.kewill.kcx.component.mapping.countries.de.Import.msg.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: Import<br>
 * Created		: 29.10.2012<br>
 * Description	: ConfirmationCodes.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class ConfirmationCodes extends KCXMessage {

	private String tarifCodeConfirmation;
    private String grossMassConfirmation;
    private String netMassConfirmation;
    private String quantityConfirmation;
    private String statisticalValueConfirmation;
    private String customsValueConfirmation;
    private String taraSupplementConfirmation;
    private String nonRegularCustomsRateConfirmation;
    private String vatCodeConfirmation;   
    
	private enum EDeferment {
		//KIDS							
		TarifCodeConfirmation,						
		GrossMassConfirmation,
		NetMassConfirmation,
		QuantityConfirmation,
		StatisticalValueConfirmation,
		CustomsValueConfirmation,
		TaraSupplementConfirmation,
		NonRegularCustomsRateConfirmation,   
		VATCodeConfirmation;	
    }

	public ConfirmationCodes() {
		super();  
	}

    public ConfirmationCodes(XmlMsgScanner scanner) {
  		super(scanner);
  	}
 
  	public void startElement(Enum tag, String value, Attributes attr) {
  		if (value == null) {
  			switch ((EDeferment) tag) {
  			default:
  					return;
  			}
  		} else {

  			switch ((EDeferment) tag) {
  				case TarifCodeConfirmation:
  					setTarifCodeConfirmation(value);
  					break;  					  				 				
  				case GrossMassConfirmation:  				
  					setGrossMassConfirmation(value);
  					break;
  				case NetMassConfirmation:  				
  					setNetMassConfirmation(value);
  					break;  
  				case QuantityConfirmation:
  					setQuantityConfirmation(value);
  					break;  					  				 				
  				case StatisticalValueConfirmation:  				
  					setStatisticalValueConfirmation(value);
  					break;
  				case CustomsValueConfirmation:  				
  					setCustomsValueConfirmation(value);
  					break;  
  				case TaraSupplementConfirmation:
  					setTaraSupplementConfirmation(value);
  					break;  					  				 				
  				case NonRegularCustomsRateConfirmation:  				
  					setNonRegularCustomsRateConfirmation(value);
  					break;
  				case VATCodeConfirmation:
  					setVATCodeConfirmation(value);    //EI20120928
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
  			return EDeferment.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
  	}

	public String getTarifCodeConfirmation() {
		return tarifCodeConfirmation;
	}
	public void setTarifCodeConfirmation(String argument) {
		tarifCodeConfirmation = argument;
	}

	public String getGrossMassConfirmation() {
		return grossMassConfirmation;
	}
	public void setGrossMassConfirmation(String argument) {
		grossMassConfirmation = argument;
	}
		
	public String getNetMassConfirmation() {
		return netMassConfirmation;
	}
	public void setNetMassConfirmation(String argument) {
		netMassConfirmation = argument;
	}
	
	public String getQuantityConfirmation() {
		return quantityConfirmation;
	}
	public void setQuantityConfirmation(String argument) {
		quantityConfirmation = argument;
	}

	public String getStatisticalValueConfirmation() {
		return statisticalValueConfirmation;
	}
	public void setStatisticalValueConfirmation(String argument) {
		statisticalValueConfirmation = argument;
	}
		
	public String getCustomsValueConfirmation() {
		return customsValueConfirmation;
	}
	public void setCustomsValueConfirmation(String argument) {
		customsValueConfirmation = argument;
	}
	
	public String getTaraSupplementConfirmation() {
		return taraSupplementConfirmation;
	}
	public void setTaraSupplementConfirmation(String argument) {
		taraSupplementConfirmation = argument;
	}	
		
	public String getNonRegularCustomsRateConfirmation() {
		return nonRegularCustomsRateConfirmation;
	}
	public void setNonRegularCustomsRateConfirmation(String argument) {
		nonRegularCustomsRateConfirmation = argument;
	}
		
	public String getVATCodeConfirmation() {
		return vatCodeConfirmation;
	}
	public void setVATCodeConfirmation(String argument) {
		vatCodeConfirmation = argument;
	}	
	
	public boolean isEmpty() {
		return (Utils.isStringEmpty(tarifCodeConfirmation) && 
				Utils.isStringEmpty(grossMassConfirmation) && Utils.isStringEmpty(netMassConfirmation) && 
				Utils.isStringEmpty(quantityConfirmation) && Utils.isStringEmpty(statisticalValueConfirmation) && 
				Utils.isStringEmpty(customsValueConfirmation) && Utils.isStringEmpty(taraSupplementConfirmation) && 
		        Utils.isStringEmpty(nonRegularCustomsRateConfirmation) && Utils.isStringEmpty(vatCodeConfirmation)); 
	}
	
}

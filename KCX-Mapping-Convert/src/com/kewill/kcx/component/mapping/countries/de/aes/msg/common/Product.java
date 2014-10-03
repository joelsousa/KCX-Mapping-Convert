package com.kewill.kcx.component.mapping.countries.de.aes.msg.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module       : Export/aes<br>
 * Created      : 2.07.2012.<br>
 * Description	: Kids Version 2.1.00
 * 
 * @author iwaniuk
 * @version 2.1.00
 */

public class Product extends KCXMessage {

    private String tarifCode;			
    private String description;	  
 
  	private enum ETarif {
  	// Kids-TagNames, 			UIDS-TagNames
  		TarifCode,				CommodityCode,		
		Description,            GoodsDescription;												
  	} 

    public Product() {
    	super();    		
    }
    
    public Product(XmlMsgScanner scanner) {
  		super(scanner);
  	}
     
  	public void startElement(Enum tag, String value, Attributes attr) {
  		if (value == null) {
  			switch ((ETarif) tag) {
  			default:
  					return;
  			}
  		} else {
  			switch ((ETarif) tag) {  			
  				case TarifCode:  
  				case CommodityCode:
  					setTarifCode(value);
  					break;  					
  				case Description: 
  				case GoodsDescription:
  					setDescription(value);
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
  			return ETarif.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
  	}

  	
	public String getTarifCode() {
		return tarifCode;
	}
	public void setTarifCode(String value) {
		this.tarifCode = value;
	}

	public String getDescription() {
		return description;
	}
	public void setDescription(String value) {
		this.description = value;
	}	
	
	public boolean isEmpty() {
		return (Utils.isStringEmpty(tarifCode) && Utils.isStringEmpty(description));  
	}
}

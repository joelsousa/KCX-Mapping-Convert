package com.kewill.kcx.component.mapping.countries.de.aes.msg.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.common.Packages;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: Export/aes<br>
 * Created		: 16.07.2012<br>
 * Description  : -. 					
 * 
 * @author Iwaniuk
 * @version 1.0.00
 */
public class InvoiceItem extends KCXMessage {
      
    private String   itemNumber;
    private String   description;
    private String   netMass;
    private String   grosMass;
    private String   invoicePrice;
    private String   currency;
    private Packages packages;
	
  	private enum EInvoiceItem {		
  		ItemNumber,
  		Description,
  		NetMass,
  		GrosMass,
		InvoicePrice,			
		Currency,		 
		Packages;			
  	} 

    public InvoiceItem() {
    	super();    		
    }
    
    public InvoiceItem(XmlMsgScanner scanner) {
  		super(scanner);
  	}
     
  	public void startElement(Enum tag, String value, Attributes attr) {
  		if (value == null) {
  			switch ((EInvoiceItem) tag) {  				
  				case Packages:
  					packages = new Packages(getScanner());
  					packages.parse(tag.name());
  					break;	
  				
  			default:
  					return;
  			}
  		} else {
  			switch ((EInvoiceItem) tag) {  			   
  				case ItemNumber:
  					setItemNumber(value);
  					break;
  				case Description:
  					setDescription(value);
  					break;
  				case NetMass:
  					setNetMass(value);
  					break;
  				case GrosMass:
  					setGrosMass(value);
  					break;  					
  				case InvoicePrice:
  					setInvoicePrice(value);
  					break;  					  					
  				case Currency:
  					setCurrency(value);
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
  			return EInvoiceItem.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
  	}

  	public String getItemNumber() {
		return itemNumber;
	}
	public void setItemNumber(String invoiceNumber) {
		this.itemNumber = invoiceNumber;
	}

	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String argument) {
		this.currency = argument;
	}
	
	public void setInvoicePrice(String argument) {
		this.invoicePrice = argument;
	}			
	public String getInvoicePrice() {
		return this.invoicePrice;
	}
	
	public String getDescription() {
	    return description;
	}
	public void setDescription(String argument) {
		this.description = argument;
	}	
	
	public String getNetMass() {
	    return netMass;
	}
	public void setNetMass(String argument) {
		this.netMass = argument;
	}
	
	
	public String getGrosMass() {
	    return grosMass;
	}
	public void setGrosMass(String argument) {
		this.grosMass = argument;
	}
	
	public Packages getPackages() {		
		return packages;
	}
	public void setPackages(Packages argument) {
		this.packages = argument;
	}	
	
	public boolean isEmpty() {
		return (Utils.isStringEmpty(itemNumber) && Utils.isStringEmpty(description) && 
				Utils.isStringEmpty(netMass) && Utils.isStringEmpty(grosMass) && 
				Utils.isStringEmpty(invoicePrice) && Utils.isStringEmpty(currency) &&
		        packages == null); 
	}
}

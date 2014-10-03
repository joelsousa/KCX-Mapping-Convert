package com.kewill.kcx.component.mapping.countries.de.aes.msg.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: Business<br>
 * Created		: 10.09.2008<br>
 * Description	: Contains the Business Data/Transaction with all Fields used in KIDS and UIDS. 
 * 
 * @author Houdek
 * @version 1.0.00
 */
public class Business extends KCXMessage {

    private String businessTypeCode;			
    private String invoicePrice;	//n(15,2)	
    private String currency;
    private String additionalType;  
 
  	private enum EBusiness {
  	// Kids-TagNames, 			UIDS-TagNames
		BusinessTypeCode,		Type,
								AdditionalType,
		InvoicePrice,			Amount,
		Currency;				//same								
  	} 

    public Business() {
    	super();    		
    }
    
    public Business(XmlMsgScanner scanner) {
  		super(scanner);
  	}
     
  	public void startElement(Enum tag, String value, Attributes attr) {
  		if (value == null) {
  			switch ((EBusiness) tag) {
  			default:
  					return;
  			}
  		} else {
  			switch ((EBusiness) tag) {  			
  				case BusinessTypeCode:
  				case Type:
  					setBusinessTypeCode(value);
  					break;  					
  				case InvoicePrice:
  				case Amount:
  					setInvoicePrice(value);
  					break;  					
  				case Currency:
  					setCurrency(value);
  					break;  					
  				case AdditionalType:
  					setAdditionalType(value);
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
  			return EBusiness.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
  	}
  	
	public String getBusinessTypeCode() {
		return businessTypeCode;
	}
	public void setBusinessTypeCode(String businessTypeCode) {
		this.businessTypeCode = businessTypeCode;
	}

	public String getInvoicePrice() {
		return invoicePrice;
	}	

	public void setInvoicePrice(String invoicePrice) {
		this.invoicePrice = invoicePrice;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}
	
	public String getAdditionalType() {
		return additionalType;
	}

	public void setAdditionalType(String additionalType) {
		this.additionalType = additionalType;
	}
	
	public boolean isEmpty() {
		return (Utils.isStringEmpty(businessTypeCode) && Utils.isStringEmpty(invoicePrice) && 
		        Utils.isStringEmpty(currency) && Utils.isStringEmpty(additionalType));
	}	
}

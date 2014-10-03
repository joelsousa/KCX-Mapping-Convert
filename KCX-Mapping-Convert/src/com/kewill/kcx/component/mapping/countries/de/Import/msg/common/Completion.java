package com.kewill.kcx.component.mapping.countries.de.Import.msg.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: Import<br>
 * Created		: 20.09.2011<br>
 * Description	: Contains the Completion Data with all Fields used in KIDS.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class Completion extends KCXMessage {

	private String 	  registrationNumber;
    private String 	  itemNumber;   
    private String    atlasInFlow;    
    private String 	  commonUse;    
    private String 	  commodityCode;
    private Quantity  outflow;
    private Quantity  tradedVolume;        
    private String    information;
    private String    atlasAlignment;
    
	private enum EImportDocument {
		RegistrationNumber,				
		ItemNumber,	
		ATLASInFlow,
		CommonUse,	
		CommodityCode,
		Outflow,
		TradedVolume, 				
		AdditionalInformation,
		ComplementOfInformation,
		ATLASAlignment;
    }
	
	public Completion() {
		super();  
	}

    public Completion(XmlMsgScanner scanner) {
  		super(scanner);
  	}  	
 
  	public void startElement(Enum tag, String value, Attributes attr) {
  		if (value == null) {
  			switch ((EImportDocument) tag) {
  			case Outflow:
  				outflow = new Quantity(getScanner());
  				outflow.parse(tag.name());
					break;	
  			case TradedVolume:
  				tradedVolume = new Quantity(getScanner());
  				tradedVolume.parse(tag.name());
  			default:
  					return;
  			}
  		} else {

  			switch ((EImportDocument) tag) {
  				case RegistrationNumber:
  					setRegistrationNumber(value);
  					break;
  				case ItemNumber:
  	  				setItemNumber(value);
  	  				break;  					  								  				
  				case ATLASInFlow:
  					setATLASInFlow(value);
  					break;
  				case ATLASAlignment:
  					setATLASAlignment(value);
  					break;
  				case CommonUse:  				
  					setCommonUse(value);
  					break;
  				case CommodityCode:
  					setCommodityCode(value);
  					break;
  				case AdditionalInformation:  				  						  			
  				case ComplementOfInformation:
  					setInformation(value);
  					break;	
  				default: break;
  			}
  		}
  	}

  	public void stoppElement(Enum tag) {
  	}

  	public Enum translate(String token) {
  		try {
  			return EImportDocument.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
  	}

	public String getRegistrationNumber() {
		return registrationNumber;
	}
	public void setRegistrationNumber(String argument) {
		registrationNumber = argument;
	}

	public String getItemNumber() {
		return itemNumber;
	}
	public void setItemNumber(String value) {
		this.itemNumber = value;
	}
	
	public String getCommonUse() {
		return commonUse;
	}
	public void setCommonUse(String argument) {
		commonUse = argument;
	}
	
	public void setTradedVolume(Quantity argument) {
		this.tradedVolume = argument;
	}			
	public Quantity getTradedVolume() {
		return this.tradedVolume;
	}
	
	public void setSOutflow(Quantity argument) {
		this.outflow = argument;
	}			
	public Quantity getOutflow() {
		return this.outflow;
	}
	
	public void setATLASAlignment(String argument) {
		this.atlasAlignment = argument;
	}			
	public String getATLASAlignment() {
		return this.atlasAlignment;
	}
	
	public void setATLASInFlow(String argument) {
		this.atlasInFlow = argument;
	}			
	public String getATLASInFlow() {
		return this.atlasInFlow;
	}
			
	public void setCommodityCode(String argument) {
		this.commodityCode = argument;
	}			
	public String getCommodityCode() {
		return this.commodityCode;
	}
	
	public void setInformation(String argument) {
		this.information = argument;
	}			
	public String getInformation() {
		return this.information;
	}	
	
	public boolean isEmpty() {
		return (Utils.isStringEmpty(this.registrationNumber) && Utils.isStringEmpty(this.itemNumber) && 
				Utils.isStringEmpty(this.commonUse) && Utils.isStringEmpty(this.commodityCode) && 
				Utils.isStringEmpty(this.atlasInFlow) && Utils.isStringEmpty(this.atlasAlignment) && 
				Utils.isStringEmpty(this.information) &&
				outflow == null && tradedVolume == null);	        				
	}    
	
}

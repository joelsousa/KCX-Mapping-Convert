package com.kewill.kcx.component.mapping.countries.ie.msg.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: Ireland<br>
 * Created		: 05.06.2014<br>
 * Description	: Commodity.
 *                           
 * @author Iwaniuk
 * @version 1.0.00
 */
public class Commodity extends KCXMessage {
	
	private String descriptionText;			
    private String tariffClassificationCode;
    private String euTariffClassificationAdd1Code;
    private String euTariffClassificationAdd2Code;			
    private String euTariffClassificationNatCode;
    private String dangerousGoodsCodeIdentifier;
    private String euDutyRegimeCode;    
    //TODO-spaeter: private DutyTaxFee dutyTaxFee;
 
	private enum ECommodity {
		DescriptionText,
		TariffClassificationCode,
		EUTariffClassificationAdd1Code,
		EUTariffClassificationAdd2Code,
		EUTariffClassificationNatCode,
		DangerousGoodsCodeIdentifier,
		EUDutyRegimeCode,
		DutyTaxFee;
   }	

	public Commodity() {
		super();  
	}
	public Commodity(String person) {
		super();  		
	}	

	public Commodity(XmlMsgScanner scanner) {
  		super(scanner);
  	}
	public Commodity(XmlMsgScanner scanner, String person) {
  		super(scanner);  		
  	}	

  	public void startElement(Enum tag, String value, Attributes attr) {
  		if (value == null) {
  			switch ((ECommodity) tag) {	
  				case DutyTaxFee:
  					break;
				default:
  					return;
  			}
  		} else {
  			switch((ECommodity) tag) {   			
  				case DescriptionText:  				
  					setDescriptionText(value);
  					break;   				
  				case TariffClassificationCode:
  					setTariffClassificationCode(value);
  					break;   					
  				case EUTariffClassificationAdd1Code:
  					setEUTariffClassificationAdd1Code(value);
  					break;
  				case EUTariffClassificationAdd2Code:
  					setEUTariffClassificationAdd2Code(value);
  					break;
  				case EUTariffClassificationNatCode:
  					setEUTariffClassificationNatCode(value);
  					break;
  				case DangerousGoodsCodeIdentifier:
  					setDangerousGoodsCodeIdentifier(value);
  					break;
  				case EUDutyRegimeCode:
  					setEUDutyRegimeCode(value);
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
  			return ECommodity.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
  	}
       
	public String getDescriptionText() {
		return descriptionText;
	}
	public void setDescriptionText(String descriptionText) {
		this.descriptionText = descriptionText;
	}
	
	public String getTariffClassificationCode() {
		return tariffClassificationCode;
	}
	public void setTariffClassificationCode(String tariffClassificationCode) {
		this.tariffClassificationCode = tariffClassificationCode;
	}
	
	public String getEUTariffClassificationAdd1Code() {
		return euTariffClassificationAdd1Code;
	}
	public void setEUTariffClassificationAdd1Code(
			String euTariffClassificationAdd1Code) {
		this.euTariffClassificationAdd1Code = euTariffClassificationAdd1Code;
	}
	
	public String getEUTariffClassificationAdd2Code() {
		return euTariffClassificationAdd2Code;
	}
	public void setEUTariffClassificationAdd2Code(
			String euTariffClassificationAdd2Code) {
		this.euTariffClassificationAdd2Code = euTariffClassificationAdd2Code;
	}
	public String getEUTariffClassificationNatCode() {
		return euTariffClassificationNatCode;
	}
	public void setEUTariffClassificationNatCode(
			String euTariffClassificationNatCode) {
		this.euTariffClassificationNatCode = euTariffClassificationNatCode;
	}
	
	public String getDangerousGoodsCodeIdentifier() {
		return dangerousGoodsCodeIdentifier;
	}
	public void setDangerousGoodsCodeIdentifier(String dangerousGoodsCodeIdentifier) {
		this.dangerousGoodsCodeIdentifier = dangerousGoodsCodeIdentifier;
	}
	
	public String getEUDutyRegimeCode() {
		return euDutyRegimeCode;
	}
	public void setEUDutyRegimeCode(String euDutyRegimeCode) {
		this.euDutyRegimeCode = euDutyRegimeCode;
	}
	
	public boolean isEmpty() {
		return Utils.isStringEmpty(this.tariffClassificationCode) && Utils.isStringEmpty(this.euTariffClassificationAdd1Code) && 
			Utils.isStringEmpty(this.euTariffClassificationAdd2Code) && Utils.isStringEmpty(this.euTariffClassificationNatCode) && 
			Utils.isStringEmpty(this.euTariffClassificationAdd2Code) && 
			Utils.isStringEmpty(this.euDutyRegimeCode);
	}    
}

package com.kewill.kcx.component.mapping.countries.de.Import.msg.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.CommodityCode;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: Import<br>
 * Created		: 30.10.2012<br>
 * Description	: CountrySpecificValues used in KIDS for CH. 
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class SpecificValuesCH extends KCXMessage {
	
    private String customsNetWeigth;
    private String netDuty;
    private String kindOfArticle;
    private String taraAddition;
    private String nonRegularDutyRate;
    private String vatCode; 
    private ConfirmationCodes confirmationCodes;
    
	private enum ESpecificValuesCH {
		//KIDS							//UIDS		
		CustomsNetWeigth,
		NetDuty,
		KindOfArticle,
		TaraAddition,
		NonRegularDutyRate,
		VATCode,
		ConformationCodes,		
    }

	public SpecificValuesCH() {
		super();  
	}

    public SpecificValuesCH(XmlMsgScanner scanner) {
  		super(scanner);
  	}
 
  	public void startElement(Enum tag, String value, Attributes attr) {
  		if (value == null) {
  			switch ((ESpecificValuesCH) tag) {
  			case ConformationCodes:
  				confirmationCodes = new ConfirmationCodes(getScanner());
  				confirmationCodes.parse(tag.name());
					break;  					  				 				
				
  			default:
  					return;
  			}
  		} else {

  			switch ((ESpecificValuesCH) tag) {  				 					  				 			
  				case CustomsNetWeigth:  				
  					setCustomsNetWeigth(value);
  					break;
  				case NetDuty:  				
  					setNetDuty(value);
  					break;  
  				case KindOfArticle:
  					setKindOfArticle(value);
  					break;  					  				 				
  				case TaraAddition:  				
  					setTaraAddition(value);  					
  					break;
  				case NonRegularDutyRate:
  					setNonRegularDutyRate(value);
  					break;
  				case VATCode:  				
  					setVATCode(value);
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
  			return ESpecificValuesCH.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
  	}  	
	
	public String getCustomsNetWeigth() {
		return customsNetWeigth;
	}
	public void setCustomsNetWeigth(String value) {
		customsNetWeigth = value;
	}

	public String getNetDuty() {
		return netDuty;
	}
	public void setNetDuty(String value) {
		netDuty = value;
	}
		
	public String getKindOfArticle() {
		return kindOfArticle;
	}
	public void setKindOfArticle(String value) {
		kindOfArticle = value;
	}
	
	public String getTaraAddition() {
		return taraAddition;
	}
	public void setTaraAddition(String value) {
		taraAddition = value;
	}
	
	public String getNonRegularDutyRate() {
		return nonRegularDutyRate;
	}
	public void setNonRegularDutyRate(String value) {
		nonRegularDutyRate = value;
	}
	public String getVATCode() {
		return vatCode;
	}
	public void setVATCode(String value) {
		vatCode = value;
	}
		
	public ConfirmationCodes getConformationCodes() {
		return confirmationCodes;
	}
	public void setConformationCodes(ConfirmationCodes value) {
		confirmationCodes = value;
	}
	
	
	public boolean isEmpty() {
		return (Utils.isStringEmpty(customsNetWeigth) && Utils.isStringEmpty(netDuty) && 
				Utils.isStringEmpty(kindOfArticle) && Utils.isStringEmpty(taraAddition) && 
				Utils.isStringEmpty(vatCode) && (confirmationCodes == null)); 		     
	}
	
}

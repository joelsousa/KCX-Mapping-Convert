package com.kewill.kcx.component.mapping.countries.de.aes.msg.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: Export<br>
 * Created		: 10.09.2008<br>
 * Description	: Contains the CommodityCode Data with all Fields used in KIDS.
 * 
 * @author Houdek
 * @version 1.0.00
 */
public class CommodityCode extends KCXMessage {

    private String tarifCode   	         = "";
    private String euTarifCode           = "";
    private String tarifAddition1        = "";
    private String tarifAddition2        = "";
    private String addition              = "";
    private String addition2             = "";
    private String addition3             = "";    
    private String confirmation          = "";   //AK20110420

	private enum ECommodityCode {
		TarifCode,
		EUTarifCode,
		TarifAddition1,
		TarifAddition2,
		Addition,
		Addition2,
		Addition3,
		Confirmation;
     }
	
      public CommodityCode() {
	      	super();
    }

  	public CommodityCode(XmlMsgScanner scanner) {
  		super(scanner);
  	}

  	
  	public void startElement(Enum tag, String value, Attributes attr) {
  		if (value == null) {
  			switch ((ECommodityCode) tag) {
  			default:
  					return;
  			}
  		} else {
  			switch ((ECommodityCode) tag) {

  				case TarifCode:
  					setTarifCode(value);
  					break;

  				case EUTarifCode:
  					setEUTarifCode(value);
  					break;

  				case TarifAddition1:
  					setTarifAddition1(value);
  					break;

  				case TarifAddition2:
  					setTarifAddition2(value);
  					break;

  				case Addition:
  					setAddition(value);
  					break;

  				case Addition2:
  					setAddition2(value);
  					break;
  					
  				case Addition3:
  					setAddition3(value);
  					break;

  				case Confirmation:
  					setConfirmation(value);
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
  			return ECommodityCode.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
  	}

// -----

	public String getTarifCode() {
		return tarifCode;
	}
	public void setTarifCode(String tarifCode) {
		this.tarifCode = tarifCode;
	}
	public String getEUTarifCode() {
		return euTarifCode;
	}
	public void setEUTarifCode(String tarifCode) {
		euTarifCode = tarifCode;
	}
	public String getTarifAddition1() {
		return tarifAddition1;
	}
	public void setTarifAddition1(String tarifAddition1) {
		this.tarifAddition1 = tarifAddition1;
	}
	public String getTarifAddition2() {
		return tarifAddition2;
	}
	public void setTarifAddition2(String tarifAddition2) {
		this.tarifAddition2 = tarifAddition2;
	}
	public String getAddition() {
		return addition;
	}
	public void setAddition(String addition) {
		this.addition = addition;
	}
	public String getAddition2() {
		return addition2;
	
	}
	public void setAddition2(String addition2) {
		this.addition2 = Utils.checkNull(addition2);
	}
	public String getAddition3() {
		return addition3;	
	}
	public void setAddition3(String addition3) {
		this.addition3 = Utils.checkNull(addition3);
	}

	public String getConfirmation() {
		return confirmation;
	}
	public void setConfirmation(String confirmation) {
		this.confirmation = Utils.checkNull(confirmation);
	}
	
	public boolean isEmpty() {
		return (Utils.isStringEmpty(tarifCode) && Utils.isStringEmpty(euTarifCode) && 
				Utils.isStringEmpty(tarifAddition1) && Utils.isStringEmpty(tarifAddition2) && 
				Utils.isStringEmpty(addition) && Utils.isStringEmpty(addition2) &&
		        Utils.isStringEmpty(addition3) &&Utils.isStringEmpty(confirmation)); 
	}
}

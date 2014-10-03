package com.kewill.kcx.component.mapping.countries.de.ncts.msg.common;

import javax.xml.stream.XMLEventReader;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module    	: CommodityCode 
 * Created     	: 31.08.2008
 * Description 	: Contains the CommodityCode Data
 * 			   	  with all Fields used in KIDS/UIDS.
 * 
 * @author Jude Eco 
 * @version 1.0.00
 */
public class CommodityCode extends KCXMessage {

    private String tarifCode;
    private String eUTarifCode;
    private String tarifAddition1;
    private String tarifAddition2;
    private String addition;
    private String addition2;
    private String addition3;
    
    private XMLEventReader parser = null;
    
	private enum ECommodityCode {
		TarifCode,
		EUTarifCode,
		TarifAddition1,
		TarifAddition2,
		Addition,
		Addition2,
		Addition3;
     }
    
    public CommodityCode() {
    	super();
    }

    public CommodityCode(XMLEventReader parser) {
  		super(parser);
  		this.parser = parser;
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

	public String getTarifCode() {
		return tarifCode;
	}

	public void setTarifCode(String tarifCode) {
		this.tarifCode = tarifCode;
	}

	public String getEUTarifCode() {
		return eUTarifCode;
	}

	public void setEUTarifCode(String tarifCode) {
		eUTarifCode = tarifCode;
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

	public XMLEventReader getParser() {
		return parser;
	}

	public void setParser(XMLEventReader parser) {
		this.parser = parser;
	}
	
	public boolean isEmpty() {
		if (Utils.isStringEmpty(this.tarifCode) && 
				Utils.isStringEmpty(this.eUTarifCode) && 
				Utils.isStringEmpty(this.tarifAddition1) && 
				Utils.isStringEmpty(this.tarifAddition2) && 
				Utils.isStringEmpty(this.addition) && 
				Utils.isStringEmpty(this.addition2) && 
				Utils.isStringEmpty(this.addition3)) {
			return true;
		} else {
			return false;
		}
	}
}

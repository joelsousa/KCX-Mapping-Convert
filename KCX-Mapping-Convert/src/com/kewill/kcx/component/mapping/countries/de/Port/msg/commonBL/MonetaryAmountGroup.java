package com.kewill.kcx.component.mapping.countries.de.Port.msg.commonBL;

import java.util.ArrayList;
import java.util.List;

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

public class MonetaryAmountGroup extends KCXMessage {
		
	private List<MonetaryAmount> monetaryAmountList;
	
	private enum EMonetaryAmountGroup {		
		MonetaryAmount;			       			
   }	

	public MonetaryAmountGroup() {
		super();  
	}

	public MonetaryAmountGroup(XmlMsgScanner scanner) {
  		super(scanner);
  	}
	
  	public void startElement(Enum tag, String value, Attributes attr) {
  		if (value == null) {
  			switch ((EMonetaryAmountGroup) tag) {  			
				case MonetaryAmount:
					MonetaryAmount temp = new MonetaryAmount(getScanner());  	
  					temp.parse(tag.name());
  					addMonetaryAmountList(temp);
  					break; 
  				
				default:
  					break;
  			}
  		} else {

  			switch((EMonetaryAmountGroup) tag) {   
  				default:
  					break;
  			}
  		}
  	}

  	public void stoppElement(Enum tag) {
  	}

  	public Enum translate(String token) {
  		try {
  			return EMonetaryAmountGroup.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
  	}

	
	public List<MonetaryAmount> getMonetaryAmountList() {
		return monetaryAmountList;
	}    
	public void setMonetaryAmountList(List<MonetaryAmount> list) {
		this.monetaryAmountList = list;
	}
	public void addMonetaryAmountList(MonetaryAmount value) {
		if (monetaryAmountList == null) {
			monetaryAmountList = new ArrayList<MonetaryAmount>();
		}
		this.monetaryAmountList.add(value);
	}
	
   
   
}


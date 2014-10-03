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

public class FreightAndChargeGroup extends KCXMessage {
		
	private List<FreightAndCharge> freightAndChargeList;
	
	private enum EFreightAndChargeGroup {		
		FreightAndCharge;			       			
   }	

	public FreightAndChargeGroup() {
		super();  
	}

	public FreightAndChargeGroup(XmlMsgScanner scanner) {
  		super(scanner);
  	}
	
  	public void startElement(Enum tag, String value, Attributes attr) {
  		if (value == null) {
  			switch ((EFreightAndChargeGroup) tag) {  			
				case FreightAndCharge:
					FreightAndCharge temp = new FreightAndCharge(getScanner());  	
  					temp.parse(tag.name());
  					addFreightAndChargeList(temp);
  					break; 
  				
				default:
  					break;
  			}
  		} else {

  			switch((EFreightAndChargeGroup) tag) {   
  				default:
  					break;
  			}
  		}
  	}

  	public void stoppElement(Enum tag) {
  	}

  	public Enum translate(String token) {
  		try {
  			return EFreightAndChargeGroup.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
  	}
	
	public List<FreightAndCharge> getFreightAndChargeList() {
		return freightAndChargeList;
	}    
	public void setFreightAndChargeList(List<FreightAndCharge> list) {
		this.freightAndChargeList = list;
	}
	public void addFreightAndChargeList(FreightAndCharge value) {
		if (freightAndChargeList == null) {
			freightAndChargeList = new ArrayList<FreightAndCharge>();
		}
		this.freightAndChargeList.add(value);
	}	
}


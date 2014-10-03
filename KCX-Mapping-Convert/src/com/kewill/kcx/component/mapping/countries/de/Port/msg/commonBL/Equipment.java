package com.kewill.kcx.component.mapping.countries.de.Port.msg.commonBL;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: Port - BillOfLading.<br>
 * Created		: 10.04.2012<br>
 * Description	:
 * 
 * @author iwaniuk
 * @version 1.0.00
 */

public class Equipment extends KCXMessage {
	
	private List<EquipmentQualifier> equipmentQualifierList;
	
	private enum EEquipment {	
		EquipmentQualifier;				       	
   }	

	public Equipment() {
		super();  
	}

	public Equipment(XmlMsgScanner scanner) {
  		super(scanner);
  	}
	
  	public void startElement(Enum tag, String value, Attributes attr) {
  		if (value == null) {
  			switch ((EEquipment) tag) {  
  				case EquipmentQualifier:
  					EquipmentQualifier temp = new EquipmentQualifier(getScanner());  	
  					temp.parse(tag.name());				
  					addEquipmentQualifierList(temp);
  					break;
				default:
  					break;
  			}
  		} else {

  			switch((EEquipment) tag) {   			  				   			
  				default:
  					break;
  			}
  		}
  	}

  	public void stoppElement(Enum tag) {
  	}

  	public Enum translate(String token) {
  		try {
  			return EEquipment.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
  	}
		
	public List<EquipmentQualifier> getEquipmentQualifierList() {
		return equipmentQualifierList;
	}    
	public void setEquipmentQualifierList(List<EquipmentQualifier> list) {
		this.equipmentQualifierList = list;
	}
	public void addEquipmentQualifierList(EquipmentQualifier value) {
		if (equipmentQualifierList == null) {
			equipmentQualifierList = new ArrayList<EquipmentQualifier>();
		}
		this.equipmentQualifierList.add(value);
	}
	
	public boolean isEmpty() {
		return (equipmentQualifierList == null || equipmentQualifierList.size() == 0); 
	}
}


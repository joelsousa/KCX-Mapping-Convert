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

public class AllocatedEquipment extends KCXMessage {
		
	private String equipmentIdentificationNumber = "";
	private DistributedOverSeveralContainer distributedOverSeveralContainer;
	
	private enum EAttEqQu {	
		EquipmentIdentificationNumber,
		DistributedOverSeveralContainer;			       		
   }	

	public AllocatedEquipment() {
		super();  
	}

	public AllocatedEquipment(XmlMsgScanner scanner) {
  		super(scanner);
  	}
	
  	public void startElement(Enum tag, String value, Attributes attr) {
  		if (value == null) {
  			switch ((EAttEqQu) tag) {  			
				case DistributedOverSeveralContainer:
					distributedOverSeveralContainer = new DistributedOverSeveralContainer(getScanner());	
					distributedOverSeveralContainer.parse(tag.name());					
  					break;   				
				default:
  					break;
  			}
  		} else {

  			switch((EAttEqQu) tag) {  
  			case EquipmentIdentificationNumber:
  				setEquipmentIdentificationNumber(value);
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
  			return EAttEqQu.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
  	}
	
	  	
  	public String getEquipmentIdentificationNumber() {
		return equipmentIdentificationNumber;
	}    
	public void setEquipmentIdentificationNumber(String value) {
		this.equipmentIdentificationNumber = value;
	}
  	
	public DistributedOverSeveralContainer getDistributedOverSeveralContainert() {
		return distributedOverSeveralContainer;
	}		
    public void setDistributedOverSeveralContainer(DistributedOverSeveralContainer value) {
		this.distributedOverSeveralContainer = value;
	}
   
    public boolean isEmpty() {
		return Utils.isStringEmpty(equipmentIdentificationNumber) && distributedOverSeveralContainer == null; 
	}
}


package com.kewill.kcx.component.mapping.countries.ie.msg.common;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: Ireland<br>
 * Created		: 04.06.2014<br>
 * Description	: TransportEquipment.
 *                           
 * @author Iwaniuk
 * @version 1.0.00
 */
public class TransportEquipment extends KCXMessage {
	
	
    private Identifier equipmentIdentification;
   
 
	private enum ETransportEquipment {			
		EquipmentIdentification;
   }	

	public TransportEquipment() {
		super();  
	}
	public TransportEquipment(String person) {
		super();  		
	}	

	public TransportEquipment(XmlMsgScanner scanner) {
  		super(scanner);
  	}
	public TransportEquipment(XmlMsgScanner scanner, String person) {
  		super(scanner);  		
  	}	

  	public void startElement(Enum tag, String value, Attributes attr) {
  		if (value == null) {
  			switch ((ETransportEquipment) tag) {
				case EquipmentIdentification:
					equipmentIdentification = new Identifier(getScanner());  	
					equipmentIdentification.parse(tag.name());
  					break;   				
				default:
  					return;
  			}
  		} else {
  			switch((ETransportEquipment) tag) {   						
                default:
                	break;
  			}
  		}
  	}

  	public void stoppElement(Enum tag) {
  	}

  	public Enum translate(String token) {
  		try {
  			return ETransportEquipment.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
  	}
	
	public Identifier getAddress() {
		return equipmentIdentification;
	}
    public void setAddress(Identifier argument) {
		this.equipmentIdentification = argument;
	}
       
	public boolean isEmpty() {
		return this.equipmentIdentification  == null;
	}    
}

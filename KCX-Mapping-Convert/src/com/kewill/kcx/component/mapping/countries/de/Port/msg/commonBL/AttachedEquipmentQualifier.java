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

public class AttachedEquipmentQualifier extends KCXMessage {
		
	private AttachedEquipmentDetails reeferGenerator;
	private AttachedEquipmentDetails trailer;
	private AttachedEquipmentDetails chassis;
	
	private enum EContact {	
		ReeferGenerator,
		Trailer,
		Chassis;			       		
   }	

	public AttachedEquipmentQualifier() {
		super();  
	}

	public AttachedEquipmentQualifier(XmlMsgScanner scanner) {
  		super(scanner);
  	}
	
  	public void startElement(Enum tag, String value, Attributes attr) {
  		if (value == null) {
  			switch ((EContact) tag) {  			
				case ReeferGenerator:
					AttachedEquipmentDetails reeferGenerator = new AttachedEquipmentDetails(getScanner());
					reeferGenerator.parse(tag.name());					
  					break; 
				case Trailer:
					AttachedEquipmentDetails trailer = new AttachedEquipmentDetails(getScanner());	
					trailer.parse(tag.name());					
  					break;  
				case Chassis:
					AttachedEquipmentDetails chassis = new AttachedEquipmentDetails(getScanner());	
					chassis.parse(tag.name());					
  					break;  
				default:
  					break;
  			}
  		} else {

  			switch((EContact) tag) {   			  				  		
  				default:
  					break;
  			}
  		}
  	}

  	public void stoppElement(Enum tag) {
  	}

  	public Enum translate(String token) {
  		try {
  			return EContact.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
  	}
		
	public AttachedEquipmentDetails getReeferGenerator() {
		return reeferGenerator;
	}		
    public void setReeferGenerator(AttachedEquipmentDetails argument) {
		this.reeferGenerator = argument;
	}
    
    public AttachedEquipmentDetails getTrailer() {
		return trailer;
	}		
    public void setTrailer(AttachedEquipmentDetails argument) {
		this.trailer = argument;
	}
    
    public AttachedEquipmentDetails getChassis() {
		return chassis;
	}		
    public void setChassis(AttachedEquipmentDetails argument) {
		this.chassis = argument;
	}
}


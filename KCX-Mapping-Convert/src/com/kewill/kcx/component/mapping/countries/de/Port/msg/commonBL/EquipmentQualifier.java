package com.kewill.kcx.component.mapping.countries.de.Port.msg.commonBL;

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

public class EquipmentQualifier extends KCXMessage {
		
	private EquipmentDetails container;
	private EquipmentDetails trailer;
	
	private enum EEquipmentQualifier {	
		Container,
		Trailer;			       		
   }	

	public EquipmentQualifier() {
		super();  
	}

	public EquipmentQualifier(XmlMsgScanner scanner) {
  		super(scanner);
  	}
	
  	public void startElement(Enum tag, String value, Attributes attr) {
  		if (value == null) {
  			switch ((EEquipmentQualifier) tag) {  
  				case Container:
  					container = new EquipmentDetails(getScanner());  	
  					container.parse(tag.name());
					break; 
  				case Trailer:
  					trailer = new EquipmentDetails(getScanner());  	
  					trailer.parse(tag.name());  					
  					break;  				
				default:
  					break;
  			}
  		} else {
  			switch((EEquipmentQualifier) tag) {   			  				   			
  				default:
  					break;
  			}
  		}
  	}

  	public void stoppElement(Enum tag) {
  	}

  	public Enum translate(String token) {
  		try {
  			return EEquipmentQualifier.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
  	}
	
	public EquipmentDetails getContainer() {
		return container;
	}    
	public void setContainer(EquipmentDetails value) {
		this.container = value;
	}
		
	public EquipmentDetails getTrailer() {
		return trailer;
	}    
	public void setTrailer(EquipmentDetails value) {
		this.trailer = value;
	}
	
	public boolean isEmpty() {
		return container == null && trailer == null; 
	}
}


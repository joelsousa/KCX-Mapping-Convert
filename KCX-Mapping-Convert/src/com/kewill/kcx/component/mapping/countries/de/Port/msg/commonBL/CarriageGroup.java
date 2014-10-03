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

public class CarriageGroup extends KCXMessage {
		
	private List<Carriage> carriageList;
	
	private enum ECarriageGroup {		
		Carriage;			       			
   }	

	public CarriageGroup() {
		super();  
	}

	public CarriageGroup(XmlMsgScanner scanner) {
  		super(scanner);
  	}
	
  	public void startElement(Enum tag, String value, Attributes attr) {
  		if (value == null) {
  			switch ((ECarriageGroup) tag) {  			
				case Carriage:
					Carriage temp = new Carriage(getScanner());  	
  					temp.parse(tag.name());
  					addCarriageList(temp);
  					break; 
  				
				default:
  					break;
  			}
  		} else {

  			switch((ECarriageGroup) tag) {   
  				default:
  					break;
  			}
  		}
  	}

  	public void stoppElement(Enum tag) {
  	}

  	public Enum translate(String token) {
  		try {
  			return ECarriageGroup.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
  	}
	
	public List<Carriage> getCarriageList() {
		return carriageList;
	}    
	public void setCarriageList(List<Carriage> list) {
		this.carriageList = list;
	}
	public void addCarriageList(Carriage value) {
		if (carriageList == null) {
			carriageList = new ArrayList<Carriage>();
		}
		this.carriageList.add(value);
	}	
}


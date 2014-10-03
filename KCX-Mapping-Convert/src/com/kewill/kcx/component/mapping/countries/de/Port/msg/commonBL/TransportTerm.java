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

public class TransportTerm extends KCXMessage {
		
	private String code;
	private List<String> textList;
	private Place place;
	
	private enum ETransportTerm {	
		Code,
		Text,
		Place;				       	
   }	

	public TransportTerm() {
		super();  
	}

	public TransportTerm(XmlMsgScanner scanner) {
  		super(scanner);
  	}
	
  	public void startElement(Enum tag, String value, Attributes attr) {
  		if (value == null) {
  			switch ((ETransportTerm) tag) { 
  				case Place:
					place = new Place(getScanner());	
					place.parse(tag.name());				
					break; 				 			
				default:
  					break;
  			}
  		} else {

  			switch((ETransportTerm) tag) {   			
  				case Code:
  					setCode(value);
  					break;   
  				case Text:
  					addTextList(value);
  				default:
  					break;
  			}
  		}
  	}

  	public void stoppElement(Enum tag) {
  	}

  	public Enum translate(String token) {
  		try {
  			return ETransportTerm.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
  	}
	
	public String getCode() {
		return code;
	}    
	public void setCode(String value) {
		this.code = value;
	}
	
	public List<String> getTextList() {
		return textList;
	} 
	public void setTextList(List<String> list) {
		this.textList = list;
	}
	public void addTextList(String value) {
		if (textList == null) {
			textList = new ArrayList<String>();
		}
		this.textList.add(value);
	}	
	
	public Place getPlace() {
		return place;
	}    
	public void setPlace(Place place) {
		this.place = place;
	}	
  
}


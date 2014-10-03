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

public class TextAAD extends KCXMessage {

	private List<String> textList;
	
	private enum EContact {		
		Text;			       		
   }	

	public TextAAD() {
		super();  
	}

	public TextAAD(XmlMsgScanner scanner) {
  		super(scanner);
  	}
	
  	public void startElement(Enum tag, String value, Attributes attr) {
  		if (value == null) {
  			switch ((EContact) tag) {  							   			
				default:
  					break;
  			}
  		} else {

  			switch((EContact) tag) {   			
  				case Text:
  					addTextList(value);
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
  			return EContact.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
  	}
		
	public List<String> getTextList() {
		return textList;
	}		
    public void setTextList(List<String> list) {
		this.textList = list;
	}
    public void addTextList(String argument) {
    	if (textList == null) {
    		textList = new ArrayList<String>();
    	}
		this.textList.add(argument);
	}
    
    public boolean isEmpty() {
    	return  textList == null; 
    }
}


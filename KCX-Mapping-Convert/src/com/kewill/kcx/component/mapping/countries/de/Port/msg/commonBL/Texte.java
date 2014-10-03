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

public class Texte extends KCXMessage {
		
	private List<TextBL> textList;
	
	private enum EBLText {	
		Text;			       		
   }	

	public Texte() {
		super();  
	}

	public Texte(XmlMsgScanner scanner) {
  		super(scanner);
  	}
	
  	public void startElement(Enum tag, String value, Attributes attr) {
  		if (value == null) {
  			switch ((EBLText) tag) {  			
				default:
  					break;
  			}
  		} else {

  			switch((EBLText) tag) {   			
  				case Text:  					
  					TextBL txt = new TextBL();  					
  					addTextList(txt);
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
  			return EBLText.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
  	}

	
	public List<TextBL> getTextList() {
		return textList;
	}    
	public void setText(List<TextBL> list) {
		this.textList = list;
	}
	
	 public void addTextList(TextBL txt) {	
	    	if (textList == null) {
	    		textList = new ArrayList<TextBL>();	
	    	}
	    	textList.add(txt);
	    }
	    
}


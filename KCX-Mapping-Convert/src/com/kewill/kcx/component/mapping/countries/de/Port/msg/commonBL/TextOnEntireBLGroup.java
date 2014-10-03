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

public class TextOnEntireBLGroup extends KCXMessage {
		
	private List<TextOnEntireBL> textOnEntireBLList;
	
	private enum ETextOnEntireBLGroup {		
		TextOnEntireBL;			       			
   }	

	public TextOnEntireBLGroup() {
		super();  
	}

	public TextOnEntireBLGroup(XmlMsgScanner scanner) {
  		super(scanner);
  	}
	
  	public void startElement(Enum tag, String value, Attributes attr) {
  		if (value == null) {
  			switch ((ETextOnEntireBLGroup) tag) {  			
				case TextOnEntireBL:
					TextOnEntireBL temp = new TextOnEntireBL(getScanner());  	
  					temp.parse(tag.name());
  					addTextOnEntireBLList(temp);
  					break; 
  				
				default:
  					break;
  			}
  		} else {

  			switch((ETextOnEntireBLGroup) tag) {   
  				default:
  					break;
  			}
  		}
  	}

  	public void stoppElement(Enum tag) {
  	}

  	public Enum translate(String token) {
  		try {
  			return ETextOnEntireBLGroup.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
  	}

	
	public List<TextOnEntireBL> getTextOnEntireBLList() {
		return textOnEntireBLList;
	}    
	public void setTextOnEntireBLList(List<TextOnEntireBL> list) {
		this.textOnEntireBLList = list;
	}
	public void addTextOnEntireBLList(TextOnEntireBL value) {
		if (textOnEntireBLList == null) {
			textOnEntireBLList = new ArrayList<TextOnEntireBL>();
		}
		this.textOnEntireBLList.add(value);
	}
	
   
   
}


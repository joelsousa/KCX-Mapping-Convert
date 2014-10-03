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

public class PackingLevel3 extends KCXMessage {
		
	private ItemDetails itemDetails;
	private ItemText itemText;		
	
	private enum ECarriage {	
		DetailsOnItem,
		TextOnItem;			       	
   }	

	public PackingLevel3() {
		super();  
	}

	public PackingLevel3(XmlMsgScanner scanner) {
  		super(scanner);
  	}
	
  	public void startElement(Enum tag, String value, Attributes attr) {
  		if (value == null) {
  			switch ((ECarriage) tag) {  
  				case DetailsOnItem:
  					itemDetails = new ItemDetails(getScanner());  	
  					itemDetails.parse(tag.name());
  				case TextOnItem:
  					itemText = new ItemText(getScanner());  	
  					itemText.parse(tag.name());  				 			
				default:
  					break;
  			}
  		} else {

  			switch((ECarriage) tag) {     			
  				default:
  					break;
  			}
  		}
  	}

  	public void stoppElement(Enum tag) {
  	}

  	public Enum translate(String token) {
  		try {
  			return ECarriage.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
  	}
	
  	public ItemDetails getDetailsOnItem() {
		return itemDetails;
	}    
	public void setDetailsOnItem(ItemDetails value) {
		this.itemDetails = value;
	}
	
	public ItemText getTextOnItem() {
		return itemText;
	}    
	public void setTextOnItem(ItemText value) {
		this.itemText = value;
	}
}


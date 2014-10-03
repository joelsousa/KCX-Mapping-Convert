package com.kewill.kcx.component.mapping.countries.de.Port.msg.commonBL;

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

public class GoodsItem extends KCXMessage {
		
	private String itemNumber;	
	private PackingLevel1 packingLevel1;
	
	
	private enum ECarriage {	
		GoodsItemNumber,		
		FirstPackingLevel;		
   }	

	public GoodsItem() {
		super();  
	}

	public GoodsItem(XmlMsgScanner scanner) {
  		super(scanner);
  	}
	
  	public void startElement(Enum tag, String value, Attributes attr) {
  		if (value == null) {
  			switch ((ECarriage) tag) {  
  				case FirstPackingLevel:
  					packingLevel1 = new PackingLevel1(getScanner());  	
  					packingLevel1.parse(tag.name());
					break;				
				default:
  					break;
  			}
  		} else {

  			switch((ECarriage) tag) {   
  			case GoodsItemNumber:
  				setGoodsItemNumber(value);
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
  			return ECarriage.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
  	}
	
	public String getGoodsItemNumber() {
		return itemNumber;
	}    
	public void setGoodsItemNumber(String value) {
		this.itemNumber = value;
	}
		
	public PackingLevel1 getFirstPackingLevel() {
		return packingLevel1;
	}    
	public void setFirstPackingLevel(PackingLevel1 level) {
		this.packingLevel1 = level;
	}
	
	public boolean isEmpty() {
		return Utils.isStringEmpty(itemNumber) && packingLevel1 == null; 
	}
}


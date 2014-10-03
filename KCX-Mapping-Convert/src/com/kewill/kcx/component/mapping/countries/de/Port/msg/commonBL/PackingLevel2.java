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

public class PackingLevel2 extends KCXMessage {
		
	private ItemDetails itemDetails;
	private ItemText itemText;	
	private List<PackingLevel3> packingLevel3List;
	
	private enum EPackingLevel2 {	
		DetailsOnItem,
		TextOnItem,		
		ThirdPackingLevel;			       			
   }	

	public PackingLevel2() {
		super();  
	}

	public PackingLevel2(XmlMsgScanner scanner) {
  		super(scanner);
  	}
	
  	public void startElement(Enum tag, String value, Attributes attr) {
  		if (value == null) {
  			switch ((EPackingLevel2) tag) {  
  				case DetailsOnItem:
  					itemDetails = new ItemDetails(getScanner());  	
  					itemDetails.parse(tag.name());
  					break;
  				case TextOnItem:
  					itemText = new ItemText(getScanner());  	
  					itemText.parse(tag.name());
  					break;
  				case ThirdPackingLevel:
  					PackingLevel3 level3 = new PackingLevel3(getScanner());  	
  					level3.parse(tag.name());
  					addPackingLevel3List(level3);
					break;   				
				default:
  					break;
  			}
  		} else {

  			switch((EPackingLevel2) tag) {     			
  				default:
  					break;
  			}
  		}
  	}

  	public void stoppElement(Enum tag) {
  	}

  	public Enum translate(String token) {
  		try {
  			return EPackingLevel2.valueOf(token);
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
	
	public List<PackingLevel3> getPackingLevel3List() {
		return packingLevel3List;
	}    
	public void setPackingLevel3List(List<PackingLevel3> list) {
		this.packingLevel3List = list;
	}
	public void addPackingLevel3List(PackingLevel3 value) {
		if (packingLevel3List == null) {
			packingLevel3List = new ArrayList<PackingLevel3>();
		}
		this.packingLevel3List.add(value);
	}
	
	public boolean isEmpty() {
		return itemDetails == null && itemText == null && packingLevel3List == null; 
	}
}


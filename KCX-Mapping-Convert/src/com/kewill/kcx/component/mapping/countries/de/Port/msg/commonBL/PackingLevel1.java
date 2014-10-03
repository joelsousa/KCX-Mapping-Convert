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

public class PackingLevel1 extends KCXMessage {
		
	private ItemDetails itemDetails;
	private ItemText itemText;
	private String grossWeight;
	private List<AllocatedEquipment> allocatedEquipmentList;
	private List<DangerousGoods> dangerousGoodsList;
	private List<PackingLevel2> packingLevel2List;
	
	private enum ECarriage {	
		DetailsOnItem,
		TextOnItem,
		GrossWeightKilogram,
		AllocatedEquipment,
		DangerousGoods,
		SecondPackingLevel;			       		
   }	

	public PackingLevel1() {
		super();  
	}

	public PackingLevel1(XmlMsgScanner scanner) {
  		super(scanner);
  	}
	
  	public void startElement(Enum tag, String value, Attributes attr) {
  		if (value == null) {
  			switch ((ECarriage) tag) {  
  				case DetailsOnItem:
  					itemDetails = new ItemDetails(getScanner());  	
  					itemDetails.parse(tag.name());
				    break;  
  				case TextOnItem:
  					itemText = new ItemText(getScanner());  	
  					itemText.parse(tag.name());
					break;
  				case AllocatedEquipment:
  					AllocatedEquipment all = new AllocatedEquipment(getScanner());  	
  					all.parse(tag.name());
  					addAllocatedEquipmentList(all);
					break;
  				case DangerousGoods:
  					DangerousGoods dg = new DangerousGoods(getScanner());  	
  					dg.parse(tag.name());
  					addDangerousGoodsList(dg);
					break;  
  				case SecondPackingLevel:
  					PackingLevel2 level2 = new PackingLevel2(getScanner());  	
  					level2.parse(tag.name());
  					addPackingLevel2List(level2);
					break;   				
				default:
  					break;
  			}
  		} else {

  			switch((ECarriage) tag) {   
  			case GrossWeightKilogram:
  				setGrossWeightKilogram(value);
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
	
	public String getGrossWeightKilogram() {
		return grossWeight;
	}    
	public void setGrossWeightKilogram(String value) {
		this.grossWeight = value;
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
		
	public List<AllocatedEquipment> getAllocatedEquipmentList() {
		return allocatedEquipmentList;
	}    
	public void setAllocatedEquipmentList(List<AllocatedEquipment> list) {
		this.allocatedEquipmentList = list;
	}
	public void addAllocatedEquipmentList(AllocatedEquipment value) {
		if (value == null) { //AK20121212
			return;
		}
		if (allocatedEquipmentList == null) {
			allocatedEquipmentList = new ArrayList<AllocatedEquipment>();
		}
		this.allocatedEquipmentList.add(value);
	}
	
	public List<DangerousGoods> getDangerousGoodsList() {
		return dangerousGoodsList;
	}    
	public void setDangerousGoodsList(List<DangerousGoods> list) {
		this.dangerousGoodsList = list;
	}
	public void addDangerousGoodsList(DangerousGoods value) {
		if (value == null) { //AK20121212
			return;
		}
		if (dangerousGoodsList == null) {
			dangerousGoodsList = new ArrayList<DangerousGoods>();
		}
		this.dangerousGoodsList.add(value);
	}
	
	public List<PackingLevel2> getPackingLevel2List() {
		return packingLevel2List;
	}    
	public void setPackingLevel2List(List<PackingLevel2> list) {
		this.packingLevel2List = list;
	}
	public void addPackingLevel2List(PackingLevel2 value) {
		if (value == null) {  //AK20121212
			return;
		}
		if (packingLevel2List == null) {
			packingLevel2List = new ArrayList<PackingLevel2>();
		}
		this.packingLevel2List.add(value);
	}
	
	public boolean isEmpty() {
		return Utils.isStringEmpty(grossWeight) && itemDetails == null && itemText == null; 
	}
}


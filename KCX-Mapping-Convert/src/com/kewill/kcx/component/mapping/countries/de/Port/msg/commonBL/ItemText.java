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

public class ItemText extends KCXMessage {
		
	private List<TextBL> goodsDescriptionList;	
	private List<TextBL> goodsDescriptionNoPrintList;
	private List<TextBL>  remarksBeforeList;
	private List<TextBL>  remarksAfterList;
	private List<TextBL>  packagingInformationList;
	
	private enum EItemText {	
		GoodsDescription,		
		GoodsDescriptionNoPrint,
		RemarksBeforeGoodsDescription,	
		RemarksAfterGoodsDescription,
		PackagingInformation;		
   }	

	public ItemText() {
		super();  
	}

	public ItemText(XmlMsgScanner scanner) {
  		super(scanner);
  	}
	
  	public void startElement(Enum tag, String value, Attributes attr) {
  		if (value == null) {
  			switch ((EItemText) tag) {  
  				case GoodsDescription:
  					TextBL goodsDescription = new TextBL(getScanner());  	
  					goodsDescription.parse(tag.name());
  					addGoodsDescriptionList(goodsDescription);
					break;					
  				case GoodsDescriptionNoPrint:
  					TextBL descriptionNoPrint = new TextBL(getScanner());  	
  					descriptionNoPrint.parse(tag.name());
  					addGoodsDescriptionNoPrintList(descriptionNoPrint);
					break; 
  				case RemarksBeforeGoodsDescription:
  					TextBL remarkBefore = new TextBL(getScanner());  	
  					remarkBefore.parse(tag.name());
  					addRemarksBeforeGoodsDescriptionList(remarkBefore);
					break; 
  				case RemarksAfterGoodsDescription:
  					TextBL remarkAfter = new TextBL(getScanner());  	
  					remarkAfter.parse(tag.name());
  					addRemarksAfterGoodsDescriptionList(remarkAfter);
					break;
  				case PackagingInformation:
  					TextBL packagingInformation = new TextBL(getScanner());  	
  					packagingInformation.parse(tag.name());
  					addPackagingInformationList(packagingInformation);
					break;
				default:
  					break;
  			}
  		} else {

  			switch((EItemText) tag) {     			
  				default:
  					break;
  			}
  		}
  	}

  	public void stoppElement(Enum tag) {
  	}

  	public Enum translate(String token) {
  		try {
  			return EItemText.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
  	}
	
  	public List<TextBL> getGoodsDescriptionList() {
		return goodsDescriptionList;
	}    
	public void setGoodsDescriptionList(List<TextBL> list) {
		this.goodsDescriptionList = list;
	}
	public void addGoodsDescriptionList(TextBL value) {
		if (value == null) {  //AK20121212
			return;
		}
		if (goodsDescriptionList == null) {
			goodsDescriptionList = new ArrayList<TextBL>();
		}
		this.goodsDescriptionList.add(value);
	}
	
	public List<TextBL> getGoodsDescriptionNoPrintList() {
		return goodsDescriptionNoPrintList;
	}    
	public void setGoodsDescriptionNoPrintList(List<TextBL> list) {
		this.goodsDescriptionNoPrintList = list;
	}
	public void addGoodsDescriptionNoPrintList(TextBL value) {
		if (value == null) { //AK20121212
			return;
		}
		if (goodsDescriptionNoPrintList == null) {
			goodsDescriptionNoPrintList = new ArrayList<TextBL>();
		}
		this.goodsDescriptionNoPrintList.add(value);
	}
	
		
	public List<TextBL> getPackagingInformationList() {
		return packagingInformationList;
	}    
	public void setPackagingInformationList(List<TextBL> level) {
		this.packagingInformationList = level;
	}
	public void addPackagingInformationList(TextBL value) {
		if (value == null) { //AK20121212
			return;
		}
		if (packagingInformationList == null) {
			packagingInformationList = new ArrayList<TextBL>();
		}
		this.packagingInformationList.add(value);
	}
	
	public List<TextBL> getRemarksBeforeGoodsDescriptionList() {
		return remarksBeforeList;
	}    
	public void setRemarksBeforeGoodsDescriptionList(List<TextBL> level) {
		this.remarksBeforeList = level;
	}
	public void addRemarksBeforeGoodsDescriptionList(TextBL value) {
		if (value == null) { //AK20121212
			return;
		}

		if (remarksBeforeList == null) {
			remarksBeforeList = new ArrayList<TextBL>();
		}
		this.remarksBeforeList.add(value);
	}
	
	public List<TextBL> getRemarksAfterGoodsDescriptionList() {
		return remarksAfterList;
	}    
	public void setRemarksAfterGoodsDescriptionList(List<TextBL> level) {
		this.remarksAfterList = level;
	}
	public void addRemarksAfterGoodsDescriptionList(TextBL value) {
		if (value == null) { //AK20121212
			return;
		}

		if (remarksAfterList == null) {
			remarksAfterList = new ArrayList<TextBL>();
		}
		this.remarksAfterList.add(value);
	}
	
	public boolean isEmpty() {
		return goodsDescriptionList == null && goodsDescriptionNoPrintList == null && 
		remarksBeforeList == null && remarksAfterList == null && 
		packagingInformationList == null;
	}
}


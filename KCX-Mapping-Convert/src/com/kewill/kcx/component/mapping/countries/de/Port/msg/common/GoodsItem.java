package com.kewill.kcx.component.mapping.countries.de.Port.msg.common;

import java.util.List;
import java.util.Vector;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.CommodityCode;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: Import<br>
 * Created		: 14.09.2011<br>
 * Description	: GoodsItem.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */

public class GoodsItem extends KCXMessage {
	
	 private String itemNumber; 
	 private String customsDeclarationType;	 // Art der Zollanmeldung: AES, AEM, DUX...
	 private String containerNumber;	 
	 private String handlingMode;	         // handla; Umschlagsspezifikation
	 private String tarifCode;	             // prodid Warencode
	 private String truckNumber;	         // kfzid; Fahrzeug-Identifikationsnummer / Chassisnummer
	 private String transportationNumber;	 // verktr; Verkehrsträgerkennzeichen 	
	 private String transportationAccesoryNumber;	 // kzubeh; Kennzeichen Zubehör/Beiladung für Fahrzeugverladungen 	
	 //private List<CtnItem> ctnList;
	
	 
	private GoodsLevel		firstGoodsLevel;	
	private List<GoodsLevel>	secondGoodsLevelList;
	private Container container;	  //EI20130508 BDP
		
	public GoodsItem() {
		super();				
	}

	public GoodsItem(XmlMsgScanner scanner) {
		super(scanner);
	}
		
	private enum EGoodsItem {
		//KIDS:							KFF:
		ItemNumber,                   ItemSNO,         //fzpapk_posnr
		CustomsDeclarationType,       CustomsDeclType, //artzol
		TypeOfDeclaration, //BDP 
		ContainerNumber,              CtnItems, //ItemIntContNo, //fzpapk_connr
		Container,   //EI20130508: BDP als CT_Container
		HandlingMode,
		TarifCode,	                  HSCode,      //fzpapk_prodid
		//CommodityCode, //EI20130723: BDP    
		TruckNumber,                  ItemCArgoItemRemarks, //fzpapk_kfzid  
		TransportationNumber,
		TransportationAccesoryNumber, TransportationAccesoryFlag,
		FirstGoodsLevel,             ItemPCS, ItemPCS_UT, ItemPCS_UT_Description, 
									 ItemWGT, ItemWGT_UT, ItemWGT_UT_Description, 
									 ItemVOL, ItemVOL_UT, ItemVOL_UT_Description, 
		SecondGoodsLevel,			 ItemInnerPCS, ItemInnerPCS_UT, ItemInnerPCS_UT_Description,				
	}
	
	public void startElement(Enum tag, String value, Attributes attr) {
	    if (value == null) {
			switch ((EGoodsItem) tag) {
			case FirstGoodsLevel:
				firstGoodsLevel = new GoodsLevel(getScanner());
				firstGoodsLevel.parse(tag.name());	
				break;			
			case SecondGoodsLevel:
				GoodsLevel secondGoodsLevel = new GoodsLevel(getScanner());
				secondGoodsLevel.parse(tag.name());	
				addSecondGoodsLevelList(secondGoodsLevel);
				break;	
			case Container:
				container = new Container(getScanner());
				container.parse(tag.name());
				if (container != null) {
					this.containerNumber = container.getContainerNumber();
				}
				break;
				/*
			case CommodityCode:   //EI20130723
				CommodityCode cc = new CommodityCode(getScanner());
				cc.parse(tag.name());
				if (cc != null) {
					this.tarifCode = cc.getTarifCode();
				}
				break;
				*/
			default:
				return;
			}
	    } else {
	    	switch ((EGoodsItem) tag) {
			case ItemNumber:			
				 setItemNumber(value);
				 break;							
			case CustomsDeclarationType:	
			case TypeOfDeclaration:
				 setCustomsDeclarationType(value);
				 break;									
			case ContainerNumber:						
				 setContainerNumber(value);
				 break;				
			case HandlingMode:
				 setHandlingMode(value);
				 break;
			case TarifCode:			//BDP schickt CommodityCode.TarifCode, da CommodityCode nicht in Enum gibt
				 setTarifCode(value);   //wird weiter innerhalb von C-Tag gelesen: also TarifCode
				 break;
			case TruckNumber:			
				 setTruckNumber(value);
				 break;	
			case TransportationNumber:
				 setTransportationNumber(value);
				 break;
			case TransportationAccesoryNumber:
			case TransportationAccesoryFlag:
				 setTransportationAccesoryNumber(value);
				 break;											
			default:
				return;
			}
	    }		
	}

	public void stoppElement(Enum tag) {		
	}

	public Enum translate(String token) {
 		try {
  			return EGoodsItem.valueOf(token);
  		} catch (IllegalArgumentException e) {
  			return null;
  		}
	}

	
	public String getItemNumber() {
		return itemNumber;
	}
	public void setItemNumber(String value) {
		this.itemNumber = value;
	}		
	
	public String getCustomsDeclarationType() {
		return customsDeclarationType;
	}
	public void setCustomsDeclarationType(String value) {
		this.customsDeclarationType = value;
	}

	public String getContainerNumber() {
		return containerNumber;
	}
	public void setContainerNumber(String value) {
		this.containerNumber = value;
	}
	
	public String getHandlingMode() {
		return handlingMode;
	}
	public void setHandlingMode(String value) {
		this.handlingMode = value;
	}

	public String getTarifCode() {
		return tarifCode;
	}
	public void setTarifCode(String value) {
		this.tarifCode = value;
	}
	
	public String getTruckNumber() {
		return truckNumber;
	}
	public void setTruckNumber(String value) {
		this.truckNumber = value;
	}

	public String getTransportationNumber() {
		return transportationNumber;
	}
	public void setTransportationNumber(String value) {
		this.transportationNumber = value;
	}	

	public String getTransportationAccesoryNumber() {
		return transportationAccesoryNumber;
	}
	public void setTransportationAccesoryNumber(String value) {
		this.transportationAccesoryNumber = value;
	}

	public GoodsLevel getFirstGoodsLevel() {
		return firstGoodsLevel;
	}
	public void setFirstGoodsLevel(GoodsLevel goods) {
		this.firstGoodsLevel = goods;
	}	
	
	public List<GoodsLevel> getSecondGoodsLevelList() {
		return secondGoodsLevelList;
	}
	public void setSecondGoodsLevelList(List<GoodsLevel> list) {
		this.secondGoodsLevelList = list;
	}
	public void addSecondGoodsLevelList(GoodsLevel argument) {
		if (secondGoodsLevelList == null) {
			secondGoodsLevelList = new Vector<GoodsLevel>();	
		}
		this.secondGoodsLevelList.add(argument);
	}						

	
	public void setItemInnerPCS(String value) {
		if (firstGoodsLevel == null) {
			firstGoodsLevel = new GoodsLevel();
		}
		firstGoodsLevel.setQuantity(value);
	}
	public void setItemInnerPcsUt(String value) {
		if (firstGoodsLevel == null) {
			firstGoodsLevel = new GoodsLevel();
		}
		firstGoodsLevel.setPackingType(value);
	}
	public void setItemInnerPcsUtDescription(String value) {
		if (firstGoodsLevel == null) {
			firstGoodsLevel = new GoodsLevel();
		}
		//firstGoodsLevel.addDescriptionList(value);
	}
	
	public boolean isEmpty() {
		return (Utils.isStringEmpty(this.itemNumber) && Utils.isStringEmpty(this.customsDeclarationType) && 
		        Utils.isStringEmpty(this.containerNumber) && Utils.isStringEmpty(this.handlingMode) && 
		        Utils.isStringEmpty(this.tarifCode) && Utils.isStringEmpty(this.truckNumber) && 
		        Utils.isStringEmpty(this.transportationNumber) && 
		        this.firstGoodsLevel == null);  
	}
}


package com.kewill.kcx.component.mapping.formats.kff.common;

import java.util.List;
import java.util.Vector;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.Port.msg.common.CtnItem;
import com.kewill.kcx.component.mapping.countries.de.Port.msg.common.CtnItems;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.xml.XmlMsgScanner;

/**
 * Module		: Port<br>
 * Created		: 24.10.2011<br>
 * Description	: Container Data.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */

public class CargoItem extends KCXMessage {

	private String itemSNO;   	
	private String hsCode;  
	private String itemCargoItemRemarks; 
	private String customsDeclType;  
	private String marks;
	private String description;
	
	private String itemPcs;
	private String itemPcsUt;
	private String itemWgt; 
	private String itemWgtUt; 
	private String itemVol;   
	private String itemVolUt; 
	
	private String itemInnerPcs; 
	private String itemInnerPcsUt; 
		
	private CtnItem ctn;
	private List<CtnItem> ctnList;      //EI20130412
	private CtnItems ctns;
	
	private String itemHazardCode;
	private String itemHazardClass;
	private String itemHazardDesc;
	private String itemHazardLabel;
	private String itemHazardCodeQualifier;
	private String itemHazardClassification;
	private String itemHazardContact;	              //EI20120510
	private String itemHazardContactTel;              //EI20120510
	private String itemHazardUOM;
	private String itemUNPageNum;
	private String itemDGPackingGroup;
	private String itemNegativeIndicator;
	private String itemFlashPoint;
	private String itemFlashPointUOM;
	
	private String itemHighestTemp;
	private String itemLowestTemp;
	private String itemSetTemp;
	private String itemTempUOM;
	
	 public CargoItem() {
		 super();  
	 }

	 public CargoItem(XmlMsgScanner scanner) {
 		super(scanner);
	 }

	 private enum EDocumentFormat {	
		 // KFF								FSS
		    ItemSNO,                        //fzpapk_posnr
		    ItemPCS,				        //fzpaco_colanz
			ItemPCS_UT,                     //fzpapk_colart
			//ItemPCS_UT_Description,         //ausgeschriebene verpackungsart, wie PCS ist Pieces
			ItemWGT,					     //fzpaco_rohmas
			ItemWGT_UT,                     //fzpapk_
			//ItemWGT_UT_Description,         //KGS ist KILOGRAM
			ItemVOL,                        //fzpapk_brtmas
			ItemVOL_UT,
			//ItemVOL_UT_Description,
			ItemMoveType,                   //steht CFS/CFS was ist das?
			Marks,
			Description,	
			HSCode,                         //fzpapk_prodid
		    ItemCargoItemRemarks,			//fzpapk_kfzid
		    CustomsDeclType,                //fzpapk_artzol - wird mit AES gefüllt, JOB sendet es im Kopf (EXPORT)	
							    
			//CtnItems,                       //fzpapk_connr
			CtnItem,								
			CtnItems,
		    ItemHazardCode,
            ItemHazardClass,
            ItemHazardDesc,
            ItemHazardCodeQualifier,
            ItemDGPackingGroup,
            
            ItemHazardLabel,
            ItemHazardClassification,
			ItemHazardContact,	
			ItemHazardContactTel,
			ItemHazardUOM,
			ItemUNPageNum,
			ItemNegativeIndicator,
			ItemFlashPoint,
			ItemFlashPointUOM,			
			ItemHighestTemp,
			ItemLowestTemp,
			ItemSetTemp,
			ItemTempUOM,
			
			ItemInnerPCS,				     //secondLevel
			ItemInnerPCS_UT,			     //				
			//ItemInnerPCS_UT_Description,
			
	 }	 
	 
	 public void startElement(Enum tag, String value, Attributes attr) {
		if (value == null) {		
			switch ((EDocumentFormat) tag) {			   
				case CtnItem:
					ctn = new CtnItem(getScanner());
					ctn.parse(tag.name());													
			    case CtnItems:
			    	ctns = new CtnItems(getScanner());			    	
			    	ctns.parse(tag.name());			    					    	
			    	break;
				default: return;			
			}
		} else {			
			switch ((EDocumentFormat) tag) {			
				case ItemSNO:
					setItemSNO(value);
					break;
				case CustomsDeclType:			
					 setCustomsDeclarationType(value);
					 break;		
				case HSCode:
					 setHsCode(value);
					 break;				
				case ItemCargoItemRemarks:
					 setItemCargoItemRemarks(value);
					 break;	
				case Marks:
					setMarks(value);
					 break;	
				case Description:
					setDescription(value);
					 break;	
				case ItemPCS:				
					 setItemPcs(value);
					 break;	
				case ItemPCS_UT:
					setItemPcsUt(value);
					 break;
				//case ItemPCS_UT_Description:
					 //setItemInnerPcsUtDescription(value);
					 //break;								
				case ItemWGT:
					 setItemWgt(value);
					 break;
				case ItemWGT_UT:
					 setItemWgtUt(value);
					 break;
				case ItemVOL:
					 setItemVol(value);
					 break;	
				case ItemVOL_UT:
					 setItemVolUt(value);
					 break;	
				case ItemInnerPCS:
					 setItemInnerPcs(value);
					 break;
				case ItemInnerPCS_UT:
					 setItemInnerPcsUt(value);
					 break;
				//case ItemInnerPCS_UT_Description:
					 //setItemInnerPcs(value);
					 //break;					 
				case ItemHazardCode:
					setItemHazardCode(value);
				 break;
				case ItemHazardClass:
					setItemHazardClass(value);
					 break;					
				case ItemHazardDesc:
					setItemHazardDesc(value);
					 break;
				case ItemHazardCodeQualifier:
					setItemHazardCodeQualifier(value);
					 break;
				case ItemDGPackingGroup:
					setItemDGPackingGroup(value);
					break;
				case ItemHazardContact:
					setItemHazardContact(value);
					break;
				case ItemHazardContactTel:
					setItemHazardContactTel(value);
					break;
					/*
					ItemHazardLabel,
		            ItemHazardClassification,
					
					ItemHazardUOM,
					ItemUNPageNum,
					ItemNegativeIndicator,
					ItemFlashPoint,
					ItemFlashPointUOM,
					
					ItemHighestTemp,
					ItemLowestTemp,
					ItemSetTemp,
					ItemTempUOM,					
					*/
				default:
					return;
			}
		}
	}

	 public void stoppElement(Enum tag) {
	 }
	
	 public Enum translate(String token) {
		 try {
			return EDocumentFormat.valueOf(token);
		 } catch (IllegalArgumentException e) {
			return null;
		 }
	 }

   public String getItemSNO() {
		return itemSNO;
	}
	public void setItemSNO(String argument) {
		this.itemSNO = argument;
	}					
		
	public String getCustomsDeclarationType() {
		return customsDeclType;
	}
	public void setCustomsDeclarationType(String argument) {
		this.customsDeclType = argument;
	}	
	
	public String getHsCoded() {
		return hsCode;
	}
	public void setHsCode(String argument) {
		this.hsCode = argument;
	}		
	
	public String getItemCargoItemRemarks() {
		return itemCargoItemRemarks;
	}
	public void setItemCargoItemRemarks(String argument) {
		this.itemCargoItemRemarks = argument;
	}	
	
	public String getMarks() {
		return marks;
	}
	public void setMarks(String argument) {
		this.marks = argument;
	}	
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String argument) {
		this.description = argument;
	}	
	
	public String getItemPcs() {
		return itemPcs;
	}
	public void setItemPcs(String argument) {
		this.itemPcs = argument;
	}	
	
	public String getItemPcsUt() {
		return itemPcsUt;
	}
	public void setItemPcsUt(String argument) {
		this.itemPcsUt = argument;
	}	
	
	public String getItemWgt() {
		return itemWgt;
	}
	public void setItemWgt(String argument) {
		this.itemWgt = argument;
	}	
	
	public String getItemWgtUt() {
		return itemWgtUt;
	}
	public void setItemWgtUt(String argument) {
		this.itemWgtUt = argument;
	}	
	
	public String getItemVol() {
		return itemVol;
	}
	public void setItemVol(String argument) {
		this.itemVol = argument;
	}
	
	public String getItemVolUt() {
		return itemVolUt;
	}
	public void setItemVolUt(String argument) {
		this.itemVolUt = argument;
	}	
	
	public String getItemInnerPcs() {
		return itemInnerPcs;
	}
	public void setItemInnerPcs(String argument) {
		this.itemInnerPcs = argument;
	}	
		
	public String getItemInnerPcsUt() {
		return itemInnerPcsUt;
	}
	public void setItemInnerPcsUt(String argument) {
		this.itemInnerPcsUt = argument;
	}	
	
	public CtnItem getCtnItem() {
		return ctn;
	}
	public void setCtnItem(CtnItem argument) {
		this.ctn = argument;
	}

	public String getItemHazardCode() {
		return itemHazardCode;
	}
	public void setItemHazardCode(String argument) {
		this.itemHazardCode = argument;
	}
	
	public String getItemHazardClass() {
		return itemHazardClass;
	}
	public void setItemHazardClass(String argument) {
		this.itemHazardClass = argument;
	}
	
	public String getItemHazardDesc() {
		return itemHazardDesc;
	}
	public void setItemHazardDesc(String argument) {
		this.itemHazardDesc = argument;
	}
	
	public String getItemHazardCodeQualifier() {
		return itemHazardCodeQualifier;
	}
	public void setItemHazardCodeQualifier(String argument) {
		this.itemHazardCodeQualifier = argument;
	}
	
	public String getItemDGPackingGroup() {
		return itemDGPackingGroup;
	}
	public void setItemDGPackingGroup(String argument) {
		this.itemDGPackingGroup = argument;
	}        
    	
	public String getItemHazardContact() {
		return itemHazardContact;
	}
	public void setItemHazardContact(String argument) {
		this.itemHazardContact = argument;
	}
	
	public String getItemHazardContactTel() {
		return itemHazardContactTel;
	}
	public void setItemHazardContactTel(String argument) {
		this.itemHazardContactTel = argument;
	}
	
	public CtnItems getCtnItems() {
		return ctns;
	}
	public void setCtnItems(CtnItems ctns) {
		this.ctns = ctns;
	}	
	
	
	public boolean isEmpty() {
		return (Utils.isStringEmpty(this.itemSNO) && Utils.isStringEmpty(this.customsDeclType) && 
		        Utils.isStringEmpty(this.hsCode) && Utils.isStringEmpty(this.itemCargoItemRemarks) && 
		        Utils.isStringEmpty(this.itemPcs) && Utils.isStringEmpty(this.itemWgt));		        
	}
}

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

public class CtnItem extends KCXMessage {
	
	 private String itemNumber; 
	 private String containerNumber;	 
	 private String containerSealNumber;	
	 //EI20130412:
	 private String ctnType;
	 private String ctnItemTOTPCS;
	 private String ctnItemTOTPCS_UT;
	 private String ctnItemTOTWGT;
	 private String ctnItemTOTWGT_UT;
	 private String ctnItemTOTVOL;
		
	public CtnItem() {
		super();				
	}

	public CtnItem(XmlMsgScanner scanner) {
		super(scanner);
	}
		
	private enum ECntItem {
		//KIDS:							KFF:
		ItemNumber,                   
		//TODO ??? restkiche Tags???
		ContainerNumber,              ItemContNo, //fzpapk_connr
									  CntSealNumver,
									  CtnType,
									  CtnItemTOTPCS,
									  CtnItemTOTPCS_UT,
									  CtnItemTOTWGT,
									  CtnItemTOTWGT_UT,
									  CtnItemTOTVOL,
	}
	
	public void startElement(Enum tag, String value, Attributes attr) {
	    if (value == null) {
			switch ((ECntItem) tag) {
				
			default:
				return;
			}
	    } else {
	    	switch ((ECntItem) tag) {
			case ItemNumber:
			//case ItemSNO:
				 setItemNumber(value);
				 break;												
			case ContainerNumber:		
			case ItemContNo:
				 setContainerNumber(value);
				 break;			
			case CntSealNumver:	
				 setContainerSealNumber(value);
				 break;	
			case CtnType:
				setCtnType(value);
				 break;	
			case CtnItemTOTPCS:
				setCtnItemTOTPCS(value);
				 break;
			case CtnItemTOTPCS_UT:
				setCtnItemTOTPCS_UT(value);
				 break;
			case  CtnItemTOTWGT:
				 setCtnItemTOTWGT(value);
				 break;
			case  CtnItemTOTWGT_UT:
				setCtnItemTOTWGT_UT(value);
				 break;
			case  CtnItemTOTVOL:
				setCtnItemTOTVOL(value);
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
  			return ECntItem.valueOf(token);
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
	
	
	public String getContainerNumber() {
		return containerNumber;
	}
	public void setContainerNumber(String value) {
		this.containerNumber = value;
	}

	public String getContainerSealNumber() {
		return containerSealNumber;
	}
	public void setContainerSealNumber(String value) {
		this.containerSealNumber = value;
	}	

	public String getCtnType() {
		return ctnType;
	}
	public void setCtnType(String value) {
		this.ctnType = value;
	}	
	
	public String getCtnItemTOTPCS() {
		return ctnItemTOTPCS;
	}
	public void setCtnItemTOTPCS(String value) {
		this.ctnItemTOTPCS = value;
	}	
	
	public String getCtnItemTOTPCS_UT() {
		return  ctnItemTOTPCS_UT;
	}
	public void setCtnItemTOTPCS_UT(String value) {
		this.ctnItemTOTPCS_UT = value;
	}	
	
	public String getCtnItemTOTWGT() {
		return ctnItemTOTWGT;
	}
	public void setCtnItemTOTWGT(String value) {
		this.ctnItemTOTWGT = value;
	}	
	
	public String getCtnItemTOTWGT_UT() {
		return ctnItemTOTWGT_UT;
	}
	public void setCtnItemTOTWGT_UT(String value) {
		this.ctnItemTOTWGT_UT = value;
	}	
	
	public String getCtnItemTOTVOL() {
		return ctnItemTOTVOL;
	}
	public void setCtnItemTOTVOL(String value) {
		this.ctnItemTOTVOL = value;
	}	
	

	public boolean isEmpty() {
		return (Utils.isStringEmpty(this.itemNumber) && 
		        Utils.isStringEmpty(this.containerNumber));
		       
	}
}


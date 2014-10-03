package com.kewill.kcx.component.mapping.formats.fss.Port.messages.BL.common;

import java.util.ArrayList;
import java.util.List;

import com.kewill.kcx.component.mapping.formats.fss.Port.subsetsBL.TsAllocatedEquipment;
import com.kewill.kcx.component.mapping.formats.fss.Port.subsetsBL.TsGoodsItemDetails;
import com.kewill.kcx.component.mapping.formats.fss.Port.subsetsBL.TsMarksAndNumber;
import com.kewill.kcx.component.mapping.formats.fss.Port.subsetsBL.TsPackingLevel;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;

/**
 * Module		: Port BL.<br>
 * Created		: 10.04.2012<br>
 * Description	:
 * 
 * @author iwaniuk
 * @version 1.0.00
 */

public class PackingDetailsFSS {
	
	//private TsCount tsGoodsItem;	
	private TsPackingLevel tsPackingLevel;	
	private TsGoodsItemDetails detailslevel;	
	private PartyFSS originalShipper;	
	private PartyFSS ultimateConsignee;	
	private TsMarksAndNumber marksAndNumbers;	
	private TextOnItemFSS textOnItem;	
	
	public PackingDetailsFSS() throws FssException {
		super(); 				
	}
	
	public TsPackingLevel getTsPackingLevel() {
		return tsPackingLevel;
	}
	public void setTsPackingLevel(TsPackingLevel tsFirstPackingLevel) {
		this.tsPackingLevel = tsFirstPackingLevel;
	}

	public TsGoodsItemDetails getDetailslevel() {
		return detailslevel;
	}
	public void setDetailslevel(TsGoodsItemDetails detailslevel1) {
		this.detailslevel = detailslevel1;
	}

	public TsMarksAndNumber getMarksAndNumbers() {
		return marksAndNumbers;
	}
	public void setMarksAndNumbers(TsMarksAndNumber marks) {
		this.marksAndNumbers = marks;
	}

	public PartyFSS getOriginalShipper() {
		return originalShipper;
	}
	public void setOriginalShipper(PartyFSS originalShipper) {
		this.originalShipper = originalShipper;
	}

	public PartyFSS getUltimateConsignee() {
		return ultimateConsignee;
	}
	public void setUltimateConsignee(PartyFSS ultimateConsignee) {
		this.ultimateConsignee = ultimateConsignee;
	}

	public TextOnItemFSS getTextOnItem() {
		return textOnItem;
	}
	public void setTextOnItem(TextOnItemFSS textOnItem) {
		this.textOnItem = textOnItem;
	}	   
}

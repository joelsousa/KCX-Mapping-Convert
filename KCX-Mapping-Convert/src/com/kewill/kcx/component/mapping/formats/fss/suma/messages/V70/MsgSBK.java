package com.kewill.kcx.component.mapping.formats.fss.suma.messages.V70;

import java.util.ArrayList;
import java.util.List;

import com.kewill.kcx.component.mapping.formats.fss.Import.messages.FssMessage;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.fss.common.TsHead;
import com.kewill.kcx.component.mapping.formats.fss.suma.subsets.V70.TsSBK;
import com.kewill.kcx.component.mapping.formats.fss.suma.subsets.V70.TsSBP;
import com.kewill.kcx.component.mapping.formats.fss.suma.subsets.V70.TsSBR;

/**
 * Module		: Manifest<br>
 * Created		: 17.06.2013<br>
 * Description	: MsgSBK/ Gestellung-/Aufteilungsdaten
 * 				  Bestätigung einer oder mehrer Positionen im Status "vorzeitig" NotificationOfPresentationConfirmed.
 * 
 * @author krzoska
 * @version 7.0.00
 */

public class MsgSBK extends FssMessage {
	
	private TsHead	headSubset;
	private TsSBK   sbkSubset; 
	private TsSBR   sbrSubset;
	private ArrayList   <TsSBP>sbpList;

	public MsgSBK() {
		super();  
		headSubset = new TsHead(); 
	}
	
	public TsHead getHeadSubset() {
		return headSubset;
	}
	public void setHeadSubset(TsHead head) {
		this.headSubset = head;
	}
    	
	public TsSBK getSbkSubset() {
		return sbkSubset;
	}
	public void setSbkSubset(TsSBK sbkSubset) {
		this.sbkSubset = sbkSubset;
	}

	public TsSBR getSbrSubset() {
		return sbrSubset;
	}
	public void setSbrSubset(TsSBR sbrSubset) {
		this.sbrSubset = sbrSubset;
	}

	public List<TsSBP> getSbpList() {
		return sbpList;
	}
	public void setSbpList(ArrayList<TsSBP> list) {
		sbpList = list;
	}
	public void addSbpList(TsSBP sbp) {
		if (sbpList == null) {
			sbpList = new ArrayList<TsSBP>();
		}
		this.sbpList.add(sbp);
	}
	
	
	public String getFssString() throws FssException {
		String res = "";	
				
		res = appendString(res, headSubset.teilsatzBilden());	
		
		if (sbkSubset != null) {	 
			res = appendString(res, sbkSubset.teilsatzBilden());	
		}
		if (sbrSubset != null) {	 
			res = appendString(res, sbrSubset.teilsatzBilden());	
		}
		if (sbpList != null) {                     					 			
			for (TsSBP sbp : sbpList) {				
				if (sbp != null && !sbp.isEmpty()) {
					res = appendString(res, sbp.teilsatzBilden());
				}
			}
		}
		
		return res;
	}

}



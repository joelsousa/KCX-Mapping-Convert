package com.kewill.kcx.component.mapping.formats.fss.suma.messages.V70;

import java.util.ArrayList;
import java.util.List;

import com.kewill.kcx.component.mapping.formats.fss.Import.messages.FssMessage;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.fss.common.TsHead;
import com.kewill.kcx.component.mapping.formats.fss.suma.subsets.V70.TsSNK;
import com.kewill.kcx.component.mapping.formats.fss.suma.subsets.V70.TsSNP;

/**
 * Module		: Manifest<br>
 * Created		: 17.06.2013<br>
 * Description	: MsgSNK - Nachtraegliche Zuweisung der MRNs u. Positionen auf SumAs 
 * 
 * @author iwaniuk
 * @version 7.0.00
 */

public class MsgSNK  extends FssMessage {

	private TsHead	headSubset;
	private TsSNK 	snkSubset;
	private ArrayList <TsSNP> snpList;
	
	
	public MsgSNK() throws FssException {
		super();  
		headSubset = new TsHead(); 
	}
	
	public TsHead getHeadSubset() {
		return headSubset;
	}
	public void setHeadSubset(TsHead head) {
		this.headSubset = head;
	}
	
	public TsSNK getSnkSubset() {
		return snkSubset;
	}
	public void setSnkSubset(TsSNK ts) {
		this.snkSubset = ts;
	}
	
	public List<TsSNP> getSnpList() {
		return snpList;
	}
	public void setSnpList(ArrayList<TsSNP> list) {
		snpList = list;
	}
	public void addSnpList(TsSNP ts) {
		if (snpList == null) {
			snpList = new ArrayList<TsSNP>();
		}
		this.snpList.add(ts);
	}
	
	public String getFssString() throws FssException {
		String res = "";	
		
		
		res = appendString(res, headSubset.teilsatzBilden());			
		if (snkSubset != null) {	 
			res = appendString(res, snkSubset.teilsatzBilden());	
		}		
		if (snpList != null) {                     					 			
			for (TsSNP snp : snpList) {				
				if (snp != null && !snp.isEmpty()) {
					res = appendString(res, snp.teilsatzBilden());
				}
			}
		}
		
		return res;
	}
}

package com.kewill.kcx.component.mapping.formats.fss.suma.messages.V70;

import java.util.ArrayList;

import com.kewill.kcx.component.mapping.formats.fss.Import.messages.FssMessage;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.fss.common.TsHead;
import com.kewill.kcx.component.mapping.formats.fss.suma.subsets.V70.TsSAK;
import com.kewill.kcx.component.mapping.formats.fss.suma.subsets.V70.TsSAP;

/**
 * Module		: Manifest<br>
 * Created		: 17.06.2013<br>
 * Description	: MsgSAK ReExport. 
 * 				  
 * 
 * @author iwaniuk
 * @version 7.0.00
 */

public class MsgSAK extends FssMessage {
	
	private TsHead	headSubset;
	private TsSAK   sakSubset; 	
	private ArrayList <TsSAP>sapList;

	public MsgSAK() {
		super();  
		headSubset = new TsHead(); 
	}
	
	public TsHead getHeadSubset() {
		return headSubset;
	}
	public void setHeadSubset(TsHead head) {
		this.headSubset = head;
	}
    	
	public TsSAK getSakSubset() {
		return sakSubset;
	}
	public void setSakSubset(TsSAK ts) {
		this.sakSubset = ts;
	}
	
	public ArrayList<TsSAP> getSapList() {
		return sapList;
	}
	public void setSapList(ArrayList<TsSAP> list) {
		sapList = list;
	}
	public void addSapList(TsSAP sap) {
		if (sapList == null) {
			sapList = new ArrayList<TsSAP>();
		}
		this.sapList.add(sap);
	}
	
	
	public String getFssString() throws FssException {
		String res = "";	
				
		res = appendString(res, headSubset.teilsatzBilden());	
		
		if (sakSubset != null) {	 
			res = appendString(res, sakSubset.teilsatzBilden());	
		}
		
		if (sapList != null) {                     					 			
			for (TsSAP sap : sapList) {				
				if (sap != null && !sap.isEmpty()) {
					res = appendString(res, sap.teilsatzBilden());
				}
			}
		}
		
		return res;
	}

}



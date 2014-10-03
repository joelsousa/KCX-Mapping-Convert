package com.kewill.kcx.component.mapping.formats.fss.Import.messages.V64;

import java.util.List;
import java.util.Vector;

import com.kewill.kcx.component.mapping.formats.fss.Import.messages.FssMessage;
import com.kewill.kcx.component.mapping.formats.fss.Import.subsets.V64.TsABW;
import com.kewill.kcx.component.mapping.formats.fss.Import.subsets.V64.TsAHICTX;
import com.kewill.kcx.component.mapping.formats.fss.Import.subsets.V64.TsAKG;
import com.kewill.kcx.component.mapping.formats.fss.Import.subsets.V64.TsCRP;
import com.kewill.kcx.component.mapping.formats.fss.Import.subsets.V64.TsSOF;
import com.kewill.kcx.component.mapping.formats.fss.Import.subsets.V64.TsTXT;
import com.kewill.kcx.component.mapping.formats.fss.Import.subsets.V64.TsUNT;

/**
 * Module		: Import<br>
 * Date Created	: 12.09.2011<br>
 * Description	: MsgCRL Position / ImportDeclarationDecision.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class MsgCRLPos extends FssMessage{
		
	private TsCRP        crpSubset;    // Positionssatz 		
	private List <TsTXT> txtList;      // Texte 
	
	public MsgCRLPos() {
		super();  
	}
	
	public TsCRP getCRPSubset() {
		return crpSubset;
	}
	public void setCRPSubset(TsCRP subset) {
		crpSubset = subset;
	}
	
	public void setTXTList(List <TsTXT> list) {
		txtList = list;
    }
	public List <TsTXT> getTXTList() {
		return txtList;
	}
	public void addTXTList(TsTXT subset) {
		if (txtList == null) {
			txtList = new Vector<TsTXT>();
		}
		txtList.add(subset);
    }
	
	
}


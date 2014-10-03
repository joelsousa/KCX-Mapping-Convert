package com.kewill.kcx.component.mapping.formats.fss.Port.messages.V65;

import java.util.List;
import java.util.Vector;

import com.kewill.kcx.component.mapping.formats.fss.Import.messages.FssMessage;
import com.kewill.kcx.component.mapping.formats.fss.Port.messages.V65.common.PorPos;
import com.kewill.kcx.component.mapping.formats.fss.Port.subsets.V65.TsAPK;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: Port<br>
 * Created	    : 21.10.2011<br>
 * Description	: Position von POR
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class MsgPORPos extends FssMessage {
	
	private TsAPK	apkSubset;
	private PorPos  posEbene1;   	
	private List <PorPos>  posEbene2List;   
	
	public MsgPORPos() {
		super();  
	}	
    
	public void setAPKSubset(TsAPK argument) {
		apkSubset = argument;
    }
	public TsAPK getAPKSubset() {
		return apkSubset;
	}

	public void setPosEbene1(PorPos argument) {
		posEbene1 = argument;
    }
	public PorPos getPosEbene1() {
		return posEbene1;
	}	
	
	public void setPosEbene2List(List <PorPos> list) {
		posEbene2List = list;
    }
	public List <PorPos> getPosEbene2List() {
		return posEbene2List;
	}
	public void addPosEbene2List(PorPos ebene2) {
		if (posEbene2List == null) {
			posEbene2List = new Vector<PorPos>();	
		}
		posEbene2List.add(ebene2);
    }
	
	 public boolean isEmpty() {
		return  apkSubset == null || apkSubset.isEmpty();		    
	 }	
}


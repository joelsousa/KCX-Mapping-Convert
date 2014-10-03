package com.kewill.kcx.component.mapping.formats.fss.Port.messages.V71;

import java.util.List;
import java.util.Vector;

import com.kewill.kcx.component.mapping.formats.fss.Import.messages.FssMessage;
import com.kewill.kcx.component.mapping.formats.fss.Port.messages.V71.common.PorPos;
import com.kewill.kcx.component.mapping.formats.fss.Port.subsets.V65.TsAPK;

/**
 * Module		: Port<br>
 * Created	    : 12.12.2013<br>
 * Description	: Position von POR.
 * 				: V71 new PorPos: TsPOB
 * 
 * @author iwaniuk
 * @version 7.1.00
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


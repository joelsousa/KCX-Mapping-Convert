package com.kewill.kcx.component.mapping.formats.fss.ncts.messages.V70;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import java.util.Vector;

import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.fss.common.TsHead;
import com.kewill.kcx.component.mapping.formats.fss.common.TsVOR;
import com.kewill.kcx.component.mapping.formats.fss.ncts.messages.FssMessage62;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62.TsVFO;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62.TsVFP;
import com.kewill.kcx.component.mapping.formats.fss.utils.FssUtils;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: NCTS<br>
 * Created		: 12.02.2013<br>
 * Description	: VFO.
 * 				: new TsHead (only change)
 * 
 * @author iwaniuk
 * @version 7.0.00
 */
public class MsgVFO extends FssMessage62 {

	private TsVOR	vorSubset;
	private TsHead headSubset;
	private TsVFO	vfoSubset;
	private List< TsVFP > vfpSubsetList;
	
	public TsVOR getVorSubset() {
		return vorSubset;
	}
	public void setVorSubset(TsVOR vor) {
		this.vorSubset = vor;
	}
	
	public TsHead getHeadSubset() {
		return headSubset;
	}
	public void setHeadSubset(TsHead head) {
		this.headSubset = head;
	}
	
	public TsVFO getVfoSubset() {
		return this.vfoSubset;
	}
	public void setVfoSubset(TsVFO tsVFO) {
		this.vfoSubset	= tsVFO;
	}
	
	public void addVfpSubsetList(TsVFP vfpSubset) {
		if (vfpSubset == null) {
			return;
		}
		
		if (vfpSubsetList == null) {
			vfpSubsetList	= new Vector< TsVFP >();
		}
		
		this.vfpSubsetList.add(vfpSubset);
	}
	public List< TsVFP > getVfpSubsetList() {
		return this.vfpSubsetList;
	}
	
    /* Struktur der Nachricht 
     * 

    */
	

	public String getFssString() throws FssException {
		return getFssString("");
	}	
	public String getFssString(String firstTs) throws FssException {
		String res = "";
		
		if (firstTs != null && firstTs.equalsIgnoreCase("HEAD")) {
			headSubset.mapVor2Head(vorSubset);
			if (headSubset != null && !headSubset.isEmpty()) {		 
				res = appendString(res, headSubset.teilsatzBilden());
			}
		} else {			
			if (vorSubset != null && !vorSubset.isEmpty()) {	 
				res = appendString(res, vorSubset.teilsatzBilden());	
			}
		}		
		
		//if (vfoSubset != null && !vfoSubset.isEmpty()) {
		if (vfoSubset != null) {	//EI20111011 im Hauptsatz keine isEmpty Abfrage
			res	= appendString(res, vfoSubset.teilsatzBilden());
		}
		if (vfpSubsetList != null) {
			for (TsVFP vfpSubset : vfpSubsetList) {
				if (vfpSubset != null && !vfpSubset.isEmpty()) {
					res	= appendString(res, vfpSubset.teilsatzBilden());
				}
			}
		}
		return res;
	}
	
	public void readMessage(BufferedReader message) throws FssException {
		try {
			String line	= "";
			
			while ((line = message.readLine()) != null) {
				String lineType	= "";
				if (line.length() > 3) {
					lineType = line.substring(0, 3);
					Utils.log("linetype " + lineType);
				}
				
				if (lineType.equalsIgnoreCase("VFO")) {
					vfoSubset	= new TsVFO();
					String[] vfo	= line.split("" + FssUtils.FSS_FS);
					vfoSubset.setFields(vfo);
				} else if (lineType.equalsIgnoreCase("VFP")) {
					TsVFP vfpSubset	= new TsVFP();
					String[] vfp	= line.split("" + FssUtils.FSS_FS);
					vfpSubset.setFields(vfp);
					
					if (vfpSubsetList == null) {
						vfpSubsetList	= new Vector< TsVFP >();
					}					
					vfpSubsetList.add(vfpSubset);
				}  else if (lineType.equalsIgnoreCase("NAC")) {
					Utils.log("ignore linetype NAC");	
				} else if (lineType.equals("")) {   //EI20110526
					Utils.log("lineType " + lineType);
				} else {
					throw new FssException("Wrong message line " + lineType);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


}


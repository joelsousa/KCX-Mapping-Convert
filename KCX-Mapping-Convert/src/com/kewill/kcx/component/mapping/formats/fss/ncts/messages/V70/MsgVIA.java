package com.kewill.kcx.component.mapping.formats.fss.ncts.messages.V70;

import java.io.BufferedReader;
import java.io.IOException;

import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.fss.common.TsHead;
import com.kewill.kcx.component.mapping.formats.fss.common.TsVOR;
import com.kewill.kcx.component.mapping.formats.fss.ncts.messages.FssMessage62;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.V70.TsVIA;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.V70.TsVIR;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62.TsVIB;
import com.kewill.kcx.component.mapping.formats.fss.utils.FssUtils;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module 	   : MsgVIA<br>
 * Created     : 09.02.2013<br>
 * Description : MsgVIA.
 * 
 * @author  iwaniuk
 * @version 7.0.00
 */
public class MsgVIA extends FssMessage62 {

	private TsVOR  vorSubset;
	private TsHead headSubset;
	private TsVIA  viaSubset;  //V70 changes
	private TsVIB  vibSubset;
	private TsVIR  virSubset;  //V70 new	
	//private TsVIS visSubset;  		  V70 -entfernt
	//private List<TsVIP> vipSubsetList;  V70 -entfernt

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
	
		if (viaSubset != null) {	//EI20111011 im Hauptsatz keine isEmpty Abfrage
			res = appendString(res, viaSubset.teilsatzBilden());
		}

		if (vibSubset != null && !vibSubset.isEmpty()) {
			res = appendString(res, vibSubset.teilsatzBilden());
		}

		if (virSubset != null && !virSubset.isEmpty()) {
			res = appendString(res, virSubset.teilsatzBilden());
		}	

		return res;
	}

	public void readMessage(BufferedReader message) throws FssException {
		try {
			String line = "";
			
			while ((line = message.readLine()) != null) {
				String lineType	= "";
				if (line.length() > 3) {
					lineType = line.substring(0, 3);
					Utils.log("linetype " + lineType);
				}
			
				if (lineType.equalsIgnoreCase("VIA")) {
					viaSubset = new TsVIA();
					String[] via = line.split("" + FssUtils.FSS_FS);
					viaSubset.setFields(via);
				} else if (lineType.equalsIgnoreCase("VIB")) {
					vibSubset = new TsVIB();
					String[] vib = line.split("" + FssUtils.FSS_FS);
					vibSubset.setFields(vib);
				} else if (lineType.equalsIgnoreCase("VIR")) {
					virSubset = new TsVIR();
					String[] vir = line.split("" + FssUtils.FSS_FS);
					virSubset.setFields(vir);
					
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
	
	public TsVIA getViaSubset() {
		return viaSubset;
	}
	public void setViaSubset(TsVIA viaSubset) {
		this.viaSubset = viaSubset;
	}

	public TsVIB getVibSubset() {
		return vibSubset;
	}
	public void setVibSubset(TsVIB vibSubset) {
		this.vibSubset = vibSubset;
	}

	public TsVIR getVirSubset() {
		return virSubset;
	}
	public void setVirSubset(TsVIR vir) {
		this.virSubset = vir;
	}
	
}

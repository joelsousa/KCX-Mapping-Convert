/*
 * Modul    	: KidsToUids.java
 * Titel       	:
 * Date        	: 17.10.2008
 * @author      : Kewill CSF / Christine Kron
 * Description 	: Contains the Message ExportDeclaration in the ZABIS FSS-Format  
 * 			    : Version 6.0
 * Parameters  	:
 *  
 * Changes 
 * -----------
 * Author      : EI
 * Date        : 05.05.2009
 * Label       : EI20090505
 * Description : removed argument "typ" from setConsignee(...) aso.
 *
 * Author      : EI
 * Date        : 14.08.2009
 * Label       : EI20090814
 * Description : BAV, BZL 
 */

package com.kewill.kcx.component.mapping.formats.fss.ncts.messages;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import java.util.Vector;

import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62.TsVDZ;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62.TsVIA;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62.TsVIB;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62.TsVIP;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62.TsVIS;
import com.kewill.kcx.component.mapping.formats.fss.utils.FssUtils;

import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.fss.common.TsVOR;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Modul 	: MsgVIA<br>
 * Erstellt : 2010.09.03<br>
 * Beschreibung : FSS Message for VIA.
 * 
 * @author Frederick T
 * @version 6.0.00
 */
public class MsgVIA extends FssMessage62 {

	private TsVOR vorSubset;
	private TsVIA viaSubset;
	private TsVIB vibSubset;
	private TsVIS visSubset;

	private List<TsVIP> vipSubsetList;

	public String getFssString() throws FssException {
		String res = "";

		res = appendString(res, vorSubset.teilsatzBilden());

		//if (viaSubset != null && !viaSubset.isEmpty()) {
		if (viaSubset != null) {	//EI20111011 im Hauptsatz keine isEmpty Abfrage
			res = appendString(res, viaSubset.teilsatzBilden());
		}

		if (vibSubset != null && !vibSubset.isEmpty()) {
			res = appendString(res, vibSubset.teilsatzBilden());
		}

		if (visSubset != null && !visSubset.isEmpty()) {
			res = appendString(res, visSubset.teilsatzBilden());
		}
		if (vipSubsetList != null) {
			int vipSize = vipSubsetList.size();
			for (int j = 0; j < vipSize; j++) {
				TsVIP vip = (TsVIP) vipSubsetList.get(j);
				if (vip != null && !vip.isEmpty()) {
					res = appendString(res, vip.teilsatzBilden());
				}
			}
		}				
		// decommented because of NullPointerExceptions!!
//		for (TsVIP vipSubset : vipSubsetList) {
//			if (!vipSubset.isEmpty()) {
//				res = appendString(res, vipSubset.teilsatzBilden());
//			}
//		}

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
				} else if (lineType.equalsIgnoreCase("VIS")) {
					visSubset = new TsVIS();
					String[] vis = line.split("" + FssUtils.FSS_FS);
					visSubset.setFields(vis);
				} else if (lineType.equalsIgnoreCase("VIP")) {
					TsVIP vipSubset = new TsVIP();
					String[] vip = line.split("" + FssUtils.FSS_FS);
					vipSubset.setFields(vip);					
					/*if (vipSubsetList == null) {
						vipSubsetList = new Vector<TsVIP>();
					}					
					vipSubsetList.add(vipSubset);*/					
					addVipSubsetList(vipSubset);
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

	public TsVIS getVisSubset() {
		return visSubset;
	}

	public void setVisSubset(TsVIS visSubset) {
		this.visSubset = visSubset;
	}

	public void addVipSubsetList(TsVIP vipSubset) {
		if (vipSubset == null) {
			return;
		}

		if (vipSubsetList == null) {
			vipSubsetList = new Vector<TsVIP>();
		}

		this.vipSubsetList.add(vipSubset);
	}

	public List<TsVIP> getVipSubsetList() {
		return this.vipSubsetList;
	}
}

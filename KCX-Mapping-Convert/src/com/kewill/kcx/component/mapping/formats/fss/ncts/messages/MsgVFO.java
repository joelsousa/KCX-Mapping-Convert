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

import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62.TsVFO;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62.TsVFP;
import com.kewill.kcx.component.mapping.formats.fss.utils.FssUtils;

import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.fss.common.TsVOR;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Modul		: MsgADA<br>
 * Erstellt		: 12.05.2009<br>
 * Beschreibung	: .
 * 
 * @author iwaniuk
 * @version 6.0.00
 */
public class MsgVFO extends FssMessage62 {

	private TsVOR	vorSubset;
	private TsVFO	vfoSubset;
	private List< TsVFP > vfpSubsetList;
	
	public TsVOR getVorSubset() {
		return vorSubset;
	}
	public void setVorSubset(TsVOR vor) {
		this.vorSubset = vor;
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
		String res = "";
		
		res	= appendString(res, vorSubset.teilsatzBilden());
		
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


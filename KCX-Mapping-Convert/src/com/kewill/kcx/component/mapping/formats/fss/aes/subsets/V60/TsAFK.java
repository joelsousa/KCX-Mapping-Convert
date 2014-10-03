/*
 * Function    : TsAFK.java
 * Titel       :
 * Date        : 27.08.2008
 * Author      : Kewill CSF / Koschara
 * Description : 
 * Parameters  : 

 * Changes 
 * -----------
 * Author      :
 * Date        :
 * Label       :
 * Description :
 *
 */

package com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

public class TsAFK extends Teilsatz {

	private String beznr 	= "";		// Bezugsnummer
	private String mrn		= "";		// MRN
	private String fregnr	= "";		// Registriernummerfremdsystem
	
    public TsAFK() {
        tsTyp = "AFK";
    }
	
	public void setFields(String[] fields) {
		int size = fields.length;
		String ausgabe = "FSS: "+fields[0]+" size = "+ size;
		//Utils.log( ausgabe);
		
		if (size < 1 ) return;		
        tsTyp = fields[0];
        if (size < 2 ) return;
        beznr   = fields[1];
        if (size < 3 ) return;
        mrn	    = fields[2];
        if (size < 4 ) return;
        fregnr  = fields[3];   
	}

	public String teilsatzBilden() {
        StringBuffer buff = new StringBuffer();
        buff = buff.append(tsTyp);
        buff = buff.append(trenner);
        buff = buff.append(mrn);
        buff = buff.append(trenner);
        buff = buff.append(fregnr);
        buff = buff.append(trenner);
        return new String(buff);
	}

	public String getBeznr() {
		return beznr;
	
	}

	public void setBeznr(String beznr) {
		this.beznr = Utils.checkNull(beznr);
	}

	public String getMrn() {
		return mrn;
	
	}

	public void setMrn(String mrn) {
		this.mrn = Utils.checkNull(mrn);
	}

	public String getFregnr() {
		return fregnr;
	
	}

	public void setFregnr(String fregnr) {
		this.fregnr = Utils.checkNull(fregnr);
	}
	
	public boolean isEmpty() {
		//Utils.isStringEmpty(beznr)  &&
		if ( Utils.isStringEmpty(mrn) && Utils.isStringEmpty(fregnr) ) 
			return true;
		else
			return false;
	} 
	
}

package com.kewill.kcx.component.mapping.formats.fss.suma.subsets.V70;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Modul		:	Manifest
 * Erstellt		:	17.12.2013
 * Beschreibung	:   Kopfsatz Zeile CND.
 *        			Zabis Version 70  
 *@author iwaniuk
 *@version 7.0.00
 */

public class CsnCND  extends Teilsatz {

	private String csnTrenner	= "/";			
	private String mrn	= "";
	private String anz	= "";

	 public CsnCND() {
	        tsTyp = "CND";
	    }

	    public void setFields(String[] fields) {
			int size = fields.length;
			//String ausgabe = "FSS: "+fields[0] + " size = " + size;
			//Utils.log( ausgabe);
				
			
			if (size < 1) { return; }		
	        tsTyp = fields[0];
	        if (size < 2) { return; }		
	        mrn = fields[1];
	        if (size < 3) { return; }
	        anz = fields[2];	       
	    }

	 public String teilsatzBilden() {
	        StringBuffer buff = new StringBuffer();

	        buff.append(tsTyp);
	        buff.append(csnTrenner);
	        buff.append(mrn);
	        buff.append(csnTrenner);
	        buff.append(anz);
	        
	        return new String(buff);
	    }

	 public String getTsTyp() {
	 	return tsTyp;
	 }
	 public void setTsTyp(String tsTyp) {
	 	this.tsTyp = Utils.checkNull(tsTyp);
	 }

	 public String getMrn() {
		return mrn;
	 }
	 public void setMrn(String value) {
		this.mrn = Utils.checkNull(value);
	 }

	 public String getAnz() {
		return anz;
	 }
	 public void setAnz(String value) {
		this.anz = Utils.checkNull(value);
	 }

	public boolean isEmpty() {
 		return (Utils.isStringEmpty(mrn) && Utils.isStringEmpty(anz));
 	}
}



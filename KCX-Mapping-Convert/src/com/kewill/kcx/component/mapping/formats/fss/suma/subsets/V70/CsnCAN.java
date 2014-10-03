package com.kewill.kcx.component.mapping.formats.fss.suma.subsets.V70;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Modul		:	Manifest
 * Erstellt		:	17.12.2013
 * Beschreibung	:   Kopfsatz Zeile CAN.
 *        			Zabis Version 70  
 *@author iwaniuk
 *@version 7.0.00
 */

public class CsnCAN  extends Teilsatz {

	private String csnTrenner	= "/";		
	private String code	= "CS";
	private String dst = "";
	private String append = "001";

	 public CsnCAN() {
	        tsTyp = "CAN";
	    }

	 public void setFields(String[] fields) {
			int size = fields.length;
			
			if (size < 1) { return; }		
	        tsTyp = fields[0];
	        if (size < 2) { return; }		
	        code = fields[1];
	        if (size < 3) { return; }
	        dst = fields[2];
	        if (size < 4) { return; }
	        append = fields[3];
	 }

	 public String teilsatzBilden() {
	        StringBuffer buff = new StringBuffer();

	        buff.append(tsTyp);
	        buff.append(csnTrenner);
	        buff.append(code);
	        buff.append(csnTrenner);
	        buff.append(dst);
	        buff.append(csnTrenner);
	        buff.append(append);
	        
	        return new String(buff);
	    }

	 public String getTsTyp() {
	 	return tsTyp;
	 }
	 public void setTsTyp(String tsTyp) {
	 	this.tsTyp = Utils.checkNull(tsTyp);
	 }

	 
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = Utils.checkNull(code);
	}

	public String getDst() {
		return dst;
	}
	public void setDst(String dst) {
		this.dst = Utils.checkNull(dst);
	}
	
	public String getAppend() {
		return append;
	}
	public void setAppend(String append) {
		this.append = Utils.checkNull(append);
	}

	public boolean isEmpty() {
 		return (Utils.isStringEmpty(code) && Utils.isStringEmpty(dst) &&
 				Utils.isStringEmpty(append));
 	}
}



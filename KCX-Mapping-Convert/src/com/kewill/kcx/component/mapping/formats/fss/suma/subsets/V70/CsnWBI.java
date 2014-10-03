package com.kewill.kcx.component.mapping.formats.fss.suma.subsets.V70;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Modul		:	Manifest
 * Erstellt		:	17.12.2013
 * Beschreibung	:   Kopfsatz Zeile WBI.
 *        			Zabis Version 70  
 *@author iwaniuk
 *@version 7.0.00
 */

public class CsnWBI  extends Teilsatz {

	private String csnTrenner	= "/";			
	private String awb	= "";
	private String type	= "M";

	 public CsnWBI() {
	        tsTyp = "WBI";
	    }

	    public void setFields(String[] fields) {
			int size = fields.length;
			//String ausgabe = "FSS: "+fields[0] + " size = " + size;
			//Utils.log( ausgabe);
				
			
			if (size < 1) { return; }		
	        tsTyp = fields[0];
	        if (size < 2) { return; }		
	        awb = fields[1];
	        if (size < 3) { return; }
	        type = fields[2];	       
	    }

	 public String teilsatzBilden() {
	        StringBuffer buff = new StringBuffer();

	        buff.append(tsTyp);
	        buff.append(csnTrenner);
	        buff.append(awb);
	        buff.append(csnTrenner);
	        buff.append(type);
	        
	        return new String(buff);
	    }

	 public String getTsTyp() {
	 	return tsTyp;
	 }
	 public void setTsTyp(String tsTyp) {
	 	this.tsTyp = Utils.checkNull(tsTyp);
	 }

	 public String getAwb() {
		return awb;
	 }
	 public void setAwb(String value) {
		this.awb = Utils.checkNull(value);
	 }

	 public String getType() {
		return type;
	 }
	 public void setType(String value) {
		this.type = Utils.checkNull(value);
	 }

	public boolean isEmpty() {
 		return (Utils.isStringEmpty(awb) && Utils.isStringEmpty(type));
 	}
}



package com.kewill.kcx.component.mapping.formats.fss.suma.subsets.V70;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Modul		:	Manifest
 * Erstellt		:	17.12.2013
 * Beschreibung	:   Kopfsatz Zeile DTN.
 *        			Zabis Version 70  
 *@author iwaniuk
 *@version 7.0.00
 */

public class CsnDTN  extends Teilsatz {

	private String csnTrenner	= "/";			
	private String dayMon	= "";
	private String time	= "";

	 public CsnDTN() {
	        tsTyp = "DTN";
	    }

	    public void setFields(String[] fields) {
			int size = fields.length;
			//String ausgabe = "FSS: "+fields[0] + " size = " + size;
			//Utils.log( ausgabe);
				
			
			if (size < 1) { return; }		
	        tsTyp = fields[0];
	        if (size < 2) { return; }		
	        dayMon = fields[1];
	        if (size < 3) { return; }
	        time = fields[2];	       
	    }

	 public String teilsatzBilden() {
	        StringBuffer buff = new StringBuffer();

	        buff.append(tsTyp);
	        buff.append(csnTrenner);
	        buff.append(dayMon);
	        buff.append(csnTrenner);
	        buff.append(time);
	        
	        return new String(buff);
	    }

	 public String getTsTyp() {
	 	return tsTyp;
	 }
	 public void setTsTyp(String tsTyp) {
	 	this.tsTyp = Utils.checkNull(tsTyp);
	 }

	public String getDayMon() {
			return dayMon;
	}
	public void setDayMon(String value) {
		this.dayMon = Utils.checkNull(value);
	}

	public String getTime() {
		return time;
	}
	public void setTime(String value) {
		this.time = Utils.checkNull(value);
	}

	public boolean isEmpty() {
 		return (Utils.isStringEmpty(dayMon) && Utils.isStringEmpty(time));
 	}
}



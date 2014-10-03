package com.kewill.kcx.component.mapping.formats.fss.suma.subsets.V70;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Modul		:	Manifest
 * Erstellt		:	17.12.2013
 * Beschreibung	:   Kopfsatz Zeile FLT.
 *        			Zabis Version 70  
 *@author iwaniuk
 *@version 7.0.00
 */

public class CsnFLT  extends Teilsatz {

	private String csnTrenner	= "/";			
	private String flightNr	= "";
	private String airports	= "";   //departure + arrival
	private String dayMon	= "";

	 public CsnFLT() {
	        tsTyp = "FLT";
	    }

	 public void setFields(String[] fields) {
			int size = fields.length;
			
			if (size < 1) { return; }		
	        tsTyp = fields[0];
	        if (size < 2) { return; }		
	        flightNr = fields[1];
	        if (size < 3) { return; }
	        airports = fields[2];
	        if (size < 4) { return; }
	        dayMon = fields[3];
	 }

	 public String teilsatzBilden() {
	        StringBuffer buff = new StringBuffer();

	        buff.append(tsTyp);
	        buff.append(csnTrenner);
	        buff.append(flightNr);
	        buff.append(csnTrenner);
	        buff.append(airports);
	        buff.append(csnTrenner);
	        buff.append(dayMon);
	        
	        return new String(buff);
	    }

	 public String getTsTyp() {
	 	return tsTyp;
	 }
	 public void setTsTyp(String tsTyp) {
	 	this.tsTyp = Utils.checkNull(tsTyp);
	 }

	 
	public String getFlightNr() {
		return flightNr;
	}
	public void setFlightNr(String flightNr) {
		this.flightNr = Utils.checkNull(flightNr);
	}

	public String getAirports() {
		return airports;
	}
	public void setAirports(String airports) {
		this.airports = Utils.checkNull(airports);
	}
	
	public String getDayMon() {
		return dayMon;
	}
	public void setDayMon(String dayMon) {
		this.dayMon = Utils.checkNull(dayMon);
	}

	public boolean isEmpty() {
 		return (Utils.isStringEmpty(flightNr) && Utils.isStringEmpty(airports) &&
 				Utils.isStringEmpty(dayMon));
 	}
}



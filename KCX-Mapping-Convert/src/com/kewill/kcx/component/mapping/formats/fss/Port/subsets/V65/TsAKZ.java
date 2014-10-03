package com.kewill.kcx.component.mapping.formats.fss.Port.subsets.V65; 

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module       : Port
 * Created		: 21.10.2011<br>
 * Description	: Hafenauftrag Zusatzangaben  / MsgPOR.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */

public class TsAKZ extends Teilsatz {

	 private String beznr	= "";	 // Bezugsnummer
	 private String kzlib	= "";	 // Kennzeichen direkte Übernahme
	 private String bunr	= "";	 // Buchungsnummer
	 private String blnr	= "";	 // B/L-Nummer
	 private String spdpwd	= "";	 // Passwort
	 private String rgwech 	= "";	 // Beantragungsnummer, Rechnungswechsel
	
    public TsAKZ() {
	    tsTyp = "AKZ";
    }

    public void setFields(String[] fields) {
    	int size = fields.length;
    	//String ausgabe = "FSS: " + fields[0] + " size = " + size;

    	if (size < 1) { return; }
    	tsTyp = fields[0];
	    if (size < 2) { return; }
	    beznr = fields[1];
	    if (size < 3) { return; }
	    kzlib = fields[2];
	    if (size < 4) { return; }
	    bunr = fields[3];
	    if (size < 5) { return; }
	    blnr = fields[4];
	    if (size < 6) { return; }
	    spdpwd = fields[5];
	    if (size < 7) { return; }
	    rgwech = fields[6];	    
    }

    public String teilsatzBilden() {
    	StringBuffer buff = new StringBuffer();

    	buff.append(tsTyp);
    	buff.append(trenner);
    	buff.append(beznr);
    	buff.append(trenner);
    	buff.append(kzlib);
    	buff.append(trenner);
    	buff.append(bunr);
    	buff.append(trenner);
    	buff.append(blnr);
    	buff.append(trenner);
    	buff.append(spdpwd);
    	buff.append(trenner);
    	buff.append(rgwech);
    	buff.append(trenner);
    	
    	return new String(buff);    
    }
    
    public String getBeznr() {
    	 return beznr;
    }
    public void setBeznr(String beznr) {
    	this.beznr = Utils.checkNull(beznr);
    }

    public String getKzlib() {
    	 return kzlib;
    }
    public void setKzlib(String kzlib) {
    	this.kzlib = Utils.checkNull(kzlib);
    }

    public String getBunr() {
    	 return bunr;
    }
    public void setBunr(String bunr) {
    	this.bunr = Utils.checkNull(bunr);
    }

    public String getBlnr() {
    	 return blnr;
    }
    public void setBlnr(String blnr) {
    	this.blnr = Utils.checkNull(blnr);
    }

    public String getSpdpwd() {
    	 return spdpwd;
    }
    public void setSpdpwd(String spdpwd) {
    	this.spdpwd = Utils.checkNull(spdpwd);
    }
    
    public String getRgwech() {
   	 	return rgwech;
    }
    public void setRgwech(String argument) {
    	this.rgwech = Utils.checkNull(argument);
    }   

    public boolean isEmpty() {
	  return  Utils.isStringEmpty(kzlib) &&
		  	  Utils.isStringEmpty(bunr) &&
		  	  Utils.isStringEmpty(blnr) &&
		  	  Utils.isStringEmpty(spdpwd) &&		
		  	  Utils.isStringEmpty(rgwech);
    }
   
}


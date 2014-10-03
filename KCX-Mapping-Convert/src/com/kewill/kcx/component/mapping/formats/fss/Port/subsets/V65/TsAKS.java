package com.kewill.kcx.component.mapping.formats.fss.Port.subsets.V65; 

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module       : Port
 * Created		: 21.10.2011<br>
 * Description	: Hafenauftrag.SACO-Referenzen  / MsgPOR.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */

public class TsAKS extends Teilsatz {

	 private String beznr	= "";	 // Bezugsnummer
	 private String lfdnr	= "";	 // laufende Nummer
	 private String saconr	= "";	 // SACO B/Z/S -Nummer
	 private String savoll	= "";	 // Vollständigkeitskennzeichen
	 
    public TsAKS() {
	    tsTyp = "AKS";
    }

    public void setFields(String[] fields) {
    	int size = fields.length;
    	String ausgabe = "FSS: " + fields[0] + " size = " + size;

    	if (size < 1) { return; }
    	tsTyp = fields[0];
	    if (size < 2) { return; }
	    beznr = fields[1];
	    if (size < 3) { return; }
	    lfdnr = fields[2];
	    if (size < 4) { return; }
	    saconr = fields[3];
	    if (size < 5) { return; }
	    savoll = fields[4];
    }

    public String teilsatzBilden() {
    	StringBuffer buff = new StringBuffer();

    	buff.append(tsTyp);
    	buff.append(trenner);
    	buff.append(beznr);
    	buff.append(trenner);
    	buff.append(lfdnr);
    	buff.append(trenner);
    	buff.append(saconr);
    	buff.append(trenner);
    	buff.append(savoll);
    	buff.append(trenner);
    	
    	return new String(buff);    
    }
    
    public String getBeznr() {
    	 return beznr;
    }
    public void setBeznr(String argument) {
    	this.beznr = Utils.checkNull(argument);
    }

    public String getLfdnr() {
    	 return lfdnr;
    }
    public void setLfdnr(String argument) {
    	this.lfdnr = Utils.checkNull(argument);
    }

    public String getSaconr() {
    	 return saconr;
    }
    public void setSaconr(String argument) {
    	this.saconr = Utils.checkNull(argument);
    }

    public String getSavoll() {
    	 return savoll;
    }
    public void setSavoll(String argument) {
    	this.savoll = Utils.checkNull(argument);
    }

    public boolean isEmpty() {
    	return  Utils.isStringEmpty(saconr) && Utils.isStringEmpty(savoll);		  
    }

}


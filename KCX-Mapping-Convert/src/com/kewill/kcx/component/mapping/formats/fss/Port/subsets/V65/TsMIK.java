package com.kewill.kcx.component.mapping.formats.fss.Port.subsets.V65; 

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module       : Port
 * Created		: 24.10.2011<br>
 * Description	: Mindermengenmeldung .
 * 
 * @author iwaniuk
 * @version 1.0.00
 */

public class TsMIK extends Teilsatz {

	private String beznr	= "";	 // Bezugsnummer
	private String mrn		= "";	 // 
	private String knztst	= "";	 // Kennzeichen Testantrag
	 
    public TsMIK() {
	    tsTyp = "MIK";
    }

    public void setFields(String[] fields) {
    	int size = fields.length;
    	String ausgabe = "FSS: " + fields[0] + " size = " + size;

    	if (size < 1) { return; }
    	tsTyp = fields[0];
	    if (size < 2) { return; }
	    beznr = fields[1];
	    if (size < 3) { return; }
	    mrn = fields[2];
	    if (size < 4) { return; }
	    knztst = fields[3];	   
    }

    public String teilsatzBilden() {
    	StringBuffer buff = new StringBuffer();

    	buff.append(tsTyp);
    	buff.append(trenner);
    	buff.append(beznr);
    	buff.append(trenner);
    	buff.append(mrn);
    	buff.append(trenner);
    	buff.append(knztst);
    	buff.append(trenner);
    	
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
    public void setMrn(String connr) {
    	this.mrn = Utils.checkNull(connr);
    }

    public String getKnztst() {
    	 return knztst;
    }
    public void setKnztst(String txt) {
    	this.knztst = Utils.checkNull(txt);
    }

    public boolean isEmpty() {
    	return  Utils.isStringEmpty(mrn) &&    	
    			Utils.isStringEmpty(knztst);
    }
}


package com.kewill.kcx.component.mapping.formats.fss.Port.subsets.V65; 

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module       : Port
 * Created		: 24.10.2011<br>
 * Description	: Textangaben zum Container. 
 * 
 * @author iwaniuk
 * @version 1.0.00
 */

public class TsACT extends Teilsatz {

	private String beznr	= "";	 // Bezugsnummer
	private String connr	= "";	 // Containernummer
	private String txt		= "";	 // 
	 
    public TsACT() {
	    tsTyp = "ACT";
    }

    public void setFields(String[] fields) {
    	int size = fields.length;
    	String ausgabe = "FSS: " + fields[0] + " size = " + size;

    	if (size < 1) { return; }
    	tsTyp = fields[0];
	    if (size < 2) { return; }
	    beznr = fields[1];
	    if (size < 3) { return; }
	    connr = fields[2];
	    if (size < 4) { return; }
	    txt = fields[3];	   
    }

    public String teilsatzBilden() {
    	StringBuffer buff = new StringBuffer();

    	buff.append(tsTyp);
    	buff.append(trenner);
    	buff.append(beznr);
    	buff.append(trenner);
    	buff.append(connr);
    	buff.append(trenner);
    	buff.append(txt);
    	buff.append(trenner);
    	
    	return new String(buff);
    }

    public String getBeznr() {
    	 return beznr;
    }
    public void setBeznr(String beznr) {
    	this.beznr = Utils.checkNull(beznr);
    }

    public String getConnr() {
    	 return connr;
    }
    public void setConnr(String connr) {
    	this.connr = Utils.checkNull(connr);
    }

    public String getTxt() {
    	 return txt;
    }
    public void setTxt(String txt) {
    	this.txt = Utils.checkNull(txt);
    }

    public boolean isEmpty() {
    	return  Utils.isStringEmpty(connr) &&    	
    			Utils.isStringEmpty(txt);
    }
}


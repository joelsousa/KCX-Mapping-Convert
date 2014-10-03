package com.kewill.kcx.component.mapping.formats.fss.Port.subsets.V65; 

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module       : Port
 * Created		: 21.10.2011<br>
 * Description	: Hafenauftrag.TransportNachlauf / MsgPOR.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */

public class TsAKN extends Teilsatz {

	 private String beznr	= "";	 // Bezugsnummer
	 private String ntpcde	= "";	 // Nachlauf Transportart, Code
	 private String ntpid	= "";	 // Nachlauf Funkrufzeichen
	 private String ntpken	= "";	 // Nachlauf Transportmittel Identifikation
	 
    public TsAKN() {
	    tsTyp = "AKN";
    }

    public void setFields(String[] fields) {
    	int size = fields.length;
    	//String ausgabe = "FSS: " + fields[0] + " size = " + size;

    	if (size < 1) { return; }
    	tsTyp = fields[0];
	    if (size < 2) { return; }
	    beznr = fields[1];
	    if (size < 3) { return; }
	    ntpcde = fields[2];
	    if (size < 4) { return; }
	    ntpid = fields[3];
	    if (size < 5) { return; }
	    ntpken = fields[4];	   
    }

    public String teilsatzBilden() {
    	StringBuffer buff = new StringBuffer();

    	buff.append(tsTyp);
    	buff.append(trenner);
    	buff.append(beznr);
    	buff.append(trenner);
    	buff.append(ntpcde);
    	buff.append(trenner);
    	buff.append(ntpid);
    	buff.append(trenner);
    	buff.append(ntpken);
    	buff.append(trenner);
    	
    	return new String(buff);    
    }

    public String getBeznr() {
    	 return beznr;
    }
    public void setBeznr(String beznr) {
    	this.beznr = Utils.checkNull(beznr);
    }

    public String getNtpcde() {
    	 return ntpcde;
    }
    public void setNtpcde(String ntpcde) {
    	this.ntpcde = Utils.checkNull(ntpcde);
    }

    public String getNtpid() {
    	 return ntpid;
    }
    public void setNtpid(String ntpid) {
    	this.ntpid = Utils.checkNull(ntpid);
    }

    public String getNtpken() {
    	 return ntpken;
    }
    public void setNtpken(String ntpken) {
    	this.ntpken = Utils.checkNull(ntpken);
    }

    public boolean isEmpty() {
	  return  Utils.isStringEmpty(ntpcde) &&
	  		  Utils.isStringEmpty(ntpid) &&		
	  		  Utils.isStringEmpty(ntpken);
    }

}


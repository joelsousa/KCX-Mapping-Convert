package com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V70;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		:	Export/aes
 * Created		:	30.07.2012
 * Description	:	Daten der Anmahnung AUG.
 *
 * @author iwaniuk
 * @version 7.0.00
 *
 */


public class TsOldAUG extends Teilsatz {

    private String tsTyp  = "";                   //Ts-Schlüssel
    private String beznr  = "";                   //Bezugsnummer
    private String sawdat = "";                   //Datum der spätestmöglichen Antwort
    private String anmdat = "";                   //Datum der Anmahnung

    public TsOldAUG() {
        tsTyp = "AUG";
    }

    public void setFields(String[] fields) {    
		int size = fields.length;
			
		if (size < 1) { return; }		
        tsTyp  = fields[0];    	
        if (size < 2) { return; }	
        beznr  = fields[1];
        if (size < 3) { return; }
        sawdat = fields[2];
        if (size < 4) { return; }	
        anmdat = fields[3];   
    }

    public String teilsatzBilden() {
        StringBuffer buff = new StringBuffer();

        buff.append(tsTyp);
        buff.append(trenner);
        buff.append(beznr);
        buff.append(trenner);
        buff.append(sawdat);
        buff.append(trenner);
        buff.append(anmdat);
        buff.append(trenner); 
        
        return new String(buff);
    }

	public String getTsTyp() {
		return tsTyp;
	}
	public void setTsTyp(String tsTyp) {
		this.tsTyp = Utils.checkNull(tsTyp);
	}

	public String getBeznr() {
		return beznr;
	}
	public void setBeznr(String beznr) {
		this.beznr = Utils.checkNull(beznr);
	}

	public String getSawdat() {
		return sawdat;
	}
	public void setSawdat(String sawdat) {
		this.sawdat = Utils.checkNull(sawdat);
	}

	public String getAnmdat() {
		return anmdat;
	}
	public void setAnmdat(String anmdat) {
		this.anmdat = Utils.checkNull(anmdat);
	}
	
	public boolean isEmpty() {
		return (Utils.isStringEmpty(sawdat) && Utils.isStringEmpty(anmdat));		  		
	}
}



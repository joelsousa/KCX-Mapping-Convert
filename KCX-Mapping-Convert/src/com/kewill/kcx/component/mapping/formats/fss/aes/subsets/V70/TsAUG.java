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


public class TsAUG extends Teilsatz {

    private String tsTyp  = "";                   //Ts-Schlüssel
    private String beznr  = "";                   //Bezugsnummer
    private String mrn    = "";      //EI20121004
    private String eamdst = "";      //EI20121004    
    private String sawdat = "";                   //Datum der spätestmöglichen Antwort
    private String urgdat = "";                   //Datum der Anmahnung

    public TsAUG() {
        tsTyp = "AUG";
    }

    public void setFields(String[] fields) {    
		int size = fields.length;
			
		if (size < 1) { return; }		
        tsTyp  = fields[0];    	
        if (size < 2) { return; }	
        beznr  = fields[1];
        if (size < 3) { return; }
        mrn = fields[2];
        if (size < 4) { return; }	
        eamdst = fields[3];
        if (size < 5) { return; }	
        sawdat = fields[4];
        if (size < 6) { return; }	
        urgdat = fields[5]; 
    }

    public String teilsatzBilden() {
        StringBuffer buff = new StringBuffer();

        buff.append(tsTyp);
        buff.append(trenner);
        buff.append(beznr);
        buff.append(trenner);
        buff.append(mrn);
        buff.append(trenner);
        buff.append(eamdst);
        buff.append(trenner); 
        buff.append(sawdat);
        buff.append(trenner);
        buff.append(urgdat);
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
		return urgdat;
	}
	public void setAnmdat(String urgdat) {
		this.urgdat = Utils.checkNull(urgdat);
	}
	
	public String getMrn() {
		return mrn;
	}
	public void setMrn(String mrn) {
		this.mrn = Utils.checkNull(mrn);
	}

	public String getEamdst() {
		return eamdst;
	}
	public void setEamdst(String eamdst) {
		this.eamdst = Utils.checkNull(eamdst);
	}
	
	public boolean isEmpty() {
		return Utils.isStringEmpty(sawdat) && Utils.isStringEmpty(urgdat) &&
			Utils.isStringEmpty(mrn) && Utils.isStringEmpty(eamdst);
	}
}



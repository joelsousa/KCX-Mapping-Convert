package com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V72;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Modul		:	TsAUP
 * Erstellt		:	22.07.2013
 * Beschreibung	:	Daten der Wiedervorlage AUP.
 * 				: 	neu in Zabis-V72: artwvl, svldat
 *
 * @author iwaniuk
 * @version 2.1.00
 */


public class TsAUP extends Teilsatz {

    private String tsTyp  = "";                   //Ts-Schlüssel
    private String beznr  = "";                   //Bezugsnummer
    private String sawdat = "";                   //Datum der spätestmöglichen Antwort
    private String nfgdat = "";                   //Datum der Nachforschung 
    private String artwvl = "";                   //EI20130722:	Art der Wiedervorlage (an1)
    private String svldat = "";                   //EI20130722:	Datum der spätestmöglichen Vorlage (date8)

    public TsAUP() {
        tsTyp = "AUP";
    }

    public void setFields(String[] fields) {
		int size = fields.length;
			
		if (size < 1) { return;	}	
        tsTyp         = fields[0];    	
        if (size < 2) { return;	}
        beznr         = fields[1];
        if (size < 3) { return;	}
        sawdat        = fields[2];
        if (size < 4) { return;	}
        nfgdat        = fields[3];  
        if (size < 5) { return;	}	
        artwvl         = fields[4];  
        if (size < 6) { return;	}	
        svldat         = fields[5];  
     }

    public String teilsatzBilden() {
        StringBuffer buff = new StringBuffer();

        buff.append(tsTyp);
        buff.append(trenner);
        buff.append(beznr);
        buff.append(trenner);
        buff.append(sawdat);
        buff.append(trenner);
        buff.append(nfgdat);
        buff.append(trenner);         
        buff.append(artwvl);
        buff.append(trenner);
        buff.append(svldat);
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

	public String getNfgdat() {
		return nfgdat;
	}

	public void setNfgdat(String nfgdat) {
		this.nfgdat = Utils.checkNull(nfgdat);
	}	
	
	public String getArtwvl() {
		return artwvl;
	}

	public void setArtwvl(String artwvl) {
		this.artwvl = Utils.checkNull(artwvl);
	}

	public String getSvldat() {
		return svldat;
	}

	public void setSvldat(String svldat) {
		this.svldat = Utils.checkNull(svldat);
	}

	public boolean isEmpty() {
		return Utils.isStringEmpty(sawdat)  && Utils.isStringEmpty(nfgdat) && 
				Utils.isStringEmpty(svldat);		  			
	}

}



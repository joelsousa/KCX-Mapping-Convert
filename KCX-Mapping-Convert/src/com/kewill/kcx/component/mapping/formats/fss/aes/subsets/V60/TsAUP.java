package com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60;
import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Modul		:	TsAUP
 * Erstellt		:	02.10.2008
 * Beschreibung	:	Daten der Wiedervorlage AUP
 *
 * @author 			Miro Houdek
 *
 */


public class TsAUP extends Teilsatz {

    private String tsTyp  = "";                   //Ts-Schlüssel
    private String beznr  = "";                   //Bezugsnummer
    private String sawdat = "";                   //Datum der spätestmöglichen Antwort
    private String nfgdat = "";                   //Datum der Nachforschung 



    public TsAUP() {
        tsTyp = "AUP";
    }

    public void setFields(String[] fields)
    {
		int size = fields.length;
		String ausgabe = "FSS: "+fields[0]+" size = "+ size;
		//Utils.log( ausgabe);
			
		if (size < 1 ) return;		
        tsTyp         = fields[0];    	
        if (size < 2 ) return;	
        beznr         = fields[1];
        if (size < 3 ) return;	
        sawdat        = fields[2];
        if (size < 4 ) return;	
        nfgdat        = fields[3];   
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
	
	public boolean isEmpty() {
		if ( Utils.isStringEmpty(sawdat)  && Utils.isStringEmpty(nfgdat))		  
			return true;
		else
			return false;
	}

}



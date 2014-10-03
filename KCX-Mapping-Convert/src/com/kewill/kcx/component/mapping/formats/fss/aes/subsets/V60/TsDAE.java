package com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60;
import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/*
* Funktion    : TsDAE.java
* Titel       :
* Erstellt    : 03.09.2008
* Author      : CSF GmbH / Alfred Krzoska
* Beschreibung: 
* Anmerkungen : 
* Parameter   : 
* Rückgabe    : keine
* Aufruf      : 
*
* Änderungen:
* -----------
* Author      : EI
* Datum       : 06.03.2009 
* Kennzeichen : 
* Beschreibung: V60 new member: altnot
*
*/

public class TsDAE extends Teilsatz {
	private String beznr  = ""; //  Bezugsnummer
    private String anzpos = ""; //  Anzahl Positionen
    private String anzcol = ""; //  Anzahl Packstücke
    private String kzmin  = ""; //  Kennzeichen, dass im Ausgang Mindermengen erfasst sind
    private String altnot = ""; //  Kennzeichen Alternative Nachweis

    public TsDAE(){
    	tsTyp = "DAE";
    }

    public void setFields(String[] fields)
    {
		int size = fields.length;
		String ausgabe = "FSS: "+fields[0]+" size = "+ size;
		//Utils.log( ausgabe);
		
		if (size < 1 ) return;		
        tsTyp   = fields[0];    
        if (size < 2 ) return;	
        beznr    = fields[1];
        if (size < 3 ) return;		
        anzpos   = fields[2];
        if (size < 4 ) return;		
        anzcol   = fields[3];
        if (size < 5 ) return;		
        kzmin    = fields[4];
        if (size < 6 ) return;		
        altnot    = fields[5];
    }
    
    public String teilsatzBilden() {
        StringBuffer buff = new StringBuffer();

        buff.append(tsTyp);
        buff.append(trenner);
        buff.append(beznr);
        buff.append(trenner);
        buff.append(anzpos);
        buff.append(trenner);
        buff.append(anzcol);
        buff.append(trenner);
        buff.append(kzmin);
        buff.append(trenner);
        buff.append(altnot);
        buff.append(trenner);

        return new String(buff);
    }

	public String getBeznr() {
		return beznr;
	}

	public void setBeznr(String beznr) {
		this.beznr = Utils.checkNull(beznr);
	}

	public String getAnzpos() {
		return anzpos;
	}

	public void setAnzpos(String anzpos) {
		this.anzpos = Utils.checkNull(anzpos);
	}

	public String getAnzcol() {
		return anzcol;
	}

	public void setAnzcol(String anzcol) {
		this.anzcol = Utils.checkNull(anzcol);
	}

	public String getKzmin() {
		return kzmin;
	}

	public void setKzmin(String kzmin) {
		this.kzmin = Utils.checkNull(kzmin);
	}
	public String getAltnot() {
		return altnot;
	}

	public void setAltnot(String argument) {
		this.altnot = Utils.checkNull(argument);
	}	
	
	public boolean isEmpty() {
		//Utils.isStringEmpty(beznr)  &&
		if ( Utils.isStringEmpty(anzpos) && Utils.isStringEmpty(anzcol) 
		  && Utils.isStringEmpty(kzmin) && Utils.isStringEmpty(altnot)) 
			return true;
		else
			return false;
	}   
}
  
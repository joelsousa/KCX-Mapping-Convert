package com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V53;
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
* Author      :
* Datum       :
* Kennzeichen :
* Beschreibung:
* Anmerkungen :
* Parameter   :
*
*/

public class TsDAE extends Teilsatz {
	private String beznr  = ""; //  Bezugsnummer
	private String anzpos = ""; //  Anzahl Positionen
	private String gsroh  = ""; //Gesamtrohmasse  //in V6 in EDA verlegt
    private String anzcol = ""; //  Anzahl Packstücke
    private String kzmin  = ""; //  Kennzeichen, dass im Ausgang Mindermengen erfasst sind

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
        gsroh    = fields[3];
        if (size < 5 ) return;		
        anzcol   = fields[4];
        if (size < 6 ) return;		
        kzmin    = fields[5];
        
    }
    
    public String teilsatzBilden() {
        StringBuffer buff = new StringBuffer();

        buff.append(tsTyp);
        buff.append(trenner);
        buff.append(beznr);
        buff.append(trenner);
        buff.append(anzpos);
        buff.append(trenner);
        buff.append(gsroh);
        buff.append(trenner);
        buff.append(anzcol);
        buff.append(trenner);
        buff.append(kzmin);
        buff.append(trenner);

        return new String(buff);
    }

	public String getBeznr() {
		return beznr;
	}

	public void setBeznr(String argument) {
		this.beznr = Utils.checkNull(argument);
	}

	public String getAnzpos() {
		return anzpos;
	}

	public void setAnzpos(String argument) {
		this.anzpos = Utils.checkNull(argument);
	}

	public String getAnzcol() {
		return anzcol;
	}

	public void setAnzcol(String argument) {
		this.anzcol = Utils.checkNull(argument);
	}

	public String getKzmin() {
		return kzmin;
	}

	public void setKzmin(String argument) {
		this.kzmin = Utils.checkNull(argument);
	}
	
	public String getGsroh() {
		return gsroh;
	}

	public void setGsroh(String argument) {
		this.gsroh = Utils.checkNull(argument);
	}
	
	public boolean isEmpty() {
		//Utils.isStringEmpty(beznr)  &&
		if ( Utils.isStringEmpty(anzpos) && Utils.isStringEmpty(anzcol) 
		  && Utils.isStringEmpty(kzmin) ) 
			return true;
		else
			return false;
	}   
}
  
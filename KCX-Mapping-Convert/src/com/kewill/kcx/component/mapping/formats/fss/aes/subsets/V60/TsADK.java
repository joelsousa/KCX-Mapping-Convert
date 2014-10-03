
package com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60;
import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Modul		:	TsADK
 * Erstellt		:	19.08.2008
 * Beschreibung	:   14.5.41	 Ausgangdaten Kopfsatz   ADK
 *
 * 03.09.2008       Version 6  Miro Houdek
 *
 */

public class TsADK extends Teilsatz {

    private String tsTyp     = "";       // Ts-Schlüssel
    private String beznr     = "";       // Bezugsnummer
    private String artaus    = "";       // Art der Anmeldung
    private String conkz     = "";       // Kennzeichen Container
    private String ebeznr    = "";       // Bezugsnummer der Ausfuhranmeldung
    private String anzpos    = "";       // ges. Anzahl Positionen
    private String gsroh     = "";       // Gesamtrohmasse
    private String anzcol    = "";       // ges. Anzahl Packstücke
    private String expdst    = "";       // Ausfuhrzolldienststelle
    private String artueb    = "";       // Art der Überführung

    public TsADK() {
        tsTyp = "ADK";
    }

    public void setFields(String[] fields)
    {
		int size = fields.length;
		String ausgabe = "FSS: "+fields[0]+" size = "+ size;
		//Utils.log( ausgabe);
			
		
		if (size < 1 ) return;		
        tsTyp = fields[0];
        if (size < 2 ) return;	
           beznr         = fields[1];
           if (size < 3 ) return;
           artaus        = fields[2];
           if (size < 4 ) return;
           conkz         = fields[3];
           if (size < 5 ) return;
           ebeznr        = fields[4];
           if (size < 6 ) return;
           anzpos        = fields[5];
           if (size < 7 ) return;
           gsroh         = fields[6];
           if (size < 8 ) return;
           anzcol        = fields[7];
           if (size < 9 ) return;
           expdst        = fields[8];
           if (size < 10 ) return;
           artueb        = fields[9];
      }


    public String teilsatzBilden() {
        StringBuffer buff = new StringBuffer();

        buff.append(tsTyp);
        buff.append(trenner);
        buff.append(beznr);
        buff.append(trenner);
        buff.append(artaus);
        buff.append(trenner);
        buff.append(conkz);
        buff.append(trenner);
        buff.append(ebeznr);
        buff.append(trenner);
        buff.append(anzpos);
        buff.append(trenner);
        buff.append(gsroh);
        buff.append(trenner);
        buff.append(anzcol);
        buff.append(trenner);
        buff.append(expdst);
        buff.append(trenner);
        buff.append(artueb);
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

	public String getArtaus() {
		return artaus;
	
	}

	public void setArtaus(String artaus) {
		this.artaus = Utils.checkNull(artaus);
	}

	public String getConkz() {
		return conkz;
	
	}

	public void setConkz(String conkz) {
		this.conkz = Utils.checkNull(conkz);
	}

	public String getEbeznr() {
		return ebeznr;
	
	}

	public void setEbeznr(String ebeznr) {
		this.ebeznr = Utils.checkNull(ebeznr);
	}

	public String getAnzpos() {
		return anzpos;
	
	}

	public void setAnzpos(String anzpos) {
		this.anzpos = Utils.checkNull(anzpos);
	}

	public String getGsroh() {
		return gsroh;
	
	}

	public void setGsroh(String gsroh) {
		this.gsroh = Utils.checkNull(gsroh);
	}

	public String getAnzcol() {
		return anzcol;
	
	}

	public void setAnzcol(String anzcol) {
		this.anzcol = Utils.checkNull(anzcol);
	}

	public String getExpdst() {
		return expdst;
	
	}

	public void setExpdst(String expdst) {
		this.expdst = Utils.checkNull(expdst);
	}

	public String getArtueb() {
		return artueb;
	
	}

	public void setArtueb(String artueb) {
		this.artueb = Utils.checkNull(artueb);
	}
	
	public boolean isEmpty() {

		if ( Utils.isStringEmpty(artaus) && Utils.isStringEmpty(conkz) 
				&& Utils.isStringEmpty(ebeznr) && Utils.isStringEmpty(anzpos) 		  
		        && Utils.isStringEmpty(gsroh) && Utils.isStringEmpty(anzcol) 
		        && Utils.isStringEmpty(expdst) && Utils.isStringEmpty(artueb) )
			return true;
		else
			return false;
	} 
	
}



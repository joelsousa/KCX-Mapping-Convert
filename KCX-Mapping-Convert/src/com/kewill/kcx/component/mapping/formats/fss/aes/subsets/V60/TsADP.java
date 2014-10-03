package com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60;
import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Modul		:	TsADP
 * Erstellt		:	19.08.2008
 * Beschreibung	:   14.5.42	 Ausgangdaten Positionssatz   ADP
 *
 * 19.06.2008       Version 6  Miro Houdek
 *
 */

public class TsADP extends Teilsatz {

    private String tsTyp     = "";       // Ts-Schlüssel
    private String beznr     = "";       // Bezugsnummer
    private String posnr     = "";       // Positionsnummer
    private String timau     = "";       // TIN des Ausführers
    private String tinem     = "";       // TIN des Empfängers
    private String tnr       = "";       // Warentarifnummer
    private String tnrtrc    = "";       // Warennummer TARIC
    private String tnrzu1    = "";       // Warennummer 1. Zusatz
    private String tnrzu2    = "";       // Warennummer 2. Zusatz
    private String tnrnat    = "";       // Warennummer; nat. Angaben
    private String wbsch     = "";       // Warenbeschreibung
    private String eigmas    = "";       // Eigenmasse
    private String rohmas    = "";       // Rohmasse
    private String ldbe      = "";       // Bestimmungsland
    private String knrsdg    = "";       // Kennnummer der Sendung
    private String undgnr    = "";       // Gefahrgutnummer (UNDG)

    public TsADP() {
        tsTyp = "ADP";
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
           posnr         = fields[2];
           if (size < 4 ) return;
           timau         = fields[3];
           if (size < 5 ) return;
           tinem         = fields[4];
           if (size < 6 ) return;
           tnr           = fields[5];
           if (size < 7 ) return;
           tnrtrc        = fields[6];
           if (size < 8 ) return;
           tnrzu1        = fields[7];
           if (size < 9 ) return;
           tnrzu2        = fields[8];
           if (size < 10 ) return;
           tnrnat        = fields[9];
           if (size < 11 ) return;
           wbsch         = fields[10];
           if (size < 12 ) return;
           eigmas        = fields[11];
           if (size < 13 ) return;
           rohmas        = fields[12];
           if (size < 14 ) return;
           ldbe          = fields[13];
           if (size < 15 ) return;
           knrsdg        = fields[14];
           if (size < 16 ) return;
           undgnr        = fields[15];

      }

 public String teilsatzBilden() {
        StringBuffer buff = new StringBuffer();

        buff.append(tsTyp);
        buff.append(trenner);
        buff.append(beznr);
        buff.append(trenner);
        buff.append(posnr);
        buff.append(trenner);
        buff.append(timau);
        buff.append(trenner);
        buff.append(tinem);
        buff.append(trenner);
        buff.append(tnr);
        buff.append(trenner);
        buff.append(tnrtrc);
        buff.append(trenner);
        buff.append(tnrzu1);
        buff.append(trenner);
        buff.append(tnrzu2);
        buff.append(trenner);
        buff.append(tnrnat);
        buff.append(trenner);
        buff.append(wbsch);
        buff.append(trenner);
        buff.append(eigmas);
        buff.append(trenner);
        buff.append(rohmas);
        buff.append(trenner);
        buff.append(ldbe);
        buff.append(trenner);
        buff.append(knrsdg);
        buff.append(trenner);
        buff.append(undgnr);
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

public String getPosnr() {
	return posnr;

}

public void setPosnr(String posnr) {
	this.posnr = Utils.checkNull(posnr);
}

public String getTimau() {
	return timau;

}

public void setTimau(String timau) {
	this.timau = Utils.checkNull(timau);
}

public String getTinem() {
	return tinem;

}

public void setTinem(String tinem) {
	this.tinem = Utils.checkNull(tinem);
}

public String getTnr() {
	return tnr;

}

public void setTnr(String tnr) {
	this.tnr = Utils.checkNull(tnr);
}

public String getTnrtrc() {
	return tnrtrc;

}

public void setTnrtrc(String tnrtrc) {
	this.tnrtrc = Utils.checkNull(tnrtrc);
}

public String getTnrzu1() {
	return tnrzu1;

}

public void setTnrzu1(String tnrzu1) {
	this.tnrzu1 = Utils.checkNull(tnrzu1);
}

public String getTnrzu2() {
	return tnrzu2;

}

public void setTnrzu2(String tnrzu2) {
	this.tnrzu2 = Utils.checkNull(tnrzu2);
}

public String getTnrnat() {
	return tnrnat;

}

public void setTnrnat(String tnrnat) {
	this.tnrnat = Utils.checkNull(tnrnat);
}

public String getWbsch() {
	return wbsch;

}

public void setWbsch(String wbsch) {
	this.wbsch = Utils.checkNull(wbsch);
}

public String getEigmas() {
	return eigmas;

}

public void setEigmas(String eigmas) {
	this.eigmas = Utils.checkNull(eigmas);
}

public String getRohmas() {
	return rohmas;

}

public void setRohmas(String rohmas) {
	this.rohmas = Utils.checkNull(rohmas);
}

public String getLdbe() {
	return ldbe;

}

public void setLdbe(String ldbe) {
	this.ldbe = Utils.checkNull(ldbe);
}

public String getKnrsdg() {
	return knrsdg;

}

public void setKnrsdg(String knrsdg) {
	this.knrsdg = Utils.checkNull(knrsdg);
}

public String getUndgnr() {
	return undgnr;

}

public void setUndgnr(String undgnr) {
	this.undgnr = Utils.checkNull(undgnr);
}
public boolean isEmpty() {

	if ( Utils.isStringEmpty(timau)        && Utils.isStringEmpty(tinem) 
			&& Utils.isStringEmpty(tnr)    && Utils.isStringEmpty(tnrtrc) 		  
	        && Utils.isStringEmpty(tnrzu1) && Utils.isStringEmpty(tnrzu2) 
	        && Utils.isStringEmpty(tnrnat) && Utils.isStringEmpty(wbsch)  
	        && Utils.isStringEmpty(eigmas) && Utils.isStringEmpty(rohmas)  
	        && Utils.isStringEmpty(ldbe)   && Utils.isStringEmpty(knrsdg)  
	        && Utils.isStringEmpty(undgnr))
		return true;
	else
		return false;
} 

}



/*
 * Project:     fssin.uids.aes
 * Package:     fss.teilsaetze.aes
 * File   :     TsADD.java
 * Changed:
 *
 * Created on 22.05.2006
 * Beschreibung :
 * Dieser Teilsatz kommt in folgenden FSS-Nachrichten vor:
 * · Ausfuhranmeldung ADA
 * ·
 *
 */
package com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V53;
import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Modul        :   ADD
 * Teilsatz     :   TsADD.java
 * Erstellt     :   22.05.2006
 * Beschreibung :   Dokumente - Positionen ADD
 *
 * 03.09.2008       Version 6  Miro Houdek
 */


public class TsADD extends Teilsatz {

    private String tsTyp     = "";       // Ts-Schlüssel
    private String beznr     = "";       // Bezugsnummer
    private String lfdnr     = "";       // Zeilennummer
    private String lfdpos    = "";       // lfd. Nummer im Formular
    private String posnr     = "";       // Positionsnummer aus AM
    private String wbsch     = "";       // Freitext für Packstücke und
    private String rohmas    = "";       // Rohmasse
    private String rohmea    = "";       // Maßeinheit zur Rohmasse
    private String renr      = "";       // Rechnungsnummer zur Position

    public TsADD() {
        tsTyp = "ADD";
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
        lfdnr         = fields[2];
        if (size < 4 ) return;
        lfdpos        = fields[3];
        if (size < 5 ) return;
        posnr         = fields[4];
        if (size < 6 ) return;
        wbsch         = fields[5];
        if (size < 7 ) return;
        rohmas        = fields[6];
        if (size < 8 ) return;
        rohmea        = fields[7];
        if (size < 9 ) return;
        renr          = fields[8];
      }



 public String teilsatzBilden() {
        StringBuffer buff = new StringBuffer();

        buff.append(tsTyp);
        buff.append(trenner);
        buff.append(beznr);
        buff.append(trenner);
        buff.append(lfdnr);
        buff.append(trenner);
        buff.append(lfdpos);
        buff.append(trenner);
        buff.append(posnr);
        buff.append(trenner);
        buff.append(wbsch);
        buff.append(trenner);
        buff.append(rohmas);
        buff.append(trenner);
        buff.append(rohmea);
        buff.append(trenner);
        buff.append(renr);
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


public String getLfdnr() {
	return lfdnr;

}


public void setLfdnr(String lfdnr) {
	this.lfdnr = Utils.checkNull(lfdnr);
}


public String getLfdpos() {
	return lfdpos;

}


public void setLfdpos(String lfdpos) {
	this.lfdpos = Utils.checkNull(lfdpos);
}


public String getPosnr() {
	return posnr;

}


public void setPosnr(String posnr) {
	this.posnr = Utils.checkNull(posnr);
}


public String getWbsch() {
	return wbsch;

}


public void setWbsch(String wbsch) {
	this.wbsch = Utils.checkNull(wbsch);
}


public String getRohmas() {
	return rohmas;

}


public void setRohmas(String rohmas) {
	this.rohmas = Utils.checkNull(rohmas);
}


public String getRohmea() {
	return rohmea;

}


public void setRohmea(String rohmea) {
	this.rohmea = Utils.checkNull(rohmea);
}


public String getRenr() {
	return renr;

}


public void setRenr(String renr) {
	this.renr = Utils.checkNull(renr);
}


public boolean isEmpty() {
	if ( Utils.isStringEmpty(wbsch)
		 && Utils.isStringEmpty(rohmas)
		 && Utils.isStringEmpty(rohmea) 
		 && Utils.isStringEmpty(renr) ) 
		return true;
	else
		return false;
}	
	
}



package com.kewill.kcx.component.mapping.formats.fss.Import.subsets.V64;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Modul                :       TsAHI 
 * Erstellt             :       21.09.2011
 * Beschreibung 		:       Minderungsangaben für EZA 
 								Es können insgesamt maximal 10 Abzüge / Hinzurechnungen 
 								zur Position übermittelt werden.
 *
 * @author                      Alfred Krzoska
 * @version 1.0.00
 *
 */

public class TsAHI extends Teilsatz {

    private String ahibnr		 = "";	 // Bezugsnummer
    private String ahipnr		 = "";	 // Positionsnummer
    private String arhia		 = "";	 // Art der Abzüge / Hinzurechnungen 
    private String preis		 = "";	 // Betrag 
    private String ahwae		 = "";	 // Währung 
    private String kzia		 	 = "";	 // KZ IATA-Kurs 
    private String kzku		 	 = "";	 // KZ Kurs vereinbart 
    private String kurs		 	 = "";	 // Kurs 
    private String kudat		 = "";	 // Kursdatum 
    private String arhipr		 = "";	 // Prozentsatz Frachtkosten

    public TsAHI() {
	    tsTyp = "AHI";
    }

    public void setFields(String[] fields) {
    	int size = fields.length;
    	String ausgabe = "FSS: " + fields[0] + " size = " + size;

    	if (size < 1) { return; }
    	tsTyp = fields[0];

	    if (size < 2) { return; }
	    ahibnr = fields[1];

	    if (size < 3) { return; }
	    ahipnr = fields[2];

	    if (size < 4) { return; }
	    arhia = fields[3];

	    if (size < 5) { return; }
	    preis = fields[4];

	    if (size < 6) { return; }
	    ahwae = fields[5];

	    if (size < 7) { return; }
	    kzia = fields[6];

	    if (size < 8) { return; }
	    kzku = fields[7];

	    if (size < 9) { return; }
	    kurs = fields[8];

	    if (size < 10) { return; }
	    kudat = fields[9];

	    if (size < 11) { return; }
	    arhipr = fields[10];
    }



    public String getAhibnr() {
    	 return ahibnr;
    }


    public void setAhibnr(String ahibnr) {
    	this.ahibnr = Utils.checkNull(ahibnr);
    }


    public String getAhipnr() {
    	 return ahipnr;
    }


    public void setAhipnr(String ahipnr) {
    	this.ahipnr = Utils.checkNull(ahipnr);
    }


    public String getArhia() {
    	 return arhia;
    }


    public void setArhia(String arhia) {
    	this.arhia = Utils.checkNull(arhia);
    }


    public String getPreis() {
    	 return preis;
    }


    public void setPreis(String preis) {
    	this.preis = Utils.checkNull(preis);
    }


    public String getAhwae() {
    	 return ahwae;
    }


    public void setAhwae(String ahwae) {
    	this.ahwae = Utils.checkNull(ahwae);
    }


    public String getKzia() {
    	 return kzia;
    }


    public void setKzia(String kzia) {
    	this.kzia = Utils.checkNull(kzia);
    }


    public String getKzku() {
    	 return kzku;
    }


    public void setKzku(String kzku) {
    	this.kzku = Utils.checkNull(kzku);
    }


    public String getKurs() {
    	 return kurs;
    }


    public void setKurs(String kurs) {
    	this.kurs = Utils.checkNull(kurs);
    }


    public String getKudat() {
    	 return kudat;
    }


    public void setKudat(String kudat) {
    	this.kudat = Utils.checkNull(kudat);
    }


    public String getArhipr() {
    	 return arhipr;
    }


    public void setArhipr(String arhipr) {
    	this.arhipr = Utils.checkNull(arhipr);
    }


    public String teilsatzBilden() {
    	StringBuffer buff = new StringBuffer();

    	buff.append(tsTyp);
    	buff.append(trenner);
    	buff.append(ahibnr);
    	buff.append(trenner);
    	buff.append(ahipnr);
    	buff.append(trenner);
    	buff.append(arhia);
    	buff.append(trenner);
    	buff.append(preis);
    	buff.append(trenner);
    	buff.append(ahwae);
    	buff.append(trenner);
    	buff.append(kzia);
    	buff.append(trenner);
    	buff.append(kzku);
    	buff.append(trenner);
    	buff.append(kurs);
    	buff.append(trenner);
    	buff.append(kudat);
    	buff.append(trenner);
    	buff.append(arhipr);
    	buff.append(trenner);

    	return new String(buff);
    
    }



    public boolean isEmpty() {
		return   Utils.isStringEmpty(arhia) &&
	    	Utils.isStringEmpty(preis) &&
	    	Utils.isStringEmpty(ahwae) &&
	    	Utils.isStringEmpty(kzia) &&
	    	Utils.isStringEmpty(kzku) &&
	    	Utils.isStringEmpty(kurs) &&
	    	Utils.isStringEmpty(kudat) &&
	    	Utils.isStringEmpty(arhipr);
    }
}

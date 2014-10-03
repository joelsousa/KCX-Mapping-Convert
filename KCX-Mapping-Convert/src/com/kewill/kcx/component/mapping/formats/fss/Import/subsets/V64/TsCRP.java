package com.kewill.kcx.component.mapping.formats.fss.Import.subsets.V64;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Modul                :       TsCRP

 * Erstellt             :       14.09.2011
 * Beschreibung 		:       Positions-Teilsatz zur Nachricht CURREL
 				 
 *
 * @author                      Alfred Krzoska
 *
 */

public class TsCRP extends Teilsatz {

    private String posnr		 = "";	 // Positionsnummer
    private String andat		 = "";	 // Datum der Annahme
    private String uebdat		 = "";	 // Datum der Überlassung
    private String uebzei		 = "";	 // Uhrzeit der Überlassung
    private String kzann		 = "";	 // Kennzeichen Annahme
    private String kzueb		 = "";	 // Kennzeichen Überlassung
    private String kzrgb		 = "";	 // Kennzeichen Rückgabe
    private String kzaor		 = "";	 // Kennzeichen Anordnung

    public TsCRP() {
	    tsTyp = "CRP";
    }

    public void setFields(String[] fields) {
    	int size = fields.length;
    	String ausgabe = "FSS: " + fields[0] + " size = " + size;

    	if (size < 1) { return; }
    	tsTyp = fields[0];

	    if (size < 2) { return; }
	    posnr = fields[1];

	    if (size < 3) { return; }
	    andat = fields[2];

	    if (size < 4) { return; }
	    uebdat = fields[3];

	    if (size < 5) { return; }
	    uebzei = fields[4];

	    if (size < 6) { return; }
	    kzann = fields[5];

	    if (size < 7) { return; }
	    kzueb = fields[6];

	    if (size < 8) { return; }
	    kzrgb = fields[7];

	    if (size < 9) { return; }
	    kzaor = fields[8];
    }



    public String getPosnr() {
    	 return posnr;
    }


    public void setPosnr(String posnr) {
    	this.posnr = Utils.checkNull(posnr);
    }


    public String getAndat() {
    	 return andat;
    }


    public void setAndat(String andat) {
    	this.andat = Utils.checkNull(andat);
    }


    public String getUebdat() {
    	 return uebdat;
    }


    public void setUebdat(String uebdat) {
    	this.uebdat = Utils.checkNull(uebdat);
    }


    public String getUebzei() {
    	 return uebzei;
    }


    public void setUebzei(String uebzei) {
    	this.uebzei = Utils.checkNull(uebzei);
    }


    public String getKzann() {
    	 return kzann;
    }


    public void setKzann(String kzann) {
    	this.kzann = Utils.checkNull(kzann);
    }


    public String getKzueb() {
    	 return kzueb;
    }


    public void setKzueb(String kzueb) {
    	this.kzueb = Utils.checkNull(kzueb);
    }


    public String getKzrgb() {
    	 return kzrgb;
    }


    public void setKzrgb(String kzrgb) {
    	this.kzrgb = Utils.checkNull(kzrgb);
    }


    public String getKzaor() {
    	 return kzaor;
    }


    public void setKzaor(String kzaor) {
    	this.kzaor = Utils.checkNull(kzaor);
    }


    public String teilsatzBilden() {
    	StringBuffer buff = new StringBuffer();

    	buff.append(tsTyp);
    	buff.append(trenner);
    	buff.append(posnr);
    	buff.append(trenner);
    	buff.append(andat);
    	buff.append(trenner);
    	buff.append(uebdat);
    	buff.append(trenner);
    	buff.append(uebzei);
    	buff.append(trenner);
    	buff.append(kzann);
    	buff.append(trenner);
    	buff.append(kzueb);
    	buff.append(trenner);
    	buff.append(kzrgb);
    	buff.append(trenner);
    	buff.append(kzaor);
    	buff.append(trenner);

    	return new String(buff);
    
    }


    public boolean isEmpty() {
	  return  Utils.isStringEmpty(andat) &&
    	Utils.isStringEmpty(uebdat) &&
    	Utils.isStringEmpty(uebzei) &&
    	Utils.isStringEmpty(kzann) &&
    	Utils.isStringEmpty(kzueb) &&
    	Utils.isStringEmpty(kzrgb) &&
    	Utils.isStringEmpty(kzaor);
    }

}

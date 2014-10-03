package com.kewill.kcx.component.mapping.formats.fss.Import.subsets.V64;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Modul                :       TsAHI

 * Erstellt             :       13.09.2011
 * Beschreibung 		:       Es können maximal 10 Angaben zu Abzüge/Hinzurechnungen übermittelt werden
 				
 				 
 *
 * @author                      Alfred Krzoska
 *
 */

public class TsAHICTX extends Teilsatz {

    private String regkz		 = "";	 // Registrierkennzeichen
    private String lfdnr		 = "";	 // laufende Nummer des Abgabenbescheids
    private String posnr		 = "";	 // Positionsnummer
    private String artah		 = "";	 // Art Abzug/ Hinzurechnung	vgl. Deutsche Codeliste
    private String kursah		 = "";	 // Angewandter Kurs zu Abzug/Hinzurechnung

    public TsAHICTX() {
	    tsTyp = "AHI";
    }

    public void setFields(String[] fields) {
    	int size = fields.length;
    	String ausgabe = "FSS: " + fields[0] + " size = " + size;

    	if (size < 1) { return; }
    	tsTyp = fields[0];

	    if (size < 2) { return; }
	    regkz = fields[1];

	    if (size < 3) { return; }
	    lfdnr = fields[2];

	    if (size < 4) { return; }
	    posnr = fields[3];

	    if (size < 5) { return; }
	    artah = fields[4];

	    if (size < 6) { return; }
	    kursah = fields[5];
    }



    public String getRegkz() {
    	 return regkz;
    }


    public void setRegkz(String regkz) {
    	this.regkz = Utils.checkNull(regkz);
    }


    public String getLfdnr() {
    	 return lfdnr;
    }


    public void setLfdnr(String lfdnr) {
    	this.lfdnr = Utils.checkNull(lfdnr);
    }


    public String getPosnr() {
    	 return posnr;
    }


    public void setPosnr(String posnr) {
    	this.posnr = Utils.checkNull(posnr);
    }


    public String getArtah() {
    	 return artah;
    }


    public void setArtah(String artah) {
    	this.artah = Utils.checkNull(artah);
    }


    public String getKursah() {
    	 return kursah;
    }


    public void setKursah(String kursah) {
    	this.kursah = Utils.checkNull(kursah);
    }


    public String teilsatzBilden() {
    	StringBuffer buff = new StringBuffer();

    	buff.append(tsTyp);
    	buff.append(trenner);
    	buff.append(regkz);
    	buff.append(trenner);
    	buff.append(lfdnr);
    	buff.append(trenner);
    	buff.append(posnr);
    	buff.append(trenner);
    	buff.append(artah);
    	buff.append(trenner);
    	buff.append(kursah);
    	buff.append(trenner);

    	return new String(buff);
    
    }


    public boolean isEmpty() {
	  return  Utils.isStringEmpty(artah) &&
    	Utils.isStringEmpty(kursah);
    }

}

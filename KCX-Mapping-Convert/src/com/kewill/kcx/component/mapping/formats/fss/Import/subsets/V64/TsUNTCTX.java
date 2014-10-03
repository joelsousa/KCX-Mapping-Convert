package com.kewill.kcx.component.mapping.formats.fss.Import.subsets.V64; 

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Modul                :       TsUNTCTX
 * Erstellt             :       14.09.2011
 * Beschreibung 		:       Unterlagensatz.
								Es können maximal 99 Angaben zu den Unterlagen zur Position übermittelt werden.
 *
 * @author                      Alfred Krzoska
 *
 */

public class TsUNTCTX extends Teilsatz {

    private String regkz		 = "";	 // Registrierkennzeichen
    private String lfdnr		 = "";	 // laufende Nummer des Abgabenbescheids
    private String posnr		 = "";	 // Positionsnummer
    private String untar		 = "";	 // Art der Unterlage
    private String untber		 = "";	 // Bereich der Unterlage 	
						 				 // 1=Zeugnis/Bescheinigung 2=Einfuhrpapiere 3=Präferenznachweise
						 				 // 4=Unterlagen 5=Befreiungstatbestände/Erklärungen=Nachweis 
    									 // Gemeinschaftscharakter /     Freiverkehrseigenschaft
    private String untnr		 = "";	 // Nummer der Unterlage
    private String kzane		 = "";	 // KZ Anerkennung
    private String kzabs		 = "";	 // KZ Abschreibung
    private String kzein		 = "";	 // KZ einbehalten

    public TsUNTCTX() {
	    tsTyp = "UNT";
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
		untar = fields[4];
	
		if (size < 6) { return; }
		untber = fields[5];
	
		if (size < 7) { return; }
		untnr = fields[6];
	
		if (size < 8) { return; }
		kzane = fields[7];
	
		if (size < 9) { return; }
		kzabs = fields[8];
	
		if (size < 10) { return; }
		kzein = fields[9];
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


    public String getUntar() {
    	 return untar;
    }


    public void setUntar(String untar) {
    	this.untar = Utils.checkNull(untar);
    }


    public String getUntber() {
    	 return untber;
    }


    public void setUntber(String untber) {
    	this.untber = Utils.checkNull(untber);
    }


    public String getUntnr() {
    	 return untnr;
    }


    public void setUntnr(String untnr) {
    	this.untnr = Utils.checkNull(untnr);
    }


    public String getKzane() {
    	 return kzane;
    }


    public void setKzane(String kzane) {
    	this.kzane = Utils.checkNull(kzane);
    }


    public String getKzabs() {
    	 return kzabs;
    }


    public void setKzabs(String kzabs) {
    	this.kzabs = Utils.checkNull(kzabs);
    }


    public String getKzein() {
    	 return kzein;
    }


    public void setKzein(String kzein) {
    	this.kzein = Utils.checkNull(kzein);
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
    	buff.append(untar);
    	buff.append(trenner);
    	buff.append(untber);
    	buff.append(trenner);
    	buff.append(untnr);
    	buff.append(trenner);
    	buff.append(kzane);
    	buff.append(trenner);
    	buff.append(kzabs);
    	buff.append(trenner);
    	buff.append(kzein);
    	buff.append(trenner);

    	return new String(buff);
    
    }



    public boolean isEmpty() {
	 return   Utils.isStringEmpty(untar) &&
		  Utils.isStringEmpty(untber) &&
		  Utils.isStringEmpty(untnr) &&
		  Utils.isStringEmpty(kzane) &&
		  Utils.isStringEmpty(kzabs) &&
		  Utils.isStringEmpty(kzein);
    }
}

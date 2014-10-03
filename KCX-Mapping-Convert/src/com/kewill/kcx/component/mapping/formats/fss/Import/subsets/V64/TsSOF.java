package com.kewill.kcx.component.mapping.formats.fss.Import.subsets.V64;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Modul                :       TsSOF

 * Erstellt             :       13.09.2011
 * Beschreibung 		:       Teilsatz mit Angaben zu den Sonderfalleingaben 

 				 
 *
 * @author                      Alfred Krzoska
 *
 */

public class TsSOF extends Teilsatz {

    private String regkz		 = "";	 // Registrierkennzeichen
    private String lfdnr		 = "";	 // laufende Nummer des Abgabenbescheids
    private String posnr		 = "";	 // Positionsnummer
    private String sogru		 = "";	 // Sonderabgabengruppe (Sonderfall-eingabe) 	vgl. Deutsche Codeliste
    private String soanw		 = "";	 // Anwendungsart zur Sonderabgaben-gruppe
    private String sosbf		 = "";	 // Satz

    public TsSOF() {
	    tsTyp = "SOF";
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
	    sogru = fields[4];

	    if (size < 6) { return; }
	    soanw = fields[5];

	    if (size < 7) { return; }
	    sosbf = fields[6];
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


    public String getSogru() {
    	 return sogru;
    }


    public void setSogru(String sogru) {
    	this.sogru = Utils.checkNull(sogru);
    }


    public String getSoanw() {
    	 return soanw;
    }


    public void setSoanw(String soanw) {
    	this.soanw = Utils.checkNull(soanw);
    }


    public String getSosbf() {
    	 return sosbf;
    }


    public void setSosbf(String sosbf) {
    	this.sosbf = Utils.checkNull(sosbf);
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
    	buff.append(sogru);
    	buff.append(trenner);
    	buff.append(soanw);
    	buff.append(trenner);
    	buff.append(sosbf);
    	buff.append(trenner);

    	return new String(buff);
    
    }


    public boolean isEmpty() {
	  return   Utils.isStringEmpty(sogru) &&
    	Utils.isStringEmpty(soanw) &&
    	Utils.isStringEmpty(sosbf);
    }

}

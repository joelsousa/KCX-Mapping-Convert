package com.kewill.kcx.component.mapping.formats.fss.Import.subsets.V64; 

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Modul                :       TsAAB
 * Erstellt             :       14.09.2011
 * Beschreibung 		:       Abgabenteilsatz AAB kann bis zu 60 mal pro Position vorkommen
								alle Angaben sind auf je eine Art der Abgabe bezogen.
 *
 * @author                      Alfred Krzoska
 *
 */

public class TsAAB extends Teilsatz {

    private String regkz		 = "";	 // Registrierkennzeichen
    private String lfdnr		 = "";	 // laufende Nummer des Abgabenbescheids
    private String posnr		 = "";	 // Positionsnummer
    private String abus			 = "";	 // Art der Abgabe	vgl. Deutsche Codeliste
    private String bema			 = "";	 // Bemessungsmaﬂstab zur Abgabenfestsetzung 	vgl. Deutsche Codeliste
    private String kzbgr		 = "";	 // Kennz. Bemessungsgrundlage 	1=Zollwert  2=Zollmenge 
						 // 3=Agrarzollmenge 5=Verbrauchssteuermenge 6=Verbrauchssteuerwert
						 // 7=EUSt-Wert 8=Besondere Wertangabe
    private String ghar			 = "";	 // Art Gehaltsangabe zur Abgabenfestsetzung
    private String ghgp		 	 = "";	 // Gehaltsangabe in Grad/ Prozent zur Abgabenfestsetzung
    private String abgsa		 = "";	 // Abgabensatz

    public TsAAB() {
	    tsTyp = "AAB";
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
	    abus = fields[4];

	    if (size < 6) { return; }
	    bema = fields[5];

	    if (size < 7) { return; }
	    kzbgr = fields[6];

	    if (size < 8) { return; }
	    ghar = fields[7];

	    if (size < 9) { return; }
	    ghgp = fields[8];

	    if (size < 10) { return; }
	    abgsa = fields[9];
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


    public String getAbus() {
    	 return abus;
    }


    public void setAbus(String abus) {
    	this.abus = Utils.checkNull(abus);
    }


    public String getBema() {
    	 return bema;
    }


    public void setBema(String bema) {
    	this.bema = Utils.checkNull(bema);
    }


    public String getKzbgr() {
    	 return kzbgr;
    }


    public void setKzbgr(String kzbgr) {
    	this.kzbgr = Utils.checkNull(kzbgr);
    }


    public String getGhar() {
    	 return ghar;
    }


    public void setGhar(String ghar) {
    	this.ghar = Utils.checkNull(ghar);
    }


    public String getGhgp() {
    	 return ghgp;
    }


    public void setGhgp(String ghgp) {
    	this.ghgp = Utils.checkNull(ghgp);
    }


    public String getAbgsa() {
    	 return abgsa;
    }


    public void setAbgsa(String abgsa) {
    	this.abgsa = Utils.checkNull(abgsa);
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
    	buff.append(abus);
    	buff.append(trenner);
    	buff.append(bema);
    	buff.append(trenner);
    	buff.append(kzbgr);
    	buff.append(trenner);
    	buff.append(ghar);
    	buff.append(trenner);
    	buff.append(ghgp);
    	buff.append(trenner);
    	buff.append(abgsa);
    	buff.append(trenner);

    	return new String(buff);
    
    }



    public boolean isEmpty() {
	return  Utils.isStringEmpty(abus) &&
    	Utils.isStringEmpty(bema) &&
    	Utils.isStringEmpty(kzbgr) &&
    	Utils.isStringEmpty(ghar) &&
    	Utils.isStringEmpty(ghgp) &&
    	Utils.isStringEmpty(abgsa);
    }
}


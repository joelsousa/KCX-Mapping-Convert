package com.kewill.kcx.component.mapping.formats.fss.Import.subsets.V64;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module      :  TsASK.
 * Created     :  13.09.2011
 * Description :  Teilsatz Aufschubkonten
 				
 *
 * @author    Alfred Krzoska
 * @version   7.0.00
 */

public class TsASK extends Teilsatz {

    private String regkz		 = "";	 // Registrierkennzeichen
    private String lfdnr		 = "";	 // laufende Nummer des Abgabenbescheids
    private String abus		 	 = "";	 // Art der Abgabe	vgl. Deutsche Codeliste
    private String atnkz		 = "";	 // Kennzeichen  dass der Aufschubkontoinhaber der Teilnehmer selbst ist
    									 // J=Kontoinhaber ist der TeilnehmerN=Kontoinhaber ist nicht der Teilnehmer
    private String anam		 	 = "";	 // Name des Aufschubnehmers
    private String azbnr		 = "";	 // Zollnummer des Aufschubkontoinhabers
    private String akto		 	 = "";	 // Aufschubkontonr
    private String aaart		 = "";	 // Art Aufschubantrag
    private String faedat		 = "";	 // Fälligkeitsdatum
    private String aofd		 	 = "";	 // OFD zum Aufschubkonto
    private String zaart		 = "";	 // Zahlungsart
    private String abgb		 	 = "";	 // Abgabenbetrag

    public TsASK() {
	    tsTyp = "ASK";
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
	    abus = fields[3];

	    if (size < 5) { return; }
	    atnkz = fields[4];

	    if (size < 6) { return; }
	    anam = fields[5];

	    if (size < 7) { return; }
	    azbnr = fields[6];

	    if (size < 8) { return; }
	    akto = fields[7];

	    if (size < 9) { return; }
	    aaart = fields[8];

	    if (size < 10) { return; }
	    faedat = fields[9];

	    if (size < 11) { return; }
	    aofd = fields[10];

	    if (size < 12) { return; }
	    zaart = fields[11];

	    if (size < 13) { return; }
	    abgb = fields[12];
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


    public String getAbus() {
    	 return abus;
    }


    public void setAbus(String abus) {
    	this.abus = Utils.checkNull(abus);
    }


    public String getAtnkz() {
    	 return atnkz;
    }


    public void setAtnkz(String atnkz) {
    	this.atnkz = Utils.checkNull(atnkz);
    }


    public String getAnam() {
    	 return anam;
    }


    public void setAnam(String anam) {
    	this.anam = Utils.checkNull(anam);
    }


    public String getAzbnr() {
    	 return azbnr;
    }


    public void setAzbnr(String azbnr) {
    	this.azbnr = Utils.checkNull(azbnr);
    }


    public String getAkto() {
    	 return akto;
    }


    public void setAkto(String akto) {
    	this.akto = Utils.checkNull(akto);
    }


    public String getAaart() {
    	 return aaart;
    }


    public void setAaart(String aaart) {
    	this.aaart = Utils.checkNull(aaart);
    }


    public String getFaedat() {
    	 return faedat;
    }


    public void setFaedat(String faedat) {
    	this.faedat = Utils.checkNull(faedat);
    }


    public String getAofd() {
    	 return aofd;
    }


    public void setAofd(String aofd) {
    	this.aofd = Utils.checkNull(aofd);
    }


    public String getZaart() {
    	 return zaart;
    }


    public void setZaart(String zaart) {
    	this.zaart = Utils.checkNull(zaart);
    }


    public String getAbgb() {
    	 return abgb;
    }


    public void setAbgb(String abgb) {
    	this.abgb = Utils.checkNull(abgb);
    }


    public String teilsatzBilden() {
    	StringBuffer buff = new StringBuffer();

    	buff.append(tsTyp);
    	buff.append(trenner);
    	buff.append(regkz);
    	buff.append(trenner);
    	buff.append(lfdnr);
    	buff.append(trenner);
    	buff.append(abus);
    	buff.append(trenner);
    	buff.append(atnkz);
    	buff.append(trenner);
    	buff.append(anam);
    	buff.append(trenner);
    	buff.append(azbnr);
    	buff.append(trenner);
    	buff.append(akto);
    	buff.append(trenner);
    	buff.append(aaart);
    	buff.append(trenner);
    	buff.append(faedat);
    	buff.append(trenner);
    	buff.append(aofd);
    	buff.append(trenner);
    	buff.append(zaart);
    	buff.append(trenner);
    	buff.append(abgb);
    	buff.append(trenner);

    	return new String(buff);
    
    }

    public boolean isEmpty() {
	   return  Utils.isStringEmpty(regkz) &&
    	Utils.isStringEmpty(lfdnr) &&
    	Utils.isStringEmpty(abus) &&
    	Utils.isStringEmpty(atnkz) &&
    	Utils.isStringEmpty(anam) &&
    	Utils.isStringEmpty(azbnr) &&
    	Utils.isStringEmpty(akto) &&
    	Utils.isStringEmpty(aaart) &&
    	Utils.isStringEmpty(faedat) &&
    	Utils.isStringEmpty(aofd) &&
    	Utils.isStringEmpty(zaart) &&
    	Utils.isStringEmpty(abgb);
    }
}

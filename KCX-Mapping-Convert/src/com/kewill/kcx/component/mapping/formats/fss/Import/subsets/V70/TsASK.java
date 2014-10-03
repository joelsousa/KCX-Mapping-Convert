package com.kewill.kcx.component.mapping.formats.fss.Import.subsets.V70;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module      :  Import 70.
 * Created     :  12.11.2012
 * Description :  Aufschubkonten
 				
 *
 * @author    iwaniuk
 * @version   7.0.00
 */

public class TsASK extends Teilsatz {

    private String regkz		 = "";	 // Registrierkennzeichen
    private String lfdnr		 = "";	 // laufende Nummer des Abgabenbescheids
    private String abus		 	 = "";	 // Art der Abgabe	vgl. Deutsche Codeliste
    private String atnkz		 = "";	 // Kennzeichen  dass der Aufschubkontoinhaber der Teilnehmer selbst ist
    									 // J=Kontoinhaber ist der TeilnehmerN=Kontoinhaber ist nicht der Teilnehmer
    private String anam		 	 = "";	 // Name des Aufschubnehmers
    //V70: private String azbnr		 = "";	 // Zollnummer des Aufschubkontoinhabers    
    private String akto		 	 = "";	 // Aufschubkontonr
    private String aaart		 = "";	 // Art Aufschubantrag
    private String faedat		 = "";	 // Fälligkeitsdatum
    private String aofd		 	 = "";	 // OFD zum Aufschubkonto
    private String zaart		 = "";	 // Zahlungsart
    private String abgb		 	 = "";	 // Abgabenbetrag
    private String aeori		 	 = "";	 //new V70 Aufschubkontoinhaber EORI
    private String anlnr		 	 = "";	 //new V70 Aufschubkontoinhaber Niederlassungsnummer

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
	    akto = fields[6];
	    if (size < 8) { return; }
	    aaart = fields[7];
	    if (size < 9) { return; }
	    faedat = fields[8];
	    if (size < 10) { return; }
	    aofd = fields[9];
	    if (size < 11) { return; }
	    zaart = fields[10];
	    if (size < 12) { return; }
	    abgb = fields[11];
	    if (size < 13) { return; }
	    aeori = fields[12];
	    if (size < 14) { return; }
	    anlnr = fields[13];
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

    public String getAeori() {
    	return aeori;
    }

   public void setAeori(String value) {
   	  	this.aeori = Utils.checkNull(value);
   }

   public String getAnlnr() {
  	 	return anlnr;
   }

   public void setAnlnr(String value) {
	   this.anlnr = Utils.checkNull(value);
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
    	buff.append(aeori);
    	buff.append(trenner);
    	buff.append(anlnr);
    	buff.append(trenner);

    	return new String(buff);
    
    }

    public boolean isEmpty() {
	   return  Utils.isStringEmpty(regkz) &&
    	Utils.isStringEmpty(lfdnr) &&
    	Utils.isStringEmpty(abus) &&
    	Utils.isStringEmpty(atnkz) &&
    	Utils.isStringEmpty(anam) &&
    	Utils.isStringEmpty(aeori) &&
    	Utils.isStringEmpty(akto) &&
    	Utils.isStringEmpty(aaart) &&
    	Utils.isStringEmpty(faedat) &&
    	Utils.isStringEmpty(aofd) &&
    	Utils.isStringEmpty(zaart) &&
    	Utils.isStringEmpty(abgb);
    }
}

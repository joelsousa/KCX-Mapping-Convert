package com.kewill.kcx.component.mapping.formats.fss.Import.subsets.V64;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module       :    TsADRCTX.
 * Created      :    13.09.2011
 * Description	:    Adressteilsatzes des Abgabenbescheids
 * 
 * 1=Anmelder
 * 2=Vertreter
 * 4=Empfänger
 * 10=Dienststelle
 * 11=Zollzahlstelle
 * 14=Rechtsbehelf-HZA
 * 18=Für Rechnung

 * @author                      Alfred Krzoska
 * @version 7.0.00
 */

public class TsADRCTX extends Teilsatz {

    private String regkz		 = "";	 // Registrierkennzeichen
    private String lfdnr		 = "";	 // laufende Nummer des Abgabenbescheids
    private String kztyp		 = "";	 // Adresstyp	1=Anmelder 2=Vertreter 4=Empfänger 10=Dienststelle
    									 // 11=Zollzahlstelle 14=Rechtsbehelf-HZA 18=Für Rechnung
    private String anam1		 = "";	 // Namensteil1
    private String anam2		 = "";	 // Namensteil2
    private String anam3		 = "";	 // Namensteil3
    private String astr		 	 = "";	 // Straße
    private String apostf		 = "";	 // Postfach
    private String alnd		 	 = "";	 // Nationalität
    private String aplz		 	 = "";	 // Postleitzahl
    private String aort		 	 = "";	 // Ort
    private String aoteil		 = "";	 // Ortsteil

    public TsADRCTX() {
	    tsTyp = "ADR";
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
	    kztyp = fields[3];

	    if (size < 5) { return; }
	    anam1 = fields[4];

	    if (size < 6) { return; }
	    anam2 = fields[5];

	    if (size < 7) { return; }
	    anam3 = fields[6];

	    if (size < 8) { return; }
	    astr = fields[7];

	    if (size < 9) { return; }
	    apostf = fields[8];

	    if (size < 10) { return; }
	    alnd = fields[9];

	    if (size < 11) { return; }
	    aplz = fields[10];

	    if (size < 12) { return; }
	    aort = fields[11];

	    if (size < 13) { return; }
	    aoteil = fields[12];
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


    public String getKztyp() {
    	 return kztyp;
    }


    public void setKztyp(String kztyp) {
    	this.kztyp = Utils.checkNull(kztyp);
    }


    public String getAnam1() {
    	 return anam1;
    }


    public void setAnam1(String anam1) {
    	this.anam1 = Utils.checkNull(anam1);
    }


    public String getAnam2() {
    	 return anam2;
    }


    public void setAnam2(String anam2) {
    	this.anam2 = Utils.checkNull(anam2);
    }


    public String getAnam3() {
    	 return anam3;
    }


    public void setAnam3(String anam3) {
    	this.anam3 = Utils.checkNull(anam3);
    }


    public String getAstr() {
    	 return astr;
    }


    public void setAstr(String astr) {
    	this.astr = Utils.checkNull(astr);
    }


    public String getApostf() {
    	 return apostf;
    }


    public void setApostf(String apostf) {
    	this.apostf = Utils.checkNull(apostf);
    }


    public String getAlnd() {
    	 return alnd;
    }


    public void setAlnd(String alnd) {
    	this.alnd = Utils.checkNull(alnd);
    }


    public String getAplz() {
    	 return aplz;
    }


    public void setAplz(String aplz) {
    	this.aplz = Utils.checkNull(aplz);
    }


    public String getAort() {
    	 return aort;
    }


    public void setAort(String aort) {
    	this.aort = Utils.checkNull(aort);
    }


    public String getAoteil() {
    	 return aoteil;
    }


    public void setAoteil(String aoteil) {
    	this.aoteil = Utils.checkNull(aoteil);
    }


    public String teilsatzBilden() {
    	StringBuffer buff = new StringBuffer();

    	buff.append(tsTyp);
    	buff.append(trenner);
    	buff.append(regkz);
    	buff.append(trenner);
    	buff.append(lfdnr);
    	buff.append(trenner);
    	buff.append(kztyp);
    	buff.append(trenner);
    	buff.append(anam1);
    	buff.append(trenner);
    	buff.append(anam2);
    	buff.append(trenner);
    	buff.append(anam3);
    	buff.append(trenner);
    	buff.append(astr);
    	buff.append(trenner);
    	buff.append(apostf);
    	buff.append(trenner);
    	buff.append(alnd);
    	buff.append(trenner);
    	buff.append(aplz);
    	buff.append(trenner);
    	buff.append(aort);
    	buff.append(trenner);
    	buff.append(aoteil);
    	buff.append(trenner);

    	return new String(buff);

    }



    public boolean isEmpty() {
	  return  Utils.isStringEmpty(regkz) &&
    	Utils.isStringEmpty(anam1) &&
    	Utils.isStringEmpty(anam2) &&
    	Utils.isStringEmpty(anam3) &&
    	Utils.isStringEmpty(astr) &&
    	Utils.isStringEmpty(apostf) &&
    	Utils.isStringEmpty(alnd) &&
    	Utils.isStringEmpty(aplz) &&
    	Utils.isStringEmpty(aort) &&
    	Utils.isStringEmpty(aoteil);
    }
}

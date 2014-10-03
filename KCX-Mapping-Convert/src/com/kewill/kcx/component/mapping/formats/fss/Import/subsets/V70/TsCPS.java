package com.kewill.kcx.component.mapping.formats.fss.Import.subsets.V70;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/** 
 * Module        : Import 70
 * Created       : 12.11.2012
 * Description 	 : Positionssatz des Abgabenbescheids.
				   Es können maximal 99 Angaben zu den Unterlagen zur Position übermittelt werden.
 *
 * @author iwaniuk
 * @version 7.0.00
 */

public class TsCPS extends Teilsatz {

    private String regkz		 = "";	 // Registrierkennzeichen
    private String lfdnr		 = "";	 // laufende Nummer des Abgabenbescheids
    private String posnr		 = "";	 // Positionsnummer
    //V70: private String mittei		 = "";	 // Mitteilung an den Teilnehmer (Beschau)
    private String nachw		 = "";	 // Weiterer Nachweis
    private String nawfri		 = "";	 // Frist weiterer Nachweis
    private String miterl		 = "";	 // Mitteilung an den Teilnehmer (Erledigung)
    private String entdat		 = "";	 // Datum der Annahme
    private String uebdat		 = "";	 // Datum der Überlassung
    private String kzbeg		 = "";	 // Gewährte Begünstigung
    private String kzaord		 = "";	 // Kennzeichen Anordnungen	01=Volle Gesamtbeschau02=stichprobenweise Mengenbeschau03=stichprobenweise Beschaffenheits-beschau09=Andere
    private String kzerl		 = "";	 // Kennzeichen Erledigung	1=Ungültigkeitserklärung von Amts wegen2=Ungültigkeitserklärung auf Antrag3=Unwirksamkeitserklärung4=Nicht abschließende Festsetzung der Einfuhrabgaben5=abschließende Festsetzung der Einfuhrabgaben6=abschließende Festsetzung der Einfuhrabgaben7=Zur Abrechnungsstelle übersandt
    private String kzann		 = "";	 // Kennzeichen Annahme	J=Position angenommenN=Position nicht angenommen
    private String kzsof		 = "";	 // Kennz. Sonderfalleingabe abweichend festgesetzt	J=Sonderfalleingabe wurde abweichend festgesetztN=Sonderfalleingabe wurde nicht abweichend festgesetzt
    private String kzdv1		 = "";	 // Kennzeichen D.V.1 abweichend festgesetzt	J=Angaben zur D.V.1 wurden abweichend festgesetztN=Angaben zur D.V.1 wurden nicht abweichend festgesetzt
    private String eustwe		 = "";	 // EUSt-Wert
    private String silbet		 = "";	 // Betrag der Sicherheitsleistung
    private String kunet		 = "";	 // Angewandter Kurs zum Nettopreis
    private String kumiz		 = "";	 // Angewandter Kurs zu mittelbare Zahlungen
    private String zollw		 = "";	 // Zollwert
    private String prluft		 = "";	 // Prozentsatz Luftfrachtkosten
    private String kuluft		 = "";	 // Angewandter Kurs Luftfrachtkosten

    public TsCPS() {
	    tsTyp = "CPS";
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
	    nachw = fields[4];
	    if (size < 6) { return; }
	    nawfri = fields[5];
	    if (size < 7) { return; }
	    miterl = fields[6];
	    if (size < 8) { return; }
	    entdat = fields[7];
	    if (size < 9) { return; }
	    uebdat = fields[8];
	    if (size < 10) { return; }
	    kzbeg = fields[9];
	    if (size < 11) { return; }
	    kzaord = fields[10];
	    if (size < 12) { return; }
	    kzerl = fields[11];
	    if (size < 13) { return; }
	    kzann = fields[12];
	    if (size < 14) { return; }
	    kzsof = fields[13];
	    if (size < 15) { return; }
	    kzdv1 = fields[14];
	    if (size < 16) { return; }
	    eustwe = fields[15];
	    if (size < 17) { return; }
	    silbet = fields[16];
	    if (size < 18) { return; }
	    kunet = fields[17];
	    if (size < 19) { return; }
	    kumiz = fields[18];
	    if (size < 20) { return; }
	    zollw = fields[19];
	    if (size < 21) { return; }
	    prluft = fields[20];
	    if (size < 22) { return; }
	    kuluft = fields[21];
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

    public String getNachw() {
    	 return nachw;
    }

    public void setNachw(String nachw) {
    	this.nachw = Utils.checkNull(nachw);
    }

    public String getNawfri() {
    	 return nawfri;
    }

    public void setNawfri(String nawfri) {
    	this.nawfri = Utils.checkNull(nawfri);
    }

    public String getMiterl() {
    	 return miterl;
    }

    public void setMiterl(String miterl) {
    	this.miterl = Utils.checkNull(miterl);
    }

    public String getEntdat() {
    	 return entdat;
    }

    public void setEntdat(String entdat) {
    	this.entdat = Utils.checkNull(entdat);
    }

    public String getUebdat() {
    	 return uebdat;
    }

    public void setUebdat(String uebdat) {
    	this.uebdat = Utils.checkNull(uebdat);
    }

    public String getKzbeg() {
    	 return kzbeg;
    }

    public void setKzbeg(String kzbeg) {
    	this.kzbeg = Utils.checkNull(kzbeg);
    }

    public String getKzaord() {
    	 return kzaord;
    }

    public void setKzaord(String kzaord) {
    	this.kzaord = Utils.checkNull(kzaord);
    }

    public String getKzerl() {
    	 return kzerl;
    }

    public void setKzerl(String kzerl) {
    	this.kzerl = Utils.checkNull(kzerl);
    }

    public String getKzann() {
    	 return kzann;
    }

    public void setKzann(String kzann) {
    	this.kzann = Utils.checkNull(kzann);
    }

    public String getKzsof() {
    	 return kzsof;
    }

    public void setKzsof(String kzsof) {
    	this.kzsof = Utils.checkNull(kzsof);
    }

    public String getKzdv1() {
    	 return kzdv1;
    }

    public void setKzdv1(String kzdv1) {
    	this.kzdv1 = Utils.checkNull(kzdv1);
    }

    public String getEustwe() {
    	 return eustwe;
    }

    public void setEustwe(String eustwe) {
    	this.eustwe = Utils.checkNull(eustwe);
    }

    public String getSilbet() {
    	 return silbet;
    }

    public void setSilbet(String silbet) {
    	this.silbet = Utils.checkNull(silbet);
    }

    public String getKunet() {
    	 return kunet;
    }

    public void setKunet(String kunet) {
    	this.kunet = Utils.checkNull(kunet);
    }

    public String getKumiz() {
    	 return kumiz;
    }

    public void setKumiz(String kumiz) {
    	this.kumiz = Utils.checkNull(kumiz);
    }

    public String getZollw() {
    	 return zollw;
    }

    public void setZollw(String zollw) {
    	this.zollw = Utils.checkNull(zollw);
    }

    public String getPrluft() {
    	 return prluft;
    }

    public void setPrluft(String prluft) {
    	this.prluft = Utils.checkNull(prluft);
    }

    public String getKuluft() {
    	 return kuluft;
    }

    public void setKuluft(String kuluft) {
    	this.kuluft = Utils.checkNull(kuluft);
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
    	buff.append(nachw);
    	buff.append(trenner);
    	buff.append(nawfri);
    	buff.append(trenner);
    	buff.append(miterl);
    	buff.append(trenner);
    	buff.append(entdat);
    	buff.append(trenner);
    	buff.append(uebdat);
    	buff.append(trenner);
    	buff.append(kzbeg);
    	buff.append(trenner);
    	buff.append(kzaord);
    	buff.append(trenner);
    	buff.append(kzerl);
    	buff.append(trenner);
    	buff.append(kzann);
    	buff.append(trenner);
    	buff.append(kzsof);
    	buff.append(trenner);
    	buff.append(kzdv1);
    	buff.append(trenner);
    	buff.append(eustwe);
    	buff.append(trenner);
    	buff.append(silbet);
    	buff.append(trenner);
    	buff.append(kunet);
    	buff.append(trenner);
    	buff.append(kumiz);
    	buff.append(trenner);
    	buff.append(zollw);
    	buff.append(trenner);
    	buff.append(prluft);
    	buff.append(trenner);
    	buff.append(kuluft);
    	buff.append(trenner);

    	return new String(buff);
    }

    public boolean isEmpty() {
	  return  Utils.isStringEmpty(nachw) &&    	
    	Utils.isStringEmpty(nawfri) &&
    	Utils.isStringEmpty(miterl) &&
    	Utils.isStringEmpty(entdat) &&
    	Utils.isStringEmpty(uebdat) &&
    	Utils.isStringEmpty(kzbeg) &&
    	Utils.isStringEmpty(kzaord) &&
    	Utils.isStringEmpty(kzerl) &&
    	Utils.isStringEmpty(kzann) &&
    	Utils.isStringEmpty(kzsof) &&
    	Utils.isStringEmpty(kzdv1) &&
    	Utils.isStringEmpty(eustwe) &&
    	Utils.isStringEmpty(silbet) &&
    	Utils.isStringEmpty(kunet) &&
    	Utils.isStringEmpty(kumiz) &&
    	Utils.isStringEmpty(zollw) &&
    	Utils.isStringEmpty(prluft) &&
    	Utils.isStringEmpty(kuluft);
    }

}

package com.kewill.kcx.component.mapping.formats.fss.Import.subsets.V70;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module       :   Import 70<br>
 * Subset       :   TsAN1.java<br>
 * Created      :   12.11.2012<br>
 * Description  :   1. Hauptsatz des Antragteils für EZA.<br>
 *
 * @author          iwaniuk
 * @version         7.0.00 
 */

public class TsAN1 extends Teilsatz {

    private String tsTyp    = "";       // Ts-Schlüssel
    private String beznr    = "";       // Bezugsnummer
    private String korrkz   = "";       // Korrekturkennzeichen  //0=neu, 1=korrektur, 2=egal
    private String antart   = "";       // Antragsart
    private String wkz		= "";       // Währungskennzeichen
    private String kzzvg	= "";       // KZ für Zollanmeldung vor Gestellung
    private String kzvtr    = "";       // KZ für Vertretungsverhältnis
    private String kzemp    = "";       // KZ für Empfänger = Anmelder
    private String kzvsta	= "";		// KZ für Vorsteuerabzug
    private String kzdv1	= "";		// KZ für DV1-Angaben
    private String kzcon	= "";		// KZ Container
    private String kzza		= "";		// Zahlungsart
    private String dst		= "";		// Abfertigungszollstelle
    private String anmkd	= "";		// Kundennummer des Anmelders
    //private String anmzb	= "";		// Zollnummer des Anmelders
    private String anmeori	= "";                                              //new V70
    private String anmnl	= "";                                              //new V70
    private String anmust	= "";		// UST-ID des Anmelders
    private String vertkd	= "";		// Kundennummer des Vertreters
    //private String verzb	= "";		// Zollnummer des Vertreters
    private String verteori	= "";                                              //new V70
    private String vertnl	= "";                                              //new V70
    private String verskd	= "";		// Kundennummer des Versenders
    //private String veszb	= "";		// Zollnnummer des Versenders
    private String verseori	= "";                                              //new V70
    private String versnl	= "";                                              //new V70
    private String erwkd	= "";		// Kundennummer des Erwerbers
    //private String erwzb	= "";		// Zollnummer des Erwerbers
    private String erweori	= "";                                              //new V70
    private String erwnl	= "";                                              //new V70
    private String erwust	= "";		// UST-ID des Erwerbers
    private String empfkd	= "";		// Kundennummer des Empfängers
    //private String empzb	= "";		// Zollnummer des Empfängers
    private String empfeori	= "";                                              //new V70
    private String empfnl	= "";                                              //new  V70
    private String kfkd	= "";		// Kundennummer des Käufers
    //private String kfzb		= "";		// Zollnummer des Käufers
    private String kfeori	= "";                                              //new  V70
    private String kfnl	= "";                                                  //new  V70
    private String verkkd	= "";		// Kundennummer des Verkäufers
    //private String vekzb	= "";		// Zollnummer des Verkäufers
    private String verkeori	= "";                                              //new  V70
    private String verknl	= "";                                              //new  V70
    private String zwakd	= "";		// Kundennummer des Zollwertanmelders
    //private String zwazb	= "";		// Zollnummer des Zollwertanmelders
    private String zwaeori	= "";                                              //new  V70
    private String zwanl	= "";                                              //new  V70
    private String vzwakd	= "";		// Kundennummer des Vertreters des ZWA
    //private String vwazb	= "";		// Zollnummer des Vertreters des ZWA
    private String vzwaeori	= "";                                              //new  V70
    private String vzwanl	= "";                                              //new  V70
    private String anzpos	= "";		// Anzahl der Positionen
    private String gesart	= "";		// Geschäftsart
    private String ausort	= "";		// Ausstellungsort
    private String vland	= "";		// Versendungsland
    private String rechkd	= "";		// Kundennummer "Für Rechnung"
    //private String rechzb	= "";		// Zollnummer "Für Rechnung"
    private String recheori	= "";                                              //new  V70
    private String rechnl	= "";                                              //new  V70
    private String quelkz	= "";		// Kennzeichen Quell-Verfahren

    
    public TsAN1() {
        tsTyp = "AN1";
    }

   public void setFields(String[] fields) {   
		int size = fields.length;
		//String ausgabe = "FSS: "+fields[0]+" size = "+ size;
		//Utils.log( ausgabe);
			
		
		if (size < 1) { return; }
        tsTyp = fields[0];
        
        if (size < 2) { return; }
        beznr = fields[1];
        
        if (size < 3) { return; } 
        korrkz = fields[2];

        if (size < 4) { return; } 
        antart = fields[3];

        if (size < 5) { return; } 
        wkz = fields[4];

        if (size < 6) { return; } 
        kzzvg = fields[5];
        
        if (size < 7) { return; } 
        kzvtr = fields[6]; 
        		
        if (size < 8) { return; }
        kzemp = fields[7]; 
        		
        if (size < 9) { return; } 
        kzvsta = fields[8];

        if (size < 10) { return; } 
        kzdv1 = fields[9];
        		
        if (size < 11) { return; } 
        kzcon = fields[10];
        
        if (size < 12) { return; }
        kzza = fields[11];

        if (size < 13) { return; }
        dst = fields[12];
        
        if (size < 14) { return; }
        anmkd = fields[13];        
        if (size < 15) { return; }
        anmeori = fields[14];
        if (size < 16) { return; }
        anmnl = fields[15];
        
        if (size < 17) { return; }
        anmust = fields[16];
        
        if (size < 18) { return; }
        vertkd = fields[17];        
        if (size < 19) { return; }
        verteori = fields[18];
        if (size < 20) { return; }
        vertnl = fields[19];
        
        if (size < 21) { return; }
        verskd = fields[20];
        if (size < 22) { return; }
        verseori = fields[21];
        if (size < 23) { return; }
        versnl = fields[22];

        if (size < 24) { return; }
        erwkd = fields[23];
        if (size < 25) { return; }
        erweori = fields[24];
        if (size < 26) { return; }
        erwnl = fields[25];
        
        if (size < 27) { return; }
        erwust = fields[26];
         
        if (size < 28) { return; }
        empfkd = fields[27];        
        if (size < 29) { return; }
        empfeori = fields[28];
        if (size < 30) { return; }
        empfnl = fields[29];
        
        if (size < 31) { return; }
        kfkd = fields[30];        
        if (size < 32) { return; }
        kfeori = fields[31];
        if (size < 33) { return; }
        kfnl = fields[32];
        
        if (size < 34) { return; }
        verkkd = fields[33];        
        if (size < 35) { return; }
        verkeori = fields[34];
        if (size < 36) { return; }
        verknl = fields[35];
        
        if (size < 37) { return; }
        zwakd = fields[36];        
        if (size < 38) { return; }
        zwaeori = fields[37];
        if (size < 39) { return; }
        zwanl = fields[38];

        if (size < 40) { return; }
        vzwakd = fields[39];
        if (size < 41) { return; }
        vzwaeori = fields[40];
        if (size < 42) { return; }
        vzwanl = fields[41];

        if (size < 43) { return; }
        anzpos = fields[42];         
        if (size < 44) { return; }
        gesart = fields[43];        
        if (size < 45) { return; }
        ausort = fields[44];
        if (size < 46) { return; }
        vland = fields[45];

        if (size < 47) { return; }
        rechkd = fields[46];        
        if (size < 48) { return; }
        recheori = fields[47];
        if (size < 49) { return; }
        rechnl = fields[48];
        
        if (size < 50) { return; }
        quelkz = fields[49];
   
   }

    public String teilsatzBilden() {
        StringBuffer buff = new StringBuffer();

        buff = buff.append(tsTyp);
        buff = buff.append(trenner);				
        buff = buff.append(beznr);
        buff = buff.append(trenner);				
        buff = buff.append(korrkz);
        buff = buff.append(trenner);				
        buff = buff.append(antart);
        buff = buff.append(trenner);				
        buff = buff.append(wkz);
        buff = buff.append(trenner);				
        buff = buff.append(kzzvg);
        buff = buff.append(trenner);				
        buff = buff.append(kzvtr);
        buff = buff.append(trenner);				
        buff = buff.append(kzemp);
        buff = buff.append(trenner);				
        buff = buff.append(kzvsta);
        buff = buff.append(trenner);				
        buff = buff.append(kzdv1);
        buff = buff.append(trenner);				
        buff = buff.append(kzcon);
        buff = buff.append(trenner);				
        buff = buff.append(kzza);
        buff = buff.append(trenner);				
        buff = buff.append(dst);
        buff = buff.append(trenner);				
        buff = buff.append(anmkd);
        buff = buff.append(trenner);				
        buff = buff.append(anmeori);
        buff = buff.append(trenner);				
        buff = buff.append(anmnl);
        buff = buff.append(trenner);				
        buff = buff.append(anmust);
        buff = buff.append(trenner);				
        buff = buff.append(vertkd);
        buff = buff.append(trenner);				
        buff = buff.append(verteori);
        buff = buff.append(trenner);				
        buff = buff.append(vertnl);
        buff = buff.append(trenner);				
        buff = buff.append(verskd);
        buff = buff.append(trenner);				
        buff = buff.append(verseori);
        buff = buff.append(trenner);	
        buff = buff.append(versnl);
        buff = buff.append(trenner);
        buff = buff.append(erwkd);
        buff = buff.append(trenner);				
        buff = buff.append(erweori);
        buff = buff.append(trenner);				
        buff = buff.append(erwnl);       
        buff = buff.append(trenner);				
        buff = buff.append(erwust);
        buff = buff.append(trenner);				
        buff = buff.append(empfkd);
        buff = buff.append(trenner);				
        buff = buff.append(empfeori);
        buff = buff.append(trenner);				
        buff = buff.append(empfnl);
        buff = buff.append(trenner);				
        buff = buff.append(kfkd);
        buff = buff.append(trenner);				
        buff = buff.append(kfeori);
        buff = buff.append(trenner);				
        buff = buff.append(kfnl);
        buff = buff.append(trenner);				
        buff = buff.append(verkkd);
        buff = buff.append(trenner);				
        buff = buff.append(verkeori);
        buff = buff.append(trenner);				
        buff = buff.append(verknl);
        buff = buff.append(trenner);				
        buff = buff.append(zwakd);
        buff = buff.append(trenner);				
        buff = buff.append(zwaeori);
        buff = buff.append(trenner);				
        buff = buff.append(zwanl);
        buff = buff.append(trenner);				
        buff = buff.append(vzwakd);
        buff = buff.append(trenner);				
        buff = buff.append(vzwaeori);
        buff = buff.append(trenner);				
        buff = buff.append(vzwanl);
        buff = buff.append(trenner);				
        buff = buff.append(anzpos);
        buff = buff.append(trenner);				
        buff = buff.append(gesart);
        buff = buff.append(trenner);				
        buff = buff.append(ausort);
        buff = buff.append(trenner);				
        buff = buff.append(vland);
        buff = buff.append(trenner);				
        buff = buff.append(rechkd);
        buff = buff.append(trenner);				
        buff = buff.append(recheori);
        buff = buff.append(trenner);				
        buff = buff.append(rechnl);
        buff = buff.append(trenner);				
        buff = buff.append(quelkz);
        buff = buff.append(trenner);				


        return new String(buff);
    }

	public String getTsTyp() {
		return tsTyp;
	
	}

	public void setTsTyp(String tsTyp) {
		this.tsTyp = Utils.checkNull(tsTyp);
	}

	public String getBeznr() {
		return beznr;
	
	}

	public void setBeznr(String beznr) {
		this.beznr = Utils.checkNull(beznr);
	}

	
	public boolean isEmpty() {
		return Utils.isStringEmpty(korrkz) &&
			Utils.isStringEmpty(antart) && 
			Utils.isStringEmpty(wkz) &&
			Utils.isStringEmpty(kzzvg)	&&
			Utils.isStringEmpty(kzvtr) &&
			Utils.isStringEmpty(kzemp) && 
			Utils.isStringEmpty(kzvsta) &&  
			Utils.isStringEmpty(kzdv1) && 
			Utils.isStringEmpty(kzcon) && 
			Utils.isStringEmpty(kzza) && 
			Utils.isStringEmpty(dst) && 
			Utils.isStringEmpty(anmkd) &&
			Utils.isStringEmpty(anmeori) && 
			Utils.isStringEmpty(anmust) && 
			Utils.isStringEmpty(vertkd) && 
			Utils.isStringEmpty(verteori) &&
			Utils.isStringEmpty(verskd) && 
			Utils.isStringEmpty(verseori) && 
			Utils.isStringEmpty(erwkd) && 
			Utils.isStringEmpty(erweori) &&
			Utils.isStringEmpty(erwust) && 
			Utils.isStringEmpty(empfkd) && 
			Utils.isStringEmpty(empfeori) && 
			Utils.isStringEmpty(kfkd) &&
			Utils.isStringEmpty(kfeori)  &&
			Utils.isStringEmpty(verkkd) && 
			Utils.isStringEmpty(verkeori)  &&
			Utils.isStringEmpty(zwakd) &&
			Utils.isStringEmpty(zwaeori)  &&
			Utils.isStringEmpty(vzwakd) &&
			Utils.isStringEmpty(vzwaeori)  &&
			Utils.isStringEmpty(anzpos) &&
			Utils.isStringEmpty(gesart)  &&
			Utils.isStringEmpty(ausort)  &&
			Utils.isStringEmpty(vland)  &&
			Utils.isStringEmpty(rechkd) &&
			Utils.isStringEmpty(recheori)  &&
			Utils.isStringEmpty(quelkz);
	}

	public String getAnmeori() {
		return anmeori;
	}

	public void setAnmeori(String value) {
		this.anmeori = Utils.checkNull(value);
	}

	public String getAnmnl() {
		return anmnl;
	}

	public void setAnmnl(String value) {
		this.anmnl = Utils.checkNull(value);
	}

	public String getVerteori() {
		return verteori;
	}

	public void setVerteori(String value) {
		this.verteori = Utils.checkNull(value);
	}

	public String getVertnl() {
		return vertnl;
	}

	public void setVertnl(String value) {
		this.vertnl = Utils.checkNull(value);
	}

	public String getVerseori() {
		return verseori;
	}

	public void setVerseori(String value) {
		this.verseori = Utils.checkNull(value);
	}

	public String getVersnl() {
		return versnl;
	}

	public void setVersnl(String value) {
		this.versnl = Utils.checkNull(value);
	}

	public String getErweori() {
		return erweori;
	}

	public void setErweori(String value) {
		this.erweori = Utils.checkNull(value);
	}

	public String getErwnl() {
		return erwnl;
	}

	public void setErwnl(String value) {
		this.erwnl = Utils.checkNull(value);
	}

	public String getEmpfeori() {
		return empfeori;
	}

	public void setEmpfeori(String value) {
		this.empfeori = Utils.checkNull(value);
	}

	public String getEmpfnl() {
		return empfnl;
	}

	public void setEmpfnl(String value) {
		this.empfnl = Utils.checkNull(value);
	}

	public String getKfeori() {
		return kfeori;
	}

	public void setKfeori(String value) {
		this.kfeori = Utils.checkNull(value);
	}

	public String getKfnl() {
		return kfnl;
	}

	public void setKfnl(String value) {
		this.kfnl = Utils.checkNull(value);
	}

	public String getVerkeori() {
		return verkeori;
	}

	public void setVerkeori(String value) {
		this.verkeori = Utils.checkNull(value);
	}

	public String getVerknl() {
		return verknl;
	}

	public void setVerknl(String value) {
		this.verknl = Utils.checkNull(value);
	}

	public String getZwaeori() {
		return zwaeori;
	}

	public void setZwaeori(String value) {
		this.zwaeori = Utils.checkNull(value);
	}

	public String getZwanl() {
		return zwanl;
	}

	public void setZwanl(String value) {
		this.zwanl = Utils.checkNull(value);
	}

	public String getVzwakd() {
		return vzwakd;
	}

	public void setVzwakd(String value) {
		this.vzwakd = Utils.checkNull(value);
	}

	public String getVzwaeori() {
		return vzwaeori;
	}

	public void setVzwaeori(String value) {
		this.vzwaeori = Utils.checkNull(value);
	}

	public String getVzwanl() {
		return vzwanl;
	}

	public void setVzwanl(String value) {
		this.vzwanl = Utils.checkNull(value);
	}

	public String getRecheori() {
		return recheori;
	}

	public void setRecheori(String value) {
		this.recheori = Utils.checkNull(value);
	}

	public String getRechnl() {
		return rechnl;
	}

	public void setRechnl(String value) {
		this.rechnl = Utils.checkNull(value);
	}

	public String getKorrkz() {
		return korrkz;
	}

	public void setKorrkz(String value) {
		this.korrkz = Utils.checkNull(value);
	}

	public String getAntart() {
		return antart;
	}

	public void setAntart(String value) {
		this.antart = Utils.checkNull(value);
	}

	public String getWkz() {
		return wkz;
	}

	public void setWkz(String wkz) {
		this.wkz = Utils.checkNull(wkz);
	}

	public String getKzzvg() {
		return kzzvg;
	}

	public void setKzzvg(String kzzvg) {
		this.kzzvg = Utils.checkNull(kzzvg);
	}

	public String getKzvtr() {
		return kzvtr;
	}

	public void setKzvtr(String kzvtr) {
		this.kzvtr = Utils.checkNull(kzvtr);
	}

	public String getKzemp() {
		return kzemp;
	}

	public void setKzemp(String kzemp) {
		this.kzemp = Utils.checkNull(kzemp);
	}

	public String getKzvsta() {
		return kzvsta;
	}

	public void setKzvsta(String kzvsta) {
		this.kzvsta = Utils.checkNull(kzvsta);
	}

	public String getKzdv1() {
		return kzdv1;
	}

	public void setKzdv1(String kzdv1) {
		this.kzdv1 = Utils.checkNull(kzdv1);
	}

	public String getKzcon() {
		return kzcon;
	}

	public void setKzcon(String kzcon) {
		this.kzcon = Utils.checkNull(kzcon);
	}

	public String getKzza() {
		return kzza;
	}

	public void setKzza(String kzza) {
		this.kzza = Utils.checkNull(kzza);
	}

	public String getDst() {
		return dst;
	}

	public void setDst(String dst) {
		this.dst = Utils.checkNull(dst);
	}

	public String getAnmkd() {
		return anmkd;
	}

	public void setAnmkd(String anmkd) {
		this.anmkd = Utils.checkNull(anmkd);
	}

	

	public String getAnmust() {
		return anmust;
	}

	public void setAnmust(String anmust) {
		this.anmust = Utils.checkNull(anmust);
	}

	public String getVertkd() {
		return vertkd;
	}

	public void setVertkd(String vertkd) {
		this.vertkd = Utils.checkNull(vertkd);
	}
	
	public String getVerskd() {
		return verskd;
	}

	public void setVerskd(String verskd) {
		this.verskd = Utils.checkNull(verskd);
	}	

	public String getErwkd() {
		return erwkd;
	}

	public void setErwkd(String erwkd) {
		this.erwkd = Utils.checkNull(erwkd);
	}

	public String getErwust() {
		return erwust;
	}

	public void setErwust(String erwust) {
		this.erwust = Utils.checkNull(erwust);
	}

	public String getEmpfkd() {
		return empfkd;
	}

	public void setEmpfkd(String empfkd) {
		this.empfkd = Utils.checkNull(empfkd);
	}

	public String getKfkd() {
		return kfkd;
	}

	public void setKfkd(String kfkd) {
		this.kfkd = Utils.checkNull(kfkd);
	}

	public String getVerkkd() {
		return verkkd;
	}

	public void setVerkkd(String verkkd) {
		this.verkkd = Utils.checkNull(verkkd);
	}

	public String getZwakd() {
		return zwakd;
	}

	public void setZwakd(String zwakd) {
		this.zwakd = Utils.checkNull(zwakd);
	}

	public String getAnzpos() {
		return anzpos;
	}

	public void setAnzpos(String anzpos) {
		this.anzpos = Utils.checkNull(anzpos);
	}

	public String getGesart() {
		return gesart;
	}

	public void setGesart(String gesart) {
		this.gesart = Utils.checkNull(gesart);
	}

	public String getAusort() {
		return ausort;
	}

	public void setAusort(String ausort) {
		this.ausort = Utils.checkNull(ausort);
	}

	public String getVland() {
		return vland;
	}

	public void setVland(String vland) {
		this.vland = Utils.checkNull(vland);
	}

	public String getRechkd() {
		return rechkd;
	}

	public void setRechkd(String rechkd) {
		this.rechkd = Utils.checkNull(rechkd);
	}

	public String getQuelkz() {
		return quelkz;
	}

	public void setQuelkz(String quelkz) {
		this.quelkz = Utils.checkNull(quelkz);
	}
}
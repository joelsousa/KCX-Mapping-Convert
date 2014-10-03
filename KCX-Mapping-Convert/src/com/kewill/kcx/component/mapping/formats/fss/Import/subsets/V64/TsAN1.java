package com.kewill.kcx.component.mapping.formats.fss.Import.subsets.V64;
import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Modul        :   AN1
 * Teilsatz     :   TsAN1.java
 * Erstellt     :   05.09.2011
 * Beschreibung :   1. Hauptsatz des Antragteils für EZA
 *
 * @author          Alfred Krzoska
 *
 */

public class TsAN1 extends Teilsatz {

    private String tsTyp    = "";       // Ts-Schlüssel
    private String beznr    = "";       // Bezugsnummer
    private String korrkz   = "";       // Korrekturkennzeichen
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
    private String anmknr	= "";		// Kundennummer des Anmelders
    private String anmzb	= "";		// Zollnummer des Anmelders
    private String anmust	= "";		// UST-ID des Anmelders
    private String verknr	= "";		// Kundennummer des Vertreters
    private String verzb	= "";		// Zollnummer des Vertreters
    private String vesknr	= "";		// Kundennummer des Versenders
    private String veszb	= "";		// Zollnnummer des Versenders
    private String erwknr	= "";		// Kundennummer des Erwerbers
    private String erwzb	= "";		// Zollnummer des Erwerbers
    private String erwust	= "";		// UST-ID des Erwerbers
    private String empknr	= "";		// Kundennummer des Empfängers
    private String empzb	= "";		// Zollnummer des Empfängers
    private String kfknr	= "";		// Kundennummer des Käufers
    private String kfzb		= "";		// Zollnummer des Käufers
    private String vekknr	= "";		// Kundennummer des Verkäufers
    private String vekzb	= "";		// Zollnummer des Verkäufers
    private String zwaknr	= "";		// Kundennummer des Zollwertanmelders
    private String zwazb	= "";		// Zollnummer des Zollwertanmelders
    private String vwaknr	= "";		// Kundennummer des Vertreters des ZWA
    private String vwazb	= "";		// Zollnummer des Vertreters des ZWA
    private String anzpos	= "";		// Anzahl der Positionen
    private String gesart	= "";		// Geschäftsart
    private String ausort	= "";		// Ausstellungsort
    private String vland	= "";		// Versendungsland
    private String recknr	= "";		// Kundennummer "Für Rechnung"
    private String rechzb	= "";		// Zollnummer "Für Rechnung"
    private String quelkz	= "";		// Kennzeichen Quell-Verfahren

    
    public TsAN1() {
        tsTyp = "AN1";
    }

   public void setFields(String[] fields)
   {
		int size = fields.length;
		String ausgabe = "FSS: "+fields[0]+" size = "+ size;
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
        anmknr = fields[13];
        
        if (size < 15) { return; }
        anmzb = fields[14];
        
        if (size < 16) { return; }
        anmust = fields[15];
        
        if (size < 17) { return; }
        verknr = fields[16];
        
        if (size < 18) { return; }
        verzb = fields[17];
        
        if (size < 19) { return; }
        vesknr = fields[18];

        if (size < 20) { return; }
        veszb = fields[19];

        if (size < 21) { return; }
        erwknr = fields[20];

        if (size < 22) { return; }
        erwzb = fields[21];
        
        if (size < 23) { return; }
        erwust = fields[22];
         
        if (size < 24) { return; }
        empknr = fields[23];
        
        if (size < 25) { return; }
        empzb = fields[24];
        
        if (size < 26) { return; }
        kfknr = fields[25];
        
        if (size < 27) { return; }
        kfzb = fields[26];
        
        if (size < 28) { return; }
        vekknr = fields[27];
        
        if (size < 29) { return; }
        vekzb = fields[28];
        
        if (size < 30) { return; }
        zwaknr = fields[29];
        
        if (size < 31) { return; }
        zwazb = fields[30];

        if (size < 32) { return; }
        vwaknr = fields[31];

        if (size < 33) { return; }
        vwazb = fields[32];

        if (size < 34) { return; }
        anzpos = fields[33];
         
        if (size < 35) { return; }
        gesart = fields[34];
        
        if (size < 36) { return; }
        ausort = fields[35];

        if (size < 37) { return; }
        vland = fields[36];

        if (size < 38) { return; }
        recknr = fields[37];
        
        if (size < 39) { return; }
        rechzb = fields[38];
        
        if (size < 40) { return; }
        quelkz = fields[39];
   
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
        buff = buff.append(anmknr);
        buff = buff.append(trenner);				
        buff = buff.append(anmzb);
        buff = buff.append(trenner);				
        buff = buff.append(anmust);
        buff = buff.append(trenner);				
        buff = buff.append(verknr);
        buff = buff.append(trenner);				
        buff = buff.append(verzb);
        buff = buff.append(trenner);				
        buff = buff.append(vesknr);
        buff = buff.append(trenner);				
        buff = buff.append(veszb);
        buff = buff.append(trenner);				
        buff = buff.append(erwknr);
        buff = buff.append(trenner);				
        buff = buff.append(erwzb);
        buff = buff.append(trenner);				
        buff = buff.append(erwust);
        buff = buff.append(trenner);				
        buff = buff.append(empknr);
        buff = buff.append(trenner);				
        buff = buff.append(empzb);
        buff = buff.append(trenner);				
        buff = buff.append(kfknr);
        buff = buff.append(trenner);				
        buff = buff.append(kfzb);
        buff = buff.append(trenner);				
        buff = buff.append(vekknr);
        buff = buff.append(trenner);				
        buff = buff.append(vekzb);
        buff = buff.append(trenner);				
        buff = buff.append(zwaknr);
        buff = buff.append(trenner);				
        buff = buff.append(zwazb);
        buff = buff.append(trenner);				
        buff = buff.append(vwaknr);
        buff = buff.append(trenner);				
        buff = buff.append(vwazb);
        buff = buff.append(trenner);				
        buff = buff.append(anzpos);
        buff = buff.append(trenner);				
        buff = buff.append(gesart);
        buff = buff.append(trenner);				
        buff = buff.append(ausort);
        buff = buff.append(trenner);				
        buff = buff.append(vland);
        buff = buff.append(trenner);				
        buff = buff.append(recknr);
        buff = buff.append(trenner);				
        buff = buff.append(rechzb);
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
			Utils.isStringEmpty(anmknr) &&
			Utils.isStringEmpty(anmzb) && 
			Utils.isStringEmpty(anmust) && 
			Utils.isStringEmpty(verknr) && 
			Utils.isStringEmpty(verzb) &&
			Utils.isStringEmpty(vesknr) && 
			Utils.isStringEmpty(veszb) && 
			Utils.isStringEmpty(erwknr) && 
			Utils.isStringEmpty(erwzb) &&
			Utils.isStringEmpty(erwust) && 
			Utils.isStringEmpty(empknr) && 
			Utils.isStringEmpty(empzb) && 
			Utils.isStringEmpty(kfknr) &&
			Utils.isStringEmpty(kfzb)  &&
			Utils.isStringEmpty(vekknr) && 
			Utils.isStringEmpty(vekzb)  &&
			Utils.isStringEmpty(zwaknr) &&
			Utils.isStringEmpty(zwazb)  &&
			Utils.isStringEmpty(vwaknr) &&
			Utils.isStringEmpty(vwazb)  &&
			Utils.isStringEmpty(anzpos) &&
			Utils.isStringEmpty(gesart)  &&
			Utils.isStringEmpty(ausort)  &&
			Utils.isStringEmpty(vland)  &&
			Utils.isStringEmpty(recknr) &&
			Utils.isStringEmpty(rechzb)  &&
			Utils.isStringEmpty(quelkz);
	}

	public String getKorrkz() {
		return korrkz;
	}

	public void setKorrkz(String korrkz) {
		this.korrkz = Utils.checkNull(korrkz);
	}

	public String getAntart() {
		return antart;
	}

	public void setAntart(String antart) {
		this.antart = Utils.checkNull(antart);
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

	public String getAnmknr() {
		return anmknr;
	}

	public void setAnmknr(String anmknr) {
		this.anmknr = Utils.checkNull(anmknr);
	}

	public String getAnmzb() {
		return anmzb;
	}

	public void setAnmzb(String anmzb) {
		this.anmzb = Utils.checkNull(anmzb);
	}

	public String getAnmust() {
		return anmust;
	}

	public void setAnmust(String anmust) {
		this.anmust = Utils.checkNull(anmust);
	}

	public String getVerknr() {
		return verknr;
	}

	public void setVerknr(String verknr) {
		this.verknr = Utils.checkNull(verknr);
	}

	public String getVerzb() {
		return verzb;
	}

	public void setVerzb(String verzb) {
		this.verzb = Utils.checkNull(verzb);
	}

	public String getVesknr() {
		return vesknr;
	}

	public void setVesknr(String vesknr) {
		this.vesknr = Utils.checkNull(vesknr);
	}

	public String getVeszb() {
		return veszb;
	}

	public void setVeszb(String veszb) {
		this.veszb = Utils.checkNull(veszb);
	}

	public String getErwknr() {
		return erwknr;
	}

	public void setErwknr(String erwknr) {
		this.erwknr = Utils.checkNull(erwknr);
	}

	public String getErwzb() {
		return erwzb;
	}

	public void setErwzb(String erwzb) {
		this.erwzb = Utils.checkNull(erwzb);
	}

	public String getErwust() {
		return erwust;
	}

	public void setErwust(String erwust) {
		this.erwust = Utils.checkNull(erwust);
	}

	public String getEmpknr() {
		return empknr;
	}

	public void setEmpknr(String empknr) {
		this.empknr = Utils.checkNull(empknr);
	}

	public String getEmpzb() {
		return empzb;
	}

	public void setEmpzb(String empzb) {
		this.empzb = Utils.checkNull(empzb);
	}

	public String getKfknr() {
		return kfknr;
	}

	public void setKfknr(String kfknr) {
		this.kfknr = Utils.checkNull(kfknr);
	}

	public String getKfzb() {
		return kfzb;
	}

	public void setKfzb(String kfzb) {
		this.kfzb = Utils.checkNull(kfzb);
	}

	public String getVekknr() {
		return vekknr;
	}

	public void setVekknr(String vekknr) {
		this.vekknr = Utils.checkNull(vekknr);
	}

	public String getVekzb() {
		return vekzb;
	}

	public void setVekzb(String vekzb) {
		this.vekzb = Utils.checkNull(vekzb);
	}

	public String getZwaknr() {
		return zwaknr;
	}

	public void setZwaknr(String zwaknr) {
		this.zwaknr = Utils.checkNull(zwaknr);
	}

	public String getZwazb() {
		return zwazb;
	}

	public void setZwazb(String zwazb) {
		this.zwazb = Utils.checkNull(zwazb);
	}

	public String getVwaknr() {
		return vwaknr;
	}

	public void setVwaknr(String vwaknr) {
		this.vwaknr = Utils.checkNull(vwaknr);
	}

	public String getVwazb() {
		return vwazb;
	}

	public void setVwazb(String vwazb) {
		this.vwazb = Utils.checkNull(vwazb);
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

	public String getRecknr() {
		return recknr;
	}

	public void setRecknr(String recknr) {
		this.recknr = Utils.checkNull(recknr);
	}

	public String getRechzb() {
		return rechzb;
	}

	public void setRechzb(String rechzb) {
		this.rechzb = Utils.checkNull(rechzb);
	}

	public String getQuelkz() {
		return quelkz;
	}

	public void setQuelkz(String quelkz) {
		this.quelkz = Utils.checkNull(quelkz);
	}
}
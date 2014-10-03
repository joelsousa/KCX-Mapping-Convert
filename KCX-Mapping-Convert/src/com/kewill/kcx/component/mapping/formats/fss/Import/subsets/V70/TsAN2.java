package com.kewill.kcx.component.mapping.formats.fss.Import.subsets.V70;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module       :   Import 70<br>
 * Subset       :   TsAN2.java<br>
 * Created      :   12.11.2012<br>
 * Description  :   2. Hauptsatz des Antragteils für EZA.<br>
 *
 * @author          iwaniuk
 * @version         7.0.00 
 */

public class TsAN2 extends Teilsatz {

	private String		beznr  = "";   		//Bezugsnummer	
	private String		einzst = "";   		//Eingangszollstelle	
	private String		einfsi = "";   		//Sitz des Einführers	
	private String		waort  = "";   		//Warenort			
	private String		bbland = "";   		//Bestimmungsbundesland	
	private String		finamt = "";   		//Finanzamt Anmelder		
	private String		stzoll = "";   		//zollrechtlicher Status	
	private String		ststat = "";   		//statistischer Status	
	private String		rohm   = "";   		//Gesamtrohmasse		
	private String		vorpnr = "";   		//Vorpapiernummer		
	private String		vorpar = "";   		//Vorpapierart		
	private String		bfvkzg = "";   		//Verkehrszweig Grenze	
	private String		bfartg = "";   		//Beförderungsmittel Grenze	
	private String		bfnatg = "";   		//Nationalität Beförderungsmittel Grenze	
	private String		bfvkzi = "";   		//Verkehrszweig Inland	
	private String		bfbesi = "";   		//Beschreibung Beförderungsmittel Inland	
	private String		bfkzi  = "";   		//KZ Beförderungsmittel bei Ankunft	
	private String		sbname = "";   		//Sachbearbeiter Name	kann leer sein, 
											//auch dann, wenn keine SB-Nr. angegeben ist	
	private String		sbstel = "";   		//Sachbearbeiter Stellung	
	private String		sbtele = "";   		//Sachbearbeiter Telefon	
	private String		sbnr   = "";   		//Sachbearbeiternummer	kann leer sein,
											// auch dann, wenn keine anderen SB-Daten angegeben sind	
	private String		libsch = "";   		//Lieferbedingung Schlüssel	
	private String		libart = "";   		//Lieferbedingung Art	
	private String		libinc = "";   		//Lieferbedingung Incoterm	
	private String		libort = "";   		//Lieferbedingung Ort	
	private String		preisr = "";   		//Rechnungspreis	
	private String		waehr  = "";   		//Währung		
	private String		kurs   = "";   		//Umrechnungskurs	
	private String		mzp    = "";   		//maßgeblicher Zeitpunkt	
	private String		suaart = "";   		//BE-Anteil SumA, Art der Identifikation	
	private String		azvbew = "";   		//BE-Anteil Zolllager und AV/UV, Bewilligungsnummer	
	private String		zlbez  = "";   		//BE-Anteil Zolllager, Bezugsnummer	
	private String		kuatnr = "";   		//Kundenauftragsnummer
	private String 		kzmail = "";		//Kennzeichen, ob das PDF des Steuerbescheids beim 
											//Automatikdruck an Anmelder ODER Vertreter gemailt werden soll
	private String 		beland = "";        //new V70

    
    public TsAN2() {
        tsTyp = "AN2";
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
        einzst = fields[2];

        if (size < 4) { return; } 
        einfsi = fields[3];

        if (size < 5) { return; } 
        waort =	fields[4];

        if (size < 6) { return; } 
        bbland = fields[5];
        
        if (size < 7) { return; } 
        finamt = fields[6]; 
        		
        if (size < 8) { return; }
        stzoll = fields[7]; 
        		
        if (size < 9) { return; } 
        ststat = fields[8];

        if (size < 10) { return; } 
        rohm =	fields[9];
        		
        if (size < 11) { return; } 
        vorpnr = fields[10];
        
        if (size < 12) { return; }
        vorpar = fields[11];

        if (size < 13) { return; }
        bfvkzg = fields[12];
        
        if (size < 14) { return; }
        bfartg = fields[13];
        
        if (size < 15) { return; }
        bfnatg = fields[14];
        
        if (size < 16) { return; }
        bfvkzi = fields[15];
        
        if (size < 17) { return; }
        bfbesi = fields[16];
        
        if (size < 18) { return; }
        bfkzi =	fields[17];
        
        if (size < 19) { return; }
        sbname = fields[18];

        if (size < 20) { return; }
        sbstel = fields[19];

        if (size < 21) { return; }
        sbtele = fields[20];

        if (size < 22) { return; }
        sbnr =	fields[21];
        
        if (size < 23) { return; }
        libsch = fields[22];
         
        if (size < 24) { return; }
        libart = fields[23];
        
        if (size < 25) { return; }
        libinc = fields[24];
        
        if (size < 26) { return; }
        libort = fields[25];
        
        if (size < 27) { return; }
        preisr = fields[26];
        
        if (size < 28) { return; }
        waehr =	fields[27];
        
        if (size < 29) { return; }
        kurs =	fields[28];
        
        if (size < 30) { return; }
        mzp =	fields[29];
        
        if (size < 31) { return; }
        suaart = fields[30];

        if (size < 32) { return; }
        azvbew = fields[31];

        if (size < 33) { return; }
        zlbez =	fields[32];

        if (size < 34) { return; }
        kuatnr = fields[33];
        
        if (size < 35) { return; }
        kuatnr = fields[34];
        
        if (size < 36) { return; }
        beland = fields[35];
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
	
	public String getEinzst() {
		return einzst;
	}
	
	public void setEinzst(String einzst) {
		this.einzst = Utils.checkNull(einzst);
	}
	
	public String getEinfsi() {
		return einfsi;
	}
	
	public void setEinfsi(String einfsi) {
		this.einfsi = Utils.checkNull(einfsi);
	}
	
	public String getWaort() {
		return waort;
	}
	
	public void setWaort(String waort) {
		this.waort = Utils.checkNull(waort);
	}
	
	public String getBbland() {
		return bbland;
	}
	
	public void setBbland(String bbland) {
		this.bbland = Utils.checkNull(bbland);
	}
	
	public String getFinamt() {
		return finamt;
	}
	
	public void setFinamt(String finamt) {
		this.finamt = Utils.checkNull(finamt);
	}
	
	public String getStzoll() {
		return stzoll;
	}
	
	public void setStzoll(String stzoll) {
		this.stzoll = Utils.checkNull(stzoll);
	}
	
	public String getStstat() {
		return ststat;
	}
	
	public void setStstat(String ststat) {
		this.ststat = Utils.checkNull(ststat);
	}
	
	public String getRohm() {
		return rohm;
	}
	
	public void setRohm(String rohm) {
		this.rohm = Utils.checkNull(rohm);
	}
	
	public String getVorpnr() {
		return vorpnr;
	}
	
	public void setVorpnr(String vorpnr) {
		this.vorpnr = Utils.checkNull(vorpnr);
	}
	
	public String getVorpar() {
		return vorpar;
	}
	
	public void setVorpar(String vorpar) {
		this.vorpar = Utils.checkNull(vorpar);
	}
	
	public String getBfvkzg() {
		return bfvkzg;
	}
	
	public void setBfvkzg(String bfvkzg) {
		this.bfvkzg = Utils.checkNull(bfvkzg);
	}
	
	public String getBfartg() {
		return bfartg;
	}
	
	public void setBfartg(String bfartg) {
		this.bfartg = Utils.checkNull(bfartg);
	}
	
	public String getBfnatg() {
		return bfnatg;
	}
	
	public void setBfnatg(String bfnatg) {
		this.bfnatg = Utils.checkNull(bfnatg);
	}
	
	public String getBfvkzi() {
		return bfvkzi;
	}
	
	public void setBfvkzi(String bfvkzi) {
		this.bfvkzi = Utils.checkNull(bfvkzi);
	}
	
	public String getBfbesi() {
		return bfbesi;
	}
	
	public void setBfbesi(String bfbesi) {
		this.bfbesi = Utils.checkNull(bfbesi);
	}
	
	public String getBfkzi() {
		return bfkzi;
	}
	
	public void setBfkzi(String bfkzi) {
		this.bfkzi = Utils.checkNull(bfkzi);
	}
	
	public String getSbname() {
		return sbname;
	}
	
	public void setSbname(String sbname) {
		this.sbname = Utils.checkNull(sbname);
	}
	
	public String getSbstel() {
		return sbstel;
	}
	
	public void setSbstel(String sbstel) {
		this.sbstel = Utils.checkNull(sbstel);
	}
	
	public String getSbtele() {
		return sbtele;
	}
	
	public void setSbtele(String sbtele) {
		this.sbtele = Utils.checkNull(sbtele);
	}
	
	public String getSbnr() {
		return sbnr;
	}
	
	public void setSbnr(String sbnr) {
		this.sbnr = Utils.checkNull(sbnr);
	}
	
	public String getLibsch() {
		return libsch;
	}
	
	public void setLibsch(String libsch) {
		this.libsch = Utils.checkNull(libsch);
	}
	
	public String getLibart() {
		return libart;
	}
	
	public void setLibart(String libart) {
		this.libart = Utils.checkNull(libart);
	}
	
	public String getLibinc() {
		return libinc;
	}
	
	public void setLibinc(String libinc) {
		this.libinc = Utils.checkNull(libinc);
	}
	
	public String getLibort() {
		return libort;
	}
	
	public void setLibort(String libort) {
		this.libort = Utils.checkNull(libort);
	}
	
	public String getPreisr() {
		return preisr;
	}
	
	public void setPreisr(String preisr) {
		this.preisr = Utils.checkNull(preisr);
	}
	
	public String getWaehr() {
		return waehr;
	}
	
	public void setWaehr(String waehr) {
		this.waehr = Utils.checkNull(waehr);
	}
	
	public String getKurs() {
		return kurs;
	}
	
	public void setKurs(String kurs) {
		this.kurs = Utils.checkNull(kurs);
	}
	
	public String getMzp() {
		return mzp;
	}
	
	public void setMzp(String mzp) {
		this.mzp = Utils.checkNull(mzp);
	}
	
	public String getSuaart() {
		return suaart;
	}
	
	public void setSuaart(String suaart) {
		this.suaart = Utils.checkNull(suaart);
	}
	
	public String getAzvbew() {
		return azvbew;
	}
	
	public void setAzvbew(String azvbew) {
		this.azvbew = Utils.checkNull(azvbew);
	}
	
	public String getZlbez() {
		return zlbez;
	}
	
	public void setZlbez(String zlbez) {
		this.zlbez = Utils.checkNull(zlbez);
	}
	
	public String getKuatnr() {
		return kuatnr;
	}
	
	public void setKuatnr(String kuatnr) {
		this.kuatnr = Utils.checkNull(kuatnr);
	}

	public String getKzmail() {
		return kzmail;
	}

	public void setKzmail(String kzmail) {
		this.kzmail = Utils.checkNull(kzmail);
	}
	
	public String getBeland() {
		return beland;
	}

	public void setBeland(String value) {
		this.beland = Utils.checkNull(value);
	}
	
	public String teilsatzBilden() {
        StringBuffer buff = new StringBuffer();

        buff = buff.append(tsTyp);
        buff = buff.append(trenner);				
        buff = buff.append(beznr);
        buff = buff.append(trenner);				
        buff = buff.append(einzst);
        buff = buff.append(trenner);				
        buff = buff.append(einfsi);
        buff = buff.append(trenner);				
        buff = buff.append(waort);
        buff = buff.append(trenner);				
        buff = buff.append(bbland);
        buff = buff.append(trenner);				
        buff = buff.append(finamt);
        buff = buff.append(trenner);				
        buff = buff.append(stzoll);
        buff = buff.append(trenner);				
        buff = buff.append(ststat);
        buff = buff.append(trenner);				
        buff = buff.append(rohm);
        buff = buff.append(trenner);				
        buff = buff.append(vorpnr);
        buff = buff.append(trenner);				
        buff = buff.append(vorpar);
        buff = buff.append(trenner);				
        buff = buff.append(bfvkzg);
        buff = buff.append(trenner);				
        buff = buff.append(bfartg);
        buff = buff.append(trenner);				
        buff = buff.append(bfnatg);
        buff = buff.append(trenner);				
        buff = buff.append(bfvkzi);
        buff = buff.append(trenner);				
        buff = buff.append(bfbesi);
        buff = buff.append(trenner);				
        buff = buff.append(bfkzi);
        buff = buff.append(trenner);				
        buff = buff.append(sbname);
        buff = buff.append(trenner);				
        buff = buff.append(sbstel);
        buff = buff.append(trenner);				
        buff = buff.append(sbtele);
        buff = buff.append(trenner);				
        buff = buff.append(sbnr);
        buff = buff.append(trenner);				
        buff = buff.append(libsch);
        buff = buff.append(trenner);				
        buff = buff.append(libart);
        buff = buff.append(trenner);				
        buff = buff.append(libinc);
        buff = buff.append(trenner);				
        buff = buff.append(libort);
        buff = buff.append(trenner);				
        buff = buff.append(preisr);
        buff = buff.append(trenner);				
        buff = buff.append(waehr);
        buff = buff.append(trenner);				
        buff = buff.append(kurs);
        buff = buff.append(trenner);				
        buff = buff.append(mzp);
        buff = buff.append(trenner);				
        buff = buff.append(suaart);
        buff = buff.append(trenner);				
        buff = buff.append(azvbew);
        buff = buff.append(trenner);				
        buff = buff.append(zlbez);
        buff = buff.append(trenner);				
        buff = buff.append(kuatnr);
        buff = buff.append(trenner);				
        buff = buff.append(kzmail);
        buff = buff.append(trenner);
        buff = buff.append(beland);
        buff = buff.append(trenner);        

        return new String(buff);
	}
	
	public boolean isEmpty() {
		return Utils.isStringEmpty(einzst) &&
			Utils.isStringEmpty(einfsi) && 
			Utils.isStringEmpty(waort) &&
			Utils.isStringEmpty(bbland)	&&
			Utils.isStringEmpty(finamt) &&
			Utils.isStringEmpty(stzoll) && 
			Utils.isStringEmpty(ststat) &&  
			Utils.isStringEmpty(vorpnr) && 
			Utils.isStringEmpty(vorpar) && 
			Utils.isStringEmpty(bfvkzg) && 
			Utils.isStringEmpty(bfartg) && 
			Utils.isStringEmpty(bfnatg) &&
			Utils.isStringEmpty(bfvkzi) && 
			Utils.isStringEmpty(bfbesi) && 
			Utils.isStringEmpty(bfkzi) && 
			Utils.isStringEmpty(sbname) &&
			Utils.isStringEmpty(sbstel) && 
			Utils.isStringEmpty(sbtele) && 
			Utils.isStringEmpty(sbnr) && 
			Utils.isStringEmpty(libsch) &&
			Utils.isStringEmpty(libart) && 
			Utils.isStringEmpty(libinc) && 
			Utils.isStringEmpty(libort) && 
			Utils.isStringEmpty(preisr) &&
			Utils.isStringEmpty(waehr)  &&
			Utils.isStringEmpty(kurs) && 
			Utils.isStringEmpty(mzp)  &&
			Utils.isStringEmpty(suaart) &&
			Utils.isStringEmpty(azvbew)  &&
			Utils.isStringEmpty(zlbez) &&
			Utils.isStringEmpty(kuatnr);
	}
}

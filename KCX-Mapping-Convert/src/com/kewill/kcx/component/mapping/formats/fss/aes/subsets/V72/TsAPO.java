package com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V72;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.countries.common.TIN;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.MsgExpEntPos;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.Statistic;

/**
* Module		:	Export/aes
* Created		:	19.07.2013
* Description	:	Positionssatz. 
* 				:   neu inV72: Kennzeichen AH-stat Wert, mit 0 senden
*
* @author 	iwaniuk
* @version  2.2.00 (Zabis V72)   
* 
*/

public class TsAPO  extends Teilsatz {
	
    private String beznr  = "";       // Bezugsnummer
    private String posnr  = "";       // Positionsnummer
    private String oripos = "";
    private String artnr  = "";       // Artikelnummer
    private String tnr    = "";       // Warentarifnummer
    private String tnrtrc = "";       // Warennummer TARIC
    private String tnrzu1 = "";       // Warennummer 1. Zusatz
    private String tnrzu2 = "";       // Warennummer 2. Zusatz
    private String tnrnat = "";       // Warennummer; nat. Angaben
    private String wbsch  = "";       // Warenbeschreibung
    private String fregnr = "";       // Registriernummer Fremdsystem
    private String verm   = "";       // Vermerk
    private String eigmas = "";       // Eigenmasse
    private String rohmas = "";       // Rohmasse
    private String anmvrf = "";       // angemeldetes Verfahren
    private String prevrf = "";       // vorangegangenes Verfahren
    private String natvrf = "";       // weiteres Verfahren, nat.
    // CK20120828
    private String asvfr  = "";		  // Verfahren Ausfuhrerstattung ergänzt da es fehlte!
	private String ubland = "";       // Ursprungsbundesland
    private String wmahst = "";       // AH-statistische Menge
    private String ahwert = "";       // AH-statistische Wert        
    private String adrkon = "";                     
    private String gesart  = "";     
    private String libart = "";                     
    private String libinc = "";  
    private String libort = "";                     
    private String kzh2o  = "";     
    private String azvbew = "";       // Bewilligungsnummer im Beendigungsanteil           
    private String zlbez  = "";       // Bezugsnummer im BE-Anteil ZL     
    private String ahrico  = "";      // EI20130719:  Kennzeichen AH-stat Wert, mit 0 senden

    public TsAPO() {
        tsTyp = "APO";
    } 

    public void setFields(String[] fields) {
		int size = fields.length;
			
		if (size < 1)  { return; }
		tsTyp   = fields[0];
		if (size < 2)  { return; }	           
		beznr   = fields[1];
    	if (size < 3)  { return; }	
    	posnr   = fields[2];   
    	if (size < 4)  { return; }	
    	oripos  = fields[3];
        if (size < 5)  { return; }
        artnr   = fields[4];
        if (size < 6)  { return; }
        tnr     = fields[5];
        if (size < 7)  { return; }	
        tnrtrc  = fields[6];
        if (size < 8)  { return; }	
        tnrzu1  = fields[7];
        if (size < 9)  { return; }	
        tnrzu2  = fields[8];
        if (size < 10)  { return; }	
        tnrnat  = fields[9];
        if (size < 11)  { return; }	
        wbsch   = fields[10];
        if (size < 12)  { return; }	
        fregnr  = fields[11];
        if (size < 13)  { return; }	
        verm    = fields[12];
        if (size < 14)  { return; }	        
        eigmas  = fields[13];
        if (size < 15)  { return; }	
        rohmas  = fields[14];
        if (size < 16)  { return; }	
        anmvrf  = fields[15];
        if (size < 17)  { return; }	
        prevrf  = fields[16];
        if (size < 18)  { return; }	
        natvrf  = fields[17];
        if (size < 19)  { return; }
        // CK20120828 asvfr eingefügt
        asvfr	= fields[18];
        if (size < 20)  { return; }
        ubland  = fields[19];
        if (size < 21)  { return; }	
        wmahst  = fields[20];
        if (size < 22)  { return; }	
        ahwert  = fields[21];
        if (size < 23)  { return; }	
        adrkon  = fields[22];   
        if (size < 24)  { return; }	
        gesart   = fields[23];
        if (size < 25)  { return; }		           
        libart   = fields[24];
		if (size < 26)  { return; }		           
		libinc   = fields[25];
		if (size < 27)  { return; }		           
		libort   = fields[26];
		if (size < 28)  { return; }		           
		kzh2o   = fields[27];	
        if (size < 29)  { return; }	
        azvbew  = fields[28];  
        if (size < 30)  { return; }	
        zlbez   = fields[29];   
        if (size < 31)  { return; }	
        ahrico = fields[30];          
    }

	public String teilsatzBilden() {
		
        StringBuffer buff = new StringBuffer();
        
        buff = buff.append(tsTyp);
        buff = buff.append(trenner);
        buff = buff.append(beznr);
        buff = buff.append(trenner);
        buff = buff.append(posnr);
        buff = buff.append(trenner);
        buff = buff.append(oripos);
        buff = buff.append(trenner);
        buff = buff.append(artnr);
        buff = buff.append(trenner);
        buff = buff.append(tnr);
        buff = buff.append(trenner);
        buff = buff.append(tnrtrc);
        buff = buff.append(trenner);
        buff = buff.append(tnrzu1);
        buff = buff.append(trenner);
        buff = buff.append(tnrzu2);
        buff = buff.append(trenner);
        buff = buff.append(tnrnat);
        buff = buff.append(trenner);
        buff = buff.append(wbsch);
        buff = buff.append(trenner);
        buff = buff.append(fregnr);
        buff = buff.append(trenner);
        buff = buff.append(verm);
        buff = buff.append(trenner);
        buff = buff.append(eigmas);
        buff = buff.append(trenner);
        buff = buff.append(rohmas);
        buff = buff.append(trenner);
        buff = buff.append(anmvrf);
        buff = buff.append(trenner);
        buff = buff.append(prevrf);
        buff = buff.append(trenner);
        buff = buff.append(natvrf);
        buff = buff.append(trenner);
        // CK20120828 asvfr eingefügt
        buff = buff.append(asvfr);
        buff = buff.append(trenner);
        buff = buff.append(ubland);
        buff = buff.append(trenner);
        buff = buff.append(wmahst);
        buff = buff.append(trenner);
        buff = buff.append(ahwert);
        buff = buff.append(trenner);
        buff = buff.append(adrkon);
        buff = buff.append(trenner);
        buff = buff.append(gesart);
        buff = buff.append(trenner);
        buff = buff.append(libart);
        buff = buff.append(trenner);
        buff = buff.append(libinc);
        buff = buff.append(trenner);
        buff = buff.append(libort);
        buff = buff.append(trenner);
        buff = buff.append(kzh2o);
        buff = buff.append(trenner);        
        buff = buff.append(azvbew);
        buff = buff.append(trenner);
        buff = buff.append(zlbez);
        buff = buff.append(trenner); 
        buff = buff.append(ahrico);
        buff = buff.append(trenner); 
   
        return new String(buff);
	}        
        
    public boolean isProcedureValid() { //TODO-V21 brauche ich das ???
        if (anmvrf.trim().equals("") && prevrf.trim().equals("") && natvrf.trim().equals("")) {
            return false;
        }
        return true;
    }

	public String getBeznr() {
		return beznr;
	}
	public void setBeznr(String beznr) {
		this.beznr = Utils.checkNull(beznr);
	}

	public String getPosnr() {
		return posnr;
	}
	public void setPosnr(String posnr) {
		this.posnr = Utils.checkNull(posnr);
	}

	public String getOripos() {
		return oripos;
	}
	public void setOripos(String posnr) {
		this.oripos = Utils.checkNull(posnr);
	}
	
	public String getArtnr() {
		return artnr;
	}
	public void setArtnr(String artnr) {
		this.artnr = Utils.checkNull(artnr);
	}

	public String getTnr() {
		return tnr;
	}
	public void setTnr(String tnr) {
		this.tnr = Utils.checkNull(tnr);
	}

	public String getTnrtrc() {
		return tnrtrc;
	}
	public void setTnrtrc(String tnrtrc) {
		this.tnrtrc = Utils.checkNull(tnrtrc);
	}

	public String getTnrzu1() {
		return tnrzu1;
	}
	public void setTnrzu1(String tnrzu1) {
		this.tnrzu1 = Utils.checkNull(tnrzu1);
	}

	public String getTnrzu2() {
		return tnrzu2;
	}
	public void setTnrzu2(String tnrzu2) {
		this.tnrzu2 = Utils.checkNull(tnrzu2);
	}

	public String getTnrnat() {
		return tnrnat;
	}
	public void setTnrnat(String tnrnat) {
		this.tnrnat = Utils.checkNull(tnrnat);
	}

	public String getWbsch() {
		return wbsch;
	}
	public void setWbsch(String wbsch) {
		this.wbsch = Utils.checkNull(wbsch);
	}

	public String getFregnr() {
		return fregnr;
	}
	public void setFregnr(String fregnr) {
		this.fregnr = Utils.checkNull(fregnr);
	}

	public String getVerm() {
		return verm;
	}
	public void setVerm(String verm) {
		this.verm = Utils.checkNull(verm);
	}

	public String getEigmas() {
		return eigmas;
	}
	public void setEigmas(String eigmas) {
		this.eigmas = Utils.checkNull(eigmas);		
	}

	public String getRohmas() {
		return rohmas;
	}
	public void setRohmas(String rohmas) {
		this.rohmas = Utils.checkNull(rohmas);		
	}

	public String getAnmvrf() {
		return anmvrf;
	}
	public void setAnmvrf(String anmvrf) {
		this.anmvrf = Utils.checkNull(anmvrf);
	}

	public String getPrevrf() {
		return prevrf;
	}
	public void setPrevrf(String prevrf) {
		this.prevrf = Utils.checkNull(prevrf);
	}

	public String getNatvrf() {
		return natvrf;
	}
	public void setNatvrf(String natvrf) {
		this.natvrf = Utils.checkNull(natvrf);
	}

	public String getUbland() {
		return ubland;
	}
	public void setUbland(String ubland) {
		this.ubland = Utils.checkNull(ubland);
	}

	public String getWmahst() {
		return wmahst;
	}
	public void setWmahst(String wmahst) {
		this.wmahst = Utils.checkNull(wmahst);
	}

	public String getAhwert() {
		return ahwert;
	}
	public void setAhwert(String ahwert) {
		this.ahwert = Utils.checkNull(ahwert);
	}

	public String getAdrkon() {
		return adrkon;
	}
	public void setAdrkon(String adrkon) {
		this.adrkon = Utils.checkNull(adrkon);
	}

	public String getGesart() {
		return gesart;
	}
	public void setGesart(String gesart) {
		this.gesart = Utils.checkNull(gesart);
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
	
	public String getKzh2o() {
		return kzh2o;
	}
	public void setKzh2o(String value) {
		this.kzh2o = Utils.checkNull(value);
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
	
	
	public String getAhrico() {
		return ahrico;
	}
	public void setAhrico(String value) {
		this.ahrico = Utils.checkNull(value);
	}

	public boolean isEmpty() {
		return Utils.isStringEmpty(artnr)  && Utils.isStringEmpty(tnr) && Utils.isStringEmpty(tnrtrc) &&
		  Utils.isStringEmpty(tnrzu1) && Utils.isStringEmpty(tnrzu2) && Utils.isStringEmpty(tnrnat) &&
		  Utils.isStringEmpty(wbsch) && Utils.isStringEmpty(fregnr) && Utils.isStringEmpty(verm) &&
		  Utils.isStringEmpty(eigmas) && Utils.isStringEmpty(rohmas) && Utils.isStringEmpty(anmvrf) &&
		  Utils.isStringEmpty(prevrf) && Utils.isStringEmpty(natvrf) && Utils.isStringEmpty(ubland) &&
		  Utils.isStringEmpty(wmahst) && Utils.isStringEmpty(ahwert) && Utils.isStringEmpty(adrkon) &&
		  Utils.isStringEmpty(gesart) && Utils.isStringEmpty(azvbew) && Utils.isStringEmpty(zlbez) &&
		  Utils.isStringEmpty(oripos) && Utils.isStringEmpty(libart) && Utils.isStringEmpty(libinc) &&
		  Utils.isStringEmpty(libort) && Utils.isStringEmpty(kzh2o);		
	}

	public String getAsvfr() {
		return asvfr;
	}

	public void setAsvfr(String asvfr) {
		this.asvfr = Utils.checkNull(asvfr);
	}
}

   
   
   
    
   
   





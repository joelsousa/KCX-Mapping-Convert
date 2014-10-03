/*
 * Funktion    : TsATP.java
 * Titel       :
 * Erstellt    : 03.09.2008
 * Author      : Elisabeth Iwaniuk
 * Beschreibung:
 * Anmerkungen : 06.03.2009 - V60 checked
 *
 */

package com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V70;

import java.util.List;
import java.util.Vector;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsATI;

public class TsATP extends Teilsatz {

    private String beznr  = "";            // Bezugsnummer
    private String posnr  = "";            // Positionsnummer
    private String meaest = "";            // Maﬂeinheit
    private String menge  = "";            // Menge
    private String uland  = "";            // Ursprungsland
    private String kzwert = "";            // Kennzeichen Sch‰tzwert
    private String zfnai  = "";            // Zahlungsfall
    private String apgket = "";            // Anpassungskoeffizient
    private String anwrta = "";            // Anteilswert A
    private String anwrtb = "";            // Anteilswert B
    private String anwrtc = "";            // Anteilswert C
    private String anwrtd = "";            // Anteilswert D
    private String wberg1 = "";            // Warenbezeichnung (erste  Erg‰nzug)
    private String wberg2 = "";            // Warenbezeichnung (zweite Erg‰nzug)
    //private String asvfr  = "";            // Verfahren Azsfuhrerstattung
    
  //AK20090407
    private List <TsATI>  atiList  = null;

    public TsATP() {
        tsTyp = "ATP";
    } 
    public void setFields(String[] fields) {   
		int size = fields.length;
		//String ausgabe = "FSS: " + fields[0] + " size = " + size;
		//Utils.log( ausgabe);
			
		
		if (size < 1 ) return;		
        tsTyp = fields[0];
        if (size < 2 ) return;	
        beznr       = fields[1];
        if (size < 3 ) return;
        posnr       = fields[2];
        if (size < 4 ) return;
        meaest      = fields[3];
        if (size < 5 ) return;
        menge       = fields[4];
        if (size < 6 ) return;
        uland       = fields[5];
        if (size < 7 ) return;
        kzwert      = fields[6];
        if (size < 8 ) return;
        zfnai       = fields[7];
        if (size < 9 ) return;
        apgket      = fields[8];
        if (size < 10 ) return;
        anwrta      = fields[9];
        if (size < 11 ) return;
        anwrtb      = fields[10];
        if (size < 12 ) return;
        anwrtc      = fields[11];
        if (size < 13 ) return;
        anwrtd      = fields[12];
        if (size < 14 ) return;
        wberg1      = fields[13];
        if (size < 15 ) return;
        wberg2      = fields[14];
        //if (size < 16 ) return;
        //asvfr       = fields[15];
    }

	public String teilsatzBilden() {
		
        StringBuffer buff = new StringBuffer();
  
        buff = buff.append(tsTyp);
        buff = buff.append(trenner);
        buff = buff.append(beznr);
        buff = buff.append(trenner);
        buff = buff.append(posnr);
        buff = buff.append(trenner);
        buff = buff.append(meaest);
        buff = buff.append(trenner);
        buff = buff.append(menge);
        buff = buff.append(trenner);
        buff = buff.append(uland);
        buff = buff.append(trenner);
        buff = buff.append(kzwert);
        buff = buff.append(trenner);
        buff = buff.append(zfnai);
        buff = buff.append(trenner);
        buff = buff.append(apgket);
        buff = buff.append(trenner); 
        buff = buff.append(anwrta);
        buff = buff.append(trenner);
        buff = buff.append(anwrtb);
        buff = buff.append(trenner);
        buff = buff.append(anwrtc);
        buff = buff.append(trenner);
        buff = buff.append(anwrtd);
        buff = buff.append(trenner);  
        buff = buff.append(wberg1);
        buff = buff.append(trenner);
        buff = buff.append(wberg2);
        buff = buff.append(trenner);
        //buff = buff.append(asvfr);
        //buff = buff.append(trenner);
        
        return new String(buff);
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
	public String getMeaest() {
		return meaest;
	}
	public void setMeaest(String meaest) {
		this.meaest = Utils.checkNull(meaest);
	}
	public String getMenge() {
		return menge;
	}
	public void setMenge(String menge) {
		this.menge = Utils.checkNull(menge);
	}
	public String getUland() {
		return uland;
	}
	public void setUland(String uland) {
		this.uland = Utils.checkNull(uland);
	}
	public String getKzwert() {
		return kzwert;
	}
	public void setKzwert(String kzwert) {
		this.kzwert = Utils.checkNull(kzwert);
	}
	public String getZfnai() {
		return zfnai;
	}
	public void setZfnai(String zfnai) {
		this.zfnai = Utils.checkNull(zfnai);
	}
	public String getApgket() {
		return apgket;
	}
	public void setApgket(String apgket) {
		this.apgket = Utils.checkNull(apgket);
	}
	public String getAnwrta() {
		return anwrta;
	}
	public void setAnwrta(String anwrta) {
		this.anwrta = Utils.checkNull(anwrta);
	}
	public String getAnwrtb() {
		return anwrtb;
	}
	public void setAnwrtb(String anwrtb) {
		this.anwrtb = Utils.checkNull(anwrtb);
	}
	public String getAnwrtc() {
		return anwrtc;
	}
	public void setAnwrtc(String anwrtc) {
		this.anwrtc = Utils.checkNull(anwrtc);
	}
	public String getAnwrtd() {
		return anwrtd;
	}
	public void setAnwrtd(String anwrtd) {
		this.anwrtd = Utils.checkNull(anwrtd);
	}
	public String getWberg1() {
		return wberg1;
	}
	public void setWberg1(String wberg1) {
		this.wberg1 = Utils.checkNull(wberg1);
	}
	public String getWberg2() {
		return wberg2;
	}
	public void setWberg2(String wberg2) {
		this.wberg2 = Utils.checkNull(wberg2);
	}
	/*
	public String getAsvfr() {
		return asvfr;
	}
	public void setAsvfr(String asvfr) {
		this.asvfr = Utils.checkNull(asvfr);
	}
	*/
	public boolean isEmpty() {
		if ( Utils.isStringEmpty(meaest)  && Utils.isStringEmpty(menge) && Utils.isStringEmpty(uland)
		  && Utils.isStringEmpty(kzwert) && Utils.isStringEmpty(zfnai) && Utils.isStringEmpty(apgket)
		  && Utils.isStringEmpty(anwrta) && Utils.isStringEmpty(anwrtb) && Utils.isStringEmpty(anwrtc)
		  && Utils.isStringEmpty(anwrtd)  && Utils.isStringEmpty(wberg1) && Utils.isStringEmpty(wberg2))		
			return true;
		else
			return false;
	}
	
	//AK20090407
	public List<TsATI> getAtiList() {
		return atiList;
	
	}
	public void setAtiList(List<TsATI> atiList) {
		this.atiList = atiList;
	}
	
	public void addAtiList(TsATI ati){
		if ( ati == null) return; 
		if(atiList==null){
			atiList=new Vector<TsATI>();
		}
		atiList.add(ati);
	}
}

  


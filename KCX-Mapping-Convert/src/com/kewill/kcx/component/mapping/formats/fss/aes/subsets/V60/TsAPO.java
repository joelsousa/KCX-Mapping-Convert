/*
 * Funktion    : TsAPO.java
 * Titel       :
 * Erstellt    : 03.09.2008
 * Author      : Elisabeth iwaniuk
 * Beschreibung:
 * Anmerkungen : 06.03.2009 - V60 checked
 */

package com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.countries.common.TIN;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.MsgExpEntPos;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.Statistic;


public class TsAPO  extends Teilsatz {
	
    private String beznr  = "";       // Bezugsnummer
    private String posnr  = "";       // Positionsnummer
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
    private String ubland = "";       // Ursprungsbundesland
    private String wmahst = "";       // AH-statistische Menge
    private String ahwert = "";       // AH-statistische Wert
    private String kdnrem = "";       // Kundennummer des Empfängers                        //26.08.08
    private String tinem  = "";       // TIN des Empfängers                                 //26.08.08
    private String azvbew = "";       // Bewilligungsnummer im Beendigungsanteil            //26.08.08
    private String zlbez  = "";       // Bezugsnummer im BE-Anteil ZL                       //26.08.08

    public TsAPO() {
        tsTyp = "APO";
    } 

    public void setFields(String[] fields)
    {
		int size = fields.length;
		String ausgabe = "FSS: "+fields[0]+" size = "+ size;
		//Utils.log( ausgabe);
			
		if (size < 1 ) return;
		tsTyp   = fields[0];
		if (size < 2 ) return;		           
		beznr   = fields[1];
    	if (size < 3 ) return;	
    	posnr   = fields[2];
        if (size < 4 ) return;	
        artnr   = fields[3];
        if (size < 5 ) return;	
        tnr     = fields[4];
        if (size < 6 ) return;	
        tnrtrc  = fields[5];
        if (size < 7 ) return;	
        tnrzu1  = fields[6];
        if (size < 8 ) return;	
        tnrzu2  = fields[7];
        if (size < 9 ) return;	
        tnrnat  = fields[8];
        if (size < 10 ) return;	
        wbsch   = fields[9];
        if (size < 11 ) return;	
        fregnr  = fields[10];
        if (size < 12 ) return;	
        verm    = fields[11];
        if (size < 13 ) return;	        
        eigmas  = fields[12];
        if (size < 14 ) return;	
        rohmas  = fields[13];
        if (size < 15 ) return;	
        anmvrf  = fields[14];
        if (size < 16 ) return;	
        prevrf  = fields[15];
        if (size < 17 ) return;	
        natvrf  = fields[16];
        if (size < 18 ) return;	
        ubland  = fields[17];
        if (size < 19 ) return;	
        wmahst  = fields[18];
        if (size < 20 ) return;	
        ahwert  = fields[19];
        if (size < 21 ) return;	
        kdnrem  = fields[20];   
        if (size < 22 ) return;	
        tinem   = fields[21];
        if (size < 23 ) return;	
        azvbew  = fields[22];  
        if (size < 24 ) return;	
        zlbez   = fields[23];                                                         //26.08.08
    }

	public String teilsatzBilden() {
		
        StringBuffer buff = new StringBuffer();
        
        buff = buff.append(tsTyp);
        buff = buff.append(trenner);
        buff = buff.append(beznr);
        buff = buff.append(trenner);
        buff = buff.append(posnr);
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
        buff = buff.append(ubland);
        buff = buff.append(trenner);
        buff = buff.append(wmahst);
        buff = buff.append(trenner);
        buff = buff.append(ahwert);
        buff = buff.append(trenner);
        buff = buff.append(kdnrem);
        buff = buff.append(trenner);
        buff = buff.append(tinem);
        buff = buff.append(trenner);
        buff = buff.append(azvbew);
        buff = buff.append(trenner);
        buff = buff.append(zlbez);
        buff = buff.append(trenner);
   
        return new String(buff);
	}        
        
    public boolean isProcedureValid() {
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
Utils.log("setEigmas eigmas:" + eigmas);		
	}

	public String getRohmas() {
		return rohmas;
	}

	public void setRohmas(String rohmas) {
		this.rohmas = Utils.checkNull(rohmas);
Utils.log("setRohmas rohmas:" + rohmas);		
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

	public String getKdnrem() {
		return kdnrem;
	}

	public void setKdnrem(String kdnrem) {
		this.kdnrem = Utils.checkNull(kdnrem);
	}

	public String getTinem() {
		return tinem;
	}

	public void setTinem(String tinem) {
		this.tinem = Utils.checkNull(tinem);
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

	public void setApoSubset(MsgExpEntPos msgExpEntPos, String aBeznr ) {
		Statistic statistic = null;
		TIN tin 			= null;
		
		if (msgExpEntPos == null) return;
		if (Utils.isStringEmpty(aBeznr)) return;
		if (Utils.isStringEmpty(msgExpEntPos.getItemNumber())) return;
		
		this.setBeznr(aBeznr);
		this.setPosnr(msgExpEntPos.getItemNumber());  		

		
		this.setUbland(msgExpEntPos.getOriginFederalState());  

		statistic = msgExpEntPos.getStatistic();

		if (statistic  != null) {
			this.setWmahst(statistic.getAdditionalUnitConfirmation());  
			this.setAhwert(statistic.getStatisticalValueConfirmation()); 
		}

		tin = msgExpEntPos.getConsigneeTIN() ;
		if (tin != null) {
			this.setKdnrem(tin.getCustomerIdentifier());  
			this.setTinem(tin.getTIN());
		}
	}

	public boolean isEmpty() {
		if ( Utils.isStringEmpty(artnr)  && Utils.isStringEmpty(tnr) && Utils.isStringEmpty(tnrtrc)
		  && Utils.isStringEmpty(tnrzu1) && Utils.isStringEmpty(tnrzu2) && Utils.isStringEmpty(tnrnat)
		  && Utils.isStringEmpty(wbsch) && Utils.isStringEmpty(fregnr) && Utils.isStringEmpty(verm)
		  && Utils.isStringEmpty(eigmas) && Utils.isStringEmpty(eigmas) && Utils.isStringEmpty(anmvrf)
		  && Utils.isStringEmpty(prevrf) && Utils.isStringEmpty(natvrf) && Utils.isStringEmpty(ubland)
		  && Utils.isStringEmpty(wmahst) && Utils.isStringEmpty(ahwert) && Utils.isStringEmpty(kdnrem)
		  && Utils.isStringEmpty(tinem) && Utils.isStringEmpty(azvbew) && Utils.isStringEmpty(zlbez)
		  )
			return true;
		else
			return false;
	}
		
}

   
   
   
    
   
   





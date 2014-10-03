package com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.V70;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: NCTS OUT<br>
 * Created		: 12.11.2012<br>
 * Description	: Definition of subset TsVAN.
 * 
 * @author iwaniuk
 * @version 7.0.00
 */

public class TsVAN extends Teilsatz {
    
	private String beznr  	= "";      // Bezugsnummer 
    private String bewnr 	= "";      // Bewilligungsnummer 
    private String anmart   = "";      // Anmeldeart
    private String ldvs  	= "";      // Versendungs-/Ausfuhrland
    private String ldbe 	= "";      // Bestimmungsland
    private String abgdst 	= "";      // Abgangszollstelle 
    private String bedst 	= "";      // Bestimmungszollstelle 
    private String wgdat  	= "";      // Gestellungsdatum
    private String kzzv 	= "";      // KZ für vereinfachtes Verfahren 0 = Nein, 1 = Ja
    private String gsroh  	= "";      // Gesamt-Rohmasse
    //V70: private String stvnam 	= "";      // Name des Stellvertreters
    //V70: private String stvstl  	= "";      // Dienststellung des Stellvertreters
    private String route 	= "";      // Vorgeschlagene Route  
    private String kzraus  	= "";      // KZ Aussetzung der verbindlichen Route bewilligt   
    private String kztyd 	= "";      // Verschlüsse sind Tydenseals
    private String kzstock  = "";      // Verwendung des hinterlegten Tydensealstock
    private String vsanz  	= "";      // Anzahl verwendeter Verschlüsse
    private String vsart  	= "";      // Verschlussart
    private String bfabkz  	= "";      // Kennzeichen des Beförderungs-mittels beim Abgang 
    private String bfabld  	= "";      // Nationalität des Beförderungsmittels beim Abgang
    private String bfvkzg  	= "";   //neu V70, vreschoben vom Ende hierher
    private String bfgrcd  	= "";      // Art des Beförderungsmittels an der Grenze
    private String bfgrkz  	= "";      // Kennzeichen des Beförderungsmittels an der Grenze
    private String bfgrld  	= "";      // Nationalität des Beförderungsmittels an der Grenze, ISO-Code
    private String kdnrve  	= "";   //neu V70
    private String tinve  	= "";      // EORI des Versenders    
    private String nlve  	= "";   //neu V70
    private String dtzove  	= "";   //neu V70
    private String kdnrem  	= "";   //neu V70
    private String tinem  	= "";      // EORI des Empfängers
    private String nlem  	= "";      // Kundenummer der Empfängers
    private String dtzoem  	= "";   //neu V70
    private String kdnrze  	= "";      // Kundenummer der zugelassenen Empfängers
    private String tinze  	= "";      // TIN des zugelassenen Empfängers    
    private String anzcol  	= "";      // Anzahl Packstücke
    private String uebort  	= "";      // Übergabeort der Waren
    private String etnadr  	= "";      // ETN-Adresse des zugelassenen Empfängers 
    private String sb  		= "";      // Sachbearbeiterkennung   
    private String kdnrhp  	= "";      // Kundenummer Hauptverpflichte-ten
    private String tinhp  	= "";      // TIN des Hauptverpflichteten
    private String nlhp  	= "";   //neu V70
    private String dtzohp  	= "";   //neu V70
    private String carnhp  	= "";   
    private String kzhpbf  	= "";   //neu V70
    private String quelkz  	= "";      // kennzeichnet das Vorverfahren, in dem der Vorgang erfasst wurde    
    private String kzsusi  	= "";      // Kennzeichen SumA-Sicherheit
    private String zlbez  	= "";      // Bezugsnummer der ZL-Auslagerung in ZABIS
    private String avbez  	= "";      // Bezugsnummer der AV/UV-Auslagerung in ZABIS    
   

    public TsVAN() {
        tsTyp = "VAN";
    }
    
    public String teilsatzBilden() {
        StringBuffer buff = new StringBuffer();
          
        buff.append(tsTyp);
        buff.append(trenner);        
        buff.append(beznr);
        buff.append(trenner);	
        buff.append(bewnr);
        buff.append(trenner);	
        buff.append(anmart);
        buff.append(trenner);	
        buff.append(ldvs);
        buff.append(trenner);	
        buff.append(ldbe);
        buff.append(trenner);	
        buff.append(abgdst);
        buff.append(trenner);	
        buff.append(bedst);
        buff.append(trenner);	
        buff.append(wgdat);
        buff.append(trenner);	
        buff.append(kzzv);
        buff.append(trenner);	
        buff.append(gsroh);
        buff.append(trenner);	        
        buff.append(route);
        buff.append(trenner);	
        buff.append(kzraus);
        buff.append(trenner);	
        buff.append(kztyd);
        buff.append(trenner);	
        buff.append(kzstock);
        buff.append(trenner);	
        buff.append(vsanz);
        buff.append(trenner);	
        buff.append(vsart);
        buff.append(trenner);	
        buff.append(bfabkz);
        buff.append(trenner);	
        buff.append(bfabld);
        buff.append(trenner);
        buff.append(bfvkzg);        
        buff.append(trenner);	
        buff.append(bfgrcd);
        buff.append(trenner);	
        buff.append(bfgrkz);
        buff.append(trenner);	
        buff.append(bfgrld);
        buff.append(trenner);	       
        buff.append(kdnrve);
        buff.append(trenner);	
        buff.append(tinve);
        buff.append(trenner);	
        buff.append(nlve);
        buff.append(trenner);
        buff.append(dtzove);
        buff.append(trenner);
        buff.append(kdnrem);
        buff.append(trenner);
        buff.append(tinem);
        buff.append(trenner);	
        buff.append(nlem);
        buff.append(trenner);
        buff.append(dtzoem);
        buff.append(trenner);
        buff.append(kdnrze);
        buff.append(trenner);
        buff.append(tinze);
        buff.append(trenner);	       
        buff.append(anzcol);
        buff.append(trenner);	
        buff.append(uebort);
        buff.append(trenner);	
        buff.append(etnadr);
        buff.append(trenner);	
        buff.append(sb);
        buff.append(trenner);	
        buff.append(kdnrhp);
        buff.append(trenner);	
        buff.append(tinhp);
        buff.append(trenner);	
        buff.append(nlhp);
        buff.append(trenner);	
        buff.append(dtzohp);
        buff.append(trenner);
        buff.append(carnhp);
        buff.append(trenner);
        buff.append(kzhpbf);
        buff.append(trenner);
        buff.append(quelkz);
        buff.append(trenner);	       
        buff.append(kzsusi);
        buff.append(trenner);	
        buff.append(zlbez);
        buff.append(trenner);	
        buff.append(avbez);
        buff.append(trenner);	
        	       
    	return buff.toString();
    }
	
	public String getBeznr() {
		return beznr;
	}

	public void setBeznr(String beznr) {
		this.beznr = Utils.checkNull(beznr);
	}

	public String getBewnr() {
		return bewnr;
	}

	public void setBewnr(String bewnr) {
		this.bewnr = Utils.checkNull(bewnr);
	}

	public String getAnmart() {
		return anmart;
	}

	public void setAnmart(String anmart) {
		this.anmart = Utils.checkNull(anmart);
	}

	public String getLdvs() {
		return ldvs;
	}

	public void setLdvs(String ldvs) {
		this.ldvs = Utils.checkNull(ldvs);
	}

	public String getLdbe() {
		return ldbe;
	}

	public void setLdbe(String ldbe) {
		this.ldbe = Utils.checkNull(ldbe);
	}

	public String getAbgdst() {
		return abgdst;
	}

	public void setAbgdst(String abgdst) {
		this.abgdst = Utils.checkNull(abgdst);
	}

	public String getBedst() {
		return bedst;
	}

	public void setBedst(String bedst) {
		this.bedst = Utils.checkNull(bedst);
	}

	public String getWgdat() {
		return wgdat;
	}

	public void setWgdat(String wgdat) {
		this.wgdat = Utils.checkNull(wgdat);
	}

	public String getKzzv() {
		return kzzv;
	}

	public void setKzzv(String kzzv) {
		this.kzzv = Utils.checkNull(kzzv);
	}

	public String getGsroh() {
		return gsroh;
	}

	public void setGsroh(String gsroh) {
		this.gsroh = Utils.checkNull(gsroh);
	}	

	public String getRoute() {
		return route;
	}

	public void setRoute(String route) {
		this.route = Utils.checkNull(route);
	}

	public String getKzraus() {
		return kzraus;
	}

	public void setKzraus(String kzraus) {
		this.kzraus = Utils.checkNull(kzraus);
	}

	public String getKztyd() {
		return kztyd;
	}

	public void setKztyd(String kztyd) {
		this.kztyd = Utils.checkNull(kztyd);
	}

	public String getKzstock() {
		return kzstock;
	}

	public void setKzstock(String kzstock) {
		this.kzstock = Utils.checkNull(kzstock);
	}

	public String getVsanz() {
		return vsanz;
	}

	public void setVsanz(String vsanz) {
		this.vsanz = Utils.checkNull(vsanz);
	}

	public String getVsart() {
		return vsart;
	}

	public void setVsart(String vsart) {
		this.vsart = Utils.checkNull(vsart);
	}

	public String getBfabkz() {
		return bfabkz;
	}

	public void setBfabkz(String bfabkz) {
		this.bfabkz = Utils.checkNull(bfabkz);
	}

	public String getBfabld() {
		return bfabld;
	}

	public void setBfabld(String bfabld) {
		this.bfabld = Utils.checkNull(bfabld);
	}

	public String getBfgrcd() {
		return bfgrcd;
	}

	public void setBfgrcd(String bfgrcd) {
		this.bfgrcd = Utils.checkNull(bfgrcd);
	}

	public String getBfgrkz() {
		return bfgrkz;
	}

	public void setBfgrkz(String bfgrkz) {
		this.bfgrkz = Utils.checkNull(bfgrkz);
	}

	public String getBfgrld() {
		return bfgrld;
	}

	public void setBfgrld(String bfgrld) {
		this.bfgrld = Utils.checkNull(bfgrld);
	}

	public String getTinve() {
		return tinve;
	}

	public void setTinve(String tinve) {
		this.tinve = Utils.checkNull(tinve);
	}

	public String getKdnrve() {
		return kdnrve;
	}

	public void setKdnrve(String kdnrve) {
		this.kdnrve = Utils.checkNull(kdnrve);
	}

	public String getTinem() {
		return tinem;
	}

	public void setTinem(String tinem) {
		this.tinem = Utils.checkNull(tinem);
	}

	public String getKdnrem() {
		return kdnrem;
	}

	public void setKdnrem(String kdnrem) {
		this.kdnrem = Utils.checkNull(kdnrem);
	}

	public String getTinze() {
		return tinze;
	}

	public void setTinze(String tinze) {
		this.tinze = Utils.checkNull(tinze);
	}

	public String getKdnrze() {
		return kdnrze;
	}

	public void setKdnrze(String kdnrze) {
		this.kdnrze = Utils.checkNull(kdnrze);
	}

	public String getAnzcol() {
		return anzcol;
	}

	public void setAnzcol(String anzcol) {
		this.anzcol = Utils.checkNull(anzcol);
	}

	public String getUebort() {
		return uebort;
	}

	public void setUebort(String uebort) {
		this.uebort = Utils.checkNull(uebort);
	}

	public String getEtnadr() {
		return etnadr;
	}

	public void setEtnadr(String etnadr) {
		this.etnadr = Utils.checkNull(etnadr);
	}

	public String getSb() {
		return sb;
	}

	public void setSb(String sb) {
		this.sb = Utils.checkNull(sb);
	}

	public String getTinhp() {
		return tinhp;
	}

	public void setTinhp(String tinhp) {
		this.tinhp = Utils.checkNull(tinhp);
	}

	public String getKdnrhp() {
		return kdnrhp;
	}

	public void setKdnrhp(String kdnrhp) {
		this.kdnrhp = Utils.checkNull(kdnrhp);
	}

	public String getQuelkz() {
		return quelkz;
	}

	public void setQuelkz(String quelkz) {
		this.quelkz = Utils.checkNull(quelkz);
	}

	public String getDtzohp() {
		return dtzohp;
	}

	public void setDtzohp(String dtzohp) {
		this.dtzohp = Utils.checkNull(dtzohp);
	}

	public String getKzsusi() {
		return kzsusi;
	}

	public void setKzsusi(String kzsusi) {
		this.kzsusi = Utils.checkNull(kzsusi);
	}

	public String getZlbez() {
		return zlbez;
	}

	public void setZlbez(String zlbez) {
		this.zlbez = Utils.checkNull(zlbez);
	}

	public String getAvbez() {
		return avbez;
	}

	public void setAvbez(String avbez) {
		this.avbez = Utils.checkNull(avbez);
	}

	public String getCarnhp() {
		return carnhp;
	}

	public void setCarnhp(String carnhp) {
		this.carnhp = Utils.checkNull(carnhp);
	}

	public String getBfvkzg() {
		return bfvkzg;
	}

	public void setBfvkzg(String value) {
		this.bfvkzg = Utils.checkNull(value);
	}
	
	
	// setFields will not be used because VAN is a specific subset of the message VAN
	// and this FSS-message is created by KCX to be sent to Zabis
	// only messages from Zabis have to write out this method

	public String getNlve() {
		return nlve;
	}

	public void setNlve(String value) {
		this.nlve = Utils.checkNull(value);
	}

	public String getDtzove() {
		return dtzove;
	}

	public void setDtzove(String value) {
		this.dtzove = Utils.checkNull(value);
	}

	public String getNlem() {
		return nlem;
	}

	public void setNlem(String value) {
		this.nlem = Utils.checkNull(value);
	}

	public String getDtzoem() {
		return dtzoem;
	}

	public void setDtzoem(String value) {
		this.dtzoem = Utils.checkNull(value);
	}

	public String getNlhp() {
		return nlhp;
	}

	public void setNlhp(String value) {
		this.nlhp = Utils.checkNull(value);
	}

	public String getKzhpbf() {
		return kzhpbf;
	}

	public void setKzhpbf(String value) {
		this.kzhpbf = Utils.checkNull(value);
	}

	public void setFields(String[] fields) {
		Utils.log("TsVAN.setFields not created");
	}
	
	public boolean isEmpty() {
		if (Utils.isStringEmpty(bewnr) && Utils.isStringEmpty(anmart) &&
			Utils.isStringEmpty(ldvs) && Utils.isStringEmpty(ldbe) &&
			Utils.isStringEmpty(abgdst) && Utils.isStringEmpty(bedst) &&
			Utils.isStringEmpty(gsroh) && 
			Utils.isStringEmpty(bfabkz) && 
			Utils.isStringEmpty(bfgrcd) && Utils.isStringEmpty(bfgrkz) &&			
			Utils.isStringEmpty(bfvkzg)) {
			return true;
		}
		return false;
	}
		
}




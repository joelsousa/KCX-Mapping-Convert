package com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62;


import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/*
* Funktion    : TsVAN.java
* Titel       :
* Erstellt    : 02.09.2010
* Author      : Kewill / Christine Kron
* 
* Description : subset VAN refers to ve-fss-62.doc 

* Parameter   : 
* Return      : keine

*
* Changes:
* -----------
* Author      :  
* Date        :  
* Kennzeichen :
* Description :
*             
*
*/


/**
 * Modul		: TsVAN<br>
 * Date			: 2.9.2010<br>
 * Description	: Definition of subset TsVAN.
 * 
 * @author Christine Kron
 * @version 1.0.00
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
    private String stvnam 	= "";      // Name des Stellvertreters
    private String stvstl  	= "";      // Dienststellung des Stellvertreters
    private String route 	= "";      // Vorgeschlagene Route  
    private String kzraus  	= "";      // KZ Aussetzung der verbindlichen Route bewilligt   
    private String kztyd 	= "";      // Verschlüsse sind Tydenseals
    private String kzstock  = "";      // Verwendung des hinterlegten Tydensealstock
    private String vsanz  	= "";      // Anzahl verwendeter Verschlüsse
    private String vsart  	= "";      // Verschlussart
    private String bfabkz  	= "";      // Kennzeichen des Beförderungs-mittels beim Abgang 
    private String bfabld  	= "";      // Nationalität des Beförderungsmittels beim Abgang
    private String bfgrcd  	= "";      // Art des Beförderungsmittels an der Grenze
    private String bfgrkz  	= "";      // Kennzeichen des Beförderungsmittels an der Grenze
    private String bfgrld  	= "";      // Nationalität des Beförderungsmittels an der Grenze, ISO-Code
    private String tinve  	= "";      // TIN des Versenders
    private String kdnrve  	= "";      // Kundenummer der Versenders
    private String tinem  	= "";      // TIN des Empfängers
    private String kdnrem  	= "";      // Kundenummer der Empfängers
    private String tinze  	= "";      // TIN des zugelassenen Empfängers
    private String kdnrze  	= "";      // Kundenummer der zugelassenen Empfängers
    private String anzcol  	= "";      // Anzahl Packstücke
    private String uebort  	= "";      // Übergabeort der Waren
    private String etnadr  	= "";      // ETN-Adresse des zugelassenen Empfängers 
    private String sb  		= "";      // Sachbearbeiterkennung
    private String tinhp  	= "";      // TIN des Hauptverpflichteten
    private String kdnrhp  	= "";      // Kundenummer Hauptverpflichte-ten
    private String quelkz  	= "";      // kennzeichnet das Vorverfahren, in dem der Vorgang erfasst wurde
    private String dtzohp  	= "";      // Kennzeichen dt. Zollnummer 0 = TIN keine dt.Zollnummer 1 = deutsch
    private String kzsusi  	= "";      // Kennzeichen SumA-Sicherheit
    private String zlbez  	= "";      // Bezugsnummer der ZL-Auslagerung in ZABIS
    private String avbez  	= "";      // Bezugsnummer der AV/UV-Auslagerung in ZABIS
    private String carnhp  	= "";      // Hauptverpflichteter (ID Carnetin-haber)
    private String bfvkzg  	= "";      // Verkehrszweig zum Beförde-rungsmittel an der Grenze
    

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
        buff.append(stvnam);
        buff.append(trenner);	
        buff.append(stvstl);
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
        buff.append(bfgrcd);
        buff.append(trenner);	
        buff.append(bfgrkz);
        buff.append(trenner);	
        buff.append(bfgrld);
        buff.append(trenner);	
        buff.append(tinve);
        buff.append(trenner);	
        buff.append(kdnrve);
        buff.append(trenner);	
        buff.append(tinem);
        buff.append(trenner);	
        buff.append(kdnrem);
        buff.append(trenner);	
        buff.append(tinze);
        buff.append(trenner);	
        buff.append(kdnrze);
        buff.append(trenner);	
        buff.append(anzcol);
        buff.append(trenner);	
        buff.append(uebort);
        buff.append(trenner);	
        buff.append(etnadr);
        buff.append(trenner);	
        buff.append(sb);
        buff.append(trenner);	
        buff.append(tinhp);
        buff.append(trenner);	
        buff.append(kdnrhp);
        buff.append(trenner);	
        buff.append(quelkz);
        buff.append(trenner);	
        buff.append(dtzohp);
        buff.append(trenner);	
        buff.append(kzsusi);
        buff.append(trenner);	
        buff.append(zlbez);
        buff.append(trenner);	
        buff.append(avbez);
        buff.append(trenner);	
        buff.append(carnhp);
        buff.append(trenner);	
        buff.append(bfvkzg);
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

	public String getStvnam() {
		return stvnam;
	}

	public void setStvnam(String stvnam) {
		this.stvnam = Utils.checkNull(stvnam);
	}

	public String getStvstl() {
		return stvstl;
	}

	public void setStvstl(String stvstl) {
		this.stvstl = Utils.checkNull(stvstl);
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

	public void setBfvkzg(String bfvkzg) {
		this.bfvkzg = Utils.checkNull(bfvkzg);
	}

	// setFields will not be used because VAN is a specific subset of the message VAN
	// and this FSS-message is created by KCX to be sent to Zabis
	// only messages from Zabis have to write out this method

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




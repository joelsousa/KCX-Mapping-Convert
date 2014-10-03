package com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/*
* Funktion    : TsEDA.java
* Titel       :
* Erstellt    : 03.09.2008
* Author      : CSF GmbH / Alfred Krzoska
* Beschreibung: 
* Anmerkungen : 
* Parameter   : 
* Rückgabe    : keine
* Aufruf      : 
*
* Änderungen:
* -----------
* Author      :
* Datum       :
* Kennzeichen :
* Beschreibung:
* Anmerkungen :
* Parameter   :
*
*/

public class TsEDA extends Teilsatz {
  
    private String beznr   = "";      // Bezugsnummer
    private String bewnr   = "";      // Bewiligungsnummer
    private String ldocde  = "";      // Codierter Ladeort
    private String beostr  = "";      // Strasse und Hausnummer des Ladeorte
    private String beoort  = "";      // Ort zum Ladeort
    private String beoplz  = "";      // PLZ zum Ladeort
    private String beozus  = "";      // Zusatz zum Ladeort
    private String artaus  = "";      // Art der Anmeldung zur Ausfuhr
    private String artueb  = "";      // Art der Anmeldung zur Überführung
    private String ldbe    = "";      // Bestimmungsland
    private String conkz   = "";      // Kennzeichen "Container"
    private String fregnr  = "";      // Registriernummer Fremdsystem
    private String flhcde  = "";      // Flughafencode
    private String extdst  = "";      // Ausgangsdienststelle
    private String kzanau  = "";      // Anmelder=Ausführer
    private String kdnrau  = "";      // Kundennummer des Ausführer
    private String tinau   = "";      // TIN des Ausführers
    private String dtzoau  = "";      // TIN des Ausführers ist dt. Zollnummer
    private String kdnrva  = "";      // Kundennummer des Vertreters des Anmelders
    private String tinva   = "";      // TIN des Vertreters des Anmelders
    private String dtzova  = "";      // TIN des Vertreters des Anmelders ist dt. Zollnummer
    private String etnva   = "";      // ETN-Adresse des Vertreters des Anmelders
    private String kdnrsu  = "";      // Kundennummer des Subunternehmers
    private String tinsu   = "";      // TIN des Subunternehmers
    private String dtzosu  = "";      // TIN des Subunternehmers ist dt. Zollnummer
    private String etnsu   = "";      // ETN-Adresse des Subunternehmers
    private String anmdat  = "";      // Zeitpunkt der Anmeldung
    private String gststr  = "";      // Beginn der Gestellungsfrist
    private String gstend  = "";      // Ende   der Gestellungsfrist
    private String verm    = "";      // Vermerk
    //private static final String bcktax  = "0";     // Kennzeichen Ausfuhrerstattung konstant wg. FSS Abwärtskompatibilitaet  
    private String kztyd   = "";      // Verschlüsse sind Tydenseals
    private String kzstock = "";      // Verwendung des hinterlegten Tydensealstock
    private String anzve   = "";      // Anzahl verwendeter Verschlüsse
    private String vsart   = "";      // Verschlussart
    private String ldve    = "";      // Ausfuhrland
    private String gsroh   = "";      // Gesamtrohmasse
    private String inddat  = "";      // Zeitpunkt der Vorankündigung

    public TsEDA() {
        tsTyp = "EDA";
    }
    
	public void setFields(String[] fields) {
		int size = fields.length;
		String ausgabe = "FSS: "+fields[0]+" size = "+ size;
		//Utils.log( ausgabe);
	
		
		if (size < 1 ) return;
		tsTyp   = fields[0];
		if (size < 2 ) return;
		beznr   = fields[1];		
		if (size < 3 ) return;
		bewnr   = fields[2];
		if (size < 4 ) return;
		ldocde  = fields[3];
		if (size < 5 ) return;
		beostr  = fields[4];
		if (size < 6) return;
		beoort  = fields[5];
		if (size < 7 ) return;
		beoplz  = fields[6];
		if (size < 8) return;
		beozus  = fields[7];
		if (size < 9) return;
		artaus  = fields[8];
		if (size < 10) return;
		artueb  = fields[9];
		if (size < 11 ) return;
		ldbe    = fields[10];
		if (size < 12 ) return;
		conkz   = fields[11];
		if (size < 13 ) return;
		fregnr  = fields[12];
		if (size < 14 ) return;
		flhcde  = fields[13];
		if (size < 15 ) return;
		extdst  = fields[14];
		if (size < 16 ) return;
		kzanau  = fields[15];
		if (size < 17 ) return;
		kdnrau  = fields[16];
		if (size < 18 ) return;
		tinau   = fields[17];
		if (size < 19 ) return;
		dtzoau  = fields[18];
		if (size < 20 ) return;
		kdnrva  = fields[19];
		if (size < 21 ) return;
		tinva   = fields[20];
		if (size < 22 ) return;
		dtzova  = fields[21];
		if (size < 23 ) return;
		etnva   = fields[22];
		if (size < 24 ) return;
		kdnrsu  = fields[23];
		if (size < 25 ) return;
		tinsu   = fields[24];
		if (size < 26 ) return;
		dtzosu  = fields[25];
		if (size < 27 ) return;
		etnsu   = fields[26];
		if (size < 28 ) return;
		anmdat  = fields[27];
		if (size < 29 ) return;
		gststr  = fields[28];
		if (size < 30 ) return;
		gstend  = fields[29];
		if (size < 31 ) return;
		verm    = fields[30];
		if (size < 32 ) return;
		kztyd   = fields[31];
		if (size < 33 ) return;
		kzstock = fields[32];
		if (size < 34 ) return;
		anzve   = fields[33];
		if (size < 35 ) return;
		vsart   = fields[34];
		if (size < 36 ) return;
		ldve    = fields[35];
		if (size < 37 ) return;
		gsroh   = fields[36];
		if (size < 38 ) return;
		inddat  = fields[37];				
	}


    public String teilsatzBilden() {
        StringBuffer buff = new StringBuffer();

        buff.append(tsTyp);
        buff.append(trenner);
        buff.append(beznr);
        buff.append(trenner);
        buff.append(bewnr);
        buff.append(trenner);
        buff.append(ldocde);
        buff.append(trenner);
        buff.append(beostr);
        buff.append(trenner);
        buff.append(beoort);
        buff.append(trenner);
        buff.append(beoplz);
        buff.append(trenner);
        buff.append(beozus);
        buff.append(trenner);
        buff.append(artaus);
        buff.append(trenner);
        buff.append(artueb);
        buff.append(trenner);
        buff.append(ldbe);
        buff.append(trenner);
        buff.append(conkz);
        buff.append(trenner);
        buff.append(fregnr);
        buff.append(trenner);
        buff.append(flhcde);
        buff.append(trenner);
        buff.append(extdst);
        buff.append(trenner);
        buff.append(kzanau);
        buff.append(trenner);
        buff.append(kdnrau);
        buff.append(trenner);
        buff.append(tinau);
        buff.append(trenner);
        buff.append(dtzoau);
        buff.append(trenner);
        buff.append(kdnrva);
        buff.append(trenner);
        buff.append(tinva);
        buff.append(trenner);
        buff.append(dtzova);
        buff.append(trenner);
        buff.append(etnva);
        buff.append(trenner);
        buff.append(kdnrsu);
        buff.append(trenner);
        buff.append(tinsu);
        buff.append(trenner);
        buff.append(dtzosu);
        buff.append(trenner);
        buff.append(etnsu);
        buff.append(trenner);
        buff.append(anmdat);
        buff.append(trenner);
        buff.append(gststr);
        buff.append(trenner);
        buff.append(gstend);
        buff.append(trenner);
        buff.append(verm);
        buff.append(trenner);
       // buff.append(bcktax);
       // buff.append(trenner);
        buff.append(kztyd);
        buff.append(trenner);
        buff.append(kzstock);
        buff.append(trenner);
        buff.append(anzve);
        buff.append(trenner);
        buff.append(vsart);
        buff.append(trenner);
        buff.append(ldve);
        buff.append(trenner);
        buff.append(gsroh);
        buff.append(trenner);
        buff.append(inddat);
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

	public String getLdocde() {
		return ldocde;
	}

	public void setLdocde(String ldocde) {
		this.ldocde = Utils.checkNull(ldocde);
	}

	public String getBeostr() {
		return beostr;
	}

	public void setBeostr(String beostr) {
		this.beostr = Utils.checkNull(beostr);
	}

	public String getBeoort() {
		return beoort;
	}

	public void setBeoort(String beoort) {
		this.beoort = Utils.checkNull(beoort);
	}

	public String getBeoplz() {
		return beoplz;
	}

	public void setBeoplz(String beoplz) {
		this.beoplz = Utils.checkNull(beoplz);
	}

	public String getBeozus() {
		return beozus;
	}

	public void setBeozus(String beozus) {
		this.beozus = Utils.checkNull(beozus);
	}

	public  String getArtaus() {
		return artaus;
	}

	public  void setArtaus(String artaus) {
		this.artaus = Utils.checkNull(artaus);
	}

	public String getArtueb() {
		return artueb;
	}

	public void setArtueb(String artueb) {
		this.artueb = Utils.checkNull(artueb);
	}

	public String getLdbe() {
		return ldbe;
	}

	public void setLdbe(String ldbe) {
		this.ldbe = Utils.checkNull(ldbe);
	}

	public String getConkz() {
		return conkz;
	}

	public void setConkz(String conkz) {
		this.conkz = Utils.checkNull(conkz);
	}

	public String getFregnr() {
		return fregnr;
	}

	public void setFregnr(String fregnr) {
		this.fregnr = Utils.checkNull(fregnr);
	}

	public String getFlhcde() {
		return flhcde;
	}

	public void setFlhcde(String flhcde) {
		this.flhcde = Utils.checkNull(flhcde);
	}

	public String getExtdst() {
		return extdst;
	}

	public void setExtdst(String extdst) {
		this.extdst = Utils.checkNull(extdst);
	}

	public String getKzanau() {
		return kzanau;
	}

	public void setKzanau(String kzanau) {
		this.kzanau = Utils.checkNull(kzanau);
	}

	public String getKdnrau() {
		return kdnrau;
	}

	public void setKdnrau(String kdnrau) {
		this.kdnrau = Utils.checkNull(kdnrau);
	}

	public String getTinau() {
		return tinau;
	}

	public void setTinau(String tinau) {
		this.tinau = Utils.checkNull(tinau);
	}

	public String getDtzoau() {
		return dtzoau;
	}

	public void setDtzoau(String dtzoau) {
		this.dtzoau = Utils.checkNull(dtzoau);
	}

	public String getKdnrva() {
		return kdnrva;
	}

	public void setKdnrva(String kdnrva) {
		this.kdnrva = Utils.checkNull(kdnrva);
	}

	public String getTinva() {
		return tinva;
	}

	public void setTinva(String tinva) {
		this.tinva = Utils.checkNull(tinva);
	}

	public String getDtzova() {
		return dtzova;
	}

	public void setDtzova(String dtzova) {
		this.dtzova = Utils.checkNull(dtzova);
	}

	public String getEtnva() {
		return etnva;
	}

	public void setEtnva(String etnva) {
		this.etnva = Utils.checkNull(etnva);
	}

	public String getKdnrsu() {
		return kdnrsu;
	}

	public void setKdnrsu(String kdnrsu) {
		this.kdnrsu = Utils.checkNull(kdnrsu);
	}

	public String getTinsu() {
		return tinsu;
	}

	public void setTinsu(String tinsu) {
		this.tinsu = Utils.checkNull(tinsu);
	}

	public String getDtzosu() {
		return dtzosu;
	}

	public void setDtzosu(String dtzosu) {
		this.dtzosu = Utils.checkNull(dtzosu);
	}

	public String getEtnsu() {
		return etnsu;
	}

	public void setEtnsu(String etnsu) {
		this.etnsu = Utils.checkNull(etnsu);
	}

	public String getAnmdat() {
		return anmdat;
	}
	public String getAnmdat(String formatFlag) {
		String anmdatDateTime = "";
		if (formatFlag.equals(""))
			anmdatDateTime = anmdat;
		else		
			//"anmdat" must be converted from YYYYMMDDHHMM to formated anmdatDateTime		
			anmdatDateTime = stringToDateTime(anmdat);
		
		return anmdatDateTime;
	}
	public void setAnmdat(String anmdat) {
		this.anmdat = Utils.checkNull(anmdat);
	}
	public void setAnmdat(String anmdat, String formatFlag) {
		String date = "";
		if (formatFlag.equals(""))
			date = anmdat;
		else 
			date = dateTimeToString(anmdat);
		this.anmdat = Utils.checkNull(date);
	}

	public String getGststr() {
		return gststr;
	}

	public void setGststr(String gststr) {
		this.gststr = Utils.checkNull(gststr);
	}

	public String getGstend() {
		return gstend;
	}

	public void setGstend(String gstend) {
		this.gstend = Utils.checkNull(gstend);
	}

	public String getVerm() {
		return verm;
	}

	public void setVerm(String verm) {
		this.verm = Utils.checkNull(verm);
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

	public String getAnzve() {
		return anzve;
	}

	public void setAnzve(String anzve) {
		this.anzve = Utils.checkNull(anzve);
	}

	public String getVsart() {
		return vsart;
	}

	public void setVsart(String vsart) {
		this.vsart = Utils.checkNull(vsart);
	}

	public String getLdve() {
		return ldve;
	}

	public void setLdve(String ldve) {
		this.ldve = Utils.checkNull(ldve);
	}

	public String getGsroh() {
		return gsroh;
	}

	public void setGsroh(String gsroh) {
		this.gsroh = Utils.checkNull(gsroh);
	}

	public String getInddat() {
		return inddat;
	}

	public void setInddat(String inddat) {
		this.inddat = Utils.checkNull(inddat);
	}

	//public static String getBcktax() {
	//	return bcktax;
	//}


	public boolean isEmpty() {
		if ( Utils.isStringEmpty(bewnr)  && Utils.isStringEmpty(ldocde) && Utils.isStringEmpty(beostr)
		  && Utils.isStringEmpty(beoort) && Utils.isStringEmpty(beoplz) && Utils.isStringEmpty(beozus)
		  && Utils.isStringEmpty(artaus) && Utils.isStringEmpty(artueb) && Utils.isStringEmpty(ldbe)
		  && Utils.isStringEmpty(conkz) && Utils.isStringEmpty(fregnr) && Utils.isStringEmpty(flhcde)
		  && Utils.isStringEmpty(extdst) && Utils.isStringEmpty(kzanau) && Utils.isStringEmpty(kdnrau)
		  && Utils.isStringEmpty(tinau) && Utils.isStringEmpty(dtzoau) && Utils.isStringEmpty(kdnrva)
		  && Utils.isStringEmpty(tinva) && Utils.isStringEmpty(dtzova) && Utils.isStringEmpty(etnva)
		  && Utils.isStringEmpty(kdnrsu) && Utils.isStringEmpty(tinsu) && Utils.isStringEmpty(dtzosu)
		  && Utils.isStringEmpty(etnsu) && Utils.isStringEmpty(anmdat) && Utils.isStringEmpty(gststr)
		  && Utils.isStringEmpty(gstend) && Utils.isStringEmpty(verm) && Utils.isStringEmpty(kztyd)
		  && Utils.isStringEmpty(kzstock) && Utils.isStringEmpty(anzve) && Utils.isStringEmpty(vsart)
		  && Utils.isStringEmpty(ldve) && Utils.isStringEmpty(gsroh) && Utils.isStringEmpty(inddat) )		  
			return true;
		else
			return false;
	}
		
 }
    
   
 
   
   
    
   
  


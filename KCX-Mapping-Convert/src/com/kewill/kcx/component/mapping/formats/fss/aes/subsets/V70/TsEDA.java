package com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V70;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		:	Export/aes
 * Created		:	25.07.2012
 * Description	:	second Headline.  
 *
 * @author 	iwaniuk
 * @version 2.1.00 (Zabis V70)
 */

public class TsEDA extends Teilsatz {
  
    private String beznr   = "";      // Bezugsnummer
    private String bewnr   = "";      // Bewiligungsnummer
    private String bewva   = ""; 
    private String ldocde  = "";      // Codierter Ladeort
    private String beostr  = "";      // Strasse und Hausnummer des Ladeorte
    private String beoort  = "";      // Ort zum Ladeort
    private String beoplz  = "";      // PLZ zum Ladeort
    private String beozus  = "";      // Zusatz zum Ladeort
    private String artaus  = "";      // Art der Anmeldung zur Ausfuhr
    private String artvfr  = "";
    private String artueb  = "";      // Art der Anmeldung zur Überführung
    private String bfarta  = "";
    private String bfkza   = "";
    private String bfnata  = "";
    private String ldve    = "";
    private String ldbe    = "";      // Bestimmungsland
    private String conkz   = "";      // Kennzeichen "Container"
    private String fregnr  = "";      // Registriernummer Fremdsystem
    private String flhcde  = "";      // Flughafencode
    private String extdst  = "";      // Ausgangsdienststelle
    private String kzanau  = "";      // Anmelder=Ausführer   
    private String inddat  = "";      // Zeitpunkt der Vorankündigung
    private String anmdat  = "";      // Zeitpunkt der Anmeldung
    private String gststr  = "";      // Beginn der Gestellungsfrist
    private String gstend  = "";      // Ende   der Gestellungsfrist
    private String verm    = "";      // Vermerk    
    private String kztyd   = "";      // Verschlüsse sind Tydenseals
    private String kzstock = "";      // Verwendung des hinterlegten Tydensealstock
    private String anzve   = "";      // Anzahl verwendeter Verschlüsse
    private String vsart   = "";      // Verschlussart   
    private String gsroh   = "";      // Gesamtrohmasse
   
    public TsEDA() {
        tsTyp = "EDA";
    }
    
	public void setFields(String[] fields) {
		int size = fields.length;
		
		if (size < 1)  { return; }
		tsTyp   = fields[0];
		if (size < 2)  { return; }
		beznr   = fields[1];		
		if (size < 3)  { return; }
		bewnr   = fields[2];
		if (size < 4)  { return; }
		bewva  = fields[3];
		if (size < 5)  { return; }
		ldocde  = fields[4];
		if (size < 6)  { return; }
		beostr  = fields[5];
		if (size < 7)  { return; }
		beoort  = fields[6];
		if (size < 8)  { return; }
		beoplz  = fields[7];
		if (size < 9)  { return; }
		beozus  = fields[8];
		if (size < 10) { return; }
		artaus  = fields[9];	
		if (size < 11) { return; }
		artvfr  = fields[10];
		if (size < 12)  { return; }
		artueb  = fields[11];
		if (size < 13)  { return; }
		bfarta  =  fields[12];		
		if (size < 14)  { return; }
		bfkza   = fields[13];
		if (size < 15)  { return; }
		bfnata  = fields[14];
		if (size < 16)  { return; }
		ldve    = fields[15];		
		if (size < 17)  { return; }
		ldbe    = fields[16];
		if (size < 18)  { return; }
		conkz   = fields[17];
		if (size < 19)  { return; }
		fregnr  = fields[18];
		if (size < 20)  { return; }
		flhcde  = fields[19];
		if (size < 21)  { return; }
		extdst  = fields[20];
		if (size < 22)  { return; }
		kzanau  = fields[21];				
		if (size < 23)  { return; }
		inddat  = fields[22];
		if (size < 24)  { return; }
		anmdat  = fields[23];
		if (size < 25)  { return; }
		gststr  = fields[24];
		if (size < 26)  { return; }
		gstend  = fields[25];
		if (size < 27)  { return; }
		verm    = fields[26];
		if (size < 28)  { return; }
		kztyd   = fields[27];
		if (size < 29)  { return; }
		kzstock = fields[28];
		if (size < 30)  { return; }		
		anzve   = fields[29];	
		if (size < 31)  { return; }
		vsart   = fields[30];	
		if (size < 32)  { return; }
		gsroh   = fields[31];	
	}


    public String teilsatzBilden() {
        StringBuffer buff = new StringBuffer();

        buff.append(tsTyp);
        buff.append(trenner);
        buff.append(beznr);
        buff.append(trenner);
        buff.append(bewnr);
        buff.append(trenner);      
        buff.append(bewva);
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
        buff.append(artvfr);
        buff.append(trenner);
        buff.append(artueb);
        buff.append(trenner);
        buff.append(bfarta);
        buff.append(trenner);
        buff.append(bfkza);
        buff.append(trenner);
        buff.append(bfnata);
        buff.append(trenner); 
        buff.append(ldve);
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
        buff.append(inddat);
        buff.append(trenner);
        buff.append(anmdat);
        buff.append(trenner);
        buff.append(gststr);
        buff.append(trenner);
        buff.append(gstend);
        buff.append(trenner);
        buff.append(verm);
        buff.append(trenner);      
        buff.append(kztyd);
        buff.append(trenner);
        buff.append(kzstock);
        buff.append(trenner);
        buff.append(anzve);
        buff.append(trenner);
        buff.append(vsart);
        buff.append(trenner);        
        buff.append(gsroh);       
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

	public String getBewva() {
		return bewva;
	}
	public void setBewva(String kdnrau) {
		this.bewva = Utils.checkNull(kdnrau);
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
	
	public String getArtvfr() {
		return artvfr;
	}
	public void setArtvfr(String artueb) {
		this.artvfr = Utils.checkNull(artueb);
	}
	
	public String getArtueb() {
		return artueb;
	}
	public void setArtueb(String artueb) {
		this.artueb = Utils.checkNull(artueb);
	}
	
	public String getBfarta() {
		return bfarta;
	}
	public void setBfarta(String kdnrau) {
		this.bfarta = Utils.checkNull(kdnrau);
	}
	
	public String getBfkza() {
		return bfkza;
	}
	public void setBfkza(String dtzoau) {
		this.bfkza = Utils.checkNull(dtzoau);
	}

	public String getBfnata() {
		return bfnata;
	}
	public void setBfnata(String kdnrva) {
		this.bfnata = Utils.checkNull(kdnrva);
	}

	public String getLdve() {
		return ldve;
	}
	public void setLdve(String tinva) {
		this.ldve = Utils.checkNull(tinva);
	}
	
	public String getLdbe() {
		return ldbe;
	}
	public void setLdbe(String ldve) {
		this.ldbe = Utils.checkNull(ldve);
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

	public String getInddat() {
		return inddat;
	}
	public void setInddat(String inddat) {
		this.inddat = Utils.checkNull(inddat);
	}
	
	public String getAnmdat() {
		return anmdat;
	}
	public String getAnmdat(String formatFlag) {
		String anmdatDateTime = "";
		if (formatFlag.equals("")) {
			anmdatDateTime = anmdat;
		} else	{	
			//"anmdat" must be converted from YYYYMMDDHHMM to formated anmdatDateTime		
			anmdatDateTime = stringToDateTime(anmdat);
		}
		return anmdatDateTime;
	}
	public void setAnmdat(String anmdat) {
		this.anmdat = Utils.checkNull(anmdat);
	}
	public void setAnmdat(String anmdat, String formatFlag) {
		String date = "";
		if (formatFlag.equals("")) {
			date = anmdat;
		} else {
			date = dateTimeToString(anmdat);
		}
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

	public String getGsroh() {
		return gsroh;
	}
	public void setGsroh(String gsroh) {
		this.gsroh = Utils.checkNull(gsroh);
	}

	
	
	public boolean isEmpty() {
		return Utils.isStringEmpty(bewnr)  && Utils.isStringEmpty(ldocde) && Utils.isStringEmpty(beostr) &&
	      Utils.isStringEmpty(beoort) && Utils.isStringEmpty(beoplz) && Utils.isStringEmpty(beozus) &&
		  Utils.isStringEmpty(artaus) && Utils.isStringEmpty(artueb) && Utils.isStringEmpty(ldbe) &&
		  Utils.isStringEmpty(conkz) && Utils.isStringEmpty(fregnr) && Utils.isStringEmpty(flhcde) &&
		  Utils.isStringEmpty(extdst) && Utils.isStringEmpty(kzanau) && Utils.isStringEmpty(bewva) &&
		  Utils.isStringEmpty(bfarta) && Utils.isStringEmpty(bfkza) && Utils.isStringEmpty(bfnata) &&		
		  Utils.isStringEmpty(inddat) && Utils.isStringEmpty(anmdat) && Utils.isStringEmpty(gststr) &&
		  Utils.isStringEmpty(gstend) && Utils.isStringEmpty(verm) && Utils.isStringEmpty(kztyd) &&
		  Utils.isStringEmpty(kzstock) && Utils.isStringEmpty(anzve) && Utils.isStringEmpty(vsart) &&
		  Utils.isStringEmpty(ldve) && Utils.isStringEmpty(gsroh);		  		
	}
		
 }

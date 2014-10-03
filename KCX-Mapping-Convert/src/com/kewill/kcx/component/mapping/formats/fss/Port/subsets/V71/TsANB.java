package com.kewill.kcx.component.mapping.formats.fss.Port.subsets.V71; 

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module       : Port
 * Created		: 04.10.2013<br>
 * Description	: BHT Hafenauftrag Kopf Zusatzangaben
 * 
 * @author iwaniuk
 * @version 1.0.00
 */

public class TsANB extends Teilsatz {

    private String beznr	= "";	 // Bezugsnummer
    private String wrnrtg   = "";	 // ProcedureFlag: Warenrichtungscode: I=Import, E=Export
    private String aftok	= "";	 // ExemptionFlag: Auftr-Durchf-ohne-Freistell: J,N,leer 
    private String ldgcde	= "";	 // LadingFlag: Bremen-Ladung-Code: J,N,leer
    private String uadcde	= "";	 // Überw-an-DBH-Code str8
    private String uakunr	= "";	 // Überw-an-Kosten-UMS-KD-Nr srt4
    private String ualdat	= "";	 // Nachrichtenfunktion
    private String uarcde 	= "";	 // Kennzeichen Testantrag
    private String uvdcde	= "";	 // 
    private String uvkunr 	= "";	 // 
    private String uvldat 	= "";	 // 
    private String uvrcde 	= "";
    private String afkdn 	= "";
    private String recabt 	= "";
    private String fecrew 	= "";
    private String recoff 	= "";
    private String scidat 	= "";
    private String sckref 	= "";
    private String abwlcd 	= "";
    private String relnr 	= "";
    private String abglcd 	= "";
    private String abgort 	= "";
    private String bfart 	= "";
    private String wgncde 	= "";
    private String wgnbnr 	= "";
    private String wgnzus 	= "";
    private String fbrnr 	= "";
    private String spdpos 	= "";
    private String abwocd 	= "";
    private String anhknz 	= "";
    private String aftref 	= "";
    private String kdnref 	= "";
    private String aftbez 	= "";
  
    public TsANB() {
	    tsTyp = "ANB";
    }

    public void setFields(String[] fields) {
    	int size = fields.length;
    	//String ausgabe = "FSS: " + fields[0] + " size = " + size;

    	if (size < 1) { return; }
    	tsTyp = fields[0];
	    if (size < 2) { return; }
	    beznr = fields[1];
	    if (size < 3) { return; }
	    wrnrtg = fields[2];
	    if (size < 4) { return; }
	    aftok = fields[3];
	    if (size < 5) { return; }
	    ldgcde = fields[4];	   
	    if (size < 6) { return; }
	    uadcde = fields[5];
	    if (size < 7) { return; }
	    uakunr = fields[6];
	    if (size < 8) { return; }
	    ualdat = fields[7];
	    if (size < 9) { return; }
	    uarcde = fields[8];
	    if (size < 10) { return; }
	    uvdcde = fields[9];
	    if (size < 11) { return; }
	    uvkunr = fields[10];
	    if (size < 12) { return; }
	    uvldat = fields[11];
	    if (size < 13) { return; }
	    uvrcde = fields[12];
	    if (size < 14) { return; }
	    afkdn = fields[13];
	    if (size < 15) { return; }
	    recabt = fields[14];
	    if (size < 16) { return; }
	    fecrew = fields[15];
	    if (size < 17) { return; }
	    recoff = fields[16];
	    if (size < 18) { return; }
	    scidat = fields[17];
	    if (size < 19) { return; }
	    sckref = fields[18];	   
	    if (size < 20) { return; }
	    abwlcd = fields[19];
	    if (size < 21) { return; }
	    relnr = fields[20];
	    if (size < 22) { return; }
	    abglcd = fields[21];
	    if (size < 23) { return; }
	    abgort = fields[22];
	    if (size < 24) { return; }
	    bfart = fields[23];
	    if (size < 25) { return; }
	    wgncde = fields[24];
	    if (size < 26) { return; }
	    wgnbnr = fields[25];
	    if (size < 27) { return; }
	    wgnzus = fields[26];
	    if (size < 28) { return; }
	    fbrnr = fields[27];
	    if (size < 29) { return; }
	    spdpos = fields[28];
	    if (size < 30) { return; }
	    abwocd = fields[29];
	    if (size < 31) { return; }
	    anhknz = fields[30];
	    if (size < 32) { return; }
	    aftref = fields[31];
	    if (size < 33) { return; }
	    kdnref = fields[32];
	    if (size < 34) { return; }
	    aftbez = fields[33];
    }
   
    public String teilsatzBilden() {
    	StringBuffer buff = new StringBuffer();
    	
    	buff.append(tsTyp);
    	buff.append(trenner);
    	buff.append(beznr);
    	buff.append(trenner);
    	buff.append(wrnrtg);
    	buff.append(trenner);
    	buff.append(aftok);
    	buff.append(trenner);
    	buff.append(ldgcde);
    	buff.append(trenner);    	
    	buff.append(uadcde);    	
    	buff.append(trenner);
    	buff.append(uakunr);
    	buff.append(trenner);    	
    	buff.append(ualdat);
    	buff.append(trenner);    	
    	buff.append(uarcde);
    	buff.append(trenner);
    	buff.append(uvdcde);
    	buff.append(trenner);
    	buff.append(uvkunr);
    	buff.append(trenner);
    	buff.append(uvldat);
    	buff.append(trenner);
    	buff.append(uvrcde);
    	buff.append(trenner);
    	buff.append(afkdn);
    	buff.append(trenner);
    	buff.append(recabt);
    	buff.append(trenner);
    	buff.append(fecrew);
    	buff.append(trenner);
    	buff.append(recoff);
    	buff.append(trenner);
    	buff.append(scidat);
    	buff.append(trenner);
    	buff.append(sckref);
    	buff.append(trenner);
    	buff.append(abwlcd);
    	buff.append(trenner);
    	buff.append(relnr);
    	buff.append(trenner);
    	buff.append(abglcd);
    	buff.append(trenner);
    	buff.append(abgort);
    	buff.append(trenner);
    	buff.append(bfart);
    	buff.append(trenner);
    	buff.append(wgncde);
    	buff.append(trenner);
    	buff.append(wgnbnr);
    	buff.append(trenner);
    	buff.append(wgnzus);
    	buff.append(trenner);
    	buff.append(fbrnr);
    	buff.append(trenner);
    	buff.append(spdpos);
    	buff.append(trenner);
    	buff.append(abwocd);
    	buff.append(trenner);
    	buff.append(anhknz);
    	buff.append(trenner);
    	buff.append(aftref);
    	buff.append(trenner);
    	buff.append(kdnref);
    	buff.append(trenner);
    	buff.append(aftbez);
    	buff.append(trenner);
    	
    	return new String(buff);    
    }
    
    public String getBeznr() {
    	 return beznr;
    }
    public void setBeznr(String argument) {
    	this.beznr = Utils.checkNull(argument);
    }   
  
    public String getWrnrtg() {
		return wrnrtg;
	}
	public void setWrnrtg(String wrnrtg) {
		this.wrnrtg = Utils.checkNull(wrnrtg);
	}

	public String getAftok() {
		return aftok;
	}
	public void setAftok(String aftok) {
		this.aftok = Utils.checkNull(aftok);
	}

	public String getLdgcde() {
		return ldgcde;
	}
	public void setLdgcde(String ldgcde) {
		this.ldgcde = Utils.checkNull(ldgcde);
	}

	public String getUadcde() {
		return uadcde;
	}
	public void setUadcde(String uadcde) {
		this.uadcde = Utils.checkNull(uadcde);
	}

	public String getUakunr() {
		return uakunr;
	}
	public void setUakunr(String uakunr) {
		this.uakunr = Utils.checkNull(uakunr);
	}

	public String getUaldat() {
		return ualdat;
	}
	public void setUaldat(String ualdat) {
		this.ualdat = Utils.checkNull(ualdat);
	}

	public String getUarcde() {
		return uarcde;
	}
	public void setUarcde(String uarcde) {
		this.uarcde = Utils.checkNull(uarcde);
	}

	public String getUvdcde() {
		return uvdcde;
	}
	public void setUvdcde(String uvdcde) {
		this.uvdcde = Utils.checkNull(uvdcde);
	}

	public String getUvkunr() {
		return uvkunr;
	}
	public void setUvkunr(String uvkunr) {
		this.uvkunr = Utils.checkNull(uvkunr);
	}

	public String getUvldat() {
		return uvldat;
	}
	public void setUvldat(String uvldat) {
		this.uvldat = Utils.checkNull(uvldat);
	}

	public String getUvrcde() {
		return uvrcde;
	}
	public void setUvrcde(String uvrcde) {
		this.uvrcde = Utils.checkNull(uvrcde);
	}

	public String getAfkdn() {
		return afkdn;
	}
	public void setAfkdn(String afkdn) {
		this.afkdn = Utils.checkNull(afkdn);
	}

	public String getRecabt() {
		return recabt;
	}
	public void setRecabt(String recabt) {
		this.recabt = Utils.checkNull(recabt);
	}

	public String getFecrew() {
		return fecrew;
	}
	public void setFecrew(String fecrew) {
		this.fecrew = Utils.checkNull(fecrew);
	}

	public String getRecoff() {
		return recoff;
	}
	public void setRecoff(String recoff) {
		this.recoff = Utils.checkNull(recoff);
	}

	public String getScidat() {
		return scidat;
	}
	public void setScidat(String scidat) {
		this.scidat = Utils.checkNull(scidat);
	}

	public String getSckref() {
		return sckref;
	}
	public void setSckref(String sckref) {
		this.sckref = Utils.checkNull(sckref);
	}
	
	public String getAbwlcd() {
		return abwlcd;
	}
	public void setAbwlcd(String abwlcd) {
		this.abwlcd = Utils.checkNull(abwlcd);
	}

	public String getRelnr() {
		return relnr;
	}
	public void setRelnr(String relnr) {
		this.relnr = Utils.checkNull(relnr);
	}

	public String getAbglcd() {
		return abglcd;
	}
	public void setAbglcd(String abglcd) {
		this.abglcd = Utils.checkNull(abglcd);
	}

	public String getAbgort() {
		return abgort;
	}
	public void setAbgort(String abgort) {
		this.abgort = Utils.checkNull(abgort);
	}

	public String getBfart() {
		return bfart;
	}
	public void setBfart(String bfart) {
		this.bfart = Utils.checkNull(bfart);
	}

	public String getWgncde() {
		return wgncde;
	}
	public void setWgncde(String wgncde) {
		this.wgncde = Utils.checkNull(wgncde);
	}

	public String getWgnbnr() {
		return wgnbnr;
	}
	public void setWgnbnr(String wgnbnr) {
		this.wgnbnr = Utils.checkNull(wgnbnr);
	}

	public String getWgnzus() {
		return wgnzus;
	}
	public void setWgnzus(String wgnzus) {
		this.wgnzus = Utils.checkNull(wgnzus);
	}

	public String getFbrnr() {
		return fbrnr;
	}
	public void setFbrnr(String fbrnr) {
		this.fbrnr = Utils.checkNull(fbrnr);
	}

	public String getSpdpos() {
		return spdpos;
	}
	public void setSpdpos(String spdpos) {
		this.spdpos = Utils.checkNull(spdpos);
	}

	public String getAbwocd() {
		return abwocd;
	}
	public void setAbwocd(String abwocd) {
		this.abwocd = Utils.checkNull(abwocd);
	}

	public String getAnhknz() {
		return anhknz;
	}
	public void setAnhknz(String anhknz) {
		this.anhknz = Utils.checkNull(anhknz);
	}

	public String getAftref() {
		return aftref;
	}
	public void setAftref(String aftref) {
		this.aftref = Utils.checkNull(aftref);
	}

	public String getKdnref() {
		return kdnref;
	}
	public void setKdnref(String kdnref) {
		this.kdnref = Utils.checkNull(kdnref);
	}

	public String getAftbez() {
		return aftbez;
	}
	public void setAftbez(String aftbez) {
		this.aftbez = Utils.checkNull(aftbez);
	}

	public boolean isEmpty() {
	return  
		Utils.isStringEmpty(wrnrtg) &&
		Utils.isStringEmpty(aftok) &&
		Utils.isStringEmpty(ldgcde) &&
		//TODO rest, reicht mussfelder
		Utils.isStringEmpty(scidat) &&
    	Utils.isStringEmpty(spdpos); 
    
    }
}


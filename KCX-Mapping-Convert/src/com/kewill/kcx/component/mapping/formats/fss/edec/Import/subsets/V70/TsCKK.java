package com.kewill.kcx.component.mapping.formats.fss.edec.Import.subsets.V70;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: EDEC Import 70<br>
 * Created		: 29.10.2012<br>
 * Description  : FSS Definition of Subset CKK (Kopfsatz). 
 * 
 * @author iwaniuk
 * @version 7.0.00
 */


public class TsCKK extends Teilsatz {

    private String refnr    = "";       //Bezugsnummer
    private String korant   = "";      // Korrekturnummer 
    private String korrcd   = "";      // 
    private String svtype   = "";      // 
    private String version  = "";      //
    private String lang     = "";      //
    private String abspdnr  = "";      //
    private String cdkorgrd = "";      //
    private String aufnr    = "";      //
    private String samsdnr  = "";      //
    private String loc      = "";      //
    private String cdstus   = "";      //
    private String cdtype   = "";      //
    private String cdprvgrd = "";      //
    private String dstnr    = "";      //
    private String lde      = "";      //
    private String gscolli  = "";      //
    private String gsrohm   = "";      //
    private String gsrohmk  = "";      //
    private String gswert   = "";      //
    private String cdvz     = "";      //
    private String cdbefart = "";      //
    private String ldim     = "";
    private String trspm    = "";
    private String ktomw    = "";
    private String mwstnr   = "";
    private String ktonr    = "";
    private String kzcont   = "";
    private String vekdnr   = "";
    private String ablort   = "";
    private String imkdnr   = "";
    private String emkdnr   = ""; 
    private String azvvz    = "";
    private String dklhin   = "";
    private String vehin    = "";
    private String imhin    = "";
    private String emhin    = "";
    private String dknr     = "";
    private String sbnr     = "";
    private String cdinco   = "";
    private String lderc    = "";
    private String whcod    = "";
   
    public TsCKK() {
        tsTyp = "CKK";
    }

    public void setFields(String[] fields) {
    
		int size = fields.length;
		//String ausgabe = "FSS: "+fields[0]+" size = "+ size;
		//Utils.log( ausgabe);
			
		
		if (size < 1) { return; }		
        	tsTyp = fields[0];
        if (size < 2) { return; }	
        	refnr = fields[1];
        if (size < 3) { return; }		
        	korant = fields[2];
        if (size < 4) { return; }		
        	korrcd = fields[3];
        if (size < 5) { return; }	
        	svtype = fields[4];        
        if (size < 6) { return; }	
        	version = fields[5];
        if (size < 7) { return; }	
        	lang = fields[6];
        if (size < 8) { return; }	
        	abspdnr = fields[7];        	
        if (size < 9) { return; }		
        	cdkorgrd = fields[8];
        if (size < 10) { return; }	
        	aufnr = fields[9];
        if (size < 11) { return; }	
        	samsdnr = fields[10];        
        if (size < 12) { return; }		
        	loc = fields[11];
        if (size < 13) { return; }	
         	cdstus = fields[12];
        if (size < 14) { return; }	
        	cdtype = fields[13];        	
        if (size < 15) { return; }	
        	cdprvgrd = fields[14];
        if (size < 16) { return; }	
        	dstnr = fields[15];
        if (size < 17) { return; }	
        	lde = fields[16];
        if (size < 18) { return; }	
        	gscolli = fields[17];        
        if (size < 19) { return; }	
        	gsrohm = fields[18];
        if (size < 20) { return; }	
        	gsrohmk = fields[19];
        if (size < 21) { return; }	
         	gswert = fields[20];        	
        if (size < 22) { return; }	
        	cdvz = fields[21];
        if (size < 23) { return; }	
         	cdbefart = fields[22];         
        if (size < 24) { return; }	
         	ldim = fields[23];        	
        if (size < 25) { return; }
        	trspm = fields[24];
        if (size < 26) { return; }	
          	ldim = fields[25];        	
        if (size < 27) { return; }
         	trspm = fields[26];
        if (size < 28) { return; }	
          	ldim = fields[27];        	
        if (size < 29) { return; }
         	trspm = fields[28];
        if (size < 30) { return; }	
          	ldim = fields[29];        	
        if (size < 31) { return; }
         	trspm = fields[30];
        if (size < 32) { return; }	
        	ktomw = fields[31];        	
        if (size < 33) { return; }
        	mwstnr = fields[32];
        if (size < 34) { return; }	
        	ktonr = fields[33];        	
        if (size < 35) { return; }
        	kzcont = fields[34];
         if (size < 36) { return; }	
         	vekdnr = fields[35];        	
         if (size < 37) { return; }
         	ablort = fields[36];
         if (size < 38) { return; }	
         	imkdnr = fields[37];        	
         if (size < 39) { return; }
         	emkdnr = fields[38];
         if (size < 40) { return; }	
         	azvvz = fields[39];        	
         if (size < 41) { return; }
         	dklhin = fields[40];          	          
         if (size < 42) { return; }	
         	vehin = fields[41];        	
         if (size < 43) { return; }
         	imhin = fields[42];
         if (size < 44) { return; }	
         	emhin = fields[43];        	
         if (size < 45) { return; }
         	dknr = fields[44]; 	
         if (size < 46) { return; }	
         	sbnr = fields[45];        	
         if (size < 47) { return; }
         	cdinco = fields[46];
         if (size < 48) { return; }	
         	lderc = fields[47];        	
         if (size < 49) { return; }
         	whcod = fields[48];          	        
    }

    public String teilsatzBilden() {
        StringBuffer buff = new StringBuffer();

        buff.append(tsTyp);
        buff.append(trenner);
        buff.append(refnr);
        buff.append(trenner);
        buff.append(korant);
        buff.append(trenner);
        buff.append(korrcd);
        buff.append(trenner);
        buff.append(svtype);
        buff.append(trenner);
        buff.append(version);
        buff.append(trenner);
        buff.append(lang);
        buff.append(trenner);
        buff.append(abspdnr);
        buff.append(trenner);
        buff.append(cdkorgrd);	
        buff.append(trenner);
        buff.append(aufnr);
        buff.append(trenner);
        buff.append(samsdnr);
        buff.append(trenner);
        buff.append(loc);
        buff.append(trenner);
        buff.append(cdstus);
        buff.append(trenner);
        buff.append(cdtype);
        buff.append(trenner);
        buff.append(cdprvgrd);	
        buff.append(trenner);
        buff.append(dstnr);	
        buff.append(trenner);
        buff.append(lde);	
        buff.append(trenner);
        buff.append(gscolli);
		buff.append(trenner);
		buff.append(gsrohm);
		buff.append(trenner);
		buff.append(gsrohmk);
		buff.append(trenner);
		buff.append(gswert);
		buff.append(trenner);
		buff.append(cdvz);
		buff.append(trenner);
		buff.append(cdbefart);
		buff.append(trenner); 
        buff.append(ldim);	
        buff.append(trenner);
        buff.append(trspm);
        buff.append(trenner);
        buff.append(ktomw);	
        buff.append(trenner);
        buff.append(mwstnr);
        buff.append(trenner);
        buff.append(ktonr);	
        buff.append(trenner);
        buff.append(kzcont);
        buff.append(trenner);
        buff.append(vekdnr);	
        buff.append(trenner);
        buff.append(ablort);
        buff.append(trenner);
        buff.append(imkdnr);	
        buff.append(trenner);
        buff.append(emkdnr);
        buff.append(trenner);
        buff.append(azvvz);	
        buff.append(trenner);
        buff.append(dklhin);
        buff.append(trenner);
        buff.append(vehin);	
        buff.append(trenner);
        buff.append(imhin);
        buff.append(trenner);
        buff.append(emhin);
        buff.append(trenner);
        buff.append(dknr);
        buff.append(trenner);
        buff.append(sbnr);
        buff.append(trenner);
        buff.append(cdinco);
        buff.append(trenner);
        buff.append(lderc);
        buff.append(trenner);
        buff.append(whcod);
        buff.append(trenner);       
		
        return new String(buff);
      }  

	public boolean isEmpty() {
		  if ( Utils.isStringEmpty(korant) &&
				 Utils.isStringEmpty(korrcd) &&
			 	 Utils.isStringEmpty(svtype) &&
			 	 Utils.isStringEmpty(version) && 
			 	 Utils.isStringEmpty(lang) && 
			 	 Utils.isStringEmpty(abspdnr) &&
			 	 Utils.isStringEmpty(lang) && 
			 	 Utils.isStringEmpty(cdkorgrd) &&
			 	 Utils.isStringEmpty(samsdnr) && 
			 	 Utils.isStringEmpty(loc) &&
			 	 Utils.isStringEmpty(cdstus) &&
			 	 Utils.isStringEmpty(cdtype) &&
			 	 Utils.isStringEmpty(cdprvgrd) && 
			 	 Utils.isStringEmpty(dstnr) && 
			 	 Utils.isStringEmpty(lde) && 
			 	 Utils.isStringEmpty(gscolli) &&
			 	 Utils.isStringEmpty(gsrohm) &&
			 	 Utils.isStringEmpty(gsrohmk) &&
			 	 Utils.isStringEmpty(gswert) &&
			 	 Utils.isStringEmpty(cdvz)  &&
			 	 Utils.isStringEmpty(cdbefart) 
		  	  ) {
			return true;
		} else {
			return false;
		}
	}

	public String getRefnr() {
		return refnr;	
	}

	public void setRefnr(String refnr) {
		this.refnr = Utils.checkNull(refnr);
	}

	public String getKorant() {
		return korant;	
	}

	public void setKorant(String korant) {
		this.korant = Utils.checkNull(korant);
	}

	public String getKorrcd() {
		return korrcd;	
	}

	public void setKorrcd(String korrcd) {
		this.korrcd = Utils.checkNull(korrcd);
	}

	public String getSvtype() {
		return svtype;	
	}

	public void setSvtype(String svtype) {
		this.svtype = Utils.checkNull(svtype);
	}

	public String getVersion() {
		return version;	
	}

	public void setVersion(String version) {
		this.version = Utils.checkNull(version);
	}

	public String getLang() {
		return lang;	
	}

	public void setLang(String lang) {
		this.lang = Utils.checkNull(lang);
	}

	public String getAbspdnr() {
		return abspdnr;	
	}

	public void setAbspdnr(String absamsdnr) {
		this.abspdnr = Utils.checkNull(absamsdnr);
	}

	public String getCdkorgrd() {
		return cdkorgrd;	
	}

	public void setCdkorgrd(String lang) {
		this.cdkorgrd = Utils.checkNull(lang);
	}

	public String getAufnr() {
		return aufnr;	
	}

	public void setAufnr(String aufnr) {
		this.aufnr = Utils.checkNull(aufnr);
	}

	public String getSamsdnr() {
		return samsdnr;	
	}

	public void setSamsdnr(String samsdnr) {
		this.samsdnr = Utils.checkNull(samsdnr);
	}

	public String getLoc() {
		return loc;	
	}

	public void setLoc(String loc) {
		this.loc = Utils.checkNull(loc);
	}

	public String getCdstus() {
		return cdstus;	
	}

	public void setCdstus(String cdstus) {
		this.cdstus = Utils.checkNull(cdstus);
	}

	public String getCdtype() {
		return cdtype;	
	}

	public void setCdtype(String cdtype) {
		this.cdtype = Utils.checkNull(cdtype);
	}

	public String getCdprvgrd() {
		return cdprvgrd;	
	}

	public void setCdprvgrd(String cdprvgrd) {
		this.cdprvgrd = Utils.checkNull(cdprvgrd);
	}

	public String getDstnr() {
		return dstnr;	
	}

	public void setDstnr(String dstnr) {
		this.dstnr = Utils.checkNull(dstnr);
	}

	public String getLde() {
		return lde;	
	}

	public void setLde(String lde) {
		this.lde = Utils.checkNull(lde);
	}

	public String getGscolli() {
		return gscolli;	
	}

	public void setGscolli(String gscolli) {
		this.gscolli = Utils.checkNull(gscolli);
	}

	public String getGsrohm() {
		return gsrohm;	
	}

	public void setGsrohm(String value) {
		this.gsrohm = Utils.checkNull(value);
	}

	public String getGsrohmk() {
		return gsrohmk;	
	}

	public void setGsrohmk(String value) {
		this.gsrohmk = Utils.checkNull(value);
	}

	public String getGswert() {
		return gswert;	
	}

	public void setGswert(String gswert) {
		this.gswert = Utils.checkNull(gswert);
	}

	public String getCdvz() {
		return cdvz;	
	}

	public void setCdvz(String value) {
		this.cdvz = Utils.checkNull(value);
	}

	public String getCdbefart() {
		return cdbefart;	
	}

	public void setCdbefart(String cdbefart) {
		this.cdbefart = Utils.checkNull(cdbefart);
	}
	
	public String getLdim() {
		return ldim;	
	}

	public void setLdim(String cdvz) {
		this.ldim = Utils.checkNull(cdvz);
	}
	
	public String getTrspm() {
		return trspm;	
	}

	public void setTrspm(String value) {
		this.trspm = Utils.checkNull(value);
	}	

	public String getKtomw() {
		return ktomw;	
	}

	public void setKtomw(String value) {
		this.ktomw = Utils.checkNull(value);
	}
	
	public String getMwstnr() {
		return mwstnr;	
	}

	public void setMwstnr(String value) {
		this.mwstnr = Utils.checkNull(value);
	}
	
	public String getKtonr() {
		return ktonr;	
	}

	public void setKtonr(String value) {
		this.ktonr = Utils.checkNull(value);
	}
	
	public String getKzcont() {
		return kzcont;	
	}

	public void setKzcont(String value) {
		this.kzcont = Utils.checkNull(value);
	}

	public String getVekdnr() {
		return vekdnr;	
	}

	public void setVekdnr(String value) {
		this.vekdnr = Utils.checkNull(value);
	}
	
	public String getAblort() {
		return ablort;	
	}

	public void setAblort(String value) {
		this.ablort = Utils.checkNull(value);
	}
	
	public String getImkdnr() {
		return imkdnr;	
	}

	public void setImkdnr(String value) {
		this.imkdnr = Utils.checkNull(value);
	}
	
	public String getEmkdnr() {
		return emkdnr;	
	}

	public void setEmkdnr(String value) {
		this.emkdnr = Utils.checkNull(value);
	}
	
	public String getAzvvz() {
		return azvvz;	
	}

	public void setAzvvz(String value) {
		this.azvvz = Utils.checkNull(value);
	}
	
	public String getDklhin() {
		return dklhin;	
	}

	public void setDklhin(String value) {
		this.dklhin = Utils.checkNull(value);
	}
	
	public String getVehin() {
		return vehin;	
	}
	
	public void setVehin(String value) {
		this.vehin = Utils.checkNull(value);
	}
	
	public String getImhin() {
		return imhin;	
	}

	public void setImhin(String value) {
		this.imhin = Utils.checkNull(value);
	}
	
	public String getEmhin() {
		return emhin;	
	}

	public void setEmhin(String value) {
		this.emhin = Utils.checkNull(value);
	}
	
	public String getDknr() {
		return dknr;	
	}
	
	public void setDknr(String value) {
		this.dknr = Utils.checkNull(value);
	}
	
	public String getSbnr() {
		return sbnr;	
	}
	
	public void setSbnr(String value) {
		this.sbnr = Utils.checkNull(value);
	}	
	
	public String getCdinco() {
		return cdinco;	
	}

	public void setCdinco(String value) {
		this.cdinco = Utils.checkNull(value);
	}
	
	public String getLderc() {
		return lderc;	
	}

	public void setLderc(String value) {
		this.lderc = Utils.checkNull(value);
	}
		
	
	public String getWhcod() {
		return whcod;	
	}
	
	public void setWhcod(String value) {
		this.whcod = Utils.checkNull(value);
	}
}



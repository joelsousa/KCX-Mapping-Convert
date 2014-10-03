package com.kewill.kcx.component.mapping.formats.fss.edec.ncts20.subsets;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module        :   NCTS 70 
 * Created       :   07.06.2013
 * Description   :   Kopf-Ts for MsgCTK Transitabmeldung.
 *  
 * @author         iwaniuk
 * @version        7.0.00
 */

public class TsCTK extends Teilsatz {

    private String beznr = "";       //Bezugsnummer
    private String sammnr = "";      //  
    private String spdnr = "";      //
    private String abgdst = "";      // 
    private String bedst = ""; 
    private String kzcont = "";       //
    private String kzraus = "";       //
    private String bfabkz = "";      // 
    private String bfabld = "";        //
    private String vzgrcd = "";       //
    private String tinhp = "";       // 
    private String tinem = "";      //
    private String emname = "";      //
    private String emstr = "";      //
    private String emland = "";        //
    private String emplz = "";        // 
    private String emort = "";        // 
    private String tinze = "";      //  
    private String anzve = "";      //
    private String anzpos = "";      //
    private String anzcol = "";      //
    private String gsroh = "";       //
    private String dknr = "";      //
    private String tinhlt = "";      //
    private String  kzsec = "";      //
    private String  knrha = "";      //
    private String  vzgr = "";      //
    private String  knrtr = "";      //
    private String  ladort = "";      //
    private String  entort = "";      //
    private String  tinbef = "";      //
    private String  bfgrkz = "";      //
    private String  bfgrld = "";      //
    private String  cdzhlw = "";      //
    private String  wgfri = "";      //
    private String  zores= "";      //
    private String  entadr = "";      //
    private String  lang = "";      //
    private String  kzzv = "";      //
    private String  kznoeu = "";      //
    
    
    public TsCTK() {
        tsTyp = "CTK";
    }

    public String teilsatzBilden() {
        StringBuffer buff = new StringBuffer();

        buff.append(tsTyp);
        buff.append(trenner);
        buff.append(beznr);
        buff.append(trenner);
        buff.append(sammnr);
        buff.append(trenner);
        buff.append(spdnr);
        buff.append(trenner);
        buff.append(abgdst);
        buff.append(trenner);
        buff.append(bedst);
        buff.append(trenner);
        buff.append(kzcont);
        buff.append(trenner);
        buff.append(kzraus);
        buff.append(trenner);
        buff.append(bfabkz);	
        buff.append(trenner);
        buff.append(bfabld);
        buff.append(trenner);
        buff.append(vzgrcd);
        buff.append(trenner);
        buff.append(tinhp);
        buff.append(trenner);
        buff.append(tinem);
        buff.append(trenner);
        buff.append(emname);
        buff.append(trenner);
        buff.append(emstr);	
        buff.append(trenner);
        buff.append(emland);	
        buff.append(trenner);
        buff.append(emplz);	        
        buff.append(trenner);
        	buff.append(emort);
        	buff.append(trenner);
        	buff.append(tinze);
        	buff.append(trenner);
        	buff.append(anzve);
        	buff.append(trenner);
        	buff.append(anzpos);
        	buff.append(trenner);
        	buff.append(anzcol);
        	buff.append(trenner);
        	buff.append(gsroh);
        	buff.append(trenner);
        	buff.append(dknr);
        	buff.append(trenner);
	        buff.append(tinhlt);
	        buff.append(trenner);
	        buff.append(kzsec);
	        buff.append(trenner);
	        buff.append(knrha);
	        buff.append(trenner);
	        buff.append(vzgr);	
	        buff.append(trenner);
	        buff.append(knrtr);	
	        buff.append(trenner);
	        buff.append(ladort);	
	        buff.append(trenner);
	        buff.append(entort);	
	        buff.append(trenner);
	        buff.append(tinbef);	
	        buff.append(trenner);
	        buff.append(bfgrkz);	
	        buff.append(trenner);
	        buff.append(bfgrld);	
	        buff.append(trenner);
	        buff.append(cdzhlw);		        
	        buff.append(trenner);	        
	     buff.append(wgfri);	
	     buff.append(trenner);
	        buff.append(zores);	
	        buff.append(trenner);
	        buff.append(entadr);	
	        buff.append(trenner);
	        buff.append(lang);	
	        buff.append(trenner);
	        buff.append(kzzv);	
	        buff.append(trenner);
	        buff.append(kznoeu);	
	        buff.append(trenner);	       

        return new String(buff);
    }   
    
    public void setFields(String[] fields) {
		int size = fields.length;
		//String ausgabe = "FSS: "+fields[0]+" size = "+ size;
		//Utils.log( ausgabe);		
		
		if (size < 1 ) return;		
        	tsTyp = fields[0];
        	//TODO z.Z ist nicht notwendig
    }    

	public boolean isEmpty() {
		  if (Utils.isStringEmpty(sammnr)  //nur MussFelder
			 	&& Utils.isStringEmpty(spdnr)
			 	&& Utils.isStringEmpty(abgdst)
			 	&& Utils.isStringEmpty(bfabkz) 
			 	&& Utils.isStringEmpty(bfabld) 
			 	&& Utils.isStringEmpty(vzgrcd)
			 	&& Utils.isStringEmpty(lang) 
			 	&& Utils.isStringEmpty(anzcol)
			 	&& Utils.isStringEmpty(gsroh) 			 	
		  	  )
			return true;
		else
			return false;
	}

	public String getBeznr() {
		return beznr;	
	}
	public void setBeznr(String beznr) {
		this.beznr = Utils.checkNull(beznr);
	}

	public String getSammnr() {
		return sammnr;	
	}
	public void setSammnr(String sammnr) {
		this.sammnr = Utils.checkNull(sammnr);
	}

	public String getSpdnr() {
		return spdnr;
	}
	public void setSpdnr(String spdnr) {
		this.spdnr = Utils.checkNull(spdnr);
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

	public String getKzcont() {
		return kzcont;
	}
	public void setKzcont(String kzcont) {
		this.kzcont = Utils.checkNull(kzcont);
	}

	public String getKzraus() {
		return kzraus;
	}
	public void setKzraus(String kzraus) {
		this.kzraus = Utils.checkNull(kzraus);
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

	public String getVzgrcd() {
		return vzgrcd;
	}
	public void setVzgrcd(String vzgrcd) {
		this.vzgrcd = vzgrcd;
	}

	public String getTinhp() {
		return tinhp;
	}
	public void setTinhp(String tinhp) {
		this.tinhp = Utils.checkNull(tinhp);
	}

	public String getTinem() {
		return tinem;
	}
	public void setTinem(String tinem) {
		this.tinem = Utils.checkNull(tinem);
	}

	public String getEmname() {
		return emname;
	}
	public void setEmname(String emname) {
		this.emname = Utils.checkNull(emname);
	}

	public String getEmstr() {
		return emstr;
	}
	public void setEmstr(String emstr) {
		this.emstr = Utils.checkNull(emstr);
	}

	public String getEmland() {
		return emland;
	}
	public void setEmland(String emland) {
		this.emland = Utils.checkNull(emland);
	}

	public String getEmplz() {
		return emplz;
	}

	public void setEmplz(String emplz) {
		this.emplz = Utils.checkNull(emplz);
	}
	public String getEmort() {
		return emort;
	}
	public void setEmort(String emort) {
		this.emort = Utils.checkNull(emort);
	}

	public String getTinze() {
		return tinze;
	}
	public void setTinze(String tinze) {
		this.tinze = Utils.checkNull(tinze);
	}

	public String getAnzve() {
		return anzve;
	}
	public void setAnzve(String anzve) {
		this.anzve = Utils.checkNull(anzve);
	}

	public String getAnzpos() {
		return anzpos;
	}
	public void setAnzpos(String anzpos) {
		this.anzpos = Utils.checkNull(anzpos);
	}

	public String getAnzcol() {
		return anzcol;
	}
	public void setAnzcol(String anzcol) {
		this.anzcol = Utils.checkNull(anzcol);
	}

	public String getGsroh() {
		return gsroh;
	}
	public void setGsroh(String gsroh) {
		this.gsroh = Utils.checkNull(gsroh);
	}

	public String getDknr() {
		return dknr;
	}
	public void setDknr(String dknr) {
		this.dknr = Utils.checkNull(dknr);
	}

	public String getTinhlt() {
		return tinhlt;
	}
	public void setTinhlt(String tinhlt) {
		this.tinhlt = Utils.checkNull(tinhlt);
	}

	public String getKzsec() {
		return kzsec;
	}
	public void setKzsec(String kzsec) {
		this.kzsec = Utils.checkNull(kzsec);
	}

	public String getKnrha() {
		return knrha;
	}
	public void setKnrha(String knrha) {
		this.knrha = Utils.checkNull(knrha);
	}

	public String getVzgr() {
		return vzgr;
	}
	public void setVzgr(String vzgr) {
		this.vzgr = Utils.checkNull(vzgr);
	}

	public String getKnrtr() {
		return knrtr;
	}
	public void setKnrtr(String knrtr) {
		this.knrtr = Utils.checkNull(knrtr);
	}

	public String getLadort() {
		return ladort;
	}
	public void setLadort(String ladort) {
		this.ladort = Utils.checkNull(ladort);
	}

	public String getEntort() {
		return entort;
	}
	public void setEntort(String entort) {
		this.entort = Utils.checkNull(entort);
	}

	public String getTinbef() {
		return tinbef;
	}
	public void setTinbef(String tinbef) {
		this.tinbef = Utils.checkNull(tinbef);
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

	public String getCdzhlw() {
		return cdzhlw;
	}
	public void setCdzhlw(String cdzhlw) {
		this.cdzhlw = Utils.checkNull(cdzhlw);
	}

	public String getWgfri() {
		return wgfri;
	}
	public void setWgfri(String wgfri) {
		this.wgfri = Utils.checkNull(wgfri);
	}

	public String getZores() {
		return zores;
	}
	public void setZores(String zores) {
		this.zores = Utils.checkNull(zores);
	}

	public String getEntadr() {
		return entadr;
	}
	public void setEntadr(String entadr) {
		this.entadr = Utils.checkNull(entadr);
	}

	public String getLang() {
		return lang;
	}
	public void setLang(String lang) {
		this.lang = Utils.checkNull(lang);
	}

	public String getKzzv() {
		return kzzv;
	}
	public void setKzzv(String kzzv) {
		this.kzzv = Utils.checkNull(kzzv);
	}

	public String getKznoeu() {
		return kznoeu;
	}
	public void setKznoeu(String kznoeu) {
		this.kznoeu = Utils.checkNull(kznoeu);
	}	
}



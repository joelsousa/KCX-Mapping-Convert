package com.kewill.kcx.component.mapping.formats.fss.edec.Import.subsets.V70;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: EDEC Import 70<br>
 * Created		: 06.11.2012<br>
 * Description  : FSS Definition of Subset: AB1 Kopfsatz. 
 * 
 * @author iwaniuk
 * @version 7.0.00
 */

public class TsAB1 extends Teilsatz {
	
	private String ab1bnr = "";       //Bezugsnummer	
	private String regnr  = "";		 
	private String korant = "";		
	private String regkz  = "";		
	private String lfdnr  = "";		  
	private String antart = "";		 
	private String wkz    = "";		 
	//bardat, sicdat	
	private String abgdat = "";	 
	private String erfdat = "";	 
	private String abes   = "";	 
	private String kzvtr  = "";
	//kzkbg, kzzaab, kzzaau, kzzasi
	private String anmzb  = ""; 
	private String dstnr  = "";
	private String dstnam = "";	
	private String zbearb = "";
	private String dsttel = ""; 
	private String vertzb = "";
	private String empzb  = "";
	private String kzrebe = "";
	private String kzgein = "";
	//rebhza
	
	public TsAB1() {
		tsTyp = "AB1";
    }

    public void setFields(String[] fields) { 
		int size = fields.length;
		//String ausgabe = "FSS: "+ fields[0] + " size = " + size;
		//Utils.log( ausgabe);
					
		if (size < 1) { return; }		
		tsTyp = fields[0];
        if (size < 2) { return;	}
        ab1bnr = fields[1];
        if (size < 3) { return;	}
        regnr  = fields[2];        	
        if (size < 4) { return;	}	
        korant = fields[3];
        if (size < 5) { return;	}	
        regkz = fields[4];
        if (size < 6) { return;	}	
        lfdnr = fields[5];        
        if (size < 7) { return;	}	
        antart = fields[6];
        if (size < 8) { return;	}	
        wkz = fields[7];
        //TODO: bardat, sicdat
        if (size < 9) { return;	}	
        abgdat = fields[8];        
        if (size < 10) { return; }	
        erfdat = fields[9];
        if (size < 11) { return; }	
        abes = fields[10];
        if (size < 12) { return; }	
        kzvtr = fields[11];
        //TODO: kzkbg, kzzaab, kzzaau, kzzasi
        if (size < 13) { return; }	
        anmzb = fields[12];
        if (size < 1) { return; }	
        dstnr = fields[1];
        if (size < 1) { return; }	
        dstnam = fields[1];
        if (size < 1) { return; }
    	zbearb = fields[1];
    	if (size < 1) { return; }	
        dsttel = fields[1];
        if (size < 1) { return; }	
    	vertzb = fields[1];
    	if (size < 1) { return; }	
    	empzb  = fields[1];
    	if (size < 1) { return; }	
    	kzrebe = fields[1];
    	if (size < 1) { return; }	
    	kzgein = fields[1];
    	//TODO: rebhza
    }

    public String teilsatzBilden() {
    	StringBuffer buff = new StringBuffer();

        buff.append(tsTyp);
        buff.append(trenner);
        buff.append(ab1bnr);
        buff.append(trenner);
        buff.append(regnr);
        buff.append(trenner);
        buff.append(korant);
        buff.append(trenner);
        buff.append(regkz);
        buff.append(trenner);
        buff.append(lfdnr);
        buff.append(trenner);
        buff.append(antart);
        buff.append(trenner);
        buff.append(wkz);
        buff.append(trenner);
        //TODO: bardat, sicdat
        buff.append(abgdat);
        buff.append(trenner);
        buff.append(erfdat);
        buff.append(trenner);
        buff.append(abes);
        buff.append(trenner);          
        buff.append(kzvtr);
        buff.append(trenner);    
        //TODO: kzkbg, kzzaab, kzzaau, kzzasi
        buff.append(anmzb);
        buff.append(trenner);
        buff.append(dstnr);
        buff.append(trenner);
        buff.append(dstnam);
        buff.append(trenner);        
        buff.append(zbearb);
        buff.append(trenner);
        buff.append(dsttel);
        buff.append(trenner);
        buff.append(vertzb);
        buff.append(trenner);
        buff.append(empzb);
        buff.append(trenner);
        buff.append(kzrebe);
        buff.append(trenner);        
        buff.append(kzgein);
        buff.append(trenner);      
    	//rebhza
        
        return new String(buff);
      }

    public void setAb1bnr(String ab1bnr) {
		this.ab1bnr = Utils.checkNull(ab1bnr);
	}

	public String getAb1bnr() {
		return ab1bnr;	
    }

	public String getRegnr() {
		return regnr;	
	}

	public void setRegnr(String regnr) {
		this.regnr = Utils.checkNull(regnr);
	}
	
	public String getKorant() {
		return korant;	
	}

	public void setKorant(String korant) {
		this.korant = Utils.checkNull(korant);
	}

	public String getRegkz() {
		return regkz;	
	}

	public void setRegkz(String regkz) {
		this.regkz = Utils.checkNull(regkz);
	}

	public String getLfdnr() {
		return lfdnr;	
	}

	public void setLfdnr(String lfdnr) {
		this.lfdnr = Utils.checkNull(lfdnr);
	}

	public String getAntart() {
		return antart;	
	}

	public void setAntart(String antart) {
		this.antart = Utils.checkNull(antart);
	}

	public String getWkz() {
		return wkz;	
	}

	public void setWkz(String wkz) {
		this.wkz = Utils.checkNull(wkz);
	}

	public void setAbgdat(String value) {
		this.abgdat = Utils.checkNull(value);
	}

	public String getAbgdat() {
		return abgdat;	
	}	
	
	public void setErfdat(String value) {
		this.erfdat = Utils.checkNull(value);
	}

	public String getErfdat() {
		return erfdat;	
	}
	
	public void setAbes(String value) {
		this.abes = Utils.checkNull(value);
	}

	public String getAbes() {
		return abes;	
	}
		
	public String getKzvtr() {
		return kzvtr;
	}

	public void setKzvtr(String kzvtr) {
		this.kzvtr = kzvtr;
	}

	public String getAnmzb() {
		return anmzb;
	}

	public void setAnmzb(String anmzb) {
		this.anmzb = anmzb;
	}

	public String getDstnr() {
		return dstnr;
	}

	public void setDstnr(String dstnr) {
		this.dstnr = dstnr;
	}

	public String getDstnam() {
		return dstnam;
	}

	public void setDstnam(String dstnam) {
		this.dstnam = dstnam;
	}

	public String getZbearb() {
		return zbearb;
	}

	public void setZbearb(String zbearb) {
		this.zbearb = zbearb;
	}

	public String getDsttel() {
		return dsttel;
	}

	public void setDsttel(String dsttel) {
		this.dsttel = dsttel;
	}

	public String getVertzb() {
		return vertzb;
	}

	public void setVertzb(String vertzb) {
		this.vertzb = vertzb;
	}

	public String getEmpzb() {
		return empzb;
	}

	public void setEmpzb(String empzb) {
		this.empzb = empzb;
	}

	public String getKzrebe() {
		return kzrebe;
	}

	public void setKzrebe(String kzrebe) {
		this.kzrebe = kzrebe;
	}

	public String getKzgein() {
		return kzgein;
	}

	public void setKzgein(String kzgein) {
		this.kzgein = kzgein;
	}

	public boolean isEmpty() {
		if ( Utils.isStringEmpty(regnr) &&
			 Utils.isStringEmpty(korant) &&
			 Utils.isStringEmpty(regkz) &&
			 Utils.isStringEmpty(lfdnr) &&
			 Utils.isStringEmpty(antart) && 
			 Utils.isStringEmpty(wkz)) {			 			
			return true;			
		} else {
			return false;
		}
	}

}	

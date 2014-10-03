package com.kewill.kcx.component.mapping.formats.fss.edec.Import.subsets.V70;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: EDEC Import 70<br>
 * Created		: 06.11.2012<br>
 * Description  : FSS Definition of Subset: Positionssatzes des Abgabenbescheids. 
 * 
 * @author iwaniuk
 * @version 7.0.00
 */

public class TsABP extends Teilsatz {
		
	private String regkz  = "";		 
	private String lfdnr  = "";		
	private String posnr  = "";		
	private String abus   = "";		  		
	private String abgru  = "";
	//TODO	
	private String abbe   = "";	
	//TODO	
	private String vsme   = "";	 
	//TODO	
	private String vscd   = "";	
	private String vsgp   = "";	
	private String vsmg   = ""; 
	//TODO	
	private String kzbgr1 = ""; 
	//TODO	
	
	public TsABP() {
		tsTyp = "ABP";
    }

    public void setFields(String[] fields) { 
		int size = fields.length;
		//String ausgabe = "FSS: "+ fields[0] + " size = " + size;
		//Utils.log( ausgabe);
					
		if (size < 1) { return; }		
		tsTyp = fields[0];
        if (size < 2) { return;	}
        regkz = fields[1];
        if (size < 3) { return;	}
        lfdnr  = fields[2];        	
        if (size < 4) { return;	}	
        posnr = fields[3];        
        if (size < 5) { return;	}	
        abus = fields[4];
        if (size < 6) { return;	}	
        abgru = fields[5];  
        //TODO
        if (size < 7) { return;	}	
        abbe = fields[6];
        if (size < 8) { return;	}
        vsme = fields[7];       
        //TODO
        if (size < 9) { return;	}	
        vscd = fields[8];        
        if (size < 10) { return; }	
        vsgp = fields[9];
        if (size < 11) { return; }	
        vsmg = fields[10];
        //TODO
        if (size < 12) { return; }	
        kzbgr1 = fields[11];
        //TODO
    }

    public String teilsatzBilden() {
    	StringBuffer buff = new StringBuffer();

        buff.append(tsTyp);
        buff.append(trenner);
        buff.append(regkz);
        buff.append(trenner);
        buff.append(lfdnr);
        buff.append(trenner);
        buff.append(posnr);        
        buff.append(trenner);      
        buff.append(abus);
        buff.append(trenner);
        buff.append(abgru);       
        buff.append(trenner);
        //TODO
        buff.append(abbe);    
        buff.append(trenner);
         //TODO
        buff.append(vsme);
        buff.append(trenner);
        //TODO
        buff.append(vscd);
        buff.append(trenner);
        buff.append(vsgp);
        buff.append(trenner);
        buff.append(vsmg);
        buff.append(trenner);
        //TODO
        buff.append(kzbgr1);
        buff.append(trenner);
        //TODO
        return new String(buff);
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
	
	public String getPosnr() {
		return posnr;
	}

	public void setPosnr(String posnr) {
		this.posnr = posnr;
	}

	public String getAbus() {
		return abus;
	}

	public void setAbus(String abus) {
		this.abus = abus;
	}

	public String getAbgru() {
		return abgru;
	}

	public void setAbgru(String abgru) {
		this.abgru = abgru;
	}

	public String getAbbe() {
		return abbe;
	}

	public void setAbbe(String abbe) {
		this.abbe = abbe;
	}

	public String getVsme() {
		return vsme;
	}

	public void setVsme(String vsme) {
		this.vsme = vsme;
	}

	public String getVscd() {
		return vscd;
	}

	public void setVscd(String vscd) {
		this.vscd = vscd;
	}

	public String getVsgp() {
		return vsgp;
	}

	public void setVsgp(String vsgp) {
		this.vsgp = vsgp;
	}

	public String getVsmg() {
		return vsmg;
	}

	public void setVsmg(String vsmg) {
		this.vsmg = vsmg;
	}

	public String getKzbgr1() {
		return kzbgr1;
	}

	public void setKzbgr1(String kzbgr1) {
		this.kzbgr1 = kzbgr1;
	}

	public boolean isEmpty() {
		return ( Utils.isStringEmpty(regkz) &&
			 Utils.isStringEmpty(lfdnr) &&
			 Utils.isStringEmpty(posnr) &&
			 Utils.isStringEmpty(abus) &&
			 Utils.isStringEmpty(abgru) && 
			 Utils.isStringEmpty(abbe) && 
			 Utils.isStringEmpty(vsme) && 
			 Utils.isStringEmpty(vscd) && 
			 Utils.isStringEmpty(vsgp) && 			 
			 Utils.isStringEmpty(kzbgr1));			 				
	}

}	

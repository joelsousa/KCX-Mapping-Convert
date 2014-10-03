package com.kewill.kcx.component.mapping.formats.fss.edec.Import.subsets.V70;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: EDEC Import 70<br>
 * Created		: 06.11.2012<br>
 * Description  : FSS Definition of Subset: AB2 Abgabendaten. 
 * 
 * @author iwaniuk
 * @version 7.0.00
 */

public class TsAB2 extends Teilsatz {
		
	private String regkz  = "";		 
	private String lfdnr  = "";		
	private String regdat = "";	
	//TODO	
	private String atnkz  = "";		  		
	private String anehm  = "";	 
	private String azbnr  = "";	 
	private String akto   = "";	 
	//TODO
	private String zaart  = "";	
	private String abgb  = ""; 
	//TODO	
	
	public TsAB2() {
		tsTyp = "AB2";
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
        regdat = fields[3];
        //TODO
        if (size < 5) { return;	}	
        atnkz = fields[4];
        if (size < 6) { return;	}	
        anehm = fields[5];        
        if (size < 7) { return;	}	
        azbnr = fields[6];
        if (size < 8) { return;	}	
        akto = fields[7];
        //TODO: 
        if (size < 9) { return;	}	
        zaart = fields[8];        
        if (size < 10) { return; }	
        abgb = fields[9];
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
        buff.append(regdat);        
        buff.append(trenner);
        //TODO
        buff.append(atnkz);
        buff.append(trenner);
        buff.append(anehm);
        buff.append(trenner);
        buff.append(azbnr);
        buff.append(trenner);
        buff.append(akto);
        buff.append(trenner);
        //TODO
        buff.append(zaart);
        buff.append(trenner);
        buff.append(abgb);
        buff.append(trenner);
        //TODO
       
        return new String(buff);
      }
    
	public String getRegdat() {
		return regdat;	
	}

	public void setRegdat(String korant) {
		this.regdat = Utils.checkNull(korant);
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

	public String getAnehm() {
		return anehm;	
	}

	public void setAnehm(String antart) {
		this.anehm = Utils.checkNull(antart);
	}

	public String getAtnkz() {
		return atnkz;	
	}

	public void setAtnkz(String atnkz) {
		this.atnkz = Utils.checkNull(atnkz);
	}

	public void setAbgb(String value) {
		this.abgb = Utils.checkNull(value);
	}

	public String getAbgb() {
		return abgb;	
	}	
	
	public void setAzbnr(String value) {
		this.azbnr = Utils.checkNull(value);
	}

	public String getAzbnr() {
		return azbnr;	
	}
	
	public void setAkto(String value) {
		this.akto = Utils.checkNull(value);
	}

	public String getAkto() {
		return akto;	
	}
			
	public boolean isEmpty() {
		return ( Utils.isStringEmpty(regkz) &&
			 Utils.isStringEmpty(lfdnr) &&
			 Utils.isStringEmpty(regdat) &&
			 Utils.isStringEmpty(atnkz) &&
			 Utils.isStringEmpty(anehm) && 
			 Utils.isStringEmpty(azbnr) && 
			 Utils.isStringEmpty(akto) && 
			 Utils.isStringEmpty(zaart) && 
			 Utils.isStringEmpty(abgb));			 				
	}

}	

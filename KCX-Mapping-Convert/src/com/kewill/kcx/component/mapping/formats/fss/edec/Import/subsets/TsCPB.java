package com.kewill.kcx.component.mapping.formats.fss.edec.Import.subsets;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;
 
/**
 * Module		: EDEC Import 70<br>
 * Created		: 29.10.2012<br>
 * Description  : FSS Definition of Ergänzungsdaten Bewilligungen. 
 * 
 * @author iwaniuk
 * @version 7.0.00
 */

public class TsCPB extends Teilsatz { 
	private String refnr  = "";      //Referencenumber
	private String posnr  = "";		 //Itemnumber
	private String bwtyp  = "";		 
	private String bwstel = "";		
	private String bwttyp = "";	 
	private String bwnr   = "";	 
	private String bwdat  = "";	 
	private String bwzus  = "";	
	
	public TsCPB() {
        tsTyp = "CPB";
    }

    public void setFields(String[] fields) {
    	
		int size = fields.length;
		//String ausgabe = "FSS: " + fields[0] + " size = " + size;
		//Utils.log( ausgabe);			
		
		if (size < 1) { return; }		
        	tsTyp = fields[0];
        if (size < 2) { return; }
        	refnr = fields[1];
        if (size < 3) { return; }	
        	posnr = fields[2];
        if (size < 4) { return; }
        	bwtyp = fields[3];
        if (size < 5) { return; }
        	bwstel = fields[4];        
        if (size < 6) { return; }
        	bwttyp = fields[5];
        if (size < 7) { return; }
        	bwnr = fields[6];
        if (size < 8) { return; }
       		bwdat = fields[7];
        if (size < 9) { return; }
        	bwzus = fields[8];
      
    }

    public String teilsatzBilden() {
        StringBuffer buff = new StringBuffer();

        buff.append(tsTyp);
        buff.append(trenner);
        buff.append(refnr);
        buff.append(trenner);
        buff.append(posnr);
        buff.append(trenner);
        buff.append(bwtyp);
        buff.append(trenner);
        buff.append(bwstel);
        buff.append(trenner);
        buff.append(bwttyp);
        buff.append(trenner);
        buff.append(bwnr);
        buff.append(trenner);
        buff.append(bwdat);
        buff.append(trenner);
        buff.append(bwzus);
        buff.append(trenner);      
        
        return new String(buff);
      }

    public void setRefnr(String beznr) {
		this.refnr = Utils.checkNull(beznr);
	}
	public String getRefnr() {
		return refnr;	
	}

	public String getPosnr() {
		return posnr;	
	}
	public void setPosnr(String posnr) {
		this.posnr = Utils.checkNull(posnr);
	}

	public String getBwtyp() {
		return bwtyp;	
	}
	public void setBwtyp(String bwtyp) {
		this.bwtyp = Utils.checkNull(bwtyp);
	}

	public String getBwstel() {
		return bwstel;	
	}
	public void setBwstel(String bwstel) {
		this.bwstel = Utils.checkNull(bwstel);
	}

	public String getBwttyp() {
		return bwttyp;	
	}
	public void setBwttyp(String bwttyp) {
		this.bwttyp = Utils.checkNull(bwttyp);
	}

	public String getBwnr() {
		return bwnr;	
	}
	public void setBwnr(String bwnr) {
		this.bwnr = Utils.checkNull(bwnr);
	}

	public String getBwdat() {
		return bwdat;	
	}

	public void setBwdat(String bwdat) {
		this.bwdat = Utils.checkNull(bwdat);
	}

	public String getBwzus() {
		return bwzus;	
	}
	public void setBwzus(String bwzus) {
		this.bwzus = Utils.checkNull(bwzus);
	}
	
	public boolean isEmpty() {
		return Utils.isStringEmpty(bwtyp)&&
			Utils.isStringEmpty(bwstel) &&
			Utils.isStringEmpty(bwttyp) &&
			Utils.isStringEmpty(bwnr) &&
			Utils.isStringEmpty(bwdat) &&			
			Utils.isStringEmpty(bwzus);
	}
	

}	

package com.kewill.kcx.component.mapping.formats.fss.edec.Import.subsets;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: EDEC Import 70<br>
 * Created		: 05.11.2012<br>
 * Description  : FSS Definition of Ergaenzungsdaten Veredelung. 
 * 
 * @author iwaniuk
 * @version 7.0.00
 */

public class TsCPE extends Teilsatz {
	private String refnr    = "";      //Referencenumber
	private String posnr    = "";		 //Itemnumber
	private String rrdir    = "";		
	private String rretyp   = "";		
	private String rrvtyp   = "";	
	private String rrkzfp   = "";	
	private String rratyp   = "";	
	private String rrptyp   = "";	 
	private String rrexwtfw = "";	
	private String rrexwtwg = "";	
	private String rrexwtku = "";	
	private String rrexwtfk = "";	
	private String rrexwt   = "";	
	private String rrlokofw = "";
	private String rrlokowg = "";
	private String rrlokoku = "";
	private String rrlokofk = "";
	private String rrloko   = "";
	private String rrfrkofw = "";
	private String rrfrkowg = "";
	private String rrfrkoku = "";
	private String rrfrkofk = "";
	private String rrfrko   = "";
	private String rrmawtfw = "";
	private String rrmawtwg = "";
	private String rrmawtku = "";
	private String rrmawtfk = "";
	private String rrmawt   = "";
	private String dstnr    = "";
	
	public TsCPE() {
        tsTyp = "CPE";
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
        	rrdir = fields[3];
        if (size < 5) { return; }
        	rretyp = fields[4];        
        if (size < 6) { return; }
        	rrvtyp = fields[5];
        if (size < 7) { return; }
        	rratyp = fields[6];
        if (size < 8) { return; }      
        	rrkzfp = fields[7];      
        if (size < 9) { return; }
       		rrptyp = fields[8];
        if (size < 10) { return; }
        	rrexwtfw = fields[9];
        if (size < 11) { return; }
       		rrexwtwg = fields[10];       	
       	if (size < 12) { return; }		
       		rrexwtku = fields[11];
        if (size < 13) { return; }
        	rrexwtfk = fields[12];
        if (size < 14) { return; }	
        	rrexwt = fields[13];
        if (size < 15) { return; }
        	rrlokofw = fields[14];
        if (size < 16) { return; }
        	rrlokowg = fields[15];        
        if (size < 17) { return; }
        	rrlokoku = fields[16];
        if (size < 18) { return; }
        	rrlokofk = fields[17];
        if (size < 19) { return; }
        	rrloko = fields[18];
        if (size < 20) { return; }
        	rrfrkofw = fields[19];       
        if (size < 21) { return; }
        	rrfrkowg = fields[20];
       	if (size < 22) { return; }		
       		rrfrkoku = fields[21];
        if (size < 23) { return; }
        	rrfrkofk = fields[22];
        if (size < 24) { return; }	
        	rrfrko = fields[23];
        if (size < 25) { return; }
        	rrmawtfw = fields[24];
        if (size < 26) { return; }
        	rrmawtwg = fields[25];        
        if (size < 27) { return; }
        	rrmawtku = fields[26];
        if (size < 28) { return; }
        	rrmawtfk = fields[27];
        if (size < 29) { return; }
        	rrmawt = fields[28];
        if (size < 30) { return; }
        	dstnr = fields[29];
      
    }

    public String teilsatzBilden() {
        StringBuffer buff = new StringBuffer();

        buff.append(tsTyp);
        buff.append(trenner);
        buff.append(refnr);
        buff.append(trenner);
        buff.append(posnr);
        buff.append(trenner);
        buff.append(rrdir);
        buff.append(trenner);
        buff.append(rretyp);
        buff.append(trenner);
        buff.append(rrvtyp);
        buff.append(trenner);
        buff.append(rratyp); 
        buff.append(trenner);
        buff.append(rrkzfp); 
        buff.append(trenner);
        buff.append(rrptyp);
        buff.append(trenner);
        buff.append(rrexwtfw);
        buff.append(trenner);
        buff.append(rrexwtwg);
        buff.append(trenner);
        buff.append(rrexwtku);
        buff.append(trenner);
        buff.append(rrexwtfk);
        buff.append(trenner);
        buff.append(rrexwt);
        buff.append(trenner);
        buff.append(rrlokofw);
        buff.append(trenner);
        buff.append(rrlokowg);
        buff.append(trenner);
        buff.append(rrlokoku);
        buff.append(trenner);
        buff.append(rrlokofk);
        buff.append(trenner);
        buff.append(rrloko);
        buff.append(trenner);
        buff.append(rrfrkofw);
        buff.append(trenner);
        buff.append(rrfrkowg);
        buff.append(trenner);
        buff.append(rrfrkoku);
        buff.append(trenner);
        buff.append(rrfrkofk);
        buff.append(trenner);
        buff.append(rrfrko);
        buff.append(trenner);
        buff.append(rrmawtfw);
        buff.append(trenner);
        buff.append(rrmawtwg);
        buff.append(trenner);
        buff.append(rrmawtku);
        buff.append(trenner);
        buff.append(rrmawtfk);
        buff.append(trenner);
        buff.append(rrmawt);
        buff.append(trenner);
        buff.append(dstnr);
        buff.append(trenner);
        
        return new String(buff);    	
      }

    public void setRefnr(String refnr) {
		this.refnr = Utils.checkNull(refnr);
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

	public String getRrdir() {
		return rrdir;	
	}
	public void setRrdir(String rrdir) {
		this.rrdir = Utils.checkNull(rrdir);
	}

	public String getRretyp() {
		return rretyp;	
	}
	public void setRretyp(String rretyp) {
		this.rretyp = Utils.checkNull(rretyp);
	}

	public String getRrvtyp() {
		return rrvtyp;	
	}
	public void setRrvtyp(String rrvtyp) {
		this.rrvtyp = Utils.checkNull(rrvtyp);
	}

	public String getRratyp() {
		return rratyp;	
	}
	public void setRratyp(String rratyp) {
		this.rratyp = Utils.checkNull(rratyp);
	}
	
	public String getRrkzfp() {
		return rrkzfp;	
	}
	public void setRrkzfp(String rratyp) {
		this.rrkzfp = Utils.checkNull(rratyp);
	}
	
	public String getRrptyp() {
		return rrptyp;	
	}

	public void setRrptyp(String rrptyp) {
		this.rrptyp = Utils.checkNull(rrptyp);
	}

	public String getRrexwtfw() {
		return rrexwtfw;	
	}
	public void setRrexwtfw(String rrexwtfw) {
		this.rrexwtfw = Utils.checkNull(rrexwtfw);
	}

	public String getRrexwtwg() {
		return rrexwtwg;	
	}
	public void setRrexwtwg(String rrexwtwg) {
		this.rrexwtwg = Utils.checkNull(rrexwtwg);
	}

	public String getRrexwtku() {
		return rrexwtku;
	}

	public void setRrexwtku(String rrexwtku) {
		this.rrexwtku = rrexwtku;
	}

	public String getRrexwtfk() {
		return rrexwtfk;
	}

	public void setRrexwtfk(String rrexwtfk) {
		this.rrexwtfk = rrexwtfk;
	}

	public String getRrexwt() {
		return rrexwt;
	}

	public void setRrexwt(String rrexwt) {
		this.rrexwt = rrexwt;
	}

	public String getRrlokofw() {
		return rrlokofw;
	}

	public void setRrlokofw(String rrlokofw) {
		this.rrlokofw = rrlokofw;
	}

	public String getRrlokowg() {
		return rrlokowg;
	}

	public void setRrlokowg(String rrlokowg) {
		this.rrlokowg = rrlokowg;
	}

	public String getRrlokoku() {
		return rrlokoku;
	}

	public void setRrlokoku(String rrlokoku) {
		this.rrlokoku = rrlokoku;
	}

	public String getRrlokofk() {
		return rrlokofk;
	}

	public void setRrlokofk(String rrlokofk) {
		this.rrlokofk = rrlokofk;
	}

	public String getRrloko() {
		return rrloko;
	}

	public void setRrloko(String rrloko) {
		this.rrloko = rrloko;
	}

	public String getRrfrkofw() {
		return rrfrkofw;
	}

	public void setRrfrkofw(String rrfrkofw) {
		this.rrfrkofw = rrfrkofw;
	}

	public String getRrfrkowg() {
		return rrfrkowg;
	}

	public void setRrfrkowg(String rrfrkowg) {
		this.rrfrkowg = rrfrkowg;
	}

	public String getRrfrkoku() {
		return rrfrkoku;
	}

	public void setRrfrkoku(String rrfrkoku) {
		this.rrfrkoku = rrfrkoku;
	}

	public String getRrfrkofk() {
		return rrfrkofk;
	}

	public void setRrfrkofk(String rrfrkofk) {
		this.rrfrkofk = rrfrkofk;
	}

	public String getRrfrko() {
		return rrfrko;
	}

	public void setRrfrko(String rrfrko) {
		this.rrfrko = rrfrko;
	}

	public String getRrmawtfw() {
		return rrmawtfw;
	}

	public void setRrmawtfw(String rrmawtfw) {
		this.rrmawtfw = rrmawtfw;
	}

	public String getRrmawtwg() {
		return rrmawtwg;
	}

	public void setRrmawtwg(String rrmawtwg) {
		this.rrmawtwg = rrmawtwg;
	}

	public String getRrmawtku() {
		return rrmawtku;
	}

	public void setRrmawtku(String rrmawtku) {
		this.rrmawtku = rrmawtku;
	}

	public String getRrmawtfk() {
		return rrmawtfk;
	}

	public void setRrmawtfk(String rrmawtfk) {
		this.rrmawtfk = rrmawtfk;
	}

	public String getRrmawt() {
		return rrmawt;
	}

	public void setRrmawt(String rrmawt) {
		this.rrmawt = rrmawt;
	}

	public String getDstnr() {
		return dstnr;
	}

	public void setDstnr(String dstnr) {
		this.dstnr = dstnr;
	}

	public boolean isEmpty() {
		return Utils.isStringEmpty(rrdir) &&
			Utils.isStringEmpty(rretyp) &&
			Utils.isStringEmpty(rrvtyp) &&
			Utils.isStringEmpty(rratyp) &&
			Utils.isStringEmpty(rrptyp) &&
			Utils.isStringEmpty(rrexwtfw) &&
			Utils.isStringEmpty(rrexwtwg);
	}
	

}	

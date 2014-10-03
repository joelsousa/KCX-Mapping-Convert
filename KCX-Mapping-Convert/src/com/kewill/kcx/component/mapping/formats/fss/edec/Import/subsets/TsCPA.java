package com.kewill.kcx.component.mapping.formats.fss.edec.Import.subsets;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: EDEC Import 70<br>
 * Created		: 05.11.2012<br>
 * Description  : FSS Definition of Ergaenzungssatz Zusatzabgaben. 
 * 
 * @author iwaniuk
 * @version 7.0.00
 */
public class TsCPA extends Teilsatz {
	private String refnr   = "";         //Referencenumber
	private String posnr   = "";		 //Itemnumber
	private String art     = "";		 //
	private String abgart  = "";		 //
	private String abgschl = "";	
	private String abgmg   = "";	 
	private String abgalk  = "";	 
	private String gbbtg   = "";	 
	private String gbbtgrc = "";	 
	
	
	public TsCPA() {
        tsTyp = "CPA";
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
        	art = fields[3];
        if (size < 5) { return; }
        	abgart = fields[4];        
        if (size < 6) { return; }
        	abgschl = fields[5];
       if (size < 7) { return; }
        	abgmg = fields[6];
       if (size < 8) { return; }
       		abgalk = fields[7];
       if (size < 9) { return; }
        	gbbtg = fields[8];
       if (size < 10) { return; }
       		gbbtgrc = fields[9];
    }

    public String teilsatzBilden() {
        StringBuffer buff = new StringBuffer();

        buff.append(tsTyp);
        buff.append(trenner);
        buff.append(refnr);
        buff.append(trenner);
        buff.append(posnr);
        buff.append(trenner);
        buff.append(art);
        buff.append(trenner);
        buff.append(abgart);
        buff.append(trenner);
        buff.append(abgschl);
        buff.append(trenner);
        buff.append(abgmg);
        buff.append(trenner);
        buff.append(abgalk);
        buff.append(trenner);
        buff.append(gbbtg);
        buff.append(trenner);
        buff.append(gbbtgrc);
        buff.append(trenner);
        
        return new String(buff);
      }

    public void setRefnr(String beznr) {
		this.refnr = Utils.checkNull(beznr);
	}
	public String getrefnr() {
		return refnr;	
	}

	public String getPosnr() {
		return posnr;	
	}
	public void setPosnr(String posnr) {
		this.posnr = Utils.checkNull(posnr);
	}

	public String getArt() {
		return art;	
	}
	public void setArt(String art) {
		this.art = Utils.checkNull(art);
	}

	public String getAbgart() {
		return abgart;	
	}
	public void setAbgart(String abgart) {
		this.abgart = Utils.checkNull(abgart);
	}

	public String getAbgschl() {
		return abgschl;	
	}
	public void setAbgschl(String abgschl) {
		this.abgschl = Utils.checkNull(abgschl);
	}

	public String getAbgmg() {
		return abgmg;	
	}
	public void setAbgmg(String abgmg) {
		this.abgmg = Utils.checkNull(abgmg);
	}

	public String getAbgalk() {
		return abgalk;	
	}

	public void setAbgalk(String abgalk) {
		this.abgalk = Utils.checkNull(abgalk);
	}

	public String getGbbtg() {
		return gbbtg;	
	}
	public void setGbbtg(String gbbtg) {
		this.gbbtg = Utils.checkNull(gbbtg);
	}

	public String getGbbtgrc() {
		return gbbtgrc;	
	}
	public void setGbbtgrc(String gbbtgrc) {
		this.gbbtgrc = Utils.checkNull(gbbtgrc);
	}

	public boolean isEmpty() {
		return Utils.isStringEmpty(art)&&
			Utils.isStringEmpty(abgart)&&
			Utils.isStringEmpty(abgschl)&&
			Utils.isStringEmpty(abgmg)&&
			Utils.isStringEmpty(abgalk)&&
			Utils.isStringEmpty(gbbtg)&&
			Utils.isStringEmpty(gbbtgrc);
	}
	

}	

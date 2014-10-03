package com.kewill.kcx.component.mapping.formats.fss.Port.subsets.V71; 

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module       : Port
 * Created		: 04.10.2013<br>
 * Description	: BHT Hafenauftrag Position Zusatzangaben.
 * 
 * @author iwaniuk
 * @version 7.1.00
 */

public class TsPOB extends Teilsatz {

    private String beznr	= "";	 // Bezugsnummer
    private String posnr   = "";	 // 
    private String btncod	= "";	 // BTN-Code   
    private String hamcod	= "";	 // Harmonized-Code
    private String inhart	= "";	 // Pos-Inhalt-Art str1
    private String uspcod	= "";	 // USP-Code str2
    private String mavptx	= "";	 // Manifest-Verpackung-Text
    private String umshw1 	= "";	 // Umschlaghinweis-2-Code
    private String umshw2	= "";	 // Umschlaghinweis-3-Code
    private String kzklcl 	= "";	 // Konv-oder-LCL-Code str1 (L-lcl,B-barge,K-konventionel
    private String artmar 	= "";	 // Art der Markierung str1: B=Bestandsmarkierung, V=Verlademarkierung
    private String tempe 	= "";    //Tempratur-Einheit: C, F, K
    private String tempu 	= "";    // Temperatur Wert Untergrenze
    private String tempo 	= "";	 // Temperatur Wert Obergrenze
    private String mod 		= "";	 // Kühlart: K = Kühler, H = Heizer C = Conair

   
    public TsPOB() {
	    tsTyp = "POB";
    }

    public void setFields(String[] fields) {
    	int size = fields.length;
    	//String ausgabe = "FSS: " + fields[0] + " size = " + size;

    	if (size < 1) { return; }
    	tsTyp = fields[0];
	    if (size < 2) { return; }
	    beznr = fields[1];
	    if (size < 3) { return; }
	    posnr = fields[2];
	    if (size < 4) { return; }
	    btncod = fields[3];
	    if (size < 5) { return; }
	    hamcod = fields[4];
	    if (size < 6) { return; }
	    inhart = fields[5];
	    if (size < 7) { return; }
	    uspcod = fields[6];
	    if (size < 8) { return; }
	    mavptx = fields[7];
	    if (size < 9) { return; }
	    umshw1 = fields[8];
	    if (size < 10) { return; }
	    umshw2 = fields[9];
	    if (size < 11) { return; }
	    kzklcl = fields[10];
	    if (size < 12) { return; }
	    artmar = fields[11];
	    if (size < 13) { return; }
	    tempe = fields[12];
	    if (size < 14) { return; }
	    tempu = fields[13];
	    if (size < 15) { return; }
	    tempo = fields[14];
	    if (size < 16) { return; }
	    mod = fields[15];
	    
    }

    public String teilsatzBilden() {
    	StringBuffer buff = new StringBuffer();

    	buff.append(tsTyp);
    	buff.append(trenner);
    	buff.append(beznr);
    	buff.append(trenner);
    	buff.append(posnr);
    	buff.append(trenner);
    	buff.append(btncod);
    	buff.append(trenner);
    	buff.append(hamcod);
    	buff.append(trenner);
    	buff.append(inhart);    	
    	buff.append(trenner);
    	buff.append(uspcod);
    	buff.append(trenner);
    	buff.append(mavptx);
    	buff.append(trenner);
    	buff.append(umshw1);
    	buff.append(trenner);
    	buff.append(umshw2);
    	buff.append(trenner);
    	buff.append(kzklcl);
    	buff.append(trenner);
    	buff.append(artmar);
    	buff.append(trenner);
    	buff.append(tempe);
    	buff.append(trenner);
    	buff.append(tempu);    	
    	buff.append(trenner);
    	buff.append(tempo);
    	buff.append(trenner);
    	buff.append(mod);
    	buff.append(trenner);
 
    	return new String(buff);    
    }
    
    public String getBeznr() {
    	 return beznr;
    }
    public void setBeznr(String argument) {
    	this.beznr = Utils.checkNull(argument);
    }
 
    public String getPosnr() {
		return posnr;
	}
	public void setPosnr(String posnr) {
		this.posnr = Utils.checkNull(posnr);
	}

	public String getBtncod() {
		return btncod;
	}
	public void setBtncod(String btncod) {
		this.btncod = Utils.checkNull(btncod);
	}

	public String getHamcod() {
		return hamcod;
	}
	public void setHamcod(String hamcod) {
		this.hamcod = Utils.checkNull(hamcod);
	}

	public String getInhart() {
		return inhart;
	}
	public void setInhart(String inhart) {
		this.inhart = Utils.checkNull(inhart);
	}

	public String getUspcod() {
		return uspcod;
	}
	public void setUspcod(String uspcod) {
		this.uspcod = Utils.checkNull(uspcod);
	}

	public String getMavptx() {
		return mavptx;
	}
	public void setMavptx(String mavptx) {
		this.mavptx = Utils.checkNull(mavptx);
	}

	public String getUmshw1() {
		return umshw1;
	}
	public void setUmshw1(String umshw1) {
		this.umshw1 = Utils.checkNull(umshw1);
	}

	public String getUmshw2() {
		return umshw2;
	}
	public void setUmshw2(String umshw2) {
		this.umshw2 = Utils.checkNull(umshw2);
	}

	public String getKzklcl() {
		return kzklcl;
	}
	public void setKzklcl(String kzklcl) {
		this.kzklcl = Utils.checkNull(kzklcl);
	}

	public String getArtmar() {
		return artmar;
	}
	public void setArtmar(String artmar) {
		this.artmar = Utils.checkNull(artmar);
	}

	public String getTempe() {
		return tempe;
	}
	public void setTempe(String tempe) {
		this.tempe = Utils.checkNull(tempe);
	}

	public String getTempu() {
		return tempu;
	}
	public void setTempu(String tempu) {
		this.tempu = Utils.checkNull(tempu);
	}

	public String getTempo() {
		return tempo;
	}
	public void setTempo(String tempo) {
		this.tempo = Utils.checkNull(tempo);
	}

	public String getMod() {
		return mod;
	}
	public void setMod(String mod) {
		this.mod = Utils.checkNull(mod);
	}

	public boolean isEmpty() {
	return  Utils.isStringEmpty(posnr) &&
    	Utils.isStringEmpty(btncod) &&
    	Utils.isStringEmpty(hamcod) &&
    	Utils.isStringEmpty(inhart) &&
    	Utils.isStringEmpty(uspcod) &&
    	Utils.isStringEmpty(mavptx) &&  //usw
    	Utils.isStringEmpty(artmar);
	
    }
}


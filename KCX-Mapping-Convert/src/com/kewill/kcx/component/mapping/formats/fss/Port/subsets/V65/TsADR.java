package com.kewill.kcx.component.mapping.formats.fss.Port.subsets.V65;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module       : Port
 * Created		: 21.10.2011<br>
 * Description	: Hafenauftrag. Adressangaben  / MsgPOR.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */ 

public class TsADR extends Teilsatz {

	private String beznr    = "";       // Bezugsnummer
	private String posnr	= "";	    // Positionsnummer
	private String pe2lfd	= "";	    // Laufende Nr. zu Ebene2
	private String pe3lfd	= "";	    // Laufende Nr. zu Ebene3
	private String typ      = "";      // Adresstyp
	private String name1    = "";      // Name1
	private String name2    = "";      // Name2
	private String name3    = "";      // Name3
	private String str      = "";      // Strasse
	private String ort      = "";      // Ort
	private String plz      = "";      // Postfach
	private String alpha    = "";      // Land
	
    public TsADR() {
	        tsTyp = "ADR";
    }
    
  //3 = Anmelder
  //4 = Empfänger
  //6 = Versender
  //7 = Mitteiler 

	public void setFields(String[] fields) {
		int size = fields.length;
		//String ausgabe = "FSS: " + fields[0] + " size = " + size;
		//Utils.log( ausgabe);
			
		if (size < 1) { return; }
		tsTyp = fields[0];
		if (size < 2) { return; }	
	    beznr = fields[1];
	    if (size < 3) { return; }	    
	    posnr = fields[3];
	    if (size < 4) { return; }
	    pe2lfd = fields[4];
	    if (size < 5) { return; }
	    pe3lfd = fields[5];
	    if (size < 6) { return; }
	    typ       = fields[2];
	    if (size < 7) { return; }
	    name1        = fields[3];
	    if (size < 7) { return; }
	    name2        = fields[4];
	    if (size < 6) { return; }
	    name3        = fields[5];
	    if (size < 7) { return; }
	    str         = fields[6];
	    if (size < 8) { return; }
	    ort		 = fields[7];
	    if (size < 9) { return; }
	    plz         = fields[8];
	    if (size < 10) { return; }
	    alpha         = fields[9];	    
	}

    public String teilsatzBilden() {
        StringBuffer buff = new StringBuffer();

        buff.append(tsTyp);
        buff.append(trenner);
        buff.append(beznr);
        buff.append(trenner);
        buff.append(posnr);
    	buff.append(trenner);
    	buff.append(pe2lfd);
    	buff.append(trenner);
    	buff.append(pe3lfd);
    	buff.append(trenner);
        buff.append(typ);
        buff.append(trenner);
        buff.append(name1);
        buff.append(trenner);
        buff.append(name2);
        buff.append(trenner);
        buff.append(name3);
        buff.append(trenner);
        buff.append(str);
        buff.append(trenner);
        buff.append(ort);
        buff.append(trenner);
        buff.append(plz);
        buff.append(trenner);
        buff.append(alpha);
        buff.append(trenner);
       
        return new String(buff);
    }

	public String getBeznr() {
		return beznr;
	}
	public void setBeznr(String beznr) {
		this.beznr = Utils.checkNull(beznr);
	}
	
	public String getPosnr() {
		return posnr;
	}
	public void setPosnr(String posnr) {
		this.posnr = Utils.checkNull(posnr);
	}

	public String getTyp() {
		return typ;
	}
	public void setTyp(String typ) {
		this.typ = Utils.checkNull(typ);
	}

	public String getName1() {
		return name1;
	}

	public void setName1(String anam1) {
		this.name1 = Utils.checkNull(anam1);
	}

	public String getName2() {
		return name2;
	}

	public void setName2(String anam1) {
		this.name2 = Utils.checkNull(anam1);
	}
	
	public String getName3() {
		return name3;
	}

	public void setName3(String anam1) {
		this.name3 = Utils.checkNull(anam1);
	}
	
	public String getStr() {
		return str;
	}

	public void setStr(String astr) {
		this.str = Utils.checkNull(astr);
	}

	public String getPlz() {
		return plz;
	}

	public void setPlz(String apostf) {
		this.plz = Utils.checkNull(apostf);
	}

	public String getAlpha() {
		return alpha;
	}

	public void setAlpha(String alnd) {
		this.alpha = Utils.checkNull(alnd);
	}

	public String getOrt() {
		return ort;
	}

	public void setOrt(String aort) {
		this.ort = Utils.checkNull(aort);
	}
	
	public boolean isEmpty() {
		String name = name1 + name2 + name3;	
			
		return Utils.isStringEmpty(name) && 
			   Utils.isStringEmpty(str) && 
			   Utils.isStringEmpty(plz) &&			   
			   Utils.isStringEmpty(alpha) && 			  
			   Utils.isStringEmpty(ort);
	}
}
    

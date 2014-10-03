package com.kewill.kcx.component.mapping.formats.fss.Port.subsets.V65; 

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module       : Port
 * Created		: 24.10.2011<br>
 * Description	: Gefahrgut Klasse 7. 
 *
 * @author iwaniuk
 * @version 1.0.00
 */

public class TsAGX extends Teilsatz {

	 private String beznr	= "";	 // Bezugsnummer
	 private String posnr	= "";	 // Positionsnummer des Hafenauftrags 	 
	 private String pe2lfd	= "";	 // Laufende Nr. zu Ebene2
	 private String pe3lfd	= "";	 // Laufende Nr. zu Ebene3	
	 private String kat	    = "";	 // 
	 private String vptyp 	= "";	 // 
	 private String xraace	= "";	 // 
	 private String xratac	= "";	 // 
	 private String xratrw	= "";	 // 
	 private String xrakrw	= "";	 // 
	 private String blttnr	= "";	 //	 
	 
    public TsAGX() {
    	tsTyp = "AGX";
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
	    pe2lfd = fields[3];
	    if (size < 5) { return; }
	    pe3lfd = fields[4];
	    if (size < 6) { return; }
	    kat = fields[5];
	    if (size < 7) { return; }
	    vptyp = fields[6];
	    if (size < 8) { return; }
	    xraace = fields[7];
	    if (size < 9) { return; }
	    xratac = fields[8];
	    if (size < 10) { return; }
	    xratrw = fields[9];
	    if (size < 11) { return; }
	    xrakrw = fields[10];
	    if (size < 12) { return; }
	    blttnr = fields[11];	    
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
    	buff.append(kat);
    	buff.append(trenner);
    	buff.append(vptyp);
    	buff.append(trenner);
    	buff.append(xraace);
    	buff.append(trenner);
    	buff.append(xratac);
    	buff.append(trenner);
    	buff.append(xratrw);
    	buff.append(trenner);
    	buff.append(xrakrw);
    	buff.append(trenner);
    	buff.append(blttnr);
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

    public String getPe2lfd() {
    	 return pe2lfd;
    }
    public void setPe2lfd(String pe2lfd) {
    	this.pe2lfd = Utils.checkNull(pe2lfd);
    }

    public String getPe3lfd() {
    	 return pe3lfd;
    }
    public void setPe3lfd(String pe3lfd) {
    	this.pe3lfd = Utils.checkNull(pe3lfd);
    }

    public String getKat() {
    	 return kat;
    }
    public void setKat(String argument) {
    	this.kat = Utils.checkNull(argument);
    }
    
    public String getVptyp() {
   	 	return vptyp;
    }
    public void setVptyp(String argument) {
    	this.vptyp = Utils.checkNull(argument);
    }
   
    public String getXraace() {
   	 	return xraace;
    }
    public void setXraace(String argument) {
    	this.xraace = Utils.checkNull(argument);
    }
   
    public String getXratac() {
   	 	return xratac;
    }
    public void setXratac(String argument) {
    	this.xratac = Utils.checkNull(argument);
    }
       
    public String getXratrw() {
   	 	return xratrw;
    }
    public void setXratrw(String argument) {
    	this.xratrw = Utils.checkNull(argument);
    }
   
    public String getXrakrw() {
   	 	return xrakrw;
    }
    public void setXrakrw(String argument) {
    	this.xrakrw = Utils.checkNull(argument);
    }
    
    public String getBlttnr() {
   	 	return blttnr;
    }
    public void setBlttnr(String argument) {
    	this.blttnr = Utils.checkNull(argument);
    }
    
    public boolean isEmpty() {
	  return  Utils.isStringEmpty(kat) &&
		  Utils.isStringEmpty(vptyp) &&		 
		  Utils.isStringEmpty(xraace) &&
		  Utils.isStringEmpty(xratac) &&
		  Utils.isStringEmpty(xratrw) &&
		  Utils.isStringEmpty(xrakrw) &&		 
		  Utils.isStringEmpty(blttnr);
    }

}


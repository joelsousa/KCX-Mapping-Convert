package com.kewill.kcx.component.mapping.formats.fss.Port.subsets.V65; 

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module       : Port
 * Created		: 24.10.2011<br>
 * Description	: Gefahrgut Textabgaben . 
 *
 * @author iwaniuk
 * @version 1.0.00
 */

public class TsAGT extends Teilsatz {

	 private String beznr	= "";	 // Bezugsnummer
	 private String posnr	= "";	 // Positionsnummer des Hafenauftrags 	 
	 private String pe2lfd	= "";	 // Laufende Nr. zu Ebene2
	 private String pe3lfd	= "";	 // Laufende Nr. zu Ebene3	
	 private String gfausl	= "";	 // Gefahrenauslöser
	 private String stautx 	= "";	 // 
	 private String eigens	= "";	 // 
	 private String verm	= "";	 // 
	 private String ggvla	= "";	 // 
	 private String ggvlis	= "";	 //
	 private String ggvkem	= "";	 // 
	 
    public TsAGT() {
    	tsTyp = "AGT";
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
	    gfausl = fields[5];
	    if (size < 7) { return; }
	    stautx = fields[6];
	    if (size < 8) { return; }
	    eigens = fields[7];
	    if (size < 9) { return; }
	    verm = fields[8];
	    if (size < 10) { return; }
	    ggvla = fields[9];
	    if (size < 11) { return; }
	    ggvlis = fields[10];
	    if (size < 12) { return; }
	    ggvkem = fields[11];	   
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
    	buff.append(gfausl);
    	buff.append(trenner);
    	buff.append(stautx);
    	buff.append(trenner);
    	buff.append(eigens);
    	buff.append(trenner);
    	buff.append(verm);
    	buff.append(trenner);
    	buff.append(ggvla);
    	buff.append(trenner);
    	buff.append(ggvlis);
    	buff.append(trenner);
    	buff.append(ggvkem);
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

    public String getGfausl() {
    	 return gfausl;
    }
    public void setGfausl(String argument) {
    	this.gfausl = Utils.checkNull(argument);
    }
    
    public String getStautx() {
   	 	return stautx;
    }
    public void setStautx(String argument) {
    	this.stautx = Utils.checkNull(argument);
    }
   
    public String getEigens() {
   	 	return eigens;
    }
    public void setEigens(String argument) {
    	this.eigens = Utils.checkNull(argument);
    }
   
    public String getVerm() {
   	 	return verm;
    }
    public void setVerm(String argument) {
    	this.verm = Utils.checkNull(argument);
    }
       
    public String getGgvla() {
   	 	return ggvla;
    }
    public void setGgvla(String argument) {
    	this.ggvla = Utils.checkNull(argument);
    }
   
    public String getGgvlis() {
   	 	return ggvlis;
    }
    public void setGgvlis(String argument) {
    	this.ggvlis = Utils.checkNull(argument);
    }
    
    public String getGgvkem() {
   	 	return ggvkem;
    }
    public void setGgvkem(String argument) {
    	this.ggvkem = Utils.checkNull(argument);
    }
       
    public boolean isEmpty() {
	  return  Utils.isStringEmpty(gfausl) &&
		  Utils.isStringEmpty(stautx) &&		 
		  Utils.isStringEmpty(eigens) &&
		  Utils.isStringEmpty(verm) &&
		  Utils.isStringEmpty(ggvla) &&
		  Utils.isStringEmpty(ggvlis) &&		 
		  Utils.isStringEmpty(ggvkem);
    }

}


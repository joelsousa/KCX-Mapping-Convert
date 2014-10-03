package com.kewill.kcx.component.mapping.formats.fss.Port.subsets.V65; 

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module       : Port
 * Created		: 24.10.2011<br>
 * Description	: Gefahrgutangaben 2. 
 *
 * @author iwaniuk
 * @version 1.0.00
 */

public class TsAGI extends Teilsatz {

	 private String beznr	= "";	 // Bezugsnummer
	 private String posnr	= "";	 // Positionsnummer des Hafenauftrags 	 
	 private String pe2lfd	= "";	 // Laufende Nr. zu Ebene2
	 private String pe3lfd	= "";	 // Laufende Nr. zu Ebene3	
	 private String imdgc	= "";	 // Amedments zur IMDG-Class
	 private String ems 	= "";	 // EmS-Angaben
	 private String label1	= "";	 // 
	 private String label2	= "";	 // 
	 private String label3	= "";	 // 
	 private String limqu	= "";	 // limitet quantities
	 private String wgkcde	= "";	 // Wassergefährdungsklasse
	 private String bfsnr	= "";	 //
	 private String excqu 	= "";	 // 
	 private String ems2	= "";	 //  	
	 private String mfag  = "";	 // 
	 private String mfag2  = "";	 		
	 
    public TsAGI() {
    	tsTyp = "AGI";
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
	    imdgc = fields[5];
	    if (size < 7) { return; }
	    ems = fields[6];
	    if (size < 8) { return; }
	    label1 = fields[7];
	    if (size < 9) { return; }
	    label2 = fields[8];
	    if (size < 10) { return; }
	    label3 = fields[9];
	    if (size < 11) { return; }
	    limqu = fields[10];
	    if (size < 12) { return; }
	    wgkcde = fields[11];
	    if (size < 13) { return; }
	    bfsnr = fields[12];
	    if (size < 14) { return; }
	    excqu = fields[13];
	    if (size < 15) { return; }
	    ems2 = fields[14];
	    if (size < 16) { return; }
	    mfag = fields[15];
	    if (size < 17) { return; }
	    mfag2 = fields[16];	   
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
    	buff.append(imdgc);
    	buff.append(trenner);
    	buff.append(ems);
    	buff.append(trenner);
    	buff.append(label1);
    	buff.append(trenner);
    	buff.append(label2);
    	buff.append(trenner);
    	buff.append(label3);
    	buff.append(trenner);
    	buff.append(limqu);
    	buff.append(trenner);
    	buff.append(wgkcde);
    	buff.append(trenner);
    	buff.append(bfsnr);
    	buff.append(trenner);
    	buff.append(excqu);
    	buff.append(trenner);
    	buff.append(ems2);
    	buff.append(trenner);
    	buff.append(mfag);
    	buff.append(trenner);
    	buff.append(mfag2);
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

    public String getImdgc() {
    	 return imdgc;
    }
    public void setImdgc(String argument) {
    	this.imdgc = Utils.checkNull(argument);
    }
    
    public String getEms() {
   	 	return ems;
    }
    public void setEms(String argument) {
    	this.ems = Utils.checkNull(argument);
    }
   
    public String getLabel1() {
   	 	return label1;
    }
    public void setLabel1(String argument) {
    	this.label1 = Utils.checkNull(argument);
    }
   
    public String getLabel2() {
   	 	return label2;
    }
    public void setLabel2(String argument) {
    	this.label2 = Utils.checkNull(argument);
    }
       
    public String getLabel3() {
   	 	return label3;
    }
    public void setLabel3(String argument) {
    	this.label3 = Utils.checkNull(argument);
    }
   
    public String getLimqu() {
   	 	return limqu;
    }
    public void setLimqu(String argument) {
    	this.limqu = Utils.checkNull(argument);
    }
    
    public String getWgkcde() {
   	 	return wgkcde;
    }
    public void setWgkcde(String argument) {
    	this.wgkcde = Utils.checkNull(argument);
    }
    
    public String getBfsnr() {
   	 	return bfsnr;
    }
    public void setBfsnr(String argument) {
    	this.bfsnr = Utils.checkNull(argument);
    }
   
    public String getExcqu() {
   	 	return excqu;
    }
    public void setExcqu(String argument) {
    	this.excqu = Utils.checkNull(argument);
    }
    
    public String getEms2() {
   	 	return ems2;
    }
    public void setEms2(String argument) {
    	this.ems2 = Utils.checkNull(argument);
    }
    
    public String getMfag() {
   	 	return mfag;
    }
    public void setMfag(String argument) {
    	this.mfag = Utils.checkNull(argument);
    }

    public String getMfag2() {
   	 	return mfag2;
    }
    public void setMfag2(String argument) {
    	this.mfag2 = Utils.checkNull(argument);
    }
    
    public boolean isEmpty() {
	  return  Utils.isStringEmpty(imdgc) &&
		  Utils.isStringEmpty(ems) &&		 
		  Utils.isStringEmpty(label1) &&
		  Utils.isStringEmpty(label2) &&
		  Utils.isStringEmpty(label3) &&
		  Utils.isStringEmpty(limqu) &&
		  Utils.isStringEmpty(wgkcde) &&
		  Utils.isStringEmpty(bfsnr) &&
		  Utils.isStringEmpty(excqu) &&
		  Utils.isStringEmpty(ems2) &&		
		  Utils.isStringEmpty(mfag);
    }

}


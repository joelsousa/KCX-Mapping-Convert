package com.kewill.kcx.component.mapping.formats.fss.Port.subsets.V65; 

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module       : Port
 * Created		: 24.10.2011<br>
 * Description	: Statusmeldungen Referenzen . 
 *
 * @author iwaniuk
 * @version 1.0.00
 */

public class TsTODO extends Teilsatz {

	 private String beznr	= "";	 // Bezugsnummer
	 private String lfdnr	= "";	 // 	 
	 private String date	= "";	 // 
	 private String absend	= "";	 // 
	 private String mrn		= "";	 // 
	 private String regfsy 	= "";	 // 
	 private String art		= "";	 // 
	 private String regnrs	= "";	 // 
	 private String stacod	= "";	 // 
	 private String datsta	= "";	 // 
	 private String datunt	= "";	 // 
	 private String datend	= "";	 // 
	 private String datkon 	= "";	 //   
	 private String datclr	= "";	 // 	
	 private String contrl  = "";	 // 
	 private String connr   = "";	 // 
	 private String statxt  = "";	 // 
	 private String contrl1 = "";	 // 
	 private String from  	= "";	 // 
	
    public TsTODO() {
    	tsTyp = "TODO";
    }

    public void setFields(String[] fields) {
    	int size = fields.length;
    	//String ausgabe = "FSS: " + fields[0] + " size = " + size;

    	if (size < 1) { return; }
    	tsTyp = fields[0];
	    if (size < 2) { return; }
	    beznr = fields[1];
	    if (size < 3) { return; }
	    lfdnr = fields[2];
	    if (size < 4) { return; }
	    date = fields[3];
	    if (size < 5) { return; }
	    absend = fields[4];
	    if (size < 6) { return; }
	    mrn = fields[5];
	    if (size < 7) { return; }
	    regfsy = fields[6];
	    if (size < 8) { return; }
	    art = fields[7];
	    if (size < 9) { return; }
	    regnrs = fields[8];
	    if (size < 10) { return; }
	    stacod = fields[9];
	    if (size < 11) { return; }
	    datsta = fields[10];
	    if (size < 12) { return; }
	    datunt = fields[11];
	    if (size < 13) { return; }
	    datend = fields[12];
	    if (size < 14) { return; }
	    datkon = fields[13];
	    if (size < 15) { return; }
	    datclr = fields[14];
	    if (size < 16) { return; }
	    contrl = fields[15];
	    if (size < 17) { return; }
	    connr = fields[16];
	    if (size < 18) { return; }
	    statxt = fields[17];	    
	    if (size < 19) { return; }
	    contrl1 = fields[18];
	    if (size < 20) { return; }
	    from = fields[19];	   
    }
   
    public String teilsatzBilden() {
    	StringBuffer buff = new StringBuffer();

    	buff.append(tsTyp);
    	buff.append(trenner);
    	buff.append(beznr);
    	buff.append(trenner);
    	buff.append(lfdnr);
    	buff.append(trenner);
    	buff.append(date);
    	buff.append(trenner);
    	buff.append(absend);
    	buff.append(trenner);
    	buff.append(mrn);
    	buff.append(trenner);
    	buff.append(regfsy);
    	buff.append(trenner);
    	buff.append(art);
    	buff.append(trenner);
    	buff.append(regnrs);
    	buff.append(trenner);
    	buff.append(stacod);
    	buff.append(trenner);
    	buff.append(datsta);
    	buff.append(trenner);
    	buff.append(datunt);
    	buff.append(trenner);
    	buff.append(datend);
    	buff.append(trenner);
    	buff.append(datkon);
    	buff.append(trenner);
    	buff.append(datclr);
    	buff.append(trenner);
    	buff.append(contrl);
    	buff.append(trenner);
    	buff.append(connr);
    	buff.append(trenner);
    	buff.append(statxt);
    	buff.append(trenner);
    	buff.append(contrl1);
    	buff.append(trenner);
    	buff.append(from);
    	buff.append(trenner);    
    	
    	return new String(buff);   
    }
   
    public String getBeznr() {
    	 return beznr;
    }
    public void setBeznr(String beznr) {
    	this.beznr = Utils.checkNull(beznr);
    }

    public String getLfdnr() {
    	 return lfdnr;
    }
    public void setLfdnr(String lfdnr) {
    	this.lfdnr = Utils.checkNull(lfdnr);
    }

    public String getDate() {
    	 return date;
    }
    public void setDate(String date) {
    	this.date = Utils.checkNull(date);
    }

    public String getAbsend() {
    	 return absend;
    }
    public void setAbsend(String absend) {
    	this.absend = Utils.checkNull(absend);
    }

    public String getMrn() {
    	 return mrn;
    }
    public void setMrn(String argument) {
    	this.mrn = Utils.checkNull(argument);
    }
    
    public String getRegfsy() {
   	 	return regfsy;
    }
    public void setRegfsy(String argument) {
    	this.regfsy = Utils.checkNull(argument);
    }
   
    public String getArt() {
   	 	return art;
    }
    public void setArt(String argument) {
    	this.art = Utils.checkNull(argument);
    }
   
    public String getRegnrs() {
   	 	return regnrs;
    }
    public void setRegnrs(String argument) {
    	this.regnrs = Utils.checkNull(argument);
    }
       
    public String getStacod() {
   	 	return stacod;
    }
    public void setStacod(String argument) {
    	this.stacod = Utils.checkNull(argument);
    }
   
    public String getDatsta() {
   	 	return datsta;
    }
    public void setDatsta(String argument) {
    	this.datsta = Utils.checkNull(argument);
    }
    
    public String getDatunt() {
   	 	return datunt;
    }
    public void setDatunt(String argument) {
    	this.datunt = Utils.checkNull(argument);
    }
    
    public String getDatend() {
   	 	return datend;
    }
    public void setDatend(String argument) {
    	this.datend = Utils.checkNull(argument);
    }
   
    public String getDatkon() {
   	 	return datkon;
    }
    public void setDatkon(String argument) {
    	this.datkon = Utils.checkNull(argument);
    }
    
    public String getDatclr() {
   	 	return datclr;
    }
    public void setDatclr(String argument) {
    	this.datclr = Utils.checkNull(argument);
    }
    
    public String getContrl() {
   	 	return contrl;
    }
    public void setContrl(String argument) {
    	this.contrl = Utils.checkNull(argument);
    }

    public String getConnr() {
   	 	return connr;
    }
    public void setConnr(String argument) {
    	this.connr = Utils.checkNull(argument);
    }
    
    public String getStatxt() {
   	 	return statxt;
    }
    public void setStatxt(String argument) {
    	this.statxt = Utils.checkNull(argument);
    }
    
    public String getContrl1() {
   	 	return contrl1;
    }
    public void setContrl1(String argument) {
    	this.contrl = Utils.checkNull(argument);
    }
    
    public String getFrom() {
   	 	return from;
    }
    public void setFrom(String argument) {
    	this.from = Utils.checkNull(argument);
    }   
    
    public boolean isEmpty() {
	  return  Utils.isStringEmpty(date) &&
	  		  Utils.isStringEmpty(absend) &&
	  		  Utils.isStringEmpty(mrn) &&
		  Utils.isStringEmpty(regfsy) &&		 
		  Utils.isStringEmpty(art) &&
		  Utils.isStringEmpty(regnrs) &&
		  Utils.isStringEmpty(stacod) &&
		  Utils.isStringEmpty(datsta) &&
		  Utils.isStringEmpty(datunt) &&
		  Utils.isStringEmpty(datend) &&
		  Utils.isStringEmpty(datkon) &&
		  Utils.isStringEmpty(datclr) &&		
		  Utils.isStringEmpty(contrl);
    }

}


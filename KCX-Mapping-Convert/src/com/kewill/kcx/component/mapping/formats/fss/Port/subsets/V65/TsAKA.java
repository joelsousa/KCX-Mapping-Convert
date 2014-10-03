package com.kewill.kcx.component.mapping.formats.fss.Port.subsets.V65; 

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module       : Port
 * Created		: 21.10.2011<br>
 * Description	: Hafenauftrag.Auftraggeberdaten  / MsgPOR.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */

public class TsAKA extends Teilsatz {

	 private String beznr	= "";	 // Bezugsnummer
	 private String spdkai	= "";	 // Kai-Konto des Spediteurs
	 private String spdsa	= "";	 // Sachbearbeiter Abteilung
	 private String spdsb	= "";	 // Sachbearbeiter des Spediteurs
	 private String spdtel	= "";	 // Telefon
	 private String spdfax	= "";	 // Fax
	 private String spdmai 	= "";	 // Email
	 private String gfgsb	= "";	 // Ansprechpartner Gefahrgut 
	 private String gfgtel	= "";	 // Telefon des Ansprechpartner Gefahrgut 	  
	    
    public TsAKA() {
	    tsTyp = "AKA";
    }

    public void setFields(String[] fields) {
    	int size = fields.length;
    	//String ausgabe = "FSS: " + fields[0] + " size = " + size;

       	if (size < 1) { return; }
    	tsTyp = fields[0];
	    if (size < 2) { return; }
	    beznr = fields[1];
	    if (size < 3) { return; }
	    spdkai = fields[2];
	    if (size < 4) { return; }
	    spdsa = fields[3];
	    if (size < 5) { return; }
	    spdsb = fields[4];
	    if (size < 6) { return; }
	    spdtel = fields[5];
	    if (size < 7) { return; }
	    spdfax = fields[6];
	    if (size < 8) { return; }
	    spdmai = fields[7];	 
	    if (size < 9) { return; }
	    gfgsb = fields[8];
	    if (size < 10) { return; }
	    gfgtel = fields[9];	    
    }

    public String teilsatzBilden() {
    	StringBuffer buff = new StringBuffer();

    	buff.append(tsTyp);
    	buff.append(trenner);
    	buff.append(beznr);
    	buff.append(trenner);
    	buff.append(spdkai);
    	buff.append(trenner);
    	buff.append(spdsa);
    	buff.append(trenner);
    	buff.append(spdsb);
    	buff.append(trenner);
    	buff.append(spdtel);
    	buff.append(trenner);
    	buff.append(spdfax);
    	buff.append(trenner);
    	buff.append(spdmai);
    	buff.append(trenner);
    	buff.append(gfgsb);
    	buff.append(trenner);
    	buff.append(gfgtel);    	

    	return new String(buff);    		 
    }

    public String getBeznr() {
   	 	return beznr;
    }
    public void setBeznr(String argument) {
    	this.beznr = Utils.checkNull(argument);
   	}

    public String getSpdkai() {
    	 return spdkai;
    }
    public void setSpdkai(String argument) {
    	this.spdkai = Utils.checkNull(argument);
    }

    public String getSpdsa() {
    	 return spdsa;
    }
    public void setSpdsa(String lfdnr) {
    	this.spdsa = Utils.checkNull(lfdnr);
    }

    public String getSpdsb() {
   	 	return spdsb;
    }
    public void setSpdsb(String argument) {
    	this.spdsb = Utils.checkNull(argument);
    }
   
    public String getSpdtel() {
   	 	return spdtel;
    }
    public void setSpdtel(String argument) {
    	this.spdtel = Utils.checkNull(argument);
    }
    
    public String getSpdfax() {
   	 	return spdfax;
    }
    public void setSpdfax(String argument) {
    	this.spdfax = Utils.checkNull(argument);
    }
    
    public String getSpdmai() {
   	 	return spdmai;
    }
    public void setSpdmai(String argument) {
    	this.spdmai = Utils.checkNull(argument);
    }
    
    public String getGfgsb() {
   	 	return gfgsb;
    }
    public void setGfgsb(String argument) {
    	this.gfgsb = Utils.checkNull(argument);
    }    
    public String getGfgtel() {
    	 return gfgtel;
    }
    public void setGfgtel(String argument) {
    	this.gfgtel = Utils.checkNull(argument);
    }
        
    public boolean isEmpty() {
	  return  Utils.isStringEmpty(spdkai) &&
		  	Utils.isStringEmpty(spdsa) &&
		  	Utils.isStringEmpty(spdsb) &&
		  	Utils.isStringEmpty(spdtel) &&
		  	Utils.isStringEmpty(spdfax) &&
		  	Utils.isStringEmpty(spdmai) &&
		  	Utils.isStringEmpty(gfgsb) &&		  
		  	Utils.isStringEmpty(gfgtel);
    }

}


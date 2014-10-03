package com.kewill.kcx.component.mapping.formats.fss.Port.subsets.V71; 

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module       : Port
 * Created		: 24.10.2011<br>
 * Description	: Documente Positionsdaten. 
 * 				: EI20131007 new V71: lfdnr
 *
 * @author iwaniuk
 * @version 1.0.00
 */

public class TsAEP extends Teilsatz {

	 private String beznr	= "";	 // Bezugsnummer
	 private String posnr	= "";	 // Positionsnummer des Hafenauftrags 	 
	 private String pe2lfd	= "";	 // Laufende Nr. zu Ebene2
	 private String pe3lfd	= "";	 // Laufende Nr. zu Ebene3	
	 private String aepos	= "";	 // KEY; Pos-Nr der AE
	 private String tnr 	= "";	 // Warentarifnummer
	 private String wbsch	= "";	 // 
	 private String eigmas	= "";	 // 
	 private String rohmas	= "";	 // 
	 private String cdevfr	= "";	 //Verfahrenscode
	 private String verme1	= "";	 //
	 private String verme2	= "";	 // 
	 private String verme3 	= "";	 //
	 private String lfdnr   = "";	 //   //EI20131007
	
    public TsAEP() {
    	tsTyp = "AEP";
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
	    aepos = fields[5];
	    if (size < 7) { return; }
	    tnr = fields[6];
	    if (size < 8) { return; }
	    wbsch = fields[7];
	    if (size < 9) { return; }
	    eigmas = fields[8];
	    if (size < 10) { return; }
	    rohmas = fields[9];
	    if (size < 11) { return; }
	    cdevfr = fields[10];
	    if (size < 12) { return; }
	    verme1 = fields[11];
	    if (size < 13) { return; }
	    verme2 = fields[12];
	    if (size < 14) { return; }
	    verme3 = fields[13];
	    if (size < 15) { return; }
	    lfdnr = fields[14];
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
    	buff.append(aepos);
    	buff.append(trenner);
    	buff.append(tnr);
    	buff.append(trenner);
    	buff.append(wbsch);
    	buff.append(trenner);
    	buff.append(eigmas);
    	buff.append(trenner);
    	buff.append(rohmas);
    	buff.append(trenner);
    	buff.append(cdevfr);
    	buff.append(trenner);
    	buff.append(verme1);
    	buff.append(trenner);
    	buff.append(verme2);
    	buff.append(trenner);
    	buff.append(verme3);
    	buff.append(trenner);     	
    	buff.append(lfdnr);
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

    public String getAepos() {
    	 return aepos;
    }
    public void setAepos(String aepos) {
    	this.aepos = Utils.checkNull(aepos);
    }
    
    public String getTnr() {
   	 	return tnr;
    }
    public void setTnr(String argument) {
    	this.tnr = Utils.checkNull(argument);
    }
   
    public String getWbsch() {
   	 	return wbsch;
    }
    public void setWbsch(String argument) {
    	this.wbsch = Utils.checkNull(argument);
    }
   
    public String getEigmas() {
   	 	return eigmas;
    }
    public void setEigmas(String argument) {
    	this.eigmas = Utils.checkNull(argument);
    }
       
    public String getRohmas() {
   	 	return rohmas;
    }
    public void setRohmas(String argument) {
    	this.rohmas = Utils.checkNull(argument);
    }
   
    public String getCdevfr() {
   	 	return cdevfr;
    }
    public void setCdevfr(String argument) {
    	this.cdevfr = Utils.checkNull(argument);
    }
    
    public String getVerme1() {
   	 	return verme1;
    }
    public void setVerme1(String argument) {
    	this.verme1 = Utils.checkNull(argument);
    }
    
    public String getVerme2() {
   	 	return verme2;
    }
    public void setVerme2(String argument) {
    	this.verme2 = Utils.checkNull(argument);
    }
   
    public String getVerme3() {
   	 	return verme3;
    }
    public void setVerme3(String argument) {
    	this.verme3 = Utils.checkNull(argument);
    }
   
    public String getLfdnr() {
		return lfdnr;
	}
	public void setLfdnr(String lfdnr) {
		this.lfdnr = lfdnr;
	}
	
    public boolean isEmpty() {
	  return  Utils.isStringEmpty(aepos) &&
		  Utils.isStringEmpty(tnr) &&		 
		  Utils.isStringEmpty(wbsch) &&
		  Utils.isStringEmpty(eigmas) &&
		  Utils.isStringEmpty(rohmas) &&
		  Utils.isStringEmpty(cdevfr) &&
		  Utils.isStringEmpty(verme1) &&
		  Utils.isStringEmpty(verme2) &&		 
		  Utils.isStringEmpty(verme3);
    }

}


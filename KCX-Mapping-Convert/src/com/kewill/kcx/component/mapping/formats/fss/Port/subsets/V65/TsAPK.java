package com.kewill.kcx.component.mapping.formats.fss.Port.subsets.V65; 

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module       : Port
 * Created		: 21.10.2011<br>
 * Description	: Hafenauftrag Positionskopfdaten  / MsgPOR.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */

public class TsAPK extends Teilsatz {

	 private String beznr	= "";	 // Bezugsnummer
	 private String posnr	= "";	 // Positionsnummer
	 private String connr	= "";	 // Containernummer
	 private String handla	= "";	 // Umschlagsspezifikation
	 private String prodid	= "";	 // Warencode
	 private String kfzid 	= "";	 // Fahrzeug-Identifikationsnummer / Chassisnummer
	 private String verktr	= "";	 // Verkehrsträgerkennzeichen 
	 private String artzol	= "";	 // Art der Zollanmeldung
	 private String kzubeh	= "";	 // Kennzeichen Zubehör/Beiladung für Fahrzeugverladungen 
	
    public TsAPK() {
	    tsTyp = "APK";
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
	    connr = fields[3];
	    if (size < 5) { return; }
	    handla = fields[4];
	    if (size < 6) { return; }
	    prodid = fields[5];
	    if (size < 7) { return; }
	    kfzid = fields[6];
	    if (size < 8) { return; }
	    verktr = fields[7];
	    if (size < 9) { return; }
	    artzol = fields[8];	 
	    if (size < 10) { return; }
	    kzubeh = fields[9];	   
    }

    public String teilsatzBilden() {
    	StringBuffer buff = new StringBuffer();

    	buff.append(tsTyp);
    	buff.append(trenner);
    	buff.append(beznr);
    	buff.append(trenner);
    	buff.append(posnr);
    	buff.append(trenner);
    	buff.append(connr);
    	buff.append(trenner);
    	buff.append(handla);
    	buff.append(trenner);
    	buff.append(prodid);
    	buff.append(trenner);
    	buff.append(kfzid);
    	buff.append(trenner);
    	buff.append(verktr);
    	buff.append(trenner);
    	buff.append(artzol);
    	buff.append(trenner);  
    	buff.append(kzubeh);
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

    public String getConnr() {
    	 return connr;
    }
    public void setConnr(String connr) {
    	this.connr = Utils.checkNull(connr);
    }

    public String getHandla() {
    	 return handla;
    }
    public void setHandla(String handla) {
    	this.handla = Utils.checkNull(handla);
    }

    public String getProdid() {
    	 return prodid;
    }
    public void setProdid(String prodid) {
    	this.prodid = Utils.checkNull(prodid);
    }
    
    public String getKfzid() {
   	 	return kfzid;
    }
    public void setKfzid(String argument) {
    	this.kfzid = Utils.checkNull(argument);
    }
   
    public String getVerktr() {
   	 	return verktr;
    }
    public void setVerktr(String argument) {
    	this.verktr = Utils.checkNull(argument);
    }
   
    public String getArtzol() {
   	 	return artzol;
    }
    public void setArtzol(String argument) {
    	this.artzol = Utils.checkNull(argument);
    }
    
    public String getKzubeh() {
   	 	return kzubeh;
    }
    public void setKzubeh(String argument) {
    	this.kzubeh = Utils.checkNull(argument);
    }
    
    public boolean isEmpty() {
	  return  Utils.isStringEmpty(posnr) && //EI20131007: die posnr ist einziger Mussfeld = muss hier abgefragt werden! 
	  	  Utils.isStringEmpty(connr) &&
		  Utils.isStringEmpty(handla) &&
		  Utils.isStringEmpty(prodid) &&
		  Utils.isStringEmpty(kfzid) &&
		  Utils.isStringEmpty(verktr) &&
		  Utils.isStringEmpty(artzol) &&		 
		  Utils.isStringEmpty(kzubeh);
    }
    	
}


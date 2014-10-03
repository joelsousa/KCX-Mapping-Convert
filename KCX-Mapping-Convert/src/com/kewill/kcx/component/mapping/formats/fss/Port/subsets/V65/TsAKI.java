package com.kewill.kcx.component.mapping.formats.fss.Port.subsets.V65; 

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module       : Port
 * Created		: 21.10.2011<br>
 * Description	: Hafenauftrag.Identifikation/Art  / MsgPOR.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */

public class TsAKI extends Teilsatz {
	
	private String beznr	= "";	 // Bezugsnummer
    private String hasys	= "";	 // Hafensystem
    private String artauf	= "";	 // Auftragsart
    private String qartau	= "";	 // Spezifizierung zur Auftragsart
    private String kzauft	= "";	 // Kz. Art Antrag / Auftrag 
    private String kzsaco 	= "";	 // Kz. SACO Anmeldung
    private String leiart	= "";	 // Leistungsart
  
    public TsAKI() {
	    tsTyp = "AKI";
    }

    public void setFields(String[] fields) {
    	int size = fields.length;
    	//String ausgabe = "FSS: " + fields[0] + " size = " + size;

    	if (size < 1) { return; }
    	tsTyp = fields[0];
	    if (size < 2) { return; }
	    beznr = fields[1];
	    if (size < 3) { return; }
	    hasys = fields[2];
	    if (size < 4) { return; }
	    artauf = fields[3];
	    if (size < 5) { return; }
	    qartau = fields[4];
	    if (size < 6) { return; }
	    kzauft = fields[5];
	    if (size < 7) { return; }
	    kzsaco = fields[6];
	    if (size < 8) { return; }
	    leiart = fields[7];	   
    }

    public String teilsatzBilden() {
    	StringBuffer buff = new StringBuffer();

    	buff.append(tsTyp);
    	buff.append(trenner);
    	buff.append(beznr);
    	buff.append(trenner);
    	buff.append(hasys);
    	buff.append(trenner);
    	buff.append(artauf);
    	buff.append(trenner);
    	buff.append(qartau);
    	buff.append(trenner);
    	buff.append(kzauft);
    	buff.append(trenner);
    	buff.append(kzsaco);
    	buff.append(trenner);
    	buff.append(leiart);
    	buff.append(trenner);
    	
    	return new String(buff);
    }

    public String getBeznr() {
    	 return beznr;
    }
    public void setBeznr(String argument) {
    	this.beznr = Utils.checkNull(argument);
    }
    
    public String getHasys() {
   	 	return hasys;
    }
    public void setHasys(String argument) {
    	this.hasys = Utils.checkNull(argument);
    }

    public String getArtauf() {
    	 return artauf;
    }
    public void setArtauf(String argument) {
    	this.artauf = Utils.checkNull(argument);
    }

    public String getQartau() {
    	 return qartau;
    }
    public void setQartau(String argument) {
    	this.qartau = Utils.checkNull(argument);
    }

    public String getKzauft() {
    	 return kzauft;
    }
    public void setKzauft(String argument) {
    	this.kzauft = Utils.checkNull(argument);
    }

    public String getKzsaco() {
    	 return kzsaco;
    }
    public void setKzsaco(String argument) {
    	this.kzsaco = Utils.checkNull(argument);
    }

    public String getLeiart() {
    	return leiart;
    }
    public void setLeiart(String argument) {
    	this.leiart = Utils.checkNull(argument);
    }

    public boolean isEmpty() {
	  return  Utils.isStringEmpty(hasys) &&
	  		 Utils.isStringEmpty(artauf) &&
	  		 Utils.isStringEmpty(qartau) &&
	  		 Utils.isStringEmpty(kzauft) &&	 
	  		 Utils.isStringEmpty(kzsaco) &&		 
	  		 Utils.isStringEmpty(leiart);
    }

}


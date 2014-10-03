package com.kewill.kcx.component.mapping.formats.fss.Port.subsets.V71; 

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module       : Port
 * Created		: 12.12.2013<br>
 * Description	: BHT Hafenauftrag Ergänzungen zum Container.
 * 				: KIDS: BHTContainerAdditionalData  
 * 				: new in V71 
 * @author iwaniuk
 * @version 1.0.00
 */

public class TsCTB extends Teilsatz {

    private String beznr	= "";	 // Bezugsnummer
    private String connr	= "";	 // 
    private String siegl5	= "";	 // AdditionalSeal
    private String sprfah	= "";	 // str1 Spreadable
    private String stacod	= "";	 // str1 LoadStatus
    private String befart	= "";	 // str3 Beförderungsart-Code transportationCode
    private String flatpa 	= "";	 // Container-Bündel-Identifakation str2 BundleId
    private String uemasc	= "";	 // Container-Übermaß-Code
    private String uelink 	= "";	 // Container-Übermaß links
    private String uerech 	= "";	 // Container-Übermaß rechts
    private String uehoeh 	= "";	 // Container-Übermaß höhe
    private String uehint 	= "";	 // Container-Übermaß hinten
    private String uevorn 	= "";	 // Container-Übermaß vorne
    private String staupl 	= "";	 // Stauplatz
    private String verlaa 	= "";	 // Verladeanweisung
    private String bemree 	= "";	 // Bemerkung Reeder
    private String bauart 	= "";	 // Container Bauart-Code
    private String hoehe 	= "";	 // Container Höhe Nummer
    private String laenge 	= "";	 // Container Höhe Nummer
    
    public TsCTB() {
	    tsTyp = "CTB";
    }

    public void setFields(String[] fields) {
    	int size = fields.length;
    	//String ausgabe = "FSS: " + fields[0] + " size = " + size;

    	if (size < 1) { return; }
    	tsTyp = fields[0];
	    if (size < 2) { return; }
	    beznr = fields[1];
	    if (size < 3) { return; }
	    connr = fields[2];	    
	    if (size < 4) { return; }
	    siegl5 = fields[3];
	    if (size < 5) { return; }
	    sprfah = fields[4];
	    if (size < 6) { return; }
	    stacod = fields[5];
	    if (size < 7) { return; }
	    befart = fields[6];
	    if (size < 8) { return; }
	    flatpa = fields[7];
	    if (size < 9) { return; }
	    uemasc = fields[8];
	    if (size < 10) { return; }
	    uelink = fields[9];
	    if (size < 11) { return; }
	    uerech = fields[10];
	    if (size < 12) { return; }
	    uehoeh = fields[11];	   
	    if (size < 13) { return; }
	    uehint = fields[12];
	    if (size < 14) { return; }
	    uevorn = fields[13];
	    if (size < 15) { return; }
	    staupl = fields[14];
	    if (size < 16) { return; }
	    verlaa = fields[15];
	    if (size < 17) { return; }
	    bemree = fields[16];
	    if (size < 18) { return; }
	    bauart = fields[17];
	    if (size < 19) { return; }
	    hoehe = fields[18];
	    if (size < 20) { return; }
	    laenge = fields[19];
    }
   
    public String teilsatzBilden() {
    	StringBuffer buff = new StringBuffer();

    	buff.append(tsTyp);
    	buff.append(trenner);
    	buff.append(beznr);
    	buff.append(trenner);
    	buff.append(connr);
    	buff.append(trenner);    
    	buff.append(siegl5);
    	buff.append(trenner);
    	buff.append(sprfah);
    	buff.append(trenner);
    	buff.append(stacod);    	
    	buff.append(trenner);
    	buff.append(befart);
    	buff.append(trenner);
    	buff.append(flatpa);
    	buff.append(trenner);
    	buff.append(uemasc);
    	buff.append(trenner);
    	buff.append(uelink);
    	buff.append(trenner);
    	buff.append(uerech);
    	buff.append(trenner);
    	buff.append(uehoeh);
    	buff.append(trenner);
    	buff.append(uehint);
    	buff.append(trenner);
    	buff.append(uevorn);
    	buff.append(trenner);
    	buff.append(staupl);
    	buff.append(trenner);
    	buff.append(verlaa);
    	buff.append(trenner);
    	buff.append(bemree);
    	buff.append(trenner);
    	buff.append(bauart);
    	buff.append(trenner);
    	buff.append(hoehe);
    	buff.append(trenner);    
    	buff.append(laenge);
    	buff.append(trenner);  

    	return new String(buff);    
    }
   
    public String getBeznr() {
    	 return beznr;
    }
    public void setBeznr(String argument) {
    	this.beznr = Utils.checkNull(argument);
    }
   
    public String getConnr() {
		return connr;
	}
	public void setConnr(String connr) {
		this.connr = Utils.checkNull(connr);
	}

	public String getSiegl5() {
		return siegl5;
	}
	public void setSiegl5(String refnr) {
		this.siegl5 = Utils.checkNull(refnr);
	}

	public String getSprfah() {
		return sprfah;
	}
	public void setSprfah(String sprfah) {
		this.sprfah = Utils.checkNull(sprfah);
	}

	public String getStacod() {
		return stacod;
	}
	public void setStacod(String stacod) {
		this.stacod = Utils.checkNull(stacod);
	}

	public String getBefart() {
		return befart;
	}
	public void setBefart(String befart) {
		this.befart = Utils.checkNull(befart);
	}

	public String getFlatpa() {
		return flatpa;
	}
	public void setFlatpa(String flatpa) {
		this.flatpa = Utils.checkNull(flatpa);
	}

	public String getUemasc() {
		return uemasc;
	}
	public void setUemasc(String uemasc) {
		this.uemasc = Utils.checkNull(uemasc);
	}

	public String getUelink() {
		return uelink;
	}
	public void setUelink(String uelink) {
		this.uelink = Utils.checkNull(uelink);
	}

	public String getUerech() {
		return uerech;
	}
	public void setUerech(String uerech) {
		this.uerech = Utils.checkNull(uerech);
	}
	
	public String getUehoeh() {
		return uehoeh;
	}

	public void setUehoeh(String uehoeh) {
		this.uehoeh = Utils.checkNull(uehoeh);
	}
	public String getUehint() {
		return uehint;
	}

	public void setUehint(String uehint) {
		this.uehint = Utils.checkNull(uehint);
	}

	public String getUevorn() {
		return uevorn;
	}
	public void setUevorn(String uevorn) {
		this.uevorn = Utils.checkNull(uevorn);
	}

	public String getStaupl() {
		return staupl;
	}
	public void setStaupl(String staupl) {
		this.staupl = Utils.checkNull(staupl);
	}

	public String getVerlaa() {
		return verlaa;
	}
	public void setVerlaa(String verlaa) {
		this.verlaa = Utils.checkNull(verlaa);
	}

	public String getBemree() {
		return bemree;
	}
	public void setBemree(String bemree) {
		this.bemree = Utils.checkNull(bemree);
	}

	public String getBauart() {
		return bauart;
	}
	public void setBauart(String bauart) {
		this.bauart = Utils.checkNull(bauart);
	}

	public String getHoehe() {
		return hoehe;
	}
	public void setHoehe(String hoehe) {
		this.hoehe = Utils.checkNull(hoehe);
	}

	public String getLaenge() {
		return laenge;
	}
	public void setLaenge(String laenge) {
		this.laenge = Utils.checkNull(laenge);
	}

	public boolean isEmpty() {
    	return  Utils.isStringEmpty(connr) && Utils.isStringEmpty(siegl5);  //&& ... TODO
    }
}


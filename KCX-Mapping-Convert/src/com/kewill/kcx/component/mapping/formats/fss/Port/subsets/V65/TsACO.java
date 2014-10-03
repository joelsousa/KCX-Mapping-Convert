package com.kewill.kcx.component.mapping.formats.fss.Port.subsets.V65; 

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module       : Port
 * Created		: 24.10.2011<br>
 * Description	: Positionsdaten
 *
 * @author iwaniuk
 * @version 1.0.00
 */

public class TsACO extends Teilsatz {

    private String beznr		 = "";	 // Bezugsnummer
    private String posnr		 = "";	 // Positionsnummer
    private String pe2lfd		 = "";	 // Laufende Nr. zu Ebene2
    private String pe3lfd		 = "";	 // Laufende Nr. zu Ebene3
    private String colanz		 = "";	 // 
    private String colart		 = "";	 // 
    private String rohmas		 = "";	 // 
    private String brtvol		 = "";	 // 

    public TsACO() {
	    tsTyp = "ACO";
    }

    public void setFields(String[] fields) {
    	int size = fields.length;

    	String ausgabe = "FSS: " + fields[0] + " size = " + size;

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
		colanz = fields[5];	
		if (size < 7) { return; }
		colart = fields[6];	
		if (size < 8) { return; }
		rohmas = fields[7];	
		if (size < 9) { return; }
		brtvol = fields[8];	
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
    	buff.append(colanz);
    	buff.append(trenner);
    	buff.append(colart);
    	buff.append(trenner);
    	buff.append(rohmas);
    	buff.append(trenner);
    	buff.append(brtvol);
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

    public String getPe3lfdFeld() {
    	 return pe3lfd;
    }
    public void setPe3lfd(String pe3lfd) {
    	this.pe3lfd = Utils.checkNull(pe3lfd);
    }

    public String getColanz() {
    	 return colanz;
    }
    public void setColanz(String colanz) {
    	this.colanz = Utils.checkNull(colanz);
    }

    public String getColart() {
    	 return colart;
    }
    public void setColart(String colart) {
    	this.colart = Utils.checkNull(colart);
    }

    public String getRohmas() {
    	 return rohmas;
    }
    public void setRohmas(String rohmas) {
    	this.rohmas = Utils.checkNull(rohmas);
    }

    public String getBrtvol() {
    	 return brtvol;
    }
    public void setBrtvol(String brtvol) {
    	this.brtvol = Utils.checkNull(brtvol);
    }

    public boolean isEmpty() {
	  return   Utils.isStringEmpty(colanz) &&
    	Utils.isStringEmpty(colart) &&
    	Utils.isStringEmpty(rohmas) &&    	
    	Utils.isStringEmpty(brtvol);
    }
}

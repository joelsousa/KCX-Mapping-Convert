package com.kewill.kcx.component.mapping.formats.fss.Import.subsets.V64;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module        : Import
 * Created       : 21.09.2011
 * Description 	 : Kopfteil der Nachricht CON /CUSCUN 			 
 *
 * @author Iwaniuk
 * @version 6.4.00
 */

public class TsCON extends Teilsatz {
   
    private String arbnr		 = "";	 // Arbeitsnummer
    private String beznr		 = "";	 // Bezugsnummer
    private String korant		 = "";	 // Korrekturnummer
    private String anmzb		 = "";	 // Zollnummer des Anmelders
    private String sbname		 = "";	 // Sachbearbeiter Name
    private String sbstel		 = "";	 // Sachbearbeiter Stellung
    private String sbtele		 = "";	 // Sachbearbeiter Telefon
    private String sbnr		 	 = "";	 // Sachbearbeiternummer
    private String waort		 = "";	 // Warenort
    private String bfkzi		 = "";	 // Kennzeichen/Name des Beförderungsmittel bei Ankunft
    private String vorpnr		 = "";	 // Vorpapiernummer
    private String vorpar		 = "";	 // Vorpapierart

    public TsCON() {
	    tsTyp = "CON";
    }

    public void setFields(String[] fields) {
    	int size = fields.length;
    	String ausgabe = "FSS: " + fields[0] + " size = " + size;

    	if (size < 1) { return; }
    	tsTyp = fields[0];

	    if (size < 2) { return; }
	    arbnr = fields[1];

	    if (size < 3) { return; }
	    beznr = fields[2];

	    if (size < 4) { return; }
	    korant = fields[3];

	    if (size < 5) { return; }
	    anmzb = fields[4];

	    if (size < 6) { return; }
	    sbname = fields[5];

	    if (size < 7) { return; }
	    sbstel = fields[6];
	    
	    if (size < 8) { return; }
	    sbtele = fields[7];

	    if (size < 9) { return; }
	    sbnr = fields[8];
	    
	    if (size < 10) { return; }
	    waort = fields[9];

	    if (size < 11) { return; }
	    bfkzi = fields[10];
	    
	    if (size < 12) { return; }
	    vorpnr = fields[11];

	    if (size < 13) { return; }
	    vorpar = fields[12];	   
    }


    public String getArbnr() {
   	 	return arbnr;
    }
    public void setArbnr(String arbnr) {
    	this.arbnr = Utils.checkNull(arbnr);
    }

    public String getBeznr() {
    	return beznr;
    }
    public void setBeznr(String beznr) {
    	this.beznr = Utils.checkNull(beznr);
    }

    public String getKorant() {
    	 return korant;
    }
    public void setKorant(String korant) {
    	this.korant = Utils.checkNull(korant);
    }
 
    public String getAnmzb() {
    	return anmzb;
    }
    public void setAnmzb(String arbnr) {
    	this.anmzb = Utils.checkNull(arbnr);
    }    
    
    public String getSbname() {
    	 return sbname;
    }
    public void setSbname(String regnr) {
    	this.sbname = Utils.checkNull(regnr);
    }

    public String getSbstel() {
   	 	return sbstel;
    }
    public void setSbstel(String regnr) {
    	this.sbstel = Utils.checkNull(regnr);
    }
   
    public String getSbtele() {
  	 return sbtele;
    }
    public void setSbtele(String regnr) {
  	this.sbtele = Utils.checkNull(regnr);
    }
  
    public String getSbnr() {
 	 return sbnr;
    }
    public void setSbnr(String regnr) {
 	this.sbnr = Utils.checkNull(regnr);
    }
 
    public String getVorpnr() {
    	return vorpnr;
    }
    public void setVorpnr(String lfdnr) {
    	this.vorpnr = Utils.checkNull(lfdnr);
    }
    
    public String getWaort() {
    	return waort;
    }
    public void setWaort(String arbnr) {
    	this.waort = Utils.checkNull(arbnr);
    }    
    
    public String getBfkzi() {
    	 return bfkzi;
    }
    public void setBfkzi(String regnr) {
    	this.bfkzi = Utils.checkNull(regnr);
    }
    
    public String getVorpar() {
    	return vorpar;
    }
    public void setVorpar(String lfdnr) {
    	this.vorpar = Utils.checkNull(lfdnr);
    }
    
    public String teilsatzBilden() {
    	StringBuffer buff = new StringBuffer();

    	buff.append(tsTyp);
    	buff.append(trenner);
    	buff.append(arbnr);
    	buff.append(trenner);
    	buff.append(beznr);
    	buff.append(trenner);
    	buff.append(korant);
    	buff.append(trenner);
    	buff.append(anmzb);
    	buff.append(trenner);
    	buff.append(sbname);
    	buff.append(trenner);
    	buff.append(sbstel);
    	buff.append(trenner);
    	buff.append(sbtele);
    	buff.append(trenner);
    	buff.append(sbnr);
    	buff.append(trenner);
    	buff.append(waort);
    	buff.append(trenner);
    	buff.append(bfkzi);
    	buff.append(trenner);
    	buff.append(vorpnr);
    	buff.append(trenner);
    	buff.append(vorpar);
    	buff.append(trenner);

    	return new String(buff);
    
    }

    public boolean isEmpty() {
	  return  Utils.isStringEmpty(anmzb) &&
    	Utils.isStringEmpty(sbname) && Utils.isStringEmpty(sbtele) &&
    	Utils.isStringEmpty(sbnr) && Utils.isStringEmpty(waort) &&
    	Utils.isStringEmpty(bfkzi);
    }

}

/*
 * Funktion    : TsAED.java
 * Titel       :
 * Erstellt    : 03.09.2008
 * Author      : Elisabeth Iwaniuk
 * Beschreibung:
 * Anmerkungen : 06.03.2009 - V60 checked
 * Parameter   :
 * --------------------
 * Changes:
 * 
 * Author      : EI
 * Label       : EI20090622
 * Description : in isEmpty() fehlte untart
 */

package com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.Document;
import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

public class TsAED extends Teilsatz {

    private String beznr  = "";        // Bezugsnummer
    private String posnr  = "";        // Positionsnummer
    private String untqar = "";        // Qualifikator der Unterlage
    private String untart = "";        // Art der Unterlage
    private String untnr  = "";        // Unterlagenreferenz
    private String untzus = "";        // Unterlagenzusatz
    private String untdat = "";        // Datum der Ausstellung
    private String gbdat  = "";        // Datum des Gültigkeitsendes
    private String detail = "";        // Detail                                             26.08.08
    private String wert   = "";        // Wert                                               26.08.08
    //private String mgeqme = "";        // Qualifikator zur Abschreibungsmenge     //EI20090306 wird nicht mehr verwendet           26.08.08
    private String mgeme  = "";        // Maßeinheit                                         26.08.08
    private String abgwrt = "";        // Wert der Abschreibungsmenge                        26.08.08

	public TsAED() {
        tsTyp = "AED";
    }
	
	public void setFields(String[] fields)
	{ 
		int size = fields.length;
		String ausgabe = "FSS: "+fields[0]+" size = "+ size;
		//Utils.log( ausgabe);
		
		if (size < 1 ) return;		
        tsTyp = fields[0];
        if (size < 2 ) return;
	    beznr = fields[1];
	    if (size < 3 ) return;
	    posnr = fields[2];
	    if (size < 4 ) return;
	    untqar = fields[3];
	    if (size < 5 ) return;
	    untart = fields[4];
	    if (size < 6 ) return;
	    untnr = fields[5];
	    if (size < 7 ) return;
	    untzus = fields[6];
	    if (size < 8 ) return;
	    untdat = fields[7];
	    if (size < 9 ) return;
	    gbdat = fields[8];
	    if (size < 10 ) return;
	    detail = fields[9];
	    if (size < 11 ) return;
	    wert = fields[10]; 
	    //if (size < 12 ) return;
	    //mgeqme = fields[11];   
	    if (size < 12 ) return;
	    mgeme = fields[11];  
	    if (size < 13 ) return;
	    abgwrt = fields[12];                                                    //26.08.08
	}
	   
	public String teilsatzBilden() {
	    StringBuffer buff = new StringBuffer();

	    buff.append(tsTyp);
	    buff.append(trenner);
	    buff.append(beznr);
	    buff.append(trenner);
	    buff.append(posnr);
	    buff.append(trenner);
	    buff.append(untqar);
	    buff.append(trenner);
	    buff.append(untart);
	    buff.append(trenner);
        buff.append(untnr); 
        buff.append(trenner);
        buff.append(untzus); 
        buff.append(trenner);
        buff.append(untdat);
        buff.append(trenner);
        buff.append(gbdat); 
        buff.append(trenner);
        buff.append(detail); 
        buff.append(trenner);
        buff.append(wert); 
        buff.append(trenner);
        //buff.append(mgeqme); 
        //buff.append(trenner);
        buff.append(mgeme); 
        buff.append(trenner);
        buff.append(abgwrt); 
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

	public String getUntqar() {
		return untqar;
	
	}

	public void setUntqar(String untqar) {
		this.untqar = Utils.checkNull(untqar);
	}

	public String getUntart() {
		return untart;
	
	}

	public void setUntart(String untart) {
		this.untart = Utils.checkNull(untart);
	}

	public String getUntnr() {
		return untnr;
	
	}

	public void setUntnr(String untnr) {
		this.untnr = Utils.checkNull(untnr);
	}

	public String getUntzus() {
		return untzus;
	
	}

	public void setUntzus(String untzus) {
		this.untzus = Utils.checkNull(untzus);
	}

	public String getUntdat() {
		return untdat;
	
	}

	public void setUntdat(String untdat) {
		this.untdat = Utils.checkNull(untdat);
	}

	public String getGbdat() {
		return gbdat;
	
	}

	public void setGbdat(String gbdat) {
		this.gbdat = Utils.checkNull(gbdat);
	}

	public String getDetail() {
		return detail;
	
	}

	public void setDetail(String detail) {
		this.detail = Utils.checkNull(detail);
	}

	public String getWert() {
		return wert;
	
	}

	public void setWert(String wert) {
		this.wert = Utils.checkNull(wert);
	}
    /*
	public String getMgeqme() {
		return mgeqme;	
	}
	public void setMgeqme(String mgeqme) {
		this.mgeqme = Utils.checkNull(mgeqme);
	}
     */
	public String getMgeme() {
		return mgeme;
	
	}

	public void setMgeme(String mgeme) {
		this.mgeme = Utils.checkNull(mgeme);
	}

	public String getAbgwrt() {
		return abgwrt;
	
	}

	public void setAbgwrt(String abgwrt) {
		this.abgwrt = Utils.checkNull(abgwrt);
	}
	
	
	public void setAedSubset(Document document, String ref, String item) {
		if (document == null) return;
		
		this.setBeznr(ref);
		this.setPosnr(item);
		this.setUntqar(document.getQualifier());
		this.setUntart(document.getTypeKids());   //EI20090409
		this.setUntnr(document.getReference());
		this.setUntzus(document.getAdditionalInformation());
		this.setUntdat(document.getIssueDate());
		this.setGbdat(document.getExpirationDate());
	}

 
	public boolean isEmpty() {
		if ( Utils.isStringEmpty(untqar) 
			&& Utils.isStringEmpty(untnr)
			&& Utils.isStringEmpty(untart) // EI 22.06.2009 
			&& Utils.isStringEmpty(untzus)
			&& Utils.isStringEmpty(untdat)
			&& Utils.isStringEmpty(gbdat)
			&& Utils.isStringEmpty(detail)
			&& Utils.isStringEmpty(gbdat)
			&& Utils.isStringEmpty(wert)
			//&& Utils.isStringEmpty(mgeqme)
			&& Utils.isStringEmpty(abgwrt) )
				return true;
			else
				return false;			
		}
		
}

/*
 * Funktion    : TsSAP.java
 * Titel       :
 * Erstellt    : 03.09.2008
 * Author      : Elisabeth Iwaniuk
 * Beschreibung:
 * Anmerkungen : V60 checked
 */

package com.kewill.kcx.component.mapping.formats.fss.base.subsets;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

public class TsSAP extends Teilsatz {

    private String beznr  = "";                      //Bezugsnummer
    private String posnr  = "";                      //Positionsnummer
    private String bfgkzw = "";                      //Beförderungskosten (Zahlungsweise)
    private String knrsdg = "";                      //Kennnummer der Sendung
    private String undgnr = "";                      //Gefahrgutnummer

    public TsSAP() {
        tsTyp = "SAP";
    } 
    
    public void setFields(String[] fields)
    {
		int size = fields.length;
		String ausgabe = "FSS: "+fields[0]+" size = "+ size;
		//Utils.log( ausgabe);
			
		
		if (size < 1 ) return;		
        tsTyp = fields[0];
        if (size < 2 ) return;	
        beznr       = fields[1];
        if (size < 3 ) return;	
        posnr       = fields[2];
        if (size < 4 ) return;	
        bfgkzw      = fields[3];
        if (size < 5 ) return;	
        knrsdg      = fields[4];
        if (size < 6 ) return;	
        undgnr      = fields[5];
    }
    
	public String teilsatzBilden() {
		
        StringBuffer buff = new StringBuffer();
        
        buff = buff.append(tsTyp);
        buff = buff.append(trenner);
        buff = buff.append(beznr);
        buff = buff.append(trenner);
        buff = buff.append(posnr);
        buff = buff.append(trenner);
        buff = buff.append(bfgkzw);
        buff = buff.append(trenner); 
        buff = buff.append(knrsdg);
        buff = buff.append(trenner);
        buff = buff.append(undgnr);
        buff = buff.append(trenner);
        
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

	public String getBfgkzw() {
		return bfgkzw;
	}

	public void setBfgkzw(String bfgkzw) {
		this.bfgkzw = Utils.checkNull(bfgkzw);
	}

	public String getKnrsdg() {
		return knrsdg;
	}

	public void setKnrsdg(String knrsdg) {
		this.knrsdg = Utils.checkNull(knrsdg);
	}

	public String getUndgnr() {
		return undgnr;
	}

	public void setUndgnr(String undgnr) {
		this.undgnr = Utils.checkNull(undgnr);
	}  
	
	public boolean isEmpty() {
		if ( Utils.isStringEmpty(bfgkzw)  && Utils.isStringEmpty(knrsdg) && Utils.isStringEmpty(undgnr))		  
			return true;
		else
			return false;
	}

}


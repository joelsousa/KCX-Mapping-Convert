/*
 * Funktion    : TsAED.java
 * Titel       :
 * Erstellt    : 14.10.2008
 * Author      : Alfred Krzoska
 * Beschreibung:
 * Anmerkungen : copy from V60
 * Parameter   :
 * R�ckgabe    : keine
 * Aufruf      :
 *
 */

package com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V53;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.Document;
import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

public class TsAED extends Teilsatz {

    private String beznr     = "";        // Bezugsnummer
    private String posnr     = "";        // Positionsnummer
    private String untqar    = "";        // Qualifikator der Unterlage
    private String untart    = "";        // Art der Unterlage
    private String untnr     = "";        // Unterlagenreferenz
    private String untzus    = "";        // Unterlagenzusatz
    private String untdat    = "";        // Datum der Ausstellung
    private String gbdat     = "";        // Datum des G�ltigkeitsendes

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

	
	public boolean isEmpty() {
		if (Utils.isStringEmpty(untqar) 
			&& Utils.isStringEmpty(untnr)
			&& Utils.isStringEmpty(untzus)
			&& Utils.isStringEmpty(untdat)
			&& Utils.isStringEmpty(untart) // C.K. 12.06.2009 erg�nzt da manchmal nur die untart kommt
			&& Utils.isStringEmpty(gbdat))
				return true;
			else
				return false;			
		}

	public void setAedSubset(Document document, String ref, String item) {
		if (document == null) return;
		
		this.setBeznr(ref);
		this.setPosnr(item);
		this.setUntqar(document.getQualifier());
		this.setUntart(document.getTypeKids());  //EI20090409
		this.setUntnr(document.getReference());
		this.setUntzus(document.getAdditionalInformation());
		this.setUntdat(document.getIssueDate());
		this.setGbdat(document.getExpirationDate());
		
	}
}

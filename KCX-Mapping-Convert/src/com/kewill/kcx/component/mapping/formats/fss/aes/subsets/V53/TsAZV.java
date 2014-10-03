/*
 * Funktion    : TsAZV.java
 * Titel       :
 * Erstellt    : 03.09.2008
 * Author      : Elisabeth Iwaniuk
 * Beschreibung:
 * Anmerkungen :
 * Parameter   :
 * Rückgabe    : keine
 * Aufruf      :
 *
 */

package com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V53;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.PreviousDocument;
import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

public class TsAZV extends Teilsatz {

    private String beznr     = "";        // Bezugsnummer
    private String posnr     = "";        // Positionsnummerz
    private String azvref    = "";       // Referenz zum Vorpapier
    private String azvzus    = "";       // Zusatz zum Vorpapier

    public TsAZV() {
        tsTyp = "AZV";
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
        azvref = fields[3];
        if (size < 5 ) return;
        azvzus = fields[4];
    }

	public String teilsatzBilden() {
		
        StringBuffer buff = new StringBuffer();
        
        buff = buff.append(tsTyp);
        buff = buff.append(trenner);
        buff = buff.append(beznr);
        buff = buff.append(trenner);
        buff = buff.append(posnr);
        buff = buff.append(trenner);
        buff = buff.append(azvref);
        buff = buff.append(trenner);
        buff = buff.append(azvzus);
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

	public String getAzvref() {
		return azvref;
	}
	public void setAzvref(String azvref) {
		this.azvref = Utils.checkNull(azvref);
	}
	public String getAzvzus() {
		return azvzus;
	}
	public void setAzvzus(String azvzus) {
		this.azvzus = Utils.checkNull(azvzus);
	}
	
	public void setAzvSubset(PreviousDocument prd, String beznr, String item ) {

		if (prd == null) return;
		
		if (Utils.isStringEmpty(beznr)) return;
		if (Utils.isStringEmpty(item)) return;

		this.setBeznr(beznr);
		this.setPosnr(item);
		
		this.setAzvref(prd.getMarks());   
		this.setAzvzus(prd.getAdditionalInformation());
	}
	
	public boolean isEmpty() {
		if ( Utils.isStringEmpty(azvref)&& Utils.isStringEmpty(azvzus) )
    		return true;
		else
			return false;
	}
}




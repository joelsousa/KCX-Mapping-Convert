/*
 * Funktion    : TsAZV.java
 * Titel       :
 * Erstellt    : 03.09.2008
 * Author      : Elisabeth Iwaniuk
 * Beschreibung:
 * Anmerkungen : EI 06.03.2009 V60 checked
 * Parameter   :
 * Rückgabe    : keine
 * Aufruf      :
 *
 */

package com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.MsgExpEntPos;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.PreviousDocument;

public class TsAZV extends Teilsatz {

    private String beznr  = "";       // Bezugsnummer
    private String posnr  = "";       // Positionsnummer
    private String vptyp  = "";       // Typ des Vorpapiers                                 //26.08.08
    private String azvref = "";       // Referenz zum Vorpapier
    private String azvzus = "";       // Zusatz zum Vorpapier

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
        vptyp = fields[3];
        if (size < 5 ) return;
        azvref = fields[4];
        if (size < 6 ) return;
        azvzus = fields[5];
    }

	public String teilsatzBilden() {
		
        StringBuffer buff = new StringBuffer();
        
        buff = buff.append(tsTyp);
        buff = buff.append(trenner);
        buff = buff.append(beznr);
        buff = buff.append(trenner);
        buff = buff.append(posnr);
        buff = buff.append(trenner);
        buff = buff.append(vptyp);
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
	public String getVptyp() {
		return vptyp;
	}
	public void setVptyp(String vptyp) {
		this.vptyp = Utils.checkNull(vptyp);
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
	
	public void setAzvSubset(MsgExpEntPos msgExpEntPos, String beznr ) {
		PreviousDocument previousDocument 	= null;
		
		if (msgExpEntPos == null) return;
		if (Utils.isStringEmpty(beznr)) return;
		if (Utils.isStringEmpty(msgExpEntPos.getItemNumber())) return;

		this.setBeznr(beznr);
		this.setPosnr(msgExpEntPos.getItemNumber());
		
		previousDocument = msgExpEntPos.getPreviousDocument();
		
		if (previousDocument != null) {
			this.setVptyp(previousDocument.getType());   
			this.setAzvref(previousDocument.getMarks());   
			this.setAzvzus(previousDocument.getAdditionalInformation());  		
		}
	}

	
	public boolean isEmpty() {
		if ( Utils.isStringEmpty(vptyp) && Utils.isStringEmpty(azvref)&& Utils.isStringEmpty(azvzus) )
    		return true;
		else
			return false;
	}
}




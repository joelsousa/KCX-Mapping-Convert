/*
 * Funktion    : TsEPO.java
 * Titel       :
 * Erstellt    : 14.10.2008
 * Author      : M.Houdek
 * Beschreibung:
 * Anmerkungen :
 * Parameter   :
 * Rückgabe    : keine
 * Aufruf      :
 *
 */

package com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V53;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

public class TsEPO extends Teilsatz {

    private String beznr  = "";                       //Bezugsnummer
    private String posnr  = "";                       //Positionsnummer
    private String basbtg = "";                      //Basisbetrag zur Berechnung des AH-Wertes
    private String baswrg = "";                      //Währung zum Basisbetrag

    public TsEPO() {
        tsTyp = "EPO";
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
        basbtg      = fields[3];
        if (size < 5 ) return;	
        baswrg      = fields[4];
    }
    
	public String teilsatzBilden() {
		
        StringBuffer buff = new StringBuffer();
        
        buff = buff.append(tsTyp);
        buff = buff.append(trenner);
        buff = buff.append(beznr);
        buff = buff.append(trenner);
        buff = buff.append(posnr);
        buff = buff.append(trenner);
        buff = buff.append(basbtg);
        buff = buff.append(trenner); 
        buff = buff.append(baswrg);
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

	public String getBasbtg() {
		return basbtg;
	}

	public void setBasbtg(String basbtg) {
		this.basbtg = Utils.checkNull(basbtg);
	}

	public String getBaswrg() {
		return baswrg;
	}

	public void setBaswrg(String baswrg) {
		this.baswrg = Utils.checkNull(baswrg);
	}

	public boolean isEmpty() {
		if ( Utils.isStringEmpty(basbtg) && Utils.isStringEmpty(baswrg))
    		return true;
		else
			return false;
	}
}





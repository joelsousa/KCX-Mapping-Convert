package com.kewill.kcx.component.mapping.formats.fss.base.subsets;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Modul        :   Basis
 * Nachricht    :   ANR (Internal Status Message)
 * Erstellt     :   03.09.2008
 * Beschreibung :   TsANR - Teilsatz Auftragsnummer zu STI (subset Order Number to STI)
 *  
 * @author          Sven Heise
 * 
 */

public class TsANR extends Teilsatz {

	private String beznr = " ";        // Bezugsnummer
	private String aufnr = " ";        // Auftragsnummer beim Kunden

    public TsANR() {
        tsTyp = "ANR";
    }

	public void setFields(String[] fields) {
        tsTyp   = fields[0];
        beznr   = fields[1];
        aufnr   = fields[2];
	}

	public String teilsatzBilden() {
		StringBuffer buff = new StringBuffer();
		
        buff = buff.append(tsTyp);
        buff = buff.append(trenner);
        buff = buff.append(beznr);
        buff = buff.append(trenner);
        buff = buff.append(aufnr);
        buff = buff.append(trenner);
        return new String(buff);
	}

	public String getBeznr() {
		return beznr;
	}

	public void setBeznr(String beznr) {
		this.beznr = beznr;
	}

	public String getAufnr() {
		return aufnr;
	}

	public void setAufnr(String aufnr) {
		this.aufnr = aufnr;
	}


	public boolean isEmpty() {
		//Utils.isStringEmpty(beznr)  &&
		if ( Utils.isStringEmpty(aufnr))		
			return true;
		else
			return false;
	} 	
}


package com.kewill.kcx.component.mapping.formats.fss.base.subsets;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Modul        :   Basis
 * Nachricht    :   STI (Internal Status Message)
 * Erstellt     :   03.09.2008
 * Beschreibung :   TsSTI
 *  
 * @author          Sven Heise
 * 
 */

public class TsSTI extends Teilsatz {

	private String beznr	= " ";        // 	STR(35)	Bezugsnummer M
	private String korant	= " ";        // 	NUM(03)	Korrekturnummer M
	private String regnr	= " ";        // 	STR(21)	Registriernummer K
	private String arbnr	= " ";        // 	STR(21)	Arbeitsnummer K
	private String datum	= " ";        // 	STR(08)	Datum der Statusänderung Format JJJJMMTT M
	private String zeit		= " ";        // 	NUM(04)	Uhrzeit der Statusänderung Format HHMM	M
	private String status	= " ";        // 	NUM(02)	Neuer Statuswert der Zollanmeldung	s.u.	M

    public TsSTI() {
        tsTyp = "STI";
    }

	public void setFields(String[] fields) {
        tsTyp   = fields[0];
        beznr   = fields[1];
        korant  = fields[2];
        regnr	= fields[3];
        arbnr  	= fields[4];
        datum	= fields[5];
        zeit	= fields[6];
        status	= fields[7];
	}

	public String teilsatzBilden() {
        StringBuffer buff = new StringBuffer();
        
        buff = buff.append(tsTyp);
        buff = buff.append(trenner);
        buff = buff.append(beznr);
        buff = buff.append(trenner);
        buff = buff.append(korant);
        buff = buff.append(trenner);
        buff = buff.append(regnr);
        buff = buff.append(trenner);
        buff = buff.append(arbnr);
        buff = buff.append(trenner);
        buff = buff.append(datum);
        buff = buff.append(trenner);
        buff = buff.append(zeit);
        buff = buff.append(trenner);
        buff = buff.append(status);
        buff = buff.append(trenner);
        
        return new String(buff);
	}

	public String getBeznr() {
		return beznr;
	}

	public void setBeznr(String beznr) {
		this.beznr = beznr;
	}

	public String getKorant() {
		return korant;
	}

	public void setKorant(String korant) {
		this.korant = korant;
	}

	public String getRegnr() {
		return regnr;
	}

	public void setRegnr(String regnr) {
		this.regnr = regnr;
	}

	public String getArbnr() {
		return arbnr;
	}

	public void setArbnr(String arbnr) {
		this.arbnr = arbnr;
	}

	public String getDatum() {
		return datum;
	}

	public void setDatum(String datum) {
		this.datum = datum;
	}

	public String getZeit() {
		return zeit;
	}

	public void setZeit(String zeit) {
		this.zeit = zeit;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	
	public boolean isEmpty() {
		//Utils.isStringEmpty(beznr)  &&
		if ( Utils.isStringEmpty(korant) && Utils.isStringEmpty(regnr) 
		  && Utils.isStringEmpty(arbnr) && Utils.isStringEmpty(datum) 		  
		  && Utils.isStringEmpty(zeit) && Utils.isStringEmpty(status))	
			return true;
		else
			return false;
	} 	

}

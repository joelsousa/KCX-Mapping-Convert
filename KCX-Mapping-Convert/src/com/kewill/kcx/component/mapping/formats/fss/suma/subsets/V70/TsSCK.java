package com.kewill.kcx.component.mapping.formats.fss.suma.subsets.V70;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;


/**
 * Module		:	Manifest
 * Created		:	17.06.2013
 * Description	:   Kopfsatz Verarbeitungsergebnisse. 
 *        			Zabis Version 70  
 *
 * @author iwaniuk
 * @version 7.0.00
 */

public class TsSCK  extends Teilsatz {

	private String nagru	= "";	   	// Nachrichtengruppe
	private String beznr	= "";		// Bezugsnummer
	private String regnr	= "";		// Registriernummer
	private String regdat	= "";		// Registrierdatum
	private String vgref	= "";		// Kennung "Kopf/Position"
	private String befnum	= "";		// Nummer des Beförderungsmittels
	private String ankdat	= "";		// Ankunftsdatum
	private String edinr	= "";		// EDIFACT-Nummer
	private String empdat	= "";		// Empfangsdatum der Nachricht	
	
    public TsSCK() {
        tsTyp = "SCK";
    }

    public void setFields(String[] fields) {  
		int size = fields.length;
		//String ausgabe = "FSS: "+fields[0]+" size = "+ size;
		//Utils.log( ausgabe);
				
		
		if (size < 1) { return; }		
        tsTyp = fields[0];
        if (size < 2) { return; }		
        nagru = fields[1];
        if (size < 3) { return; }	
        beznr = fields[2];
        if (size < 4) { return; }	
        regnr = fields[3];
        if (size < 5) { return; }	
        regdat = fields[4];
        if (size < 6) { return; }	
        vgref = fields[5];
        if (size < 7) { return; }	
        befnum = fields[6];
        if (size < 8) { return; }	
        ankdat = fields[7];
        if (size < 9) { return; }	
        edinr = fields[8];
        if (size < 10) { return; }	
        empdat = fields[9];
      }

    public String teilsatzBilden() {
        StringBuffer buff = new StringBuffer();

        buff.append(tsTyp);
        buff.append(trenner);
        buff.append(nagru);
        buff.append(trenner);
        buff.append(beznr);
        buff.append(trenner);
        buff.append(regnr);
        buff.append(trenner);
        buff.append(regdat);
        buff.append(trenner);
        buff.append(vgref);
        buff.append(trenner);
        buff.append(befnum);
        buff.append(trenner);
        buff.append(ankdat);
        buff.append(trenner);
        buff.append(edinr);
        buff.append(trenner);
        buff.append(empdat);
        buff.append(trenner);
       
        return new String(buff);
    }	

	public String getTsTyp() {
		return tsTyp;
	}
	public void setTsTyp(String tsTyp) {
		this.tsTyp = Utils.checkNull(tsTyp);
	}
	
	public String getBeznr() {
		return beznr;
	}
	public void setBeznr(String beznr) {
		this.beznr = Utils.checkNull(beznr);
	}

	public String getNagru() {
		return nagru;
	}

	public void setNagru(String value) {
		this.nagru = Utils.checkNull(value);
	}

	public String getRegnr() {
		return regnr;
	}
	public void setRegnr(String value) {
		this.regnr = Utils.checkNull(value);
	}

	public String getRegdat() {
		return regdat;
	}
	public void setRegdat(String value) {
		this.regdat = Utils.checkNull(value);
	}

	public String getVgref() {
		return vgref;
	}
	public void setVgref(String value) {
		this.vgref = Utils.checkNull(value);
	}

	public String getBefnum() {
		return befnum;
	}
	public void setBefnum(String befnum) {
		this.befnum = Utils.checkNull(befnum);
	}

	public String getAnkdat() {
		return ankdat;
	}
	public void setAnkdat(String ankdat) {
		this.ankdat = Utils.checkNull(ankdat);
	}
	
	public String getEdinr() {
		return edinr;
	}
	public void setEdinr(String edinr) {
		this.edinr = Utils.checkNull(edinr);
	}

	public String getEmpdat() {
		return empdat;
	}
	public void setEmpdat(String empdat) {
		this.empdat = Utils.checkNull(empdat);
	}

	public boolean isEmpty() {
		return (Utils.isStringEmpty(nagru) && Utils.isStringEmpty(regnr) &&
			Utils.isStringEmpty(regdat) && Utils.isStringEmpty(vgref) &&
			Utils.isStringEmpty(befnum) && Utils.isStringEmpty(ankdat) &&
			Utils.isStringEmpty(edinr) && Utils.isStringEmpty(empdat));
	}

}



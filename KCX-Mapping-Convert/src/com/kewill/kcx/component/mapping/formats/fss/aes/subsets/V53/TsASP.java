package com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V53;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/*
* Funktion    : TsASP.java
* Titel       :
* Erstellt    : 03.09.2008
* Author      : CSF GmbH / Houdek
* Beschreibung: 
* Anmerkungen : 
* Parameter   : 
* Rückgabe    : keine
* Aufruf      : 
*
* Änderungen:
* -----------
* Author      :
* Datum       :
* Kennzeichen :
* Beschreibung:
* Anmerkungen :
* Parameter   :
*
*/

public class TsASP extends Teilsatz {

    private String tsTyp	= "";       //  TS-Schlüssel
    private String beznr	= "";       //  Bezugsnummer
    private String ackdat	= "";       //  Zeitpunkt der Entgegennahme - Format: YYYYMMDDHHMM
    private String andat	= "";       //  Zeitpunkt der Annahme
    private String uebdat	= "";       //  Zeitpunkt der Überlassung
    private String gstdat	= "";       //  Zeitpunkt der Ablehnung des Antrags auf Gestellung nach §9
    private String gststr	= "";       //  Zeitpunkt des Beginns des Verladens/Verpackens bei Gestellung
    private String gstend	= "";       //  Zeitpunkt des Endes des Verladens/Verpackens bei Gestellung außerhalb des Amtsplatz
    private String bwbdat	= "";       //  Zeitpunkt des Beginns der Weiterbearbeitung außerhalb AES
    private String stodat	= "";       //  Zeitpunkt der Stornierung
    private String extdat	= "";       //  Zeitpunkt des Ausgangs

    public TsASP() {
        tsTyp = "ASP";
    }
    
    
    public void setFields(String[] fields) {
		int size = fields.length;
		String ausgabe = "FSS: "+fields[0]+" size = "+ size;
		//Utils.log( ausgabe);
			
		
		if (size < 1 ) return;		
        tsTyp = fields[0];
        if (size < 2 ) return;	
        beznr     = fields[1];
        if (size < 3 ) return;
        ackdat    = fields[2];
        if (size < 4 ) return;
        andat     = fields[3];
        if (size < 5 ) return;
        uebdat    = fields[4];
        if (size < 6 ) return;
        gstdat    = fields[5];
        if (size < 7 ) return;
        gststr    = fields[6];
        if (size < 8 ) return;
        gstend    = fields[7];
        if (size < 9 ) return;
        bwbdat    = fields[8];
        if (size < 10 ) return;
        stodat    = fields[9];
        if (size < 11 ) return;
        extdat    = fields[10];
        if (size < 12 ) return;

    }
	public String teilsatzBilden() {
        StringBuffer buff = new StringBuffer();
        
        buff = buff.append(tsTyp);
        buff = buff.append(trenner);
        buff = buff.append(beznr);
        buff = buff.append(trenner);
        buff = buff.append(ackdat);
        buff = buff.append(trenner);
        buff = buff.append(andat);
        buff = buff.append(trenner);
        buff = buff.append(uebdat);
        buff = buff.append(trenner);
        buff = buff.append(gstdat);
        buff = buff.append(trenner);
        buff = buff.append(gststr);
        buff = buff.append(trenner);
        buff = buff.append(gstend);
        buff = buff.append(trenner);
        buff = buff.append(bwbdat);
        buff = buff.append(trenner);
        buff = buff.append(stodat);
        buff = buff.append(trenner);
        buff = buff.append(extdat);
            
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


	public String getAckdat() {
		return ackdat;
	}


	public void setAckdat(String ackdat) {
		this.ackdat = Utils.checkNull(ackdat);
	}


	public String getAndat() {
		return andat;
	}


	public void setAndat(String andat) {
		this.andat = Utils.checkNull(andat);
	}


	public String getUebdat() {
		return uebdat;
	}


	public void setUebdat(String uebdat) {
		this.uebdat = Utils.checkNull(uebdat);
	}


	public String getGstdat() {
		return gstdat;
	}


	public void setGstdat(String gstdat) {
		this.gstdat = Utils.checkNull(gstdat);
	}


	public String getGststr() {
		return gststr;
	}


	public void setGststr(String gststr) {
		this.gststr = Utils.checkNull(gststr);
	}


	public String getGstend() {
		return gstend;
	}


	public void setGstend(String gstend) {
		this.gstend = Utils.checkNull(gstend);
	}


	public String getBwbdat() {
		return bwbdat;
	}


	public void setBwbdat(String bwbdat) {
		this.bwbdat = Utils.checkNull(bwbdat);
	}


	public String getStodat() {
		return stodat;
	}


	public void setStodat(String stodat) {
		this.stodat = Utils.checkNull(stodat);
	}


	public String getExtdat() {
		return extdat;
	}


	public void setExtdat(String extdat) {
		this.extdat = Utils.checkNull(extdat);
	}
	
	public boolean isEmpty() {
		if ( Utils.isStringEmpty(ackdat)  && Utils.isStringEmpty(andat) && Utils.isStringEmpty(uebdat)  
		 	&& Utils.isStringEmpty(gstdat) && Utils.isStringEmpty(gststr)&& Utils.isStringEmpty(gstend)
		 	&& Utils.isStringEmpty(bwbdat) && Utils.isStringEmpty(stodat)&& Utils.isStringEmpty(extdat)) 
	   		return true;
		else
			return false;
	}
   
}









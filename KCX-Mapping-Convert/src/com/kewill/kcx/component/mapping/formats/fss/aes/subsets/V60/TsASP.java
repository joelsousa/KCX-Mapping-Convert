package com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/*
* Funktion    : TsASP.java
* Titel       :
* Erstellt    : 03.09.2008
* Author      : CSF GmbH / Alfred Krzoska
* Beschreibung: 
* Anmerkungen : 
* Parameter   : 
* R�ckgabe    : keine
* Aufruf      : 
*
* �nderungen:
* -----------
* Author      : CSF GmbH / Alfred Krzoska
* Datum       : 03.03.2009
* Kennzeichen :
* Beschreibung:
* Anmerkungen :
* Parameter   :
*
*/

public class TsASP extends Teilsatz {

    private String tsTyp	= "";       //  TS-Schl�ssel
    private String beznr	= "";       //  Bezugsnummer
    private String ackdat	= "";       //  Zeitpunkt der Entgegennahme - Format: YYYYMMDDHHMM
    private String andat	= "";       //  Zeitpunkt der Annahme
    private String uebdat	= "";       //  Zeitpunkt der �berlassung
    private String gstdat	= "";       //  Zeitpunkt der Ablehnung des Antrags auf Gestellung nach �9
    private String gststr	= "";       //  Zeitpunkt des Beginns des Verladens/Verpackens bei Gestellung
    private String gstend	= "";       //  Zeitpunkt des Endes des Verladens/Verpackens bei Gestellung au�erhalb des Amtsplatz
    private String bwbdat	= "";       //  Zeitpunkt des Beginns der Weiterbearbeitung au�erhalb AES
    private String stodat	= "";       //  Zeitpunkt der Stornierung
    private String extdat	= "";       //  Zeitpunkt des Ausgangs
    private String inddat	= "";       //  Zeitpunkt der Vorank�ndigung    
    private String iawdat	= "";       //  Zeitpunkt der Abweisung der Vorank�ndigung   
    private String erldat	= "";       //  Zeitpunkt der Erledigung      
    private String eamdst	= "";       //  EAM-Ausfuhrzollstelle                                           
    private String tinan	= "";       //  TIN des Anmelder                                               
    private String dtzoan	= "";       //  Kennzeichen dt. Zollnummer ist die TIN des Anmelders            
    private String tinva	= "";       //  TIN des Vertreters                                              
    private String dtzova	= "";       //  Kennzeichen dt. Zollnummer ist die TIN des Vertreters          

    
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
        inddat    = fields[11]; 
        if (size < 13 ) return;
        iawdat    = fields[12];  
        if (size < 14 ) return;
        erldat    = fields[13];            
        if (size < 15 ) return;
        eamdst    = fields[14]; 
        if (size < 16 ) return;
        tinan     = fields[15];
        if (size < 17 ) return;
        dtzoan    = fields[16];  
        if (size < 18 ) return;
        tinva     = fields[17];  
        if (size < 19 ) return;
        dtzova    = fields[18];                                                     
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
        buff = buff.append(trenner);
        buff = buff.append(inddat);
        buff = buff.append(trenner);
        buff = buff.append(iawdat);
        buff = buff.append(trenner);   
        buff = buff.append(erldat);
        buff = buff.append(trenner);           
        buff = buff.append(eamdst);
        buff = buff.append(trenner);
        buff = buff.append(tinan);
        buff = buff.append(trenner);
        buff = buff.append(dtzoan);
        buff = buff.append(trenner);
        buff = buff.append(tinva);
        buff = buff.append(trenner);
        buff = buff.append(dtzova);
        buff = buff.append(trenner);
            
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


	public String getInddat() {
		return inddat;
	}


	public void setInddat(String inddat) {
		this.inddat = Utils.checkNull(inddat);
	}


	public String getEamdst() {
		return eamdst;
	}


	public void setEamdst(String eamdst) {
		this.eamdst = Utils.checkNull(eamdst);
	}


	public String getTinan() {
		return tinan;
	}


	public void setTinan(String tinan) {
		this.tinan = Utils.checkNull(tinan);
	}


	public String getDtzoan() {
		return dtzoan;
	}


	public void setDtzoan(String dtzoan) {
		this.dtzoan = Utils.checkNull(dtzoan);
	}


	public String getTinva() {
		return tinva;
	}


	public void setTinva(String tinva) {
		this.tinva = Utils.checkNull(tinva);
	}


	public String getDtzova() {
		return dtzova;
	}


	public void setDtzova(String dtzova) {
		this.dtzova = Utils.checkNull(dtzova);
	}

	public String getIawdat() {
		return iawdat;
	}


	public void setIawdat(String iawdat) {
		this.iawdat = Utils.checkNull(iawdat);
	}


	public String getErldat() {
		return erldat;
	}


	public void setErldat(String erldat) {
		this.erldat = Utils.checkNull(erldat);
	}
	
	public boolean isEmpty() {
		if ( Utils.isStringEmpty(ackdat)  && Utils.isStringEmpty(andat) && Utils.isStringEmpty(uebdat)  
		 	&& Utils.isStringEmpty(gstdat) && Utils.isStringEmpty(gststr)&& Utils.isStringEmpty(gstend)
		 	&& Utils.isStringEmpty(bwbdat) && Utils.isStringEmpty(stodat)&& Utils.isStringEmpty(extdat)
		 	&& Utils.isStringEmpty(inddat) && Utils.isStringEmpty(eamdst)&& Utils.isStringEmpty(tinan)	
		  	&& Utils.isStringEmpty(iawdat) && Utils.isStringEmpty(erldat)
		  	&& Utils.isStringEmpty(dtzoan) && Utils.isStringEmpty(tinva)&& Utils.isStringEmpty(dtzova) )	
			return true;
		else
			return false;
	}

   
}









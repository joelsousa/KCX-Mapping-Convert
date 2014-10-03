/**
 * Modul        :   ACN
 * Teilsatz     :   TsACN.java
 * Erstellt     :   07.04.2006
 * Beschreibung :   Containerdaten ACN
 *
 * 03.09.2008       Version 6  Miro Houdek
 * Anmerkungen :    EI 06.03.2009 - V60 checked
 */
 

package com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

public class TsACN extends Teilsatz {

    private String tsTyp     = "";       // Ts-Schlüssel
    private String beznr     = "";       // Bezugsnummer
    private String posnr     = "";       // Positionsnummer
    private String connr     = "";       // Containernummer


    public TsACN() {
        tsTyp = "ACN";
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
        connr = fields[3];
    }


    public String teilsatzBilden() {
        StringBuffer buff = new StringBuffer();

        buff.append(tsTyp);
        buff.append(trenner);
        buff.append(beznr);
        buff.append(trenner);
        buff.append(posnr);
        buff.append(trenner);
        buff.append(connr);
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

	public String getPosnr() {
		return posnr;
	
	}

	public void setPosnr(String posnr) {
		this.posnr = Utils.checkNull(posnr);
	}

	public String getConnr() {
		return connr;
	
	}
	
	public void setAcnSubset(String containerNumber, String aBeznr, String aPosnr) {
		if (Utils.isStringEmpty(containerNumber)) return;	

		this.setBeznr(aBeznr);  				 
		this.setPosnr(aPosnr);  
		this.setConnr(containerNumber);
	}


	public void setConnr(String connr) {
		this.connr = Utils.checkNull(connr);
	}
	
	public boolean isEmpty() {
		//Utils.isStringEmpty(beznr)  &&
		if ( Utils.isStringEmpty(connr) ) 
			return true;
		else
			return false;
	} 

}



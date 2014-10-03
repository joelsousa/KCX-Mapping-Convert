package com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/*
* Funktion    : TsVSU.java
* Titel       :
* Erstellt    : 27.10.2010
* Author      : Kewill / Christine Kron
* 
* Description : subset VSU refers to ve-fss-62.doc 

* Parameter   : 
* Return      : keine

*
* Changes:
* -----------
* Author      :  
* Date        :  
* Kennzeichen :
* Description :
*             
*
*/


/**
 * Modul		: TsVSU<br>
 * Date			: 8.9.2010<br>
 * Description	: Definition of subset TsVSU.
 * 
 * @author Christine Kron
 * @version 1.0.00
 */
public class TsVSU extends Teilsatz {
    
	private String beznr  	= "";      	// Bezugsnummer
	private String posnr  	= "";		// Positionsnummer
	private String knrbf  	= "";		// Kundennummer Beförderer
	private String tinbf  	= "";		// TIN Beförderer
	private String bfgnr  	= "";		// Nummer der Beförderung
	private String ldeort  	= "";		// Ladeort
	private String eldort  	= "";		// Entladeort
	private String knrvsu  	= "";		// Kundennummer Versender SumA-Sicherheit
	private String tinvsu  	= "";		// TIN Versender SumA-Sicherheit
	private String knresu  	= "";		// Kundennummer Empfänger SumA-Sicherheit
	private String tinesu  	= "";		// TIN Empfänger SumA-Sicherheit


    public TsVSU() {
        tsTyp = "VSU";
    }

    public String teilsatzBilden() {
        StringBuffer buff = new StringBuffer();
          
        buff.append(tsTyp);
        buff.append(trenner);        
        buff.append(beznr);
        buff.append(trenner);	
        buff.append(posnr);
        buff.append(trenner);	
        buff.append(knrbf);
        buff.append(trenner);	
        buff.append(tinbf);
        buff.append(trenner);	
        buff.append(bfgnr);
        buff.append(trenner);	
        buff.append(ldeort);
        buff.append(trenner);	
        buff.append(eldort);
        buff.append(trenner);	
        buff.append(knrvsu);
        buff.append(trenner);	
        buff.append(tinvsu);
        buff.append(trenner);	
        buff.append(knresu);
        buff.append(trenner);	
        buff.append(tinesu);
        buff.append(trenner);	

    	return buff.toString();
    }

	// setFields will not be used because VSU is a specific subset of the message VAN
	// and this FSS-message is created by KCX to be sent to Zabis
	// only messages from Zabis have to write out this method

	public void setFields(String[] fields) {
		Utils.log("TsVSU.setFields not created");
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

	public String getKnrbf() {
		return knrbf;
	}

	public void setKnrbf(String knrbf) {
		this.knrbf = Utils.checkNull(knrbf);
	}

	public String getTinbf() {
		return tinbf;
	}

	public void setTinbf(String tinbf) {
		this.tinbf = Utils.checkNull(tinbf);
	}

	public String getBfgnr() {
		return bfgnr;
	}

	public void setBfgnr(String bfgnr) {
		this.bfgnr = Utils.checkNull(bfgnr);
	}

	public String getLdeort() {
		return ldeort;
	}

	public void setLdeort(String ldeort) {
		this.ldeort = Utils.checkNull(ldeort);
	}

	public String getEldort() {
		return eldort;
	}

	public void setEldort(String eldort) {
		this.eldort = Utils.checkNull(eldort);
	}

	public String getKnrvsu() {
		return knrvsu;
	}

	public void setKnrvsu(String knrvsu) {
		this.knrvsu = Utils.checkNull(knrvsu);
	}

	public String getTinvsu() {
		return tinvsu;
	}

	public void setTinvsu(String tinvsu) {
		this.tinvsu = Utils.checkNull(tinvsu);
	}

	public String getKnresu() {
		return knresu;
	}

	public void setKnresu(String knresu) {
		this.knresu = Utils.checkNull(knresu);
	}

	public String getTinesu() {
		return tinesu;
	}

	public void setTinesu(String tinesu) {
		this.tinesu = Utils.checkNull(tinesu);
	}
	
	public boolean isEmpty() {
		return (Utils.isStringEmpty(knrbf) && Utils.isStringEmpty(tinbf) &&
				Utils.isStringEmpty(bfgnr) && Utils.isStringEmpty(ldeort) && 
				Utils.isStringEmpty(eldort) && Utils.isStringEmpty(knrvsu) && 
				Utils.isStringEmpty(tinvsu) && Utils.isStringEmpty(knresu) && 
				Utils.isStringEmpty(tinesu));		
	}

}




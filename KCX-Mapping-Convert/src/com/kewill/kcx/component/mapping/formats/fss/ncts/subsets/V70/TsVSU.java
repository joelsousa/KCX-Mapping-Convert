package com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.V70;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: NCTS OUT<br>
 * Created		: 12.11.2012<br>
 * Description	: TsVSU: Ergänzungssatz SumA-Sicherheit.
 *  
 * @author iwaniuk
 * @version 7.0.00
 */

public class TsVSU extends Teilsatz {
    
	private String beznr  	= "";      	// Bezugsnummer
	private String posnr  	= "";		// Positionsnummer
	private String knrbf  	= "";		// Kundennummer Beförderer
	private String tinbf  	= "";		// EORI Beförderer
	private String nlbf  	= "";  //new V70
	private String dtzobf  	= "";  //new V70
	private String bfgnr  	= "";		// Nummer der Beförderung
	private String ldeort  	= "";		// Ladeort
	private String eldort  	= "";		// Entladeort
	private String knrvsu  	= "";		// Kundennummer Versender SumA-Sicherheit
	private String tinvsu  	= "";		// EORI Versender SumA-Sicherheit
	private String nlvsu  	= "";  //new V70
	private String dtzovsu  = "";  //new V70
	private String knresu  	= "";		// Kundennummer Empfänger SumA-Sicherheit
	private String tinesu  	= "";		// EORI Empfänger SumA-Sicherheit
	private String nlesu  	= "";  //new V70
	private String dtzoesu  = "";  //new V70


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
        buff.append(nlbf);
        buff.append(trenner);	
        buff.append(dtzobf);
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
        buff.append(nlvsu);
        buff.append(trenner);	
        buff.append(dtzovsu);
        buff.append(trenner);	
        buff.append(knresu);
        buff.append(trenner);	
        buff.append(tinesu);
        buff.append(trenner);	
        buff.append(nlesu);
        buff.append(trenner);
        buff.append(dtzoesu);
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

	public void setTinesu(String value) {
		this.tinesu = Utils.checkNull(value);
	}	
	
	public String getNlbf() {
		return nlbf;
	}

	public void setNlbf(String value) {
		this.nlbf = Utils.checkNull(value);
	}

	public String getDtzobf() {
		return dtzobf;
	}

	public void setDtzobf(String value) {
		this.dtzobf = Utils.checkNull(value);
	}

	public String getNlvsu() {
		return nlvsu;
	}

	public void setNlvsu(String value) {
		this.nlvsu = Utils.checkNull(value);
	}

	public String getDtzovsu() {
		return dtzovsu;
	}

	public void setDtzovsu(String value) {
		this.dtzovsu = Utils.checkNull(value);
	}

	public String getNlesu() {
		return nlesu;
	}

	public void setNlesu(String value) {
		this.nlesu = Utils.checkNull(value);
	}

	public String getDtzoesu() {
		return dtzoesu;
	}

	public void setDtzoesu(String value) {
		this.dtzoesu = Utils.checkNull(value);
	}

	public boolean isEmpty() {
		return (Utils.isStringEmpty(knrbf) && Utils.isStringEmpty(tinbf) &&
				Utils.isStringEmpty(bfgnr) && Utils.isStringEmpty(ldeort) && 
				Utils.isStringEmpty(eldort) && Utils.isStringEmpty(knrvsu) && 
				Utils.isStringEmpty(tinvsu) && Utils.isStringEmpty(knresu) && 
				Utils.isStringEmpty(tinesu));		
	}

}




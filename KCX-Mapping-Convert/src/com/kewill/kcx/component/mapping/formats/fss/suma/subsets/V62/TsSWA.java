package com.kewill.kcx.component.mapping.formats.fss.suma.subsets.V62;

/**
 * Modul		:	TsSWA
 * Erstellt		:	19.11.2012
 * Beschreibung	:   Adresssatz  SWA
 *        			@version 06.02  Krzoska.
 *
 */


import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

public class TsSWA extends Teilsatz {

	private String beznr;			// Bezugsnummer
	private String regnr;			// Registriernummer
	private String posnr;			// Positionsnummer
	private String adrtyp;			// Adresstyp
	private String name1;			// Name
	private String name2;			
	private String name3;			
	private String str;				// Straﬂe	
	private String ort;				// Ort
	private String plz;				// Postleitzahl
	private String ldkz;				// Land
	private String oteil;			// Ortsteil
	private String postf;			// Postfach
	private String kzawb;			// Art des spezifischen Ordnungsbegriffes
	private String awbzzz;			// Spezifischer Ordnungsbegriff
	private String vrwznr;			// Zollnummer des Verwahrers
	
	public TsSWA() {    //EI20130617: hat hier gefehlt
        tsTyp = "SWA";
    }
	
	public void setFields(String[] fields) {
		int size = fields.length;
		
		if (size < 1) { return; }
		tsTyp	= fields[0];
		if (size < 2) { return; }
		beznr	= fields[1];
		if (size < 3) { return; }
		regnr	= fields[2];
		if (size < 4) { return; }
		posnr	= fields[3];
		if (size < 5) { return; }
		adrtyp	= fields[4];
		if (size < 6) { return; }
		name1	= fields[5];
		if (size < 7) { return; }
		name2	= fields[6];
		if (size < 8) { return; }
		name3	= fields[7];
		if (size < 9) { return; }
		str		= fields[8];
		if (size < 10) { return; }
		ort		= fields[9];
		if (size < 11) { return; }
		plz		= fields[10];
		if (size < 12) { return; }
		ldkz	= fields[11];
		if (size < 13) { return; }
		oteil	= fields[12];
		if (size < 14) { return; }		
		postf	= fields[13];
		if (size < 15) { return; }
		kzawb	= fields[14];
		if (size < 16) { return; }
		awbzzz	= fields[15];
		if (size < 17) { return; }
		vrwznr	= fields[16];

	}
	
	

	@Override
	public boolean isEmpty() {
		if (Utils.isStringEmpty(regnr) && Utils.isStringEmpty(name1) &&
			Utils.isStringEmpty(name2) && Utils.isStringEmpty(name3) &&
			Utils.isStringEmpty(str) && Utils.isStringEmpty(ort) &&
			Utils.isStringEmpty(plz) && Utils.isStringEmpty(ldkz) &&
			Utils.isStringEmpty(oteil) && Utils.isStringEmpty(postf) &&
			Utils.isStringEmpty(kzawb) && Utils.isStringEmpty(awbzzz) &&			
			Utils.isStringEmpty(vrwznr)) {
				return true;
			} else {
				return false;
			}	
	}

	@Override
	public String teilsatzBilden() {
		StringBuffer buff = new StringBuffer();
		
		buff.append(tsTyp);
		buff.append(trenner);
		buff.append(beznr);
		buff.append(trenner);
		buff.append(regnr);
		buff.append(trenner);
		buff.append(posnr);
		buff.append(trenner);
		buff.append(adrtyp);
		buff.append(trenner);
		buff.append(name1);
		buff.append(trenner);
		buff.append(name2);
		buff.append(trenner);	
		buff.append(name3);
		buff.append(trenner);	
		buff.append(str);
		buff.append(trenner);	
		buff.append(ort);
		buff.append(trenner);	
		buff.append(plz);
		buff.append(trenner);	
		buff.append(ldkz);
		buff.append(trenner);	
		buff.append(oteil);
		buff.append(trenner);	
		buff.append(postf);
		buff.append(trenner);	
		buff.append(kzawb);
		buff.append(trenner);	
		buff.append(awbzzz);
		buff.append(trenner);
		buff.append(vrwznr);
		buff.append(trenner);
		
		return buff.toString();
	}



	public String getBeznr() {
		return beznr;
	}



	public void setBeznr(String beznr) {
		this.beznr = Utils.checkNull(beznr);
	}



	public String getRegnr() {
		return regnr;
	}



	public void setRegnr(String regnr) {
		this.regnr = Utils.checkNull(regnr);
	}



	public String getPosnr() {
		return posnr;
	}



	public void setPosnr(String posnr) {
		this.posnr = Utils.checkNull(posnr);
	}



	public String getAdrtyp() {
		return adrtyp;
	}



	public void setAdrtyp(String adrtyp) {
		this.adrtyp = Utils.checkNull(adrtyp);
	}



	public String getName1() {
		return name1;
	}



	public void setName1(String name1) {
		this.name1 = Utils.checkNull(name1);
	}



	public String getName2() {
		return name2;
	}



	public void setName2(String name2) {
		this.name2 = Utils.checkNull(name2);
	}



	public String getName3() {
		return name3;
	}



	public void setName3(String name3) {
		this.name3 = Utils.checkNull(name3);
	}



	public String getStr() {
		return str;
	}



	public void setStr(String str) {
		this.str = Utils.checkNull(str);
	}



	public String getOrt() {
		return ort;
	}



	public void setOrt(String ort) {
		this.ort = Utils.checkNull(ort);
	}



	public String getPlz() {
		return plz;
	}



	public void setPlz(String plz) {
		this.plz = Utils.checkNull(plz);
	}



	public String getLdkz() {
		return ldkz;
	}



	public void setLdkz(String ldkz) {
		this.ldkz = Utils.checkNull(ldkz);
	}



	public String getOteil() {
		return oteil;
	}



	public void setOteil(String oteil) {
		this.oteil = Utils.checkNull(oteil);
	}



	public String getPostf() {
		return postf;
	}



	public void setPostf(String postf) {
		this.postf = Utils.checkNull(postf);
	}



	public String getKzawb() {
		return kzawb;
	}



	public void setKzawb(String kzawb) {
		this.kzawb = Utils.checkNull(kzawb);
	}



	public String getAwbzzz() {
		return awbzzz;
	}



	public void setAwbzzz(String awbzzz) {
		this.awbzzz = Utils.checkNull(awbzzz);
	}



	public String getVrwznr() {
		return vrwznr;
	}



	public void setVrwznr(String vrwznr) {
		this.vrwznr = Utils.checkNull(vrwznr);
	}
}

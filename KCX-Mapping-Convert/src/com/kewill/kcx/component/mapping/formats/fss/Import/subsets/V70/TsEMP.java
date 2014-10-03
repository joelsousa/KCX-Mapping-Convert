package com.kewill.kcx.component.mapping.formats.fss.Import.subsets.V70;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module       :   Import 70<br>
 * Subset       :   TsADR.java<br>
 * Created      :   12.11.2012<br>
 * Description  :   Zusätzlicher Empfänger EMP.  .<br>
 
 * @author          iwaniuk
 * @version         7.0.00 
 */

public class TsEMP extends Teilsatz {

	private String beznr    = "";      // Bezugsnummer
	private String kunnr    = "";      // Kundennummer
	//private String zbnr     = "";      // Zollnummer
	private String eori     = "";                          //new V70
	private String nlnr     = "";                          //new V70
	private String ustid    = "";      // Umsatzsteuer-ID
	private String anam1    = "";      // Name1
	private String anam2    = "";      // Name2
	private String anam3    = "";      // Name3
	private String str      = "";      // Strasse
	private String postf    = "";      // Postfach
	private String lnd      = "";      // Land
	private String plz      = "";      // Postleitzahl
	private String ort      = "";      // Ort
	private String oteil    = "";      // Ortsteil
	private String tel	    = "";      // Telefon
	private String fax	    = "";      // Fax
	private String email    = "";      // e-mail Adresse
	private String kontkt   = "";      // Kontakt
	private String bemerk    = "";      // Bemerkungen
	
	
	    
    public TsEMP() {
	        tsTyp = "EMP";
    }

	public void setFields(String[] fields) {
		int size = fields.length;
		//String ausgabe = "FSS: " + fields[0] + " size = " + size;
		//Utils.log( ausgabe);
			
		if (size < 1) { return; }
		tsTyp = fields[0];
		if (size < 2) { return; }	
	    beznr       = fields[1];
	    if (size < 3) { return; }
	    kunnr		= fields[2];
	    if (size < 4) { return; }
	    eori		= fields[3];
	    if (size < 5) { return; }
	    nlnr		= fields[4];
	    if (size < 6) { return; }
	    ustid		= fields[5];
	    if (size < 7) { return; }
	    anam1        = fields[6];
	    if (size < 8) { return; }
	    anam2		= fields[7];
	    if (size < 9) { return; }
	    anam3		 = fields[8];
	    if (size < 10) { return; }
	    str         = fields[9];
	    if (size < 11) { return; }
	    postf       = fields[10];
	    if (size < 12) { return; }
	    lnd         = fields[11];
	    if (size < 13) { return; }
	    plz			= fields[12];
	    if (size < 14) { return; }
	    ort			= fields[13];
	    if (size < 15) { return; }
	    oteil		= fields[14];
	    if (size < 16) { return; }
	    tel	        = fields[15];
	    if (size < 17) { return; }
	    fax			= fields[16];
	    if (size < 18) { return; }
	    email		 = fields[17];
	    if (size < 19) { return; }
	    kontkt       = fields[18];
	    if (size < 20) { return; }
	    bemerk       = fields[19];
	}

    public String teilsatzBilden() {
        StringBuffer buff = new StringBuffer();

        buff.append(tsTyp);
        buff.append(trenner);
        buff.append(beznr);
        buff.append(trenner);
        buff.append(kunnr);
        buff.append(trenner);
        buff.append(eori);
        buff.append(trenner);
        buff.append(nlnr);
        buff.append(trenner);
        buff.append(ustid);
        buff.append(trenner);
        buff.append(anam1);
        buff.append(trenner);
        buff.append(anam2);
        buff.append(trenner);
        buff.append(anam3);
        buff.append(trenner);
        buff.append(str);
        buff.append(trenner);
        buff.append(postf);
        buff.append(trenner);
        buff.append(lnd);
        buff.append(trenner);
        buff.append(plz);
        buff.append(trenner);
        buff.append(ort);
        buff.append(trenner);
        buff.append(oteil);
	    buff.append(trenner);
        buff.append(tel);
        buff.append(trenner);
        buff.append(fax);
        buff.append(trenner);
        buff.append(email);
        buff.append(trenner);
        buff.append(kontkt);
        buff.append(trenner);
        buff.append(bemerk);
        buff.append(trenner);

        return new String(buff);
    }

    
	
	public String getBeznr() {
		return beznr;
	}

	public void setBeznr(String value) {
		this.beznr = Utils.checkNull(value);
	}

	public String getKunnr() {
		return kunnr;
	}

	public void setKunnr(String value) {
		this.kunnr = Utils.checkNull(value);
	}

	public String getEori() {
		return eori;
	}

	public void setEori(String value) {
		this.eori = Utils.checkNull(value);
	}

	public String getNlnr() {
		return nlnr;
	}

	public void setNlnr(String value) {
		this.nlnr = Utils.checkNull(value);
	}

	public String getUstid() {
		return ustid;
	}

	public void setUstid(String value) {
		this.ustid = Utils.checkNull(value);
	}

	public String getAnam1() {
		return anam1;
	}

	public void setAnam1(String value) {
		this.anam1 = Utils.checkNull(value);
	}

	public String getAnam2() {
		return anam2;
	}

	public void setAnam2(String value) {
		this.anam2 = Utils.checkNull(value);
	}

	public String getAnam3() {
		return anam3;
	}

	public void setAnam3(String value) {
		this.anam3 = Utils.checkNull(value);
	}

	public String getStr() {
		return str;
	}

	public void setStr(String value) {
		this.str = Utils.checkNull(value);
	}

	public String getPostf() {
		return postf;
	}

	public void setPostf(String value) {
		this.postf = Utils.checkNull(value);
	}

	public String getLnd() {
		return lnd;
	}

	public void setLnd(String value) {
		this.lnd = Utils.checkNull(value);
	}

	public String getPlz() {
		return plz;
	}

	public void setPlz(String value) {
		this.plz = Utils.checkNull(value);
	}

	public String getOrt() {
		return ort;
	}

	public void setOrt(String value) {
		this.ort = Utils.checkNull(value);
	}

	public String getOteil() {
		return oteil;
	}

	public void setOteil(String value) {
		this.oteil = Utils.checkNull(value);
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String value) {
		this.tel = Utils.checkNull(value);
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String value) {
		this.fax = Utils.checkNull(value);
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String value) {
		this.email = Utils.checkNull(value);
	}

	public String getKontkt() {
		return kontkt;
	}

	public void setKontkt(String value) {
		this.kontkt = Utils.checkNull(value);
	}

	public String getBemerk() {
		return bemerk;
	}

	public void setBemerk(String value) {
		this.bemerk = Utils.checkNull(value);
	}

	public boolean isEmpty() {
	String name = anam1 + anam2 + anam3;	
		
		return Utils.isStringEmpty(kunnr) && 
			   Utils.isStringEmpty(eori) &&
			   Utils.isStringEmpty(ustid) &&
			   Utils.isStringEmpty(name) &&
			   Utils.isStringEmpty(str) && 
			   Utils.isStringEmpty(postf) &&
			   Utils.isStringEmpty(lnd) && 
			   Utils.isStringEmpty(plz) && 
			   Utils.isStringEmpty(ort) &&
			   Utils.isStringEmpty(oteil) &&
			   Utils.isStringEmpty(tel) &&
			   Utils.isStringEmpty(fax) && 
			   Utils.isStringEmpty(email) && 
			   Utils.isStringEmpty(kontkt) &&
			   Utils.isStringEmpty(bemerk);
	}
}
    

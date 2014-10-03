/*
 * Funktion    : TsBZL.java
 * Titel       :
 * Erstellt    : 03.09.2008
 * Author      : Elisabeth Iwaniuk
 * Beschreibung:
 * Anmerkungen : EI 06.03.2009 V60 checked
 */

package com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

public class TsBZL extends Teilsatz {

    private String beznr  = "";              // Bezugsnummer
    private String posnr  = "";              // Positionsnummer
    private String vregnr = "";              // Registriernummer des zu erledigenden Vorgangs
    private String vposnr = "";              // Positionsnummer im zu erledigenden Vorgang
    private String atlas  = "";              // Kennzeichen Zugang in ATLAS
    private String kzuebl = "";              // Kennzeichen ¸bliche Behandlung
    private String wanr   = "";              // Warencodenummer
    private String mgeabg = "";              // Abgangsmenge
    private String meabg  = "";              // Maﬂeinheit zur Abgangsmenge
    private String qmeabg = "";              // Qualifikator zur Maﬂeinheit zur Abgangsmenge
    private String mgehdl = "";              // Handelsmenge
    private String mehdl  = "";              // Maﬂeinheit zur Handelsmenge
    private String qmehdl = "";              // Qualifikator zur Maﬂeinheit zur Handelsmenge
    private String txzus  = "";              // Zusatztext
    private String azvagl = "";              // Abgleich in ATLAS erfolgt

    public TsBZL() {
        tsTyp = "BZL";
    } 
    
    public void setFields(String[] fields)
	{
		int size = fields.length;
		String ausgabe = "FSS: "+fields[0]+" size = "+ size;
		//Utils.log( ausgabe);
			

		if (size < 1 ) return;		
        tsTyp = fields[0];
        if (size < 2 ) return;	
	        beznr       = fields[1];
	        if (size < 3 ) return;	
	        posnr       = fields[2];
	        if (size < 4 ) return;	
	        vregnr      = fields[3];
	        if (size < 5 ) return;	
	        vposnr      = fields[4];
	        if (size < 6 ) return;	
	        atlas       = fields[5];
	        if (size < 7 ) return;	
	        kzuebl      = fields[6];
	        if (size < 8 ) return;	
	        wanr        = fields[7];
	        if (size < 9 ) return;	
	        mgeabg      = fields[8];
	        if (size < 10 ) return;
	        meabg       = fields[9];
	        if (size < 11 ) return;
	        qmeabg      = fields[10];
	        if (size < 12 ) return;
	        mgehdl      = fields[11];
	        if (size < 13 ) return;
	        mehdl       = fields[12];
	        if (size < 14 ) return;
	        qmehdl      = fields[13];
	        if (size < 15 ) return;
	        txzus       = fields[14];
	        if (size < 16 ) return;
	        azvagl      = fields[15];
       }
    
	public String teilsatzBilden() {
		
        StringBuffer buff = new StringBuffer();
        
        buff = buff.append(tsTyp);
        buff = buff.append(trenner);
        buff = buff.append(beznr);
        buff = buff.append(trenner);
        buff = buff.append(posnr);
        buff = buff.append(trenner);
        buff = buff.append(vregnr);
        buff = buff.append(trenner);
        buff = buff.append(vposnr);
        buff = buff.append(trenner);
        buff = buff.append(atlas);
        buff = buff.append(trenner); 
        buff = buff.append(kzuebl);
        buff = buff.append(trenner);
        buff = buff.append(wanr);
        buff = buff.append(trenner);
        buff = buff.append(mgeabg);
        buff = buff.append(trenner);
        buff = buff.append(meabg);
        buff = buff.append(trenner);        
        buff = buff.append(qmeabg);
        buff = buff.append(trenner); 
        buff = buff.append(mgehdl);
        buff = buff.append(trenner);
        buff = buff.append(mehdl);
        buff = buff.append(trenner);
        buff = buff.append(qmehdl);
        buff = buff.append(trenner);
        buff = buff.append(txzus);
        buff = buff.append(trenner);
        buff = buff.append(azvagl);
        buff = buff.append(trenner);
        
        return new String(buff);
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

	public String getVregnr() {
		return vregnr;
	}

	public void setVregnr(String vregnr) {
		this.vregnr = Utils.checkNull(vregnr);
	}

	public String getVposnr() {
		return vposnr;
	}

	public void setVposnr(String vposnr) {
		this.vposnr = Utils.checkNull(vposnr);
	}

	public String getAtlas() {
		return atlas;
	}

	public void setAtlas(String atlas) {
		this.atlas = Utils.checkNull(atlas);
	}

	public String getKzuebl() {
		return kzuebl;
	}

	public void setKzuebl(String kzuebl) {
		this.kzuebl = Utils.checkNull(kzuebl);
	}

	public String getWanr() {
		return wanr;
	}

	public void setWanr(String wanr) {
		this.wanr = Utils.checkNull(wanr);
	}

	public String getMgeabg() {
		return mgeabg;
	}

	public void setMgeabg(String mgeabg) {
		this.mgeabg = Utils.checkNull(mgeabg);
	}

	public String getMeabg() {
		return meabg;
	}

	public void setMeabg(String meabg) {
		this.meabg = Utils.checkNull(meabg);
	}

	public String getQmeabg() {
		return qmeabg;
	}

	public void setQmeabg(String qmeabg) {
		this.qmeabg = Utils.checkNull(qmeabg);
	}

	public String getMgehdl() {
		return mgehdl;
	}

	public void setMgehdl(String mgehdl) {
		this.mgehdl = Utils.checkNull(mgehdl);
	}

	public String getMehdl() {
		return mehdl;
	}

	public void setMehdl(String mehdl) {
		this.mehdl = Utils.checkNull(mehdl);
	}

	public String getQmehdl() {
		return qmehdl;
	}

	public void setQmehdl(String qmehdl) {
		this.qmehdl = Utils.checkNull(qmehdl);
	}

	public String getTxzus() {
		return txzus;
	}

	public void setTxzus(String txzus) {
		this.txzus = Utils.checkNull(txzus);
	}

	public String getAzvagl() {
		return azvagl;
	}

	public void setAzvagl(String azvagl) {
		this.azvagl = Utils.checkNull(azvagl);
	} 
	public boolean isEmpty() {
		if ( Utils.isStringEmpty(vregnr)  && Utils.isStringEmpty(vposnr) && Utils.isStringEmpty(atlas)
		  && Utils.isStringEmpty(kzuebl) && Utils.isStringEmpty(wanr) && Utils.isStringEmpty(mgeabg)
		  && Utils.isStringEmpty(meabg) && Utils.isStringEmpty(qmeabg) && Utils.isStringEmpty(mgehdl)
		  && Utils.isStringEmpty(mehdl)  && Utils.isStringEmpty(qmehdl) && Utils.isStringEmpty(txzus)
		  && Utils.isStringEmpty(azvagl))
			return true;
		else
			return false;
	}

}


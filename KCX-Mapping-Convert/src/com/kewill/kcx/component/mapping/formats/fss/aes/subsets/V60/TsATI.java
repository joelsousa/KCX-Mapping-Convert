/*
 * Funktion    : TsATI.java
 * Titel       :
 * Erstellt    : 03.09.2008
 * Author      : Elisabeth Iwaniuk
 * Beschreibung:
 * Anmerkungen : 06.03.2009 - V60 checked
 */

package com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

public class TsATI extends Teilsatz {

    private String beznr  = "";             // Bezugsnummer
    private String posnr  = "";             // Positionsnummer
    private String lfdnr  = "";             // Nummer
    private String urfkt1 = "";             // Umrechnungsfaktor 1
    private String kzfkt1 = "";             // KZ Umrechnungsfaktor 1
    private String urfkt2 = "";             // Umrechnungsfaktor 2
    private String urfkts = "";             // KZ Umrechnungsfaktor 2
    private String ghtant = "";             // Anteil GHT
    private String mgeant = "";             // Anteilsmenge
    private String heklnr = "";             // Hersteller-Erklärungsnummer	  iwa 02.09.08	 Namenskorrektur
    private String keynr  = "";             // Schlüsselnummer
    private String liznr  = "";             // Lizenznummer
    private String text   = "";             // Textliche Erklärungen

    public TsATI() {
        tsTyp = "ATI";
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
	        lfdnr       = fields[3];
	        if (size < 5 ) return;	
	        urfkt1      = fields[4];
	        if (size < 6 ) return;	
	        kzfkt1      = fields[5];
	        if (size < 7 ) return;	
	        urfkt2      = fields[6];
	        if (size < 8 ) return;	
	        urfkts      = fields[7];
	        if (size < 9 ) return;	
	        ghtant      = fields[8];
	        if (size < 10 ) return;
	        mgeant      = fields[9];
	        if (size < 11 ) return;
	        heklnr      = fields[10];
	        if (size < 12 ) return;
	        keynr       = fields[11];
	        if (size < 13 ) return;
	        liznr       = fields[12];
	        if (size < 14 ) return;
	        text        = fields[13];
       }

	public String teilsatzBilden() {
		
        StringBuffer buff = new StringBuffer();
        
        buff = buff.append(tsTyp);
        buff = buff.append(trenner);
        buff = buff.append(beznr);
        buff = buff.append(trenner);
        buff = buff.append(posnr);
        buff = buff.append(trenner);
        buff = buff.append(lfdnr);
        buff = buff.append(trenner);
        buff = buff.append(urfkt1);
        buff = buff.append(trenner);
        buff = buff.append(kzfkt1);
        buff = buff.append(trenner); 
        buff = buff.append(urfkt2);              
        buff = buff.append(trenner);
        buff = buff.append(urfkts);
        buff = buff.append(trenner);
        buff = buff.append(ghtant);
        buff = buff.append(trenner);
        buff = buff.append(mgeant);
        buff = buff.append(trenner); 
        buff = buff.append(heklnr);
        buff = buff.append(trenner);
        buff = buff.append(keynr);
        buff = buff.append(trenner);
        buff = buff.append(liznr);
        buff = buff.append(trenner);
        buff = buff.append(text);
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

	public String getLfdnr() {
		return lfdnr;
	}

	public void setLfdnr(String lfdnr) {
		this.lfdnr = Utils.checkNull(lfdnr);
	}

	public String getUrfkt1() {
		return urfkt1;
	}

	public void setUrfkt1(String urfkt1) {
		this.urfkt1 = Utils.checkNull(urfkt1);
	}

	public String getKzfkt1() {
		return kzfkt1;
	}

	public void setKzfkt1(String kzfkt1) {
		this.kzfkt1 = Utils.checkNull(kzfkt1);
	}

	public String getUrfkt2() {
		return urfkt2;
	}

	public void setUrfkt2(String urfkt2) {
		this.urfkt2 = Utils.checkNull(urfkt2);
	}

	public String getUrfkts() {
		return urfkts;
	}

	public void setUrfkts(String urfkts) {
		this.urfkts = Utils.checkNull(urfkts);
	}

	public String getGhtant() {
		return ghtant;
	}

	public void setGhtant(String ghtant) {
		this.ghtant = Utils.checkNull(ghtant);
	}

	public String getMgeant() {
		return mgeant;
	}

	public void setMgeant(String mgeant) {
		this.mgeant = Utils.checkNull(mgeant);
	}

	public String getHeklnr() {
		return heklnr;
	}

	public void setHeklnr(String heklnr) {
		this.heklnr = Utils.checkNull(heklnr);
	}

	public String getKeynr() {
		return keynr;
	}

	public void setKeynr(String keynr) {
		this.keynr = Utils.checkNull(keynr);
	}

	public String getLiznr() {
		return liznr;
	}

	public void setLiznr(String liznr) {
		this.liznr = Utils.checkNull(liznr);
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = Utils.checkNull(text);
	}
	public boolean isEmpty() {
		if ( Utils.isStringEmpty(lfdnr)  && Utils.isStringEmpty(urfkt1) && Utils.isStringEmpty(kzfkt1)
		  && Utils.isStringEmpty(urfkt2) && Utils.isStringEmpty(urfkts) && Utils.isStringEmpty(ghtant)
		  && Utils.isStringEmpty(mgeant) && Utils.isStringEmpty(heklnr) && Utils.isStringEmpty(keynr)
		  && Utils.isStringEmpty(liznr)  && Utils.isStringEmpty(text) )
			return true;
		else
			return false;
	}

}
 
 
 
 
 
 



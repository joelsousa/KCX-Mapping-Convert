package com.kewill.kcx.component.mapping.formats.fss.base.subsets.V70;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module       :   Basis V70
 * Subset       :   BSU  
 * Created      :   12.11.2012
 * Description  :   Beendigung SumA.
 *  
 * @author          iwaniuk
 * @version         7.0.00
 */



public class TsBSU extends Teilsatz {

	private String beznr  = "";				// Bezugsnummer
	private String posnr  = "";				// Positionsnummer
	private String vregnr = "";				// Registriernummer
	private String vposnr = "";				// Positionsnummer im zu erledigenden Vorgang
	private String suastk = "";				// Anzahl der zu erledigenden Packstücke 
	private String vrwknr = "";				// Kundennummer des Verwahrers	
	//private String verwzb = "";				// TIN / Zollbeteiligtennummer des Verwahrers
	private String verweori = "";	   //new V70
	private String verwid = "";	       //new V70
	private String spoart = "";				// Art des spezifischen Ordnungsbegriff
	private String sponr  = "";				// Nummer des spezifischen Ordnungsbegriff
	private String azvagl = "";				// Abgleich in ATLAS erfolgt 	0 = NEIN 	1 = JA

               
	
	public TsBSU() {
	        tsTyp = "BSU";
	}

	
	public void setFields(String[] fields) 	{
		
		int size = fields.length;
		
        if (size < 1) { return; }        
        tsTyp	= fields[0];
        if (size < 2) { return; }        
        beznr	= fields[1];
        if (size < 3)  { return; }       
        posnr	= fields[2];
        if (size < 4)  { return; }        
        vregnr	= fields[3];
        if (size < 5)  { return; }       
        vposnr	= fields[4];
        if (size < 6)  { return; }       
        suastk	= fields[5];
        if (size < 7)  { return; }      
        vrwknr	= fields[6];
        if (size < 8) { return; }       
        verweori	= fields[7];
        if (size < 9) { return; }       
        verwid	= fields[8];
        if (size < 10) { return;	}
        spoart	= fields[9];
        if (size < 11)  { return; }        
        sponr	= fields[10];
        if (size < 12)  { return; }        
        azvagl	= fields[11];
        
		
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
        buff = buff.append(suastk);
        buff = buff.append(trenner);
        buff = buff.append(vrwknr);
        buff = buff.append(trenner);
        buff = buff.append(verweori);
        buff = buff.append(trenner);
        buff = buff.append(verwid);
        buff = buff.append(trenner);
        buff = buff.append(spoart);
        buff = buff.append(trenner);
        buff = buff.append(sponr);
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


	public String getSuastk() {
		return suastk;
	}


	public void setSuastk(String suastk) {
		this.suastk = Utils.checkNull(suastk);
	}


	public String getVrwknr() {
		return vrwknr;
	}


	public void setVrwknr(String vrwknr) {
		this.vrwknr = Utils.checkNull(vrwknr);
	}


	public String getVerweori() {
		return verweori;
	}

	public void setVerweori(String value) {
		this.verweori = Utils.checkNull(value);
	}

	public String getVerwId() {
		return verwid;
	}

	public void setVerwId(String verwzb) {
		this.verwid = Utils.checkNull(verwzb);
	}
	
	public String getSpoart() {
		return spoart;
	}


	public void setSpoart(String spoart) {
		this.spoart = Utils.checkNull(spoart);
	}


	public String getSponr() {
		return sponr;
	}


	public void setSponr(String sponr) {
		this.sponr = Utils.checkNull(sponr);
	}


	public String getAzvagl() {
		return azvagl;
	}


	public void setAzvagl(String azvagl) {
		this.azvagl = Utils.checkNull(azvagl);
	}

	public boolean isEmpty() {
		 
		if (Utils.isStringEmpty(beznr) && Utils.isStringEmpty(posnr)  && Utils.isStringEmpty(vregnr)) {
			return true;
		} else {
			return false;
		}
			
	}    
}

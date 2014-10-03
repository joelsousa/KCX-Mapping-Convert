/*
 * Funktion    : TsBAV.java
 * Titel       :
 * Erstellt    : 03.09.2008
 * Author      : Elisabeth Iwaniuk
 * Beschreibung:
 * Anmerkungen : V60 checked
 * Parameter   :
 * Rückgabe    : keine
 * Aufruf      :
 *
 */

package com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

public class TsBAV extends Teilsatz {

    private String beznr  = "";              // Bezugsnummer
    private String posnr  = "";              // Positionsnummer
    private String vregnr = "";              // Registriernummer des zu erledigenden Vorgangs
    private String vposnr = "";              // Positionsnummer im zu erledigenden Vorgang
    private String atlas  = "";              // Kennzeichen Zugang in ATLAS
    private String txzus  = "";              // Zusatztext
    private String azavgl = "";              // Abgleich in ATLAS erfolgt

    public TsBAV() {
        tsTyp = "BAV";
    } 
    public void setFields(String[] fields)
	    {
    		int size = fields.length;
    		String ausgabe = "FSS: "+fields[0]+" size = "+ size;
    		//Utils.log( ausgabe);
			
    		
    		if (size < 1 ) return;	
    		tsTyp       = fields[0];
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
	        txzus       = fields[6];
	        if (size < 8 ) return;	
	        azavgl      = fields[7];
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
        buff = buff.append(txzus);
        buff = buff.append(trenner);
        buff = buff.append(azavgl);
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
	public String getTxzus() {
		return txzus;
	}
	public void setTxzus(String txzus) {
		this.txzus = Utils.checkNull(txzus);
	}
	public String getAzavgl() {
		return azavgl;
	}
	public void setAzavgl(String azavgl) {
		this.azavgl = Utils.checkNull(azavgl);
	}
	public boolean isEmpty() {
		if ( Utils.isStringEmpty(vregnr)  && Utils.isStringEmpty(vposnr) && Utils.isStringEmpty(atlas)
		  && Utils.isStringEmpty(txzus) && Utils.isStringEmpty(azavgl) )		 
			return true;
		else
			return false;
	}

}


 
 


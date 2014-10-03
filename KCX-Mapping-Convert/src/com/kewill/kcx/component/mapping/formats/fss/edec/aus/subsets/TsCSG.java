/*
* Funktion    : TsCSG.java
* Titel       :
* Erstellt    : 19.03.2010
* Author      : Alfred Krzoska
* Beschreibung: Ergaenzungsdaten Empfindliche Waren   
* Anmerkungen :
* Parameter   :
* Rückgabe    : keine
* Aufruf      :
*
*/

package com.kewill.kcx.component.mapping.formats.fss.edec.aus.subsets;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.MsgExpDatPos;
import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Function : TsCSG.
 * Description : Ergaenzungsdaten Empfindliche Waren 
 * 
 * @author krzoska
 * @version 1.0.00
 * 
 */
public class TsCSG extends Teilsatz {

    private String refnr = "";      // Dossiernummer Spediteur
    private String posnr = "";		// Positionsnummer
    private String cdsgi = "";      // Warencode
    //AK20110414
    private String gew   = "";      // Menge Format nnnnnnnnnnN,NNN
    
    public TsCSG() {
        tsTyp = "CST";
    }
    public void setFields(String[] fields) {
    
		int size = fields.length;
		//String ausgabe = "FSS: "+fields[0]+" size = "+ size;
		//Utils.log( ausgabe);
		
		if (size < 1) { return; }		
        tsTyp = fields[0];
        if (size < 2) { return; }	
        refnr = fields[1];
        if (size < 3) { return; }	
        posnr = fields[2];
        if (size < 4) { return; }	
        cdsgi = fields[3];   
        if (size < 5) { return; }	
        gew = fields[4];   
    }

    public String teilsatzBilden() {
        StringBuffer buff = new StringBuffer();

        buff.append(tsTyp);
        buff.append(trenner);
        buff.append(refnr);
        buff.append(trenner);
        buff.append(posnr);
        buff.append(trenner);
        buff.append(cdsgi);
        buff.append(trenner);
        buff.append(gew);
        
        return new String(buff);
      }


	public void setCSGSubset(MsgExpDatPos msgExpDatPos) {
		
	}	
	
	public boolean isEmpty() {
	    return Utils.isStringEmpty(cdsgi) && Utils.isStringEmpty(gew);
	}
	public String getRefnr() {
		return refnr;
	
	}
	public void setRefnr(String refnr) {
		this.refnr = Utils.checkNull(refnr);
	}
	public String getPosnr() {
		return posnr;
	
	}
	public void setPosnr(String posnr) {
		this.posnr = Utils.checkNull(posnr);
	}
	public String getCdsgi() {
		return cdsgi;
	
	}
	public void setCdsgi(String cdsgi) {
		this.cdsgi = Utils.checkNull(cdsgi);
	}
	public String getGew() {
		return gew;
	
	}
	public void setGew(String gew) {
		this.gew = Utils.checkNull(gew);
	}


}



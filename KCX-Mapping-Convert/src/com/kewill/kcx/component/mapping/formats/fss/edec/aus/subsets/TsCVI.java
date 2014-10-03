/*
* Funktion    : TsCVI.java
* Titel       :
* Erstellt    : 18.03.2010
* Author      : Alfred Krzoska
* Beschreibung: CVI Ergänzungsdaten Vorpapiere   
* Anmerkungen :
* Parameter   :
* Rückgabe    : keine
* Aufruf      :
*
*/

package com.kewill.kcx.component.mapping.formats.fss.edec.aus.subsets;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.MsgExpDat;
import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Function.
 * @author krzoska
 * @version 1.0.00
 */
public class TsCVI extends Teilsatz {

    private String beznr = "";       	// ReferenceNumber
    private String posnr = "";    		// Positionsnummer
    private String vpart = "";			// Vorpapierart gemäss Codeverzeichnis
    private String vpref = "";      	// Vorpapier-Zeichen/Nummer.
    private String vpinf = "";      	// Vorpapier-Zusätzliche Angaben
    
    public TsCVI() {
        tsTyp = "CVI";
    }
    public void setFields(String[] fields) {
    
		int size = fields.length;
		//String ausgabe = "FSS: "+fields[0]+" size = "+ size;
		//Utils.log( ausgabe);
		
		if (size < 1) {
			return;		
		}
        tsTyp = fields[0];
        if (size < 2) {
        	return;	
        }
        beznr = fields[1];
        if (size < 3) {
        	return;	
        }
        posnr = fields[2];
        if (size < 4) {
        	return;	
        }
        vpart = fields[3];
        if (size < 5) {
        	return;	
        }
        vpref = fields[4];        
        if (size < 6) {
        	return;	
        }
        vpinf = fields[5];                	
    }

    public String teilsatzBilden() {
        StringBuffer buff = new StringBuffer();

        buff.append(tsTyp);
        buff.append(trenner);
        buff.append(beznr);
        buff.append(trenner);
        buff.append(posnr);
        buff.append(trenner);
        buff.append(vpart);
        buff.append(trenner);
        buff.append(vpref);
        buff.append(trenner);
        buff.append(vpinf);
        buff.append(trenner);
        
        return new String(buff);
      }

	public boolean isEmpty() {
		
	    if (Utils.isStringEmpty(vpart) &&    
			Utils.isStringEmpty(vpref) &&	
		    Utils.isStringEmpty(vpinf)) { 
	    	return true;
	    } else {
			return false;
	    }
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
	public String getVpart() {
		return vpart;
	
	}
	public void setVpart(String vpart) {
		this.vpart = Utils.checkNull(vpart);
	}
	public String getVpref() {
		return vpref;
	
	}
	public void setVpref(String vpref) {
		this.vpref = Utils.checkNull(vpref);
	}
	public String getVpinf() {
		return vpinf;
	
	}
	public void setVpinf(String vpinf) {
		this.vpinf = Utils.checkNull(vpinf);
	}
	
}



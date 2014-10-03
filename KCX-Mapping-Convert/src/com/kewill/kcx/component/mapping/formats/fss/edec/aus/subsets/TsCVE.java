/*
* Funktion    : TsCVE.java
* Titel       :
* Erstellt    : 18.03.2010
* Author      : Alfred Krzoska
* Beschreibung: Ergänzungsdaten Veredelung   
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
 * Function.
 * @author krzoska
 * @version 1.0.00
 */
public class TsCVE extends Teilsatz {

    private String refnr = "";       	// Dossiernummer Spediteur
    private String posnr = "";			// Positionsnummer
    private String rrdir = "";      	// Verkehrsrichtung 
    private String rretyp = "";      	// Veredelungstyp
    private String rrvtyp = "";      	// Verfahrenstyp 
    private String rratyp = "";      	// Abrechnungstyp
    private String rrkzfp = "";      	// Vorübergehende Verwendung (Freipass)
    private String rrptyp = "";      	// Positionstyp 
    
    public TsCVE() {
        tsTyp = "CVE";
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
        refnr = fields[1];
        if (size < 3) {
        	return;	
        }
        posnr = fields[2];
        if (size < 4) {
        	return;	
        }
        rrdir = fields[3];        
        if (size < 5) {
        	return;	
        }
        rretyp = fields[4];        
        if (size < 6) {
        	return;	
        }
        rrvtyp = fields[5];        
        if (size < 7) {
        	return;	
        }
        rratyp = fields[6];        
        if (size < 8) {
        	return;	
        }
        rrkzfp = fields[7];        
        if (size < 9) {
        	return;	
        }
        rrptyp = fields[8];        
       	
    }

    public String teilsatzBilden() {
        StringBuffer buff = new StringBuffer();

        buff.append(tsTyp);
        buff.append(trenner);
        buff.append(refnr);
        buff.append(trenner);
        buff.append(posnr);
        buff.append(trenner);
        buff.append(rrdir);
        buff.append(trenner);
        buff.append(rretyp);
        buff.append(trenner);
        buff.append(rrvtyp);
        buff.append(trenner);
        buff.append(rratyp);
        buff.append(trenner);
        buff.append(rrkzfp);
        buff.append(trenner);
        buff.append(rrptyp);
        buff.append(trenner);
        
        return new String(buff);
      }


	public void setCnzSubset(MsgExpDatPos msgExpDatPos) {
		
	}	
	
	public boolean isEmpty() {
	    if (Utils.isStringEmpty(rrdir) &&
	    	Utils.isStringEmpty(rretyp) &&
	    	Utils.isStringEmpty(rrvtyp) &&
	    	Utils.isStringEmpty(rratyp) &&
	    	Utils.isStringEmpty(rrkzfp) &&
	    	Utils.isStringEmpty(rrptyp)) {	   
	    	return true;
	    } else {
			return false;
	    }
	}
	public String getRefnr() {
		return refnr;
	
	}
	public void setRefnr(String refnr) {
		this.refnr = Utils.checkNull(refnr);
	}
	public String getRrdir() {
		return rrdir;
	
	}
	public void setRrdir(String rrdir) {
		this.rrdir = Utils.checkNull(rrdir);
	}
	public String getRretyp() {
		return rretyp;
	
	}
	public void setRretyp(String rretyp) {
		this.rretyp = Utils.checkNull(rretyp);
	}
	public String getRrvtyp() {
		return rrvtyp;
	
	}
	public void setRrvtyp(String rrvtyp) {
		this.rrvtyp = Utils.checkNull(rrvtyp);
	}
	public String getRratyp() {
		return rratyp;
	
	}
	public void setRratyp(String rratyp) {
		this.rratyp = Utils.checkNull(rratyp);
	}
	public String getRrkzfp() {
		return rrkzfp;
	
	}
	public void setRrkzfp(String rrkzfp) {
		this.rrkzfp = Utils.checkNull(rrkzfp);
	}
	public String getRrptyp() {
		return rrptyp;
	
	}
	public void setRrptyp(String rrptyp) {
		this.rrptyp = Utils.checkNull(rrptyp);
	}
	public String getPosnr() {
		return posnr;
	
	}
	public void setPosnr(String posnr) {
		this.posnr = Utils.checkNull(posnr);
	}
}



/*
* Funktion    : TsCVM.java
* Titel       :
* Erstellt    : 18.03.2010
* Author      : Alfred Krzoska
* Beschreibung: Ergänzungsdaten Vermerke   
* Anmerkungen :
* Parameter   :
* Rückgabe    : keine
* Aufruf      :
*
*/

package com.kewill.kcx.component.mapping.formats.fss.edec.aus.subsets;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Function.
 * @author krzoska
 * @version 1.0.00
 */
public class TsCVM extends Teilsatz {

    private String refnr = "";       	// Dossiernummer Spediteur
    private String posnr = "";			// Positionsnummer
    private String vm = "";      		// Besondere Vermerke 
    
    public TsCVM() {
        tsTyp = "CVM";
    }
    public void setFields(String[] fields) {
    	
		int size = fields.length;
		//String ausgabe = "FSS: " + fields[0] + " size = " + size;
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
        vm = fields[3];               	
    }

    public String teilsatzBilden() {
        StringBuffer buff = new StringBuffer();

        buff.append(tsTyp);
        buff.append(trenner);
        buff.append(refnr);
        buff.append(trenner);
        buff.append(posnr);
        buff.append(trenner);
        buff.append(vm);
        buff.append(trenner);
        
        return new String(buff);
      }
	
	public boolean isEmpty() {
	    return Utils.isStringEmpty(vm);
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
	
	public String getVm() {
		return vm;	
	}
	public void setVm(String vm) {
		this.vm = Utils.checkNull(vm);
	}	

}



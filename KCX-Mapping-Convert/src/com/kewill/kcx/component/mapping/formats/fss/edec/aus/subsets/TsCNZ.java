/*
* Funktion    : TsCNZ.java
* Titel       :
* Erstellt    : 18.03.2010
* Author      : Alfred Krzoska
* Beschreibung: Ergänzungsdaten NZE-Daten   
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

public class TsCNZ extends Teilsatz {

    private String refnr = "";       	// Dossiernummer Spediteur
    private String posnr = "";			// Positionsnummer
    private String cdnz = "";      		// NZE-Artencode  
    
    public TsCNZ() {
        tsTyp = "CNZ";
    }
    public void setFields(String[] fields)
    {
		int size = fields.length;
		String ausgabe = "FSS: "+fields[0]+" size = "+ size;
		//Utils.log( ausgabe);
		
		if (size < 1 ) return;		
        	tsTyp = fields[0];
        if (size < 2 ) return;	
        	refnr = fields[1];
        if (size < 3 ) return;	
        	posnr = fields[2];
        if (size < 4 ) return;	
        	cdnz = fields[3];        
       	
    }

    public String teilsatzBilden() {
        StringBuffer buff = new StringBuffer();

        buff.append(tsTyp);
        buff.append(trenner);
        buff.append(refnr);
        buff.append(trenner);
        buff.append(posnr);
        buff.append(trenner);
        buff.append(cdnz);
        buff.append(trenner);
        
        return new String(buff);
      }


	public void setCnzSubset(MsgExpDatPos msgExpDatPos) {
		
	}	
	
	public boolean isEmpty() {
	    if ( Utils.isStringEmpty(cdnz))
	    	return true;
		else
			return false;
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
	public String getCdnz() {
		return cdnz;
	
	}
	public void setCdnz(String cdnz) {
		this.cdnz = Utils.checkNull(cdnz);
	}
	

}



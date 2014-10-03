/*
* Funktion    : TsAAK.java
* Titel       :
* Erstellt    : 03.09.2008
* Author      : Elisabeth Iwaniuk
* Beschreibung:
* Anmerkungen :
* Parameter   :
* Rückgabe    : keine
* Aufruf      :
*
*/

package com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

public class TsAAK extends Teilsatz {

    private String beznr = "";       //Bezugsnummer
    private String gsroh = "";       //Gesamtrohmasse

    public TsAAK() {
        tsTyp = "AAK";
    }

    public void setFields(String[] fields)
    {
		int size = fields.length;
		String ausgabe = "FSS: "+fields[0]+" size = "+ size;
		//Utils.log( ausgabe);
			
		
		if (size < 1 ) return;		
        tsTyp = fields[0];
        if (size < 2 ) return;	
        beznr         = fields[1];
        if (size < 3 ) return;	
        gsroh         = fields[2];
    }

    public String teilsatzBilden() {
        StringBuffer buff = new StringBuffer();

        buff.append(tsTyp);
        buff.append(trenner);
        buff.append(beznr);
        buff.append(trenner);
        buff.append(gsroh);
        buff.append(trenner);

        return new String(buff);
      }

	public String getBeznr() {
		return beznr;
	
	}

	public void setBeznr(String beznr) {
		this.beznr = Utils.checkNull(beznr);
	}

	public String getGsroh() {
		return gsroh;
	
	}

	public void setGsroh(String gsroh) {
		this.gsroh = Utils.checkNull(gsroh);
	}

	public boolean isEmpty() {
			//return false;  // Always return false because Grossmass is optional
		if ( Utils.isStringEmpty(gsroh)) 							 
			return true;
		else
			return false;			
	}
}



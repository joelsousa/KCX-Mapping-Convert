/*
* Funktion    : TsNVE.java
* Titel       :
* Erstellt    : 10.09.2008
* Author      : Elisabeth Iwaniuk
* Beschreibung:
* Anmerkungen :
* Parameter   :
* Rückgabe    : keine
* Aufruf      :
*
*/
 
package com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V53;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

public class TsNVE extends Teilsatz {

    private String tsTyp  = "";       // Ts-Schlüssel
    private String beznr  = "";       // Bezugsnummer
    private String posnr  = "";       // Positionsnummer
    private String nve    = "";         // Packstückmarkierung NVE; SSCC
    private String ntxnve = ""; 	  // nue Packstückmarkierung

    public TsNVE() {
        tsTyp = "NVE";
    }

    public void setFields(String[] fields)
    {
    	int size = fields.length;
    	String ausgabe = "FSS: "+fields[0]+" size = "+ size;
    	//Utils.log( ausgabe);
		
    	
    	if (size < 1 ) return;		
    	tsTyp = fields[0];	
    	if (size < 2 ) return;
        beznr = fields[1];
        if (size < 3 ) return;
        posnr = fields[2];
        if (size < 4 ) return;
        nve = fields[3];
        if (size < 5 ) return;
        ntxnve = fields[4];
    }


    public String teilsatzBilden() {
        StringBuffer buff = new StringBuffer();

        buff.append(tsTyp);
        buff.append(trenner);
        buff.append(beznr);
        buff.append(trenner);
        buff.append(posnr);
        buff.append(trenner);
        buff.append(nve);
        buff.append(trenner);
        buff.append(ntxnve);
        buff.append(trenner);

        return new String(buff);
    }

	public String getTsTyp() {
		return tsTyp;
	}

	public void setTsTyp(String tsTyp) {
		this.tsTyp = Utils.checkNull(tsTyp);
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

	public String getNve() {
		return nve;
	}

	public void setNve(String nve) {
		this.nve = Utils.checkNull(nve);
	}

	public String getNtxnve() {
		return ntxnve;
	}

	public void setNtxnve(String ntxnve) {
		this.ntxnve = Utils.checkNull(ntxnve);
	}
	public boolean isEmpty() {
		if ( Utils.isStringEmpty(nve)  && Utils.isStringEmpty(ntxnve))		
			return true;
		else
			return false;
	}

}



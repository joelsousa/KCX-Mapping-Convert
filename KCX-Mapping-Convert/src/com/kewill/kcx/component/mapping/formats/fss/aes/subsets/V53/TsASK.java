package com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V53;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/*
* Funktion    : TsASK.java
* Titel       :
* Erstellt    : 03.09.2008
* Author      : CSF GmbH / Alfred Krzoska
* Beschreibung: 
* Anmerkungen : 
* Parameter   : 
* Rückgabe    : keine
* Aufruf      : 
*
* Änderungen:
* -----------
* Author      :
* Datum       :
* Kennzeichen :
* Beschreibung:
* Anmerkungen :
* Parameter   :
*
*/
public class TsASK extends Teilsatz {

	private String tsTyp	= "";	//	TS-Schlüssel
	private String beznr	= "";	//	Bezugsnummer
	private String stacde	= "";	//	Zollstatus
	private String grund	= "";	//	Grund

	public TsASK() {
	        tsTyp = "ASK";
	}
	  
	public void setFields(String[] fields) {
		int size = fields.length;
		String ausgabe = "FSS: "+fields[0]+" size = "+ size;
		//Utils.log( ausgabe);
			
		
		if (size < 1 ) return;		
        tsTyp = fields[0];
        if (size < 2 ) return;
		beznr	  = fields[1];
		if (size < 3 ) return;
		stacde    = fields[2];
		if (size < 4 ) return;
		grund	  = fields[3];
	}

    public String teilsatzBilden() {
        StringBuffer buff = new StringBuffer();
        
        buff = buff.append(tsTyp);
        buff = buff.append(trenner);
        buff = buff.append(beznr);
        buff = buff.append(trenner);
        buff = buff.append(stacde);
        buff = buff.append(trenner);
        buff = buff.append(grund);
        buff = buff.append(trenner);
        
        return new String(buff);
    }


	public boolean isJustificationValid() {

		if (stacde.trim().equals("") && grund.trim().equals("")) {
			return false;
		}
		return true;
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

	public String getStacde() {
		return stacde;
	}

	public void setStacde(String stacde) {
		this.stacde = Utils.checkNull(stacde);
	}

	public String getGrund() {
		return grund;
	}

	public void setGrund(String grund) {
		this.grund = Utils.checkNull(grund);
	}
	
	public boolean isEmpty() {
		if ( Utils.isStringEmpty(stacde)  && Utils.isStringEmpty(grund) )
			return true;
		else
			return false;
	}

	
}

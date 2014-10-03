/*
* Function    : TsHS.java
* Title       :
* Created     : 29.09.2009
* Author      : Elisabeth Iwaniuk
* Description: HeaderSeal for KIDS-AES to eCustoms
*/

package com.kewill.kcx.component.mapping.formats.Bdec.subsets;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Modul        : TsHS<br>
 * Erstellt     : 29.09.2009<br>
 * Beschreibung : HeaderSeal for KIDS-AES to eCustoms.
 * 
 * @author iwaniuk
 * @version 6.0.00
 */
public class TsHS extends Teilsatz {

    private String sealId = "";       //Bezugsnummer
    private String sealIdLng = "";       //Gesamtrohmasse

    public TsHS() {
        tsTyp = "HS";
        trenner = trennerUK;
    }

    public void setFields(String[] fields) {
		int size = fields.length;
		//String ausgabe = "FSS: "+fields[0]+" size = "+ size;
		//Utils.log( ausgabe);
			
		if (size < 1) { return; }
        tsTyp = fields[0];
        if (size < 2) { return; }
        sealId         = fields[1];
        if (size < 3) { return; }
        sealIdLng      = fields[2];
    }

    public String teilsatzBilden() {
        StringBuffer buff = new StringBuffer();

        buff.append(tsTyp);
        buff.append(trenner);
        buff.append(sealId);
        buff.append(trenner);
        buff.append(sealIdLng);
        //buff.append(trenner);

        return new String(buff);
      }

	public String getSealId() {
		return sealId;	
	}

	public void setSealId(String argument) {
		this.sealId = Utils.checkNull(argument);
	}

	public String getSealIdLng() {
		return sealIdLng;	
	}

	public void setSealIdLng(String argument) {
		this.sealIdLng = Utils.checkNull(argument);
	}

	public boolean isEmpty() {		
		return (Utils.isStringEmpty(sealId) && Utils.isStringEmpty(sealIdLng));
	}
}



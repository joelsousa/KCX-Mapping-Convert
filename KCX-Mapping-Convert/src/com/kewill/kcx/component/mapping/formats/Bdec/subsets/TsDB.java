/*
* Function    : TsDBjava
* Title       :
* Created     : 29.09.2009
* Author      : Elisabeth Iwaniuk
* Description : DetailIBSDCode for KIDS-AES to eCustoms
*/

package com.kewill.kcx.component.mapping.formats.Bdec.subsets;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Modul        : TsDB<br>
 * Erstellt     : 29.09.2009<br>
 * Beschreibung : DetailIBSDCode for KIDS-AES to eCustoms.
 * 
 * @author iwaniuk
 * @version 6.0.00
 */
public class TsDB extends Teilsatz {

    private String ibsdCode   = "";       //Bezugsnummer
    private String percentage = "";       //Gesamtrohmasse

    public TsDB() {
        tsTyp = "DB";
        trenner = trennerUK;
    }

    public void setFields(String[] fields) {
		int size = fields.length;
		//String ausgabe = "TsDB: "+fields[0]+" size = "+ size;
		//Utils.log( ausgabe);
			
		if (size < 1) { return; }
        tsTyp = fields[0];
        if (size < 2) { return; }
        ibsdCode      = fields[1];
        if (size < 3) { return;	}
        percentage    = fields[2];
    }

    public String teilsatzBilden() {
        StringBuffer buff = new StringBuffer();

        buff.append(tsTyp);
        buff.append(trenner);
        buff.append(ibsdCode);
        buff.append(trenner);
        buff.append(percentage);
        //buff.append(trenner);

        return new String(buff);
      }

	public String getIbsdCode() {
		return ibsdCode;	
	}

	public void setIbsdCode(String argument) {
		this.ibsdCode = Utils.checkNull(argument);
	}

	public String getPercentage() {
		return percentage;	
	}

	public void setPercentage(String argument) {
		this.percentage = Utils.checkNull(argument);
	}

	public boolean isEmpty() {
		//return false;  // Always return false because Grossmass is optional
	    return (Utils.isStringEmpty(ibsdCode) && Utils.isStringEmpty(percentage));
	}
}



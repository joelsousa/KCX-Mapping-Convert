/*
* Function    : TsHR.java
* Title       :
* Created     : 29.09.2009
* Author      : Elisabeth Iwaniuk
* Description: HeaderRoute for KIDS-AES to eCustoms
*/

package com.kewill.kcx.component.mapping.formats.Bdec.subsets;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Modul        : TsHR<br>
 * Erstellt     : 29.09.2009<br>
 * Beschreibung : HeaderRoute for KIDS-AES to eCustoms.
 * 
 * @author iwaniuk
 * @version 6.0.00
 */
public class TsHR extends Teilsatz {

    private String routeCntry = "";       //Bezugsnummer
 
    public TsHR() {
        tsTyp = "HR";
        trenner = trennerUK;
    }

    public void setFields(String[] fields) {
		int size = fields.length;
		//String ausgabe = "TsHR: "+fields[0]+" size = "+ size;
		//Utils.log( ausgabe);
			
		
		if (size < 1) { return; }
        tsTyp = fields[0];
        if (size < 2) { return; }
        routeCntry         = fields[1];
    }

    public String teilsatzBilden() {
        StringBuffer buff = new StringBuffer();

        buff.append(tsTyp);
        buff.append(trenner);
        buff.append(routeCntry);
        //buff.append(trenner);
 
        return new String(buff);
      }

	public String getRouteCntry() {
		return routeCntry;	
	}

	public void setRouteCntry(String argument) {
		this.routeCntry = Utils.checkNull(argument);
	}

	public boolean isEmpty() {			
		return Utils.isStringEmpty(routeCntry);
	}
}



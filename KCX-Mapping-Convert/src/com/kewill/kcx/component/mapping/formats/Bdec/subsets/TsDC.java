/*
* Function    : TsDC.java
* Title       :
* Created     : 29.09.2009
* Author      : Elisabeth Iwaniuk
* Description : DetailContainer for KIDS-AES to eCustoms
*/

package com.kewill.kcx.component.mapping.formats.Bdec.subsets;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Modul        : TsDC<br>
 * Erstellt     : 29.09.2009<br>
 * Beschreibung : DetailContainer for KIDS-AES to eCustoms.
 * 
 * @author iwaniuk
 * @version 6.0.00
 */
public class TsDC extends Teilsatz {

    private String cntrNo = "";       //Bezugsnummer

    public TsDC() {
        tsTyp = "DC";
        trenner = trennerUK;
    }

    public void setFields(String[] fields) {
		int size = fields.length;
		//String ausgabe = "FSS: "+fields[0]+" size = "+ size;
		//Utils.log( ausgabe);
			
		
		if (size < 1) { return; }
        tsTyp = fields[0];
        if (size < 2) { return; }
        cntrNo         = fields[1];        
    }

    public String teilsatzBilden() {
        StringBuffer buff = new StringBuffer();

        buff.append(tsTyp);
        buff.append(trenner);
        buff.append(cntrNo);
        //buff.append(trenner);

        return new String(buff);
      }

	public String getCntrNo() {
		return cntrNo;	
	}

	public void setCntrNo(String argument) {
		this.cntrNo = Utils.checkNull(argument);
	}

	public boolean isEmpty() {
		return Utils.isStringEmpty(cntrNo);
	}
}



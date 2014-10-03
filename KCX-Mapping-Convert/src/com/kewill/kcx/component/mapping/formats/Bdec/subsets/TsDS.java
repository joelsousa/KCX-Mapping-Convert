/*
* Function    : TsDS.java
* Title       :
* Created     : 29.09.2009
* Author      : Elisabeth Iwaniuk
* Description : DetailScheme for KIDS-AES to eCustoms
*/

package com.kewill.kcx.component.mapping.formats.Bdec.subsets;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Modul        : TsDS<br>
 * Erstellt     : 29.09.2009<br>
 * Beschreibung : DetailScheme for KIDS-AES to eCustoms.
 * 
 * @author iwaniuk
 * @version 6.0.00
 */
public class TsDS extends Teilsatz {

    private String schemeId            = "";       //
    private String schemeAuthorisation = "";       //
    private String schemeControlOffice = "";       //
    private String schemeControlYear   = "";       //

    public TsDS() {
        tsTyp = "DS";
        trenner = trennerUK;
    }

    public void setFields(String[] fields) {
		int size = fields.length;
		//String ausgabe = "TsDS: "+fields[0]+" size = "+ size;
		//Utils.log( ausgabe);
			
		if (size < 1) { return; }
        tsTyp = fields[0];
        if (size < 2) { return; }
        schemeId            = fields[1];
        if (size < 3) { return; }
        schemeAuthorisation = fields[2];
        if (size < 4) { return; }
        schemeControlOffice = fields[3];
        if (size < 5) { return; }
        schemeControlYear   = fields[4];        
    }

    public String teilsatzBilden() {
        StringBuffer buff = new StringBuffer();

        buff.append(tsTyp);
        buff.append(trenner);
        buff.append(schemeId);
        buff.append(trenner);
        buff.append(schemeAuthorisation);
        buff.append(trenner);
        buff.append(schemeControlOffice);
        buff.append(trenner);
        buff.append(schemeControlYear);
        //buff.append(trenner);

        return new String(buff);
      }

	public String getSchemeId() {
		return schemeId;	
	}

	public void setSchemeId(String argument) {
		this.schemeId = Utils.checkNull(argument);
	}

	public String getSchemeAuthorisation() {
		return schemeAuthorisation;	
	}

	public void setschemeAuthorisation(String argument) {
		this.schemeAuthorisation = Utils.checkNull(argument);
	}

	public String getSchemeControlOffice() {
		return schemeControlOffice;	
	}

	public void setSchemeControlOffice(String argument) {
		this.schemeControlOffice = Utils.checkNull(argument);
	}

	public String getSchemeControlYear() {
		return schemeControlYear;	
	}

	public void setSchemeControlYear(String argument) {
		this.schemeControlYear = Utils.checkNull(argument);
	}

	public boolean isEmpty() {			
		return (Utils.isStringEmpty(schemeId) && Utils.isStringEmpty(schemeAuthorisation) &&
		        Utils.isStringEmpty(schemeControlOffice) && Utils.isStringEmpty(schemeControlYear));
	}
}



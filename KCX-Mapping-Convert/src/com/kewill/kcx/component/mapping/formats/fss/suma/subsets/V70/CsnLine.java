package com.kewill.kcx.component.mapping.formats.fss.suma.subsets.V70;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Modul		:	Manifest
 * Erstellt		:	17.12.2013
 * Beschreibung	:   Kopfsatz Zeile 1,2 und 3.
 *        			Zabis Version 70  
 *@author iwaniuk
 *@version 7.0.00
 */

public class CsnLine  extends Teilsatz {

	private String line	= "";			//bsp. QK FRAFMLH, .ICSEULH 161326, CSN/3 

    public CsnLine() {
      super();
    }

    public void setFields(String[] text) {
    	line = Utils.checkNull(text[0]);
    }

    public String teilsatzBilden() {        
        return line;
    }

 	public String getLine() {
 		return line;
 	}
 	public void setLine(String text) {
 		this.line = Utils.checkNull(text);
 	}

 	public boolean isEmpty() {
 		return Utils.isStringEmpty(line); 
 	}

}



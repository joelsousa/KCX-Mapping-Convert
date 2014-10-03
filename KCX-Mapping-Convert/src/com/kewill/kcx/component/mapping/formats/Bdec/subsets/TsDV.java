/*
* Function    : TsDD.java
* Title       :
* Created     : 29.09.2009
* Author      : Elisabeth Iwaniuk
* Description : DetailsPrevDocsv for KIDS-AES to eCustoms
*/

package com.kewill.kcx.component.mapping.formats.Bdec.subsets;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Modul        : TsDV<br>
 * Erstellt     : 29.09.2009<br>
 * Beschreibung : DetailsPrevDocsv for KIDS-AES to eCustoms.
 * 
 * @author iwaniuk
 * @version 6.0.00
 */
public class TsDV extends Teilsatz {

    private String prevDocType  = "";       //
    private String prevDocRef   = "";       //
    private String prevDocLng   = "";       //
    private String prevDocClass = "";       //

    public TsDV() {
        tsTyp = "DV";
        trenner = trennerUK;
    }

    public void setFields(String[] fields) {
		int size = fields.length;
		//String ausgabe = "TsDV: "+fields[0]+" size = "+ size;
		//Utils.log( ausgabe);
					
		if (size < 1) { return; }
        tsTyp = fields[0];
        if (size < 2) { return; }
        prevDocType        = fields[1];
        if (size < 3) { return; }
        prevDocRef         = fields[2];
        if (size < 4) { return; }
        prevDocLng         = fields[3];
        if (size < 5) { return; }
        prevDocClass       = fields[4];  
    }

    public String teilsatzBilden() {
        StringBuffer buff = new StringBuffer();

        buff.append(tsTyp);
        buff.append(trenner);
        buff.append(prevDocType);
        buff.append(trenner);
        buff.append(prevDocRef);
        buff.append(trenner);
        buff.append(prevDocLng);
        buff.append(trenner);
        buff.append(prevDocClass);
        //buff.append(trenner);

        return new String(buff);
      }

	public String getPrevDocType() {
		return prevDocType;	
	}

	public void setPrevDocType(String argument) {
		this.prevDocType = Utils.checkNull(argument);
	}

	public String getPrevDocRef() {
		return prevDocRef;	
	}

	public void setPrevDocRef(String argument) {
		this.prevDocRef = Utils.checkNull(argument);
	}
	public String getPrevDocLng() {
		return prevDocLng;	
	}

	public void setPrevDocLng(String argument) {
		this.prevDocLng = Utils.checkNull(argument);
	}

	public String getPrevDocClass() {
		return prevDocClass;	
	}

	public void setPrevDocClass(String argument) {
		this.prevDocClass = Utils.checkNull(argument);
	}	

	public boolean isEmpty() {			
		return (Utils.isStringEmpty(prevDocType) && Utils.isStringEmpty(prevDocRef) &&
			    Utils.isStringEmpty(prevDocLng) && Utils.isStringEmpty(prevDocClass));
	}
}



/*
* Function    : TsDL.java
* Title       :
* Created     : 29.09.2009
* Author      : Elisabeth Iwaniuk
* Description : DetailsLicence for KIDS-AES to eCustoms
*/

package com.kewill.kcx.component.mapping.formats.Bdec.subsets;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Modul        : TsDL<br>
 * Erstellt     : 29.09.2009<br>
 * Beschreibung : DetailsLicence for KIDS-AES to eCustoms.
 * 
 * @author iwaniuk
 * @version 6.0.00
 */
public class TsDL extends Teilsatz {

    private String licenceRef      = "";       //
    private String licenceLineNo   = "";       //new Element 
    private String licenceStatus   = "";       //new Element 
    private String licenceQuantity = "";       //new Element 


    public TsDL() {
        tsTyp = "DL";
        trenner = trennerUK;
    }

    public void setFields(String[] fields) {
		int size = fields.length;
		//String ausgabe = "TsDL: "+fields[0]+" size = "+ size;
		//Utils.log( ausgabe);
		
		if (size < 1) { return; }
        tsTyp = fields[0];
        if (size < 2) { return; }
        licenceRef     = fields[1];
        if (size < 3) { return; }
        licenceLineNo         = fields[2];
        if (size < 4) { return; }
        licenceStatus         = fields[3];
        if (size < 5) { return; }
        licenceQuantity         = fields[4];
    }

    public String teilsatzBilden() {
        StringBuffer buff = new StringBuffer();

        buff.append(tsTyp);
        buff.append(trenner);
        buff.append(licenceRef);
        buff.append(trenner);
        buff.append(licenceLineNo);
        buff.append(trenner);
        buff.append(licenceStatus);
        buff.append(trenner);
        buff.append(licenceQuantity);
        //buff.append(trenner);

        return new String(buff);
      }

	public String getLicenceRef() {
		return licenceRef;	
	}

	public void setLicenceRef(String argument) {
		this.licenceRef = Utils.checkNull(argument);
	}

	public String getLicenceLineNo() {
		return licenceLineNo;	
	}

	public void setLicenceLineNo(String argument) {
		this.licenceLineNo = Utils.checkNull(argument);
	}
	
	public String getLicenceStatus() {
		return licenceStatus;	
	}

	public void setlicenceStatus(String argument) {
		this.licenceStatus = Utils.checkNull(argument);
	}
	
	public String getLicenceQuantity() {
		return licenceQuantity;	
	}

	public void setLicenceQuantity(String argument) {
		this.licenceQuantity = Utils.checkNull(argument);
	}	

	public boolean isEmpty() {
			//return false;  // Always return false because Grossmass is optional
		return (Utils.isStringEmpty(licenceRef) && Utils.isStringEmpty(licenceLineNo) &&
				Utils.isStringEmpty(licenceStatus) && Utils.isStringEmpty(licenceQuantity));
	}
}



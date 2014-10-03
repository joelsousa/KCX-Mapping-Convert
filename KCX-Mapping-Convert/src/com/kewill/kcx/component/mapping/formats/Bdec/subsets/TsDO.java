/*
* Function    : TsDO.java
* Title       :
* Created     : 29.09.2009
* Author      : Elisabeth Iwaniuk
* Description : DetailDocs for KIDS-AES to eCustoms
*/

package com.kewill.kcx.component.mapping.formats.Bdec.subsets;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Modul        : TsDO<br>
 * Erstellt     : 29.09.2009<br>
 * Beschreibung : DetailDocs for KIDS-AES to eCustoms.
 * 
 * @author iwaniuk
 * @version 6.0.00
 */
public class TsDO extends Teilsatz {

	private String code   = "";       //
	private String reference = "";       //
	private String status = "";       //
	private String reason = "";       //
	private String language    = "";       //
	private String part   = "";       //
	private String quantity    = "";       //

    public TsDO(String type) {
        tsTyp = type;
        trenner = trennerUK;
    }
    public void setFields(String[] fields) {
		int size = fields.length;
		//String ausgabe = "FSS: "+fields[0]+" size = "+ size;
		//Utils.log( ausgabe);
		
		if (size < 1) { return; }
        tsTyp = fields[0];
        if (size < 2) { return; }
        code        = fields[1];
        if (size < 3) { return; }
        reference   = fields[2];
        if (size < 4) { return; }
        status      = fields[3];
        if (size < 5) { return; }
        reason      = fields[4];    
        if (size < 6) { return; }
        language    = fields[5];
        if (size < 7) { return; }
        part        = fields[6];
        if (size < 8) { return; }
        quantity    = fields[7]; 
    }

    public String teilsatzBilden() {
        StringBuffer buff = new StringBuffer();

        buff.append(tsTyp);
        buff.append(trenner);
        buff.append(code);
        buff.append(trenner);
        buff.append(reference);
        buff.append(trenner);
        buff.append(status);
        buff.append(trenner);
        buff.append(reason);
        buff.append(trenner);
        buff.append(language);
        buff.append(trenner);
        buff.append(part);
        buff.append(trenner);
        buff.append(quantity);
        //buff.append(trenner);

        return new String(buff);
      }

	public String getCode() {
		return code;	
	}

	public void setCode(String argument) {
		this.code = Utils.checkNull(argument);
	}

	public String getReference() {
		return reference;	
	}

	public void setReference(String argument) {
		this.reference = Utils.checkNull(argument);
	}
	public String getStatus() {
		return status;	
	}

	public void setStatus(String argument) {
		this.status = Utils.checkNull(argument);
	}

	public String getReason() {
		return reason;	
	}

	public void setReason(String argument) {
		this.reason = Utils.checkNull(argument);
	}
	public String getLanguage() {
		return language;	
	}

	public void setLanguage(String argument) {
		this.language = Utils.checkNull(argument);
	}

	public String getPart() {
		return part;	
	}

	public void setPart(String argument) {
		this.part = Utils.checkNull(argument);
	}
	public String getQuantity() {
		return quantity;	
	}

	public void setQuantity(String argument) {
		this.quantity = Utils.checkNull(argument);
	}

	public boolean isEmpty() {			
		return (Utils.isStringEmpty(code) && Utils.isStringEmpty(reference) &&
				Utils.isStringEmpty(status) && Utils.isStringEmpty(reason) && 
				Utils.isStringEmpty(language) && Utils.isStringEmpty(part) && 
				Utils.isStringEmpty(quantity));
	}
}



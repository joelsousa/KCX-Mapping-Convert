package com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;
/*
* Funktion    : TsVDZ.java
* Titel       :
* Erstellt    : 07.09.2010
* Author      : Kewill / Christine Kron
* 
* Description : subset VDZ refers to ve-fss-62.doc 

* Parameter   : 
* Return      : keine

*
* Changes:
* -----------
* Author      :  
* Date        :  
* Kennzeichen :
* Description :
*             
*
*/

/**
 * Modul		: TsVDZ<br>
 * Date			: 7.9.2010<br>
 * Description	: Definition of subset TsVDZ Durchgangszollstellen.
 * 
 * @author Christine Kron
 * @version 1.0.00
 */
public class TsVDZ extends Teilsatz {
	
	private String beznr  	= "";		// Bezugsnummer
	private String dgzst	= "";		// NCTS-Nummer der Durchgangszollstelle
	
	public TsVDZ() {
		tsTyp = "VDZ";
	}
	
	public String teilsatzBilden() {
		StringBuffer buff = new StringBuffer();
		
		buff.append(tsTyp);
		buff.append(trenner);
		buff.append(beznr);
		buff.append(trenner);
		buff.append(dgzst);
		buff.append(trenner);
		
		return buff.toString();
	}
	
	public void setFields(String[] fields) {
		int size = fields.length;
		
		if (size < 1) {
			return;
		}
		tsTyp = fields[0];
		if (size < 2) {
			return;
		}
		beznr = fields[1];
		if (size < 3) {
			return;
		}
		dgzst =  fields[2];
	}

	
	public String getBeznr() {
		return beznr;
	}

	public void setBeznr(String beznr) {
		this.beznr = Utils.checkNull(beznr);
	}

	public String getDgzst() {
		return dgzst;
	}

	public void setDgzst(String dgzst) {
		this.dgzst = Utils.checkNull(dgzst);
	}

	public boolean isEmpty() {
		return (Utils.isStringEmpty(dgzst));
	}
}


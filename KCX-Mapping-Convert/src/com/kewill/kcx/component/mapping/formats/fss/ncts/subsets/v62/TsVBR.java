package com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/*
* Funktion    : TsVBR.java
* Titel       :
* Erstellt    : 08.09.2010
* Author      : Kewill / Christine Kron
* 
* Description : subset VBR refers to ve-fss-62.doc 

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
 * Modul		: TsVBR<br>
 * Date			: 8.9.2010<br>
 * Description	: Definition of subset TsVBR.
 * 
 * @author Christine Kron
 * @version 1.0.00
 */
public class TsVBR extends Teilsatz {
    
	private String beznr  	= "";      // Bezugsnummer 
	private String ldbf  	= "";      // Verschlussnummer

    public TsVBR() {
        tsTyp = "VBR";
    }

    public String teilsatzBilden() {
        StringBuffer buff = new StringBuffer();
          
        buff.append(tsTyp);
        buff.append(trenner);        
        buff.append(beznr);
        buff.append(trenner);	
        buff.append(ldbf);
        buff.append(trenner);	

    	return buff.toString();
    }

	

	// setFields will not be used because VBR is a specific subset of the message VAN
	// and this FSS-message is created by KCX to be sent to Zabis
	// only messages from Zabis have to write out this method

	public void setFields(String[] fields) {
		Utils.log("TsVBR.setFields not created");
	}
		
	public String getBeznr() {
		return beznr;
	}

	public void setBeznr(String beznr) {
		this.beznr = Utils.checkNull(beznr);
	}

	public String getLdbf() {
		return ldbf;
	}

	public void setLdbf(String ldbf) {
		this.ldbf = Utils.checkNull(ldbf);
	}
	
	public boolean isEmpty() {
		return (Utils.isStringEmpty(ldbf)); 
	}

}





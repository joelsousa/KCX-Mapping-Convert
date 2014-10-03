package com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/*
* Funktion    : TsVVS.java
* Titel       :
* Erstellt    : 08.09.2010
* Author      : Kewill / Christine Kron
* 
* Description : subset VVS refers to ve-fss-62.doc 

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
 * Modul		: TsVVS<br>
 * Date			: 8.9.2010<br>
 * Description	: Definition of subset TsVVS.
 * 
 * @author Christine Kron
 * @version 1.0.00
 */
public class TsVVS extends Teilsatz {
    
	private String beznr  	= "";      // Bezugsnummer 
	private String seal  	= "";      // Verschlussnummer

    public TsVVS() {
        tsTyp = "VVS";
    }

    public String teilsatzBilden() {
        StringBuffer buff = new StringBuffer();
          
        buff.append(tsTyp);
        buff.append(trenner);        
        buff.append(beznr);
        buff.append(trenner);	
        buff.append(seal);
        buff.append(trenner);	

    	return buff.toString();
    }

	

	// setFields will not be used because VVS is a specific subset of the message VAN
	// and this FSS-message is created by KCX to be sent to Zabis
	// only messages from Zabis have to write out this method

	public void setFields(String[] fields) {
		Utils.log("TsVVS.setFields not created");
	}

	public String getBeznr() {
		return beznr;
	}

	public void setBeznr(String beznr) {
		this.beznr = Utils.checkNull(beznr);
	}

	public String getSeal() {
		return seal;
	}

	public void setSeal(String seal) {
		this.seal = Utils.checkNull(seal);
	}
	
	public boolean isEmpty() {
		return (Utils.isStringEmpty(seal));		
	}
}




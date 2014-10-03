package com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/*
* Funktion    : TsVTV.java
* Titel       :
* Erstellt    : 14.09.2010
* Author      : Kewill / Christine Kron
* 
* Description : subset VTV refers to ve-fss-62.doc 

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
 * Modul		: TsVTV<br>
 * Date			: 14.09.2010<br>
 * Description	: Definition of subset TsVTV.
 * 
 * @author Christine Kron
 * @version 1.0.00
 */
public class TsVTV extends Teilsatz {
    
	private String beznr  	= "";      // Bezugsnummer 
	private String posnr  	= "0";     // Positionsnummer hier immer mit "0" besetzen
	private String vregnr  	= "";      // Ordnungsmerkmal (Referenz) des Vorpapier
	private String txzus  	= "";      // Zusätzliche textuelle Beschreibung

    public TsVTV() {
        tsTyp = "VTV";
    }
    public String teilsatzBilden() {
        StringBuffer buff = new StringBuffer();
          
        buff.append(tsTyp);
        buff.append(trenner);        
        buff.append(beznr);
        buff.append(trenner);	
        buff.append(posnr);
        buff.append(trenner);	
        buff.append(vregnr);
        buff.append(trenner);	
        buff.append(txzus);
        buff.append(trenner);	

    	return buff.toString();
    }

	// setFields will not be used because VTV is a specific subset of the message VAN
	// and this FSS-message is created by KCX to be sent to Zabis
	// only messages from Zabis have to write out this method

	public void setFields(String[] fields) {
		Utils.log("TsVTV.setFields not created");
	}	

	public String getBeznr() {
		return beznr;
	}

	public void setBeznr(String beznr) {
		this.beznr = Utils.checkNull(beznr);
	}

	public String getPosnr() {
		return posnr;
	}

	public void setPosnr(String posnr) {
		this.posnr = Utils.checkNull(posnr);
	}
	public String getVregnr() {
		return vregnr;
	}
	public void setVregnr(String vregnr) {
		this.vregnr = Utils.checkNull(vregnr);
	}
	public String getTxzus() {
		return txzus;
	}
	public void setTxzus(String txzus) {
		this.txzus = Utils.checkNull(txzus);
	}
	
	public boolean isEmpty() {
		return (Utils.isStringEmpty(vregnr) && Utils.isStringEmpty(txzus));
	}
}




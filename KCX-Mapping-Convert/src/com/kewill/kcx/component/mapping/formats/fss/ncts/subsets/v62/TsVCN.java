package com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/*
* Funktion    : TsVCN.java
* Titel       :
* Erstellt    : 10.09.2010
* Author      : Kewill / Christine Kron
* 
* Description : subset VCN refers to ve-fss-62.doc 

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
 * Modul		: TsVCN<br>
 * Date			: 10.09.2010<br>
 * Description	: Definition of subset TsVCN.
 * 
 * @author Christine Kron
 * @version 1.0.00
 */
public class TsVCN extends Teilsatz {
    
	private String beznr  	= "";      // Bezugsnummer 
	private String posnr  	= "0";     // Positionsnummer hier immer mit "0" besetzen
	private String connr  	= "";      // Containernummer

    public TsVCN() {
        tsTyp = "VCN";
    }
    public String teilsatzBilden() {
        StringBuffer buff = new StringBuffer();
          
        buff.append(tsTyp);
        buff.append(trenner);        
        buff.append(beznr);
        buff.append(trenner);	
        buff.append(posnr);
        buff.append(trenner);	
        buff.append(connr);
        buff.append(trenner);	

    	return buff.toString();
    }

	// setFields will not be used because VCN is a specific subset of the message VAN
	// and this FSS-message is created by KCX to be sent to Zabis
	// only messages from Zabis have to write out this method

	public void setFields(String[] fields) {
		Utils.log("TsVCN.setFields not created");
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
	public String getConnr() {
		return connr;
	}
	public void setConnr(String connr) {
		this.connr = Utils.checkNull(connr);
	}
	public boolean isEmpty() {
		return (Utils.isStringEmpty(connr));		
	}
}




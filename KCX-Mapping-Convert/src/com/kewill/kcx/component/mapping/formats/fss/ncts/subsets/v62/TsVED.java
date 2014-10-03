package com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/*
* Funktion    : TsVED.java
* Titel       :
* Erstellt    : 14.09.2010
* Author      : Kewill / Christine Kron
* 
* Description : subset VED refers to ve-fss-62.doc 

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
 * Modul		: TsVED<br>
 * Date			: 14.09.2010<br>
 * Description	: Definition of subset TsVED.
 * 
 * @author Christine Kron
 * @version 1.0.00
 */
public class TsVED extends Teilsatz {
    
	private String beznr  	= "";      // Bezugsnummer 
	private String posnr  	= "0";     // Positionsnummer hier immer mit "0" besetzen
	private String untar  	= "";      // Unterlagenart
	private String untre  	= "";      // Referenz
	private String untzu  	= "";      // Zusatz zur Unterlagen-Referenz

    public TsVED() {
        tsTyp = "VED";
    }
    public String teilsatzBilden() {
        StringBuffer buff = new StringBuffer();
          
        buff.append(tsTyp);
        buff.append(trenner);        
        buff.append(beznr);
        buff.append(trenner);	
        buff.append(posnr);
        buff.append(trenner);	
        buff.append(untar);
        buff.append(trenner);	
        buff.append(untre);
        buff.append(trenner);	
        buff.append(untzu);
        buff.append(trenner);	

    	return buff.toString();
    }

	// setFields will not be used because VED is a specific subset of the message VAN
	// and this FSS-message is created by KCX to be sent to Zabis
	// only messages from Zabis have to write out this method

	public void setFields(String[] fields) {
		Utils.log("TsVED.setFields not created");
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
	public String getUntar() {
		return untar;
	}
	public void setUntar(String untar) {
		this.untar = Utils.checkNull(untar);
	}
	public String getUntre() {
		return untre;
	}
	public void setUntre(String untre) {
		this.untre = Utils.checkNull(untre);
	}
	public String getUntzu() {
		return untzu;
	}
	public void setUntzu(String untzu) {
		this.untzu = Utils.checkNull(untzu);
	}
	
	public boolean isEmpty() {
		return (Utils.isStringEmpty(untar) &&
				Utils.isStringEmpty(untre)  && Utils.isStringEmpty(untzu)); 		
	}
}




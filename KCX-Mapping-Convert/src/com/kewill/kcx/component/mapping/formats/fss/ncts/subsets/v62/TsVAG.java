package com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/*
* Funktion    : TsVAG.java
* Titel       :
* Erstellt    : 08.09.2010
* Author      : Kewill / Christine Kron
* 
* Description : subset VSI refers to ve-fss-62.doc 

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
 * Modul		: TsVAG<br>
 * Date			: 05.12.2011<br>
 * Description	: Definition of subset TsVAG.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class TsVAG extends Teilsatz {
    
	private String beznr  	= "";      // Bezugsnummer 
	private String posnr  	= "0";     // Positionsnummer hier immer mit "0" besetzen	
	private String sicbsc  	= "";      // Nummer der Bürgschaftsbescheinigung 
	private String sicbtg  	= "";      // Abgabenbetrag zur Sicherheit  (15,2)
	private String abggrp  	= "";      // Abgabengruppe gem. Stammdaten
	private String basbtg  	= "";      // Basisbetrag (16,2)
	private String waehrg  	= "";      // Währung des Basisbetrags 

    public TsVAG() {
        tsTyp = "VAG";
    }

    public String teilsatzBilden() {
        StringBuffer buff = new StringBuffer();
          
        buff.append(tsTyp);
        buff.append(trenner);        
        buff.append(beznr);
        buff.append(trenner);	
        buff.append(posnr);
        buff.append(trenner);	
        buff.append(sicbsc);
        buff.append(trenner);	
        buff.append(sicbtg);
        buff.append(trenner);	
        buff.append(abggrp);
        buff.append(trenner);	
        buff.append(basbtg);
        buff.append(trenner);	
        buff.append(waehrg);
        buff.append(trenner);	
        
    	return buff.toString();
    }
	
	public void setFields(String[] fields) {
		Utils.log("TsVAG.setFields not created");
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

	public String getSicbsc() {
		return sicbsc;
	}

	public void setSicbsc(String sicbsc) {
		this.sicbsc = Utils.checkNull(sicbsc);
	}

	public String getSicbtg() {
		return sicbtg;
	}

	public void setSicbtg(String sicbtg) {
		this.sicbtg = Utils.checkNull(sicbtg);
	}

	public String getAbggrp() {
		return abggrp;
	}

	public void setAbggrp(String abggrp) {
		this.abggrp = Utils.checkNull(abggrp);
	}

	public String getBasbtg() {
		return basbtg;
	}

	public void setBasbtg(String basbtg) {
		this.basbtg = Utils.checkNull(basbtg);
	}
	
	public boolean isEmpty() {
		return (Utils.isStringEmpty(sicbsc) && Utils.isStringEmpty(sicbtg) &&
			Utils.isStringEmpty(abggrp) && Utils.isStringEmpty(basbtg));	
	}

	public String getWaehrg() {
		return waehrg;
	}

	public void setWaehrg(String waehrg) {
		this.waehrg = Utils.checkNull(waehrg);
	}
}




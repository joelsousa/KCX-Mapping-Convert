package com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/*
* Funktion    : TsVCO.java
* Titel       :
* Erstellt    : 10.09.2010
* Author      : Kewill / Christine Kron
* 
* Description : subset VCO refers to ve-fss-62.doc 

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
 * Modul		: TsVCO<br>
 * Date			: 10.09.2010<br>
 * Description	: Definition of subset TsVCO.
 * 
 * @author Christine Kron
 * @version 1.0.00
 */
public class TsVCO extends Teilsatz {
    
	private String beznr  	= "";      // Bezugsnummer 
	private String posnr  	= "0";     // Positionsnummer hier immer mit "0" besetzen
	private String colanz  	= "";      // Anzahl Colli
	private String colart  	= "";      // Art der Packstücke
	private String colzs  	= "";      // Collizeichen und -nummern

    public TsVCO() {
        tsTyp = "VCO";
    }
    public String teilsatzBilden() {
        StringBuffer buff = new StringBuffer();
          
        buff.append(tsTyp);
        buff.append(trenner);        
        buff.append(beznr);
        buff.append(trenner);	
        buff.append(posnr);
        buff.append(trenner);	
        buff.append(colanz);
        buff.append(trenner);	
        buff.append(colart);
        buff.append(trenner);	
        buff.append(colzs);
        buff.append(trenner);	

    	return buff.toString();
    }

	// setFields will not be used because VCO is a specific subset of the message VAN
	// and this FSS-message is created by KCX to be sent to Zabis
	// only messages from Zabis have to write out this method

	public void setFields(String[] fields) {
		Utils.log("TsVCO.setFields not created");
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

	public String getColanz() {
		return colanz;
	}

	public void setColanz(String colanz) {
		this.colanz = Utils.checkNull(colanz);
	}

	public String getColart() {
		return colart;
	}

	public void setColart(String colart) {
		this.colart = Utils.checkNull(colart);
	}

	public String getColzs() {
		return colzs;
	}

	public void setColzs(String colzs) {
		this.colzs = Utils.checkNull(colzs);
	}

	public boolean isEmpty() {
		return (Utils.isStringEmpty(colzs) && Utils.isStringEmpty(colanz) &&
			 Utils.isStringEmpty(colart));		
	}
}




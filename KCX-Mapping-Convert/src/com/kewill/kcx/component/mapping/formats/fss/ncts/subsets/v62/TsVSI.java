package com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/*
* Funktion    : TsVSI.java
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
 * Modul		: TsVSI<br>
 * Date			: 8.9.2010<br>
 * Description	: Definition of subset TsVSI.
 * 
 * @author Christine Kron
 * @version 1.0.00
 */
public class TsVSI extends Teilsatz {
    
	private String beznr  	= "";      // Bezugsnummer 
	private String sicart  	= "";      // Art der Sicherheitsleistung
	private String sicbsc  	= "";      // Nummer der Bürgschaftsbescheinigung 
	private String accd  	= "";      // Zugriffscode 
	private String sictin  	= "";      // TIN des Sicherungsgebers
	private String kzncts  	= "";      // 0 = OTS-Sicherheit, 1 = GRN


    public TsVSI() {
        tsTyp = "VSI";
    }

    public String teilsatzBilden() {
        StringBuffer buff = new StringBuffer();
          
        buff.append(tsTyp);
        buff.append(trenner);        
        buff.append(beznr);
        buff.append(trenner);	
        buff.append(sicart);
        buff.append(trenner);	
        buff.append(sicbsc);
        buff.append(trenner);	
        buff.append(accd);
        buff.append(trenner);	
        buff.append(sictin);
        buff.append(trenner);	
        buff.append(kzncts);
        buff.append(trenner);	

    	return buff.toString();
    }

	// setFields will not be used because VSI is a specific subset of the message VAN
	// and this FSS-message is created by KCX to be sent to Zabis
	// only messages from Zabis have to write out this method

	public void setFields(String[] fields) {
		Utils.log("TsVSI.setFields not created");
	}	

	public String getBeznr() {
		return beznr;
	}

	public void setBeznr(String beznr) {
		this.beznr = Utils.checkNull(beznr);
	}

	public String getSicart() {
		return sicart;
	}

	public void setSicart(String sicart) {
		this.sicart = Utils.checkNull(sicart);
	}

	public String getSicbsc() {
		return sicbsc;
	}

	public void setSicbsc(String sicbsc) {
		this.sicbsc = Utils.checkNull(sicbsc);
	}

	public String getAccd() {
		return accd;
	}

	public void setAccd(String accd) {
		this.accd = Utils.checkNull(accd);
	}

	public String getSictin() {
		return sictin;
	}

	public void setSictin(String sictin) {
		this.sictin = Utils.checkNull(sictin);
	}

	public String getKzncts() {
		return kzncts;
	}

	public void setKzncts(String kzncts) {
		this.kzncts = Utils.checkNull(kzncts);
	}
	
	public boolean isEmpty() {
		return (Utils.isStringEmpty(sicart) && Utils.isStringEmpty(sicbsc) &&
			Utils.isStringEmpty(accd) && Utils.isStringEmpty(sictin));	
	}
}




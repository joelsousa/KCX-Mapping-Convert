package com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/*
* Funktion    : TsVSO.java
* Titel       :
* Erstellt    : 11.05.2011
* Author      : Kewill / iwaniuk
* 
* Description : subset VSO refers to ve-fss-62.doc 

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
 * Modul		: TsVSO<br>
 * Date			: 11.05.2011<br>
 * Description	: Definition of subset TsVSO.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class TsVSO extends Teilsatz {
    
	private String beznr  	= "";      // Bezugsnummer 
	private String arbnr  	= "";      // 
	private String mrn  	= "";      //  
	private String stacde  	= "";      // Status der Überwachung/Überführung 
	private String ackdat  	= "";      // Zeitpunkt der Entgegennahme 
	private String andat  	= "";      // Datum der Annahme
	private String antime   = "";      //
	private String uebdat  	= "";      // Datum der Überlassung  
	private String uebtim  	= "";      //
	private String stodat  	= "";      // Zeitpunkt der Stornierung 
	private String bwbdat  	= "";      // Zeitpunkt des Beginns der Weiterverarbeitung außerhalb 
	private String text  	= "";      //  
	private String okdat  	= "";      // Zeitpunkt der Erledigung
	private String guadat  	= "";      // Zeitpunkt der Anrechnung der Referenzbeträge 
	
    public TsVSO() {
        tsTyp = "VSO";
    }

    public String teilsatzBilden() {
        StringBuffer buff = new StringBuffer();
          
        buff.append(tsTyp);
        buff.append(trenner);        
        buff.append(beznr);
        buff.append(trenner);	
        buff.append(arbnr);
        buff.append(trenner);	
        buff.append(mrn);
        buff.append(trenner);	
        buff.append(stacde);
        buff.append(trenner);	
        buff.append(ackdat);
        buff.append(trenner);	
        buff.append(andat);
        buff.append(trenner);	
        buff.append(antime);
        buff.append(trenner);	
        buff.append(uebdat);
        buff.append(trenner);	
        buff.append(uebtim);
        buff.append(trenner);	
        buff.append(stodat);
        buff.append(trenner);	
        buff.append(bwbdat);
        buff.append(trenner);	
        buff.append(text);
        buff.append(trenner);	
        buff.append(okdat);
        buff.append(trenner);	
        buff.append(guadat);
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
		arbnr =  fields[2];
		if (size < 4) {
			return;
		}
		mrn = fields[3];		
		if (size < 5) {
			return;
		}
		stacde = fields[4];
		if (size < 6) {
			return;
		}
		ackdat =  fields[5];
		if (size < 7) {
			return;
		}		
		andat = fields[6];
		if (size < 8) {
			return;
		}		
		antime =  fields[7];
		if (size < 9) {
			return;
		}
		uebdat = fields[8];		
		if (size < 10) {
			return;
		}
		uebtim = fields[9];
		if (size < 11) {
			return;
		}
		stodat =  fields[10];
		if (size < 12) {
			return;
		}
		bwbdat =  fields[11];
		if (size < 13) {
			return;
		}
		text =  fields[12];
		if (size < 14) {
			return;
		}
		okdat =  fields[13];
		if (size < 15) {
			return;
		}
		guadat =  fields[14];				
	}	

	public String getBeznr() {
		return beznr;
	}
	public void setBeznr(String argument) {
		this.beznr = Utils.checkNull(argument);
	}

	public String getArbnr() {
		return arbnr;
	}
	public void setArbnr(String argument) {
		this.arbnr = Utils.checkNull(argument);
	}	

	public String getMrn() {
		return mrn;
	}
	public void setMrn(String argument) {
		this.mrn = Utils.checkNull(argument);
	}
	
	public String getStacde() {
		return stacde;
	}
	public void setStacde(String argument) {
		this.stacde = Utils.checkNull(argument);
	}
	
	public String getAckdat() {
		return ackdat;
	}
	public void setAckdat(String argument) {
		this.ackdat = Utils.checkNull(argument);
	}

	public String getAndat() {
		return andat;
	}
	public void setAndat(String argument) {
		this.andat = Utils.checkNull(argument);
	}
	
	public String getAntime() {
		return antime;
	}
	public void setAntime(String argument) {
		this.antime = Utils.checkNull(argument);
	}

	public String getUebdat() {
		return uebdat;
	}
	public void setUebdat(String argument) {
		this.uebdat = Utils.checkNull(argument);
	}
	
	public String getUebtim() {
		return uebtim;
	}
	public void setUebtim(String argument) {
		this.uebtim = Utils.checkNull(argument);
	}

	public String getStodat() {
		return stodat;
	}
	public void setStodat(String argument) {
		this.stodat = Utils.checkNull(argument);
	}
	
	public String getBwbdat() {
		return bwbdat;
	}
	public void setBwbdat(String argument) {
		this.bwbdat = Utils.checkNull(argument);
	}

	public String getText() {
		return text;
	}
	public void setText(String argument) {
		this.text = Utils.checkNull(argument);
	}
	
	public String getOkdat() {
		return okdat;
	}
	public void setOkdat(String argument) {
		this.okdat = Utils.checkNull(argument);
	}

	public String getGuadat() {
		return guadat;
	}
	public void setGuadat(String argument) {
		this.guadat = Utils.checkNull(argument);
	}
	
	public boolean isEmpty() {
		return ( Utils.isStringEmpty(arbnr) && Utils.isStringEmpty(mrn) && 
				Utils.isStringEmpty(stacde) && Utils.isStringEmpty(ackdat) && 				 
				Utils.isStringEmpty(andat) && Utils.isStringEmpty(antime) &&
				Utils.isStringEmpty(uebdat) && Utils.isStringEmpty(uebtim) && 	
				Utils.isStringEmpty(stodat) && Utils.isStringEmpty(bwbdat) && 
				Utils.isStringEmpty(text) && 
				Utils.isStringEmpty(okdat) && Utils.isStringEmpty(guadat));
		
	}
}




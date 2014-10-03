package com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V70;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		:	Export/aes
 * Created		:	02.08.2012
 * Description	:	Ausfuhrerstattung Kopf ATK.
 *
 * @author 	iwaniuk
 * @version 2.1.00 (Zabis V70)
 */

public class TsATK extends Teilsatz {
	
    private String beznr  = "";    // Bezugsnummer 
   // private String bfarta = "";    // Art des Beförderungsmittels beim Abgang
   // private String bfkza  = "";    // Kennzeichen des Beförderungsmittels beim Abgang
   // private String bfnata = "";    // Land des Beförderungsmittels beim Abgang
    private String asldbe = "";    // Bestimmungsland
    private String asart  = "";    // Antragsart
    private String aszweg = "";    // Zahlungsweg
    private String aszsbv = "";    // Zustellungsbevollmächtigter
    private String asskto = "";    // Sicherheitenkonto
    private String askvb  = "";    // Klammerungsvorbehalt
    private String text   = "";    // Textliche Erklärungen
    
	public TsATK() {
        tsTyp = "ATK";
    }
	
    public void setFields(String[] fields) {    
		int size = fields.length;
				
		if (size < 1) { return; }		
        tsTyp = fields[0];
        if (size < 2) { return; }	
        beznr   = fields[1];
        if (size < 3) { return; }	       
        asldbe  = fields[2];
        if (size < 4) { return; }	
        asart   = fields[3];
        if (size < 5) { return; }	
        aszweg  = fields[4];
        if (size < 6) { return; }	
        aszsbv  = fields[5];
        if (size < 7) { return; }	
        asskto  = fields[6];
        if (size < 8) { return; }	
        askvb   = fields[7];
        if (size < 9) { return; }	
        text    = fields[8];
    }
        
    public String teilsatzBilden() {
        StringBuffer buff = new StringBuffer();
          
        buff.append(tsTyp);
        buff.append(trenner);	        
        buff.append(beznr);	  			
        buff.append(trenner);	 			       
        buff.append(asldbe);	
        buff.append(trenner);	 
        buff.append(asart);	
        buff.append(trenner);	
        buff.append(aszweg);		
        buff.append(trenner);	 
        buff.append(aszsbv);		  	
        buff.append(trenner);	
        buff.append(asskto);
        buff.append(trenner);	
        buff.append(askvb);
        buff.append(trenner);	
        buff.append(text);
        buff.append(trenner);	
        
    	return buff.toString();
    }

	public String getBeznr() {
		return beznr;
	}
	public void setBeznr(String beznr) {
		this.beznr = Utils.checkNull(beznr);
	}
	
	public String getAsldbe() {
		return asldbe;
	}
	public void setAsldbe(String asldbe) {
		this.asldbe = Utils.checkNull(asldbe);
	}

	public String getAsart() {
		return asart;
	}
	public void setAsart(String asart) {
		this.asart = Utils.checkNull(asart);
	}

	public String getAszweg() {
		return aszweg;
	}
	public void setAszweg(String aszweg) {
		this.aszweg = Utils.checkNull(aszweg);
	}

	public String getAszsbv() {
		return aszsbv;
	}
	public void setAszsbv(String aszsbv) {
		this.aszsbv = Utils.checkNull(aszsbv);
	}

	public String getAsskto() {
		return asskto;
	}
	public void setAsskto(String asskto) {
		this.asskto = Utils.checkNull(asskto);
	}

	public String getAskvb() {
		return askvb;
	}
	public void setAskvb(String askvb) {
		this.askvb = Utils.checkNull(askvb);
	}

	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = Utils.checkNull(text);
	}
 
	public boolean isEmpty() {
		return  Utils.isStringEmpty(asldbe) && Utils.isStringEmpty(asart) && Utils.isStringEmpty(aszweg) && 		 
		   Utils.isStringEmpty(aszsbv) && Utils.isStringEmpty(asskto) &&
		   Utils.isStringEmpty(askvb)  && Utils.isStringEmpty(text);			
	}

}

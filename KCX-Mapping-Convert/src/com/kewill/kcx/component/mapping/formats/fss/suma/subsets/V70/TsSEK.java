package com.kewill.kcx.component.mapping.formats.fss.suma.subsets.V70;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		:	Manifest
 * Created		:	17.06.2013
 * Description	:   Kopfsatz Mitteilung der Erledigung - NotificationOfSettlement. 
 *        			Zabis Version 70  
 *
 * @author iwaniuk
 * @version 7.0.00
 */

public class TsSEK  extends Teilsatz {
	  
	private String beznr	= "";			
	private String regnr	= "";		
	private String erlreg	= "";	//Registriernummer des Erledigungsvorgangs (an21)
	private String erlart	= "";	//Art der Erledigung (an6)
	private String befnum	= "";	
	private String ankdat	= "";	
	private String edinr	= "";		
	private String empdat	= "";	//Empfangsdatum dieser Nachricht
	
    public TsSEK() {
        tsTyp = "SEK";
    }

    public void setFields(String[] fields) {  
		int size = fields.length;
		//String ausgabe = "FSS: "+fields[0]+" size = "+ size;
		//Utils.log( ausgabe);
				
		
		if (size < 1) { return; }		
        tsTyp = fields[0];
        if (size < 2) { return; }		
        beznr = fields[1];
        if (size < 3) { return; }	
        regnr = fields[2];
        if (size < 4) { return; }	
        erlreg = fields[3];
        if (size < 5) { return; }	
        erlart = fields[4];
        if (size < 6) { return; }	
        befnum = fields[5];
        if (size < 7) { return; }	
        ankdat = fields[6];
        if (size < 8) { return; }	
        edinr = fields[7];
        if (size < 9) { return; }	
        empdat = fields[8];       
      }

    public String teilsatzBilden() {
        StringBuffer buff = new StringBuffer();

        buff.append(tsTyp);
        buff.append(trenner);       
        buff.append(beznr);
        buff.append(trenner);
        buff.append(regnr);
        buff.append(trenner);
        buff.append(erlreg);
        buff.append(trenner);
        buff.append(erlart);
        buff.append(trenner);
        buff.append(befnum);
        buff.append(trenner);
        buff.append(ankdat);
        buff.append(trenner);
        buff.append(edinr);
        buff.append(trenner);
        buff.append(empdat);
        buff.append(trenner);
       
        return new String(buff);
    }	

	public String getTsTyp() {
		return tsTyp;
	}
	public void setTsTyp(String tsTyp) {
		this.tsTyp = Utils.checkNull(tsTyp);
	}
	
	public String getBeznr() {
		return beznr;
	}
	public void setBeznr(String beznr) {
		this.beznr = Utils.checkNull(beznr);
	}	

	public String getRegnr() {
		return regnr;
	}
	public void setRegnr(String regnr) {
		this.regnr = Utils.checkNull(regnr);
	}

	public String getErlreg() {
		return erlreg;
	}

	public void setErlreg(String erlreg) {
		this.erlreg = Utils.checkNull(erlreg);
	}
	
	public String getErlart() {
		return erlart;
	}

	public void setErlart(String erlart) {
		this.erlart = Utils.checkNull(erlart);
	}

	public String getBefnum() {
		return befnum;
	}
	public void setBefnum(String befnum) {
		this.befnum = Utils.checkNull(befnum);
	}

	public String getAnkdat() {
		return ankdat;
	}
	public void setAnkdat(String ankdat) {
		this.ankdat = Utils.checkNull(ankdat);
	}
	
	public String getEdinr() {
		return edinr;
	}
	public void setEdinr(String edinr) {
		this.edinr = Utils.checkNull(edinr);
	}

	public String getEmpdat() {
		return empdat;
	}
	public void setEmpdat(String empdat) {
		this.empdat = Utils.checkNull(empdat);
	}

	public boolean isEmpty() {
		return (Utils.isStringEmpty(regnr) && 
				Utils.isStringEmpty(erlreg) && Utils.isStringEmpty(erlart) && 
				Utils.isStringEmpty(befnum) && Utils.isStringEmpty(ankdat) &&
				Utils.isStringEmpty(edinr) && Utils.isStringEmpty(empdat));
	}

}



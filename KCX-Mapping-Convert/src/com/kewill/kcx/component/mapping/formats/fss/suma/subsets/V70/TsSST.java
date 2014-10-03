package com.kewill.kcx.component.mapping.formats.fss.suma.subsets.V70;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;


/**
 * Module		:	Manifest
 * Created		:	17.06.2013
 * Description	:   Kopfsatz Bekanntgabe einer Maﬂnahme. 
 *        			Zabis Version 70  
 *
 * @author iwaniuk
 * @version 7.0.00
 */

public class TsSST  extends Teilsatz {
	  
	private String beznr	= "";			
	private String regnr	= "";		
	private String bekdat	= "";	//Datum der Bekanntgabe	
	private String edinr	= "";	//EDIFACT-Nummer	
	private String empdat	= "";	//Empfangsdatum der Nachricht
	
    public TsSST() {
        tsTyp = "SSK";
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
        bekdat = fields[3];
        if (size < 5) { return; }	
        edinr = fields[4];
        if (size < 6) { return; }	
        empdat = fields[5];       
      }

    public String teilsatzBilden() {
        StringBuffer buff = new StringBuffer();

        buff.append(tsTyp);
        buff.append(trenner);       
        buff.append(beznr);
        buff.append(trenner);
        buff.append(regnr);
        buff.append(trenner);
        buff.append(bekdat);
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
	public void setRegnr(String idfltnum) {
		this.regnr = Utils.checkNull(idfltnum);
	}

	public String getBekdat() {
		return bekdat;
	}
	public void setBekdat(String vgref) {
		this.bekdat = Utils.checkNull(vgref);
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
		return (Utils.isStringEmpty(regnr) && Utils.isStringEmpty(bekdat) &&		
			Utils.isStringEmpty(edinr) && Utils.isStringEmpty(empdat));
	}

}



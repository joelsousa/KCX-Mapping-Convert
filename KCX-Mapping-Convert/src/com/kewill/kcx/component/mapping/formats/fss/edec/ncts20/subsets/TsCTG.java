package com.kewill.kcx.component.mapping.formats.fss.edec.ncts20.subsets;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module        :   NCTS 70 
 * Created       :   07.06.2013
 * Description   :   Ts-Abgabengruppen for MsgCTK Transitabmeldung.
 *  
 * @author         iwaniuk
 * @version        7.0.00
 */

public class TsCTG extends Teilsatz {
	private String beznr = "";       //Bezugsnummer
	private String posnr  = "";		
	private String sicbsc = "";		 
	private String abggrp = "";		 
	private String basbtg = "";		 
	private String baswrg = "";		 
	
	public TsCTG() {
        tsTyp = "CTG";
    }

    public void setFields(String[] fields) {
		int size = fields.length;
		//String ausgabe = "FSS: " + fields[0] + " size = " + size;
		//Utils.log( ausgabe);
			
		
		if (size < 1) { return; }		
        tsTyp = fields[0];
        if (size < 2) { return; }	
        beznr = fields[1];
        if (size < 3) { return; }	
         posnr = fields[2];        	
        if (size < 4) { return; }	
        sicbsc = fields[3];
        if (size < 5) { return; }	
        abggrp = fields[4];
        if (size < 6) { return; }	
        basbtg = fields[5];        
        if (size < 7) { return; }	
        baswrg = fields[6];       
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
        buff.append(abggrp);
        buff.append(trenner);
        buff.append(basbtg);
        buff.append(trenner);
        buff.append(baswrg);
        buff.append(trenner);
       
        return new String(buff);
      }

    public void setBeznr(String beznr) {
		this.beznr = Utils.checkNull(beznr);
	}
	public String getBeznr() {
		return beznr;	
	}

	public String getSicbsc() {
		return sicbsc;	
	}
	public void setSicbsc(String connr) {
		this.sicbsc = Utils.checkNull(connr);
	}

	public String getAbggrp() {
		return abggrp;	
	}
	public void setAbggrp(String connr) {
		this.abggrp = Utils.checkNull(connr);
	}

	public String getBasbtg() {
		return basbtg;	
	}
	public void setBasbtg(String connr) {
		this.basbtg = Utils.checkNull(connr);
	}

	public String getBaswrg() {
		return baswrg;	
	}
	public void setBaswrg(String connr) {
		this.baswrg = Utils.checkNull(connr);
	}

	
	public boolean isEmpty() {
		return (Utils.isStringEmpty(sicbsc) &&
				Utils.isStringEmpty(abggrp) && 
				Utils.isStringEmpty(basbtg) && 
				Utils.isStringEmpty(baswrg));
	}

}	

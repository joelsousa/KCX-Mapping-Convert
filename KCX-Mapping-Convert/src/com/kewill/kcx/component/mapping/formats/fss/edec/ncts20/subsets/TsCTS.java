package com.kewill.kcx.component.mapping.formats.fss.edec.ncts20.subsets;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module        :   NCTS 70 
 * Created       :   07.06.2013
 * Description   :   Ts-Sicherheiten for MsgCTK Transitabmeldung.
 *  
 * @author         iwaniuk
 * @version        7.0.00
 */

public class TsCTS extends Teilsatz {
	private String beznr  = "";      //Bezugsnummer
	private String sicart = "";		 //
	private String sicbsc = "";		 //
	private String accd   = "";		 //
	private String tc31nr = "";		 //
	private String sicbtg = "";		 //
	private String sicwrg = "";		 //	 
	
	public TsCTS() {
        tsTyp = "CTS";
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
        	sicart = fields[2];        	
        if (size < 4) { return; }	
        	sicbsc = fields[3];
        if (size < 5) { return; }	
        	accd = fields[4];
        if (size < 6) { return; }	
        	tc31nr = fields[5];        
        if (size < 7) { return; }	
        	sicbtg = fields[6];
        if (size < 8) { return; }	
        	sicwrg = fields[7];
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
        buff.append(tc31nr);
        buff.append(trenner);
        buff.append(sicbtg);
        buff.append(trenner);
        buff.append(sicwrg);
        buff.append(trenner);

        return new String(buff);
      }

    public void setBeznr(String beznr) {
		this.beznr = Utils.checkNull(beznr);
	}
	public String getBeznr() {
		return beznr;	
	}
	
    public void setSicart(String beznr) {
		this.sicart = Utils.checkNull(beznr);
	}
	public String getSicart() {
		return sicart;	
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

	public String getSicwrg() {
		return sicwrg;	
	}
	public void setSicwrg(String sicwrg) {
		this.sicwrg = Utils.checkNull(sicwrg);
	}

	public String getSicbtg() {
		return sicbtg;	
	}
	public void setSicbtg(String sicbtg) {
		this.sicbtg = Utils.checkNull(sicbtg);
	}

	public String getT31nr() {
		return tc31nr;	
	}
	public void setTC31nr(String tc31nr) {
		this.tc31nr = Utils.checkNull(tc31nr);
	}
	
	public boolean isEmpty() {
		return (Utils.isStringEmpty(sicart) &&
				Utils.isStringEmpty(sicbsc) &&
				Utils.isStringEmpty(accd) && 
				Utils.isStringEmpty(sicwrg) && 
				Utils.isStringEmpty(sicbtg) && 
				Utils.isStringEmpty(tc31nr));
	}

}	

package com.kewill.kcx.component.mapping.formats.fss.edec.ncts20.subsets;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module        :   NCTS 70 
 * Created       :   07.06.2013
 * Description   :   Ts-Containerdaten for MsgCTK Transitabmeldung.
 *  
 * @author         iwaniuk
 * @version        7.0.00
 */

public class TsCTC extends Teilsatz {
	private String beznr = "";       //Bezugsnummer
	private String posnr  = "";		
	private String connr1 = "";		 
	private String connr2 = "";		 
	private String connr3 = "";		 
	private String connr4 = "";		 
	private String connr5 = "";	
	private String connr6 = "";		 
	private String connr7 = "";		 
	private String connr8 = "";		 
	private String connr9 = "";		
	
	public TsCTC() {
        tsTyp = "CTC";
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
        connr1 = fields[3];
        if (size < 5) { return; }	
        connr2 = fields[4];
        if (size < 6) { return; }	
        connr3 = fields[5];        
        if (size < 7) { return; }	
        connr4 = fields[6];
        if (size < 8) { return; }	
        connr5 = fields[7];       
        if (size < 9) { return; }	
        connr6 = fields[8];        
        if (size < 10) { return; }	
        connr7 = fields[9];
        if (size < 11) { return; }	
        connr8 = fields[10];
        if (size < 12) { return; }	
        connr9 = fields[11];
    }

    public String teilsatzBilden() {
        StringBuffer buff = new StringBuffer();

        buff.append(tsTyp);
        buff.append(trenner);
        buff.append(beznr);
        buff.append(trenner);
        buff.append(posnr);
        buff.append(trenner);
        buff.append(connr1);
        buff.append(trenner);
        buff.append(connr2);
        buff.append(trenner);
        buff.append(connr3);
        buff.append(trenner);
        buff.append(connr4);
        buff.append(trenner);
        buff.append(connr5);
        buff.append(trenner);
        buff.append(connr6);
        buff.append(trenner);
        buff.append(connr7);
        buff.append(trenner);
        buff.append(connr8);
        buff.append(trenner);
        buff.append(connr9);
        buff.append(trenner);

        return new String(buff);
      }

    public void setBeznr(String beznr) {
		this.beznr = Utils.checkNull(beznr);
	}
	public String getBeznr() {
		return beznr;	
	}

	public String getConnr1() {
		return connr1;	
	}
	public void setConnr1(String connr) {
		this.connr1 = Utils.checkNull(connr);
	}

	public String getConnr2() {
		return connr2;	
	}
	public void setConnr2(String connr) {
		this.connr2 = Utils.checkNull(connr);
	}

	public String getConnr3() {
		return connr3;	
	}
	public void setConnr3(String connr) {
		this.connr3 = Utils.checkNull(connr);
	}

	public String getConnr4() {
		return connr4;	
	}
	public void setConnr4(String connr) {
		this.connr4 = Utils.checkNull(connr);
	}

	public String getConnr5() {
		return connr5;	
	}
	public void setConnr5(String connr) {
		this.connr5 = Utils.checkNull(connr);
	}

	public String getConnr6() {
		return connr6;	
	}
	public void setConnr6(String connr) {
		this.connr6 = Utils.checkNull(connr);
	}

	public String getConnr7() {
		return connr7;	
	}
	public void setConnr7(String connr) {
		this.connr7 = Utils.checkNull(connr);
	}

	public String getConnr8() {
		return connr8;	
	}
	public void setConnr8(String connr) {
		this.connr8 = Utils.checkNull(connr);
	}

	public String getConnr9() {
		return connr9;	
	}
	public void setConnr9(String connr) {
		this.connr9 = Utils.checkNull(connr);
	}

	
	public boolean isEmpty() {
		return (Utils.isStringEmpty(connr1) &&
				Utils.isStringEmpty(connr2) && 
				Utils.isStringEmpty(connr3) && 
				Utils.isStringEmpty(connr4) && 
				Utils.isStringEmpty(connr5) && 
				Utils.isStringEmpty(connr6) && 
				Utils.isStringEmpty(connr7) && 
				Utils.isStringEmpty(connr8) && 
				Utils.isStringEmpty(connr9));
	}

}	

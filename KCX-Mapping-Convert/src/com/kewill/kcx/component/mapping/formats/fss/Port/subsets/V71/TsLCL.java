package com.kewill.kcx.component.mapping.formats.fss.Port.subsets.V71; 

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module       : Port
 * Created		: 12.12.2013<br>
 * Description	: BHT Hafenauftrag LCL-Verweise zum Container . 
 * 				: KIDS: BHTContainerAdditionalData 
 * 				: new in V71 
 * @author iwaniuk
 * @version 1.0.00
 */

public class TsLCL extends Teilsatz {

    private String beznr	= "";	 // Bezugsnummer
    private String connr	= "";	 // 
    private String refnr	= "";	 // Referenznummer / Hafenreferenz 
    private String beznrkd	= "";	 // 
    private String refkd	= "";	 // 
    private String kopfnr	= "";	 // 
    private String nrpos 	= "";	 // 
    private String ctrnr	= "";	 // 
    private String bargnr 	= "";	 // 
    private String sollgw 	= "";	 // 
    private String sollan 	= "";
    private String mark 	= "";
    private String verweis 	= "";
    private String staupl 	= "";
    private String bem 	= "";
    
    public TsLCL() {
	    tsTyp = "CTB";
    }

    public void setFields(String[] fields) {
    	int size = fields.length;
    	//String ausgabe = "FSS: " + fields[0] + " size = " + size;

    	if (size < 1) { return; }
    	tsTyp = fields[0];
	    if (size < 2) { return; }
	    beznr = fields[1];
	    if (size < 3) { return; }
	    connr = fields[2];	    
	    if (size < 4) { return; }
	    refnr = fields[3];
	    if (size < 5) { return; }
	    beznrkd = fields[4];
	    if (size < 6) { return; }
	    refkd = fields[5];
	    if (size < 7) { return; }
	    kopfnr = fields[6];
	    if (size < 8) { return; }
	    nrpos = fields[7];
	    if (size < 9) { return; }
	    ctrnr = fields[8];
	    if (size < 10) { return; }
	    bargnr = fields[9];
	    if (size < 11) { return; }
	    sollgw = fields[10];
	    if (size < 12) { return; }
	    sollan = fields[11];	   
	    if (size < 13) { return; }
	    mark = fields[12];
	    if (size < 14) { return; }
	    verweis = fields[13];
	    if (size < 15) { return; }
	    staupl = fields[14];
	    if (size < 16) { return; }
	    bem = fields[15];	    
    }

    public String teilsatzBilden() {
    	StringBuffer buff = new StringBuffer();

    	buff.append(tsTyp);
    	buff.append(trenner);
    	buff.append(beznr);
    	buff.append(trenner);
    	buff.append(connr);
    	buff.append(trenner);    
    	buff.append(refnr);
    	buff.append(trenner);
    	buff.append(beznrkd);
    	buff.append(trenner);
    	buff.append(refkd);    	
    	buff.append(trenner);
    	buff.append(kopfnr);
    	buff.append(trenner);
    	buff.append(nrpos);
    	buff.append(trenner);
    	buff.append(ctrnr);
    	buff.append(trenner);
    	buff.append(bargnr);
    	buff.append(trenner);
    	buff.append(sollgw);
    	buff.append(trenner);
    	buff.append(sollan);
    	buff.append(trenner);
    	buff.append(mark);
    	buff.append(trenner);
    	buff.append(verweis);
    	buff.append(trenner);
    	buff.append(staupl);
    	buff.append(trenner);
    	buff.append(bem);
    	buff.append(trenner);
    	
    	return new String(buff);    
    }
    
    public String getBeznr() {
    	 return beznr;
    }
    public void setBeznr(String argument) {
    	this.beznr = Utils.checkNull(argument);
    }
   
    public String getConnr() {
		return connr;
	}
	public void setConnr(String connr) {
		this.connr = Utils.checkNull(connr);
	}

	public String getRefnr() {
		return refnr;
	}
	public void setRefnr(String refnr) {
		this.refnr = Utils.checkNull(refnr);
	}

	public String getBeznrkd() {
		return beznrkd;
	}
	public void setBeznrkd(String beznrkd) {
		this.beznrkd = Utils.checkNull(beznrkd);
	}

	public String getRefkd() {
		return refkd;
	}
	public void setRefkd(String refkd) {
		this.refkd = Utils.checkNull(refkd);
	}

	public String getKopfnr() {
		return kopfnr;
	}
	public void setKopfnr(String kopfnr) {
		this.kopfnr = Utils.checkNull(kopfnr);
	}

	public String getNrpos() {
		return nrpos;
	}
	public void setNrpos(String nrpos) {
		this.nrpos = Utils.checkNull(nrpos);
	}

	public String getCtrnr() {
		return ctrnr;
	}
	public void setCtrnr(String ctrnr) {
		this.ctrnr = Utils.checkNull(ctrnr);
	}

	public String getBargnr() {
		return bargnr;
	}
	public void setBargnr(String bargnr) {
		this.bargnr = Utils.checkNull(bargnr);
	}

	public String getSollgw() {
		return sollgw;
	}
	public void setSollgw(String sollgw) {
		this.sollgw = Utils.checkNull(sollgw);
	}
	
	public String getSollan() {
		return sollan;
	}

	public void setSollan(String sollan) {
		this.sollan = Utils.checkNull(sollan);
	}
	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = Utils.checkNull(mark);
	}

	public String getVerweis() {
		return verweis;
	}
	public void setVerweis(String verweis) {
		this.verweis = Utils.checkNull(verweis);
	}

	public String getStaupl() {
		return staupl;
	}
	public void setStaupl(String staupl) {
		this.staupl = Utils.checkNull(staupl);
	}

	public String getBem() {
		return bem;
	}
	public void setBem(String bem) {
		this.bem = Utils.checkNull(bem);
	}

	
	public boolean isEmpty() {
    	return  Utils.isStringEmpty(connr);  //&& ... TODO
    }
}


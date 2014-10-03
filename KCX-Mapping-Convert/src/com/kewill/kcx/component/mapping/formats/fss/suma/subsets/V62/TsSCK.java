package com.kewill.kcx.component.mapping.formats.fss.suma.subsets.V62;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;


/**
 * Module		:	TsSCK
 * Created		:	08.02.2013
 * Description	:   Verarbeitungsergebnisse. 
 *        			Zabis Version 06.02  
 *
 * @author iwaniuk
 * @version 1.0.00
 */

public class TsSCK  extends Teilsatz {

	private String nagru	= "";	   
	private String beznr	= "";			
	private String regnr	= "";			
	private String vgref	= "";	
	private String fltnum	= "";	
	private String befnum	= "";	
	private String ankdat	= "";	
	
    public TsSCK() {
        tsTyp = "SCK";
    }

    public void setFields(String[] fields) {  
		int size = fields.length;
		//String ausgabe = "FSS: "+fields[0]+" size = "+ size;
		//Utils.log( ausgabe);
				
		
		if (size < 1) { return; }		
        tsTyp = fields[0];
        if (size < 2) { return; }		
        nagru = fields[1];
        if (size < 3) { return; }	
        beznr = fields[2];
        if (size < 4) { return; }	
        regnr = fields[3];
        if (size < 5) { return; }	
        vgref = fields[4];
        if (size < 6) { return; }	
        fltnum = fields[5];
        if (size < 7) { return; }	
        befnum = fields[6];
        if (size < 8) { return; }	
        ankdat = fields[7];
      }

    public String teilsatzBilden() {
        StringBuffer buff = new StringBuffer();

        buff.append(tsTyp);
        buff.append(trenner);
        buff.append(nagru);
        buff.append(trenner);
        buff.append(beznr);
        buff.append(trenner);
        buff.append(regnr);
        buff.append(trenner);
        buff.append(vgref);
        buff.append(trenner);
        buff.append(fltnum);
        buff.append(trenner);
        buff.append(befnum);
        buff.append(trenner);
        buff.append(ankdat);
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

	public String getNagru() {
		return nagru;
	}

	//EI20130704: public void setNagru(String idfltnumnagru) {
	public void setNagru(String nagru) {   //EI20130704
		this.nagru = Utils.checkNull(nagru);
	}

	public String getRegnr() {
		return regnr;
	}
	public void setRegnr(String idfltnum) {
		this.regnr = Utils.checkNull(idfltnum);
	}

	public String getVgref() {
		return vgref;
	}

	public void setVgref(String vgref) {
		this.vgref = Utils.checkNull(vgref);
	}

	public String getFltnum() {
		return fltnum;
	}

	public void setFltnum(String fltnum) {
		this.fltnum = Utils.checkNull(fltnum);
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

	public boolean isEmpty() {
		return (Utils.isStringEmpty(nagru) && Utils.isStringEmpty(regnr) &&
			Utils.isStringEmpty(vgref) && Utils.isStringEmpty(fltnum) &&
			Utils.isStringEmpty(befnum) && Utils.isStringEmpty(ankdat));
	}

}



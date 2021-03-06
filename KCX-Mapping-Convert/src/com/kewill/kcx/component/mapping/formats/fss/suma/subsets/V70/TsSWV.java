package com.kewill.kcx.component.mapping.formats.fss.suma.subsets.V70;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;


/**
 * Module		:	Manifest
 * Created		:	17.06.2013
 * Description	:   Kopfsatz Verwahrmitteilung aus NCTS-GoodsReleasedInternal SWV.
 *        			Zabis Version 70  
 *
 * @author iwaniuk
 * @version 7.0.00
 */

public class TsSWV extends Teilsatz {
	
	private String beznr;			//	Bezugsnummer
	private String regnr;			//	Registriernummer	
	private String gstdat;			//	Gestellungsdatum
	private String vorart;			//	Vorpapierart
	private String vornr;			//	Vorpapiernummer
	private String belo;			//	Beladeort
	private String befnum;			//  Nummer der Beförderung
	private String ankdat;			//  Ankunftsdatum
	private String ntyp;			//  Nachrichtentyp
	private String edinr;	
	private String empdat;	
	

    public TsSWV() {
        tsTyp = "SWV";
    }

	
	public void setFields(String[] fields) {
		int size = fields.length;
		
		if (size < 1) { return; }
        tsTyp = fields[0];
        if (size < 2) { return; }		
        beznr = fields[1];
        if (size < 3) { return; }
        regnr = fields[2];
        if (size < 4) { return; }        
        gstdat = fields[3];
        if (size < 5) { return; }
		vorart = fields[4];
        if (size < 6) { return; }
		vornr = fields[5];
        if (size < 7) { return; }
		belo = fields[6];
		if (size < 8) { return; }
		befnum = fields[7];
		if (size < 9) { return; }
		ankdat = fields[8];
		if (size < 10) { return; }
		ntyp = fields[9];
		if (size < 11) { return; }
		edinr = fields[10];
		if (size < 12) { return; }
		empdat = fields[11];
	}
	
	public boolean isEmpty() {

		return (Utils.isStringEmpty(regnr) && 
			Utils.isStringEmpty(gstdat) && Utils.isStringEmpty(vorart) &&
			Utils.isStringEmpty(vornr) && Utils.isStringEmpty(belo)	&& 
			Utils.isStringEmpty(befnum) && Utils.isStringEmpty(ankdat) &&
			Utils.isStringEmpty(edinr) && Utils.isStringEmpty(empdat) &&	
			Utils.isStringEmpty(ntyp));
				
	}

	@Override
	public String teilsatzBilden() {
		 StringBuffer buff = new StringBuffer();

		 	buff.append(tsTyp);
		 	buff.append(trenner);
		 	buff.append(beznr);
		 	buff.append(trenner);
	        buff.append(regnr);
	        buff.append(trenner);	       
	        buff.append(gstdat);
	        buff.append(trenner);
	        buff.append(vorart);
	        buff.append(trenner);	       
	        buff.append(vornr);
	        buff.append(trenner);
	        buff.append(belo);
	        buff.append(trenner);
	        buff.append(befnum);
	        buff.append(trenner);
	        buff.append(ankdat);
	        buff.append(trenner);
	        buff.append(ntyp);
	        buff.append(trenner);
	        buff.append(edinr);
	        buff.append(trenner);
	        buff.append(empdat);
	        buff.append(trenner);

	        return new String(buff);	
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

	public String getGstdat() {
		return gstdat;
	}
	public void setGstdat(String gstdat) {
		this.gstdat = Utils.checkNull(gstdat);
	}

	public String getVorart() {
		return vorart;
	}
	public void setVorart(String vorart) {
		this.vorart = Utils.checkNull(vorart);
	}

	public String getVornr() {
		return vornr;
	}
	public void setVornr(String vornr) {
		this.vornr = Utils.checkNull(vornr);
	}

	public String getBelo() {
		return belo;
	}
	public void setBelo(String belo) {
		this.belo = Utils.checkNull(belo);
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

	public String getNtyp() {
		return ntyp;
	}
	public void setNtyp(String ntyp) {
		this.ntyp = Utils.checkNull(ntyp);
	}

	public String getEdinr() {
		return edinr;
	}
	public void setEdinr(String fltnum) {
		this.edinr = Utils.checkNull(fltnum);
	}
	
	public String getEmpdat() {
		return empdat;
	}
	public void setEmpdat(String fltnum) {
		this.empdat = Utils.checkNull(fltnum);
	}
	
}

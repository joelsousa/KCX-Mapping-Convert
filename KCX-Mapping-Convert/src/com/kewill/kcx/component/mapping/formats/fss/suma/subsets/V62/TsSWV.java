/**
 * Modul		:	TsSWV
 * Erstellt		:	19.11.2012
 * Beschreibung	:   Kopfsatz Verwahrmitteilung aus NCTS <GoodsReleasedInternal> SWV
 *        			@version 06.02  Krzoska
 *
 */


package com.kewill.kcx.component.mapping.formats.fss.suma.subsets.V62;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

public class TsSWV extends Teilsatz {
	
	private String beznr;			//	Bezugsnummer
	private String regnr;			//	Registriernummer
	private String fltnum;			//	Flugnummer der SumA wenn vorhanden
	private String gstdat;			//	Gestellungsdatum
	private String vorart;			//	Vorpapierart
	private String vornr;			//	Vorpapiernummer
	private String belo;			//	Beladeort
	private String befnum;			//  Nummer der Beförderung
	private String ankdat;			//  Ankunftsdatum
	private String ntyp;			//  Nachrichtentyp
	

    public TsSWV() {
        tsTyp = "SWV";
    }

	
	public void setFields(String[] fields){
		int size = fields.length;
		
		if (size < 1) { return; }
        tsTyp = fields[0];
        if (size < 2) { return; }		
        beznr = fields[1];
        if (size < 3) { return; }
        regnr = fields[2];
        if (size < 4) { return; }
        fltnum = fields[3];
        if (size < 5) { return; }
        gstdat = fields[4];
        if (size < 6) { return; }
		vorart = fields[5];
        if (size < 7) { return; }
		vornr = fields[6];
        if (size < 8) { return; }
		belo = fields[7];
		if (size < 9) { return; }
		befnum = fields[8];
		if (size < 10) { return; }
		ankdat = fields[9];
		if (size < 11) { return; }
		ntyp = fields[10];
	}
	
	public boolean isEmpty() {

		if (Utils.isStringEmpty(regnr) && Utils.isStringEmpty(fltnum) &&
			Utils.isStringEmpty(gstdat) && Utils.isStringEmpty(vorart) &&
			Utils.isStringEmpty(vornr) && Utils.isStringEmpty(belo)	&& 
			Utils.isStringEmpty(befnum) && Utils.isStringEmpty(ankdat) &&	
			Utils.isStringEmpty(ntyp)) {
				return true;
		}
		else {
			return false;
		}
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
	        buff.append(fltnum);
	        buff.append(trenner);
	        buff.append(gstdat);
	        buff.append(trenner);
	        buff.append(vorart);
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


	public String getFltnum() {
		return fltnum;
	}


	public void setFltnum(String fltnum) {
		this.fltnum = Utils.checkNull(fltnum);
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

	
	
}

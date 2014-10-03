package com.kewill.kcx.component.mapping.formats.fss.suma.subsets.V70;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;


/**
 * Module		:	Manifest
 * Created		:	17.06.2013
 * Description	:   Positionssatz ReExport.
 *        			(Zabis V70) 
 *
 * @author iwaniuk
 * @version 7.0.00
 */

public class TsSAP extends Teilsatz {
	
	private String beznr = "";		// Bezugsnummer
	private String posnr = "";		// Nummer der Position des Wiederausfuhr/ Versand Vorgangs
	private String aregnr = "";		// Registriernummer des Bezugsvorgangs
	private String aposnr = "";		// Positionsnummer des Bezugs-Vorgangs
	private String colanz = "";		// Anzahl Packstücke
	private String kzawb = "";		// Art des spezifischen Ordnungsbegriffes (AWB-Nummer oder sonstiges)
	private String awbzzz = "";		// Spezifischer Ordnungsbegriff
	private String besort = "";		// Bestimmungsort
	private String kzaend = "";		// Änderungskennzeichen
	private String idkzawb = "";		// Angesprochener SpO-Art
	private String idawbzzz = "";	// Angesprochener SpO
	private String idvwrkdnr = "";	// Angesprochener Verwahrer Kundennummer (neu in Atlas8.4)
	private String idvwreori = "";	// Angesprochener Verwahrer EORI_TIN (neu in Atlas8.4)
	private String asumamrn = "";	// Ausgangs-SumA MRN
	private String asumaposnr = "";	// Ausgangs-SumA Positionsnummer
	
	public TsSAP() {
		tsTyp = "SAP";
	}
	
	public String teilsatzBilden() {
		StringBuffer buff = new StringBuffer();
		buff.append(tsTyp);
		buff.append(trenner);
		buff.append(beznr);
		buff.append(trenner);
		buff.append(posnr);
		buff.append(trenner);
		buff.append(aregnr);
		buff.append(trenner);
		buff.append(aposnr);
		buff.append(trenner);
		buff.append(colanz);
		buff.append(trenner);
		buff.append(kzawb);
		buff.append(trenner);
		buff.append(awbzzz);
		buff.append(trenner);
		buff.append(besort);
		buff.append(trenner);		
		buff.append(kzaend);
		buff.append(trenner);		
		buff.append(idkzawb);
		buff.append(trenner);
		buff.append(idawbzzz);
		buff.append(trenner);
		buff.append(idvwrkdnr);
		buff.append(trenner);
		buff.append(idvwreori);
		buff.append(trenner);
		buff.append(asumamrn);
		buff.append(trenner);
		buff.append(asumaposnr);
		buff.append(trenner);
		return new String(buff);
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
        aregnr = fields[2];
        //usw.
	}
	
	public boolean isEmpty() {
    	return Utils.isStringEmpty(aregnr) && Utils.isStringEmpty(posnr);    
    	//usw.
    }

	public String getBeznr() {
		return beznr;
	}
	public void setBeznr(String beznr) {
		this.beznr = Utils.checkNull(beznr);
	}

	public String getAregnr() {
		return aregnr;
	}
	public void setAregnr(String aregnr) {
		this.aregnr = Utils.checkNull(aregnr);
	}
	
	public String getPosnr() {
		return posnr;
	}
	public void setPosnr(String posnr) {
		this.posnr = Utils.checkNull(posnr);
	}
	
	public String getAposnr() {
		return aposnr;
	}
	public void setAposnr(String aposnr) {
		this.aposnr = Utils.checkNull(aposnr);
	}

	public String getColanz() {
		return colanz;
	}
	public void setColanz(String stkanz) {
		this.colanz = Utils.checkNull(stkanz);
	}

	public String getKzawb() {
		return kzawb;
	}
	public void setKzawb(String kzawb) {
		this.kzawb = Utils.checkNull(kzawb);
	}

	public String getAwbzzz() {
		return awbzzz;
	}
	public void setAwbzzz(String awbzzz) {
		this.awbzzz = Utils.checkNull(awbzzz);
	}

	public String getBesort() {
		return besort;
	}
	public void setBesort(String besort) {
		this.besort = Utils.checkNull(besort);
	}
	
	public String getKzaend() {
		return kzaend;
	}
	public void setKzaend(String kzaend) {
		this.kzaend = Utils.checkNull(kzaend);
	}
	
	public String getIdkzawb() {
		return idkzawb;
	}
	public void setIdkzawb(String idkzawb) {
		this.idkzawb = Utils.checkNull(idkzawb);
	}

	public String getIdawbzzz() {
		return idawbzzz;
	}
	public void setIdawbzzz(String idawbzzz) {
		this.idawbzzz = Utils.checkNull(idawbzzz);
	}

	public String getIdvwrkdnr() {
		return idvwrkdnr;
	}
	public void setIdvwrkdnr(String idvwrkdnr) {
		this.idvwrkdnr = Utils.checkNull(idvwrkdnr);
	}

	public String getIdvwreori() {
		return idvwreori;
	}
	public void setIdvwreori(String idvwreori) {
		this.idvwreori = Utils.checkNull(idvwreori);
	}

	public String getAsumamrn() {
		return asumamrn;
	}
	public void setAsumamrn(String asumamrn) {
		this.asumamrn = Utils.checkNull(asumamrn);
	}
	
	public String getAsumaposnr() {
		return asumaposnr;
	}
	public void setAsumaposnr(String asumaposnr) {
		this.asumaposnr = Utils.checkNull(asumaposnr);
	}	

}

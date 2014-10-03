package com.kewill.kcx.component.mapping.formats.fss.suma.subsets.V70;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;


/**
 * Module		:	TsSUP
 * Created		:	31.05.2013
 * Description	:   Kopfsatz Gestellung-/Aufteilungsdaten.
 *        			Zabis Version 07.00  
 *
 * @author krzoska
 * @version 7.0.00
 */

public class TsSBP  extends Teilsatz {

	private String beznr		= "";	// Bezugsnummer
	private String posnr		= "";	// Positionsnummer
	private String kzbest		= "";	// Bestätigungs-Kennzeichen
	private String kzuvwm		= "";	// Kennzeichen "Unterdrückung der Verwahrmitteilung"
	private String vland		= "";	// Versendungs-/Ausfuhrland
	private String besort		= "";	// Bestimmungsort
	private String zollst		= "";	// Zollrechtlicher Status
	private String kzawb		= "";	// Art des spezifischen Ordnungsbegriffes (AWB-Nummer oder sonstiges)
	private String spo			= "";	// Spezifischer Ordnungsbegriff
	private String colart		= "";	// Art der Packstücke
	private String colanz		= "";	// Anzahl Packstücke
	private String rohm			= "";	// Rohmasse  (n(14,3))
	private String wabes		= "";	// Warenbeschreibung
	private String wakr			= "";	// Warenkreis
	private String vrwkdnr		= "";	// Kundennummer des Verwahrers (neu in Atlas8.4)
	private String vrweori		= "";	// EORI-TIN des Verwahrers (neu in Atlas8.4)
	private String vrwnlnr		= "";	// Niederlassung des Verwahrers (neu in Atlas8.4)
	private String vrwort		= "";	// Verwahrortschlüssel
	private String vfbkdnr		= "";	// Kundennummer des Verfügungsberechtigten (neu in Atlas8.4)
	private String vfbeori		= "";	// EORI-TIN des Verfügungsberechtigten (neu in Atlas8.4)
	private String vfbnlnr		= "";	// Niederlassung des Verfügungsberechtigten (neu in Atlas8.4)
	private String emrn			= "";	// Eingangs-SumA MRN      //EI20130617
	private String emrnpos		= "";	// Eingangs-SumA Position //EI20130617
	private String kzfrzone		= "";	// Kennzeichen Freizone

    public TsSBP() {
        tsTyp = "SBP";
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
        posnr = fields[2];
        if (size < 4) { return; }	
        kzbest = fields[3];
        //usw.... z.T wird die methode nicht gebraucht
      }

    public String teilsatzBilden() {
        StringBuffer buff = new StringBuffer();

        buff.append(tsTyp);
		buff.append(trenner);
		buff.append(beznr);
		buff.append(trenner);
		buff.append(posnr);
		buff.append(trenner);
		buff.append(kzbest);
		buff.append(trenner);
		buff.append(kzuvwm);
		buff.append(trenner);
		buff.append(vland);
		buff.append(trenner);
		buff.append(besort);
		buff.append(trenner);
		buff.append(zollst);
		buff.append(trenner);
		buff.append(kzawb);
		buff.append(trenner);
		buff.append(spo);
		buff.append(trenner);
		buff.append(colart);
		buff.append(trenner);
		buff.append(colanz);
		buff.append(trenner);
		buff.append(rohm);
		buff.append(trenner);
		buff.append(wabes);
		buff.append(trenner);
		buff.append(wakr);
		buff.append(trenner);
		buff.append(vrwkdnr);
		buff.append(trenner);
		buff.append(vrweori);
		buff.append(trenner);
		buff.append(vrwnlnr);
		buff.append(trenner);
		buff.append(vrwort);
		buff.append(trenner);
		buff.append(vfbkdnr);
		buff.append(trenner);
		buff.append(vfbeori);
		buff.append(trenner);
		buff.append(vfbnlnr); 
		buff.append(trenner);
		buff.append(emrn); 
		buff.append(trenner);
		buff.append(emrnpos); 
		buff.append(trenner);
		buff.append(kzfrzone);
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

	public String getPosnr() {
		return posnr;
	}

	public void setPosnr(String posnr) {
		this.posnr = Utils.checkNull(posnr);
	}

	public String getKzuvwm() {
		return kzuvwm;
	}

	public void setKzuvwm(String kzuvwm) {
		this.kzuvwm = Utils.checkNull(kzuvwm);
	}

	public String getVland() {
		return vland;
	}

	public void setVland(String vland) {
		this.vland = Utils.checkNull(vland);
	}

	public String getBesort() {
		return besort;
	}

	public void setBesort(String besort) {
		this.besort = Utils.checkNull(besort);
	}

	public String getZollst() {
		return zollst;
	}

	public void setZollst(String zollst) {
		this.zollst = Utils.checkNull(zollst);
	}

	public String getKzawb() {
		return kzawb;
	}

	public void setKzawb(String kzawb) {
		this.kzawb = Utils.checkNull(kzawb);
	}

	public String getSpo() {
		return spo;
	}

	public void setSpo(String spo) {
		this.spo = Utils.checkNull(spo);
	}

	public String getColart() {
		return colart;
	}

	public void setColart(String colart) {
		this.colart = Utils.checkNull(colart);
	}

	public String getColanz() {
		return colanz;
	}

	public void setColanz(String colanz) {
		this.colanz = Utils.checkNull(colanz);
	}

	public String getRohm() {
		return rohm;
	}

	public void setRohm(String rohm) {
		this.rohm = Utils.checkNull(rohm);
	}

	public String getWabes() {
		return wabes;
	}

	public void setWabes(String wabes) {
		this.wabes = Utils.checkNull(wabes);
	}

	public String getWakr() {
		return wakr;
	}

	public void setWakr(String wakr) {
		this.wakr = Utils.checkNull(wakr);
	}

	public String getVrwort() {
		return vrwort;
	}

	public void setVrwort(String vrwort) {
		this.vrwort = Utils.checkNull(vrwort);
	}

	public String getKzbest() {
		return kzbest;
	}

	public void setKzbest(String kzbest) {
		this.kzbest = Utils.checkNull(kzbest);
	}

	public boolean isEmpty() {

		return	(Utils.isStringEmpty(kzbest) && Utils.isStringEmpty(kzuvwm) && 
				Utils.isStringEmpty(vland) && Utils.isStringEmpty(besort) && 
				Utils.isStringEmpty(zollst) && Utils.isStringEmpty(kzawb) && 
				Utils.isStringEmpty(spo) && Utils.isStringEmpty(colart) && 
				Utils.isStringEmpty(colanz) && Utils.isStringEmpty(rohm) && 
				Utils.isStringEmpty(wabes) && Utils.isStringEmpty(wakr) && 
				Utils.isStringEmpty(vrwkdnr) &&	Utils.isStringEmpty(vrweori) && 
				Utils.isStringEmpty(vrwnlnr) &&	Utils.isStringEmpty(vrwort) && 
				Utils.isStringEmpty(vfbkdnr) &&	Utils.isStringEmpty(vfbeori) && 
				Utils.isStringEmpty(vfbnlnr) &&	Utils.isStringEmpty(kzfrzone));
	}

	public String getVrwkdnr() {
		return vrwkdnr;
	}

	public void setVrwkdnr(String vrwkdnr) {
		this.vrwkdnr = Utils.checkNull(vrwkdnr);
	}

	public String getVrweori() {
		return vrweori;
	}

	public void setVrweori(String vrweori) {
		this.vrweori = Utils.checkNull(vrweori);
	}

	public String getVrwnlnr() {
		return vrwnlnr;
	}

	public void setVrwnlnr(String vrwnlnr) {
		this.vrwnlnr = Utils.checkNull(vrwnlnr);
	}

	public String getVfbkdnr() {
		return vfbkdnr;
	}

	public void setVfbkdnr(String vfbkdnr) {
		this.vfbkdnr = Utils.checkNull(vfbkdnr);
	}

	public String getVfbeori() {
		return vfbeori;
	}

	public void setVfbeori(String vfbeori) {
		this.vfbeori = Utils.checkNull(vfbeori);
	}

	public String getVfbnlnr() {
		return vfbnlnr;
	}

	public void setVfbnlnr(String vfbnlnr) {
		this.vfbnlnr = Utils.checkNull(vfbnlnr);
	}

	public String getEmrn() {
		return emrn;
	}

	public void setEmrn(String emrn) {
		this.emrn = Utils.checkNull(emrn);
	}

	public String getEmrnpos() {
		return emrnpos;
	}

	public void setEmrnpos(String emrnpos) {
		this.emrnpos = Utils.checkNull(emrnpos);
	}

	public String getKzfrzone() {
		return kzfrzone;
	}

	public void setKzfrzone(String kzfrzone) {
		this.kzfrzone = Utils.checkNull(kzfrzone);
	}

	
}
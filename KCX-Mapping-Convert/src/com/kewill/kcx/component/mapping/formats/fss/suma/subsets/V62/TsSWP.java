package com.kewill.kcx.component.mapping.formats.fss.suma.subsets.V62;

/**
 * Modul		:	TsSWP
 * Erstellt		:	19.11.2012
 * Beschreibung	:   Kopfsatz Verwahrmitteilung aus NCTS <GoodsReleasedInternal> SWP
 *        			@version 06.02  Krzoska.
 *
 */

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

public class TsSWP extends Teilsatz {
	
	private String beznr;			//	Bezugsnummer
	private String regnr;			//	Registriernummer
	private String fltnum;			//	Flugnummer der SumA wenn vorhanden
	private String posnr;			//	Positionsnummer
	private String anmfri;			//	Anmeldefrist
	private String kzawb;			//	Art des spezifischen Ordnungsbegriffes
	private String awbzzz;			//	Spezifischer Ordnungsbegriff
	private String collart;			//	Art der Packstücke
	private String collbez;			//	Colliart
	private String stkanz;			//	Anzahl Packstücke
	private String rohm;				//	Rohmasse
	private String wabes;			//	Warenbeschreibung
	private String wakr;				//	Warenkreis VUB
	private String wakbez;			//	Bezeichnung Warenkreis
	private String vrwznr;			//	Zollnummer des Verwahrers
	private String vwocd;			//	Verwahrortschlüssel
	private String vwobez;			//	Bezeichnung des Verwahrorts
	private String vfbznr;			//	Zollnummer des Verfügungsberechtigten
	private String vwdat;			//	Empfangsdatum der Verwahrmitteilung
	private String vwzeit;			//	Empfangszeit der Verwahrmitteilung
	private String fremd;			//	Kennzeichen „fremd
	private String kzfrzone;		//  Kennzeichen Freizone
	private String vland;			//  Versendungs-/Ausfuhrland“
	
	public TsSWP() {    //EI20130617: hat hier gefehlt
        tsTyp = "SWP";
    }
	
	public void setFields(String[] fields) {
		int size = fields.length;
		
		if (size < 1) { return; }
		tsTyp	= fields[0];
		if (size < 2) { return; }
		beznr	= fields[1];
		if (size < 3) { return; }
		regnr	= fields[2];
		if (size < 4) { return; }
		fltnum =  fields[3];
		if (size < 5) { return; }
		posnr	= fields[4];
		if (size < 6) { return; }
		anmfri	= fields[5];
		if (size < 7) { return; }
		kzawb	= fields[6];
		if (size < 8) { return; }
		awbzzz	= fields[7];
		if (size < 9) { return; }
		collart	= fields[8];
		if (size < 10) { return; }
		collbez	= fields[9];
		if (size < 11) { return; }
		stkanz	= fields[10];
		if (size < 12) { return; }
		rohm	= fields[11];
		if (size < 13) { return; }
		wabes	= fields[12];
		if (size < 14) { return; }
		wakr	= fields[13];
		if (size < 15) { return; }
		wakbez	= fields[14];
		if (size < 16) { return; }
		vrwznr	= fields[15];
		if (size < 17) { return; }
		vwocd	= fields[16];
		if (size < 18) { return; }
		vwobez	= fields[17];
		if (size < 19) { return; }
		vfbznr	= fields[18];
		if (size < 20) { return; }
		vwdat	= fields[19];
		if (size < 21) { return; }
		vwzeit	= fields[20];
		if (size < 22) { return; }
		fremd	= fields[21];
		if (size < 23) { return; }
		kzfrzone = fields[22];
		if (size < 24) { return; }
		vland	= fields[23];
	}
	
	
	@Override
	public boolean isEmpty() {
		if (Utils.isStringEmpty(regnr)  && Utils.isStringEmpty(fltnum)  &&  
			Utils.isStringEmpty(anmfri)  &&  
			Utils.isStringEmpty(kzawb)  &&  Utils.isStringEmpty(awbzzz)  &&  
			Utils.isStringEmpty(collart) && Utils.isStringEmpty(collbez)  &&  
			Utils.isStringEmpty(stkanz)  &&  Utils.isStringEmpty(rohm)  &&  
			Utils.isStringEmpty(wabes)  &&  Utils.isStringEmpty(wakr)  &&  
			Utils.isStringEmpty(wakbez)  && Utils.isStringEmpty(vrwznr)  &&  
			Utils.isStringEmpty(vwocd)  &&  Utils.isStringEmpty(vwobez)  &&  
			Utils.isStringEmpty(vfbznr)  && Utils.isStringEmpty(vwdat)  &&  
			Utils.isStringEmpty(vwzeit)  && Utils.isStringEmpty(fremd) && 
			Utils.isStringEmpty(kzfrzone)  && Utils.isStringEmpty(vland)) {
				return true;
		} else {
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
		 	buff.append(posnr);	
		 	buff.append(trenner);
		 	buff.append(anmfri);	
		 	buff.append(trenner);
		 	buff.append(kzawb);	
		 	buff.append(trenner);
		 	buff.append(awbzzz);	
		 	buff.append(trenner);
		 	buff.append(collart);
		 	buff.append(trenner);
		 	buff.append(collbez);
		 	buff.append(trenner);
		 	buff.append(stkanz);	
		 	buff.append(trenner);
		 	buff.append(rohm);	
		 	buff.append(trenner);
		 	buff.append(wabes);	
		 	buff.append(trenner);
		 	buff.append(wakr);	
		 	buff.append(trenner);
		 	buff.append(wakbez);	
		 	buff.append(trenner);
		 	buff.append(vrwznr);	
		 	buff.append(trenner);
		 	buff.append(vwocd);	
		 	buff.append(trenner);
		 	buff.append(vwobez);	
		 	buff.append(trenner);
		 	buff.append(vfbznr);	
		 	buff.append(trenner);
		 	buff.append(vwdat);	
		 	buff.append(trenner);
		 	buff.append(vwzeit);	
		 	buff.append(trenner);
		 	buff.append(fremd);	
		 	buff.append(trenner);
		 	buff.append(kzfrzone);	
		 	buff.append(trenner);
		 	buff.append(vland);	
		 	buff.append(trenner);
		 	
		 	return buff.toString();
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


	public String getPosnr() {
		return posnr;
	}


	public void setPosnr(String posnr) {
		this.posnr = Utils.checkNull(posnr);
	}


	public String getAnmfri() {
		return anmfri;
	}


	public void setAnmfri(String anmfri) {
		this.anmfri = Utils.checkNull(anmfri);
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


	public String getCollart() {
		return collart;
	}


	public void setCollart(String collart) {
		this.collart = Utils.checkNull(collart);
	}


	public String getCollbez() {
		return collbez;
	}


	public void setCollbez(String collbez) {
		this.collbez = Utils.checkNull(collbez);
	}


	public String getStkanz() {
		return stkanz;
	}


	public void setStkanz(String stkanz) {
		this.stkanz = Utils.checkNull(stkanz);
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


	public String getWakbez() {
		return wakbez;
	}


	public void setWakbez(String wakbez) {
		this.wakbez = Utils.checkNull(wakbez);
	}


	public String getVrwznr() {
		return vrwznr;
	}


	public void setVrwznr(String vrwznr) {
		this.vrwznr = Utils.checkNull(vrwznr);
	}


	public String getVwocd() {
		return vwocd;
	}


	public void setVwocd(String vwocd) {
		this.vwocd = Utils.checkNull(vwocd);
	}


	public String getVwobez() {
		return vwobez;
	}


	public void setVwobez(String vwobez) {
		this.vwobez = Utils.checkNull(vwobez);
	}


	public String getVfbznr() {
		return vfbznr;
	}


	public void setVfbznr(String vfbznr) {
		this.vfbznr = Utils.checkNull(vfbznr);
	}


	public String getVwdat() {
		return vwdat;
	}


	public void setVwdat(String vwdat) {
		this.vwdat = Utils.checkNull(vwdat);
	}


	public String getVwzeit() {
		return vwzeit;
	}


	public void setVwzeit(String vwzeit) {
		this.vwzeit = Utils.checkNull(vwzeit);
	}


	public String getFremd() {
		return fremd;
	}


	public void setFremd(String fremd) {
		this.fremd = Utils.checkNull(fremd);
	}


	public String getKzfrzone() {
		return kzfrzone;
	}


	public void setKzfrzone(String kzfrzone) {
		this.kzfrzone = Utils.checkNull(kzfrzone);
	}


	public String getVland() {
		return vland;
	}


	public void setVland(String vland) {
		this.vland = Utils.checkNull(vland);
	}
}
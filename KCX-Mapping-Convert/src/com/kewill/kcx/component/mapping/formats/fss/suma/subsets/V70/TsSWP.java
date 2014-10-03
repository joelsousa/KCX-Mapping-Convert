package com.kewill.kcx.component.mapping.formats.fss.suma.subsets.V70;

/**
 * Module		:	Manifest
 * Created		:	17.06.2013
 * Description	:   Positionssatz Verwahrmitteilung aus NCTS-GoodsReleasedInternal SWV.
 *        			Zabis Version 70  
 *
 * @author iwaniuk
 * @version 7.0.00
 */

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

public class TsSWP extends Teilsatz {
	
	private String beznr;			//	Bezugsnummer
	private String regnr;			//	Registriernummer	
	private String posnr;			//	Positionsnummer
	private String anmfri;			//	Anmeldefrist
	private String kzawb;			//	Art des spezifischen Ordnungsbegriffes
	private String awbzzz;			//	Spezifischer Ordnungsbegriff
	private String collart;			//	Art der Packstücke
	private String collbez;			//	Colliart
	private String stkanz;			//	Anzahl Packstücke
	private String rohm;			//	Rohmasse   (n(14,3)
	private String wabes;			//	Warenbeschreibung (an140)
	private String wakr;			//	Warenkreis VUB   (an1)
	private String wakbez;			//	Bezeichnung Warenkreis (an35)
	//private String vrwknr;			//	Zollnummer des Verwahrers
	private String vrweori;
	private String vrwnl;
	private String vrwort;			//	Verwahrortschlüssel (an2)
	private String vwobez;			//	Bezeichnung des Verwahrorts (an35)
	//private String vfbknr;		//	Zollnummer des Verfügungsberechtigten
	private String vfbeori;
	private String vfbnl;
	private String vwdat;			//	Empfangsdatum der Verwahrmitteilung
	private String vwzeit;			//	Empfangszeit der Verwahrmitteilung
	private String fremd;			//	Kennzeichen „fremd
	private String zollst;			//  Zollrechtlicher Status
	private String frzonekz;		//  Kennzeichen Freizone
	private String vland;			//  Versendungs-/Ausfuhrland“
	
	
	public TsSWP() {    
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
		posnr	= fields[3];
		if (size < 5) { return; }
		anmfri	= fields[4];
		if (size < 6) { return; }
		kzawb	= fields[5];
		if (size < 7) { return; }
		awbzzz	= fields[6];
		if (size < 8) { return; }
		collart	= fields[7];
		if (size < 9) { return; }
		collbez	= fields[8];
		if (size < 10) { return; }
		stkanz	= fields[9];
		if (size < 11) { return; }
		rohm	= fields[10];
		if (size < 12) { return; }
		wabes	= fields[11];
		if (size < 13) { return; }
		wakr	= fields[12];
		if (size < 14) { return; }
		wakbez	= fields[13];
		if (size < 15) { return; }
		vrweori	= fields[14];
		if (size < 16) { return; }
		vrwnl =  fields[15];
		if (size < 17) { return; }
		vrwort =  fields[16];
		if (size < 18) { return; }
		vwobez	= fields[17];
		if (size < 19) { return; }
		vfbeori	= fields[18];
		if (size < 20) { return; }
		vfbnl = fields[19];
		if (size < 21) { return; }
		vwdat	= fields[20];
		if (size < 22) { return; }
		vwzeit	= fields[21];
		if (size < 23) { return; }
		fremd = fields[22];
		if (size < 24) { return; }
		zollst = fields[23];
		if (size < 25) { return; }
		frzonekz = fields[24]; 
		if (size < 26) { return; }
		vland = fields[25];
		
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
		 	buff.append(vrweori);	
		 	buff.append(trenner);
		 	buff.append(vrwnl);	
		 	buff.append(trenner);
		 	buff.append(vrwort);	
		 	buff.append(trenner);
		 	buff.append(vwobez);	
		 	buff.append(trenner);		 	
		 	buff.append(vfbeori);	
		 	buff.append(trenner);
		 	buff.append(vfbnl);	
		 	buff.append(trenner);
		 	buff.append(vwdat);	
		 	buff.append(trenner);
		 	buff.append(vwzeit);	
		 	buff.append(trenner);
		 	buff.append(fremd);	
		 	buff.append(trenner);
		 	buff.append(zollst);	
		 	buff.append(trenner);
		 	buff.append(frzonekz);	
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

	
	public String getVrweori() {
		return vrweori;
	}
	public void setVrweori(String vrweori) {
		this.vrweori = Utils.checkNull(vrweori);
	}
	
	public String getVrwnl() {
		return vrwnl;
	}
	public void setVrwnl(String vrwnl) {
		this.vrwnl = Utils.checkNull(vrwnl);
	}
	
	public String getVrwort() {
		return vrwort;
	}
	public void setVrwort(String vrwort) {
		this.vrwort = Utils.checkNull(vrwort);
	}

	public String getVwobez() {
		return vwobez;
	}
	public void setVwobez(String vwobez) {
		this.vwobez = Utils.checkNull(vwobez);
	}

	public String getVfbeori() {
		return vfbeori;
	}
	public void setVfbeori(String vfbeori) {
		this.vfbeori = Utils.checkNull(vfbeori);
	}
	
	public String getVfbnl() {
		return vfbnl;
	}
	public void setVfbnl(String vfbnl) {
		this.vfbnl = Utils.checkNull(vfbnl);
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

	public String getZollst() {
		return zollst;
	}
	public void setZollst(String zollst) {
		this.zollst = Utils.checkNull(zollst);
	}

	
	public String getFrzonekz() {
		return frzonekz;
	}
	public void setFrzonekz(String frzonekz) {
		this.frzonekz = Utils.checkNull(frzonekz);
	}

	public String getVland() {
		return vland;
	}
	public void setVland(String vland) {
		this.vland = Utils.checkNull(vland);
	}
	
	@Override
	public boolean isEmpty() {
		if (Utils.isStringEmpty(regnr)  && Utils.isStringEmpty(anmfri)  &&  			
			Utils.isStringEmpty(kzawb)  &&  Utils.isStringEmpty(awbzzz)  &&  
			Utils.isStringEmpty(collart) && Utils.isStringEmpty(collbez)  &&  
			Utils.isStringEmpty(stkanz)  &&  Utils.isStringEmpty(rohm)  &&  
			Utils.isStringEmpty(wabes)  &&  Utils.isStringEmpty(wakr)  &&  
			Utils.isStringEmpty(wakbez)  && Utils.isStringEmpty(vrweori)  &&  
			Utils.isStringEmpty(vrwort)  &&  Utils.isStringEmpty(vwobez)  &&  
			Utils.isStringEmpty(vwdat)  &&  
			Utils.isStringEmpty(vwzeit)  && Utils.isStringEmpty(fremd) && 
			Utils.isStringEmpty(frzonekz)  && Utils.isStringEmpty(vland)) {
				return true;
		} else {
			return false;
		}
	}
}
package com.kewill.kcx.component.mapping.formats.fss.suma.subsets.V62;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;


/**
 * Module		:	TsEVK
 * Created		:	19.12.2012
 * Description	:   Kopfsatz Gestellung-/Aufteilungsdaten.
 *        			Zabis Version 06.02  
 *
 * @author iwaniuk
 * @version 1.0.00
 */

public class TsSUP  extends Teilsatz {

	private String beznr	= "";			//	
	private String posnr	= "";			//	Registriernummer
	private String kzuvwm	= "";			//	Gestellungsdatum 
	private String vland	= "";
	private String besort	= "";
	private String zollst	= "";
	private String kzawb	= "";
	private String spo		= "";
	private String colart	= "";
	private String colanz	= "";
	private String rohm		= "";
	private String wabes	= "";
	private String wakr		= "";
	private String vrwznr	= "";
	private String vrwort	= "";
	private String vfbznr	= "";
	private String kzbest	= "";
	private String emrn		= "";
	private String emrnpos	= "";
	private String kzfrzone	= "";
	
    public TsSUP() {
        tsTyp = "SUP";
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
        kzuvwm = fields[3];
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
        buff.append(vrwznr);
        buff.append(trenner);
        buff.append(vrwort);
        buff.append(trenner);
        buff.append(vfbznr);
        buff.append(trenner);
        buff.append(kzbest);
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

public String getVrwznr() {
	return vrwznr;
}

public void setVrwznr(String vrwznr) {
	this.vrwznr = Utils.checkNull(vrwznr);
}

public String getVrwort() {
	return vrwort;
}

public void setVrwort(String vrwort) {
	this.vrwort = Utils.checkNull(vrwort);
}

public String getVfbznr() {
	return vfbznr;
}

public void setVfbznr(String vfbznr) {
	this.vfbznr = Utils.checkNull(vfbznr);
}

public String getKzbest() {
	return kzbest;
}

public void setKzbest(String kzbest) {
	this.kzbest = Utils.checkNull(kzbest);
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

	public boolean isEmpty() {

	return ( Utils.isStringEmpty(kzuvwm) && Utils.isStringEmpty(vland) && Utils.isStringEmpty(besort) && 
			Utils.isStringEmpty(zollst) && Utils.isStringEmpty(colart)&& Utils.isStringEmpty(colanz) &&
			Utils.isStringEmpty(rohm) && Utils.isStringEmpty(kzbest)&& Utils.isStringEmpty(kzfrzone));	
	}

}



package com.kewill.kcx.component.mapping.formats.fss.suma.subsets.V70;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;


/**
 * Module		:	Manifest
 * Created		:	17.06.2013
 * Description	:   Positionssatz Bekanntgabe einer Maßnahme.
 *        			Zabis Version 70 
 *
 * @author iwaniuk
 * @version 7.0.00
 */

public class TsSSP  extends Teilsatz {

	private String beznr	= "";			
	private String regnr	= "";			
	private String posnr	= "";	
	private String kzawb	= "";	//Art des Spezifischen Ordnungsbegriffs
	private String awbzzz	= "";	//Spezifischer Ordnungsbegriff
	private String colart	= "";	// Art der Packstücke
	private String colanz	= "";	// Anzahl Packstücke	
	private String wabes	= "";	// Warenbeschreibung			
	private String vrweori	= "";   
	private String vrwnl	= "";	
	private String mcode	= "";	//Massnahmencode
	private String kzzus	= "";   //Kennzeichen Zustellbarkeit 
		
    public TsSSP() {
        tsTyp = "SSP";
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
        regnr = fields[2];
        if (size < 4) { return; }	
        posnr = fields[3];
        if (size < 5) { return; }	
        kzawb = fields[4];
        if (size < 6) { return; }	
        awbzzz = fields[5];
        if (size < 7) { return; }	
        colart = fields[6];
        if (size < 8) { return; }	        
        colanz = fields[7];
        if (size < 9) { return; }
        wabes = fields[8];
        if (size < 10) { return; }	
        vrweori = fields[9];
        if (size < 11) { return; }	        
        vrwnl = fields[10];
        if (size < 12) { return; }	
        mcode = fields[11];
        if (size < 13) { return; }	        
        kzzus = fields[12];
       
      }

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
        buff.append(kzawb);
        buff.append(trenner);
        buff.append(awbzzz);
        buff.append(trenner); 
        buff.append(colart);
        buff.append(trenner);
        buff.append(colanz);
        buff.append(trenner);
        buff.append(wabes);
        buff.append(trenner);
        buff.append(vrweori);
        buff.append(trenner);
        buff.append(vrwnl);
        buff.append(trenner);        
        buff.append(mcode);
        buff.append(trenner);
        buff.append(kzzus);
        buff.append(trenner);
       
        return new String(buff);
    }

	public boolean isEmpty() {
		return (Utils.isStringEmpty(regnr) && Utils.isStringEmpty(kzawb) &&
			Utils.isStringEmpty(awbzzz) && Utils.isStringEmpty(vrweori) &&
			Utils.isStringEmpty(colart) && Utils.isStringEmpty(colanz) &&
			Utils.isStringEmpty(mcode) && Utils.isStringEmpty(kzzus));
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

	public String getRegnr() {
		return regnr;
	}
	public void setRegnr(String idfltnum) {
		this.regnr = Utils.checkNull(idfltnum);
	}

	public String getPosnr() {
		return posnr;
	}
	public void setPosnr(String idfltblo) {
		this.posnr = Utils.checkNull(idfltblo);
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

	public String getWabes() {
		return wabes;
	}
	public void setWabes(String wabes) {
		this.wabes = Utils.checkNull(wabes);
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

	public String getMcode() {
		return mcode;
	}
	public void setMcode(String mcode) {
		this.mcode = Utils.checkNull(mcode);
	}

	public String getKzzus() {
		return kzzus;
	}
	public void setKzzus(String kzzus) {
		this.kzzus = Utils.checkNull(kzzus);
	}	
	
}



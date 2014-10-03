package com.kewill.kcx.component.mapping.formats.fss.suma.subsets.V70;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;


/**
 * Module		:	Manifest
 * Created		:	17.06.2013
 * Description	:   Positionssatz Verarbeitungsergebnisse.
 *        			Zabis Version 70 
 *
 * @author iwaniuk
 * @version 7.0.00
 */

public class TsSCP  extends Teilsatz {

	private String beznr	= "";			
	private String regnr	= "";			
	private String posnr	= "";	
	private String kzawb	= "";	//Art des Spezifischen Ordnungsbegriffs
	private String awbzzz	= "";	//Spezifischer Ordnungsbegriff
	//private String vrwknr	= "";	// Verwahrer
	private String vrweori	= "";   
	private String vrwnl	= "";	
	private String melgew	= "";	//Meldegewichtung: //auch "ERR" weil es hat mit Gewicht nichts zu tun, ist an3
	private String melpref	= "";   //Meldungspräfix // dann: "60122"
	private String melnr	= "";   //Meldungsnummer //und text: "Es konnte keine summarische Anmeldung angelegt werden, da..."
	private String fehtxt	= "";  	//Fehlertext //und text: "Es konnte keine summarische Anmeldung angelegt werden, da..."
		
    public TsSCP() {
        tsTyp = "SCP";
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
        vrweori = fields[6];
        if (size < 8) { return; }	        
        vrwnl = fields[7];
        if (size < 9) { return; }	
        melgew = fields[8];
        if (size < 10) { return; }	        
        melpref = fields[9];
        if (size < 11) { return; }	
        melnr = fields[10];
        if (size < 12) { return; }	
        fehtxt = fields[11];
       
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
        buff.append(vrweori);
        buff.append(trenner);
        buff.append(vrwnl);
        buff.append(trenner);        
        buff.append(melgew);
        buff.append(trenner);
        buff.append(melpref);
        buff.append(trenner);
        buff.append(melnr);
        buff.append(trenner);
        buff.append(fehtxt);
        buff.append(trenner);

        return new String(buff);
    }

	public boolean isEmpty() {
		return (Utils.isStringEmpty(regnr)  &&
			Utils.isStringEmpty(kzawb) && Utils.isStringEmpty(regnr) &&
			Utils.isStringEmpty(melgew) && Utils.isStringEmpty(melnr));
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

	public String getMelgew() {
		return melgew;
	}
	public void setMelgew(String melgew) {
		this.melgew = Utils.checkNull(melgew);
	}

	public String getMelnr() {
		return melnr;
	}
	public void setMelnr(String melnr) {
		this.melnr = Utils.checkNull(melnr);
	}

	public String getMelPref() {
		return melpref;
	}
	public void setMelPref(String prefix) {
		this.melpref = Utils.checkNull(prefix);
	}
	
	public String getFehtxt() {
		return fehtxt;
	}
	public void setFehtxt(String fehtxt) {
		this.fehtxt = Utils.checkNull(fehtxt);
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

	
}



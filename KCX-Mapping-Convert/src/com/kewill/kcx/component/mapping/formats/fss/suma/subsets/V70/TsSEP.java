package com.kewill.kcx.component.mapping.formats.fss.suma.subsets.V70;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		:	Manifest
 * Created		:	17.06.2013
 * Description	:   Positionssatz Mitteilung der Erledigung - NotificationOfSettlement. 
 *        			Zabis Version 70  
 *
 * @author iwaniuk
 * @version 7.0.00
 */

public class TsSEP extends Teilsatz {
	
	private String beznr;			//	Bezugsnummer
	private String regnr;			//	Registriernummer	
	private String posnr;			//	Positionsnummer	
	private String kzawb;			//	Art des spezifischen Ordnungsbegriffes
	private String awbzzz;			//	Spezifischer Ordnungsbegriff	
	private String vrweori;			//	Zollnummer des Verwahrers
	private String vrwnl;	
	private String stkerl;			//	Anzahl Packstücke	
	private String erldat;			
	private String erlsb;		
	private String kzstor;		// Kennzeichnung, dass durch die Zollverwaltung eine Erledigung storniert oder 
								// die Anzahl erledigter Packstücke reduziert wurde
	
	public TsSEP() {    
        tsTyp = "SEP";
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
		kzawb	= fields[4];
		if (size < 6) { return; }
		awbzzz	= fields[5];
		if (size < 7) { return; }
		vrweori	= fields[6];
		if (size < 8) { return; }
		vrwnl	= fields[7];
		if (size < 9) { return; }
		stkerl	= fields[8];
		if (size < 10) { return; }
		erldat	= fields[9];
		if (size < 11) { return; }
		erlsb	= fields[10];
		if (size < 12) { return; }
		kzstor	= fields[11];		
	}
	
	
	@Override
	public boolean isEmpty() {
		return (Utils.isStringEmpty(regnr)  && 			
			Utils.isStringEmpty(kzawb)  &&  Utils.isStringEmpty(awbzzz)  &&  
			Utils.isStringEmpty(vrweori) && Utils.isStringEmpty(vrwnl)  &&  
			Utils.isStringEmpty(stkerl)  &&  Utils.isStringEmpty(erldat)  &&  
			Utils.isStringEmpty(erlsb)  &&  Utils.isStringEmpty(kzstor));   	
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
		 	buff.append(kzawb);	
		 	buff.append(trenner);
		 	buff.append(awbzzz);	
		 	buff.append(trenner);
		 	buff.append(vrweori);
		 	buff.append(trenner);
		 	buff.append(vrwnl);
		 	buff.append(trenner);
		 	buff.append(stkerl);	
		 	buff.append(trenner);
		 	buff.append(erldat);	
		 	buff.append(trenner);
		 	buff.append(kzstor);	
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
	
	public String getStkerl() {
		return stkerl;
	}
	public void setStkerl(String stkerl) {
		this.stkerl = Utils.checkNull(stkerl);
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
	
	public String getErldat() {
		return erldat;
	}
	public void setErldat(String erldat) {
		this.erldat = Utils.checkNull(erldat);
	}

	public String getErlsb() {
		return erlsb;
	}
	public void setErlsb(String erlsb) {
		this.erlsb = Utils.checkNull(erlsb);
	}

	public String getKzstor() {
		return kzstor;
	}
	public void setKzstor(String kzstor) {
		this.kzstor = Utils.checkNull(kzstor);
	}
	
}

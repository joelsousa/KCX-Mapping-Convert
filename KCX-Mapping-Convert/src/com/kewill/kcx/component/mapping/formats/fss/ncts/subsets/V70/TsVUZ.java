package com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.V70;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;
/**
 * Module		: TsVUZ<br>
 * Created		: 05.02.2013<br>
 * Description	: Zusatzangaben Kopf.
 *  			: V70: new rohm
 * 
 * @author	: iwaniuk
 * @version	: 7.0.00
 *
 */
public class TsVUZ extends Teilsatz {
	private String mrn = "";			//MRN des Antrags
	private String anzve = "";			//festgestellte Anzahl Verschlüsse
	private String bfabkz = "";			//Festgestelltes Kennzeichen des Beförderungsmittels
	private String bfabld = "";			//Festgestellte Nationalität des Beförderungsmittels
	private String unstm = "";			//Freitext Unstimmigkeiten
	private String erlvm = "";			//Erläuterungen/Anmerkungen
	private String rohm = "";			//V70 new: Gesamtrohmasse
	
	public TsVUZ() {
		tsTyp	= "VUZ";
	}
	
	public String getMrn() {
		return mrn;
	}
	public void setMrn(String mrn) {
		this.mrn = Utils.checkNull(mrn);
	}

	public String getAnzve() {
		return anzve;
	}
	public void setAnzve(String anzve) {
		this.anzve = Utils.checkNull(anzve);
	}

	public String getBfabkz() {
		return bfabkz;
	}
	public void setBfabkz(String bfabkz) {
		this.bfabkz = Utils.checkNull(bfabkz);
	}

	public String getBfabld() {
		return bfabld;
	}
	public void setBfabld(String bfabld) {
		this.bfabld = Utils.checkNull(bfabld);
	}

	public String getUnstm() {
		return unstm;
	}
	public void setUnstm(String unstm) {
		this.unstm = Utils.checkNull(unstm);
	}

	public String getErlvm() {
		return erlvm;
	}
	public void setErlvm(String erlvm) {
		this.erlvm = Utils.checkNull(erlvm);
	}

	public String getRohm() {
		return rohm;
	}
	public void setRohm(String rohm) {
		this.rohm = Utils.checkNull(rohm);
	}
	
	public String teilsatzBilden() {
		StringBuffer buff = new StringBuffer();
		
		buff.append(tsTyp);
		buff.append(trenner);
		buff.append(mrn);
		buff.append(trenner);
		buff.append(anzve);
		buff.append(trenner);
		buff.append(bfabkz);
		buff.append(trenner);
		buff.append(bfabld);
		buff.append(trenner);
		buff.append(unstm);
		buff.append(trenner);
		buff.append(erlvm);
		buff.append(trenner); 
		buff.append(rohm);
		buff.append(trenner); 
		
		return buff.toString();
	}
	
	public void setFields(String[] fields) {
		int size	= fields.length;
		
		if (size < 1) {
			return;
		}
		tsTyp = fields[0];
		if (size < 2) {
			return;
		}
		mrn	= fields[1];
		if (size < 3) {
			return;
		}
		anzve = fields[2];
		if (size < 4) {
			return;
		}
		bfabkz	= fields[3];
		if (size < 5) {
			return;
		}
		bfabld = fields[4];
		if (size < 6) {
			return;
		}
		unstm = fields[5];
		if (size < 7) { return; }
		erlvm = fields[6]; 
		if (size < 8) { return; }
		rohm = fields[7];
	}
	
	public boolean isEmpty() {		
		return(Utils.isStringEmpty(anzve) && 
				Utils.isStringEmpty(bfabkz) &&
				Utils.isStringEmpty(bfabld) &&
				Utils.isStringEmpty(unstm) &&
				Utils.isStringEmpty(erlvm));
	}

}

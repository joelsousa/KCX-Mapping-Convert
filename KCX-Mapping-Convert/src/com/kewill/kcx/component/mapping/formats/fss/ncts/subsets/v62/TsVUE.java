package com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: TsVUE<br>
 * Created		: 09.06.2010<br>
 * Description	: VUE model class.
 * 
 * @author	: Jude Eco
 * @version	: 6.2.00
 *
 */
public class TsVUE extends Teilsatz {
	private String mrn = "";			//MRN des Antrags
	private String kzvorf = "";			//Kennzeichen Vorfall
	private String kzuml = "";			//Kennzeichen Umladung
	private String nbfld = "";			//neues Land des Fahrzeugs bei Umladung
	private String nbfkz = "";			//neues Kennzeichen des Fahrzeugs bei Umladung
	private String ergamt = "";			//bescheinigende Behörde des Ereignisses
	private String ergort = "";			//Ort des Ereignisses
	private String ergld = "";			//Land des Ereignisses
	private String ergdat = "";			//Datum des Ereignisses
	private String umlamt = "";			//bescheinigende Behörde der Umladung
	private String umlort = "";			//Ort der Umladung
	private String umlld = "";			//Land der Umladung
	private String umldat = "";			//Datum der Umladung
	private String vrfamt = "";			//bescheinigende Behörde des Vorfalls
	private String vrfort = "";			//Ort des Vorfalls
	private String vrfld = "";			//Land des Vorfalls
	private String vrfdat = "";			//Datum des Vorfalls
	private String vrfmsn = "";			//Beschreibung des Vorfalls
	
	public TsVUE() {
		tsTyp	= "VUE";
	}
	
	public String getMrn() {
		return mrn;
	}

	public void setMrn(String mrn) {
		this.mrn = Utils.checkNull(mrn);
	}

	public String getKzvorf() {
		return kzvorf;
	}

	public void setKzvorf(String kzvorf) {
		this.kzvorf = Utils.checkNull(kzvorf);
	}

	public String getKzuml() {
		return kzuml;
	}

	public void setKzuml(String kzuml) {
		this.kzuml = Utils.checkNull(kzuml);
	}

	public String getNbfld() {
		return nbfld;
	}

	public void setNbfld(String nbfld) {
		this.nbfld = Utils.checkNull(nbfld);
	}

	public String getNbfkz() {
		return nbfkz;
	}

	public void setNbfkz(String nbfkz) {
		this.nbfkz = Utils.checkNull(nbfkz);
	}

	public String getErgamt() {
		return ergamt;
	}

	public void setErgamt(String ergamt) {
		this.ergamt = Utils.checkNull(ergamt);
	}

	public String getErgort() {
		return ergort;
	}

	public void setErgort(String ergort) {
		this.ergort = Utils.checkNull(ergort);
	}

	public String getErgld() {
		return ergld;
	}

	public void setErgld(String ergld) {
		this.ergld = Utils.checkNull(ergld);
	}

	public String getErgdat() {
		return ergdat;
	}

	public void setErgdat(String ergdat) {
		this.ergdat = Utils.checkNull(ergdat);
	}

	public String getUmlamt() {
		return umlamt;
	}

	public void setUmlamt(String umlamt) {
		this.umlamt = Utils.checkNull(umlamt);
	}

	public String getUmlort() {
		return umlort;
	}

	public void setUmlort(String umlort) {
		this.umlort = Utils.checkNull(umlort);
	}

	public String getUmlld() {
		return umlld;
	}

	public void setUmlld(String umlld) {
		this.umlld = Utils.checkNull(umlld);
	}

	public String getUmldat() {
		return umldat;
	}

	public void setUmldat(String umldat) {
		this.umldat = Utils.checkNull(umldat);
	}

	public String getVrfamt() {
		return vrfamt;
	}

	public void setVrfamt(String vrfamt) {
		this.vrfamt = Utils.checkNull(vrfamt);
	}

	public String getVrfort() {
		return vrfort;
	}

	public void setVrfort(String vrfort) {
		this.vrfort = Utils.checkNull(vrfort);
	}

	public String getVrfld() {
		return vrfld;
	}

	public void setVrfld(String vrfld) {
		this.vrfld = Utils.checkNull(vrfld);
	}

	public String getVrfdat() {
		return vrfdat;
	}

	public void setVrfdat(String vrfdat) {
		this.vrfdat = Utils.checkNull(vrfdat);
	}

	public String getVrfmsn() {
		return vrfmsn;
	}

	public void setVrfmsn(String vrfmsn) {
		this.vrfmsn = Utils.checkNull(vrfmsn);
	}

	public String teilsatzBilden() {
		StringBuffer buff = new StringBuffer();
		
		buff.append(tsTyp);
		buff.append(trenner);
		buff.append(mrn);
		buff.append(trenner);
		buff.append(kzvorf);
		buff.append(trenner);
		buff.append(kzuml);
		buff.append(trenner);
		buff.append(nbfld);
		buff.append(trenner);
		buff.append(nbfkz);
		buff.append(trenner);
		buff.append(ergamt);
		buff.append(trenner);
		buff.append(ergort);
		buff.append(trenner);
		buff.append(ergld);
		buff.append(trenner);
		buff.append(ergdat);
		buff.append(trenner);
		buff.append(umlamt);
		buff.append(trenner);
		buff.append(umlort);
		buff.append(trenner);
		buff.append(umlld);
		buff.append(trenner);
		buff.append(umldat);
		buff.append(trenner);
		buff.append(vrfamt);
		buff.append(trenner);
		buff.append(vrfort);
		buff.append(trenner);
		buff.append(vrfld);
		buff.append(trenner);
		buff.append(vrfdat);
		buff.append(trenner);
		buff.append(vrfmsn);
		buff.append(trenner);
		
		return buff.toString();
	}
	
	public void setFields(String[] fields) {
		int size	= fields.length;
		
		if (size < 1) {
			return;
		}
		tsTyp	= fields[0];
		if (size < 2) {
			return;
		}
		mrn	= fields[1];
		if (size < 3) {
			return;
		}
		kzvorf = fields[2];
		if (size < 4) {
			return;
		}
		kzuml = fields[3];
		if (size < 5) {
			return;
		}
		nbfld = fields[4];
		if (size < 6) {
			return;
		}
		nbfkz = fields[5];
		if (size < 7) {
			return;
		}
		ergamt = fields[6];
		if (size < 8) {
			return;
		}
		ergort = fields[7];
		if (size < 9) {
			return;
		}
		ergld = fields[8];
		if (size < 10) {
			return;
		}
		ergdat = fields[9];
		if (size < 11) {
			return;
		}
		umlamt = fields[10];
		if (size < 12) {
			return;
		}
		umlort = fields[11];
		if (size < 13) {
			return;
		}
		umlld = fields[12];
		if (size < 14) {
			return;
		}
		umldat = fields[13];
		if (size < 15) {
			return;
		}
		vrfamt = fields[14];
		if (size < 16) {
			return;
		}
		vrfort = fields[15];
		if (size < 17) {
			return;
		}
		vrfld = fields[16];
		if (size < 18) {
			return;
		}
		vrfdat = fields[17];
		if (size < 19) {
			return;
		}
		vrfmsn = fields[18];
	}
	

	public boolean isEmpty() {		
		return (Utils.isStringEmpty(kzvorf) && 
				Utils.isStringEmpty(kzuml) && 
				Utils.isStringEmpty(nbfld) && 
				Utils.isStringEmpty(nbfkz) && 
				Utils.isStringEmpty(ergamt) && 
				Utils.isStringEmpty(ergort) && 
				Utils.isStringEmpty(ergld) && 
				Utils.isStringEmpty(ergdat) && 
				Utils.isStringEmpty(umlamt) && 
				Utils.isStringEmpty(umlort) && 
				Utils.isStringEmpty(umlld) && 
				Utils.isStringEmpty(umldat) && 
				Utils.isStringEmpty(vrfamt) && 
				Utils.isStringEmpty(vrfort) && 
				Utils.isStringEmpty(vrfld) && 
				Utils.isStringEmpty(vrfdat) && 
				Utils.isStringEmpty(vrfmsn));
	}
}

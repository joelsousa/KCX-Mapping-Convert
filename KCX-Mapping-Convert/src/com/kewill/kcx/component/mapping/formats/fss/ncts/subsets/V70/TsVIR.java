package com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.V70;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;
/**
 * Module		: TsVIR<br>
 * Created		: 2013.02.05<br>
 * Description	: VIR Ansprechpartner.
 * 				: V70 neu
 * 
 * @author iwaniuk                
 * @version 7.0.00
 *
 */
public class TsVIR extends Teilsatz {
	
	private String mrn	 	= "";	
	private String posnr 	= "";	//Positionsnummer
	private String typ	 	= "";	//Typ
	private String sbname	= "";	//Name des Sachbearbeiters
	private String stellg	= "";	//Dienststellung des Sachbearbeiters
	private String tel		= "";	//
	private String fax		= "";	//
	private String email	= "";	//
	
	public TsVIR() {
		tsTyp = "VIR";
	}
	
	public String teilsatzBilden() {
		StringBuffer buff = new StringBuffer();
		
		buff.append(tsTyp);
		buff.append(trenner);
		buff.append(mrn);
		buff.append(trenner);
		buff.append(posnr);
		buff.append(trenner);
		buff.append(typ);
		buff.append(trenner);
		buff.append(sbname);
		buff.append(trenner);
		buff.append(stellg);
		buff.append(trenner);
		buff.append(tel);
		buff.append(trenner);
		buff.append(fax);
		buff.append(trenner);
		buff.append(email);
		buff.append(trenner);
		
		return buff.toString();
	}
	
	public void setFields(String[] fields) {
		int size = fields.length;
		
		if (size < 1) { return; }
		tsTyp = fields[0];
		if (size < 2) { return; }
		mrn = fields[1];
		if (size < 3) { return; }
		posnr =  fields[2];
		if (size < 4) { return; }
		typ = fields[3];
		if (size < 5) { return; }
		sbname = fields[4];
		if (size < 6) { return; }
		stellg = fields[5];
		if (size < 7) { return; }
		tel = fields[6];
		if (size < 8) { return; }
		fax = fields[7];
		if (size < 9) { return; }
		email = fields[8];
	}

	
	
	public String getMrn() {
		return mrn;
	}
	public void setMrn(String mrn) {
		this.mrn = Utils.checkNull(mrn);
	}

	public String getPosnr() {
		return posnr;
	}
	public void setPosnr(String posnr) {
		this.posnr = Utils.checkNull(posnr);
	}

	public String getTyp() {
		return typ;
	}
	public void setTyp(String typ) {
		this.typ = Utils.checkNull(typ);
	}
	
	public String getSbname() {
		return sbname;
	}
	public void setSbname(String sbname) {
		this.sbname = Utils.checkNull(sbname);
	}

	public String getStellg() {
		return stellg;
	}
	public void setStellg(String sbstel) {
		this.stellg = Utils.checkNull(sbstel);
	}

	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = Utils.checkNull(tel);
	}

	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = Utils.checkNull(fax);
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = Utils.checkNull(email);
	}

	public boolean isEmpty() {
		return (Utils.isStringEmpty(mrn) && Utils.isStringEmpty(typ) && 
				Utils.isStringEmpty(sbname) && Utils.isStringEmpty(stellg) &&
				Utils.isStringEmpty(tel) && Utils.isStringEmpty(email));
	}
}

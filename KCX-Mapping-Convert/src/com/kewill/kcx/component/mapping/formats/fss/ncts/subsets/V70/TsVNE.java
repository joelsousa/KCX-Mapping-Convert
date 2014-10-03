package com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.V70;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;
/**
 * Module		: TsVNE<br>
 * Created		: 05.02.2013<br>
 * Description	: EORI-Wechsel.
 * 				: V70 new
 * 
 * @author	: iwaniuk
 * @version	: 7.0.00
 *
 */
public class TsVNE extends Teilsatz {
	
	private String mrn    = "";		//MRN des Antrags
	private String typbe  = "";		//Typ des Beteiligten
	private String kdnrem = "";		//Kundennummer des zugelassenen Empfängers
	private String eoriem = "";		//EORI des zugelassenen Empfängers
	private String nlem = "";		//Niederlassungsnummer des zugelassenen Empfängers
	
	public TsVNE() {
		tsTyp	= "VNE";
	}
	
	public String getMrn() {
		return mrn;
	}
	public void setMrn(String mrn) {
		this.mrn = Utils.checkNull(mrn);
	}
	
	public String getTypbe() {
		return typbe;
	}
	public void setTypbe(String type) {
		this.typbe = Utils.checkNull(type);
	}
	
	public String getKdnrem() {
		return kdnrem;
	}
	public void setKdnrem(String nr) {
		this.kdnrem = Utils.checkNull(nr);
	}
	
	public String getEoriem() {
		return eoriem;
	}
	public void setEoriem(String eori) {
		this.eoriem = Utils.checkNull(eori);
	}
	
	public String getNlem() {
		return nlem;
	}
	public void setNlem(String nl) {
		this.nlem = Utils.checkNull(nl);
	}

	public String teilsatzBilden() {
		StringBuffer buff = new StringBuffer();
		
		buff.append(tsTyp);
		buff.append(trenner);
		buff.append(mrn);
		buff.append(trenner);
		buff.append(typbe);
		buff.append(trenner);
		buff.append(kdnrem);
		buff.append(trenner);
		buff.append(eoriem);
		buff.append(trenner);
		buff.append(nlem);
		buff.append(trenner);
		
		return buff.toString();
	}
	
	public void setFields(String[] fields) {
		int size	= fields.length;
		
		if (size < 1) { return; }
		tsTyp = fields[0];
		if (size < 2) { return; }
		mrn	= fields[1];
		if (size < 3) { return; }
		typbe = fields[2];
		if (size < 4) { return; }
		kdnrem = fields[3];
		if (size < 5) { return; }
		eoriem = fields[4];
		if (size < 6) { return; }
		nlem = fields[5];
	}
	

	public boolean isEmpty() {
		return (Utils.isStringEmpty(mrn) && Utils.isStringEmpty(typbe) &&
				Utils.isStringEmpty(kdnrem) && Utils.isStringEmpty(eoriem));				
	}
}

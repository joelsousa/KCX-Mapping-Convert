package com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.V70;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.EFormat;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: NCTS<br>
 * Created		: 09.02.2013<br>
 * Description	: TsVIA.
 * 
 * @author iwaniuk
 * @version 7.0.00
 *
 */
public class TsVIA extends Teilsatz {
	private String mrn		= "";		//Movement-Reference-Number
	private String beznr	= "";		//Bezugsnummer
	private String wgdat	= "";		//Ankunftsdatum
	private String kzerg	= "";		//Kennzeichen Ereignisse
	private String quelkz	= "";		//Zabis-Vorverfahrens
	private String kdnr  	= "";		//Kundennummer des zugelassenen Empfänger
	private String eori 	= "";		//EORI des zugelassenen Empfänger
	private String nl   	= "";		//Niederlassungnummer des zugelassenen Empfänger
	private String idtype	= "";		//Identifikationsart zu EORI des zugelassenen Empfänger
	private String sb   	= "";		//Sachbearbeiterkennung

	private EFormat wgdatFormat;
	
	public TsVIA() {
		tsTyp = "VIA";
	}
	
	public String teilsatzBilden() {
		StringBuffer buff = new StringBuffer();
		
		buff.append(tsTyp);
		buff.append(trenner);
		buff.append(mrn);
		buff.append(trenner);
		buff.append(beznr);
		buff.append(trenner);
		buff.append(wgdat);
		buff.append(trenner);
		buff.append(kzerg);
		buff.append(trenner);
		buff.append(quelkz);
		buff.append(trenner);
		buff.append(kdnr);
		buff.append(trenner);
		buff.append(eori);
		buff.append(trenner);
		buff.append(nl);
		buff.append(trenner);
		buff.append(idtype);
		buff.append(trenner);
		buff.append(sb);
		buff.append(trenner);
		
		return buff.toString();
	}
	
	public void setFields(String[] fields) {
		int size = fields.length;
		
//		String ausgabe = "FSS: " + fields[0] + " size = " + size;
//		Utils.log(ausgabe);
		
		if (size < 1) { return; }
		tsTyp = fields[0];
		if (size < 2) { return; }
		mrn = fields[1];
		if (size < 3) { return; }
		beznr =  fields[2];
		if (size < 4) { return; }
		wgdat = fields[3];
		if (size < 5) { return; }
		kzerg = fields[4];
		if (size < 6) { return; }
		quelkz = fields[5];
		if (size < 7) { return; }
		kdnr = fields[6];
		if (size < 8) { return; }
		eori = fields[7];
		if (size < 9) { return; }
		nl = fields[8];
		if (size < 10) { return; }
		idtype = fields[9];
		if (size < 11) { return; }
		sb = fields[10];
	}

	public String getMrn() {
		return mrn;
	}
	public void setMrn(String mrn) {
		this.mrn = Utils.checkNull(mrn);
	}

	public String getBeznr() {
		return beznr;
	}
	public void setBeznr(String beznr) {
		this.beznr = Utils.checkNull(beznr);
	}

	public String getWgdat() {
		return wgdat;
	}
	public void setWgdat(String wgdat) {
		this.wgdat = Utils.checkNull(wgdat);
	}

	public String getKzerg() {
		return kzerg;
	}
	public void setKzerg(String kzerg) {
		this.kzerg = Utils.checkNull(kzerg);
	}

	public String getQuelkz() {
		return quelkz;
	}
	public void setQuelkz(String quelkz) {
		this.quelkz = Utils.checkNull(quelkz);
	}

	public String getKdnr() {
		return kdnr;
	}
	public void setKdnr(String kdnrze) {
		this.kdnr = Utils.checkNull(kdnrze);
	}

	public String getEori() {
		return eori;
	}
	public void setEori(String tin) {
		this.eori = Utils.checkNull(tin);
	}

	public String getNl() {
		return nl;
	}
	public void setNl(String nl) {
		this.nl = Utils.checkNull(nl);
	}
	
	public String getIdtype() {
		return idtype;
	}
	public void setIdtype(String type) {
		this.idtype = Utils.checkNull(type);
	}
	
	public String getSb() {
		return sb;
	}
	public void setSb(String sb) {
		this.sb = Utils.checkNull(sb);
	}
	public EFormat getWgdatFormat() {
		return wgdatFormat;
	}
	public void setWgdatFormat(EFormat wgdatFormat) {
		this.wgdatFormat = wgdatFormat;
	}
	
	public boolean isEmpty() {
		return (Utils.isStringEmpty(mrn) && Utils.isStringEmpty(beznr) &&
				Utils.isStringEmpty(wgdat) && Utils.isStringEmpty(kzerg) &&
				Utils.isStringEmpty(quelkz) && Utils.isStringEmpty(kdnr) &&
				Utils.isStringEmpty(eori));
	}
}

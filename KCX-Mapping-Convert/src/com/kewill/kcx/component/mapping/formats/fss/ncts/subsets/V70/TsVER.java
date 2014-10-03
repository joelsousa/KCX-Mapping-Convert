package com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.V70;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.formats.fss.utils.FssUtils;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		:	Manifest
 * Created		:	23.01.2014
 * Description	:   optionaller Teilsatz in VSO (speziell fuer LCAG)
 *        			Zabis Version 70  
 *
 * @author iwaniuk
 * @version 7.0.00
 */

public class TsVER extends Teilsatz {
		
	private String nl = "";			//	Niderelassung
	private String flugnr = "";		//	
	private String arrcode = "";	//ArrivalLocation
	private String wgdat = "";		//veoant_wgdat replaced "-" mit "" Gestellungsdatum
	private String andat = "";		//veoant_andat Datum der Annahme 
	private String uebdat = "";		//veoant_uebdat Datum der Überlassung 
	private String sponr = "";		//veoazv_sponr  Nummer des Spezifischen Ordnungsbegriffs bsp. AWB-Nummer
	private String status = "";		//veoant_stat  //EI20140306
	
	public TsVER() {    
        tsTyp = "VER";
    }
	
	public void setFields(String[] fields) {		
		int size = fields.length;
		
		if (size < 1) { return; }
		tsTyp	= fields[0];
		if (size < 2) { return; }
		nl	= fields[1];
		if (size < 3) { return; }
		flugnr	= fields[2];		
		if (size < 4) { return; }
		arrcode	= fields[3];
		if (size < 5) { return; }
		wgdat	= fields[4];	
		if (size < 6) { return; }
		andat	= fields[5];	
		if (size < 7) { return; }
		uebdat	= fields[6];	
		if (size < 8) { return; } 
		sponr	= fields[7];
		if (size < 9) { return; } 
		status	= fields[8];	
	}
	
	@Override
	public boolean isEmpty() {
		return Utils.isStringEmpty(flugnr) && Utils.isStringEmpty(nl);					   
	}

	@Override
	public String teilsatzBilden() {
		 StringBuffer buff = new StringBuffer();

		 	buff.append(tsTyp);
		 	buff.append(trenner);
		 	buff.append(nl);
		 	buff.append(trenner);
		 	buff.append(flugnr);
		 	buff.append(trenner);		 	
		 	buff.append(arrcode);	
		 	buff.append(trenner);		 	
		 	buff.append(wgdat);	
		 	buff.append(trenner);	
		 	buff.append(andat);	
		 	buff.append(trenner);	
		 	buff.append(uebdat);	
		 	buff.append(trenner);	
		 	buff.append(sponr);	
		 	buff.append(trenner);	
		 	buff.append(status);	
		 	buff.append(trenner);
		 	
		 	return buff.toString();
	}
	
	
	public String getNl() {
		return nl;
	}
	public void setNl(String nl) {
		this.nl = Utils.checkNull(nl);
	}
	
	public String getFlugnr() {
		return flugnr;
	}
	public void setFlugnr(String flugnr) {
		this.flugnr = Utils.checkNull(flugnr);
	}

	public String getArrcode() {
		return arrcode;
	}
	public void setArrcode(String value) {
		this.arrcode = Utils.checkNull(value);
	}
	
	public String getWgdat() {
		return wgdat;
	}
	public void setWgdat(String value) {
		this.wgdat = Utils.checkNull(value);
	}

	public String getAndat() {
		return andat;
	}
	public void setAndat(String value) {
		this.andat = Utils.checkNull(value);
	}

	public String getUebdat() {
		return uebdat;
	}
	public void setUebdat(String value) {
		this.uebdat = Utils.checkNull(value);
	}

	public String getSponr() {
		return sponr;
	}
	public void setSponr(String value) {
		this.sponr = Utils.checkNull(value);
	}

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
		 	 	
}

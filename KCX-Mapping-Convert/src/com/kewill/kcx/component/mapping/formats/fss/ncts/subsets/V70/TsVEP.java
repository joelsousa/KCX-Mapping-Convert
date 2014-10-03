package com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.V70;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.formats.fss.utils.FssUtils;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		:	Manifest
 * Created		:	17.03.2014
 * Description	:   optionaller Teilsatz in VSO (speziell fuer LCAG)
 *        			Zabis Version 70  
 *
 * @author iwaniuk
 * @version 7.0.00
 */


public class TsVEP extends Teilsatz {
		
	private String nl = "";			// Niderelassung
	private String flugnr = "";		//Flunnr	
	private String arrcode = "";	// ArrivalLocation
	private String posnr = "";		
	private String status = "";		
	private String awb = "";		
	
	
	public TsVEP() {    
        tsTyp = "VEP";
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
		posnr	= fields[4];
		if (size < 6) { return; }
		status	= fields[5];		
		if (size < 7) { return; }
		awb	= fields[6];
		
	}
	
	@Override
	public boolean isEmpty() {
		return Utils.isStringEmpty(awb) && Utils.isStringEmpty(status);					   
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
		 	buff.append(posnr);
		 	buff.append(trenner);
		 	buff.append(status);
		 	buff.append(trenner);		 	
		 	buff.append(awb);	
		 	buff.append(trenner);
		 	
		 	return buff.toString();
	}
	
	
	public String getNl() {
		return nl;
	}
	public void setNl(String nl) {
		this.nl = nl;
	}

	public String getFlugnr() {
		return flugnr;
	}
	public void setFlugnr(String flugnr) {
		this.flugnr = flugnr;
	}

	public String getArrcode() {
		return arrcode;
	}
	public void setArrcode(String arrcode) {
		this.arrcode = arrcode;
	}

	public String getPosnr() {
		return posnr;
	}
	public void setPosnr(String posnr) {
		this.posnr = Utils.checkNull(posnr);
	}
	
	public String getAwb() {
		return awb;
	}
	public void setAwb(String awb) {
		this.awb = Utils.checkNull(awb);
	}

	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
		 	 	
}

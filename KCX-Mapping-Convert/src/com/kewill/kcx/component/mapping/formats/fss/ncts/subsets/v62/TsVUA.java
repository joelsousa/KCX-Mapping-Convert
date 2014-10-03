package com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;
/**
 * Module		: TsVUA<br>
 * Created		: 09.07.2010<br>
 * Description	: VUA model class.
 * 
 * @author	: Jude Eco
 * @version	: 6.2.00
 *
 */
public class TsVUA extends Teilsatz {
	private String mrn = "";			//MRN des Antrags
	private String posnr = "";			//Positionsnummer der AWB im NCTS-IN
	private String awbzzz = "";			//AWB-Nummmer
	private String suapos = "";			//Positionsnummer der SumA
	
	public TsVUA() {
		tsTyp	= "VUA";
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

	public String getAwbzzz() {
		return awbzzz;
	}

	public void setAwbzzz(String awbzzz) {
		this.awbzzz = Utils.checkNull(awbzzz);
	}

	public String getSuapos() {
		return suapos;
	}

	public void setSuapos(String suapos) {
		this.suapos = Utils.checkNull(suapos);
	}

	public String teilsatzBilden() {
		StringBuffer buff = new StringBuffer();
		
		buff.append(tsTyp);
		buff.append(trenner);
		buff.append(mrn);
		buff.append(trenner);
		buff.append(posnr);
		buff.append(trenner);
		buff.append(awbzzz);
		buff.append(trenner);
		buff.append(suapos);
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
		posnr = fields[2];
		if (size < 4) {
			return;
		}
		awbzzz = fields[3];
		if (size < 5) {
			return;
		}
		suapos = fields[4];
	}
	

	public boolean isEmpty() {		
		return (Utils.isStringEmpty(posnr) &&
				Utils.isStringEmpty(awbzzz) &&
				Utils.isStringEmpty(suapos));
	}
}

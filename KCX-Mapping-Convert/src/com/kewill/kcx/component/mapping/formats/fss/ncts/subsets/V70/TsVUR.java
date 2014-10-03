package com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.V70;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: TsVUR<br>
 * Created		: 09.02.2013<br>
 * Description	: VUR model class.
 * 				: V70: new sb
 * 
 * @author	: Jude Eco
 * @version	: 7.0.00
 *
 */
public class TsVUR extends Teilsatz {
	private String mrn 	  = "";			//MRN des Antrags
	private String kzkonf = "";			//UnloadingRemark/Conform
	private String kzvsok = "";			//UnloadingRemark/StateOfSealsOK
	private String quelkz = "";			//V70 new: Quellkennzeichen       
	private String sb     = "";			//V70 new: Sachbearbeiterkennung
	
	public TsVUR() {
		tsTyp	= "VUR";
	}
	
	public String getMrn() {
		return mrn;
	}
	public void setMrn(String mrn) {
		this.mrn = Utils.checkNull(mrn);
	}

	public String getKzkonf() {
		return kzkonf;
	}
	public void setKzkonf(String kzkonf) {
		this.kzkonf = Utils.checkNull(kzkonf);
	}
	
	public String getQuelkz() {
		return quelkz;
	}
	public void setQuelkz(String quelkz) {
		this.quelkz = Utils.checkNull(quelkz);
	}
	
	public String getSb() {
		return sb;
	}
	public void setSb(String sb) {
		this.sb = Utils.checkNull(sb);
	}

	public String getKzvsok() {
		return kzvsok;
	}
	public void setKzvsok(String kzvsok) {
		this.kzvsok = Utils.checkNull(kzvsok);
	}

	
	public String teilsatzBilden() {
		StringBuffer buff = new StringBuffer();
		
		buff.append(tsTyp);
		buff.append(trenner);
		buff.append(mrn);
		buff.append(trenner);
		buff.append(kzkonf);
		buff.append(trenner);
		buff.append(kzvsok);
		buff.append(trenner);
		buff.append(quelkz);
		buff.append(trenner);
		buff.append(sb);
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
		kzkonf = fields[2];
		if (size < 4) { return; }
		kzvsok	= fields[3];
		if (size < 5) { return; }
		quelkz	= fields[4];
		if (size < 6) { return; }
		sb	= fields[5];
	}
	

	public boolean isEmpty() {		
		return (Utils.isStringEmpty(kzkonf) && Utils.isStringEmpty(kzvsok));
	}
}

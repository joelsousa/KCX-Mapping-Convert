package com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: TsVUR<br>
 * Created		: 09.06.2010<br>
 * Description	: VUR model class.
 * 
 * @author	: Jude Eco
 * @version	: 6.2.00
 *
 */
public class TsVUR extends Teilsatz {
	private String mrn = "";			//MRN des Antrags
	private String kzkonf = "";			//UnloadingRemark/Conform
	private String kzvsok = "";			//UnloadingRemark/StateOfSealsOK

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
		if (size < 4) {
			return;
		}
		kzvsok	= fields[3];

	}
	

	public boolean isEmpty() {		
		return (Utils.isStringEmpty(kzkonf) && Utils.isStringEmpty(kzvsok));
	}
}

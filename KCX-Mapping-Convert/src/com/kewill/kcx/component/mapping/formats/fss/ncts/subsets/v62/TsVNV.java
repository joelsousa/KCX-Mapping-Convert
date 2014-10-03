package com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;
/**
 * Module		: TsVNV<br>
 * Created		: 09.07.2010<br>
 * Description	: VNV model class.
 * 
 * @author	: Jude Eco
 * @version	: 6.2.00
 *
 */
public class TsVNV extends Teilsatz {
	private String mrn = "";		//MRN des Antrags
	private String seal = "";		//Verschlusszeichen
	
	public TsVNV() {
		tsTyp	= "VNV";
	}
	
	public String getMrn() {
		return mrn;
	}

	public void setMrn(String mrn) {
		this.mrn = Utils.checkNull(mrn);
	}
	
	public String getSeal() {
		return seal;
	}

	public void setSeal(String seal) {
		this.seal = Utils.checkNull(seal);
	}

	public String teilsatzBilden() {
		StringBuffer buff = new StringBuffer();
		
		buff.append(tsTyp);
		buff.append(trenner);
		buff.append(mrn);
		buff.append(trenner);
		buff.append(seal);
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
		seal = fields[2];
	}
	

	public boolean isEmpty() {
		return (Utils.isStringEmpty(seal));				
	}
}

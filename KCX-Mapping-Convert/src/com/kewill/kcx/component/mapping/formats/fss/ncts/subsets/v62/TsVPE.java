package com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: TsVPE<br>
 * Created		: 2010.09.03<br>
 * Description	: VPE model class.
 * 
 * @author Frederick T
 * @version 6.2.00
 *
 */
public class TsVPE extends Teilsatz {
	private String beznr		= "";
	private String posnr		= "";
	private String code			= "";
	private String menge		= "";
	
	public TsVPE() {
		tsTyp = "VPE";
	}
	
	public String teilsatzBilden() {
		StringBuffer buff = new StringBuffer();
		
		buff.append(tsTyp);
		buff.append(trenner);
		buff.append(beznr);
		buff.append(trenner);
		buff.append(posnr);
		buff.append(trenner);
		buff.append(code);
		buff.append(trenner);
		buff.append(menge);
		buff.append(trenner);

		return buff.toString();
	}
	
	public void setFields(String[] fields) {
		int size = fields.length;
		
//		String ausgabe = "FSS: " + fields[0] + " size = " + size;
//		Utils.log(ausgabe);
		
		if (size < 1) {
			return;
		}
		tsTyp = fields[0];
		if (size < 2) {
			return;
		}
		beznr = fields[1];
		if (size < 3) {
			return;
		}
		posnr =  fields[2];
		if (size < 4) {
			return;
		}
		code = fields[3];
		if (size < 5) {
			return;
		}
		menge = fields[4];
	}

	public String getBeznr() {
		return beznr;
	}

	public void setBeznr(String beznr) {
		this.beznr = Utils.checkNull(beznr);
	}

	public String getPosnr() {
		return posnr;
	}

	public void setPosnr(String posnr) {
		this.posnr = Utils.checkNull(posnr);
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = Utils.checkNull(code);
	}

	public String getMenge() {
		return menge;
	}

	public void setMenge(String menge) {
		this.menge = Utils.checkNull(menge);
	}
	
	public boolean isEmpty() {
		return (Utils.isStringEmpty(code) && Utils.isStringEmpty(menge));				
	}
}

package com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: TsVPU<br>
 * Created		: 2010.09.03<br>
 * Description	: VPU model class.
 * 
 * @author Frederick T
 * @version 6.2.00
 *
 */
public class TsVPU extends Teilsatz {
	private String posnr		= "";
	private String untart		= "";
	private String untref		= "";
	private String untzus		= "";
	
	public TsVPU() {
		tsTyp = "VPU";
	}
	
	public String teilsatzBilden() {
		StringBuffer buff = new StringBuffer();
		
		buff.append(tsTyp);
		buff.append(trenner);
		buff.append(posnr);
		buff.append(trenner);
		buff.append(untart);
		buff.append(trenner);
		buff.append(untref);
		buff.append(trenner);
		buff.append(untzus);
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
		posnr = fields[1];
		if (size < 3) {
			return;
		}
		untart =  fields[2];
		if (size < 4) {
			return;
		}
		untref = fields[3];
		if (size < 5) {
			return;
		}
		untzus = fields[4];
	}

	public String getPosnr() {
		return posnr;
	}

	public void setPosnr(String posnr) {
		this.posnr = Utils.checkNull(posnr);
	}

	public String getUntart() {
		return untart;
	}

	public void setUntart(String untart) {
		this.untart = Utils.checkNull(untart);
	}

	public String getUntref() {
		return untref;
	}

	public void setUntref(String untref) {
		this.untref = Utils.checkNull(untref);
	}

	public String getUntzus() {
		return untzus;
	}

	public void setUntzus(String untzus) {
		this.untzus = Utils.checkNull(untzus);
	}
	
	public boolean isEmpty() {
		return (Utils.isStringEmpty(untart) &&
				Utils.isStringEmpty(untref) && Utils.isStringEmpty(untzus));
	}
}

package com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;
/**
 * Module		: TsVIP<br>
 * Created		: 2010.09.02<br>
 * Description	: VIP model class.
 * 
 * @author Frederick T
 * @version 6.2.00
 *
 */
public class TsVIP extends Teilsatz {
	private String beznr	= "";	//Bezugsnummer 
	private String awbzzz	= "";	//AWB-Nummmer,
	private String suapos	= "";	//Positionsnummer
	
	public TsVIP() {
		tsTyp = "VIP";
	}
	
	public String teilsatzBilden() {
		StringBuffer buff = new StringBuffer();
		
		buff.append(tsTyp);
		buff.append(trenner);
		buff.append(beznr);
		buff.append(trenner);
		buff.append(awbzzz);
		buff.append(trenner);
		buff.append(suapos);
		buff.append(trenner);
		
		return buff.toString();
	}
	
	public void setFields(String[] fields) {
		int size = fields.length;
		
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
		awbzzz =  fields[2];
		if (size < 4) {
			return;
		}
		suapos = fields[3];
	}

	public String getBeznr() {
		return beznr;
	}

	public void setBeznr(String beznr) {
		this.beznr = Utils.checkNull(beznr);
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
	
	public boolean isEmpty() {
		return (Utils.isStringEmpty(beznr) && Utils.isStringEmpty(awbzzz) && 
				Utils.isStringEmpty(suapos));
	}
}

package com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;
/**
 * Module		: TsVIS<br>
 * Created		: 2010.09.02<br>
 * Description	: VIS model class.
 * 
 * @author Frederick T
 * @version 6.2.00
 *
 */
public class TsVIS extends Teilsatz {
	private String beznr	= "";	//Bezugsnummer 
	private String suabez	= "";	//Bezugsnummer 
	private String fltnum	= "";	//Flugnummer 
	
	public TsVIS() {
		tsTyp = "VIS";
	}
	
	public String teilsatzBilden() {
		StringBuffer buff = new StringBuffer();
		
		buff.append(tsTyp);
		buff.append(trenner);
		buff.append(beznr);
		buff.append(trenner);
		buff.append(suabez);
		buff.append(trenner);
		buff.append(fltnum);
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
		suabez =  fields[2];
		if (size < 4) {
			return;
		}
		fltnum = fields[3];
	}

	public String getBeznr() {
		return beznr;
	}

	public void setBeznr(String beznr) {
		this.beznr = Utils.checkNull(beznr);
	}

	public String getSuabez() {
		return suabez;
	}

	public void setSuabez(String suabez) {
		this.suabez = Utils.checkNull(suabez);
	}

	public String getFltnum() {
		return fltnum;
	}

	public void setFltnum(String fltnum) {
		this.fltnum = Utils.checkNull(fltnum);
	}
	
	// dont write the subset VIS if beznr is filled but fltnum and suabez empty
	// this causes an error in ZABIS C.K.20.5.2011
	public boolean isEmpty() {
		return (Utils.isStringEmpty(suabez) && Utils.isStringEmpty(fltnum));
	}
}

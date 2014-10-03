package com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;
/**
 * Module		: TsVIB<br>
 * Created		: 2010.09.02<br>
 * Description	: VIB model class.
 * 
 * @author Frederick T
 * @version 6.2.00
 *
 */
public class TsVIB extends Teilsatz {
	private String beznr	= "";	//Bezugsnummer
	private String bewnr	= "";	//Bewilligungsnummer 
	private String ubgort	= "";	//Entladeortcode 
	private String bedst	= "";	//Dienststelle 
	
	public TsVIB() {
		tsTyp = "VIB";
	}
	
	public String teilsatzBilden() {
		StringBuffer buff = new StringBuffer();
		
		buff.append(tsTyp);
		buff.append(trenner);
		buff.append(beznr);
		buff.append(trenner);
		buff.append(bewnr);
		buff.append(trenner);
		buff.append(ubgort);
		buff.append(trenner);
		buff.append(bedst);
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
		bewnr =  fields[2];
		if (size < 4) {
			return;
		}
		ubgort = fields[3];
		if (size < 5) {
			return;
		}
		bedst = fields[4];
	}

	public String getBeznr() {
		return beznr;
	}

	public void setBeznr(String beznr) {
		this.beznr = Utils.checkNull(beznr);
	}

	public String getBewnr() {
		return bewnr;
	}

	public void setBewnr(String bewnr) {
		this.bewnr = Utils.checkNull(bewnr);
	}

	public String getUbgort() {
		return ubgort;
	}

	public void setUbgort(String ubgort) {
		this.ubgort = Utils.checkNull(ubgort);
	}

	public String getBedst() {
		return bedst;
	}

	public void setBedst(String bedst) {
		this.bedst = Utils.checkNull(bedst);
	}
	
	public boolean isEmpty() {
		return (Utils.isStringEmpty(bewnr) && 
				Utils.isStringEmpty(ubgort) && Utils.isStringEmpty(bedst));
	}
}

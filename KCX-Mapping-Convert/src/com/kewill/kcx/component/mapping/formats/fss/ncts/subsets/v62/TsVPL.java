package com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: TsVPL<br>
 * Created		: 2010.09.03<br>
 * Description	: VPL model class.
 * 
 * @author Frederick T
 * @version 6.2.00
 *
 */
public class TsVPL extends Teilsatz {
	private String posnr		= "";
	private String colanz		= "";
	private String colart		= "";
	private String collz		= "";
	
	public TsVPL() {
		tsTyp = "VPL";
	}
	
	public String teilsatzBilden() {
		StringBuffer buff = new StringBuffer();
		
		buff.append(tsTyp);
		buff.append(trenner);
		buff.append(posnr);
		buff.append(trenner);
		buff.append(colanz);
		buff.append(trenner);
		buff.append(colart);
		buff.append(trenner);
		buff.append(collz);
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
		colanz =  fields[2];
		if (size < 4) {
			return;
		}
		colart = fields[3];
		if (size < 5) {
			return;
		}
		collz = fields[4];
	}

	public String getPosnr() {
		return posnr;
	}

	public void setPosnr(String posnr) {
		this.posnr = Utils.checkNull(posnr);
	}

	public String getColanz() {
		return colanz;
	}

	public void setColanz(String colanz) {
		this.colanz = Utils.checkNull(colanz);
	}

	public String getColart() {
		return colart;
	}

	public void setColart(String colart) {
		this.colart = Utils.checkNull(colart);
	}

	public String getCollz() {
		return collz;
	}

	public void setCollz(String collz) {
		this.collz = Utils.checkNull(collz);
	}
	
	public boolean isEmpty() {
		return (Utils.isStringEmpty(colanz) &&
				Utils.isStringEmpty(colart) && Utils.isStringEmpty(collz));
	}
}

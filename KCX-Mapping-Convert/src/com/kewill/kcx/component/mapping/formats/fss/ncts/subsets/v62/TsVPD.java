package com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: TsVPD<br>
 * Created		: 2010.09.03<br>
 * Description	: VPD model class.
 * 
 * @author Frederick T
 * @version 6.2.00
 *
 */
public class TsVPD extends Teilsatz {
	private String posnr	= "";
	private String typ		= "";
	private String name		= "";
	private String str		= "";
	private String ort		= "";
	private String plz		= "";
	private String land		= "";
	
	public TsVPD() {
		tsTyp = "VPD";
	}
	
	public String teilsatzBilden() {
		StringBuffer buff = new StringBuffer();
		
		buff.append(tsTyp);
		buff.append(trenner);
		buff.append(posnr);
		buff.append(trenner);
		buff.append(typ);
		buff.append(trenner);
		buff.append(name);
		buff.append(trenner);
		buff.append(str);
		buff.append(trenner);
		buff.append(ort);
		buff.append(trenner);
		buff.append(plz);
		buff.append(trenner);
		buff.append(land);
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
		typ =  fields[2];
		if (size < 4) {
			return;
		}
		name = fields[3];
		if (size < 5) {
			return;
		}
		str = fields[4];
		if (size < 6) {
			return;
		}
		ort = fields[5];
		if (size < 7) {
			return;
		}
		plz = fields[6];
		if (size < 8) {
			return;
		}
		land = fields[7];
	}

	public String getPosnr() {
		return posnr;
	}

	public void setPosnr(String posnr) {
		this.posnr = Utils.checkNull(posnr);
	}

	public String getTyp() {
		return typ;
	}

	public void setTyp(String typ) {
		this.typ = Utils.checkNull(typ);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = Utils.checkNull(name);
	}

	public String getStr() {
		return str;
	}

	public void setStr(String str) {
		this.str = Utils.checkNull(str);
	}

	public String getOrt() {
		return ort;
	}

	public void setOrt(String ort) {
		this.ort = Utils.checkNull(ort);
	}

	public String getPlz() {
		return plz;
	}

	public void setPlz(String plz) {
		this.plz = Utils.checkNull(plz);
	}
	
	public String getLand() {
		return land;
	}

	public void setLand(String land) {
		this.land = Utils.checkNull(land);
	}

	public boolean isEmpty() {
		return (Utils.isStringEmpty(name) && Utils.isStringEmpty(str) &&
				Utils.isStringEmpty(ort) && Utils.isStringEmpty(plz) &&
				Utils.isStringEmpty(ort) && Utils.isStringEmpty(typ));				
	}
}

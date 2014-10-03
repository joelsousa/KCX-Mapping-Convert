package com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;
/**
 * Module		: TsVKA<br>
 * Created		: 2011.05.12<br>
 * Description	: VPA model class.
 * 
 * @author iwaniuk
 * @version 1.0.00
 *
 */
public class TsVKA extends Teilsatz {
		
	private String beznr  = "";
	private String posnr  = "";		
	private String adrtyp = "";	
	private String name	  = "";
	private String str	  = "";
	private String ort	  = "";
	private String plz	  = "";
	private String land	  = "";
	
	public TsVKA() {
		tsTyp = "VKA";
	}
	
	public String teilsatzBilden() {
		StringBuffer buff = new StringBuffer();
		
		buff.append(tsTyp);
		buff.append(trenner);
		buff.append(beznr);
		buff.append(trenner);
		buff.append(posnr);		
		buff.append(trenner);
		buff.append(adrtyp);
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
		beznr = fields[1];		
		if (size < 3) {
			return;
		}		
		posnr =  fields[2];		
		if (size < 4) {
			return;
		}		
		adrtyp = fields[3];
		if (size < 5) {
			return;
		}		
		name = fields[4];
		if (size < 6) {
			return;
		}		
		str = fields[5];		
		if (size < 7) {
			return;
		}		
		ort = fields[6];
		if (size < 8) {
			return;
		}		
		plz = fields[7];
		if (size < 9) {
			return;
		}		
		land = fields[8];
		
	}

	public String getBeznr() {
		return beznr;
	}
	public void setBeznr(String argument) {
		this.beznr = Utils.checkNull(argument);
	}
	
	public String getPosnr() {
		return posnr;
	}
	public void setPosnr(String argument) {
		this.posnr = Utils.checkNull(argument);
	}	
	public String getAdrtyp() {
		return adrtyp;
	}
	public void setAdrtyp(String typ) {
		this.adrtyp = Utils.checkNull(typ);
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
		return (Utils.isStringEmpty(adrtyp) && Utils.isStringEmpty(name) &&
				Utils.isStringEmpty(str) && Utils.isStringEmpty(ort) &&
				Utils.isStringEmpty(plz) && Utils.isStringEmpty(land));
	}
}

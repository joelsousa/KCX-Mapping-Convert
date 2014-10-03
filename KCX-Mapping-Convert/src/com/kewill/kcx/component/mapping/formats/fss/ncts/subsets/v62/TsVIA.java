package com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.EFormat;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: TsVIA<br>
 * Created		: 2010.09.02<br>
 * Description	: VIA model class.
 * 
 * @author Frederick T
 * @version 6.2.00
 *
 */
public class TsVIA extends Teilsatz {
	private String mrn		= "";		//Movement-Reference-Number
	private String beznr	= "";		//Bezugsnummer
	private String wgdat	= "";		//Ankunftsdatum
	private String kzerg	= "";		//Kennzeichen Ereignisse
	private String quelkz	= "";		//Zabis-Vorverfahrens
	private String kdnrze	= "";		//TIN des zugelassenen Empfänger
	private String tinze	= "";		//TIN des zugelassenen Empfänger Derzeit

	private EFormat wgdatFormat;
	
	public TsVIA() {
		tsTyp = "VIA";
	}
	
	public String teilsatzBilden() {
		StringBuffer buff = new StringBuffer();
		
		buff.append(tsTyp);
		buff.append(trenner);
		buff.append(mrn);
		buff.append(trenner);
		buff.append(beznr);
		buff.append(trenner);
		buff.append(wgdat);
		buff.append(trenner);
		buff.append(kzerg);
		buff.append(trenner);
		buff.append(quelkz);
		buff.append(trenner);
		buff.append(kdnrze);
		buff.append(trenner);
		buff.append(tinze);
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
		mrn = fields[1];
		if (size < 3) {
			return;
		}
		beznr =  fields[2];
		if (size < 4) {
			return;
		}
		wgdat = fields[3];
		if (size < 5) {
			return;
		}
		kzerg = fields[4];
		if (size < 6) {
			return;
		}
		quelkz = fields[5];
		if (size < 7) {
			return;
		}
		kdnrze = fields[6];
		if (size < 8) {
			return;
		}
		tinze = fields[7];
	}

	public String getMrn() {
		return mrn;
	}

	public void setMrn(String mrn) {
		this.mrn = Utils.checkNull(mrn);
	}

	public String getBeznr() {
		return beznr;
	}

	public void setBeznr(String beznr) {
		this.beznr = Utils.checkNull(beznr);
	}

	public String getWgdat() {
		return wgdat;
	}

	public void setWgdat(String wgdat) {
		this.wgdat = Utils.checkNull(wgdat);
	}

	public String getKzerg() {
		return kzerg;
	}

	public void setKzerg(String kzerg) {
		this.kzerg = Utils.checkNull(kzerg);
	}

	public String getQuelkz() {
		return quelkz;
	}

	public void setQuelkz(String quelkz) {
		this.quelkz = Utils.checkNull(quelkz);
	}

	public String getKdnrze() {
		return kdnrze;
	}

	public void setKdnrze(String kdnrze) {
		this.kdnrze = Utils.checkNull(kdnrze);
	}

	public String getTinze() {
		return tinze;
	}

	public void setTinze(String tinze) {
		this.tinze = Utils.checkNull(tinze);
	}

	public EFormat getWgdatFormat() {
		return wgdatFormat;
	}

	public void setWgdatFormat(EFormat wgdatFormat) {
		this.wgdatFormat = wgdatFormat;
	}
	
	public boolean isEmpty() {
		return (Utils.isStringEmpty(mrn) && Utils.isStringEmpty(beznr) &&
				Utils.isStringEmpty(wgdat) && Utils.isStringEmpty(kzerg) &&
				Utils.isStringEmpty(quelkz) && Utils.isStringEmpty(kdnrze) &&
				Utils.isStringEmpty(tinze));
	}
}

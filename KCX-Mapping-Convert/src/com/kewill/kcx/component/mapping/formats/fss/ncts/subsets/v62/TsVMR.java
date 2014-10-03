package com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module : TsVMR<br>
 * Created : 2010.11.03<br>
 * Description : VMR model class.
 * 
 * @author Lassiter Caviles
 * @version 6.2.00
 * 
 */
public class TsVMR extends Teilsatz {
	private String beznr 	= ""; // Bezugsnummer
	private String arbnr 	= ""; // Arbeitsnummer
	private String mrn 		= ""; // MRN
	private String zlbez 	= ""; // Bezugsnummer der ZL-Auslagerung
	private String avbez 	= ""; // Bezugsnummer der AV/UV-Auslagerung

	public TsVMR() {
		tsTyp = "VMR";
	}

	public String teilsatzBilden() {
		StringBuffer buff = new StringBuffer();
		
		buff.append(tsTyp);
		buff.append(trenner);
		buff.append(beznr);
		buff.append(trenner);
		buff.append(arbnr);
		buff.append(trenner);
		buff.append(mrn);
		buff.append(trenner);
		buff.append(zlbez);
		buff.append(trenner);
		buff.append(avbez);
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
		arbnr =  fields[2];
		if (size < 4) {
			return;
		}
		mrn =  fields[3];
		if (size < 5) {
			return;
		}

		zlbez = fields[4];
		if (size < 6) {
			return;
		}
		avbez = fields[5];
		if (size < 7) {
			return;
		}

	}

	public String getBeznr() {
		return beznr;
	}

	public void setBeznr(String beznr) {
		this.beznr = Utils.checkNull(beznr);
	}

	public String getArbnr() {
		return arbnr;
	}

	public void setArbnr(String arbnr) {
		this.arbnr = Utils.checkNull(arbnr);
	}

	public String getMrn() {
		return mrn;
	}

	public void setMrn(String mrn) {
		this.mrn = Utils.checkNull(mrn);
	}

	public String getZlbez() {
		return zlbez;
	}

	public void setZlbez(String zlbez) {
		this.zlbez = Utils.checkNull(zlbez);
	}

	public String getAvbez() {
		return avbez;
	}

	public void setAvbez(String avbez) {
		this.avbez = Utils.checkNull(avbez);
	}

	public boolean isEmpty() {		
		return (Utils.isStringEmpty(arbnr) && 
			    Utils.isStringEmpty(mrn) && 
			    Utils.isStringEmpty(zlbez) && 
			    Utils.isStringEmpty(avbez));
	}

}

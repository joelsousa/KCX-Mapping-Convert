package com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;
/**
 * Module : TsVMV<br>
 * Created : 2010.11.03<br>
 * Description : VMV model class.
 * 
 * @author Lassiter Caviles
 * @version 6.2.00
 * 
 */
public class TsVMV extends Teilsatz {
	private String	beznr = ""; //Bezugsnummer 
	private String	mrn	=	""; //MRN 
	private String	tyd = 	"";	//Vergebene 
	
	public TsVMV() {
		tsTyp = "VMV";
	}
	
	public String teilsatzBilden() {
		StringBuffer buff = new StringBuffer();

		buff.append(tsTyp);
		buff.append(trenner);
		buff.append(beznr);
		buff.append(trenner);
		buff.append(mrn);
		buff.append(trenner);
		buff.append(tyd);
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
		mrn = fields[2];
		if (size < 4) {
			return;
		}
		tyd = fields[3];
		if (size < 5) {
			return;
		}
	}

	public String getBeznr() {
		return beznr;
	}

	public void setBeznr(String beznr) {
		this.beznr = Utils.checkNull(beznr);
	}

	public String getMrn() {
		return mrn;
	}

	public void setMrn(String mrn) {
		this.mrn = Utils.checkNull(mrn);
	}

	public String getTyd() {
		return tyd;
	}

	public void setTyd(String tyd) {
		this.tyd = Utils.checkNull(tyd);
	}
	
	public boolean isEmpty() {
		return (Utils.isStringEmpty(mrn) && Utils.isStringEmpty(tyd));				
	}
}

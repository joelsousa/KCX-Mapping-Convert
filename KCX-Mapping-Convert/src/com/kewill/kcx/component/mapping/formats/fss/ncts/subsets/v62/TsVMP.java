package com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module : TsVMP<br>
 * Created : 2010.11.03<br>
 * Description : VMP model class.
 * 
 * @author Lassiter Caviles
 * @version 6.2.00
 * 
 */
public class TsVMP extends Teilsatz {
	private String beznr = ""; // Bezugsnummer des Antrags
	private String mrn = ""; // MRN
	private String pdfnam = ""; // Dateiname
	private String pdfpfd = ""; // Pfad
	private String subdir = ""; // Unterverzeichnis

	public TsVMP() {
		tsTyp = "VMP";
	}

	public String teilsatzBilden() {
		StringBuffer buff = new StringBuffer();

		buff.append(tsTyp);
		buff.append(trenner);
		buff.append(beznr);
		buff.append(trenner);
		buff.append(mrn);
		buff.append(trenner);
		buff.append(pdfnam);
		buff.append(trenner);
		buff.append(pdfpfd);
		buff.append(trenner);
		buff.append(subdir);
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
		pdfnam = fields[3];
		if (size < 5) {
			return;
		}
		pdfpfd = fields[4];
		if (size < 6) {
			return;
		}
		subdir = fields[5];

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

	public String getPdfnam() {
		return pdfnam;
	}

	public void setPdfnam(String pdfnam) {
		this.pdfnam = Utils.checkNull(pdfnam);
	}

	public String getPdfpfd() {
		return pdfpfd;
	}

	public void setPdfpfd(String pdfpfd) {
		this.pdfpfd = Utils.checkNull(pdfpfd);
	}

	public String getSubdir() {
		return subdir;
	}

	public void setSubdir(String subdir) {
		this.subdir = Utils.checkNull(subdir);
	}

	public boolean isEmpty() {	
		return (Utils.isStringEmpty(pdfnam) && 
				Utils.isStringEmpty(mrn) && 
				Utils.isStringEmpty(pdfpfd) && 
				Utils.isStringEmpty(subdir));
	}
}

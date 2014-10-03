package com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: TsVPP<br>
 * Created		: 2010.09.03<br>
 * Description	: VPP model class.
 * 
 * @author Frederick T
 * @version 6.2.00
 *
 */
public class TsVPP extends Teilsatz {
	private String beznr		= "";
	private String posnr		= "";
	private String tnr			= "";
	private String wabsch		= "";
	private String unstm		= "";
	private String rohmas		= "";
	private String eigmas		= "";
	private String pfehlt		= "";
	private String awbzzz		= "";
	private String suapos		= "";
	private String kzexeu		= "";
	private String ldexp		= "";
	private String kzvub		= "";
	private String kzabg		= "";
	private String kzexp		= "";
	
	public TsVPP() {
		tsTyp = "VPP";
	}
	
	public String teilsatzBilden() {
		StringBuffer buff = new StringBuffer();
		
		buff.append(tsTyp);
		buff.append(trenner);
		buff.append(beznr);
		buff.append(trenner);
		buff.append(posnr);
		buff.append(trenner);
		buff.append(tnr);
		buff.append(trenner);
		buff.append(wabsch);
		buff.append(trenner);
		buff.append(unstm);
		buff.append(trenner);
		buff.append(rohmas);
		buff.append(trenner);
		buff.append(eigmas);
		buff.append(trenner);
		buff.append(pfehlt);
		buff.append(trenner);
		buff.append(awbzzz);
		buff.append(trenner);
		buff.append(suapos);
		buff.append(trenner);
		buff.append(kzexeu);
		buff.append(trenner);
		buff.append(ldexp);
		buff.append(trenner);
		buff.append(kzvub);
		buff.append(trenner);
		buff.append(kzabg);
		buff.append(trenner);
		buff.append(kzexp);
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
		tnr = fields[3];
		if (size < 5) {
			return;
		}
		wabsch = fields[4];
		if (size < 6) {
			return;
		}
		unstm = fields[5];
		if (size < 7) {
			return;
		}
		rohmas = fields[6];
		if (size < 8) {
			return;
		}
		eigmas = fields[7];
		if (size < 9) {
			return;
		}
		pfehlt = fields[8];
		if (size < 10) {
			return;
		}
		awbzzz = fields[9];
		if (size < 11) {
			return;
		}
		suapos = fields[10];
		if (size < 12) {
			return;
		}
		kzexeu = fields[11];
		if (size < 13) {
			return;
		}
		ldexp = fields[12];
		if (size < 14) {
			return;
		}
		kzvub = fields[13];
		if (size < 15) {
			return;
		}
		kzabg = fields[14];
		if (size < 16) {
			return;
		}
		kzexp = fields[15];
	}

	public String getBeznr() {
		return beznr;
	}

	public void setBeznr(String beznr) {
		this.beznr = Utils.checkNull(beznr);
	}

	public String getPosnr() {
		return posnr;
	}

	public void setPosnr(String posnr) {
		this.posnr = Utils.checkNull(posnr);
	}

	public String getTnr() {
		return tnr;
	}

	public void setTnr(String tnr) {
		this.tnr = Utils.checkNull(tnr);
	}

	public String getWabsch() {
		return wabsch;
	}

	public void setWabsch(String wabsch) {
		this.wabsch = Utils.checkNull(wabsch);
	}

	public String getUnstm() {
		return unstm;
	}

	public void setUnstm(String unstm) {
		this.unstm = Utils.checkNull(unstm);
	}

	public String getRohmas() {
		return rohmas;
	}

	public void setRohmas(String rohmas) {
		this.rohmas = Utils.checkNull(rohmas);
	}

	public String getEigmas() {
		return eigmas;
	}

	public void setEigmas(String eigmas) {
		this.eigmas = Utils.checkNull(eigmas);
	}

	public String getPfehlt() {
		return pfehlt;
	}

	public void setPfehlt(String pfehlt) {
		this.pfehlt = Utils.checkNull(pfehlt);
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

	public String getKzexeu() {
		return kzexeu;
	}

	public void setKzexeu(String kzexeu) {
		this.kzexeu = Utils.checkNull(kzexeu);
	}

	public String getLdexp() {
		return ldexp;
	}

	public void setLdexp(String ldexp) {
		this.ldexp = Utils.checkNull(ldexp);
	}

	public String getKzvub() {
		return kzvub;
	}

	public void setKzvub(String kzvub) {
		this.kzvub = Utils.checkNull(kzvub);
	}

	public String getKzabg() {
		return kzabg;
	}

	public void setKzabg(String kzabg) {
		this.kzabg = Utils.checkNull(kzabg);
	}

	public String getKzexp() {
		return kzexp;
	}

	public void setKzexp(String kzexp) {
		this.kzexp = Utils.checkNull(kzexp);
	}
	
	public boolean isEmpty() {
		return (Utils.isStringEmpty(posnr) &&
				Utils.isStringEmpty(tnr) && Utils.isStringEmpty(wabsch) &&
				Utils.isStringEmpty(unstm) && Utils.isStringEmpty(rohmas) &&
				Utils.isStringEmpty(eigmas) && Utils.isStringEmpty(pfehlt) &&
				Utils.isStringEmpty(awbzzz) && Utils.isStringEmpty(suapos) &&
				Utils.isStringEmpty(kzexeu) && Utils.isStringEmpty(ldexp) &&
				Utils.isStringEmpty(kzvub) && Utils.isStringEmpty(kzabg) &&
				Utils.isStringEmpty(kzexp));
	}
}

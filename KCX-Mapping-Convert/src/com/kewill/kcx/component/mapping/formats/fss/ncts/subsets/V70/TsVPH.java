package com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.V70;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;
/**
 * Module		: TsVPH<br>
 * Created		: 2010.09.03<br>
 * Description	: VPH model class.
 * 
 * @author Frederick T
 * @version 6.2.00
 *
 */
public class TsVPH extends Teilsatz {
	private String beznr		= "";
	private String mrn			= "";
	private String anmart		= "";
	private String ubgort		= "";
	private String ldvs		= "";
	private String ldbe		= "";
	private String bedst		= "";
	private String abgdst		= "";
	private String wgfri		= "";
	private String bfabkz		= "";
	private String bfabld		= "";
	private String tinve		= "";
	private String nlve			= "";  //V70 new
	private String idve			= "";  //V70 new
	private String tinem		= "";
	private String nlem			= "";  //V70 new
	private String idem			= "";  //V70 new
	private String tinhp		= "";
	private String nlhp			= "";  //V70 new
	private String idhp			= "";  //V70 new
	private String hp			= "";  //V70 new	ID-Carnetinhaber
	private String gsroh		= "";
	private String anzpos		= "";
	private String anzcol		= "";
	private String anzve		= "";
	private String etnabs		= "";
	
	public TsVPH() {
		tsTyp = "VPH";
	}
	
	public String teilsatzBilden() {
		StringBuffer buff = new StringBuffer();
		
		buff.append(tsTyp);
		buff.append(trenner);
		buff.append(beznr);
		buff.append(trenner);
		buff.append(mrn);
		buff.append(trenner);
		buff.append(anmart);
		buff.append(trenner);
		buff.append(ubgort);
		buff.append(trenner);
		buff.append(ldvs);
		buff.append(trenner);
		buff.append(ldbe);
		buff.append(trenner);
		buff.append(bedst);
		buff.append(trenner);
		buff.append(abgdst);
		buff.append(trenner);
		buff.append(wgfri);
		buff.append(trenner);
		buff.append(bfabkz);
		buff.append(trenner);
		buff.append(bfabld);
		buff.append(trenner);
		buff.append(tinve);
		buff.append(trenner);
		buff.append(nlve);
		buff.append(trenner);
		buff.append(idve);
		buff.append(trenner);
		buff.append(tinem);
		buff.append(trenner);
		buff.append(nlem);
		buff.append(trenner);
		buff.append(idem);
		buff.append(trenner);
		buff.append(tinhp);
		buff.append(trenner);
		buff.append(nlhp);
		buff.append(trenner);
		buff.append(idhp);
		buff.append(trenner);
		buff.append(hp);
		buff.append(trenner);
		buff.append(gsroh);
		buff.append(trenner);
		buff.append(anzpos);
		buff.append(trenner);
		buff.append(anzcol);
		buff.append(trenner);
		buff.append(anzve);
		buff.append(trenner);
		buff.append(etnabs);
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
		mrn =  fields[2];
		if (size < 4) {
			return;
		}
		anmart = fields[3];
		if (size < 5) {
			return;
		}
		ubgort = fields[4];
		if (size < 6) {
			return;
		}
		ldvs = fields[5];
		if (size < 7) {
			return;
		}
		ldbe = fields[6];
		if (size < 8) {
			return;
		}
		bedst = fields[7];
		if (size < 9) {
			return;
		}
		abgdst = fields[8];
		if (size < 10) {
			return;
		}
		wgfri = fields[9];
		if (size < 11) {
			return;
		}
		bfabkz = fields[10];
		if (size < 12) {
			return;
		}
		bfabld = fields[11];
		if (size < 13) { return; }
		tinve = fields[12];
		if (size < 14) { return; }
		nlve = fields[13];
		if (size < 15) { return; }
		idve = fields[14];
		if (size < 16) {return;}
		tinem = fields[15];
		if (size < 17) { return; }
		nlem = fields[16];
		if (size < 18) { return; }
		idem = fields[17];
		if (size < 19) { return; }
		tinhp = fields[18];
		if (size < 20) { return; }
		nlhp = fields[19];
		if (size < 21) { return; }
		idhp = fields[20];
		if (size < 22) { return; }
		hp = fields[21];
		if (size < 23) { return; }
		gsroh = fields[22];
		if (size < 24) { return;}
		anzpos = fields[23];
		if (size < 25) {return;}
		anzcol = fields[24];
		if (size < 26) {return;}
		anzve = fields[25];
		if (size < 27) {return;}
		etnabs = fields[26];
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

	public String getAnmart() {
		return anmart;
	}
	public void setAnmart(String anmart) {
		this.anmart = Utils.checkNull(anmart);
	}

	public String getUbgort() {
		return ubgort;
	}
	public void setUbgort(String ubgort) {
		this.ubgort = Utils.checkNull(ubgort);
	}

	public String getLdvs() {
		return ldvs;
	}
	public void setLdvs(String ldvs) {
		this.ldvs = Utils.checkNull(ldvs);
	}

	public String getLdbe() {
		return ldbe;
	}
	public void setLdbe(String ldbe) {
		this.ldbe = Utils.checkNull(ldbe);
	}

	public String getBedst() {
		return bedst;
	}
	public void setBedst(String bedst) {
		this.bedst = Utils.checkNull(bedst);
	}

	public String getAbgdst() {
		return abgdst;
	}
	public void setAbgdst(String abgdst) {
		this.abgdst = Utils.checkNull(abgdst);
	}

	public String getWgfri() {
		return wgfri;
	}
	public void setWgfri(String wgfri) {
		this.wgfri = Utils.checkNull(wgfri);
	}

	public String getBfabkz() {
		return bfabkz;
	}
	public void setBfabkz(String bfabkz) {
		this.bfabkz = Utils.checkNull(bfabkz);
	}

	public String getBfabld() {
		return bfabld;
	}
	public void setBfabld(String bfabld) {
		this.bfabld = Utils.checkNull(bfabld);
	}

	public String getTinve() {
		return tinve;
	}
	public void setTinve(String tinve) {
		this.tinve = Utils.checkNull(tinve);
	}

	public String getTinem() {
		return tinem;
	}

	public void setTinem(String tinem) {
		this.tinem = Utils.checkNull(tinem);
	}
	public String getTinhp() {
		return tinhp;
	}
	public void setTinhp(String tinhp) {
		this.tinhp = Utils.checkNull(tinhp);
	}
	
	public String getGsroh() {
		return gsroh;
	}
	public void setGsroh(String gsroh) {
		this.gsroh = Utils.checkNull(gsroh);
	}

	public String getAnzpos() {
		return anzpos;
	}
	public void setAnzpos(String anzpos) {
		this.anzpos = Utils.checkNull(anzpos);
	}

	public String getAnzcol() {
		return anzcol;
	}
	public void setAnzcol(String anzcol) {
		this.anzcol = Utils.checkNull(anzcol);
	}

	public String getAnzve() {
		return anzve;
	}
	public void setAnzve(String anzve) {
		this.anzve = Utils.checkNull(anzve);
	}

	public String getEtnabs() {
		return etnabs;
	}
	public void setEtnabs(String etnabs) {
		this.etnabs = Utils.checkNull(etnabs);
	}	
	
	public String getNlve() {
		return nlve;
	}
	public void setNlve(String nlve) {
		this.nlve = Utils.checkNull(nlve);
	}

	public String getIdve() {
		return idve;
	}
	public void setIdve(String idve) {
		this.idve = Utils.checkNull(idve);
	}

	public String getNlem() {
		return nlem;
	}
	public void setNlem(String nlem) {
		this.nlem = Utils.checkNull(nlem);
	}

	public String getIdem() {
		return idem;
	}
	public void setIdem(String idem) {
		this.idem = Utils.checkNull(idem);
	}

	public String getNlhp() {
		return nlhp;
	}
	public void setNlhp(String nlhp) {
		this.nlhp = Utils.checkNull(nlhp);
	}

	public String getIdhp() {
		return idhp;
	}
	public void setIdhp(String idhp) {
		this.idhp = Utils.checkNull(idhp);
	}

	public String getHp() {
		return hp;
	}
	public void setHp(String hp) {
		this.hp = Utils.checkNull(hp);
	}

	public boolean isEmpty() {
		return (Utils.isStringEmpty(mrn) &&
				Utils.isStringEmpty(anmart) && Utils.isStringEmpty(ubgort) &&
				Utils.isStringEmpty(ldvs) && Utils.isStringEmpty(ldbe) &&
				Utils.isStringEmpty(bedst) && Utils.isStringEmpty(abgdst) &&
				Utils.isStringEmpty(wgfri) && Utils.isStringEmpty(bfabkz) &&
				Utils.isStringEmpty(bfabld) && Utils.isStringEmpty(tinve) &&
				Utils.isStringEmpty(tinem) && Utils.isStringEmpty(tinhp) &&
				Utils.isStringEmpty(gsroh) && Utils.isStringEmpty(anzpos) &&
				Utils.isStringEmpty(anzcol) && Utils.isStringEmpty(anzve) &&
				Utils.isStringEmpty(etnabs));
	}
}

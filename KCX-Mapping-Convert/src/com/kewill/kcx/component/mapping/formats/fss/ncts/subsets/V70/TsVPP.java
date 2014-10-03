package com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.V70;

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
	//private String unstm		= ""; V70 removed
	private String rohmas		= "";
	private String eigmas		= "";
	private String eorive		= "";  //V70 new
	private String nlve		    = "";  //V70 new
	private String idve		    = "";  //V70 new
	private String eoriem		= "";  //V70 new
	private String nlem		    = "";  //V70 new
	private String idem		    = "";  //V70 new
	/*
	private String pfehlt		= "";
	private String awbzzz		= "";
	private String suapos		= "";
	*/
	private String anmart		= "";  //V70 new
	private String veld		    = "";  //V70 new Versendungsland
	private String beld		    = "";  //V70 new Bestimmungsland 	
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
		buff.append(rohmas);
		buff.append(trenner);
		buff.append(eigmas);
		buff.append(trenner);
		buff.append(eorive);
		buff.append(trenner);
		buff.append(nlve);
		buff.append(trenner);
		buff.append(idve);
		buff.append(trenner);
		buff.append(eoriem);
		buff.append(trenner);
		buff.append(nlem);
		buff.append(trenner);
		buff.append(idem);
		buff.append(trenner);
		buff.append(anmart);
		buff.append(trenner);
		buff.append(veld);
		buff.append(trenner);
		buff.append(beld);
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
		if (size < 5) { return; }
		wabsch = fields[4];
		if (size < 6) { return; }		
		rohmas = fields[5];
		if (size < 7) { return; }
		eigmas = fields[6];
		if (size < 8) { return; }
		eorive = fields[7];
		if (size < 9) { return; }
		nlve = fields[8];
		if (size < 10) { return; }
		idve = fields[9];
		if (size < 11) { return; }
		eoriem = fields[10];
		if (size < 12) { return; }
		nlem = fields[11];
		if (size < 13) { return; }
		idem = fields[12];
		if (size < 14) { return; }
		anmart = fields[13];
		if (size < 15) { return; }
		veld = fields[14];
		if (size < 16) { return; }
		beld = fields[15];
		if (size < 17) { return; }
		kzexeu = fields[16];
		if (size < 18) { return; }
		ldexp = fields[17];
		if (size < 19) { return; }
		kzvub = fields[18];
		if (size < 20) { return; }
		kzabg = fields[19];
		if (size < 21) { return; }
		kzexp = fields[20];
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

	public String getEorive() {
		return eorive;
	}
	public void setEorive(String pfehlt) {
		this.eorive = Utils.checkNull(pfehlt);
	}

	public String getNlve() {
		return nlve;
	}
	public void setNlve(String awbzzz) {
		this.nlve = Utils.checkNull(awbzzz);
	}

	public String getIdve() {
		return idve;
	}
	public void setIdve(String suapos) {
		this.idve = Utils.checkNull(suapos);
	}
	
	public String getEoriem() {
		return eoriem;
	}
	public void setEoriem(String pfehlt) {
		this.eoriem = Utils.checkNull(pfehlt);
	}

	public String getNlem() {
		return nlem;
	}
	public void setNlem(String awbzzz) {
		this.nlem = Utils.checkNull(awbzzz);
	}

	public String getIdem() {
		return idem;
	}
	public void setIdem(String suapos) {
		this.idem = Utils.checkNull(suapos);
	}
	
	public String getAnmart() {
		return anmart;
	}
	public void setAnmart(String pfehlt) {
		this.anmart = Utils.checkNull(pfehlt);
	}

	public String getVeld() {
		return veld;
	}
	public void setVeld(String awbzzz) {
		this.veld = Utils.checkNull(awbzzz);
	}

	public String getBeld() {
		return beld;
	}
	public void setBeld(String suapos) {
		this.beld = Utils.checkNull(suapos);
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
				Utils.isStringEmpty(rohmas) && Utils.isStringEmpty(eigmas) &&
				Utils.isStringEmpty(eorive) && Utils.isStringEmpty(eoriem) &&
				Utils.isStringEmpty(anmart) &&
				Utils.isStringEmpty(veld) && Utils.isStringEmpty(beld) &&
				Utils.isStringEmpty(kzexeu) && Utils.isStringEmpty(ldexp) &&
				Utils.isStringEmpty(kzvub) && Utils.isStringEmpty(kzabg) &&
				Utils.isStringEmpty(kzexp));
	}
}

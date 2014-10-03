package com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.V70;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: NCTS OUT<br>
 * Created		: 12.11.2012<br>
 * Description	: Definition of subset TsVPO.
 * 
 * @author iwaniuk
 * @version 7.0.00
 */

public class TsVPO extends Teilsatz {
    
	private String beznr  	= "";		// Bezugsnummer 
	private String posnr  	= "";		// Positionsnummer
	private String anmart  	= "";		// Art der Anmeldung
	private String tnr  	= "";		//
	private String wbsch1  	= "";		//
	private String wbsch2  	= "";		//
	private String wbsch3  	= "";		//
	private String wbsch4  	= "";		//
	private String artnr  	= "";		//
	private String ldvs  	= "";		//
	private String ldbe  	= "";		//
	private String rohm  	= "";		//
	private String eigm  	= "";		//
	private String kdnrve  	= "";		//V70 
	private String tinve  	= "";		//
	private String nlve  	= "";		//V70 
	private String dtzove  	= "";		//V70 
	private String kdnrem  	= "";		//V70
	private String tinem  	= "";		//
	private String nlem  	= "";		//V70
	private String dtzoem  	= "";		//V70
	private String vptyp  	= "";		//
	private String suaart  	= "";		//
	private String zlbez  	= "";		//
	private String azvbew  	= "";		//
	private String kzexeu  	= "";		// Kennzeichen Ausfuhr aus EU 0 = Nein, 1 = Ja
	private String ldexp  	= "";		// Export aus EU 
	private String kzvub  	= "";		// Kennzeichen Verbote und Beschrän-kungen 
	private String kzabg  	= "";		// Kennzeichen Ausfuhrabgaben
	private String kzexp  	= "";		// Kennzeichen Ausfuhr
	private String verm  	= "";		// Vermerk zur Position
	private String sgicd  	= "";		// Kennzeichen Empfindliche Menge
	private String sgimng  	= "";		// Mengenangabe Empfindliche Menge
	private String bvm  	= "";		// Besondere Vermerke 
	

    public TsVPO() {
        tsTyp = "VPO";
    }

    public String teilsatzBilden() {
        StringBuffer buff = new StringBuffer();
          
        buff.append(tsTyp);
        buff.append(trenner);        
        buff.append(beznr);
        buff.append(trenner);	
        buff.append(posnr);
        buff.append(trenner);	
        buff.append(anmart);
        buff.append(trenner);	
        buff.append(tnr);
        buff.append(trenner);	
        buff.append(wbsch1);
        buff.append(trenner);	
        buff.append(wbsch2);
        buff.append(trenner);	
        buff.append(wbsch3);
        buff.append(trenner);	
        buff.append(wbsch4);
        buff.append(trenner);	
        buff.append(artnr);
        buff.append(trenner);	
        buff.append(ldvs);
        buff.append(trenner);	
        buff.append(ldbe);
        buff.append(trenner);	
        buff.append(rohm);
        buff.append(trenner);	
        buff.append(eigm);
        buff.append(trenner);
        buff.append(kdnrve);
        buff.append(trenner);	
        buff.append(tinve);
        buff.append(trenner);	
        buff.append(nlve);
        buff.append(trenner);	
        buff.append(dtzove);
        buff.append(trenner);	
        buff.append(kdnrem);
        buff.append(trenner);	
        buff.append(tinem);
        buff.append(trenner);	
        buff.append(nlem);
        buff.append(trenner);	
        buff.append(dtzoem);
        buff.append(trenner);	       
        buff.append(vptyp);
        buff.append(trenner);	
        buff.append(suaart);
        buff.append(trenner);	
        buff.append(zlbez);
        buff.append(trenner);	
        buff.append(azvbew);
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
        buff.append(verm);
        buff.append(trenner);	
        buff.append(sgicd);
        buff.append(trenner);	
        buff.append(sgimng);
        buff.append(trenner);	
        buff.append(bvm);
        buff.append(trenner);	

    	return buff.toString();
    }

	// setFields will not be used because VPO is a specific subset of the message VAN
	// and this FSS-message is created by KCX to be sent to Zabis
	// only messages from Zabis have to write out this method

	public void setFields(String[] fields) {
		Utils.log("TsVPO.setFields not created");
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

	public String getAnmart() {
		return anmart;
	}

	public void setAnmart(String anmart) {
		this.anmart = Utils.checkNull(anmart);
	}

	public String getTnr() {
		return tnr;
	}

	public void setTnr(String tnr) {
		this.tnr = Utils.checkNull(tnr);
	}

	// additional set-Method splitting the data to fss fields
	public void setWbsch(String wbsch) {
		int len = 0;
		
		if (wbsch != null) {						
				len = wbsch.length();
			}
		
							
			if (len > 0 && len <= 70) {
				wbsch1 = wbsch;
			} else if (len > 70 && len <= 140) {
				wbsch1 = wbsch.substring(0, 70);
				wbsch2 = wbsch.substring(70);
			} else if  (len > 140 && len <= 210) {
				wbsch1 = wbsch.substring(0, 70);
				wbsch2 = wbsch.substring(70, 140);
				wbsch3 = wbsch.substring(140);
			}
			else if (len > 210 ) {
				wbsch1 = wbsch.substring(0, 70);
				wbsch2 = wbsch.substring(70, 140);
				wbsch3 = wbsch.substring(140, 210);
				wbsch4 = wbsch.substring(210);
		}				
	}
	
	public String getWbsch1() {
		return wbsch1;
	}

	public void setWbsch1(String wbsch1) {
		this.wbsch1 = Utils.checkNull(wbsch1);
	}

	public String getWbsch2() {
		return wbsch2;
	}

	public void setWbsch2(String wbsch2) {
		this.wbsch2 = Utils.checkNull(wbsch2);
	}

	public String getWbsch3() {
		return wbsch3;
	}

	public void setWbsch3(String wbsch3) {
		this.wbsch3 = Utils.checkNull(wbsch3);
	}

	public String getWbsch4() {
		return wbsch4;
	}

	public void setWbsch4(String wbsch4) {
		this.wbsch4 = Utils.checkNull(wbsch4);
	}

	public String getArtnr() {
		return artnr;
	}

	public void setArtnr(String artnr) {
		this.artnr = Utils.checkNull(artnr);
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

	public String getRohm() {
		return rohm;
	}

	public void setRohm(String rohm) {
		this.rohm = Utils.checkNull(rohm);
	}

	public String getEigm() {
		return eigm;
	}

	public void setEigm(String eigm) {
		this.eigm = Utils.checkNull(eigm);
	}

	public String getTinve() {
		return tinve;
	}

	public void setTinve(String tinve) {
		this.tinve = Utils.checkNull(tinve);
	}

	public String getKdnrve() {
		return kdnrve;
	}

	public void setKdnrve(String kdnrve) {
		this.kdnrve = Utils.checkNull(kdnrve);
	}

	public String getTinem() {
		return tinem;
	}

	public void setTinem(String tinem) {
		this.tinem = Utils.checkNull(tinem);
	}

	public String getKdnrem() {
		return kdnrem;
	}

	public void setKdnrem(String kdnrem) {
		this.kdnrem = Utils.checkNull(kdnrem);
	}

	public String getVptyp() {
		return vptyp;
	}

	public void setVptyp(String vptyp) {
		this.vptyp = Utils.checkNull(vptyp);
	}

	public String getSuaart() {
		return suaart;
	}

	public void setSuaart(String suaart) {
		this.suaart = Utils.checkNull(suaart);
	}

	public String getZlbez() {
		return zlbez;
	}

	public void setZlbez(String zlbez) {
		this.zlbez = Utils.checkNull(zlbez);
	}

	public String getAzvbew() {
		return azvbew;
	}

	public void setAzvbew(String azvbew) {
		this.azvbew = Utils.checkNull(azvbew);
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

	public String getVerm() {
		return verm;
	}

	public void setVerm(String verm) {
		this.verm = Utils.checkNull(verm);
	}

	public String getSgicd() {
		return sgicd;
	}

	public void setSgicd(String sgicd) {
		this.sgicd = Utils.checkNull(sgicd);
	}

	public String getSgimng() {
		return sgimng;
	}

	public void setSgimng(String sgimng) {
		this.sgimng = Utils.checkNull(sgimng);
	}

	public String getBvm() {
		return bvm;
	}

	public void setBvm(String bvm) {
		this.bvm = Utils.checkNull(bvm);
	}
	
	public boolean isEmpty() {
		return (Utils.isStringEmpty(anmart) && Utils.isStringEmpty(tnr) &&
				Utils.isStringEmpty(wbsch1) && Utils.isStringEmpty(artnr) &&
				Utils.isStringEmpty(ldvs) && Utils.isStringEmpty(ldbe) &&
				Utils.isStringEmpty(rohm) && Utils.isStringEmpty(eigm) &&
				Utils.isStringEmpty(tinve) && Utils.isStringEmpty(kdnrve) &&
				Utils.isStringEmpty(tinem) && Utils.isStringEmpty(kdnrem) &&
				Utils.isStringEmpty(vptyp) && Utils.isStringEmpty(suaart) &&
				Utils.isStringEmpty(zlbez));

	}
		
}




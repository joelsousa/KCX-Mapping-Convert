package com.kewill.kcx.component.mapping.formats.fss.suma.subsets.V62;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;


/**
 * Module		:	TsEVK
 * Created		:	19.12.2012
 * Description	:   Kopfsatz Gestellung-/Aufteilungsdaten.
 *        			Zabis Version 06.02  
 *
 * @author iwaniuk
 * @version 1.0.00
 */

public class TsSUK  extends Teilsatz {

	private String beznr	= "";	
	private String erfkz	= "";
	private String aposnr	= "";
	private String aregnr	= "";	
	private String kzvorz	= "";
	private String gstznr	= "";
	private String vtrznr	= "";
	private String gstdat	= "";	//Format = YYYYMMDD
	private String dstnr	= "";	
	private String seekz	= "";	
	private String vorart	= "";	
	private String vornr	= "";	
	private String befart	= "";	
	private String befkz	= "";	
	private String befson	= "";	
	private String anzcon	= "";	
	private String belo		= "";	
	private String sb		= "";	
	private String sbname	= "";	
	private String sbdstl	= "";	
	private String sbtel	= "";	
	private String idart	= "";	
	private String akz		= "";	
	private String fltnum	= "";	
	private String kzncts	= "";	
	private String bfvkzg	= "";	
	private String eindst	= "";
	private String kzeindst	= "";	
	private String befnum	= "";	
	private String ankdat	= "";

    public TsSUK() {
        tsTyp = "SUK";
    }

    public void setFields(String[] fields) {  
		int size = fields.length;
		//String ausgabe = "FSS: "+fields[0]+" size = "+ size;
		//Utils.log( ausgabe);
			
		
		if (size < 1) { return; }		
        tsTyp = fields[0];
        if (size < 2) { return; }		
        beznr = fields[1];
        if (size < 3) { return; }	
        erfkz = fields[2];
        if (size < 4) { return; }	
        aposnr = fields[3];
        
        //usw.... z.T wird die methode nicht gebraucht
      }

    public String teilsatzBilden() {
        StringBuffer buff = new StringBuffer();

        buff.append(tsTyp);
        buff.append(trenner);
        buff.append(beznr);
        buff.append(trenner);
        buff.append(erfkz);
        buff.append(trenner);
        buff.append(aposnr);
        buff.append(trenner);
        buff.append(aregnr);
        buff.append(trenner);
        buff.append(kzvorz);
        buff.append(trenner);
        buff.append(gstznr);
        buff.append(trenner);
        buff.append(vtrznr);
        buff.append(trenner);
        buff.append(gstdat);
        buff.append(trenner);
        buff.append(dstnr);
        buff.append(trenner);
        buff.append(seekz);
        buff.append(trenner);
        buff.append(vorart);
        buff.append(trenner);
        buff.append(vornr);
        buff.append(trenner);
        buff.append(befart);
        buff.append(trenner);
        buff.append(befkz);
        buff.append(trenner);
        buff.append(befson);
        buff.append(trenner);
        buff.append(anzcon);
        buff.append(trenner);
        buff.append(belo);
        buff.append(trenner);
        buff.append(sb);
        buff.append(trenner);
        buff.append(sbname);
        buff.append(trenner);
        buff.append(sbdstl);
        buff.append(trenner);
        buff.append(sbtel);
        buff.append(trenner);
        buff.append(idart);
        buff.append(trenner);
        buff.append(akz);
        buff.append(trenner);
        buff.append(fltnum);
        buff.append(trenner);
        buff.append(kzncts);
        buff.append(trenner);
        buff.append(bfvkzg);
        buff.append(trenner);
        buff.append(eindst);
        buff.append(trenner);
        buff.append(kzeindst);
        buff.append(trenner);
        buff.append(befnum);
        buff.append(trenner);
        buff.append(ankdat);
        buff.append(trenner);
        
        return new String(buff);
    }

    public String getTsTyp() {
    	return tsTyp;
    }
    public void setTsTyp(String tsTyp) {
    	this.tsTyp = Utils.checkNull(tsTyp);
    }

    public String getBeznr() {
		return beznr;
	}
	public void setBeznr(String beznr) {
		this.beznr = Utils.checkNull(beznr);
	}

	public String getErfkz() {
		return erfkz;
	}
	public void setErfkz(String erfkz) {
		this.erfkz = Utils.checkNull(erfkz);
	}

	public String getAposnr() {
		return aposnr;
	}
	public void setAposnr(String aposnr) {
		this.aposnr = Utils.checkNull(aposnr);
	}

	public String getAregnr() {
		return aregnr;
	}
	public void setAregnr(String aregnr) {
		this.aregnr = Utils.checkNull(aregnr);
	}

	public String getKzvorz() {
		return kzvorz;
	}
	public void setKzvorz(String kzvorz) {
		this.kzvorz = Utils.checkNull(kzvorz);
	}

	public String getGstznr() {
		return gstznr;
	}
	public void setGstznr(String gstznr) {
		this.gstznr = Utils.checkNull(gstznr);
	}

	public String getVtrznr() {
		return vtrznr;
	}
	public void setVtrznr(String vtrznr) {
		this.vtrznr = Utils.checkNull(vtrznr);
	}

	public String getGstdat() {
		return gstdat;
	}
	public void setGstdat(String gstdat) {
		this.gstdat = Utils.checkNull(gstdat);
	}
	
	public String getDstnr() {
		return dstnr;
	}
	public void setDstnr(String dstnr) {
		this.dstnr = Utils.checkNull(dstnr);
	}
	
	public String getSeekz() {
		return seekz;
	}
	public void setSeekz(String seekz) {
		this.seekz = Utils.checkNull(seekz);
	}

	public String getVorart() {
		return vorart;
	}
	public void setVorart(String vorart) {
		this.vorart = Utils.checkNull(vorart);
	}

	public String getVornr() {
		return vornr;
	}
	public void setVornr(String vornr) {
		this.vornr = Utils.checkNull(vornr);
	}

	public String getBefart() {
		return befart;
	}
	public void setBefart(String befart) {
		this.befart = Utils.checkNull(befart);
	}

	public String getBefkz() {
		return befkz;
	}
	public void setBefkz(String befkz) {
		this.befkz = Utils.checkNull(befkz);
	}

	public String getBefson() {
		return befson;
	}
	public void setBefson(String befson) {
		this.befson = Utils.checkNull(befson);
	}

	public String getAnzcon() {
		return anzcon;
	}
	public void setAnzcon(String anzcon) {
		this.anzcon = Utils.checkNull(anzcon);
	}

	public String getBelo() {
		return belo;
	}
	public void setBelo(String belo) {
		this.belo = Utils.checkNull(belo);
	}

	public String getSb() {
		return sb;
	}
	public void setSb(String sb) {
		this.sb = Utils.checkNull(sb);
	}

	public String getSbname() {
		return sbname;
	}
	public void setSbname(String sbname) {
		this.sbname = Utils.checkNull(sbname);
	}

	public String getSbdstl() {
		return sbdstl;
	}
	public void setSbdstl(String sbdstl) {
		this.sbdstl = Utils.checkNull(sbdstl);
	}

	public String getSbtel() {
		return sbtel;
	}
	public void setSbtel(String sbtel) {
		this.sbtel = Utils.checkNull(sbtel);
	}

	public String getIdart() {
		return idart;
	}
	public void setIdart(String idart) {
		this.idart = Utils.checkNull(idart);
	}

	public String getAkz() {
		return akz;
	}
	public void setAkz(String akz) {
		this.akz = Utils.checkNull(akz);
	}

	public String getFltnum() {
		return fltnum;
	}
	public void setFltnum(String fltnum) {
		this.fltnum = Utils.checkNull(fltnum);
	}

	public String getKzncts() {
		return kzncts;
	}
	public void setKzncts(String kzncts) {
		this.kzncts = Utils.checkNull(kzncts);
	}

	public String getBfvkzg() {
		return bfvkzg;
	}
	public void setBfvkzg(String bfvkzg) {
		this.bfvkzg = Utils.checkNull(bfvkzg);
	}

	public String getEindst() {
		return eindst;
	}
	public void setEindst(String eindst) {
		this.eindst = Utils.checkNull(eindst);
	}

	public String getKzeindst() {
		return kzeindst;
	}
	public void setKzeindst(String kzeindst) {
		this.kzeindst = Utils.checkNull(kzeindst);
	}

	public String getBefnum() {
		return befnum;
	}
	public void setBefnum(String befnum) {
		this.befnum = Utils.checkNull(befnum);
	}

	public String getAnkdat() {
		return ankdat;
	}
	public void setAnkdat(String ankdat) {
		this.ankdat = Utils.checkNull(ankdat);
	}

	public boolean isEmpty() {
    	return (Utils.isStringEmpty(beznr) && Utils.isStringEmpty(erfkz) &&
    			//TODO weiter...
    			Utils.isStringEmpty(aregnr) && Utils.isStringEmpty(gstdat));		
    }
	
}



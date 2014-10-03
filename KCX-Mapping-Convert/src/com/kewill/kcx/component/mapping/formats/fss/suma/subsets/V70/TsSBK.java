package com.kewill.kcx.component.mapping.formats.fss.suma.subsets.V70;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;


/**
 * Module		:	TsSBK
 * Created		:	31.05.2013
 * Description	:   Kopfsatz Gestellung-/Aufteilungsdaten.
 *        			(Zabis V70) 
 *
 * @author krzoska
 * @version 7.0.00
 */

public class TsSBK  extends Teilsatz {
	
	private String beznr	= "";	// Bezugsnummer
	private String gstdat	= "";	// Gestellungsdatum (neu in Atlas8.4)
	private String seekz	= "";	// Kennzeichen Seeverkehr
	private String vorart	= "";	// Vorpapierart
	private String vornr	= "";	// Vorpapiernummer (neu in Atlas8.4)
	private String befart	= "";	// Beförderungsmittel
	private String befkz	= "";	// Kennzeichen Beförderungsmittel
	private String befson	= "";	// sonstiges Beförderungsmittel
	private String anzcon	= "";	// Anzahl der Container (gesamt)
	private String belo		= "";	// Beladeort
	private String sb		= "";	// Sachbearbeiter ID
	private String sbname	= "";	// Sachbearbeitername
	private String sbdstl	= "";	// Dienststellung des Sachbearbeiters
	private String sbtel	= "";	// Telefonnummer des Sachbearbeiters
	private String idart	= "";	// Art der Identifikation
	private String kzncts	= "";	// Kennzeichen NCTS-Aussetzung
	private String befnum	= "";	// Nummer der Beförderung
	private String ankdat	= "";	// Ankunftsdatum (neu in Atlas8.4)
	private String vkzwg	= "";	// Verkehrszweig an der Grenze (neu in Atlas8.4)
	private String andst	= "";	// Angemeldete erste Eingangszollstelle (neu in Atlas8.4)
	private String kzeindst	= "";	// Kennzeichen erste Eingangszollstelle (neu in Atlas8.4)
	private String gstkdnr	= "";	// Gestellender Kundennummer (neu in Atlas8.4)
	private String gsteori	= "";	// Gestellender EORI-TIN (neu in Atlas8.4)
	private String gstnlnr	= "";	// Gestellender Niederlassung (neu in Atlas8.4)
	private String vtrkdnr	= "";	// Vertreter Kundennummer (neu in Atlas8.4)
	private String vtreori	= "";	// Vertreter EORI-TIN (neu in Atlas8.4)
	private String vtrnlnr	= "";	// Vertreter Niederlassung (neu in Atlas8.4)
	
	
    public TsSBK() {
        tsTyp = "SBK";
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
        gstdat = fields[2];
        if (size < 4) { return; }	
        seekz = fields[3];
        
        //usw.... z.T wird die methode nicht gebraucht
      }
    
    public String teilsatzBilden() {
        StringBuffer buff = new StringBuffer();

        buff.append(tsTyp);
		buff.append(trenner);
		buff.append(beznr);
		buff.append(trenner);
		buff.append(gstdat);
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
		buff.append(kzncts);
		buff.append(trenner);
		buff.append(befnum);
		buff.append(trenner);
		buff.append(ankdat);
		buff.append(trenner);
		buff.append(vkzwg);
		buff.append(trenner);
		buff.append(andst);
		buff.append(trenner);
		buff.append(kzeindst);
		buff.append(trenner);
		buff.append(gstkdnr);
		buff.append(trenner);
		buff.append(gsteori);
		buff.append(trenner);
		buff.append(gstnlnr);
		buff.append(trenner);
		buff.append(vtrkdnr);
		buff.append(trenner);
		buff.append(vtreori);
		buff.append(trenner);
		buff.append(vtrnlnr);
		buff.append(trenner);
		
		return new String(buff);
    }
    

	public boolean isEmpty() {
		return (Utils.isStringEmpty(gstdat) && Utils.isStringEmpty(seekz) && 
				Utils.isStringEmpty(vorart) &&  Utils.isStringEmpty(vornr) && 
				Utils.isStringEmpty(befart) &&  Utils.isStringEmpty(befkz) && 
				Utils.isStringEmpty(befson) &&  Utils.isStringEmpty(anzcon) && 
				Utils.isStringEmpty(belo) &&    Utils.isStringEmpty(sb) && 
				Utils.isStringEmpty(sbname) &&  Utils.isStringEmpty(sbdstl) && 
				Utils.isStringEmpty(sbtel) &&   Utils.isStringEmpty(idart) && 
				Utils.isStringEmpty(kzncts) &&  Utils.isStringEmpty(befnum) && 
				Utils.isStringEmpty(ankdat) &&  Utils.isStringEmpty(vkzwg) && 
				Utils.isStringEmpty(andst) &&   Utils.isStringEmpty(kzeindst) && 
				Utils.isStringEmpty(gstkdnr) && Utils.isStringEmpty(gsteori) && 
				Utils.isStringEmpty(gstnlnr) && Utils.isStringEmpty(vtrkdnr) && 
				Utils.isStringEmpty(vtreori) && Utils.isStringEmpty(vtrnlnr));
	}
	
	public String getBeznr() {
		return beznr;
	}

	public void setBeznr(String beznr) {
		this.beznr = Utils.checkNull(beznr);
	}

	public String getGstkdnr() {
		return gstkdnr;
	}

	public void setGstkdnr(String gstkdnr) {
		this.gstkdnr = Utils.checkNull(gstkdnr);
	}

	public String getGsteori() {
		return gsteori;
	}

	public void setGsteori(String gsteori) {
		this.gsteori = Utils.checkNull(gsteori);
	}

	public String getGstnlnr() {
		return gstnlnr;
	}

	public void setGstnlnr(String gstnlnr) {
		this.gstnlnr = Utils.checkNull(gstnlnr);
	}

	public String getVtrkdnr() {
		return vtrkdnr;
	}

	public void setVtrkdnr(String vtrkdnr) {
		this.vtrkdnr = Utils.checkNull(vtrkdnr);
	}

	public String getVtreori() {
		return vtreori;
	}

	public void setVtreori(String vtreori) {
		this.vtreori = Utils.checkNull(vtreori);
	}

	public String getVtrnlnr() {
		return vtrnlnr;
	}

	public void setVtrnlnr(String vtrnlnr) {
		this.vtrnlnr = Utils.checkNull(vtrnlnr);
	}

	public String getGstdat() {
		return gstdat;
	}

	public void setGstdat(String gstdat) {
		this.gstdat = Utils.checkNull(gstdat);
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

	public String getKzncts() {
		return kzncts;
	}

	public void setKzncts(String kzncts) {
		this.kzncts = Utils.checkNull(kzncts);
	}

	public String getVkzwg() {
		return vkzwg;
	}

	public void setVkzwg(String vkzwg) {
		this.vkzwg = Utils.checkNull(vkzwg);
	}

	public String getAndst() {
		return andst;
	}

	public void setAndst(String andst) {
		this.andst = Utils.checkNull(andst);
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
	
}



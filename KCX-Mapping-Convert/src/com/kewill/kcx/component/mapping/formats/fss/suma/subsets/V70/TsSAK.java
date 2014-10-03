package com.kewill.kcx.component.mapping.formats.fss.suma.subsets.V70;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;


/**
 * Module		:	Manifest
 * Created		:	17.06.2013
 * Description	:   Kopfsatz ReExport.
 *        			(Zabis V70) 
 *
 * @author iwaniuk
 * @version 7.0.00
 */

public class TsSAK extends Teilsatz {
	
	private String beznr = "";		// Bezugsnummer
	private String vrfart = ""; 	// Art des Verfahrens
	private String befkz = "";		// Kennzeichen des Beförderungsmittels
	private String elo = "";		// Entladeort
	private String sbnr = "";		// Sachbearbeiterkennung
	private String sbname = "";		// Sachbearbeitername
	private String sbdstl = "";		// Dienststellung des Sachbearbeiters
	private String sbtel = "";		// Telefonnummer des Sachbearbeiters	
	private String vtrkdnr = "";	// Kundennummer des Vertreters (neu in Atlas8.4)
	private String vtreori = "";	// EORI-TIN des Vertreters (neu in Atlas8.4)
	private String fltnum = "";		// Flugnummer des neuen Flugs
	private String idart	= "";	// Art der Identifikation
	private String kzbest = "";		// Kennzeichen „Bestätigung“
	private String kzflab = "";		// Kennzeichen „Flugabschluss“

	
	public TsSAK() {
		tsTyp = "SAK";
	}
	
	public String teilsatzBilden() {
		StringBuffer buff = new StringBuffer();
		buff.append(tsTyp);
		buff.append(trenner);
		buff.append(beznr);
		buff.append(trenner);
		buff.append(vrfart);
		buff.append(trenner);
		buff.append(befkz);
		buff.append(trenner);
		buff.append(elo);
		buff.append(trenner);
		buff.append(sbnr);
		buff.append(trenner);
		buff.append(sbname);
		buff.append(trenner);
		buff.append(sbdstl);
		buff.append(trenner);
		buff.append(sbtel);
		buff.append(trenner);
		buff.append(vtrkdnr);
		buff.append(trenner);
		buff.append(vtreori);
		buff.append(trenner);
		buff.append(fltnum);
		buff.append(trenner);
		buff.append(idart);
		buff.append(trenner);
		buff.append(kzbest);
		buff.append(trenner);
		buff.append(kzflab);
		buff.append(trenner);
		return new String(buff);
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
        vrfart = fields[2];
        //usw.
	}
	
	public boolean isEmpty() {
    	return Utils.isStringEmpty(vrfart) && Utils.isStringEmpty(befkz);    
    	//usw.
    }

	public String getBeznr() {
		return beznr;
	}
	public void setBeznr(String beznr) {
		this.beznr = Utils.checkNull(beznr);
	}

	public String getVrfart() {
		return vrfart;
	}
	public void setVrfart(String vrfart) {
		this.vrfart = Utils.checkNull(vrfart);
	}

	public String getBefkz() {
		return befkz;
	}
	public void setBefkz(String befkz) {
		this.befkz = Utils.checkNull(befkz);
	}

	public String getElo() {
		return elo;
	}
	public void setElo(String elo) {
		this.elo = Utils.checkNull(elo);
	}

	public String getSbnr() {
		return sbnr;
	}
	public void setSbnr(String sbnr) {
		this.sbnr = Utils.checkNull(sbnr);
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

	public String getFltnum() {
		return fltnum;
	}
	public void setFltnum(String fltnum) {
		this.fltnum = Utils.checkNull(fltnum);
	}

	public String getIdart() {
		return idart;
	}
	public void setIdart(String idart) {
		this.idart = Utils.checkNull(idart);
	}

	public String getKzbest() {
		return kzbest;
	}
	public void setKzbest(String kzbest) {
		this.kzbest = Utils.checkNull(kzbest);
	}

	public String getKzflab() {
		return kzflab;
	}
	public void setKzflab(String kzflab) {
		this.kzflab = Utils.checkNull(kzflab);
	}
	
	
}

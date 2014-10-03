package com.kewill.kcx.component.mapping.formats.fss.suma.subsets.V70;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;


/**
 * Module		:	Manifest
 * Created		:	24.05.2013
 * Description	:   Referenzierungssatz Gestellung-/Aufteilungsdaten.
 *        			Zabis Version 07.00  
 *
 * @author krzoska
 * @version 7.0.00
 */

public class TsSUR  extends Teilsatz {

	private String beznr	 = "";	// Bezugsnummer
	private String idfltblo	 = "";	// Abgabestelle/Beladeort
	private String idfltvpa	 = "";	// Vorpapierart
	private String idkzawb	 = "";	// Referenzierte Art des spezifischen Ordnungsbegriffes (AWB-Nummer oder sonstiges)
	private String idspo 	 = "";	// Spezifischer Ordnungsbegriff
	private String idvrwkdnr = "";	// ID Verwahrer Kundennummer (neu in Atlas8.4)
	private String idvrweori = "";	// ID Verwahrer EORI-TIN (neu in Atlas8.4)
	private String befnum	 = "";	// Nummer der Beförderung
	private String ankdat	 = "";	// Ankunftsdatum (neu in Atlas8.4)

		
    public TsSUR() {
        tsTyp = "SUR";
    }

    public void setFields(String[] fields) {  
		int size = fields.length;
					
		if (size < 1) { return; }		
        tsTyp = fields[0];
        if (size < 2) { return; }		
        beznr = fields[1];
        if (size < 3) { return; }	
        idfltblo = fields[2];
        if (size < 4) { return; }	
        idfltvpa = fields[3];
        //usw.... z.T wird die methode nicht gebraucht
      }

    public String teilsatzBilden() {
		StringBuffer buff = new StringBuffer();
		buff.append(tsTyp);
		buff.append(trenner);
		buff.append(beznr);
		buff.append(trenner);
		buff.append(idfltblo);
		buff.append(trenner);
		buff.append(idfltvpa);
		buff.append(trenner);
		buff.append(idkzawb);
		buff.append(trenner);
		buff.append(idspo);
		buff.append(trenner);
		buff.append(idvrwkdnr);
		buff.append(trenner);
		buff.append(idvrweori);
		buff.append(trenner);
		buff.append(befnum);
		buff.append(trenner);
		buff.append(ankdat);
		buff.append(trenner);
		return new String(buff);
	}


	public boolean isEmpty() {
		return (Utils.isStringEmpty(idfltblo) && Utils.isStringEmpty(idfltvpa) &&
			Utils.isStringEmpty(idkzawb) && Utils.isStringEmpty(idspo)) && 
			Utils.isStringEmpty(befnum) && Utils.isStringEmpty(ankdat);
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

	public String getIdfltblo() {
		return idfltblo;
	}

	public void setIdfltblo(String idfltblo) {
		this.idfltblo = Utils.checkNull(idfltblo);
	}

	public String getIdfltvpa() {
		return idfltvpa;
	}

	public void setIdfltvpa(String idfltvpa) {
		this.idfltvpa = Utils.checkNull(idfltvpa);
	}

	public String getIdkzawb() {
		return idkzawb;
	}

	public void setIdkzawb(String idkzawb) {
		this.idkzawb = Utils.checkNull(idkzawb);
	}

	public String getIdspo() {
		return idspo;
	}

	public void setIdspo(String idspo) {
		this.idspo = Utils.checkNull(idspo);
	}

	public String getIdvrwkdnr() {
		return idvrwkdnr;
	}

	public void setIdvrwkdnr(String idvrwkdnr) {
		this.idvrwkdnr = Utils.checkNull(idvrwkdnr);
	}

	public String getIdvrweori() {
		return idvrweori;
	}

	public void setIdvrweori(String idvrweori) {
		this.idvrweori = Utils.checkNull(idvrweori);
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



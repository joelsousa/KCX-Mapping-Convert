package com.kewill.kcx.component.mapping.formats.fss.suma.subsets.V70;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;


/**
 * Module		:	TsSBR
 * Created		:	31.05.2013
 * Description	:   Referenzen, wenn die zu ändernden / ergänzenden Daten über Manifest-ID angesprochen werden sollen
 *        			Zabis Version 07.00  
 *
 * @author krzoska
 * @version 2.0.00
 */

public class TsSBR  extends Teilsatz {

	public String beznr		= "";	// Bezugsnummer
	public String idregnr	= "";	// Angesprochene Registriernummer
	public String idfltblo	= "";	// Angesprochener Beladeort
	public String idfltvpa	= "";	// Angesprochene Vorpapierart
	public String befnum	= "";	// Nummer der Beförderung
	public String ankdat	= "";	// Ankunftsdatum (neu in Atlas8.4)
		
    public TsSBR() {
        tsTyp = "SBR";
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
		buff.append(idregnr);
		buff.append(trenner);
		buff.append(idfltblo);
		buff.append(trenner);
		buff.append(idfltvpa);
		buff.append(trenner);
		buff.append(befnum);
		buff.append(trenner);
		buff.append(ankdat);
		buff.append(trenner);
		
		return new String(buff);
	}


	public boolean isEmpty() {
		return (Utils.isStringEmpty(idfltblo) && Utils.isStringEmpty(idfltvpa) &&
			Utils.isStringEmpty(idregnr) && Utils.isStringEmpty(idfltblo)) && 
			Utils.isStringEmpty(idfltvpa) && Utils.isStringEmpty(befnum) &&
			Utils.isStringEmpty(ankdat);
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

	public String getIdregnr() {
		return idregnr;
	}

	public void setIdregnr(String idregnr) {
		this.idregnr = Utils.checkNull(idregnr);
	}
}



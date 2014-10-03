package com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60;
import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import java.util.ArrayList;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Modul        :   AAR
 * Teilsatz     :   TsAAR.java
 * Erstellt     :   13.06.2006
 * Beschreibung :   Referenzsatz AAR
 *
 * 03.09.2008       Version 6  Miro Houdek
 */

public class TsAAR extends Teilsatz {

    private String tsTyp     = "";         // Ts-Schlüssel
    private String beznr     = "";         // Bezugsnummer
    private String mrn       = "";         // MRN
    private String fregnr    = "";         // Registriernummer Fremdsystem
    private String spdknr    = "";         // Kundennummer Spediteur
    private String spdtin    = "";         // TIN Spediteur
    private String extdst    = "";         // Ausgangszolldienststelle
    private String dtzosp    = "";         // Kennzeichen deutsche Zollnummer

    public TsAAR() {
        tsTyp = "AAR";
    }

    public void setFields(String[] fields)
	{
		int size = fields.length;
		String ausgabe = "FSS: "+fields[0]+" size = "+ size;
		//Utils.log( ausgabe);
			
		
			if (size < 1 ) return;		
        	tsTyp = fields[0];
        	if (size < 2 ) return;
	        beznr         = fields[1];
	        if (size < 3 ) return;
	        mrn           = fields[2];
	           if (size < 4 ) return;
	           fregnr        = fields[3];
	           if (size < 5 ) return;
	           spdknr        = fields[4];
	           if (size < 6 ) return;
	           spdtin        = fields[5];
	           if (size < 7 ) return;
	           extdst        = fields[6];
	           if (size < 8 ) return;
	           dtzosp        = fields[7];
	      }


    public String teilsatzBilden() {
        StringBuffer buff = new StringBuffer();

        buff.append(tsTyp);
        buff.append(trenner);
        buff.append(beznr);
        buff.append(trenner);
        buff.append(mrn);
        buff.append(trenner);
        buff.append(fregnr);
        buff.append(trenner);
        buff.append(spdknr);
        buff.append(trenner);
        buff.append(spdtin);
        buff.append(trenner);
        buff.append(extdst);
        buff.append(trenner);
        buff.append(dtzosp);
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

	public String getMrn() {
		return mrn;
	
	}

	public void setMrn(String mrn) {
		this.mrn = Utils.checkNull(mrn);
	}

	public String getFregnr() {
		return fregnr;
	
	}

	public void setFregnr(String fregnr) {
		this.fregnr = Utils.checkNull(fregnr);
	}

	public String getSpdknr() {
		return spdknr;
	
	}

	public void setSpdknr(String spdknr) {
		this.spdknr = Utils.checkNull(spdknr);
	}

	public String getSpdtin() {
		return spdtin;
	
	}

	public void setSpdtin(String spdtin) {
		this.spdtin = Utils.checkNull(spdtin);
	}

	public String getExtdst() {
		return extdst;
	
	}

	public void setExtdst(String extdst) {
		this.extdst = Utils.checkNull(extdst);
	}

	public String getDtzosp() {
		return dtzosp;
	
	}

	public void setDtzosp(String dtzosp) {
		this.dtzosp = Utils.checkNull(dtzosp);
	}
	
	public boolean isEmpty() {
		//Utils.isStringEmpty(beznr)  &&
		if ( Utils.isStringEmpty(mrn) && Utils.isStringEmpty(fregnr) 
				&& Utils.isStringEmpty(spdknr) && Utils.isStringEmpty(spdtin) 		  
		  && Utils.isStringEmpty(extdst) && Utils.isStringEmpty(dtzosp))	
			return true;
		else
			return false;
	} 
	
}
 
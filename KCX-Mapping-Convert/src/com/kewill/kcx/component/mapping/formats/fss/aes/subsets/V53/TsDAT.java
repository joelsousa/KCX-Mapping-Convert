package com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V53;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/*
* Funktion    : TsDAT.java
* Titel       :
* Erstellt    : 03.09.2008
* Author      : CSF GmbH / Alfred Krzoska
* Beschreibung: 
* Anmerkungen : 
* Parameter   : 
* R�ckgabe    : keine
* Aufruf      : 
*
* �nderungen:
* -----------
* Author      :
* Datum       :
* Kennzeichen :
* Beschreibung:
* Anmerkungen :
* Parameter   :
*
*/

public class TsDAT extends Teilsatz {
    
	private String beznr  = "";      // Bezugsnummer 
    private String kuatnr = "";      // externe Kundenauftragsnummer
    private String mrn    = "";      // Movement-Reference-Number
    private String kzart  = "";      // Ausgangsbenachrichtigung 0 = eingehend 1= �berlassung 2 = Beendigung
    private String expdst = "";      // Ausfuhrdienststelle Einheitspapier Feld A
    private String eamdst = "";      // Dienststelle der EAM
    private String kdnran = "";      // Kundennummer des Anmelder
    private String tinan  = "";      // TIN des Anmelders
    //private String dtzoan =" ";      //in V5.3 is in EDA; TIN des Anmelders ist dt. Zollnummer
    private String etnan  = "";      // ETN-Adresse des Anmelders
    private String kdnrem = "";      // Kundennummer des Empf�ngers
    private String tinem  = "";      // TIN des Empf�ngers
    private String kdnrsp = "";      // Kundennummer des Spediteurs am Ausgang
    private String etnsp  = "";      // ETN-Adresse des Spediteurs am Ausgang
    private String quelkz = "";      // Vorverfahren
    private String sb     = "";      // Sachbearbeiter
    private String vekan  = "";      // CSF-Kundennummer des Vortragsempf�ngers
    
    public TsDAT() {
        tsTyp = "DAT";
    }
    
    public String teilsatzBilden() {
        StringBuffer buff = new StringBuffer();
          
        buff.append(tsTyp);
        buff.append(trenner);        
        buff.append(beznr);
        buff.append(trenner);	
        buff.append(kuatnr);
        buff.append(trenner);	
        buff.append(mrn);
        buff.append(trenner);	
        buff.append(kzart);
        buff.append(trenner);	
        buff.append(expdst);
        buff.append(trenner);	
        buff.append(eamdst);
        buff.append(trenner);	
        buff.append(kdnran);
        buff.append(trenner);	
        buff.append(tinan);
        buff.append(trenner);	
       // buff.append(dtzoan);
       // buff.append(trenner);	
        buff.append(etnan);
        buff.append(trenner);	
        buff.append(kdnrem);
        buff.append(trenner);	
        buff.append(tinem);
        buff.append(trenner);	
        buff.append(kdnrsp);
        buff.append(trenner);	
        buff.append(etnsp);
        buff.append(trenner);	
        buff.append(quelkz);
        buff.append(trenner);	
        buff.append(sb);
        buff.append(trenner);	
        buff.append(vekan);
        buff.append(trenner);	
        
    	return buff.toString();
    }

	
	public void setFields(String[] fields) {
		int size = fields.length;
		String ausgabe = "FSS: "+fields[0]+" size = "+ size;
		//Utils.log( ausgabe);
		
		if (size < 1 ) return;		
        tsTyp   = fields[0];
        if (size < 2 ) return;	
        beznr   = fields[1];
        if (size < 3 ) return;	
        kuatnr  = fields[2];
        if (size < 4 ) return;	
        mrn		= fields[3];
        if (size < 5 ) return;	
        kzart   = fields[4];
        if (size < 6 ) return;	
        expdst  = fields[5];
        if (size < 7 ) return;	
        eamdst	= fields[6];
        if (size < 8 ) return;	
        kdnran	= fields[7];
        if (size < 9 ) return;	
        tinan	= fields[8];
        //if (size < 10 ) return;
        //dtzoan	= fields[9];
        if (size < 10 ) return;
        etnan	= fields[9];
        if (size < 11 ) return;
        kdnrem	= fields[10];
        if (size < 12 ) return;
        tinem	= fields[11];
        if (size < 13 ) return;
        kdnrsp	= fields[12];
        if (size < 14 ) return;
        etnsp	= fields[13];
        if (size < 15) return;
        quelkz	= fields[14];
        if (size < 16 ) return;
        sb		= fields[15];
        if (size < 17 ) return;
        vekan	= fields[16];
    }

	public String getBeznr() {
		return beznr;
	}

	public void setBeznr(String beznr) {
		this.beznr = Utils.checkNull(beznr);
	}

	public String getKuatnr() {
		return kuatnr;
	}

	public void setKuatnr(String kuatnr) {
		this.kuatnr = Utils.checkNull(kuatnr);
	}

	public String getMrn() {
		return mrn;
	}

	public void setMrn(String mrn) {
		this.mrn = Utils.checkNull(mrn);
	}

	public String getKzart() {
		return kzart;
	}

	public void setKzart(String kzart) {
		this.kzart = Utils.checkNull(kzart);
	}

	public String getExpdst() {
		return expdst;
	}

	public void setExpdst(String expdst) {
		this.expdst = Utils.checkNull(expdst);
	}

	public String getEamdst() {
		return eamdst;
	}

	public void setEamdst(String eamdst) {
		this.eamdst = Utils.checkNull(eamdst);
	}

	public String getKdnran() {
		return kdnran;
	}

	public void setKdnran(String kdnran) {
		this.kdnran = Utils.checkNull(kdnran);
	}

	public String getTinan() {
		return tinan;
	}

	public void setTinan(String tinan) {
		this.tinan = Utils.checkNull(tinan);
	}
	/*
	public String getDtzoan() {
		return dtzoan;
	}

	public void setDtzoan(String dtzoan) {
		this.dtzoan = Utils.checkNull(dtzoan);
	}
	*/
	public String getEtnan() {
		return etnan;
	}

	public void setEtnan(String etnan) {
		this.etnan = Utils.checkNull(etnan);
	}

	public String getKdnrem() {
		return kdnrem;
	}

	public void setKdnrem(String kdnrem) {
		this.kdnrem = Utils.checkNull(kdnrem);
	}

	public String getTinem() {
		return tinem;
	}

	public void setTinem(String tinem) {
		this.tinem = Utils.checkNull(tinem);
	}

	public String getKdnrsp() {
		return kdnrsp;
	}

	public void setKdnrsp(String kdnrsp) {
		this.kdnrsp = Utils.checkNull(kdnrsp);
	}

	public String getEtnsp() {
		return etnsp;
	}

	public void setEtnsp(String etnsp) {
		this.etnsp = Utils.checkNull(etnsp);
	}

	public String getQuelkz() {
		return quelkz;
	}

	public void setQuelkz(String quelkz) {
		this.quelkz = Utils.checkNull(quelkz);
	}

	public String getSb() {
		return sb;
	}

	public void setSb(String sb) {
		this.sb = Utils.checkNull(sb);
	}

	public String getVekan() {
		return vekan;
	}

	public void setVekan(String vekan) {
		this.vekan = Utils.checkNull(vekan);
	}

	public String getDAT() {
		return vekan;
	}

	public boolean isEmpty() {
		//Utils.isStringEmpty(beznr)  &&
		if ( Utils.isStringEmpty(kuatnr) && Utils.isStringEmpty(mrn)
		  && Utils.isStringEmpty(kzart) && Utils.isStringEmpty(expdst) && Utils.isStringEmpty(eamdst)
		  && Utils.isStringEmpty(kdnran) && Utils.isStringEmpty(tinan) 
		  && Utils.isStringEmpty(etnan)  && Utils.isStringEmpty(kdnrem) && Utils.isStringEmpty(tinem) 
		  && Utils.isStringEmpty(kdnrsp)  && Utils.isStringEmpty(etnsp)  && Utils.isStringEmpty(quelkz) 
		  && Utils.isStringEmpty(sb)  && Utils.isStringEmpty(vekan) )
			return true;
		else
			return false;
	}
	
}

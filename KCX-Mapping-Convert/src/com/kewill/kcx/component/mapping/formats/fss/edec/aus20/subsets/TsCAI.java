package com.kewill.kcx.component.mapping.formats.fss.edec.aus20.subsets;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.MsgExpDat;
import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: EDEC 70<br>
 * Created		: 26.10.2012<br>
 * Description  : FSS Definition of Subset CAI. 
 * 
 * @author iwaniuk
 * @version 7.0.00
 */

public class TsCAI extends Teilsatz {

    private String beznr = "";       	// ReferenceNumber
    private String cdkorgrd = "";    	// Berichtigungsgrund
    private String dectim = "";			// declarationTime
    private String bezlag = "";      	// Bezeichnung des Lagers 
    private String verort = "";      	// Veranlagungsort
    private String kzzv = "";    		// Kennzeichen Zugelassener Versender 
    private String kzsec = "";			// Kennzeichen Security
    private String tinvs = "";      	// TIN Versender 
    private String tinem = "";      	// TIN Empfänger
    private String tinvsc = "";			// TIN Versender Security
    private String tinesc = "";			// TIN Empfänger
    private String tinzul = "";			// TIN Zulader 
    private String tintra = "";			// TIN Transporteur 
    private String tinspd = "";			// TIN Spediteur
    private String besums = "";			// Kennnummer für besondere Umstände
    private String ucr    = "";			// Unique consignment reference number 
    private String kzinj  = "";			// Antrag 2. Veranlagungsverfügung 
    private String waort  = "";			// Bewilligter Warenort 
    private String cdtype = "";			// Anmeldungstyp
    private String cdprvgrd	= "";		// Grund für provisorische Abfertigung reason
    private String cdgsart	= "";			// Geschäftsartencode    
    private String abladort= "";	//new V70  	// Abladeort               
    //private String ladort	= "";			    // Ladeort
    private String cdinco	= "";		// Incoterm
    private String fnrstpf	= "";		// Firmennummer Steuerpflichtiger
    private String kunrem	= "";		// Kundennummer Empfänger 
    private String kunrvsc	= "";		// Kundennummer des Versenders
    private String kunresc	= "";		// Kundennummer des Empfängers
    private String kunrzul	= "";		// Kundennummer des Zuladeorts 
    private String kunrtra	= "";		// Kundennummer des Transporteurs
    private String dknr		= "";	    // Deklarantennummer (muss beim Schweizer Zoll zur Spediteurnummer hinterlegt sein)
    private String whcod    = "";	//new V70  	// Rechnungswährungscode      new V70								
    									        //Codierung: 1 = CHF, 2 = EUR, 3 = andere Europa, 4 = USD, 5 = sonstige
    
    
    public TsCAI() {
        tsTyp = "CAI";
    }
    public void setFields(String[] fields)
    {
		int size = fields.length;
		String ausgabe = "FSS: "+fields[0]+" size = "+ size;
		//Utils.log( ausgabe);
		
		if (size < 1) return;		
        	tsTyp = fields[0];
        if (size < 2) return;	
        	beznr = fields[1];
        if (size < 3) return;	
        	cdkorgrd = fields[2];
        if (size < 4) return;	
        	dectim = fields[3];
        if (size < 5) return;	
        	bezlag = fields[4];        
        if (size < 6) return;	
        	verort  = fields[5];
        if (size < 7) return;	
        	kzzv = fields[6];
        if (size < 8) return;	
        	kzsec = fields[7];
        if (size < 9) return;	
        	tinvs = fields[8];
        if (size < 10) return;	
        	tinem = fields[9];
        if (size < 11) return;	
        	tinvsc = fields[10];
        if (size < 12) return;	
        	tinesc = fields[11];
        if (size < 13) return;	
        	tinzul = fields[12];
        if (size < 14) return;	
        	tintra = fields[13];
        if (size < 15) return;	
        	tinspd = fields[14];
        if (size < 16) return;	
        	besums = fields[15];
        if (size < 17) return;	
        	ucr = fields[16];
        if (size < 18) return;	
        	kzinj = fields[17];
        if (size < 19) return;	
        	waort = fields[18];
        if (size < 20) return;	
        	cdtype = fields[19];
        if (size < 21) return;	
        	cdprvgrd = fields[20];
        if (size < 22) return;	
        	cdgsart  = fields[21];
        if (size < 23) return;	
        	abladort = fields[22];
        if (size < 24) return;	
        	cdinco = fields[23];         	
        if (size < 25) return;	
        	fnrstpf = fields[24];
        if (size < 26) return;	
        	kunrem = fields[25];
        if (size < 27) return;	
        	kunrvsc  = fields[26];
        if (size < 28) return;	
        	kunresc = fields[27];
        if (size < 29) return;	
        	kunrzul = fields[28];         	
        if (size < 30) return;	
        	kunrtra = fields[29];
        if (size < 31) return;	
        	dknr = fields[30];
        	
    }

    public String teilsatzBilden() {
        StringBuffer buff = new StringBuffer();

        buff.append(tsTyp);
        buff.append(trenner);
        buff.append(beznr);
        buff.append(trenner);
        buff.append(cdkorgrd);
        buff.append(trenner);
        buff.append(dectim);
        buff.append(trenner);
        buff.append(bezlag);
        buff.append(trenner);
        buff.append(verort);
        buff.append(trenner);
        buff.append(kzzv);
        buff.append(trenner);
        buff.append(kzsec);
        buff.append(trenner);
        buff.append(tinvs);
        buff.append(trenner);
        buff.append(tinem);
        buff.append(trenner);
        buff.append(tinvsc);
        buff.append(trenner);
        buff.append(tinesc);
        buff.append(trenner);
        buff.append(tinzul);
        buff.append(trenner);
        buff.append(tintra);
        buff.append(trenner);
        buff.append(tinspd);
        buff.append(trenner);
        buff.append(besums);
        buff.append(trenner);
        buff.append(ucr);
        buff.append(trenner);
        buff.append(kzinj);
        buff.append(trenner);
        buff.append(waort);
        buff.append(trenner);
        buff.append(cdtype);
        buff.append(trenner);
        buff.append(cdprvgrd);
        buff.append(trenner);
        buff.append(cdgsart);
        buff.append(trenner);
        buff.append(abladort);
        buff.append(trenner);
        buff.append(cdinco);
        buff.append(trenner);
        buff.append(fnrstpf);
        buff.append(trenner);
        buff.append(kunrem);
        buff.append(trenner);
        buff.append(kunrvsc);
        buff.append(trenner);
        buff.append(kunresc);
        buff.append(trenner);
        buff.append(kunrzul);
        buff.append(trenner);
        buff.append(kunrtra);
        buff.append(trenner);
        buff.append(dknr);
        //EI20110510: buff.append(dknr);
        buff.append(trenner); //EI20110510

        return new String(buff);
      }


	public void setCaiSubset(MsgExpDat msgExpDat) {
		
	}	
	
	public boolean isEmpty() {
	    if ( Utils.isStringEmpty(cdkorgrd) &&    
			 Utils.isStringEmpty(dectim) &&	
		     Utils.isStringEmpty(bezlag) &&      
			 Utils.isStringEmpty(verort) &&      
			 Utils.isStringEmpty(kzzv) &&    	
			 Utils.isStringEmpty(kzsec) &&	
			 Utils.isStringEmpty(tinvs) &&      
			 Utils.isStringEmpty(tinem) &&      
			 Utils.isStringEmpty(tinvsc) &&	
			 Utils.isStringEmpty(tinesc) &&	
			 Utils.isStringEmpty(tinzul) &&	
			 Utils.isStringEmpty(tintra) &&	
			 Utils.isStringEmpty(tinspd) &&	
			 Utils.isStringEmpty(besums) &&	
			 Utils.isStringEmpty(ucr)  &&	
			 Utils.isStringEmpty(kzinj) &&	
			 Utils.isStringEmpty(waort) &&	
			 Utils.isStringEmpty(cdtype) &&	
			 Utils.isStringEmpty(cdprvgrd) &&	
			 Utils.isStringEmpty(cdgsart)  &&	
			 Utils.isStringEmpty(abladort) &&	
			 Utils.isStringEmpty(cdinco)  &&	
			 Utils.isStringEmpty(fnrstpf) &&		
			 Utils.isStringEmpty(kunrem)  &&		
			 Utils.isStringEmpty(kunrvsc) &&		
			 Utils.isStringEmpty(kunresc) &&		
			 Utils.isStringEmpty(kunrzul) &&		
			 Utils.isStringEmpty(kunrtra) &&
			 Utils.isStringEmpty(dknr)) { 
	    	return true;
		} else {
			return false;
		}
	}
	
	public String getBeznr() {
		return beznr;	
	}
	
	public void setBeznr(String beznr) {
		this.beznr = Utils.checkNull(beznr);
	}
	public String getCdkorgrd() {
		return cdkorgrd;	
	}
	
	public void setCdkorgrd(String cdkorgrd) {
		this.cdkorgrd = Utils.checkNull(cdkorgrd);
	}
	public String getDectim() {
		return dectim;	
	}
	
	public void setDectim(String dectim) {
		this.dectim = Utils.checkNull(dectim);
	}
	public String getBezlag() {
		return bezlag;	
	}
	
	public void setBezlag(String bezlag) {
		this.bezlag = Utils.checkNull(bezlag);
	}
	public String getVerort() {
		return verort;
	
	}
	public void setVerort(String verort) {
		this.verort = Utils.checkNull(verort);
	}
	public String getKzzv() {
		return kzzv;	
	}
	
	public void setKzzv(String kzzv) {
		this.kzzv = Utils.checkNull(kzzv);
	}
	public String getKzsec() {
		return kzsec;	
	}
	
	public void setKzsec(String kzsec) {
		this.kzsec = Utils.checkNull(kzsec);
	}
	public String getTinvs() {
		return tinvs;
	
	}
	public void setTinvs(String tinvs) {
		this.tinvs = Utils.checkNull(tinvs);
	}
	
	public String getTinem() {
		return tinem;	
	}
	
	public void setTinem(String tinem) {
		this.tinem = Utils.checkNull(tinem);
	}
	
	public String getTinvsc() {
		return tinvsc;	
	}
	
	public void setTinvsc(String tinvsc) {
		this.tinvsc = Utils.checkNull(tinvsc);
	}
	public String getTinesc() {
		return tinesc;	
	}
	
	public void setTinesc(String tinesc) {
		this.tinesc = Utils.checkNull(tinesc);
	}
	
	public String getTinzul() {
		return tinzul;	
	}
	public void setTinzul(String tinzul) {
		this.tinzul = Utils.checkNull(tinzul);
	}
	
	public String getTintra() {
		return tintra;	
	}
	
	public void setTintra(String tintra) {
		this.tintra = Utils.checkNull(tintra);
	}
	
	public String getTinspd() {
		return tinspd;	
	}
	
	public void setTinspd(String tinspd) {
		this.tinspd = Utils.checkNull(tinspd);
	}
	public String getBesums() {
		return besums;	
	}
	
	public void setBesums(String besums) {
		this.besums = Utils.checkNull(besums);
	}
	
	public String getUcr() {
		return ucr;	
	}
	
	public void setUcr(String ucr) {
		this.ucr = Utils.checkNull(ucr);
	}
	public String getKzinj() {
		return kzinj;	
	}
	
	public void setKzinj(String kzinj) {
		this.kzinj = Utils.checkNull(kzinj);
	}
	public String getWaort() {
		return waort;	
	}
	
	public void setWaort(String waort) {
		this.waort = Utils.checkNull(waort);
	}
	
	public String getCdtype() {
		return cdtype;	
	}
	
	public void setCdtype(String cdtype) {
		this.cdtype = Utils.checkNull(cdtype);
	}
	
	public String getCdprvgrd() {
		return cdprvgrd;	
	}
	
	public void setCdprvgrd(String cdprvgrd) {
		this.cdprvgrd = Utils.checkNull(cdprvgrd);
	}
	
	public String getCdgsart() {
		return cdgsart;	
	}
	
	public void setCdgsart(String cdgsart) {
		this.cdgsart = Utils.checkNull(cdgsart);
	}
	
	public String getCdinco() {
		return cdinco;	
	}
	
	public void setCdinco(String cdinco) {
		this.cdinco = Utils.checkNull(cdinco);
	}
	
	public String getFnrstpf() {
		return fnrstpf;	
	}
	
	public void setFnrstpf(String fnrstpf) {
		this.fnrstpf = Utils.checkNull(fnrstpf);
	}
	
	public String getKunrem() {
		return kunrem;	
	}
	
	public void setKunrem(String kunrem) {
		this.kunrem = Utils.checkNull(kunrem);
	}
	
	public String getKunrvsc() {
		return kunrvsc;	
	}
	
	public void setKunrvsc(String kunrvsc) {
		this.kunrvsc = Utils.checkNull(kunrvsc);
	}
	
	public String getKunresc() {
		return kunresc;	
	}
	
	public void setKunresc(String kunresc) {
		this.kunresc = Utils.checkNull(kunresc);
	}
	
	public String getKunrzul() {
		return kunrzul;	
	}
	
	public void setKunrzul(String kunrzul) {
		this.kunrzul = Utils.checkNull(kunrzul);
	}
	
	public String getKunrtra() {
		return kunrtra;	
	}
	
	public void setKunrtra(String kunrtra) {
		this.kunrtra = Utils.checkNull(kunrtra);
	}
	
	public String getAbladort() {
		return abladort;	
	}
	public void setAbladort(String ladort) {
		this.abladort = Utils.checkNull(ladort);
	}
	
	public String getDknr() {
		return dknr;	
	}
	
	public void setDknr(String dknr) {
		this.dknr = Utils.checkNull(dknr);
	}
	
	public String getWhcod() {
		return whcod;	
	}
	
	public void setWhcod(String dknr) {
		this.whcod = Utils.checkNull(dknr);
	}
}



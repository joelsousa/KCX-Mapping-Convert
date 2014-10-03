/*
* Funktion    : TsCAN.java
* Titel       :
* Erstellt    : 20.10.2008
* Author      : Alfred Krzoska
* Beschreibung: Export Antragsteil Declaration  
* Anmerkungen :
* Parameter   :
* Rückgabe    : keine
* Aufruf      :
*
* Changes 
* -----------
* Author      : AK
* Date        : 19.03.2010
* Label       : AK20100319
* Description : MsgKids_old replaced with MsgExpDat
*
* Changes 
* -----------
* Author      : 
* Date        : 
* Label       : 
* Description : 
*
*/

package com.kewill.kcx.component.mapping.formats.fss.edec.aus.subsets;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.MsgExpDat;
import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

public class TsCAN extends Teilsatz {

    private String beznr = "";       //Bezugsnummer
    private String korant = "";      // Korrekturnummer 
    private String dklart = "";      // Art der Deklaration
    private String sammnr = "";      // Sammelsendungsnummer
    private String aufnr = "";       //Auftragsnummer des Partnersystems
    private String tstat = "";       //T-Status der Deklaration
    private String cdstat = "";      //Statuscode der Deklaration
    private String lang = "";        //Sprachkennzeichen
    private String kunrvs= "";       //Kundennummer des Versenders (nicht Spediteurnummer!).
    private String spdnr = "";       //Spediteurnummer des Deklaranten
    private String ausdst = "";      //Ausgangszollstelle, NCTS-Code
    private String abgdst = "";      //Abgangszollstelle, NCTS-Code
    private String kzncts = "";      //Kennzeichen, dass diese Deklaration ein Versand wird
    private String ldvs = "";        //Versendungsland
    private String ldbe = "";        //Bestimmungsland für den ganzen Antrag
    private String vzgr = "";        //Verkehrszweig an der Grenze, codiert.
    private String bfabkz = "";      //Kennzeichen des Beförderungsmittels beim Abgang
    private String bfabld = "";      //Nationalität des Beförderungsmittels beim Abgang
    private String kzcont = "";      //KZ "Container"
    private String ladort = "";      //Bewilligter Ladeort
    private String gsroh = "";       //Gesamt-Rohmasse
    private String mwstnr = "";      //Mehrwertsteuernummer
    
    
    public TsCAN() {
        tsTyp = "CAN";
    }

    public void setFields(String[] fields)
    {
		int size = fields.length;
		String ausgabe = "FSS: "+fields[0]+" size = "+ size;
		//Utils.log( ausgabe);
			
		
		if (size < 1 ) return;		
        	tsTyp = fields[0];
        if (size < 2 ) return;	
        	beznr = fields[1];
        if (size < 3 ) return;	
        	korant = fields[2];
        if (size < 4 ) return;	
        	dklart= fields[3];
        if (size < 5 ) return;	
        	sammnr = fields[4];        
        if (size < 6 ) return;	
        	aufnr = fields[5];
        if (size < 7 ) return;	
        	tstat = fields[6];
        if (size < 8 ) return;	
        	cdstat = fields[7];        	
        if (size < 9 ) return;	
        	lang = fields[8];
        if (size < 10 ) return;	
        	kunrvs = fields[9];
        if (size < 11 ) return;	
        	spdnr = fields[10];        
        if (size < 12 ) return;	
        	ausdst = fields[11];
        if (size < 13 ) return;	
         	abgdst = fields[12];
        if (size < 14 ) return;	
        	kzncts = fields[13];        	
        if (size < 15 ) return;	
        	ldvs = fields[14];
        if (size < 16 ) return;	
        	ldbe = fields[15];
        if (size < 17 ) return;	
        	vzgr = fields[16];
        if (size < 18 ) return;	
        	bfabkz = fields[17];        
        if (size < 19 ) return;	
        	bfabld = fields[18];
        if (size < 20 ) return;	
        	kzcont = fields[19];
        if (size < 21 ) return;	
         	ladort = fields[20];        	
        if (size < 22 ) return;	
        	gsroh = fields[21];
        if (size < 23 ) return;	
         	mwstnr = fields[22];        	
         	
    }

    public String teilsatzBilden() {
        StringBuffer buff = new StringBuffer();

        buff.append(tsTyp);
        buff.append(trenner);
        buff.append(beznr);
        buff.append(trenner);
        buff.append(korant);
        buff.append(trenner);
        buff.append(dklart);
        buff.append(trenner);
        buff.append(sammnr);
        buff.append(trenner);
        buff.append(aufnr);
        buff.append(trenner);
        buff.append(tstat);
        buff.append(trenner);
        buff.append(cdstat);
        buff.append(trenner);
        buff.append(lang);	
        buff.append(trenner);
        buff.append(kunrvs);
        buff.append(trenner);
        buff.append(spdnr);
        buff.append(trenner);
        buff.append(ausdst);
        buff.append(trenner);
        buff.append(abgdst);
        buff.append(trenner);
        buff.append(kzncts);
        buff.append(trenner);
        buff.append(ldvs);	
        buff.append(trenner);
        buff.append(ldbe);	
        buff.append(trenner);
        buff.append(vzgr);	
        buff.append(trenner);
        buff.append(bfabkz);
		buff.append(trenner);
		buff.append(bfabld);
		buff.append(trenner);
		buff.append(kzcont);
		buff.append(trenner);
		buff.append(ladort);
		buff.append(trenner);
		buff.append(gsroh);
		buff.append(trenner);
		buff.append(mwstnr);
		buff.append(trenner);

        return new String(buff);
      }

    //AK20100319 
    public void setCanSubset(MsgExpDat msgKids) {		
		
		if (Utils.isStringEmpty(msgKids.getReferenceNumber())) return;

		this.setBeznr(msgKids.getReferenceNumber());
		this.setKorant(msgKids.getCorrectionCode());
		this.setDklart(msgKids.getKindOfDeclaration());
		this.setSammnr(msgKids.getBunchNumber());		
		this.setAufnr(msgKids.getOrderNumber());
		this.setTstat(msgKids.getNCTSType());
		
		this.setCdstat(msgKids.getTypeOfPermitCH());
		this.setLang(msgKids.getLanguage());

		if (msgKids.getConsignor() != null && msgKids.getConsignor().getPartyTIN() != null) {
			this.setKunrvs(msgKids.getConsignor().getPartyTIN().getCustomerIdentifier());
		}

		if (msgKids.getDeclarant() != null && msgKids.getDeclarant().getPartyTIN() != null){
				this.setSpdnr(msgKids.getDeclarant().getPartyTIN().getTIN());
		}
		
		this.setAusdst(msgKids.getIntendedOfficeOfExit());
		this.setAbgdst(msgKids.getCustomsOfficeExport());
		this.setKzncts(msgKids.getTransferToTransitSystem());
		this.setLdvs(msgKids.getDispatchCountry());
		this.setLdbe(msgKids.getDestinationCountry());
		
		if (msgKids.getTransportMeansBorder() != null )  {
			this.setVzgr(msgKids.getTransportMeansBorder().getTransportMode());
		}
		
		if (msgKids.getTransportMeansDeparture() != null )  {
			this.setBfabkz(msgKids.getTransportMeansDeparture().getTransportationNumber());
			this.setBfabld(msgKids.getTransportMeansDeparture().getTransportationCountry());			
		}
		this.setKzcont(msgKids.getTransportInContainer());
		if (msgKids.getPlaceOfLoading()!= null )  {
			this.setLadort(msgKids.getPlaceOfLoading().getAgreedLocationOfGoods() );
		}
		this.setGsroh(msgKids.getGrossMass());
		if (msgKids.getForwarder()!= null )  {
			this.setMwstnr(msgKids.getForwarder().getVATNumber());
		}
	}
    

	public boolean isEmpty() {
		  if (     Utils.isStringEmpty(korant)
			 	&& Utils.isStringEmpty(dklart)
			 	&& Utils.isStringEmpty(sammnr)
			 	&& Utils.isStringEmpty(aufnr) 
			 	&& Utils.isStringEmpty(tstat) 
			 	&& Utils.isStringEmpty(cdstat)
			 	&& Utils.isStringEmpty(lang) 
			 	&& Utils.isStringEmpty(kunrvs)
			 	&& Utils.isStringEmpty(spdnr) 
			 	&& Utils.isStringEmpty(ausdst)
			 	&& Utils.isStringEmpty(abgdst)
			 	&& Utils.isStringEmpty(kzncts)
			 	&& Utils.isStringEmpty(ldvs) 
			 	&& Utils.isStringEmpty(ldbe) 
			 	&& Utils.isStringEmpty(vzgr) 
			 	&& Utils.isStringEmpty(bfabkz)
			 	&& Utils.isStringEmpty(bfabld)
			 	&& Utils.isStringEmpty(kzcont)
			 	&& Utils.isStringEmpty(ladort)
			 	&& Utils.isStringEmpty(gsroh) 
			 	&& Utils.isStringEmpty(mwstnr)
		  	  )
			return true;
		else
			return false;
	}

	public String getBeznr() {
		return beznr;
	
	}

	public void setBeznr(String beznr) {
		this.beznr = Utils.checkNull(beznr);
	}

	public String getKorant() {
		return korant;
	
	}

	public void setKorant(String korant) {
		this.korant = Utils.checkNull(korant);
	}

	public String getDklart() {
		return dklart;
	
	}

	public void setDklart(String dklart) {
		this.dklart = Utils.checkNull(dklart);
	}

	public String getSammnr() {
		return sammnr;
	
	}

	public void setSammnr(String sammnr) {
		this.sammnr = Utils.checkNull(sammnr);
	}

	public String getAufnr() {
		return aufnr;
	
	}

	public void setAufnr(String aufnr) {
		this.aufnr = Utils.checkNull(aufnr);
	}

	public String getTstat() {
		return tstat;
	
	}

	public void setTstat(String tstat) {
		this.tstat = Utils.checkNull(tstat);
	}

	public String getCdstat() {
		return cdstat;
	
	}

	public void setCdstat(String cdstat) {
		this.cdstat = Utils.checkNull(cdstat);
	}

	public String getLang() {
		return lang;
	
	}

	public void setLang(String lang) {
		this.lang = Utils.checkNull(lang);
	}

	public String getKunrvs() {
		return kunrvs;
	
	}

	public void setKunrvs(String kunrvs) {
		this.kunrvs = Utils.checkNull(kunrvs);
	}

	public String getSpdnr() {
		return spdnr;
	
	}

	public void setSpdnr(String spdnr) {
		this.spdnr = Utils.checkNull(spdnr);
	}

	public String getAusdst() {
		return ausdst;
	
	}

	public void setAusdst(String ausdst) {
		this.ausdst = Utils.checkNull(ausdst);
	}

	public String getAbgdst() {
		return abgdst;
	
	}

	public void setAbgdst(String abgdst) {
		this.abgdst = Utils.checkNull(abgdst);
	}

	public String getKzncts() {
		return kzncts;
	
	}

	public void setKzncts(String kzncts) {
		this.kzncts = Utils.checkNull(kzncts);
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

	public String getVzgr() {
		return vzgr;
	
	}

	public void setVzgr(String vzgr) {
		this.vzgr = Utils.checkNull(vzgr);
	}

	public String getBfabkz() {
		return bfabkz;
	
	}

	public void setBfabkz(String bfabkz) {
		this.bfabkz = Utils.checkNull(bfabkz);
	}

	public String getBfabld() {
		return bfabld;
	
	}

	public void setBfabld(String bfabld) {
		this.bfabld = Utils.checkNull(bfabld);
	}

	public String getKzcont() {
		return kzcont;
	
	}

	public void setKzcont(String kzcont) {
		this.kzcont = Utils.checkNull(kzcont);
	}

	public String getLadort() {
		return ladort;
	
	}

	public void setLadort(String ladort) {
		this.ladort = Utils.checkNull(ladort);
	}

	public String getGsroh() {
		return gsroh;
	
	}

	public void setGsroh(String gsroh) {
		this.gsroh = Utils.checkNull(gsroh);
	}

	public String getMwstnr() {
		return mwstnr;
	
	}

	public void setMwstnr(String mwstnr) {
		this.mwstnr = Utils.checkNull(mwstnr);
	}
}



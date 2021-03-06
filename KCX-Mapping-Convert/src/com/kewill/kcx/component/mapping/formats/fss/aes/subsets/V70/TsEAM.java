package com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V70;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.MsgExpEnt;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.Business;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.IncoTerms;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.TransportMeans;
import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		:	Export/aes
 * Created		:	25.07.2012
 * Description	:	Ergänzende Ausfuhranmeldung. 
 *
 * @author 	iwaniuk
 * @version 2.1.00 (Zabis V70)
 */

public class TsEAM extends Teilsatz {
  
    private String beznr  = "";       //  Bezugsnummer
    private String eamdat = "";       //  Zeitpunkt der EAM
    private String bfvkzi = "";       //  Verkehrszweig Inland
    private String bfvkzg = "";       //  Verkehrszweig an der Grenze
    private String bfartg = "";       //  Art des Beförderungsmittel an der Grenze
    private String bfkzg  = "";       //  Kennzeichen des Beförderungsmittels an der Grenze
    private String bfnatg = "";       //  Nationalität des Beförderungsmittels an der Grenze
    private String gesart = "";       //  Geschäftsart
    private String gesprs = "";       //  Rechnungspreis
    private String geswrg = "";       //  Rechnungswährung
    private String libart = "";       //  Lieferbedingung Incoterm-Code
    private String libinc = "";       //  Lieferbedingung Text
    private String libort = "";       //  Lieferbedingung Ort
    private String adrkon = ""; 

    public TsEAM() {
        tsTyp = "EAM";
    } 

    public void setFields(String[] fields) {    
    	int size = fields.length;
    	
    	if (size < 1)  { return; }	
    	tsTyp   = fields[0];
    	if (size < 2)  { return; }	
        beznr   = fields[1];
        if (size < 3)  { return; }	
        eamdat  = fields[2];
        if (size < 4)  { return; }	
        bfvkzi  = fields[3];
        if (size < 5)  { return; }	
        bfvkzg  = fields[4];
        if (size < 6)  { return; }	
        bfartg  = fields[5];
        if (size < 7)  { return; }	
        bfkzg   = fields[6];
        if (size < 8)  { return; }	
        bfnatg  = fields[7];
        if (size < 9)  { return; }	
        gesart  = fields[8];
        if (size < 10) { return; }	
        gesprs  = fields[9];
        if (size < 11) { return; }	
        geswrg  = fields[10];
        if (size < 12) { return; }	
        libart  = fields[11];
        if (size < 13) { return; }	
        libinc  = fields[12];
        if (size < 14) { return; }	
        libort  = fields[13];
        if (size < 15) { return; }	
        adrkon  = fields[14];
    }

	public String teilsatzBilden() {
		
        StringBuffer buff = new StringBuffer();
        
        buff = buff.append(tsTyp);
        buff = buff.append(trenner);
        buff = buff.append(beznr);
        buff = buff.append(trenner);
        buff = buff.append(eamdat);
        buff = buff.append(trenner);	
        buff = buff.append(bfvkzi);
        buff = buff.append(trenner);
        buff = buff.append(bfvkzg);
        buff = buff.append(trenner);   
        buff = buff.append(bfartg);
        buff = buff.append(trenner);
        buff = buff.append(bfkzg);
        buff = buff.append(trenner);
        buff = buff.append(bfnatg);
        buff = buff.append(trenner);
        buff = buff.append(gesart);
        buff = buff.append(trenner);
        buff = buff.append(gesprs);
        buff = buff.append(trenner);
        buff = buff.append(geswrg);
        buff = buff.append(trenner); 
        buff = buff.append(libart);
        buff = buff.append(trenner);
        buff = buff.append(libinc);
        buff = buff.append(trenner);
        buff = buff.append(libort);
        buff = buff.append(trenner);
        buff = buff.append(adrkon);
        buff = buff.append(trenner);

        return new String(buff);
	}
        
    public boolean isTransportInlandValid() {
        if (bfvkzi.trim().equals("")) {
            return false;
        }
        return true;
    }

    public boolean isTransportDepartureValid() {
        if (bfvkzg.trim().equals("") && bfartg.trim().equals("") && bfkzg.trim().equals("") && bfnatg.trim().equals("")) {
            return false;
        }
        return true;
    }

    public boolean isTransactionValid() {
        if (gesart.trim().equals("") && gesprs.trim().equals("") && geswrg.trim().equals("")) {
            return false;
        }
        return true;
    }

    public boolean isIncotermsValid() {
        if (libart.trim().equals("") && libinc.trim().equals("") && libort.trim().equals("")) {
            return false;
        }
        return true;
    }

	public String getBeznr() {
		return beznr;
	}
	public void setBeznr(String beznr) {
		this.beznr = Utils.checkNull(beznr);
	}

	public String getEamdat() {
		return eamdat;
	}
	public void setEamdat(String eamdat) {
		this.eamdat = Utils.checkNull(eamdat);
	}

	public String getBfvkzi() {
		return bfvkzi;
	}
	public void setBfvkzi(String bfvkzi) {
		this.bfvkzi = Utils.checkNull(bfvkzi);
	}

	public String getBfvkzg() {
		return bfvkzg;
	}
	public void setBfvkzg(String bfvkzg) {
		this.bfvkzg = Utils.checkNull(bfvkzg);
	}

	public String getBfartg() {
		return bfartg;
	}
	public void setBfartg(String bfartg) {
		this.bfartg = Utils.checkNull(bfartg);
	}

	public String getBfkzg() {
		return bfkzg;
	}
	public void setBfkzg(String bfkzg) {
		this.bfkzg = Utils.checkNull(bfkzg);
	}

	public String getBfnatg() {
		return bfnatg;
	}
	public void setBfnatg(String bfnatg) {
		this.bfnatg = Utils.checkNull(bfnatg);
	}

	public String getGesart() {
		return gesart;
	}
	public void setGesart(String gesart) {
		this.gesart = Utils.checkNull(gesart);
	}

	public String getGesprs() {
		return gesprs;
	}
	public void setGesprs(String gesprs) {
		this.gesprs = Utils.checkNull(gesprs);
	}

	public String getGeswrg() {
		return geswrg;
	}
	public void setGeswrg(String geswrg) {
		this.geswrg = Utils.checkNull(geswrg);
	}

	public String getLibart() {
		return libart;
	}
	public void setLibart(String libart) {
		this.libart = Utils.checkNull(libart);
	}
	
	public String getLibinc() {
		return libinc;
	}
	public void setLibinc(String libinc) {
		this.libinc = Utils.checkNull(libinc);
	}

	public String getLibort() {
		return libort;
	}
	public void setLibort(String libort) {
		this.libort = Utils.checkNull(libort);
	}
	
	public String getAdrkon() {
		return adrkon;
	}
	public void setAdrkon(String value) {
		this.adrkon = Utils.checkNull(value);
	}
	
	public void setEAMSubset(MsgExpEnt msgExpEnt) {	
		if (msgExpEnt == null) {
			return;
		}
		if (Utils.isStringEmpty(msgExpEnt.getReferenceNumber())) {
			return;
		}
		TransportMeans transportInland = msgExpEnt.getTransportInland();
		TransportMeans transportBorder = msgExpEnt.getTransportBorder();
		Business business =  msgExpEnt.getBusiness();
		IncoTerms incoTerms = msgExpEnt.getIncoTerms(); 
		
		this.setBeznr(msgExpEnt.getReferenceNumber());		
		this.setEamdat(msgExpEnt.getCompletionTime()); 
		
		if (transportInland != null) {
			this.setBfvkzi(transportInland.getTransportMode()); 
		}
		if (transportBorder != null) {
	    	this.setBfvkzg(transportBorder.getTransportMode()); 	
	    	this.setBfartg(transportBorder.getTransportationType()); 			
	    	this.setBfkzg(transportBorder.getTransportationNumber()); 
	    	this.setBfnatg(transportBorder.getTransportationCountry());
	    }
	    if (business != null) {
	    	this.setGesart(business.getBusinessTypeCode());  
	    	this.setGesprs(business.getInvoicePrice());  
	    	this.setGeswrg(business.getCurrency()); 		   
	    }
	    if (incoTerms != null) {
	    	this.setLibart(incoTerms.getIncoTerm()); 
	    	this.setLibinc(incoTerms.getText()); 		
	    	this.setLibort(incoTerms.getPlace()); 			
	    } 
	}

	public boolean isEmpty() {
		return Utils.isStringEmpty(eamdat) && Utils.isStringEmpty(bfvkzi) &&
		  Utils.isStringEmpty(bfvkzg) && Utils.isStringEmpty(bfartg) && Utils.isStringEmpty(bfkzg) &&
		  Utils.isStringEmpty(bfnatg) && Utils.isStringEmpty(gesart) && Utils.isStringEmpty(gesprs) &&
		  Utils.isStringEmpty(geswrg) && Utils.isStringEmpty(libart) && Utils.isStringEmpty(libinc) &&
		  Utils.isStringEmpty(libort) &&  Utils.isStringEmpty(adrkon);    	
	}
}

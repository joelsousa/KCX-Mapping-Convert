/*
* Funktion    : TsCBW.java
* Titel       :
* Erstellt    : 21.10.2008
* Author      : Alfred Krzoska
* Beschreibung: Allowance  
* Anmerkungen :
* Parameter   :
* Rückgabe    : keine
* Aufruf      :
*
*/

package com.kewill.kcx.component.mapping.formats.fss.edec.aus.subsets;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.Permit;
import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

public class TsCBW extends Teilsatz {

    private String beznr = "";       // ReferenceNumber
    private String bwstcd = "";      // Category
    private String bwnr   = "";      // Reference
    private String bwdat  = "";      // Date
    private String bwverm = "";      // Remark
    private String posnr = "";       //	ItemNumber
    //AK20110414
    private String zusang = "";		 // zusätzliche Angaben
    
    public TsCBW() {
        tsTyp = "CBW";
    }

    public void setFields(String[] fields)
    {
		int size = fields.length;
		String ausgabe = "FSS: "+fields[0]+" size = "+ size;
		//Utils.log( ausgabe);
			
		
		if (size < 1) { return; }		
        	tsTyp = fields[0];
        if (size < 2) { return; }	
        	beznr = fields[1];
        if (size < 3) { return; }	
        	bwstcd = fields[2];
        if (size < 4) { return; }	
        	bwnr = fields[3];
        if (size < 5) { return; }	
        	bwdat = fields[4];        
        if (size < 6) { return; }	
        	bwverm = fields[5];
        if (size < 7) { return; }	
        	posnr = fields[6];
        if (size < 8) { return; }	
        	zusang = fields[7];	
    }

    public String teilsatzBilden() {
        StringBuffer buff = new StringBuffer();

        buff.append(tsTyp);
        buff.append(trenner);
        buff.append(beznr);
        buff.append(trenner);
        buff.append(bwstcd);
        buff.append(trenner);
        buff.append(bwnr);
        buff.append(trenner);
        buff.append(bwdat);
        buff.append(trenner);
        buff.append(bwverm);
        buff.append(trenner);
        buff.append(posnr);
        buff.append(trenner);
        buff.append(zusang);
        buff.append(trenner);
        
        return new String(buff);
      }

	public String getBwstcd() {
		return bwstcd;
	
	}

	public void setBwstcd(String bwstcd) {
		this.bwstcd = Utils.checkNull(bwstcd);
	}

	public String getBwnr() {
		return bwnr;
	
	}

	public void setBwnr(String bwnr) {
		this.bwnr = Utils.checkNull(bwnr);
	}

	public String getBwdat() {
		return bwdat;
	
	}

	public void setBwdat(String bwdat) {
		this.bwdat = Utils.checkNull(bwdat);
	}

	public String getBwverm() {
		return bwverm;
	
	}

	public void setBwverm(String bwverm) {
		this.bwverm = Utils.checkNull(bwverm);
	}

	public String getPosnr() {
		return posnr;
	
	}

	public void setPosnr(String posnr) {
		this.posnr = Utils.checkNull(posnr);
	}

	public String getBeznr() {
		return beznr;
	
	}

	public void setBeznr(String beznr) {
		this.beznr = Utils.checkNull(beznr);
	}
	
	public String getZusang() {
		return zusang;
	
	}

	public void setZusang(String zusang) {
		this.zusang = Utils.checkNull(zusang);
	}

	public boolean isEmpty() {
		   return (Utils.isStringEmpty(bwstcd) &&
			 	  Utils.isStringEmpty(bwnr)  &&
			 	  Utils.isStringEmpty(bwdat) &&
			 	  Utils.isStringEmpty(bwverm) &&
			 	  Utils.isStringEmpty(zusang));
	}

	public void setCbwSubset(Permit permit, String ref, String item) {
		if (permit == null) { return; }
		if (Utils.isStringEmpty(ref)) { return; }
		if (Utils.isStringEmpty(item)) { return; }
		this.setBeznr(ref);
		this.setPosnr(item);
		
		this.setBwstcd(permit.getPermitAuthority());
		this.setBwnr(permit.getPermitNumber());
		this.setBwdat(permit.getIssueDate());
		this.setBwverm(permit.getAdditionalInformation());
	}


}
package com.kewill.kcx.component.mapping.formats.fss.base.subsets;

import com.kewill.kcx.component.mapping.util.Utils;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;

/*
* Funktion    : TsSAS.java
* Titel       :
* Erstellt    : 03.09.2008
* Author      : CSF GmbH / Alfred Krzoska
* Beschreibung: 
* Anmerkungen : EI 06.03.2009 V60 checked
* Änderungen:
* -----------
* Author      :
* Datum       :
* Kennzeichen :
* Beschreibung:
* Anmerkungen :
* Parameter   :
*
*/

public class TsSAS extends Teilsatz {
	
	private String beznr  = ""; // Bezugsnummer 
    private String bfgkzw = ""; // Beförderungskosten (Zahlungsweise)
    private String knrsdg = ""; // Kennnummer der Sendung
    // CK090429 kzart gibt es nicht im SAS V6 !!
    // private String kzart  = ""; // Ausgangsbenachrichtigung 0 = eingehend 1= Überlassung 2 = Beendigung
    private String besust = ""; // Besondere Umstände

    public TsSAS() {
        tsTyp = "SAS";
    }
    
    public void setFields(String[] fields)
    {
		int size = fields.length;
		String ausgabe = "FSS: "+fields[0]+" size = "+ size;
		//Utils.log( ausgabe);
			
		
		if (size < 1 ) return;		
        tsTyp = fields[0];
        if (size < 2 ) return;	
        beznr        = fields[1];
        if (size < 3 ) return;	
        bfgkzw       = fields[2];
        if (size < 4 ) return;	
        knrsdg       = fields[3];
        if (size < 5 ) return;	
        besust        = fields[4];
   }
    
    public String getBeznr() {
		return beznr;
	}

	public void setBeznr(String beznr) {
		this.beznr = Utils.checkNull(beznr);
	}

	public String getBfgkzw() {
		return bfgkzw;
	}

	public void setBfgkzw(String bfgkzw) {
		this.bfgkzw = Utils.checkNull(bfgkzw);
	}

	public String getKnrsdg() {
		return knrsdg;
	}

	public void setKnrsdg(String knrsdg) {
		this.knrsdg = Utils.checkNull(knrsdg);
	}

	public String getBesust() {
		return besust;
	}

	public void setBesust(String besust) {
		this.besust = Utils.checkNull(besust);
	}

	public String teilsatzBilden() {
        StringBuffer buff = new StringBuffer();
          
        buff.append(tsTyp);
        buff.append(trenner);
        buff.append(beznr);
        buff.append(trenner);	
        buff.append(bfgkzw);
        buff.append(trenner);	
        buff.append(knrsdg);
        buff.append(trenner);	
        buff.append(besust);
        buff.append(trenner);	
        
    	return buff.toString();
    }
	
	public boolean isEmpty() {
		if ( Utils.isStringEmpty(bfgkzw)  && Utils.isStringEmpty(knrsdg) 
				&& Utils.isStringEmpty(besust)	)		  
			return true;
		else
			return false;
	}

}



/*
 * Project:     fssin.uids.aes
 * Package:     fss.teilsaetze.aes
 * File   :     TsADB.java
 * Changed:
 *
 * Created on 22.05.2006
 * Beschreibung :
 * Dieser Teilsatz kommt in folgenden FSS-Nachrichten vor:
 * · Ausfuhranmeldung ADA
 * ·
 *
 */
package com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V53;
import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Modul        :   ADB
 * Teilsatz     :   TsADB.java
 * Erstellt     :   22.05.2006
 * Beschreibung :   Dokumente - Bermerkung ADB
 *
 * 03.09.2008       Version 6  Miro Houdek
 */

public class TsADB extends Teilsatz {

    private String tsTyp     = "";       // Ts-Schlüssel
    private String beznr     = "";       // Bezugsnummer
    private String lfdnr     = "";       // Zeilennummer
    private String bem       = "";       // Bemerkungen

    public TsADB() {
        tsTyp = "ADB";
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
        lfdnr         = fields[2];
        if (size < 4 ) return;
        bem           = fields[3];
      }

    public String teilsatzBilden() {
        StringBuffer buff = new StringBuffer();

        buff.append(tsTyp);
        buff.append(trenner);
        buff.append(beznr);
        buff.append(trenner);
        buff.append(lfdnr);
        buff.append(trenner);
        buff.append(bem);
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

	public String getLfdnr() {
		return lfdnr;
	
	}

	public void setLfdnr(String lfdnr) {
		this.lfdnr = Utils.checkNull(lfdnr);
	}

	public String getBem() {
		return bem;
	
	}

	public void setBem(String bem) {
		this.bem = Utils.checkNull(bem);
	}
	

	public boolean isEmpty() {
		if ( Utils.isStringEmpty(bem))  
    		return true;
		else
			return false;
	}	
	
}



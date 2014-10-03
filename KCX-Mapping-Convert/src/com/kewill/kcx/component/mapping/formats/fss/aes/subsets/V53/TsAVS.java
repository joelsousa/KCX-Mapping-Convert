package com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V53;
import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Modul        :   AES
 * Teilsatz     :   TsAVS.java
 * Erstellt     :   06.04.2006
 * Beschreibung :   Ergänzungssatz Verschlüsse - AVS
 * 
 * 19.06.2008       Version 5.3  Miro Houdek
 */

public class TsAVS extends Teilsatz {

    private String tsTyp     = "";     // Ts-Schlüssel
    private String beznr     = "";     // Bezugsnummer
    private String seal      = "";     // Verschlusszeichen


    public TsAVS() {
        tsTyp = "AVS";
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
	    seal = fields[2];
    }

    public String teilsatzBilden() {
        StringBuffer buff = new StringBuffer();

        buff.append(tsTyp);
        buff.append(trenner);
        buff.append(beznr);
        buff.append(trenner);
        buff.append(seal);
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

	public String getSeal() {
		return seal;
	}

	public void setSeal(String seal) {
		this.seal = Utils.checkNull(seal);
	}

	public boolean isEmpty() {
		if ( Utils.isStringEmpty(seal) )
				return true;
			else
				return false;			
		}

	public void setAvsSubset(String string, String referenz) {

		
	}


}
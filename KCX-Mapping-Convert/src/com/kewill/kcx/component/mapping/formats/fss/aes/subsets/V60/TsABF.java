package com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60;
import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;


/**
 * Modul		:	TsABF
 * Erstellt		:	03.09.2008
 * Beschreibung	:	Beförderungsteilsatz
 *
 * @author 			Miro Houdek
 */


public class TsABF extends Teilsatz {

    private String tsTyp     = "";       //Ts-Schlüssel
    private String beznr     = "";       // Bezugsnummer
    private String ldbf      = "";       // Land


    public TsABF() {
        tsTyp = "ABF";
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
        ldbf          = fields[2];
    }

    public String teilsatzBilden() {
        StringBuffer buff = new StringBuffer();

        buff.append(tsTyp);
        buff.append(trenner);
        buff.append(beznr);
        buff.append(trenner);
        buff.append(ldbf);
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

	public String getLdbf() {
		return ldbf;
	
	}

	public void setLdbf(String ldbf) {
		this.ldbf = Utils.checkNull(ldbf);
	}

	
	public boolean isEmpty() {
		//Utils.isStringEmpty(beznr)  &&
		if ( Utils.isStringEmpty(ldbf))
			return true;
		else
			return false;
	} 	

}
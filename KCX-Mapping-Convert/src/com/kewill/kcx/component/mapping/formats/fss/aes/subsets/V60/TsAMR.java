package com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;



public class TsAMR extends Teilsatz {
    private String beznr 	= "";        // Bezugsnummer
    private String kzart	= "";        // Kennzeichen, mit der die Art der Nachricht und damit das PDF gekennzeichnet wird
    private String mrn		= "";        // MRN
    private String amrdat	= "";        // Zeitpunkt der Entgegennahme bzw. Ueberlassung
    
    public TsAMR() {
        tsTyp = "AMR";
    }
    
    public void setFields(String[] fields) {
		int size = fields.length;
		String ausgabe = "FSS: "+fields[0]+" size = "+ size;
		//Utils.log( ausgabe);				
		
		if (size < 1 ) return;		
        tsTyp = fields[0];
        if (size < 2 ) return;
        beznr   = fields[1];
        if (size < 3 ) return;
        kzart   = fields[2];
        if (size < 4 ) return;
        mrn     = fields[3];
        if (size < 5 ) return;
        amrdat  = fields[4];                
    }

	public String teilsatzBilden() {
        StringBuffer buff = new StringBuffer();
        
        buff = buff.append(tsTyp);
        buff = buff.append(trenner);
        buff = buff.append(beznr);
        buff = buff.append(trenner);
        buff = buff.append(kzart);
        buff = buff.append(trenner);
        buff = buff.append(mrn);
        buff = buff.append(trenner);
        buff = buff.append(amrdat);
        buff = buff.append(trenner);
        
        return new String(buff);
    }

	public String getBeznr() {
		return beznr;
	}

	public void setBeznr(String beznr) {
		this.beznr = Utils.checkNull(beznr);
	}

	public String getKzart() {
		return kzart;
	}

	public void setKzart(String kzart) {
		this.kzart = Utils.checkNull(kzart);
	}

	public String getMrn() {
		return mrn;
	}

	public void setMrn(String mrn) {
		this.mrn = Utils.checkNull(mrn);
	}

	public String getAmrdat() {
		return amrdat;
	}

	public void setAmrdat(String amrdat) {
		this.amrdat = Utils.checkNull(amrdat);
	}
	
	public boolean isEmpty() {
 
		if ( Utils.isStringEmpty(kzart) && Utils.isStringEmpty(mrn)  && Utils.isStringEmpty(amrdat))
			return true;
		else
			return false;
	}    
}

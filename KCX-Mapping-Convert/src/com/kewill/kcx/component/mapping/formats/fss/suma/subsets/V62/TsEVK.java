package com.kewill.kcx.component.mapping.formats.fss.suma.subsets.V62;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;


/**
 * Modul		:	TsEVK
 * Erstellt		:	12.11.2012
 * Beschreibung	:   Kopfsatz Verwahrmitteilung aus NCTS <GoodsReleasedExternal> EVK
 *        			Version 06.02  Christine Kron
 *
 */

public class TsEVK  extends Teilsatz {

	private String mrn		= "";			//	MRN aus NCTS
	private String regnr	= "";			//	Registriernummer
	private String gstdat	= "";			//	Gestellungsdatum

    public TsEVK() {
        tsTyp = "EVK";
    }

    public void setFields(String[] fields)
    {
		int size = fields.length;
		String ausgabe = "FSS: "+fields[0]+" size = "+ size;
		//Utils.log( ausgabe);
			
		
		if (size < 1 ) return;		
        tsTyp = fields[0];
        if (size < 2 ) return;		
           mrn         = fields[1];
           if (size < 3 ) return;
           regnr         = fields[2];
           if (size < 4 ) return;
           gstdat         = fields[3];

      }

 public String teilsatzBilden() {
        StringBuffer buff = new StringBuffer();

        buff.append(tsTyp);
        buff.append(trenner);
        buff.append(mrn);
        buff.append(trenner);
        buff.append(regnr);
        buff.append(trenner);
        buff.append(gstdat);
        buff.append(trenner);

        return new String(buff);
    }

public String getTsTyp() {
	return tsTyp;

}

public void setTsTyp(String tsTyp) {
	this.tsTyp = Utils.checkNull(tsTyp);
}


public boolean isEmpty() {

	if ( Utils.isStringEmpty(mrn)        && Utils.isStringEmpty(regnr) 
			&& Utils.isStringEmpty(gstdat))
		return true;
	else
		return false;
}

public String getMrn() {
	return mrn;
}

public void setMrn(String mrn) {
	this.mrn = Utils.checkNull(mrn);
}

public String getRegnr() {
	return regnr;
}

public void setRegnr(String regnr) {
	this.regnr = Utils.checkNull(regnr);
}

public String getGstdat() {
	return gstdat;
}

public void setGstdat(String gstdat) {
	this.gstdat = Utils.checkNull(gstdat);
} 

}



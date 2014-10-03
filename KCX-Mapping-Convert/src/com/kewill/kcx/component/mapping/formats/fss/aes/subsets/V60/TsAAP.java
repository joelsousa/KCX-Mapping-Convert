package com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60;
import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Modul		:	TsAAP
 * Erstellt		:	03.09.2008
 * Beschreibung	:   14.5.22	Positionssatz Nachtrag  AAP
 *
 * 03.09.2008       Version 6  Miro Houdek
 *
 */

public class TsAAP extends Teilsatz {

    private String tsTyp     = "";       // Ts-Schlüssel
    private String beznr     = "";       // Bezugsnummer
    private String posnr     = "";       // Positionsnummer
    private String eigmas    = "";       // Eigenmasse
    private String rohmas    = "";       // Rohmasse
    private String menge     = "";       // Menge zur Ausfuhrerstattung

    public TsAAP() {
        tsTyp = "AAP";
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
           posnr         = fields[2];
           if (size < 4 ) return;
           eigmas        = fields[3];
           if (size < 5 ) return;
           rohmas        = fields[4];
           if (size < 6 ) return;
           menge         = fields[5];
      }


public String teilsatzBilden() {
        StringBuffer buff = new StringBuffer();

        buff.append(tsTyp);
        buff.append(trenner);
        buff.append(beznr);
        buff.append(trenner);
        buff.append(posnr);
        buff.append(trenner);
        buff.append(eigmas);
        buff.append(trenner);
        buff.append(rohmas);
        buff.append(trenner);
        buff.append(menge);
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

public String getPosnr() {
	return posnr;

}

public void setPosnr(String posnr) {
	this.posnr = Utils.checkNull(posnr);
}

public String getEigmas() {
	return eigmas;

}

public void setEigmas(String eigmas) {
	this.eigmas = Utils.checkNull(eigmas);
}

public String getRohmas() {
	return rohmas;

}

public void setRohmas(String rohmas) {
	this.rohmas = Utils.checkNull(rohmas);
}

public String getMenge() {
	return menge;

}

public void setMenge(String menge) {
	this.menge = Utils.checkNull(menge);
}

public boolean isEmpty() {
	//Utils.isStringEmpty(beznr)  &&
	if ( Utils.isStringEmpty(eigmas) && Utils.isStringEmpty(rohmas)
	  && Utils.isStringEmpty(menge))
		return true;
	else
		return false;
}

}



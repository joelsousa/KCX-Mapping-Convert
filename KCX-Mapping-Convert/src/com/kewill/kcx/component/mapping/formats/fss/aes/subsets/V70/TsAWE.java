package com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V70;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		:	Export/aes
 * Created		:	25.07.2012
 * Description	:	Passive Veredelung - Wiedereinfuhr.
 *
 * @author 	iwaniuk
 * @version 2.1.00 (Zabis V70)
 */

public class TsAWE extends Teilsatz {

    private String beznr = "";       
    private String ldwe  = "";       // Land der Wiedereinfuhr

    public TsAWE() {
        tsTyp = "AWE";
    }

    public void setFields(String[] fields) {
		int size = fields.length;
			
		if (size < 1)  { return; }		
        tsTyp = fields[0];    	
        if (size < 2)  { return; }	
        beznr = fields[1];
        if (size < 3)  { return; }	
        ldwe  = fields[2];
    }

    public String teilsatzBilden() {
        StringBuffer buff = new StringBuffer();

        buff.append(tsTyp);
        buff.append(trenner);
        buff.append(beznr);
        buff.append(trenner);
        buff.append(ldwe);
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
	
	public String getLdwe() {
		return ldwe;	
	}
	public void setLdwe(String land) {
		this.ldwe = Utils.checkNull(land);
	}
	
	public boolean isEmpty() {		
		return Utils.isStringEmpty(ldwe);		
	} 	
}

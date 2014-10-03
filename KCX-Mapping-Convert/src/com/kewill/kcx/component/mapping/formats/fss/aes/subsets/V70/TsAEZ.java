package com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V70;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		:	Export/aes
 * Created		:	25.07.2012
 * Description	:	Passive Veredelung - Erzeugnis.
 *
 * @author 	iwaniuk
 * @version 2.1.00 (Zabis V70)
 */

public class TsAEZ extends Teilsatz {

    private String beznr = "";       
    private String trnr  = "";     
    private String text  = "";  

    public TsAEZ() {
        tsTyp = "AEZ";
    }

    public void setFields(String[] fields) {
		int size = fields.length;
			
		if (size < 1)  { return; }	
        tsTyp  = fields[0];    	
        if (size < 2) { return; }
        beznr  = fields[1];
        if (size < 3)  { return; }
        trnr   = fields[2];
        if (size < 4)  { return; }
        text   = fields[3];
    }

    public String teilsatzBilden() {
        StringBuffer buff = new StringBuffer();

        buff.append(tsTyp);
        buff.append(trenner);
        buff.append(beznr);
        buff.append(trenner);
        buff.append(trnr);
        buff.append(trenner);
        buff.append(text);
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
	
	public String getTrnr() {
		return trnr;	
	}
	public void setTrnr(String value) {
		this.trnr = Utils.checkNull(value);
	}
	
	public String getText() {
		return text;	
	}
	public void setText(String value) {
		this.text = Utils.checkNull(value);
	}
	
	public boolean isEmpty() {		
		return Utils.isStringEmpty(trnr) && Utils.isStringEmpty(text);		
	} 	
}

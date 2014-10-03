package com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V70;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		:	Export/aes
 * Created		:	25.07.2012
 * Description	:	Passive Veredelung - Nämlichkeitsmittel.
 *
 * @author 	iwaniuk
 * @version 2.1.00 (Zabis V70)
 */

public class TsANM extends Teilsatz {

    private String beznr = "";       
    private String artnkm  = "";     
    private String text  = "";  

    public TsANM() {
        tsTyp = "ANM";
    }

    public void setFields(String[] fields) {
		int size = fields.length;
			
		if (size < 1)  { return; }	
        tsTyp  = fields[0];    	
        if (size < 2)  { return; }
        beznr  = fields[1];
        if (size < 3)  { return; }
        artnkm = fields[2];
        if (size < 4)  { return; }
        text   = fields[3];
    }

    public String teilsatzBilden() {
        StringBuffer buff = new StringBuffer();

        buff.append(tsTyp);
        buff.append(trenner);
        buff.append(beznr);
        buff.append(trenner);
        buff.append(artnkm);
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
	
	public String getArtnkm() {
		return artnkm;	
	}
	public void setArtnkm(String ldbf) {
		this.artnkm = Utils.checkNull(ldbf);
	}
	
	public String getText() {
		return text;	
	}
	public void setText(String text) {
		this.text = Utils.checkNull(text);
	}
	
	public boolean isEmpty() {		
		return Utils.isStringEmpty(artnkm) && Utils.isStringEmpty(text);		
	} 	
}

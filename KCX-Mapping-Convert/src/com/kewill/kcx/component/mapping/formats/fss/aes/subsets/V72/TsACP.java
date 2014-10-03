package com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V72;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Modul		:	TsACP
 * Erstellt		:	03.09.2008
 * Beschreibung	:	Kontrolldaten Positionssatz.
 *
 * @author iwaniuk
 * @version 2.1.00
 */

public class TsACP extends Teilsatz {

    private String beznr     = "";       // Bezugsnummer
    private String posnr     = "";       // Positionsnummer
    private String ctlart    = "";       // Art der Kontrollmaﬂnahme
    private String text      = "";       // Vermerk zur Art der Kontrollmaﬂnahme


    public TsACP() {
        tsTyp = "ACP";
    }

    public void setFields(String[] fields) {
		int size = fields.length;
			
		
		if (size < 1) { return; }		
        tsTyp = fields[0];
        if (size < 2) { return; }		   
        beznr         = fields[1];
        if (size < 3) { return; }
        posnr         = fields[2];
        if (size < 4) { return; }
        ctlart        = fields[3];
        if (size < 5) { return; }
        text          = fields[4];
      }

    public String teilsatzBilden() {
        StringBuffer buff = new StringBuffer();

        buff.append(tsTyp);
        buff.append(trenner);
        buff.append(beznr);
        buff.append(trenner);
        buff.append(posnr);
        buff.append(trenner);
        buff.append(ctlart);
        buff.append(trenner);
        buff.append(text);
        buff.append(trenner);

        return new String(buff);
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

	public String getCtlart() {
		return ctlart;	
	}
	public void setCtlart(String ctlart) {
		this.ctlart = Utils.checkNull(ctlart);
	}

	public String getText() {
		return text;	
	}
	public void setText(String text) {
		this.text = Utils.checkNull(text);
	}
	
	public boolean isEmpty() {		
		return Utils.isStringEmpty(ctlart) && Utils.isStringEmpty(text); 			
	} 
	

}
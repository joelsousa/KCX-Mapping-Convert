/**
 * Modul		:	AES
 * Erstellt		:	03.09.2008
 * Beschreibung	:	EXPORT Kontrolldaten Kopfsatz
 *
 * @author 			iwaniuk
 * @version 2.1.00
 */

package com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V72;
import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;


public class TsACK extends Teilsatz {

    private String tsTyp     = "";       //Ts-Schl¸ssel
    private String beznr     = "";       // Bezugsnummer
    private String mrn       = "";       // EI20130722
    private String ctldat    = "";       // Zeitpunkt der Kontrollmaﬂnahme
    private String sbname    = "";       // Name des Sachbearbeiters, der die Kontrollmaﬂnahme angeordnet hat
    private String ctlart    = "";       // Art der Kontrollmaﬂnahme
    private String text      = "";       // Vermerk zur Art der Kontrollmaﬂnahme


    public TsACK() {
        tsTyp = "ACK";
    }

    public void setFields(String[] fields) {
		int size = fields.length;		
		
		if (size < 1) { return;	}	
        tsTyp = fields[0];
        if (size < 2) { return;	}
        beznr         = fields[1];
        if (size < 3) { return;	}
        mrn        = fields[2];
        if (size < 4) { return;	}
        ctldat        = fields[3];
        if (size < 5) { return;	}
        sbname        = fields[4];
        if (size < 6) { return;	}
        ctlart          = fields[5];
        if (size < 7) { return;	}
        text          = fields[6];
    }


    public String teilsatzBilden() {
        StringBuffer buff = new StringBuffer();

        buff.append(tsTyp);
        buff.append(trenner);
        buff.append(beznr);
        buff.append(trenner);
        buff.append(mrn);
        buff.append(trenner);
        buff.append(ctldat);
        buff.append(trenner);
        buff.append(sbname);
        buff.append(trenner);
        buff.append(ctlart);
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

	public String getMrn() {
		return mrn;	
	}
	public void setMrn(String beznr) {
		this.mrn = Utils.checkNull(beznr);
	}
	
	public String getCtldat() {
		return ctldat;	
	}
	public void setCtldat(String ctldat) {
		this.ctldat = Utils.checkNull(ctldat);
	}

	public String getSbname() {
		return sbname;	
	}
	public void setSbname(String sbname) {
		this.sbname = Utils.checkNull(sbname);
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
		return ( Utils.isStringEmpty(mrn) && Utils.isStringEmpty(ctldat) && Utils.isStringEmpty(sbname) &&
				 Utils.isStringEmpty(ctlart) && Utils.isStringEmpty(text)); 		  
	}
	
}
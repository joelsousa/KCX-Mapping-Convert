package com.kewill.kcx.component.mapping.formats.fss.Import.subsets.V64;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Modul                :       TsTXT

 * Erstellt             :       14.09.2011
 * Beschreibung 		:       Texte zur Anmeldung

 *
 * @author                      Alfred Krzoska
 *
 */

public class TsTXT extends Teilsatz {

    private String beznr			 = "";	 // Bezugsnummer
    private String posnr			 = "";	 // Positionsnummer
    private String text			 	 = "";	 // Zusatztext (zum Kopf)

    public TsTXT() {
	    tsTyp = "TXT";
    }

    public void setFields(String[] fields) {
    	int size = fields.length;
    	String ausgabe = "FSS: " + fields[0] + " size = " + size;

    	if (size < 1) { return; }
    	tsTyp = fields[0];

	    if (size < 2) { return; }
	    beznr	 = fields[1];

	    if (size < 3) { return; }
	    posnr	 = fields[2];

	    if (size < 4) { return; }
	    text	 = fields[3];
    }



    public String getBeznr() {
    	 return beznr;
    }


    public void setBeznr(String beznr) {
    	this.beznr	= Utils.checkNull(beznr);
    }


    public String getPosnr() {
    	 return posnr;
    }


    public void setPosnr(String posnr) {
    	this.posnr	 = Utils.checkNull(posnr);
    }


    public String getText() {
    	 return text;
    }


    public void setText(String text) {
    	this.text = Utils.checkNull(text);
    }


    public String teilsatzBilden() {
    	StringBuffer buff = new StringBuffer();

    	buff.append(tsTyp);
    	buff.append(trenner);
    	buff.append(beznr);
    	buff.append(trenner);
    	buff.append(posnr);
    	buff.append(trenner);
    	buff.append(text);
    	buff.append(trenner);

    	return new String(buff);
    }



    public boolean isEmpty() {
	  return   Utils.isStringEmpty(text);
    }
}

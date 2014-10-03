package com.kewill.kcx.component.mapping.formats.fss.Import.subsets.V64;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Modul                :       TsANR

 * Erstellt             :       13.09.2011
 
 * Beschreibung 		:       In allen Nachrichten, die von ZABIS ausgehen, gibt es einen optionalen Teilsatz 
 								vom Typ ANR, der zu der Deklaration, auf die sich die Nachricht bezieht, eine 
 								Auftragsnummer enthält (die der ursprünglich vom Fremdsystem vergebenen Bezugsnummer 
 								entspricht). Der Teilsatz hat in allen Nachrichten die selbe Struktur.
 				 
 *
 * @author                      Alfred Krzoska
 *
 */

public class TsANR extends Teilsatz {

    private String beznr		 = "";	 // Bezugsnummer CSF
    private String aufnr		 = "";	 // Auftragsnummer beim Kunden

    public TsANR() {
	    tsTyp = "ANR";
    }

    public void setFields(String[] fields) {
    	int size = fields.length;
    	String ausgabe = "FSS: " + fields[0] + " size = " + size;

    	if (size < 1) { return; }
    	tsTyp = fields[0];

	    if (size < 2) { return; }
	    beznr = fields[1];

	    if (size < 3) { return; }
	    aufnr = fields[2];
    }



    public String getBeznr() {
    	 return beznr;
    }


    public void setBeznr(String beznr) {
    	this.beznr = Utils.checkNull(beznr);
    }


    public String getAufnr() {
    	 return aufnr;
    }


    public void setAufnr(String aufnr) {
    	this.aufnr = Utils.checkNull(aufnr);
    }


    public String teilsatzBilden() {
    	StringBuffer buff = new StringBuffer();

    	buff.append(tsTyp);
    	buff.append(trenner);
    	buff.append(beznr);
    	buff.append(trenner);
    	buff.append(aufnr);
    	buff.append(trenner);

    	return new String(buff);
    }



    public boolean isEmpty() {
      return Utils.isStringEmpty(aufnr);
    }

}

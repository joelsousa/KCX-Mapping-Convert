package com.kewill.kcx.component.mapping.formats.fss.Import.subsets.V64;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Modul                :       TsBEW

 * Erstellt             :       14.09.2011
 * Beschreibung 		:       Besondere Wertangaben
 								Die besonderen Wertangaben können bis zu 5 mal vorkommen

 *
 * @author                      Alfred Krzoska
 *
 */

public class TsBEW extends Teilsatz {

    private String bewbnr		 = "";	 // Bezugsnummer
    private String bewpnr		 = "";	 // Positionsnummer
    private String bewe		 	 = "";	 // Besond. Wertangabe
    private String prsar		 = "";	 // Preisart 	vgl. deutsche Codeliste

    public TsBEW() {
	    tsTyp = "BEW";
    }

    public void setFields(String[] fields) {
    	int size = fields.length;
    	String ausgabe = "FSS: " + fields[0] + " size = " + size;

    	if (size < 1) { return; }
    	tsTyp = fields[0];

	    if (size < 2) { return; }
	    bewbnr = fields[1];

	    if (size < 3) { return; }
	    bewpnr = fields[2];

	    if (size < 4) { return; }
	    bewe = fields[3];

	    if (size < 5) { return; }
	    prsar = fields[4];
    }



    public String getBewbnr() {
    	 return bewbnr;
    }


    public void setBewbnr(String bewbnr) {
    	this.bewbnr = Utils.checkNull(bewbnr);
    }


    public String getBewpnr() {
    	 return bewpnr;
    }


    public void setBewpnr(String bewpnr) {
    	this.bewpnr = Utils.checkNull(bewpnr);
    }


    public String getBewe() {
    	 return bewe;
    }


    public void setBewe(String bewe) {
    	this.bewe = Utils.checkNull(bewe);
    }


    public String getPrsar() {
    	 return prsar;
    }


    public void setPrsar(String prsar) {
    	this.prsar = Utils.checkNull(prsar);
    }


    public String teilsatzBilden() {
    	StringBuffer buff = new StringBuffer();

    	buff.append(tsTyp);
    	buff.append(trenner);
    	buff.append(bewbnr);
    	buff.append(trenner);
    	buff.append(bewpnr);
    	buff.append(trenner);
    	buff.append(bewe);
    	buff.append(trenner);
    	buff.append(prsar);
    	buff.append(trenner);

    	return new String(buff);

    }



    public boolean isEmpty() {
	  return   Utils.isStringEmpty(bewe) &&
    	Utils.isStringEmpty(prsar);
    }

}

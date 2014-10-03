package com.kewill.kcx.component.mapping.formats.fss.Import.subsets.V64;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Modul                :       TsTXA.

 * Erstellt             :       13.09.2011
 * Beschreibung 		:       TeilsATZ zu Texten des Abgabenbescheids
 				 
 *
 * @author                      Alfred Krzoska
 *
 */

public class TsTXA extends Teilsatz {

    private String regkz		 = "";	 // Registrierkennzeichen
    private String lfdnr		 = "";	 // laufende Nummer des Abgabenbescheids
    private String txaart		 = "";	 // Art des Textes	AAH für Befund
    private String txablk		 = "";	 // Teil eines Textes

    public TsTXA() {
	    tsTyp = "TXA";
    }

    public void setFields(String[] fields) {
    	int size = fields.length;
    	String ausgabe = "FSS: " + fields[0] + " size = " + size;

    	if (size < 1) { return; }
    	tsTyp = fields[0];

	    if (size < 2) { return; }
	    regkz = fields[1];

	    if (size < 3) { return; }
	    lfdnr = fields[2];

	    if (size < 4) { return; }
	    txaart = fields[3];

	    if (size < 5) { return; }
	    txablk = fields[4];
    }



    public String getRegkz() {
    	 return regkz;
    }


    public void setRegkz(String regkz) {
    	this.regkz = Utils.checkNull(regkz);
    }


    public String getLfdnr() {
    	 return lfdnr;
    }


    public void setLfdnr(String lfdnr) {
    	this.lfdnr = Utils.checkNull(lfdnr);
    }


    public String getTxaart() {
    	 return txaart;
    }


    public void setTxaart(String txaart) {
    	this.txaart = Utils.checkNull(txaart);
    }


    public String getTxablk() {
    	 return txablk;
    }


    public void setTxablk(String txablk) {
    	this.txablk = Utils.checkNull(txablk);
    }


    public String teilsatzBilden() {
    	StringBuffer buff = new StringBuffer();

    	buff.append(tsTyp);
    	buff.append(trenner);
    	buff.append(regkz);
    	buff.append(trenner);
    	buff.append(lfdnr);
    	buff.append(trenner);
    	buff.append(txaart);
    	buff.append(trenner);
    	buff.append(txablk);
    	buff.append(trenner);

    	return new String(buff);
    }


    public boolean isEmpty() {
	  return   Utils.isStringEmpty(txaart) &&
    	Utils.isStringEmpty(txablk);
    }
}

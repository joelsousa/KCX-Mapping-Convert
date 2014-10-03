package com.kewill.kcx.component.mapping.formats.fss.Import.subsets.V64;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Modul                :       TsTXP

 * Erstellt             :       14.09.2011
 * Beschreibung 		:       Positionsbefund. Es können maximal 3 Angaben zum Positionsbefund übermittelt werden.
 				
 *
 * @author                      Alfred Krzoska
 *
 */

public class TsTXP extends Teilsatz {

    private String regkz		 = "";	 // Registrierkennzeichen
    private String lfdnr		 = "";	 // laufende Nummer des Abgabenbescheids
    private String posnr		 = "";	 // Positionsnummer
    private String txpart		 = "";	 // Art des Textes AAH für Befund
    private String txpblk		 = "";	 // Teil eines Textes

    public TsTXP() {
	    tsTyp = "TXP";
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
	    posnr = fields[3];

	    if (size < 5) { return; }
	    txpart = fields[4];

	    if (size < 6) { return; }
	    txpblk = fields[5];
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


    public String getPosnr() {
    	 return posnr;
    }


    public void setPosnr(String posnr) {
    	this.posnr = Utils.checkNull(posnr);
    }


    public String getTxpart() {
    	 return txpart;
    }


    public void setTxpart(String txpart) {
    	this.txpart = Utils.checkNull(txpart);
    }


    public String getTxpblk() {
    	 return txpblk;
    }


    public void setTxpblk(String txpblk) {
    	this.txpblk = Utils.checkNull(txpblk);
    }


    public String teilsatzBilden() {
    	StringBuffer buff = new StringBuffer();

    	buff.append(tsTyp);
    	buff.append(trenner);
    	buff.append(regkz);
    	buff.append(trenner);
    	buff.append(lfdnr);
    	buff.append(trenner);
    	buff.append(posnr);
    	buff.append(trenner);
    	buff.append(txpart);
    	buff.append(trenner);
    	buff.append(txpblk);
    	buff.append(trenner);

    	return new String(buff);
    
    }



    public boolean isEmpty() {
	  return   Utils.isStringEmpty(txpart) &&
    	Utils.isStringEmpty(txpblk);
    }

}

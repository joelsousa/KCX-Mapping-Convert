package com.kewill.kcx.component.mapping.formats.fss.Import.subsets.V64;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Modul                :       TsSUA.

 * Erstellt             :       13.09.2011
 * Beschreibung 		:       SumA-Teilsatzes des Abgabenbescheids
 				
 				 
 *
 * @author                      Alfred Krzoska
 *
 */

public class TsSUA extends Teilsatz {

    private String regkz		 = "";	 // Registrierkennzeichen
    private String lfdnr		 = "";	 // laufende Nummer des Abgabenbescheids
    private String suanr		 = "";	 // SumA Reg.Nummer
    private String suapo		 = "";	 // SumA-Positionsnr
    private String suadat		 = "";	 // SumA-Datum

    public TsSUA() {
	    tsTyp = "SUA";
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
	    suanr = fields[3];

	    if (size < 5) { return; }
	    suapo = fields[4];

	    if (size < 6) { return; }
	    suadat = fields[5];
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


    public String getSuanr() {
    	 return suanr;
    }


    public void setSuanr(String suanr) {
    	this.suanr = Utils.checkNull(suanr);
    }


    public String getSuapo() {
    	 return suapo;
    }


    public void setSuapo(String suapo) {
    	this.suapo = Utils.checkNull(suapo);
    }


    public String getSuadat() {
    	 return suadat;
    }


    public void setSuadat(String suadat) {
    	this.suadat = Utils.checkNull(suadat);
    }


    public String teilsatzBilden() {
    	StringBuffer buff = new StringBuffer();

    	buff.append(tsTyp);
    	buff.append(trenner);
    	buff.append(regkz);
    	buff.append(trenner);
    	buff.append(lfdnr);
    	buff.append(trenner);
    	buff.append(suanr);
    	buff.append(trenner);
    	buff.append(suapo);
    	buff.append(trenner);
    	buff.append(suadat);
    	buff.append(trenner);

    	return new String(buff);
    }



    public boolean isEmpty() {
      return Utils.isStringEmpty(regkz) &&
    	Utils.isStringEmpty(lfdnr) &&
    	Utils.isStringEmpty(suanr) &&
    	Utils.isStringEmpty(suapo) &&
    	Utils.isStringEmpty(suadat);
    }

}

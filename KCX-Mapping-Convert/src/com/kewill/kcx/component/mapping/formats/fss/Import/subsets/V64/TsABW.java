package com.kewill.kcx.component.mapping.formats.fss.Import.subsets.V64; 

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Modul                :       TsABW
 * Erstellt             :       14.09.2011
 * Beschreibung 		:       Abweichende Festsetzungen
								Die abweichenden Festsetzungen können beliebig oft vorkommen.
 *
 * @author                      Alfred Krzoska
 *
 */

public class TsABW extends Teilsatz {

    private String regkz		 = "";	 // Registrierkennzeichen
    private String lfdnr		 = "";	 // laufende Nummer des Abgabenbescheids
    private String posnr		 = "";	 // Positionsnummer
    private String feld		 	 = "";	 // Code des abweichend festgesetzten Feldes vgl. Deutsche Codeliste
    private String qfeld		 = "";	 // Qualifikator zum Code des abweichend festgesetzten Feldes Deutsche Codeliste
    private String qinhal		 = "";	 // Qualifikatorinhalt
    private String abwart		 = "";	 // Art der abweichenden Festsetzung	1=neu 2=geändert 3=gelöscht
    private String abwfv		 = "";	 // abweichend festgesetzt von
    private String abwfa		 = "";	 // abweichend festgesetzt auf

    public TsABW() {
	    tsTyp = "ABW";
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
		feld = fields[4];
	
		if (size < 6) { return; }
		qfeld = fields[5];
	
		if (size < 7) { return; }
		qinhal = fields[6];
	
		if (size < 8) { return; }
		abwart = fields[7];
	
		if (size < 9) { return; }
		abwfv = fields[8];
	
		if (size < 10) { return; }
		abwfa = fields[9];
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


    public String getFeld() {
    	 return feld;
    }


    public void setFeld(String feld) {
    	this.feld = Utils.checkNull(feld);
    }


    public String getQfeld() {
    	 return qfeld;
    }


    public void setQfeld(String qfeld) {
    	this.qfeld = Utils.checkNull(qfeld);
    }


    public String getQinhal() {
    	 return qinhal;
    }


    public void setQinhal(String qinhal) {
    	this.qinhal = Utils.checkNull(qinhal);
    }


    public String getAbwart() {
    	 return abwart;
    }


    public void setAbwart(String abwart) {
    	this.abwart = Utils.checkNull(abwart);
    }


    public String getAbwfv() {
    	 return abwfv;
    }


    public void setAbwfv(String abwfv) {
    	this.abwfv = Utils.checkNull(abwfv);
    }


    public String getAbwfa() {
    	 return abwfa;
    }


    public void setAbwfa(String abwfa) {
    	this.abwfa = Utils.checkNull(abwfa);
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
    	buff.append(feld);
    	buff.append(trenner);
    	buff.append(qfeld);
    	buff.append(trenner);
    	buff.append(qinhal);
    	buff.append(trenner);
    	buff.append(abwart);
    	buff.append(trenner);
    	buff.append(abwfv);
    	buff.append(trenner);
    	buff.append(abwfa);
    	buff.append(trenner);

    	return new String(buff);
    
    }



    public boolean isEmpty() {
	  return   Utils.isStringEmpty(feld) &&
    	Utils.isStringEmpty(qfeld) &&
    	Utils.isStringEmpty(qinhal) &&
    	Utils.isStringEmpty(abwart) &&
    	Utils.isStringEmpty(abwfv) &&
    	Utils.isStringEmpty(abwfa);
    }
}

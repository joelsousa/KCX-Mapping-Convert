package com.kewill.kcx.component.mapping.formats.fss.Import.subsets.V64;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Modul                :       TsREP.

 * Erstellt             :       22.09.2011
 * Beschreibung 		:       Positionssatz der Rückmeldung CUSREC 
 				

 *
 * @author                      Alfred Krzoska
 * @version 1.0.00
 */

public class TsREP extends Teilsatz {

    private String lfdnr		 = "";	 // laufende Nummer
    private String posnr		 = "";	 // Positionsnummer
    private String txtlfd		 = "";	 // laufende Nummer Textblock
    private String errcd		 = "";	 // Fehlercode
    private String errgew		 = "";	 // Meldegewicht
    private String errpfx		 = "";	 // Meldepräfix
    private String errtxt		 = "";	 // Fehlertext
    private String vregnr		 = "";	 // Registriernummer BE-Anteile

    public TsREP() {
	    tsTyp = "REP";
    }

    public void setFields(String[] fields) {
    	int size = fields.length;
    	String ausgabe = "FSS: " + fields[0] + " size = " + size;

    	if (size < 1) { return; }
    	tsTyp = fields[0];

	    if (size < 2) { return; }
	    lfdnr = fields[1];

	    if (size < 3) { return; }
	    posnr = fields[2];

	    if (size < 4) { return; }
	    txtlfd = fields[3];

	    if (size < 5) { return; }
	    errcd = fields[4];

	    if (size < 6) { return; }
	    errgew = fields[5];

	    if (size < 7) { return; }
	    errpfx = fields[6];

	    if (size < 8) { return; }
	    errtxt = fields[7];

	    if (size < 9) { return; }
	    vregnr = fields[8];
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


    public String getTxtlfd() {
    	 return txtlfd;
    }


    public void setTxtlfd(String txtlfd) {
    	this.txtlfd = Utils.checkNull(txtlfd);
    }


    public String getErrcd() {
    	 return errcd;
    }


    public void setErrcd(String errcd) {
    	this.errcd = Utils.checkNull(errcd);
    }


    public String getErrgew() {
    	 return errgew;
    }


    public void setErrgew(String errgew) {
    	this.errgew = Utils.checkNull(errgew);
    }


    public String getErrpfx() {
    	 return errpfx;
    }


    public void setErrpfx(String errpfx) {
    	this.errpfx = Utils.checkNull(errpfx);
    }


    public String getErrtxt() {
    	 return errtxt;
    }


    public void setErrtxt(String errtxt) {
    	this.errtxt = Utils.checkNull(errtxt);
    }


    public String getVregnr() {
    	 return vregnr;
    }


    public void setVregnr(String vregnr) {
    	this.vregnr = Utils.checkNull(vregnr);
    }


    public String teilsatzBilden() {
    	StringBuffer buff = new StringBuffer();

    	buff.append(tsTyp);
    	buff.append(trenner);
    	buff.append(lfdnr);
    	buff.append(trenner);
    	buff.append(posnr);
    	buff.append(trenner);
    	buff.append(txtlfd);
    	buff.append(trenner);
    	buff.append(errcd);
    	buff.append(trenner);
    	buff.append(errgew);
    	buff.append(trenner);
    	buff.append(errpfx);
    	buff.append(trenner);
    	buff.append(errtxt);
    	buff.append(trenner);
    	buff.append(vregnr);
    	buff.append(trenner);

    	return new String(buff);
    }



    public boolean isEmpty() {
	    return  Utils.isStringEmpty(errcd) &&
	    	Utils.isStringEmpty(errgew) &&
	    	Utils.isStringEmpty(errpfx) &&
	    	Utils.isStringEmpty(errtxt);    
    }

}

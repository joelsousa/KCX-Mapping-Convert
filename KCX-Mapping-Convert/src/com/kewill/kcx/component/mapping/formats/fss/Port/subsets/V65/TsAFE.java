package com.kewill.kcx.component.mapping.formats.fss.Port.subsets.V65; 

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module       : Port
 * Created		: 24.10.2011<br>
 * Description	: Positionsdaten
 *
 * @author iwaniuk
 * @version 1.0.00
 */

public class TsAFE extends Teilsatz {

    private String beznr		 = "";	 // Bezugsnummer
    private String lfdnr		 = "";	 // Positionsnummer
    private String lfnrfe		 = "";	 // Laufende Nr. zu Ebene2
    private String errcod		 = "";	 // Error Code
    private String errdic		 = "";	 // Description
   
    public TsAFE() {
	    tsTyp = "AFE";
    }

    public void setFields(String[] fields) {
    	int size = fields.length;
    	//String ausgabe = "FSS: " + fields[0] + " size = " + size;

    	if (size < 1) { return; }
    	tsTyp = fields[0];
		if (size < 2) { return; }
		beznr = fields[1];
		if (size < 3) { return; }
		lfdnr = fields[2];
		if (size < 4) { return; }
		lfnrfe = fields[3];
		if (size < 5) { return; }
		errcod = fields[4];	
		if (size < 6) { return; }
		errdic = fields[5];			
    }

    public String teilsatzBilden() {
    	StringBuffer buff = new StringBuffer();

    	buff.append(tsTyp);
    	buff.append(trenner);
    	buff.append(beznr);
    	buff.append(trenner);
    	buff.append(lfdnr);
    	buff.append(trenner);
    	buff.append(lfnrfe);
    	buff.append(trenner);
    	buff.append(errcod);
    	buff.append(trenner);    	
    	buff.append(errdic);
    	buff.append(trenner);
    	
    	return new String(buff);    
    }

    public String getRegkz() {
    	 return beznr;
    }
    public void setRegkz(String argument) {
    	this.beznr = Utils.checkNull(argument);
    }

    public String getLfdnr() {
    	 return lfdnr;
    }
    public void setLfdnr(String argument) {
    	this.lfdnr = Utils.checkNull(argument);
    }

    public String getLfnrfe() {
    	 return lfnrfe;
    }
    public void setLfnrfe(String argument) {
    	this.lfnrfe = Utils.checkNull(argument);
    }

    public String getErrcode() {
    	 return errcod;
    }
    public void setErrcode(String argument) {
    	this.errcod = Utils.checkNull(argument);
    }

    public String getErrdic() {
    	 return errdic;
    }
    public void setErrdic(String argument) {
    	this.errdic = Utils.checkNull(argument);
    }
  
    public boolean isEmpty() {
	  return   Utils.isStringEmpty(lfdnr) &&
    	Utils.isStringEmpty(lfnrfe) &&
    	Utils.isStringEmpty(errcod) &&    	
    	Utils.isStringEmpty(errdic);
    }
}

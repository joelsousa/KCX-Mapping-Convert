package com.kewill.kcx.component.mapping.formats.fss.Port.subsets.V65; 

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module       : Port
 * Created		: 24.10.2011<br>
 * Description	: Statusmeldungen Referenzen . 
 *
 * @author iwaniuk
 * @version 1.0.00
 */

public class TsSTT extends Teilsatz {

	 private String beznr	= "";	 // Bezugsnummer
	 private String lfnr	= "";	 // 	 	
	 private String txtart	= "";	 // 
	 private String lfnrt		= "";	 // 
	 private String text 	= "";	 // 
	
	
    public TsSTT() {
    	tsTyp = "STT";
    }

    public void setFields(String[] fields) {
    	int size = fields.length;
    	//String ausgabe = "FSS: " + fields[0] + " size = " + size;

    	if (size < 1) { return; }
    	tsTyp = fields[0];
	    if (size < 2) { return; }
	    beznr = fields[1];
	    if (size < 3) { return; }
	    lfnr = fields[2];
	    if (size < 4) { return; }
	    txtart = fields[3];
	    if (size < 5) { return; }
	    lfnrt = fields[4];
	    if (size < 6) { return; }
	    text = fields[5];
	    
    }
   
    public String teilsatzBilden() {
    	StringBuffer buff = new StringBuffer();

    	buff.append(tsTyp);
    	buff.append(trenner);
    	buff.append(beznr);
    	buff.append(trenner);
    	buff.append(lfnr);
    	buff.append(trenner);    	
    	buff.append(txtart);
    	buff.append(trenner);
    	buff.append(lfnrt);
    	buff.append(trenner);
    	buff.append(text);
    	buff.append(trenner);
    	
    	return new String(buff);   
    }
   
    public String getBeznr() {
    	 return beznr;
    }
    public void setBeznr(String beznr) {
    	this.beznr = Utils.checkNull(beznr);
    }

    public String getLfnr() {
    	 return lfnr;
    }
    public void setLfnr(String lfdnr) {
    	this.lfnr = Utils.checkNull(lfdnr);
    }
    
    public String getTxtart() {
    	 return txtart;
    }
    public void setTxtart(String txtart) {
    	this.txtart = Utils.checkNull(txtart);
    }

    public String getLfnrt() {
    	 return lfnrt;
    }
    public void setLfnrt(String argument) {
    	this.lfnrt = Utils.checkNull(argument);
    }
    
    public String getText() {
   	 	return text;
    }
    public void setText(String argument) {
    	this.text = Utils.checkNull(argument);
    }   
    
    public boolean isEmpty() {
	  return  Utils.isStringEmpty(lfnr) &&
	  		  Utils.isStringEmpty(txtart) &&	  					 	
	  		  Utils.isStringEmpty(text);
    }

}


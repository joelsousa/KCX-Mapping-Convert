package com.kewill.kcx.component.mapping.formats.fss.Port.subsets.V71; 

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module       : Port
 * Created		: 04.10.2013<br>
 * Description	: BHT Hafenauftrag BHTContainer Packzertifikat  
 * 				: KIDS: BHTContainerAdditionalData 
 * @author iwaniuk
 * @version 1.0.00
 */

public class TsCPZ extends Teilsatz {

    private String beznr	= "";	 // Bezugsnummer
    private String connr	= "";	 // 
    private String txt		= "";	 // char(720) Container Packzertifikat    
  
    public TsCPZ() {
	    tsTyp = "CPZ";
    }

    public void setFields(String[] fields) {
    	int size = fields.length;
    	//String ausgabe = "FSS: " + fields[0] + " size = " + size;

    	if (size < 1) { return; }
    	tsTyp = fields[0];
	    if (size < 2) { return; }
	    beznr = fields[1];
	    if (size < 3) { return; }
	    connr = fields[2];	    
	    if (size < 4) { return; }
	    txt = fields[3];
	    
	    
    }

    public String teilsatzBilden() {
    	StringBuffer buff = new StringBuffer();

    	buff.append(tsTyp);
    	buff.append(trenner);
    	buff.append(beznr);
    	buff.append(trenner);
    	buff.append(connr);
    	buff.append(trenner);    
    	buff.append(txt);
    	buff.append(trenner);
    	
    	return new String(buff);    
    }
    
    public String getBeznr() {
    	 return beznr;
    }
    public void setBeznr(String argument) {
    	this.beznr = Utils.checkNull(argument);
    }
   
    public String getConnr() {
		return connr;
	}
	public void setConnr(String connr) {
		this.connr = Utils.checkNull(connr);
	}

	public String getSiegl5() {
		return txt;
	}
	public void setSiegl5(String text) {
		this.txt = Utils.checkNull(text);
	}

	public boolean isEmpty() {
    	return  Utils.isStringEmpty(connr) && Utils.isStringEmpty(txt); 
    }
}


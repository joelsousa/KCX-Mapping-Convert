package com.kewill.kcx.component.mapping.formats.fss.Port.subsetsBL; 

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module                :       PORT - BL.
 * Created               :       10.04.2012
 * Description    		 :
 *
 * @author                      iwaniuk
 * @version 1.0.00
 */

public class TsSeals extends Teilsatz {

	private String count = "";	
    private String sealingPartyCode	= "";	 // aktuelle Zeilenummer (Kopf 0)
    private String sealingParty		= "";	 // Sprache codiert
    private String sealingNumber	= "";	 // Text

    public TsSeals() {
	    tsTyp = "SEALS";	   
    }

    public void setFields(String[] fields) {
    	int size = fields.length;
    	
    	if (size < 1) { return; }
    	tsTyp = fields[0];
	    if (size < 2) { return; }
	    count = fields[1];
	    if (size < 3) { return; }
	    sealingPartyCode = fields[2];
	    if (size < 4) { return; }
	    sealingParty = fields[3];
	    if (size < 4) { return; }
	    sealingNumber = fields[3];
    }
    
    public String teilsatzBilden() {
    	StringBuffer buff = new StringBuffer();

    	buff.append(tsTyp);
    	buff.append(trenner);
    	buff.append(count);
    	buff.append(trenner);
    	buff.append(sealingPartyCode);
    	buff.append(trenner);
    	buff.append(sealingParty);
    	buff.append(trenner);
    	buff.append(sealingNumber);
    	//buff.append(trenner);

    	return new String(buff);    
    }

    public String getSealingPartyCode() {
    	 return sealingPartyCode;
    }
    public void setSealingPartyCode(String pos) {
    	this.sealingPartyCode = Utils.checkNull(pos);
    }
    
    public String getSealingParty() {
    	 return sealingParty;
    }
    public void setSealingParty(String languageCoded) {
    	this.sealingParty = Utils.checkNull(languageCoded);
    }

    public String getSealingNumber() {
    	 return sealingNumber;
    }
    public void setSealingNumber(String text) {
    	this.sealingNumber = Utils.checkNull(text);
    }

    public String getCount() {
   	 	return count;
    }
    public void setCount(String unLocode) {
    	this.count = Utils.checkNull(unLocode);
    }
    public void setCount(int i) {
    	if (i > 0) {
    		this.count = "" + i;
    	} else {
    		this.count = "";
    	}
    }

    public boolean isEmpty() {
    	return Utils.isStringEmpty(sealingPartyCode) && Utils.isStringEmpty(sealingNumber) &&
    		Utils.isStringEmpty(sealingParty);
    
    }
}

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

public class TsCommunicationContact extends Teilsatz {
    
	private String count = "";
    private String ediQualifier	 = "";	 // TE=Telefon FX=Telefax EM=Elektronische Post
    private String communicationNumber		 = "";	 // Kommunikationsnummer

    public TsCommunicationContact() {
	    tsTyp = "COMMUNICATIONCONTACT";
    }

    public void setFields(String[] fields) {
    	int size = fields.length;

    	if (size < 1) { return; }
    	tsTyp = fields[0];
	    if (size < 2) { return; }
	    count = fields[1];
	    if (size < 3) { return; }
	    ediQualifier  = fields[2];
	    if (size < 4) { return; }
	    communicationNumber = fields[3];
    }
    
    public String teilsatzBilden() {
    	StringBuffer buff = new StringBuffer();

    	buff.append(tsTyp);
    	buff.append(trenner);    	
    	buff.append(count);
    	buff.append(trenner);
    	buff.append(ediQualifier);
    	buff.append(trenner);
    	buff.append(communicationNumber);
    	//buff.append(trenner);

    	return new String(buff);    
    }
    
    public String getCommunicationNumber() {
    	 return communicationNumber;
    }
    public void setCommunicationNumber(String communicationNumber) {
    	this.communicationNumber = Utils.checkNull(communicationNumber);
    }

    public String getEdiQualifier() {
    	 return ediQualifier;
    }
    public void setEdiQualifier(String communicationQualifier) {
    	this.ediQualifier = Utils.checkNull(communicationQualifier);
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
    		this.count = "0";
    	}
    } 
   
    public boolean isEmpty() {
    	return  Utils.isStringEmpty(communicationNumber);        
    }

}

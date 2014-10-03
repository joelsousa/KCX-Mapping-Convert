package com.kewill.kcx.component.mapping.formats.fss.Port.subsetsBL; 

import com.kewill.kcx.component.mapping.countries.de.Port.msg.commonBL.Place;
import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module                :       PORT - BL.
 * Created               :       27.04.2012
 * Description    		 :
 *
 * @author                      iwaniuk
 * @version 1.0.00
 */

public class TsOneElement extends Teilsatz {

	private String element = "";	 //nur zaehler
	
    public TsOneElement(String type) {
	    tsTyp = type;
    }
   
    public void setFields(String[] fields) {
    	int size = fields.length;

    	if (size < 1) { return; }
    	tsTyp = fields[0];
	    if (size < 2) { return; }
	    element = fields[1];	    
    }

    public String getTsTyp() {
    	 return tsTyp;
    }
    public void setTsTyp(String value) {
    	this.tsTyp = Utils.checkNull(value);
    }
   
    public String getElement() {
   	 	return element;
    }
    public void setElement(String value) {
    	this.element = Utils.checkNull(value);
    }   
  
    public String teilsatzBilden() {
    	StringBuffer buff = new StringBuffer();

    	buff.append(tsTyp);
    	buff.append(trenner);
    	buff.append(element);
    	//buff.append(trenner);
    	
    	return new String(buff);    
    }
    
    public boolean isEmpty() {
    	//return  Utils.isStringEmpty(element); 
    	//auch leer sollte ausgegeben werden
    	return false;
    }
  
}

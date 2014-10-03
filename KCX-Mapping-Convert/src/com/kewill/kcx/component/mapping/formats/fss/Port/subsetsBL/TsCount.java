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

public class TsCount extends Teilsatz {

	private String count = "";	 //nur zaehler
	
    public TsCount(String type) {
	    tsTyp = type;
    }
   
    public void setFields(String[] fields) {
    	int size = fields.length;

    	if (size < 1) { return; }
    	tsTyp = fields[0];
	    if (size < 2) { return; }
	    count = fields[1];	    
    }

    public String getType() {
    	 return tsTyp;
    }
    public void setType(String value) {
    	this.tsTyp = Utils.checkNull(value);
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
  
    public String teilsatzBilden() {
    	StringBuffer buff = new StringBuffer();

    	buff.append(tsTyp);
    	buff.append(trenner);
    	buff.append(count);
    	//buff.append(trenner);
    	
    	return new String(buff);    
    }

    
    public boolean isEmpty() {
    	return  Utils.isStringEmpty(count); 
    }

}

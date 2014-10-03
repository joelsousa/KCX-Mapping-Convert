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

public class TsTermsOfTransport extends Teilsatz {

    private String code		= "";				
    private String text1	= "";	
    private String text2	= "";	
  
    public TsTermsOfTransport() {
	    tsTyp = "TERMSOFTRANSPORT";
    }

    public void setFields(String[] fields) {
    	int size = fields.length;
    	
    	if (size < 1) { return; }
    	tsTyp = fields[0];
	    if (size < 2) { return; }
	    code = fields[1];
	    if (size < 3) { return; }
	    text1 = fields[2];	
	    if (size < 4) { return; }
	    text2 = fields[3];
    }
    
    public String teilsatzBilden() {
    	StringBuffer buff = new StringBuffer();

    	buff.append(tsTyp);
    	buff.append(trenner);
    	buff.append(code);
    	buff.append(trenner);
    	buff.append(text1);
    	buff.append(trenner);
    	buff.append(text2);
    	//buff.append(trenner);
   
    	return new String(buff);    
    }

    public String getCode() {
    	 return code;
    }
    public void setCode(String code) {
    	this.code = Utils.checkNull(code);
    }

    public String getText1() {
    	 return text1;
    }
    public void setText1(String text) {
    	this.text1 = Utils.checkNull(text);
    }

    public String getText2() {
   	 return text2;
   }
   public void setText2(String text) {
   	this.text2 = Utils.checkNull(text);
   }   
    
    public boolean isEmpty() {
    	//return    	Utils.isStringEmpty(code) && Utils.isStringEmpty(text);    	
    	//auch leer sollte ausgegeben werden
    	return false;
    }
    
}

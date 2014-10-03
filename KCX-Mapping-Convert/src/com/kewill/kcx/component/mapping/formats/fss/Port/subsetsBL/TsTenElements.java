package com.kewill.kcx.component.mapping.formats.fss.Port.subsetsBL; 

import com.kewill.kcx.component.mapping.countries.de.Port.msg.commonBL.AddressUnqualified;
import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Module                :       PORT - BL.
 * Created               :       04.05.2012
 * Description    		 :
 *
 * @author                      iwaniuk
 * @version 1.0.00
 */

public class TsTenElements extends Teilsatz {
   
    private String element = ""; 
    private String text1 = "";  
    private String text2 = "";
    private String text3 = "";
    private String text4 = "";
    private String text5 = "";
    private String text6 = "";
    private String text7 = "";
    private String text8 = "";  
    private String text9 = "";  
    private String text10 = "";  
    
 
    public TsTenElements(String type) {
	    //tsTyp = "TECHNICALNAME";
    	tsTyp = type;
    }

    public void setFields(String[] fields) {
    	int size = fields.length;

    	if (size < 1) { return; }
    	tsTyp = fields[0];
	    if (size < 2) { return; }
	    element = fields[1];
	    if (size < 3) { return; }
	    text1 = fields[2];
	    if (size < 4) { return; }
	    text2 = fields[3];
	    if (size < 5) { return; }
	    text3 = fields[4];
	    if (size < 6) { return; }
	    text4 = fields[5];
	    if (size < 7) { return; }
	    text5 = fields[6];
	    if (size < 8) { return; }
	    text6 = fields[7];
	    if (size < 9) { return; }
	    text7 = fields[8];
	    if (size < 10) { return; }	  
	    text8 = fields[6];
	    if (size < 8) { return; }
	    text9 = fields[7];
	    if (size < 9) { return; }
	    text10 = fields[8];
	    if (size < 10) { return; }	   
    }

    public String getElement() {
    	 return element;
    }
    public void setElement(String edi) {
    	this.element = Utils.checkNull(edi);
    }
    
    public String getText1() {
   	 return text1;
   }
   public void setText1(String value) {
   	this.text1 = Utils.checkNull(value);
   }

    public String getText2() {
   	 	return text2;
    }
    public void setText2(String value) {
   		this.text2 = Utils.checkNull(value);
    }

    public String getText3() {
    	return text3;
    }
    public void setText3(String value) {
    	this.text3 = Utils.checkNull(value);
    }
    
    public String getText4() {
   	 return text4;
   }
   public void setText4(String value) {
   	this.text4 = Utils.checkNull(value);
   }

   public String getText5() {
  	 	return text5;
   }
   public void setText5(String value) {
  		this.text5 = Utils.checkNull(value);
   }

   public String getText6() {
   	return text6;
   }
   public void setText6(String value) {
   	this.text6 = Utils.checkNull(value);
   }
   
   public String getText7() {
  	 return text7;
  }
  public void setText7(String value) {
  	this.text7 = Utils.checkNull(value);
  }

  public String getText8() {
 	 	return text8;
  }
  public void setText8(String value) {
 		this.text8 = Utils.checkNull(value);
  }

  public String getText9() {
 	 return text9;
 }
 public void setText9(String value) {
 	this.text9 = Utils.checkNull(value);
 }
 
 public String getText10() {
	 return text10;
}
public void setText10(String value) {
	this.text10 = Utils.checkNull(value);
}

    public String teilsatzBilden() {
    	StringBuffer buff = new StringBuffer();

    	buff.append(tsTyp);    	
    	buff.append(trenner);    	
    	buff.append(element);    	
    	buff.append(trenner);    
    	buff.append(text1);
    	buff.append(trenner);
    	buff.append(text2);
    	buff.append(trenner);
    	buff.append(text3);
    	buff.append(trenner);
    	buff.append(text4);
    	buff.append(trenner);
    	buff.append(text5);
    	buff.append(trenner);
    	buff.append(text6);
    	buff.append(trenner);
    	buff.append(text7);
    	buff.append(trenner);
    	buff.append(text8);
    	buff.append(trenner);  
    	buff.append(text9);
    	buff.append(trenner);
    	buff.append(text10);
    	//buff.append(trenner);
    	
    	return new String(buff);
    }

    public void setAllFields(String element, List<String> list) {
    	
    	this.element = element;
    	
    	if (list == null) {
    		return;
    	}    	    	
    	int count = 0;
    	for (String tx : list) {
    		if (!Utils.isStringEmpty(tx)) {
    			count = count + 1;
    			if (count == 1) {
    				text1 = tx;
    			}
    			if (count == 2) {
    				text2 = tx;
    			}
    			if (count == 3) {
    				text3 = tx;
    			}
    			if (count == 4) {
    				text4 = tx;
    			}
    			if (count == 5) {
    				text5 = tx;
    			}
    			if (count == 6) {
    				text6 = tx;
    			}
    			if (count == 7) {
    				text7 = tx;
    			}
    			if (count == 8) {
    				text8 = tx;
    			}   
    			if (count == 9) {
    				text9 = tx;
    			}
    			if (count == 10) {
    				text10 = tx;
    			}
    		}
    	}
    }
   
    public boolean isEmpty() {
    	return  Utils.isStringEmpty(element)
    		&& Utils.isStringEmpty(text1) && Utils.isStringEmpty(text2);  	
    }

}

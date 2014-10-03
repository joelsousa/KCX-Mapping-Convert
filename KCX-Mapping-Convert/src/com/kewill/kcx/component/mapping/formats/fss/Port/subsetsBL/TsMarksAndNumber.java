package com.kewill.kcx.component.mapping.formats.fss.Port.subsetsBL; 

import java.util.ArrayList;
import java.util.List;
import com.kewill.kcx.component.mapping.countries.de.Port.msg.commonBL.Address;
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

public class TsMarksAndNumber extends Teilsatz {

	 private String marksAndNumber1 = "";  
	 private String marksAndNumber2 = "";
	 private String marksAndNumber3 = "";
	 private String marksAndNumber4 = "";
	 private String marksAndNumber5 = "";
	 private String marksAndNumber6 = "";
	 private String marksAndNumber7 = "";
	 private String marksAndNumber8 = "";
	 private String marksAndNumber9 = "";
	 private String marksAndNumber10 = "";
		
    public TsMarksAndNumber() {
	    tsTyp = "MARKSANDNUMBERS";
    }

    public void setFields(String[] fields) {
    	int size = fields.length;

    	if (size < 1) { return; }
    	tsTyp = fields[0];    	
	    if (size < 2) { return; }
	    marksAndNumber1 = fields[1];	   
	    if (size < 3) { return; }
	    	marksAndNumber2 = fields[2];	
	    	if (size < 4) { return; }
	    	marksAndNumber3 = fields[3];
	    	if (size < 5) { return; }
	    	marksAndNumber4 = fields[4];
	    	if (size < 6) { return; }
	    	marksAndNumber5 = fields[5];
	    	if (size < 7) { return; }
	    	marksAndNumber6 = fields[6];
	    	if (size < 8) { return; }
	    	marksAndNumber7 = fields[7];
	    	if (size < 9) { return; }
	    	marksAndNumber8 = fields[8];
	    	if (size < 10) { return; }
	    	marksAndNumber9 = fields[9];
	    	if (size < 11) { return; }
	    	marksAndNumber10 = fields[10];
    }

    public String teilsatzBilden() {
    	StringBuffer buff = new StringBuffer();

    	buff.append(tsTyp);
    	buff.append(trenner);
    	buff.append(marksAndNumber1);
    	buff.append(trenner);   
    	buff.append(marksAndNumber2);
    	buff.append(trenner); 
    	buff.append(marksAndNumber3);
    	buff.append(trenner); 
    	buff.append(marksAndNumber4);
    	buff.append(trenner); 
    	buff.append(marksAndNumber5);
    	buff.append(trenner); 
    	buff.append(marksAndNumber6);
    	buff.append(trenner); 
    	buff.append(marksAndNumber7);
    	buff.append(trenner); 
    	buff.append(marksAndNumber8);
    	buff.append(trenner); 
    	buff.append(marksAndNumber9);
    	buff.append(trenner); 
    	buff.append(marksAndNumber10);
    	//buff.append(trenner); 
    	
    	return new String(buff);
    }

    
    public String getMarksAndNumber1() {
    	return marksAndNumber1;
    }
    public void setMarksAndNumber1(String text) {
    	this.marksAndNumber1 = Utils.checkNull(text);
    }    

	public String getMarksAndNumber2() {
		return marksAndNumber2;
	}

	public void setMarksAndNumber2(String text) {
		this.marksAndNumber2 = Utils.checkNull(text);
	}

	public String getMarksAndNumber3() {
		return marksAndNumber3;
	}

	public void setMarksAndNumber3(String text) {
		this.marksAndNumber3 = Utils.checkNull(text);
	}

	public String getMarksAndNumber4() {
		return marksAndNumber4;
	}

	public void setMarksAndNumber4(String text) {
		this.marksAndNumber4 = Utils.checkNull(text);
	}

	public String getMarksAndNumber5() {
		return marksAndNumber5;
	}

	public void setMarksAndNumber5(String text) {
		this.marksAndNumber5 = Utils.checkNull(text);
	}

	public String getMarksAndNumber6() {
		return marksAndNumber6;
	}

	public void setMarksAndNumber6(String text) {
		this.marksAndNumber6 = Utils.checkNull(text);
	}

	public String getMarksAndNumber7() {
		return marksAndNumber7;
	}

	public void setMarksAndNumber7(String text) {
		this.marksAndNumber7 = Utils.checkNull(text);
	}

	public String getMarksAndNumber8() {
		return marksAndNumber8;
	}

	public void setMarksAndNumber8(String text) {
		this.marksAndNumber8 = Utils.checkNull(text);
	}

	public String getMarksAndNumber9() {
		return marksAndNumber9;
	}

	public void setMarksAndNumber9(String text) {
		this.marksAndNumber9 = Utils.checkNull(text);
	}

    public String getMarksAndNumber10() {
    	return marksAndNumber10;
    }
    public void setMarksAndNumber10(String text) {
    	this.marksAndNumber10 = Utils.checkNull(text);
    }
    
    public void setAllFields(List<String> list) {
    	if (list == null) {
    		return;
    	}
    	
    	int ii = 0;
    	for (String marks : list) {
    		if (!Utils.isStringEmpty(marks)) {
    			ii = ii + 1;
    			if (ii == 1) {
    				marksAndNumber1 = marks;
    			}
    			if (ii == 2) {
    				marksAndNumber2 = marks;
    			}
    			if (ii == 3) {
    				marksAndNumber3 = marks;		
    			}
    			if (ii == 4) {
    				marksAndNumber4 = marks;		
    			}
    			if (ii == 5) {
    				marksAndNumber5 = marks;		
    			}
    			if (ii == 6) {
    				marksAndNumber6 = marks;
    			}
    			if (ii == 7) {
    				marksAndNumber7 = marks;
    			}
    			if (ii == 8) {
    				marksAndNumber8 = marks;		
    			}
    			if (ii == 9) {
    				marksAndNumber9 = marks;		
    			}
    			if (ii == 10) {
    				marksAndNumber10 = marks;		
    			}
    		}    		
    	}    	   
    }
    
	public boolean isEmpty() {
    	return  Utils.isStringEmpty(marksAndNumber1);
    }
}

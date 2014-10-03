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

public class TsTextOnBL extends Teilsatz {
	
	private String tsTyp2				= "";	 // e.g. BillOfLadingRemarks, CargoRemarks	
    private String count		 		= "";	 // zaehler 
    private String ediQualifier	 		= "";	 //
    private String textReference		= "";	 // 
    private String text1   		 		= "";	 // Text, 
    private String text2   		 		= "";	 // Text
    private String text3   		 		= "";	 // Text
    private String text4   		 		= "";	 // Text
    private String text5   		 		= "";	 // Text
    private String languageCoded		= "";	 // Sprache codiert
    
    public TsTextOnBL(String type, String type2) {
	    tsTyp = type;	
	    tsTyp2 = type2;
    }
   
    public void setFields(String[] fields) {
    	int size = fields.length;
    	
    	if (size < 1) { return; }
    	tsTyp = fields[0];
	    if (size < 2) { return; }
	    tsTyp2 = fields[1];
	    if (size < 3) { return; }
	    count = fields[2];	    
	    if (size < 4) { return; }
	    ediQualifier = fields[3];
	    if (size < 5) { return; }
	    textReference = fields[4];
	    if (size < 6) { return; }
	    text1 = fields[5];	
	    if (size < 7) { return; }
	    text2 = fields[6];	
	    if (size < 8) { return; }
	    text3 = fields[7];	
	    if (size < 9) { return; }
	    text4 = fields[8];
	    if (size < 10) { return; }
	    text5 = fields[9];
	    if (size < 11) { return; }
	    languageCoded = fields[10];
    }

    public String teilsatzBilden() {
    	StringBuffer buff = new StringBuffer();
     
    	buff.append(tsTyp);
    	buff.append(trenner);
    	buff.append(tsTyp2);
    	buff.append(trenner);
    	buff.append(count);
    	buff.append(trenner);    	
    	buff.append(ediQualifier);
    	buff.append(trenner);
    	buff.append(textReference);
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
    	buff.append(languageCoded);    	
    	//buff.append(trenner);
    	
    	return new String(buff);
    }    
    
    public String getCount() {
    	 return count;
    }
    public void setCount(int i) {
    	if (i > 0) {
    		this.count = "" + i;
    	} else {
    		this.count = "0";
    	}
    }
    
    public String getEdiQualifier() {
    	 return ediQualifier;
    }
    public void setEdiQualifier(String value) {
    	this.ediQualifier = Utils.checkNull(value);
    }
    
    public String getTsTyp2() {
   	 	return tsTyp2;
    }
    public void seTsTyp2(String value) {
    	this.tsTyp2 = Utils.checkNull(value);
    }
   
    public String getTextReference() {
   	 	return textReference;
    }
    public void setTextReference(String value) {
    	this.textReference = Utils.checkNull(value);
    }    
    
    public String getLanguageCoded() {
   	 	return languageCoded;
    }
    public void setLanguageCoded(String value) {
    	this.languageCoded = Utils.checkNull(value);
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
   public String getText3() {
  	 return text3;
  }
  public void setText3(String text) {
  	this.text3 = Utils.checkNull(text);
  }
  public String getText4() {
 	 return text4;
 }
 public void setText4(String text) {
 	this.text4 = Utils.checkNull(text);
 }
 public String getText5() {
	 return text5;
}
public void setText5(String text) {
	this.text5 = Utils.checkNull(text);
}

    public boolean isEmpty() {
    	return Utils.isStringEmpty(tsTyp2) && Utils.isStringEmpty(textReference) &&
    	Utils.isStringEmpty(text1);    
    }

}

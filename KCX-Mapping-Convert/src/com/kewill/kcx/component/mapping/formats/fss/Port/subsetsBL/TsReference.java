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

public class TsReference extends Teilsatz {

	private String tsTyp2	 		= "";    //FORWARDERSREFERENCE (FF), ADDITIONALREFERENCE (die restlichen)                  
	private String count            = "";
    private String ediQualifier     = "";	 //FF,   BM, BN
    private String reference		= "";
    private String dateTime		 	= "";	 //  Datum der Referenz
    private String dateTimeType		= "";	 // "102" wenn 8-stellig, "203" wenn 12-stellig
    
    public TsReference(String type) {
	    tsTyp = "REFERENCES";
    	tsTyp2 = type;
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
	    reference = fields[4];
	    if (size < 6) { return; }
	    dateTime = fields[5];
	    if (size < 6) { return; }
	    dateTimeType = fields[5];
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
    	buff.append(reference);
    	buff.append(trenner);
    	buff.append(dateTime);
    	buff.append(trenner);
    	buff.append(dateTimeType);
    	//buff.append(trenner);

    	return new String(buff);
    }
    
    public String getTsTyp2() {
    	 return tsTyp2;
    }
    public void setTsTyp2(String reference) {
    	this.tsTyp2 = Utils.checkNull(reference);
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
    public String getReference() {
   	  	return reference;
    }
    public void setReference(String reference) {
    	this.reference = Utils.checkNull(reference);
    }
   
    public String getDateTimeType() {
    	 return dateTimeType;
    }
    public void setDateTimeType(String date) {
    	this.dateTimeType = Utils.checkNull(date);
    }
    
    public String getDateTime() {
    	 return dateTime;
    }
    public void setDateTime(String dateTime) {
    	this.dateTime = Utils.checkNull(dateTime);
    }
    
    public String getEdiQualifier() {
   	 return ediQualifier;
   }
   public void setEdiQualifier(String dateTime) {
   	this.ediQualifier = Utils.checkNull(dateTime);
   }

    public boolean isEmpty() {
    	return  Utils.isStringEmpty(reference) &&    	
    	 Utils.isStringEmpty(dateTime);
    }
}

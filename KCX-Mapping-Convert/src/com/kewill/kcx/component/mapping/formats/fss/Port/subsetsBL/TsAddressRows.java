package com.kewill.kcx.component.mapping.formats.fss.Port.subsetsBL; 

import com.kewill.kcx.component.mapping.countries.de.Port.msg.commonBL.AddressUnqualified;
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

public class TsAddressRows extends Teilsatz {

	 private String firstRow = "";    // Positionsnr (im Kopfteil 0)
	 private String nextRow1 = "";	 // 1. Zeile des Namens und 4 NextRow zusammengefasst    
	 private String nextRow2 = "";	
	 private String nextRow3 = "";	
	 private String nextRow4 = "";	
	
    
   // private String communicationQualifier		 = "";

    public TsAddressRows() {
	    tsTyp = "ADDRESSUNQUALIFIED";
    }

    public void setFields(String[] fields) {
    	int size = fields.length;

    	if (size < 1) { return; }
    	tsTyp = fields[0];
	    if (size < 2) { return; }
	    firstRow = fields[1];
	    if (size < 3) { return; }
	    nextRow1 = fields[2];
	    if (size < 4) { return; }
	    nextRow2 = fields[3];
	    if (size < 5) { return; }
	    nextRow3 = fields[4];
	    if (size < 6) { return; }
	    nextRow4 = fields[5];	   
    }

    public String teilsatzBilden() {
    	StringBuffer buff = new StringBuffer();

    	buff.append(tsTyp);
    	buff.append(trenner);
    	buff.append(firstRow);
    	buff.append(trenner);    	
    	buff.append(nextRow1);
    	buff.append(trenner);
    	buff.append(nextRow2);
    	buff.append(trenner);
    	buff.append(nextRow3);
    	buff.append(trenner);
    	buff.append(nextRow4);
    	//buff.append(trenner);
    	
    	return new String(buff);
    }

    
    public String getNextRow1() {
   	 	return nextRow1;
    }	
    public void setNextRow1(String value) {
    	this.nextRow1 = Utils.checkNull(value);
    }

    public String getNextRow2() {
   	 	return nextRow2;
    }	
    public void setNextRow2(String value) {
    	this.nextRow2 = Utils.checkNull(value);
    }
    
    public String getNextRow3() {
   	 	return nextRow3;
    }	
    public void setNextRow3(String value) {
    	this.nextRow3 = Utils.checkNull(value);
    }
    
    public String getNextRow4() {
   	 	return nextRow4;
    }	
    public void setNextRow4(String value) {
    	this.nextRow4 = Utils.checkNull(value);
    }
    
    public String getFirstRow() {
   	 	return firstRow;
    }	
    public void setFirstRow(String value) {
    	this.firstRow = Utils.checkNull(value);
    }
  
    public void setAllFields(AddressUnqualified address) {
    	if (address == null) {
    		return;
    	}
    	if (address.getPartyNameAndAddress() != null) {
    		if (address.getPartyNameAndAddress().getFirstRow() != null) {
    			firstRow = address.getPartyNameAndAddress().getFirstRow();
    		}
    		if (address.getPartyNameAndAddress().getNextRowList() != null) {
    			int count = 0;
    			for (String text : address.getPartyNameAndAddress().getNextRowList()) {
    				if (!Utils.isStringEmpty(text)) {
    					count = count + 1;
    					if (count == 1) {
    						nextRow1 = text;
    					} 
    					if (count == 2) {
    						nextRow2 = text;
    					} 
    					if (count == 3) {
    						nextRow3 = text;
    					} 
    					if (count == 4) {
    						nextRow4 = text;
    					}     					
    				}
    			}
    		}
    	}    	    	
    }
    
    public boolean isEmpty() {
    	return  Utils.isStringEmpty(firstRow) && Utils.isStringEmpty(nextRow1) && Utils.isStringEmpty(nextRow2); 
    }
}

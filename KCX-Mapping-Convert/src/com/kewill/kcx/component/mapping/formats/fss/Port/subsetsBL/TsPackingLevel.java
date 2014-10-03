package com.kewill.kcx.component.mapping.formats.fss.Port.subsetsBL; 

import com.kewill.kcx.component.mapping.countries.de.Port.msg.commonBL.Place;
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

public class TsPackingLevel extends Teilsatz {
	
	private String tsTyp2 = "";
	private String itemNumber = "";	
	private String grossWeightKilogram = "";

    public TsPackingLevel(String type) {
	    tsTyp = "PACKINGLEVEL";
	    tsTyp2 = type;
    }
    
    public void setFields(String[] fields) {
    	int size = fields.length;

    	if (size < 1) { return; }
    	tsTyp = fields[0];
	    if (size < 2) { return; }
	    tsTyp2 = fields[1];
	    if (size < 3) { return; }
	    itemNumber = fields[2];	 
	    if (size < 4) { return; }
	    grossWeightKilogram = fields[3];	 
    }

    public String teilsatzBilden() {
    	StringBuffer buff = new StringBuffer();

    	buff.append(tsTyp);
    	buff.append(trenner);
    	buff.append(tsTyp2);
    	buff.append(trenner);
    	buff.append(itemNumber);
    	buff.append(trenner);
    	buff.append(grossWeightKilogram);
    	//buff.append(trenner);
    	
    	return new String(buff);    
    }
   
    public String getTsTyp2() {
		return tsTyp2;
	}
	public void setTsTyp2(String tsTyp2) {
		this.tsTyp2 = Utils.checkNull(tsTyp2);
	}

	public String getItemNumber() {
		return itemNumber;
	}
	public void setItemNumber(String itemNumber) {
		this.itemNumber = Utils.checkNull(itemNumber);
	}

	public String getGrossWeightKilogram() {
		return grossWeightKilogram;
	}
	public void setGrossWeightKilogram(String grossWeightKilogram) {
		this.grossWeightKilogram = Utils.checkNull(grossWeightKilogram);
	}

	public boolean isEmpty() {
    	return  Utils.isStringEmpty(grossWeightKilogram); 
    }

}

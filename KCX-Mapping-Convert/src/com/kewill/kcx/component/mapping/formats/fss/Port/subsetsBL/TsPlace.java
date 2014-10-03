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

public class TsPlace extends Teilsatz {

	private String tsTyp2            = "";
	private String count             = "";
	private String ediQualifier      = "";
    private String unLocode		 	 = "";	 //  Code des Ortes
    private String locationClearText = "";	 //  Klartext

    public TsPlace() {
	    tsTyp = "PLACE";
    }   
    public TsPlace(String type, String edi) {
    	tsTyp = "PLACE";
	    tsTyp2 = type;
	    ediQualifier = edi;
    }
    
    public void setFields(String[] fields) {
    	int size = fields.length;

    	if (size < 1) { return; }
    	tsTyp = fields[0];
	    if (size < 2) { return; }
	    tsTyp2 = fields[1];
	    if (size < 3) { return; }
	    count = fields[2];	
	    if (size < 1) { return; }
	    ediQualifier = fields[0];
	    if (size < 2) { return; }
	    unLocode = fields[1];
	    if (size < 3) { return; }
	    locationClearText = fields[2];
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
    	buff.append(unLocode);
    	buff.append(trenner);
    	buff.append(locationClearText);
    	//buff.append(trenner);

    	return new String(buff);    
    }
    
    public String getUnLocode() {
    	 return unLocode;
    }
    public void setUnLocode(String unLocode) {
    	this.unLocode = Utils.checkNull(unLocode);
    }

    public String getLocationClearText() {
    	 return locationClearText;
    }
    public void setLocationClearText(String value) {
    	this.locationClearText = Utils.checkNull(value);
    }
  
    public String getCount() {
   	 	return count;
    }
    public void setCount(String value) {
    	this.count = Utils.checkNull(value);
    }
    public void setCount(int i) {
    	if (i > 0) {
    		this.count = "" + i;
    	} else {
    		this.count = "0";
    	}
    }    
    
    public String getTyp2() {
   	 return tsTyp2;
   }
   public void setTyp2(String value) {
   	this.tsTyp2 = Utils.checkNull(value);
   }

   public String getEdiQualifier() {
   	 return ediQualifier;
   }
   public void setEdiQualifier(String value) {
   	this.ediQualifier = Utils.checkNull(value);
   }    
    public void setAllFields(Place place) {
    	if (place == null) {
    		return;
    	}
    	unLocode = Utils.checkNull(place.getUnLocode());
    	locationClearText = Utils.checkNull(place.getLocationClearText());
    }

    public boolean isEmpty() {
    	return  Utils.isStringEmpty(unLocode) && Utils.isStringEmpty(locationClearText); 
    }

}

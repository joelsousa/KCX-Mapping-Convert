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

public class TsCarriage extends Teilsatz {

	private String tsTyp2                   = "";
	private String count                    = "";
	private String ediQualifier             = "";
    private String estimatedDepartureDate	= "";	 
    private String estimatedArrivalDate		= "";	 
    private String shipReferenceNumber		= "";	 
   
    public TsCarriage(String type) {
	    tsTyp = "CARRIAGE";
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
	    estimatedDepartureDate = fields[4];
    	if (size < 6) { return; }
    	estimatedArrivalDate = fields[5];
    	if (size < 6) { return; }
    	shipReferenceNumber = fields[5];
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
    	buff.append(estimatedDepartureDate);
    	buff.append(trenner);
    	buff.append(estimatedArrivalDate);
    	buff.append(trenner);
    	buff.append(shipReferenceNumber);
    	//buff.append(trenner);

    	return new String(buff);
    }

    public String getEstimatedDepartureDate() {
    	 return estimatedDepartureDate;
    }
    public void setEstimatedDepartureDate(String estimatedDepartureDate) {
    	this.estimatedDepartureDate = Utils.checkNull(estimatedDepartureDate);
    }

    public String getEstimatedArrivalDate() {
    	 return estimatedArrivalDate;
    }
    public void setEstimatedArrivalDate(String estimatedArrivalDate) {
    	this.estimatedArrivalDate = Utils.checkNull(estimatedArrivalDate);
    }

    public String getShipReferenceNumber() {
    	 return shipReferenceNumber;
    }
    public void setShipReferenceNumber(String refnr) {
    	this.shipReferenceNumber = Utils.checkNull(refnr);
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
    
    public String getEdiQualifier() {
   	 return ediQualifier;
   }
   public void setEdiQualifier(String edi) {
   	this.ediQualifier = Utils.checkNull(edi);
   }

    public boolean isEmpty() {
	return    	Utils.isStringEmpty(estimatedDepartureDate)
    	&& Utils.isStringEmpty(estimatedArrivalDate)
    	&& Utils.isStringEmpty(shipReferenceNumber);

    }

}

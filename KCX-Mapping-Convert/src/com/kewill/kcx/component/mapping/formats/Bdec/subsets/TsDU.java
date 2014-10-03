/*
* Function    : TsDU.java
* Title       :
* Created     : 29.09.2009
* Author      : Elisabeth Iwaniuk
* Description : DetailsUPCs for KIDS-AES to eCustoms
*/

package com.kewill.kcx.component.mapping.formats.Bdec.subsets;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Modul        : TsDU<br>
 * Erstellt     : 29.09.2009<br>
 * Beschreibung : DetailsUPCs for KIDS-AES to eCustoms.
 * 
 * @author iwaniuk
 * @version 6.0.00
 */
public class TsDU extends Teilsatz {

    private String parentMessageType = "";       //SDILIN, IFDDTL, IFWDTL, ISDDTL, ISWDTL
    private String upc               = "";       //
    private String upcQuantity       = "";       //
    private String upcValue          = "";       //
    private String upcComments       = "";       //
    private String upcUnitValue      = "";       //
    private String upcUnitWeight     = "";       //
    private String upcWeight         = "";       //


    public TsDU() {
        tsTyp = "DU";
        trenner = trennerUK;
    }

    public void setFields(String[] fields) {
		int size = fields.length;
		//String ausgabe = "TsDU: "+fields[0]+" size = "+ size;
		//Utils.log( ausgabe);
					
		if (size < 1) { return; }
        tsTyp = fields[0];
        if (size < 2) { return; }
        parentMessageType = fields[1];
        if (size < 3) { return; }
        upc               = fields[2];
        if (size < 4) { return; }
        upcQuantity       = fields[3];
        if (size < 5) { return; }
        upcValue          = fields[4];    
        if (size < 6) { return; }
        upcComments       = fields[5];
        if (size < 7) { return; }
        upcUnitValue      = fields[6];
        if (size < 8) { return; }
        upcUnitWeight     = fields[7]; 
        if (size < 9) { return; }
        upcWeight         = fields[8]; 
    }

    public String teilsatzBilden() {
        StringBuffer buff = new StringBuffer();

        buff.append(tsTyp);
        buff.append(trenner);
        buff.append(parentMessageType);
        buff.append(trenner);
        buff.append(upc);
        buff.append(trenner);
        buff.append(upcQuantity);
        buff.append(trenner);
        buff.append(upcValue);
        buff.append(trenner);
        buff.append(upcComments);
        buff.append(trenner);
        buff.append(upcUnitValue);
        buff.append(trenner);
        buff.append(upcUnitWeight);
        buff.append(trenner);
        buff.append(upcWeight);
        //buff.append(trenner);

        return new String(buff);
      }

	public String getParentMessageType() {
		return parentMessageType;	
	}

	public void setParentMessageType(String argument) {
		this.parentMessageType = Utils.checkNull(argument);
	}

	public String getUpc() {
		return upc;	
	}

	public void setUpc(String argument) {
		this.upc = Utils.checkNull(argument);
	}
	
	public String getUpcQuantity() {
		return upcQuantity;	
	}

	public void setUpcQuantity(String argument) {
		this.upcQuantity = Utils.checkNull(argument);
	}
	
	public String getUpcValue() {
		return upcValue;	
	}

	public void setUpcValue(String argument) {
		this.upcValue = Utils.checkNull(argument);
	}
	
	public String getUpcComments() {
		return upcComments;	
	}

	public void setUpcComments(String argument) {
		this.upcComments = Utils.checkNull(argument);
	}
	
	public String getUpcUnitValue() {
		return upcUnitValue;	
	}

	public void setUpcUnitValue(String argument) {
		this.upcUnitValue = Utils.checkNull(argument);
	}
	
	public String getUpcUnitWeight() {
		return upcUnitWeight;	
	}

	public void setUpcUnitWeight(String argument) {
		this.upcUnitWeight = Utils.checkNull(argument);
	}
	
	public String getUpcWeight() {
		return upcWeight;	
	}

	public void setUpcWeight(String argument) {
		this.upcWeight = Utils.checkNull(argument);
	}

	public boolean isEmpty() {			
		return (Utils.isStringEmpty(parentMessageType) && Utils.isStringEmpty(upc) &&
			 Utils.isStringEmpty(upcQuantity) && Utils.isStringEmpty(upcValue) &&
		     Utils.isStringEmpty(upcComments) && Utils.isStringEmpty(upcUnitValue) &&
			 Utils.isStringEmpty(upcWeight));
	}
}



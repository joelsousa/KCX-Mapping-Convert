package com.kewill.kcx.component.mapping.formats.fss.edec.Import.subsets.V70;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/**
* Module		: EDEC Import 70<br>
 * Created		: 29.10.2012<br>
 * Description  : FSS Definition of Subset CKR - Vermerke. 
 * 
 * @author iwaniuk
 * @version 7.0.00
 */
public class TsCKR extends Teilsatz {

    private String refnr = "";       	// 
    private String vm = "";      		// Besondere Vermerke 
    
    public TsCKR() {
        tsTyp = "CKR";
    }
    public void setFields(String[] fields) {
    	
		int size = fields.length;
		//String ausgabe = "FSS: " + fields[0] + " size = " + size;
		//Utils.log( ausgabe);
		
		if (size < 1) { return; }		
        tsTyp = fields[0];
        if (size < 2) { return; }	       
        refnr = fields[1];
        if (size < 3) { return; }	       
        vm = fields[2];               	
    }

    public String teilsatzBilden() {
        StringBuffer buff = new StringBuffer();

        buff.append(tsTyp);
        buff.append(trenner);
        buff.append(refnr);
        buff.append(trenner);       
        buff.append(vm);
        buff.append(trenner);
        
        return new String(buff);
      }
	
	public boolean isEmpty() {
	    return Utils.isStringEmpty(vm);
	}
	
	public String getRefnr() {
		return refnr;	
	}
	public void setRefnr(String refnr) {
		this.refnr = Utils.checkNull(refnr);
	}
		
	public String getVm() {
		return vm;	
	}
	public void setVm(String vm) {
		this.vm = Utils.checkNull(vm);
	}	

}



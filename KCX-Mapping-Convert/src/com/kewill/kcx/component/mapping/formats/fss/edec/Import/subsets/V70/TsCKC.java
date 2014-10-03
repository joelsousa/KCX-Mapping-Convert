package com.kewill.kcx.component.mapping.formats.fss.edec.Import.subsets.V70;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;
import java.util.List;
import java.util.Vector;

/**
 * Module		: EDEC Import 70<br>
 * Created		: 29.10.2012<br>
 * Description  : FSS Definition of Subset CKC - Containerdaten. 
 * 
 * @author iwaniuk
 * @version 7.0.00
 */


public class TsCKC extends Teilsatz {
	private String refnr = "";       //Bezugsnummer
	private String ctnr  = "";		 //Container-Nummer	
	
	public TsCKC() {
        tsTyp = "CKC";
    }

    public void setFields(String[] fields) {  
		int size = fields.length;
		//String ausgabe = "FSS: "+fields[0]+" size = "+ size;
		//Utils.log( ausgabe);		
		
		if (size < 1) { return; }		
        	tsTyp = fields[0];
        if (size < 2) { return; }	
        	refnr = fields[1];
        if (size < 3) { return; }	
        	ctnr = fields[2];       
    }

    public String teilsatzBilden() {
        StringBuffer buff = new StringBuffer();

        buff.append(tsTyp);
        buff.append(trenner);
        buff.append(refnr);
        buff.append(trenner);
        buff.append(ctnr);
        buff.append(trenner);        

        return new String(buff);
      }

    public void setRefnr(String refnr) {
		this.refnr = Utils.checkNull(refnr);
	}

	public String getRefnr() {
		return refnr;	
	}

	public String getCtnr() {
		return ctnr;	
	}

	public void setCtnr(String ctnr) {
		this.ctnr = Utils.checkNull(ctnr);
	}	
	
	public boolean isEmpty() {
		return Utils.isStringEmpty(ctnr);
	}	
}	

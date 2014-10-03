package com.kewill.kcx.component.mapping.formats.fss.edec.Import.subsets;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: EDEC Import 70<br>
 * Created		: 29.10.2012<br>
 * Description  : FSS Definition of Ergänzungsdaten Unterlagen. 
 * 
 * @author iwaniuk
 * @version 7.0.00
 */

public class TsCPU extends Teilsatz {

    private String refnr  = "";       // ReferenceNumber
    private String posnr  = "";     
    private String untart = "";      
    private String untnr  = "";      
    private String untdat = "";     
    private String untzus = "";       
   
    public TsCPU() {
        tsTyp = "CPU";
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
        posnr = fields[2];
        if (size < 4) { return; }	
        untart = fields[3];
        if (size < 5) { return; }	
        untnr = fields[4];        
        if (size < 6) { return; }	
        untdat = fields[5];
        if (size < 7) { return; }	
        untzus = fields[6];
       
    }

    public String teilsatzBilden() {
        StringBuffer buff = new StringBuffer();

        buff.append(tsTyp);
        buff.append(trenner);
        buff.append(refnr);
        buff.append(trenner);
        buff.append(posnr);
        buff.append(trenner);
        buff.append(untart);
        buff.append(trenner);
        buff.append(untnr);
        buff.append(trenner);
        buff.append(untdat);
        buff.append(trenner);       
        buff.append(untzus);
        buff.append(trenner);
        
        return new String(buff);
      }
    
    public String getRefnr() {
		return refnr;	
	}

	public void setRefnr(String refnr) {
		this.refnr = Utils.checkNull(refnr);
	}
	
	public String getPosnr() {
		return posnr;	
	}

	public void setPosnr(String posnr) {
		this.posnr = Utils.checkNull(posnr);
	}
	
	public String getUntart() {
		return untart;	
	}

	public void setUntart(String untart) {
		this.untart = Utils.checkNull(untart);
	}

	public String getUntzus() {
		return untzus;	
	}

	public void setUntzus(String untzus) {
		this.untzus = Utils.checkNull(untzus);
	}

	public String getUntnr() {
		return untnr;	
	}

	public void setUntnr(String bwverm) {
		this.untnr = Utils.checkNull(bwverm);
	}

	public String getUntdat() {
		return untdat;	
	}

	public void setUntdat(String zusang) {
		this.untdat = Utils.checkNull(zusang);
	}

	public boolean isEmpty() {
		   return (Utils.isStringEmpty(untart) &&
			 	  Utils.isStringEmpty(untnr)  &&
			 	  Utils.isStringEmpty(untdat) &&			 	
			 	  Utils.isStringEmpty(untzus));
	}	
}

package com.kewill.kcx.component.mapping.formats.fss.edec.Import.subsets;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: EDEC Import 70<br>
 * Created		: 05.11.2012<br>
 * Description  : FSS Definition of Ergänzungsdaten Meldestellen. 
 * 
 * @author iwaniuk
 * @version 7.0.00
 */

public class TsCPM extends Teilsatz {  
	private String refnr  = "";      //Referencenumber
	private String posnr  = "";		 //Itemnumber
	private String mldstl = "";		 //Meldungsstellencode
	
	public TsCPM() {
        tsTyp = "CPM";
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
        	posnr = fields[2];
        if (size < 4) { return; }
        	mldstl = fields[3];        
    }

    public String teilsatzBilden() {
        StringBuffer buff = new StringBuffer();

        buff.append(tsTyp);
        buff.append(trenner);
        buff.append(refnr);
        buff.append(trenner);
        buff.append(posnr);
        buff.append(trenner);
        buff.append(mldstl);
        buff.append(trenner);
       
        return new String(buff);
      }

    public void setRefnr(String refnr) {
		this.refnr = Utils.checkNull(refnr);
	}
	public String getRefnr() {
		return refnr;	
	}

	public String getPosnr() {
		return posnr;	
	}
	public void setPosnr(String posnr) {
		this.posnr = Utils.checkNull(posnr);
	}

	public String getMldstl() {
		return mldstl;	
	}
	public void setMldstl(String mldstl) {
		this.mldstl = Utils.checkNull(mldstl);
	}
	
	public boolean isEmpty() {
		return Utils.isStringEmpty(mldstl);
	}
	

}	

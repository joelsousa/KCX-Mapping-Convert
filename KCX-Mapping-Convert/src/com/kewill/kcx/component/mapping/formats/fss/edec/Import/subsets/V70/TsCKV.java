package com.kewill.kcx.component.mapping.formats.fss.edec.Import.subsets.V70;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;
import java.util.List;

/**
 * Module		: EDEC Import 70<br>
 * Created		: 29.10.2012<br>
 * Description  : FSS Definition of Subset CKV - Vorpapiere. 
 * 
 * @author iwaniuk
 * @version 7.0.00
 */
public class TsCKV extends Teilsatz {
	private String refnr  = "";      //Bezugsnummer
	private String voart  = "";		 
	private String vornr  = "";		 
	private String vorzus = "";		
	
	public TsCKV() {
        tsTyp = "CKV";
    }

    public void setFields(String[] fields) {
    
		int size = fields.length;
		
		if (size < 1) { return;	}		
        tsTyp = fields[0];
        if (size < 2) { return;	}	       
        refnr = fields[1];
        if (size < 3) { return;	}	        
        voart = fields[2];
        if (size < 4) { return;	}	        
        vornr = fields[3];
        if (size < 5) { return;	}	       
        vorzus = fields[4];        
    }

    public String teilsatzBilden() {
        StringBuffer buff = new StringBuffer();

        buff.append(tsTyp);
        buff.append(trenner);
        buff.append(refnr);
        buff.append(trenner);
        buff.append(voart);
        buff.append(trenner);
        buff.append(vornr);
        buff.append(trenner);
        buff.append(vorzus);
        buff.append(trenner);
       
        return new String(buff);
      }

    public void setRefnr(String refnr) {
		this.refnr = Utils.checkNull(refnr);
	}
	public String getRefnr() {
		return refnr;	
	}

	public String getVoart() {
		return voart;
	}
	public void setVoart(String voart) {
		this.voart = Utils.checkNull(voart);
	}

	public String getVornr() {
		return vornr;	
	}
	public void setVornr(String vornr) {
		this.vornr = Utils.checkNull(vornr);
	}

	public String getVorzus() {
		return vorzus;	
	}
	public void setVorzus(String vorzus) {
		this.vorzus = Utils.checkNull(vorzus);
	}

	public boolean isEmpty() {
		return (Utils.isStringEmpty(voart) &&
			Utils.isStringEmpty(vornr) &&
			Utils.isStringEmpty(vorzus));								
	}	
}	

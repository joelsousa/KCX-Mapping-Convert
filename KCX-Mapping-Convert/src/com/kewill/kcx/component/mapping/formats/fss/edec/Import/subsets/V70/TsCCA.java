package com.kewill.kcx.component.mapping.formats.fss.edec.Import.subsets.V70;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: EDEC Import 70<br>
 * Created		: 06.11.2012<br>
 * Description  : FSS Definition of Subset CCA: Mitteilung ueber Annullation. 
 * 
 * @author iwaniuk
 * @version 7.0.00
 */

public class TsCCA extends Teilsatz {
	
	private String refnr   = "";     	
	private String lang    = "";	
	private String abspdnr = "";	
	private String aufnr   = "";		 
	private String samsdnr = "";		
	
	public TsCCA() {
        tsTyp = "CCA";
    }

    public void setFields(String[] fields) {
    
		int size = fields.length;
		
		if (size < 1) { return;	}		
        tsTyp = fields[0];
        if (size < 2) { return;	}	       
        refnr = fields[1];
        if (size < 3) { return;	}	        
        lang = fields[2];
        if (size < 4) { return;	}	        
        abspdnr = fields[3];
        if (size < 5) { return;	}	       
        aufnr = fields[4];  
        if (size < 5) { return;	}	       
        samsdnr = fields[4];   
    }

    public String teilsatzBilden() {
        StringBuffer buff = new StringBuffer();

        buff.append(tsTyp);
        buff.append(trenner);
        buff.append(refnr);
        buff.append(trenner);
        buff.append(lang);
        buff.append(trenner);
        buff.append(abspdnr);
        buff.append(trenner);
        buff.append(aufnr);
        buff.append(trenner);
        buff.append(samsdnr);
        buff.append(trenner);
       
        return new String(buff);
      }

    public void setRefnr(String refnr) {
		this.refnr = Utils.checkNull(refnr);
	}
	public String getRefnr() {
		return refnr;	
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public String getAbspdnr() {
		return abspdnr;
	}

	public void setAbspdnr(String abspdnr) {
		this.abspdnr = abspdnr;
	}

	public String getAufnr() {
		return aufnr;	
	}
	public void setAufnr(String aufnr) {
		this.aufnr = Utils.checkNull(aufnr);
	}

	public String getSamsdnr() {
		return samsdnr;	
	}
	public void setSamsdnr(String vorzus) {
		this.samsdnr = Utils.checkNull(vorzus);
	}

	public boolean isEmpty() {
		return (Utils.isStringEmpty(lang) &&
				Utils.isStringEmpty(abspdnr) &&
			Utils.isStringEmpty(aufnr) &&
			Utils.isStringEmpty(samsdnr));								
	}	
}	

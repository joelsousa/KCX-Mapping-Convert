package com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V70;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		:	Export/aes
 * Created		:	25.07.2012
 * Description	:	Nachtragsdaen.  
 *
 * @author 	iwaniuk
 * @version 2.1.00 (Zabis V70)
 */

public class TsAND extends Teilsatz {
    
	private String beznr  = "";    
    private String flhtex = "";      
    private String texdst = "";     
    private String ausdat = "";     
    private String mzpdat = "";     
    private String intra  = "";     
   
    public TsAND() {
        tsTyp = "AND";
    }
    
    public String teilsatzBilden() {
        StringBuffer buff = new StringBuffer();
          
        buff.append(tsTyp);
        buff.append(trenner);        
        buff.append(beznr);
        buff.append(trenner);	
        buff.append(flhtex);
        buff.append(trenner);	
        buff.append(texdst);
        buff.append(trenner);	
        buff.append(ausdat);
        buff.append(trenner);	
        buff.append(mzpdat);
        buff.append(trenner);	
        buff.append(intra);
        buff.append(trenner);       
       
    	return buff.toString();
    }

	
	public void setFields(String[] fields) {
		int size = fields.length;
		
		if (size < 1)  { return; }	
        tsTyp   = fields[0];
        if (size < 2)  { return; }
        beznr   = fields[1];
        if (size < 3)  { return; }
        flhtex  = fields[2];
        if (size < 4)  { return; }
        texdst  = fields[3];
        if (size < 5)  { return; }
        ausdat  = fields[4];
        if (size < 6)  { return; }
        mzpdat  = fields[5];
        if (size < 7)  { return; }
        intra	= fields[6];               
	}

	public String getBeznr() {
		return beznr;
	}
	public void setBeznr(String beznr) {
		this.beznr = Utils.checkNull(beznr);
	}

	public String getFlhtex() {
		return flhtex;
	}
	public void setFlhtex(String flhtex) {
		this.flhtex = Utils.checkNull(flhtex);
	}

	public String getTexdst() {
		return texdst;
	}
	public void setTexdst(String texdst) {
		this.texdst = Utils.checkNull(texdst);
	}
	
	public String getAusdat() {
		return ausdat;
	}
	public void setAusdat(String ausdat) {
		this.ausdat = Utils.checkNull(ausdat);
	}

	public String getMzpdat() {
		return mzpdat;
	}
	public void setMzpdat(String mzpdat) {
		this.mzpdat = Utils.checkNull(mzpdat);
	}

	public String getIntra() {
		return intra;
	}
	public void setIntra(String intra) {
		this.intra = Utils.checkNull(intra);
	}	

	public boolean isEmpty() {		
		return Utils.isStringEmpty(flhtex) && Utils.isStringEmpty(texdst) && Utils.isStringEmpty(ausdat) && 
		  Utils.isStringEmpty(mzpdat) && Utils.isStringEmpty(intra);				
	}	
}

package com.kewill.kcx.component.mapping.formats.fss.edec.Import.subsets;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: EDEC Import 70<br>
 * Created		: 29.10.2012<br>
 * Description  : FSS Definition of Ergänzungsdaten NZE-Daten. 
 * 
 * @author iwaniuk
 * @version 7.0.00
 */

public class TsCPN extends Teilsatz {
	private String refnr  = "";      //Referencenumber
	private String posnr  = "";		 //Itemnumber
	private String cdnz   = "";		 //
	
	public TsCPN() {
        tsTyp = "CPN";
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
        	cdnz = fields[3];        
    }

    public String teilsatzBilden() {
        StringBuffer buff = new StringBuffer();

        buff.append(tsTyp);
        buff.append(trenner);
        buff.append(refnr);
        buff.append(trenner);
        buff.append(posnr);
        buff.append(trenner);
        buff.append(cdnz);
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

	public String getCdnz() {
		return cdnz;	
	}
	public void setCdnz(String cdbwpf) {
		this.cdnz = Utils.checkNull(cdbwpf);
	}

	public boolean isEmpty() {
		return Utils.isStringEmpty(cdnz);
	}
	

}	

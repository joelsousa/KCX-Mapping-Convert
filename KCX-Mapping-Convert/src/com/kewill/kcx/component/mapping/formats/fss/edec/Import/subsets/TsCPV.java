package com.kewill.kcx.component.mapping.formats.fss.edec.Import.subsets;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;
import java.util.List;

/**
 * Module		: EDEC Import 70<br>
 * Created		: 26.10.2012<br>
 * Description  : FSS Definition of Ergänzungsdaten Verpackung. 
 * 
 * @author iwaniuk
 * @version 7.0.00
 */

public class TsCPV extends Teilsatz {
	private String refnr = "";       //Bezugsnummer
	private String posnr = "";		 //
	private String vpart = "";		 //
	private String vpanz = "";		 //
	private String vpzch = "";		 //

	public TsCPV() {
        tsTyp = "CPV";
    }

    public void setFields(String[] fields) {
    
		int size = fields.length;
		//String ausgabe = "FSS: " + fields[0] + " size = " + size;
		//Utils.log( ausgabe);
					
		if (size < 1) { return;	 }		
        tsTyp = fields[0];
        if (size < 2) { return;	 }	      
        refnr = fields[1];
        if (size < 3) { return;	 }	     
        posnr = fields[2];
        if (size < 4) { return;	 }	      
        vpart = fields[3];
        if (size < 5) { return;	 }	       
        vpanz = fields[4];        
        if (size < 6) { return;	 }	        
        vpzch = fields[5];
               
    }

    public String teilsatzBilden() {
        StringBuffer buff = new StringBuffer();

        buff.append(tsTyp);
        buff.append(trenner);
        buff.append(refnr);
        buff.append(trenner);
        buff.append(posnr);
        buff.append(trenner);
        buff.append(vpart);
        buff.append(trenner);
        buff.append(vpanz);
        buff.append(trenner);
        buff.append(vpzch);
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

	public String getVpart() {
		return vpart;	
	}
	public void setVpart(String vpart) {
		this.vpart = Utils.checkNull(vpart);
	}

	public String getVpanz() {
		return vpanz;	
	}
	public void setVpanz(String vpanz) {
		this.vpanz = Utils.checkNull(vpanz);
	}

	public String getVpzch() {
		return vpzch;	
	}
	public void setVpzch(String vpzch) {
		this.vpzch = Utils.checkNull(vpzch);
	}
	
	public boolean isEmpty() {
		if (Utils.isStringEmpty(posnr) &&
			Utils.isStringEmpty(vpart) &&
			Utils.isStringEmpty(vpanz) &&
			Utils.isStringEmpty(vpzch)) {
			return true;
		} else {
			return false;
		}
	}	
}	

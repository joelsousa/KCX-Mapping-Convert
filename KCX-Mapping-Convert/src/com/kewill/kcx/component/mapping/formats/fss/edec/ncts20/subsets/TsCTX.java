package com.kewill.kcx.component.mapping.formats.fss.edec.ncts20.subsets;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module        :   NCTS 70 
 * Created       :   07.06.2013
 * Description   :   Ts-Export-Deklarationen for MsgCTK Transitabmeldung.
 *  
 * @author         iwaniuk
 * @version        7.0.00
 */

public class TsCTX extends Teilsatz {
	private String beznr  = "";      //Bezugsnummer des Versandvorgangs	
	private String posnr  = "";		 //
	private String dknrzo = "";		 //Deklarationsnummer Zoll eines Export-Vorgangs	
	
	public TsCTX() {
        tsTyp = "CTX";
    }

    public void setFields(String[] fields) {
		int size = fields.length;
		//String ausgabe = "FSS: " + fields[0] + " size = " + size;
		//Utils.log( ausgabe);
			
		
		if (size < 1) { return; }		
        	tsTyp = fields[0];
        if (size < 2) { return; }	
        	beznr = fields[1];
        if (size < 3) { return; }	
        	posnr = fields[2];        	
        if (size < 4) { return; }	
        	dknrzo = fields[3];
        
    }

    public String teilsatzBilden() {
        StringBuffer buff = new StringBuffer();

        buff.append(tsTyp);
        buff.append(trenner);
        buff.append(beznr);
        buff.append(trenner);        
        buff.append(posnr);
        buff.append(trenner);
        buff.append(dknrzo);
        buff.append(trenner);
       
        return new String(buff);
      }

    public void setBeznr(String beznr) {
		this.beznr = Utils.checkNull(beznr);
	}
	public String getBeznr() {
		return beznr;	
	}

	public String getPosnr() {
		return posnr;	
	}
	public void setPosnr(String posnr) {
		this.posnr = Utils.checkNull(posnr);
	}

	public String getDknrzo() {
		return dknrzo;	
	}
	public void setDknrzo(String dknrzo) {
		this.dknrzo = Utils.checkNull(dknrzo);
	}
	
	public boolean isEmpty() {
		return (Utils.isStringEmpty(posnr) &&
				Utils.isStringEmpty(dknrzo));				
	}

}	

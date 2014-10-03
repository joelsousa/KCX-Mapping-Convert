package com.kewill.kcx.component.mapping.formats.fss.edec.ncts20.subsets;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module        :   NCTS 70 
 * Created       :   07.06.2013
 * Description   :   Ts-Transportweg  for MsgCTK Transitabmeldung.
 *  
 * @author         iwaniuk
 * @version        7.0.00
 */

public class TsCTW extends Teilsatz {
	private String beznr  = "";      //Bezugsnummer des Versandvorgangs	
	private String land  = "";		 //	
	
	public TsCTW() {
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
        land = fields[2];        	
       
    }

    public String teilsatzBilden() {
        StringBuffer buff = new StringBuffer();

        buff.append(tsTyp);
        buff.append(trenner);
        buff.append(beznr);
        buff.append(trenner);        
        buff.append(land);
        buff.append(trenner);
        
        return new String(buff);
      }

    public void setBeznr(String beznr) {
		this.beznr = Utils.checkNull(beznr);
	}
	public String getBeznr() {
		return beznr;	
	}

	public String getLand() {
		return land;	
	}
	public void setLand(String posnr) {
		this.land = Utils.checkNull(posnr);
	}

	public boolean isEmpty() {
		return (Utils.isStringEmpty(land));				
	}

}	

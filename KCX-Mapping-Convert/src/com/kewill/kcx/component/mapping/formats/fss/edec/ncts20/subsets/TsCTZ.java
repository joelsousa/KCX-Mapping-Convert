package com.kewill.kcx.component.mapping.formats.fss.edec.ncts20.subsets;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module        :   NCTS 70 
 * Created       :   07.06.2013
 * Description   :   Ts-Durchgangszollstellen  for MsgCTK Transitabmeldung.
 *  
 * @author         iwaniuk
 * @version        7.0.00
 */

public class TsCTZ extends Teilsatz {
	private String beznr  = "";      //Bezugsnummer
	private String dgzst = "";		 //NCTS-Zollstelle (8-stellig)
	private String ankzst = "";		 //Ankunftsdatum und Uhrzeit, Format JJJJMMTTHHMM
	
	public TsCTZ() {
        tsTyp = "CTZ";
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
        	dgzst = fields[2];        	
        if (size < 4) { return; }	
        	ankzst = fields[3];       
    }

    public String teilsatzBilden() {
        StringBuffer buff = new StringBuffer();

        buff.append(tsTyp);
        buff.append(trenner);
        buff.append(beznr);
        buff.append(trenner);
        buff.append(dgzst);
        buff.append(trenner);
        buff.append(ankzst);
        buff.append(trenner);       

        return new String(buff);
      }

    public void setBeznr(String beznr) {
		this.beznr = Utils.checkNull(beznr);
	}
	public String getBeznr() {
		return beznr;	
	}
	
    public void setDgzst(String beznr) {
		this.dgzst = Utils.checkNull(beznr);
	}
	public String getDgzst() {
		return dgzst;	
	} 

	public String getAnkzst() {
		return ankzst;	
	}
	public void setAnkzst(String ankzst) {
		this.ankzst = Utils.checkNull(ankzst);
	}
	
	
	public boolean isEmpty() {
		return (Utils.isStringEmpty(dgzst) &&
				Utils.isStringEmpty(ankzst));
	}

}	

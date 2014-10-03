package com.kewill.kcx.component.mapping.formats.fss.Import.subsets.V64;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;


/**
 * Modul			:	TsUNT  Unterlagen im Kopfteil.
 * Erstellt			:	09.09.2011
 * Beschreibung		:	 
 *
 * @author 			Alfred Krzoska
 * 
 */

public class TsUNT extends Teilsatz {

	private String beznr	 = "";      // Bezugsnummer 
	private String untart    = "";      // Art der Unterlage 
	private String untnr	 = "";      // Nr. der Unterlage 
	private String untdat    = "";      // Datum der Unterlage 
	    
    public TsUNT() {
	        tsTyp = "UNT";
    }

	public void setFields(String[] fields) {
		int size = fields.length;
		String ausgabe = "FSS: " + fields[0] + " size = " + size;
		//Utils.log( ausgabe);
			
	    if (size < 1) { return; }                
	    tsTyp = fields[0];			     
	    if (size < 2) { return; }		     
	    beznr        = fields[1];		     
	    if (size < 3) { return; }		     
	    untart       = fields[2];		     
	    if (size < 4) { return; }		     
	    untnr        = fields[3];		     
	    if (size < 5) { return; }		     
	    untdat       = fields[4];		     
	}

    
	public String teilsatzBilden() {
        StringBuffer buff = new StringBuffer();

        buff.append(tsTyp);
        buff.append(trenner);
        buff.append(beznr);   
        buff.append(trenner);
        buff.append(untart);	
        buff.append(trenner);
        buff.append(untnr);	
        buff.append(trenner);
        buff.append(untdat);	
        buff.append(trenner);
						        	 
        return new String(buff);		    
    }						           
						    
	public String getBeznr() {
		return beznr;
	}

	public void setBeznr(String beznr) {
		this.beznr = Utils.checkNull(beznr);
	}

	public String getUntart() {
		return untart;
	}

	public void setUntart(String untart) {
		this.untart = Utils.checkNull(untart);
	}

	public String getUntnr() {
		return untnr;
	}

	public void setUntnr(String untnr) {
		this.untnr = Utils.checkNull(untnr);
	}

	public String getUntdat() {
		return untdat;
	}

	public void setUntdat(String untdat) {
		this.untdat = Utils.checkNull(untdat);
	}

	public boolean isEmpty() {		           
		
		return Utils.isStringEmpty(untart) &&                         	
			   Utils.isStringEmpty(untnr) &&		         
			   Utils.isStringEmpty(untdat);			      	
	}								      
}

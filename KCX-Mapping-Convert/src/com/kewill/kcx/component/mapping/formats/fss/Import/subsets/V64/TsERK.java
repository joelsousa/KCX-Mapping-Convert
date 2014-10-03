package com.kewill.kcx.component.mapping.formats.fss.Import.subsets.V64;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;


/**
 * Modul			:	TsERK.
 * Erstellt			:	09.09.2011
 * Beschreibung		:	Ergänzungssatz Aufschubkonten 
 *
 * @author 			Alfred Krzoska
 * 
 */

public class TsERK extends Teilsatz {

	private String kbeznr	 = "";      // Bezugsnummer 
	private String ktoaa     = "";      // Art der Unterlage 
	private String ktozb	 = "";      // Nr. der Unterlage 
	private String ktokn     = "";      // Datum der Unterlage 
	    
    public TsERK() {
	        tsTyp = "ERK";
    }

	public void setFields(String[] fields) {
		int size = fields.length;
		String ausgabe = "FSS: " + fields[0] + " size = " + size;
		//Utils.log( ausgabe);
			
	    if (size < 1) { return; }                
	    tsTyp = fields[0];			     
	    
	    if (size < 2) { return; }		     
	    kbeznr        = fields[1];		     
	    
	    if (size < 3) { return; }		     
	    ktoaa        = fields[2];
	    
	    if (size < 4) { return; }		     
	    ktozb        = fields[3];
	    
	    if (size < 5) { return; }		     
	    ktokn       = fields[4];		     
	}

    
	public String getKbeznr() {
		return kbeznr;
	}

	public void setKbeznr(String kbeznr) {
		this.kbeznr = Utils.checkNull(kbeznr);
	}

	public String getKtoaa() {
		return ktoaa;
	}

	public void setKtoaa(String ktoaa) {
		this.ktoaa = Utils.checkNull(ktoaa);
	}

	public String getKtozb() {
		return ktozb;
	}

	public void setKtozb(String ktozb) {
		this.ktozb = Utils.checkNull(ktozb);
	}

	public String getKtokn() {
		return ktokn;
	}

	public void setKtokn(String ktokn) {
		this.ktokn = Utils.checkNull(ktokn);
	}

	public String teilsatzBilden() {
        StringBuffer buff = new StringBuffer();

        buff.append(tsTyp);
        buff.append(trenner);
        buff.append(kbeznr);   
        buff.append(trenner);
        buff.append(ktoaa);	
        buff.append(trenner);
        buff.append(ktozb);	
        buff.append(trenner);
        buff.append(ktokn);	
        buff.append(trenner);
						        	 
        return new String(buff);		    
    }						           
						    
    						            
						    
	public boolean isEmpty() {		           
		
		return Utils.isStringEmpty(ktoaa) &&                         	
			   Utils.isStringEmpty(ktozb) &&		         
			   Utils.isStringEmpty(ktokn);			      	
	}								      
}

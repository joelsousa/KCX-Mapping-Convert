package com.kewill.kcx.component.mapping.formats.fss.Import.subsets.V70;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;


/** 
 * Module       :   Import 70<br>
 * Subset       :   TsADR.java<br>
 * Created      :   12.11.2012<br>
 * Description  :   Ergänzungssatz Aufschubkonten .<br>
 *
 * @author          iwaniuk
 * @version         7.0.00  
 */

public class TsERK extends Teilsatz {

	private String kbeznr	 = "";      // Bezugsnummer 
	private String ktoaa     = "";      // Aufschubart
	//private String ktozb	 = "";      // Zollnummer 
	private String ktokd     = "";      // Kundennummer
	private String ktoeori   = "";      //new V70
	private String ktonl     = "";      //new V70
	    
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
	    ktokd        = fields[3];	    
	    if (size < 5) { return; }		     
	    ktoeori     = fields[4];	
	    if (size < 5) { return; }		     
	    ktonl       = fields[4];	
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

	public String getKtoeori() {
		return ktoeori;
	}

	public void setKtoeori(String value) {
		this.ktoeori = Utils.checkNull(value);
	}
	
	public String getKtonl() {
		return ktonl;
	}

	public void setKtonl(String value) {
		this.ktonl = Utils.checkNull(value);
	}

	public String getKtokd() {
		return ktokd;
	}

	public void setKtokd(String ktokd) {
		this.ktokd = Utils.checkNull(ktokd);
	}

	public String teilsatzBilden() {
        StringBuffer buff = new StringBuffer();

        buff.append(tsTyp);
        buff.append(trenner);
        buff.append(kbeznr);   
        buff.append(trenner);
        buff.append(ktoaa);	
        buff.append(trenner);
        buff.append(ktokd);	
        buff.append(trenner);
        buff.append(ktoeori);	
        buff.append(trenner);
        buff.append(ktonl);	
        buff.append(trenner);
						        	 
        return new String(buff);		    
    }						           
						    
    						            
						    
	public boolean isEmpty() {		           
		
		return Utils.isStringEmpty(ktoaa) &&                         	
			   Utils.isStringEmpty(ktokd) &&		         
			   Utils.isStringEmpty(ktoeori);			      	
	}								      
}

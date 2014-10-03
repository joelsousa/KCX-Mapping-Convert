package com.kewill.kcx.component.mapping.formats.fss.base.subsets;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;


/**
 * Modul			:	TsUNT.java
 * Erstellt			:	09.09.2011
 * Beschreibung		:	Beendigungsanteil AV/UV 
 *
 * @author 			Alfred Krzoska
 * 
 */

public class TsBAV extends Teilsatz {

	private String posnr     = "";      // Positionsnummer
	private String vregnr	 = "";      // Registriernummer des zu erledigenden Vorgangs 
										// nur für Art der Identifikation= REG erlaubt
	private String vposnr    = "";      // Positionsnummer im zu erledigenden Vorgang nur 
										// für Art der Identifikation= REG erlaubt
	private String atlas	 = "";      // Kennzeichen Zugang in ATLAS
	private String txzus     = "";      // Warenbezogene Angaben
	private String azvagl	 = "";      // Abgleich in ATLAS erfolgt

	    
    public TsBAV() {
	        tsTyp = "BAV";
    }

	public void setFields(String[] fields) {
		int size = fields.length;
		String ausgabe = "FSS: " + fields[0] + " size = " + size;
		//Utils.log( ausgabe);
			
	    if (size < 1) { return; }                
	    tsTyp = fields[0];			 
	    
	    if (size < 2) { return; }		     
	    posnr        = fields[1];
	    
	    if (size < 3) { return; }		     
	    vregnr       = fields[2];
	    
	    if (size < 4) { return; }		     
	    vposnr       = fields[3];
	    
	    if (size < 5) { return; }		     
	    atlas        = fields[4];
	    
	    if (size < 6) { return; }		     
	    txzus        = fields[5];
	    
	    if (size < 7) { return; }		      
	    azvagl       = fields[6];		     
	}

    public String getPosnr() {
		return posnr;
	}

	public void setPosnr(String posnr) {
		this.posnr = Utils.checkNull(posnr);
	}

	public String getVregnr() {
		return vregnr;
	}

	public void setVregnr(String vregnr) {
		this.vregnr = Utils.checkNull(vregnr);
	}

	public String getVposnr() {
		return vposnr;
	}

	public void setVposnr(String vposnr) {
		this.vposnr = Utils.checkNull(vposnr);
	}

	public String getAtlas() {
		return atlas;
	}

	public void setAtlas(String atlas) {
		this.atlas = Utils.checkNull(atlas);
	}

	public String getTxzus() {
		return txzus;
	}

	public void setTxzus(String txzus) {
		this.txzus = Utils.checkNull(txzus);
	}

	public String getAzvagl() {
		return azvagl;
	}

	public void setAzvagl(String azvagl) {
		this.azvagl = Utils.checkNull(azvagl);
	}

	public String teilsatzBilden() {
        StringBuffer buff = new StringBuffer();

        buff.append(tsTyp);
        buff.append(trenner);
        buff.append(posnr);   
        buff.append(trenner);
        buff.append(vregnr);	
        buff.append(trenner);
        buff.append(vposnr);	
        buff.append(trenner);
        buff.append(atlas);	
        buff.append(trenner);
        buff.append(txzus);	
        buff.append(trenner);
        buff.append(azvagl);	
        buff.append(trenner);
						        	 
        return new String(buff);		    
    }						           
						    
    						            
						    
	public boolean isEmpty() {		           
		
		return Utils.isStringEmpty(atlas) &&                         	
			   Utils.isStringEmpty(txzus) &&		         
			   Utils.isStringEmpty(azvagl);			      	
	}								      
}

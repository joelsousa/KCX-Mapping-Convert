package com.kewill.kcx.component.mapping.formats.fss.Import.subsets.V64;


import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Modul		:	TsERC
 * Erstellt		:	07.09.2011
 * Beschreibung	:	Ergänzungssatz Container
 *
 * @author 			Alfred Krzoska
 * 
 
 */

public class TsERC extends Teilsatz {

	private String cbeznr    = "";      // Bezugsnummer
	private String connr	 = "";      // Containernummer
	    
    public TsERC() {
        tsTyp = "ERC";
    }

	public void setFields(String[] fields) {
	
		int size = fields.length;
		String ausgabe = "FSS: " + fields[0] + " size = " + size;
		//Utils.log( ausgabe);
			
	    if (size < 1) { return; }
	    tsTyp = fields[0];
	    
	    if (size < 2) { return; }	
	    cbeznr       = fields[1];
	    
	    if (size < 3) { return; }
	    connr       = fields[2];
	}

    public String teilsatzBilden() {
        StringBuffer buff = new StringBuffer();

        buff.append(tsTyp);
        buff.append(trenner);
        buff.append(cbeznr);
        buff.append(trenner);
        buff.append(connr);
        buff.append(trenner);

        return new String(buff);
    }

    
	
    public String getCbeznr() {
		return cbeznr;
	}

	public void setCbeznr(String cbeznr) {
		this.cbeznr = Utils.checkNull(cbeznr);
	}

	public String getConnr() {
		return connr;
	}

	public void setConnr(String connr) {
		this.connr = Utils.checkNull(connr);
	}

	public boolean isEmpty() {
    	return Utils.isStringEmpty(connr);
    }
}

package com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Modul        :   AME
 * Teilsatz     :   TsAME.java
 * Erstellt     :   30.03.2009
 * Beschreibung :   Referenzsatz AME for ManuelTermination (AEP)
 *
 */

public class TsAME extends Teilsatz {
    private String beznr     = "";         // Bezugsnummer
    private String erldat    = "";          
    private String text      = "";         
    private String kztyd     = "";       
    private String sb        = "";         
 
    public TsAME() {
        tsTyp = "AME";
    }

    public void setFields(String[] fields)
	{
		int size = fields.length;
		String ausgabe = "FSS: "+fields[0]+" size = "+ size;
		//Utils.log( ausgabe);
			
		
			if (size < 1 ) return;		
        		tsTyp = fields[0];
        	if (size < 2 ) return;
	        	beznr = fields[1];
	        if (size < 3 ) return;
	        	erldat = fields[2];
	        if (size < 4 ) return;
	        	text = fields[3];
	        if (size < 5 ) return;
	        	kztyd = fields[4];
	        if (size < 6 ) return;
	           sb = fields[5];	         
	      }


    public String teilsatzBilden() {
        StringBuffer buff = new StringBuffer();

        buff.append(tsTyp);
        buff.append(trenner);
        buff.append(beznr);
        buff.append(trenner);
        buff.append(erldat);
        buff.append(trenner);
        buff.append(text);
        buff.append(trenner);
        buff.append(kztyd);
        buff.append(trenner);
        buff.append(sb);
        buff.append(trenner);        

        return new String(buff);
    }

	public String getTsTyp() {
		return tsTyp;
	
	}

	public void setTsTyp(String tsTyp) {
		this.tsTyp = Utils.checkNull(tsTyp);
	}

	public String getBeznr() {
		return beznr;
	
	}

	public void setBeznr(String beznr) {
		this.beznr = Utils.checkNull(beznr);
	}

	public String getErldat() {
		return erldat;
	
	}

	public void setErldat(String argument) {
		this.erldat = Utils.checkNull(argument);
	}

	public String getText() {
		return text;
	
	}

	public void setText(String argument) {
		this.text = Utils.checkNull(argument);
	}

	public String getKztyd() {
		return kztyd;
	
	}

	public void setKztyd(String argument) {
		this.kztyd = Utils.checkNull(argument);
	}

	public String getSb() {
		return sb;
	
	}

	public void setSb(String argument) {
		this.sb = Utils.checkNull(argument);
	}
	
	public boolean isEmpty() {
		if ( Utils.isStringEmpty(erldat) && Utils.isStringEmpty(text) 
		   && Utils.isStringEmpty(kztyd) && Utils.isStringEmpty(sb)) 		  		  
			return true;
		else
			return false;
	} 

}
 
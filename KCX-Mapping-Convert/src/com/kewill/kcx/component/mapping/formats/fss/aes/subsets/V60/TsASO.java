/*
 
 * Funktion    : TsASO.java
 * Titel       :
 * Date	       : 15.10.2008
 * Author      : Kewill CSF / Christine Kron
 * Description: 
 *
 * Changes 
 * -----------
 * Author      : EI
 * Date        : 13.03.2009
 * Label       :
 * Description : checked for V60 - no changes
 */
 
package com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

public class TsASO extends Teilsatz {

    private String beznr  = "";          	// Bezugsnummer
    private String artsto = "";         	// Art der Stornierung
    private String asodat = "";         	// Zeitpunkt der Stornierung
    private String text   = "";           	// Stornierungsgrund
    private String sb     = "";          	// Sachbearbeiterkennung

    public TsASO() {
        tsTyp = "ASO";
    }   
    
    public void setFields(String[] fields)
	{
    	int size = fields.length;
    	
    	if (size <= 0 ) return;		
        tsTyp = fields[0];
        if (size < 1 ) return;		
	    beznr	= fields[1];
	    if (size < 2 ) return;	
	    artsto	= fields[2];
	    if (size < 3 ) return;	
	    asodat	= fields[3];
	    if (size < 4 ) return;	
	    text	= fields[4];
	    if (size < 5 ) return;	
	    sb		= fields[5];
       }

	public String teilsatzBilden() {
		
        StringBuffer buff = new StringBuffer();
        
        buff = buff.append(tsTyp);
        buff = buff.append(trenner);
        buff = buff.append(beznr);
        buff = buff.append(trenner);
        buff = buff.append(artsto);
        buff = buff.append(trenner);        
        buff = buff.append(asodat);
        buff = buff.append(trenner);
        buff = buff.append(text);
        buff = buff.append(trenner);
        buff = buff.append(sb);
        buff = buff.append(trenner);         
        
        return new String(buff);
	}

	public String getBeznr() {
		return beznr;
	}

	public void setBeznr(String beznr) {
		this.beznr = Utils.checkNull(beznr);
	}

	public String getArtsto() {
		return artsto;
	}

	public void setArtsto(String artsto) {
		this.artsto = Utils.checkNull(artsto);
	}

	public String getAsodat() {
		return asodat;
	}

	public void setAsodat(String asodat) {
		this.asodat = Utils.checkNull(asodat);
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = Utils.checkNull(text);
	}

	public String getSb() {
		return sb;
	}

	public void setSb(String sb) {
		this.sb = Utils.checkNull(sb);
	}
	
	public boolean isEmpty() {
		if ( Utils.isStringEmpty(artsto)  && Utils.isStringEmpty(asodat) && Utils.isStringEmpty(text)  
		 	&& Utils.isStringEmpty(sb))  
	   		return true;
		else
			return false;
	}
}
 
 
 



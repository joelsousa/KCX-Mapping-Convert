package com.kewill.kcx.component.mapping.formats.fss.edec.aus.subsets;

/**
 * Modul        :   EDEC Export NCTS (Ausfuhr)
 * Nachricht    :   CAE ( cancellation response )
 * Erstellt     :   20.10.2008
 * Beschreibung :   TsCAE
 *  
 * @author          Miro Houdek
 * 
 */

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;


public class TsCAE extends Teilsatz {

	private String dklart	= " ";        
	private String dknrzo	= " ";   
	private String cdanzo	= " "; 	
	private String cdanbs	= " "; 
	private String anugrd	= " "; 
	
    public TsCAE() {
        tsTyp = "CAE";        
    }

    
	public void setFields(String[] fields) {
		int size = fields.length;
		String ausgabe = "FSS: "+fields[0]+" size = "+ size;
		//Utils.log( ausgabe);
		
		if (size < 1 ) return;		           
		tsTyp = fields[0];	
		if (size < 2 ) return;  
		dklart = fields[1];
        if (size < 3 ) return;
        dknrzo = fields[2];
        if (size < 4 ) return;
        cdanzo = fields[3];
        if (size < 5 ) return;
        cdanbs = fields[4];
        if (size < 6 ) return;
        anugrd = fields[5];
	}

	public String teilsatzBilden() {
        StringBuffer buff = new StringBuffer();
        
        buff = buff.append(tsTyp);
        buff = buff.append(trenner);
        buff = buff.append(dklart);
        buff = buff.append(trenner);
        buff = buff.append(dknrzo);
        buff = buff.append(trenner);
        buff = buff.append(cdanzo);
        buff = buff.append(trenner);
        buff = buff.append(cdanbs);
        buff = buff.append(trenner);  
        buff = buff.append(anugrd);
        buff = buff.append(trenner);         
        
        return new String(buff);
	}

	public String getDklart() {
		return dklart;
	}

	public void setDklart(String dklart) {
		this.dklart = Utils.checkNull(dklart);
	}

	public String getDknrzo() {
		return dknrzo;
	}

	public void setDknrzo(String dknrzo) {
		this.dknrzo = Utils.checkNull(dknrzo);
	}

	public String getAnugrd() {
		return anugrd;
	}

	public void setAnugrd(String anugrd) {
		this.anugrd = Utils.checkNull(anugrd);
	}

	public String getCdanzo() {
		return cdanzo;
	}

	public void setCdanzo(String cdanzo) {
		this.cdanzo = Utils.checkNull(cdanzo);
	}

	public String getCdanbs() {
		return cdanbs;
	}

	public void setCdanbs(String cdanbs) {
		this.cdanbs = Utils.checkNull(cdanbs);
	}
	
	public boolean isEmpty() {
		if ( Utils.isStringEmpty(dklart)  && Utils.isStringEmpty(dknrzo) && Utils.isStringEmpty(anugrd)
		  && Utils.isStringEmpty(cdanzo) && Utils.isStringEmpty(cdanbs)) 
			return true;
		else
			return false;
	}

}

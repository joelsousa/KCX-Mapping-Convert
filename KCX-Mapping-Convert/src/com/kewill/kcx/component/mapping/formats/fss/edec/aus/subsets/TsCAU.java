package com.kewill.kcx.component.mapping.formats.fss.edec.aus.subsets;

/**
 * Modul        :   EDEC Export NCTS (Ausfuhr)
 * Nachricht    :   CAU (Cancellation)
 * Erstellt     :   17.09.2008
 * Beschreibung :   TsCAU
 *  
 * @author          Sven Heise
 * 
 */

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

public class TsCAU extends Teilsatz {

	private String dklart	= " ";        // 	STR(5)	Verfahren CH801/2/3/5 / kind of declaration
	private String dknrzo	= " ";        // 	STR(17)	Deklarationsnummer Zoll / declaration number customs
	private String anugrd	= " ";        // 	STR(350) Annullationsgrund / reason of cancellation

    public TsCAU() {
        tsTyp = "CAU";
    }

	public void setFields(String[] fields) {
		int size = fields.length;
		String ausgabe = "FSS: "+fields[0]+" size = "+ size;
		//Utils.log( ausgabe);
		
		if (size < 1 ) return;		           
		dklart = fields[0];	
		if (size < 2 ) return;  
		dknrzo = fields[1];
        if (size < 3 ) return;
        anugrd = fields[2];
        if (size < 4 ) return;
	}

	public String teilsatzBilden() {
        StringBuffer buff = new StringBuffer();
        
        buff = buff.append(tsTyp);
        buff = buff.append(trenner);
        buff = buff.append(dklart);
        buff = buff.append(trenner);
        buff = buff.append(dknrzo);
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
	
	public boolean isEmpty() {
		if ( Utils.isStringEmpty(dklart)  && Utils.isStringEmpty(dknrzo) && Utils.isStringEmpty(dknrzo)
		  && Utils.isStringEmpty(anugrd)) 
			return true;
		else
			return false;
	}

}

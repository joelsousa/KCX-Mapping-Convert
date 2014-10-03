/*
* Funktion    : TsAAK.java
* Titel       :
* Erstellt    : 03.09.2008
* Author      : Elisabeth Iwaniuk
* Beschreibung:
* Anmerkungen :
* Parameter   :
* Rückgabe    : keine
* Aufruf      :
*
*/

package com.kewill.kcx.component.mapping.formats.fss.edec.aus.subsets;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

public class TsCAA extends Teilsatz {

    private String beznr = "";       //Bezugsnummer
    private String dklart = "";      //DeclarationKind 
    private String dksp = "";        // DeclarationNumberForwarder
    private String dkzo = "";        // DeclarationNumberCustoms
    private String antdat = "";      //AcceptanceTime
    private String cdrev = "";       //RevisionCode
    private String cdfrei = "";      //CodeOfRelease   
    
    public TsCAA() {
        tsTyp = "CAA";
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
        	dklart = fields[2];
        if (size < 4 ) return;	
        	dksp = fields[3];
        if (size < 5 ) return;	
        	dkzo = fields[4];        
        if (size < 6 ) return;	
        	antdat = fields[5];
        if (size < 7 ) return;	
        	cdrev = fields[6];
        if (size < 8 ) return;	
        	cdfrei = fields[7];        	
    }

    public String teilsatzBilden() {
        StringBuffer buff = new StringBuffer();

        buff.append(tsTyp);
        buff.append(trenner);
        buff.append(beznr);
        buff.append(trenner);
        buff.append(dklart);
        buff.append(trenner);
        buff.append(dksp);
        buff.append(trenner);
        buff.append(dkzo);
        buff.append(trenner);
        buff.append(antdat);
        buff.append(trenner);
        buff.append(cdrev);
        buff.append(trenner);
        buff.append(cdfrei);
        buff.append(trenner);

        return new String(buff);
      }

	public String getBeznr() {
		return beznr;
	
	}

	public void setBeznr(String beznr) {
		this.beznr = Utils.checkNull(beznr);
	}

	public String getDklart() {
		return dklart;
	
	}

	public void setDklart(String dklart) {
		this.dklart = Utils.checkNull(dklart);
	}
	
	public String getDksp() {
		return dksp;
	
	}

	public void setDksp(String dksp) {
		this.dksp = Utils.checkNull(dksp);
	}
	
	public String getDkzo() {
		return dkzo;
	
	}

	public void setDkzo(String dklzo) {
		this.dkzo = Utils.checkNull(dklzo);
	}
	
	public String getAntdat() {
		return antdat;
	
	}

	public void setAntdat(String antdat) {
		this.antdat = Utils.checkNull(antdat);
	}
	
	public String getCdrev() {
		return cdrev;
	
	}

	public void setCdrev(String cdrev) {
		this.cdrev = Utils.checkNull(cdrev);
	}
	
	public String getCdfrei() {
		return cdfrei;
	
	}

	public void setCdfrei(String cdfrei) {
		this.cdfrei = Utils.checkNull(cdfrei);
	}	

    /*
	public void setAakSubset(MsgKids msgKids) {
		this.setBeznr(msgKids.getReferenceNumber());
		this.setGsroh(msgKids.getGrossMass());
	}
	*/
	public boolean isEmpty() {
		if ( Utils.isStringEmpty(dklart) && Utils.isStringEmpty(dksp) 
		  && Utils.isStringEmpty(dkzo) && Utils.isStringEmpty(antdat)		  
		  && Utils.isStringEmpty(cdrev) && Utils.isStringEmpty(cdfrei))
			return true;
		else
			return false;
	}
}



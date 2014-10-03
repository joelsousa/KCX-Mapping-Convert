package com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V53;

/*
* Funktion    : TsAFP.java
* Titel       :
* Erstellt    : 03.09.2008
* Author      : CSF GmbH / Alfred Krzoska
* Beschreibung: 
* Anmerkungen : 
* Parameter   : 
* Rückgabe    : keine
* Aufruf      : 
*
* Änderungen:
* -----------
* Author      :
* Datum       :
* Kennzeichen :
* Beschreibung:
* Anmerkungen :
* Parameter   :
*
*/

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;


public class TsAFP extends Teilsatz {

    private String beznr		= "";       //  Bezugsnummer
    private String txtlfd		= "";       //  laufende Nummer des Fehlers
    private String errcde		= "";       //  Fehlercode
    private String errtxt		= "";       //  Fehlertext
    private String zeiger		= "";       //  Zeiger

    public String[] fields = null;

    
    public TsAFP() {
        tsTyp = "AFP";
    }
    
    public void setFields(String[] fields) {
		int size = fields.length;
		String ausgabe = "FSS: "+fields[0]+" size = "+ size;
		//Utils.log( ausgabe);
		
		if (size < 1 ) return;		
        tsTyp = fields[0];
        if (size < 2 ) return;
        beznr     = fields[1];
        if (size < 3 ) return;
        txtlfd    = fields[2];
        if (size < 4 ) return;
        errcde    = fields[3];
        if (size < 5 ) return;
        errtxt    = fields[4];
        if (size < 6 ) return;
        zeiger    = fields[5];
    }


    public boolean isErrorValid() {

        if (errcde.trim().equals("") && errtxt.trim().equals("") && zeiger.trim().equals("")) {
            return false;
        }
        return true;
    }

	
	
	public String teilsatzBilden() {
        StringBuffer buff = new StringBuffer();
        
        buff = buff.append(tsTyp);
        buff = buff.append(trenner);
        buff = buff.append(beznr);
        buff = buff.append(trenner);
        buff = buff.append(txtlfd);
        buff = buff.append(trenner);
        buff = buff.append(errcde);
        buff = buff.append(trenner);
        buff = buff.append(errtxt);
        buff = buff.append(trenner);
        buff = buff.append(zeiger);
        buff = buff.append(trenner);

        return new String(buff);
    }

	public String getBeznr() {
		return beznr;
	
	}

	public void setBeznr(String beznr) {
		this.beznr = Utils.checkNull(beznr);
	}

	public String getTxtlfd() {
		return txtlfd;
	
	}

	public void setTxtlfd(String txtlfd) {
		this.txtlfd = Utils.checkNull(txtlfd);
	}

	public String getErrcde() {
		return errcde;
	
	}

	public void setErrcde(String errcde) {
		this.errcde = Utils.checkNull(errcde);
	}

	public String getErrtxt() {
		return errtxt;
	
	}

	public void setErrtxt(String errtxt) {
		this.errtxt = Utils.checkNull(errtxt);
	}

	public String getZeiger() {
		return zeiger;
	
	}

	public void setZeiger(String zeiger) {
		this.zeiger = Utils.checkNull(zeiger);
	}

	public String[] getFields() {
		return fields;
	
	}
	
	public boolean isEmpty() {
		if ( Utils.isStringEmpty(txtlfd)  && Utils.isStringEmpty(errcde) && Utils.isStringEmpty(errtxt)
				&& Utils.isStringEmpty(zeiger) )
			return true;
		else
			return false;
	}

}


package com.kewill.kcx.component.mapping.formats.fss.Import.subsets.V64;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Modul		:	TsEMP
 * Erstellt		:	07.09.2011
 * Beschreibung	:	Zusätzlicher Empfänger EMP.  
 *
 * @author 			Alfred Krzoska
 * 
 * 	1=Anmelder
	3=Versender
	4=Empfänger
	5=Erwerber-EU
	6=Käufer
	7=Verkäufer
	8=Zollwertanmelder
	9=Vertreter Zollwertanmelder
	10=Für Rechnung

 */

public class TsEMP extends Teilsatz {

	private String beznr    = "";      // Bezugsnummer
	private String kunnr    = "";      // Kundennummer
	private String zbnr     = "";      // Zollnummer
	private String ustid    = "";      // Umsatzsteuer-ID
	private String anam1    = "";      // Name1
	private String anam2    = "";      // Name2
	private String anam3    = "";      // Name3
	private String str      = "";      // Strasse
	private String postf    = "";      // Postfach
	private String lnd      = "";      // Land
	private String plz      = "";      // Postleitzahl
	private String ort      = "";      // Ort
	private String oteil    = "";      // Ortsteil
	private String tel	    = "";      // Telefon
	private String fax	    = "";      // Fax
	private String email    = "";      // e-mail Adresse
	private String kontkt   = "";      // Kontakt
	private String bemerk    = "";      // Bemerkungen
	
	
	    
    public TsEMP() {
	        tsTyp = "EMP";
    }

	public void setFields(String[] fields) {
		int size = fields.length;
		String ausgabe = "FSS: " + fields[0] + " size = " + size;
		//Utils.log( ausgabe);
			
		if (size < 1) { return; }
		tsTyp = fields[0];
		if (size < 2) { return; }	
	    beznr       = fields[1];
	    if (size < 3) { return; }
	    kunnr		= fields[2];
	    if (size < 4) { return; }
	    zbnr		= fields[3];
	    if (size < 5) { return; }
	    ustid		= fields[4];
	    if (size < 6) { return; }
	    anam1        = fields[5];
	    if (size < 7) { return; }
	    anam2		= fields[6];
	    if (size < 8) { return; }
	    anam3		 = fields[7];
	    if (size < 9) { return; }
	    str         = fields[8];
	    if (size < 10) { return; }
	    postf       = fields[9];
	    if (size < 11) { return; }
	    lnd         = fields[10];
	    if (size < 12) { return; }
	    plz			= fields[11];
	    if (size < 13) { return; }
	    ort			= fields[12];
	    if (size < 14) { return; }
	    oteil		= fields[13];
	    if (size < 15) { return; }
	    tel	        = fields[14];
	    if (size < 16) { return; }
	    fax			= fields[15];
	    if (size < 17) { return; }
	    email		 = fields[16];
	    if (size < 18) { return; }
	    kontkt       = fields[17];
	    if (size < 19) { return; }
	    bemerk       = fields[18];
	}

    public String teilsatzBilden() {
        StringBuffer buff = new StringBuffer();

        buff.append(tsTyp);
        buff.append(trenner);
        buff.append(beznr);
        buff.append(trenner);
        buff.append(kunnr);
        buff.append(trenner);
        buff.append(zbnr);
        buff.append(trenner);
        buff.append(ustid);
        buff.append(trenner);
        buff.append(anam1);
        buff.append(trenner);
        buff.append(anam2);
        buff.append(trenner);
        buff.append(anam3);
        buff.append(trenner);
        buff.append(str);
        buff.append(trenner);
        buff.append(postf);
        buff.append(trenner);
        buff.append(lnd);
        buff.append(trenner);
        buff.append(plz);
        buff.append(trenner);
        buff.append(ort);
        buff.append(trenner);
        buff.append(oteil);
	    buff.append(trenner);
        buff.append(tel);
        buff.append(trenner);
        buff.append(fax);
        buff.append(trenner);
        buff.append(email);
        buff.append(trenner);
        buff.append(kontkt);
        buff.append(trenner);
        buff.append(bemerk);
        buff.append(trenner);

        return new String(buff);
    }

    
	
	public boolean isEmpty() {
	String name = anam1 + anam2 + anam3;	
		
		return Utils.isStringEmpty(kunnr) && 
			   Utils.isStringEmpty(zbnr) &&
			   Utils.isStringEmpty(ustid) &&
			   Utils.isStringEmpty(name) &&
			   Utils.isStringEmpty(str) && 
			   Utils.isStringEmpty(postf) &&
			   Utils.isStringEmpty(lnd) && 
			   Utils.isStringEmpty(plz) && 
			   Utils.isStringEmpty(ort) &&
			   Utils.isStringEmpty(oteil) &&
			   Utils.isStringEmpty(tel) &&
			   Utils.isStringEmpty(fax) && 
			   Utils.isStringEmpty(email) && 
			   Utils.isStringEmpty(kontkt) &&
			   Utils.isStringEmpty(bemerk);
	}
}
    

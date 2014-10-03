package com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60;

import com.kewill.kcx.component.mapping.countries.common.Address;
import com.kewill.kcx.component.mapping.countries.common.ContactPerson;
import com.kewill.kcx.component.mapping.countries.common.Party;
import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Modul		:	TsADR
 * Erstellt		:	03.09.2008
 * Beschreibung	:	Ergänzungssatz Adressen ADR
 *
 * @author 			Miro Houdek
 */

public class TsADR extends Teilsatz {

    
    private String beznr     = "";      // Bezugsnummer
    private String posnr     = "";      // Positionsnummer
    private String typ       = "";      // Adresstyp
    private String name1     = "";      // Name1
    private String name2     = "";      // Name2
    private String name3     = "";      // Name3
    private String str       = "";      // Strasse
    private String ort       = "";      // Ort
    private String plz       = "";      // Postleitzahl
    private String land      = "";      // Land
    private String sbstlg    = "";      // Stellung
    private String sbname    = "";      // Sachbearbeiter
    private String email	 = "";      // eMail-Adresse
    private String telnr     = "";      // Telefon-Nummer
    private String faxnr     = "";      // Telefax-Nummer

    public TsADR() {
        tsTyp = "ADR";
    }

public void setFields(String[] fields)
       {
	int size = fields.length;
	String ausgabe = "FSS: "+fields[0]+" size = "+ size;
	//Utils.log( ausgabe);
		
	
	if (size < 1 ) return;		
		tsTyp   = fields[0];
		if (size < 2 ) return;	
           beznr         = fields[1];
           if (size < 3 ) return;
           posnr         = fields[2];
           if (size < 4 ) return;
           typ           = fields[3];
           if (size < 5 ) return;
           name1         = fields[4];
           if (size < 6 ) return;
           name2         = fields[5];
           if (size < 7 ) return;
           name3         = fields[6];
           if (size < 8 ) return;
           str           = fields[7];
           if (size < 9) return;
           ort           = fields[8];
           if (size < 10) return;
           plz           = fields[9];
           if (size < 11) return;
           land          = fields[10];
           if (size < 12) return;
           sbstlg        = fields[11];
           if (size < 13) return;
           sbname        = fields[12];
           if (size < 14) return;
           email         = fields[13];
           if (size < 15) return;
           telnr         = fields[14];
           if (size < 16) return;
           faxnr         = fields[15];
      }

    public String teilsatzBilden() {
        StringBuffer buff = new StringBuffer();

        buff.append(tsTyp);
        buff.append(trenner);
        buff.append(beznr);
        buff.append(trenner);
        buff.append(posnr);
        buff.append(trenner);
        buff.append(typ);
        buff.append(trenner);
        buff.append(name1);
        buff.append(trenner);
        buff.append(name2);
        buff.append(trenner);
        buff.append(name3);
        buff.append(trenner);
        buff.append(str);
        buff.append(trenner);
        buff.append(ort);
        buff.append(trenner);
        buff.append(plz);
        buff.append(trenner);
        buff.append(land);
        buff.append(trenner);
        buff.append(sbstlg);
	    buff.append(trenner);
	    buff.append(sbname);
	    buff.append(trenner);
	    buff.append(email);
	    buff.append(trenner);
	    buff.append(telnr);
	    buff.append(trenner);
	    buff.append(faxnr);
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

	public String getPosnr() {
		return posnr;
	
	}

	public void setPosnr(String posnr) {
		this.posnr = Utils.checkNull(posnr);
	}

	public String getTyp() {
		return typ;
	
	}

	public void setTyp(String typ) {
		this.typ = Utils.checkNull(typ);
	}

	public String getName1() {
		return name1;
	
	}

	public void setName1(String name1) {
		this.name1 = Utils.checkNull(name1);
	}

	public String getName2() {
		return name2;
	
	}

	public void setName2(String name2) {
		this.name2 = Utils.checkNull(name2);
	}

	public String getName3() {
		return name3;
	
	}

	public void setName3(String name3) {
		this.name3 = Utils.checkNull(name3);
	}

	public String getStr() {
		return str;
	
	}

	public void setStr(String str) {
		this.str = Utils.checkNull(str);
	}

	public String getOrt() {
		return ort;
	
	}

	public void setOrt(String ort) {
		this.ort = Utils.checkNull(ort);
	}

	public String getPlz() {
		return plz;
	
	}

	public void setPlz(String plz) {
		this.plz = Utils.checkNull(plz);
	}

	public String getLand() {
		return land;
	
	}

	public void setLand(String land) {
		this.land = Utils.checkNull(land);
	}

	public String getSbstlg() {
		return sbstlg;
	
	}

	public void setSbstlg(String sbstlg) {
		this.sbstlg = Utils.checkNull(sbstlg);
	}

	public String getSbname() {
		return sbname;
	
	}

	public void setSbname(String sbname) {
		this.sbname = Utils.checkNull(sbname);
	}

	public String getEmail() {
		return email;
	
	}

	public void setEmail(String email) {
		this.email = Utils.checkNull(email);
	}

	public String getTelnr() {
		return telnr;
	
	}

	public void setTelnr(String telnr) {
		this.telnr = Utils.checkNull(telnr);
	}

	public String getFaxnr() {
		return faxnr;
	
	}

	public void setFaxnr(String faxnr) {
		this.faxnr = Utils.checkNull(faxnr);
	}

	public void setAdrSubset(Party party, String typ, String beznr, String posnr) {
		// Consignor(1), Consignee(2), Declarant(3), Agent(4), Subcontractor(5)			
		if (party == null) 
			return;
		
		Address address = party.getAddress();			
		ContactPerson contact = party.getContactPerson();
		
		if (address == null && contact == null) 
			return;
		
		this.setBeznr(beznr);
		this.setPosnr(posnr);    
		this.setTyp(typ);  
		
		String name = "";
		String n1 = "";
		String n2 = "";
		String n3 = "";					 			
		int len = 0;
		
		if (address != null) {						
			name = address.getName();
			if (name != null)
				len = name.length();			
			if (len > 0 && len <= 35)
				n1 = name;
			else if (len > 35 && len <= 70) {
				// C.K. Substring bis 35 statt 34
				n1 = name.substring(0, 35);
				n2 = name.substring(35);
			}
			else if (len > 70 ) {		
				n1 = name.substring(0, 35);
				// C.K. Substring bis 70 statt 69
				n2 = name.substring(35, 70);
				n3 = name.substring(70);
			}
			
			this.setName1(n1);  
			this.setName2(n2);      
			this.setName3(n3);      
			this.setStr(address.getStreet());     
			this.setOrt(address.getCity());     
			this.setPlz(address.getPostalCode());      
			this.setLand(address.getCountry()); 			
		}				
		
		// CK090429 mittels set-Methoden besetzen, da dort checknull aufgerufen wird
		if (contact != null) {
			setSbstlg(contact.getPosition());   	// Stellung
			setSbname(contact.getClerk());      	// Sachbearbeiter Name (Kids-Bezeichnung)
			setEmail(contact.getEmail());      		// eMail-Adresse
			setTelnr(contact.getPhoneNumber());     // Telefon-Nummer
			setFaxnr(contact.getFaxNumber());
		}				
	}
	
	public boolean isEmpty() {
	String name = name1 + name2 + name3;	
		
		if ( Utils.isStringEmpty(name)  && Utils.isStringEmpty(str) && Utils.isStringEmpty(ort)
		  && Utils.isStringEmpty(plz) && Utils.isStringEmpty(land) && Utils.isStringEmpty(sbstlg)
		  && Utils.isStringEmpty(sbname) && Utils.isStringEmpty(email) && Utils.isStringEmpty(telnr)
		  && Utils.isStringEmpty(faxnr) )
			return true;
		else
			return false;			
	}
}
    

 

  
  



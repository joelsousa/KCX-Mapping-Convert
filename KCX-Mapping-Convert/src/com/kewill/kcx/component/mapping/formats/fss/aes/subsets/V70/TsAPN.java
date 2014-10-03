package com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V70;

import com.kewill.kcx.component.mapping.countries.common.ContactPerson;
import com.kewill.kcx.component.mapping.countries.common.Party;
import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		:	Export/aes
 * Created		:	25.07.2012
 * Description	:	Kontaktdaten - Ergänzungssatz zu Adressen ADR.
 *
 * @author 	iwaniuk
 * @version  2.1.00 (Zabis V70)   
 */

public class TsAPN extends Teilsatz {

    
    private String beznr     = "";      // Bezugsnummer
    private String posnr     = "";      // Positionsnummer
    private String typ       = "";      // Adresstyp    
    private String sbstlg    = "";      // Stellung
    private String sbname    = "";      // Sachbearbeiter
    private String email	 = "";      // eMail-Adresse
    private String telnr     = "";      // Telefon-Nummer
    private String faxnr     = "";      // Telefax-Nummer

    public TsAPN() {
        tsTyp = "APN";
    }

    public void setFields(String[] fields) {    
    	int size = fields.length;
		
    	if (size < 1)  { return; }
			tsTyp = fields[0];
		if (size < 2)  { return; }
           beznr  = fields[1];
        if (size < 3)  { return; }
           posnr  = fields[2];
        if (size < 4)  { return; }
           typ    = fields[3];
        if (size < 5)  { return; }
           sbstlg = fields[4];
        if (size < 6)  { return; }
           sbname = fields[5];
        if (size < 7)  { return; }
           email  = fields[6];
        if (size < 8)  { return; }
           telnr  = fields[7];
        if (size < 9)  { return; }
           faxnr  = fields[8];          
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

	public void setAdrContact(ContactPerson contact, String typ, String beznr, String posnr) {		
		if (contact == null) {
			return;
		}
		this.setBeznr(beznr);
		this.setPosnr(posnr);    
		this.setTyp(typ);  
	
		this.setSbstlg(contact.getPosition());   	
		if (!Utils.isStringEmpty(contact.getName())) {
			this.setSbname(contact.getName());      	
		} else {
			this.setSbname(contact.getClerk());     
		}
		this.setEmail(contact.getEmail());      	
		this.setTelnr(contact.getPhoneNumber());    
		this.setFaxnr(contact.getFaxNumber());			
	}
	
	public void setApnSubset(Party party, String typ, String beznr, String posnr) {
		// Consignor(1), Consignee(2), Declarant(3), Agent(4), Subcontractor(5), FinalUser(6)			
		if (party == null) {
			return;	
		}
		if (party.getContactPerson() == null) {
			return;	
		}
		ContactPerson contact = party.getContactPerson();
		
		this.setBeznr(beznr);
		this.setPosnr(posnr);    
		this.setTyp(typ);
		this.setSbstlg(contact.getPosition()); 
		if (!Utils.isStringEmpty(contact.getName())) {
			this.setSbname(contact.getName());
		} else {
			this.setSbname(contact.getClerk());
		}
		this.setEmail(contact.getEmail());
		this.setTelnr(contact.getPhoneNumber());
		this.setFaxnr(contact.getFaxNumber());		
	}
		
	public boolean isEmpty() {		
		return Utils.isStringEmpty(sbstlg)  && Utils.isStringEmpty(sbname) && Utils.isStringEmpty(telnr) &&	  
		   Utils.isStringEmpty(email) && Utils.isStringEmpty(faxnr);			
	}
}
    

      // xcbnxgbn

  
  



package com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V70;

import com.kewill.kcx.component.mapping.countries.common.Address;
import com.kewill.kcx.component.mapping.countries.common.ContactPerson;
import com.kewill.kcx.component.mapping.countries.common.Party;
import com.kewill.kcx.component.mapping.countries.common.TIN;
import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		:	Export/aes
 * Created		:	25.07.2012
 * Description	:	Adressen ADR.  
 *
 * @author 	iwaniuk
 * @version 2.1.00 (Zabis V70)
 * 
 * 	1=Ausfuehrer == Consignor
 *  2=Empfaenger == Consignee 
	3=Anmelder == Declarant
	4=Vertreter des Anmelders == Agent
	5=Subunternehmer == Subcontractor
	6=Endverwender	== FinalUser
 */

public class TsADR extends Teilsatz {

	private String beznr    = "";      // Bezugsnummer
	private String posnr    = "";      // Positionsnummer
	private String typ      = "";      // Adresstyp
	private String kdnr     = "";      // Kundennummer des Beteiligten
	private String tin      = "";      // EORI.TIN des Beteiligten
	private String nl       = "";      // Niederlassung des Anmelders
	private String dtzo     = "";      // Identifikationsart zur EORI-TIN des Anmelders (0=Address, 2=EORI)
	private String etn	    = "";      // ETN-Adresse zum Vortrag einer EAM an den Anmedler
	private String name1    = "";
	private String name2    = "";
	private String name3    = "";
	private String str      = "";      // Strasse
	private String ort      = "";      // Ort
	private String plz      = "";      // Postleitzahl
	private String land      = "";      // Land
		
    public TsADR() {
	        tsTyp = "ADR";
    }
    public TsADR(String type) {
        tsTyp = "ADR";
        typ = type;
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
	    posnr       = fields[2];
	    if (size < 4) { return; }
	    typ        = fields[3];
	    if (size < 5) { return; }
	    kdnr        = fields[4];
	    if (size < 6) { return; }
	    tin        = fields[5];
	    if (size < 7) { return; }
	    nl         = fields[6];
	    if (size < 8) { return; }
	    dtzo		 = fields[7];
	    if (size < 9) { return; }
	    etn         = fields[8];
	    if (size < 10) { return; }
	    name1         = fields[9];
	    if (size < 11) { return; }
	    name2         = fields[10];
	    if (size < 12) { return; }
	    name3       = fields[11];
	    if (size < 13) { return; }
	    str         = fields[12];
	    if (size < 14) { return; }
	    ort         = fields[13];
	    if (size < 15) { return; }
	    plz         = fields[14];
	    if (size < 16) { return; }
	    land       = fields[15];
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
        buff.append(kdnr);
        buff.append(trenner);
        buff.append(tin);
        buff.append(trenner);
        buff.append(nl);
        buff.append(trenner);
        buff.append(dtzo);
	    buff.append(trenner);
	    buff.append(etn);
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
       
        return new String(buff);
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
	public void setPosnr(String beznr) {
		this.posnr = Utils.checkNull(beznr);
	}

	public String getTyp() {
		return typ;
	}
	public void setTyp(String typ) {
		this.typ = Utils.checkNull(typ);
	}

	public String getKdnr() {
		return kdnr;
	}
	public void setKdnr(String kdnr) {
		this.kdnr = Utils.checkNull(kdnr);
	}
	public String getTin() {
		return tin;
	}
	public void setTin(String tin) {
		this.tin = Utils.checkNull(tin);
	}

	public String getNl() {
		return nl;
	}
	public void setNl(String nl) {
		this.nl = Utils.checkNull(nl);
	}
	
	public String getDtzo() {
		return dtzo;
	}
	public void setDtzo(String value) {
		this.dtzo = Utils.checkNull(value);
	}
	
	public String getEtn() {
		return etn;
	}
	public void setEtn(String value) {
		this.etn = Utils.checkNull(value);
	}
	
	public String getName1() {
		return name1;
	}
	public void setName1(String name) {
		this.name1 = Utils.checkNull(name);
	}
	
	public String getName2() {
		return name2;
	}
	public void setName2(String name) {
		this.name2 = Utils.checkNull(name);
	}
	
	public String getName3() {
		return name3;
	}
	public void setName3(String name) {
		this.name3 = Utils.checkNull(name);
	}
	
	public String getStr() {
		return str;
	}

	public void setStr(String str) {
		this.str = Utils.checkNull(str);
	}
	
	public String getLand() {
		return land;
	}

	public void setLand(String land) {
		this.land = Utils.checkNull(land);
	}

	public String getPlz() {
		return plz;
	}

	public void setPlz(String plz) {
		this.plz = Utils.checkNull(plz);
	}

	public String getOrt() {
		return ort;
	}

	public void setOrt(String ort) {
		this.ort = Utils.checkNull(ort);
	}
	
	public void setAdrSubset(Party party, String typ, String beznr, String posnr) {
		// Consignor(1), Consignee(2), Declarant(3), Agent(4), Subcontractor(5), FinalUser(6)			
		if (party == null) {
			return;
		}
		Address address = party.getAddress();			
		ContactPerson contact = party.getContactPerson();
		TIN tin = party.getPartyTIN();
		
		if (address == null && contact == null && tin == null) {
			return;
		}
		
		this.setBeznr(beznr);
		this.setPosnr(posnr);    
		this.setTyp(typ);
		
		this.setEtn(party.getETNAddress());
		
		if (party.getPartyTIN() != null) {
			this.setKdnr(party.getPartyTIN().getCustomerIdentifier());
			this.setTin(party.getPartyTIN().getTIN());
			this.setNl(party.getPartyTIN().getBO());
			this.setDtzo(party.getPartyTIN().getIdentificationType());			
		}
						 						
		if (address != null) {	
			String name = "";
			String n1 = "";
			String n2 = "";
			String n3 = "";	
			if (!Utils.isStringEmpty(address.getName())) {
				n1 = address.getName();		
				if (n1.length() > 35) {
					name = n1;
					n1 = name.substring(0, 35);
					n2 = name.substring(35);
				}	
				if (n2.length() > 35) {
					name = n2;
					n2 = name.substring(0, 35);
					n3 = name.substring(35);
				}				
			}		
			this.setName1(n1);  
			this.setName2(n2);      
			this.setName3(n3);      
			this.setStr(address.getStreet());     
			this.setOrt(address.getCity());     
			this.setPlz(address.getPostalCode());      
			this.setLand(address.getCountry()); 		
			
		}										
	}
	
	public boolean isEmpty() {
		String name = name1 + name2 + name3;	
			
		return ( Utils.isStringEmpty(kdnr)  && Utils.isStringEmpty(tin) && Utils.isStringEmpty(nl) &&
			     Utils.isStringEmpty(dtzo) && Utils.isStringEmpty(etn) && Utils.isStringEmpty(name) &&
			     Utils.isStringEmpty(str) && Utils.isStringEmpty(ort) && Utils.isStringEmpty(plz) &&
			     Utils.isStringEmpty(land));						
		}

}
    

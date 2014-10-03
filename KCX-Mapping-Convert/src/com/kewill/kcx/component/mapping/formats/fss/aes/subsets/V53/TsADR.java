package com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V53;

import com.kewill.kcx.component.mapping.countries.common.Address;
import com.kewill.kcx.component.mapping.countries.common.Party;
import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Modul		:	TsADR
 * Erstellt		:	03.09.2008
 * Beschreibung	:	Ergänzungssatz Adressen ADR
 *
 * @author 			Miro Houdek
 *
 */

public class TsADR extends Teilsatz {

    private String tsTyp     = "";      // Ts-Schlüssel
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

    public TsADR() {
        tsTyp = "ADR";
    }

public void setFields(String[] fields)
       {
	int size = fields.length;
	//String ausgabe = "FSS: "+fields[0]+" size = "+ size;
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
	
	public void setAdrSubset(Party party, String typ, String beznr, String posnr) {
		// Consignor(1), Consignee(2), Declarant(3), Agent(4), Subcontractor(5)			
		if (party == null) 
			return;
		
		String name = "";
		String n1 = "";
		String n2 = "";
		String n3 = "";		
			 		
		Address address = party.getAddress();			
			
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
						
			this.setBeznr(beznr);
			this.setPosnr(posnr);    
			this.setTyp(typ);       
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
		
		if ( Utils.isStringEmpty(name)  && Utils.isStringEmpty(str) && Utils.isStringEmpty(ort)
		  && Utils.isStringEmpty(plz) && Utils.isStringEmpty(land) )		
			return true;
		else
			return false;			
	}
}
    

  
  



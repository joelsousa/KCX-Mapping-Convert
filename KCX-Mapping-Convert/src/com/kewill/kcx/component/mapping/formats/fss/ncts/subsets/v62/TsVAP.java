package com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62;


import com.kewill.kcx.component.mapping.countries.common.ContactPerson;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.PartyNCTS;
import com.kewill.kcx.component.mapping.countries.de.ncts.msg.common.AddressNCTS;
import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/*
* Funktion    : TsVAP.java
* Titel       :
* Erstellt    : 10.09.2010
* Author      : Kewill / Christine Kron
* 
* Description : subset VAP refers to ve-fss-62.doc 

* Parameter   : 
* Return      : keine

*
* Changes:
* -----------
* Author      :  
* Date        :  
* Kennzeichen :
* Description :
*             
*
*/


/**
 * Modul		: TsVAP<br>
 * Date			: 10.09.2010<br>
 * Description	: Definition of subset TsVAP.
 * 
 * @author Christine Kron
 * @version 1.0.00
 */
public class TsVAP extends Teilsatz {
    
	private String beznr  	= "";      // Bezugsnummer 
	private String posnr  	= "0";      // Positionsnummer hier immer mit "0" besetzen
	private String adrtyp  	= "";      // Adresstyp 
	private String name1  	= "";      // Name1
	private String str  	= "";      // Strasse und (!) Hausnummer
	private String ort  	= "";      // Ort
	private String plz  	= "";      // Postleitzahl
	private String land  	= "";      // Land

    public TsVAP() {
        tsTyp = "VAP";
    }
	public void setAdrSubset(PartyNCTS party, String typ, String beznr, String posnr) {
		// Consignor(1), Consignee(2), Declarant(3), Agent(4), Subcontractor(5)			
		if (party == null) {
			return;
		}
		
		AddressNCTS address = party.getAddress();			
		if (address == null) {
			return;
		}		
		
		this.setBeznr(beznr);
		this.setPosnr(posnr);    
		this.setAdrtyp(typ);  
		
		String name = address.getName();
		String n1 = "";
		// String n2 = "";
		// String n3 = "";	
		
		int len = 0;									
		if (name != null) {
			len = name.length();
		}
							
		if (len > 0 && len <= 35) {
				n1 = name;
		} else if (len > 35 && len <= 70) {
				// C.K. Substring bis 35 statt 34
				n1 = name.substring(0, 35);
				// n2 = name.substring(35);
		} else if (len > 70) {		
				n1 = name.substring(0, 35);
				// C.K. Substring bis 70 statt 69
				// n2 = name.substring(35, 70);
				// n3 = name.substring(70);
		}
			
		this.setName1(n1);  
//			this.setName2(n2);      
//			this.setName3(n3);      
		this.setStr(address.getStreet()); 
			//EI20111027: street + HouseNumber
		if (address.getHouseNumber() != null && address.getHouseNumber().length() > 0) {  //EI20110523
				String strasse = Utils.checkNull(address.getStreet());
				String number = Utils.checkNull(address.getHouseNumber());
				strasse = strasse + " " + number;
				this.setStr(strasse.trim());  
		}
		this.setOrt(address.getCity());     
		this.setPlz(address.getPostalCode());      
		this.setLand(address.getCountry()); 			
								
	}

    public String teilsatzBilden() {
        StringBuffer buff = new StringBuffer();
          
        buff.append(tsTyp);
        buff.append(trenner);        
        buff.append(beznr);
        buff.append(trenner);	
        buff.append(posnr);
        buff.append(trenner);	
        buff.append(adrtyp);
        buff.append(trenner);	
        buff.append(name1);
        buff.append(trenner);	
        buff.append(str);
        buff.append(trenner);	
        buff.append(ort);
        buff.append(trenner);	
        buff.append(plz);
        buff.append(trenner);	
        buff.append(land);
        buff.append(trenner);	

    	return buff.toString();
    }

	

	// setFields will not be used because VAP is a specific subset of the message VAN
	// and this FSS-message is created by KCX to be sent to Zabis
	// only messages from Zabis have to write out this method

	public void setFields(String[] fields) {
		Utils.log("TsVAP.setFields not created");
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

	public String getAdrtyp() {
		return adrtyp;
	}

	public void setAdrtyp(String adrtyp) {
		this.adrtyp = Utils.checkNull(adrtyp);
	}

	public String getName1() {
		return name1;
	}

	public void setName1(String name1) {
		this.name1 = Utils.checkNull(name1);
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
	
	public boolean isEmpty() {
		if (Utils.isStringEmpty(adrtyp) && Utils.isStringEmpty(name1) &&
			 Utils.isStringEmpty(ort)) {
			return true;
		}
		return false;
	}
}




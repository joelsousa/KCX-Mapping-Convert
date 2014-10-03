package com.kewill.kcx.component.mapping.formats.fss.edec.Import.subsets.V70;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: EDEC Import 70<br>
 * Created		: 26.10.2012<br>
 * Description  : FSS Definition of Subset ADR - Addresssatz. 
 * 
 * @author iwaniuk
 * @version 7.0.00
 */

public class TsADR extends Teilsatz {
		
	private String lfdnr = "";     
	private String typ   = "";		 
	private String name1 = "";		 //Name
	private String name2 = "";		 //Name
	private String name3 = "";		 //Name
	private String str   = "";		 //Strasse
	private String land  = "";		 //Land		 
	private String plz   = "";		 //Postleitzahl
	private String ort   = "";		 //Ort	
	private String oteil  = "";	 
	
	/* Adresstypen: 
	3=Versender  == Consignor
	4=Empfaenger == Consignee
	5=Importeur  == Delarant
	6=Spediteur  == Representative
    */
	
	public TsADR() {
        tsTyp = "ADR";
    }

    public void setFields(String[] fields) { 
		int size = fields.length;
		//String ausgabe = "FSS: "+ fields[0] + " size = " + size;
		//Utils.log( ausgabe);
					
		if (size < 1) { return; }		
        tsTyp = fields[0];
        if (size < 2) { return;	}
        lfdnr = fields[1];
        if (size < 3) { return;	}
        typ = fields[2];        	
        if (size < 4) { return;	}	
        name1 = fields[3];
        if (size < 5) { return;	}	
        name2 = fields[4];
        if (size < 6) { return;	}	
        name3 = fields[5];        
        if (size < 7) { return;	}	
        str = fields[6];
        if (size < 8) { return;	}	
        land = fields[7];
        if (size < 9) { return;	}	
        plz = fields[8];        
        if (size < 10) { return;	}	
        ort = fields[9];
        if (size < 11) { return;	}	
        oteil = fields[10];
    }

    public String teilsatzBilden() {
        StringBuffer buff = new StringBuffer();

        buff.append(tsTyp);
        buff.append(trenner);
        buff.append(lfdnr);
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
        buff.append(land);
        buff.append(trenner);
        buff.append(plz);
        buff.append(trenner);
        buff.append(ort);
        buff.append(trenner);
        buff.append(oteil);
        buff.append(trenner);       

        return new String(buff);
      }

    public void setLfdnr(String lfdnr) {
		this.lfdnr = Utils.checkNull(lfdnr);
	}

	public String getLfdnr() {
		return lfdnr;	
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

	public String getTyp() {
		return typ;	
	}

	public void setTyp(String typ) {
		this.typ = Utils.checkNull(typ);
	}

	public String getOteil() {
		return oteil;	
	}

	public void setOteil(String ort) {
		this.oteil = Utils.checkNull(ort);
	}
	
	public boolean isEmpty() {
		if ( Utils.isStringEmpty(name1) &&
			 Utils.isStringEmpty(name2) &&
			 Utils.isStringEmpty(name3) &&
			 Utils.isStringEmpty(str) &&
			 Utils.isStringEmpty(land) &&
			 Utils.isStringEmpty(plz) && 
			 Utils.isStringEmpty(ort)) {			 			
			return true;			
		} else {
			return false;
		}
	}

}	

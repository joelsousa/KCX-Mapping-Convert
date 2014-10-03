package com.kewill.kcx.component.mapping.formats.fss.edec.Import.subsets.V70;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: EDEC Import 70<br>
 * Created		: 26.10.2012<br>
 * Description  : FSS Definition of Subset CKA. 
 * 
 * @author iwaniuk
 * @version 7.0.00
 */

public class TsCKA extends Teilsatz {
	
	private String refnr = "";       //Bezugsnummer	
	private String typ   = "";		 
	private String name  = "";		 //Name
	private String str   = "";		 //Strasse
	private String plz   = "";		 //Postleitzahl
	private String ort   = "";		 //Ort	
	private String land  = "";		 //Land		 	
	private String ref   = "";	 //new V70
	private String name1  = "";	 //new V70	
	private String name2  = "";	 //new V70	
		
	/* Adresstypen: 
		1=Versender  == Consignor
		2=Empfaenger == Consignee
		3=Importeur  == Declarant
		4=Spediteur  == Representative
	*/
	public TsCKA() {
        tsTyp = "CKA";
    }
	public TsCKA(String type) {
        tsTyp = "CKA";
        typ = type;
    }

    public void setFields(String[] fields) { 
		int size = fields.length;
		//String ausgabe = "FSS: "+ fields[0] + " size = " + size;
		//Utils.log( ausgabe);
					
		if (size < 1) { return; }		
        	tsTyp = fields[0];
        if (size < 2) { return;	}
        	refnr = fields[1];
        if (size < 3) { return;	}
         	typ = fields[2];        	
        if (size < 4) { return;	}	
        	name = fields[3];
        if (size < 5) { return;	}	
        	str = fields[4];
        if (size < 6) { return;	}	
        	plz = fields[5];        
        if (size < 7) { return;	}	
        	ort = fields[6];
        if (size < 8) { return;	}	
        	land = fields[7];
        if (size < 9) { return;	}	
        	ref = fields[8];        
        if (size < 10) { return; }	
        	name1 = fields[9];
        if (size < 11) { return; }	
        	name2 = fields[10];
    }

    public String teilsatzBilden() {
        StringBuffer buff = new StringBuffer();

        buff.append(tsTyp);
        buff.append(trenner);
        buff.append(refnr);
        buff.append(trenner);
        buff.append(typ);
        buff.append(trenner);
        buff.append(name);
        buff.append(trenner);
        buff.append(str);
        buff.append(trenner);
        buff.append(plz);
        buff.append(trenner);
        buff.append(ort);
        buff.append(trenner);
        buff.append(land);
        buff.append(trenner);
        buff.append(ref);
        buff.append(trenner);
        buff.append(name1);
        buff.append(trenner);
        buff.append(name2);
        buff.append(trenner);


        return new String(buff);
      }

    public void setRefnr(String refnr) {
		this.refnr = Utils.checkNull(refnr);
	}

	public String getRefnr() {
		return refnr;	
    }

	public String getName() {
		return name;	
	}

	public void setName(String name) {
		this.name = Utils.checkNull(name);
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
	
	public String getRef() {
		return ref;	
	}

	public void setRef(String plz) {
		this.ref = Utils.checkNull(plz);
	}

	public String getName1() {
		return name1;	
	}

	public void setName1(String value) {
		this.name1 = Utils.checkNull(value);
	}

	public String getName2() {
		return name2;	
	}

	public void setName2(String value) {
		this.name2 = Utils.checkNull(value);
	}
	
	public boolean isEmpty() {
		if ( Utils.isStringEmpty(name) &&
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

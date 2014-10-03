package com.kewill.kcx.component.mapping.formats.fss.edec.aus.subsets;

/*
* Funktion    : TsCAD.java   
* Titel       :
* Erstellt    : 20.10.2008
* Author      : Alfred Krzoska
* Beschreibung: Adresssatz / address
* Anmerkungen :
* Parameter   :
* Rückgabe    : keine
* Aufruf      :
*
*/

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

public class TsCAD extends Teilsatz {
	private String beznr = "";       //Bezugsnummer
	private String typ   = "";		 //Adresstyp 1=Exporter 2=Declarant
	private String name  = "";		 //Name
	private String str   = "";		 //Strasse
	private String land  = "";		 //Land		 
	private String plz   = "";		 //Postleitzahl
	private String ort   = "";		 //Ort
	
	public TsCAD() {
        tsTyp = "CAD";
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
         	typ = fields[2];        	
        if (size < 4 ) return;	
        	name = fields[3];
        if (size < 5 ) return;	
        	str = fields[4];
        if (size < 6 ) return;	
        	land = fields[5];        
        if (size < 7 ) return;	
        	plz = fields[6];
        if (size < 8 ) return;	
        	ort = fields[7];
    }

    public String teilsatzBilden() {
        StringBuffer buff = new StringBuffer();

        buff.append(tsTyp);
        buff.append(trenner);
        buff.append(beznr);
        buff.append(trenner);
        buff.append(typ);
        buff.append(trenner);
        buff.append(name);
        buff.append(trenner);
        buff.append(str);
        buff.append(trenner);
        buff.append(land);
        buff.append(trenner);
        buff.append(plz);
        buff.append(trenner);
        buff.append(ort);
        buff.append(trenner);

        return new String(buff);
      }

    public void setBeznr(String beznr) {
		this.beznr = Utils.checkNull(beznr);
	}

	public String getBeznr() {
		return beznr;
	
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
	
	public boolean isEmpty() {
		if ( 	Utils.isStringEmpty(name)
			 && Utils.isStringEmpty(str) 
			 && Utils.isStringEmpty(land) 
			 && Utils.isStringEmpty(plz) 
			 && Utils.isStringEmpty(ort)			 
			) return true;			
		else
			return false;
	}

}	

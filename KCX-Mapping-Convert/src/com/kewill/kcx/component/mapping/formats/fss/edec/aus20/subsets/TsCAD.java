package com.kewill.kcx.component.mapping.formats.fss.edec.aus20.subsets;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: EDEC 70<br>
 * Created		: 26.10.2012<br>
 * Description  : FSS Definition of Subset CAD. 
 * 
 * @author iwaniuk
 * @version 7.0.00
 */

public class TsCAD extends Teilsatz {
	
	private String beznr = "";       //Bezugsnummer	
	private String typ   = "";		 
	private String name  = "";		 //Name
	private String str   = "";		 //Strasse
	private String land  = "";		 //Land		 
	private String plz   = "";		 //Postleitzahl
	private String ort   = "";		 //Ort	
	private String ref   = "";	 //new V70
	private String zus1  = "";	 //new V70	
	private String zus2  = "";	 //new V70	
	
	//Adresstyp: 1=Consignor 2=Declarant 3=Consignee 5= ConsignorSecurity 6=ConsigneeSecurity 
	//           8=Zuladeort 9=Carrier
	
	public TsCAD() {
        tsTyp = "CAD";
    }

    public void setFields(String[] fields) { 
		int size = fields.length;
		//String ausgabe = "FSS: "+ fields[0] + " size = " + size;
		//Utils.log( ausgabe);
					
		if (size < 1) { return; }		
        	tsTyp = fields[0];
        if (size < 2) { return;	}
        	beznr = fields[1];
        if (size < 3) { return;	}
         	typ = fields[2];        	
        if (size < 4) { return;	}	
        	name = fields[3];
        if (size < 5) { return;	}	
        	str = fields[4];
        if (size < 6) { return;	}	
        	land = fields[5];        
        if (size < 7) { return;	}	
        	plz = fields[6];
        if (size < 8) { return;	}	
        	ort = fields[7];
        if (size < 9) { return;	}	
        	ref = fields[8];        
        if (size < 10) { return;	}	
        	zus1 = fields[9];
        if (size < 11) { return;	}	
        	zus2 = fields[10];
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
        buff.append(ref);
        buff.append(trenner);
        buff.append(zus1);
        buff.append(trenner);
        buff.append(zus2);
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

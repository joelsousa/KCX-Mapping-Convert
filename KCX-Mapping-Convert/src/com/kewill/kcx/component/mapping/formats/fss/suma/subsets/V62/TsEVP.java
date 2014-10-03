

package com.kewill.kcx.component.mapping.formats.fss.suma.subsets.V62;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;


/**
 * Modul		:	TsEVP
 * Erstellt		:	12.11.2012
 * Beschreibung	:   Positionsteilsatz
 * 					Verwahrmitteilung aus NCTS <GoodsReleasedExternal> EVP
 *        			Version 06.02  Christine Kron
 *
 */

public class TsEVP  extends Teilsatz {

	private String adrtyp	= "";			
	private String name		= "";			
	private String str		= "";			
	private String plz		= "";
	private String ort		= "";
	private String ldkz		= "";

    public TsEVP() {
        tsTyp = "EVP";
    }

    public void setFields(String[] fields)
    {
		int size = fields.length;
		String ausgabe = "FSS: "+fields[0]+" size = "+ size;
		//Utils.log( ausgabe);
			
		
		if (size < 1 ) return;		
        tsTyp = fields[0];
        if (size < 2 ) return;		
           adrtyp         = fields[1];
           if (size < 3 ) return;
           name         = fields[2];
           if (size < 4 ) return;
           str         = fields[3];
           if (size < 5 ) return;
           plz         = fields[4];
           if (size < 6 ) return;
           ort         = fields[5];
           if (size < 7 ) return;
           ldkz         = fields[6];

      }

 public String teilsatzBilden() {
        StringBuffer buff = new StringBuffer();

        buff.append(tsTyp);
        buff.append(trenner);
        buff.append(adrtyp);
        buff.append(trenner);
        buff.append(name);
        buff.append(trenner);
        buff.append(str);
        buff.append(trenner);
        buff.append(plz);
        buff.append(trenner);
        buff.append(ort);
        buff.append(trenner);
        buff.append(ldkz);
        buff.append(trenner);

        return new String(buff);
    }

public String getTsTyp() {
	return tsTyp;

}

public void setTsTyp(String tsTyp) {
	this.tsTyp = Utils.checkNull(tsTyp);
}


public boolean isEmpty() {

	if ( Utils.isStringEmpty(adrtyp)        && Utils.isStringEmpty(name) 
			&& Utils.isStringEmpty(str)
			&& Utils.isStringEmpty(plz)
			&& Utils.isStringEmpty(ort)
			&& Utils.isStringEmpty(ldkz)
			)
		return true;
	else
		return false;
}

public String getAdrtyp() {
	return adrtyp;
}

public void setAdrtyp(String adrtyp) {
	this.adrtyp = Utils.checkNull(adrtyp);
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
	this.str = Utils.checkNull(str);;
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
	this.ort = Utils.checkNull(ort);;
}

public String getLdkz() {
	return ldkz;
}

public void setLdkz(String ldkz) {
	this.ldkz = Utils.checkNull(ldkz);;
}


}



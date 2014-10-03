package com.kewill.kcx.component.mapping.formats.fss.suma.subsets.V62;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;


/**
 * Module		:	TsEVK
 * Created		:	19.12.2012
 * Description	:   Kopfsatz Gestellung-/Aufteilungsdaten.
 *        			Zabis Version 06.02  
 *
 * @author iwaniuk
 * @version 1.0.00
 */

public class TsSUA  extends Teilsatz {

	private String beznr = "";			// Bezugsnummer	
	private String name1 = "";			// Name
	private String name2 = "";			
	private String name3 = "";			
	private String str   = "";			// Straﬂe	
	private String ort   = "";			// Ort
	private String plz   = "";			// Postleitzahl
	private String ldkz  = "";			// Land
	private String oteil = "";			// Ortsteil
	private String postf = "";			// Postfach	

    public TsSUA() {
        tsTyp = "SUA";
    }

    public void setFields(String[] fields) {  
    	int size = fields.length;
		
		if (size < 1) { return; }
		tsTyp	= fields[0];
		if (size < 2) { return; }
		beznr	= fields[1];
		if (size < 3) { return; }		
		name1	= fields[2];
		if (size < 4) { return; }
		name2	= fields[3];
		if (size < 5) { return; }
		name3	= fields[4];
		if (size < 6) { return; }
		str		= fields[5];
		if (size < 7) { return; }
		ort		= fields[6];
		if (size < 8) { return; }
		plz		= fields[7];
		if (size < 9) { return; }
		ldkz	= fields[8];
		if (size < 10) { return; }
		oteil	= fields[9];
		if (size < 11) { return; }		
		postf	= fields[10];
		
    }

    public String teilsatzBilden() {
        StringBuffer buff = new StringBuffer();

		buff.append(tsTyp);
		buff.append(trenner);
		buff.append(beznr);
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
		buff.append(ldkz);
		buff.append(trenner);	
		buff.append(oteil);
		buff.append(trenner);	
		buff.append(postf);
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

	public String getLdkz() {
		return ldkz;
	}

	public void setLdkz(String ldkz) {
		this.ldkz = Utils.checkNull(ldkz);
	}

	public String getOteil() {
		return oteil;
	}

	public void setOteil(String oteil) {
		this.oteil = Utils.checkNull(oteil);
	}

	public String getPostf() {
		return postf;
	}

	public void setPostf(String postf) {
		this.postf = Utils.checkNull(postf);
	}

	public boolean isEmpty() {
		return (Utils.isStringEmpty(name1) &&
				Utils.isStringEmpty(name2) && Utils.isStringEmpty(name3) &&
				Utils.isStringEmpty(str) && Utils.isStringEmpty(ort) &&
				Utils.isStringEmpty(plz) && Utils.isStringEmpty(ldkz) &&
				Utils.isStringEmpty(oteil) && Utils.isStringEmpty(postf));					
	}

}



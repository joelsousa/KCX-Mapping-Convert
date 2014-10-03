/*
* Function    : TsNA.java
* Title       :
* Created     : 29.09.2009
* Author      : Elisabeth Iwaniuk
* Description: NameAndAddress for KIDS-AES to eCustoms
*/

package com.kewill.kcx.component.mapping.formats.Bdec.subsets;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Modul        : TsNA<br>
 * Erstellt     : 29.09.2009<br>
 * Beschreibung : NameAndAddress for KIDS-AES to eCustoms.
 * 
 * @author iwaniuk
 * @version 6.0.00
 */
public class TsNA extends Teilsatz {

    private String type            = "";       
    //CZ=EUTrader, CN=nonEUTrader, DT=declarant, WH=Warehouse, CM=Supervising 
    private String shortname       = "";       //
    private String tidEori         = "";       //
    private String longname        = "";       //
    private String street          = "";       //
    private String city            = "";       //
    private String lng             = "";       //
    private String postcode        = "";       //
    private String cntry           = "";       //
    private String eoriAiStatement = "";       //


    public TsNA() {
        tsTyp = "NA";
        trenner = trennerUK;
    }

    public void setFields(String[] fields) {
		int size = fields.length;
		//String ausgabe = "TsNA: "+fields[0]+" size = "+ size;
		//Utils.log( ausgabe);
			
		if (size < 1) { return; }
        tsTyp           = fields[0];
        if (size < 2) { return; }
        type            = fields[1];
        if (size < 3) { return; }
        shortname       = fields[2];
        if (size < 4) { return; }
        tidEori         = fields[3];
        if (size < 5) { return; }
        longname        = fields[4];    
        if (size < 6) { return; }
        street          = fields[5];
        if (size < 7) { return; }
        city            = fields[6];
        if (size < 8) { return; }
        lng             = fields[7]; 
        if (size < 9) { return; }
        postcode        = fields[8];
        if (size < 10) { return; }
        cntry           = fields[9];
        if (size < 11) { return; }
        eoriAiStatement = fields[10];
    }

    public String teilsatzBilden() {
        StringBuffer buff = new StringBuffer();
        
        buff.append(tsTyp);
        buff.append(trenner);
        buff.append(type);
        buff.append(trenner);
        buff.append(shortname);
        buff.append(trenner);
        buff.append(tidEori);
        buff.append(trenner);
        buff.append(longname);
        buff.append(trenner);
        buff.append(street);
        buff.append(trenner);
        buff.append(city);
        buff.append(trenner);
        buff.append(lng);
        buff.append(trenner);
        buff.append(postcode);
        buff.append(trenner);
        buff.append(cntry);
        buff.append(trenner);
        buff.append(eoriAiStatement);
        //buff.append(trenner);       

        return new String(buff);
      }

	public String getType() {
		return type;	
	}
	public void setType(String argument) {
		this.type = Utils.checkNull(argument);
	}
	
	public String getShortname() {
		return shortname;	
	}
	public void setShortname(String argument) {
		this.shortname = Utils.checkNull(argument);
	}

	public String getTidEori() {
		return tidEori;	
	}

	public void setTidEori(String argument) {
		this.tidEori = Utils.checkNull(argument);
	}
	public String getLongname() {
		return longname;	
	}

	public void setLongname(String argument) {
		this.longname = Utils.checkNull(argument);
	}

	public String getStreet() {
		return street;	
	}

	public void setStreet(String argument) {
		this.street = Utils.checkNull(argument);
	}
	public String getCity() {
		return city;	
	}

	public void setCity(String argument) {
		this.city = Utils.checkNull(argument);
	}

	public String getLng() {
		return lng;	
	}

	public void setLng(String argument) {
		this.lng = Utils.checkNull(argument);
	}
		
	public String getPostcode() {
		return postcode;	
	}

	public void setPostcode(String argument) {
		this.postcode = Utils.checkNull(argument);
	}
	
	public String getCntry() {
		return cntry;	
	}

	public void setCntry(String argument) {
		this.cntry = Utils.checkNull(argument);
	}

	public String getEoriAiStatement() {
		return eoriAiStatement;	
	}

	public void seteoriAiStatement(String argument) {
		this.eoriAiStatement = Utils.checkNull(argument);
	}

	public boolean isEmpty() {			
		return (Utils.isStringEmpty(shortname) && Utils.isStringEmpty(tidEori) &&
			    Utils.isStringEmpty(longname) && Utils.isStringEmpty(street) &&
		        Utils.isStringEmpty(city) && Utils.isStringEmpty(lng) &&
		        Utils.isStringEmpty(postcode) && Utils.isStringEmpty(cntry) &&
			    Utils.isStringEmpty(eoriAiStatement));
	}
}



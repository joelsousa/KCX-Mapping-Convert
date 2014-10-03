package com.kewill.kcx.component.mapping.formats.fss.suma.subsets.V70;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Modul		:	Manifest
 * Erstellt		:	17.12.2013
 * Beschreibung	:   Kopfsatz Zeile OCI.
 *        			Zabis Version 70  
 *@author iwaniuk
 *@version 7.0.00
 */

public class CsnOCI  extends Teilsatz {

	private String csnTrenner	= "/";			
	private String land	= "DE";
	private String subType	= "OCI";
	private String type = "M";
	private String mrn	= "";

	 public CsnOCI() {
	        tsTyp = "OCI";
	    }

	 public void setFields(String[] fields) {
			int size = fields.length;
			
			if (size < 1) { return; }		
	        tsTyp = fields[0];
	        if (size < 2) { return; }		
	        land = fields[1];
	        if (size < 3) { return; }
	        subType = fields[2];
	        if (size < 4) { return; }
	        type = fields[3];
	        if (size < 5) { return; }
	        mrn = fields[4];
	 }

	 public String teilsatzBilden() {
	        StringBuffer buff = new StringBuffer();

	        buff.append(tsTyp);
	        buff.append(csnTrenner);
	        buff.append(land);
	        buff.append(csnTrenner);
	        buff.append(subType);
	        buff.append(csnTrenner);
	        buff.append(type);
	        buff.append(csnTrenner);
	        buff.append(mrn);
	        
	        return new String(buff);
	    }

	 public String getTsTyp() {
	 	return tsTyp;
	 }
	 public void setTsTyp(String tsTyp) {
	 	this.tsTyp = Utils.checkNull(tsTyp);
	 }

	 
	public String getLand() {
		return land;
	}
	public void setLand(String land) {
		this.land = Utils.checkNull(land);
	}

	public String getSubType() {
		return subType;
	}
	public void setSubType(String oci) {
		this.subType = Utils.checkNull(oci);
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = Utils.checkNull(type);
	}

	public String getMrn() {
		return mrn;
	}
	public void setMrn(String mrn) {
		this.mrn = Utils.checkNull(mrn);
	}
	
	public boolean isEmpty() {
 		return (Utils.isStringEmpty(land) && Utils.isStringEmpty(subType) &&
 				Utils.isStringEmpty(type) && Utils.isStringEmpty(mrn));
 	}
}



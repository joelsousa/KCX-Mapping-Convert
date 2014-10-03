/*
* Function    : TsDT.java
* Title       :
* Created     : 29.09.2009
* Author      : Elisabeth Iwaniuk
* Description : DetailTax for KIDS-AES to eCustoms
*/

package com.kewill.kcx.component.mapping.formats.Bdec.subsets;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Modul        : TsDT<br>
 * Erstellt     : 29.09.2009<br>
 * Beschreibung : DetailTax for KIDS-AES to eCustoms.
 * 
 * @author iwaniuk
 * @version 6.0.00
 */
public class TsDT extends Teilsatz {

    private String ttyCode       = "";       //
    private String mopCode       = "";       //
    private String itlnBaseQty   = "";       //
    private String taxRateId     = "";       //
    private String ttyOvrCode    = "";       //
    private String itlnDeclTaxDc = "";       //
    private String itlnBaseAmtDc = "";       //
 

    public TsDT() {
        tsTyp = "DT";
        trenner = trennerUK;
    }

    public void setFields(String[] fields) {
		int size = fields.length;
		//String ausgabe = "TsDT: "+fields[0]+" size = "+ size;
		//Utils.log( ausgabe);
		
		if (size < 1) { return; }
        tsTyp = fields[0];
        if (size < 2) { return; }
        ttyCode         = fields[1];
        if (size < 3) { return; }
        mopCode         = fields[2];
        if (size < 4) { return; }
        itlnBaseQty     = fields[3];
        if (size < 5) { return; }
        taxRateId       = fields[4];    
        if (size < 6) { return; }
        ttyOvrCode      = fields[5];
        if (size < 7) { return; }
        itlnDeclTaxDc   = fields[6];
        if (size < 8) { return; }
        itlnBaseAmtDc   = fields[7];     
    }

    public String teilsatzBilden() {
        StringBuffer buff = new StringBuffer();

        buff.append(tsTyp);
        buff.append(trenner);
        buff.append(ttyCode);
        buff.append(trenner);
        buff.append(mopCode);
        buff.append(trenner);
        buff.append(itlnBaseQty);
        buff.append(trenner);
        buff.append(taxRateId);
        buff.append(trenner);
        buff.append(ttyOvrCode);
        buff.append(trenner);
        buff.append(itlnDeclTaxDc);
        buff.append(trenner);
        buff.append(itlnBaseAmtDc);
        //buff.append(trenner);
       
        return new String(buff);
      }

	public String getTtyCode() {
		return ttyCode;	
	}

	public void setTtyCode(String argument) {
		this.ttyCode = Utils.checkNull(argument);
	}

	public String getMopCode() {
		return mopCode;	
	}

	public void setMopCode(String argument) {
		this.mopCode = Utils.checkNull(argument);
	}

	public String getItlnBaseQty() {
		return itlnBaseQty;	
	}

	public void setItlnBaseQty(String argument) {
		this.itlnBaseQty = Utils.checkNull(argument);
	}

	public String getTaxRateId() {
		return taxRateId;	
	}

	public void setTaxRateId(String argument) {
		this.taxRateId = Utils.checkNull(argument);
	}
	public String getTtyOvrCode() {
		return ttyOvrCode;	
	}

	public void setTtyOvrCode(String argument) {
		this.ttyOvrCode = Utils.checkNull(argument);
	}

	public String getItlnDeclTaxDc() {
		return itlnDeclTaxDc;	
	}

	public void setItlnDeclTaxDc(String argument) {
		this.itlnDeclTaxDc = Utils.checkNull(argument);
	}
	public String getItlnBaseAmtDc() {
		return itlnBaseAmtDc;	
	}

	public void setItlnBaseAmtDc(String argument) {
		this.itlnBaseAmtDc = Utils.checkNull(argument);
	}

	public boolean isEmpty() {		
		return (Utils.isStringEmpty(ttyCode) && Utils.isStringEmpty(mopCode) &&
				Utils.isStringEmpty(itlnBaseQty) && Utils.isStringEmpty(taxRateId) &&
				Utils.isStringEmpty(ttyOvrCode) && Utils.isStringEmpty(itlnDeclTaxDc) &&
				Utils.isStringEmpty(itlnBaseAmtDc));
	}
}



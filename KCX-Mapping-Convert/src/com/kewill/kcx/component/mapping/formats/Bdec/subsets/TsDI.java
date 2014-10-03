/*
* Function    : TsDI.java
* Title       :
* Created     : 29.09.2009
* Author      : Elisabeth Iwaniuk
* Description : DetailIngredient for KIDS-AES to eCustoms
*/

package com.kewill.kcx.component.mapping.formats.Bdec.subsets;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Modul        : TsDI<br>
 * Erstellt     : 29.09.2009<br>
 * Beschreibung : DetailIngredient for KIDS-AES to eCustoms.
 * 
 * @author iwaniuk
 * @version 6.0.00
 */
public class TsDI extends Teilsatz {

    private String ibIngredientCode = "";       //
    private String ibIngredientQuantity = "";       //
    private String ibIngredientLicenceSn = "";       //

    public TsDI() {
        tsTyp = "DI";
        trenner = trennerUK;
    }

    public void setFields(String[] fields) {
		int size = fields.length;
		//String ausgabe = "TsDI: "+fields[0]+" size = "+ size;
		//Utils.log( ausgabe);
			
		
		if (size < 1) { return; }
        tsTyp = fields[0];
        if (size < 2) { return; }
        ibIngredientCode      = fields[1];
        if (size < 3) { return; }
        ibIngredientQuantity  = fields[2];
        if (size < 4) { return; }
        ibIngredientLicenceSn = fields[3];        
    }

    public String teilsatzBilden() {
        StringBuffer buff = new StringBuffer();

        buff.append(tsTyp);
        buff.append(trenner);
        buff.append(ibIngredientCode);
        buff.append(trenner);
        buff.append(ibIngredientQuantity);
        buff.append(trenner);
        buff.append(ibIngredientLicenceSn);
        //buff.append(trenner);

        return new String(buff);
      }

	public String getIbIngredientCode() {
		return ibIngredientCode;	
	}

	public void setIbIngredientCode(String argument) {
		this.ibIngredientCode = Utils.checkNull(argument);
	}

	public String getIbIngredientQuantity() {
		return ibIngredientQuantity;	
	}

	public void setIbIngredientQuantity(String argument) {
		this.ibIngredientQuantity = Utils.checkNull(argument);
	}

	public String getIbIngredientLicenceSn() {
		return ibIngredientLicenceSn;	
	}

	public void setIbIngredientLicenceSn(String argument) {
		this.ibIngredientLicenceSn = Utils.checkNull(argument);
	}	
	
	public boolean isEmpty() {
			//return false;  // Always return false because Grossmass is optional
		return (Utils.isStringEmpty(ibIngredientCode) && Utils.isStringEmpty(ibIngredientQuantity) && 
		        Utils.isStringEmpty(ibIngredientLicenceSn));
	}
}



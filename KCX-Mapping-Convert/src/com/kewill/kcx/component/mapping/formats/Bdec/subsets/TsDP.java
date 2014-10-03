/*
* Function    : TsDP.java
* Title       :
* Created     : 29.09.2009
* Author      : Elisabeth Iwaniuk
* Description : DetailPackage for KIDS-AES to eCustoms
*/

package com.kewill.kcx.component.mapping.formats.Bdec.subsets;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Modul        : TsDP<br>
 * Erstellt     : 29.09.2009<br>
 * Beschreibung : DetailPackage for KIDS-AES to eCustoms.
 * 
 * @author iwaniuk
 * @version 6.0.00
 */
public class TsDP extends Teilsatz {

    private String pkgCount = "";       //
    private String pkgKind = "";       //
    private String pkgMarks = "";       //
    private String pkgLng = "";       //

    public TsDP() {
        tsTyp = "DP";
        trenner = trennerUK;
    }

    public void setFields(String[] fields) {
		int size = fields.length;
		//String ausgabe = "FSS: "+fields[0]+" size = "+ size;
		//Utils.log( ausgabe);
		
		if (size < 1) { return; }
        tsTyp = fields[0];
        if (size < 2) { return; }
        pkgCount        = fields[1];
        if (size < 3) { return; }
        pkgKind         = fields[2];
        if (size < 4) { return; }
        pkgMarks        = fields[3];
        if (size < 5) { return; }
        pkgLng          = fields[4];        
    }

    public String teilsatzBilden() {
        StringBuffer buff = new StringBuffer();

        buff.append(tsTyp);
        buff.append(trenner);
        buff.append(pkgCount);
        buff.append(trenner);
        buff.append(pkgKind);
        buff.append(trenner);
        buff.append(pkgMarks);
        buff.append(trenner);
        buff.append(pkgLng);
        //buff.append(trenner);

        return new String(buff);
      }

	public String getPkgCount() {
		return pkgCount;	
	}

	public void setPkgCount(String argument) {
		this.pkgCount = Utils.checkNull(argument);
	}

	public String getPkgKind() {
		return pkgKind;	
	}

	public void setPkgKind(String argument) {
		this.pkgKind = Utils.checkNull(argument);
	}

	public String getPkgMarks() {
		return pkgMarks;	
	}

	public void setPkgMarks(String argument) {
		this.pkgMarks = Utils.checkNull(argument);
	}
	
	public String getPkgLng() {
		return pkgLng;	
	}

	public void setPkgLng(String argument) {
		this.pkgLng = Utils.checkNull(argument);
	}
	public boolean isEmpty() {
		return (Utils.isStringEmpty(pkgCount) && Utils.isStringEmpty(pkgKind) &&
			    Utils.isStringEmpty(pkgMarks) && Utils.isStringEmpty(pkgLng));
	}
}



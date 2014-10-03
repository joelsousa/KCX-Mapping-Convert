/**
 * Modul        :   Export
 * Nachricht    :   Angaben PDF AMP
 * Erstellt     :   02.06.2006
 * Beschreibung :   TsAMP
 *  
 * @author          Kilian Braune
 * 
 */

package com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V53;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.util.Utils;

public class TsAMP extends Teilsatz {
    public String beznr  = "";        //  Bezugsnummer
    public String pdfnam = "";       //  Name des PDF-Dokumentes
    public String pdfpfd = "";       //  Pfad, in dem diese Datei liegt
    public String subdir = "";       //  Unterverzeichnis, in das das PDF-DOC verschoben werden muss
    
    public TsAMP() {
        tsTyp = "AMP";
    }
    
    public void setFields(String[] fields)
    {
		int size = fields.length;
		String ausgabe = "FSS: "+fields[0]+" size = "+ size;
		//Utils.log( ausgabe);
			
		
		if (size < 1 ) return;		
        tsTyp   = fields[0];
        if (size < 2 ) return;	
        beznr   = fields[1];
        if (size < 3 ) return;	
        pdfnam  = fields[2];
        if (size < 4 ) return;	
        pdfpfd  = fields[3];
        if (size < 5 ) return;	
        subdir  = fields[4];                
    }
    
    public String teilsatzBilden() {
        StringBuffer buff = new StringBuffer();
        
        buff = buff.append(tsTyp);
        buff = buff.append(trenner);
        buff = buff.append(beznr);
        buff = buff.append(trenner);
        buff = buff.append(pdfnam);
        buff = buff.append(trenner);
        buff = buff.append(pdfpfd);
        buff = buff.append(trenner);
        buff = buff.append(subdir);
        buff = buff.append(trenner);
        
        return new String(buff);
    }

    public boolean isPDFValid() {
        if (pdfnam.trim().equals("") && 
            pdfpfd.trim().equals("") && 
            subdir.trim().equals("")) {
            return false;
        }
        return true;        
    }

	public String getBeznr() {
		return beznr;
	
	}

	public void setBeznr(String beznr) {
		this.beznr = Utils.checkNull(beznr);
	}

	public String getPdfnam() {
		return pdfnam;
	
	}

	public void setPdfnam(String pdfnam) {
		this.pdfnam = Utils.checkNull(pdfnam);
	}

	public String getPdfpfd() {
		return pdfpfd;
	
	}

	public void setPdfpfd(String pdfpfd) {
		this.pdfpfd = Utils.checkNull(pdfpfd);
	}

	public String getSubdir() {
		return subdir;
	
	}

	public void setSubdir(String subdir) {
		this.subdir = Utils.checkNull(subdir);
	}
	
	public boolean isEmpty() {
		if ( Utils.isStringEmpty(pdfnam)  && Utils.isStringEmpty(pdfpfd) 
		&& Utils.isStringEmpty(subdir) )		 
			return true;
		else
			return false;
	}

}
package com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V53;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.countries.common.Packages;
import com.kewill.kcx.component.mapping.util.Utils;


/**
 * Modul		:	TsACO
 * Erstellt		:	03.09.2008
 * Beschreibung	:	collidaten
 *
 * @author 			Miro Houdek
 *
 */




public class TsACO extends Teilsatz {

    private String tsTyp     = "";       // Ts-Schlüssel
    private String beznr     = "";       // Bezugsnummer
    private String posnr     = "";       // Positionsnummer
    private String lfdnr     = "";       // laufende Nummer
    private String colanz    = "";       // Anzahl Packstücke
    private String colart    = "";       // Art Packstücke
    private String colzch    = "";       // Collizeichen und -nummern

	public TsACO() {
        tsTyp = "ACO";
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
        posnr = fields[2];
        if (size < 4 ) return;
        lfdnr = fields[3];
        if (size < 5 ) return;
        colanz = fields[4];
        if (size < 6 ) return;
        colart = fields[5];
        if (size < 7 ) return;
        colzch = fields[6];
    }

    public String teilsatzBilden() {
        StringBuffer buff = new StringBuffer();

        buff.append(tsTyp);
        buff.append(trenner);        
        buff.append(beznr);
        buff.append(trenner);
        buff.append(posnr);
        buff.append(trenner);
        buff.append(lfdnr);
        buff.append(trenner);
        buff.append(colanz);
        buff.append(trenner);
        buff.append(colart);
        buff.append(trenner);
        buff.append(colzch);
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

	public String getPosnr() {
		return posnr;
	
	}

	public void setPosnr(String posnr) {
		this.posnr = Utils.checkNull(posnr);
	}

	public String getLfdnr() {
		return lfdnr;
	
	}

	public void setLfdnr(String lfdnr) {
		this.lfdnr = Utils.checkNull(lfdnr);
	}

	public String getColanz() {
		return colanz;
	
	}

	public void setColanz(String colanz) {
		this.colanz = Utils.checkNull(colanz);
	}

	public String getColart() {
		return colart;
	
	}

	public void setColart(String colart) {
		this.colart = Utils.checkNull(colart);
	}

	public String getColzch() {
		return colzch;
	
	}

	public void setColzch(String colzch) {
		this.colzch = Utils.checkNull(colzch);
	}
	
	public void setAcoSubset(Packages packages, String aBeznr, String aPosnr ) {
		if (packages == null) return;	

		this.setBeznr(aBeznr);  				 
		this.setPosnr(aPosnr);  
		this.setLfdnr(packages.getSequentialNumber());  
		this.setColanz(packages.getQuantity()); 
		this.setColart(packages.getType()); 
		this.setColzch(packages.getMarks());		
	}
	public boolean isEmpty() {
		if ( Utils.isStringEmpty(colanz) 
		  && Utils.isStringEmpty(colart) && Utils.isStringEmpty(colzch))		  
			return true;
		else
			return false;
	}
	
}

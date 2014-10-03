/*
* Funktion    : TsCCL.java
* Titel       :
* Erstellt    : 21.10.2008
* Author      : Alfred Krzoska
* Beschreibung: Packaging  
* Anmerkungen :
* Parameter   :
* Rückgabe    : keine
* Aufruf      :
*
*/

package com.kewill.kcx.component.mapping.formats.fss.edec.aus.subsets;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.countries.common.Packages;

public class TsCCL extends Teilsatz {

    private String beznr = "";       // ReferenceNumber
    private String posnr = "";       //	ItemNumber
    private String colart = "";      // Identifier
    private String colanz = "";      // Quantity
    private String colstk = "";      // Pieces
    private String colzch = "";      // MarksAndNumbers

    
    
    public TsCCL() {
        tsTyp = "CCL";
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
        	colart = fields[3];
        if (size < 5 ) return;	
        	colanz = fields[4];        
        if (size < 6 ) return;	
        	colstk  = fields[5];
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
        buff.append(colart);
        buff.append(trenner);
        buff.append(colanz);
        buff.append(trenner);
        buff.append(colstk);
        buff.append(trenner);
        buff.append(colzch);
        buff.append(trenner);

        return new String(buff);
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

	public String getColart() {
		return colart;
	
	}

	public void setColart(String colart) {
		this.colart = Utils.checkNull(colart);
	}

	public String getColanz() {
		return colanz;
	
	}

	public void setColanz(String colanz) {
		this.colanz = Utils.checkNull(colanz);
	}

	public String getColstk() {
		return colstk;
	
	}

	public void setColstk(String colstk) {
		this.colstk = Utils.checkNull(colstk);
	}

	public String getColzch() {
		return colzch;
	
	}

	public void setColzch(String colzch) {
		this.colzch = Utils.checkNull(colzch);
	}

	public void setCclSubset(Packages pac, String beznr, String item ) {
		if (pac == null) return;
		if (Utils.isStringEmpty(beznr)) return;
		if (Utils.isStringEmpty(item)) return;

		this.setBeznr(beznr);
		this.setPosnr(item);  	
		this.setColart(pac.getIdentifier());
		this.setColanz(pac.getQuantity());
		this.setColstk(pac.getQuantityCH());
		this.setColzch(pac.getMarks());
	}	
	
	public boolean isEmpty() {
		  if (     Utils.isStringEmpty(colart)
			 	&& Utils.isStringEmpty(colanz)
			 	&& Utils.isStringEmpty(colstk)
			 	&& Utils.isStringEmpty(colzch) 
		  	  )
			return true;
		else
			return false;
	}


}



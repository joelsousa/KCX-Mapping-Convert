package com.kewill.kcx.component.mapping.formats.fss.edec.aus.subsets;

/**
 * Funktion     :   EDEC Export TsCUN ProducedDocument Subset Position
 * Nachricht    :   
 * Erstellt     :   22.10.2008
 * Beschreibung :   TsCUN
 *  
 * @author          Alfred Krzoska
 * 
 */

import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.Document;
import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;




public class TsCUN extends Teilsatz {
	private String beznr  = "";      //Referencenumber
	private String posnr  = "";		 //Itemnumber
	private String untart = "";		 //Unterlagenart gemäss Codeverzeichnis
	private String untref = "";		 //Unterlagen-Referenz
	private String untzus  = "";	 //Unterlagen-Zusatz
	//AK20110414
	private String datum  = "";      //Unterlagen Datum nur bei Export
	private String zusang = "";      //Zusaetzliche Angaben nur bei Export
	
	
	public TsCUN() {
        tsTyp = "CUN";
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
        	untart = fields[3];
        if (size < 5 ) return;	
        	untref = fields[4];        
        if (size < 6 ) return;	
        	untzus = fields[5];
        if (size < 7 ) return;	
        	datum = fields[6];
        if (size < 8 ) return;	
        	zusang = fields[7];
    }

    public String teilsatzBilden() {
        StringBuffer buff = new StringBuffer();

        buff.append(tsTyp);
        buff.append(trenner);
        buff.append(beznr);
        buff.append(trenner);
        buff.append(posnr);
        buff.append(trenner);
        buff.append(untart);
        buff.append(trenner);
        buff.append(untref);
        buff.append(trenner);
        buff.append(untzus);
        buff.append(trenner);
        buff.append(datum);
        buff.append(trenner);
        buff.append(zusang);

        return new String(buff);
      }

    public void setBeznr(String beznr) {
		this.beznr = Utils.checkNull(beznr);
	}

	public String getBeznr() {
		return beznr;
	
	}

	public String getPosnr() {
		return posnr;
	
	}

	public void setPosnr(String posnr) {
		this.posnr = Utils.checkNull(posnr);
	}

	
	public void setCunSubset(Document doc, String beznr, String item ) {
		if (doc == null) return;
		if (Utils.isStringEmpty(beznr)) return;
		if (Utils.isStringEmpty(item)) return;

		this.setBeznr(beznr);
		this.setPosnr(item);
		
		if (doc != null)   {
			this.setUntart(doc.getTypeKids());  //EI20090409
			this.setUntref(doc.getReference());
			this.setUntzus(doc.getAdditionalInformation());
		}
	}

	public boolean isEmpty() {
         return Utils.isStringEmpty(untart) &&
       		 Utils.isStringEmpty(untref) &&
       		 Utils.isStringEmpty(untzus) &&
       		 Utils.isStringEmpty(datum) &&
       		 Utils.isStringEmpty(zusang);
	}

	public String getUntart() {
		return untart;
	
	}

	public void setUntart(String untart) {
		this.untart = Utils.checkNull(untart);
	}

	public String getUntref() {
		return untref;
	
	}

	public void setUntref(String untref) {
		this.untref = Utils.checkNull(untref);
	}

	public String getUntzus() {
		return untzus;
	
	}

	public void setUntzus(String untzus) {
		this.untzus = Utils.checkNull(untzus);
	}

	public String getDatum() {
		return datum;
	
	}

	public void setDatum(String datum) {
		this.datum = Utils.checkNull(datum);
	}

	public String getZusang() {
		return zusang;
	
	}

	public void setZusang(String zusang) {
		this.zusang = Utils.checkNull(zusang);
	}	

}	

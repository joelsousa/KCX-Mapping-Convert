
package com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60;
import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import java.util.ArrayList;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Modul        :   AIB
 * Teilsatz     :   TsAIB.java
 * Erstellt     :   13.06.2006
 * Beschreibung :   Beförderungsangaben AIB
 * 19.08.2008   : 	Alfred Krzoska
 *				:   Version 6
 */


public class TsAIB extends Teilsatz {

    public ArrayList aprList    = new ArrayList();            // Positionsreferenz
    public ArrayList acnList    = new ArrayList();            // Containerdaten

    private String tsTyp     = "";     // Ts-Schlüssel
    private String beznr     = "";     // Bezugsnummer
    private String artinf    = "";     // Art der Information
    private String kzart     = "";     // Kennzeichen Art der Information
    private String bfdat     = "";     // Zeitpunkt des Abfluges/Abfahrt
    private String bfkz      = "";     // Kennzeichen des Beförderungsmittels
    private String bfort     = "";     // Ladeort


    public TsAIB() {
        tsTyp = "AIB";
    }

    public void setFields(String[] fields)
	{
		int size = fields.length;
		String ausgabe = "FSS: "+fields[0]+" size = "+ size;
		//Utils.log( ausgabe);
	
		if (size < 1 ) return;		
        tsTyp = fields[0];
        if (size < 2 ) return;
	           beznr         = fields[1];
	           if (size < 3 ) return;
	           artinf        = fields[2];
	           if (size < 4 ) return;
	           kzart         = fields[3];
	           if (size < 5 ) return;
	           bfdat         = fields[4];
	           if (size < 6 ) return;
	           bfkz          = fields[5];
	           if (size < 7 ) return;
	           bfort         = fields[6];
	}


    public String teilsatzBilden() {
        StringBuffer buff = new StringBuffer();

        buff.append(tsTyp);
        buff.append(trenner);
        buff.append(beznr);
        buff.append(trenner);
        buff.append(artinf);
        buff.append(trenner);
        buff.append(kzart);
        buff.append(trenner);
        buff.append(bfdat);
        buff.append(trenner);
        buff.append(bfkz);
        buff.append(trenner);
        buff.append(bfort);
        buff.append(trenner);


        return new String(buff);
    }

    public void addApr(TsAPR apr) {

        aprList.add(apr);
    }

    public void addAcn(TsACN acn) {

        acnList.add(acn);
    }

	public ArrayList getAprList() {
		return aprList;
	}

	public void setAprList(ArrayList aprList) {
		this.aprList = aprList;
	}

	public ArrayList getAcnList() {
		return acnList;
	}

	public void setAcnList(ArrayList acnList) {
		this.acnList = acnList;
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

	public String getArtinf() {
		return artinf;
	
	}

	public void setArtinf(String artinf) {
		this.artinf = Utils.checkNull(artinf);
	}

	public String getKzart() {
		return kzart;
	
	}

	public void setKzart(String kzart) {
		this.kzart = Utils.checkNull(kzart);
	}

	public String getBfdat() {
		return bfdat;
	
	}

	public void setBfdat(String bfdat) {
		this.bfdat = Utils.checkNull(bfdat);
	}

	public String getBfkz() {
		return bfkz;
	
	}

	public void setBfkz(String bfkz) {
		this.bfkz = Utils.checkNull(bfkz);
	}

	public String getBfort() {
		return bfort;
	
	}

	public void setBfort(String bfort) {
		this.bfort = Utils.checkNull(bfort);
	}
	
	public boolean isEmpty() {

		if ( Utils.isStringEmpty(artinf) && Utils.isStringEmpty(kzart) 
				&& Utils.isStringEmpty(bfdat) && Utils.isStringEmpty(bfkz) 		  
		  && Utils.isStringEmpty(bfort))	
			return true;
		else
			return false;
	} 

}
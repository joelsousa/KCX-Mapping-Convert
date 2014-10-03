/**
 * Project:     fssin.uids.aes
 * Package:     fss.teilsaetze.aes
 * Created:     22.05.2006
 * File   :     TsADO.java
 * 
 * 03.09.2008       Version 6  Miro Houdek
 *
 */
package com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V53;
import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;

import java.util.ArrayList;
import com.kewill.kcx.component.mapping.util.Utils;

public class TsADO extends Teilsatz {

    public ArrayList adrList    = new ArrayList();            // Adressendaten
    public ArrayList adbList    = new ArrayList();            // Bermerkungsdaten
    public ArrayList addList    = new ArrayList();            // Dokumentenpositionsdaten

    private String tsTyp     = "";      // Ts-Schlüssel
    private String beznr     = "";      // Bezugsnummer
    private String pfomnr    = "";      // Präfix zur Formularnummer
    private String fomnr     = "";      // Formularnummer
    private String fomtyp    = "";      // Dokumententyp
    private String kdnrau    = "";      // Kundennummer des Ausführers
    private String kdnrem    = "";      // Kundennummer des Empfängers
    private String ldve      = "";      // Ausfuhrland
    private String ldbe      = "";      // Bestimmungsland
    private String bfvkzg    = "";      // Verkehrszweig an der Grenze
    private String bfartg    = "";      // Art des Beförderungsmittels an der Grenze
    private String bfkzg     = "";      // Kennzeichen/Name des Beförderungsmittels an der Grenze
    private String bfnatg    = "";      // Nationalität des Beförderungsmittels an der Grenze
    private String expdst    = "";      // Zollstelle


    public TsADO() {
        tsTyp = "ADO";
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
           pfomnr        = fields[2];
           if (size < 4 ) return;
           fomnr         = fields[3];
           if (size < 5 ) return;
           fomtyp        = fields[4];
           if (size < 6 ) return;
           kdnrau        = fields[5];
           if (size < 7 ) return;
           kdnrem        = fields[6];
           if (size < 8 ) return;
           ldve          = fields[7];
           if (size < 9 ) return;
           ldbe          = fields[8];
           if (size < 10 ) return;
           bfvkzg        = fields[9];
           if (size < 11 ) return;
           bfartg        = fields[10];
           if (size < 12 ) return;
           bfkzg         = fields[11];
           if (size < 13 ) return;
           bfnatg        = fields[12];
           if (size < 14 ) return;
           expdst        = fields[13];
      }

 public String teilsatzBilden() {
        StringBuffer buff = new StringBuffer();

        buff.append(tsTyp);
        buff.append(trenner);
        buff.append(beznr);
        buff.append(trenner);
        buff.append(pfomnr);
        buff.append(trenner);
        buff.append(fomnr);
        buff.append(trenner);
        buff.append(fomtyp);
        buff.append(trenner);
        buff.append(kdnrau);
        buff.append(trenner);
        buff.append(kdnrem);
        buff.append(trenner);
        buff.append(ldve);
        buff.append(trenner);
        buff.append(ldbe);
        buff.append(trenner);
        buff.append(bfvkzg);
        buff.append(trenner);
        buff.append(bfartg);
        buff.append(trenner);
        buff.append(bfkzg);
        buff.append(trenner);
        buff.append(bfnatg);
        buff.append(trenner);
        buff.append(expdst);
        buff.append(trenner);


        return new String(buff);
    }

    public void addAdr(TsADR adr) {

        adrList.add(adr);
    }

    public void addAdb(TsADB adb) {

        adbList.add(adb);
    }

    public void addAdd(TsADD add) {

        addList.add(add);
    }

	public ArrayList getAdrList() {
		return adrList;
	}

	public void setAdrList(ArrayList adrList) {
		this.adrList = adrList;
	}

	public ArrayList getAdbList() {
		return adbList;
	}

	public void setAdbList(ArrayList adbList) {
		this.adbList = adbList;
	}

	public ArrayList getAddList() {
		return addList;
	}

	public void setAddList(ArrayList addList) {
		this.addList = addList;
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

	public String getPfomnr() {
		return pfomnr;
	
	}

	public void setPfomnr(String pfomnr) {
		this.pfomnr = Utils.checkNull(pfomnr);
	}

	public String getFomnr() {
		return fomnr;
	
	}

	public void setFomnr(String fomnr) {
		this.fomnr = Utils.checkNull(fomnr);
	}

	public String getFomtyp() {
		return fomtyp;
	
	}

	public void setFomtyp(String fomtyp) {
		this.fomtyp = Utils.checkNull(fomtyp);
	}

	public String getKdnrau() {
		return kdnrau;
	
	}

	public void setKdnrau(String kdnrau) {
		this.kdnrau = Utils.checkNull(kdnrau);
	}

	public String getKdnrem() {
		return kdnrem;
	
	}

	public void setKdnrem(String kdnrem) {
		this.kdnrem = Utils.checkNull(kdnrem);
	}

	public String getLdve() {
		return ldve;
	
	}

	public void setLdve(String ldve) {
		this.ldve = Utils.checkNull(ldve);
	}

	public String getLdbe() {
		return ldbe;
	
	}

	public void setLdbe(String ldbe) {
		this.ldbe = Utils.checkNull(ldbe);
	}

	public String getBfvkzg() {
		return bfvkzg;
	
	}

	public void setBfvkzg(String bfvkzg) {
		this.bfvkzg = Utils.checkNull(bfvkzg);
	}

	public String getBfartg() {
		return bfartg;
	
	}

	public void setBfartg(String bfartg) {
		this.bfartg = Utils.checkNull(bfartg);
	}

	public String getBfkzg() {
		return bfkzg;
	
	}

	public void setBfkzg(String bfkzg) {
		this.bfkzg = Utils.checkNull(bfkzg);
	}

	public String getBfnatg() {
		return bfnatg;
	
	}

	public void setBfnatg(String bfnatg) {
		this.bfnatg = Utils.checkNull(bfnatg);
	}

	public String getExpdst() {
		return expdst;
	
	}

	public void setExpdst(String expdst) {
		this.expdst = Utils.checkNull(expdst);
	}

	
	public boolean isEmpty() {
		if ( Utils.isStringEmpty(pfomnr)
			 && Utils.isStringEmpty(fomnr)
			 && Utils.isStringEmpty(fomtyp) 
			 && Utils.isStringEmpty(kdnrau) 
			 && Utils.isStringEmpty(kdnrem)
			 && Utils.isStringEmpty(ldve) 
			 && Utils.isStringEmpty(ldbe) 
			 && Utils.isStringEmpty(bfvkzg)
			 && Utils.isStringEmpty(bfartg)
			 && Utils.isStringEmpty(bfkzg)
			 && Utils.isStringEmpty(bfnatg) 
			 && Utils.isStringEmpty(expdst) )  
    		return true;
		else
			return false;
	}	
}

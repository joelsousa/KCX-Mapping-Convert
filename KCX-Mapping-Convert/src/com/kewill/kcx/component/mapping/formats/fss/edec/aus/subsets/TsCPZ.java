package com.kewill.kcx.component.mapping.formats.fss.edec.aus.subsets;

/**
 * Funktion     :   EDEC Export TsCPZ Antragspositionen Ergänzung
 * Nachricht    :   
 * Erstellt     :   14.04.2011
 * Beschreibung :   TsCPZ
 *  
 * @author          Alfred Krzoska
 * 
 */

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Function TsCPZ.
 * @author krzoska
 * @version 1.0.00
 */
public class TsCPZ extends Teilsatz {
	private String beznr  = "";      //Referencenumber
	private String posnr  = "";		 //Itemnumber
	private String cdbwpf = "";		 //Bewilligungs-Pflichtcode
	private String rohmri = "";		 //RICO Rohmasse
	private String eigmri  = "";	 //RICO Eigenmasse	
	private String zusmri  = "";	 //RICO Statistische Zusatzmenge
	private String stwtri  = "";	 //RICO Statistischer Wert
	private String tnrri   = "";	 //RICO Warennummer
	private String artnr   = "";	 //Artikelnummer
	
	
	public TsCPZ() {
        tsTyp = "CPZ";
    }

    public void setFields(String[] fields) {
		int size = fields.length;
		//String ausgabe = "FSS: " + fields[0] + " size = " + size;
		//Utils.log( ausgabe);
			
		
		if (size < 1) { return; }		
        	tsTyp = fields[0];
        if (size < 2) { return; }
        	beznr = fields[1];
        if (size < 3) { return; }	
        	posnr = fields[2];
        if (size < 4) { return; }
        	cdbwpf = fields[3];
        if (size < 5) { return; }
        	rohmri = fields[4];        
        if (size < 6) { return; }
        	eigmri = fields[5];
       if (size < 7) { return; }
        	zusmri = fields[6];
       if (size < 8) { return; }
       		stwtri = fields[7];
       if (size < 9) { return; }
        	tnrri = fields[8];
       if (size < 10) { return; }
       		artnr = fields[9];
    }

    public String teilsatzBilden() {
        StringBuffer buff = new StringBuffer();

        buff.append(tsTyp);
        buff.append(trenner);
        buff.append(beznr);
        buff.append(trenner);
        buff.append(posnr);
        buff.append(trenner);
        buff.append(cdbwpf);
        buff.append(trenner);
        buff.append(rohmri);
        buff.append(trenner);
        buff.append(eigmri);
        buff.append(trenner);
        buff.append(zusmri);
        buff.append(trenner);
        buff.append(stwtri);
        buff.append(trenner);
        buff.append(tnrri);
        buff.append(trenner);
        buff.append(artnr);
        buff.append(trenner);
        
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

	public String getCdbwpf() {
		return cdbwpf;	
	}
	public void setCdbwpf(String cdbwpf) {
		this.cdbwpf = Utils.checkNull(cdbwpf);
	}

	public String getRohmri() {
		return rohmri;	
	}
	public void setRohmri(String rohmri) {
		this.rohmri = Utils.checkNull(rohmri);
	}

	public String getEigmri() {
		return eigmri;	
	}
	public void setEigmri(String eigmri) {
		this.eigmri = Utils.checkNull(eigmri);
	}

	public String getZusmri() {
		return zusmri;	
	}
	public void setZusmri(String zusmri) {
		this.zusmri = Utils.checkNull(zusmri);
	}

	public String getStwtri() {
		return stwtri;	
	}

	public void setStwtri(String stwtri) {
		this.stwtri = Utils.checkNull(stwtri);
	}

	public String getTnrri() {
		return tnrri;	
	}
	public void setTnrri(String tnrri) {
		this.tnrri = Utils.checkNull(tnrri);
	}

	public String getArtnr() {
		return artnr;	
	}
	public void setArtnr(String artnr) {
		this.artnr = Utils.checkNull(artnr);
	}

	public boolean isEmpty() {
		return Utils.isStringEmpty(cdbwpf)&&
			Utils.isStringEmpty(rohmri)&&
			Utils.isStringEmpty(eigmri)&&
			Utils.isStringEmpty(zusmri)&&
			Utils.isStringEmpty(stwtri)&&
			Utils.isStringEmpty(tnrri)&&
			Utils.isStringEmpty(artnr);
	}
	

}	

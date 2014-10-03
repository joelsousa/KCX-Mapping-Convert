
package com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60;
import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import java.util.ArrayList;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Modul        :   APR
 * Teilsatz     :   TsAPR.java
 * Erstellt     :   13.06.2006
 * Beschreibung :   Positionsreferenz APR
 * 
 * 19.06.2008       Version 6  Miro Houdek
 */

public class TsAPR extends Teilsatz {

    private String tsTyp     = "";         // Ts-Schlüssel
    private String beznr     = "";         // Bezugsnummer
    private String posnr     = "";         // Positionsnummer
    private String fregnr    = "";         // Registriernummer Fremdsystem
    private String eigmas    = "";         // Eigenmasse
    private String rohmas    = "";         // Rohmasse
    private String kzmin     = "";         // Kennzeichen Mindermenge

    public ArrayList adpList    = new ArrayList();            // Ausgangsdaten Position
    public ArrayList acoList    = new ArrayList();            // Collidaten - packaging
    public ArrayList nveList    = new ArrayList();            // Collidaten - packaging
    public ArrayList acnList    = new ArrayList();            // Containerdaten - container
    public ArrayList adrList    = new ArrayList();            // Adressen, Ausfuehrer & Empfaenger
    public ArrayList aedList    = new ArrayList();            // Unterlagendaten - document


    public TsAPR() {
        tsTyp = "APR";
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
	           posnr         = fields[2];
	           if (size < 4 ) return;
	           fregnr        = fields[3];
	           if (size < 5 ) return;
	           eigmas        = fields[4];
	           if (size < 6 ) return;
	           rohmas        = fields[5];
	           if (size < 7 ) return;
	           kzmin         = fields[6];
	}


    public String teilsatzBilden() {
        StringBuffer buff = new StringBuffer();

        buff.append(tsTyp);
        buff.append(trenner);
        buff.append(beznr);
        buff.append(trenner);
        buff.append(posnr);
        buff.append(trenner);
        buff.append(fregnr);
        buff.append(trenner);
        buff.append(eigmas);
        buff.append(trenner);
        buff.append(rohmas);
        buff.append(trenner);
        buff.append(kzmin);
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

	public String getFregnr() {
		return fregnr;
	}

	public void setFregnr(String fregnr) {
		this.fregnr = Utils.checkNull(fregnr);
	}

	public String getEigmas() {
		return eigmas;
	}

	public void setEigmas(String eigmas) {
		this.eigmas = Utils.checkNull(eigmas);
	}

	public String getRohmas() {
		return rohmas;
	}

	public void setRohmas(String rohmas) {
		this.rohmas = Utils.checkNull(rohmas);
	}

	public String getKzmin() {
		return kzmin;
	}

	public void setKzmin(String kzmin) {
		this.kzmin = Utils.checkNull(kzmin);
	}

	public ArrayList getAdpList() {
		return adpList;
	}

	public void setAdpList(ArrayList adpList) {
		this.adpList = adpList;
	}

	public ArrayList getAcoList() {
		return acoList;
	}

	public void setAcoList(ArrayList acoList) {
		this.acoList = acoList;
	}

	public ArrayList getNveList() {
		return nveList;
	}

	public void setNveList(ArrayList nveList) {
		this.nveList = nveList;
	}

	public ArrayList getAcnList() {
		return acnList;
	}

	public void setAcnList(ArrayList acnList) {
		this.acnList = acnList;
	}

	public ArrayList getAdrList() {
		return adrList;
	}

	public void setAdrList(ArrayList adrList) {
		this.adrList = adrList;
	}

	public ArrayList getAedList() {
		return aedList;
	}

	public void setAedList(ArrayList aedList) {
		this.aedList = aedList;
	}
	
	public boolean isEmpty() {

		if ( Utils.isStringEmpty(fregnr) && Utils.isStringEmpty(eigmas) 
				&& Utils.isStringEmpty(rohmas) && Utils.isStringEmpty(kzmin) )		  
			return true;
		else
			return false;
	} 
}
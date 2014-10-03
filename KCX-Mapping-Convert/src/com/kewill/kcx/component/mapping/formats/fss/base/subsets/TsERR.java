package com.kewill.kcx.component.mapping.formats.fss.base.subsets;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Modul        :   Basis
 * Nachricht    :   ERR (Local Application Result)
 * Erstellt     :   04.12.2008
 * Beschreibung :   Fehlermeldung zu einer FSS-Einarbeitung
 *     
 * @author          Alfred Krzoska
 * 
 * changes: iwaniuk: etFields korrigiert, damit es nicht abstuerzt, wenn nicht alle Felder geliefert
 */



public class TsERR extends Teilsatz
{
	private String datei;				// Dateiname der FSS-Datei
	private String beznr;				// Bezugsnummer
	private String arbnr;				// Arbeitsnummer des Antrags
	private String korant;				// Korrekturnummer
	private String regnr;				// Registriernummer (MRN) des Antrags
	private String posnr;				// Positionsnummer (0=Gesamter Antrag)
	private String fehart;				// Art des Fehlers; 02,04,05,06
	private String fehent;				// Entstehungsangabe; 03,07,08,09
	private String objart;				// Objektart; 01,02,03,04
	private String objnr;				// Referenznummer zum betroffenen Objekt
	private String fehcd;				// Fehlercode
	private String fehbes;				// Fehlermeldungstext
	private String gendat;				// Generierungsdatum der Fehlernachricht
	private String genzei;				// Generierungszeit der Fehlernachricht
               
	
	public TsERR() {
	        tsTyp = "ERR";
	}

	
	public void setFields(String[] fields) {
		int size = fields.length;
		
		if (size < 1) { return; }  
		tsTyp	= fields[0];
		if (size < 2) { return; }  
		datei	= fields[1];
		if (size < 3) { return; }  		
		beznr	= fields[2];
		if (size < 4) { return; }  
		arbnr	= fields[3];
		if (size < 5) { return; }  
		korant	= fields[4];
		if (size < 6) { return; }  
		regnr	= fields[5];
		if (size < 7) { return; }  
		posnr	= fields[6];
		if (size < 8) { return; }  
		fehart	= fields[7];
		if (size < 9) { return; }  
		fehent	= fields[8];
		if (size < 10) { return; }  
		objart	= fields[9];
		if (size < 11) { return; }  
		objnr	= fields[10];
		if (size < 12) { return; }  
		fehcd	= fields[11];
		if (size < 13) { return; }  
		fehbes	= fields[12];
		if (size < 14) { return; }  
		gendat	= fields[13];
		if (size < 15) { return; }  
		genzei	= fields[14];
	}

	public String teilsatzBilden() {
        StringBuffer buff = new StringBuffer();
        
        buff = buff.append(tsTyp);
        buff = buff.append(trenner);
        buff = buff.append(datei);
        buff = buff.append(trenner);
        buff = buff.append(beznr);
        buff = buff.append(trenner);
        buff = buff.append(korant);
        buff = buff.append(trenner);
        buff = buff.append(regnr);
        buff = buff.append(trenner);
        buff = buff.append(posnr);
        buff = buff.append(trenner);
        buff = buff.append(fehart);
        buff = buff.append(trenner);
        buff = buff.append(fehent);
        buff = buff.append(trenner);
        buff = buff.append(objart);
        buff = buff.append(trenner);
        buff = buff.append(objnr);
        buff = buff.append(trenner);
        buff = buff.append(fehcd);
        buff = buff.append(trenner);
        buff = buff.append(fehbes);
        buff = buff.append(trenner);
        buff = buff.append(gendat);
        buff = buff.append(trenner);
        buff = buff.append(genzei);
        buff = buff.append(trenner);
           
        return new String(buff);
	}

	
	public String getDatei() {
		return datei;
	
	}

	public void setDatei(String datei) {
		this.datei = Utils.checkNull(datei);
	}

	public String getBeznr() {
		return beznr;
	
	}

	public void setBeznr(String beznr) {
		this.beznr = Utils.checkNull(beznr);
	}

	public String getArbnr() {
		return arbnr;
	
	}

	public void setArbnr(String arbnr) {
		this.arbnr = Utils.checkNull(arbnr);
	}

	public String getKorant() {
		return korant;
	
	}

	public void setKorant(String korant) {
		this.korant = Utils.checkNull(korant);
	}

	public String getRegnr() {
		return regnr;
	
	}

	public void setRegnr(String regnr) {
		this.regnr = Utils.checkNull(regnr);
	}

	public String getPosnr() {
		return posnr;
	
	}

	public void setPosnr(String posnr) {
		this.posnr = Utils.checkNull(posnr);
	}

	public String getFehart() {
		return fehart;
	
	}

	public void setFehart(String fehart) {
		this.fehart = Utils.checkNull(fehart);
	}

	public String getFehent() {
		return fehent;
	
	}

	public void setFehent(String fehent) {
		this.fehent = Utils.checkNull(fehent);
	}

	public String getObjart() {
		return objart;
	
	}

	public void setObjart(String objart) {
		this.objart = Utils.checkNull(objart);
	}

	public String getObjnr() {
		return objnr;
	
	}

	public void setObjnr(String objnr) {
		this.objnr = Utils.checkNull(objnr);
	}

	public String getFehcd() {
		return fehcd;
	
	}

	public void setFehcd(String fehcd) {
		this.fehcd = Utils.checkNull(fehcd);
	}

	public String getFehbes() {
		return fehbes;
	
	}

	public void setFehbes(String fehbes) {
		this.fehbes = Utils.checkNull(fehbes);
	}

	public String getGendat() {
		return gendat;
	
	}

	public void setGendat(String gendat) {
		this.gendat = Utils.checkNull(gendat);
	}

	public String getGenzei() {
		return genzei;
	
	}

	public void setGenzei(String genzei) {
		this.genzei = Utils.checkNull(genzei);
	}

	public boolean isEmpty() {
	
		return false;
	} 	
	
}

package com.kewill.kcx.component.mapping.formats.fss.suma.subsets.V70;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.formats.fss.utils.FssUtils;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		:	Manifest
 * Created		:	12.03.2014
 * Description	:   optionaller Teilsath in allen AntwortNachrichten (wg. LCAG/CMP)
 *        			Zabis Version 70  
 *
 * @author iwaniuk        
 * @version 7.0.00
 * 
 * 
 * 
 *                  TODO
 * 
 * 
 */

public class TsKUP extends Teilsatz {
		
	private String beznr;			//	Bezugsnummer
	private String regnr;			//	Registriernummer
	private String posnr;			//	
	private String inhalt;			//	optionaler Inhalt: an3 an3 an2, an3, an14, n2, leer
									// an3 =Verarbeitungszustand, moegliche Werte: VSA, VSM, ESA, ESV, WAV 
									// an3 =Höchstes Fehlergewicht, nur bei CUSREC, moegliche Werte: INF, ERR, ? 
									// an2 =Kopfstatus in ZABIS, 
									// an3 =3LetterCode des Beladeortes(Airport of Departure)
								    // an14=Empfangsdatum der ATB-Nummer, 
									//EI20140120: jetzt ist  Time (glaube ich ) dazu gekommen
									// an2 =Positionsstatus (nur CUSFIN/SEK) 
									//TODO: FlunNr
									//TODO: DepartureDate
									//TODO: AirportOfArrival, ArrivalDate
	/* KopfStatus feste Vorbelegung (DN)
	CUSREC (VSA/VSM/ESA/ESV): 90 ( Hier werden eh nur die fehlhaften gelistet)
	CUSSTP:                   45
	CUSTST:                   45
	CUSFIN: da hier die Nachricht für jede Position einzeln kommt, kann Positionsstatus in den KUN-Teilsatz  
			aufgenommen werden, der kann 60 oder 45 sein. 
	 */
	
	//private char pipe =  0x7C; //"|"; 
	private String  pipe = "|"; 
	private String iStatus = "";
	private String iBesOrt = "";
	
	
	public TsKUP() {    
        tsTyp = "KUP";
    }
	
	public void setFields(String[] fields) {		
		int size = fields.length;
		
		if (size < 1) { return; } 
		tsTyp	= fields[0];
		if (size < 2) { return; }
		beznr	= fields[1];
		if (size < 3) { return; }
		regnr	= fields[2];		
		if (size < 4) { return; }
		posnr	= fields[3];	
		if (size < 5) { return; }
		inhalt	= fields[4];
		
		this.setInhaltArray(inhalt);
		
	}
	private void setInhaltArray(String inhalt) {
		if (Utils.isStringEmpty(inhalt)) {
			return;
		}
		inhalt = inhalt.trim();
		//String[] inh = inhalt.split("" + pipe); //trennerUK		
		//EI20140214: pipe = inhalt.substring(3, 4);
		String[] inh = inhalt.split("\\" + pipe); 
		int len = inh.length;
		String inh0 = inh[0];
		if (len == 1 && !Utils.isStringEmpty(inh0)) { //EI20140214
			inh0 = inh0.trim();
			if (inh0.equals(inhalt)) {
				Utils.log("(TsKUP ohne Trennen im Inhalt");    
				return;           //ohne pipe
			}
		}
		
		if (len < 1) { return; }
		iStatus = inh[0]; 
		if (len < 2) { return; }
		iBesOrt = inh[1]; 
		/*
		if (len < 3) { return; }
		iKpfStat = inh[2]; 
		if (len < 4) { return; }
		iDepCode = inh[3]; 
		if (len < 5) { return; }
		iAtbDat = inh[4]; 
		if (len < 6) { return; }
		iAtbTime = inh[5]; 
		if (len < 7) { return; }
		iPosStat = inh[6]; 		
		 */
	}
	
	@Override
	public boolean isEmpty() {
		return Utils.isStringEmpty(regnr) && Utils.isStringEmpty(inhalt);					   
	}

	@Override
	public String teilsatzBilden() {
		 StringBuffer buff = new StringBuffer();

		 	buff.append(tsTyp);
		 	buff.append(trenner);
		 	buff.append(beznr);
		 	buff.append(trenner);
		 	buff.append(regnr);
		 	buff.append(trenner);		 	
		 	buff.append(posnr);	
		 	buff.append(trenner);
		 	buff.append(inhalt);	
		 	buff.append(trenner);
		 	
		 	return buff.toString();
	}


	public String getBeznr() {
		return beznr;
	}
	public void setBeznr(String beznr) {
		this.beznr = Utils.checkNull(beznr);
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
	public void setPosnr(String value) {
		this.posnr = Utils.checkNull(value);
	}
	
	public String getInhalt() {
		return inhalt;
	}
	public void setInhalt(String value) {
		this.inhalt = Utils.checkNull(value);
	}
	///
	public String getStauts() {
		return iStatus;
	}
	public void setStauts(String value) {
		this.iStatus = Utils.checkNull(value);
	}
	
	public String getBesOrt() {
		return iBesOrt;
	}
	public void setBesOrt(String value) {
		this.iBesOrt = Utils.checkNull(value);
	}
}

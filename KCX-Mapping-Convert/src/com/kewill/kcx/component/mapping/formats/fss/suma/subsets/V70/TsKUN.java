package com.kewill.kcx.component.mapping.formats.fss.suma.subsets.V70;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.formats.fss.utils.FssUtils;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		:	Manifest
 * Created		:	03.07.2013
 * Description	:   optionaller Teilsatz in allen AntwortNachrichten (wg. LCAG/CMP)
 *        			Zabis Version 70  
 *
 * @author iwaniuk
 * @version 7.0.00
 */

public class TsKUN extends Teilsatz {
		
	private String beznr;			//	Bezugsnummer
	private String regnr;			//	Registriernummer	
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
	private String iVerZstd = "";
	private String iFehGew = "";
	private String iKpfStat = "";
	private String iDepCode = "";	
	private String iAtbDat = "";
	private String iAtbTime = "";
	private String iPosStat = ""; //Positionsstatus (nur CUSFIN/SEK)
	private String iEntOrt = "";  //EI20140604: 3Letter-Code des Entladeortes, ausschließlich für Luftfracht/Wiederausfuhr
	private String iFlugNr = "";  //EI20140604:
	private String iAnkDat = ""; //EI20140604: Ankunftsdatum, ausschließlich für Luftfracht/Wiederausfuhr (FMT=DD.MO.YYYY
	//private String iArrCode = "";
	
	
	public TsKUN() {    
        tsTyp = "KUN";
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
		inhalt	= fields[3];	
		
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
				Utils.log("(TsKUN ohne Trennen im Inhalt (alte Variante von TsKUN)");    
				return;           //alte TsKUN: ohne pipe
			}
		}
		
		if (len < 1) { return; }
		iVerZstd = inh[0]; 
		if (len < 2) { return; }
		iFehGew = inh[1]; 
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
		if (len < 8) { return; }
		iEntOrt = inh[7]; 
		if (len < 9) { return; }
		iFlugNr = inh[8]; 
		if (len < 10) { return; }
		iAnkDat = inh[9];
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

	public String getInhalt() {
		return inhalt;
	}
	public void setInhalt(String value) {
		this.inhalt = Utils.checkNull(value);
	}
	///
	public String getVerZstd() {
		return iVerZstd;
	}
	public String getFehGew() {
		return iFehGew;
	}
	public String getKpfStat() {
		return iKpfStat;
	}
	public String getDepartureCode() {
		return iDepCode;
	}
	public String getAtbDat() {
		return iAtbDat;
	}
	public String getAtbTime() {
		return iAtbTime;
	}
	public String getPosStat() {
		return iPosStat;
	}
	public String getEntOrt() {
		return iEntOrt;
	}
	public String getFlugNr() {
		return iFlugNr;
	}
	public String getAnkDat() {
		//EI20140627: return iAnkDat:
		String date = iAnkDat;   //EI20140627
		if (!Utils.isStringEmpty(iAnkDat)) {  			
			if (iAnkDat.contains(".") && iAnkDat.length() == 10) {				
				date = iAnkDat.substring(6, 10) + iAnkDat.substring(3, 5) + iAnkDat.substring(0, 2);				
			}
		}
		return date;
	}
}

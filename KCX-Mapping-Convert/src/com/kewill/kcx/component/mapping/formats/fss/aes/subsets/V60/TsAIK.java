package com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import java.util.ArrayList;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Modul        :   AIK
 * Teilsatz     :   TsAIK.java
 * Erstellt     :   13.06.2006
 * Beschreibung :   Kopfsatz zur Qualifizierung AIK
 * 				: 	neue Felder natlfd und natsum
 * 					werden aber nur dann angelegt wenn sie gefüllt sind,
 * 					ansonsten hat der TS die alte Struktur!
 *
 * 04.09.2008   : 	Version 6 Miro Houdek
 *
 */


public class TsAIK extends Teilsatz {

    public ArrayList avsList    = new ArrayList();
    public ArrayList aibList    = new ArrayList();


    private String tsTyp     = "";     // Ts-Schlüssel
    private String beznr     = "";     // Bezugsnummer
    private String lstinf    = "";     // Abschlusskennzeichen
    private String kztyd     = "";     // Verschlüsse sind Tydenseals
    private String kzstock   = "";     // Verwendung des hinterlegten Tydensealstock
    private String anzve     = "";     // Anzahl verwendeter Verschlüsse
    private String natlfd    = "";     // laufende Nummer der Nachricht
    private String natsum    = "";     // Gesamtanzahl aller AIF-Nachrichten


    public TsAIK() {
        tsTyp = "AIK";
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
	           lstinf        = fields[2];
	           if (size < 4 ) return;
	           kztyd         = fields[3];
	           if (size < 5 ) return;
	           kzstock       = fields[4];
	           if (size < 6 ) return;
	           anzve         = fields[5];
	           if (size < 7 ) return;
	           natlfd        = fields[6];
	           if (size < 8 ) return;
	           natsum        = fields[7];
	}

    public String teilsatzBilden() {
        StringBuffer buff = new StringBuffer();

        buff.append(tsTyp);
        buff.append(trenner);
        buff.append(beznr);
        buff.append(trenner);
        buff.append(lstinf);
        buff.append(trenner);
        buff.append(kztyd);
        buff.append(trenner);
        buff.append(kzstock);
        buff.append(trenner);
        buff.append(anzve);
        buff.append(trenner);
// 		Beginn 18.06.2008
//      Teilsatz nur verlängern wenn die Felder in dieser Zwischenversion auch kommen
//		oder eins von beiden (was aber keinen Sinn macht)
//      sonst Teilsatz im alten Format erzeugen nach Absprache mit AL
        if(!natlfd.equals("") || !natsum.equals("")) {
            buff.append(natlfd);
            buff.append(trenner);

            buff.append(natsum);
            buff.append(trenner);
        }
//      Ende 18.06.2008


        return new String(buff);
    }


    public void addAvs(TsAVS avs) {

        avsList.add(avs);
    }

    public void addAib(TsAIB aib) {

        aibList.add(aib);
    }

	public ArrayList getAvsList() {
		return avsList;
	}

	public void setAvsList(ArrayList avsList) {
		this.avsList = avsList;
	}

	public ArrayList getAibList() {
		return aibList;
	}

	public void setAibList(ArrayList aibList) {
		this.aibList = aibList;
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

	public String getLstinf() {
		return lstinf;
	
	}

	public void setLstinf(String lstinf) {
		this.lstinf = Utils.checkNull(lstinf);
	}

	public String getKztyd() {
		return kztyd;
	
	}

	public void setKztyd(String kztyd) {
		this.kztyd = Utils.checkNull(kztyd);
	}

	public String getKzstock() {
		return kzstock;
	
	}

	public void setKzstock(String kzstock) {
		this.kzstock = Utils.checkNull(kzstock);
	}

	public String getAnzve() {
		return anzve;
	
	}

	public void setAnzve(String anzve) {
		this.anzve = Utils.checkNull(anzve);
	}

	public String getNatlfd() {
		return natlfd;
	
	}

	public void setNatlfd(String natlfd) {
		this.natlfd = Utils.checkNull(natlfd);
	}

	public String getNatsum() {
		return natsum;
	
	}

	public void setNatsum(String natsum) {
		this.natsum = Utils.checkNull(natsum);
	}
	
	public boolean isEmpty() {
		//Utils.isStringEmpty(beznr)  &&
		if ( Utils.isStringEmpty(kztyd) && Utils.isStringEmpty(kzstock) 
			&& Utils.isStringEmpty(anzve) && Utils.isStringEmpty(natlfd) 		  
		    && Utils.isStringEmpty(natsum))	
			return true;
		else
			return false;
	} 
	
}

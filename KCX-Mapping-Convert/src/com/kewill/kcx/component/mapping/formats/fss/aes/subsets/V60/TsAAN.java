package com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60;
import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Modul        :   AAN
 * Teilsatz     :   TsAAN.java
 * Erstellt     :   03.09.2006
 * Beschreibung :   Gestellungsanzeige AAN
 *
 * @author          Miro Houdek
 *
 */

public class TsAAN extends Teilsatz {

    private String tsTyp     = "";       // Ts-Schlüssel
    private String beznr     = "";       // Bezugsnummer
    private String gstdat    = "";       // Datum der Gestellung
    private String spdsb     = "";       // Name des Sachbearbeiter
    private String spdfax    = "";       // Faxnummer des SB
    private String spdtel    = "";       // Telefonnummer des SB
    private String sb        = "";       // Kennung des Sachbearbeiters
    private String spmail    = "";       // EMail-Adresse des Sachbearbeiters

    public TsAAN() {
        tsTyp = "AAN";
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
           gstdat        = fields[2];
           if (size < 4 ) return;
           spdsb         = fields[3];
           if (size < 5 ) return;
           spdfax        = fields[4];
           if (size < 6 ) return;
           spdtel        = fields[5];
           if (size < 7 ) return;
           sb            = fields[6];
           if (size < 8 ) return;
           spmail        = fields[7];
      }

    public String teilsatzBilden() {
        StringBuffer buff = new StringBuffer();

        buff.append(tsTyp);
        buff.append(trenner);
        buff.append(beznr);
        buff.append(trenner);
        buff.append(gstdat);
        buff.append(trenner);
        buff.append(spdsb);
        buff.append(trenner);
        buff.append(spdfax);
        buff.append(trenner);
        buff.append(spdtel);
        buff.append(trenner);
        buff.append(sb);
        buff.append(trenner);
        buff.append(spmail);
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

	public String getGstdat() {
		return gstdat;
	
	}

	public void setGstdat(String gstdat) {
		this.gstdat = Utils.checkNull(gstdat);
	}

	public String getSpdsb() {
		return spdsb;
	
	}

	public void setSpdsb(String spdsb) {
		this.spdsb = Utils.checkNull(spdsb);
	}

	public String getSpdfax() {
		return spdfax;
	
	}

	public void setSpdfax(String spdfax) {
		this.spdfax = Utils.checkNull(spdfax);
	}

	public String getSpdtel() {
		return spdtel;
	
	}

	public void setSpdtel(String spdtel) {
		this.spdtel = Utils.checkNull(spdtel);
	}

	public String getSb() {
		return sb;
	
	}

	public void setSb(String sb) {
		this.sb = Utils.checkNull(sb);
	}

	public String getSpmail() {
		return spmail;
	
	}

	public void setSpmail(String spmail) {
		this.spmail = Utils.checkNull(spmail);
	}
	
	public boolean isEmpty() {
		//Utils.isStringEmpty(beznr)  &&
		if ( Utils.isStringEmpty(gstdat) && Utils.isStringEmpty(spdsb) 
				&& Utils.isStringEmpty(spdfax) && Utils.isStringEmpty(spdtel) 		  
		  && Utils.isStringEmpty(sb) && Utils.isStringEmpty(spmail))	
			return true;
		else
			return false;
	} 
	
}

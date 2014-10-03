package com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V70;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		:	Export/aes
 * Created		:	25.07.2012
 * Description	:	Passive Veredelung.
 *
 * @author 	iwaniuk
 * @version 2.1.00 (Zabis V70)
 */

public class TsAPV extends Teilsatz {

    
    private String beznr     = "";      // Bezugsnummer
    private String bewpv     = "";      // Bewilligung zur passiven Veredelung
    private String bewa7     = "";      // Bewilligung zum Anschreibeverfahren in der passiven Veredelung
    private String eindat    = "";      // Datum der Wiedereinfuhr
    private String kzstau    = "";      // Kennzeichen Standardaustausch   

    public TsAPV() {
    	tsTyp = "APV";
    }

    public void setFields(String[] fields) {    
    	int size = fields.length;
		
    	if (size < 1)  { return; }		
    	   tsTyp   = fields[0];
		if (size < 2)  { return; }	
           beznr   = fields[1];
        if (size < 3)  { return; }
           bewpv   = fields[2];
        if (size < 4)  { return; }
           bewa7   = fields[3];
        if (size < 5)  { return; }
           eindat  = fields[4];
        if (size < 6)  { return; }
           kzstau  = fields[5];             
      }

    public String teilsatzBilden() {
        StringBuffer buff = new StringBuffer();

        buff.append(tsTyp);
        buff.append(trenner);
        buff.append(beznr);
        buff.append(trenner);
        buff.append(bewpv);
        buff.append(trenner);
        buff.append(bewa7);
        buff.append(trenner);
        buff.append(eindat);
        buff.append(trenner);
        buff.append(kzstau);
        buff.append(trenner);         

        return new String(buff);
    }

	public String getTsTyp() {
		return tsTyp;	
	}
	public void setTsTyp(String tsBewa7) {
		this.tsTyp = Utils.checkNull(tsBewa7);
	}

	public String getBeznr() {
		return beznr;	
	}
	public void setBeznr(String beznr) {
		this.beznr = Utils.checkNull(beznr);
	}

	public String getBewpv() {
		return bewpv;	
	}
	public void setBewpv(String bewpv) {
		this.bewpv = Utils.checkNull(bewpv);
	}

	public String getBewa7() {
		return bewa7;	
	}
	public void setBewa7(String bewa7) {
		this.bewa7 = Utils.checkNull(bewa7);
	}
	
	public String getEindat() {
		return eindat;	
	}
	public void setEindat(String eindat) {
		this.eindat = Utils.checkNull(eindat);
	}

	public String getKzstau() {
		return kzstau;	
	}
	public void setKzstau(String kzstau) {
		this.kzstau = Utils.checkNull(kzstau);
	}

	public boolean isEmpty() {		
		return (Utils.isStringEmpty(bewpv)  && Utils.isStringEmpty(bewa7) &&
		   Utils.isStringEmpty(eindat) && Utils.isStringEmpty(kzstau));			
	}
}

package com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: TsVFI<br>	
 * Created		: 02.09.2010<br>
 * Description	: VFI segment.
 * 
 * @author Edwin Bautista
 * @version 6.2.00
 */
public class TsVFI extends Teilsatz {

    private String beznr 	= "";       //Bezugsnummer
    private String regnr 	= "";       //MRN
    private String arbnr 	= "";       //Arbeitsnummer
    private String regdat 	= "";       //Registrierungsdatum
    private String natyp 	= "";		//technischer
    
    public TsVFI() {
        tsTyp = "VFI";
    }

    public void setFields(String[] fields) {
		int size = fields.length;
		
		if (size < 1) { return; }
        tsTyp 	= fields[0];
        if (size < 2) { return; }
        beznr	= fields[1];
        if (size < 3) { return; }
        regnr   = fields[2];
        if (size < 4) { return; }
        arbnr   = fields[3];
        if (size < 5) { return; }
        regdat   = fields[4];
        if (size < 6) { return; }
        natyp	= fields[5];
    }

    public String teilsatzBilden() {
        StringBuffer buff = new StringBuffer();

        buff.append(tsTyp);
        buff.append(trenner);
        buff.append(beznr);
        buff.append(trenner);
        buff.append(regnr);
        buff.append(trenner);
        buff.append(arbnr);
        buff.append(trenner);
        buff.append(regdat);
        buff.append(trenner);
        buff.append(natyp);
        buff.append(trenner);
        
        return new String(buff);
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

	public String getArbnr() {
		return arbnr;
	}

	public void setArbnr(String arbnr) {
		this.arbnr = Utils.checkNull(arbnr);
	}

	public String getRegdat() {
		return regdat;
	}

	public void setRegdat(String regdat) {
		this.regdat = Utils.checkNull(regdat);
	}

	public String getNatyp() {
		return natyp;
	}

	public void setNatyp(String natyp) {
		this.natyp = Utils.checkNull(natyp);
	}
	
	public boolean isEmpty() {
		return (Utils.isStringEmpty(natyp) && Utils.isStringEmpty(regnr) && 
				Utils.isStringEmpty(arbnr) && Utils.isStringEmpty(regdat));				
	}
}



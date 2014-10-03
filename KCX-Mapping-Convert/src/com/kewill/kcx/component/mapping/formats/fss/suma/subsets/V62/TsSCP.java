package com.kewill.kcx.component.mapping.formats.fss.suma.subsets.V62;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;


/**
 * Module		:	TsSCP
 * Created		:	08.02.2013
 * Description	:   Verarbeitungsergebnisse.
 *        			Zabis Version 06.02  
 *
 * @author iwaniuk
 * @version 1.0.00
 */

public class TsSCP  extends Teilsatz {

	private String beznr	= "";			
	private String regnr	= "";			
	private String posnr	= "";	
	private String kzawb	= "";	
	private String awbzzz	= "";	
	private String vrwznr	= "";	
	private String fehgew	= "";	
	private String fehnr	= "";
	private String fehtxt	= "";  
		
    public TsSCP() {
        tsTyp = "SCP";
    }

    public void setFields(String[] fields) {  
		int size = fields.length;
		//String ausgabe = "FSS: "+fields[0]+" size = "+ size;
		//Utils.log( ausgabe);
				
		if (size < 1) { return; }		
        tsTyp = fields[0];
        if (size < 2) { return; }		
        beznr = fields[1];
        if (size < 3) { return; }	
        regnr = fields[2];
        if (size < 4) { return; }	
        posnr = fields[3];
        if (size < 5) { return; }	
        kzawb = fields[4];
        if (size < 6) { return; }	
        awbzzz = fields[5];
        if (size < 7) { return; }	
        vrwznr = fields[6];
        if (size < 8) { return; }	
        fehgew = fields[7];
        if (size < 9) { return; }	
        fehnr = fields[8];
        if (size < 10) { return; }	
        fehtxt = fields[9];
       
      }

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
        buff.append(kzawb);
        buff.append(trenner);
        buff.append(awbzzz);
        buff.append(trenner);
        buff.append(vrwznr);
        buff.append(trenner);
        buff.append(fehgew);
        buff.append(trenner);
        buff.append(fehnr);
        buff.append(trenner);
        buff.append(fehtxt);
        buff.append(trenner);

        return new String(buff);
    }

	public boolean isEmpty() {
		return (Utils.isStringEmpty(regnr) && Utils.isStringEmpty(kzawb) &&
			Utils.isStringEmpty(awbzzz) && Utils.isStringEmpty(vrwznr) &&
			Utils.isStringEmpty(fehgew) && Utils.isStringEmpty(fehnr));
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

	public String getRegnr() {
		return regnr;
	}
	public void setRegnr(String idfltnum) {
		this.regnr = Utils.checkNull(idfltnum);
	}

	public String getPosnr() {
		return posnr;
	}
	public void setPosnr(String idfltblo) {
		this.posnr = Utils.checkNull(idfltblo);
	}

	public String getKzawb() {
		return kzawb;
	}
	public void setKzawb(String kzawb) {
		this.kzawb = Utils.checkNull(kzawb);
	}

	public String getAwbzzz() {
		return awbzzz;
	}
	public void setAwbzzz(String awbzzz) {
		this.awbzzz = Utils.checkNull(awbzzz);
	}

	public String getVrwznr() {
		return vrwznr;
	}
	public void setVrwznr(String vrwznr) {
		this.vrwznr = Utils.checkNull(vrwznr);
	}

	public String getFehgew() {
		return fehgew;
	}
	public void setFehgew(String fehgew) {
		this.fehgew = Utils.checkNull(fehgew);
	}

	public String getFehnr() {
		return fehnr;
	}
	public void setFehnr(String fehnr) {
		this.fehnr = Utils.checkNull(fehnr);
	}

	public String getFehtxt() {
		return fehtxt;
	}
	public void setFehtxt(String fehtxt) {
		this.fehtxt = Utils.checkNull(fehtxt);
	}

	
}



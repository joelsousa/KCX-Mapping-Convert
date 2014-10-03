package com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V70;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		:	Export/aes
 * Created		:	27.07.2012
 * Description	:	first Headline.  
 *
 * @author 	iwaniuk
 * @version 2.1.00 (Zabis V70)
 */

public class TsDAT extends Teilsatz {
    
	private String beznr  = "";      // Bezugsnummer 
    private String kuatnr = "";      // externe Kundenauftragsnummer
    private String mrn    = "";      // Movement-Reference-Number
    private String kzart  = "";      // Ausgangsbenachrichtigung 0 = eingehend 1= Überlassung 2 = Beendigung
    private String expdst = "";      // Ausfuhrdienststelle Einheitspapier Feld A
    private String eamdst = "";      // Dienststelle der EAM
    private String quelkz = "";
    private String sb     = "";
    private String zlbez  = "";
    private String avbez  = "";
    private String emebez = "";
    
    public TsDAT() {
        tsTyp = "DAT";
    }
    
    public String teilsatzBilden() {
        StringBuffer buff = new StringBuffer();
          
        buff.append(tsTyp);
        buff.append(trenner);        
        buff.append(beznr);
        buff.append(trenner);	
        buff.append(kuatnr);
        buff.append(trenner);	
        buff.append(mrn);
        buff.append(trenner);	
        buff.append(kzart);
        buff.append(trenner);	
        buff.append(expdst);
        buff.append(trenner);	
        buff.append(eamdst);
        buff.append(trenner);
        buff.append(quelkz);
        buff.append(trenner);
        buff.append(sb);
        buff.append(trenner);
        buff.append(zlbez);
        buff.append(trenner);       
        buff.append(avbez);
        buff.append(trenner);
        buff.append(emebez);
        buff.append(trenner);
       
    	return buff.toString();
    }

	
	public void setFields(String[] fields) {
		int size = fields.length;
		
		if (size < 1)  { return; }		
        tsTyp   = fields[0];
        if (size < 2)  { return; }	
        beznr   = fields[1];
        if (size < 3)  { return; }	
        kuatnr  = fields[2];
        if (size < 4)  { return; }	
        mrn		= fields[3];
        if (size < 5)  { return; }	
        kzart   = fields[4];
        if (size < 6)  { return; }	
        expdst  = fields[5];
        if (size < 7)  { return; }	
        eamdst	= fields[6];        
        if (size < 8)  { return; }	
        quelkz	= fields[7];   
        if (size < 9)  { return; }	
        sb	    = fields[8];   
        if (size < 10) { return; }	
        zlbez	= fields[9];   
        if (size < 11) { return; }	
        avbez	= fields[10];   
        if (size < 12) { return; }	
        emebez	= fields[11];   
	}

	public String getBeznr() {
		return beznr;
	}
	public void setBeznr(String beznr) {
		this.beznr = Utils.checkNull(beznr);
	}

	public String getKuatnr() {
		return kuatnr;
	}
	public void setKuatnr(String kuatnr) {
		this.kuatnr = Utils.checkNull(kuatnr);
	}

	public String getMrn() {
		return mrn;
	}
	public void setMrn(String mrn) {
		this.mrn = Utils.checkNull(mrn);
	}
	
	public String getKzart() {
		return kzart;
	}
	public void setKzart(String kzart) {
		this.kzart = Utils.checkNull(kzart);
	}

	public String getExpdst() {
		return expdst;
	}
	public void setExpdst(String expdst) {
		this.expdst = Utils.checkNull(expdst);
	}

	public String getEamdst() {
		return eamdst;
	}
	public void setEamdst(String eamdst) {
		this.eamdst = Utils.checkNull(eamdst);
	}	
	
	public String getQuelkz() {
		return quelkz;
	}
	public void setQuelkz(String value) {
		this.quelkz = Utils.checkNull(value);
	}
	
	public String getSb() {
		return sb;
	}
	public void setSb(String value) {
		this.sb = Utils.checkNull(value);
	}
	
	public String getZlbez() {
		return zlbez;
	}
	public void setZlbez(String value) {
		this.zlbez = Utils.checkNull(value);
	}
	
	public String getAvbez() {
		return avbez;
	}
	public void setAvbez(String value) {
		this.avbez = Utils.checkNull(value);
	}
	
	public String getEmebez() {
		return emebez;
	}
	public void setEmebez(String value) {
		this.emebez = Utils.checkNull(value);
	}
	
	
	public boolean isEmpty() {		
		return Utils.isStringEmpty(kuatnr) && Utils.isStringEmpty(mrn) && Utils.isStringEmpty(kzart) && 
		  Utils.isStringEmpty(expdst) && Utils.isStringEmpty(eamdst) && Utils.isStringEmpty(sb) &&
		  Utils.isStringEmpty(zlbez) && Utils.isStringEmpty(avbez) && Utils.isStringEmpty(emebez);		 			
	}	
}

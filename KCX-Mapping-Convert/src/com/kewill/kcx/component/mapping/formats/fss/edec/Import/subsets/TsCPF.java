package com.kewill.kcx.component.mapping.formats.fss.edec.Import.subsets;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.MsgExpDatPos;
import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: EDEC Import 70<br>
 * Created		: 05.11.2012<br>
 * Description  : FSS Definition of Frachtkostenberechnung zur Position. 
 * 
 * @author iwaniuk
 * @version 7.0.00
 */

public class TsCPF extends Teilsatz {

    private String refnr = "";       	//
    private String posnr = "";			// Positionsnummer
    private String npbt  = "";      	    
    private String npwg  = "";      	     
    private String npchf = "";      	
    private String fkbt  = "";      	   
    private String fkprz = "";      	 
    private String fkwg  = "";      	
    private String fkchf = "";      	 
    private String vsbt  = "";      
    private String vswg  = "";      	 
    private String vschf = "";      	 
    private String ksbt  = "";      	 
    private String kswg  = "";      	 
    private String kschf = "";      	 
    private String kmbt  = "";      	 
    private String kmwg  = "";      	 
    private String kmchf = "";     
    
    public TsCPF() {
        tsTyp = "CPF";
    }
    public void setFields(String[] fields) {
    
		int size = fields.length;
		//String ausgabe = "FSS: "+fields[0]+" size = "+ size;
		//Utils.log( ausgabe);
		
		if (size < 1) { return;	}
        tsTyp = fields[0];
        if (size < 2) { return;	}       
        refnr = fields[1];
        if (size < 3) { return;	}        
        posnr = fields[2];
        if (size < 4) { return;	}        
        npbt = fields[3];        
        if (size < 5) { return;	}       
        npwg = fields[4];        
        if (size < 6) { return;	}        
        npchf = fields[5];        
        if (size < 7) { return;	}       
        fkbt = fields[6];        
        if (size < 8) { return;	}       
        fkprz = fields[7];        
        if (size < 9) { return;	}        
        fkwg = fields[8];        
        if (size < 10) { return;	}
        fkchf = fields[9];
        if (size < 11) { return;	}
        vsbt = fields[10];
        if (size < 12) { return;	}       
        vswg = fields[11];
        if (size < 13) { return;	}        
        vschf = fields[12];
        if (size < 14) { return;	}        
        ksbt = fields[13];        
        if (size < 15) { return;	}       
        kswg = fields[14];        
        if (size < 16) { return;	}        
        kschf = fields[15];        
        if (size < 17) { return;	}       
        kmbt = fields[16];        
        if (size < 18) { return;	}       
        kmwg = fields[17];        
        if (size < 19) { return;	}       
        kmchf = fields[18];    
    }

    public String teilsatzBilden() {
        StringBuffer buff = new StringBuffer();

        buff.append(tsTyp);
        buff.append(trenner);
        buff.append(refnr);
        buff.append(trenner);
        buff.append(posnr);
        buff.append(trenner);
        buff.append(npbt);
        buff.append(trenner);
        buff.append(npwg);
        buff.append(trenner);
        buff.append(npchf);
        buff.append(trenner);
        buff.append(fkbt);
        buff.append(trenner);
        buff.append(fkprz);
        buff.append(trenner);
        buff.append(fkwg);
        buff.append(trenner);
        buff.append(fkchf);
        buff.append(trenner);
        buff.append(vsbt);
        buff.append(trenner);
        buff.append(vswg);
        buff.append(trenner);
        buff.append(vschf);
        buff.append(trenner);
        buff.append(ksbt);
        buff.append(trenner);
        buff.append(kswg);
        buff.append(trenner);
        buff.append(ksbt);
        buff.append(trenner);
        buff.append(kmbt);
        buff.append(trenner);
        buff.append(kmwg);
        buff.append(trenner);
        buff.append(kmchf);
        buff.append(trenner);
        
        return new String(buff);
      }


	public void setCnzSubset(MsgExpDatPos msgExpDatPos) {
		
	}	
	
	public boolean isEmpty() {
	    if (Utils.isStringEmpty(npbt) &&
	    	Utils.isStringEmpty(npwg) &&
	    	Utils.isStringEmpty(npchf) &&
	    	Utils.isStringEmpty(fkbt) &&
	    	Utils.isStringEmpty(fkprz) &&
	    	Utils.isStringEmpty(fkwg)) {	   
	    	return true;
	    } else {
			return false;
	    }
	}
	
	public String getRefnr() {
		return refnr;	
	}
	
	public void setRefnr(String refnr) {
		this.refnr = Utils.checkNull(refnr);
	}
	
	public String getPosnr() {
		return posnr;	
	}
	public void setPosnr(String posnr) {
		this.posnr = Utils.checkNull(posnr);
	}
	
	public String getNpbt() {
		return npbt;	
	}
	public void setNpbt(String npbt) {
		this.npbt = Utils.checkNull(npbt);
	}
	
	public String getNpwg() {
		return npwg;	
	}
	
	public void setNpwg(String npwg) {
		this.npwg = Utils.checkNull(npwg);
	}
	
	public String getNpchf() {
		return npchf;	
	}
	
	public void setNpchf(String npchf) {
		this.npchf = Utils.checkNull(npchf);
	}
	
	public String getFkbt() {
		return fkbt;
	
	}
	public void setFkbt(String fkbt) {
		this.fkbt = Utils.checkNull(fkbt);
	}
	
	public String getFkprz() {
		return fkprz;	
	}
	public void setFkprz(String fkprz) {
		this.fkprz = Utils.checkNull(fkprz);
	}
	
	public String getFkwg() {
		return fkwg;	
	}
	public void setFkwg(String fkwg) {
		this.fkwg = Utils.checkNull(fkwg);
	}
	
	public String getFkchf() {
		return fkchf;	
	}
	public void setFkchf(String value) {
		this.fkchf = Utils.checkNull(value);
	}
	
	public String getVsbt() {
		return vsbt;	
	}
	public void setVsbt(String value) {
		this.vsbt = Utils.checkNull(value);
	}
	
	public String getVswg() {
		return vswg;	
	}
	public void setVswg(String value) {
		this.vswg = Utils.checkNull(value);
	}
	
	public String getVschf() {
		return vschf;	
	}
	public void setVschf(String value) {
		this.vschf = Utils.checkNull(value);
	}
	
	public String getKsbt() {
		return ksbt;	
	}
	public void setKsbt(String value) {
		this.ksbt = Utils.checkNull(value);
	}
	
	public String getKswg() {
		return kswg;	
	}
	public void setKswg(String value) {
		this.kswg = Utils.checkNull(value);
	}
	
	public String getKschf() {
		return kschf;	
	}
	public void setKschf(String value) {
		this.kschf = Utils.checkNull(value);
	}
	
	public String getKmbt() {
		return kmbt;	
	}
	public void setKmbt(String value) {
		this.kmbt = Utils.checkNull(value);
	}
	
	public String getKmwg() {
		return kmwg;	
	}
	public void setKmwg(String value) {
		this.kmwg = Utils.checkNull(value);
	}
	
	public String getKmchf() {
		return kmchf;	
	}
	public void setKmchf(String value) {
		this.kmchf = Utils.checkNull(value);
	}
}



package com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V70;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.countries.common.TIN;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.MsgExpEntPos;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.Statistic;

/**
* Module		:	Export/aes
* Created		:	25.07.2012
* Description	:	Dokumentenkopf.
*
* @author 	iwaniuk
* @version  2.1.00 (Zabis V70)   
*/

public class TsADO  extends Teilsatz {
	
    private String beznr  = "";       
    private String pfomnr = "";      
    private String fomnr  = "";
    private String fomtyp = "";      
    private String ldve   = "";       
    private String ldbe   = "";       
    private String bfvkzg = "";       
    private String bfartg = "";      
    private String bfkzg  = "";      
    private String bfnatg = "";    
    private String expdst = "";       
    private String ldvetx  = "";     
    private String docort = "";       
    private String docdat = "";      

    public TsADO() {
        tsTyp = "ADO";
    } 

    public void setFields(String[] fields) {
		int size = fields.length;
		
		if (size < 1) { return; }
		tsTyp   = fields[0];
		if (size < 2) { return; }
		beznr   = fields[1];
    	if (size < 3)  { return; }
    	pfomnr   = fields[2];   
    	if (size < 4)  { return; }
    	fomnr   = fields[3];
        if (size < 5)  { return; }
        fomtyp   = fields[4];
        if (size < 6)  { return; }
        ldve     = fields[5];
        if (size < 7)  { return; }
        ldbe  = fields[6];
        if (size < 8)  { return; }
        bfvkzg  = fields[7];
        if (size < 9)  { return; }
        bfartg  = fields[8];
        if (size < 10)  { return; }
        bfkzg  = fields[9];
        if (size < 11) { return; }
        bfnatg   = fields[10];
        if (size < 12) { return; }
        expdst  = fields[11];
        if (size < 13)  { return; }
        ldvetx    = fields[12];
        if (size < 14)  { return; }
        docort  = fields[13];
        if (size < 15)  { return; }
        docdat  = fields[14];                                                     
    }

	public String teilsatzBilden() {
		
        StringBuffer buff = new StringBuffer();
        
        buff = buff.append(tsTyp);
        buff = buff.append(trenner);
        buff = buff.append(beznr);
        buff = buff.append(trenner);
        buff = buff.append(pfomnr);
        buff = buff.append(trenner);
        buff = buff.append(fomnr);
        buff = buff.append(trenner);
        buff = buff.append(fomtyp);
        buff = buff.append(trenner);
        buff = buff.append(ldve);
        buff = buff.append(trenner);
        buff = buff.append(ldbe);
        buff = buff.append(trenner);
        buff = buff.append(bfvkzg);
        buff = buff.append(trenner);
        buff = buff.append(bfartg);
        buff = buff.append(trenner);
        buff = buff.append(bfkzg);
        buff = buff.append(trenner);
        buff = buff.append(bfnatg);
        buff = buff.append(trenner);
        buff = buff.append(expdst);
        buff = buff.append(trenner);
        buff = buff.append(ldvetx);
        buff = buff.append(trenner);
        buff = buff.append(docort);
        buff = buff.append(trenner);
        buff = buff.append(docdat);
        buff = buff.append(trenner);       
   
        return new String(buff);
	}        

	public String getBeznr() {
		return beznr;
	}
	public void setBeznr(String beznr) {
		this.beznr = Utils.checkNull(beznr);
	}

	public String getPfomnr() {
		return pfomnr;
	}
	public void setPfomnr(String pfomnr) {
		this.pfomnr = Utils.checkNull(pfomnr);
	}

	public String getFomnr() {
		return fomnr;
	}
	public void setFomnr(String pfomnr) {
		this.fomnr = Utils.checkNull(pfomnr);
	}
	
	public String getFomtyp() {
		return fomtyp;
	}
	public void setFomtyp(String fomtyp) {
		this.fomtyp = Utils.checkNull(fomtyp);
	}

	public String getLdve() {
		return ldve;
	}
	public void setLdve(String value) {
		this.ldve = Utils.checkNull(value);
	}

	public String getLdbe() {
		return ldbe;
	}
	public void setLdbe(String ldbe) {
		this.ldbe = Utils.checkNull(ldbe);
	}

	public String getBfvkzg() {
		return bfvkzg;
	}
	public void setBfvkzg(String bfvkzg) {
		this.bfvkzg = Utils.checkNull(bfvkzg);
	}

	public String getBfartg() {
		return bfartg;
	}
	public void setBfartg(String bfartg) {
		this.bfartg = Utils.checkNull(bfartg);
	}

	public String getBfkzg() {
		return bfkzg;
	}
	public void setBfkzg(String bfkzg) {
		this.bfkzg = Utils.checkNull(bfkzg);
	}

	public String getBfnatg() {
		return bfnatg;
	}
	public void setBfnatg(String bfnatg) {
		this.bfnatg = Utils.checkNull(bfnatg);
	}

	public String getExpdst() {
		return expdst;
	}
	public void setExpdst(String expdst) {
		this.expdst = Utils.checkNull(expdst);
	}

	public String getLdvetx() {
		return ldvetx;
	}
	public void setLdvetx(String ldvetx) {
		this.ldvetx = Utils.checkNull(ldvetx);
	}

	public String getDocort() {
		return docort;
	}
	public void setDocort(String value) {
		this.docort = Utils.checkNull(value);
	}

	public String getDocdat() {
		return docdat;
	}
	public void setDocdat(String docdat) {
		this.docdat = Utils.checkNull(docdat);
	}

	public void setApoSubset(MsgExpEntPos msgExpEntPos, String aBeznr) {
		//TODO-V21 brauche ich es ???
	}

	public boolean isEmpty() {
		return( Utils.isStringEmpty(fomtyp) && Utils.isStringEmpty(pfomnr) && Utils.isStringEmpty(fomnr) &&  
		  Utils.isStringEmpty(ldve) && Utils.isStringEmpty(ldbe) &&
		  Utils.isStringEmpty(bfvkzg) && Utils.isStringEmpty(bfartg) && Utils.isStringEmpty(bfkzg) &&
		  Utils.isStringEmpty(bfnatg) && Utils.isStringEmpty(expdst) && Utils.isStringEmpty(ldvetx) &&
		  Utils.isStringEmpty(docort) && Utils.isStringEmpty(docdat));		 
	}
		
}

   
   
   
    
   
   





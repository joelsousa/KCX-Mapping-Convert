package com.kewill.kcx.component.mapping.formats.fss.edec.Import.subsets;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/**
* Module		: EDEC Import 70<br>
* Created		: 29.10.2012<br>
* Description  : FSS Definition of Position. 
* 
* @author iwaniuk
* @version 7.0.00
*/

public class TsCPS extends Teilsatz {
	private String refnr   = "";      //Referencenumber
	private String posnr   = "";		 //Itemnumber
	private String artnr   = "";		 	
	private String tnr     = "";		 
	private String tnrsch  = "";		
	private String cdhw    = "";		
	private String cdabf   = "";		 
	private String kzunvp  = "";		 
	private String wabesch = "";		 
	private String rohm    = "";		 
	private String eigm    = "";		 
	private String nttgew  = "";		 
	private String stzusm  = "";		 
	private String cdlag   = "";		 
	private String tbkart  = "";		 
	private String cdbwpf  = "";		 
	private String cdnzpf  = "";		 
	private String stwert  = "";		 
	private String mswert  = "";		 
	private String urld    = "";		 
	private String cdprf   = "";	
	private String itembz  = "";		 
	private String itemwt  = "";	
	private String kznett  = "";		 
	private String tazu    = "";	
	private String cdbeg   = "";		 
	private String cdgebw  = "";	
	private String ntrate  = "";		 
	private String zolans  = "";	
	private String cdmwst  = "";		 
	private String injtyp  = "";	
	
	public TsCPS() {
        tsTyp = "CPS";
    }

    public void setFields(String[] fields) {  
		int size = fields.length;
		//String ausgabe = "FSS: "+fields[0]+" size = "+ size;
		//Utils.log( ausgabe);
			
		if (size < 1) { return; }		
        tsTyp = fields[0];
        if (size < 2) { return; }	
        refnr = fields[1];
        if (size < 3) { return; }	
        posnr = fields[2];
        if (size < 4) { return; }	
        artnr = fields[3];
        if (size < 5) { return; }	
        tnr = fields[4];        
        if (size < 6) { return; }	
        tnrsch = fields[5];
        if (size < 7) { return; }	
        cdhw = fields[6];
        if (size < 8) { return; }	
        cdabf = fields[7];
        if (size < 9) { return; }	
        kzunvp = fields[8];
        if (size < 10) { return; }	
        wabesch = fields[9];
        if (size < 11) { return; }	
        rohm = fields[10];
        if (size < 12) { return; }	
        eigm = fields[11];        
        if (size < 13) { return; }	
        nttgew = fields[12];
        if (size < 14) { return; }	
        stzusm = fields[13];
        if (size < 15) { return; }	
        cdlag = fields[14];
        if (size < 16) { return; }	
        tbkart = fields[15];
        if (size < 17) { return; }	
        cdbwpf = fields[16];
        if (size < 18) { return; }	
        cdnzpf = fields[17];
        if (size < 19) { return; }	
        stwert = fields[18];
        if (size < 20) { return; }	
        mswert = fields[19];
        if (size < 21) { return; }	
        urld  = fields[20];
        if (size < 22) { return; }	
        cdprf = fields[21];
        if (size < 23) { return; }	
        itembz = fields[22];
        if (size < 24) { return; }	
        itemwt = fields[23];
        if (size < 25) { return; }	
        kznett  = fields[24];
        if (size < 26) { return; }	
        tazu = fields[25];
        if (size < 27) { return; }	
        cdbeg = fields[26];
        if (size < 28) { return; }	
        cdgebw = fields[27];
        if (size < 29) { return; }	
        ntrate = fields[28];
        if (size < 30) { return; }	
        zolans = fields[29];
        if (size < 31) { return; }	
        cdmwst  = fields[30];
        if (size < 32) { return; }	
        injtyp = fields[31];       
    }

    public String teilsatzBilden() {
        StringBuffer buff = new StringBuffer();

        buff.append(tsTyp);
        buff.append(trenner);
        buff.append(refnr);
        buff.append(trenner);
        buff.append(posnr);
        buff.append(trenner);
        buff.append(artnr);
        buff.append(trenner);
        buff.append(tnr);
        buff.append(trenner);
        buff.append(tnrsch);
        buff.append(trenner);
        buff.append(cdhw);
        buff.append(trenner);
        buff.append(cdabf);
        buff.append(trenner);
        buff.append(kzunvp);
        buff.append(trenner);
        buff.append(wabesch);
        buff.append(trenner);
        buff.append(rohm);
        buff.append(trenner);
        buff.append(eigm);
        buff.append(trenner);
        buff.append(nttgew);
        buff.append(trenner);
        buff.append(stzusm);
        buff.append(trenner);
        buff.append(cdlag);
        buff.append(trenner);
        buff.append(tbkart);
        buff.append(trenner);
        buff.append(cdbwpf);
        buff.append(trenner);
        buff.append(cdnzpf);
        buff.append(trenner);
        buff.append(stwert);
        buff.append(trenner);
        buff.append(mswert);
        buff.append(trenner);
        buff.append(urld);
        buff.append(trenner);
        buff.append(cdprf);
        buff.append(trenner);        
        buff.append(itembz);
        buff.append(trenner);  
        buff.append(itemwt);
        buff.append(trenner); 
        buff.append(kznett);
        buff.append(trenner); 
        buff.append(tazu);
        buff.append(trenner); 
        buff.append(cdbeg);
        buff.append(trenner); 
        buff.append(cdgebw);
        buff.append(trenner); 
        buff.append(ntrate);
        buff.append(trenner); 
        buff.append(zolans);
        buff.append(trenner); 
        buff.append(cdmwst);
        buff.append(trenner); 
        buff.append(injtyp);
        buff.append(trenner); 
        
        return new String(buff);
      }

    public void setRefnr(String refnr) {
		this.refnr = Utils.checkNull(refnr);
	}

	public String getRefnr() {
		return refnr;	
	}

	public String getPosnr() {
		return posnr;	
	}

	public void setPosnr(String posnr) {
		this.posnr = Utils.checkNull(posnr);
	}

	public String getArtnr() {
		return artnr;	
	}

	public void setArtnr(String artnr) {
		this.artnr = Utils.checkNull(artnr);
	}

	public String getTnr() {
		return tnr;	
	}

	public void setTnr(String tnr) {
		this.tnr = Utils.checkNull(tnr);
	}

	public String getTnrsch() {
		return tnrsch;	
	}

	public void setTnrsch(String tnrsch) {
		this.tnrsch = Utils.checkNull(tnrsch);
	}
	
	public void setCdhw(String cdhw) {
		this.cdhw = Utils.checkNull(cdhw);
	}
	public String getCdhw() {
		return cdhw;	
	}
	
	public String getCdabf() {
		return cdabf;	
	}

	public void setCdabf(String cdabf) {
		this.cdabf = Utils.checkNull(cdabf);
	}
	
	public void setKzunvp(String value) {
		this.kzunvp = Utils.checkNull(value);
	}

	public String getKzunvp() {
		return kzunvp;
	
	}
	
	public void setWabesch(String cdnzpf) {
		this.wabesch = Utils.checkNull(cdnzpf);
	}
	
	public String getWabesch() {
		return wabesch;	
	}
	
	public String getRohm() {
		return rohm;	
	}
	public void setRohm(String kzunvp) {
		this.rohm = Utils.checkNull(kzunvp);
	}

	public String getEigm() {
		return eigm;	
	}
	public void setEigm(String wabesch) {
		this.eigm = Utils.checkNull(wabesch);
	}

	public String getNttgew() {
		return nttgew;	
	}

	public void setNttgew(String value) {
		this.nttgew = Utils.checkNull(value);
	}

	public String getStzusm() {
		return stzusm;	
	}

	public void setStzusm(String value) {
		this.stzusm = Utils.checkNull(value);
	}

	public String getCdlag() {
		return cdlag;	
	}

	public void setCdlag(String value) {
		this.cdlag = Utils.checkNull(value);
	}
	

	public String geTtbkart() {
		return tbkart;	
	}

	public void setTbkart(String tbkart) {
		this.tbkart = Utils.checkNull(tbkart);
	}
	
	public String getCdbwpf() {
		return cdbwpf;	
	}

	public void setCdbwpf(String value) {
		this.cdbwpf = Utils.checkNull(value);
	}

	public String getCdnzpf() {
		return cdnzpf;	
	}

	public void setCdnzpf(String value) {
		this.cdnzpf = Utils.checkNull(value);
	}

	public String getStwert() {
		return stwert;	
	}

	public void setStwert(String value) {
		this.stwert = Utils.checkNull(value);
	}

	public String getMswert() {
		return mswert;	
	}

	public void setMswert(String value) {
		this.mswert = Utils.checkNull(value);
	}
	
	public String getUrld() {
		return urld;	
	}

	public void setUrld(String value) {
		this.urld = Utils.checkNull(value);
	}

	public String getCdprf() {
		return cdprf;	
	}

	public void setCdprf(String value) {
		this.cdprf = Utils.checkNull(value);
	}

	public String getItembz() {
		return itembz;	
	}

	public void setItembz(String value) {
		this.itembz = Utils.checkNull(value);
	}

	public String getItemwt() {
		return itemwt;	
	}
	
	public void setItemwt(String value) {
		this.itemwt = Utils.checkNull(value);
	}

	public String getKznett() {
		return kznett;	
	}
	
	public void setKznett(String value) {
		this.kznett = Utils.checkNull(value);
	}

	public String getTazu() {
		return tazu;	
	}
	
	public void setTazu(String value) {
		this.tazu = Utils.checkNull(value);
	}
	
	public String getCdbeg() {
		return cdbeg;	
	}
	
	public void setCdbeg(String value) {
		this.cdbeg = Utils.checkNull(value);
	}
	
	public String getCdgebw() {
		return cdgebw;	
	}
	
	public void setCdgebw(String value) {
		this.cdgebw = Utils.checkNull(value);
	}
	
	public String getNtrate() {
		return ntrate;	
	}
	
	public void setNtrate(String value) {
		this.ntrate = Utils.checkNull(value);
	}
	
	public String getZolans() {
		return zolans;	
	}
	
	public void setZolans(String value) {
		this.zolans = Utils.checkNull(value);
	}
	
	public String getCdmwst() {
		return cdmwst;	
	}
	
	public void setCdmwst(String value) {
		this.cdmwst = Utils.checkNull(value);
	}
	
	public String getInjtyp() {
		return injtyp;	
	}
	
	public void setInjtyp(String value) {
		this.injtyp = Utils.checkNull(value);
	}

	public boolean isEmpty() {
        return Utils.isStringEmpty(artnr) &&
            Utils.isStringEmpty(tnr) &&
		    Utils.isStringEmpty(tnrsch) &&
		    Utils.isStringEmpty(cdhw) &&
       		Utils.isStringEmpty(cdabf) &&
       		Utils.isStringEmpty(kzunvp) &&
       		Utils.isStringEmpty(wabesch) &&
       		   Utils.isStringEmpty(rohm) &&
       		   Utils.isStringEmpty(eigm) &&       		  
       		   Utils.isStringEmpty(nttgew) &&
       		   Utils.isStringEmpty(stzusm) &&       		   
       		   Utils.isStringEmpty(cdlag) &&
       		   Utils.isStringEmpty(tbkart) &&
       		   Utils.isStringEmpty(cdbwpf) &&
       		   Utils.isStringEmpty(cdnzpf) &&
       		   Utils.isStringEmpty(stwert) &&
       		   Utils.isStringEmpty(mswert) &&       		   
       		   Utils.isStringEmpty(urld);        	
	}

	
}	

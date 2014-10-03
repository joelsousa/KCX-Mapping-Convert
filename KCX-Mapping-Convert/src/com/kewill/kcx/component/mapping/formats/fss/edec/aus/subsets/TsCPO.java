package com.kewill.kcx.component.mapping.formats.fss.edec.aus.subsets;

/**
 * Funktion     :   EDEC Export TsCPO Subset Position
 * Nachricht    :   
 * Erstellt     :   21.10.2008
 * Beschreibung :   TsCPO
 *  
 * @author          Alfred Krzoska
 * 
 * Changes 
 * -----------
 * Author      : krzoska 
 * Date        : 15.03.2010
 * Label       : AK20100315 
 * Description : last character of wbsch1 was deleted (wrong indexing )  
 *
 * Author      : iwaniuk 
 * Date        : 24.06.2010
 * Label       :  
 * Description : in the method setFields (is not in use) was one Element double
 * Description : new member:  cdungf //TODO it is really in use??? 
 */

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

public class TsCPO extends Teilsatz {
	private String beznr  = "";      //Referencenumber
	private String posnr  = "";		 //Itemnumber
	private String cdhw   = "";		 //AdditionalCode
	private String cdabf2 = "";		 //ClearanceCode2
	private String cdabf  = "";		 //ClearanceCode
	private String cdabfa = "";		 //ClearanceTypeCode
	private String cdnzpf = "";		 //RestrictionCode
	private String tnr    = "";		 //CommodityCodeKN8
	private String tnrsch = "";		 //CommodityCode NationalAddition
	private String wbsch1 = "";		 //GoodsDescription
	private String wbsch2 = "";		 //GoodsDescription
	private String rohm   = "";		 //GrossMass
	private String eigm	  = "";		 //NetMass
	private String zusm   = "";		 //StatisticalQuantity
	private String stwt   = "";		 //StatisticalValue
	private String sgicd  = "";		 //SpecialGoodsInformation code
	private String sgimng = "";		 //SpecialGoodsInformation Amount
	private String cdmeld = "";		 //
	private String zusang = "";		 //SpecialMentions
	private String cdexeu = "";		 //ECExportIdentifier
	private String cdexld = "";		 //ExportCountry
	private String verm   = "";		 //Remark
	private String cdungf = "";		 //UN-Gefahrencode, wird nur bei EDEC Export übernommen
	//AK20110314
	private String rpwae  = "";		 //Währung zum Rechnungspreis
	private String rpreis = "";		 //Rechnungspreis in Fremdwährung
	
	public TsCPO() {
        tsTyp = "CPO";
    }

    public void setFields(String[] fields)
    {
		int size = fields.length;
		String ausgabe = "FSS: "+fields[0]+" size = "+ size;
		//Utils.log( ausgabe);
			
		if (size < 1) { return; }		
        	tsTyp = fields[0];
        if (size < 2) { return; }	
        	beznr = fields[1];
        if (size < 3) { return; }	
        	posnr = fields[2];
        if (size < 4) { return; }	
        	cdhw = fields[3];
        if (size < 5) { return; }	
        	cdabf2 = fields[4];        
        if (size < 6) { return; }	
        	cdabf = fields[5];
        if (size < 7) { return; }	
        	cdabfa = fields[6];
        if (size < 8) { return; }	
        	cdnzpf = fields[7];
        if (size < 9) { return; }	
        	tnr = fields[8];
        if (size < 10) { return; }	
        	tnrsch = fields[9];
        if (size < 11) { return; }	
        	wbsch1 = fields[10];
        if (size < 12) { return; }	
        	wbsch2 = fields[11];        
        if (size < 13) { return; }	
        	rohm = fields[12];
        if (size < 14) { return; }	
        	eigm = fields[13];
        if (size < 15) { return; }	
        	zusm = fields[14];
        if (size < 16) { return; }	
        	stwt = fields[15];
        if (size < 17) { return; }	
        	sgicd = fields[16];
        if (size < 18) { return; }	
        	sgimng = fields[17];
        if (size < 19) { return; }	
        	cdmeld = fields[18];
        if (size < 20) { return; }	
        	zusang = fields[19];
        if (size < 21) { return; }	
        	cdexeu  = fields[20];
        if (size < 22) { return; }	
        	cdexld = fields[21];
        if (size < 23) { return; }	
        	verm = fields[22];
        if (size < 24) { return; }	
        	cdungf = fields[23];
        if (size < 25) { return; }	
           	rpwae  = fields[24];
        if (size < 26) { return; }	
           	rpreis = fields[25];
    }

    public String teilsatzBilden() {
        StringBuffer buff = new StringBuffer();

        buff.append(tsTyp);
        buff.append(trenner);
        buff.append(beznr);
        buff.append(trenner);
        buff.append(posnr);
        buff.append(trenner);
        buff.append(cdhw);
        buff.append(trenner);
        buff.append(cdabf2);
        buff.append(trenner);
        buff.append(cdabf);
        buff.append(trenner);
        buff.append(cdabfa);
        buff.append(trenner);
        buff.append(cdnzpf);
        buff.append(trenner);
        buff.append(tnr);
        buff.append(trenner);
        buff.append(tnrsch);
        buff.append(trenner);
        buff.append(wbsch1);
        buff.append(trenner);
        buff.append(wbsch2);
        buff.append(trenner);
        buff.append(rohm);
        buff.append(trenner);
        buff.append(eigm);
        buff.append(trenner);
        buff.append(zusm);
        buff.append(trenner);
        buff.append(stwt);
        buff.append(trenner);
        buff.append(sgicd);
        buff.append(trenner);
        buff.append(sgimng);
        buff.append(trenner);
        buff.append(cdmeld);
        buff.append(trenner);
        buff.append(zusang);
        buff.append(trenner);
        buff.append(cdexeu);
        buff.append(trenner);
        buff.append(cdexld);
        buff.append(trenner);        
        buff.append(verm);
        buff.append(trenner);        

        return new String(buff);
      }

    public void setBeznr(String beznr) {
		this.beznr = Utils.checkNull(beznr);
	}

	public String getBeznr() {
		return beznr;
	
	}

	public String getPosnr() {
		return posnr;
	
	}

	public void setPosnr(String posnr) {
		this.posnr = Utils.checkNull(posnr);
	}

	public String getCdhw() {
		return cdhw;
	
	}

	public void setCdhw(String cdhw) {
		this.cdhw = Utils.checkNull(cdhw);
	}

	public String getCdabf2() {
		return cdabf2;
	
	}

	public void setCdabf2(String cdabf2) {
		this.cdabf2 = Utils.checkNull(cdabf2);
	}

	public String getCdabf() {
		return cdabf;
	
	}

	public void setCdabf(String cdabf) {
		this.cdabf = Utils.checkNull(cdabf);
	}

	public String getCdabfa() {
		return cdabfa;
	
	}

	public void setCdabfa(String cdabfa) {
		this.cdabfa = Utils.checkNull(cdabfa);
	}

	public String getCdnzpf() {
		return cdnzpf;
	
	}

	public void setCdnzpf(String cdnzpf) {
		this.cdnzpf = Utils.checkNull(cdnzpf);
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

	public String getWbsch1() {
		return wbsch1;
	
	}

	public void setWabes(String wabes) {
		int len = 0;

		if (wabes != null) {
			len = wabes.length();			
			if (len > 0 && len <= 70) {
				wbsch1 = wabes;
			} else if (len > 70 && len <= 140) {
				//AK20100315
				wbsch1 = wabes.substring(0, 70);
				//AK20110819  wrong endindex
				wbsch2 = wabes.substring(70, len);
			} else  { // if (len > 140)
				wbsch1 = wabes.substring(0, 70);
				wbsch2 = wabes.substring(70, 140);
				verm   = wabes.substring(140);
			}
		}
	}

	
	public String getRohm() {
		return rohm;
	
	}

	public void setRohm(String rohm) {
		this.rohm = Utils.checkNull(rohm);
	}

	public String getEigm() {
		return eigm;
	
	}

	public void setEigm(String eigm) {
		this.eigm = Utils.checkNull(eigm);
	}

	public String getZusm() {
		return zusm;
	
	}

	public void setZusm(String zusm) {
		this.zusm = Utils.checkNull(zusm);
	}

	public String getStwt() {
		return stwt;
	
	}

	public void setStwt(String stwt) {
		this.stwt = Utils.checkNull(stwt);
	}

	public String getSgicd() {
		return sgicd;
	
	}

	public void setSgicd(String sgicd) {
		this.sgicd = Utils.checkNull(sgicd);
	}

	public String getSgimng() {
		return sgimng;
	
	}

	public void setSgimng(String sgimng) {
		this.sgimng = Utils.checkNull(sgimng);
	}

	public String getCdmeld() {
		return cdmeld;
	
	}

	public void setCdmeld(String cdmeld) {
		this.cdmeld = Utils.checkNull(cdmeld);
	}

	public String getZusang() {
		return zusang;
	
	}

	public void setZusang(String zusang) {
		this.zusang = Utils.checkNull(zusang);
	}

	public String getCdexeu() {
		return cdexeu;
	
	}

	public void setCdexeu(String cdexeu) {
		this.cdexeu = Utils.checkNull(cdexeu);
	}

	public String getCdexld() {
		return cdexld;
	
	}

	public void setCdexld(String cdexld) {
		this.cdexld = Utils.checkNull(cdexld);
	}

	public String getVerm() {
		return verm;
	
	}

	public void setVerm(String verm) {
		this.verm = Utils.checkNull(verm);
	}
	
	public boolean isEmpty() {
        return Utils.isStringEmpty(cdhw) &&
       		   Utils.isStringEmpty(cdabf2) &&
       		   Utils.isStringEmpty(cdabf) &&
       		   Utils.isStringEmpty(cdabfa) &&
       		   Utils.isStringEmpty(cdnzpf) &&
       		   Utils.isStringEmpty(tnr) &&
       		   Utils.isStringEmpty(tnrsch) &&
       		   Utils.isStringEmpty(wbsch1) &&
       		   Utils.isStringEmpty(wbsch2) &&
       		   Utils.isStringEmpty(rohm) &&
       		   Utils.isStringEmpty(eigm) &&
       		   Utils.isStringEmpty(zusm) &&
       		   Utils.isStringEmpty(stwt) &&
       		   Utils.isStringEmpty(sgicd) &&
       		   Utils.isStringEmpty(sgimng) &&
       		   Utils.isStringEmpty(cdmeld) &&
       		   Utils.isStringEmpty(zusang) &&
       		   Utils.isStringEmpty(cdexeu) &&
       		   Utils.isStringEmpty(cdexld) &&
       		   Utils.isStringEmpty(verm);  
	}

	public String getWbsch2() {
		return wbsch2;
	
	}

	public void setWbsch2(String wbsch2) {
		this.wbsch2 = Utils.checkNull(wbsch2);
	}

	public String getCdungf() {
		return cdungf;
	
	}

	public void setCdungf(String cdungf) {
		this.cdungf = Utils.checkNull(cdungf);
	}

	public String getRpwae() {
		return rpwae;
	
	}

	public void setRpwae(String rpwae) {
		this.rpwae = Utils.checkNull(rpwae);
	}

	public String getRpreis() {
		return rpreis;
	
	}

	public void setRpreis(String rpreis) {
		this.rpreis = Utils.checkNull(rpreis);
	}

	public void setWbsch1(String wbsch1) {
		this.wbsch1 = Utils.checkNull(wbsch1);
	}	

}	

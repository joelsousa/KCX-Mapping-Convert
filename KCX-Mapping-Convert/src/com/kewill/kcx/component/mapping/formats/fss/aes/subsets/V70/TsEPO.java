package com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V70;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		:	Export/aes
 * Created		:	25.07.2012
 * Description	:	Ergänzungssatz Position EPO.
 *
 * @author 	iwaniuk
 * @version  2.1.00 (Zabis V70)   
 */

public class TsEPO extends Teilsatz {

    
    private String beznr     = "";     
    private String posnr     = "";      
    //EI20130425: private String typ = "";         
    private String basbtg    = "";    
    private String baswrg    = "";     
    private String kziata	 = "";     
   
    public TsEPO() {
        tsTyp = "EPO";
    }

    public void setFields(String[] fields) {    
    	int size = fields.length;
		
    	
    	if (size < 1)  { return; }		
			tsTyp = fields[0];
		if (size < 2)  { return; }	
           beznr  = fields[1];
        if (size < 3)  { return; }
           posnr  = fields[2];       
        if (size < 4)  { return; }  //EI20130719: hier war falsche nummerierung
           basbtg = fields[3];
        if (size < 5)  { return; }
           baswrg = fields[4];
        if (size < 6)  { return; }
           kziata = fields[5];               
      }

    public String teilsatzBilden() {
        StringBuffer buff = new StringBuffer();

        buff.append(tsTyp);
        buff.append(trenner);
        buff.append(beznr);
        buff.append(trenner);
        buff.append(posnr);
        buff.append(trenner);
        //buff.append(typ);
        //buff.append(trenner);
        buff.append(basbtg);
        buff.append(trenner);
        buff.append(baswrg);
        buff.append(trenner);
        buff.append(kziata);
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

	public String getPosnr() {
		return posnr;	
	}
	public void setPosnr(String posnr) {
		this.posnr = Utils.checkNull(posnr);
	}
    /*EI20130424
	public String getTyp() {
		return typ;	
	}
	public void setTyp(String typ) {
		this.typ = Utils.checkNull(typ);
	}
	*/
	public String getBasbtg() {
		return basbtg;	
	}
	public void setBasbtg(String basbtg) {
		this.basbtg = Utils.checkNull(basbtg);
	}

	public String getBaswrg() {
		return baswrg;	
	}
	public void setBaswrg(String baswrg) {
		this.baswrg = Utils.checkNull(baswrg);
	}

	public String getKziata() {
		return kziata;	
	}
	public void setKziata(String kziata) {
		this.kziata = Utils.checkNull(kziata);
	}
	
	public boolean isEmpty() {		
		return  Utils.isStringEmpty(basbtg) && Utils.isStringEmpty(baswrg) &&	  
		        Utils.isStringEmpty(kziata);			
	}
}
    

 

  
  



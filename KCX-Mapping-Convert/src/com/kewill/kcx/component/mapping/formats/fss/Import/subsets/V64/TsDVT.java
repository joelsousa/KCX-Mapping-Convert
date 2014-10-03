package com.kewill.kcx.component.mapping.formats.fss.Import.subsets.V64;
import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Modul        :   DVT
 * Teilsatz     :   TsDVT.java
 * Erstellt     :   09.09.2011
 * Beschreibung :   DV1 Teilsatz
 *
 * @author          Alfred Krzoska
 *
 */

public class TsDVT extends Teilsatz {

    private String tsTyp    = "";       // Ts-Schlüssel
    private String dvtbnr   = "";       // Bezugsnummer
    private String kzverb   = "";       // KZ Verbundenheit Käufer/Verkäufer
    private String txverb   = "";       // Einzelheiten zum KZ Verbundenheit Käufer/Verkäufer
    private String kzesch	= "";       // KZ Einschränkung liegen vor
    private String txesch	= "";       // Text zur Einschränkung 
    private String kzlizg   = "";       // KZ Lizenzgebühr zu zahlen
    private String txlizg   = "";       // Text zur Lizenzgebühr
    private String kzwuv	= "";		// KZ Weiterverkauf/ Überlassung/Verwendung
    private String txwuv	= "";		// Text Weiterverkauf/Überlassung/ Verwendung
    private String kzvtrd	= "";		// KZ Vertretungsverhältnis DV1
    private String kzbedl	= "";		// KZ Bedingungen  / Leistungen
    private String txbedl	= "";		// Text zu Bedingungen / Leistungen
    private String txfren	= "";		// Text frühere Entscheidungen

    
    public TsDVT() {
        tsTyp = "DVT";
    }

   public void setFields(String[] fields)
   {
		int size = fields.length;
		String ausgabe = "FSS: " + fields[0] + " size = " + size;
		//Utils.log( ausgabe);
			
		
		if (size < 1) { return; }
        tsTyp = fields[0];
        
        if (size < 2) { return; }
        dvtbnr = fields[1];
        
        if (size < 3) { return; } 
        kzverb = fields[2];

        if (size < 4) { return; } 
        txverb = fields[3];

        if (size < 5) { return; } 
        kzesch = fields[4];

        if (size < 6) { return; } 
        txesch = fields[5];
        
        if (size < 7) { return; } 
        kzlizg = fields[6]; 
        		
        if (size < 8) { return; }
        txlizg = fields[7]; 
        		
        if (size < 9) { return; } 
        kzwuv = fields[8];

        if (size < 10) { return; } 
        txwuv = fields[9];
        		
        if (size < 11) { return; } 
        kzvtrd = fields[10];
        
        if (size < 12) { return; }
        kzbedl = fields[11];

        if (size < 13) { return; }
        txbedl = fields[12];
        
        if (size < 14) { return; }
        txfren = fields[13];
        
   }

    public String getTsTyp() {
    	return tsTyp;
    }

	public void setTsTyp(String tsTyp) {
		this.tsTyp = Utils.checkNull(tsTyp);
	}
	
	public String getDvtbnr() {
		return dvtbnr;
	}
	
	public void setDvtbnr(String dvtbnr) {
		this.dvtbnr = Utils.checkNull(dvtbnr);
	}
	
	public String getKzverb() {
		return kzverb;
	}
	
	public void setKzverb(String kzverb) {
		this.kzverb = Utils.checkNull(kzverb);
	}
	
	public String getTxverb() {
		return txverb;
	}
	
	public void setTxverb(String txverb) {
		this.txverb = Utils.checkNull(txverb);
	}
	
	public String getKzesch() {
		return kzesch;
	}
	
	public void setKzesch(String kzesch) {
		this.kzesch = Utils.checkNull(kzesch);
	}
	
	public String getTxesch() {
		return txesch;
	}
	
	public void setTxesch(String txesch) {
		this.txesch = Utils.checkNull(txesch);
	}
	
	public String getKzlizg() {
		return kzlizg;
	}
	
	public void setKzlizg(String kzlizg) {
		this.kzlizg = Utils.checkNull(kzlizg);
	}
	
	public String getTxlizg() {
		return txlizg;
	}
	
	public void setTxlizg(String txlizg) {
		this.txlizg = Utils.checkNull(txlizg);
	}
	
	public String getKzwuv() {
		return kzwuv;
	}
	
	public void setKzwuv(String kzwuv) {
		this.kzwuv = Utils.checkNull(kzwuv);
	}
	
	public String getTxwuv() {
		return txwuv;
	}
	
	public void setTxwuv(String txwuv) {
		this.txwuv = Utils.checkNull(txwuv);
	}
	
	public String getKzvtrd() {
		return kzvtrd;
	}
	
	public void setKzvtrd(String kzvtrd) {
		this.kzvtrd = Utils.checkNull(kzvtrd);
	}
	
	public String getKzbedl() {
		return kzbedl;
	}
	
	public void setKzbedl(String kzbedl) {
		this.kzbedl = Utils.checkNull(kzbedl);
	}
	
	public String getTxbedl() {
		return txbedl;
	}
	
	public void setTxbedl(String txbedl) {
		this.txbedl = Utils.checkNull(txbedl);
	}
	
	public String getTxfren() {
		return txfren;
	}
	
	public void setTxfren(String txfren) {
		this.txfren = Utils.checkNull(txfren);
	}

	public String teilsatzBilden() {
        StringBuffer buff = new StringBuffer();

        buff = buff.append(tsTyp);
        buff = buff.append(trenner);				
        buff = buff.append(dvtbnr);
        buff = buff.append(trenner);				
        buff = buff.append(kzverb);
        buff = buff.append(trenner);				
        buff = buff.append(txverb);
        buff = buff.append(trenner);				
        buff = buff.append(kzesch);
        buff = buff.append(trenner);				
        buff = buff.append(txesch);
        buff = buff.append(trenner);				
        buff = buff.append(kzlizg);
        buff = buff.append(trenner);				
        buff = buff.append(txlizg);
        buff = buff.append(trenner);				
        buff = buff.append(kzwuv);
        buff = buff.append(trenner);				
        buff = buff.append(txwuv);
        buff = buff.append(trenner);				
        buff = buff.append(kzvtrd);
        buff = buff.append(trenner);				
        buff = buff.append(kzbedl);
        buff = buff.append(trenner);				
        buff = buff.append(txbedl);
        buff = buff.append(trenner);				
        buff = buff.append(txfren);
        buff = buff.append(trenner);				

        return new String(buff);
    }

	
	public boolean isEmpty() {
		return Utils.isStringEmpty(kzverb) &&
				Utils.isStringEmpty(txverb) &&                           
				Utils.isStringEmpty(kzesch) &&				
				Utils.isStringEmpty(txesch) &&				 
				Utils.isStringEmpty(kzlizg) &&				
				Utils.isStringEmpty(txlizg) && 				
				Utils.isStringEmpty(kzwuv)  &&  			
				Utils.isStringEmpty(txwuv)  && 				
				Utils.isStringEmpty(kzvtrd) && 				
				Utils.isStringEmpty(kzbedl) && 				
				Utils.isStringEmpty(txbedl) && 				
				Utils.isStringEmpty(txfren);
	}
}
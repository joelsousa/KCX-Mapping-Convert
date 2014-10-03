package com.kewill.kcx.component.mapping.formats.fss.Import.subsets.V70;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module       :   Import 70<br>
 * Subset       :   TsADR.java<br>
 * Created      :   12.11.2012<br>
 * Description  :   Address.<br>
 *
 * @author          iwaniuk
 * @version         7.0.00 
 * 
 * 	1=Anmelder
	3=Versender
	4=Empfänger
	5=Erwerber-EU
	6=Käufer
	7=Verkäufer
	8=Zollwertanmelder
	9=Vertreter Zollwertanmelder
	10=Für Rechnung

 */

public class TsADR extends Teilsatz {

	private String abeznr    = "";      // Bezugsnummer
	private String adztyp    = "";      // Adresstyp
	private String anam1     = "";      // Name1
	private String anam2     = "";      // Name2
	private String anam3     = "";      // Name3
	private String astr      = "";      // Strasse
	//private String apostf    = "";      // Postfach
	private String alnd      = "";      // Land
	private String aplz      = "";      // Postleitzahl
	private String aort      = "";      // Ort
	private String aoteil    = "";      // Ortsteil
	    
    public TsADR() {
	        tsTyp = "ADR";
    }

	public void setFields(String[] fields) {
		int size = fields.length;
		//String ausgabe = "FSS: " + fields[0] + " size = " + size;
		//Utils.log( ausgabe);
			
		if (size < 1) { return; }
		tsTyp = fields[0];
		if (size < 2) { return; }	
	    abeznr       = fields[1];
	    if (size < 3) { return; }
	    adztyp       = fields[2];
	    if (size < 4) { return; }
	    anam1        = fields[3];
	    if (size < 5) { return; }
	    anam2        = fields[4];
	    if (size < 6) { return; }
	    anam3        = fields[5];
	    if (size < 7) { return; }
	    astr         = fields[6];
	    if (size < 8) { return; }
	    alnd		 = fields[7];
	    if (size < 9) { return; }
	    aplz         = fields[8];
	    if (size < 10) { return; }
	    aort         = fields[9];
	    if (size < 11) { return; }
	    aoteil         = fields[10];	    
	}

    public String teilsatzBilden() {
        StringBuffer buff = new StringBuffer();

        buff.append(tsTyp);
        buff.append(trenner);
        buff.append(abeznr);
        buff.append(trenner);
        buff.append(adztyp);
        buff.append(trenner);
        buff.append(anam1);
        buff.append(trenner);
        buff.append(anam2);
        buff.append(trenner);
        buff.append(anam3);
        buff.append(trenner);
        buff.append(astr);
        buff.append(trenner);        
        buff.append(alnd);
        buff.append(trenner);
        buff.append(aplz);
        buff.append(trenner);
        buff.append(aort);
        buff.append(trenner);
        buff.append(aoteil);
	    buff.append(trenner);

        return new String(buff);
    }

    
	
	public boolean isEmpty() {
	String name = anam1 + anam2 + anam3;	
		
		return Utils.isStringEmpty(name) && 
			   Utils.isStringEmpty(astr) && 			 
			   Utils.isStringEmpty(alnd) && 
			   Utils.isStringEmpty(aplz) && 
			   Utils.isStringEmpty(aort) &&
			   Utils.isStringEmpty(aoteil);
	}

	public String getAbeznr() {
		return abeznr;
	}

	public void setAbeznr(String abeznr) {
		this.abeznr = Utils.checkNull(abeznr);
	}

	public String getAdztyp() {
		return adztyp;
	}

	public void setAdztyp(String adztyp) {
		this.adztyp = Utils.checkNull(adztyp);
	}

	public String getAnam1() {
		return anam1;
	}

	public void setAnam1(String anam1) {
		this.anam1 = Utils.checkNull(anam1);
	}

	public String getAnam2() {
		return anam2;
	}

	public void setAnam2(String anam2) {
		this.anam2 = Utils.checkNull(anam2);
	}

	public String getAnam3() {
		return anam3;
	}

	public void setAnam3(String anam3) {
		this.anam3 = Utils.checkNull(anam3);
	}

	public String getAstr() {
		return astr;
	}

	public void setAstr(String astr) {
		this.astr = Utils.checkNull(astr);
	}
	
	public String getAlnd() {
		return alnd;
	}

	public void setAlnd(String alnd) {
		this.alnd = Utils.checkNull(alnd);
	}

	public String getAplz() {
		return aplz;
	}

	public void setAplz(String aplz) {
		this.aplz = Utils.checkNull(aplz);
	}

	public String getAort() {
		return aort;
	}

	public void setAort(String aort) {
		this.aort = Utils.checkNull(aort);
	}

	public String getAoteil() {
		return aoteil;
	}

	public void setAoteil(String aoteil) {
		this.aoteil = Utils.checkNull(aoteil);
	}
}
    

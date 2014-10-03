package com.kewill.kcx.component.mapping.formats.fss.suma.subsets.V70;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;


/**
 * Module		:	Manifest
 * Created		:	17.06.2013
 * Description	:   Kopfsatz Nachtraegliche Zuweisung der MRNs u. Positionen auf SumAs
 *        			(Zabis V70) 
 *
 * @author iwaniuk
 * @version 7.0.00
 */

public class TsSNK extends Teilsatz {
	
	private String beznr	= "";		// Bezugsnummer
	private String idbeznr	= "";		// Angesprochene Bezugsnummer
	private String idart	= "";		// Art der Identifikation	
	private String vornr	= "";		// Vorpapiernummer
	private String sb		= "";		// Sachbearbeiter Zabis Kennung
	private String sbname   = "";		// Sachbearbeiter Name
	private String sbdstl	= "";		// Dienststellung des Sachbearbeiers
	private String sbtel	= "";		// Telefonnummer des Sachbearbeiters
	private String befnum	= "";		// Nummer der Beförderung
	private String ankdat	= "";		// Ankunftsdatum
	private String brefnum	= "";		// Referenzierte Nummer der Beförderung
	private String iddat	= "";		// ID Ankunftsdatum
	
	public TsSNK() {
		tsTyp = "SNK";
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
	        idbeznr = fields[2];
	        if (size < 4) { return; }	
	        idart = fields[3];
	        
	        //usw.... z.T wird die methode nicht gebraucht
	 }
	
	public String teilsatzBilden() {
		StringBuffer buff = new StringBuffer();
		buff.append(tsTyp);
		buff.append(trenner);		
		buff.append(beznr);
		buff.append(trenner);
		buff.append(idbeznr);	
		buff.append(trenner);
		buff.append(idart);
		buff.append(trenner);		
		buff.append(vornr);		
		buff.append(trenner);
		buff.append(sb);		
		buff.append(trenner);
		buff.append(sbname);		
		buff.append(trenner);
		buff.append(sbdstl);	
		buff.append(trenner);
		buff.append(sbtel);		
		buff.append(trenner);
		buff.append(befnum);	
		buff.append(trenner);
		buff.append(ankdat);
		buff.append(trenner);
		buff.append(brefnum);
		buff.append(trenner);
		buff.append(iddat);
		buff.append(trenner);
		
		return new String(buff);
	}
	
	public boolean isEmpty() {
    	return (Utils.isStringEmpty(beznr) && Utils.isStringEmpty(idbeznr) &&
    			//TODO weiter...
    			Utils.isStringEmpty(idart) && Utils.isStringEmpty(vornr));		
    }
	
	public String getBeznr() {
		return beznr;
	}
	public void setBeznr(String beznr) {
		this.beznr = Utils.checkNull(beznr);
	}

	public String getIdbeznr() {
		return idbeznr;
	}
	public void setIdbeznr(String ibez) {
		this.idbeznr = Utils.checkNull(ibez);
	}

	public String getIdart() {
		return idart;
	}
	public void setIdart(String idart) {
		this.idart = Utils.checkNull(idart);
	}

	public String getVornr() {
		return vornr;
	}
	public void setVornr(String vornr) {
		this.vornr = Utils.checkNull(vornr);
	}

	public String getSb() {
		return sb;
	}
	public void setSb(String sb) {
		this.sb = Utils.checkNull(sb);
	}

	public String getSbname() {
		return sbname;
	}
	public void setSbname(String sbname) {
		this.sbname = Utils.checkNull(sbname);
	}

	public String getSbdstl() {
		return sbdstl;
	}
	public void setSbdstl(String sbdstl) {
		this.sbdstl = Utils.checkNull(sbdstl);
	}

	public String getSbtel() {
		return sbtel;
	}
	public void setSbtel(String sbtel) {
		this.sbtel = Utils.checkNull(sbtel);
	}

	public String getBefnum() {
		return befnum;
	}
	public void setBefnum(String befnum) {
		this.befnum = Utils.checkNull(befnum);
	}

	public String getAnkdat() {
		return ankdat;
	}
	public void setAnkdat(String ankdat) {
		this.ankdat = Utils.checkNull(ankdat);
	}

	public String getBrefnum() {
		return brefnum;
	}
	public void setBrefnum(String brefnum) {
		this.brefnum = Utils.checkNull(brefnum);
	}

	public String getIddat() {
		return iddat;
	}
	public void setIddat(String iddat) {
		this.iddat = Utils.checkNull(iddat);
	}
	
	
}

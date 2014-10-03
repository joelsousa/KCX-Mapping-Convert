package com.kewill.kcx.component.mapping.formats.fss.suma.subsets.V62;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;


/**
 * Module		:	TsSUR
 * Created		:	19.12.2012
 * Description	:   Gestellung-/Aufteilungsdaten.
 *        			Zabis Version 06.02  
 *
 * @author iwaniuk
 * @version 1.0.00
 */

public class TsSUR  extends Teilsatz {

	private String beznr	= "";			
	private String idfltnum	= "";			
	private String idfltblo	= "";	
	private String idfltvpa	= "";	
	private String idkzawb	= "";	
	private String idspo	= "";	
	private String idvrwznr	= "";	
	private String idbefnum	= "";
	private String idankdat	= "";  //Format = YYYYMMDD
		
    public TsSUR() {
        tsTyp = "SUR";
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
        idfltnum = fields[2];
        if (size < 4) { return; }	
        idfltblo = fields[3];
        //usw.... z.T wird die methode nicht gebraucht
      }

    public String teilsatzBilden() {
        StringBuffer buff = new StringBuffer();

        buff.append(tsTyp);
        buff.append(trenner);
        buff.append(beznr);
        buff.append(trenner);
        buff.append(idfltnum);
        buff.append(trenner);
        buff.append(idfltblo);
        buff.append(trenner);
        buff.append(idfltvpa);
        buff.append(trenner);
        buff.append(idkzawb);
        buff.append(trenner);
        buff.append(idspo);
        buff.append(trenner);
        buff.append(idvrwznr);
        buff.append(trenner);
        buff.append(idbefnum);
        buff.append(trenner);
        buff.append(idankdat);
        buff.append(trenner);

        return new String(buff);
    }

	public boolean isEmpty() {
		return (Utils.isStringEmpty(beznr) && Utils.isStringEmpty(idfltnum) &&
			Utils.isStringEmpty(idfltblo) && Utils.isStringEmpty(idfltvpa));
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

	public String getIdfltnum() {
		return idfltnum;
	}

	public void setIdfltnum(String idfltnum) {
		this.idfltnum = Utils.checkNull(idfltnum);
	}

	public String getIdfltblo() {
		return idfltblo;
	}

	public void setIdfltblo(String idfltblo) {
		this.idfltblo = Utils.checkNull(idfltblo);
	}

	public String getIdfltvpa() {
		return idfltvpa;
	}

	public void setIdfltvpa(String idfltvpa) {
		this.idfltvpa = Utils.checkNull(idfltvpa);
	}

	public String getIdkzawb() {
		return idkzawb;
	}

	public void setIdkzawb(String idkzawb) {
		this.idkzawb = Utils.checkNull(idkzawb);
	}

	public String getIdspo() {
		return idspo;
	}

	public void setIdspo(String idspo) {
		this.idspo = Utils.checkNull(idspo);
	}

	public String getIdvrwznr() {
		return idvrwznr;
	}

	public void setIdvrwznr(String idvrwznr) {
		this.idvrwznr = Utils.checkNull(idvrwznr);
	}

	public String getIdbefnum() {
		return idbefnum;
	}

	public void setIdbefnum(String idbefnum) {
		this.idbefnum = Utils.checkNull(idbefnum);
	}

	public String getIdankdat() {
		return idankdat;
	}

	public void setIdankdat(String idankdat) {
		this.idankdat = Utils.checkNull(idankdat);
	}

}



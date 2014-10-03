package com.kewill.kcx.component.mapping.formats.fss.suma.subsets.V70;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;


/**
 * Module		:	Manifest
 * Created		:	17.06.2013
 * Description	:   Positionssatz Nachtraegliche Zuweisung der MRNs u. Positionen auf SumAs
 *        			(Zabis V70) 
 *
 * @author iwaniuk
 * @version 7.0.00
 */

public class TsSNP extends Teilsatz {

	private String idawbzzz	= "";		// Angesprochener SpO
	private String idposnr	= "";		// Angesprochene Positionsnummer
	
	public TsSNP() {
		tsTyp = "SNP";
	}
	
	 public void setFields(String[] fields) {  
			int size = fields.length;
			//String ausgabe = "FSS: "+fields[0]+" size = "+ size;
			//Utils.log( ausgabe);
							
			if (size < 1) { return; }		
	        tsTyp = fields[0];
	        if (size < 2) { return; }		
	        idawbzzz = fields[1];
	        if (size < 3) { return; }	
	        idposnr = fields[2];
	       
	 }
	
	public String teilsatzBilden() {
		StringBuffer buff = new StringBuffer();
		buff.append(tsTyp);
		buff.append(trenner);
		buff.append(idawbzzz);	
		buff.append(trenner);
		buff.append(idposnr);	
		buff.append(trenner);
		return new String(buff);
	}

	public boolean isEmpty() {
    	return Utils.isStringEmpty(idawbzzz) && Utils.isStringEmpty(idposnr);    			
    }

	public String getIdawbzzz() {
		return idawbzzz;
	}
	public void setIdawbzzz(String idawbzzz) {
		this.idawbzzz = Utils.checkNull(idawbzzz);
	}

	public String getIdposnr() {
		return idposnr;
	}
	public void setIdposnr(String idposnr) {
		this.idposnr = Utils.checkNull(idposnr);
	}
	
	
}

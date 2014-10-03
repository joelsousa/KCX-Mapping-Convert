package com.kewill.kcx.component.mapping.formats.fss.suma.subsets.V70;

/**
 * Module		:	Manifest
 * Created		:	17.06.2013
 * Description	:   Text  SSA.
 *        			Zabis Version 70  
 *
 * @author iwaniuk
 * @version 7.0.00
 */

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

public class TsSSI extends Teilsatz {

	private String beznr;			// Bezugsnummer
	private String regnr;			// Registriernummer
	private String posnr;			// Positionsnummer
	private String text;			// Zusätzliche Informationen	
	
	public TsSSI() {   
        tsTyp = "SSI";
    }
	
	public void setFields(String[] fields) {
		int size = fields.length;
		
		if (size < 1) { return; }
		tsTyp	= fields[0];
		if (size < 2) { return; }
		beznr	= fields[1];
		if (size < 3) { return; }
		regnr	= fields[2];
		if (size < 4) { return; }
		posnr	= fields[3];
		if (size < 5) { return; }
		text	= fields[4];
		
	}
	
	

	@Override
	public boolean isEmpty() {
		return (Utils.isStringEmpty(regnr) && Utils.isStringEmpty(text));					
	}

	@Override
	public String teilsatzBilden() {
		StringBuffer buff = new StringBuffer();
		
		buff.append(tsTyp);
		buff.append(trenner);
		buff.append(beznr);
		buff.append(trenner);
		buff.append(regnr);
		buff.append(trenner);
		buff.append(posnr);
		buff.append(trenner);
		buff.append(text);
		buff.append(trenner);		
		
		return buff.toString();
	}

	public String getBeznr() {
		return beznr;
	}
	public void setBeznr(String beznr) {
		this.beznr = Utils.checkNull(beznr);
	}

	public String getRegnr() {
		return regnr;
	}
	public void setRegnr(String regnr) {
		this.regnr = Utils.checkNull(regnr);
	}

	public String getPosnr() {
		return posnr;
	}
	public void setPosnr(String posnr) {
		this.posnr = Utils.checkNull(posnr);
	}

	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = Utils.checkNull(text);
	}

	
}

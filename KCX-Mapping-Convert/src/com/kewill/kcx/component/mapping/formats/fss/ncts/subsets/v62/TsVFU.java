package com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: TsVFU<br>	
 * Created		: 02.09.2010<br>
 * Description	: VFU segment.
 * 
 * @author Edwin Bautista
 * @version 6.2.00
 */
public class TsVFU extends Teilsatz {
	
	private String lfdnr	= "";		//Laufende Nummer des Antrags
    private String posnr 	= "";       //Positionsnummer
    private String txtlfd	= "";		//Laufende Nummer des Fehlers
    private String errcd 	= "";       //Fehlercode
    private String errtxt 	= "";       //Fehlertext
    
    public TsVFU() {
        tsTyp = "VFU";
    }

    public void setFields(String[] fields) {
		int size = fields.length;
		
		if (size < 1) { return; }
        tsTyp 	= fields[0];
        if (size < 2) { return; }
        lfdnr   = fields[1];
        if (size < 3) { return; }
        posnr   = fields[2];
        if (size < 4) { return; }
        txtlfd   = fields[3];
        if (size < 5) { return; }
        errcd   = fields[4];
        if (size < 6) { return; }
        errtxt   = fields[5];
    }

    public String teilsatzBilden() {
        StringBuffer buff = new StringBuffer();

        buff.append(tsTyp);
        buff.append(trenner);
        buff.append(lfdnr);
        buff.append(trenner);
        buff.append(posnr);
        buff.append(trenner);
        buff.append(txtlfd);
        buff.append(trenner);
        buff.append(errcd);
        buff.append(trenner);
        buff.append(errtxt);
        buff.append(trenner);
        
        return new String(buff);
    }	
	
	public String getPosnr() {
		return posnr;
	}

	public void setPosnr(String posnr) {
		this.posnr = Utils.checkNull(posnr);
	}

	public String getErrcd() {
		return errcd;
	}

	public void setErrcd(String errcd) {
		this.errcd = Utils.checkNull(errcd);
	}

	public String getErrtxt() {
		return errtxt;
	}

	public void setErrtxt(String errtxt) {
		this.errtxt = Utils.checkNull(errtxt);
	}

	public String getLfdnr() {
		return lfdnr;
	}

	public void setLfdnr(String lfdnr) {
		this.lfdnr = Utils.checkNull(lfdnr);
	}

	public String getTxtlfd() {
		return txtlfd;
	}

	public void setTxtlfd(String txtlfd) {
		this.txtlfd = Utils.checkNull(txtlfd);
	}
	
	public boolean isEmpty() {
		return (Utils.isStringEmpty(lfdnr) && Utils.isStringEmpty(posnr) && 
				Utils.isStringEmpty(txtlfd) && Utils.isStringEmpty(errcd) &&
				Utils.isStringEmpty(errtxt));
	}
}



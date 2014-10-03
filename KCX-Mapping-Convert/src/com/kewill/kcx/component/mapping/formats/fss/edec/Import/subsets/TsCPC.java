package com.kewill.kcx.component.mapping.formats.fss.edec.Import.subsets;

import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: EDEC Import<br>
 * Created		: 29.10.2012<br>
 * Description  : FSS Definition of Zusatzdaten Position. 
 * 
 * @author iwaniuk
 * @version 7.0.00
 */

public class TsCPC extends Teilsatz {
	private String beznr    = "";      //Referencenumber
	private String posnr    = "";		 //Itemnumber
	private String tnrrc    = "";		 //
	private String rohmrc   = "";		 //
	private String eigmrc   = "";	 //
	private String stzusmrc = "";	 //
	private String stwertrc = "";	 //
	private String mswertrc = "";	 //
	private String tazurc   = "";	 //
	private String zolansrc = "";	 //
	private String cdmwstrc = "";	 //
	
	public TsCPC() {
        tsTyp = "CPC";
    }

    public void setFields(String[] fields) {
		int size = fields.length;
		//String ausgabe = "FSS: " + fields[0] + " size = " + size;
		//Utils.log( ausgabe);
			
		
		if (size < 1) { return; }		
        	tsTyp = fields[0];
        if (size < 2) { return; }
        	beznr = fields[1];
        if (size < 3) { return; }	
        	posnr = fields[2];
        if (size < 4) { return; }
        	tnrrc = fields[3];
        if (size < 5) { return; }
        	rohmrc = fields[4];        
        if (size < 6) { return; }
        	eigmrc = fields[5];
       if (size < 7) { return; }
        	stzusmrc = fields[6];
       if (size < 8) { return; }
       		stwertrc = fields[7];
       if (size < 9) { return; }
        	mswertrc = fields[8];
       if (size < 10) { return; }
       		tazurc = fields[9];
       	if (size < 11) { return; }		
       		zolansrc = fields[10];
       	if (size < 12) { return; }
       		cdmwstrc = fields[11];
       
    }

    public String teilsatzBilden() {
        StringBuffer buff = new StringBuffer();

        buff.append(tsTyp);
        buff.append(trenner);
        buff.append(beznr);
        buff.append(trenner);
        buff.append(posnr);
        buff.append(trenner);
        buff.append(tnrrc);
        buff.append(trenner);
        buff.append(rohmrc);
        buff.append(trenner);
        buff.append(eigmrc);
        buff.append(trenner);
        buff.append(stzusmrc);
        buff.append(trenner);
        buff.append(stwertrc);
        buff.append(trenner);
        buff.append(mswertrc);
        buff.append(trenner);
        buff.append(tazurc);
        buff.append(trenner);
        buff.append(zolansrc);
        buff.append(trenner);
        buff.append(cdmwstrc);
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

	public String getTnrrc() {
		return tnrrc;	
	}
	public void setTnrrc(String tnrrc) {
		this.tnrrc = Utils.checkNull(tnrrc);
	}

	public String getRohmrc() {
		return rohmrc;	
	}
	public void setRohmrc(String rohmrc) {
		this.rohmrc = Utils.checkNull(rohmrc);
	}

	public String getEigmrc() {
		return eigmrc;	
	}
	public void setEigmrc(String eigmrc) {
		this.eigmrc = Utils.checkNull(eigmrc);
	}

	public String getStzusmrc() {
		return stzusmrc;	
	}
	public void setStzusmrc(String stzusmrc) {
		this.stzusmrc = Utils.checkNull(stzusmrc);
	}

	public String getStwertrc() {
		return stwertrc;	
	}

	public void setStwertrc(String stwertrc) {
		this.stwertrc = Utils.checkNull(stwertrc);
	}

	public String getMswertrc() {
		return mswertrc;	
	}
	public void setMswertrc(String mswertrc) {
		this.mswertrc = Utils.checkNull(mswertrc);
	}

	public String getTazurc() {
		return tazurc;	
	}
	public void setTazurc(String tazurc) {
		this.tazurc = Utils.checkNull(tazurc);
	}

	public String getZolansrc() {
		return zolansrc;	
	}

	public void setZolansrc(String stwertrc) {
		this.zolansrc = Utils.checkNull(stwertrc);
	}
	
	public String getCdmwstrc() {
		return cdmwstrc;	
	}

	public void setCdmwstrc(String stwertrc) {
		this.cdmwstrc = Utils.checkNull(stwertrc);
	}
   
    
	public boolean isEmpty() {
		return Utils.isStringEmpty(tnrrc) &&
			Utils.isStringEmpty(rohmrc) &&
			Utils.isStringEmpty(eigmrc) &&
			Utils.isStringEmpty(stzusmrc) &&
			Utils.isStringEmpty(stwertrc) &&
			Utils.isStringEmpty(mswertrc) &&
			Utils.isStringEmpty(tazurc);
	}
	

}	

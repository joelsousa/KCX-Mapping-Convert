package com.kewill.kcx.component.mapping.formats.fss.edec.aus.subsets;

/**
 * Funktion     :   EDEC Export TsCVA Subset Position
 * Nachricht    :   
 * Erstellt     :   22.10.2008
 * Beschreibung :   TsCVA
 *  
 * @author          Alfred Krzoska
 * 
 */

import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.PreviousDocument;
import com.kewill.kcx.component.mapping.formats.fss.common.Teilsatz;
import com.kewill.kcx.component.mapping.util.Utils;




public class TsCVA extends Teilsatz {
	private String beznr  = "";      //Referencenumber
	private String posnr  = "";		 //Itemnumber
	private String vpart  = "";		 //Type
	private String vpnr   = "";		 //Reference
	private String vpzus  = "";	 	 //AdditionalInformation
	
	
	public TsCVA() {
        tsTyp = "CVA";
    }

    public void setFields(String[] fields)
    {
		int size = fields.length;
		String ausgabe = "FSS: "+fields[0]+" size = "+ size;
		//Utils.log( ausgabe);
			
		
		if (size < 1 ) return;		
        	tsTyp = fields[0];
        if (size < 2 ) return;	
        	beznr = fields[1];
        if (size < 3 ) return;	
        	posnr = fields[2];
        if (size < 4 ) return;	
        	vpart = fields[3];
        if (size < 5 ) return;	
        	vpnr  = fields[4];        
        if (size < 6 ) return;	
        	vpzus = fields[5];
        	
    }

    public String teilsatzBilden() {
        StringBuffer buff = new StringBuffer();

        buff.append(tsTyp);
        buff.append(trenner);
        buff.append(beznr);
        buff.append(trenner);
        buff.append(posnr);
        buff.append(trenner);
        buff.append(vpart);
        buff.append(trenner);
        buff.append(vpnr);
        buff.append(trenner);
        buff.append(vpzus);

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

	
	public void setCvaSubset(PreviousDocument pd, String beznr, String item ) {
		if (pd == null) return;
		if (Utils.isStringEmpty(beznr)) return;
		if (Utils.isStringEmpty(item)) return;

		this.setBeznr(beznr);
		this.setPosnr(item);
		
		this.setVpart(pd.getType());
		this.setVpnr(pd.getMarks());
		this.setVpzus(pd.getAdditionalInformation());

	}

	public boolean isEmpty() {
         if ( 	Utils.isStringEmpty (vpart)
       		 && Utils.isStringEmpty (vpnr)
       		 && Utils.isStringEmpty (vpzus)
         ) 
			return true;
		else
			return false;
	}

	public String getVpart() {
		return vpart;
	
	}

	public void setVpart(String vpart) {
		this.vpart = Utils.checkNull(vpart);
	}

	public String getVpnr() {
		return vpnr;
	
	}

	public void setVpnr(String vpnr) {
		this.vpnr = Utils.checkNull(vpnr);
	}

	public String getVpzus() {
		return vpzus;
	
	}

	public void setVpzus(String vpzus) {
		this.vpzus = Utils.checkNull(vpzus);
	}
}	

package com.kewill.kcx.component.mapping.formats.fss.aes.messages.V60;

/*
 * Funktion    : MsgACA.java
 * Titel       :
 * Date	       : 15.10.2008
 * Author      : Kewill CSF / Christine Kron
 * Description : Kids: Cancelation 
 *
 * Changes 
 * -----------
 * Author      : EI
 * Date        : 13.03.2009
 * Label       :
 * Description : checked for V60 - no changes
 *
 */


import java.io.BufferedReader;
import java.io.IOException;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.MsgExpCan;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsASO;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.fss.common.TsVOR;
import com.kewill.kcx.component.mapping.formats.fss.utils.FssUtils;
import com.kewill.kcx.component.mapping.util.Utils;


public class MsgACA extends FssMessage60{
	
	private TsVOR	vorSubset;
	private TsASO   asoSubset;   
	
	public TsVOR getVorSubset() {
		return vorSubset;
	}

	public void setVorSubset(TsVOR vorSubset) {
		this.vorSubset = vorSubset;
	}
    
	public void setAsoSubset(TsASO aso) {
		this.asoSubset = aso;
    }

	public TsASO getAsoSubset() {
		return asoSubset;
	}

	public String getFssString() throws FssException {
		String res = "";	
		
		if(vorSubset != null && !vorSubset.isEmpty()) 		 
			res = appendString(res, vorSubset.teilsatzBilden());	
		if(asoSubset != null && !asoSubset.isEmpty())			
			res = appendString(res, asoSubset.teilsatzBilden());
		
		return res;
	}
    public void readMessage(BufferedReader message) throws FssException {
        try {
            String line = "";

            while ((line = message.readLine())!=null) {
                String lineType = line.substring(0, 3);
                Utils.log("linetype " + lineType);
                if (lineType.equalsIgnoreCase("ASO")) {
                	asoSubset = new TsASO();
                    String[] aso = line.split("" + FssUtils.FSS_FS);
                    asoSubset.setFields(aso);
                } else if (lineType.equalsIgnoreCase("NAC")) {    //AK20081117
                	// Nachlaufsatz NAC nicht verarbeiten
                } 
                else {
                    throw new FssException("Wrong message line " + lineType);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}


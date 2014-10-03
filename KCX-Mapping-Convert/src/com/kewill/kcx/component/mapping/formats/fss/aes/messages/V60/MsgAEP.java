/*
 * Funktion    : MsgAEP.java
 * Titel       : 
 * Erstellt    : 27.03.2009
 * Author      : Alfred Krzoska
 * Beschreibung:
 * Anmerkungen :
 * Parameter   :
 * Rückgabe    : keine
 * Aufruf      :
 *
 */

package com.kewill.kcx.component.mapping.formats.fss.aes.messages.V60;

import java.io.BufferedReader;
import java.io.IOException;


import com.kewill.kcx.component.mapping.countries.de.aes.msg.MsgExpErl;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsAAR;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsAME;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.fss.common.TsVOR;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.formats.fss.common.FssDatei;
import com.kewill.kcx.component.mapping.formats.fss.utils.FssUtils;



public class MsgAEP extends FssDatei {

	private TsVOR	vorSubset;
	private TsAME   ameSubset;

			
	public MsgAEP(String outFileName) {
		super(outFileName);	
		vorSubset = new TsVOR(""); 
		ameSubset = new TsAME(); 
	}	
	public TsVOR getVorSubset() {
		return vorSubset;
	}
	public void setVorSubset(TsVOR vor) {
		this.vorSubset = vor;
	}

	public TsAME getAmeSubset() {
		return ameSubset;
	}
	public void setAmeSubset(TsAME argument) {
		this.ameSubset = argument;
	}	

	public void readMessage(BufferedReader message) throws FssException {
        try {
            String line = "";

            while ((line = message.readLine())!=null) {
                String lineType = line.substring(0, 3);
                Utils.log("linetype " + lineType);
                if (lineType.equalsIgnoreCase("AME")) {             
                   	ameSubset = new TsAME();            		
                    String[] ame = line.split("" + FssUtils.FSS_FS);
                    ameSubset.setFields(ame); 
                }  else if (lineType.equalsIgnoreCase("NAC")) {   //AK20081129
                    	// Nachlaufsatz NAC nicht verarbeiten
                } else {
                    throw new FssException("Wrong message line " + lineType);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

	public StringBuffer writeAEP() throws FssException {
		StringBuffer res = new StringBuffer();	
	
		if(vorSubset != null && !vorSubset.isEmpty()) 		 
			res = appendString(res, vorSubset.teilsatzBilden());			
		if(ameSubset != null && !ameSubset.isEmpty())			
			res = appendString(res, ameSubset.teilsatzBilden());
		
		return res;
	}
	
	private StringBuffer appendString ( StringBuffer in, String appendStr) throws FssException {
		StringBuffer stb = new StringBuffer();
		
		stb.append(in);
		stb.append(appendStr);
		stb.append(Utils.LF);
		return stb;
	}
		
}

/*
 * Funktion    : MsgAXT.java
 * Titel       : Confirm Investigation
 * Erstellt    : 02.10.2008
 * Author      : Miro Houdek
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

import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsAXT;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.fss.common.TsVOR;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.formats.fss.common.FssDatei;
import com.kewill.kcx.component.mapping.formats.fss.utils.FssUtils;



public class MsgAXT extends FssDatei {

	private TsVOR	vorSubset;
	private TsAXT   axtSubset;   

			
	public MsgAXT(String outFileName) {
		super(outFileName);		

	}	
	public TsVOR getVorSubset() {
		return vorSubset;
	}
	public void setVorSubset(TsVOR vor) {
		this.vorSubset = vor;
	}

	public TsAXT getAxtSubset() {
		return axtSubset;
	}
	public void setAxtSubset(TsAXT axt) {
		this.axtSubset = axt;
	}	

	public void readMessage(BufferedReader message) throws FssException {
        try {
            String line = "";

            while ((line = message.readLine())!=null) {
                String lineType = line.substring(0, 3);
                Utils.log("linetype " + lineType);
                if (lineType.equalsIgnoreCase("AXT")) {
                	axtSubset = new TsAXT();            		
                    String[] axt = line.split("" + FssUtils.FSS_FS);
                    axtSubset.setFields(axt);                                                                       
                } else if (lineType.equalsIgnoreCase("NAC")) {    //AK20081117
                    	// Nachlaufsatz NAC nicht verarbeiten
                } else {
                    throw new FssException("Wrong message line " + lineType);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

	public StringBuffer writeAXT() throws FssException {
		StringBuffer res = new StringBuffer();	
	
		if(vorSubset != null && !vorSubset.isEmpty()) 		 
			res = appendString(res, vorSubset.teilsatzBilden());	
		if(axtSubset != null && !axtSubset.isEmpty())			
			res = appendString(res, axtSubset.teilsatzBilden());
		
		Utils.log("(MsgAXT AXT = " + axtSubset.teilsatzBilden());
		
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

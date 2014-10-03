package com.kewill.kcx.component.mapping.formats.fss.aes.messages.V70;

import java.io.BufferedReader;
import java.io.IOException;

import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsAME;
import com.kewill.kcx.component.mapping.formats.fss.common.FssDatei;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.fss.common.TsHead;
import com.kewill.kcx.component.mapping.formats.fss.common.TsVOR;
import com.kewill.kcx.component.mapping.formats.fss.utils.FssUtils;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: Export / AES21 <br>
 * Created		: 26.09.2012<br>
 * Description	: FSS Message AEP Version 70 (KIDS-ManualTermination).
 * 
 * @author iwaniuk
 * @version 2.1.00
 */

public class MsgAEP extends FssDatei {

	private TsVOR	vorSubset;
	private TsHead	headSubset;     //EI20120928
	private TsAME   ameSubset;

			
	public MsgAEP(String outFileName) {
		super(outFileName);	
		vorSubset = new TsVOR(""); 
		//headSubset = new TsHead("HEAD");
		headSubset = new TsHead("");  //EI20121204
		ameSubset = new TsAME(); 
	}	
	public TsVOR getVorSubset() {
		return vorSubset;
	}
	public void setVorSubset(TsVOR vor) {
		this.vorSubset = vor;
	}

	public TsHead getHeadSubset() {
		return headSubset;
	}
	public void setHeadSubset(TsHead head) {
		this.headSubset = head;
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

            while ((line = message.readLine()) != null) {
                String lineType = line.substring(0, 3);
                Utils.log("linetype " + lineType);
                if (lineType.equalsIgnoreCase("AME")) {             
                   	ameSubset = new TsAME();            		
                    String[] ame = line.split("" + FssUtils.FSS_FS);
                    ameSubset.setFields(ame); 
                }  else if (lineType.equalsIgnoreCase("NAC")) {   
                    	// Nachlaufsatz NAC nicht verarbeiten
                } else {
                    throw new FssException("Wrong message line " + lineType);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	public String getFssString() throws FssException {	  //EI20121107
		return getFssString("");
	}
	
	public String getFssString(String firstTs) throws FssException {
		String res = "";
	
		if (firstTs != null && firstTs.equalsIgnoreCase("HEAD")) {			
			if (headSubset != null && !headSubset.isEmpty()) {		 
				res = appendString(res, headSubset.teilsatzBilden());	
			}
		} else {
			if (vorSubset != null && !vorSubset.isEmpty()) {	 
				res = appendString(res, vorSubset.teilsatzBilden());
			}
		}
		if (ameSubset != null && !ameSubset.isEmpty()) {			
			res = appendString(res, ameSubset.teilsatzBilden());
		}		
		return res;
	}	
	
}

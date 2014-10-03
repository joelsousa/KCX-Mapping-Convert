package com.kewill.kcx.component.mapping.formats.fss.aes.messages.V70;

import java.io.BufferedReader;
import java.io.IOException;

import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsASO;
import com.kewill.kcx.component.mapping.formats.fss.common.FssDatei;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.fss.common.TsHead;
import com.kewill.kcx.component.mapping.formats.fss.common.TsVOR;
import com.kewill.kcx.component.mapping.formats.fss.utils.FssUtils;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: Export / AES21 <br>
 * Created		: 26.09.2012<br>
 * Description	: FSS Message ACA Version 70(KIDS-Cancellation).
 * 
 * @author iwaniuk
 * @version 2.1.00
 */

public class MsgACA extends FssDatei {
	
	private TsVOR	vorSubset;
	private TsHead	headSubset;  //EI20120926
	private TsASO   asoSubset;   
	
	public MsgACA(String outFileName) {
		super(outFileName);	
		vorSubset = new TsVOR(""); 
		headSubset = new TsHead("");
		asoSubset = new TsASO(); 
	}	
	
	public TsVOR getVorSubset() {
		return vorSubset;		
	}

	public void setVorSubset(TsVOR vorSubset) {
		this.vorSubset = vorSubset;
	}
    
	public TsHead getHeadSubset() {
		return headSubset;
	}
	public void setHeadSubset(TsHead head) {
		this.headSubset = head;
	}
	
	public void setAsoSubset(TsASO aso) {
		this.asoSubset = aso;
    }

	public TsASO getAsoSubset() {
		return asoSubset;
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
		if (asoSubset != null && !asoSubset.isEmpty()) {			
			res = appendString(res, asoSubset.teilsatzBilden());
		}
		return res;
	}
	
    public void readMessage(BufferedReader message) throws FssException {
        try {
            String line = "";

            while ((line = message.readLine()) != null) {
                String lineType = line.substring(0, 3);
                Utils.log("linetype " + lineType);
                if (lineType.equalsIgnoreCase("ASO")) {
                	asoSubset = new TsASO();
                    String[] aso = line.split("" + FssUtils.FSS_FS);
                    asoSubset.setFields(aso);
                } else if (lineType.equalsIgnoreCase("NAC")) {
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


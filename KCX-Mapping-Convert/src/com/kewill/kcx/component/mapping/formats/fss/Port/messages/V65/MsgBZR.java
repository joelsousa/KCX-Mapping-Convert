package com.kewill.kcx.component.mapping.formats.fss.Port.messages.V65;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import java.util.Vector;

import com.kewill.kcx.component.mapping.formats.fss.Import.messages.FssMessage;
import com.kewill.kcx.component.mapping.formats.fss.Port.subsets.V65.TsAFE;
import com.kewill.kcx.component.mapping.formats.fss.Port.subsets.V65.TsAKO;
import com.kewill.kcx.component.mapping.formats.fss.Port.subsets.V65.TsMIK;
import com.kewill.kcx.component.mapping.formats.fss.Port.subsets.V65.TsSTR;
import com.kewill.kcx.component.mapping.formats.fss.Port.subsets.V65.TsSTT;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.fss.common.TsHead;
import com.kewill.kcx.component.mapping.formats.fss.common.TsVOR;
import com.kewill.kcx.component.mapping.formats.fss.utils.FssUtils;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module       : Port<br>
 * Created      : 24.10.2011<br>
 * Description	: Rück- / Fehlermeldungen zum Hafenauftrag.
 * 				: nur HEAD ist neu
 * @author iwaniuk
 * @version 1.0.00
 */
public class MsgBZR extends FssMessage {
	
	private TsVOR	vorSubset;
	private TsHead	headSubset;   //EI20130826
	private TsAKO   akoSubset;    //EI20120425	
	private List <TsAFE> afeSubsetList;

	public MsgBZR() {
		super();  
	}
	
	public TsVOR getVorSubset() {
		return vorSubset;
	}
	public void setVorSubset(TsVOR subset) {
		this.vorSubset = subset;
	}
	
	public TsHead getHeadSubset() {
		return headSubset;
	}
	public void setHeadSubset(TsHead head) {
		this.headSubset = head;
	}	
	
	public TsAKO getAkoSubset() {
		return akoSubset;
	}
	public void setAkoSubset(TsAKO subset) {
		this.akoSubset = subset;
	}
    	
	public void setAfeSubsetList(List <TsAFE> list) {
		afeSubsetList = list;
    }
	public List <TsAFE> getAfeSubsetList() {
		return afeSubsetList;
	}
	public void addAfeSubsetList(TsAFE subset) {
		if (afeSubsetList == null) {
			afeSubsetList = new Vector<TsAFE>();	
		}
		afeSubsetList.add(subset);
    }
	
	public String getFssString() throws FssException {
		return getFssString("");
	}
	public String getFssString(String firstTs) throws FssException {
		String res = "";	
		
		if (firstTs != null && firstTs.equalsIgnoreCase("HEAD")) {
			headSubset.mapVor2Head(vorSubset);
			if (headSubset != null && !headSubset.isEmpty()) {		 
				res = appendString(res, headSubset.teilsatzBilden());
			}
		} else {	
			if (vorSubset != null && !vorSubset.isEmpty()) {	 
				res = appendString(res, vorSubset.teilsatzBilden());	
			}
		}	
		
		if (akoSubset != null && !akoSubset.isEmpty()) {	 
			res = appendString(res, akoSubset.teilsatzBilden());	
		}		
		if (afeSubsetList != null) {                     					 			
			for (TsAFE afe : afeSubsetList) {				
				if (afe != null && !afe.isEmpty()) {
					res = appendString(res, afe.teilsatzBilden());
				}
			}
		}
		
		return res;
	}
    public void readMessage(BufferedReader message) throws FssException {
    	try {
            String line = "";
           
            while ((line = message.readLine()) != null) {
                String lineType = line.substring(0, 3);
                Utils.log("linetype " + lineType);
                if (lineType.equalsIgnoreCase("AKO")) {
                	akoSubset = new TsAKO();
                    String[] ako = line.split("" + FssUtils.FSS_FS);
                    akoSubset.setFields(ako);                    
                } else if (lineType.equalsIgnoreCase("AFE")) {
                	TsAFE afeSubset = new TsAFE();
                    String[] afe = line.split("" + FssUtils.FSS_FS);
                    afeSubset.setFields(afe); 
                    addAfeSubsetList(afeSubset);    
                } else if (lineType.equalsIgnoreCase("NAC")) {
                	break;
                } else {
                    throw new FssException("Wrong message line " + lineType);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}


package com.kewill.kcx.component.mapping.formats.fss.Port.messages.V65;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import java.util.Vector;

import com.kewill.kcx.component.mapping.formats.fss.Import.messages.FssMessage;
import com.kewill.kcx.component.mapping.formats.fss.Import.messages.V64.MsgCRLPos;
import com.kewill.kcx.component.mapping.formats.fss.Import.subsets.V64.TsCON;
import com.kewill.kcx.component.mapping.formats.fss.Port.subsets.V65.TsSTR;
import com.kewill.kcx.component.mapping.formats.fss.Port.subsets.V65.TsSTT;
import com.kewill.kcx.component.mapping.formats.fss.Port.subsets.V65.TsTODO;
import com.kewill.kcx.component.mapping.formats.fss.base.subsets.TsBAV;
import com.kewill.kcx.component.mapping.formats.fss.base.subsets.TsBSU;
import com.kewill.kcx.component.mapping.formats.fss.base.subsets.TsBZL;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.fss.common.TsHead;
import com.kewill.kcx.component.mapping.formats.fss.common.TsVOR;
import com.kewill.kcx.component.mapping.formats.fss.utils.FssUtils;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module       : Port<br>
 * Created      : 21.10.2011<br>
 * Description	: Statusmeldungen zum Hafenauftrag .   TODO : Name??? (in doc steht string!!!)
 * 				: nur HEAD ist neu
 * @author iwaniuk
 * @version 1.0.00
 */
public class MsgSTR extends FssMessage {
	
	private TsVOR	vorSubset;
	private TsHead	headSubset;   //EI20130826
	private TsSTR	strSubset;	
	private List <TsSTT> sttSubsetList;

	public MsgSTR() {
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
	public TsSTR getStrSubset() {
		return strSubset;
	}
	public void setStrSubset(TsSTR subset) {
		this.strSubset = subset;
	}
	
	public void setSttSubsetList(List <TsSTT> list) {
		sttSubsetList = list;
    }
	public List<TsSTT> getSttSubsetList() {
		return sttSubsetList;
	}
	public void addSttSubsetList(TsSTT subset) {
		if (sttSubsetList == null) {
			sttSubsetList = new Vector<TsSTT>();	
		}
		sttSubsetList.add(subset);
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
		
		if (strSubset != null && !strSubset.isEmpty()) {	 
			res = appendString(res, strSubset.teilsatzBilden());	
		}		
		if (sttSubsetList != null) {                     					 			
			for (TsSTT stt : sttSubsetList) {				
				if (stt != null && !stt.isEmpty()) {
					res = appendString(res, stt.teilsatzBilden());
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
                if (lineType.equalsIgnoreCase("STR")) {
                	strSubset = new TsSTR();
                    String[] str = line.split("" + FssUtils.FSS_FS);
                    strSubset.setFields(str);                    
                } else if (lineType.equalsIgnoreCase("STT")) {
                	TsSTT sttSubset = new TsSTT();
                    String[] stt = line.split("" + FssUtils.FSS_FS);
                    sttSubset.setFields(stt); 
                    addSttSubsetList(sttSubset);    
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


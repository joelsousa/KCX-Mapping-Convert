package com.kewill.kcx.component.mapping.formats.fss.ncts.messages.V70;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import java.util.Vector;

import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.fss.common.TsHead;
import com.kewill.kcx.component.mapping.formats.fss.common.TsVOR;
import com.kewill.kcx.component.mapping.formats.fss.ncts.messages.FssMessage62;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62.TsVFI;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62.TsVFP;
import com.kewill.kcx.component.mapping.formats.fss.utils.FssUtils;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: MsgVFI<br>
 * Created		: 02.09.2009<br>
 * Description	: FSS for Rejection Message.
 * 				: new TsHead, instead of VFU now VFP
 * 
 * @author iwaniuk
 * @version 7.0.00
 */
public class MsgVFI extends FssMessage62 {

	private TsVOR vorSubset;
	private TsHead headSubset;
	private TsVFI vfiSubset;
	private List<TsVFP> vfpList;   
	
	public void setVorSubset(TsVOR vor) {
		this.vorSubset = vor;
	}	
	public TsVOR getVorSubset() {
		return vorSubset;
	}
	
	public TsHead getHeadSubset() {
		return headSubset;
	}
	public void setHeadSubset(TsHead head) {
		this.headSubset = head;
	}	
			
	public void setVfiSubset(TsVFI vfiSubset) {
		this.vfiSubset = vfiSubset;
	}
	public TsVFI getVfiSubset() {
		return vfiSubset;
	}
	
	public List<TsVFP> getVfpList() {
		return vfpList;
	}
	public void setVfpList(List<TsVFP> vfpList) {
		this.vfpList = vfpList;
	}
	public void addVfpList(TsVFP pos) {
		if (pos == null) {
		    return;
		}		
		if (vfpList == null) {
			vfpList = new Vector<TsVFP>();			
		}		
		this.vfpList.add(pos);
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
				
		if (vfiSubset != null) {     //EI20111011 im Hauptsatz keine isEmpty Abfrage
			res = appendString(res, vfiSubset.teilsatzBilden());
		}
		if (vfpList != null) {
			for (TsVFP vfpSubset : vfpList) {					
				if (vfpSubset != null && !vfpSubset.isEmpty()) {
					res = appendString(res, vfpSubset.teilsatzBilden());
				}
			}
		}
		return res;
	}	
	
	public void readMessage(BufferedReader message) throws FssException {
        try {
            String line = "";
            TsVFI vfiSubset = null;
            List<TsVFP> vfpList = null;
            
            while ((line = message.readLine()) != null) {
                String lineType = "";
                if (line.length() > 3) {
                	lineType = line.substring(0, 3);                
                	Utils.log("linetype " + lineType);
                }
                
                if (lineType.equalsIgnoreCase("VFI")) {
                	vfiSubset = new TsVFI();
                    String[] vfi = line.split("" + FssUtils.FSS_FS);
                    vfiSubset.setFields(vfi);
                    setVfiSubset(vfiSubset);
                } else if (lineType.equalsIgnoreCase("VFP")) {    
                	TsVFP vfpSubset = new TsVFP();
                    String[] vfp = line.split("" + FssUtils.FSS_FS);
                    vfpSubset.setFields(vfp);  
                    
                    if (vfpList == null) {
                    	vfpList = new Vector<TsVFP>();
                    }
                    
                    addVfpList(vfpSubset);
                }  else if (lineType.equalsIgnoreCase("NAC")) {
					Utils.log("ignore linetype NAC");  
                } else if (lineType.equals("")) {   //EI20110526
					Utils.log("lineType " + lineType);
                } else {
                    throw new FssException("Wrong message line " + lineType);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
		
}


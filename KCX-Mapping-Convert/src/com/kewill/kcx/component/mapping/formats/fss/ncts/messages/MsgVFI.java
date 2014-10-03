package com.kewill.kcx.component.mapping.formats.fss.ncts.messages;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import java.util.Vector;

import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62.TsVFI;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62.TsVFU;
import com.kewill.kcx.component.mapping.formats.fss.utils.FssUtils;

import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.fss.common.TsVOR;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: MsgVFI<br>
 * Created		: 02.09.2009<br>
 * Description	: FSS for Rejection Message.
 * 
 * @author Edwin Bautista
 * @version 6.2.00
 */
public class MsgVFI extends FssMessage62 {

	private TsVOR vorSubset;
	private TsVFI vfiSubset;
	private List<TsVFU> vfuList;   
	
	public String getFssString() throws FssException {
		String res = "";
		TsVFU vfuSubset;
		
		res = appendString(res, vorSubset.teilsatzBilden());
		
		//EI20111011: if (vfiSubset != null && !vfiSubset.isEmpty()) {
		if (vfiSubset != null) {     //EI20111011 im Hauptsatz keine isEmpty Abfrage
			res = appendString(res, vfiSubset.teilsatzBilden());
		}
		if (vfuList != null) {
			for (int i = 0; i < vfuList.size(); i++) {
				vfuSubset = vfuList.get(i);			
				if (vfuSubset != null && !vfuSubset.isEmpty()) {
					res = appendString(res, vfuSubset.teilsatzBilden());
				}
			}
		}
		return res;
	}	
	
	public void readMessage(BufferedReader message) throws FssException {
        try {
            String line = "";
            TsVFI vfiSubset = null;
            List<TsVFU> vfuList = null;
            
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
                } else if (lineType.equalsIgnoreCase("VFU")) {    
                	TsVFU vfuSubset = new TsVFU();
                    String[] vfu = line.split("" + FssUtils.FSS_FS);
                    vfuSubset.setFields(vfu);  
                    
                    if (vfuList == null) {
                    	vfuList = new Vector<TsVFU>();
                    }
                    
                    addVfuList(vfuSubset);
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
	
	public void setVorSubset(TsVOR vor) {
		this.vorSubset = vor;
	}

	public TsVFI getVfiSubset() {
		return vfiSubset;
	}

	public void addVfuList(TsVFU pos) {
		if (pos == null) {
		    return;
		}		
		if (vfuList == null) {
			vfuList = new Vector<TsVFU>();			
		}		
		this.vfuList.add(pos);
	}
	
	public List<TsVFU> getVfuList() {
		return this.vfuList;
	}
	
	public TsVOR getVorSubset() {
		return vorSubset;
	}
			
	public void setVfiSubset(TsVFI vfiSubset) {
		this.vfiSubset = vfiSubset;
	}
}


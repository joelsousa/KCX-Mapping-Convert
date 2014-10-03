package com.kewill.kcx.component.mapping.formats.fss.ncts.messages;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import java.util.Vector;

import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.fss.common.TsVOR;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62.TsVFP;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62.TsVGP;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62.TsVKA;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62.TsVRL;
import com.kewill.kcx.component.mapping.formats.fss.ncts.subsets.v62.TsVSO;
import com.kewill.kcx.component.mapping.formats.fss.utils.FssUtils;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: MsgVSO<br>
 * Created		: 11.05.2011<br>
 * Description	: FSS for WriteOffNotification Message.
 * 
 * @author iwaniuk
 * @version 6.2.00
 */
public class MsgVSO extends FssMessage62 {

	private TsVOR vorSubset;
	private TsVSO vsoSubset;
	private TsVKA vkaSubset;
	private TsVRL vrlSubset;
	private List<TsVGP>	vgpList;
	private List<TsVFP> vfpList;
	
	public String getFssString() throws FssException {
		String res = "";
		
		res = appendString(res, vorSubset.teilsatzBilden());
		
		//if (vsoSubset != null && !vsoSubset.isEmpty()) {
		if (vsoSubset != null) {	//EI20111011 im Hauptsatz keine isEmpty Abfrage
			res = appendString(res, vsoSubset.teilsatzBilden());
		}
		if (vkaSubset != null && !vkaSubset.isEmpty()) {
			res = appendString(res, vkaSubset.teilsatzBilden());
		}
		if (vrlSubset != null && !vrlSubset.isEmpty()) {
			res = appendString(res, vrlSubset.teilsatzBilden());
		}
		if (vgpList != null) {
			for (TsVGP vgpSubset : vgpList) {
				if (vgpSubset != null && !vgpSubset.isEmpty()) {
					res = appendString(res, vgpSubset.teilsatzBilden());
				}
			}
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
            TsVSO vsoSubset = null;           
            
            while ((line = message.readLine()) != null) {
                String lineType = "";
                if (line.length() > 3) {
                	lineType = line.substring(0, 3);                
                    Utils.log("linetype " + lineType);
                }
                if (lineType.equalsIgnoreCase("VSO")) {
                	vsoSubset = new TsVSO();
                    String[] vso = line.split("" + FssUtils.FSS_FS);
                    vsoSubset.setFields(vso);
                    setVsoSubset(vsoSubset);
                } else if (lineType.equalsIgnoreCase("VKA")) {    
                	TsVKA vkaSubset = new TsVKA();
                    String[] vka = line.split("" + FssUtils.FSS_FS);
                    vkaSubset.setFields(vka);  
                    setVkaSubset(vkaSubset);
                } else if (lineType.equalsIgnoreCase("VRL")) {    
                	TsVRL vrlSubset = new TsVRL();
                    String[] vrl = line.split("" + FssUtils.FSS_FS);
                    vrlSubset.setFields(vrl);  
                    setVrlSubset(vrlSubset);
                } else if (lineType.equalsIgnoreCase("VGP")) {    
                	TsVGP vgpSubset = new TsVGP();
                    String[] vgp = line.split("" + FssUtils.FSS_FS);
                    vgpSubset.setFields(vgp);  
                    if (vgpList == null) {
                    	vgpList = new Vector<TsVGP>();	
                    }
                    vgpList.add(vgpSubset);
                } else if (lineType.equalsIgnoreCase("VFP")) {    
                	TsVFP vfpSubset = new TsVFP();
                    String[] vfp = line.split("" + FssUtils.FSS_FS);
                    vfpSubset.setFields(vfp);  
                    if (vfpList == null) {
                    	vfpList = new Vector<TsVFP>();	
                    }
                    vfpList.add(vfpSubset);                   
                } else if (lineType.equalsIgnoreCase("NAC")) {    
                	Utils.log("ignore linetype NAC");   
                } else if (lineType.equals("")) {   //EI20110526
                	Utils.log("empty line in FSS-message");
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

	public TsVOR getVorSubset() {
		return vorSubset;
	}			
			
	public void setVsoSubset(TsVSO subset) {
		this.vsoSubset = subset;
	}
	public TsVSO getVsoSubset() {
		return vsoSubset;
	}
	
	public void setVkaSubset(TsVKA subset) {
		this.vkaSubset = subset;
	}
	public TsVKA getVkaSubset() {
		return vkaSubset;
	}
	
	public void setVrlSubset(TsVRL subset) {
		this.vrlSubset = subset;
	}
	public TsVRL getVrlSubset() {
		return vrlSubset;
	}
	
	public void setVgpList(List<TsVGP> list) {
		this.vgpList = list;
	}
	public List<TsVGP> getVgpList() {
		return this.vgpList;
	}

	public void addVgpList(TsVGP subset) {
		if (subset == null) {
			return;
		}		
		if (vgpList == null) {
			vgpList = new Vector<TsVGP>();
		}		
		this.vgpList.add(subset);
	}
	
	public void setVfpList(List<TsVFP> list) {
		this.vfpList = list;
	}
	public List<TsVFP> getVfpList() {
		return this.vfpList;
	}

	public void addVfpList(TsVFP subset) {
		if (subset == null) {
			return;
		}		
		if (vfpList == null) {
			vfpList = new Vector<TsVFP>();
		}		
		this.vfpList.add(subset);
	}
}


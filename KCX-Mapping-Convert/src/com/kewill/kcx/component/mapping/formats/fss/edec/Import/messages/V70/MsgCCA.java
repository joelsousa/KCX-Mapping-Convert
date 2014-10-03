package com.kewill.kcx.component.mapping.formats.fss.edec.Import.messages.V70;

import java.io.BufferedReader;
import java.io.IOException;

import com.kewill.kcx.component.mapping.formats.fss.Import.messages.FssMessage;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.fss.common.TsHead;
import com.kewill.kcx.component.mapping.formats.fss.common.TsVOR;
import com.kewill.kcx.component.mapping.formats.fss.edec.Import.subsets.V70.TsAB1;
import com.kewill.kcx.component.mapping.formats.fss.edec.Import.subsets.V70.TsAB2;
import com.kewill.kcx.component.mapping.formats.fss.edec.Import.subsets.V70.TsABP;
import com.kewill.kcx.component.mapping.formats.fss.edec.Import.subsets.V70.TsADR;
import com.kewill.kcx.component.mapping.formats.fss.edec.Import.subsets.V70.TsCCA;
import com.kewill.kcx.component.mapping.formats.fss.utils.FssUtils;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: EDEC Import<br>
 * Created		: 06.11.2012<br>
 * Description  : FSS Definition: Zollquittungen und Bordereau der Abgaben. 
 * 
 * @author iwaniuk
 * @version 7.0.00
 */

public class MsgCCA extends FssMessage {

	private TsVOR	vorSubset;
	private TsHead	headSubset;
	private TsCCA	ccaSubset = null;  	
	
	public MsgCCA() {
		super();  
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
	
	public TsCCA getCCASubset() {
		return ccaSubset;
	}
	public void setCCASubset(TsCCA vor) {
		this.ccaSubset = vor;
	}
	
//--------------------	
	public String getFssString() throws FssException {	  //EI20121107
		return getFssString("");
	}
	public String getFssString(String firstTs) throws FssException {		
		StringBuffer res = new StringBuffer();	
		
		if (firstTs != null && firstTs.equalsIgnoreCase("HEAD")) {		
			//headSubset.mapVor2Head(vorSubset);
			if (headSubset != null && !headSubset.isEmpty()) {		 
				res.append(headSubset.teilsatzBilden() + Utils.LF);
			}
		} else {	
			if (vorSubset != null) { 		 
				res.append(vorSubset.teilsatzBilden() + Utils.LF);
			}
		}
		if (ccaSubset != null) {
			res.append(ccaSubset.teilsatzBilden() + Utils.LF);
		}
		
		return res.toString(); 	
	}	
	
	 public void readMessage(BufferedReader message) throws FssException {
	    	
	        try {
	            String line = "";
	           
	            while ((line = message.readLine()) != null) {
	                String lineType = line.substring(0, 3);
	                Utils.log("linetype " + lineType);
	               
	                if (lineType.equalsIgnoreCase("CCA")) {
	                	ccaSubset = new TsCCA();
	                    String[] cca = line.split("" + FssUtils.FSS_FS);
	                    ccaSubset.setFields(cca);	                         
	                } else if (lineType.equalsIgnoreCase("NAC")) {
	                	// Nachlaufsatz NAC nicht verarbeiten
	                } else {
	                    throw new FssException("Wrong message line " + lineType);
	                }
	            }	            
	            	            
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        
	    }
	
}

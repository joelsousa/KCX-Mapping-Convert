package com.kewill.kcx.component.mapping.formats.fss.edec.Import.messages.V70;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import java.util.Vector;

import com.kewill.kcx.component.mapping.formats.fss.Import.messages.FssMessage;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.fss.common.TsHead;
import com.kewill.kcx.component.mapping.formats.fss.common.TsVOR;
import com.kewill.kcx.component.mapping.formats.fss.edec.Import.subsets.V70.TsAB1;
import com.kewill.kcx.component.mapping.formats.fss.edec.Import.subsets.V70.TsAB2;
import com.kewill.kcx.component.mapping.formats.fss.edec.Import.subsets.V70.TsABP;
import com.kewill.kcx.component.mapping.formats.fss.edec.Import.subsets.V70.TsADR;
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

public class MsgCTXCH extends FssMessage {

	private TsVOR	vorSubset;
	private TsHead	headSubset;
	private TsAB1	ab1Subset = null;  	
	private TsAB2	ab2Subset = null;    
	private TsADR 	consignor = null; 
	private TsADR 	consignee = null;
	private TsADR 	declarant = null;
	private TsADR 	representative = null;
	private List <TsABP> abpList;         	

	public MsgCTXCH() {
		super();  
	}
	
	public TsVOR getVorSubset() {
		return vorSubset;
	}
	public void setVorSubset(TsVOR ts) {
		this.vorSubset = ts;
	}
	
	public TsHead getHeadSubset() {
		return headSubset;
	}
	public void setHeadSubset(TsHead ts) {
		this.headSubset = ts;
	}
	
	public TsAB1 getAB1Subset() {
		return ab1Subset;
	}
	public void setAB1Subset(TsAB1 vor) {
		this.ab1Subset = vor;
	}
	
	public TsAB2 getAB2Subset() {
		return ab2Subset;	
	}
	public void setAB2Subset(TsAB2 argument) {
		if (argument == null) {
			return;
		}
		if (ab2Subset == null) {
			ab2Subset = new TsAB2();
		}
		this.ab2Subset = argument;
	}	

	public TsADR getConsignor() {
		return consignor;	
	}
	public void setConsignor(TsADR argument) {
		if (argument == null) {
			return;
		}
		if (consignor == null) {
			consignor = new TsADR();
		}
		this.consignor = argument;
	}
	
	public TsADR getConsignee() {
		return consignee;	
	}
	public void setConsignee(TsADR argument) {
		if (argument == null) {
			return;
		}
		if (consignee == null) {
			consignee = new TsADR();
		}
		this.consignee = argument;
	}	
	
	public TsADR getDeclarant() {
		return declarant;	
	}
	public void setDeclarant(TsADR argument) {
		if (argument == null) {
			return;
		}
		if (declarant == null) {
			declarant = new TsADR();
		}
		this.declarant = argument;
	}	
	
	public TsADR getRepresentative() {
		return representative;	
	}
	public void setRepresentative(TsADR argument) {
		if (argument == null) {
			return;
		}
		if (representative == null) {
			representative = new TsADR();
		}
		this.representative = argument;
	}			
		
	public List<TsABP> getABPList() {
		return abpList;	
	}

	public void setABPList(List<TsABP> list) {
		this.abpList = list;
	}

	public void addABPList(TsABP argument) {
		if (argument == null) {
			return;			
		}
		if (abpList == null) {
			abpList = new Vector<TsABP>();
		}
		this.abpList.add(argument);
	}
	
//--------------------	
	
	/* z.Z nicht verwendet:
	public String writeCTX() throws FssException {		
		StringBuffer res = new StringBuffer();	
		
		if (vorSubset != null) { 		 
			res.append(vorSubset.teilsatzBilden() + Utils.LF);
		}
		if (ab1Subset != null) {
			res.append(ab1Subset.teilsatzBilden() + Utils.LF);
		}
		if (ab2Subset != null) {
			res.append(ab2Subset.teilsatzBilden() + Utils.LF);
		}
		if (consignor != null && !consignor.isEmpty()) {
			res.append(consignor.teilsatzBilden() + Utils.LF);
		}
		if (consignee != null && !consignee.isEmpty()) {
			res.append(consignee.teilsatzBilden() + Utils.LF);	
		}		
		if (declarant != null && !declarant.isEmpty()) {
			res.append(declarant.teilsatzBilden() + Utils.LF);
		}
		if (representative != null && !representative.isEmpty()) {
			res.append(representative.teilsatzBilden() + Utils.LF);	
		}
				
		if (abpList != null) {
			res.append(writeABPList());
		}
		
		return res.toString(); 	
	}

	private String writeABPList() throws FssException {
		StringBuffer stb = new StringBuffer();
		if (abpList != null) {								
			for (TsABP abpSubset : abpList) {							
				if (abpSubset != null && !abpSubset.isEmpty()) {					
					stb.append(abpSubset.teilsatzBilden() + Utils.LF);	
				}
			}
		}
		return stb.toString();
	}
	 */
	 public void readMessage(BufferedReader message) throws FssException {
	    	
	        try {
	            String line = "";
	           
	            while ((line = message.readLine()) != null) {
	                String lineType = line.substring(0, 3);
	                Utils.log("linetype " + lineType);
	               
	                if (lineType.equalsIgnoreCase("AB1")) {
	                	ab1Subset = new TsAB1();
	                    String[] ab1 = line.split("" + FssUtils.FSS_FS);
	                    ab1Subset.setFields(ab1);
	                } else if (lineType.equalsIgnoreCase("AB2")) {
	                	ab2Subset = new TsAB2();
	                    String[] ab2 = line.split("" + FssUtils.FSS_FS);
	                    ab2Subset.setFields(ab2);	                	                
	                } else if (lineType.equalsIgnoreCase("ADR")) {  
	                	TsADR adrSubset = new TsADR();
	                    String[] adr = line.split("" + FssUtils.FSS_FS);
	                    adrSubset.setFields(adr);
	                    if (adrSubset.getTyp().equals("03")) {
	                    	consignor = adrSubset;
	                    } else if (adrSubset.getTyp().equals("04")) {
	                    	consignee = adrSubset;
	                    } else if (adrSubset.getTyp().equals("05")) {
	                    	declarant = adrSubset;
	                    } else if (adrSubset.getTyp().equals("06")) {
	                    	representative = adrSubset;	 
	                    }
	                } else if (lineType.equalsIgnoreCase("ABP")) {   
	                	TsABP abpSubset = new TsABP();
	                    String[] abp = line.split("" + FssUtils.FSS_FS);
	                    abpSubset.setFields(abp);                     
	                    addABPList(abpSubset);            
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

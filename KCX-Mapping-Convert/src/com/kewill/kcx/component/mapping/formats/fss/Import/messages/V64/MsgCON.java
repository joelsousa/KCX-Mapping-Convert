package com.kewill.kcx.component.mapping.formats.fss.Import.messages.V64;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import java.util.Vector;

import com.kewill.kcx.component.mapping.formats.fss.Import.messages.FssMessage;
import com.kewill.kcx.component.mapping.formats.fss.Import.subsets.V64.TsCON;
import com.kewill.kcx.component.mapping.formats.fss.Import.subsets.V64.TsEMP;
import com.kewill.kcx.component.mapping.formats.fss.base.subsets.TsBAV;
import com.kewill.kcx.component.mapping.formats.fss.base.subsets.TsBSU;
import com.kewill.kcx.component.mapping.formats.fss.base.subsets.TsBZL;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.fss.common.TsVOR;
import com.kewill.kcx.component.mapping.formats.fss.utils.FssUtils;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: Import<br>
 * Date Created	: 12.09.2011<br>
 * Description	: MsgCRL / ImportDeclarationDecision.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class MsgCON extends FssMessage {
	
	private TsVOR	vorSubset;
	private TsCON   conSubset;    // Kopfteilsatz 	
	private List <TsBSU> bsuList;  // Beendigung SumA 
	private List <TsBZL> bzlList;  // Beendigung Zolllager
	private List <TsBAV> bavList;  // Beendigung AV/UV
	
	public MsgCON() {
		super();  
	}
	
	public TsVOR getVorSubset() {
		return vorSubset;
	}
	public void setVorSubset(TsVOR vorSubset) {
		this.vorSubset = vorSubset;
	}
    
	public void setCONSubset(TsCON subset) {
		conSubset = subset;
    }
	public TsCON getCONSubset() {
		return conSubset;
	}
	
	public void setBSUList(List <TsBSU> list) {
		bsuList = list;
    }
	public List <TsBSU> getBSUList() {
		return bsuList;
	}
	public void addBSUList(TsBSU subset) {
		if (bsuList == null) {
			bsuList = new Vector<TsBSU>();
		}
		bsuList.add(subset);
    }
	
	public void setBZLList(List <TsBZL> list) {
		bzlList = list;
    }
	public List <TsBZL> getBZLList() {
		return bzlList;
	}
	public void addBZLList(TsBZL subset) {
		if (bzlList == null) {
			bzlList = new Vector<TsBZL>();
		}
		bzlList.add(subset);
    }
	
	public void setBAVList(List <TsBAV> list) {
		bavList = list;
    }
	public List <TsBAV> getBAVList() {
		return bavList;
	}
	public void addBAVList(TsBAV subset) {
		if (bavList == null) {
			bavList = new Vector<TsBAV>();
		}
		bavList.add(subset);
    }
		
	public String getFssString() throws FssException {
		String res = "";		
		     //EI20121121: die methode war nicht definiert
		if (vorSubset != null && !vorSubset.isEmpty()) {	 
			res = appendString(res, vorSubset.teilsatzBilden());	
		}		
		if (conSubset != null && !conSubset.isEmpty()) {		
			res = appendString(res, conSubset.teilsatzBilden());
		}
		
		if (bsuList != null) {                     					 			
			for (TsBSU bsu : bsuList) {				
				if (bsu != null && !bsu.isEmpty()) {
					res = appendString(res, bsu.teilsatzBilden());
				}
			}
		}
		if (bzlList != null) {                     					 			
			for (TsBZL bzl : bzlList) {				
				if (bzl != null && !bzl.isEmpty()) {
					res = appendString(res, bzl.teilsatzBilden());
				}
			}
		}
		if (bavList != null) {                     					 			
			for (TsBAV bav : bavList) {				
				if (bav != null && !bav.isEmpty()) {
					res = appendString(res, bav.teilsatzBilden());
				}
			}
		}		
		
		return res;
	}
    public void readMessage(BufferedReader message) throws FssException {
        try {
            String line = "";
            MsgCRLPos pos = null;    
           
            while ((line = message.readLine()) != null) {
                String lineType = line.substring(0, 3);
                Utils.log("linetype " + lineType);
                if (lineType.equalsIgnoreCase("CON")) {
                	conSubset = new TsCON();
                    String[] con = line.split("" + FssUtils.FSS_FS);
                    conSubset.setFields(con);                    
                } else if (lineType.equalsIgnoreCase("BSU")) {
                	TsBSU bsuSubset = new TsBSU();
                    String[] bsu = line.split("" + FssUtils.FSS_FS);
                    bsuSubset.setFields(bsu); 
                    addBSUList(bsuSubset);    
                } else if (lineType.equalsIgnoreCase("BZL")) {
                	TsBZL bzlSubset = new TsBZL();
                    String[] bzl = line.split("" + FssUtils.FSS_FS);
                    bzlSubset.setFields(bzl);   
                    addBZLList(bzlSubset);                    	                    
                } else if (lineType.equalsIgnoreCase("BAV")) {                 	
                	TsBAV bavSubset = new TsBAV();
                    String[] bav = line.split("" + FssUtils.FSS_FS);
                    bavSubset.setFields(bav);    
                    addBAVList(bavSubset);    
                	
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


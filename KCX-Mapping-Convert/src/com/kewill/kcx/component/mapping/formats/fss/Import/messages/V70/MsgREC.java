package com.kewill.kcx.component.mapping.formats.fss.Import.messages.V70;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import java.util.Vector;

import com.kewill.kcx.component.mapping.formats.fss.Import.messages.FssMessage;
import com.kewill.kcx.component.mapping.formats.fss.Import.subsets.V64.TsREC;
import com.kewill.kcx.component.mapping.formats.fss.Import.subsets.V64.TsREP;
import com.kewill.kcx.component.mapping.formats.fss.base.subsets.TsANR;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.fss.common.TsHead;
import com.kewill.kcx.component.mapping.formats.fss.common.TsVOR;
import com.kewill.kcx.component.mapping.formats.fss.utils.FssUtils;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: Import<br>
 * Date Created	: 12.09.2011<br>
 * Description	: MsgREC 
 * 				: nur TsHead ist neu.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */
public class MsgREC extends FssMessage {
	
	private TsVOR	vorSubset;
	private TsHead	headSubset;    	
	private TsREC   recSubset;     // Kopfteilsatz 	
	private TsANR   anrSubset;     // Auftragsnummern
	private List <TsREP> repList;  // Positionssatz
	private boolean recWithErrors = false;
	
	public MsgREC() {
		super();  
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
	
	public void setRECSubset(TsREC subset) {
		recSubset = subset;
    }
	public TsREC getRECSubset() {
		return recSubset;
	}
	
	public void setANRSubset(TsANR argument) {
		anrSubset = argument;
    }
	public TsANR getANRSubset() {
		return anrSubset;
	}
	
	public void setREPList(List <TsREP> list) {
		repList = list;
    }
	public List <TsREP> getREPList() {
		return repList;
	}
	public void addREPList(TsREP subset) {
		if (repList == null) {
			repList = new Vector<TsREP>();
		}
		repList.add(subset);
    }
	

	
	public String getFssString() throws FssException {
		String res = "";	
		
		//z.Z nicht benoetigt
		
		return res;
	}
    public void readMessage(BufferedReader message) throws FssException {
        try {
            String line = "";           
           
            while ((line = message.readLine()) != null) {
                String lineType = line.substring(0, 3);
                Utils.log("linetype " + lineType);
                if (lineType.equalsIgnoreCase("REC")) {
                	recSubset = new TsREC();
                    String[] rec = line.split("" + FssUtils.FSS_FS);
                    recSubset.setFields(rec); 
                } else if (lineType.equalsIgnoreCase("ANR")) {
                	anrSubset = new TsANR();
                	String[] anr = line.split("" + FssUtils.FSS_FS);
                    anrSubset.setFields(anr);     
                } else if (lineType.equalsIgnoreCase("REP")) {
                	TsREP repSubset = new TsREP();
                    String[] rep = line.split("" + FssUtils.FSS_FS);
                    repSubset.setFields(rep); 
                    addREPList(repSubset);       
                    recWithErrors = true;
                	
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

	public boolean isRecWithErrors() {
		return recWithErrors;
	}

	public void setRecWithErrors(boolean recWithErrors) {
		this.recWithErrors = recWithErrors;
	}

}


package com.kewill.kcx.component.mapping.formats.fss.Import.messages.V70;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import java.util.Vector;

import com.kewill.kcx.component.mapping.formats.fss.Import.messages.FssMessage;
import com.kewill.kcx.component.mapping.formats.fss.Import.messages.V64.MsgCRLPos;
import com.kewill.kcx.component.mapping.formats.fss.Import.subsets.V64.TsCRL;
import com.kewill.kcx.component.mapping.formats.fss.Import.subsets.V64.TsCRP;
import com.kewill.kcx.component.mapping.formats.fss.Import.subsets.V64.TsTXT;
import com.kewill.kcx.component.mapping.formats.fss.base.subsets.TsANR;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.fss.common.TsHead;
import com.kewill.kcx.component.mapping.formats.fss.common.TsVOR;
import com.kewill.kcx.component.mapping.formats.fss.utils.FssUtils;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: Import<br>
 * Date Created	: 12.09.2011<br>
 * Description	: MsgCRL / ImportDeclarationDecision.
 *  			: nur TsHead ist neu.
 *  
 * @author iwaniuk
 * @version 1.0.00
 */
public class MsgCRL extends FssMessage {
	
	private TsVOR	vorSubset;
	private TsHead	headSubset;    	
	private TsCRL   crlSubset;    // Kopfteilsatz 
	private TsANR   anrSubset;    // Auftragsnummern
	private List<TsTXT> txtList;  // Texte 
	private List <MsgCRLPos> posList;
	
	public MsgCRL() {
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
    
	public void setCRLSubset(TsCRL subset) {
		crlSubset = subset;
    }

	public TsCRL getCRLSubset() {
		return crlSubset;
	}

	public void setANRSubset(TsANR argument) {
		anrSubset = argument;
    }
	public TsANR getANTSubset() {
		return anrSubset;
	}
	
	public void setTXTList(List <TsTXT> list) {
		txtList = list;
    }
	public List <TsTXT> getTXTList() {
		return txtList;
	}
	public void addTXTList(TsTXT subset) {
		if (txtList == null) {
			txtList = new Vector<TsTXT>();
		}
		txtList.add(subset);
    }
	
	public void setCRLPosList(List <MsgCRLPos> list) {
		posList = list;
    }
	public List <MsgCRLPos> getCRLPosList() {
		return posList;
	}
	
	public void addCRLPosList(MsgCRLPos pos) {
		if (posList == null) {
			posList = new Vector<MsgCRLPos>();	
		}
		posList.add(pos);
	}
	
	public String getFssString() throws FssException {
		String res = "";	
		
		 //z.Z nicht benoetigt
		
		return res;
	}
    public void readMessage(BufferedReader message) throws FssException {
        try {
            String line = "";
            MsgCRLPos pos = null;    
           
            while ((line = message.readLine())!= null) {
                String lineType = line.substring(0, 3);
                Utils.log("linetype " + lineType);
                if (lineType.equalsIgnoreCase("CRL")) {
                	crlSubset = new TsCRL();
                    String[] crl = line.split("" + FssUtils.FSS_FS);
                    crlSubset.setFields(crl);                    
                } else if (lineType.equalsIgnoreCase("ANR")) {
                	anrSubset = new TsANR();
                    String[] anr = line.split("" + FssUtils.FSS_FS);
                    anrSubset.setFields(anr);
                } else if (lineType.equalsIgnoreCase("TXT")) { 
                	TsTXT txtSubset = new TsTXT();
                    String[] txt = line.split("" + FssUtils.FSS_FS);
                    txtSubset.setFields(txt);                                         
                    if (pos == null) {
                    	if (txtList == null) {
                        	txtList = new Vector<TsTXT>();
                        }
                    	txtList.add(txtSubset);
                    } else {                    	
                    	pos.addTXTList(txtSubset);                    	
                    }
                } else if (lineType.equalsIgnoreCase("CRP")) {                 	
                	if (posList == null) {
                		posList = new Vector<MsgCRLPos>();
                	}
                	TsCRP crpSubset = new TsCRP();    
                	String[] crp = line.split("" + FssUtils.FSS_FS);
                	crpSubset.setFields(crp);                 	
                	pos = new MsgCRLPos();
            		pos.setCRPSubset(crpSubset);                	
                	posList.add(pos);  
                	
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


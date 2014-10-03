package com.kewill.kcx.component.mapping.formats.fss.aes.messages.V60;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import java.util.Vector;

import com.kewill.kcx.component.mapping.formats.fss.base.subsets.TsERR;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.fss.common.TsHead;
import com.kewill.kcx.component.mapping.formats.fss.common.TsVOR;
import com.kewill.kcx.component.mapping.formats.fss.utils.FssUtils;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * 
 * Module        :   Export/aes
 * Created       :   04.12.2008
 * Description   :   MsgERR.
 *  
 * @author         Krzoska
 * @version        1.0.00
 */

public class MsgERR  {
	
	private TsVOR	vorSubset;
	private TsHead	headSubset;
	private TsERR   errSubset;
	private List    <TsERR>errList;
	
	public MsgERR() {
		super();
		vorSubset = new TsVOR("");
		headSubset = new TsHead("");  //EI20121204
		errList = new Vector<TsERR>();
	}	
	
	
	public void readMessage(BufferedReader message) throws FssException {
        try {
            String line = "";

            while ((line = message.readLine()) != null) {
                String lineType = line.substring(0, 3);
                Utils.log("linetype " + lineType);
                if (lineType.equalsIgnoreCase("ERR")) {             
                   	errSubset = new TsERR();            		
                    String[] err = line.split("" + FssUtils.FSS_FS);
                    errSubset.setFields(err);
                    errList.add(errSubset);
                }  else if (lineType.equalsIgnoreCase("NAC")) {
                    	// Nachlaufsatz NAC nicht verarbeiten
                } else {
                    throw new FssException("Wrong message line " + lineType);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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
	public TsERR getErrSubset() {
		return errSubset;	
	}

	public void setErrSubset(TsERR errSubset) {
		this.errSubset = errSubset;
	}
	public List<TsERR> getErrList() {
		return errList;	
	}
	public void setErrList(List<TsERR> errList) {
		this.errList = errList;
	}
}

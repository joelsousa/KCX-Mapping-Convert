package com.kewill.kcx.component.mapping.formats.fss.aes.messages.V72;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V72.TsACK;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V72.TsACP;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.fss.common.TsHead;
import com.kewill.kcx.component.mapping.formats.fss.common.TsVOR;
import com.kewill.kcx.component.mapping.formats.fss.utils.FssUtils;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: Export/aes<br>
 * Created		: 29.07.2013<br>
 * Description	: V72 - FSS-Message ACL (KIDS-ControlNotification).
 *				: neue Nachricht in V72: EXPORT Rückgabe Kontrollmaßnahme / ControlNotification
 * 
 * @author iwaniuk
 * @version 2.1.00
 */

public class MsgACL  {

	private TsVOR	vorSubset;
	private TsHead	headSubset;
	private TsACK   ackSubset;   
	private ArrayList <TsACP>	posList;	
	
	
	public MsgACL() {
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
	
	public TsACK getAckSubset() {
		return ackSubset;
	}
	public void setAckSubset(TsACK ack) {
		this.ackSubset = ack;
	}
		
	public ArrayList<TsACP> getPosList() {
		return this.posList;
	}
	public void setPosList(ArrayList<TsACP> list) {
		this.posList = list;
	}
	public void addPosList(TsACP pos) {
		if (pos == null) {
		    return;
		}
		if (posList == null) {
		    posList = new ArrayList<TsACP>();
		}
		this.posList.add(pos);
	}
	
	public String getFssString() throws FssException {	  
		//nicht notwendig, weil zabis2kids, also nur lesen
		Utils.log("(MsgACR EXPORT = " + ackSubset.teilsatzBilden());
		
		return "";
	}
	
	 public void readMessage(BufferedReader message) throws FssException {
	        try {
	            String line = "";

	            while ((line = message.readLine()) != null) {
	                String lineType = line.substring(0, 3);
	                Utils.log("linetype " + lineType);
	                if (lineType.equalsIgnoreCase("ACK")) {
	                	ackSubset = new TsACK();
	                    String[] ack = line.split("" + FssUtils.FSS_FS);
	                    ackSubset.setFields(ack);	                    
	                } else if (lineType.equalsIgnoreCase("ACP")) {
	                	TsACP acpSubset = new TsACP();	                   
	                    String[] acp = line.split("" + FssUtils.FSS_FS);
	                    acpSubset.setFields(acp);
	                    addPosList(acpSubset);
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

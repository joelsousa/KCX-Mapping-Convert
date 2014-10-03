package com.kewill.kcx.component.mapping.formats.fss.aes.messages.V72;

import com.kewill.kcx.component.mapping.countries.common.Party;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V72.TsAXT;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V70.TsADR;
import com.kewill.kcx.component.mapping.formats.fss.common.FssDatei;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.fss.common.TsHead;
import com.kewill.kcx.component.mapping.formats.fss.common.TsVOR;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Module		: Export/aes<br>
 * Created		: 19.09.2012<br>
 * Description	: V70 - FSS-Message AXT (KIDS-ConfirmInvestigation).
 *				: neu in V72: in TsAXT Sachbearbeiter
 * 
 * @author iwaniuk
 * @version 2.1.00
 */

public class MsgAXT extends FssDatei {

	private TsVOR	vorSubset;
	private TsHead	headSubset;
	private TsAXT   axtSubset;   
	private TsADR   declarantSubset;  //anmelder	new for V21
	private TsADR   agentSubset;      //vertreter   new for V21
			
	public MsgAXT(String outFileName) {
		super(outFileName);		

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
		
	public TsAXT getAxtSubset() {
		return axtSubset;
	}
	public void setAxtSubset(TsAXT axt) {
		this.axtSubset = axt;
	}	
	
	public TsADR getDeclarantSubset() {
		return declarantSubset;
	}
	public void setDeclarantSubset(TsADR adr) {
		this.declarantSubset = adr;
	}	
	public void setDeclarant(Party party, String beznr, String posnr) {
		if (party == null) {
			return;
		}
		if (declarantSubset == null) {
			declarantSubset = new TsADR();
		}
		declarantSubset.setAdrSubset(party, "3", beznr, posnr);			
	}
	
	public TsADR getAgentSubset() {
		return agentSubset;
	}
	public void setAgentSubset(TsADR adr) {
		this.agentSubset = adr;
	}	
	public void setAgent(Party party, String beznr, String posnr) {
		if (party == null) {
			return;
		}
		if (agentSubset == null) {
			agentSubset = new TsADR();
		}
		agentSubset.setAdrSubset(party, "4", beznr, posnr);			
	}
    /* this Mehtod will not be used, only direction to Zabis 
	public void readMessage(BufferedReader message) throws FssException {
        try {
            String line = "";

            while ((line = message.readLine()) != null) {
                String lineType = line.substring(0, 3);
                Utils.log("linetype " + lineType);
                if (lineType.equalsIgnoreCase("AXT")) {
                	axtSubset = new TsAXT();            		
                    String[] axt = line.split("" + FssUtils.FSS_FS);
                    axtSubset.setFields(axt); 
                } else if (lineType.equalsIgnoreCase("ADR")) {    //new for V21
                	
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
    */
	public String getFssString() throws FssException {	  //EI20121107
		return getFssString("");
	}
	
	public String getFssString(String firstTs) throws FssException {
		String res = "";	
	
		if (firstTs != null && firstTs.equalsIgnoreCase("HEAD")) {			
			if (headSubset != null && !headSubset.isEmpty()) {		 
				res = appendString(res, headSubset.teilsatzBilden());	
			}
		} else {
			if (vorSubset != null && !vorSubset.isEmpty()) {	 
				res = appendString(res, vorSubset.teilsatzBilden());
			}
		}	
		if (axtSubset != null && !axtSubset.isEmpty()) {			
			res = appendString(res, axtSubset.teilsatzBilden());
		}
		if (declarantSubset != null && !declarantSubset.isEmpty()) {    //new for V21
			res = appendString(res, declarantSubset.teilsatzBilden());
		}		
		if (agentSubset != null && !agentSubset.isEmpty()) {            //new for V21
			res = appendString(res, agentSubset.teilsatzBilden());
		}
		
		Utils.log("(MsgAXT AXT = " + axtSubset.teilsatzBilden());
		
		return res;
	}	
}

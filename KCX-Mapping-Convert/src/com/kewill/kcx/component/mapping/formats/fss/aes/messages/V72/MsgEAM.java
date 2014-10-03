package com.kewill.kcx.component.mapping.formats.fss.aes.messages.V72;

import java.util.List;
import java.util.Vector;

import com.kewill.kcx.component.mapping.countries.common.Party;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.MsgExpEnt;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsAZV;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V70.TsADR;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V70.TsDAT;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V70.TsEAM;
import com.kewill.kcx.component.mapping.formats.fss.common.FssDatei;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.fss.common.TsHead;
import com.kewill.kcx.component.mapping.formats.fss.common.TsVOR;

/**
* Module    	: Export/aes. 
* Created    	: 19.07.2013
* Description   : FSS Message Completion
* 				: ZABIS FSS-Format Version 7.2
* 				: neu in V72: Positionssatz -  Kennzeichen AH-stat Wert, mit 0 senden 				
* 
* @author      : iwaniuk
* @version     : 2.2.00
*/

public class MsgEAM extends FssDatei {

	private TsVOR	vorSubset;
	private TsHead	headSubset;  //EI20120926
	private TsDAT   datSubset;   
	private TsEAM   eamSubset;	
	private TsADR   consignee;   //anmelder=declarant, empfaenger=consignee, endverwender=finaluser 
	private TsADR   declarant;
	private TsADR   finaluser;	     //new for V21
	private List   <MsgEAMPos>posList;	
		
	public MsgEAM(String outFileName) {
		super(outFileName);	
		vorSubset = new TsVOR(""); 
		headSubset = new TsHead("");  
		datSubset = new TsDAT(); 
		eamSubset = new TsEAM(); 
		consignee = new TsADR();
		declarant = new TsADR();
		finaluser = new TsADR();
		posList = new Vector<MsgEAMPos>();		
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
	
	
	public TsDAT getDatSubset() {
		return datSubset;
	}
	public void setDatSubset(TsDAT dat) {
		this.datSubset = dat;
	}	

	public TsEAM getEamSubset() {
		return eamSubset;
	}	
	public void setEamSubset(TsEAM eam) {
		this.eamSubset = eam;
	}
	public void setEamSubset(MsgExpEnt msgExpEnt) {
		if (eamSubset == null) {
			eamSubset = new TsEAM();	
		}
		eamSubset.setEAMSubset(msgExpEnt);
	}
	
	public TsADR getDeclarant() {
		return declarant;
	
	}	
	public void setDeclarant(TsADR adr) {
		this.declarant = adr;
	}
	public void setDeclarant(Party party, String beznr) {
		if (declarant == null) {
			declarant = new TsADR();
		}
		declarant.setAdrSubset(party, "3", beznr, "0");
	}
	
	public TsADR getConsignee() {
		return consignee;
	}
	public void setConsignee(TsADR adr) {
		this.consignee = adr;
	}
	public void setConsignee(Party party, String beznr) {
		if (consignee == null) {
			consignee = new TsADR();
		}
		consignee.setAdrSubset(party, "2", beznr, "0");
	}
	
	public TsADR getFinalUser() {
		return finaluser;
	}
	public void setFinalUser(TsADR adr) {
		this.finaluser = adr;
	}
	public void setFinalUser(Party party, String beznr) {
		if (party == null) {
			return;
		}
		if (finaluser == null) {
			finaluser = new TsADR();
		}
		finaluser.setAdrSubset(party, "6", beznr, "0");		
	}
	
	public List<MsgEAMPos> getPosList() {
		return posList;
	}	
	
	public void addPosList(MsgEAMPos argument) {
		this.posList.add(argument);
	}
	
	
	
/*  z.Z. not in use
	public void readMessage(BufferedReader message) throws FssException {
        try {
            String line = "";
            int dummy;

            while ((line = message.readLine())!=null) {
                String lineType = line.substring(0, 3);
                Utils.log("linetype " + lineType);
                if (lineType.equalsIgnoreCase("DAT")) {
                	datSubset = new TsDAT();            		
                    String[] dat = line.split("" + FssUtils.FSS_FS);
                    datSubset.setFields(dat);                                                                       
                } else if (lineType.equalsIgnoreCase("EAM")) {
                    eamSubset = new TsEAM();
                    String[] eam = line.split("" + FssUtils.FSS_FS);
                    eamSubset.setFields(eam);
                } else if (lineType.equalsIgnoreCase("ADR")) {                	
                	TsADR adrSubset = new TsADR();
                    String[] adr = line.split("" + FssUtils.FSS_FS);
                    adrSubset.setFields(adr); 
          // Consignee(2) only
                    if (adrSubset.getTyp().equals("2")) {
                    	TsADR consignee = new TsADR();
                    	consignee = adrSubset;
                     } else dummy = 1;  
                
                } else if (lineType.equalsIgnoreCase("APO")) {                	                
                	eamPos = new MsgEAMPos();                	                	
                	apoSubset = new TsAPO();
                    String[] apo = line.split("" + FssUtils.FSS_FS);
                    apoSubset.setFields(apo);                     
                    eamPos.setApoSubset(apoSubset);  
                    posList.add(eamPos);    
                } else if (lineType.equalsIgnoreCase("EPO")) {
                    epoSubset = new TsEPO();
                    String[] epo = line.split("" + FssUtils.FSS_FS);
                    epoSubset.setFields(epo); 
                    eamPos.setEpoSubset(epoSubset);                   
                    posList.add(eamPos);                       
                } else if (lineType.equalsIgnoreCase("AZV")) {
                    azvSubset = new TsAZV();
                    String[] azv = line.split("" + FssUtils.FSS_FS);
                    azvSubset.setFields(azv); 
                    eamPos.addAzvList(azvSubset);                     
                }  else if (lineType.equalsIgnoreCase("NAC")) {    //AK20081117
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
		if (datSubset != null && !datSubset.isEmpty()) {		
			res = appendString(res, datSubset.teilsatzBilden());
		}
		if (consignee != null && !consignee.isEmpty()) {
			res = appendString(res, consignee.teilsatzBilden());
		}
		if (declarant != null && !declarant.isEmpty()) {          //EI20120920
			res = appendString(res, declarant.teilsatzBilden());
		}
		if (finaluser != null && !finaluser.isEmpty()) {
			res = appendString(res, finaluser.teilsatzBilden());
		}
		if (eamSubset != null && !eamSubset.isEmpty()) {
			res = appendString(res, eamSubset.teilsatzBilden());
		}
		if (posList != null) {
			for (MsgEAMPos tmpPos : posList) {									
				if (tmpPos.getApoSubset() != null && !tmpPos.getApoSubset().isEmpty()) {
					res = appendString(res, tmpPos.getApoSubset().teilsatzBilden());
				}
				if (tmpPos.getEpoSubset() != null && !tmpPos.getEpoSubset().isEmpty()) {
					res = appendString(res, tmpPos.getEpoSubset().teilsatzBilden());
				}
			    if (tmpPos.getConsignee() != null && !tmpPos.getConsignee().isEmpty()) {
			    	res = appendString(res, tmpPos.getConsignee().teilsatzBilden());				
			    }
			    if (tmpPos.getFinalUser() != null && !tmpPos.getFinalUser().isEmpty()) {
			    	res = appendString(res, tmpPos.getFinalUser().teilsatzBilden());				
			    }
			    if (tmpPos.getAzvList() != null) {
			    	for (TsAZV tmpAZV : tmpPos.getAzvList()) {										
			    		if (tmpAZV != null && !tmpAZV.isEmpty()) {
			    			res = appendString(res, tmpAZV.teilsatzBilden());					
			    		}
			    	}
			    }
			}
		}		
		return res;
	}
			
}


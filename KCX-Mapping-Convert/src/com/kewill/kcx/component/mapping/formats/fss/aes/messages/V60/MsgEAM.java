/*
 * Funktion    : MsgEAM.java
 * Titel       :
 * Erstellt    : 13.10.2008
 * Author      : Kewill CSF / Houdek
 * Beschreibung:
 * Anmerkungen :
 * Parameter   :
 * Rückgabe    : keine
 * Aufruf      :
 *
 */

package com.kewill.kcx.component.mapping.formats.fss.aes.messages.V60;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import java.util.Vector;

import com.kewill.kcx.component.mapping.countries.common.Party;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.MsgExpEnt;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsDAT;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsADR;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsEAM;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsAPO;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsEPO;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V60.TsAZV;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.fss.common.TsVOR;
import com.kewill.kcx.component.mapping.formats.fss.utils.FssUtils;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.formats.fss.common.FssDatei;




public class MsgEAM extends FssDatei {

	private TsVOR	vorSubset;
	private TsDAT   datSubset;   
	private TsEAM   eamSubset;	
	private TsADR   consignee;
	private TsADR   declarant;

	private List   <MsgEAMPos>posList;	
	private MsgEAMPos eamPos;	
	
	private TsAPO   apoSubset;
	private TsEPO   epoSubset;
	private TsAZV   azvSubset;

			
	public MsgEAM(String outFileName) {
		super(outFileName);	
		vorSubset = new TsVOR(""); 
		datSubset = new TsDAT(); 
		eamSubset = new TsEAM(); 
		consignee = new TsADR();
		declarant = new TsADR();
		posList = new Vector<MsgEAMPos>();		
	}	
	public TsVOR getVorSubset() {
		return vorSubset;
	}
	public void setVorSubset(TsVOR vor) {
		this.vorSubset = vor;
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
		if (eamSubset == null)
			eamSubset = new TsEAM();	
		eamSubset.setEAMSubset(msgExpEnt);
	}
	
	public TsADR getDeclarant() {
		return declarant;
	
	}	
	public void setDeclarant(TsADR adr) {
		this.declarant = adr;
	}
	public void setDeclarant(Party party, String beznr) {
		if (declarant == null)
			declarant = new TsADR();	
		declarant.setAdrSubset( party, "3", beznr, "0");
	}
	
	public TsADR getConsignee() {
		return consignee;
	}
	public void setConsignee(TsADR adr) {
		this.consignee = adr;
	}
	public void setConsignee(Party party, String beznr) {
		if (consignee == null)
			consignee = new TsADR();	
		consignee.setAdrSubset(party, "2", beznr, "0");
	}
	
	public List<MsgEAMPos> getPosList() {
		return posList;
	}	
	
	public void addPosList(MsgEAMPos argument) {
		this.posList.add(argument);
	}
	
	
	////
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

	public StringBuffer writeEAM() throws FssException {
		StringBuffer res = new StringBuffer();	
	
		if(vorSubset != null && !vorSubset.isEmpty()) 		 
			res = appendString(res, vorSubset.teilsatzBilden());	
		if(datSubset != null && !datSubset.isEmpty())			
			res = appendString(res, datSubset.teilsatzBilden());
		if(consignee != null && !consignee.isEmpty())
			res = appendString(res, consignee.teilsatzBilden());


		if(eamSubset != null && !eamSubset.isEmpty())
			res = appendString(res, eamSubset.teilsatzBilden());
	

					
		for (int i = 0; i < posList.size(); i++) {			
			MsgEAMPos tmpPos = new MsgEAMPos();
			tmpPos = posList.get(i);
			if( tmpPos.getApoSubset() != null && !tmpPos.getApoSubset().isEmpty())
				res = appendString(res, tmpPos.getApoSubset().teilsatzBilden());
			if( tmpPos.getEpoSubset() != null && !tmpPos.getEpoSubset().isEmpty())
				res = appendString(res, tmpPos.getEpoSubset().teilsatzBilden());					
			//if( tmpPos.getAdrSubset() != null && !tmpPos.getAdrSubset().isEmpty())
			//	res = appendString(res, tmpPos.getAdrSubset().teilsatzBilden());				
			
			for (int j = 0; j < tmpPos.getAzvList().size(); j++) {
				TsAZV tmpAZV = new TsAZV();
				tmpAZV = (TsAZV)tmpPos.getAzvList().get(j);
				if( tmpAZV != null && !tmpAZV.isEmpty())
				res = appendString(res, tmpAZV.teilsatzBilden());					
			}
		}
		
		Utils.log("(MsgEAM DAT = " + datSubset.teilsatzBilden());
		Utils.log("(MsgEAM EAM = " + eamSubset.teilsatzBilden());
		
		return res;
	}
	
	
	private StringBuffer appendString ( StringBuffer in, String appendStr) throws FssException {
		StringBuffer stb = new StringBuffer();
		
		stb.append(in);
		stb.append(appendStr);
		stb.append(Utils.LF);
		return stb;
	}
}


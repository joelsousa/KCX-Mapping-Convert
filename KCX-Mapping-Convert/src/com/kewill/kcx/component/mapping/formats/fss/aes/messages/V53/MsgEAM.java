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

package com.kewill.kcx.component.mapping.formats.fss.aes.messages.V53;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import java.util.Vector;

import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V53.TsDAT;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V53.TsADR;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V53.TsEAM;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V53.TsAPO;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V53.TsEPO;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V53.TsAZV;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.fss.common.TsVOR;
import com.kewill.kcx.component.mapping.formats.fss.utils.FssUtils;
import com.kewill.kcx.component.mapping.util.Utils;
import com.kewill.kcx.component.mapping.formats.fss.common.FssDatei;

/**
 * Modul		: MsgEAM<br>
 * Erstellt		: 13.10.2008<br>
 * Beschreibung	: -.
 * 
 * @author Houdek
 * @version 5.3.00
 */
public class MsgEAM extends FssDatei {

	private TsVOR	vorSubset;
	private TsDAT   datSubset;   
	private TsEAM   eamSubset;	
	private TsADR   consignee;

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

	
	public TsADR getConsignee() {
		return consignee;
	}
	public void setConsignee(TsADR adr) {
		this.consignee = adr;
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

            while ((line = message.readLine()) != null) {
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
                     } else { dummy = 1; }
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
                } else {
                    throw new FssException("Wrong message line " + lineType);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

	public StringBuffer writeEAM() throws FssException {
		StringBuffer res = new StringBuffer();	
	
		if (vorSubset != null && !vorSubset.isEmpty()) {
			res = appendString(res, vorSubset.teilsatzBilden());
		}
		if (datSubset != null && !datSubset.isEmpty()) {
			res = appendString(res, datSubset.teilsatzBilden());
		}
		if (consignee != null && !consignee.isEmpty()) {
			res = appendString(res, consignee.teilsatzBilden());
		}
		if (eamSubset != null && !eamSubset.isEmpty()) {
			res = appendString(res, eamSubset.teilsatzBilden());
		}
						
		for (int i = 0; i < posList.size(); i++) {			
			MsgEAMPos tmpPos = new MsgEAMPos();
			tmpPos = posList.get(i);
			if (tmpPos.getApoSubset() != null && !tmpPos.getApoSubset().isEmpty()) {
				res = appendString(res, tmpPos.getApoSubset().teilsatzBilden());
			}
			if (tmpPos.getEpoSubset() != null && !tmpPos.getEpoSubset().isEmpty()) {
				res = appendString(res, tmpPos.getEpoSubset().teilsatzBilden());
			}
			//if( tmpPos.getAdrSubset() != null && !tmpPos.getAdrSubset().isEmpty())
			//	res = appendString(res, tmpPos.getAdrSubset().teilsatzBilden());
			
			for (int j = 0; j < tmpPos.getAzvList().size(); j++) {
				TsAZV tmpAZV = new TsAZV();
				tmpAZV = (TsAZV) tmpPos.getAzvList().get(j);
				if (tmpAZV != null && !tmpAZV.isEmpty()) {
				    res = appendString(res, tmpAZV.teilsatzBilden());
				}
			}
		}
		
		Utils.log("(MsgEAM DAT = " + datSubset.teilsatzBilden());
		Utils.log("(MsgEAM EAM = " + eamSubset.teilsatzBilden());
		
		return res;
	}
	
	
	private StringBuffer appendString(StringBuffer in, String appendStr) throws FssException {
		StringBuffer stb = new StringBuffer();
		
		stb.append(in);
		stb.append(appendStr);
		stb.append(Utils.LF);
		return stb;
	}
		
}


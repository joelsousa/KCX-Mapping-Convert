/*
 * Funktion    : MsgADP.java
 * Titel       :
 * Erstellt    : 05.09.2008
 * Author      : Elisabeth Iwaniuk
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
import java.io.StringReader;
import java.util.List;
import java.util.Vector;

import org.apache.commons.codec.binary.Base64;

import com.kewill.kcx.component.mapping.countries.common.PDFInformation;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V53.TsACN;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V53.TsACO;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V53.TsADR;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V53.TsAED;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V53.TsAMP;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V53.TsAPO;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V53.TsASP;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V53.TsAVS;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V53.TsAZV;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V53.TsDAE;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V53.TsDAT;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V53.TsEAM;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V53.TsEDA;
import com.kewill.kcx.component.mapping.formats.fss.aes.subsets.V53.TsNVE;
import com.kewill.kcx.component.mapping.formats.fss.common.FssException;
import com.kewill.kcx.component.mapping.formats.fss.common.TsVOR;
import com.kewill.kcx.component.mapping.formats.fss.utils.FssUtils;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Modul		: MsgADP<br>
 * Erstellt		: 05.09.2008<br>
 * Beschreibung	: -.
 * 
 * @author Iwaniuk
 * @version 5.3.00
 */
public class MsgADP {

	private boolean isPosition = false;
	
	private TsVOR	vorSubset;
	private TsDAT   datSubset;   
	private TsEDA   edaSubset;
	private TsDAE   daeSubset;
	private TsASP   aspSubset; 
	private TsAMP   ampSubset;
	private PDFInformation pdfInformation;	// zum Abspeichern einer PDF-Datei falls vorhanden
	private TsADR   adrSubset;
	private List    <TsADR>adrList;
	private TsEAM   eamSubset;   
	private TsAVS   avsSubset;
	private List    <TsAVS>avsList;
	
	private List   <MsgADPos>posList;
	//private MsgADPos[]  posList;
	private MsgADPos adpPos;	
	private int iPosList;
		
	private TsAPO   apoSubset;  	   	
	private TsACO   acoSubset;  
	private TsNVE   nveSubset;      // bis Version 5.3, keine Beschreibung in Excel-kids
	private TsACN   acnSubset;    
	private TsAZV   azvSubset;    
	private TsAED   aedpSubset;  
	
	public MsgADP() {
		super();
		vorSubset = new TsVOR(""); 
	}	
	
	public TsVOR getVorSubset() {
		return vorSubset;
	}
	public void setVorSubset(TsVOR vor) {
		this.vorSubset = vor;
	}
	public PDFInformation getPdfInformation() {
		return pdfInformation;
	}
	public void setPdfInformation(PDFInformation pdfInformation) {
		this.pdfInformation = pdfInformation;
	}
	
	public TsDAT getDatSubset() {
		return datSubset;
	}
	public void setDatSubset(TsDAT dat) {
		this.datSubset = dat;
	}
	
	public TsEDA getEdaSubset() {
		return edaSubset;
	}
	public void setEdaSubset(TsEDA eda) {
		this.edaSubset = eda;
	}
	public TsDAE getDaeSubset() {
		return daeSubset;
	}
	public void setDaeSubset(TsDAE dae) {
		this.daeSubset = dae;
	}	
	public TsASP getAspSubset() {
		return aspSubset;
	}
	public void setAspSubset(TsASP asp) {
		this.aspSubset = asp;
	}	
	public TsAMP getAmpSubset() {
		return ampSubset;
	}
	public void setAmpSubset(TsAMP amp) {
		this.ampSubset = amp;
	}

	public List<TsADR> getAdrList() {
		return adrList;
	}	
	public void addAdrList(TsADR adr) {
		this.adrList.add(adr);
	}
	public TsEAM getEamSubset() {
		return eamSubset;
	}
	public void setEamSubset(TsEAM eam) {
		this.eamSubset = eam;
	}
	public List<TsAVS> getAvsList() {
		return avsList;
	}	
	public void addAvsList(TsAVS avs) {
		this.avsList.add(avs);
	}
	public TsNVE getNveSubset() {
		return nveSubset;
	}
	public void setNveSubset(TsNVE argument) {
		this.nveSubset = argument;
	}		
	
	//public MsgADPos[] getPosList() {
	public List<MsgADPos> getPosList() {
		return posList;
	}	

	public int getPosListLength() {
		return iPosList;
	}
	
	public void readMessage(BufferedReader message) throws FssException {
        try {
            String line = "";
            byte[] out = null;

            while ((line = message.readLine()) != null) {
                String lineType = line.substring(0, 3);
                Utils.log("linetype " + lineType);
                Utils.log("line  =  " + line);
                if (lineType.equalsIgnoreCase("DAT")) {
                	datSubset = new TsDAT();
            		adrList = new Vector<TsADR>();
            		avsList = new Vector<TsAVS>();
            		posList = new Vector<MsgADPos>();            		
                    String[] dat = line.split("" + FssUtils.FSS_FS);
                    datSubset.setFields(dat);
                    isPosition = false;                                       
                    iPosList = 0;
                } else if (lineType.equalsIgnoreCase("EDA")) {
                    edaSubset = new TsEDA();
                    String[] eda = line.split("" + FssUtils.FSS_FS);
                    edaSubset.setFields(eda);
                } else if (lineType.equalsIgnoreCase("DAE")) {
                    daeSubset = new TsDAE();
                    String[] dae = line.split("" + FssUtils.FSS_FS);
                    daeSubset.setFields(dae);
                } else if (lineType.equalsIgnoreCase("ASP")) {
                    aspSubset = new TsASP();
                    String[] asp = line.split("" + FssUtils.FSS_FS);
                    aspSubset.setFields(asp);
                } else if (lineType.equalsIgnoreCase("AMP")) {
                    ampSubset = new TsAMP();
                    String[] amp = line.split("" + FssUtils.FSS_FS);
                    ampSubset.setFields(amp);   
                    pdfInformation = new PDFInformation();
                    pdfInformation.setName(ampSubset.getPdfnam());
                    pdfInformation.setDirectory(ampSubset.getPdfpfd());
                    pdfInformation.setNewDirectory(ampSubset.getSubdir());
                } else if (lineType.equalsIgnoreCase("ADR") && !isPosition) {
                    adrSubset = new TsADR();
                    String[] adr = line.split("" + FssUtils.FSS_FS);
                    adrSubset.setFields(adr); 
                    adrList.add(adrSubset);                    
                } else if (lineType.equalsIgnoreCase("EAM")) {
                    eamSubset = new TsEAM();
                    String[] aem = line.split("" + FssUtils.FSS_FS);
                    eamSubset.setFields(aem);  
                } else if (lineType.equalsIgnoreCase("AVS")) {
                    avsSubset = new TsAVS();
                    String[] avs = line.split("" + FssUtils.FSS_FS);
                    avsSubset.setFields(avs); 
                    avsList.add(avsSubset);                                            
                } else if (lineType.equalsIgnoreCase("AED") && !isPosition) {
                	//in V53 will not be processed
                } else if (lineType.equalsIgnoreCase("APO")) {
                	isPosition = true;                 	
                	adpPos = new MsgADPos();                	                	
                    apoSubset = new TsAPO();
                    String[] apo = line.split("" + FssUtils.FSS_FS);
                    apoSubset.setFields(apo);                     
                    adpPos.setApoSubset(apoSubset);  
                    posList.add(adpPos);
                    //posList[iPosList] = adpPos;
                    iPosList = iPosList + 1;                
                                
                } else if (lineType.equalsIgnoreCase("ACO")) {
                    acoSubset = new TsACO();
                    String[] aco = line.split("" + FssUtils.FSS_FS);
                    acoSubset.setFields(aco); 
                    adpPos.addAcoList(acoSubset);  
                } else if (lineType.equalsIgnoreCase("ADR") && isPosition) {
                	//in V53 will not be processed
                } else if (lineType.equalsIgnoreCase("NVE")) {
                	nveSubset = new TsNVE();
                    String[] nve = line.split("" + FssUtils.FSS_FS);
                    nveSubset.setFields(nve); 
                    adpPos.setNveSubset(nveSubset); 
                } else if (lineType.equalsIgnoreCase("ACN")) {
                    acnSubset = new TsACN();
                    String[] acn = line.split("" + FssUtils.FSS_FS);
                    acnSubset.setFields(acn);  
                    adpPos.addAcnList(acnSubset);   
                } else if (lineType.equalsIgnoreCase("AZV")) {
                    azvSubset = new TsAZV();
                    String[] azv = line.split("" + FssUtils.FSS_FS);
                    azvSubset.setFields(azv);  
                    adpPos.addAzvList(azvSubset);                                                    
                } else if (lineType.equalsIgnoreCase("AED") && isPosition) {
                    aedpSubset = new TsAED();
                    String[] aedp = line.split("" + FssUtils.FSS_FS);
                    aedpSubset.setFields(aedp); 
                    adpPos.addAedList(aedpSubset);   
                } else if (lineType.equalsIgnoreCase("NAC")) {    //AK20081117
                	// Nachlaufsatz NAC nicht verarbeiten
            	} else if (lineType.equalsIgnoreCase("%PD")) {
            	
            		// PDF einlesen: diese Zeile in einen neuen StringBuffer schieben
	            	// danach alle weiteren Zeilen einlesen
            		StringBuffer buff = new StringBuffer();
            		// Utils.log("(MsgADP readMessage) line = " + line);
            		// Utils.log("(MsgADP readMessage) len  = " + line.length());
            		buff.append(line + Utils.LF);
	            	
            		while ((line = message.readLine()) != null) {
                		// Utils.log("(MsgADP readMessage) line = " + line);
                		// Utils.log("(MsgADP readMessage) len  = " + line.length());
            			
	            		buff.append(line + Utils.LF);

            		}
            		out = Base64.encodeBase64Chunked(buff.toString().getBytes());
        	    	String str = new String(out);
        	    	BufferedReader br = new BufferedReader(new StringReader(str));
        	    	String line76 = null;
        			while ((line76 = br.readLine()) != null)  {
        		   		pdfInformation.getPdflist().add(line76);
        	    	}
            	} else {
                    throw new FssException("Wrong message line " + lineType);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
}


